package tlfsvc

import (
	"fmt"
	"strconv"
	"strings"
	"time"

	"empay/irf/irf"
	"empay/irf/mpgsdcf"
	"empay/irf/tlf"
)

// NetworkMapping mirrors TxnProcessingService.networkMapping: the incoming
// sub_route is combined with the sms/dms indicator to produce the canonical
// PTR_NETWORK value.
func NetworkMapping(smsDmsIndicator, subRoute string) string {
	if smsDmsIndicator == "" || subRoute == "" {
		return ""
	}
	indicator := strings.ToUpper(smsDmsIndicator)
	network := strings.ToLower(strings.TrimSpace(subRoute))
	switch indicator {
	case "SMS":
		switch network {
		case "mastercard":
			return "MDS"
		case "visa":
			return "VSMS"
		case "rupay":
			return "RSMS"
		case "amex":
			return "AMEX"
		case "uaeswitch":
			return "UAESWITCH"
		case "jaywan":
			return "JAYWAN"
		case "omannet_tps":
			return "OMANNET"
		case "onus":
			return "ONUS"
		case "mercury":
			return "MERCURY"
		}
	case "DMS", "RTS":
		switch network {
		case "mastercard":
			return "MCI"
		case "visa":
			return "VISA"
		case "amex":
			return "AMEX"
		case "uaeswitch":
			return "UAESWITCH"
		case "jaywan":
			return "JAYWAN"
		case "omannet_tps":
			return "OMANNET"
		case "onus":
			return "ONUS"
		case "mercury":
			return "MERCURY"
		}
	}
	return ""
}

// SchemeMapping mirrors TxnProcessingService.schemeMapping: MASTERCARD -> MCI,
// every other scheme upper-cased (JAYWAN, VISA, UAESWITCH, …).
func SchemeMapping(scheme string) string {
	if scheme == "" {
		return ""
	}
	s := strings.ToLower(strings.TrimSpace(scheme))
	if s == "mastercard" {
		return "MCI"
	}
	return strings.ToUpper(s)
}

// DmsSmsMode mirrors the Java posBuilder.dmsSmsMode mapping (S/D/R).
func DmsSmsMode(indicator string) string {
	switch strings.ToUpper(strings.TrimSpace(indicator)) {
	case "SMS":
		return "S"
	case "DMS":
		return "D"
	case "RTS":
		return "R"
	}
	return ""
}

// Divisor mirrors CurrencyUtil.getDivisor (3-decimal GCC currencies / 1000).
func Divisor(currencyCode string) int {
	switch currencyCode {
	case "048", "400", "414", "434", "512", "788",
		"BHD", "JOD", "KWD", "LYD", "OMR", "TND":
		return 1000
	}
	return 100
}

// ParseAmount mirrors the Java Double.valueOf / isNullOrEmpty helper.
func ParseAmount(s string) float64 {
	s = strings.TrimSpace(s)
	if s == "" {
		return 0
	}
	v, err := strconv.ParseFloat(s, 64)
	if err != nil {
		return 0
	}
	return v
}

// UpdateTxnDatetime mirrors TxnProcessingService.updateTxnDatetime:
// transmission_date is MMddhhmmss; the current year is prepended and shifted
// back a year if it lands in the future.
func UpdateTxnDatetime(dateTime string, now time.Time) *time.Time {
	if len(dateTime) < 4 {
		return nil
	}
	year := now.Year()
	txnDate := fmt.Sprintf("%d%s", year, dateTime[:4])
	if t, err := time.Parse("20060102", txnDate); err == nil && t.After(now) {
		year--
	}
	padded := fmt.Sprintf("%d%s", year, dateTime)
	if len(padded) > 14 {
		padded = padded[:14]
	}
	for len(padded) < 14 {
		padded += "0"
	}
	layout := padded[:8] + " " + padded[8:10] + ":" + padded[10:12] + ":" + padded[12:14]
	t, err := time.Parse("20060102 15:04:05", layout)
	if err != nil {
		return nil
	}
	return &t
}

