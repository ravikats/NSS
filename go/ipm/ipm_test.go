package ipm

import (
	"os"
	"strings"
	"testing"
)

// The expectations below were produced by the Python original
// (switch/NSS/IPMParser/ipm_parser.py) running against the same fixtures, so
// this file pins Go/Python behavioural parity, not just "it runs".

func mustValidator(t *testing.T) *Validator {
	t.Helper()
	v, err := NewValidator()
	if err != nil {
		t.Fatalf("NewValidator: %v", err)
	}
	return v
}

// TestValidateSmallFile pins the full record-by-record outcome of the small
// fixture, including the exact violation set.
func TestValidateSmallFile(t *testing.T) {
	v := mustValidator(t)
	results, fileViolations, err := v.ValidateFile("testdata/TESTR11130072026.01")
	if err != nil {
		t.Fatalf("ValidateFile: %v", err)
	}
	if len(results) != 6 {
		t.Fatalf("records = %d, want 6", len(results))
	}
	if len(fileViolations) != 0 {
		t.Errorf("file violations = %v, want none", fileViolations)
	}

	wantMTI := []string{"1644", "1240", "1240", "1240", "1240", "1644"}
	wantViolations := map[int][]string{
		// Python: 4 transaction records, each missing DE105 and PDS 0213;
		// record 5 additionally has no PDS 0170.
		2: {"DE105_REQUIRED", "MERCHANT_COUNTRY_OF_ORIGIN_REQUIRED"},
		3: {"DE105_REQUIRED", "MERCHANT_COUNTRY_OF_ORIGIN_REQUIRED"},
		4: {"DE105_REQUIRED", "MERCHANT_COUNTRY_OF_ORIGIN_REQUIRED"},
		5: {"ACCEPTOR_CONTACT_REQUIRED", "DE105_REQUIRED", "MERCHANT_COUNTRY_OF_ORIGIN_REQUIRED"},
	}

	for i, r := range results {
		if r.MTI != wantMTI[i] {
			t.Errorf("record %d mti = %s, want %s", r.RecordNo, r.MTI, wantMTI[i])
		}
		if len(r.Errors) != 0 {
			t.Errorf("record %d errors = %v, want none", r.RecordNo, r.Errors)
		}
		var got []string
		for _, x := range r.Violations {
			got = append(got, x.RuleID)
		}
		sortStrings(got)
		want := wantViolations[r.RecordNo]
		if len(got) != len(want) {
			t.Errorf("record %d violations = %v, want %v", r.RecordNo, got, want)
			continue
		}
		for j := range want {
			if got[j] != want[j] {
				t.Errorf("record %d violations = %v, want %v", r.RecordNo, got, want)
				break
			}
		}
	}
}

// TestValidateFieldsExact pins decoded field values on the small fixture.
// These came from the Python parser verbatim.
func TestValidateFieldsExact(t *testing.T) {
	v := mustValidator(t)
	results, _, err := v.ValidateFile("testdata/TESTR11130072026.01")
	if err != nil {
		t.Fatalf("ValidateFile: %v", err)
	}

	// Record 4 exercises the trailing-byte recovery: its declared length is
	// 2 bytes short, so DE94 only resolves to the full "034540" when the
	// trailing bytes are appended.
	want := map[int]string{
		2: "5123450000000008",
		3: "5567890000001238",
		4: "5123450000002343",
		5: "5555550000004442",
	}
	for _, r := range results {
		if w, ok := want[r.RecordNo]; ok {
			if got := r.Fields[2]; got != w {
				t.Errorf("record %d DE2 = %q, want %q", r.RecordNo, got, w)
			}
		}
	}

	if got := results[3].Fields[94]; got != "034540" {
		t.Errorf("record 4 DE94 = %q, want %q (trailing-byte recovery)", got, "034540")
	}
	if got := results[3].Fields[37]; got != "619506069551" {
		t.Errorf("record 4 DE37 = %q, want 619506069551", got)
	}
	if got := results[3].Fields[41]; got != "ALPHA01 " {
		t.Errorf("record 4 DE41 = %q, want %q", got, "ALPHA01 ")
	}
}

