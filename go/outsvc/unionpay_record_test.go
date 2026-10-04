package outsvc

import (
	"math/big"
	"strings"
	"testing"
	"time"
)

// These tests pin the byte layout of the UnionPay settlement record
// (Part III "Technical Specifications on Bankcard Interoperability", section 4
// Settlement File). Field positions are 1-based and inclusive, matching the
// specification tables, so a substring of the built record is taken with
// [start-1:end].

func upBlock0(t *testing.T, txn *UnionPayAcqTxnWorkEntity, pan, tc, bitmap string) string {
	t.Helper()
	b := unionPayBlock0(txn, pan, tc, bitmap, big.NewRat(1, 1))
	if len(b) != 269 {
		t.Fatalf("Block 0 length = %d, want 269", len(b))
	}
	return b
}

// pos returns the inclusive 1-based positions [start,end] of a record.
func pos(rec string, start, end int) string {
	if end > len(rec) {
		return rec[start-1:]
	}
	return rec[start-1 : end]
}

func TestUnionPayBlock0TransactionCodeIsRefundAware(t *testing.T) {
	sale := unionpayEntity()
	if got := pos(upBlock0(t, sale, "6212345678901234", unionPayTC100, unionPayBlock012Bitmap), 1, 3); got != "100" {
		t.Errorf("sale transaction code = %q, want 100", got)
	}
	refund := unionpayEntity()
	refund.TxnType = "20"
	if got := pos(upBlock0(t, refund, "6212345678901234", unionPayTC101, unionPayBlock01Bitmap), 1, 3); got != "101" {
		t.Errorf("refund transaction code = %q, want 101", got)
	}
}

// Block 0 positions 240-269, Part III Table 36 Note a. The regression this
// guards: the field used to be built from MeCountry (an alpha code such as
// "AE"/"ARE") in a NUMERIC slot, and the original-authorization-type was
// hardcoded "100" even on a refund.
func TestUnionPayOtherInformationSubFields(t *testing.T) {
	txn := unionpayEntity()
	txn.PosConditionCode = "00"
	txn.AcqInstCountryCode = "784"
	txn.PricingSchemeCode = "00"

	got := unionPayOtherInformation(txn)
	if len(got) != 30 {
		t.Fatalf("other information length = %d, want 30", len(got))
	}
	// Annotated sub-field assertions.
	for _, c := range []struct {
		name       string
		start, end int
		want       string
	}{
		{"240-241 installment terms (incoming only)", 1, 2, "  "},
		{"242 stand-in authorization (incoming only)", 3, 3, " "},
		{"243-244 POS condition code", 4, 5, "00"},
		{"245-247 merchant country (NUMERIC, not alpha)", 6, 8, "784"},
		{"248 transaction initiation method", 9, 9, "1"},
		{"249-251 original auth type on a sale", 10, 12, "100"},
		{"252 card level (incoming only)", 13, 13, " "},
		{"253-254 pricing scheme", 14, 15, "00"},
		{"255-257 special currency (incoming only)", 16, 18, "   "},
		{"258-259 ECI defaults to 00 when blank", 19, 20, "00"},
		{"260-261 card product (incoming only)", 21, 22, "  "},
		{"262-263 account attribute (incoming only)", 23, 24, "  "},
		{"264 UPI indicator (incoming only)", 25, 25, " "},
		{"265-266 B2B type (incoming only)", 26, 27, "  "},
		{"267 B2B medium (incoming only)", 28, 28, " "},
		{"268-269 special pricing (incoming only)", 29, 30, "  "},
	} {
		if sub := pos(got, c.start, c.end); sub != c.want {
			t.Errorf("other information %s = %q, want %q", c.name, sub, c.want)
		}
	}

	// A refund carries spaces, not 100, in the original auth type slot.
	refund := unionpayEntity()
	refund.TxnType = "20"
	if sub := pos(unionPayOtherInformation(refund), 10, 12); sub != "   " {
		t.Errorf("refund original auth type = %q, want three spaces", sub)
	}

	// Merchant country must be numeric; an alpha value must not be written
	// into the n3 slot.
	alpha := unionpayEntity()
	alpha.AcqInstCountryCode = ""
	alpha.PricingSchemeCode = ""
	if sub := pos(unionPayOtherInformation(alpha), 6, 8); sub != "784" {
		t.Errorf("merchant country default = %q, want 784", sub)
	}

	// A populated ECI is carried through.
	eci := unionpayEntity()
	eci.ECI = "05"
	if sub := pos(unionPayOtherInformation(eci), 19, 20); sub != "05" {
		t.Errorf("ECI = %q, want 05", sub)
	}
}

