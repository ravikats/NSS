package binsvc

import (
	"context"
	"database/sql"
	"errors"
	"fmt"
	"strings"
	"time"

	"github.com/sijms/go-ora/v2"
)

// errDuplicateFilename reports a FILE_UPLOAD_LOG unique-constraint violation
// (duplicate UPL_FILE_NAME), mirroring Java DataIntegrityViolationException.
var errDuplicateFilename = errors.New("binsvc: duplicate filename")

// Store is the persistence contract used by the bin service. The production
// implementation talks to Oracle; tests supply a fake.
type Store interface {
	// --- PROCESSING_JOBS ---
	InsertProcessingJob(ctx context.Context, user, insCode int) (int64, error)
	FindJobBySerialNumber(ctx context.Context, ser int) (*ProcessingJob, error)
	UpdateJobEndTime(ctx context.Context, ser int, end time.Time) error
	// DeleteJobBySerialNumber removes the PROCESSING_JOBS row. Without it a
	// removed BIN file left an orphaned job behind, since only the range rows
	// and the upload log were cleaned up.
	DeleteJobBySerialNumber(ctx context.Context, ser int) error

	// --- FILE_UPLOAD_LOG ---
	CountByUploadStatus(ctx context.Context, status int) (int, error)
	FindUploadLogBySerialNumber(ctx context.Context, ser int) (*UploadLog, error)
	FindUploadLogByFileName(ctx context.Context, name string) (*UploadLog, error)
	FindUploadLogByJobNumber(ctx context.Context, job int) (*UploadLog, error)
	InsertUploadLog(ctx context.Context, e *UploadLog) (int64, error)
	UpdateUploadLog(ctx context.Context, e *UploadLog) error
	DeleteUploadLogByFileName(ctx context.Context, name string) error

	// --- BUSINESS_DATE ---
	GetBusinessDate(ctx context.Context) (time.Time, error)

	// --- STATUS / REPORTING (added for the Settlement → BIN Files tab) ---
	// RecentJobs returns the newest PROCESSING_JOBS rows first, so the UI can
	// show what was loaded and when without a second query per job.
	RecentJobs(ctx context.Context, limit int) ([]*ProcessingJob, error)
	// RecentUploadLogs returns the newest FILE_UPLOAD_LOG rows so the status
	// view can show per-file progress. FetchUploadLogByFileName would need one
	// query per staged file; this is one query for the whole list.
	RecentUploadLogs(ctx context.Context, limit int) ([]*UploadLog, error)
	// RangeTableCounts returns the live row count of each network's range
	// table, keyed by network, so an operator can confirm a load landed.
	RangeTableCounts(ctx context.Context) (map[string]int64, error)

	// --- MC_ISS_ACC_RANGE ---
	FindMcRange(ctx context.Context, low, high, priority string) (*McRange, error)
	DeleteMcRange(ctx context.Context, e *McRange) error
	InsertMcRange(ctx context.Context, e *McRange) error
	DeleteMcRangeByJob(ctx context.Context, job int) error

	// --- VISA_ISS_ACC_RANGE ---
	CountVisaRanges(ctx context.Context) (int, error)
	FetchVisaRangeBatch(ctx context.Context, limit int) ([]*VisaRange, error)
	DeleteVisaRanges(ctx context.Context, serials []int64) error
	InsertVisaRange(ctx context.Context, e *VisaRange) error
	DeleteVisaRangeByJob(ctx context.Context, job int) error

	// --- JAYWAN_ISS_ACC_RANGE ---
	FindJaywanRanges(ctx context.Context, low, high int64) ([]*JaywanRange, error)
	DeleteJaywanRanges(ctx context.Context, serials []int64) error
	InsertJaywanRange(ctx context.Context, e *JaywanRange) error
	DeleteJaywanRangeByJob(ctx context.Context, job int) error

	// --- OMANNET_BIN_DATA ---
	FindOmanNetByBins(ctx context.Context, bins []string) ([]*OmanNetRange, error)
	DeleteOmanNet(ctx context.Context, serials []int64) error
	InsertOmanNetRange(ctx context.Context, e *OmanNetRange) error
	DeleteOmanNetByJob(ctx context.Context, job int) error

	// --- MERCURY_ISS_ACC_RANGE ---
	FindMercuryRanges(ctx context.Context, low, high int64) ([]*MercuryRange, error)
	DeleteMercuryRanges(ctx context.Context, serials []int64) error
	InsertMercuryRange(ctx context.Context, e *MercuryRange) error
}

// oracleStore is the production Store backed by Oracle.
type oracleStore struct {
	db *sql.DB
}

// --- PROCESSING_JOBS ---

func (s *oracleStore) InsertProcessingJob(ctx context.Context, user, insCode int) (int64, error) {
	var id int64
	_, err := s.db.ExecContext(ctx, `
		INSERT INTO PROCESSING_JOBS (
			PRJ_LAST_UPDATED, PRJ_UPDATED_USER, PRJ_INS_CODE, PRJ_REF_NUMBER,
			PRJ_PROCESS_NAME, PRJ_START_TIME)
		VALUES (SYSDATE, :1, :2, 1, 'BIN_FILE_PROCESSING', SYSDATE)
		RETURNING PRJ_SER_NUMBER INTO :3`,
		user, insCode, go_ora.Out{Dest: &id})
	if err != nil {
		return 0, fmt.Errorf("binsvc: insert processing job: %w", err)
	}
	return id, nil
}

