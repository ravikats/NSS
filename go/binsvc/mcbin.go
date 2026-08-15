package binsvc

import (
	"bytes"
	"context"
	"os"
	"time"
)

// mcProcessor is the Go port of com.empay.ipm.McT67Pro.
type mcProcessor struct {
	svc *Service
}

var (
	mcDelimiter   = []byte{2, 3, 0} // STX / ETX / NUL
	mcSeparators  = map[byte]bool{0xb8: true, 0xa9: true, 0xc2: true} // \u00b8 \u00a9 \u00c2
	mcTrailerMark = []byte("PTRAILER RECORD IP0040T1")
	mcCardProgs   = map[string]bool{"DMC": true, "MCC": true, "MSI": true, "PVL": true}
)

// processMCBin mirrors McT67Pro.processMCBin: character-level scan of the
// T067/T068 file (raw bytes preserved as-is, matching the Java char reads),
// inserting a MC_ISS_ACC_RANGE row per record between separator delimiters.
func (p *mcProcessor) processMCBin(ctx context.Context, fileName string, jobSer, uplSer int) {
	filePath := p.svc.Cfg.ReconIn + fileName
	s := p.svc
	s.log().Info("MC BIN FILE PROCESSING STARTED", "file", fileName)

	businessDate := s.businessDate(ctx)

	data, err := os.ReadFile(filePath)
	if err != nil {
		s.log().Error("processMCBin read", "file", fileName, "err", err)
		p.fail(ctx, uplSer, fileName)
		return
	}

	total := 0
	var builder []byte
	var abc []byte
	flag := false
	for i := 0; i < len(data); i++ {
		ch := data[i]
		builder = append(builder, ch)
		if ch == 2 || ch == 3 || ch == 0 {
			if bytes.Contains(builder, mcTrailerMark) && !flag {
				s.log().Info("End of file")
				break
			}
			builder = builder[:0]
			if flag {
				flag = false
				total++
				p.insertMcIssAccRange(ctx, jobSer, s.Cfg.UpdatedUser, string(abc), businessDate)
				abc = abc[:0]
			}
		}
		if mcSeparators[ch] {
			flag = true
			if i+1 < len(data) {
				i++
				ch = data[i]
			}
		}
		if !flag || ch == '@' {
			continue
		}
		abc = append(abc, ch)
	}

	s.updateProcess(ctx, uplSer, jobSer, total, total, 4)
	s.log().Info("MC T067/T068 file Total count", "count", total)
	s.log().Info("FILE PROCESSING COMPLETED SUCCESSFULLY")
	s.moveFile(filePath, fileName, s.Cfg.ReconProcessed)
}

// insertMcIssAccRange mirrors McT67Pro.insertMcIssAccRange. A record is
// accepted only when cardProgId is DMC/MCC/MSI/PVL and active code == "A".
func (p *mcProcessor) insertMcIssAccRange(ctx context.Context, jobSer, user int, line string, businessDate *time.Time) {
	if len(line) < 159 {
		p.svc.log().Error("insertMcIssAccRange: record shorter than 159 chars", "len", len(line))
		return
	}
	e := &McRange{
		UpdatedUser:     user,
		JobSerialNumber: jobSer,
		ActiveCode:      line[7:8],
		IssRangeLow:     line[11:30],
		GcmsProductID:   line[30:33],
		IssRangeHigh:    line[33:52],
		CardProgID:      line[52:55],
		PriorityCode:    line[55:57],
		MemberID:        line[57:68],
		ProdTypeID:      line[68:69],
		EndPoint:        line[69:76],
		CountryAlphaCode: line[76:79],
		CountryCode:     line[79:82],
		Region:          line[82:83],
		ProductClass:    line[83:86],
		TxnRoutInd:      line[86:87],
		FpReasignSwitch: line[87:88],
		ProdReasignSwitch: line[88:89],
		PwcbSwitch:      line[89:90],
		LicProdID:       line[90:93],
		MapServInd:      line[93:94],
		AccLevelInd:     line[94:95],
		ChBillCurr:      line[101:104],
		ChBillCurrExp:   line[104:105],
		ChipServInd:     line[133:134],
		FloorExpDate:    line[134:140],
		CoBrandSwitch:   line[140:141],
		SpendControlSwitch: line[141:142],
		MeCleansingService: line[142:145],
		MePayPassInd:    line[151:152],
		RateTypeInd:     line[152:153],
		PsnRouteInd:     line[153:154],
		CbWithoutPurchase: line[154:155],
		RepowerReloadInd: line[156:157],
		MoneySendInd:    line[157:158],
		DurbinRateInd:   line[158:159],
		GenStatus:       1,
		BussDate:        businessDate,
	}
	eff, err := p.svc.convertToGregorianDate(line[0:5])
	if err != nil {
		p.svc.log().Error("convert effective date", "line", line[0:5], "err", err)
		return
	}
	e.EffectiveDate = eff

	if !mcCardProgs[e.CardProgID] || e.ActiveCode != "A" {
		return
	}

	// Java: deleteMCIssAcqRange(low, high, priority) then insert.
	if existing, err := p.svc.Store.FindMcRange(ctx, e.IssRangeLow, e.IssRangeHigh, e.PriorityCode); err == nil && existing != nil {
		if derr := p.svc.Store.DeleteMcRange(ctx, existing); derr != nil {
			p.svc.log().Error("delete mc range", "ser", existing.SerialNumber, "err", derr)
		}
	} else if err != nil {
		p.svc.log().Error("find mc range", "low", e.IssRangeLow, "err", err)
	}
	if err := p.svc.Store.InsertMcRange(ctx, e); err != nil {
		p.svc.log().Error("insert mc range", "low", e.IssRangeLow, "err", err)
	}
}

// fail marks the upload as failed (status 5) and moves the file to the
// rejected directory, mirroring the MC error catch block.
func (p *mcProcessor) fail(ctx context.Context, uplSer int, fileName string) {
	e, err := p.svc.Store.FindUploadLogBySerialNumber(ctx, uplSer)
	if err != nil || e == nil {
		p.svc.log().Error("fail: upload log not found", "upl", uplSer, "err", err)
		return
	}
	e.UploadStatus = 5
	remarks := "An error occurred while attempting to read the file."
	e.Remarks = &remarks
	if err := p.svc.Store.UpdateUploadLog(ctx, e); err != nil {
		p.svc.log().Error("fail: update upload log", "err", err)
	}
	p.svc.moveFile(p.svc.Cfg.ReconIn+fileName, fileName, p.svc.Cfg.ReconRejected)
}
