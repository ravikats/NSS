package binsvc

import (
	"database/sql"
	"testing"
	"time"
)

// TestJaywanRangeFromRowToleratesNulls is the regression guard for the
// duplicate-accumulation defect.
//
// The loader uses FindJaywanRanges to collect the serials of the rows an
// incoming range must REPLACE. Scanning JBS_PRJ_SER_NUMBER into a plain int
// failed for every row whose job is NULL (rows loaded before job tracking
// existed), so the lookup returned an error, the loader collected no serials,
// and its delete was a no-op. The result was a bare insert: loading a 655-row
// file over an existing 655-row table produced 1310 rows.
//
// The fake-store tests cannot catch this -- the defect is in the SQL scan, not
// the loader -- so the conversion is tested directly.
func TestJaywanRangeFromRowToleratesNulls(t *testing.T) {
	// Exactly the shape of the pre-existing rows: only the three NOT NULL
	// columns are populated, JBS_PRJ_SER_NUMBER included.
	v := jaywanRangeNulls{
		ser:       42,
		updated:   time.Now(),
		updatedBy: 1,
	}
	got := v.toJaywanRange()

	if got.SerialNumber != 42 {
		t.Errorf("SerialNumber = %d, want 42", got.SerialNumber)
	}
	if got.JobNumber != 0 {
		t.Errorf("JobNumber = %d, want 0 for a NULL job", got.JobNumber)
	}
	// The serial is the whole point of the lookup; if it were lost the
	// replacement delete would silently target nothing.
	if got.SerialNumber == 0 {
		t.Error("serial number lost")
	}
}

func TestJaywanRangeFromRowKeepsPopulatedValues(t *testing.T) {
	v := jaywanRangeNulls{
		ser: 7, updated: time.Now(), updatedBy: 1,
		job:      sql.NullInt64{Int64: 6, Valid: true},
		low:      sql.NullInt64{Int64: 5378827700, Valid: true},
		high:     sql.NullInt64{Int64: 5378827700, Valid: true},
		panLen:   sql.NullInt64{Int64: 16, Valid: true},
		cardType: sql.NullInt64{Int64: 1, Valid: true},
		issuer:   sql.NullString{String: "JAY", Valid: true},
		badge:    sql.NullString{Valid: false},
	}
	got := v.toJaywanRange()

	if got.JobNumber != 6 {
		t.Errorf("JobNumber = %d, want 6", got.JobNumber)
	}
	if got.BinRangeLow != 5378827700 || got.BinRangeHigh != 5378827700 {
		t.Errorf("range = %d-%d, want 5378827700-5378827700", got.BinRangeLow, got.BinRangeHigh)
	}
	if got.IssuerBank != "JAY" {
		t.Errorf("IssuerBank = %q, want JAY", got.IssuerBank)
	}
	if got.BadgeInd != "" {
		t.Errorf("BadgeInd = %q, want empty for NULL", got.BadgeInd)
	}
}
