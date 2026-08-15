package binsvc

import (
	"context"
	"os"
	"strconv"
	"strings"
	"time"
)

// visaProcessor is the Go port of com.empay.visa.VisaARDEF.
type visaProcessor struct {
	svc *Service
}

// processVisaBin mirrors VisaARDEF.processVisaBin: read the ARDEF text file,
// wipe VISA_ISS_ACC_RANGE, then re-insert from the fixed-width lines.
func (p *visaProcessor) processVisaBin(ctx context.Context, fileName string, jobSer, uplSer int) {
	s := p.svc
	filePath := s.Cfg.ReconIn + fileName
	s.log().Info("VISA BIN FILE PROCESSING STARTED", "file", fileName)

	data, err := os.ReadFile(filePath)
	if err != nil {
		s.log().Error("processVisaBin read", "err", err)
		p.handleUploadError(ctx, uplSer)
		s.moveFile(filePath, fileName, s.Cfg.ReconRejected)
		return
	}

	lines := javaBuilderLines(string(data))
	batchSize := calculateBatchSize(len(lines))

	if len(lines) > 0 {
		if err := p.deleteFromVisaIssRange(ctx, batchSize); err != nil {
			s.log().Error("deleteFromVisaIssRange", "err", err)
			p.handleUploadError(ctx, uplSer)
			s.moveFile(filePath, fileName, s.Cfg.ReconRejected)
			return
		}
		total := 0
		for _, line := range lines {
			entity, perr := p.readLine(ctx, jobSer, line)
			if perr != nil {
				p.handleUploadError(ctx, uplSer)
				s.moveFile(filePath, fileName, s.Cfg.ReconRejected)
				return
			}
			if err := s.Store.InsertVisaRange(ctx, entity); err != nil {
				s.log().Error("save visa range", "err", err)
				p.handleUploadError(ctx, uplSer)
				s.moveFile(filePath, fileName, s.Cfg.ReconRejected)
				return
			}
			total++
		}
		p.updateProcess(ctx, uplSer, jobSer, total)
		s.moveFile(filePath, fileName, s.Cfg.ReconProcessed)
		s.log().Info("VISA ARDEF file Total count", "count", total)
		s.log().Info("FILE PROCESSING COMPLETED SUCCESSFULLY")
	} else {
		s.moveFile(filePath, fileName, s.Cfg.ReconProcessed)
		s.log().Info("VISA ARDEF file Total count", "count", 0)
		s.log().Info("FILE PROCESSING COMPLETED")
	}
}

// readLine maps one fixed-width line to a VisaIssAcqRangeEntity. A line
// shorter than 76 chars or with non-numeric length fails, mirroring the Java
// StringIndexOutOfBounds / NumberFormatException path (which aborts the file).
func (p *visaProcessor) readLine(ctx context.Context, jobSer int, line string) (*VisaRange, error) {
	if len(line) < 76 {
		return nil, &visaLineError{msg: "line too short"}
	}
	binLen, err := strconv.Atoi(strings.TrimSpace(line[31:33]))
	if err != nil {
		return nil, &visaLineError{msg: "bad bin length"}
	}
	return &VisaRange{
		LastUpdated:      time.Now(),
		UpdatedUser:      p.svc.Cfg.UpdatedUser,
		JobSerialNumber:  jobSer,
		IssRangeHigh:     strings.TrimSpace(line[0:9]),
		IssRangeLow:      strings.TrimSpace(line[12:21]),
		Bin:              line[24:30],
		BinLength:        binLen,
		ProcessingBin:    line[35:41],
		Domain:           line[41:42],
		Region:           line[42:43],
		CountryAlphaCode: line[43:45],
		CardProduct:      line[58:60],
		CrdrIndicator:    line[69:70],
		ProductSubType:   line[74:76],
	}, nil
}

type visaLineError struct{ msg string }

func (e *visaLineError) Error() string { return "binsvc: visa line: " + e.msg }

func (p *visaProcessor) handleUploadError(ctx context.Context, uplSer int) {
	e, err := p.svc.Store.FindUploadLogBySerialNumber(ctx, uplSer)
	if err != nil || e == nil {
		p.svc.log().Error("handleUploadError: upload log not found", "upl", uplSer, "err", err)
		return
	}
	e.UploadStatus = 5
	remarks := "An error occurred while attempting to read the file."
	e.Remarks = &remarks
	if err := p.svc.Store.UpdateUploadLog(ctx, e); err != nil {
		p.svc.log().Error("handleUploadError: update", "err", err)
	}
}

func (p *visaProcessor) updateProcess(ctx context.Context, uplSer, jobSer, total int) {
	p.svc.updateProcess(ctx, uplSer, jobSer, total, total, 4)
}

// deleteFromVisaIssRange mirrors VisaARDEF.deleteFromVisaIssRange: repeatedly
// fetch a page of rows and delete them until the table is empty.
func (p *visaProcessor) deleteFromVisaIssRange(ctx context.Context, batchSize int) error {
	for {
		rows, err := p.svc.Store.FetchVisaRangeBatch(ctx, batchSize)
		if err != nil {
			return err
		}
		if len(rows) == 0 {
			return nil
		}
		ids := make([]int64, 0, len(rows))
		for _, r := range rows {
			ids = append(ids, r.SerialNumber)
		}
		if err := p.svc.Store.DeleteVisaRanges(ctx, ids); err != nil {
			return err
		}
	}
}

// calculateBatchSize mirrors VisaARDEF.calculateBatchSize: clamp lines/10 to
// the [1000, 2000] range.
func calculateBatchSize(totalLineCount int) int {
	max, min := 2000, 1000
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

// javaBuilderLines reconstructs exactly what the Java processVisaBin produced:
// readLine() lines joined with "\n" then split on "\n". Java's String.split
// drops trailing empty strings, so those are trimmed here too.
func javaBuilderLines(data string) []string {
	var b strings.Builder
	for _, l := range javaReadLines(data) {
		b.WriteString(l)
		b.WriteString("\n")
	}
	parts := strings.Split(b.String(), "\n")
	for len(parts) > 0 && parts[len(parts)-1] == "" {
		parts = parts[:len(parts)-1]
	}
	return parts
}

// javaReadLines splits data on "\r\n", "\r" or "\n", mirroring
// BufferedReader.readLine terminator handling (terminators stripped).
func javaReadLines(data string) []string {
	var lines []string
	start := 0
	for i := 0; i < len(data); i++ {
		switch data[i] {
		case '\r':
			lines = append(lines, data[start:i])
			if i+1 < len(data) && data[i+1] == '\n' {
				i++
			}
			start = i + 1
		case '\n':
			lines = append(lines, data[start:i])
			start = i + 1
		}
	}
	if start < len(data) {
		lines = append(lines, data[start:])
	}
	return lines
}
