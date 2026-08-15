package binsvc

import (
	"context"
	"database/sql"
	"fmt"
	"log/slog"
	"os"
	"strconv"
	"strings"
	"time"
)

// Config carries the environment configuration of the bin-processing-service.
type Config struct {
	InsCode      int
	InsShortName string
	UpdatedUser  int
	// BinInterfaceCode is BIN_INTERFACE_CODE (UPL_INT_CODE).
	BinInterfaceCode int
	// FormatCodes maps network -> FOR_CODE (MC_BIN_FORMAT_CODE etc.).
	FormatCodes map[string]int
	// ReconIn is RECON_IN_<INS_SHORT_NAME> (input directory for BIN files).
	ReconIn string
	// ReconProcessed / ReconRejected are the RECON_PROCESSED_ / RECON_REJECTED_
	// directories files are moved to after processing.
	ReconProcessed string
	ReconRejected  string
	// ACL startup sync configuration (ACL_INTEGRATION_FLAG / ACL_URL / ACL_USER_APP_ID / BIN_PERMISSION_FILE).
	ACLFlag      bool
	ACLURL       string
	ACLUserAppID string
	ACLPermFile  string
}

// BinResponse mirrors BinResponseVO (only the message field is used).
type BinResponse struct {
	Message string `json:"message"`
}

// Service is the Go port of BinProcessingService (+ CommonService helpers).
type Service struct {
	Store Store
	Log   *slog.Logger
	Cfg   Config
	// Now may be overridden in tests; defaults to time.Now.
	Now func() time.Time
}

func (s *Service) now() time.Time {
	if s.Now != nil {
		return s.Now()
	}
	return time.Now()
}

// New wires the service to Oracle and config.
func New(db *sql.DB, log *slog.Logger, cfg Config) *Service {
	return &Service{Store: &oracleStore{db: db}, Log: log, Cfg: cfg}
}

func (s *Service) log() *slog.Logger {
	if s.Log != nil {
		return s.Log
	}
	return slog.Default()
}

// ProcessBin mirrors BinProcessingService.processBin. It validates the input,
// inserts the PROCESSING_JOBS + FILE_UPLOAD_LOG rows, and schedules the actual
// file processing in a background goroutine.
func (s *Service) ProcessBin(ctx context.Context, fileName, network string) *BinResponse {
	filePath := s.Cfg.ReconIn + fileName
	resp, err := s.validateFile(ctx, filePath, fileName, network)
	if err != nil {
		s.log().Error("validate file", "file", fileName, "err", err)
		return &BinResponse{Message: "Unexpected error occurred, Description :" + err.Error()}
	}
	if resp != nil {
		return resp
	}

	jobSer, err := s.insertProcessingJob(ctx)
	if err != nil {
		s.log().Error("insert processing job", "err", err)
		return &BinResponse{Message: "FAILED_TO_INSERT_JOB; failed to insert processing_job!"}
	}

	resp = &BinResponse{}
	uplSer, err := s.insertFileUploadLog(ctx, fileName, int(jobSer))
	switch {
	case err == errDuplicateFilename:
		resp.Message = "DUPLICATE_FILENAME; provided filename is already exist."
	case err != nil:
		resp.Message = "ERROR_OCCURRED; an error occurred while inserting into file_upload_log!"
	default:
		jobNum, uplNum := int(jobSer), int(uplSer)
		go func() {
			defer func() {
				if r := recover(); r != nil {
					s.log().Error("processBinFile panic", "file", fileName, "panic", r)
				}
			}()
			s.processBinFile(context.Background(), fileName, network, jobNum, uplNum)
		}()
		resp.Message = "BIN File Processing Scheduled Successfully."
	}
	return resp
}

