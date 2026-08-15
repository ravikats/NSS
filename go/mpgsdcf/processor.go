package mpgsdcf

import (
	"context"
	"fmt"
	"math/big"
	"regexp"
	"strconv"
	"strings"
	"time"
)

// Entity is the Go port of MPGSTxnWorkEntity (POS_TRANSACTIONS). Only the
// columns the MpgsDataProcessor populates are present; nil means DB NULL.
type Entity struct {
	SerialNumber            int
	InsCode                 int
	IntCode                 int
	User                    int
	JobNumber               int
	LastUpdated             time.Time
	GenStatus               int
	OutStatus               string
	IncomingStatus          string
	ProcCode                string
	MsgTypeId               string
	MerchantId              string
	TerminalId              string
	TxnAmount               float64
	Stan                    string
	Rrn                     string
	ResponseCode            string
	PosEntryMode            string
	LocalDateTime           *time.Time
	TxnDateTime             *time.Time
	ApprovalCode            *string
	SetlDate                *time.Time
	TxnCurCode              string
	SetlCurCode             string
	TxnType                 string
	TxnCode                 string
	CardNumber              string
	SetlAmount              float64
	Mcc                     string
	ExpiryDate              string
	MotoEcomIndicator       string
	TerminalType            string
	NetworkData             string
	PosConditionCode        string
	CardInputAbility        string
	ChAuthAbility           string
	CardCaptureAbility      string
	OprtEnvironment         string
	ChPresent               string
	CardPresent             string
	CardInputMode           string
	ChAuthMethod            string
	OnusOffusFlag           string
	MeName                  string
	MeCity                  string
	MeCountry               string
	CardAccStateCode        string
	MePinCode               string
	TxnFeeAmount            float64
	CashBackAmount          *float64
	TxnId                   string
	MeCategoryType          string
	Network                 string
	ServiceCode             string
	TxnUniqueId             string
	Maid                    string
	DmsSmsMode              string
	MeCountryOfOrigin       string
	ValidationCode          string
	MarketSpecAuthDataInd   string
	SpendQualificationInd   string
	Mvv                     string
	AuthCharecteresticId    string
	CardSeqNumber           string
	AppCryptogram           string
	CryptInfoData           string
	IssAppData              string
	UpblNumber              string
	AppTxnCounter           string
	TrlVerResult            string
	ChipTxnDate             *string
	ChipTxnType             string
	CryptAmount             float64
	AuthAmount              float64
	ChipCurCode             string
	AppICProfile            string
	TrlConCode              string
	ChipCashBack            float64
	CvmResult               string
	TrlCapabilities         string
	ChipTrlCapabilities     string
	IfdSerNumber            string
	Tcc                     string
	TrlAppVerNumber         string
	IssAuthData             string
	MerchantContactInfo     string
	AccepterUrlAddress      string
}

// CountryLookup resolves a CON_CODE to its CON_ALPHA3_CODE (Java
// CountriesRepository.findByCountryCode -> getCountryAlpha3Code). The DB-backed
// default lives in the service; tests inject a stub.
type CountryLookup func(ctx context.Context, code string) (string, error)

// currencyCodes3 mirrors CurrencyUtil.CURRENCY_CODES_3.
var currencyCodes3 = map[string]bool{
	"048": true, "400": true, "414": true, "434": true, "512": true, "788": true,
	"BHD": true, "JOD": true, "KWD": true, "LYD": true, "OMR": true, "TND": true,
}

// Divisor mirrors CurrencyUtil.getDivisor.
func Divisor(currencyCode string) int {
	if currencyCodes3[currencyCode] {
		return 1000
	}
	return 100
}

// PanRange is an inclusive [Start, End] card range (16-digit PANs as in
// jaywan_ranges.csv).
type PanRange struct {
	Start string
	End   string
}

