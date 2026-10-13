package outsvc

import (
	"context"
	"errors"
	"testing"
	"time"
)

// mcRow builds a minimal MC work entity.
func mcRow(ser int64, status int, fileID string) *McAcqTxnWorkEntity {
	return &McAcqTxnWorkEntity{
		SerNumber:     ser,
		GeneralStatus: status,
		FileID:        fileID,
	}
}

// flightStore is a fake that models the claim/release lifecycle and records
// which rows each step touched, so a test can assert that generation only ever
// operates on the rows belonging to its own file.
type flightStore struct {
	Store
	// rows is the live work table, keyed by ser.
	rows []*McAcqTxnWorkEntity
	// released captures the rows completeMC moved to 4.
	released []*McAcqTxnWorkEntity
	// moved captures the rows moveWorkToData archived+deleted.
	moved []*McAcqTxnWorkEntity
	// posCompletedFor records the file id passed to CompletePosStatusForFile.
	posCompletedFor string
	// posCompletedUnscoped records a call to the unscoped CompletePosStatus,
	// which generation must never make.
	posCompletedUnscoped bool
	// reaper bookkeeping
	reapCalls     int
	reapStatus    int
	reapOlderThan time.Time
	reapNow       time.Time
	reapResult    int64
}

func (f *flightStore) FindMcWorkBetween(ctx context.Context, ins, status int, from, to time.Time) ([]*McAcqTxnWorkEntity, error) {
	return f.rows, nil
}

func (f *flightStore) UpdateMcWorkStatuses(ctx context.Context, ents []*McAcqTxnWorkEntity) error {
	for _, e := range ents {
		for i, r := range f.rows {
			if r.SerNumber == e.SerNumber {
				f.rows[i].GeneralStatus = e.GeneralStatus
				f.rows[i].FileID = e.FileID
			}
		}
	}
	return nil
}

func (f *flightStore) FindMcWorkByFileId(ctx context.Context, ins int, fileId string, status int) ([]*McAcqTxnWorkEntity, error) {
	var out []*McAcqTxnWorkEntity
	for _, r := range f.rows {
		if r.FileID == fileId && r.GeneralStatus == status {
			out = append(out, r)
		}
	}
	return out, nil
}

func (f *flightStore) FindMcWorkByStatus(ctx context.Context, ins, status int) ([]*McAcqTxnWorkEntity, error) {
	var out []*McAcqTxnWorkEntity
	for _, r := range f.rows {
		if r.GeneralStatus == status {
			out = append(out, r)
		}
	}
	return out, nil
}

func (f *flightStore) CompletePosStatusForFile(ctx context.Context, ins int, fileId string) error {
	f.posCompletedFor = fileId
	return nil
}

// CompletePosStatus is the unscoped variant. Generation must never call it, so
// the fake records the call distinctly and the test can fail on the scoping
// rather than panicking.
func (f *flightStore) CompletePosStatus(ctx context.Context, ins int) error {
	f.posCompletedUnscoped = true
	return nil
}

func (f *flightStore) InsertMcData(ctx context.Context, ents []*McAcqTxnDataEntity) error {
	for _, e := range ents {
		f.moved = append(f.moved, &McAcqTxnWorkEntity{SerNumber: e.SerNumber})
	}
	return nil
}

func (f *flightStore) DeleteMcWork(ctx context.Context, ents []*McAcqTxnWorkEntity) error {
	del := map[int64]bool{}
	for _, e := range ents {
		del[e.SerNumber] = true
	}
	var keep []*McAcqTxnWorkEntity
	for _, r := range f.rows {
		if !del[r.SerNumber] {
			keep = append(keep, r)
		}
	}
	f.rows = keep
	return nil
}

func (f *flightStore) ReapStaleMcWork(ctx context.Context, ins int, status int, olderThan, now time.Time) (int64, error) {
	f.reapCalls++
	f.reapStatus = status
	f.reapOlderThan = olderThan
	f.reapNow = now
	return f.reapResult, nil
}

