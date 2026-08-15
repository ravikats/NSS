package mpgsdcf

import (
	"context"
	"bufio"
	"os"
	"strings"
	"testing"
	"time"
)

func TestParseRealSample(t *testing.T) {
	f, err := os.Open("/tmp/opencode/sample.001")
	if err != nil {
		t.Skip("sample not present:", err)
	}
	defer f.Close()

	now := time.Date(2025, 1, 8, 10, 0, 0, 0, time.Local)
	proc := &Processor{
		UserSerNumber: 2, InsCode: 1, IntCode: 13, JobNumber: 42,
		CountryCode: func(context.Context, string) (string, error) { return "OMN", nil },
		Now:         func() time.Time { return now },
	}

	var txns []*Entity
	sc := bufio.NewScanner(f)
	lineNo := 0
	for sc.Scan() {
		lineNo++
		if lineNo == 1 {
			continue // header
		}
		line := strings.TrimRight(sc.Text(), "\r")
		rec, err := ParseRecord(line)
		if err != nil {
			t.Fatalf("line %d parse: %v", lineNo, err)
		}
		ent, err := proc.Process(rec)
		if err != nil {
			t.Fatalf("line %d process: %v", lineNo, err)
		}
		if ent != nil {
			txns = append(txns, ent)
		}
	}
	if err := sc.Err(); err != nil {
		t.Fatal(err)
	}

	if len(txns) != 18 {
		t.Fatalf("expected 18 transactions, got %d", len(txns))
	}

	// Spot-check the first transaction's fields against the sample content.
	t0 := txns[0]
	if t0.Network != "MCI" {
		t.Errorf("network: got %q want MCI", t0.Network)
	}
	if t0.InsCode != 1 || t0.IntCode != 13 || t0.JobNumber != 42 || t0.GenStatus != 3 {
		t.Errorf("job fields wrong: %+v", t0)
	}
	if t0.OutStatus != "Pending" || t0.IncomingStatus != "Pending" {
		t.Errorf("statuses wrong: %+v", t0)
	}
	if t0.OnusOffusFlag != "F" {
		t.Errorf("onusOffus: got %q want F", t0.OnusOffusFlag)
	}
	if t0.MsgTypeId == "" {
		t.Error("msgTypeId empty")
	}
	if t0.TxnAmount <= 0 {
		t.Errorf("txnAmount: got %v want >0", t0.TxnAmount)
	}
	if t0.TxnCurCode == "" {
		t.Error("txnCurCode empty")
	}
	if t0.LocalDateTime == nil {
		t.Error("localDateTime nil")
	}
	if t0.CardNumber == "" {
		t.Error("cardNumber empty")
	}
	if t0.MeCountry != "OMN" {
		t.Errorf("meCountry: got %q want OMN", t0.MeCountry)
	}
	if t0.TxnId == "" {
		t.Error("txnId empty")
	}
	if t0.Mcc == "" {
		t.Error("mcc empty")
	}
	if t0.MeCategoryType == "" {
		t.Error("meCategoryType empty")
	}
	if t0.DmsSmsMode != "D" {
		t.Errorf("dmsSmsMode: got %q want D", t0.DmsSmsMode)
	}
	if t0.SetlDate == nil {
		t.Error("setlDate nil")
	}

	// Every txn must be non-empty on the identity fields the mapper needs.
	for i, e := range txns {
		if e.SerialNumber != 0 {
			t.Errorf("txn %d: serial should be 0 before insert", i)
		}
		if e.TxnAmount == 0 && e.SetlAmount == 0 {
			t.Errorf("txn %d: zero amounts", i)
		}
		if e.CardNumber == "" {
			t.Errorf("txn %d: empty card", i)
		}
		if e.Mcc == "" {
			t.Errorf("txn %d: empty mcc", i)
		}
	}
}