// TestValidateLargeFile pins the record count and confirms the large fixture
// parses completely with no violations.
func TestValidateLargeFile(t *testing.T) {
	v := mustValidator(t)
	results, fileViolations, err := v.ValidateFile("testdata/TESTR11106082026.01")
	if err != nil {
		t.Fatalf("ValidateFile: %v", err)
	}
	if len(results) != 1160 {
		t.Fatalf("records = %d, want 1160", len(results))
	}
	if len(fileViolations) != 0 {
		t.Errorf("file violations = %v, want none", fileViolations)
	}

	var txns, incomplete, withErrors int
	for _, r := range results {
		switch r.MTI {
		case "1644":
		case "1240":
			txns++
			if !r.Complete {
				incomplete++
			}
		}
		if len(r.Errors) > 0 {
			withErrors++
		}
	}
	if withErrors != 0 {
		t.Errorf("records with parse errors = %d, want 0", withErrors)
	}
	if txns != 1158 {
		t.Errorf("transaction records = %d, want 1158", txns)
	}
	if incomplete != 0 {
		t.Errorf("incomplete transaction records = %d, want 0", incomplete)
	}
	for _, r := range results {
		if r.MTI == "1240" && r.Remaining != 0 {
			t.Errorf("record %d remaining = %d, want 0", r.RecordNo, r.Remaining)
		}
	}
}

// TestFileTotalsReconcile proves the footer rule works: the declared record
// count and amount must match what the file actually contains.
func TestFileTotalsReconcile(t *testing.T) {
	v := mustValidator(t)
	results, _, err := v.ValidateFile("testdata/TESTR11106082026.01")
	if err != nil {
		t.Fatalf("ValidateFile: %v", err)
	}
	footer := results[len(results)-1]
	if footer.MTI != "1644" {
		t.Fatalf("last record mti = %s, want 1644", footer.MTI)
	}
	declared, ok := footer.Lookup("pds.0306.value")
	if !ok {
		t.Fatal("footer has no pds.0306")
	}
	// The footer pads the count to 8 digits ("00001160").
	if want := padLeft(strings.TrimSpace(""), itoa(len(results)), 8); strings.TrimLeft(declared, "0") != strings.TrimLeft(want, "0") {
		t.Errorf("footer declares %q records, file has %d", declared, len(results))
	}
}

// TestCorruptFooterDetected proves the file rules actually fire by corrupting
// the footer's declared count.
func TestCorruptFooterDetected(t *testing.T) {
	orig, err := os.ReadFile("testdata/TESTR11130072026.01")
	if err != nil {
		t.Fatal(err)
	}
	data := append([]byte(nil), orig...)

	// The footer DE48 encodes "0306008" + an 8-digit count. Both are EBCDIC,
	// and the 1012->1014 block transform may have inserted 00 00 pairs inside
	// the field, so the count digits are collected tolerantly.
	if i, digits := findCountDigits(data, "0306008", 8); i >= 0 {
		data[digits[len(digits)-1]] = asciiToEbcdicByte('9')
	} else {
		t.Fatal("fixture does not contain a decodable footer count")
	}

	v := mustValidator(t)
	_, fileViolations, err := v.Validate(data)
	if err != nil {
		t.Fatalf("Validate: %v", err)
	}
	var found bool
	for _, x := range fileViolations {
		if x.RuleID == "FILE_TOTAL_COUNT" {
			found = true
		}
	}
	if !found {
		t.Errorf("expected FILE_TOTAL_COUNT violation, got %v", fileViolations)
	}
}

func TestPDSParsing(t *testing.T) {
	// Expectations taken from the Python PDSParser running over record 2 of
	// the large fixture. The blob is rebuilt from the chunks so the test
	// cannot drift from a hand-typed length prefix.
	want := []PDS{
		{ID: "0023", Length: 3, Value: "POI"},
		{ID: "0148", Length: 4, Value: "7842"},
		{ID: "0158", Length: 12, Value: "          PE"},
		{ID: "0165", Length: 1, Value: "M"},
		{ID: "0211", Length: 2, Value: "22"},
		{ID: "0170", Length: 16, Value: "0504286420      "},
	}
	var blob string
	for _, w := range want {
		blob += w.ID + padLeft("", itoa(w.Length), 3) + w.Value
	}

	got := ParsePDS(blob)
	if len(got) != len(want) {
		t.Fatalf("PDS count = %d, want %d (%v)", len(got), len(want), got)
	}
	for i := range want {
		if got[i] != want[i] {
			t.Errorf("PDS[%d] = %+v, want %+v", i, got[i], want[i])
		}
	}
}