// Contains reports whether pan falls inside the range. Ranges and PANs are
// compared as numeric strings (big.Int) so variable-length PANs are safe.
func (r PanRange) Contains(pan string) bool {
	start, ok1 := new(big.Int).SetString(r.Start, 10)
	end, ok2 := new(big.Int).SetString(r.End, 10)
	value, ok3 := new(big.Int).SetString(pan, 10)
	if !ok1 || !ok2 || !ok3 {
		return false
	}
	return value.Cmp(start) >= 0 && value.Cmp(end) <= 0
}

// JaywanRanges holds the [Start,End] card ranges that identify Jaywan cards.
// When a 6220 record's PAN falls in one of these ranges and no network-specific
// detail record (6222/6223/6224/6226/6260) follows, the network is set to
// JAYWAN. A present detail record always wins (it carries the real network).
type JaywanRanges []PanRange

// Contains reports whether pan falls in any configured Jaywan range.
func (rs JaywanRanges) Contains(pan string) bool {
	for _, r := range rs {
		if r.Contains(pan) {
			return true
		}
	}
	return false
}

// Processor is the stateful Go port of MpgsDataProcessor. It keeps the current
// transaction like the Java bean; a 6220 record creates a fresh entity while
// 6221-6292 records enrich it in place. Only the 6220 entity is returned.
type Processor struct {
	UserSerNumber int
	InsCode       int
	IntCode       int
	JobNumber     int
	CountryCode   CountryLookup
	Now           func() time.Time
	Jaywan        JaywanRanges
	current       *Entity
}

// Process handles one parsed record and returns the entity to persist for a
// 6220 record (nil otherwise), mirroring MpgsDataProcessor.process.
func (p *Processor) Process(rec *Record) (*Entity, error) {
	if rec == nil || rec.Type == "6200" || rec.Type == "6240" {
		return nil, nil
	}
	switch {
	case rec.Txn1 != nil:
		return p.onTxn1(rec.Txn1)
	case rec.Txn2 != nil:
		return nil, p.onTxn2(rec.Txn2)
	case rec.MC != nil:
		return nil, p.onMC(rec.MC)
	case rec.Visa != nil:
		return nil, p.onVisa(rec.Visa)
	case rec.Emv != nil:
		return nil, p.onEmv(rec.Emv)
	case rec.AccData1 != nil:
		return nil, p.onAccData1(rec.AccData1)
	case rec.AccData2 != nil:
		return nil, p.onAccData2(rec.AccData2)
	default:
		switch rec.Type {
		case "6224":
			if p.current != nil {
				p.current.Network = "AMEX"
			}
		case "6226":
			if p.current != nil {
				p.current.Network = "DINERS"
			}
		case "6260":
			if p.current != nil {
				p.current.Network = "RUPAY"
			}
		}
		return nil, nil
	}
}

func (p *Processor) now() time.Time {
	if p.Now != nil {
		return p.Now()
	}
	return time.Now()
}