// UpdateLocalDatetime mirrors TxnProcessingService.updateLocalDatetime:
// local_date is MMdd, local_time is hhmmss.
func UpdateLocalDatetime(date, ttime string, now time.Time) *time.Time {
	if len(date) < 4 || len(ttime) < 6 {
		return nil
	}
	year := now.Year()
	txnDate := fmt.Sprintf("%d%s", year, date)
	if d, err := time.Parse("20060102", txnDate); err == nil && d.After(now) {
		year--
	}
	padded := fmt.Sprintf("%d%s%s", year, date, ttime)
	if len(padded) > 14 {
		padded = padded[:14]
	}
	for len(padded) < 14 {
		padded += "0"
	}
	layout := padded[:8] + " " + padded[8:10] + ":" + padded[10:12] + ":" + padded[12:14]
	dt, err := time.Parse("20060102 15:04:05", layout)
	if err != nil {
		return nil
	}
	return &dt
}

// CardInputMode mirrors TxnProcessingService.cardInputMode (pos_data_code[0:2]).
func CardInputMode(posDataCode string) string {
	if len(posDataCode) < 2 {
		return ""
	}
	switch posDataCode[:2] {
	case "00":
		return "0"
	case "01":
		return "1"
	case "02":
		return "2"
	case "03", "04":
		return "0"
	case "05":
		return "C"
	case "07":
		return "M"
	case "08":
		return "N"
	case "09":
		return "R"
	case "79":
		return "6"
	case "80":
		return "B"
	case "81":
		return "S"
	case "82":
		return "T"
	case "90":
		return "B"
	case "91":
		return "A"
	case "92":
		return "N"
	case "95":
		return "C"
	case "10":
		return "7"
	}
	return "0"
}

// GetTxnCode mirrors TxnProcessingService.getTxnCode (scheme-aware MTI/proc rules).
func GetTxnCode(mti, procCode, scheme, mcc string) string {
	result := ""
	if len(procCode) >= 2 {
		result = procCode[:2]
	}
	if matchAny(scheme, "JAYWAN", "UAESWITCH", "ONUS", "MAAL", "MERCURY", "DISCOVER", "DINERS") {
		return result
	}
	switch mti {
	case "0110", "0210", "0130":
		switch {
		case strings.HasPrefix(procCode, "00"):
			if matchAny(scheme, "VISA", "VSMS") {
				return "05"
			}
			return "00"
		case strings.HasPrefix(procCode, "09"):
			return "09"
		case strings.HasPrefix(procCode, "20"):
			if matchAny(scheme, "MCI", "MDS") || matchAny(scheme, "AMEX") {
				return "20"
			}
			return "06"
		case strings.HasPrefix(procCode, "01"):
			if matchAny(scheme, "RUPAY", "RSMS") {
				if mcc == "6010" {
					return "07"
				}
				return "01"
			}
			if matchAny(scheme, "VISA", "VSMS") {
				return "07"
			}
		case strings.HasPrefix(procCode, "17") && matchAny(scheme, "MCI", "MDS"):
			return "12"
		case strings.HasPrefix(procCode, "71"):
			return "71"
		case strings.HasPrefix(procCode, "61"):
			return "61"
		case strings.HasPrefix(procCode, "62"):
			return "62"
		case strings.HasPrefix(procCode, "21"):
			return "21"
		}
	case "0400", "0410", "0420", "0430":
		switch {
		case strings.HasPrefix(procCode, "00"):
			if matchAny(scheme, "VISA", "VSMS") {
				return "25"
			}
			return "20"
		case strings.HasPrefix(procCode, "09"):
			return "09"
		case strings.HasPrefix(procCode, "20"):
			if matchAny(scheme, "MCI", "MDS") {
				return "20"
			}
			return "26"
		case strings.HasPrefix(procCode, "01"):
			if matchAny(scheme, "RUPAY", "RSMS") {
				if mcc == "6010" {
					return "27"
				}
				return "21"
			}
			if matchAny(scheme, "VISA", "VSMS") {
				return "27"
			}
		case strings.HasPrefix(procCode, "17") && matchAny(scheme, "MCI", "MDS"):
			return "12"
		}
	}
	return result
}

