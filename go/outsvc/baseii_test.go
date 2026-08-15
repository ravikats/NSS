package outsvc

import (
	"strings"
	"testing"
	"time"
)

func visatEntity() *VisaAcqTxnWorkEntity {
	pt := func(t time.Time) *time.Time { return &t }
	pf := func(f float64) *float64 { return &f }
	return &VisaAcqTxnWorkEntity{
		TxnCode:               "05",
		EncCardNumber:         "tok1",
		Arn:                   "01234567890123456789012",
		PurchaseDate:          pt(time.Date(2026, 8, 15, 10, 30, 0, 0, time.UTC)),
		TxnAmount:             100.5,
		SchgAmount:            1.25,
		TxnCurCode:            "USD",
		MeName:                "MERCHANT",
		MeCity:                "DUBAI",
		MeCountry:             "AE",
		Mcc:                   "5812",
		ApprovalCode:          "123456",
		TerminalCapability:    "1",
		ChIdMethod:            " ",
		PosEntryMode:          "05",
		MemberText:            "",
		FeePrgIndicator:       "",
		MerchantId:            "MID1234567",
		TerminalId:            "TERM123",
		MotoEcomIndicator:     " ",
		AccSelection:          " ",
		CashbackAmount:        pf(0),
		AuthAmount:            pf(100.5),
		RespCode:              "00",
		PosEnvironment:        " ",
		AuthCharIndicator:     " ",
		MarketSpecDataInd:     " ",
		SpendQualiIndictor:    " ",
		ValidationCode:        "    ",
		AcceptanceTrlIndicator: "",
		TxnId:                 "000000000012345",
		TrlTxnDate:            pt(time.Date(2026, 8, 15, 0, 0, 0, 0, time.UTC)),
		TrlCapProfile:         "000000",
		TrlCountryCode:        "840",
		UpblNumber:            "00000000",
		AppTxnCounter:         "    ",
		AppIcProfile:          "    ",
		AppCryptogram:         "                ",
		IssAppDataB2:          "00",
		IssAppDataB3:          "  ",
		IssAppDataB4:          "00000000",
		IssAppDataB8:          "00",
		IssAppDataB9:          "0000000000000000",
		IssAppDataB1:          "00",
		IssAppDataB17:         "00",
		IssAppDataB18:         "000000000000000000000000000000",
		TrlVerResult:          "0000000000",
		CryptAmount:           pf(0),
		FormFactorIndicator:   "00000000",
		IssScriptResult:       "0000000000",
	}
}

func TestCurrencyFractionDigits(t *testing.T) {
	cases := map[string]int{"USD": 2, "BHD": 3, "JPY": 0, "VND": 0, "EUR": 2, "KWD": 3, "XOF": 0, "AED": 2}
	for code, want := range cases {
		if got := currencyFractionDigits(code); got != want {
			t.Errorf("currencyFractionDigits(%s) = %d, want %d", code, got, want)
		}
	}
}

func TestBaseIIHelpers(t *testing.T) {
	if got := jstr(""); got != "null" {
		t.Errorf("jstr(\"\") = %q, want null", got)
	}
	if got := jstr("x"); got != "x" {
		t.Errorf("jstr(x) = %q", got)
	}
	if got := rpad("", 23, " "); got != "null" {
		t.Errorf("rpad empty = %q, want null", got)
	}
	if got := lpad("", 6, " "); got != "null" {
		t.Errorf("lpad empty = %q, want null", got)
	}
	if got := sleftPad("123", 12, "0"); got != "000000000123" {
		t.Errorf("sleftPad = %q", got)
	}
	if got := sleftPad("1234567890", 3, "0"); got != "1234567890" {
		t.Errorf("sleftPad no-truncate = %q", got)
	}
	if got := sright(sleftPad("1", 12, "0"), 12); got != "000000000001" {
		t.Errorf("sright/sleftPad combo = %q", got)
	}
	if got := amtToInt(100.5, 100); got != 10050 {
		t.Errorf("amtToInt = %d", got)
	}
	if got := amtToInt(1.005, 100); got != 101 {
		t.Errorf("amtToInt HALF_UP = %d, want 101", got)
	}
}

