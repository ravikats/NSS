package binsvc

import (
	"context"
	"strings"
	"time"

	"github.com/xuri/excelize/v2"
)

// omanProcessor is the Go port of com.empay.omanNet.OmanNetBinProcessing.
type omanProcessor struct {
	svc *Service
}

// omanNetCell is a parsed cell: column index + formatted value (mirrors POI
// DataFormatter.formatCellValue).
type omanNetCell struct {
	column int
	value  string
}

// processOmanNetBin mirrors OmanNetBinProcessing.processOmanNetBin: reads the
// .xlsx workbook (first sheet), validates columns 0..3 per row, and inserts
// OMANNET_BIN_DATA rows (genStatus 4; 7 for rejected rows).
func (p *omanProcessor) processOmanNetBin(ctx context.Context, fileName string, jobSer, uplSer int) {
	s := p.svc
	filePath := s.Cfg.ReconIn + fileName
	s.log().Info("OMANNET BIN FILE PROCESSING STARTED", "file", fileName)

	f, err := excelize.OpenFile(filePath)
	if err != nil {
		s.log().Error("omannet open xlsx", "err", err)
		p.updateFileUploadLog(ctx, uplSer, 5, "Failed")
		s.moveFile(filePath, fileName, s.Cfg.ReconRejected)
		return
	}
	defer f.Close()

	sheets := f.GetSheetList()
	if len(sheets) == 0 {
		s.log().Error("omannet: no sheet", "file", fileName)
		return
	}
	rows, err := f.GetRows(sheets[0])
	if err != nil {
		s.log().Error("omannet rows", "err", err)
		p.updateFileUploadLog(ctx, uplSer, 5, "Failed")
		s.moveFile(filePath, fileName, s.Cfg.ReconRejected)
		return
	}

	totalCount := len(rows) - 1 // sheet.getLastRowNum() (0-based)
	totalFileLineCount := totalCount + 1
	batchSize := csvBatchSize(totalFileLineCount)

	now := time.Now()
	var omanList []*OmanNetRange
	accTxnCount := 0
	for rowNum, row := range rows {
		if rowNum == 0 {
			continue // header row
		}
		vo := &omanNetVO{genStatus: 4}
		iterated := false
		for colIdx, cellVal := range row {
			iterated = true
			vo.genStatus = 4 // reset per Java: set at top of every cell iteration
			ok := false
			switch colIdx {
			case 0:
				ok = omanSubRoute(vo, cellVal)
			case 1:
				ok = omanRoute(vo, cellVal)
			case 2:
				ok = omanBinNumber(vo, cellVal)
			case 3:
				ok = omanCardType(vo, cellVal)
			}
			if !ok {
				break
			}
		}
		if !iterated {
			vo.genStatus = 7
			vo.remarks = "No cells present in row"
		}
		vo.user = s.Cfg.UpdatedUser
		vo.jobNumber = jobSer
		if vo.genStatus != 7 {
			entity := omanMapToEntity(vo, now)
			omanList = append(omanList, entity)
			accTxnCount++
			if len(omanList) >= batchSize {
				p.flushBatch(ctx, omanList)
				omanList = omanList[:0]
			}
		} else {
			s.log().Info("OmanBin Rejected", "vo", vo)
		}
	}
	if len(omanList) > 0 {
		p.flushBatch(ctx, omanList)
	}

	p.updateFileUploadLog(ctx, uplSer, 4, "Success")
	s.updateProcess(ctx, uplSer, jobSer, totalCount, accTxnCount, 4)
	s.moveFile(filePath, fileName, s.Cfg.ReconProcessed)
}

// flushBatch mirrors: removeDuplicate(list) -> saveAll -> flush -> clear.
func (p *omanProcessor) flushBatch(ctx context.Context, list []*OmanNetRange) {
	s := p.svc
	seen := map[string]bool{}
	var bins []string
	for _, e := range list {
		if e.BinNumber == "" || seen[e.BinNumber] {
			continue
		}
		seen[e.BinNumber] = true
		bins = append(bins, e.BinNumber)
	}
	if existing, err := s.Store.FindOmanNetByBins(ctx, bins); err == nil && len(existing) > 0 {
		ids := make([]int64, 0, len(existing))
		for _, e := range existing {
			ids = append(ids, e.SerialNumber)
		}
		if derr := s.Store.DeleteOmanNet(ctx, ids); derr != nil {
			s.log().Error("omannet removeDuplicate delete", "err", derr)
		}
	} else if err != nil {
		s.log().Error("omannet removeDuplicate find", "err", err)
	}
	for _, e := range list {
		if ierr := s.Store.InsertOmanNetRange(ctx, e); ierr != nil {
			s.log().Error("omannet save", "err", ierr)
		}
	}
}

func (p *omanProcessor) updateFileUploadLog(ctx context.Context, uplSer, status int, remarks string) {
	e, err := p.svc.Store.FindUploadLogBySerialNumber(ctx, uplSer)
	if err != nil || e == nil {
		p.svc.log().Error("omannet update upload log: not found", "upl", uplSer, "err", err)
		return
	}
	e.UploadStatus = status
	r := remarks
	e.Remarks = &r
	if err := p.svc.Store.UpdateUploadLog(ctx, e); err != nil {
		p.svc.log().Error("omannet update upload log", "err", err)
	}
}

