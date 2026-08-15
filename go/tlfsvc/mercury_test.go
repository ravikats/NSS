package tlfsvc

import (
	"context"
	"log/slog"
	"strings"
	"testing"
	"time"
)

func TestMercuryChargeTypeCode(t *testing.T) {
	cases := map[string]string{
		"":     "999",
		"  ":   "999",
		"abc":  "999",
		"9311": "181",
		"9222": "182",
		"8043": "309",
		"4814": "860",
		"4816": "920",
		"3501": "100",
		"3999": "100",
		"7531": "410",
		"7538": "410",
		"3000": "500",
		"3299": "500",
		"8011": "110",
		"8099": "110",
		"8211": "120",
		"8299": "120",
		"7210": "130",
		"7217": "130",
		"4899": "150",
		"4900": "150",
		"6513": "160",
		"9211": "180",
		"9405": "180",
		"4784": "183",
		"7523": "183",
		"5812": "200",
		"5811": "201",
		"5813": "210",
		"7832": "211",
		"7929": "211",
		"5941": "302",
		"5942": "303",
		"5921": "305",
		"5193": "306",
		"7230": "307",
		"5912": "314",
		"5411": "315",
		"5094": "320",
		"5621": "330",
		"5732": "340",
		"7512": "400",
		"4121": "420",
		"4511": "500",
		"4722": "600",
		"4468": "610",
		"4411": "620",
		"4112": "630",
		"5541": "700",
		"5542": "710",
		"7995": "850",
		"5960": "900",
		"5969": "910",
		"8111": "930",
		"8931": "940",
		"7393": "950",
		"7361": "960",
		"7392": "970",
		"1520": "980",
		"1740": "980",
		"9999": "999",
	}
	for in, want := range cases {
		if got := MercuryChargeTypeCode(in); got != want {
			t.Errorf("MercuryChargeTypeCode(%q) = %q, want %q", in, got, want)
		}
	}
}

func TestMercuryPosUtil(t *testing.T) {
	if got := MercuryTypeOfCharge(""); got != "TE" {
		t.Errorf("getTYPCH('') = %q, want TE", got)
	}
	if got := MercuryTypeOfCharge("ECOM"); got != "TI" {
		t.Errorf("getTYPCH(ECOM) = %q, want TI", got)
	}
	if got := MercuryTypeOfCharge("051"); got != "TK" {
		t.Errorf("getTYPCH(051) = %q, want TK", got)
	}
	if got := MercuryTypeOfCharge("021"); got != "TE" {
		t.Errorf("getTYPCH(021) = %q, want TE", got)
	}
	if got := MercuryCardInputMode("051"); got != "5" {
		t.Errorf("getCPTRM(051) = %q, want 5", got)
	}
	if got := MercuryCardInputMode("071"); got != "U" {
		t.Errorf("getCPTRM(071) = %q, want U", got)
	}
	if got := MercuryCardInputMode("801"); got != "9" {
		t.Errorf("getCPTRM(801) = %q, want 9", got)
	}
	if got := MercuryCardInputMode("901"); got != "2" {
		t.Errorf("getCPTRM(901) = %q, want 2", got)
	}
	if got := MercuryCardInputMode("012"); got != "1" {
		t.Errorf("getCPTRM(012) = %q, want 1", got)
	}
	if got := MercuryCardInputMode("0710"); got != "U" {
		t.Errorf("getCPTRM(0710) = %q, want U (truncates to 071)", got)
	}
	if got := MercuryCardInputCapability("071"); got != "8" {
		t.Errorf("getCRDINP(071) = %q, want 8", got)
	}
	if got := MercuryCardInputCapability(""); got != "1" {
		t.Errorf("getCRDINP('') = %q, want 1", got)
	}
}