func matchAny(s string, candidates ...string) bool {
	u := strings.ToUpper(s)
	for _, c := range candidates {
		if u == c {
			return true
		}
	}
	return false
}

// IrfStatusCheck mirrors TxnProcessingService.irfStatusCheck.
func IrfStatusCheck(responseCode, procCode, dmsSmsMode, revIndicator string) bool {
	if responseCode != "00" || revIndicator == "R" {
		return false
	}
	if dmsSmsMode == "R" {
		return procCode != "610000" && procCode != "620000"
	}
	return (dmsSmsMode == "D" || dmsSmsMode == "S") && (procCode == "000000" || procCode == "200000")
}

// OutgoingStatusCheck mirrors TxnProcessingService.outgoingStatusCheck.
func OutgoingStatusCheck(responseCode, procCode, dmsSmsMode, revIndicator, scheme, network string) bool {
	if scheme == "" || network == "" {
		return false
	}
	sch := strings.ToUpper(scheme)
	net := strings.ToUpper(network)
	if matchAny(sch, "MCI", "VISA") && matchAny(net, "OMANNET", "UAESWITCH") {
		return responseCode == "00" && revIndicator != "R" && (dmsSmsMode == "D" || dmsSmsMode == "S")
	}
	if matchAny(sch, "JAYWAN") && matchAny(net, "OMANNET", "UAESWITCH") {
		return responseCode == "00" && revIndicator != "R" && dmsSmsMode == "D"
	}
	if matchAny(sch, "DISCOVER", "DINERS", "MERCURY", "RUPAY") && matchAny(net, "MERCURY") {
		return responseCode == "00" && revIndicator != "R" && (dmsSmsMode == "D" || dmsSmsMode == "S")
	}
	if !strings.EqualFold(sch, net) {
		return false
	}
	if matchAny(sch, "ONUS", "OMANNET") {
		return false
	}
	if responseCode != "00" || revIndicator == "R" || dmsSmsMode == "S" {
		return false
	}
	return true
}

// RevIndicator mirrors the Java revIndiCator derivation (0410/0420/0430 -> 'F',
// otherwise 'O'; a reversal staging flag forces 'R').
func RevIndicator(mti string, reversalStagingFlag int) string {
	if reversalStagingFlag == 1 {
		return "R"
	}
	switch mti {
	case "0410", "0420", "0430":
		return "F"
	default:
		return "O"
	}
}

// CheckForEmv mirrors TxnProcessingService.checkForEmv.
func CheckForEmv(network, posEntryMode string) bool {
	amex := strings.EqualFold(network, "AMEX")
	return (amex && len(posEntryMode) >= 7 && posEntryMode[6] == '5') ||
		strings.HasPrefix(posEntryMode, "05") ||
		strings.HasPrefix(posEntryMode, "07") ||
		strings.HasPrefix(posEntryMode, "95")
}

// Config holds the TLF job/institution parameters (from tlf_application.properties).
type Config struct {
	InsCode       int
	IntCode       int
	UserSerNumber int
	JobNumber     int
	ExchangeRate  float64
	Now           func() time.Time
	// KafkaIntCode is INTERFACE_CODE_KAFKA: the interface code used for the
	// Kafka-consumed path. TxnProcessingService selects it when type=="KAFKA",
	// otherwise INTERFACE_CODE_TLF.
	KafkaIntCode int
	// StageMercury enables MERCURY_ACQ_TXN_WORK staging for MERCURY-network
	// transactions after the online IRF flow (default off; requires the
	// MERCURY interface, an 'E' acquirer bin and CURRENCIES on the schema).
	StageMercury bool
}

