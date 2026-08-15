package outsvc

import (
	"context"
	"fmt"
	"os"
	"path/filepath"
	"strings"
	"time"
)

// ProcessJaywanOutgoing generates the Jaywan XML clearing file (UAE Switch
// Clearing Specification V1.3) for JAYWAN_ACQ_TXN_WORK rows with gen_status=3
// in the date range. The XML layout, header/txn/trailer field sets and compact
// one-record-per-line style follow jaywan.xml (the reference sample).
func (s *OutgoingService) ProcessJaywanOutgoing(ctx context.Context, insCode, user, formatCode int, insShortName string, fromDate, toDate *time.Time) string {
	intCategory := "JAYWAN"
	now := s.now()

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

	acqBinList, err := s.store.FindAcquirerBins(ctx, insCode, "J")
	if err != nil {
		logOutsvc("FindAcquirerBins", err)
		return "Failed"
	}
	// Java throws IllegalStateException("No Acquirer Bins available") when the
	// list is empty; that bubbles up to the outer catch -> "Failed".
	if len(acqBinList) == 0 || acqBinList[0] == nil {
		return "Failed"
	}
	acq := acqBinList[0]
	fileSequence := 0
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

	year := now.Year()
	dayOfYear := now.YearDay()
	julianDateStr := fmt.Sprintf("%02d%03d", year%100, dayOfYear)
	// File Type (2) "00" + Clearing Cycle (1) "0" + Participant ID (9) +
	// Julian Date (5) + Sequence (2) => "000" + participant + julian + seq.
	fileId := "000" + strOrNull(acq.ParticipantId) + julianDateStr + fmt.Sprintf("%02d", fileSequence)
	fileName := fileId + ".xml"

	var entities []*JaywanAcqTxnWorkEntity
	if fromDate == nil {
		entities, err = s.store.FindJaywanWorkLessThanEqual(ctx, insCode, intCode, 3, *toDate)
	} else {
		entities, err = s.store.FindJaywanWorkBetween(ctx, insCode, intCode, 3, *fromDate, *toDate)
	}
	if err != nil {
		logOutsvc("FindJaywanWork", err)
		return "Failed"
	}
	if len(entities) == 0 {
		return "Failed"
	}

	// Mark 3 -> 9 (in preparation) and save, mirroring the Java saveAll.
	for _, e := range entities {
		e.GenStatus = 9
	}
	if err := s.store.UpdateJaywanWorkStatuses(ctx, entities); err != nil {
		logOutsvc("UpdateJaywanWorkStatuses", err)
		return "Failed"
	}
	// Java: updateOutFilelog(fileName, fileId, ...) with the raw file id.
	s.updateOutFilelog(ctx, insCode, outgoingLogSerialNumber, fileName, &fileId)

	seen := map[string]struct{}{}
	tokens := make([]string, 0, len(entities))
	for _, d := range entities {
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
		for _, e := range entities {
			e.GenStatus = 7
		}
		if err := s.store.UpdateJaywanWorkStatuses(ctx, entities); err != nil {
			logOutsvc("UpdateJaywanWorkStatuses", err)
		}
		return "Outgoing Failed"
	}

	// Load JAYWAN_NETWORK_DATA for the work rows to source nTxnId/nProcCd/
	// nPosTxnStat (columns absent from JAYWAN_ACQ_TXN_WORK).
	ndByRef := map[int64]*JaywanNetworkDataEntity{}
	if len(entities) > 0 {
		prjSer := entities[0].PrjSerNumber
		refs := make([]int64, 0, len(entities))
		for _, e := range entities {
			if e.TxnRefNumber != 0 {
				refs = append(refs, e.TxnRefNumber)
			}
		}
		if len(refs) > 0 {
			netData, err := s.store.FindJaywanNetworkDataByRef(ctx, prjSer, refs)
			if err != nil {
				logOutsvc("FindJaywanNetworkDataByRef", err)
			} else {
				for _, nd := range netData {
					ndByRef[nd.TxnRefNumber] = nd
				}
			}
		}
	}

	header := s.mapJaywanHeader(acq, fileId)
	recordCounter := 2
	totalTxnAmount := 0.0
	var txns []string
	for _, e := range entities {
		if e.LocalDateTime == nil {
			return "Failed"
		}
		tagList, ok := s.mapJaywanEntityToTxn(ctx, e, decrypted, recordCounter, ndByRef[e.TxnRefNumber])
		if !ok {
			continue
		}
		txns = append(txns, buildJaywanTxn(tagList))
		totalTxnAmount += e.TxnAmount * 100.0
		recordCounter++
	}
	trailer := &jaywanXmlTrailerVO{
		NMTI:      "1644",
		NFunCd:    "671",
		NRecNum:   fmt.Sprintf("%08d", recordCounter),
		NUnFlNm:   fileId,
		NTxnCnt:   fmt.Sprintf("%08d", len(txns)),
		NRnTtlAmt: fmt.Sprintf("%015d", int64(totalTxnAmount)),
	}
	xmlContent := buildJaywanFile(header, txns, buildJaywanTrailer(trailer))

	xmlFilePath := s.writeJaywanXmlFile(xmlContent, insShortName, fileName)
	if xmlFilePath == "" {
		return "Outgoing Failed"
	}

	// Java: updateOutFilelog(fileName, fileName, ...) sets FileId = the file
	// name and keeps generated_status = 4.
	s.updateOutFilelog(ctx, insCode, outgoingLogSerialNumber, fileName, &fileName)
	// Mark 9 -> 4 (completed), mirroring the Java re-save of the entities.
	for _, e := range entities {
		e.GenStatus = 4
	}
	if err := s.store.UpdateJaywanWorkStatuses(ctx, entities); err != nil {
		logOutsvc("UpdateJaywanWorkStatuses", err)
	}
	s.insertJaywanIntoOutgoingSummary(ctx, user, insCode, intCode, fileName, outgoingLogSerialNumber)
	if err := s.store.CompleteJaywanPosStatus(ctx, insCode); err != nil {
		logOutsvc("CompleteJaywanPosStatus", err)
	}
	s.moveJaywanWorkToData(ctx, entities, fileName)
	// generateOutgoingSummaryPDF is not ported yet.
	return "Success"
}