func TestBaseIIRecordLengths(t *testing.T) {
	g := NewBaseIIGenerator("USD000")
	e := visatEntity()
	dec := map[string]string{"tok1": "1234567890123456"}
	if line := g.getTcr0(e, "1234567890123456"); len(line) != 168 {
		t.Errorf("tcr0 len = %d, want 168", len(line))
	}
	if line := g.getAdditionalData(e); len(line) != 168 {
		t.Errorf("additionalData len = %d, want 168", len(line))
	}
	if line := g.getPaymentServiceData(e); len(line) != 168 {
		t.Errorf("paymentServiceData len = %d, want 168", len(line))
	}
	if line := g.getChipCardTxnData(e); len(line) != 168 {
		t.Errorf("chipCardTxnData len = %d, want 168", len(line))
	}
	// feeCollection width depends on input lengths (Java leftPad does not
	// truncate): 16-char card (leftPad 6->16, 16->16, 3->16) and 8-char acqBin
	// (leftPad 6->8) make it 168 + (16-6) + (16-3) + (8-6) = 193.
	if line := g.getFeeCollection(e, "1234567890123456", "10087096", "0815"); len(line) != 193 {
		t.Errorf("feeCollection len = %d, want 193", len(line))
	}
	aft := *e
	aft.BussAppId = "ABCDEFGHIJKLMNO"
	if line := g.getAFTData(&aft); len(line) != 181 {
		t.Errorf("AFT len = %d, want 181", len(line))
	}
	_ = dec
}

func TestBaseIIRecordContents(t *testing.T) {
	g := NewBaseIIGenerator("USD000")
	e := visatEntity()
	card := "1234567890123456"

	tcr0 := g.getTcr0(e, card)
	if !strings.HasPrefix(tcr0, "050") {
		t.Errorf("tcr0 prefix = %q, want 050", tcr0[:3])
	}
	if !strings.Contains(tcr0, "10087096") {
		t.Error("tcr0 missing acquirerBusinessID")
	}
	if !strings.Contains(tcr0, "0815") {
		t.Error("tcr0 missing MMdd purchase date")
	}
	// amount 100.50 * 100 = 10050, left-padded 12 -> "000000010050"
	if !strings.Contains(tcr0, "000000010050") {
		t.Error("tcr0 missing padded source amount")
	}
	if !strings.HasSuffix(tcr0, "B") {
		t.Errorf("tcr0 suffix = %q, want B (reimbursement attribute)", tcr0[len(tcr0)-1:])
	}

	addl := g.getAdditionalData(e)
	if !strings.HasPrefix(addl, "0501") {
		t.Errorf("additionalData prefix = %q, want 0501", addl[:4])
	}

	psd := g.getPaymentServiceData(e)
	if !strings.HasPrefix(psd, "0505") {
		t.Errorf("paymentServiceData prefix = %q, want 0505", psd[:4])
	}

	chip := g.getChipCardTxnData(e)
	if !strings.HasPrefix(chip, "0507") {
		t.Errorf("chip prefix = %q, want 0507", chip[:4])
	}

	fee := visatEntity()
	fee.TxnCode = "10"
	fee.Arn = "0123456789012345678901234567890123456789012345678901234567890123456789"
	fee.TxnId = "000000000000015"
	fc := g.getFeeCollection(fee, card, "10087096", "0815")
	if !strings.HasPrefix(fc, "100") {
		t.Errorf("feeCollection prefix = %q, want 100", fc[:3])
	}

	aft := *e
	aft.BussAppId = "B2C"
	aft.SenderAccount = "ACC"
	aft.SenderName = "NAME"
	aftLine := g.getAFTData(&aft)
	if !strings.HasPrefix(aftLine, "0513") {
		t.Errorf("AFT prefix = %q, want 0513", aftLine[:4])
	}
	if !strings.Contains(aftLine, "CRB2C") {
		t.Error("AFT missing businessFormatCodeCR+BussAppId")
	}
}