func TestPanRange(t *testing.T) {
	cases := []struct {
		r    PanRange
		pan  string
		want bool
	}{
		{PanRange{"6690090000000000", "6690090099999999"}, "6690090000002009", true},
		{PanRange{"6690090000000000", "6690090099999999"}, "6690090099999999", true}, // upper edge
		{PanRange{"6690090000000000", "6690090099999999"}, "6690089999999999", false}, // below
		{PanRange{"6690090000000000", "6690090099999999"}, "6690100000000000", false}, // above
		{PanRange{"6690107100000000", "6690107199999999"}, "6690109900000010", false}, // not in range
		{PanRange{"9784500300000000", "9784500399999999"}, "9784500300000001", true},
		{PanRange{"6690090000000000", "6690090099999999"}, "6690099", false},      // too short
		{PanRange{"6690090000000000", "6690090099999999"}, "not-a-pan", false},    // not numeric
	}
	for _, c := range cases {
		if got := c.r.Contains(c.pan); got != c.want {
			t.Errorf("PanRange{%s,%s}.Contains(%q)=%v want %v", c.r.Start, c.r.End, c.pan, got, c.want)
		}
	}
}

func TestJaywanRangesContains(t *testing.T) {
	rs := JaywanRanges{
		{Start: "6690090000000000", End: "6690090099999999"},
		{Start: "6690090100000000", End: "6690090199999999"},
		{Start: "6690090300000000", End: "6690090399999999"},
		{Start: "6690095000000000", End: "6690095099999999"},
		{Start: "6690107100000000", End: "6690107199999999"},
		{Start: "9784500300000000", End: "9784500399999999"},
	}
	if !rs.Contains("6690090000002009") {
		t.Error("6690090000002009 should be Jaywan")
	}
	if rs.Contains("6690109900000010") {
		t.Error("6690109900000010 should NOT be Jaywan (BIN 66901099 outside all ranges)")
	}
	if !rs.Contains("9784500300000001") {
		t.Error("9784500300000001 should be Jaywan")
	}
}

func TestLoadJaywanRanges(t *testing.T) {
	path := "/tmp/opencode/jaywan_ranges.csv"
	rs, warns, err := LoadJaywanRanges(path)
	if err != nil {
		t.Fatal(err)
	}
	if len(rs) != 6 {
		t.Errorf("expected 6 ranges, got %d", len(rs))
	}
	if len(warns) != 0 {
		t.Errorf("unexpected warns: %v", warns)
	}
	if !rs.Contains("6690090000002009") {
		t.Error("6690090000002009 should be in ranges")
	}
}

func TestParseJaywanSample(t *testing.T) {
	f, err := os.Open("/tmp/opencode/jaywan.001")
	if err != nil {
		t.Skip("jaywan sample not present:", err)
	}
	defer f.Close()

	ranges := JaywanRanges{
		{Start: "6690090000000000", End: "6690090099999999"},
		{Start: "6690090100000000", End: "6690090199999999"},
		{Start: "6690090300000000", End: "6690090399999999"},
		{Start: "6690095000000000", End: "6690095099999999"},
		{Start: "6690107100000000", End: "6690107199999999"},
		{Start: "9784500300000000", End: "9784500399999999"},
	}

	now := time.Date(2026, 8, 14, 10, 0, 0, 0, time.Local)
	proc := &Processor{
		UserSerNumber: 2, InsCode: 1, IntCode: 13, JobNumber: 42,
		CountryCode: func(context.Context, string) (string, error) { return "OMN", nil },
		Now:         func() time.Time { return now },
		Jaywan:      ranges,
	}

	var txns []*Entity
	sc := bufio.NewScanner(f)
	lineNo := 0
	for sc.Scan() {
		lineNo++
		if lineNo == 1 {
			continue // header
		}
		line := strings.TrimRight(sc.Text(), "\r")
		rec, err := ParseRecord(line)
		if err != nil {
			t.Fatalf("line %d parse: %v", lineNo, err)
		}
		ent, err := proc.Process(rec)
		if err != nil {
			t.Fatalf("line %d process: %v", lineNo, err)
		}
		if ent != nil {
			txns = append(txns, ent)
		}
	}
	if err := sc.Err(); err != nil {
		t.Fatal(err)
	}

	if len(txns) != 29 {
		t.Fatalf("expected 29 transactions, got %d", len(txns))
	}

	var jaywan, other int
	for _, e := range txns {
		if e.Network == "JAYWAN" {
			jaywan++
		} else {
			other++
		}
		if e.CardNumber == "" || e.Mcc == "" {
			t.Errorf("txn %d: empty card/mcc", e.SerialNumber)
		}
	}
	// Only BIN 66900900 (13 txns) falls in a range; BIN 66901099 (16 txns) does not.
	if jaywan != 13 {
		t.Errorf("expected 13 JAYWAN txns, got %d (other=%d)", jaywan, other)
	}
}