// Block 0 positions 129-168 (ans40, ISO Field 43) = name(25) + city(12) +
// alpha-2 country(3). The alpha-3 country code must be narrowed; a naive
// 40-char truncation produces "AR".
func TestUnionPayMerchantNameNarrowsAlpha3Country(t *testing.T) {
	txn := unionpayEntity()
	txn.MeName = "TEST MERCHANT"
	txn.MeCity = "DUBAI"
	txn.MeCountry = "ARE"
	got := unionPayMerchantName(txn)
	if len(got) != 40 {
		t.Fatalf("merchant name length = %d, want 40", len(got))
	}
	if want := "TEST MERCHANT" + strings.Repeat(" ", 25-13); got[:25] != want {
		t.Errorf("merchant name field 1 = %q, want %q", got[:25], want)
	}
	if want := "DUBAI" + strings.Repeat(" ", 12-5); got[25:37] != want {
		t.Errorf("merchant name field 2 = %q, want %q", got[25:37], want)
	}
	if got[37:] != "AE " {
		t.Errorf("merchant country = %q, want %q (alpha-3 ARE must be narrowed)", got[37:], "AE ")
	}

	// An unknown alpha-3 leaves the country blank rather than guessing.
	unknown := unionpayEntity()
	unknown.MeCountry = "ZZZ"
	if got := unionPayMerchantName(unknown)[37:]; got != "   " {
		t.Errorf("unknown country = %q, want three spaces", got)
	}
	// An alpha-2 input passes through unchanged.
	already := unionpayEntity()
	already.MeCountry = "AE"
	if got := unionPayMerchantName(already)[37:]; got != "AE " {
		t.Errorf("alpha-2 passthrough = %q, want %q", got, "AE ")
	}
}

// Block 1 is entirely incoming-direction data, so per Part III 2.7 it must
// carry defaults. The regression this guards: the builder used to repeat the
// transaction amount and invent conversion rates (20000100 / 30001000).
func TestUnionPayBlock1UsesOutgoingDefaults(t *testing.T) {
	b := unionPayBlock1(unionpayEntity(), big.NewRat(1, 1))
	if len(b) != 118 {
		t.Fatalf("Block 1 length = %d, want 118", len(b))
	}
	for _, c := range []struct {
		name       string
		start, end int
		want       string
	}{
		{"7-18 amount, settlement", 7, 18, "000000000000"},
		{"19-21 currency, settlement", 19, 21, "   "},
		{"22-29 conversion rate, settlement", 22, 29, "00000000"},
		{"30-41 amount, cardholder billing", 30, 41, "000000000000"},
		{"42-44 currency, cardholder billing", 42, 44, "   "},
		{"45-52 conversion rate, cardholder billing", 45, 52, "00000000"},
		{"53-64 net fee amount", 53, 64, "D00000000000"},
		{"65-67 IRF billing currency", 65, 67, "000"},
		{"68-75 exchange rate RF->settlement", 68, 75, "00000000"},
		{"76-78 international organization", 76, 78, "   "},
		{"79 Mainland China indicator", 79, 79, " "},
		{"80-91 amount, transaction fee", 80, 91, "D00000000000"},
	} {
		if sub := pos(b, c.start, c.end); sub != c.want {
			t.Errorf("Block 1 %s = %q, want %q", c.name, sub, c.want)
		}
	}
	if strings.Contains(b, "20000100") || strings.Contains(b, "30001000") {
		t.Error("Block 1 still emits an invented conversion rate")
	}
}

