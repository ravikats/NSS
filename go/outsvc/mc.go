package outsvc

import (
	"context"
	"fmt"
	"time"
)

// OutgoingConfig carries the institution/system configuration read from the
// environment (the Java application.properties equivalents).
type OutgoingConfig struct {
	InsCode            int
	InsShortName       string
	UpdatedUser        int
	MastercardSysCode  int
	VisaSysCode        int
	JaywanSysCode      int
	AmexSysCode        int
	MercurySysCode     int
	UnionPaySysCode    int
	GCOSysCode         int
	GOCSysCode         int
	ReconOutDir        string
	ProcessingMode     string
	CurrencyCodeKafka  string
	ProductCode        string
	FileCategory       string
	VersionNumber      string
	UnionPayVersionTag string
}

// OutgoingService orchestrates the outgoing file generation flow
// (OutGoingProcessingService + network services in Java).
type OutgoingService struct {
	cfg    OutgoingConfig
	store  Store
	ipm    *IpmOutProcessor
	crypto CardCrypto
	now    func() time.Time
}

// NewOutgoingService wires the orchestrator.
func NewOutgoingService(cfg OutgoingConfig, store Store, crypto CardCrypto) *OutgoingService {
	return &OutgoingService{
		cfg:    cfg,
		store:  store,
		ipm:    NewIpmOutProcessor(cfg.ReconOutDir, cfg.ProcessingMode, store, crypto),
		crypto: crypto,
		now:    time.Now,
	}
}

const outgoingDateLayout = "02/01/2006 15:04:05"

// ProcessAndMoveData is the Go port of
// OutGoingProcessingService.processAndMoveData.
func (s *OutgoingService) ProcessAndMoveData(ctx context.Context, vo *OutGoingRequestVo, insCode, user, formatCode int, insShortName string) string {
	fromDate, err1 := time.Parse(outgoingDateLayout, vo.FromDate)
	toDate, err2 := time.Parse(outgoingDateLayout, vo.ToDate)
	if err1 != nil || err2 != nil {
		return "Invalid date time format "
	}
	if toDate.Before(fromDate) {
		return "From date is greater than To Date date time format "
	}
	txnCount := s.getTxnCount(ctx, vo.Network, insCode, &fromDate, &toDate)
	if txnCount == 0 {
		return "There are no transactions to stage!"
	}
	s.scheduleFileProcessing(ctx, insCode, user, formatCode, insShortName, vo.Network, &fromDate, &toDate)
	return "Outgoing File Processing Scheduled Successfully."
}