func (s *oracleStore) FindJobBySerialNumber(ctx context.Context, ser int) (*ProcessingJob, error) {
	e := &ProcessingJob{}
	err := s.db.QueryRowContext(ctx, `
		SELECT PRJ_SER_NUMBER, PRJ_LAST_UPDATED, PRJ_UPDATED_USER, PRJ_INS_CODE,
		       PRJ_REF_NUMBER, PRJ_PROCESS_NAME, PRJ_START_TIME, PRJ_END_TIME, PRJ_STATUS
		FROM PROCESSING_JOBS WHERE PRJ_SER_NUMBER = :1`, ser).
		Scan(&e.SerialNumber, &e.LastUpdated, &e.UpdatedUser, &e.InsCode, &e.RefNumber,
			&e.ProcessName, &e.StartTime, &e.EndTime, &e.Status)
	if errors.Is(err, sql.ErrNoRows) {
		return nil, nil
	}
	if err != nil {
		return nil, fmt.Errorf("binsvc: find job %d: %w", ser, err)
	}
	return e, nil
}

func (s *oracleStore) UpdateJobEndTime(ctx context.Context, ser int, end time.Time) error {
	if _, err := s.db.ExecContext(ctx, `
		UPDATE PROCESSING_JOBS SET PRJ_END_TIME = :1, PRJ_LAST_UPDATED = :1
		WHERE PRJ_SER_NUMBER = :2`, end, ser); err != nil {
		return fmt.Errorf("binsvc: update job end time %d: %w", ser, err)
	}
	return nil
}

// --- FILE_UPLOAD_LOG ---

func (s *oracleStore) CountByUploadStatus(ctx context.Context, status int) (int, error) {
	var n int
	if err := s.db.QueryRowContext(ctx,
		`SELECT COUNT(*) FROM FILE_UPLOAD_LOG WHERE UPL_UPLOAD_STATUS = :1`, status).Scan(&n); err != nil {
		return 0, fmt.Errorf("binsvc: count upload log by status %d: %w", status, err)
	}
	return n, nil
}

// scanUploadLog reads one FILE_UPLOAD_LOG row.
//
// Every column after UPL_UPLOAD_STATUS is NULLABLE, and a freshly inserted
// pending row has UPL_TOT_TXN_COUNT / UPL_TOT_ACCP_TXN_COUNT / UPL_FOR_CODE /
// UPL_REMARKS all NULL. Scanning those straight into int/string made the lookup
// fail with "converting NULL to int is unsupported" -- which is what happened:
// the loader finished but could not write its terminal status, so the row stayed
// pending (1) forever and the job never closed. Nullable columns are therefore
// scanned through sql.Null* and coalesced.
func (s *oracleStore) scanUploadLog(row *sql.Row) (*UploadLog, error) {
	var (
		intCode, jobNo, total, accepted, forCode sql.NullInt64
		fileName, fileID, remarks                sql.NullString
		uploadDate, procDate, bussDate           sql.NullTime
	)
	var serialNumber int64
	var lastUpdated time.Time
	var updatedUser, insCode, status int
	err := row.Scan(&serialNumber, &lastUpdated, &updatedUser, &insCode,
		&intCode, &jobNo, &fileName, &uploadDate, &status,
		&procDate, &bussDate, &fileID, &accepted,
		&total, &forCode, &remarks)
	if errors.Is(err, sql.ErrNoRows) {
		return nil, nil
	}
	if err != nil {
		return nil, err
	}
	return uploadLogFromRow(serialNumber, lastUpdated, updatedUser, insCode, status,
		intCode, jobNo, forCode, fileName, remarks, total, accepted), nil
}

// uploadLogFromRow converts a scanned FILE_UPLOAD_LOG row into an entity,
// coalescing the nullable columns.
//
// It exists as a separate pure function so the NULL handling is testable
// without a database. Every column after UPL_UPLOAD_STATUS is NULLABLE, and a
// freshly inserted pending row has NULL counts, NULL FOR_CODE and NULL REMARKS.
// Scanning those straight into int/string failed with "converting NULL to int
// is unsupported", so the loader could never write its terminal status and the
// row stayed pending (1) forever.
func uploadLogFromRow(
	serialNumber int64, lastUpdated time.Time, updatedUser, insCode, status int,
	intCode, jobNo, forCode sql.NullInt64,
	fileName, remarks sql.NullString,
	total, accepted sql.NullInt64,
) *UploadLog {
	e := &UploadLog{
		SerialNumber:    serialNumber,
		LastUpdated:     lastUpdated,
		UpdatedUser:     updatedUser,
		InstitutionCode: insCode,
		UploadStatus:    status,
		FileName:        fileName.String,
		FileID:          fileName.String,
	}
	if remarks.Valid {
		r := remarks.String
		e.Remarks = &r
	}
	if intCode.Valid {
		e.InterfaceCode = int(intCode.Int64)
	}
	if jobNo.Valid {
		e.JobNumber = int(jobNo.Int64)
	}
	if total.Valid {
		e.TotalTxnCount = int(total.Int64)
	}
	if accepted.Valid {
		e.TotalAcceptedTxnCount = int(accepted.Int64)
	}
	if forCode.Valid {
		e.FormatCode = int(forCode.Int64)
	}
	return e
}