// Block 2 positions 182-183 carry the first two digits of the processing code
// (Tag 9C). The regression this guards: it was emitting ChipTxnType instead.
func TestUnionPayBlock2TransactionCategoryIsProcessingCodePrefix(t *testing.T) {
	txn := unionpayEntity()
	txn.PosEntryMode = "05"
	txn.ChipTxnType = "01" // chip txn type -- must NOT land here
	txn.TxnType = "00"
	txn.CryptAmount = 100
	txn.ChipCurCode = "784"
	b := unionPayBlock2(txn, big.NewRat(1, 1))
	if len(b) != 294 {
		t.Fatalf("Block 2 length = %d, want 294", len(b))
	}
	if got := pos(b, 182, 183); got != "00" {
		t.Errorf("Block 2 transaction category = %q, want 00 (processing code prefix)", got)
	}
	txn.TxnType = "20"
	if got := pos(unionPayBlock2(txn, big.NewRat(1, 1)), 182, 183); got != "20" {
		t.Errorf("Block 2 transaction category for a refund = %q, want 20", got)
	}
}

// Record lengths follow directly from the block lengths: a magstripe record is
// Block 0 + Block 1 (387), a chip record adds Block 2 (681). A refund is
// always 387 -- Block 2 is absent.
func TestUnionPayRecordLengthsAndBitmap(t *testing.T) {
	dec := map[string]string{"tok1": "6212345678901234"}

	mag := unionpayEntity()
	mag.PosEntryMode = "01"
	mag.CardInputMode = "01"
	rec := unionPayTxnRecord(mag, dec, big.NewRat(1, 1))
	if len(rec) != 387 {
		t.Fatalf("magstripe record length = %d, want 387", len(rec))
	}
	if got := pos(rec, 1, 3); got != "100" {
		t.Errorf("magstripe transaction code = %q, want 100", got)
	}
	if got := pos(rec, 4, 7); got != unionPayBlock01Bitmap {
		t.Errorf("magstripe bitmap = %q, want %q", got, unionPayBlock01Bitmap)
	}

	chip := unionpayEntity()
	chip.PosEntryMode = "05"
	rec = unionPayTxnRecord(chip, dec, big.NewRat(1, 1))
	if len(rec) != 681 {
		t.Fatalf("chip record length = %d, want 681", len(rec))
	}
	if got := pos(rec, 4, 7); got != unionPayBlock012Bitmap {
		t.Errorf("chip bitmap = %q, want %q", got, unionPayBlock012Bitmap)
	}

	// A refunded chip transaction drops Block 2.
	refund := unionpayEntity()
	refund.PosEntryMode = "05"
	refund.TxnType = "20"
	rec = unionPayTxnRecord(refund, dec, big.NewRat(1, 1))
	if len(rec) != 387 {
		t.Errorf("refund record length = %d, want 387 (no Block 2)", len(rec))
	}
	if got := pos(rec, 1, 3); got != "101" {
		t.Errorf("refund transaction code = %q, want 101", got)
	}
	if got := pos(rec, 4, 7); got != unionPayBlock01Bitmap {
		t.Errorf("refund bitmap = %q, want %q", got, unionPayBlock01Bitmap)
	}
	// Position 231 is the transaction feature indicator: 'R' on a refund.
	if got := pos(rec, 231, 231); got != "R" {
		t.Errorf("refund feature indicator = %q, want R", got)
	}
	// A sale leaves it blank.
	if got := pos(unionPayTxnRecord(chip, dec, big.NewRat(1, 1)), 231, 231); got != " " {
		t.Errorf("sale feature indicator = %q, want a space", got)
	}
}

// Header and trailer are fixed length regardless of the transaction mix.
func TestUnionPayHeaderTrailerLengths(t *testing.T) {
	h := unionPayTC000("24160784", time.Date(2026, 8, 15, 0, 0, 0, 0, time.UTC), "TEST")
	if len(h) != 46 {
		t.Errorf("header length = %d, want 46", len(h))
	}
	if pos(h, 1, 3) != "000" {
		t.Errorf("header transaction code = %q, want 000", pos(h, 1, 3))
	}
	tr := unionPayTC001(7)
	if len(tr) != 49 {
		t.Errorf("trailer length = %d, want 49", len(tr))
	}
	if pos(tr, 1, 3) != "001" {
		t.Errorf("trailer transaction code = %q, want 001", pos(tr, 1, 3))
	}
}