// Entity is the POS_TRANSACTIONS entity for TLF. The embedded mpgsdcf.Entity
// carries the core column set persisted by the shared (proven) INSERT; the
// remaining fields are TLF-only columns written by a follow-up UPDATE plus the
// IRF result columns populated after the irf-service call. Field-set overlaps
// with the embedded struct are intentionally avoided (InsCode lives on the
// embedded entity).
type Entity struct {
	mpgsdcf.Entity

	Scheme                 string
	BankCode               string
	RevIndiCator           string
	SettlementIndicator    string
	AdtlAmounts            string
	ReImbursementAttribute string
	FeePgmIndicator        string
	ChIdMethod             string
	ProductId              string
	AccountFundingSource   string
	FormFactorIndicator    string
	AcceptanceTrlIndicator string
	DedicatedFileName      string
	ChipTrlType            string
	MposAccDevType         string
	TipAmount              float64
	InvoiceNumber          string
	LocationRegionCode     string
	MerchantLocationId     string
	RefundIndicator        string
	DccAmount              float64
	DccCurrency            string
	DccIndicator           string
	DccExchangeRate        float64

	// IRF result columns.
	IrdSerNumber    *int
	Ird             string
	IrfFixed        float64
	IrfPercent      float64
	IrfAmount       float64
	IrfAmountUSD    float64
	IrfMinAmount    float64
	IrfMaxAmount    float64
	CardType        string
	CardDomIntlFlag string
	CardCategory    string
	Remarks         string

	AcqInstConCode          string
	CardAcceptorCountryCode string
	NetAmount               float64
}

// ApplyResult writes the irf-service response onto the entity, mirroring Java
// updateIrfTxn / updateDefaultIrf. `cleared` (true when no IRF VO) mirrors
// updateDefaultIrf (zeroed amounts, blank dom/intl flag, nulled ird).
func (e *Entity) ApplyResult(vo *irf.IrfResultVo, cleared bool) {
	if cleared || vo == nil {
		e.IrdSerNumber = nil
		e.Ird = ""
		e.IrfFixed = 0
		e.IrfPercent = 0
		e.IrfAmount = 0
		e.IrfAmountUSD = 0
		e.IrfMinAmount = 0
		e.IrfMaxAmount = 0
		e.CardType = ""
		e.CardDomIntlFlag = " "
		e.CardCategory = ""
		e.Remarks = ""
		return
	}
	if vo.IrdSerNumber != nil {
		e.IrdSerNumber = vo.IrdSerNumber
	}
	e.Ird = vo.IrdCode
	if vo.IrfFixed != nil {
		e.IrfFixed = *vo.IrfFixed
	}
	if vo.IrfPercentage != nil {
		e.IrfPercent = *vo.IrfPercentage
	}
	if vo.IrfAmount != nil {
		e.IrfAmount = *vo.IrfAmount
	}
	if vo.IrfAmountUSD != nil {
		e.IrfAmountUSD = *vo.IrfAmountUSD
	}
	e.CardType = vo.CardType
	e.CardDomIntlFlag = vo.DomIntlFlag
	e.CardCategory = vo.GcmsProductID
	e.Remarks = vo.IrfDesc
	e.IrfMinAmount = 0
	e.IrfMaxAmount = 0
}