const uploadLogSelect = `
	SELECT UPL_SER_NUMBER, UPL_LAST_UPDATED, UPL_UPDATED_USER, UPL_INS_CODE,
	       UPL_INT_CODE, UPL_PRJ_SER_NUMBER, UPL_FILE_NAME, UPL_UPLOAD_DATE,
	       UPL_UPLOAD_STATUS, UPL_PROC_DATE, UPL_BUSS_DATE, UPL_FILE_ID,
	       UPL_TOT_ACCP_TXN_COUNT, UPL_TOT_TXN_COUNT, UPL_FOR_CODE, UPL_REMARKS
	FROM FILE_UPLOAD_LOG`

func (s *oracleStore) FindUploadLogBySerialNumber(ctx context.Context, ser int) (*UploadLog, error) {
	e, err := s.scanUploadLog(s.db.QueryRowContext(ctx, uploadLogSelect+` WHERE UPL_SER_NUMBER = :1`, ser))
	if err != nil {
		return nil, fmt.Errorf("binsvc: find upload log %d: %w", ser, err)
	}
	return e, nil
}

func (s *oracleStore) FindUploadLogByFileName(ctx context.Context, name string) (*UploadLog, error) {
	e, err := s.scanUploadLog(s.db.QueryRowContext(ctx, uploadLogSelect+` WHERE UPL_FILE_NAME = :1`, name))
	if err != nil {
		return nil, fmt.Errorf("binsvc: find upload log %s: %w", name, err)
	}
	return e, nil
}

func (s *oracleStore) FindUploadLogByJobNumber(ctx context.Context, job int) (*UploadLog, error) {
	e, err := s.scanUploadLog(s.db.QueryRowContext(ctx, uploadLogSelect+` WHERE UPL_PRJ_SER_NUMBER = :1`, job))
	if err != nil {
		return nil, fmt.Errorf("binsvc: find upload log by job %d: %w", job, err)
	}
	return e, nil
}

func (s *oracleStore) InsertUploadLog(ctx context.Context, e *UploadLog) (int64, error) {
	var id int64
	_, err := s.db.ExecContext(ctx, `
		INSERT INTO FILE_UPLOAD_LOG (
			UPL_LAST_UPDATED, UPL_UPDATED_USER, UPL_INS_CODE, UPL_INT_CODE,
			UPL_PRJ_SER_NUMBER, UPL_FILE_NAME, UPL_UPLOAD_DATE, UPL_UPLOAD_STATUS,
			UPL_PROC_DATE, UPL_BUSS_DATE, UPL_FILE_ID, UPL_FOR_CODE)
		VALUES (SYSDATE, :1, :2, :3, :4, :5, SYSDATE, 1, SYSDATE, SYSDATE, :6, :7)
		RETURNING UPL_SER_NUMBER INTO :8`,
		e.UpdatedUser, e.InstitutionCode, e.InterfaceCode, e.JobNumber, e.FileName,
		e.FileID, e.FormatCode, go_ora.Out{Dest: &id})
	if err != nil {
		if isUniqueViolation(err) {
			return 0, errDuplicateFilename
		}
		return 0, fmt.Errorf("binsvc: insert upload log: %w", err)
	}
	return id, nil
}

func (s *oracleStore) UpdateUploadLog(ctx context.Context, e *UploadLog) error {
	var remarks any
	if e.Remarks != nil {
		remarks = *e.Remarks
	}
	if _, err := s.db.ExecContext(ctx, `
		UPDATE FILE_UPLOAD_LOG SET
			UPL_UPLOAD_STATUS = :1, UPL_TOT_ACCP_TXN_COUNT = :2, UPL_TOT_TXN_COUNT = :3,
			UPL_REMARKS = :4, UPL_LAST_UPDATED = SYSDATE
		WHERE UPL_SER_NUMBER = :5`,
		e.UploadStatus, e.TotalAcceptedTxnCount, e.TotalTxnCount, remarks, e.SerialNumber); err != nil {
		return fmt.Errorf("binsvc: update upload log %d: %w", e.SerialNumber, err)
	}
	return nil
}

func (s *oracleStore) DeleteUploadLogByFileName(ctx context.Context, name string) error {
	if _, err := s.db.ExecContext(ctx,
		`DELETE FROM FILE_UPLOAD_LOG WHERE UPL_FILE_NAME = :1`, name); err != nil {
		return fmt.Errorf("binsvc: delete upload log %s: %w", name, err)
	}
	return nil
}

// --- BUSINESS_DATE ---

func (s *oracleStore) GetBusinessDate(ctx context.Context) (time.Time, error) {
	var d time.Time
	if err := s.db.QueryRowContext(ctx, `
		SELECT BDT_BUSINESS_DATE FROM (
			SELECT BDT_BUSINESS_DATE FROM BUSINESS_DATE ORDER BY BDT_INS_CODE ASC
		) WHERE ROWNUM = 1`).Scan(&d); err != nil {
		return time.Time{}, fmt.Errorf("binsvc: get business date: %w", err)
	}
	return d, nil
}

// --- MC_ISS_ACC_RANGE ---

