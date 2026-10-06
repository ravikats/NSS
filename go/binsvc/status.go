package binsvc

import (
	"context"
	"database/sql"
	"fmt"
	"log/slog"
)

// networkRangeTable maps a network (the vocabulary bin-service already accepts
// on processBin) to the range table its loader writes. Kept in one place so the
// status view and the loaders cannot disagree about where rows land.
//
// Note the UAESWITCH -> JAYWAN_ISS_ACC_RANGE mapping: the uaeswitch POS BIN
// file is Jaywan's, and the parser/finalize read it from JAYWAN_ISS_ACC_RANGE.
var networkRangeTable = map[string]string{
	"MASTERCARD": "MC_ISS_ACC_RANGE",
	"VISA":       "VISA_ISS_ACC_RANGE",
	"UAESWITCH":  "JAYWAN_ISS_ACC_RANGE",
	"JAYWAN":     "JAYWAN_ISS_ACC_RANGE",
	"OMANNET":    "OMANNET_BIN_DATA",
	"MERCURY":    "MERCURY_ISS_ACC_RANGE",
}

// RangeTables returns the network -> table mapping for callers that want to
// render it.
func RangeTables() map[string]string {
	out := make(map[string]string, len(networkRangeTable))
	for k, v := range networkRangeTable {
		out[k] = v
	}
	return out
}

// RecentJobs returns the newest PROCESSING_JOBS rows, newest first.
func (s *oracleStore) RecentJobs(ctx context.Context, limit int) ([]*ProcessingJob, error) {
	if limit <= 0 || limit > 100 {
		limit = 20
	}
	rows, err := s.db.QueryContext(ctx, `
		SELECT PRJ_SER_NUMBER, PRJ_LAST_UPDATED, PRJ_UPDATED_USER, PRJ_INS_CODE,
		       PRJ_REF_NUMBER, PRJ_PROCESS_NAME, PRJ_START_TIME, PRJ_END_TIME, PRJ_STATUS
		FROM PROCESSING_JOBS
		ORDER BY PRJ_SER_NUMBER DESC
		FETCH FIRST :1 ROWS ONLY`, limit)
	if err != nil {
		return nil, fmt.Errorf("binsvc: recent jobs: %w", err)
	}
	defer rows.Close()

	var out []*ProcessingJob
	for rows.Next() {
		var j ProcessingJob
		var status sql.NullInt64
		var end sql.NullTime
		if err := rows.Scan(&j.SerialNumber, &j.LastUpdated, &j.UpdatedUser, &j.InsCode,
			&j.RefNumber, &j.ProcessName, &j.StartTime, &end, &status); err != nil {
			return nil, fmt.Errorf("binsvc: scan job: %w", err)
		}
		if end.Valid {
			t := end.Time
			j.EndTime = &t
		}
		if status.Valid {
			v := int(status.Int64)
			j.Status = &v
		}
		out = append(out, &j)
	}
	return out, rows.Err()
}

// RangeTableCounts returns a live row count per range table, keyed by network.
// A table that does not exist in this schema is reported as absent rather than
// failing the whole status call: the UAT and local schemas differ, and a
// missing optional table must not blank the UI.
func (s *oracleStore) RangeTableCounts(ctx context.Context) (map[string]int64, error) {
	out := make(map[string]int64, len(networkRangeTable))
	for network, table := range networkRangeTable {
		var n int64
		err := s.db.QueryRowContext(ctx, "SELECT COUNT(*) FROM "+table).Scan(&n)
		switch {
		case err == sql.ErrNoRows:
			out[network] = 0
		case err != nil:
			// ORA-00942 (missing table) and ORA-00904 are tolerated; anything
			// else is a real problem but should not hide the other counts.
			out[network] = -1
			slog.Warn("range table count failed", "network", network, "table", table, "error", err)
		default:
			out[network] = n
		}
	}
	return out, nil
}

// RecentUploadLogs returns the newest FILE_UPLOAD_LOG rows, newest first.
// This is what lets the Settlement → BIN Files tab answer "is this file done?"
// without a query per staged file.
func (s *oracleStore) RecentUploadLogs(ctx context.Context, limit int) ([]*UploadLog, error) {
	if limit <= 0 || limit > 200 {
		limit = 50
	}
	rows, err := s.db.QueryContext(ctx, `
		SELECT UPL_SER_NUMBER, UPL_LAST_UPDATED, UPL_UPDATED_USER, UPL_INS_CODE,
		       UPL_INT_CODE, UPL_FOR_CODE, UPL_FILE_NAME, UPL_UPLOAD_STATUS,
		       UPL_TOT_TXN_COUNT, UPL_TOT_ACCP_TXN_COUNT, UPL_REMARKS, UPL_PRJ_SER_NUMBER
		FROM FILE_UPLOAD_LOG
		ORDER BY UPL_SER_NUMBER DESC
		FETCH FIRST :1 ROWS ONLY`, limit)
	if err != nil {
		return nil, fmt.Errorf("binsvc: recent upload logs: %w", err)
	}
	defer rows.Close()

	var out []*UploadLog
	for rows.Next() {
		var u UploadLog
		var status, total, accepted sql.NullInt64
		var remarks sql.NullString
		if err := rows.Scan(&u.SerialNumber, &u.LastUpdated, &u.UpdatedUser, &u.InstitutionCode,
			&u.InterfaceCode, &u.FormatCode, &u.FileName, &status,
			&total, &accepted, &remarks, &u.JobNumber); err != nil {
			return nil, fmt.Errorf("binsvc: scan upload log: %w", err)
		}
		if status.Valid {
			u.UploadStatus = int(status.Int64)
		}
		if total.Valid {
			u.TotalTxnCount = int(total.Int64)
		}
		if accepted.Valid {
			u.TotalAcceptedTxnCount = int(accepted.Int64)
		}
		if remarks.Valid {
			r := remarks.String
			u.Remarks = &r
		}
		out = append(out, &u)
	}
	return out, rows.Err()
}
