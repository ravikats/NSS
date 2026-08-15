// Package mpgsdcf is the Go port of the MPGS file-processing reader +
// processor (BatchConfigurations line tokenizers + MpgsDataProcessor).
//
// It reads the fixed-width 256-char MasterCard/MIGS draft-capture ("DCF") file
// layout, tokenizes each record by its 62xx type using the exact ranges from
// the Java BatchConfigurations.tokenizerMap(), and builds POS_TRANSACTIONS
// entities exactly like the Java MpgsDataProcessor.
package mpgsdcf

import (
	"strings"
)

// cut returns line[start-1:end] (1-indexed inclusive, like Spring Batch Range).
// Ranges are clamped to the line; out-of-range positions yield "".
func cut(line string, start, end int) string {
	if start < 1 {
		start = 1
	}
	if end < start {
		return ""
	}
	if start > len(line) {
		return ""
	}
	if end > len(line) {
		end = len(line)
	}
	return line[start-1 : end]
}

// readString mirrors FieldSet.readString (whitespace-trimmed).
func readString(line string, start, end int) string {
	return strings.TrimSpace(cut(line, start, end))
}

// readRawString mirrors FieldSet.readRawString (no trimming).
func readRawString(line string, start, end int) string {
	return cut(line, start, end)
}

// HeaderRecord is the 6200 record (skipped for txn building).
type HeaderRecord struct {
	FileName      string
	AcquirerICA   string
	SettlementDate string
	TxnCount      string
}

// TxnRecord1 is the 6220 record (primary txn details).
type TxnRecord1 struct {
	MsgTypeId               string
	Pan                     string
	ProcessingCode          string
	TransactionAmount       string
	SettlementAmount        string
	BillingAmount           string
	TransactionDateTime     string
	Stan                    string
	TxnLocalDate            string
	TxnLocalTime            string
	ExpiryDate              string
	SettlementDate          string
	Mcc                     string
	PosEntryMode            string
	PosConditionCode        string
	RetRefNumber            string
	AuthCode                string
	ResponseCode            string
	TerminalID              string
	MerchantId              string
	TransactionCurrencyCode string
	SettlementCurrCode      string
	BillingCurrencyCode     string
	AdviceReasonCode        string
	NetworkReferenceNumber  string
	PosServiceCode          string
	MotoEcomIndicator       string
	TrlType                 string
}

// TxnRecord2 is the 6221 record (merchant details).
type TxnRecord2 struct {
	MerchantName       string
	MerchantCity       string
	MerchantCountryCode string
	FinancialNetworkCode string
	AdviceReasonCode   string
	PaymentTxnType     string
	TransactionId      string
	TxnFeeAmount       string
	MePinCode          string
	StateCode          string
	AdditionalAmountType string
	AdditionalAmount   string
	TccType            string
}

// McSpecificData is the 6222 record (MasterCard-specific).
type McSpecificData struct {
	NetworkCode           string
	SettlementDate        string
	ServiceCode           string
	MerchantCountryofOrigin string
	TxnUniqueId           string
	Maid                  string
}

// VisaSpecificData is the 6223 record (VISA-specific).
type VisaSpecificData struct {
	VisaACIInd                string
	VisaTxnIdentifier         string
	ValidationCode            string
	MarketSpecDataIdentifier  string
	SpentQualifiedIndicator   string
	MerchantVerificationValue string
}

// EmvRecordData is the 6225 record (EMV data).
type EmvRecordData struct {
	PanSeqNumber      string
	ServiceCodeOnCard string
	AppCryptogram     string
	CryptInfoData     string
	IssuerAppData     string
	UpblNumber        string
	AppTxnCounter     string
	TrlVerResult      string
	ChipTxnDate       string
	ChipTxnType       string
	CryptAmount       string
	ChipCurCode       string
	AppIcProfile      string
	ChipTrlConCode    string
	ChipCashbackAmount string
	CVMResult         string
	TrlCapabilities   string
	ChipTrlType       string
	IFDSerNumber      string
	TxnCategoryCode   string
	AppVersionNumber  string
	IssuerAuthData    string
}

// AdditionalAccData1 is the 6291 record (customer-service phone).
type AdditionalAccData1 struct {
	AccCustomerServicePhoNumber string
}

// AdditionalAccData2 is the 6292 record (acquirer URL).
type AdditionalAccData2 struct {
	AccURLAddress string
}

// Record is one parsed 62xx line. Only one of the fields is set.
type Record struct {
	Type            string
	Header          *HeaderRecord
	Txn1            *TxnRecord1
	Txn2            *TxnRecord2
	MC              *McSpecificData
	Visa            *VisaSpecificData
	Emv             *EmvRecordData
	AccData1        *AdditionalAccData1
	AccData2        *AdditionalAccData2
}

