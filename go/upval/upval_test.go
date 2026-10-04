package upval

import (
	"os"
	"path/filepath"
	"strings"
	"testing"
)

// NOTE: there is deliberately no hand-built "golden" file in this package.
// A hand-assembled record is easy to get subtly wrong (an earlier attempt
// produced a 661-char record that the validator rightly rejected), which
// tests the fixture rather than the validator. The end-to-end check -- real
// generator output validated by this package -- lives in outsvc
// (TestUnionPayGeneratedFilePassesValidation), which builds records with the
// same functions production uses.

func TestParseBlocks(t *testing.T) {
	for _, c := range []struct {
		in   string
		want []int
	}{
		{"8000", []int{0}},
		{"C000", []int{0, 1}},
		{"E000", []int{0, 1, 2}},
		{"F000", []int{0, 1, 2, 3}},
		{"", nil},
		{"ZZZZ", nil},
	} {
		got := parseBlocks(c.in)
		if len(got) != len(c.want) {
			t.Errorf("parseBlocks(%q) = %v, want %v", c.in, got, c.want)
			continue
		}
		for i := range got {
			if got[i] != c.want[i] {
				t.Errorf("parseBlocks(%q) = %v, want %v", c.in, got, c.want)
				break
			}
		}
	}
}

func TestEmptyFileIsValid(t *testing.T) {
	rep := ValidateFileData("OFC26090851C", nil)
	if !rep.OK() {
		t.Errorf("an empty file should be a no-op pass, got %v", rep.Issues)
	}
	if rep.Records != 0 || rep.Transactions != 0 {
		t.Errorf("records/transactions = %d/%d, want 0/0", rep.Records, rep.Transactions)
	}
}

// A file whose name does not carry a YYMMDD date cannot be date-checked, which
// must be reported rather than silently skipped.
func TestFileNameDateRequired(t *testing.T) {
	hdr := "000" + "8000" + "24160784   " + "20260908" + strings.Repeat(" ", 8) + "TEST" + "00000001"
	tr := "001" + "8000" + "0000000002" + strings.Repeat(" ", 32)
	data := []byte(strings.Join([]string{hdr, tr}, "\r\n"))
	rep := ValidateFileData("no-date-here", data)
	if rep.OK() {
		t.Fatal("expected the unparseable file name to be reported")
	}
	found := false
	for _, i := range rep.Issues {
		if strings.Contains(i, "Cannot parse file date") {
			found = true
		}
	}
	if !found {
		t.Errorf("expected a file-name date issue, got %v", rep.Issues)
	}
}

// The file name date and the header batch date must be the same day.
func TestFileNameAndBatchDateMustMatch(t *testing.T) {
	hdr := "000" + "8000" + "24160784   " + "20260908" + strings.Repeat(" ", 8) + "TEST" + "00000001"
	tr := "001" + "8000" + "0000000002" + strings.Repeat(" ", 32)
	data := []byte(strings.Join([]string{hdr, tr}, "\r\n"))

	ok := ValidateFileData("OFC26090851C", data)
	if !ok.OK() {
		t.Errorf("matching dates should pass, got %v", ok.Issues)
	}
	bad := ValidateFileData("OFC26010151C", data)
	if bad.OK() {
		t.Error("mismatched file name / batch date should fail")
	}
}

func TestValidateFileReadsFromDisk(t *testing.T) {
	dir := t.TempDir()
	p := filepath.Join(dir, "OFC26090851C")
	hdr := "000" + "8000" + "24160784   " + "20260908" + strings.Repeat(" ", 8) + "TEST" + "00000001"
	tr := "001" + "8000" + "0000000002" + strings.Repeat(" ", 32)
	if err := os.WriteFile(p, []byte(strings.Join([]string{hdr, tr}, "\r\n")), 0o644); err != nil {
		t.Fatal(err)
	}
	rep, err := ValidateFile(p)
	if err != nil {
		t.Fatalf("ValidateFile: %v", err)
	}
	if !rep.OK() {
		t.Fatalf("expected valid, got %v", rep.Issues)
	}
	if rep.File != "OFC26090851C" {
		t.Errorf("File = %q, want the base name", rep.File)
	}
	if !strings.Contains(rep.Summary(), "UNIONPAY OK") {
		t.Errorf("Summary = %q", rep.Summary())
	}
	if _, err := ValidateFile(filepath.Join(dir, "missing")); err == nil {
		t.Error("expected an error for a missing file")
	}
}

// The embedded rules must be loadable and complete.
func TestEmbeddedRulesAreComplete(t *testing.T) {
	r := mustRules()
	if r.Header.Length != 46 || r.Header.Bitmap != "8000" || r.Header.RecordType != "000" {
		t.Errorf("header rules look wrong: %+v", r.Header)
	}
	if r.Trailer.Length != 49 || r.Trailer.RecordType != "001" {
		t.Errorf("trailer rules look wrong: %+v", r.Trailer)
	}
	for _, b := range []string{"0", "1", "2", "3"} {
		if r.Transaction.BlockLengths[b] == 0 {
			t.Errorf("block %s length missing", b)
		}
	}
	for _, b := range []string{"0", "1", "2"} {
		if len(r.Transaction.Fields[b]) == 0 {
			t.Errorf("block %s has no field rules", b)
		}
	}
}