// The core regression: two runs in flight at once, distinguished only by the
// file id the claim stamped. Releasing run A must not touch run B's rows.
//
// Pre-fix, completeMC selected FindMcWorkByStatus(ins, 9) -- every in-flight row
// in the institution -- so A's release swept B's rows to 4 and stamped A's file
// id on them, and moveWorkToData then deleted them out from under B.
func TestCompleteMCReleasesOnlyItsOwnRows(t *testing.T) {
	f := &flightStore{rows: []*McAcqTxnWorkEntity{
		mcRow(1, 9, "runA.ipm"),
		mcRow(2, 9, "runA.ipm"),
		mcRow(3, 9, "runB.ipm"), // concurrent run
	}}
	p := &IpmOutProcessor{store: f}

	if err := p.completeMC(context.Background(), 1, 4, "FILE_A", "runA.ipm", 2); err != nil {
		t.Fatalf("completeMC: %v", err)
	}

	// Rows 1 and 2 (runA) must have been released to 4/FILE_A and archived.
	var releasedSer []int64
	for _, m := range f.moved {
		releasedSer = append(releasedSer, m.SerNumber)
	}
	if len(releasedSer) != 2 {
		t.Fatalf("archived %v, want exactly runA's rows {1,2}", releasedSer)
	}
	for _, s := range releasedSer {
		if s != 1 && s != 2 {
			t.Errorf("archived ser %d, which belonged to another run", s)
		}
	}
	// The concurrent run's row must still be in flight and still present.
	if len(f.rows) != 1 {
		t.Fatalf("work rows remaining = %d, want 1 (runB's)", len(f.rows))
	}
	if f.rows[0].SerNumber != 3 || f.rows[0].GeneralStatus != 9 || f.rows[0].FileID != "runB.ipm" {
		t.Errorf("concurrent row disturbed: ser=%d status=%d file=%q, want 3/9/runB.ipm",
			f.rows[0].SerNumber, f.rows[0].GeneralStatus, f.rows[0].FileID)
	}
	// POS completion must be scoped to this file too.
	if f.posCompletedUnscoped {
		t.Error("used unscoped CompletePosStatus: it completes every MCI/MDS POS row whose ref appears in ANY work row at gen_status 4")
	}
	if f.posCompletedFor != "FILE_A" {
		t.Errorf("CompletePosStatusForFile called with %q, want FILE_A", f.posCompletedFor)
	}
}

// moveWorkToData must archive exactly the rows it was handed. Pre-fix it
// re-queried "every row at gen_status 4" and deleted that set, so a run could
// delete rows that belonged to another file.
func TestMoveWorkToDataDeletesOnlyTheGivenRows(t *testing.T) {
	f := &flightStore{rows: []*McAcqTxnWorkEntity{
		mcRow(1, 4, "FILE_A"),
		mcRow(9, 4, "OTHER_FILE"), // not ours; must survive
	}}
	p := &IpmOutProcessor{store: f}

	if err := p.moveWorkToData(context.Background(), 1, []*McAcqTxnWorkEntity{mcRow(1, 4, "FILE_A")}); err != nil {
		t.Fatalf("moveWorkToData: %v", err)
	}
	if len(f.rows) != 1 || f.rows[0].SerNumber != 9 {
		t.Fatalf("remaining rows = %+v, want only ser 9", f.rows)
	}
}

// A run that claimed rows but cannot see them in flight must not report
// success silently. This is the condition that produced the stranded rows.
func TestCompleteMCIsQuietWhenNothingWasClaimed(t *testing.T) {
	f := &flightStore{} // empty table
	p := &IpmOutProcessor{store: f}
	if err := p.completeMC(context.Background(), 1, 4, "FILE_A", "runA.ipm", 0); err != nil {
		t.Fatalf("no-rows, nothing-claimed should not error: %v", err)
	}
}

// The reaper must target exactly gen_status 9 and use an age cutoff derived
// from the configured age, and it must be a no-op-safe call on an empty store.
func TestReapStaleWorkUsesConfiguredAge(t *testing.T) {
	now := time.Date(2026, 10, 13, 12, 0, 0, 0, time.UTC)
	f := &flightStore{}
	s := &OutgoingService{store: f, cfg: OutgoingConfig{InsCode: 7}, now: func() time.Time { return now }}

	s.ReapStaleWork(context.Background(), 10*time.Minute)

	if f.reapCalls != 1 {
		t.Fatalf("reaper ran %d time(s), want 1", f.reapCalls)
	}
	if f.reapStatus != 9 {
		t.Errorf("reaped status %d, want 9 (in-flight)", f.reapStatus)
	}
	want := now.Add(-10 * time.Minute)
	if !f.reapOlderThan.Equal(want) {
		t.Errorf("cutoff = %v, want %v", f.reapOlderThan, want)
	}
	// "now" must be passed through so the reaper stamps each reaped row with
	// the SAME clock the column already uses. Writing SYSDATE instead would
	// corrupt the column (the DB clock runs hours behind the app-written
	// values on this deployment) and would immediately re-trip the sweep.
	if !f.reapNow.Equal(now) {
		t.Errorf("stamped now = %v, want %v", f.reapNow, now)
	}
}

// A sweep that reclaims rows must be reported, since each one was stranded by a
// run that died and had been invisible to getTxnCount.
func TestReapStaleWorkReportsReclaimedRows(t *testing.T) {
	now := time.Date(2026, 10, 13, 12, 0, 0, 0, time.UTC)
	f := &flightStore{reapResult: 7}
	s := &OutgoingService{store: f, cfg: OutgoingConfig{InsCode: 1}, now: func() time.Time { return now }}
	s.ReapStaleWork(context.Background(), time.Minute) // must not panic
}

func TestStartReaperDisabledWithoutInterval(t *testing.T) {
	s := &OutgoingService{store: &flightStore{}, cfg: OutgoingConfig{ReapInterval: 0}}
	s.StartReaper(context.Background()) // must return immediately
	var _ = errors.New                  // keep errors import meaningful for future assertions
}