func TestGetFeeAndTxnDataHappyPath(t *testing.T) {
	g := NewBaseIIGenerator("USD000")
	e := visatEntity()
	dec := map[string]string{"tok1": "1234567890123456"}

	lines := g.GetFeeAndTxnData(nil, []*VisaAcqTxnWorkEntity{e}, dec, "10087096", 1, nil)
	if len(lines) != 6 {
		t.Fatalf("lines = %d, want 6 (tcr0, addl, psd, chip, footer91, footer92)", len(lines))
	}
	for i, want := range []string{"0500", "0501", "0505", "0507"} {
		if !strings.HasPrefix(lines[i], want) {
			t.Errorf("line %d prefix = %q, want %q", i, lines[i][:4], want)
		}
	}
	if !strings.HasPrefix(lines[4], "91") {
		t.Errorf("line 4 prefix = %q, want 91", lines[4][:2])
	}
	if !strings.HasPrefix(lines[5], "92") {
		t.Errorf("line 5 prefix = %q, want 92", lines[5][:2])
	}
}

func TestGetFeeAndTxnDataUndecryptedToken(t *testing.T) {
	g := NewBaseIIGenerator("USD000")
	e1 := visatEntity()
	e2 := visatEntity()
	e2.EncCardNumber = "tok2"
	e2.Arn = "99999999999999999999999"
	dec := map[string]string{"tok1": "1234567890123456"}

	failed := 0
	lines := g.GetFeeAndTxnData(nil, []*VisaAcqTxnWorkEntity{e1, e2}, dec, "10087096", 1, func(arn string) {
		if arn == "99999999999999999999999" {
			failed++
		}
	})
	if failed != 1 {
		t.Errorf("updateFailed called %d times, want 1", failed)
	}
	// Only e1's 4 records plus 91/92 footers (e2 skipped).
	if len(lines) != 6 {
		t.Fatalf("lines = %d, want 6", len(lines))
	}
	if !strings.HasPrefix(lines[0], "050") {
		t.Errorf("first line = %q", lines[0][:3])
	}
}

func TestGetFeeAndTxnDataBatchBoundary(t *testing.T) {
	g := NewBaseIIGenerator("USD000")
	dec := map[string]string{"tok1": "1234567890123456"}
	// 813 txns * 4 records = 3252 > 3250 -> triggers an in-loop footer 91.
	txns := make([]*VisaAcqTxnWorkEntity, 0, 813)
	for i := 0; i < 813; i++ {
		txns = append(txns, visatEntity())
	}
	lines := g.GetFeeAndTxnData(nil, txns, dec, "10087096", 1, nil)
	// 3252 tcr records + 1 in-loop footer 91 + footer 92. The final
	// "if txnCount91 > 0" footer is skipped because the in-loop footer 91
	// resets txnCount91 to 0 (same as Java).
	if len(lines) != 3252+2 {
		t.Fatalf("lines = %d, want %d", len(lines), 3252+2)
	}
	if !strings.HasPrefix(lines[3252], "91") {
		t.Errorf("line 3252 = %q, want 91", lines[3252][:2])
	}
	if !strings.HasPrefix(lines[3253], "92") {
		t.Errorf("line 3253 = %q, want 92", lines[3253][:2])
	}
}

func TestGetFeeAndTxnDataNilLists(t *testing.T) {
	g := NewBaseIIGenerator("USD000")
	if lines := g.GetFeeAndTxnData(nil, nil, nil, "10087096", 1, nil); lines != nil {
		t.Errorf("nil lists should return nil lines, got %v", lines)
	}
	if lines := g.GetFeeAndTxnData([]*VisaAcqTxnWorkEntity{}, []*VisaAcqTxnWorkEntity{}, map[string]string{}, "10087096", 1, nil); len(lines) != 1 {
		t.Fatalf("empty lists should yield only footer92, got %d lines", len(lines))
	}
}

func TestGetAuthorizationCharInd(t *testing.T) {
	g := NewBaseIIGenerator("USD000")
	cases := []struct {
		auth, ecom, want string
	}{
		{"U", "", "U"},
		{" ", "", "E"},
		{"", "5", "U"},
		{"", "6", "S"},
		{"", "7", "W"},
		{"", " ", "E"},
	}
	for _, c := range cases {
		if got := g.getAuthorizationCharInd(c.auth, c.ecom); got != c.want {
			t.Errorf("auth=%q ecom=%q => %q, want %q", c.auth, c.ecom, got, c.want)
		}
	}
}
