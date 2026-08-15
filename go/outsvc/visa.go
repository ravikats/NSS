package outsvc

import (
	"context"
	"fmt"
	"os"
	"path/filepath"
	"strings"
	"time"
)

// ProcessVisaOutgoing is the Go port of
// VisaOutgoingServiceImpl.generateVisaOutgoing. It generates the Base II 1644
// text file for VISA_ACQ_TXN_WORK rows with gen_status=3 in the date range.
func (s *OutgoingService) ProcessVisaOutgoing(ctx context.Context, insCode, user, formatCode int, insShortName string, fromDate, toDate *time.Time) string {
	intCategory := "VISA"
	now := time.Now()

	fileFormatEntity, err := s.store.FindFileFormatBySystemCodeAndType(ctx, formatCode, "O")
	if err != nil {
		logOutsvc("FindFileFormatBySystemCodeAndType", err)
		return "Failed"
	}
	forCode := 0
	if fileFormatEntity != nil {
		forCode = fileFormatEntity.Code
	}
	interfaces, err := s.store.FindInterfaceByCategory(ctx, intCategory)
	if err != nil {
		logOutsvc("FindInterfaceByCategory", err)
		return "Failed"
	}
	intCode := 0
	if interfaces != nil {
		intCode = interfaces.InterfaceCode
	}
	results, err := s.store.FindFileLogByFormatCodeAndStatuses(ctx, forCode)
	if err != nil {
		logOutsvc("FindFileLogByFormatCodeAndStatuses", err)
		return "Failed"
	}
	if len(results) > 0 {
		return "File Generation already Scheduled"
	}

	outgoingLogSerialNumber := s.inserOutFileLog(ctx, user, insCode, intCode, forCode)
	if outgoingLogSerialNumber == 0 {
		return "Failed"
	}

	acqBinList, err := s.store.FindAcquirerBins(ctx, insCode, "V")
	if err != nil {
		logOutsvc("FindAcquirerBins", err)
		return "Failed"
	}
	acquirerBins := ""
	fileSequence := 0
	if len(acqBinList) > 0 && acqBinList[0] != nil {
		acq := acqBinList[0]
		acquirerBins = acq.Bin
		if acq.OutfileDate != nil && sameCalendarDay(*acq.OutfileDate, now) {
			fileSequence = acq.OutFileSeq
		} else {
			fileSequence = 1
		}
		acq.OutFileSeq = fileSequence + 1
		t := now
		acq.OutfileDate = &t
		if err := s.store.UpdateAcquirerBin(ctx, acq); err != nil {
			logOutsvc("UpdateAcquirerBin", err)
			return "Failed"
		}
	}
	fileName := insShortName + "_" + acquirerBins + "_" + now.Format("02012006") + fmt.Sprintf(".%03d", fileSequence)

	txnCode := []string{"10", "20"}
	var feeEntity, txnEntity, data []*VisaAcqTxnWorkEntity
	if fromDate == nil {
		feeEntity, err = s.store.FindVisaWorkFeeLessThanEqual(ctx, insCode, intCode, 3, txnCode, *toDate)
		if err != nil {
			logOutsvc("FindVisaWorkFeeLessThanEqual", err)
			return "Failed"
		}
		txnEntity, err = s.store.FindVisaWorkTxnLessThanEqual(ctx, insCode, intCode, 3, txnCode, *toDate)
		if err != nil {
			logOutsvc("FindVisaWorkTxnLessThanEqual", err)
			return "Failed"
		}
		data, err = s.store.FindVisaWorkLessThanEqual(ctx, insCode, intCode, 3, *toDate)
		if err != nil {
			logOutsvc("FindVisaWorkLessThanEqual", err)
			return "Failed"
		}
	} else {
		feeEntity, err = s.store.FindVisaWorkFeeBetween(ctx, insCode, intCode, 3, txnCode, *fromDate, *toDate)
		if err != nil {
			logOutsvc("FindVisaWorkFeeBetween", err)
			return "Failed"
		}
		txnEntity, err = s.store.FindVisaWorkTxnBetween(ctx, insCode, intCode, 3, txnCode, *fromDate, *toDate)
		if err != nil {
			logOutsvc("FindVisaWorkTxnBetween", err)
			return "Failed"
		}
		data, err = s.store.FindVisaWorkBetween(ctx, insCode, intCode, 3, *fromDate, *toDate)
		if err != nil {
			logOutsvc("FindVisaWorkBetween", err)
			return "Failed"
		}
	}

	s.updateVisaAcqWork(ctx, insCode, user, intCode, "", 3, fromDate, toDate)
	s.updateOutFilelog(ctx, insCode, outgoingLogSerialNumber, fileName, nil)

	seen := map[string]struct{}{}
	tokens := make([]string, 0, len(data))
	for _, d := range data {
		if d.EncCardNumber == "" {
			continue
		}
		if _, ok := seen[d.EncCardNumber]; ok {
			continue
		}
		seen[d.EncCardNumber] = struct{}{}
		tokens = append(tokens, d.EncCardNumber)
	}
	decrypted := s.crypto.GetCardNumber(tokens)
	if decrypted == nil {
		for _, d := range data {
			d.GeneralStatus = 7
		}
		if err := s.store.UpdateVisaWorkStatuses(ctx, data); err != nil {
			logOutsvc("UpdateVisaWorkStatuses", err)
		}
		s.updateOutFilelog(ctx, insCode, outgoingLogSerialNumber, fileName, nil)
		return "Outgoing Failed"
	}

	var lines []string
	if s.cfg.CurrencyCodeKafka != "NIL" {
		g := NewBaseIIGenerator(s.cfg.CurrencyCodeKafka)
		lines = g.GetFeeAndTxnData(feeEntity, txnEntity, decrypted, acquirerBins, fileSequence, s.updateFailedTxn(ctx))
	}
	fileId := s.writeVisaLinesToFile(lines, insShortName, fileName)

	if fileId != nil && lines != nil {
		s.updateOutFilelog(ctx, insCode, outgoingLogSerialNumber, fileName, fileId)
		s.insertVisaIntoOutgoingSummary(ctx, user, insCode, intCode, fileName, outgoingLogSerialNumber)
		s.updateVisaAcqWork(ctx, insCode, user, intCode, *fileId, 9, fromDate, toDate)
		if err := s.store.CompleteVisaPosStatus(ctx, insCode); err != nil {
			logOutsvc("CompleteVisaPosStatus", err)
		}
		s.moveVisaWorkToData(ctx, insCode, 4)
		// generateOutgoingSummaryPDF is not ported yet.
		return "Success"
	}
	return "Outgoing Failed"
}