func (s *oracleStore) scanMcRange(row *sql.Row) (*McRange, error) {
	e := &McRange{}
	err := row.Scan(&e.SerialNumber, &e.LastUpdated, &e.UpdatedUser, &e.JobSerialNumber,
		&e.EffectiveDate, &e.ActiveCode, &e.IssRangeLow, &e.GcmsProductID, &e.IssRangeHigh,
		&e.CardProgID, &e.PriorityCode, &e.MemberID, &e.ProdTypeID, &e.EndPoint,
		&e.CountryAlphaCode, &e.CountryCode, &e.Region, &e.ProductClass, &e.TxnRoutInd,
		&e.FpReasignSwitch, &e.ProdReasignSwitch, &e.PwcbSwitch, &e.LicProdID, &e.MapServInd,
		&e.AccLevelInd, &e.ChBillCurr, &e.ChBillCurrExp, &e.ChipServInd, &e.FloorExpDate,
		&e.CoBrandSwitch, &e.SpendControlSwitch, &e.MeCleansingService, &e.MePayPassInd,
		&e.RateTypeInd, &e.PsnRouteInd, &e.CbWithoutPurchase, &e.RepowerReloadInd,
		&e.MoneySendInd, &e.DurbinRateInd, &e.BussDate, &e.GenStatus)
	if errors.Is(err, sql.ErrNoRows) {
		return nil, nil
	}
	if err != nil {
		return nil, err
	}
	return e, nil
}

func (s *oracleStore) FindMcRange(ctx context.Context, low, high, priority string) (*McRange, error) {
	e, err := s.scanMcRange(s.db.QueryRowContext(ctx, `
		SELECT MAR_SER_NUMBER, MAR_LAST_UPDATED, MAR_UPDATED_USER, MAR_PRJ_SER_NUMBER,
		       MAR_EFFECTIVE_DATE, MAR_ACTIVE_CODE, MAR_ISS_RANGE_LOW, MAR_GCMS_PROD_ID,
		       MAR_ISS_RANGE_HIGH, MAR_CARD_PROG_ID, MAR_PRIORITY_CODE, MAR_MEMBER_ID,
		       MAR_PROD_TYPE_ID, MAR_END_POINT, MAR_COUNTRY_ALPHA_CODE, MAR_COUNTRY_CODE,
		       MAR_REGION, MAR_PRODUCT_CLASS, MAR_TXN_ROUT_IND, MAR_FP_REASSIGN_SWITCH,
		       MAR_PROD_REASSIGN_SWITCH, MAR_PWCB_SWITCH, MAR_LIC_PROD_ID, MAR_MAP_SERV_IND,
		       MAR_ACC_LEVEL_IND, MAR_CH_BILL_CURR, MAR_CH_BILL_CURR_EXP, MAR_CHIP_SERV_IND,
		       MAR_FLOOR_EXP_DATE, MAR_CO_BRAND_SWITCH, MAR_SPEND_CONTROL_SWITCH,
		       MAR_ME_CLEANSING_SERVICE, MAR_ME_PAYPASS_IND, MAR_RATE_TYPE_IND, MAR_PSN_ROUTE_IND,
		       MAR_CB_WITHOUT_PURCHASE, MAR_REPOWER_RELOAD_IND, MAR_MONEYSEND_IND,
		       MAR_DURBIN_RATE_IND, MAR_BUSS_DATE, MAR_GEN_STATUS
		FROM MC_ISS_ACC_RANGE
		WHERE MAR_ISS_RANGE_LOW = :1 AND MAR_ISS_RANGE_HIGH = :2 AND MAR_PRIORITY_CODE = :3`,
		low, high, priority))
	if err != nil {
		return nil, fmt.Errorf("binsvc: find mc range %s/%s/%s: %w", low, high, priority, err)
	}
	return e, nil
}

func (s *oracleStore) DeleteMcRange(ctx context.Context, e *McRange) error {
	if _, err := s.db.ExecContext(ctx,
		`DELETE FROM MC_ISS_ACC_RANGE WHERE MAR_SER_NUMBER = :1`, e.SerialNumber); err != nil {
		return fmt.Errorf("binsvc: delete mc range %d: %w", e.SerialNumber, err)
	}
	return nil
}

func (s *oracleStore) InsertMcRange(ctx context.Context, e *McRange) error {
	var bussDate any
	if e.BussDate != nil {
		bussDate = *e.BussDate
	}
	_, err := s.db.ExecContext(ctx, `
		INSERT INTO MC_ISS_ACC_RANGE (
			MAR_LAST_UPDATED, MAR_UPDATED_USER, MAR_PRJ_SER_NUMBER, MAR_EFFECTIVE_DATE,
			MAR_ACTIVE_CODE, MAR_ISS_RANGE_LOW, MAR_GCMS_PROD_ID, MAR_ISS_RANGE_HIGH,
			MAR_CARD_PROG_ID, MAR_PRIORITY_CODE, MAR_MEMBER_ID, MAR_PROD_TYPE_ID, MAR_END_POINT,
			MAR_COUNTRY_ALPHA_CODE, MAR_COUNTRY_CODE, MAR_REGION, MAR_PRODUCT_CLASS,
			MAR_TXN_ROUT_IND, MAR_FP_REASSIGN_SWITCH, MAR_PROD_REASSIGN_SWITCH, MAR_PWCB_SWITCH,
			MAR_LIC_PROD_ID, MAR_MAP_SERV_IND, MAR_ACC_LEVEL_IND, MAR_CH_BILL_CURR,
			MAR_CH_BILL_CURR_EXP, MAR_CHIP_SERV_IND, MAR_FLOOR_EXP_DATE, MAR_CO_BRAND_SWITCH,
			MAR_SPEND_CONTROL_SWITCH, MAR_ME_CLEANSING_SERVICE, MAR_ME_PAYPASS_IND,
			MAR_RATE_TYPE_IND, MAR_PSN_ROUTE_IND, MAR_CB_WITHOUT_PURCHASE, MAR_REPOWER_RELOAD_IND,
			MAR_MONEYSEND_IND, MAR_DURBIN_RATE_IND, MAR_BUSS_DATE, MAR_GEN_STATUS)
		VALUES (SYSDATE, :1, :2, :3, :4, :5, :6, :7, :8, :9, :10, :11, :12, :13, :14, :15,
		        :16, :17, :18, :19, :20, :21, :22, :23, :24, :25, :26, :27, :28, :29, :30,
		        :31, :32, :33, :34, :35, :36, :37, :38, :39, :40)`,
		e.UpdatedUser, e.JobSerialNumber, e.EffectiveDate, e.ActiveCode, e.IssRangeLow,
		e.GcmsProductID, e.IssRangeHigh, e.CardProgID, e.PriorityCode, e.MemberID, e.ProdTypeID,
		e.EndPoint, e.CountryAlphaCode, e.CountryCode, e.Region, e.ProductClass, e.TxnRoutInd,
		e.FpReasignSwitch, e.ProdReasignSwitch, e.PwcbSwitch, e.LicProdID, e.MapServInd,
		e.AccLevelInd, e.ChBillCurr, e.ChBillCurrExp, e.ChipServInd, e.FloorExpDate,
		e.CoBrandSwitch, e.SpendControlSwitch, e.MeCleansingService, e.MePayPassInd,
		e.RateTypeInd, e.PsnRouteInd, e.CbWithoutPurchase, e.RepowerReloadInd, e.MoneySendInd,
		e.DurbinRateInd, bussDate, e.GenStatus)
	if err != nil {
		return fmt.Errorf("binsvc: insert mc range: %w", err)
	}
	return nil
}

