package outsvc

import (
	"context"
	"database/sql"
	"testing"
	"time"

	_ "github.com/sijms/go-ora/v2"
)

// Live guard for the work-table date ranges. Binding a time.Time straight into
// a DATE comparison with go-ora resolves with the session zone applied:
// `local_date_time >= :from` matched NOTHING while `<= :to` matched everything.
// So the UI (which always sends from+to) was told "There are no transactions
// to stage!", and a from-less window produced a zero-record file. Every range
// now goes through TO_DATE(:n,'YYYY-MM-DD HH24:MI:SS') and must honour BOTH
// bounds exactly.
func TestWorkRangeQueriesHonourBothBounds(t *testing.T) {
	dsn := "oracle://NETWORK_SETTLEMENT_UAT:J6erQo6E24@127.0.0.1:1521/FREEPDB1"
	db, err := sql.Open("oracle", dsn)
	if err != nil {
		t.Fatal(err)
	}
	defer db.Close()
	ctx := context.Background()
	st := &oracleStore{db: db}

	// A lower bound of zero must not swallow everything, and an upper bound in
	// the middle of the day must not include later rows.
	var minLdt, maxLdt sql.NullTime
	if err := db.QueryRowContext(ctx,
		`SELECT MIN(MCT_LOCAL_DATE_TIME), MAX(MCT_LOCAL_DATE_TIME) FROM MC_ACQ_TXN_WORK
		  WHERE MCT_INS_CODE = 1 AND MCT_GEN_STATUS = 3`).Scan(&minLdt, &maxLdt); err != nil {
		t.Fatal(err)
	}
	if !minLdt.Valid {
		t.Skip("no staged MC work rows to range over")
	}
	lo := minLdt.Time.Add(-time.Hour)
	hi := maxLdt.Time.Add(time.Hour)

	n, err := st.CountMcWorkBetween(ctx, 1, 3, lo, hi)
	if err != nil {
		t.Fatal(err)
	}
	if n == 0 {
		t.Fatal("BETWEEN over the rows' own range returned 0 — the lower bound is broken again")
	}
	rows, err := st.FindMcWorkBetween(ctx, 1, 3, lo, hi)
	if err != nil {
		t.Fatal(err)
	}
	if len(rows) != n {
		t.Errorf("FindMcWorkBetween returned %d rows, count says %d — a file would not match its own count", len(rows), n)
	}

	// Upper bound exactly at the oldest row: only that row may be counted.
	exact := minLdt.Time
	n, err = st.CountMcWorkLessThanEqual(ctx, 1, 3, exact)
	if err != nil {
		t.Fatal(err)
	}
	rows, err = st.FindMcWorkLessThanEqual(ctx, 1, 3, exact)
	if err != nil {
		t.Fatal(err)
	}
	if n != len(rows) {
		t.Errorf("<= bound: count %d but fetch %d", n, len(rows))
	}
	if n > 1 && maxLdt.Time.After(exact) {
		t.Errorf("<= %s counted %d rows though later rows exist (%s) — upper bound is too loose",
			exact.Format(time.RFC3339), n, maxLdt.Time.Format(time.RFC3339))
	}
}

// TestOraTimeFormat pins the literal the TO_DATE wrappers parse.
func TestOraTimeFormat(t *testing.T) {
	got := oraTime(time.Date(2026, 10, 3, 2, 5, 9, 123456789, time.UTC))
	if got != "2026-10-03 02:05:09" {
		t.Errorf("oraTime = %q, want 2026-10-03 02:05:09", got)
	}
}