// processBinFile mirrors the BinFileProcessingThread: flip the upload status to
// 9 (in processing) then dispatch to the network-specific processor.
func (s *Service) processBinFile(ctx context.Context, fileName, network string, jobSer, uplSer int) {
	if !s.updateFileUploadLogByJob(ctx, jobSer, 9, nil) {
		s.log().Error("Failed to update file upload log", "file", fileName, "job", jobSer)
		return
	}
	switch strings.ToUpper(network) {
	case "MASTERCARD":
		(&mcProcessor{svc: s}).processMCBin(ctx, fileName, jobSer, uplSer)
	case "VISA":
		(&visaProcessor{svc: s}).processVisaBin(ctx, fileName, jobSer, uplSer)
	case "JAYWAN":
		(&jaywanProcessor{svc: s}).processJaywanBin(ctx, fileName, jobSer, uplSer)
	case "OMANNET":
		(&omanProcessor{svc: s}).processOmanNetBin(ctx, fileName, jobSer, uplSer)
	case "MERCURY":
		(&mercuryProcessor{svc: s}).processMercuryBin(ctx, fileName, jobSer, uplSer)
	default:
		s.log().Warn("unknown network for bin processing", "network", network)
	}
}

// BinFileDeletion mirrors BinProcessingService.binFileDeletion.
func (s *Service) BinFileDeletion(ctx context.Context, fileName, network string) *BinResponse {
	resp := &BinResponse{}
	e, err := s.Store.FindUploadLogByFileName(ctx, fileName)
	if err != nil {
		s.log().Error("find upload log for deletion", "file", fileName, "err", err)
		resp.Message = "Failed to delete file, filename :" + fileName
		return resp
	}
	if e == nil {
		resp.Message = "FILE_NOT_FOUND; there is no filename found " + fileName
		return resp
	}
	if e.UploadStatus != 5 {
		resp.Message = "DELETION_NOT_ALLOWED; file deletion not allowed."
		return resp
	}

	var delErr error
	switch strings.ToUpper(network) {
	case "MASTERCARD":
		delErr = s.Store.DeleteMcRangeByJob(ctx, e.JobNumber)
	case "VISA":
		delErr = s.Store.DeleteVisaRangeByJob(ctx, e.JobNumber)
	case "JAYWAN":
		delErr = s.Store.DeleteJaywanRangeByJob(ctx, e.JobNumber)
	case "OMANNET":
		delErr = s.Store.DeleteOmanNetByJob(ctx, e.JobNumber)
	}
	if delErr == nil {
		delErr = s.Store.DeleteUploadLogByFileName(ctx, fileName)
	}
	if delErr != nil {
		s.log().Error("delete failed", "file", fileName, "err", delErr)
		resp.Message = "Failed to delete file, filename :" + fileName
		return resp
	}
	resp.Message = "File deleted successfully."
	return resp
}

// --- Validation (ValidationService) ---

func (s *Service) validateFile(ctx context.Context, filePath, fileName, network string) (*BinResponse, error) {
	if _, err := os.Stat(filePath); err != nil {
		return &BinResponse{Message: "The file was not found at the specified path. " + filePath}, nil
	}
	inProcess, err := s.Store.CountByUploadStatus(ctx, 9)
	if err != nil {
		return nil, err
	}
	if inProcess > 0 {
		return &BinResponse{Message: "FAILED_TO_PROCESS; one file in processing."}, nil
	}
	if strings.EqualFold(network, "VISA") {
		fi, statErr := os.Stat(filePath)
		if statErr == nil && fi.Size() <= 0 {
			return &BinResponse{Message: "The file contains no data please check the file and process again"}, nil
		}
		if !strings.HasSuffix(fileName, ".txt") {
			return &BinResponse{Message: "Invalid VISA file"}, nil
		}
	}
	return nil, nil
}

// --- CommonService helpers ---

func (s *Service) insertProcessingJob(ctx context.Context) (int64, error) {
	return s.Store.InsertProcessingJob(ctx, s.Cfg.UpdatedUser, s.Cfg.InsCode)
}