func (s *oracleStore) DeleteMcRangeByJob(ctx context.Context, job int) error {
	if _, err := s.db.ExecContext(ctx,
		`DELETE FROM MC_ISS_ACC_RANGE WHERE MAR_PRJ_SER_NUMBER = :1`, job); err != nil {
		return fmt.Errorf("binsvc: delete mc ranges by job %d: %w", job, err)
	}
	return nil
}

// --- VISA_ISS_ACC_RANGE ---

func (s *oracleStore) CountVisaRanges(ctx context.Context) (int, error) {
	var n int
	if err := s.db.QueryRowContext(ctx,
		`SELECT COUNT(*) FROM VISA_ISS_ACC_RANGE`).Scan(&n); err != nil {
		return 0, fmt.Errorf("binsvc: count visa ranges: %w", err)
	}
	return n, nil
}

func (s *oracleStore) FetchVisaRangeBatch(ctx context.Context, limit int) ([]*VisaRange, error) {
	rows, err := s.db.QueryContext(ctx, `
		SELECT VAR_SER_NUMBER, VAR_LAST_UPDATED, VAR_UPDATED_USER, VAR_PRJ_SER_NUMBER,
		       VAR_ISS_RANGE_HIGH, VAR_ISS_RANGE_LOW, VAR_BIN, VAR_BIN_LENGTH, VAR_PROC_BIN,
		       VAR_DOMAIN, VAR_REGION, VAR_COUNTRY_ALPHA_CODE, VAR_CARD_PRODUCT,
		       VAR_DR_CR_CARD_IND, VAR_PROD_SUB_TYPE
		FROM VISA_ISS_ACC_RANGE WHERE ROWNUM <= :1`, limit)
	if err != nil {
		return nil, fmt.Errorf("binsvc: fetch visa batch: %w", err)
	}
	defer rows.Close()
	var out []*VisaRange
	for rows.Next() {
		e := &VisaRange{}
		if err := rows.Scan(&e.SerialNumber, &e.LastUpdated, &e.UpdatedUser, &e.JobSerialNumber,
			&e.IssRangeHigh, &e.IssRangeLow, &e.Bin, &e.BinLength, &e.ProcessingBin,
			&e.Domain, &e.Region, &e.CountryAlphaCode, &e.CardProduct, &e.CrdrIndicator,
			&e.ProductSubType); err != nil {
			return nil, fmt.Errorf("binsvc: scan visa range: %w", err)
		}
		out = append(out, e)
	}
	return out, rows.Err()
}

func (s *oracleStore) DeleteVisaRanges(ctx context.Context, serials []int64) error {
	if len(serials) == 0 {
		return nil
	}
	ids := make([]any, 0, len(serials))
	ph := make([]string, 0, len(serials))
	for _, id := range serials {
		ph = append(ph, ":1")
		ids = append(ids, id)
	}
	q := "DELETE FROM VISA_ISS_ACC_RANGE WHERE VAR_SER_NUMBER IN (" + strings.Join(ph, ",") + ")"
	if _, err := s.db.ExecContext(ctx, q, ids...); err != nil {
		return fmt.Errorf("binsvc: delete visa ranges: %w", err)
	}
	return nil
}

func (s *oracleStore) InsertVisaRange(ctx context.Context, e *VisaRange) error {
	_, err := s.db.ExecContext(ctx, `
		INSERT INTO VISA_ISS_ACC_RANGE (
			VAR_LAST_UPDATED, VAR_UPDATED_USER, VAR_PRJ_SER_NUMBER, VAR_ISS_RANGE_HIGH,
			VAR_ISS_RANGE_LOW, VAR_BIN, VAR_BIN_LENGTH, VAR_PROC_BIN, VAR_DOMAIN, VAR_REGION,
			VAR_COUNTRY_ALPHA_CODE, VAR_CARD_PRODUCT, VAR_DR_CR_CARD_IND, VAR_PROD_SUB_TYPE)
		VALUES (:1, :2, :3, :4, :5, :6, :7, :8, :9, :10, :11, :12, :13, :14)`,
		e.LastUpdated, e.UpdatedUser, e.JobSerialNumber, e.IssRangeHigh, e.IssRangeLow,
		e.Bin, e.BinLength, e.ProcessingBin, e.Domain, e.Region, e.CountryAlphaCode,
		e.CardProduct, e.CrdrIndicator, e.ProductSubType)
	if err != nil {
		return fmt.Errorf("binsvc: insert visa range: %w", err)
	}
	return nil
}

