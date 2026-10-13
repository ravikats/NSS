package tlfsvc

import (
	"testing"
	"time"
)

func withZone(t *testing.T, loc *time.Location) {
	t.Helper()
	old := TxnLocalZone
	TxnLocalZone = func() *time.Location { return loc }
	t.Cleanup(func() { TxnLocalZone = old })
}

// DE7 carries the network transaction time in UTC. It must be stored as the
// switch's local time: POS_TRANSACTIONS.PTR_TXN_DATE_TIME was reading 5.5h
// behind the switch clock on IST because the value was parsed with the zero
// offset and stored verbatim.
func TestUpdateTxnDatetimeConvertsUTCToLocal(t *testing.T) {
	withZone(t, time.FixedZone("IST", 5*3600+1800))

	// 2026-10-13 03:06:18 UTC is 08:36:18 IST.
	got := UpdateTxnDatetime("1013030618", time.Date(2026, 10, 13, 8, 36, 19, 0, time.FixedZone("IST", 5*3600+1800)))
	if got == nil {
		t.Fatal("UpdateTxnDatetime returned nil for a well-formed DE7 value")
	}
	want := time.Date(2026, 10, 13, 8, 36, 18, 0, time.FixedZone("IST", 5*3600+1800))
	if !got.Equal(want) {
		t.Errorf("instant = %v, want %v", got.UTC(), want.UTC())
	}
	// The stored wall clock must be local, not the raw UTC digits.
	if got.Hour() != 8 || got.Minute() != 36 || got.Second() != 18 {
		t.Errorf("stored wall clock = %02d:%02d:%02d, want 08:36:18 local",
			got.Hour(), got.Minute(), got.Second())
	}
	if _, off := got.Zone(); off != 5*3600+1800 {
		t.Errorf("zone offset = %d, want %d (value not converted to local)", off, 5*3600+1800)
	}
}

// A UTC evening transaction rolls into the next local day in IST. That is
// correct: PTR_TXN_DATE_TIME is a local timestamp, so 20:30 UTC is 02:00 the
// following morning.
func TestUpdateTxnDatetimeCrossesLocalDayBoundary(t *testing.T) {
	withZone(t, time.FixedZone("IST", 5*3600+1800))
	now := time.Date(2026, 10, 14, 3, 0, 0, 0, time.FixedZone("IST", 5*3600+1800))

	got := UpdateTxnDatetime("1013203000", now) // 20:30 UTC on the 13th
	if got == nil {
		t.Fatal("nil result")
	}
	if got.Day() != 14 {
		t.Errorf("day = %d, want 14 (20:30 UTC is 02:00 next day in IST)", got.Day())
	}
	if got.Hour() != 2 {
		t.Errorf("hour = %d, want 2", got.Hour())
	}
}

// Year inference must still work: a December transaction arriving in January
// belongs to the previous year.
//
// Note the local wall clock legitimately reads 2027 here -- 23:00 UTC on 31 Dec
// is 04:30 on 1 Jan in IST. The assertion is on the instant, not the local year.
func TestUpdateTxnDatetimeInfersPreviousYear(t *testing.T) {
	withZone(t, time.FixedZone("IST", 5*3600+1800))
	now := time.Date(2027, 1, 5, 12, 0, 0, 0, time.FixedZone("IST", 5*3600+1800))

	got := UpdateTxnDatetime("1231230000", now) // 23:00 UTC on 31 Dec
	if got == nil {
		t.Fatal("nil result")
	}
	wantUTC := time.Date(2026, 12, 31, 23, 0, 0, 0, time.UTC)
	if !got.UTC().Equal(wantUTC) {
		t.Errorf("instant = %v, want %v", got.UTC(), wantUTC)
	}
	// Local representation crosses into the new year, which is correct.
	if got.Year() != 2027 || got.Month() != time.January || got.Day() != 1 || got.Hour() != 4 {
		t.Errorf("local wall clock = %v, want 2027-01-01 04:30 IST", got)
	}
}

// Short/garbage input must still yield nil rather than a bogus instant.
func TestUpdateTxnDatetimeRejectsShortInput(t *testing.T) {
	withZone(t, time.FixedZone("IST", 5*3600+1800))
	now := time.Date(2026, 10, 13, 8, 0, 0, 0, time.FixedZone("IST", 5*3600+1800))
	for _, in := range []string{"", "1", "12", "123"} {
		if got := UpdateTxnDatetime(in, now); got != nil {
			t.Errorf("UpdateTxnDatetime(%q) = %v, want nil", in, got)
		}
	}
}