// inserOutFileLog mirrors the Java inserOutFileLog helpers (Visa and Jaywan
// share the same shape). The Java business-date lookup hardcodes institution 1;
// we preserve that faithfully.
func (s *OutgoingService) inserOutFileLog(ctx context.Context, user, insCode, intCode, forCode int) int64 {
	now := time.Now()
	entity := &OutGoingFileProcessingEntity{
		LastUpdated:     now,
		GeneratedDate:   now,
		UpdatedUser:     user,
		InstitutionCode: insCode,
		InterfaceCode:   intCode,
		FormatCode:      forCode,
		GeneratedStatus: 9,
	}
	bd, err := s.store.FindBusinessDateByInstitution(ctx, 1)
	if err != nil {
		logOutsvc("FindBusinessDateByInstitution", err)
		return 0
	}
	if bd != nil {
		entity.BussDate = bd.BusinessDate
	}
	serial, err := s.store.InsertFileLog(ctx, entity)
	if err != nil {
		logOutsvc("InsertFileLog", err)
		return 0
	}
	return serial
}

// updateVisaAcqWork mirrors VisaOutgoingServiceImpl.updateVisaAcqWork: status 3
// -> 9 (mark for outgoing) or 9 -> 4 (completed) for rows in the date range.
func (s *OutgoingService) updateVisaAcqWork(ctx context.Context, insCode, user, intCode int, fileId string, currentStatus int, fromDate, toDate *time.Time) {
	var ents []*VisaAcqTxnWorkEntity
	var err error
	if fromDate == nil {
		ents, err = s.store.FindVisaWorkLessThanEqual(ctx, insCode, intCode, currentStatus, *toDate)
	} else {
		ents, err = s.store.FindVisaWorkBetween(ctx, insCode, intCode, currentStatus, *fromDate, *toDate)
	}
	if err != nil {
		logOutsvc("updateVisaAcqWork", err)
		return
	}
	if len(ents) == 0 {
		return
	}
	updatedStatus := 9
	if currentStatus == 9 {
		updatedStatus = 4
	}
	now := time.Now()
	for _, e := range ents {
		e.LastUpdated = now
		e.UpdatedUser = user
		e.GeneralStatus = updatedStatus
		e.FileId = fileId
	}
	if err := s.store.UpdateVisaWorkStatuses(ctx, ents); err != nil {
		logOutsvc("UpdateVisaWorkStatuses", err)
	}
}