// MapToEntity builds the POS_TRANSACTIONS entity from the payload, mirroring
// TxnProcessingService.mapTxnToPosData. The embedded DCF Entity carries the core
// column set; the overlay carries scheme/bank/irf columns written by UPDATE.
func MapToEntity(p *SwitchExtractVo, cfg Config, countryAlpha3 func(string) string) *Entity {
	now := cfg.Now()
	divisor := float64(Divisor(p.TxnCurrencyCode))
	net := NetworkMapping(p.SmsDmsIndicator, p.Network)
	scheme := SchemeMapping(p.Scheme)
	rev := RevIndicator(p.Mti, 0)
	dms := DmsSmsMode(p.SmsDmsIndicator)
	txnAmt := ParseAmount(p.AmountTransaction) / divisor
	setlAmt := txnAmt * cfg.ExchangeRate
	if p.SettleCurrencyCode == "" {
		setlAmt = txnAmt
	}
	cashBack := 0.0
	if p.CashBackAmount != "" {
		cashBack = ParseAmount(p.CashBackAmount) / divisor
	}
	txnCode := GetTxnCode(p.Mti, p.ProcessCode, scheme, p.Mcc)

	posEntryMode := p.PosEntryMode
	if len(posEntryMode) == 4 {
		posEntryMode = posEntryMode[:3]
	}

	meCountry := p.CardAcceptorCountryCode
	if strings.EqualFold(scheme, "MCI") && len(meCountry) == 2 && countryAlpha3 != nil {
		meCountry = countryAlpha3(meCountry)
	}

	de63 := p.De63JSON
	de63Net := ""
	if de63 != nil && de63.NetworkData != "" {
		de63Net = de63.NetworkData
		if isMC(scheme) && p.SettlementDate != "" {
			de63Net = de63.NetworkData + p.SettlementDate
		}
	}

	e := &Entity{
		Entity: mpgsdcf.Entity{
			InsCode:           cfg.InsCode,
			IntCode:           cfg.IntCode,
			User:              cfg.UserSerNumber,
			JobNumber:         cfg.JobNumber,
			LastUpdated:       now,
			GenStatus:         3,
			OutStatus:         outStatusFor(net, p.SmsDmsIndicator, p.ProcessCode, p.Mti),
			IncomingStatus:    "Pending",
			ProcCode:          p.ProcessCode,
			MsgTypeId:         p.Mti,
			MerchantId:        p.MerchantId,
			TerminalId:        p.TerminalId,
			TxnAmount:         txnAmt,
			Stan:              p.Stan,
			Rrn:               p.RetRefNumber,
			ResponseCode:      p.ResponseCode,
			PosEntryMode:      posEntryMode,
			LocalDateTime:     UpdateLocalDatetime(p.LocalTxnDate, p.LocalTxnTime, now),
			TxnDateTime:       UpdateTxnDatetime(p.TxnDateTime, now),
			ApprovalCode:      strPtr(p.AuthIdResponse),
			SetlDate:          mergeSettlementDate(p.SettlementDate, now),
			TxnCurCode:        p.TxnCurrencyCode,
			SetlCurCode:       p.SettleCurrencyCode,
			TxnType:           p.ProcessCode,
			CardNumber:        p.CardNumber,
			SetlAmount:        setlAmt,
			Mcc:               p.Mcc,
			ExpiryDate:        p.ExpiryDate,
			MotoEcomIndicator: motoEcomIndicator(p, scheme),
			TerminalType:      p.TxnSource,
			NetworkData:       de63Net,
			PosConditionCode:  p.PosCode,
			CardSeqNumber:     p.PanSequence,
			ServiceCode:       p.ServiceRestrictionCode,
			TxnCode:           txnCode,
			Network:           net,
			DmsSmsMode:        dms,
			OnusOffusFlag:     onusOffus(p.OnusOffusIndicator),
			MeName:            p.CardAcceptorName,
			MeCity:            p.CardAcceptorCity,
			MeCountry:         meCountry,
			CardAccStateCode:  p.CardAcceptorStateCode,
			MePinCode:         p.CardAcceptorPinCode,
			TxnFeeAmount:      ParseAmount(p.TxnFeeAmount) / divisor,
			CashBackAmount:    cashBackPtr(cashBack),
			TxnId:             "",
			MeCategoryType:    firstCharStr(p.MeCategoryType),
			ChAuthAbility:     charAt(p.PosEntryMode, 1),
			CardInputMode:     CardInputMode(p.PosEntryMode),
			MeCountryOfOrigin: de48MeCountryOrigin(p),
			Maid:              de48Maid(p),
		},
		Scheme:                  scheme,
		BankCode:                p.BankCode,
		RevIndiCator:            rev,
		SettlementIndicator:     settlementChar(p.SettlementIndicator),
		AdtlAmounts:             adtlAmounts(p.CashBackAmount),
		AcqInstConCode:          p.AcqInsConCode,
		CardAcceptorCountryCode: p.CardAcceptorCountryCode,
		NetAmount:               txnAmt,
	}
	// de61 (mastercard only).
	if isMC(scheme) && p.De61JSON != nil {
		d := p.De61JSON
		e.CardInputAbility = d.CardInputAbility
		e.CardCaptureAbility = d.CardCaptureCapability
		e.OprtEnvironment = merge(d.OperationalEnv1, d.OperationalEnv3)
		e.ChPresent = d.ChPresent
		e.CardPresent = d.CardPresent
	}

	// de55 (EMV).
	if CheckForEmv(net, p.PosEntryMode) && p.De55JSON != nil {
		applyDe55(&e.Entity, p.De55JSON)
	}

	// visa de60 / de62.
	if strings.EqualFold(scheme, "VISA") {
		if d := p.De60JSON; d != nil {
			if validTerminalCapability(d.TerminalCapability) {
				e.TrlCapabilities = d.TerminalCapability
			}
			if d.EcomIndicator != "" {
				e.MotoEcomIndicator = d.EcomIndicator
			}
			if d.ChIdMethod != "" {
				e.ChIdMethod = d.ChIdMethod
			}
			if d.AcceptanceTrlIndicator != "" {
				e.AcceptanceTrlIndicator = d.AcceptanceTrlIndicator
			}
		}
		if d := p.De62JSON; d != nil {
			if d.TxnId != "" {
				e.TxnId = d.TxnId
			}
			if d.AuthCharecteresticId != "" {
				e.AuthCharecteresticId = firstCharStrRaw(d.AuthCharecteresticId)
			}
			e.ValidationCode = d.ValidationCode
			e.MarketSpecAuthDataInd = d.MarketSpecAuthDataInd
			if d.Mvv != "" {
				e.Mvv = d.Mvv
			}
			if d.ProductId != "" {
				e.ProductId = d.ProductId
			}
			if d.SpendQualificationInd != "" {
				e.SpendQualificationInd = firstCharStrRaw(d.SpendQualificationInd)
			}
		}
		if d := p.De111JSON; d != nil {
			e.AccountFundingSource = firstCharStrRaw(d.AccountFundingSource)
		}
	}

	// visa de126 posEnv overrides oprtEnvironment.
	if strings.EqualFold(scheme, "VISA") && p.De126JSON != nil && p.De126JSON.PosEnv != "" {
		e.OprtEnvironment = p.De126JSON.PosEnv
	}

	// amex / mc txnId from de62.
	if (strings.EqualFold(scheme, "AMEX") || isMC(scheme)) && p.De62JSON != nil && p.De62JSON.TxnId != "" {
		e.TxnId = p.De62JSON.TxnId
	}

	// de48 mpos account device type (Character column) -> overlay.
	if p.De48JSON != nil && p.De48JSON.MposAccDevType != "" {
		e.MposAccDevType = firstCharStrRaw(p.De48JSON.MposAccDevType)
	}

	// mastercard reImb / networkData / txnId (de62).
	if isMC(scheme) && p.De62JSON != nil && p.De62JSON.TxnId != "" {
		e.TxnId = p.De62JSON.TxnId
	}

	// DCC.
	if p.Dcc != nil && p.Dcc.DccIndicator == "Y" {
		e.DccIndicator = "Y"
		e.DccAmount = ParseAmount(p.Dcc.DccAmount) / divisor
		e.DccCurrency = p.Dcc.DccCurrency
		e.DccExchangeRate = ParseAmount(p.Dcc.ExchangeRate)
	}

	if p.TipAmount != "" {
		e.TipAmount = ParseAmount(p.TipAmount) / divisor
	}
	e.InvoiceNumber = p.InvoiceNumber
	e.LocationRegionCode = p.LocationRegionCode
	e.RefundIndicator = firstCharStrRaw(strings.TrimSpace(p.SettlementIndicator))

	if p.MerchantContactInfo != "" {
		phone := stripNonDigits(p.MerchantContactInfo)
		if len(phone) >= 5 && len(phone) <= 15 {
			e.MerchantContactInfo = p.MerchantContactInfo
		}
	}

	return e
}

