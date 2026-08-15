package tlfsvc

import (
	"strings"
	"time"
)

// MercuryWorkEntity maps to MERCURY_ACQ_TXN_WORK, the staging table consumed by
// the Mercury outgoing file generator. Column names mirror the Java
// MercuryAcqTxnWorkEntity.
type MercuryWorkEntity struct {
	SerNumber              int
	LastUpdated            time.Time
	UpdatedUser            int
	InstitutionCode        int
	IntCode                int
	PrjSerNumber           int
	GeneralStatus          int
	TxnRefNumber           int
	Rrn                    string
	MerchantId             string
	TerminalId             string
	TxnType                string
	CardNumber             string
	TxnAmount              float64
	SurchargeAmount        float64
	LocalDateTime          *time.Time
	TxnDate                *time.Time
	ChargeType             string
	TypeOfCharge           string
	GeoArea                string
	MeName                 string
	MeCity                 string
	MeCountry              string
	CardAccepStreetAddress string
	CardAccepStateCode     string
	MePinCode              string
	EstPhoneNumber         string
	Mcc                    string
	CardType               string
	ApprovalCode           string
	TxnCurrencyExponent    int
	TxnCurCode             string
	MercuryRefId           string
	CardDomIntlFlag        string
	DmsSmsMode             string
	EncryptedCardNumber    string
	OrgInstIdCode          string
	TrlType                string
	SettlementIndicator    string
	TxnFeeAmount           float64
	MotoEcomIndicator      string
	ResponseCode           string
	AcqinstIdCode          string
	AcqRefData             string
	CardInputMode          string
	CardInputCapability    string
	CardSeqNumber          string
	AppICProfile           string
	AppTxnCounter          string
	AppCryptogram          string
	CryptAmount            float64
	CashBackAmount         *float64
	CryptInfoData          string
	CvmResult              string
	DedicatedFileName      string
	IfdSerNumber           string
	IssAppData             string
	IssAuthData            string
	TrlConCode             string
	TrlAppVerNumber        string
	ChipTrlCapabilities    string
	ChipTrlType            string
	TrlVerResult           string
	ChipTxnDate            string
	ChipTxnType            string
	ChipCurCode            string
	UpblNumber             string
	CentreProcDate         *time.Time
	FileProcDate           *time.Time
	FileID                 string
	CardPresent            string
	ChPresent              string
	PanSequenceNumber      string
	PosEntryMode           string
}

// MercurySplitParams carries the resolved inputs for MapToMercuryAcqTxnEntity:
// the mapped POS entity, the raw payload (street address + token), the job
// context, and the reference-data lookups performed by the store.
type MercurySplitParams struct {
	Entity           *Entity
	Payload          *SwitchExtractVo
	UserSerialNumber int
	InsCode          int
	IntCode          int
	JobNumber        int
	CurrencyExponent int
	McIcaNum         string
	AcqBin           string
	Now              time.Time
}