// updateOutFilelog mirrors VisaOutgoingServiceImpl.updateOutFilelog: fileId set
// -> status 4 (completed), else status 5 (failed).
func (s *OutgoingService) updateOutFilelog(ctx context.Context, insCode int, serial int64, fileName string, fileId *string) {
	outFileProcEntity, err := s.store.FindFileLogByInstitutionAndSerial(ctx, insCode, serial)
	if err != nil {
		logOutsvc("FindFileLogByInstitutionAndSerial", err)
		return
	}
	if outFileProcEntity == nil {
		return
	}
	outFileProcEntity.LastUpdated = time.Now()
	outFileProcEntity.FileName = fileName
	if fileId != nil {
		outFileProcEntity.FileId = fileId
		outFileProcEntity.GeneratedStatus = 4
	} else {
		outFileProcEntity.GeneratedStatus = 5
	}
	if err := s.store.UpdateFileLog(ctx, outFileProcEntity); err != nil {
		logOutsvc("UpdateFileLog", err)
	}
}

// writeVisaLinesToFile mirrors VisaOutgoingServiceImpl.writeLinesToFile: writes
// the lines to RECON_OUT_{insShortName}/{fileName}, one per line; returns nil
// when lines are nil (Java: write fails / null lines).
func (s *OutgoingService) writeVisaLinesToFile(lines []string, insShortName, fileName string) *string {
	if lines == nil {
		return nil
	}
	var sb strings.Builder
	for _, line := range lines {
		sb.WriteString(line)
		sb.WriteString("\n")
	}
	path := filepath.Join(s.cfg.ReconOutDir, fileName)
	if err := os.WriteFile(path, []byte(sb.String()), 0o644); err != nil {
		logOutsvc("writeLinesToFile", err)
		return nil
	}
	f := fileName
	return &f
}

// insertVisaIntoOutgoingSummary mirrors VisaOutgoingServiceImpl.
// insertIntoOutgoingSummary: groups the gen_status=9 rows by txn code and
// writes one OUTGOING_SUMMARY row per group.
func (s *OutgoingService) insertVisaIntoOutgoingSummary(ctx context.Context, user, insCode, intCode int, fileName string, outgoingLogSerialNumber int64) {
	ents, err := s.store.FindVisaWorkByStatus(ctx, insCode, 9)
	if err != nil {
		logOutsvc("FindVisaWorkByStatus", err)
		return
	}
	groups := map[string]*struct {
		count           int
		totalTxnAmount  float64
		totalSchgAmount float64
	}{}
	for _, e := range ents {
		g := groups[e.TxnCode]
		if g == nil {
			g = &struct {
				count           int
				totalTxnAmount  float64
				totalSchgAmount float64
			}{}
			groups[e.TxnCode] = g
		}
		g.count++
		g.totalTxnAmount += e.TxnAmount
		g.totalSchgAmount += e.SchgAmount
	}
	now := time.Now()
	for txnCode, totals := range groups {
		ots := &OutgoingSummaryEntity{
			LastUpdated:     now,
			UpdatedUser:     user,
			InstitutionCode: insCode,
			InterfaceCode:   intCode,
			OutFileDate:     dateOnly(now),
			FileId:          fileName,
			RefSerialNumber: outgoingLogSerialNumber,
			MessageTypeId:   txnCode,
			FunctionCode:    "1",
			ProcCode:        "",
			Count:           totals.count,
			Amount:          totals.totalTxnAmount,
			SurchargeAmount: totals.totalSchgAmount,
			NetAmount:       totals.totalTxnAmount + totals.totalSchgAmount,
			GeneralStatus:   3,
		}
		if err := s.store.InsertSummaries(ctx, []*OutgoingSummaryEntity{ots}); err != nil {
			logOutsvc("InsertSummaries", err)
		}
	}
}