// insertFileUploadLog mirrors CommonService.insertFileUploadLog: status 1
// (pending), upload/proc/business date today, file_id = filename.
func (s *Service) insertFileUploadLog(ctx context.Context, fileName string, jobSer int) (int64, error) {
	now := s.now()
	e := &UploadLog{
		UpdatedUser:     s.Cfg.UpdatedUser,
		InstitutionCode: s.Cfg.InsCode,
		InterfaceCode:   s.Cfg.BinInterfaceCode,
		FormatCode:      s.formatCode(fileName),
		FileName:        fileName,
		UploadStatus:    1,
		JobNumber:       jobSer,
		FileID:          fileName,
		LastUpdated:     now,
		UploadDate:      now,
		ProcessingDate:  now,
		BusinessDate:    now,
	}
	return s.Store.InsertUploadLog(ctx, e)
}

func (s *Service) formatCode(network string) int {
	return s.Cfg.FormatCodes[strings.ToUpper(network)]
}

// updateFileUploadLogByJob mirrors CommonService.updateFileUploadLog (lookup by
// PRJ_SER_NUMBER then set status+remarks). Returns false on missing row/error.
func (s *Service) updateFileUploadLogByJob(ctx context.Context, jobSer, status int, remarks *string) bool {
	e, err := s.Store.FindUploadLogByJobNumber(ctx, jobSer)
	if err != nil || e == nil {
		s.log().Error("find upload log by job", "job", jobSer, "err", err)
		return false
	}
	e.UploadStatus = status
	e.Remarks = remarks
	if err := s.Store.UpdateUploadLog(ctx, e); err != nil {
		s.log().Error("update upload log", "job", jobSer, "err", err)
		return false
	}
	return true
}

// updateProcess mirrors CommonService.updateProcess: record totals + status on
// the upload log and stamp the job end time.
func (s *Service) updateProcess(ctx context.Context, uplSer, jobSer, totalCount, accTxnCount, status int) {
	e, err := s.Store.FindUploadLogBySerialNumber(ctx, uplSer)
	if err != nil || e == nil {
		s.log().Error("updateProcess: upload log not found", "upl", uplSer, "err", err)
		return
	}
	e.TotalAcceptedTxnCount = accTxnCount
	e.TotalTxnCount = totalCount
	e.UploadStatus = status
	if err := s.Store.UpdateUploadLog(ctx, e); err != nil {
		s.log().Error("updateProcess: update upload log", "upl", uplSer, "err", err)
	}
	if err := s.Store.UpdateJobEndTime(ctx, jobSer, s.now()); err != nil {
		s.log().Error("updateProcess: update job end time", "job", jobSer, "err", err)
	}
}

// moveFile mirrors CommonService.moveFile (os.Rename with REPLACE_EXISTING).
// On failure it logs "FAILED TO MOVE FILE" (Java swallows the error).
func (s *Service) moveFile(sourcePath, fileName, destDir string) {
	if err := os.Rename(sourcePath, destDir+fileName); err != nil {
		s.log().Info("FAILED TO MOVE FILE", "file", fileName, "err", err)
	} else {
		s.log().Info("FILE MOVED SUCCESSFULLY", "file", fileName)
	}
}

// convertToGregorianDate mirrors CommonService.convertToGregorianDate: a
// 5-digit (YYDDD) julian date is prefixed with "20" and expanded to a date.
func (s *Service) convertToGregorianDate(julian string) (time.Time, error) {
	if len(julian) == 5 {
		julian = "20" + julian
	}
	if len(julian) != 7 {
		return time.Time{}, fmt.Errorf("binsvc: invalid julian date %q", julian)
	}
	year, err := strconv.Atoi(julian[:4])
	if err != nil {
		return time.Time{}, err
	}
	dayOfYear, err := strconv.Atoi(julian[4:])
	if err != nil {
		return time.Time{}, err
	}
	return time.Date(year, time.January, 1, 0, 0, 0, 0, time.UTC).AddDate(0, 0, dayOfYear-1), nil
}

func (s *Service) businessDate(ctx context.Context) *time.Time {
	d, err := s.Store.GetBusinessDate(ctx)
	if err != nil {
		s.log().Error("get business date", "err", err)
		return nil
	}
	return &d
}