func (p *omanProcessor) updateProcess(ctx context.Context, uplSer, jobSer, total, acc, status int) {
	p.svc.updateProcess(ctx, uplSer, jobSer, total, acc, status)
}

func (p *omanProcessor) moveFile(sourcePath, fileName, destDir string) {
	p.svc.moveFile(sourcePath, fileName, destDir)
}

// --- column validators (mirror the OmanNetBinProcessing switch cases) ---

func omanSubRoute(vo *omanNetVO, v string) bool {
	v = strings.TrimSpace(v)
	if v == "" {
		vo.subRoute = ""
		vo.genStatus = 7
		vo.remarks = "No Value in Sub route"
		return false
	}
	if len(v) > 20 {
		vo.subRoute = ""
		vo.genStatus = 7
		vo.remarks = "Sub route length exceeds limit"
		return false
	}
	if strings.ContainsAny(v, "@#$%") {
		vo.subRoute = ""
		vo.genStatus = 7
		vo.remarks = "Invalid Value in Route"
		return false
	}
	vo.subRoute = strings.ToUpper(v)
	return true
}

func omanRoute(vo *omanNetVO, v string) bool {
	v = strings.TrimSpace(v)
	if v == "" {
		vo.route = ""
		vo.genStatus = 7
		vo.remarks = "No Value in Route"
		return false
	}
	if len(v) > 20 {
		vo.route = v
		vo.genStatus = 7
		vo.remarks = "Route length exceeds limit"
		return false
	}
	if strings.ContainsAny(v, "@#$%") {
		vo.route = ""
		vo.genStatus = 7
		vo.remarks = "Invalid Value in Route"
		return false
	}
	vo.route = strings.ToUpper(v)
	return true
}

func omanBinNumber(vo *omanNetVO, v string) bool {
	v = strings.TrimSpace(v)
	if v == "" {
		vo.binNumber = ""
		vo.genStatus = 7
		vo.remarks = "Null value Bin Number" + vo.String()
		return false
	}
	if len(v) <= 8 && digitsOnly(v) {
		vo.binNumber = v
		return true
	}
	vo.genStatus = 7
	vo.remarks = "Bin Number length exceeds" + vo.String()
	return false
}

func omanCardType(vo *omanNetVO, v string) bool {
	v = strings.TrimSpace(v)
	if v == "" {
		vo.cardType = ""
		return false // Java: setCardType(null) then breaks the loop (status stays 4)
	}
	if len(v) <= 7 && !strings.ContainsAny(v, "@#$%") {
		vo.cardType = v
		return true
	}
	vo.genStatus = 7
	vo.remarks = "Card Type length exceeds" + strings.ToUpper(vo.String())
	return false
}

func digitsOnly(v string) bool {
	if v == "" {
		return false
	}
	for _, r := range v {
		if r < '0' || r > '9' {
			return false
		}
	}
	return true
}

// omanNetVO mirrors OmanNetBinUtil.
type omanNetVO struct {
	user, jobNumber int
	genStatus       int
	subRoute        string
	route           string
	binNumber       string
	cardType        string
	remarks         string
}

// String mirrors the Lombok toString used inside Java remark messages.
func (o *omanNetVO) String() string {
	return "OmanNetBinUtil(user=" + itoa(o.user) + ", jobNumber=" + itoa(o.jobNumber) +
		", genStatus=" + itoa(o.genStatus) + ", subRoute=" + o.subRoute + ", route=" + o.route +
		", binNumber=" + o.binNumber + ", cardType=" + o.cardType + ", remarks=" + o.remarks + ")"
}

func itoa(n int) string {
	if n == 0 {
		return "0"
	}
	neg := n < 0
	if neg {
		n = -n
	}
	var buf [20]byte
	i := len(buf)
	for n > 0 {
		i--
		buf[i] = byte('0' + n%10)
		n /= 10
	}
	if neg {
		i--
		buf[i] = '-'
	}
	return string(buf[i:])
}

// omanMapToEntity mirrors OmanNetBinProcessing.mapToOmanNetData.
func omanMapToEntity(vo *omanNetVO, now time.Time) *OmanNetRange {
	ct := strings.ToLower(strings.TrimSpace(vo.cardType))
	return &OmanNetRange{
		LastUpdated: now,
		User:        vo.user,
		JobNumber:   vo.jobNumber,
		GenStatus:   vo.genStatus,
		Route:       strings.ToUpper(vo.route),
		SubRoute:    strings.ToUpper(vo.subRoute),
		BinNumber:   vo.binNumber,
		CardType:    resolveCardType(ct),
		Remarks:     strPtr(vo.remarks),
	}
}

// resolveCardType mirrors OmanNetBinProcessing.resolveCardType:
// credit->C, debit->D, default->P.
func resolveCardType(ct string) string {
	switch ct {
	case "credit":
		return "C"
	case "debit":
		return "D"
	default:
		return "P"
	}
}

func strPtr(s string) *string {
	if s == "" {
		return nil
	}
	return &s
}