// ToTxn maps the entity onto the shared tlf.Txn DTO consumed by the irf client
// (mirrors TlfTxnMapper; multi-char Character sources narrowed via firstChar).
// cardNumber is the decrypted PAN (Java passes getCardNumber(tokenIdentifier)
// into fetchIrf); the payload pan is used when no decrypt happened.
func (e *Entity) ToTxn(p *SwitchExtractVo, cardNumber string) tlf.Txn {
	var dt time.Time
	if e.TxnDateTime != nil {
		dt = *e.TxnDateTime
	}
	var encCard string
	if p != nil {
		encCard = p.TokenIdentifier
	}
	return tlf.Txn{
		SerialNumber:            e.SerialNumber,
		InsCode:                 e.InsCode,
		Network:                 e.Network,
		Scheme:                  e.Scheme,
		Mcc:                     e.Mcc,
		TxnCode:                 e.TxnCode,
		TxnId:                   nullStrPtr(e.TxnId),
		ResponseCode:            e.ResponseCode,
		ApprovalCode:            derefStr(e.ApprovalCode),
		Rrn:                     e.Rrn,
		PosEntryMode:            e.PosEntryMode,
		PosConditionCode:        e.PosConditionCode,
		ServiceCode:             e.ServiceCode,
		CardSeqNumber:           e.CardSeqNumber,
		TerminalType:            e.TerminalType,
		TxnAmount:               e.TxnAmount,
		SetlAmount:              e.SetlAmount,
		CashBackAmount:          cashBackOrZero(e.CashBackAmount),
		NetAmount:               e.NetAmount,
		TxnCurCode:              e.TxnCurCode,
		SetlCurCode:             e.SetlCurCode,
		FeePgmIndicator:         e.FeePgmIndicator,
		ReImbursementAttribute:  e.ReImbursementAttribute,
		MotoEcomIndicator:       e.MotoEcomIndicator,
		TerminalCapability:      e.TerminalType,
		TrlCapabilities:         firstCharStrRaw(e.TrlCapabilities),
		Mvv:                     e.Mvv,
		NetworkData:             e.NetworkData,
		Maid:                    e.Maid,
		MeCategoryType:          e.MeCategoryType,
		ChAuthAbility:           e.ChAuthAbility,
		CardInputAbility:        e.CardInputAbility,
		CardCaptureAbility:      e.CardCaptureAbility,
		CardInputMode:           e.CardInputMode,
		ChPresent:               e.ChPresent,
		CardPresent:             e.CardPresent,
		OprtEnvironment:         e.OprtEnvironment,
		EncCardNumber:           encCard,
		CardNumber:              cardNumber,
		TxnDateTime:             dt,
		AcqInstConCode:          e.AcqInstConCode,
		CardAcceptorCountryCode: e.CardAcceptorCountryCode,
		MsgTypeId:               e.MsgTypeId,
		TxnUniqueId:             e.TxnUniqueId,
	}
}