// jaywanXmlHeaderVO carries the 8 header tags of the V1.3 format.
type jaywanXmlHeaderVO struct {
	NMTI, NFunCd, NRecNum, NDtTmFlGen, NMemInstCd, NUnFlNm, NFlCatg, NVerNum string
}

type jaywanXmlTrailerVO struct {
	NMTI, NFunCd, NRecNum, NUnFlNm, NTxnCnt, NRnTtlAmt string
}

// mapJaywanHeader renders the V1.3 <Hdr>: nMTI=1644, nFunCd=670, nRecNum=1,
// nDtTmFlGen=MMDDhhmmss (24-hour), nMemInstCd=participant ID, nUnFlNm=fileId,
// nFlCatg and nVerNum from config.
func (s *OutgoingService) mapJaywanHeader(acq *AcquirerBinsEntity, fileId string) string {
	return buildJaywanHeader(&jaywanXmlHeaderVO{
		NMTI:       "1644",
		NFunCd:     "670",
		NRecNum:    "00000001",
		NDtTmFlGen: s.now().Format("0102150405"),
		NMemInstCd: strOrNull(acq.ParticipantId),
		NUnFlNm:    fileId,
		NFlCatg:    s.cfg.FileCategory,
		NVerNum:    s.cfg.VersionNumber,
	})
}