// getTxnCount counts gen_status=3 work rows for the network/date range. The
// AMEX branch returns 0 until that network is ported.
func (s *OutgoingService) getTxnCount(ctx context.Context, network string, insCode int, fromDate, toDate *time.Time) int {
	if network == "" {
		return 0
	}
	if fromDate == nil {
		switch network {
		case "MASTERCARD":
			n, err := s.store.CountMcWorkLessThanEqual(ctx, insCode, 3, *toDate)
			if err != nil {
				logOutsvc("CountMcWorkLessThanEqual", err)
				return 0
			}
			return n
		case "VISA":
			n, err := s.store.CountVisaWorkLessThanEqual(ctx, insCode, 3, *toDate)
			if err != nil {
				logOutsvc("CountVisaWorkLessThanEqual", err)
				return 0
			}
			return n
		case "JAYWAN":
			n, err := s.store.CountJaywanWorkLessThanEqual(ctx, insCode, 3, *toDate)
			if err != nil {
				logOutsvc("CountJaywanWorkLessThanEqual", err)
				return 0
			}
			return n
		case "AMEX":
			return 0
		case "MERCURY":
			n, err := s.store.CountMercuryWorkLessThanEqual(ctx, insCode, 3, *toDate)
			if err != nil {
				logOutsvc("CountMercuryWorkLessThanEqual", err)
				return 0
			}
			return n
		case "UNIONPAY":
			n, err := s.store.CountUnionPayWorkLessThanEqual(ctx, insCode, 3, *toDate)
			if err != nil {
				logOutsvc("CountUnionPayWorkLessThanEqual", err)
				return 0
			}
			return n
		}
		return 0
	}
	switch network {
	case "MASTERCARD":
		n, err := s.store.CountMcWorkBetween(ctx, insCode, 3, *fromDate, *toDate)
		if err != nil {
			logOutsvc("CountMcWorkBetween", err)
			return 0
		}
		return n
	case "VISA":
		n, err := s.store.CountVisaWorkBetween(ctx, insCode, 3, *fromDate, *toDate)
		if err != nil {
			logOutsvc("CountVisaWorkBetween", err)
			return 0
		}
		return n
	case "JAYWAN":
		n, err := s.store.CountJaywanWorkBetween(ctx, insCode, 3, *fromDate, *toDate)
		if err != nil {
			logOutsvc("CountJaywanWorkBetween", err)
			return 0
		}
		return n
	case "AMEX":
		return 0
	case "MERCURY":
		n, err := s.store.CountMercuryWorkBetween(ctx, insCode, 3, *fromDate, *toDate)
		if err != nil {
			logOutsvc("CountMercuryWorkBetween", err)
			return 0
		}
		return n
	case "UNIONPAY":
		n, err := s.store.CountUnionPayWorkBetween(ctx, insCode, 3, *fromDate, *toDate)
		if err != nil {
			logOutsvc("CountUnionPayWorkBetween", err)
			return 0
		}
		return n
	}
	return 0
}

