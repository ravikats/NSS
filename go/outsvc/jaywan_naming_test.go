package outsvc

import (
	"fmt"
	"regexp"
	"strings"
	"testing"
)

// Spec UAE_Switch_Clearing_Specification_Document_V1_3 section 2.5.3 defines the
// Jaywan clearing filename as five elements:
//
//	1 File Type            N2   00 = acquirer member generated
//	2 Clearing Cycle       N1   0 = default
//	3 Participant ID       AN9
//	4 Julian Date          YYDDD
//	5 File Sequence        N2   00 = 1st file, 01 = 2nd file
//
// which totals 19 characters. The port shipped two defects: the first file of a
// date used sequence 1 (spec says 00) and the sequence was rendered with
// strconv.Itoa, so it was never zero-padded and the name came out 18 chars.
const jaywanFileNamePattern = `^000\d{9}\d{5}\d{2}$`

// These call jaywanFileID -- the same function ProcessJaywanOutgoing uses -- so
// they fail if the production expression regresses. An earlier version of this
// test re-implemented the formatting inline and therefore passed against the
// broken code; that is worse than no test at all.
func TestJaywanFileIdIs19CharsAndZeroPadded(t *testing.T) {
	re := regexp.MustCompile(jaywanFileNamePattern)

	cases := []struct {
		name        string
		participant string
		year        int
		dayOfYear   int
		sequence    int
		want        string
	}{
		{"first file of the day is sequence 00", "784666661", 2026, 279, 0, "0007846666612627900"},
		{"second file", "784666661", 2026, 279, 1, "0007846666612627901"},
		{"ninth file still one digit padded", "784666661", 2026, 279, 9, "0007846666612627909"},
		{"tenth file is two digits", "784666661", 2026, 279, 10, "0007846666612627910"},
		{"jan 1st is julian 26001", "784666661", 2026, 1, 0, "0007846666612600100"},
		{"dec 31st is julian 26365", "784666661", 2026, 365, 0, "0007846666612636500"},
	}

	for _, tc := range cases {
		t.Run(tc.name, func(t *testing.T) {
			got := jaywanFileID(tc.participant, tc.year, tc.dayOfYear, tc.sequence)
			if got != tc.want {
				t.Errorf("jaywanFileID(%q, %d, %d, %d) = %q, want %q",
					tc.participant, tc.year, tc.dayOfYear, tc.sequence, got, tc.want)
			}
			if len(got) != 19 {
				t.Errorf("jaywanFileID(...) = %q is %d chars, want 19", got, len(got))
			}
			if !re.MatchString(got) {
				t.Errorf("jaywanFileID(...) = %q does not match %s", got, jaywanFileNamePattern)
			}
		})
	}
}

// The sequence is N2, so 0 must render as "00" and 10 as "10". A bare
// strconv.Itoa produced "0" and "10", i.e. 18- and 19-char names respectively.
func TestJaywanFileIdSequenceIsAlwaysTwoDigits(t *testing.T) {
	for seq := 0; seq <= 99; seq++ {
		got := jaywanFileID("784666661", 2026, 279, seq)
		want := fmt.Sprintf("00078466666126279%02d", seq)
		if got != want {
			t.Fatalf("sequence %d -> %q, want %q", seq, got, want)
		}
	}
}

// The spec's first-file sequence is 00. The pre-fix code set fileSequence = 1 in
// the "new day" branch, so every day's first file was numbered 01.
func TestJaywanFirstFileOfDaySequenceIsZeroNotOne(t *testing.T) {
	firstOfDay := jaywanFileID("784666661", 2026, 279, 0)
	if !strings.HasSuffix(firstOfDay, "00") {
		t.Errorf("first file of the day = %q, want it to end in sequence 00", firstOfDay)
	}
	if firstOfDay != "0007846666612627900" {
		t.Errorf("first file of the day = %q, want 0007846666612627900", firstOfDay)
	}
}

// ORA-32795: JWN_SER_NUMBER is GENERATED ALWAYS AS IDENTITY on BOTH
// JAYWAN_ACQ_TXN_WORK and JAYWAN_ACQ_TXN_DATA. Naming it in an insert makes
// every row fail, and binding the work table's SerialNumber also copied one
// table's primary key into the other. This locks the column list, the binder
// and the placeholder count together so the three cannot drift.
func TestJaywanInsertColumnsExcludeIdentityAndStayInLockstep(t *testing.T) {
	cols := jaywanColumnList()
	if len(cols) == 0 {
		t.Fatal("jaywanColumns parsed as empty")
	}
	for _, c := range cols {
		if c == "JWN_SER_NUMBER" {
			t.Error("jaywanColumns names JWN_SER_NUMBER, which is GENERATED ALWAYS AS IDENTITY on both jaywan tables (ORA-32795)")
		}
	}
	// One column per bind: a leftover bind for the dropped identity column is
	// exactly how the original defect shipped.
	if got, want := len(cols), len(jaywanDataArgs(&JaywanAcqTxnWorkEntity{})); got != want {
		t.Errorf("jaywanColumns has %d entries but jaywanDataArgs returns %d; they must match", got, want)
	}
	if got := len(cols); got != 48 {
		t.Errorf("jaywanColumns has %d entries, want 48 (49 minus the identity column)", got)
	}
}

// The generated INSERT must bind exactly as many placeholders as it names
// columns, for both the work and the data table (one shared list serves both).
func TestJaywanInsertSqlPlaceholderCountMatchesColumns(t *testing.T) {
	want := len(jaywanColumnList())
	for _, table := range []string{"JAYWAN_ACQ_TXN_WORK", "JAYWAN_ACQ_TXN_DATA"} {
		sql := jaywanInsertSQL(table)
		if !strings.Contains(sql, "INSERT INTO "+table) {
			t.Errorf("jaywanInsertSQL(%s) does not target that table: %s", table, sql)
		}
		if got := strings.Count(sql, ":"); got != want {
			t.Errorf("%s: insert SQL binds %d placeholders but names %d columns", table, got, want)
		}
	}
}
