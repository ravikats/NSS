// Package-level service wiring for the MPGS DCF file pipeline.
//
// It mirrors the Java flow end to end: validate the request, create a
// PROCESSING_JOBS row, insert a FILE_UPLOAD_LOG row, read the file (skipping
// the 6200 header line), tokenize each 62xx record, enrich a POS_TRANSACTIONS
// entity via the stateful processor, persist it (chunk of 10, like Spring Batch),
// then hand the job to the existing IRF step which calls irf-service over HTTP.
package mpgsdcf

import (
	"bufio"
	"context"
	"database/sql"
	"fmt"
	"log/slog"
	"os"
	"strings"
	"time"

	"empay/irf/mpgs"
	"empay/irf/mpgssvc"

	go_ora "github.com/sijms/go-ora/v2"
)

// Config holds the MPGS job parameters that the Java flow reads from env /
// the job context (INS_CODE, INTERFACE_CODE_MPGS, UPDATED_USER, ...).
type Config struct {
	InsCode          int    // INS_CODE
	IntCode          int    // INTERFACE_CODE_MPGS
	UserSerNumber    int    // UPDATED_USER
	SystemFileFormat int    // MPGS_SYSTEM_FILE_FORMAT_CODE
	InsShortName     string // insShortName (institution short name for RECON_IN_*)
	InputDir         string // RECON_IN_<insShortName> (defaults to InsShortName-based)
	ProcessName      string // processName (default "MPGS")
	JaywanRangesFile string // CSV of Jaywan card ranges (default "" -> none)
}

// FileService runs the MPGS DCF pipeline.
type FileService struct {
	DB     *sql.DB
	Client mpgs.IrfClient
	Log    *slog.Logger
	Cfg    Config
	jaywan JaywanRanges
}

// New wires the file service to an Oracle pool and the irf-service client.
func NewFileService(db *sql.DB, baseURL, secret string, log *slog.Logger, cfg Config) *FileService {
	fs := &FileService{
		DB:     db,
		Client: mpgs.NewClient(baseURL, secret),
		Log:    log,
		Cfg:    cfg,
	}
	if cfg.JaywanRangesFile != "" {
		rs, warns, err := LoadJaywanRanges(cfg.JaywanRangesFile)
		if err != nil {
			log.Warn("load jaywan ranges", "path", cfg.JaywanRangesFile, "err", err)
		} else {
			for _, w := range warns {
				log.Warn("jaywan ranges", "path", cfg.JaywanRangesFile, "warn", w)
			}
			fs.jaywan = rs
			log.Info("jaywan ranges loaded", "path", cfg.JaywanRangesFile, "count", len(rs))
		}
	}
	return fs
}

// Result summarises one MPGS file run (mirrors MpgsResponseVO + job state).
type Result struct {
	Message  string   `json:"message"`
	JobNumber int     `json:"jobNumber"`
	Rows     int      `json:"rows"`
	Updated  int      `json:"updated"`
	Callbacks int     `json:"callbacks"`
	Errors   []string `json:"errors,omitempty"`
}

// ProcessFile mirrors PGFileProcessingController.processFile: validates the
// filename + network, then runs the job.
func (s *FileService) ProcessFile(ctx context.Context, fileName, network string) (Result, error) {
	res := Result{}
	if !strings.EqualFold(network, "MASTERCARD") {
		res.Message = "network must be MASTERCARD"
		return res, fmt.Errorf("mpgsdcf: network %q != MASTERCARD", network)
	}
	if !s.isValid(ctx, fileName) {
		res.Message = "The filename already exists"
		return res, fmt.Errorf("mpgsdcf: duplicate filename %q", fileName)
	}

	path := s.inputPath(fileName)
	if _, err := os.Stat(path); err != nil {
		res.Message = "The file was not found at the specified path " + path
		return res, err
	}

	jobNumber, err := s.insertProcessingJob(ctx)
	if err != nil {
		res.Message = err.Error()
		return res, err
	}
	res.JobNumber = jobNumber

	uplSerNumber, err := s.insertFileUploadLog(ctx, fileName, jobNumber)
	if err != nil {
		res.Message = err.Error()
		s.updateProcessingJob(ctx, jobNumber, 5)
		return res, err
	}
	s.Log.Info("job started", "job", jobNumber, "file", fileName, "upl", uplSerNumber)

	rows, errors, err := s.ingest(ctx, path, jobNumber)
	res.Rows = rows
	res.Errors = errors
	if err != nil {
		res.Message = err.Error()
		s.updateProcessingJob(ctx, jobNumber, 5)
		s.updateFileUploadLog(ctx, uplSerNumber, 5, rows, 0)
		return res, err
	}

	irf, err := s.calculateIrf(ctx, jobNumber)
	res.Updated = irf.Updated
	res.Callbacks = irf.Callbacks
	res.Errors = append(res.Errors, irf.Errors...)
	if err != nil {
		res.Message = "IRF CALCULATION FAILED! " + err.Error()
		s.updateProcessingJob(ctx, jobNumber, 5)
		s.updateFileUploadLog(ctx, uplSerNumber, 5, rows, 0)
		return res, err
	}

	s.updateProcessingJob(ctx, jobNumber, 4)
	s.updateFileUploadLog(ctx, uplSerNumber, 4, rows, 0)
	res.Message = "MPGS File Processing Completed"
	s.Log.Info("job completed", "job", jobNumber, "rows", rows)
	return res, nil
}