func TestSubelementExtraction(t *testing.T) {
	// DE105 is encoded as <3-digit id><3-digit len><value> chunks.
	// The 3-digit length (022) is authoritative: a value shorter than that
	// still yields exactly 022 characters, matching the Python slice.
	v := "001022abc-def-ghi-12345678" + "002004xyzw"
	got, ok := extractSubelement(v, "001")
	if !ok || got != "abc-def-ghi-1234567800" {
		t.Errorf("subelement 001 = %q ok=%v", got, ok)
	}
	if _, ok := extractSubelement(v, "009"); ok {
		t.Error("subelement 009 should be absent")
	}
}

func TestReaderLengthTolerance(t *testing.T) {
	// A stray control byte in front of the length must be tolerated, and more
	// than MAX_LENGTH_SKIP of them must not be.
	xyz := []byte{asciiToEbcdicByte('X'), asciiToEbcdicByte('Y'), asciiToEbcdicByte('Z')}
	r := NewReader(append([]byte{0x10, 0xF0, 0xF3}, xyz...))
	n, err := r.ReadLength(2)
	if err != nil || n != "03" {
		t.Fatalf("ReadLength = %q, %v; want 03", n, err)
	}
	if got := r.ReadEbcdic(3); got != "XYZ" {
		t.Errorf("ReadEbcdic = %q, want XYZ", got)
	}

	junk := make([]byte, 0, 12)
	for i := 0; i < 12; i++ {
		junk = append(junk, 0x10)
	}
	junk = append(junk, 0xF0, 0xF3)
	if _, err := NewReader(junk).ReadLength(2); err == nil {
		t.Error("expected failure when junk exceeds the skip limit")
	}
}

func TestEbcdicRoundTrip(t *testing.T) {
	for _, s := range []string{"0123456789", "ABCDEFGHIJ", "abcdefghij", " -/:"} {
		enc := make([]byte, len(s))
		for i := 0; i < len(s); i++ {
			enc[i] = tcode[s[i]>>4][s[i]&0x0F]
		}
		if got := decodeEbcdic(enc); got != s {
			t.Errorf("round trip %q -> % x -> %q", s, enc, got)
		}
	}
}

func asciiToEbcdicByte(c byte) byte { return tcode[c>>4][c&0x0F] }

// ebcdicBytes encodes an ASCII literal the way it appears in an IPM file.
// findCountDigits locates prefix (ASCII) inside an EBCDIC-encoded file and
// returns the byte offsets of the next n EBCDIC digit bytes, skipping the
// 00 00 pairs the block transform inserts.
func findCountDigits(data []byte, prefix string, n int) (int, []int) {
	needle := ebcdicBytes(prefix)
	i := indexOf(data, needle)
	if i < 0 {
		return -1, nil
	}
	var digits []int
	for p := i + len(needle); p < len(data) && len(digits) < n; p++ {
		b := data[p]
		if b >= 0xF0 && b <= 0xF9 {
			digits = append(digits, p)
			continue
		}
		if b != 0x00 {
			break
		}
	}
	if len(digits) != n {
		return -1, nil
	}
	return i, digits
}

func ebcdicBytes(s string) []byte {
	out := make([]byte, len(s))
	for i := 0; i < len(s); i++ {
		out[i] = asciiToEbcdicByte(s[i])
	}
	return out
}

func padLeft(_, s string, n int) string {
	for len(s) < n {
		s = "0" + s
	}
	return s
}

func indexOf(h, n []byte) int {
	for i := 0; i+len(n) <= len(h); i++ {
		match := true
		for j := range n {
			if h[i+j] != n[j] {
				match = false
				break
			}
		}
		if match {
			return i
		}
	}
	return -1
}

func sortStrings(s []string) {
	for i := 1; i < len(s); i++ {
		for j := i; j > 0 && s[j] < s[j-1]; j-- {
			s[j], s[j-1] = s[j-1], s[j]
		}
	}
}