func TestMercuryGeoArea(t *testing.T) {
	if got := MercuryGeoArea("AED"); got != "784" {
		t.Errorf("getGEO(AED) = %q, want 784", got)
	}
	if got := MercuryGeoArea("USD"); got != "840" {
		t.Errorf("getGEO(USD) = %q, want 840", got)
	}
	if got := MercuryGeoArea("EUR"); got != "978" {
		t.Errorf("getGEO(EUR) = %q, want 978", got)
	}
	if got := MercuryGeoArea("GBP"); got != "784" {
		t.Errorf("getGEO(GBP) = %q, want 784 (default)", got)
	}
	if got := MercuryGeoArea(""); got != "" {
		t.Errorf("getGEO('') = %q, want ''", got)
	}
}

func TestJulianYDDD(t *testing.T) {
	// 2026-08-14 = day-of-year 226.
	d := time.Date(2026, 8, 14, 12, 0, 0, 0, time.UTC)
	if got := JulianYDDD(d); got != "6226" {
		t.Errorf("JulianYDDD = %q, want 6226", got)
	}
	// 2026-01-05 = day-of-year 5, must zero-pad to 3 digits.
	d2 := time.Date(2026, 1, 5, 12, 0, 0, 0, time.UTC)
	if got := JulianYDDD(d2); got != "6005" {
		t.Errorf("JulianYDDD = %q, want 6005", got)
	}
}

func TestAddCheckDigit(t *testing.T) {
	// Hand-computed Luhn-style check for a sample ARN.
	base := "29709626226"
	got := AddCheckDigit(base)
	if !strings.HasPrefix(got, base) || len(got) != len(base)+1 {
		t.Fatalf("AddCheckDigit(%q) = %q, want base + 1 check digit", base, got)
	}
}

func TestMercuryAcqRefData(t *testing.T) {
	now := time.Date(2026, 8, 14, 12, 0, 0, 0, time.UTC)
	got := MercuryAcqRefData("970962", "621007888375", now)
	// "2" + 970962 + 6226 + last 11 of rrn ("21007888375") + check digit.
	want := "2970962622621007888375"
	if !strings.HasPrefix(got, want) || len(got) != len(want)+1 {
		t.Fatalf("MercuryAcqRefData = %q, want prefix %q + check digit", got, want)
	}
}