// MapToMercuryAcqTxnEntity mirrors MercurySplitService.mapToMercuryAcqTxnEntity.
// Reference lookups (INTERFACES 'MERCURY', ACQUIRER_BINS 'E', CURRENCIES) are
// resolved by the caller and passed in; ""/0 signify "not found".
func MapToMercuryAcqTxnEntity(p MercurySplitParams) *MercuryWorkEntity {
	pos := p.Entity
	m := &MercuryWorkEntity{
		LastUpdated:            p.Now,
		UpdatedUser:            p.UserSerialNumber,
		InstitutionCode:        p.InsCode,
		IntCode:                p.IntCode,
		PrjSerNumber:           p.JobNumber,
		GeneralStatus:          3,
		TxnRefNumber:           pos.SerialNumber,
		Rrn:                    pos.Rrn,
		MerchantId:             pos.MerchantId,
		TerminalId:             pos.TerminalId,
		TxnType:                pos.TxnCode,
		CardNumber:             pos.CardNumber,
		TxnAmount:              pos.TxnAmount,
		SurchargeAmount:        0.0,
		LocalDateTime:          pos.LocalDateTime,
		ChargeType:             MercuryChargeTypeCode(pos.Mcc),
		TypeOfCharge:           MercuryTypeOfCharge(pos.PosEntryMode),
		GeoArea:                MercuryGeoArea(pos.MeCountry),
		TxnDate:                dateOnly(pos.TxnDateTime),
		CardInputMode:          MercuryCardInputMode(pos.PosEntryMode),
		CardInputCapability:    MercuryCardInputCapability(pos.PosEntryMode),
		MeName:                 pos.MeName,
		MeCity:                 pos.MeCity,
		MeCountry:              pos.MeCountry,
		CardAccepStreetAddress: cardAccepStreetAddress(p.Payload),
		CardAccepStateCode:     pos.CardAccStateCode,
		MePinCode:              pos.MePinCode,
		EstPhoneNumber:         truncate20(pos.MerchantContactInfo),
		Mcc:                    pos.Mcc,
		CardType:               firstCharStr(pos.CardType),
		ApprovalCode:           derefStr(pos.ApprovalCode),
		TxnCurCode:             pos.TxnCurCode,
		CardDomIntlFlag:        firstCharStr(pos.CardDomIntlFlag),
		DmsSmsMode:             firstCharStr(pos.DmsSmsMode),
		EncryptedCardNumber:    tokenIdentifier(p.Payload),
		TrlType:                mercuryTrlType(pos),
		SettlementIndicator:    mercurySettlementIndicator(pos.OnusOffusFlag),
		TxnFeeAmount:           pos.TxnFeeAmount,
		MotoEcomIndicator:      mercuryMotoEcom(pos),
		ResponseCode:           pos.ResponseCode,
		CardSeqNumber:          pos.CardSeqNumber,
		AppICProfile:           pos.AppICProfile,
		AppTxnCounter:          pos.AppTxnCounter,
		AppCryptogram:          pos.AppCryptogram,
		CryptAmount:            pos.CryptAmount,
		CryptInfoData:          pos.CryptInfoData,
		CvmResult:              pos.CvmResult,
		DedicatedFileName:      pos.DedicatedFileName,
		IfdSerNumber:           pos.IfdSerNumber,
		IssAppData:             pos.IssAppData,
		IssAuthData:            pos.IssAuthData,
		TrlConCode:             pos.TrlConCode,
		TrlAppVerNumber:        pos.TrlAppVerNumber,
		ChipTrlCapabilities:    pos.ChipTrlCapabilities,
		ChipTrlType:            pos.ChipTrlType,
		TrlVerResult:           pos.TrlVerResult,
		ChipTxnDate:            derefStr(pos.ChipTxnDate),
		ChipTxnType:            pos.ChipTxnType,
		ChipCurCode:            pos.ChipCurCode,
		UpblNumber:             pos.UpblNumber,
		ChPresent:              firstCharStr(pos.ChPresent),
		PosEntryMode:           pos.PosEntryMode,
	}

	if pos.TxnCurCode != "" {
		m.TxnCurrencyExponent = p.CurrencyExponent
	}

	if p.McIcaNum != "" {
		ica := leftPad(p.McIcaNum, 6)
		m.AcqinstIdCode = ica
		m.OrgInstIdCode = ica
		if p.AcqBin != "" {
			m.AcqRefData = MercuryAcqRefData(p.AcqBin, pos.Rrn, p.Now)
		}
	}

	// cashBackAmount: txn code 09 -> POS cash-back; else chip cash-back if a
	// cryptogram is present.
	if pos.TxnCode == "09" {
		m.CashBackAmount = pos.CashBackAmount
	} else if pos.AppCryptogram != "" {
		m.CashBackAmount = floatPtr(pos.ChipCashBack)
	}

	return m
}

func mercuryTrlType(pos *Entity) string {
	if strings.EqualFold(pos.TerminalType, "MPOS") {
		return "CT9"
	}
	if strings.HasPrefix(pos.PosEntryMode, "81") {
		return "CT6"
	}
	if firstCharStr(pos.ChPresent) == "5" && firstCharStr(pos.CardInputMode) == "7" {
		return "CT6"
	}
	return "POI"
}

func mercurySettlementIndicator(onusOffus string) string {
	if firstCharStr(onusOffus) == "O" {
		return "C"
	}
	return "M"
}

func mercuryMotoEcom(pos *Entity) string {
	if strings.HasPrefix(pos.PosEntryMode, "81") && pos.MotoEcomIndicator != "" {
		return "21" + lastChar(pos.MotoEcomIndicator)
	}
	return ""
}

func cardAccepStreetAddress(p *SwitchExtractVo) string {
	if p == nil {
		return ""
	}
	return p.CardAcceptorStreetAddress
}

func tokenIdentifier(p *SwitchExtractVo) string {
	if p == nil {
		return ""
	}
	return p.TokenIdentifier
}

func truncate20(s string) string {
	if s == "" {
		return ""
	}
	if len(s) > 20 {
		return s[:20]
	}
	return s
}

func leftPad(s string, n int) string {
	if len(s) >= n {
		return s
	}
	return strings.Repeat("0", n-len(s)) + s
}

func lastChar(s string) string {
	if s == "" {
		return ""
	}
	return s[len(s)-1:]
}

func dateOnly(t *time.Time) *time.Time {
	if t == nil {
		return nil
	}
	d := time.Date(t.Year(), t.Month(), t.Day(), 0, 0, 0, 0, t.Location())
	return &d
}

func floatPtr(f float64) *float64 { return &f }
