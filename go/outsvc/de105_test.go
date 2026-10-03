package outsvc

import "testing"

func newTestProcessor() *IpmOutProcessor { return &IpmOutProcessor{} }

// DE 105 on the 1240 detail record is subelement TLV: 3-digit tag, 3-digit
// length, then the ans-22 Transaction Link ID (e.g. 001022 + TLID). It is
// emitted last, after DE 95, and a 1740 retrieval must not carry it.
func TestDetailRecordEmitsDE105(t *testing.T) {
	p := newTestProcessor()
	tlid := "001022KZ-Gqkm6mC10lUlJ2rzd22"
	rs := &IpmOutWorkEntity{
		DE001: ptrStr("1240"),
		DE105: &tlid,
	}
	rec, dump, err := p.buildDetail(rs, map[string]string{"2": "4761732000020015"})
	if err != nil {
		t.Fatal(err)
	}
	// DE 105 is appended after the record's EBCDIC conversion point, so the
	// dump carries it EBCDIC-encoded.
	// LLLVAR ASCII: a 3-digit length ("028" for the 28-character value)
	// followed by the value, exactly as the UAT 1240 carries it.
	if !containsAll(dump, "028"+tlid) {
		t.Errorf("DE 105 subelement not in the record dump:\n%q", dump)
	}
	// Layout: 4-byte length, 4-byte MTI, then the 16-byte bitmap, so
	// bm[13] (bit 104) is rec[8+13].
	if len(rec) < 24 {
		t.Fatalf("record too short: %d bytes", len(rec))
	}
	if rec[8+13]&0x80 == 0 {
		t.Errorf("secondary bitmap bit for DE 105 not set: %08b", rec[8+13])
	}

	// A retrieval (1740) never carries DE 105.
	r1740 := "1740"
	rs2 := &IpmOutWorkEntity{DE001: &r1740, DE105: &tlid}
	_, dump2, err := p.buildDetail(rs2, map[string]string{})
	if err != nil {
		t.Fatal(err)
	}
	if containsAll(dump2, "028"+tlid) {
		t.Errorf("1740 retrieval must not carry DE 105:\n%s", dump2)
	}

	// No TLID -> no DE 105 at all, never an empty field.
	rs3 := &IpmOutWorkEntity{DE001: ptrStr("1240")}
	_, _, err = p.buildDetail(rs3, map[string]string{})
	if err != nil {
		t.Fatalf("empty DE105 must be omitted, not fatal: %v", err)
	}
}

func containsAll(s, sub string) bool {
	return len(sub) > 0 && len(s) >= len(sub) && indexOf(s, sub) >= 0
}

func indexOf(s, sub string) int {
	for i := 0; i+len(sub) <= len(s); i++ {
		if s[i:i+len(sub)] == sub {
			return i
		}
	}
	return -1
}