func TestMapToMercuryAcqTxnEntity(t *testing.T) {
	now := time.Date(2026, 8, 14, 12, 30, 0, 0, time.UTC)
	txnDT := time.Date(2026, 8, 14, 10, 0, 0, 0, time.UTC)
	locDT := time.Date(2026, 8, 14, 10, 5, 0, 0, time.UTC)
	cb := 12.5

	e := &Entity{}
	e.SerialNumber = 12345
	e.Rrn = "621007888375"
	e.MerchantId = "MERCH001"
	e.TerminalId = "TERM001"
	e.TxnCode = "09"
	e.CardNumber = "6690109700100010"
	e.TxnAmount = 95.1
	e.LocalDateTime = &locDT
	e.TxnDateTime = &txnDT
	e.PosEntryMode = "071"
	e.MeCountry = "USD"
	e.MeName = "VAULTSPAY"
	e.MeCity = "Dubai"
	e.CardAccStateCode = "DXB"
	e.MePinCode = "00000"
	e.MerchantContactInfo = "0501234567"
	e.Mcc = "9211"
	e.CardType = "D"
	e.CardDomIntlFlag = "D"
	e.DmsSmsMode = "D"
	e.OnusOffusFlag = "O"
	e.TxnFeeAmount = 0.5
	e.ResponseCode = "00"
	e.TxnCurCode = "840"
	e.TerminalType = "POS"
	e.CashBackAmount = &cb
	e.AppCryptogram = ""

	pay := &SwitchExtractVo{
		CardAcceptorStreetAddress: "Street 1",
		TokenIdentifier:           "tok-123",
	}

	m := MapToMercuryAcqTxnEntity(MercurySplitParams{
		Entity:           e,
		Payload:          pay,
		UserSerialNumber: 4,
		InsCode:          1,
		IntCode:          21,
		JobNumber:        1,
		CurrencyExponent: 2,
		McIcaNum:         "034540",
		AcqBin:           "970962",
		Now:              now,
	})

	if m.GeneralStatus != 3 {
		t.Errorf("genStatus = %d, want 3", m.GeneralStatus)
	}
	if m.TxnRefNumber != 12345 {
		t.Errorf("txnRef = %d, want 12345", m.TxnRefNumber)
	}
	if m.ChargeType != "180" {
		t.Errorf("chargeType = %q, want 180 (mcc 9211)", m.ChargeType)
	}
	if m.TypeOfCharge != "TK" {
		t.Errorf("typeOfCharge = %q, want TK (pos 071)", m.TypeOfCharge)
	}
	if m.GeoArea != "840" {
		t.Errorf("geoArea = %q, want 840 (USD)", m.GeoArea)
	}
	if m.CardInputMode != "U" {
		t.Errorf("cardInputMode = %q, want U (pos 071)", m.CardInputMode)
	}
	if m.CardInputCapability != "8" {
		t.Errorf("cardInputCapability = %q, want 8 (pos 071)", m.CardInputCapability)
	}
	if m.TrlType != "POI" {
		t.Errorf("trlType = %q, want POI (POS terminal, not MPOS/81)", m.TrlType)
	}
	if m.SettlementIndicator != "C" {
		t.Errorf("settlementIndicator = %q, want C (onus)", m.SettlementIndicator)
	}
	if m.TxnCurrencyExponent != 2 {
		t.Errorf("currencyExponent = %d, want 2", m.TxnCurrencyExponent)
	}
	if m.AcqinstIdCode != "034540" {
		t.Errorf("acqinstIdCode = %q, want 034540", m.AcqinstIdCode)
	}
	if m.OrgInstIdCode != "034540" {
		t.Errorf("orgInstIdCode = %q, want 034540", m.OrgInstIdCode)
	}
	if m.AcqRefData == "" || !strings.HasPrefix(m.AcqRefData, "29709626226") {
		t.Errorf("acqRefData = %q, want 2+bin+julian prefix", m.AcqRefData)
	}
	if m.CardAccepStreetAddress != "Street 1" {
		t.Errorf("street = %q, want Street 1", m.CardAccepStreetAddress)
	}
	if m.EncryptedCardNumber != "tok-123" {
		t.Errorf("encCard = %q, want tok-123", m.EncryptedCardNumber)
	}
	if m.CardType != "D" || m.CardDomIntlFlag != "D" || m.DmsSmsMode != "D" {
		t.Errorf("char fields = %q/%q/%q, want D/D/D", m.CardType, m.CardDomIntlFlag, m.DmsSmsMode)
	}
	if m.CashBackAmount == nil || *m.CashBackAmount != 12.5 {
		t.Errorf("cashBackAmount = %v, want 12.5 (txnCode 09 path via CashBackAmount)", m.CashBackAmount)
	}
	if m.TxnDate == nil || m.TxnDate.Year() != txnDT.Year() || m.TxnDate.Month() != txnDT.Month() || m.TxnDate.Day() != txnDT.Day() {
		t.Errorf("txnDate = %v, want date of %v", m.TxnDate, txnDT)
	}
}

func TestMapToMercuryTrlTypeMPOS(t *testing.T) {
	e := &Entity{}
	e.TerminalType = "MPOS"
	if got := mercuryTrlType(e); got != "CT9" {
		t.Errorf("MPOS trlType = %q, want CT9", got)
	}
	e = &Entity{}
	e.TerminalType = "POS"
	e.PosEntryMode = "812"
	if got := mercuryTrlType(e); got != "CT6" {
		t.Errorf("posEntryMode 81x trlType = %q, want CT6", got)
	}
	e = &Entity{}
	e.TerminalType = "POS"
	e.PosEntryMode = "012"
	e.ChPresent = "5"
	e.CardInputMode = "7"
	if got := mercuryTrlType(e); got != "CT6" {
		t.Errorf("chPresent=5 + cardInputMode=7 trlType = %q, want CT6", got)
	}
}