// mapJaywanEntityToTxn renders one <Txn> in the V1.3 field order. nd supplies
// the network-data-only fields (nProcCd/nTxnId/nPosTxnStat/nPosCPInd). It
// returns ok=false when the card cannot be decrypted (Java: the transaction is
// skipped and updateFailedTxn marks the row failed).
func (s *OutgoingService) mapJaywanEntityToTxn(ctx context.Context, e *JaywanAcqTxnWorkEntity, decrypted map[string]string, recordNumber int, nd *JaywanNetworkDataEntity) ([]jaywanTxnTag, bool) {
	decCard := decrypted[e.EncCardNumber]
	if decCard == "" {
		s.updateJaywanFailedTxn(ctx, e.Rrn)
		return nil, false
	}

	procCd := ""
	txnId := ""
	posTxnStat := "0"
	posCPInd := "5"
	if nd != nil {
		procCd = nd.ProcCode
		txnId = nd.TransIdentifier
		if nd.PosTxnStatus != "" {
			posTxnStat = nd.PosTxnStatus
		}
		if nd.PosCPInd != "" {
			posCPInd = nd.PosCPInd
		}
	}

	tags := []jaywanTxnTag{
		{"nMTI", jaywanStrPtr(e.MessageTypeId)},
		{"nFunCd", jaywanStrPtr(e.FunctionCode)},
		{"nRecNum", jaywanStrPtr(fmt.Sprintf("%08d", recordNumber))},
		{"nDtTmLcTxn", jaywanStrPtr(jaywanTxnDateTime(e.LocalDateTime))},
		{"nPAN", jaywanStrPtr(decCard)},
		{"nRRN", jaywanStrPtr(e.Rrn)},
		{"nAcqInstCd", jaywanStrPtr(e.AcqinstIdCode)},
		{"nApprvlCd", jaywanStrPtr(e.ApprovalCode)},
		{"nCrdAcptTrmId", jaywanStrPtr(e.TerminalId)},
		{"nAmtTxn", jaywanStrPtr(jaywanAmt12(e.TxnAmount, true))},
		{"nCcyCdTxn", jaywanStrPtr(e.TxnCurCode)},
		{"nTxnOrgInstCd", jaywanStrPtr(e.AcqinstIdCode)},
	}
	if e.MotoEcomIndicator != "" {
		tags = append(tags, jaywanTxnTag{"nECIInd", jaywanStrPtr(e.MotoEcomIndicator)})
	}
	tags = append(tags,
		jaywanTxnTag{"nCrdAcpIDCd", jaywanStrPtr(e.MerchantId)},
		jaywanTxnTag{"nCrdAcpNm", jaywanStrPtr(padRight(e.MeName, 23))},
		jaywanTxnTag{"nCrdAcpCity", jaywanStrPtr(padRight(e.MeCity, 13))},
		jaywanTxnTag{"nCrdAcpStNm", jaywanStrPtr(jaywanValidState(e.MeStateCode))},
		jaywanTxnTag{"nCrdAcpCtryCd", jaywanStrPtr(e.MeCountry)},
		jaywanTxnTag{"nCrdAcpBussCd", jaywanStrPtr(e.Mcc)},
		jaywanTxnTag{"nProcCd", jaywanStrPtr(procCd)},
		jaywanTxnTag{"nPosEntMode", jaywanStrPtr(e.PosEntryMode)},
		jaywanTxnTag{"nPosCondCd", jaywanStrPtr(e.PosConditionCode)},
		jaywanTxnTag{"nActnCd", jaywanStrPtr(e.ResponseCode)},
		jaywanTxnTag{"nTxnId", jaywanStrPtr(txnId)},
		jaywanTxnTag{"nFulParInd", jaywanStrPtr("F")},
		jaywanTxnTag{"nPosCPInd", jaywanStrPtr(posCPInd)},
		jaywanTxnTag{"nPosTxnStat", jaywanStrPtr(posTxnStat)},
	)
	return tags, true
}

// padRight pads s with trailing spaces up to width n (JAYWAN AN fields are
// fixed-length, space-padded on the right).
func padRight(s string, n int) string {
	if len(s) >= n {
		return s[:n]
	}
	return s + strings.Repeat(" ", n-len(s))
}

// jaywanValidState returns the card-acceptor state code when it is a valid
// Jaywan code, else the default "DU" (spec: invalid codes are corrected).
func jaywanValidState(code string) string {
	switch code {
	case "DU", "SH", "AJ", "FU", "RK", "UQ", "AZ":
		return code
	}
	return "DU"
}

// updateJaywanFailedTxn mirrors JaywanOutgoingServiceImpl.updateFailedTxn: all
// work rows with the matching RRN are set to gen_status=7.
func (s *OutgoingService) updateJaywanFailedTxn(ctx context.Context, rrn string) {
	if rrn == "" {
		return
	}
	ents, err := s.store.FindJaywanWorkByRrn(ctx, rrn)
	if err != nil {
		logOutsvc("FindJaywanWorkByRrn", err)
		return
	}
	if len(ents) == 0 {
		return
	}
	for _, e := range ents {
		e.GenStatus = 7
	}
	if err := s.store.UpdateJaywanWorkStatuses(ctx, ents); err != nil {
		logOutsvc("UpdateJaywanWorkStatuses", err)
	}
}

// writeJaywanXmlFile writes the compact XML to RECON_OUT_{insShortName}/
// {fileName}; returns "" on error.
func (s *OutgoingService) writeJaywanXmlFile(content, insShortName, fileName string) string {
	path := filepath.Join(s.cfg.ReconOutDir, fileName)
	if err := os.WriteFile(path, []byte(content), 0o644); err != nil {
		logOutsvc("writeJaywanXmlFile", err)
		return ""
	}
	return path
}