func isMC(scheme string) bool {
	return matchAny(scheme, "MCI", "MDS")
}

func outStatusFor(net, smsDms, procCode, mti string) string {
	switch strings.ToUpper(net) {
	case "UAESWITCH":
		if (strings.HasPrefix(procCode, "20") || strings.HasPrefix(procCode, "06")) && mti == "0110" {
			return "Pending for Refund file"
		}
		return "NA"
	}
	return "Pending"
}

func onusOffus(ind string) string {
	if strings.EqualFold(ind, "ONUS") {
		return "O"
	}
	return "F"
}

func motoEcomIndicator(p *SwitchExtractVo, scheme string) string {
	if d := p.De48JSON; d != nil && d.SetIndicator != "" {
		return d.SetIndicator
	}
	if strings.EqualFold(scheme, "VISA") && p.De60JSON != nil && p.De60JSON.EcomIndicator != "" {
		return p.De60JSON.EcomIndicator
	}
	return ""
}

func validTerminalCapability(c string) bool {
	if c == "" {
		return false
	}
	ch := c[0]
	return (ch >= '0' && ch <= '5') || ch == ' ' || ch == '8' || ch == '9'
}

func de48MeCountryOrigin(p *SwitchExtractVo) string {
	if d := p.De48JSON; d != nil {
		return d.MeCountryCode
	}
	return ""
}

