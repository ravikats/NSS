package binsvc

import "testing"

// UPL_FOR_CODE is the per-network FOR_CODE that Java records on the
// FILE_UPLOAD_LOG row. The port looked up FormatCodes with the FILE NAME
// instead of the NETWORK, so the column was silently always 0.
func TestFormatCodeIsKeyedOnNetworkNotFileName(t *testing.T) {
	codes := map[string]int{"MASTERCARD": 2, "VISA": 3, "JAYWAN": 61}
	svc := &Service{Cfg: Config{FormatCodes: codes}}

	for network, want := range codes {
		if got := svc.formatCode(network); got != want {
			t.Errorf("formatCode(%q) = %d, want %d", network, got, want)
		}
	}
	if got := svc.formatCode("2026-10-06_MC_RANGES.csv"); got != 0 {
		t.Errorf("formatCode(filename) = %d, want 0 (a filename is not a network)", got)
	}
	if got := svc.formatCode("mastercard"); got != 2 {
		t.Errorf("formatCode must be case-insensitive, got %d", got)
	}
}

// UAESWITCH must be accepted as an alias of JAYWAN everywhere, because the
// uaeswitch POS BIN file is Jaywan's and loads into JAYWAN_ISS_ACC_RANGE. It
// was advertised by the status endpoint and the upload API while isNetwork and
// the loader dispatch rejected it, so every UAESWITCH dispatch 400'd.
func TestUaeswitchIsAliasedToJaywan(t *testing.T) {
	svc := &Service{Cfg: Config{FormatCodes: map[string]int{"JAYWAN": 61, "UAESWITCH": 61}}}
	// formatCode must resolve for both spellings.
	for _, n := range []string{"JAYWAN", "UAESWITCH", "uaeswitch"} {
		if got := svc.formatCode(n); got != 61 {
			t.Errorf("formatCode(%q) = %d, want 61", n, got)
		}
	}
	// And the mapping table must agree.
	if networkRangeTable["UAESWITCH"] != "JAYWAN_ISS_ACC_RANGE" {
		t.Errorf("UAESWITCH table = %q, want JAYWAN_ISS_ACC_RANGE", networkRangeTable["UAESWITCH"])
	}
}

// The input path must be joined, not concatenated. RECON_IN_TEST is commonly
// configured WITHOUT a trailing slash ("/vp-switch/INPUT"), and concatenation
// then produced "/vp-switch/INPUTUAESWITCH-POS-BIN-....csv" -- the upload was
// sitting in the directory and every load still failed with "file was not
// found at the specified path". Java has the same concatenation, so it only
// worked where the property happens to end in a separator.
func TestInputPathJoinsRegardlessOfTrailingSlash(t *testing.T) {
	for _, dir := range []string{"/vp-switch/INPUT", "/vp-switch/INPUT/"} {
		svc := &Service{Cfg: Config{ReconIn: dir}}
		got := svc.inputPath("UAESWITCH-POS-BIN-20260805.csv")
		want := "/vp-switch/INPUT/UAESWITCH-POS-BIN-20260805.csv"
		if got != want {
			t.Errorf("ReconIn=%q -> inputPath = %q, want %q", dir, got, want)
		}
	}
}