// insertJaywanIntoOutgoingSummary mirrors
// JaywanOutgoingServiceImpl.insertOutgoingSummary: groups the gen_status=4 rows
// by function code and writes one OUTGOING_SUMMARY row per group.
func (s *OutgoingService) insertJaywanIntoOutgoingSummary(ctx context.Context, user, insCode, intCode int, fileName string, outgoingLogSerialNumber int64) {
	ents, err := s.store.FindJaywanWorkByIntAndStatus(ctx, insCode, intCode, 4)
	if err != nil {
		logOutsvc("FindJaywanWorkByIntAndStatus", err)
		return
	}
	groups := map[string]*struct {
		count                int
		totalTxnAmount       float64
		totalSurchargeAmount float64
	}{}
	for _, e := range ents {
		g := groups[e.FunctionCode]
		if g == nil {
			g = &struct {
				count                int
				totalTxnAmount       float64
				totalSurchargeAmount float64
			}{}
			groups[e.FunctionCode] = g
		}
		g.count++
		g.totalTxnAmount += e.TxnAmount
		g.totalSurchargeAmount += e.SurchargeAmount
	}
	now := time.Now()
	for fc, totals := range groups {
		ots := &OutgoingSummaryEntity{
			LastUpdated:     now,
			UpdatedUser:     user,
			InstitutionCode: insCode,
			InterfaceCode:   intCode,
			OutFileDate:     dateOnly(now),
			FileId:          fileName,
			RefSerialNumber: outgoingLogSerialNumber,
			MessageTypeId:   fc,
			FunctionCode:    "1",
			ProcCode:        "",
			Count:           totals.count,
			Amount:          totals.totalTxnAmount,
			SurchargeAmount: totals.totalSurchargeAmount,
			NetAmount:       totals.totalTxnAmount + totals.totalSurchargeAmount,
			GeneralStatus:   3,
		}
		if err := s.store.InsertSummaries(ctx, []*OutgoingSummaryEntity{ots}); err != nil {
			logOutsvc("InsertSummaries", err)
		}
	}
}

// moveJaywanWorkToData mirrors JaywanOutgoingServiceImpl.moveWorkToData: copies
// the processed work rows into JAYWAN_ACQ_TXN_DATA (serial preserved,
// lastUpdated reset, JWN_FILE_ID set to the file name) and deletes them from
// the work table.
func (s *OutgoingService) moveJaywanWorkToData(ctx context.Context, entities []*JaywanAcqTxnWorkEntity, fileName string) {
	now := time.Now()
	dataEntities := make([]*JaywanAcqTxnDataEntity, 0, len(entities))
	for _, e := range entities {
		d := *e
		d.LastUpdated = now
		d.FileID = fileName
		dataEntities = append(dataEntities, &d)
	}
	if err := s.store.InsertJaywanData(ctx, dataEntities); err != nil {
		logOutsvc("InsertJaywanData", err)
		return
	}
	if err := s.store.DeleteJaywanWork(ctx, entities); err != nil {
		logOutsvc("DeleteJaywanWork", err)
	}
}

// mapJaywanDataToWork mirrors OutGoingProcessingService.mapToJaywanAcqWorkEntity:
// the serial number is preserved and gen_status set to 3 (revert).
func mapJaywanDataToWork(d *JaywanAcqTxnDataEntity, now time.Time) *JaywanAcqTxnWorkEntity {
	w := *d
	w.LastUpdated = now
	w.GenStatus = 3
	return &w
}

// jaywanTxnDateTime renders nDtTmLcTxn as MMDDhhmmss (24-hour clock, no year),
// per the V1.3 spec.
func jaywanTxnDateTime(t *time.Time) string {
	return t.Format("0102150405")
}

// jaywanAmt12 formats a 12-digit zero-padded amount in fils. Java multiplies
// the txn amount by 100 (major -> minor) and truncates to long.
func jaywanAmt12(v float64, mult100 bool) string {
	if mult100 {
		return fmt.Sprintf("%012d", int64(v*100.0))
	}
	return fmt.Sprintf("%012d", int64(v))
}

func strOrNull(p *string) string {
	if p == nil {
		return "null"
	}
	return *p
}