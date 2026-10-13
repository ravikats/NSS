package outsvc

import (
	"testing"
	"time"
)

// The date range is optional narrowing. Eligibility is gen_status=3, so an
// absent range must NOT be turned into a bound that hides ready rows.
//
// This is the regression that made the console show "3 ready" while Generate
// answered "There are no transactions to stage!": the default upper bound was
// the moment the page loaded, and MCT_LOCAL_DATE_TIME is stamped at staging
// time, so rows staged afterwards fell outside it.
func TestParseOptionalRangeUnbounded(t *testing.T) {
	from, to, ok := parseOptionalRange("", "")
	if !ok {
		t.Fatal("empty range should be accepted (means: everything staged)")
	}
	if from != nil {
		t.Errorf("from = %v, want nil for an unbounded request", from)
	}
	if to == nil {
		t.Fatal("to must be non-nil: the store's LessThanEqual selectors need an upper bound")
	}
	// It only has to clear anything staged now; 50 years is generous, but the
	// point is that it is not "now".
	if !to.After(time.Now()) {
		t.Errorf("to = %v must be in the future, got a bound that truncates today's rows", to)
	}
}

func TestParseOptionalRangeExplicit(t *testing.T) {
	from, to, ok := parseOptionalRange("01/10/2026 00:00:00", "13/10/2026 23:59:59")
	if !ok {
		t.Fatal("valid range rejected")
	}
	if from == nil || to == nil {
		t.Fatal("explicit range must yield both bounds")
	}
	if !to.After(*from) {
		t.Errorf("to (%v) must be after from (%v)", to, from)
	}
	if from.Format("2006-01-02") != "2026-10-01" || to.Format("2006-01-02") != "2026-10-13" {
		t.Errorf("parsed %v..%v, want 2026-10-01..2026-10-13", from, to)
	}
}

// A half-specified range must be rejected, not silently widened. Silently
// treating "from only" as unbounded would make the UI's two independent
// datetime inputs produce different scopes depending on which one was filled.
func TestParseOptionalRangeRejectsHalfSpecified(t *testing.T) {
	if _, _, ok := parseOptionalRange("01/10/2026 00:00:00", ""); ok {
		t.Error("from-only range accepted; want rejection")
	}
	if _, _, ok := parseOptionalRange("", "13/10/2026 23:59:59"); ok {
		t.Error("to-only range accepted; want rejection")
	}
}

func TestParseOptionalRangeRejectsInvalid(t *testing.T) {
	cases := [][2]string{
		{"nonsense", "13/10/2026 23:59:59"},
		{"01/10/2026 00:00:00", "nonsense"},
		{"13/10/2026 23:59:59", "01/10/2026 00:00:00"}, // to before from
		{"2026-10-01T00:00", "2026-10-13T23:59"},       // wrong format (ISO, not the API layout)
	}
	for _, c := range cases {
		if _, _, ok := parseOptionalRange(c[0], c[1]); ok {
			t.Errorf("parseOptionalRange(%q, %q) accepted; want rejection", c[0], c[1])
		}
	}
}