func de48Maid(p *SwitchExtractVo) string {
	if d := p.De48JSON; d != nil {
		return d.Maid
	}
	return ""
}

func adtlAmounts(cashBack string) string {
	if strings.TrimSpace(cashBack) == "" {
		return "0.0"
	}
	return cashBack
}

func merge(a, b string) string {
	if a == "" {
		return b
	}
	if b == "" {
		return a
	}
	return a + b
}

func mergeSettlementDate(s string, now time.Time) *time.Time {
	if len(s) < 4 {
		return nil
	}
	year := now.Year()
	d := fmt.Sprintf("%d%s", year, s)
	t, err := time.Parse("20060102", d)
	if err != nil {
		return nil
	}
	if t.After(now) {
		t = t.AddDate(-1, 0, 0)
	}
	return &t
}

func firstCharStr(s string) string {
	if s == "" {
		return ""
	}
	return s[:1]
}

func firstCharStrRaw(s string) string {
	if s == "" {
		return ""
	}
	return s[:1]
}

func nullStrPtr(s string) string {
	if s == "" {
		return ""
	}
	return s
}

func charAt(s string, i int) string {
	if i < 0 || i >= len(s) {
		return ""
	}
	return s[i : i+1]
}

func firstPtr(s string) *string {
	if s == "" {
		return nil
	}
	return &s
}

func cashBackPtr(f float64) *float64 { return &f }

func stripNonDigits(s string) string {
	var b strings.Builder
	for _, r := range s {
		if r >= '0' && r <= '9' {
			b.WriteRune(r)
		}
	}
	return b.String()
}

func rightPad(s string, n int) string {
	if len(s) >= n {
		return s[:n]
	}
	return s + strings.Repeat(" ", n-len(s))
}

func strPtr(s string) *string { return &s }

func derefStr(p *string) string {
	if p == nil {
		return ""
	}
	return *p
}

func settlementChar(s string) string {
	if s == "" {
		return " "
	}
	return s[:1]
}

func cashBackOrZero(p *float64) float64 {
	if p == nil {
		return 0
	}
	return *p
}

// applyDe55 applies EMV tag data (de55) onto the entity for Visa-style
// cardholder-verification fields. Mastercard-only; stubbed for the Jaywan
// payload (de55 absent) — implemented lazily if a card scheme requires it.
func applyDe55(e *mpgsdcf.Entity, d *De55Vo) {
	if d == nil {
		return
	}
	e.ChipTxnDate = strPtr(d.NineF35)
	e.IssAuthData = d.NineF34
	e.AppCryptogram = d.EightyTwo
	e.CvmResult = merge(d.NineA, d.NineC)
}