// ingest reads the file (skipping the first line), tokenizes each record and
// persists the produced entities in chunks of 10 (Java chunk(10)).
func (s *FileService) ingest(ctx context.Context, path string, jobNumber int) (int, []string, error) {
	f, err := os.Open(path)
	if err != nil {
		return 0, nil, err
	}
	defer f.Close()

	proc := &Processor{
		UserSerNumber: s.Cfg.UserSerNumber,
		InsCode:       s.Cfg.InsCode,
		IntCode:       s.Cfg.IntCode,
		JobNumber:     jobNumber,
		CountryCode:   s.countryLookup,
		Jaywan:        s.jaywan,
	}

	sc := bufio.NewScanner(f)
	sc.Buffer(make([]byte, 64*1024), 64*1024)

	var errors []string
	var entities []*Entity
	var rows int
	lineNo := 0
	for sc.Scan() {
		lineNo++
		if lineNo == 1 {
			continue // reader.setLinesToSkip(1): the 6200 header
		}
		line := strings.TrimRight(sc.Text(), "\r")
		rec, err := ParseRecord(line)
		if err != nil {
			errors = append(errors, fmt.Sprintf("line %d: %v", lineNo, err))
			continue
		}
		ent, err := proc.Process(rec)
		if err != nil {
			errors = append(errors, fmt.Sprintf("line %d: %v", lineNo, err))
			continue
		}
		if ent != nil {
			entities = append(entities, ent)
		}
		if len(entities) == 10 {
			rows += s.insertChunk(ctx, entities)
			entities = entities[:0]
		}
	}
	if err := sc.Err(); err != nil {
		return rows, errors, err
	}
	if len(entities) > 0 {
		rows += s.insertChunk(ctx, entities)
	}
	return rows, errors, nil
}

// insertChunk persists up to 10 entities in one transaction. Returns the count
// actually inserted. Errors are appended to the shared error list by the caller
// via insertEntity.
func (s *FileService) insertChunk(ctx context.Context, entities []*Entity) int {
	tx, err := s.DB.BeginTx(ctx, nil)
	if err != nil {
		s.Log.Error("begin chunk", "err", err)
		return 0
	}
	defer tx.Rollback()

	n := 0
	for _, e := range entities {
		if err := s.InsertEntity(ctx, tx, e); err != nil {
			s.Log.Error("insert entity", "ser", e.SerialNumber, "err", err)
			continue
		}
		n++
	}
	if err := tx.Commit(); err != nil {
		s.Log.Error("commit chunk", "err", err)
		return 0
	}
	return n
}

// calculateIrf delegates to the existing mpgssvc.Service.ProcessJob, which
// selects the job's POS_TRANSACTIONS rows and calls irf-service over HTTP.
func (s *FileService) calculateIrf(ctx context.Context, jobNumber int) (mpgssvc.Summary, error) {
	svc := &mpgssvc.Service{DB: s.DB, Client: s.Client, Log: s.Log}
	return svc.ProcessJob(ctx, jobNumber, s.Cfg.InsCode)
}

// inputPath resolves RECON_IN_<insShortName> + fileName.
func (s *FileService) inputPath(fileName string) string {
	return strings.TrimRight(s.Cfg.InputDir, "/") + "/" + fileName
}

// isValid mirrors CommonManagementService.isValid: a file name is valid only if
// it does not already exist in FILE_UPLOAD_LOG.
func (s *FileService) isValid(ctx context.Context, fileName string) bool {
	var count int
	if err := s.DB.QueryRowContext(ctx,
		`SELECT COUNT(*) FROM FILE_UPLOAD_LOG WHERE UPL_FILE_NAME = :1`, fileName).Scan(&count); err != nil {
		s.Log.Error("isValid", "err", err)
		return false
	}
	return count == 0
}

// insertProcessingJob mirrors CommonManagementService.insertProcessingJob
// (status 1, processName, start time now) and returns the PRJ_SER_NUMBER.
func (s *FileService) insertProcessingJob(ctx context.Context) (int, error) {
	name := s.Cfg.ProcessName
	if name == "" {
		name = "MPGS"
	}
	var id int64
	_, err := s.DB.ExecContext(ctx, `
		INSERT INTO PROCESSING_JOBS (
			PRJ_LAST_UPDATED, PRJ_UPDATED_USER, PRJ_INS_CODE, PRJ_REF_NUMBER,
			PRJ_PROCESS_NAME, PRJ_START_TIME, PRJ_STATUS)
		VALUES (SYSDATE, :1, :2, 1, :3, SYSDATE, 1)
		RETURNING PRJ_SER_NUMBER INTO :4`,
		s.Cfg.UserSerNumber, s.Cfg.InsCode, name,
		go_ora.Out{Dest: &id})
	if err != nil {
		return 0, fmt.Errorf("mpgsdcf: insert processing job: %w", err)
	}
	return int(id), nil
}

