package binsvc

import (
	"database/sql"
	"testing"
	"time"
)

// A freshly inserted pending FILE_UPLOAD_LOG row has NULL in
// UPL_TOT_TXN_COUNT, UPL_TOT_ACCP_TXN_COUNT, UPL_FOR_CODE and UPL_REMARKS.
// Scanning those into int/string failed with "converting NULL to int is
// unsupported", so updateProcess could never write the terminal status: the
// loader finished but the row stayed pending (1) forever and the job never
// closed. uploadLogFromRow is the fix, and this is the exact row shape.
func TestUploadLogFromRowCoalescesPendingRowNulls(t *testing.T) {
	e := uploadLogFromRow(
		7, time.Now(), 1, 1, 1, // ser, lastUpdated, updatedUser, insCode, status=pending
		sql.NullInt64{}, sql.NullInt64{}, sql.NullInt64{}, // intCode, jobNo, forCode all NULL
		sql.NullString{String: "UAESWITCH-POS-BIN-20260805.csv", Valid: true},
		sql.NullString{},                 // remarks NULL
		sql.NullInt64{}, sql.NullInt64{}, // total, accepted NULL
	)

	if e.UploadStatus != 1 {
		t.Errorf("UploadStatus = %d, want 1", e.UploadStatus)
	}
	if e.TotalTxnCount != 0 || e.TotalAcceptedTxnCount != 0 {
		t.Errorf("NULL counts must coalesce to 0, got total=%d accepted=%d",
			e.TotalTxnCount, e.TotalAcceptedTxnCount)
	}
	if e.FormatCode != 0 || e.InterfaceCode != 0 || e.JobNumber != 0 {
		t.Errorf("NULL codes must coalesce to 0, got for=%d int=%d job=%d",
			e.FormatCode, e.InterfaceCode, e.JobNumber)
	}
	if e.Remarks != nil {
		t.Errorf("NULL remarks must stay nil, got %q", *e.Remarks)
	}
	if e.FileName != "UAESWITCH-POS-BIN-20260805.csv" {
		t.Errorf("FileName = %q, want the scanned value", e.FileName)
	}
}

func TestUploadLogFromRowKeepsRealValues(t *testing.T) {
	e := uploadLogFromRow(
		9, time.Now(), 2, 1, 4, // status = complete
		sql.NullInt64{Int64: 12, Valid: true},
		sql.NullInt64{Int64: 4, Valid: true},
		sql.NullInt64{Int64: 121, Valid: true},
		sql.NullString{String: "bins.csv", Valid: true},
		sql.NullString{String: "ok", Valid: true},
		sql.NullInt64{Int64: 654, Valid: true},
		sql.NullInt64{Int64: 654, Valid: true},
	)
	if e.UploadStatus != 4 || e.TotalTxnCount != 654 || e.TotalAcceptedTxnCount != 654 {
		t.Errorf("completed row not carried through: status=%d total=%d accepted=%d",
			e.UploadStatus, e.TotalTxnCount, e.TotalAcceptedTxnCount)
	}
	if e.FormatCode != 121 || e.InterfaceCode != 12 || e.JobNumber != 4 {
		t.Errorf("codes not carried through: for=%d int=%d job=%d",
			e.FormatCode, e.InterfaceCode, e.JobNumber)
	}
	if e.Remarks == nil || *e.Remarks != "ok" {
		t.Errorf("Remarks = %v, want \"ok\"", e.Remarks)
	}
}
