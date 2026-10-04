package outsvc

import (
	"math/big"
	"os"
	"path/filepath"
	"strings"
	"testing"
	"time"

	"empay/irf/upval"
)

// buildValidatedFile renders records with the SAME functions production uses
// and returns the file name and bytes. Using the real builders (rather than a
// hand-assembled fixture) is deliberate: a hand-built record is easy to get
// subtly wrong, which tests the fixture instead of the validator.
func buildValidatedFile(t *testing.T, batch time.Time, txns []*UnionPayAcqTxnWorkEntity) (string, []byte) {
	t.Helper()
	mult := new(big.Rat).SetInt64(1)
	dec := map[string]string{}
	lines := []string{unionPayTC000("24160784", batch, "TEST")}
	for _, txn := range txns {
		dec[txn.EncryptedCardNumber] = "6210946888070000009"
		lines = append(lines, unionPayTxnRecord(txn, dec, mult))
	}
	lines = append(lines, unionPayTC001(len(lines)+1))
	name := "OFC" + batch.Format("060102") + "51C"
	// The generator joins with CRLF and writes no trailing line break.
	return name, []byte(strings.Join(lines, "\r\n"))
}

func validUnionPayRows() []*UnionPayAcqTxnWorkEntity {
	pt := func(d int, h, m, s int) *time.Time {
		t := time.Date(2026, 8, 25, h, m, s, 0, time.UTC)
		return &t
	}
	chipSale := func(rn, stan string) *UnionPayAcqTxnWorkEntity {
		return &UnionPayAcqTxnWorkEntity{
			Rrn: rn, TxnType: "000000", PosEntryMode: "072", LocalDateTime: pt(0, 7, 3, 26),
			TransDateTime: "0825070326", StanNumber: stan, ApprovalCode: "037156",
			TxnCurCode: "784", TxnAmount: 20, MeName: "AliSaeed", MeCity: "dubai",
			MeCountry: "AE", AcqInstCountryCode: "784", Mcc: "5411", TerminalId: "T0000243",
			MerchantId: "M00000000000363", AcqinstIdCode: "24160784", FwdInstIdCode: "24160784",
			CardInputCapability: "", AppCryptogram: "39107EBE7CE43725", CardSeqNumber: "001",
			ChipTrlCapabilities: "E060C8", TrlVerResult: "0000000000", UpblNumber: "497A8B62",
			IssAppData: "07010103A00000010A0100000000003B2D55DB", AppTxnCounter: "00EE",
			AppICProfile: "7C00", ChipTxnDate: "260825", TrlConCode: "784", CryptAmount: 20,
			ChipCurCode: "784", CryptInfoData: "80", CvmResult: "3F0000", ChipTrlType: "22",
			DedicatedFileName: "A000000333010102", TrlAppVerNumber: "0030",
			EncryptedCardNumber: "tok",
		}
	}
	magSale := func(rn string) *UnionPayAcqTxnWorkEntity {
		e := chipSale(rn, "091124")
		e.PosEntryMode = "022"
		return e
	}
	refund := func(rn, origDate, origStan string) *UnionPayAcqTxnWorkEntity {
		e := chipSale(rn, "091124")
		e.TxnType = "200000"
		e.PosEntryMode = "052"
		e.OriginalRRN = "623707343027"
		e.OrigTxnCode = "100"
		e.OrigTxnDatetime = origDate
		e.OrigStan = origStan
		e.OrigSettleDate = "0825"
		e.TxnAmount = 10
		return e
	}
	return []*UnionPayAcqTxnWorkEntity{
		chipSale("623707073628", "073628"),
		chipSale("623707051029", "051029"),
		chipSale("623707103624", "103624"),
		magSale("623707916070"),
		refund("623707911124", "0825071821", "343027"),
		refund("623707505651", "0825072327", "993558"),
	}
}

// The end-to-end guard: whatever the generator produces must satisfy the
// validator. This is the check that would have caught the real defects fixed in
// the "match the real UAT settlement file" commit (blank org code, lowercase
// hex, wrong entry capability, wrong IC condition, wrong transaction category).
func TestUnionPayGeneratedFilePassesValidation(t *testing.T) {
	batch := time.Date(2026, 9, 8, 0, 0, 0, 0, time.UTC)
	name, data := buildValidatedFile(t, batch, validUnionPayRows())
	rep := upval.ValidateFileData(name, data)
	if !rep.OK() {
		t.Fatalf("generated file must satisfy the validator, got %d issue(s):\n  %s",
			len(rep.Issues), strings.Join(rep.Issues, "\n  "))
	}
	if rep.Transactions != 6 {
		t.Errorf("transactions = %d, want 6", rep.Transactions)
	}
	if rep.Records != 8 {
		t.Errorf("records = %d, want 8 (header + 6 + trailer)", rep.Records)
	}
}