func (s *oracleStore) DeleteVisaRangeByJob(ctx context.Context, job int) error {
	if _, err := s.db.ExecContext(ctx,
		`DELETE FROM VISA_ISS_ACC_RANGE WHERE VAR_PRJ_SER_NUMBER = :1`, job); err != nil {
		return fmt.Errorf("binsvc: delete visa ranges by job %d: %w", job, err)
	}
	return nil
}

// --- JAYWAN_ISS_ACC_RANGE ---

func (s *oracleStore) FindJaywanRanges(ctx context.Context, low, high int64) ([]*JaywanRange, error) {
	rows, err := s.db.QueryContext(ctx, `
		SELECT JBS_SER_NUMBER, JBS_LAST_UPDATED, JBS_UPDATED_USER, JBS_PRJ_SER_NUMBER,
		       JBS_ISSUER_BANK, JBS_INS_ID, JBS_BIN_LOW_VALUE, JBS_BIN_HIGH_VALUE, JBS_PAN_LENGTH,
		       JBS_BIN_LENGTH, JBS_PRODUCT_TYPE, JBS_SCHEME_CODE, JBS_SCHEME_PRODUCT, JBS_CARD_TYPE,
		       JBS_SERVICE, JBS_CUR_CODE, JBS_ISO_NUM_CUR_CODE, JBS_ACTION_TAKEN, JBS_ISS_ACC_CAP,
		       JBS_PROD_CLSSFY, JBS_CO_BADGE_IND
		FROM JAYWAN_ISS_ACC_RANGE
		WHERE JBS_BIN_LOW_VALUE = :1 AND JBS_BIN_HIGH_VALUE = :2`, low, high)
	if err != nil {
		return nil, fmt.Errorf("binsvc: find jaywan ranges: %w", err)
	}
	defer rows.Close()
	var out []*JaywanRange
	for rows.Next() {
		e, err := jaywanRangeFromRow(rows)
		if err != nil {
			return nil, fmt.Errorf("binsvc: scan jaywan range: %w", err)
		}
		out = append(out, e)
	}
	return out, rows.Err()
}

func (s *oracleStore) DeleteJaywanRanges(ctx context.Context, serials []int64) error {
	if len(serials) == 0 {
		return nil
	}
	ids := make([]any, 0, len(serials))
	ph := make([]string, 0, len(serials))
	for _, id := range serials {
		ph = append(ph, ":1")
		ids = append(ids, id)
	}
	q := "DELETE FROM JAYWAN_ISS_ACC_RANGE WHERE JBS_SER_NUMBER IN (" + strings.Join(ph, ",") + ")"
	if _, err := s.db.ExecContext(ctx, q, ids...); err != nil {
		return fmt.Errorf("binsvc: delete jaywan ranges: %w", err)
	}
	return nil
}

func (s *oracleStore) InsertJaywanRange(ctx context.Context, e *JaywanRange) error {
	_, err := s.db.ExecContext(ctx, `
		INSERT INTO JAYWAN_ISS_ACC_RANGE (
			JBS_LAST_UPDATED, JBS_UPDATED_USER, JBS_PRJ_SER_NUMBER, JBS_ISSUER_BANK, JBS_INS_ID,
			JBS_BIN_LOW_VALUE, JBS_BIN_HIGH_VALUE, JBS_PAN_LENGTH, JBS_BIN_LENGTH, JBS_PRODUCT_TYPE,
			JBS_SCHEME_CODE, JBS_SCHEME_PRODUCT, JBS_CARD_TYPE, JBS_SERVICE, JBS_CUR_CODE,
			JBS_ISO_NUM_CUR_CODE, JBS_ACTION_TAKEN, JBS_ISS_ACC_CAP, JBS_PROD_CLSSFY, JBS_CO_BADGE_IND)
		VALUES (:1, :2, :3, :4, :5, :6, :7, :8, :9, :10, :11, :12, :13, :14, :15, :16, :17, :18, :19, :20)`,
		e.LastUpdated, e.UpdatedUser, e.JobNumber, e.IssuerBank, e.InstitutionID, e.BinRangeLow,
		e.BinRangeHigh, e.PanLength, e.BinLength, e.ProductType, e.SchemeCode, e.SchemeProduct,
		e.CardType, e.Service, e.CurrencyCode, e.IsoNumCurrCode, e.ActionTaken, e.IssAccCap,
		e.ProdClssfy, e.BadgeInd)
	if err != nil {
		return fmt.Errorf("binsvc: insert jaywan range: %w", err)
	}
	return nil
}