// insertFileUploadLog mirrors CommonManagementService.insertFileUploadLog.
func (s *FileService) insertFileUploadLog(ctx context.Context, fileName string, jobNumber int) (int, error) {
	formatCode, err := s.fileFormatCode(ctx)
	if err != nil {
		return 0, err
	}
	var id int64
	_, err = s.DB.ExecContext(ctx, `
		INSERT INTO FILE_UPLOAD_LOG (
			UPL_LAST_UPDATED, UPL_UPDATED_USER, UPL_INS_CODE, UPL_INT_CODE,
			UPL_FOR_CODE, UPL_FILE_NAME, UPL_UPLOAD_DATE, UPL_UPLOAD_STATUS,
			UPL_PROC_DATE, UPL_BUSS_DATE, UPL_PRJ_SER_NUMBER, UPL_FILE_ID,
			UPL_TOT_ACCP_TXN_COUNT, UPL_TOT_TXN_COUNT, UPL_TOT_TXN_AMOUNT, UPL_TOT_ACCP_TXN_AMOUNT)
		VALUES (SYSDATE, :1, :2, :3, :4, :5, SYSDATE, 9, SYSDATE, :6, :7, :8, 0, 0, 0, 0)
		RETURNING UPL_SER_NUMBER INTO :9`,
		s.Cfg.UserSerNumber, s.Cfg.InsCode, s.Cfg.IntCode, formatCode, fileName,
		s.businessDate(ctx), jobNumber, fileName, go_ora.Out{Dest: &id})
	if err != nil {
		return 0, fmt.Errorf("mpgsdcf: insert file upload log: %w", err)
	}
	return int(id), nil
}

// updateProcessingJob mirrors CommonManagementService.updateProcessingJob.
func (s *FileService) updateProcessingJob(ctx context.Context, jobNumber, status int) {
	if _, err := s.DB.ExecContext(ctx, `
		UPDATE PROCESSING_JOBS
		SET PRJ_STATUS = :1, PRJ_END_TIME = SYSDATE, PRJ_LAST_UPDATED = SYSDATE
		WHERE PRJ_SER_NUMBER = :2`, status, jobNumber); err != nil {
		s.Log.Error("update processing job", "job", jobNumber, "err", err)
	}
}

// updateFileUploadLog mirrors CommonManagementService.updateFileUploadLog.
func (s *FileService) updateFileUploadLog(ctx context.Context, uplSerNumber, status, totalTxnCount int, totalTxnAmount float64) {
	if _, err := s.DB.ExecContext(ctx, `
		UPDATE FILE_UPLOAD_LOG
		SET UPL_UPLOAD_STATUS = :1, UPL_TOT_TXN_COUNT = :2, UPL_TOT_TXN_AMOUNT = :3,
			UPL_TOT_ACCP_TXN_COUNT = :4, UPL_TOT_ACCP_TXN_AMOUNT = :5, UPL_LAST_UPDATED = SYSDATE
		WHERE UPL_SER_NUMBER = :6`, status, totalTxnCount, totalTxnAmount, totalTxnCount, totalTxnAmount, uplSerNumber); err != nil {
		s.Log.Error("update file upload log", "upl", uplSerNumber, "err", err)
	}
}

// fileFormatCode resolves FOR_CODE from FOR_SYSTEM_CODE (Java
// fileFormatRepo.findBySystemCode(...).getCode()).
func (s *FileService) fileFormatCode(ctx context.Context) (int, error) {
	var code sql.NullInt64
	err := s.DB.QueryRowContext(ctx,
		`SELECT FOR_CODE FROM FILE_FORMATS WHERE FOR_SYSTEM_CODE = :1`, s.Cfg.SystemFileFormat).Scan(&code)
	if err != nil {
		return 0, fmt.Errorf("mpgsdcf: file format %d: %w", s.Cfg.SystemFileFormat, err)
	}
	return int(code.Int64), nil
}

// businessDate mirrors businessDateRepo.findByInsCode(insCode).getBusinessDate().
func (s *FileService) businessDate(ctx context.Context) time.Time {
	var d sql.NullTime
	if err := s.DB.QueryRowContext(ctx,
		`SELECT BDT_BUSINESS_DATE FROM BUSINESS_DATE WHERE BDT_INS_CODE = :1`, s.Cfg.InsCode).Scan(&d); err != nil {
		s.Log.Error("business date", "ins", s.Cfg.InsCode, "err", err)
		return time.Now()
	}
	if !d.Valid {
		return time.Now()
	}
	return d.Time
}

// countryLookup resolves CON_CODE -> CON_ALPHA3_CODE (Java
// CountriesRepository.findByCountryCode).
func (s *FileService) countryLookup(ctx context.Context, code string) (string, error) {
	var alpha3 sql.NullString
	err := s.DB.QueryRowContext(ctx,
		`SELECT CON_ALPHA3_CODE FROM COUNTRIES WHERE CON_CODE = :1`, code).Scan(&alpha3)
	if err != nil {
		return "", err
	}
	return alpha3.String, nil
}