func (p *Processor) onTxn1(r *TxnRecord1) (*Entity, error) {
	now := p.now()
	txn := &Entity{
		InsCode:          p.InsCode,
		IntCode:          p.IntCode,
		User:             p.UserSerNumber,
		JobNumber:        p.JobNumber,
		LastUpdated:      now,
		GenStatus:        3,
		OutStatus:        "Pending",
		IncomingStatus:   "Pending",
		ProcCode:         r.ProcessingCode,
		MsgTypeId:        r.MsgTypeId,
		MerchantId:       r.MerchantId,
		TerminalId:       r.TerminalID,
		TxnAmount:        parseAmt(r.TransactionAmount),
		Stan:             r.Stan,
		Rrn:              r.RetRefNumber,
		ResponseCode:     r.ResponseCode,
		PosEntryMode:     r.PosEntryMode,
		SetlDate:         mergeDate(r.SettlementDate, now),
		TxnCurCode:       r.TransactionCurrencyCode,
		SetlCurCode:      r.SettlementCurrCode,
		TxnType:          r.ProcessingCode,
		CardNumber:       r.Pan,
		SetlAmount:       parseAmt(r.SettlementAmount),
		Mcc:              r.Mcc,
		ExpiryDate:       r.ExpiryDate,
		MotoEcomIndicator: r.MotoEcomIndicator,
		TerminalType:     mapTerminalType(r.TrlType),
		NetworkData:      r.NetworkReferenceNumber,
		PosConditionCode: r.PosConditionCode,
		OnusOffusFlag:    "F",
	}
	if p.Jaywan.Contains(r.Pan) {
		txn.Network = "JAYWAN"
	}
	if dt := mergeDateAndTime(r.TxnLocalDate, r.TxnLocalTime, now); dt != nil {
		txn.LocalDateTime = dt
		txn.TxnDateTime = dt
	}
	if r.AuthCode != "" {
		ac := r.AuthCode
		txn.ApprovalCode = &ac
	}
	if r.ProcessingCode != "" {
		txn.TxnCode = r.ProcessingCode[:2]
	}
	psc := padTo(r.PosServiceCode, 8)
	txn.CardInputAbility = string(psc[0])
	txn.ChAuthAbility = string(psc[1])
	txn.CardCaptureAbility = string(psc[2])
	txn.OprtEnvironment = string(psc[3])
	txn.ChPresent = string(psc[4])
	txn.CardPresent = string(psc[5])
	txn.CardInputMode = string(psc[6])
	txn.ChAuthMethod = string(psc[7])
	p.current = txn
	return txn, nil
}

func (p *Processor) onTxn2(r *TxnRecord2) error {
	txn := p.current
	if txn == nil {
		return fmt.Errorf("mpgsdcf: 6221 before any 6220")
	}
	txn.MeName = r.MerchantName
	txn.MeCity = r.MerchantCity
	txn.MeCountry = p.lookupCountry(r.MerchantCountryCode)
	txn.CardAccStateCode = r.StateCode
	txn.MePinCode = r.MePinCode
	txn.TxnFeeAmount = parseAmt(r.TxnFeeAmount)
	if strings.EqualFold(r.AdditionalAmountType, "40") {
		amt := parseAmt(r.AdditionalAmount)
		txn.CashBackAmount = &amt
	}
	settleDate := ""
	if txn.SetlDate != nil {
		settleDate = txn.SetlDate.Format("0102")
	}
	networkData := fmt.Sprintf(" %s%s%s  ", r.FinancialNetworkCode, txn.NetworkData, settleDate)
	txn.NetworkData = networkData
	txn.TxnId = strings.TrimLeft(networkData, " ")
	if r.TccType != "" {
		txn.MeCategoryType = string(r.TccType[0])
	}
	return nil
}

func (p *Processor) onMC(r *McSpecificData) error {
	txn := p.current
	if txn == nil {
		return fmt.Errorf("mpgsdcf: 6222 before any 6220")
	}
	txn.Network = "MCI"
	div := Divisor(txn.TxnCurCode)
	txn.TxnAmount /= float64(div)
	txn.SetlAmount /= float64(div)
	txn.TxnFeeAmount /= float64(div)
	if txn.CashBackAmount != nil {
		v := *txn.CashBackAmount / float64(div)
		txn.CashBackAmount = &v
	}
	if txn.ServiceCode == "" {
		txn.ServiceCode = r.ServiceCode
	}
	txn.SetlDate = mergeDate(r.SettlementDate, p.now())
	txn.TxnUniqueId = r.TxnUniqueId
	txn.Maid = r.Maid
	txn.DmsSmsMode = "D"
	txn.NetworkData = r.NetworkCode
	txn.MeCountryOfOrigin = r.MerchantCountryofOrigin
	return nil
}