// jaywanRangeNulls holds one JAYWAN_ISS_ACC_RANGE row as scanned. Every column
// except SER_NUMBER, LAST_UPDATED and UPDATED_USER is nullable, and rows loaded
// before job tracking existed carry JBS_PRJ_SER_NUMBER = NULL.
type jaywanRangeNulls struct {
	ser                               int64
	updated                           time.Time
	updatedBy                         int
	job                               sql.NullInt64
	issuer                            sql.NullString
	insID, low, high, panLen, binLen  sql.NullInt64
	prodType, schemeCd, schemePrd     sql.NullString
	cardType, service, curCode        sql.NullInt64
	isoCur                            sql.NullInt64
	action, issAccCap, prodCls, badge sql.NullString
}

func (v *jaywanRangeNulls) scan(rows *sql.Rows) error {
	return rows.Scan(&v.ser, &v.updated, &v.updatedBy, &v.job,
		&v.issuer, &v.insID, &v.low, &v.high, &v.panLen,
		&v.binLen, &v.prodType, &v.schemeCd, &v.schemePrd, &v.cardType,
		&v.service, &v.curCode, &v.isoCur, &v.action, &v.issAccCap,
		&v.prodCls, &v.badge)
}

// toJaywanRange collapses the nullable columns. This is the fix for the
// duplicate-accumulation defect: scanning a NULL into a plain int/string made
// database/sql fail the whole scan, and the caller uses this lookup to collect
// the serials of rows it must replace -- on error it collected none. So every
// load inserted on top of the existing ranges instead of replacing them and the
// table grew by one full copy of the file per run (655 -> 1310 rows).
func (v *jaywanRangeNulls) toJaywanRange() *JaywanRange {
	return &JaywanRange{
		SerialNumber:   v.ser,
		LastUpdated:    v.updated,
		UpdatedUser:    v.updatedBy,
		JobNumber:      int(v.job.Int64),
		IssuerBank:     v.issuer.String,
		InstitutionID:  int(v.insID.Int64),
		BinRangeLow:    v.low.Int64,
		BinRangeHigh:   v.high.Int64,
		PanLength:      int(v.panLen.Int64),
		BinLength:      int(v.binLen.Int64),
		ProductType:    v.prodType.String,
		SchemeCode:     v.schemeCd.String,
		SchemeProduct:  v.schemePrd.String,
		CardType:       int(v.cardType.Int64),
		Service:        int(v.service.Int64),
		CurrencyCode:   int(v.curCode.Int64),
		IsoNumCurrCode: int(v.isoCur.Int64),
		ActionTaken:    v.action.String,
		IssAccCap:      v.issAccCap.String,
		ProdClssfy:     v.prodCls.String,
		BadgeInd:       v.badge.String,
	}
}

func jaywanRangeFromRow(rows *sql.Rows) (*JaywanRange, error) {
	var v jaywanRangeNulls
	if err := v.scan(rows); err != nil {
		return nil, err
	}
	return v.toJaywanRange(), nil
}

func (s *oracleStore) DeleteJaywanRangeByJob(ctx context.Context, job int) error {
	if _, err := s.db.ExecContext(ctx,
		`DELETE FROM JAYWAN_ISS_ACC_RANGE WHERE JBS_PRJ_SER_NUMBER = :1`, job); err != nil {
		return fmt.Errorf("binsvc: delete jaywan ranges by job %d: %w", job, err)
	}
	return nil
}

// --- OMANNET_BIN_DATA ---

func (s *oracleStore) FindOmanNetByBins(ctx context.Context, bins []string) ([]*OmanNetRange, error) {
	if len(bins) == 0 {
		return nil, nil
	}
	args := make([]any, 0, len(bins))
	ph := make([]string, 0, len(bins))
	for _, b := range bins {
		ph = append(ph, ":1")
		args = append(args, b)
	}
	q := "SELECT OBN_SER_NUMBER, OBN_LAST_UPDATED, OBN_UPDATED_USER, OBN_PRJ_SER_NUMBER, OBN_GEN_STATUS, OBN_ROUTE, OBN_SUBROUTE, OBN_BIN_NUMBER, OBN_CARD_TYPE, OBN_REMARK FROM OMANNET_BIN_DATA WHERE OBN_BIN_NUMBER IN (" + strings.Join(ph, ",") + ")"
	rows, err := s.db.QueryContext(ctx, q, args...)
	if err != nil {
		return nil, fmt.Errorf("binsvc: find omannet by bins: %w", err)
	}
	defer rows.Close()
	var out []*OmanNetRange
	for rows.Next() {
		e := &OmanNetRange{}
		if err := rows.Scan(&e.SerialNumber, &e.LastUpdated, &e.User, &e.JobNumber,
			&e.GenStatus, &e.Route, &e.SubRoute, &e.BinNumber, &e.CardType, &e.Remarks); err != nil {
			return nil, fmt.Errorf("binsvc: scan omannet range: %w", err)
		}
		out = append(out, e)
	}
	return out, rows.Err()
}

func (s *oracleStore) DeleteOmanNet(ctx context.Context, serials []int64) error {
	if len(serials) == 0 {
		return nil
	}
	ids := make([]any, 0, len(serials))
	ph := make([]string, 0, len(serials))
	for _, id := range serials {
		ph = append(ph, ":1")
		ids = append(ids, id)
	}
	q := "DELETE FROM OMANNET_BIN_DATA WHERE OBN_SER_NUMBER IN (" + strings.Join(ph, ",") + ")"
	if _, err := s.db.ExecContext(ctx, q, ids...); err != nil {
		return fmt.Errorf("binsvc: delete omannet ranges: %w", err)
	}
	return nil
}