// ParseRecord tokenizes one fixed-width line according to its 62xx type.
// Returns (nil, nil) for record types that carry no data used downstream.
func ParseRecord(line string) (*Record, error) {
	rt := readString(line, 1, 4)
	rec := &Record{Type: rt}
	switch rt {
	case "6200":
		rec.Header = &HeaderRecord{
			FileName:      readRawString(line, 5, 36),
			AcquirerICA:   readString(line, 37, 42),
			SettlementDate: readString(line, 43, 48),
			TxnCount:      readString(line, 49, 51),
		}
	case "6220":
		rec.Txn1 = &TxnRecord1{
			MsgTypeId:               readString(line, 5, 8),
			Pan:                     readString(line, 9, 28),
			ProcessingCode:          readString(line, 29, 34),
			TransactionAmount:       readString(line, 35, 46),
			SettlementAmount:        readString(line, 47, 58),
			BillingAmount:           readString(line, 59, 70),
			TransactionDateTime:     readString(line, 71, 80),
			Stan:                    readString(line, 81, 86),
			TxnLocalDate:            readString(line, 87, 90),
			TxnLocalTime:            readString(line, 91, 96),
			ExpiryDate:              readString(line, 97, 100),
			SettlementDate:          readString(line, 101, 104),
			Mcc:                     readString(line, 105, 108),
			PosEntryMode:            readString(line, 109, 111),
			PosConditionCode:        readString(line, 112, 113),
			RetRefNumber:            readString(line, 114, 125),
			AuthCode:                readString(line, 126, 131),
			ResponseCode:            readString(line, 132, 133),
			TerminalID:              readRawString(line, 134, 141),
			MerchantId:              readRawString(line, 142, 156),
			TransactionCurrencyCode: readString(line, 163, 165),
			SettlementCurrCode:      readString(line, 166, 168),
			BillingCurrencyCode:     readString(line, 169, 171),
			AdviceReasonCode:        readString(line, 172, 174),
			NetworkReferenceNumber:  readString(line, 179, 187),
			PosServiceCode:          readString(line, 247, 256),
			MotoEcomIndicator:       readString(line, 157, 159),
			TrlType:                 readString(line, 178, 178),
		}
	case "6221":
		rec.Txn2 = &TxnRecord2{
			MerchantName:       readRawString(line, 5, 26),
			MerchantCity:       readRawString(line, 72, 84),
			MerchantCountryCode: readString(line, 88, 90),
			FinancialNetworkCode: readString(line, 101, 103),
			AdviceReasonCode:   readString(line, 105, 106),
			PaymentTxnType:     readString(line, 185, 187),
			TransactionId:      readString(line, 112, 147),
			TxnFeeAmount:       readString(line, 217, 228),
			MePinCode:          readString(line, 91, 100),
			StateCode:          readString(line, 85, 87),
			AdditionalAmountType: readString(line, 161, 162),
			AdditionalAmount:   readString(line, 163, 174),
			TccType:            readString(line, 101, 104),
		}
	case "6222":
		rec.MC = &McSpecificData{
			NetworkCode:           readString(line, 5, 17),
			SettlementDate:        readString(line, 14, 17),
			ServiceCode:           readString(line, 29, 31),
			MerchantCountryofOrigin: readString(line, 70, 72),
			TxnUniqueId:           readString(line, 95, 113),
			Maid:                  readString(line, 89, 94),
		}
	case "6223":
		rec.Visa = &VisaSpecificData{
			VisaACIInd:                readString(line, 11, 11),
			VisaTxnIdentifier:         readString(line, 12, 26),
			ValidationCode:            readString(line, 27, 30),
			MarketSpecDataIdentifier:  readString(line, 32, 32),
			SpentQualifiedIndicator:   readString(line, 44, 44),
			MerchantVerificationValue: readString(line, 34, 43),
		}
	case "6225":
		rec.Emv = &EmvRecordData{
			PanSeqNumber:      readString(line, 5, 7),
			ServiceCodeOnCard: readString(line, 8, 10),
			AppCryptogram:     readString(line, 11, 26),
			CryptInfoData:     readString(line, 27, 28),
			IssuerAppData:     readString(line, 29, 92),
			UpblNumber:        readString(line, 93, 100),
			AppTxnCounter:     readString(line, 101, 104),
			TrlVerResult:      readString(line, 105, 114),
			ChipTxnDate:       readString(line, 115, 120),
			ChipTxnType:       readString(line, 121, 122),
			CryptAmount:       readString(line, 123, 134),
			ChipCurCode:       readString(line, 135, 137),
			AppIcProfile:      readString(line, 138, 141),
			ChipTrlConCode:    readString(line, 142, 144),
			ChipCashbackAmount: readString(line, 145, 156),
			CVMResult:         readString(line, 157, 162),
			TrlCapabilities:   readString(line, 163, 168),
			ChipTrlType:       readString(line, 169, 170),
			IFDSerNumber:      readString(line, 171, 178),
			TxnCategoryCode:   readString(line, 179, 179),
			AppVersionNumber:  readString(line, 212, 215),
			IssuerAuthData:    readString(line, 224, 255),
		}
	case "6291":
		rec.AccData1 = &AdditionalAccData1{
			AccCustomerServicePhoNumber: readRawString(line, 57, 72),
		}
	case "6292":
		rec.AccData2 = &AdditionalAccData2{
			AccURLAddress: readRawString(line, 5, 256),
		}
	default:
		// 6224, 6226, 6227, 6228, 6260, 6270, 6271, 6229, 6290, 6240:
		// recognized by the tokenizer map but carry no downstream data.
		return rec, nil
	}
	return rec, nil
}