func (p *Processor) onVisa(r *VisaSpecificData) error {
	txn := p.current
	if txn == nil {
		return fmt.Errorf("mpgsdcf: 6223 before any 6220")
	}
	txn.Network = "VISA"
	div := Divisor(txn.TxnCurCode)
	txn.TxnAmount /= float64(div)
	txn.SetlAmount /= float64(div)
	txn.TxnFeeAmount /= float64(div)
	if txn.CashBackAmount != nil {
		v := *txn.CashBackAmount / float64(div)
		txn.CashBackAmount = &v
	}
	txn.TxnId = r.VisaTxnIdentifier
	txn.ValidationCode = r.ValidationCode
	txn.MarketSpecAuthDataInd = r.MarketSpecDataIdentifier
	if r.SpentQualifiedIndicator == "" {
		txn.SpendQualificationInd = " "
	} else {
		txn.SpendQualificationInd = string(r.SpentQualifiedIndicator[0])
	}
	txn.Mvv = r.MerchantVerificationValue
	txn.TxnCode = getTxnCode(txn.MsgTypeId, txn.ProcCode, "VISA", txn.Mcc)
	if r.VisaACIInd == "" {
		txn.AuthCharecteresticId = " "
	} else {
		txn.AuthCharecteresticId = string(r.VisaACIInd[0])
	}
	return nil
}

func (p *Processor) onEmv(r *EmvRecordData) error {
	txn := p.current
	if txn == nil {
		return fmt.Errorf("mpgsdcf: 6225 before any 6220")
	}
	txn.CardSeqNumber = r.PanSeqNumber
	if txn.ServiceCode == "" {
		txn.ServiceCode = r.ServiceCodeOnCard
	}
	txn.AppCryptogram = r.AppCryptogram
	txn.CryptInfoData = r.CryptInfoData
	txn.IssAppData = r.IssuerAppData
	txn.UpblNumber = r.UpblNumber
	txn.AppTxnCounter = r.AppTxnCounter
	txn.TrlVerResult = r.TrlVerResult
	if r.ChipTxnDate != "" {
		cd := r.ChipTxnDate
		txn.ChipTxnDate = &cd
	}
	txn.ChipTxnType = r.ChipTxnType
	txn.CryptAmount = div100(r.CryptAmount)
	txn.AuthAmount = div100(r.CryptAmount)
	txn.ChipCurCode = r.ChipCurCode
	txn.AppICProfile = r.AppIcProfile
	txn.TrlConCode = r.ChipTrlConCode
	txn.ChipCashBack = div100(r.ChipCashbackAmount)
	txn.CvmResult = r.CVMResult
	txn.TrlCapabilities = " "
	txn.ChipTrlCapabilities = r.TrlCapabilities
	txn.TerminalType = r.ChipTrlType
	txn.IfdSerNumber = r.IFDSerNumber
	txn.Tcc = r.TxnCategoryCode
	txn.TrlAppVerNumber = r.AppVersionNumber
	txn.IssAuthData = r.IssuerAuthData
	return nil
}

func (p *Processor) onAccData1(r *AdditionalAccData1) error {
	phone := r.AccCustomerServicePhoNumber
	if phone != "" {
		phone = strings.TrimSpace(phone)
		if phonePattern.MatchString(phone) && p.current != nil {
			p.current.MerchantContactInfo = phone
		}
	}
	return nil
}

func (p *Processor) onAccData2(r *AdditionalAccData2) error {
	url := r.AccURLAddress
	if url != "" && urlPattern.MatchString(url) && p.current != nil {
		p.current.AccepterUrlAddress = url
	}
	return nil
}

func (p *Processor) lookupCountry(code string) string {
	if p.CountryCode == nil || code == "" {
		return ""
	}
	alpha3, err := p.CountryCode(context.Background(), code)
	if err != nil {
		return ""
	}
	return alpha3
}

var (
	phonePattern = regexp.MustCompile(`^\+?[0-9]{5,15}$`)
	urlPattern   = regexp.MustCompile(`^[^\s@]*[A-Za-z0-9]\.[A-Za-z]{2,}$`)
)

func parseAmt(s string) float64 {
	if strings.TrimSpace(s) == "" {
		return 0
	}
	v, err := strconv.ParseFloat(s, 64)
	if err != nil {
		return 0
	}
	return v
}

func div100(s string) float64 {
	if strings.TrimSpace(s) == "" {
		return 0
	}
	v, err := strconv.ParseFloat(s, 64)
	if err != nil {
		return 0
	}
	return v / 100
}