// NOTE: the agreement between Block 2 182-183 (transaction category) and the
// processing code is deliberately NOT asserted here. The processing code is not
// present in the settlement file, so a file-level validator cannot check it;
// that consistency is covered by TestUnionPayBlock2TransactionCategoryIsProcessingCodePrefix,
// which has access to the row.
//
// TestUnionPayValidationCatchesCorruption mutates the generator's own output
// and requires each corruption to be reported, proving the rules are not
// vacuous.
func TestUnionPayValidationCatchesCorruption(t *testing.T) {
	batch := time.Date(2026, 9, 8, 0, 0, 0, 0, time.UTC)

	type pos struct{ off, n int }
	cases := []struct {
		name    string
		rec     int // 0 = header, -1 = trailer, >0 = that txn (1-based among txns)
		at      pos
		replace string
		wantSub string
	}{
		{"org code blank", 1, pos{344, 3}, "   ", "Organization code must be CUP"},
		{"app profile lowercase", 1, pos{387 + 124, 4}, "7c00", "uppercase hex"},
		{"cryptogram lowercase", 1, pos{387, 16}, "61367a3cd782ca58", "uppercase hex"},
		{"GSCS serial non numeric", 1, pos{196, 9}, "ZZZZZZZZZ", "GSCS serial number"},
		{"channel not POS", 1, pos{228, 2}, "99", "Transaction channel must be 03"},
		{"entry capability invalid", 1, pos{409, 1}, "9", "Terminal entry capability"},
		{"IC card condition invalid", 1, pos{410, 1}, "7", "IC card condition code"},
		{"currency not numeric", 1, pos{38, 3}, "78X", "Transaction currency"},
		{"CVM non hex", 1, pos{599, 6}, "ZZZZZZ", "Cardholder authentication method"},
		{"sale feature not space", 1, pos{230, 1}, "F", "must use a space for the transaction feature"},
		{"sale orig auth blank", 1, pos{248, 3}, "   ", "original authorization type 100"},
		{"refund feature not R", 5, pos{230, 1}, " ", "must use 'R'"},
		{"refund orig auth not spaces", 5, pos{248, 3}, "100", "with spaces"},
		{"header bitmap wrong", 0, pos{3, 4}, "C000", "Header bitmap"},
		{"header batch date bad", 0, pos{18, 8}, "2026XXXX", "Batch date"},
	}

	for _, tc := range cases {
		t.Run(tc.name, func(t *testing.T) {
			lines := strings.Split(string(mustBuild(t, batch)), "\r\n")
			// Index 0 is the header, -1 is the trailer, and a positive N is the
			// Nth transaction (which sits at the same index, header included).
			idx := tc.rec
			if idx == -1 {
				idx = len(lines) - 1
			}
			line := lines[idx]
			if tc.at.off+tc.at.n > len(line) {
				t.Fatalf("mutation offset %d+%d exceeds record length %d", tc.at.off, tc.at.n, len(line))
			}
			lines[idx] = line[:tc.at.off] + tc.replace + line[tc.at.off+tc.at.n:]

			rep := upval.ValidateFileData("OFC26090851C", []byte(strings.Join(lines, "\r\n")))
			if rep.OK() {
				t.Fatalf("corruption %q was NOT detected", tc.name)
			}
			for _, i := range rep.Issues {
				if strings.Contains(i, tc.wantSub) {
					return
				}
			}
			t.Errorf("corruption %q detected but no issue matched %q; got %v", tc.name, tc.wantSub, rep.Issues)
		})
	}
}

func mustBuild(t *testing.T, batch time.Time) []byte {
	t.Helper()
	_, data := buildValidatedFile(t, batch, validUnionPayRows())
	return data
}

// TestValidateUnionPayFileReportsAndRecords covers the wiring the generator
// relies on: the report is returned, published for the inquiry UI, and written
// to disk. A valid file reports OK; a corrupted one reports its issues.
func TestValidateUnionPayFileReportsAndRecords(t *testing.T) {
	batch := time.Date(2026, 9, 8, 0, 0, 0, 0, time.UTC)
	dir := t.TempDir()
	s := &OutgoingService{cfg: OutgoingConfig{ReconOutDir: dir}}
	s.now = func() time.Time { return batch }

	name, data := buildValidatedFile(t, batch, validUnionPayRows())
	if err := os.WriteFile(filepath.Join(dir, name), data, 0o644); err != nil {
		t.Fatal(err)
	}

	rep := s.validateUnionPayFile(name, "IRF")
	if rep == nil || !rep.OK() {
		t.Fatalf("expected a clean report, got %v", rep)
	}
	vals := s.Validations()
	if len(vals) != 1 || vals[0].Network != "UNIONPAY" || !vals[0].OK {
		t.Fatalf("validation not published for the UI: %+v", vals)
	}
	if _, err := os.Stat(filepath.Join(dir, "unionpay_validation", name+".validation.json")); err != nil {
		t.Errorf("validation report not written: %v", err)
	}

	// Corrupt it and confirm the failure is reported rather than swallowed.
	bad := strings.Replace(string(data), "CUP", "   ", 1)
	if err := os.WriteFile(filepath.Join(dir, name), []byte(bad), 0o644); err != nil {
		t.Fatal(err)
	}
	rep = s.validateUnionPayFile(name, "IRF")
	if rep == nil || rep.OK() {
		t.Fatalf("corrupted file should fail validation, got %v", rep)
	}
	found := false
	for _, i := range rep.Issues {
		if strings.Contains(i, "Organization code") {
			found = true
		}
	}
	if !found {
		t.Errorf("expected the org-code issue, got %v", rep.Issues)
	}
	if len(s.Validations()) != 2 {
		t.Errorf("expected both outcomes published, got %d", len(s.Validations()))
	}

	// A missing file must return nil so it is not double-reported as a
	// validation failure alongside the generation failure.
	if got := s.validateUnionPayFile("OFC-does-not-exist", "IRF"); got != nil {
		t.Errorf("missing file should yield nil, got %v", got)
	}
}
