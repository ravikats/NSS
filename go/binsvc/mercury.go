package binsvc

import (
	"context"
	"encoding/csv"
	"os"
	"strconv"
	"strings"
	"time"
)

// mercuryProcessor is the Go port of com.empay.mercury.MercuryBinProcessing.
type mercuryProcessor struct {
	svc *Service
}

// processMercuryBin mirrors MercuryBinProcessing.processMercuryBin (9-column
// CSV: 0=binRangeLow, 1=binRangeHigh, 2=cardType, 3=cardProductId,
// 4=cardVariant, 5=cardScheme, 6=currencyCode, 7=countryCode, 8=status).
func (p *mercuryProcessor) processMercuryBin(ctx context.Context, fileName string, jobSer, uplSer int) {
	s := p.svc
	filePath := s.Cfg.ReconIn + fileName
	s.log().Info("MERCURY BIN FILE PROCESSING STARTED", "file", fileName)

	f, err := os.Open(filePath)
	if err != nil {
		s.log().Error("open mercury bin file", "err", err)
		return
	}
	defer f.Close()

	batchCount := csvBatchSize(csvLineCount(f))

	reader := csv.NewReader(f)
	reader.FieldsPerRecord = -1
	if _, err := reader.Read(); err != nil { // skip header
		s.log().Error("mercury header read", "err", err)
		return
	}

	totalCount, totalAccTxnCount := 0, 0
	var saveList []*MercuryRange
	var deleteSerials []int64
	for {
		next, err := reader.Read()
		if err != nil {
			break
		}
		entity := mercuryReadLine(s.Cfg.UpdatedUser, jobSer, next)
		if !mercuryValidLine(next) || entity == nil {
			continue
		}
		action := entity.Status
		existing, _ := s.Store.FindMercuryRanges(ctx, entity.BinRangeLow, entity.BinRangeHigh)
		switch action {
		case "A":
			saveList = append(saveList, entity)
			totalAccTxnCount++
		case "E", "U":
			if len(existing) > 0 {
				for _, e := range existing {
					deleteSerials = append(deleteSerials, e.SerialNumber)
				}
			}
			saveList = append(saveList, entity)
			totalAccTxnCount++
		case "D":
			if len(existing) > 0 {
				for _, e := range existing {
					deleteSerials = append(deleteSerials, e.SerialNumber)
				}
			}
		default:
			s.log().Warn("Invalid status value", "status", action)
		}
		if len(deleteSerials) >= batchCount {
			if derr := s.Store.DeleteMercuryRanges(ctx, deleteSerials); derr != nil {
				s.log().Error("mercury delete batch", "err", derr)
			}
			deleteSerials = deleteSerials[:0]
		}
		if len(saveList) >= batchCount {
			p.flushSaves(ctx, saveList)
			saveList = saveList[:0]
		}
		totalCount++
	}
	if len(deleteSerials) > 0 {
		if derr := s.Store.DeleteMercuryRanges(ctx, deleteSerials); derr != nil {
			s.log().Error("mercury delete", "err", derr)
		}
	}
	if len(saveList) > 0 {
		p.flushSaves(ctx, saveList)
	}

	s.updateProcess(ctx, uplSer, jobSer, totalCount, totalAccTxnCount, 4)
	s.moveFile(filePath, fileName, s.Cfg.ReconProcessed)
}

func (p *mercuryProcessor) flushSaves(ctx context.Context, list []*MercuryRange) {
	for _, e := range list {
		if ierr := p.svc.Store.InsertMercuryRange(ctx, e); ierr != nil {
			p.svc.log().Error("mercury save", "err", ierr)
		}
	}
}

func (p *mercuryProcessor) updateProcess(ctx context.Context, uplSer, jobSer, total, acc, status int) {
	p.svc.updateProcess(ctx, uplSer, jobSer, total, acc, status)
}

func (p *mercuryProcessor) moveFile(sourcePath, fileName, destDir string) {
	p.svc.moveFile(sourcePath, fileName, destDir)
}

// mercuryValidLine mirrors MercuryBinProcessing.isValidLine: exactly 9 fields
// and not all blank.
func mercuryValidLine(row []string) bool {
	if row == nil || len(row) != 9 {
		return false
	}
	for _, v := range row {
		if strings.TrimSpace(v) != "" {
			return true
		}
	}
	return false
}

// mercuryReadLine mirrors MercuryBinProcessing.readLine; nil on parse error.
func mercuryReadLine(user, jobSer int, row []string) (e *MercuryRange) {
	e = &MercuryRange{LastUpdated: time.Now(), UpdatedUser: user, JobNumber: jobSer}
	defer func() {
		if r := recover(); r != nil {
			e = nil
		}
	}()
	atoi := func(v string) int {
		n, err := strconv.Atoi(strings.TrimSpace(v))
		if err != nil {
			panic(err)
		}
		return n
	}
	atol := func(v string) int64 {
		n, err := strconv.ParseInt(strings.TrimSpace(v), 10, 64)
		if err != nil {
			panic(err)
		}
		return n
	}
	e.BinRangeLow = atol(row[0])
	e.BinRangeHigh = atol(row[1])
	e.CardType = atoi(row[2])
	e.CardProductID = atoi(row[3])
	e.CardVariant = atoi(row[4])
	e.CardScheme = atoi(row[5])
	e.CurrencyCode = atoi(row[6])
	e.CountryCode = atoi(row[7])
	if row[8] == "" {
		panic("empty status")
	}
	e.Status = row[8][:1]
	return
}