// padTo pads s with spaces to at least n chars (FixedLengthTokenizer yields
// fixed-width fields, so this only guards truncated/garbage lines).
func padTo(s string, n int) string {
	if len(s) >= n {
		return s[:n]
	}
	return s + strings.Repeat(" ", n-len(s))
}

// mergeDateAndTime mirrors MpgsDataProcessor.mergeDateAndTime.
func mergeDateAndTime(dateWithoutYear, timeStr string, now time.Time) *time.Time {
	if len(dateWithoutYear) < 4 || len(timeStr) < 6 {
		return nil
	}
	month, err := strconv.Atoi(dateWithoutYear[0:2])
	if err != nil {
		return nil
	}
	day, err := strconv.Atoi(dateWithoutYear[2:4])
	if err != nil {
		return nil
	}
	hour, err := strconv.Atoi(timeStr[0:2])
	if err != nil {
		return nil
	}
	minute, err := strconv.Atoi(timeStr[2:4])
	if err != nil {
		return nil
	}
	second, err := strconv.Atoi(timeStr[4:6])
	if err != nil {
		return nil
	}
	year := now.Year()
	date := time.Date(year, time.Month(month), day, hour, minute, second, 0, time.Local)
	if date.After(now) {
		date = date.AddDate(-1, 0, 0)
	}
	return &date
}

// mergeDate mirrors MpgsDataProcessor.mergeDate (yyyyMMdd built from current
// year + MMdd; if in the future, shifted one year back).
func mergeDate(mmDD string, now time.Time) *time.Time {
	if len(mmDD) < 4 {
		return nil
	}
	month, err := strconv.Atoi(mmDD[0:2])
	if err != nil {
		return nil
	}
	day, err := strconv.Atoi(mmDD[2:4])
	if err != nil {
		return nil
	}
	year := now.Year()
	date := time.Date(year, time.Month(month), day, 0, 0, 0, 0, time.Local)
	if date.After(now) {
		date = date.AddDate(-1, 0, 0)
	}
	return &date
}

func mapTerminalType(trlType string) string {
	switch trlType {
	case "0", "2":
		return "POI"
	case "6":
		return "CT6"
	case "9":
		return "CT9"
	}
	return ""
}

// getTxnCode mirrors MpgsDataProcessor.getTxnCode.
func getTxnCode(mti, procCode, network, merchantType string) string {
	visa := network == "VISA" || network == "VSMS"
	mc := network == "MCI" || network == "MDS"
	rupay := network == "RUPAY" || network == "RSMS"

	switch mti {
	case "0110", "0210", "0100", "0200", "0120":
		switch {
		case strings.HasPrefix(procCode, "00"):
			if visa {
				return "05"
			}
			return "00"
		case strings.HasPrefix(procCode, "09"):
			return "09"
		case strings.HasPrefix(procCode, "20"):
			if mc {
				return "20"
			}
			return "06"
		case strings.HasPrefix(procCode, "01"):
			if rupay {
				if merchantType == "6010" {
					return "07"
				}
				return "01"
			}
			if visa {
				return "07"
			}
		case strings.HasPrefix(procCode, "17") && mc:
			return "12"
		case strings.HasPrefix(procCode, "71"):
			return "71"
		case strings.HasPrefix(procCode, "61"):
			return "61"
		}
	case "0400", "0410", "0420", "0430":
		switch {
		case strings.HasPrefix(procCode, "00"):
			if visa {
				return "25"
			}
			return "20"
		case strings.HasPrefix(procCode, "09"):
			return "09"
		case strings.HasPrefix(procCode, "20"):
			if mc {
				return "20"
			}
			return "26"
		case strings.HasPrefix(procCode, "01"):
			if rupay {
				if merchantType == "6010" {
					return "27"
				}
				return "21"
			}
			if visa {
				return "27"
			}
		case strings.HasPrefix(procCode, "17") && mc:
			return "12"
		}
	}
	return ""
}