func (s *oracleStore) InsertOmanNetRange(ctx context.Context, e *OmanNetRange) error {
	var remark any
	if e.Remarks != nil {
		remark = *e.Remarks
	}
	_, err := s.db.ExecContext(ctx, `
		INSERT INTO OMANNET_BIN_DATA (
			OBN_LAST_UPDATED, OBN_UPDATED_USER, OBN_PRJ_SER_NUMBER, OBN_GEN_STATUS,
			OBN_ROUTE, OBN_SUBROUTE, OBN_BIN_NUMBER, OBN_CARD_TYPE, OBN_REMARK)
		VALUES (:1, :2, :3, :4, :5, :6, :7, :8, :9)`,
		e.LastUpdated, e.User, e.JobNumber, e.GenStatus, e.Route, e.SubRoute, e.BinNumber,
		e.CardType, remark)
	if err != nil {
		return fmt.Errorf("binsvc: insert omannet range: %w", err)
	}
	return nil
}

func (s *oracleStore) DeleteOmanNetByJob(ctx context.Context, job int) error {
	if _, err := s.db.ExecContext(ctx,
		`DELETE FROM OMANNET_BIN_DATA WHERE OBN_PRJ_SER_NUMBER = :1`, job); err != nil {
		return fmt.Errorf("binsvc: delete omannet ranges by job %d: %w", job, err)
	}
	return nil
}

// --- MERCURY_ISS_ACC_RANGE ---

func (s *oracleStore) FindMercuryRanges(ctx context.Context, low, high int64) ([]*MercuryRange, error) {
	rows, err := s.db.QueryContext(ctx, `
		SELECT MIA_SER_NUMBER, MIA_LAST_UPDATED, MIA_UPDATED_USER, MIA_PRJ_SER_NUMBER,
		       MIA_BIN_LOW_VALUE, MIA_BIN_HIGH_VALUE, MIA_CARD_TYPE, MIA_CARD_PRODUCT_ID,
		       MIA_CARD_VARIANT, MIA_CARD_SCHEME, MIA_CUR_CODE, MIA_CON_CODE, MIA_STATUS
		FROM MERCURY_ISS_ACC_RANGE
		WHERE MIA_BIN_LOW_VALUE = :1 AND MIA_BIN_HIGH_VALUE = :2`, low, high)
	if err != nil {
		return nil, fmt.Errorf("binsvc: find mercury ranges: %w", err)
	}
	defer rows.Close()
	var out []*MercuryRange
	for rows.Next() {
		e := &MercuryRange{}
		if err := rows.Scan(&e.SerialNumber, &e.LastUpdated, &e.UpdatedUser, &e.JobNumber,
			&e.BinRangeLow, &e.BinRangeHigh, &e.CardType, &e.CardProductID, &e.CardVariant,
			&e.CardScheme, &e.CurrencyCode, &e.CountryCode, &e.Status); err != nil {
			return nil, fmt.Errorf("binsvc: scan mercury range: %w", err)
		}
		out = append(out, e)
	}
	return out, rows.Err()
}

func (s *oracleStore) DeleteMercuryRanges(ctx context.Context, serials []int64) error {
	if len(serials) == 0 {
		return nil
	}
	ids := make([]any, 0, len(serials))
	ph := make([]string, 0, len(serials))
	for _, id := range serials {
		ph = append(ph, ":1")
		ids = append(ids, id)
	}
	q := "DELETE FROM MERCURY_ISS_ACC_RANGE WHERE MIA_SER_NUMBER IN (" + strings.Join(ph, ",") + ")"
	if _, err := s.db.ExecContext(ctx, q, ids...); err != nil {
		return fmt.Errorf("binsvc: delete mercury ranges: %w", err)
	}
	return nil
}

func (s *oracleStore) InsertMercuryRange(ctx context.Context, e *MercuryRange) error {
	_, err := s.db.ExecContext(ctx, `
		INSERT INTO MERCURY_ISS_ACC_RANGE (
			MIA_LAST_UPDATED, MIA_UPDATED_USER, MIA_PRJ_SER_NUMBER, MIA_BIN_LOW_VALUE,
			MIA_BIN_HIGH_VALUE, MIA_CARD_TYPE, MIA_CARD_PRODUCT_ID, MIA_CARD_VARIANT,
			MIA_CARD_SCHEME, MIA_CUR_CODE, MIA_CON_CODE, MIA_STATUS)
		VALUES (:1, :2, :3, :4, :5, :6, :7, :8, :9, :10, :11, :12)`,
		e.LastUpdated, e.UpdatedUser, e.JobNumber, e.BinRangeLow, e.BinRangeHigh, e.CardType,
		e.CardProductID, e.CardVariant, e.CardScheme, e.CurrencyCode, e.CountryCode, e.Status)
	if err != nil {
		return fmt.Errorf("binsvc: insert mercury range: %w", err)
	}
	return nil
}

// isUniqueViolation reports whether err is an ORA-00001 unique constraint
// violation (the DB-level cause of the duplicate filename rejection).
func isUniqueViolation(err error) bool {
	return err != nil && strings.Contains(strings.ToUpper(err.Error()), "ORA-00001")
}

func (s *oracleStore) DeleteJobBySerialNumber(ctx context.Context, ser int) error {
	if _, err := s.db.ExecContext(ctx,
		`DELETE FROM PROCESSING_JOBS WHERE PRJ_SER_NUMBER = :1`, ser); err != nil {
		return fmt.Errorf("binsvc: delete processing job %d: %w", ser, err)
	}
	return nil
}