func TestGetTxnCode(t *testing.T) {
	cases := []struct {
		mti, proc, net, merchantType, want string
	}{
		{"0100", "00", "VISA", "0000", "05"},
		{"0100", "00", "MCI", "0000", "00"},
		{"0100", "09", "MCI", "0000", "09"},
		{"0100", "20", "MCI", "0000", "20"},
		{"0100", "20", "VISA", "0000", "06"},
		{"0100", "01", "RUPAY", "6010", "07"},
		{"0100", "01", "RUPAY", "5999", "01"},
		{"0100", "01", "VISA", "0000", "07"},
		{"0100", "17", "MCI", "0000", "12"},
		{"0100", "71", "MCI", "0000", "71"},
		{"0100", "61", "VISA", "0000", "61"},
		{"0400", "00", "VISA", "0000", "25"},
		{"0400", "00", "MCI", "0000", "20"},
		{"0400", "20", "VISA", "0000", "26"},
		{"0400", "01", "RUPAY", "6010", "27"},
		{"0400", "01", "RUPAY", "5999", "21"},
	}
	for _, c := range cases {
		got := getTxnCode(c.mti, c.proc, c.net, c.merchantType)
		if got != c.want {
			t.Errorf("getTxnCode(%s,%s,%s,%s)=%q want %q", c.mti, c.proc, c.net, c.merchantType, got, c.want)
		}
	}
}

func TestDivisor(t *testing.T) {
	if d := Divisor("048"); d != 1000 {
		t.Errorf("048 divisor=%d want 1000", d)
	}
	if d := Divisor("840"); d != 100 {
		t.Errorf("840 divisor=%d want 100", d)
	}
	if d := Divisor("KWD"); d != 1000 {
		t.Errorf("KWD divisor=%d want 1000", d)
	}
}

func TestMergeDateAndTime(t *testing.T) {
	now := time.Date(2025, 1, 8, 10, 0, 0, 0, time.Local)
	// Local date within current year -> same year.
	dt := mergeDateAndTime("0105", "142530", now)
	if dt == nil {
		t.Fatal("dt nil")
	}
	if dt.Format("20060102150405") != "20250105142530" {
		t.Errorf("got %v", dt)
	}
	// Future date -> previous year.
	dt2 := mergeDateAndTime("1225", "235959", now)
	if dt2 == nil {
		t.Fatal("dt2 nil")
	}
	if dt2.Format("2006") != "2024" {
		t.Errorf("future date year=%v want 2024", dt2.Format("2006"))
	}
	// Garbage -> nil.
	if mergeDateAndTime("xyz", "123456", now) != nil {
		t.Error("garbage should be nil")
	}
}

func TestMapTerminalType(t *testing.T) {
	if got := mapTerminalType("0"); got != "POI" {
		t.Errorf("0 -> %q", got)
	}
	if got := mapTerminalType("6"); got != "CT6" {
		t.Errorf("6 -> %q", got)
	}
	if got := mapTerminalType("9"); got != "CT9" {
		t.Errorf("9 -> %q", got)
	}
	if got := mapTerminalType("7"); got != "" {
		t.Errorf("7 -> %q", got)
	}
}
