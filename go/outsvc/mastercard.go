package outsvc

import (
	"context"
	"fmt"
	"time"
)

// ProcessMCOutgoing is the Go port of MCOutgoingService.processMCOutgoing.
func (s *OutgoingService) ProcessMCOutgoing(ctx context.Context, insCode, user, formatCode int, insShortName string, fromDate, toDate *time.Time) string {
	var fileName string
	var processorID string
	seqNo := 0
	intCategory := "MCI"
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

	entity := &OutGoingFileProcessingEntity{
		LastUpdated:     now,
		GeneratedDate:   now,
		UpdatedUser:     user,
		InstitutionCode: insCode,
		InterfaceCode:   intCode,
		FormatCode:      forCode,
		GeneratedStatus: 9,
	}
	bd, err := s.store.FindBusinessDateByInstitution(ctx, insCode)
	if err != nil {
		logOutsvc("FindBusinessDateByInstitution", err)
		return "Failed"
	}
	if bd != nil {
		entity.BussDate = bd.BusinessDate
	}
	outgoingLogSerialNumber, err := s.store.InsertFileLog(ctx, entity)
	if err != nil {
		logOutsvc("InsertFileLog", err)
		return "Failed"
	}

	acqBinList, err := s.store.FindAcquirerBins(ctx, insCode, "M")
	if err != nil {
		logOutsvc("FindAcquirerBins", err)
		return "Failed"
	}
	if len(acqBinList) == 0 || acqBinList[0] == nil {
		// Without this row the sequence can never advance, so every run would
		// produce the same ".00" name and silently overwrite the previous file.
		// Fail loudly instead of writing a file that destroys its predecessor.
		logOutsvc("FindAcquirerBins", fmt.Errorf("no ACQUIRER_BINS row for bin_type=M ins=%d: cannot allocate file sequence", insCode))
		return "Failed"
	}
	acq := acqBinList[0]
	if acq.McIcaNo != nil {
		processorID = *acq.McIcaNo
	}
	if acq.OutfileDate != nil && sameCalendarDay(*acq.OutfileDate, now) {
		seqNo = acq.OutFileSeq
	} else {
		seqNo = 1
	}
	acq.OutFileSeq = seqNo + 1
	t := now
	acq.OutfileDate = &t
	if err := s.store.UpdateAcquirerBin(ctx, acq); err != nil {
		logOutsvc("UpdateAcquirerBin", err)
		return "Failed"
	}

	fileName = insShortName + "R111" + now.Format("02012006") + fmt.Sprintf(".%02d", seqNo)
	outFileProcEntity, err := s.store.FindFileLogByInstitutionAndSerial(ctx, insCode, outgoingLogSerialNumber)
	if err != nil {
		logOutsvc("FindFileLogByInstitutionAndSerial", err)
		return "Failed"
	}
	outFileProcEntity.LastUpdated = now
	outFileProcEntity.FileName = fileName
	if err := s.store.UpdateFileLog(ctx, outFileProcEntity); err != nil {
		logOutsvc("UpdateFileLog", err)
		return "Failed"
	}

	fileID := s.ipm.IpmPro(ctx, fileName, processorID, seqNo, insCode, intCode, dateOnly(now), int(outgoingLogSerialNumber), user, fromDate, toDate, "")

	if fileID == "" {
		outFileProcEntity.FileId = nil
		outFileProcEntity.GeneratedStatus = 5
	} else {
		fid := fileID
		outFileProcEntity.FileId = &fid
		outFileProcEntity.GeneratedStatus = 4
	}
	if err := s.store.UpdateFileLog(ctx, outFileProcEntity); err != nil {
		logOutsvc("UpdateFileLog", err)
		return "Failed"
	}
	// generateOutgoingSummaryPDF is not ported yet; summary rows are already
	// written by the IPM processor (see buildAndSaveSummaries).
	return "Success"
}

// mapMcDataToWork copies an MC data row back into a fresh work row
// (generalStatus 3). Mirrors mapToMcAcqWorkEntity: serial number is DB-assigned
// and txn/out-file dates are not carried over.
func mapMcDataToWork(d *McAcqTxnDataEntity) *McAcqTxnWorkEntity {
	w := *d
	w.SerNumber = 0
	w.GeneralStatus = 3
	w.TxnDate = nil
	w.OutFileDate = nil
	return &w
}