// scheduleFileProcessing runs the network-specific generation in the
// background, mirroring the Java thread per network.
func (s *OutgoingService) scheduleFileProcessing(ctx context.Context, insCode, user, formatCode int, insShortName, network string, fromDate, toDate *time.Time) {
	go func() {
		bg := context.Background()
		switch network {
		case "MASTERCARD":
			s.ProcessMCOutgoing(bg, insCode, user, formatCode, insShortName, fromDate, toDate)
		case "VISA":
			s.ProcessVisaOutgoing(bg, insCode, user, formatCode, insShortName, fromDate, toDate)
		case "JAYWAN":
			s.ProcessJaywanOutgoing(bg, insCode, user, formatCode, insShortName, fromDate, toDate)
		case "AMEX":
			logOutsvc("scheduleFileProcessing", fmt.Errorf("network %s not yet ported", network))
		case "MERCURY":
			s.ProcessMercuryOutgoing(bg, insCode, user, formatCode, insShortName, fromDate, toDate)
		case "UNIONPAY":
			s.ProcessUnionPayOutgoing(bg, insCode, user, formatCode, insShortName, fromDate, toDate)
		}
	}()
}

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
	if len(acqBinList) > 0 && acqBinList[0] != nil {
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

// RevertLastOutgoingData is the Go port of
// OutGoingProcessingService.revertLastOutgoingData. MASTERCARD, VISA and JAYWAN
// are ported; other networks report "Please provide valid network".
func (s *OutgoingService) RevertLastOutgoingData(ctx context.Context, intCategory string, insCode int) string {
	switch intCategory {
	case "MASTERCARD":
		intf, err := s.store.FindInterfaceByCategory(ctx, "MCI")
		if err != nil {
			logOutsvc("FindInterfaceByCategory", err)
			return "Please provide valid network"
		}
		intCode := 0
		if intf != nil {
			intCode = intf.InterfaceCode
		}
		fileLog, err := s.store.FindFileLogTopByStatusAndInterface(ctx, 4, intCode)
		if err != nil {
			logOutsvc("FindFileLogTopByStatusAndInterface", err)
			return "Please provide valid network"
		}
		if fileLog == nil || fileLog.FileId == nil {
			return "No Outgoing Data for the file ID"
		}
		outFileId := *fileLog.FileId
		data, err := s.store.FindMcDataByFileId(ctx, insCode, outFileId)
		if err != nil {
			logOutsvc("FindMcDataByFileId", err)
			return "Please provide valid network"
		}
		if len(data) > 0 {
			work := make([]*McAcqTxnWorkEntity, 0, len(data))
			for _, d := range data {
				work = append(work, mapMcDataToWork(d))
			}
			if err := s.store.InsertMcWork(ctx, work); err != nil {
				logOutsvc("InsertMcWork", err)
				return "Please provide valid network"
			}
			s.updatePOSData(ctx, nil, data, nil)
			if err := s.store.DeleteMcData(ctx, data); err != nil {
				logOutsvc("DeleteMcData", err)
				return "Please provide valid network"
			}
			if err := s.store.DeleteFileLogByInstitutionAndFileIdAndInterface(ctx, insCode, outFileId, intCode); err != nil {
				logOutsvc("DeleteFileLogByInstitutionAndFileIdAndInterface", err)
				return "Please provide valid network"
			}
			return "Revert Successfully Completed"
		}
		return "No Outgoing Data for the file ID"
	case "VISA":
		intf, err := s.store.FindInterfaceByCategory(ctx, "VISA")
		if err != nil {
			logOutsvc("FindInterfaceByCategory", err)
			return "Please provide valid network"
		}
		intCode := 0
		if intf != nil {
			intCode = intf.InterfaceCode
		}
		fileLog, err := s.store.FindFileLogTopByStatusAndInterface(ctx, 4, intCode)
		if err != nil {
			logOutsvc("FindFileLogTopByStatusAndInterface", err)
			return "Please provide valid network"
		}
		if fileLog == nil || fileLog.FileId == nil {
			return "No Outgoing Data for the file ID"
		}
		outFileId := *fileLog.FileId
		data, err := s.store.FindVisaDataByFileId(ctx, insCode, outFileId)
		if err != nil {
			logOutsvc("FindVisaDataByFileId", err)
			return "Please provide valid network"
		}
		if len(data) > 0 {
			now := time.Now()
			work := make([]*VisaAcqTxnWorkEntity, 0, len(data))
			for _, d := range data {
				work = append(work, mapVisaDataToWork(d, now))
			}
			if err := s.store.InsertVisaWork(ctx, work); err != nil {
				logOutsvc("InsertVisaWork", err)
				return "Please provide valid network"
			}
			var posCodes []int64
			for _, d := range data {
				if d.TxnRefNumber > 0 {
					posCodes = append(posCodes, d.TxnRefNumber)
				}
			}
			s.updatePOSData(ctx, posCodes, nil, nil)
			if err := s.store.DeleteVisaData(ctx, data); err != nil {
				logOutsvc("DeleteVisaData", err)
				return "Please provide valid network"
			}
			if err := s.store.DeleteFileLogByInstitutionAndFileIdAndInterface(ctx, insCode, outFileId, intCode); err != nil {
				logOutsvc("DeleteFileLogByInstitutionAndFileIdAndInterface", err)
				return "Please provide valid network"
			}
			return "Revert Successfully Completed"
		}
		return "No Outgoing Data for the file ID"
	case "JAYWAN":
		intf, err := s.store.FindInterfaceByCategory(ctx, "JAYWAN")
		if err != nil {
			logOutsvc("FindInterfaceByCategory", err)
			return "Please provide valid network"
		}
		intCode := 0
		if intf != nil {
			intCode = intf.InterfaceCode
		}
		fileLog, err := s.store.FindFileLogTopByStatusAndInterface(ctx, 4, intCode)
		if err != nil {
			logOutsvc("FindFileLogTopByStatusAndInterface", err)
			return "Please provide valid network"
		}
		if fileLog == nil || fileLog.FileId == nil {
			return "No Outgoing Data for the file ID"
		}
		outFileId := *fileLog.FileId
		data, err := s.store.FindJaywanDataByFileId(ctx, insCode, outFileId)
		if err != nil {
			logOutsvc("FindJaywanDataByFileId", err)
			return "Please provide valid network"
		}
		if len(data) > 0 {
			now := time.Now()
			work := make([]*JaywanAcqTxnWorkEntity, 0, len(data))
			for _, d := range data {
				work = append(work, mapJaywanDataToWork(d, now))
			}
			if err := s.store.InsertJaywanWork(ctx, work); err != nil {
				logOutsvc("InsertJaywanWork", err)
				return "Please provide valid network"
			}
			var posCodes []int64
			for _, d := range data {
				if d.TxnRefNumber > 0 {
					posCodes = append(posCodes, d.TxnRefNumber)
				}
			}
			s.updatePOSData(ctx, posCodes, nil, nil)
			if err := s.store.DeleteJaywanData(ctx, data); err != nil {
				logOutsvc("DeleteJaywanData", err)
				return "Please provide valid network"
			}
			if err := s.store.DeleteFileLogByInstitutionAndFileIdAndInterface(ctx, insCode, outFileId, intCode); err != nil {
				logOutsvc("DeleteFileLogByInstitutionAndFileIdAndInterface", err)
				return "Please provide valid network"
			}
			return "Revert Successfully Completed"
		}
		return "No Outgoing Data for the file ID"
	default:
		return "Please provide valid network"
	}
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

// updatePOSData marks POS transactions "Marked for Outgoing" during revert.
func (s *OutgoingService) updatePOSData(ctx context.Context, visaAcqData []int64, mcAcqData []*McAcqTxnDataEntity, jaywanAcqData []int64) {
	var posCodes []int64
	switch {
	case visaAcqData != nil:
		posCodes = visaAcqData
	case mcAcqData != nil:
		for _, d := range mcAcqData {
			if d.TxnRefSerNumber > 0 {
				posCodes = append(posCodes, d.TxnRefSerNumber)
			}
		}
	case jaywanAcqData != nil:
		posCodes = jaywanAcqData
	default:
		return
	}
	posData, err := s.store.FindPosBySerNumbers(ctx, posCodes)
	if err != nil {
		logOutsvc("FindPosBySerNumbers", err)
		return
	}
	for _, p := range posData {
		p.GenStatus = 4
		p.OutStatus = "Marked for Outgoing"
	}
	if err := s.store.UpdatePosStatuses(ctx, posData); err != nil {
		logOutsvc("UpdatePosStatuses", err)
	}
}

// AutomateSchedulerTriggering is the Go port of
// OutGoingProcessingService.automateSchedulerTriggering.
func (s *OutgoingService) AutomateSchedulerTriggering(ctx context.Context, endTimeString, network string) string {
	formatCode := 0
	switch network {
	case "MASTERCARD":
		formatCode = s.cfg.MastercardSysCode
	case "VISA":
		formatCode = s.cfg.VisaSysCode
	case "JAYWAN":
		formatCode = s.cfg.JaywanSysCode
	case "AMEX":
		formatCode = s.cfg.AmexSysCode
	}
	lt, err := time.Parse("15:04:05", endTimeString)
	if err != nil {
		return "There are no transactions to stage!"
	}
	now := time.Now()
	endTime := time.Date(now.Year(), now.Month(), now.Day(), lt.Hour(), lt.Minute(), lt.Second(), 0, now.Location())
	txnCount := s.getTxnCount(ctx, network, s.cfg.InsCode, nil, &endTime)
	if txnCount == 0 {
		return "There are no transactions to stage!"
	}
	s.scheduleFileProcessing(ctx, s.cfg.InsCode, s.cfg.UpdatedUser, formatCode, s.cfg.InsShortName, network, nil, &endTime)
	return "Outgoing File Processing Scheduled Successfully."
}

func dateOnly(t time.Time) time.Time {
	return time.Date(t.Year(), t.Month(), t.Day(), 0, 0, 0, 0, t.Location())
}

// sameCalendarDay reports whether a and b fall on the same calendar date,
// ignoring their timezone offsets (Oracle DATE values read back in UTC vs a
// local now must compare by Y/M/D, not by instant).
func sameCalendarDay(a, b time.Time) bool {
	return a.Year() == b.Year() && a.Month() == b.Month() && a.Day() == b.Day()
}
