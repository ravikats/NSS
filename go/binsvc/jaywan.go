package binsvc

import (
	"context"
	"encoding/csv"
	"os"
	"strconv"
	"strings"
	"time"
)

// jaywanProcessor is the Go port of com.empay.jaywan.JaywanBinProcessImpl.
type jaywanProcessor struct {
	svc *Service
}

// processJaywanBin mirrors JaywanBinProcessImpl.processJaywanBin (17-column
// CSV: 0=issuerBank, 1=institutionId, 2=binRangeLow, 3=binRangeHigh,
// 4=panLength, 5=productType, 6=schemeCode, 7=schemeProduct, 8=cardType,
// 9=service, 10=currencyCode, 11=isoNumCurrCode, 12=actionTaken, 13=binLength,
// 14=issAccCap, 15=prodClssfy, 16=badgeInd).
func (p *jaywanProcessor) processJaywanBin(ctx context.Context, fileName string, jobSer, uplSer int) {
	s := p.svc
	filePath := s.Cfg.ReconIn + fileName
	s.log().Info("JAYWAN BIN FILE PROCESSING STARTED", "file", fileName)

	f, err := os.Open(filePath)
	if err != nil {
		s.log().Error("open jaywan bin file", "err", err)
		return
	}
	defer f.Close()

	totalLineCount := csvLineCount(f)
	batchCount := csvBatchSize(totalLineCount)
	const deletionCount = 1000

	reader := csv.NewReader(f)
	reader.FieldsPerRecord = -1
	if _, err := reader.Read(); err != nil { // skip header
		s.log().Error("jaywan header read", "err", err)
		return
	}

	totalCount, totalAccTxnCount := 0, 0
	var entityList []*JaywanRange
	var deleteSerials []int64
	for {
		next, err := reader.Read()
		if err != nil {
			break
		}
		if !jaywanValidLine(next) {
			continue
		}
		low, _ := strconv.ParseInt(next[2], 10, 64)
		high, _ := strconv.ParseInt(next[3], 10, 64)
		if existing, ferr := s.Store.FindJaywanRanges(ctx, low, high); ferr == nil {
			for _, e := range existing {
				deleteSerials = append(deleteSerials, e.SerialNumber)
			}
			if len(deleteSerials) > deletionCount {
				if derr := s.Store.DeleteJaywanRanges(ctx, deleteSerials); derr != nil {
					s.log().Error("jaywan delete batch", "err", derr)
				}
				deleteSerials = deleteSerials[:0]
			}
		} else if ferr != nil {
			s.log().Error("jaywan find", "low", low, "err", ferr)
		}

		if entity := jaywanReadLine(s.Cfg.UpdatedUser, jobSer, next); entity != nil {
			entityList = append(entityList, entity)
			if len(entityList) >= batchCount {
				for _, e := range entityList {
					if ierr := s.Store.InsertJaywanRange(ctx, e); ierr != nil {
						s.log().Error("jaywan save batch", "err", ierr)
					}
				}
				entityList = entityList[:0]
			}
			totalAccTxnCount++
		}
		totalCount++
	}

	if len(entityList) > 0 {
		for _, e := range entityList {
			if ierr := s.Store.InsertJaywanRange(ctx, e); ierr != nil {
				s.log().Error("jaywan save", "err", ierr)
			}
		}
	}
	if len(deleteSerials) > 0 {
		if derr := s.Store.DeleteJaywanRanges(ctx, deleteSerials); derr != nil {
			s.log().Error("jaywan delete", "err", derr)
		}
	}

	s.updateProcess(ctx, uplSer, jobSer, totalCount, totalAccTxnCount, 4)
	s.moveFile(filePath, fileName, s.Cfg.ReconProcessed)
	s.log().Info("JAYWAN BIN FILE PROCESSING COMPLETED", "file", fileName)
}

func (p *jaywanProcessor) updateProcess(ctx context.Context, uplSer, jobSer, total, acc, status int) {
	p.svc.updateProcess(ctx, uplSer, jobSer, total, acc, status)
}

func (p *jaywanProcessor) moveFile(sourcePath, fileName, destDir string) {
	p.svc.moveFile(sourcePath, fileName, destDir)
}

// jaywanValidLine mirrors JaywanBinProcessImpl.isValidLine: joined CSV row
// must contain exactly 16 commas (17 fields).
func jaywanValidLine(row []string) bool {
	if len(row) != 17 {
		return false
	}
	return strings.Count(strings.Join(row, ","), ",") == 16
}

// jaywanReadLine mirrors JaywanBinProcessImpl.readLine; nil on parse error
// (mirroring the Java catch that nulls the entity).
func jaywanReadLine(user, jobSer int, row []string) (e *JaywanRange) {
	e = &JaywanRange{LastUpdated: time.Now(), UpdatedUser: user, JobNumber: jobSer}
	defer func() {
		if r := recover(); r != nil {
			e = nil
		}
	}()
	atoi := func(v string) int {
		n, err := strconv.Atoi(v)
		if err != nil {
			panic(err)
		}
		return n
	}
	atol := func(v string) int64 {
		n, err := strconv.ParseInt(v, 10, 64)
		if err != nil {
			panic(err)
		}
		return n
	}
	ch := func(v string) string {
		if v == "" {
			panic("empty char field")
		}
		return v[:1]
	}
	e.IssuerBank = row[0]
	e.InstitutionID = atoi(row[1])
	e.BinRangeLow = atol(row[2])
	e.BinRangeHigh = atol(row[3])
	e.PanLength = atoi(row[4])
	e.ProductType = ch(row[5])
	e.SchemeCode = ch(row[6])
	e.SchemeProduct = row[7]
	e.CardType = atoi(row[8])
	e.Service = atoi(row[9])
	e.CurrencyCode = atoi(row[10])
	e.IsoNumCurrCode = atoi(row[11])
	e.ActionTaken = ch(row[12])
	e.BinLength = atoi(row[13])
	e.IssAccCap = ch(row[14])
	e.ProdClssfy = ch(row[15])
	e.BadgeInd = row[16]
	return
}

// csvLineCount counts data lines minus the header, mirroring
// JaywanBinProcessImpl.getTotalLineCount (lines().skip(1).count()).
func csvLineCount(f *os.File) int {
	data, err := os.ReadFile(f.Name())
	if err != nil {
		return 0
	}
	lines := strings.Split(strings.TrimSuffix(string(data), "\n"), "\n")
	if len(lines) == 0 {
		return 0
	}
	return len(lines) - 1
}

// csvBatchSize mirrors the Jaywan/Mercury/OmanNet batch sizing: clamp
// totalLineCount/10 to the [3500, 10000] range.
func csvBatchSize(totalLineCount int) int {
	max, min := 10000, 3500
	calc := totalLineCount / 10
	switch {
	case calc > max:
		return max
	case calc < min:
		return min
	default:
		return calc
	}
}