func TestMapToMercuryChipCashBack(t *testing.T) {
	e := &Entity{}
	e.TxnCode = "00"
	e.AppCryptogram = "AB12"
	e.ChipCashBack = 7.5
	m := MapToMercuryAcqTxnEntity(MercurySplitParams{Entity: e, Now: time.Now()})
	if m.CashBackAmount == nil || *m.CashBackAmount != 7.5 {
		t.Errorf("cashBackAmount = %v, want 7.5 (chip cash-back)", m.CashBackAmount)
	}
}

func TestStageMercuryWire(t *testing.T) {
	now := time.Date(2026, 8, 14, 12, 30, 0, 0, time.UTC)
	fs := &fakeStore{serial: 42}
	svc := &Service{
		Store:     fs,
		IrfClient: tlfStubIrfClient{},
		Log:       slog.Default(),
		Cfg: Config{
			InsCode: 1, IntCode: 11, UserSerNumber: 4, JobNumber: 1,
			ExchangeRate: 0.27, StageMercury: true, Now: func() time.Time { return now },
		},
	}

	vo := &RequestVo{Payload: mercuryPayload()}
	_, w, err := svc.Stage1(context.Background(), vo)
	if err != nil {
		t.Fatalf("Stage1: %v", err)
	}
	if err := svc.Stage2(context.Background(), w); err != nil {
		t.Fatalf("Stage2: %v", err)
	}
	if !fs.mercuryInserted {
		t.Errorf("mercury not staged; want InsertMercuryWork called for outgoing MERCURY txn")
	}
}

func TestStageMercuryDisabled(t *testing.T) {
	fs := &fakeStore{serial: 42}
	svc := &Service{
		Store:     fs,
		IrfClient: tlfStubIrfClient{},
		Log:       slog.Default(),
		Cfg: Config{
			InsCode: 1, IntCode: 11, UserSerNumber: 4, JobNumber: 1,
			ExchangeRate: 0.27, Now: func() time.Time { return time.Now() },
		},
	}
	vo := &RequestVo{Payload: mercuryPayload()}
	_, w, err := svc.Stage1(context.Background(), vo)
	if err != nil {
		t.Fatalf("Stage1: %v", err)
	}
	if err := svc.Stage2(context.Background(), w); err != nil {
		t.Fatalf("Stage2: %v", err)
	}
	if fs.mercuryInserted {
		t.Errorf("mercury staged despite StageMercury=false")
	}
}

func mercuryPayload() *SwitchExtractVo {
	return &SwitchExtractVo{
		BankCode:                  "CPBA",
		Network:                   "mercury",
		Scheme:                    "MERCURY",
		Mti:                       "0130",
		CardNumber:                "6690109700100010",
		TokenIdentifier:           "tok-123",
		ProcessCode:               "000000",
		AmountTransaction:         "000000095100",
		TxnDateTime:               "0814103000",
		Stan:                      "888375",
		LocalTxnTime:              "103000",
		LocalTxnDate:              "0814",
		ExpiryDate:                "2812",
		SettlementDate:            "20260814",
		Mcc:                       "9211",
		PosEntryMode:              "0710",
		PanSequence:               "0",
		PosCode:                   "00",
		TxnFeeAmount:              "0.00",
		RetRefNumber:              "621007888375",
		AuthIdResponse:            "962981",
		ResponseCode:              "00",
		TerminalId:                "TERM001",
		MerchantId:                "MERCH001",
		CardAcceptorName:          "VAULTSPAY",
		CardAcceptorStreetAddress: "Street 1",
		CardAcceptorCity:          "Dubai",
		CardAcceptorPinCode:       "00000",
		CardAcceptorCountryCode:   "840",
		TxnCurrencyCode:           "840",
		SettleCurrencyCode:        "840",
		CashBackAmount:            "0.00",
		TxnSource:                 "POS",
		SettlementIndicator:       "Y",
		OnusOffusIndicator:        "ONUS",
		SmsDmsIndicator:           "DMS",
	}
}
