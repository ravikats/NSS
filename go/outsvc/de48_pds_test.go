package outsvc

import (
	"strings"
	"testing"
)

// PDS 0213 (merchant country of origin), PDS 0170 (acceptor contact), PDS 0018
// and PDS 0175 must reach every 1240 detail record. They were only appended
// inside the "PDS165 does not end in C" branch, so a Mastercard row settling
// with indicator C silently lost all four and the generated file failed
// PDS0213_REQUIRED / PDS0170_REQUIRED.
func TestDE48CarriesPdsOnSettlementIndicatorC(t *testing.T) {
	for _, pds165 := range []string{"0165001C", "0165001M", "0165001"} {
		rs := &IpmOutWorkEntity{
			DE001:         ptrStr("1240"),
			PDS165:        &pds165,
			DE048_PDS0213: "0213003784",
			DE048_PDS0170: "01700160560005566      ",
			DE048_PDS0175: ptrStr(""),
			PDS0018:       ptrStr(""),
			PDS155:        ptrStr(""),
			PDS23:         ptrStr("0023003POS"),
			DE033:         ptrStr("034540"),
		}
		_, dump, err := newTestProcessor().buildDetail(rs, map[string]string{})
		if err != nil {
			t.Fatalf("pds165=%s: %v", pds165, err)
		}
		if !containsAll(dump, "0213003784") {
			t.Errorf("pds165=%s: PDS 0213 missing from the record:\n%s", pds165, dump)
		}
		if !containsAll(dump, "01700160560005566") {
			t.Errorf("pds165=%s: PDS 0170 missing from the record:\n%s", pds165, dump)
		}
	}
}

// A 1740 retrieval is a reduced record: no PDS 0213 / PDS 0170 either.
func TestDE48OmitsPdsOn1740Retrieval(t *testing.T) {
	rs := &IpmOutWorkEntity{
		DE001:         ptrStr("1740"),
		PDS165:        ptrStr("0165001C"),
		PDS25:         "0025007R20261003",
		DE048_PDS0213: "0213003784",
		DE048_PDS0170: "01700160560005566      ",
	}
	_, dump, err := newTestProcessor().buildDetail(rs, map[string]string{})
	if err != nil {
		t.Fatal(err)
	}
	if strings.Contains(dump, "0213003784") || strings.Contains(dump, "01700160560005566") {
		t.Errorf("1740 retrieval must not carry PDS 0213 / PDS 0170:\n%s", dump)
	}
}
