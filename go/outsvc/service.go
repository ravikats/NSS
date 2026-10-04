package outsvc

import (
	"context"
	"sync"
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
	MercuryMemberId    string
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
	Region             string

	// IPMValidationStrict controls what happens when a generated Mastercard
	// IPM file fails compliance validation.
	//
	// Default (false) is report-only: the outcome is logged, published to the
	// inquiry UI and the file is generated normally. Set it to true to make a
	// failing file abort generation (work rows -> 7, file log -> 5).
	//
	// Report-only is the default because the shipped rule set requires data
	// this producer does not emit: VW_IPM_OUT_WORK returns NULL for every DE55
	// subfield and every DE48 PDS field, so DE105_REQUIRED and
	// MERCHANT_COUNTRY_OF_ORIGIN_REQUIRED would fail every generated file.
	// Strict mode is opt-in once the rules are reconciled with this producer.
	IPMValidationStrict bool

	// IPMReportsDir is the directory where IPM compliance CSV/JSONL reports
	// are written. If empty, defaults to <ReconOutDir>/ipm_reports/.
	IPMReportsDir string

	// VisaValidationStrict controls what happens when a generated Visa BASE II
	// file fails validation. Default (false) is report-only: the outcome is
	// logged, published to the inquiry UI and the file is kept. Set it to true
	// to abort generation (work rows -> 7, file log -> 5).
	VisaValidationStrict bool

	// Base2ReportsDir is the directory where BASE II validation CSV/JSONL
	// reports are written. If empty, defaults to <ReconOutDir>/base2_reports/.
	Base2ReportsDir string

	// UnionPayValidationStrict controls what happens when a generated UnionPay
	// settlement file fails validation. Default (false) is report-only, matching
	// Visa: the outcome is logged, published to the inquiry UI and the file is
	// kept. Set it to true to abort generation (work rows stay 9, file log -> 5).
	UnionPayValidationStrict bool

	// UnionPayValidationReportDir is where the UnionPay validation JSON report is
	// written. If empty, defaults to <ReconOutDir>/unionpay_validation/.
	UnionPayValidationReportDir string
}

// OutgoingService orchestrates the outgoing file generation flow
// (OutGoingProcessingService + network services in Java).
type OutgoingService struct {
	cfg    OutgoingConfig
	store  Store
	ipm    *IpmOutProcessor
	crypto CardCrypto
	now    func() time.Time

	validationMu sync.Mutex
	validations  []*ValidationResult
}

// NewOutgoingService wires the orchestrator.
func NewOutgoingService(cfg OutgoingConfig, store Store, crypto CardCrypto) *OutgoingService {
	s := &OutgoingService{
		cfg:    cfg,
		store:  store,
		crypto: crypto,
		now:    time.Now,
	}
	s.ipm = NewIpmOutProcessor(cfg.ReconOutDir, cfg.IPMReportsDir, cfg.ProcessingMode, store, crypto)
	return s
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

// getTxnCount counts gen_status=3 work rows for the network/date range.
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
			n, err := s.store.CountAmexWorkLessThanEqual(ctx, insCode, 3, *toDate)
			if err != nil {
				logOutsvc("CountAmexWorkLessThanEqual", err)
				return 0
			}
			return n
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
		n, err := s.store.CountAmexWorkBetween(ctx, insCode, 3, *fromDate, *toDate)
		if err != nil {
			logOutsvc("CountAmexWorkBetween", err)
			return 0
		}
		return n
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
			s.ProcessAmexOutgoing(bg, insCode, user, formatCode, insShortName, fromDate, toDate)
		case "MERCURY":
			s.ProcessMercuryOutgoing(bg, insCode, user, formatCode, insShortName, fromDate, toDate)
		case "UNIONPAY":
			s.ProcessUnionPayOutgoing(bg, insCode, user, formatCode, insShortName, fromDate, toDate)
		}
	}()
}

// RevertLastOutgoingData is the Go port of
// OutGoingProcessingService.revertLastOutgoingData. MASTERCARD, VISA, JAYWAN,
// MERCURY, UNIONPAY are ported; other networks report "Please provide valid network".
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
			s.updatePOSData(ctx, nil, data, nil, nil)
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
			s.updatePOSData(ctx, posCodes, nil, nil, nil)
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
			s.updatePOSData(ctx, posCodes, nil, nil, nil)
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
	case "MERCURY":
		intf, err := s.store.FindInterfaceByCategory(ctx, "MERCURY")
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
		data, err := s.store.FindMercuryDataByFileId(ctx, insCode, outFileId)
		if err != nil {
			logOutsvc("FindMercuryDataByFileId", err)
			return "Please provide valid network"
		}
		if len(data) > 0 {
			work := make([]*MercuryAcqTxnWorkEntity, 0, len(data))
			for _, d := range data {
				work = append(work, mapMercuryDataToWork(d))
			}
			if err := s.store.InsertMercuryWork(ctx, work); err != nil {
				logOutsvc("InsertMercuryWork", err)
				return "Please provide valid network"
			}
			var posCodes []int64
			for _, d := range data {
				if d.TxnRefNumber > 0 {
					posCodes = append(posCodes, d.TxnRefNumber)
				}
			}
			s.updatePOSData(ctx, nil, nil, nil, posCodes)
			if err := s.store.DeleteMercuryData(ctx, data); err != nil {
				logOutsvc("DeleteMercuryData", err)
				return "Please provide valid network"
			}
			if err := s.store.DeleteFileLogByInstitutionAndFileIdAndInterface(ctx, insCode, outFileId, intCode); err != nil {
				logOutsvc("DeleteFileLogByInstitutionAndFileIdAndInterface", err)
				return "Please provide valid network"
			}
			return "Revert Successfully Completed"
		}
		return "No Outgoing Data for the file ID"
	case "UNIONPAY":
		intf, err := s.store.FindInterfaceByCategory(ctx, "UNIONPAY")
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
		data, err := s.store.FindUnionPayDataByFileId(ctx, insCode, outFileId)
		if err != nil {
			logOutsvc("FindUnionPayDataByFileId", err)
			return "Please provide valid network"
		}
		if len(data) > 0 {
			now := time.Now()
			work := make([]*UnionPayAcqTxnWorkEntity, 0, len(data))
			for _, d := range data {
				w := *d
				w.SerialNumber = 0
				w.GenStatus = 3
				w.TxnDate = nil
				w.FileProcDate = nil
				w.FileID = ""
				w.LastUpdated = now
				work = append(work, &w)
			}
			if err := s.store.InsertUnionPayWork(ctx, work); err != nil {
				logOutsvc("InsertUnionPayWork", err)
				return "Please provide valid network"
			}
			var posCodes []int64
			for _, d := range data {
				if d.TxnRefNumber > 0 {
					posCodes = append(posCodes, d.TxnRefNumber)
				}
			}
			s.updatePOSData(ctx, posCodes, nil, nil, nil)
			if err := s.store.DeleteUnionPayData(ctx, data); err != nil {
				logOutsvc("DeleteUnionPayData", err)
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

// updatePOSData marks POS transactions "Marked for Outgoing" during revert.
func (s *OutgoingService) updatePOSData(ctx context.Context, visaAcqData []int64, mcAcqData []*McAcqTxnDataEntity, jaywanAcqData []int64, mercuryAcqData []int64) {
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
	case mercuryAcqData != nil:
		posCodes = mercuryAcqData
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