// moveVisaWorkToData mirrors VisaOutgoingServiceImpl.moveWorkToData: copies the
// gen_status=4 work rows into VISA_ACQ_TXN_DATA (serial preserved, lastUpdated
// reset to now) and deletes them from the work table.
func (s *OutgoingService) moveVisaWorkToData(ctx context.Context, insCode, status int) {
	ents, err := s.store.FindVisaWorkByStatus(ctx, insCode, status)
	if err != nil {
		logOutsvc("FindVisaWorkByStatus", err)
		return
	}
	if ents == nil {
		return
	}
	now := time.Now()
	dataEntities := make([]*VisaAcqTxnDataEntity, 0, len(ents))
	for _, e := range ents {
		d := *e
		d.LastUpdated = now
		dataEntities = append(dataEntities, &d)
	}
	if err := s.store.InsertVisaData(ctx, dataEntities); err != nil {
		logOutsvc("InsertVisaData", err)
		return
	}
	s.deleteVisaFromWork(ctx, insCode)
}

// deleteVisaFromWork mirrors VisaOutgoingServiceImpl.deleteFromVisaAcqWork: the
// most recently updated status-4 file log's fileId identifies the work rows to
// delete (after they were moved to the data table).
func (s *OutgoingService) deleteVisaFromWork(ctx context.Context, insCode int) {
	fileLog, err := s.store.FindFileLogTopByGeneratedStatus(ctx, 4)
	if err != nil {
		logOutsvc("FindFileLogTopByGeneratedStatus", err)
		return
	}
	if fileLog == nil || fileLog.FileId == nil {
		return
	}
	visaWork, err := s.store.FindVisaWorkByFileId(ctx, insCode, *fileLog.FileId)
	if err != nil {
		logOutsvc("FindVisaWorkByFileId", err)
		return
	}
	if len(visaWork) > 0 {
		if err := s.store.DeleteVisaWork(ctx, visaWork); err != nil {
			logOutsvc("DeleteVisaWork", err)
		}
	}
}

// updateFailedTxn returns the callback wired into BaseII GetFeeAndTxnData; it
// mirrors BaseIIOutgoingServiceImpl.updateFailedTxn (sets gen_status=7 on the
// matching work rows, identified by ARN). Builds a by-ARN index from the
// entities that are being formatted.
func (s *OutgoingService) updateFailedTxn(ctx context.Context) func(arn string) {
	return func(arn string) {
		if arn == "" {
			return
		}
		ents, err := s.store.FindVisaWorkByArn(ctx, arn)
		if err != nil {
			logOutsvc("FindVisaWorkByArn", err)
			return
		}
		if len(ents) == 0 {
			return
		}
		for _, e := range ents {
			e.GeneralStatus = 7
		}
		if err := s.store.UpdateVisaWorkStatuses(ctx, ents); err != nil {
			logOutsvc("UpdateVisaWorkStatuses", err)
		}
	}
}

// mapVisaDataToWork mirrors OutGoingProcessingService.mapToVisaAcqWorkEntity:
// the serial number is preserved and gen_status set to 3 (revert).
func mapVisaDataToWork(d *VisaAcqTxnDataEntity, now time.Time) *VisaAcqTxnWorkEntity {
	w := *d
	w.LastUpdated = now
	w.GeneralStatus = 3
	return &w
}
