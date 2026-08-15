// Package tlfsvc is the Go port of tlf-processing-service-java.
//
// It exposes the per-transaction online IRF flow that the Java
// TxnProcessingService.processOnlineTxn implements on the tlf interface,
// driven by the Kafka ingest (TxnFetchKafkaService port). Concretely:
//
//  1. parse a RequestVo ({payload: SwitchExtractVo})
//  2. map the payload onto a POS_TRANSACTIONS entity and INSERT it
//  3. irfStatusCheck -> if true, call the external irf-service over HTTP
//     (via the shared empay/irf/tlf client) and UPDATE the IRF columns;
//     if false, clear them
//  4. enqueue the scheme callback (irf-service owns IRF_CALLBACK)
//  5. return the response map (irdCode, fixed, percentage, amount,
//     domIntlFlag, cardType, description, status)
//
// The Crypt API (getCardNumber(tokenIdentifier)) is ported: when Service.Crypto
// is configured (decUrl set), switch_crypt_token is resolved to the real PAN
// before the irf-service call; otherwise the `pan` field the payload already
// carries is used (UAT/masked-pan mode).
package tlfsvc

// RequestVo wraps a SwitchExtractVo, mirroring the Java RequestVo
// ({payload: {...}}). The Kafka consumer parses these from the txn topic.
type RequestVo struct {
	Payload *SwitchExtractVo `json:"payload,omitempty"`
}

// SwitchExtractVo is the JSON shape consumed by the Kafka ingest. Field names
// and validation patterns mirror the Java VO of the same name.
type SwitchExtractVo struct {
	BankCode                  string   `json:"bank_id,omitempty"`
	Network                   string   `json:"sub_route,omitempty"`
	Scheme                    string   `json:"scheme,omitempty"`
	UniqueId                  string   `json:"ref_id,omitempty"`
	Mti                       string   `json:"switch_mti,omitempty"`
	CardNumber                string   `json:"pan,omitempty"`
	TokenIdentifier           string   `json:"switch_crypt_token,omitempty"`
	ProcessCode               string   `json:"processing_code,omitempty"`
	AmountTransaction         string   `json:"amount,omitempty"`
	AmountSettlement          string   `json:"settlement_amount,omitempty"`
	TxnDateTime               string   `json:"transmission_date,omitempty"`
	Stan                      string   `json:"stan,omitempty"`
	LocalTxnTime              string   `json:"local_time,omitempty"`
	LocalTxnDate              string   `json:"local_date,omitempty"`
	ExpiryDate                string   `json:"exp_date,omitempty"`
	SettlementDate            string   `json:"settlement_date,omitempty"`
	Mcc                       string   `json:"mcc,omitempty"`
	AcqInsConCode             string   `json:"acq_inst_country_code,omitempty"`
	PosEntryMode              string   `json:"pos_entry_mode,omitempty"`
	PanSequence               string   `json:"pan_sequence_number,omitempty"`
	PosCode                   string   `json:"pos_condition_code,omitempty"`
	TxnFeeAmount              string   `json:"txn_fee_amount,omitempty"`
	AcqInsIdCode              string   `json:"acquier_id,omitempty"`
	RetRefNumber              string   `json:"rrn,omitempty"`
	AuthIdResponse            string   `json:"auth_code,omitempty"`
	ResponseCode              string   `json:"network_response_code,omitempty"`
	ServiceRestrictionCode    string   `json:"service_restriction_code,omitempty"`
	TerminalId                string   `json:"terminal_id,omitempty"`
	MerchantId                string   `json:"merchant_id,omitempty"`
	CardAcceptorName          string   `json:"card_acceptor_name,omitempty"`
	CardAcceptorStreetAddress string   `json:"card_acceptor_st_addr,omitempty"`
	CardAcceptorCity          string   `json:"card_acceptor_city,omitempty"`
	CardAcceptorStateCode     string   `json:"card_acceptor_state_code,omitempty"`
	CardAcceptorPinCode       string   `json:"card_acceptor_pin_code,omitempty"`
	CardAcceptorCountryCode   string   `json:"card_acceptor_country_code,omitempty"`
	TxnCurrencyCode           string   `json:"currency_code,omitempty"`
	SettleCurrencyCode        string   `json:"settlement_code,omitempty"`
	CashBackAmount            string   `json:"additional_amount,omitempty"`
	TxnSource                 string   `json:"channel,omitempty"`
	ServerDateTime            string   `json:"server_date_time,omitempty"`
	SettlementIndicator       string   `json:"settlement_indicator,omitempty"`
	OnusOffusIndicator        string   `json:"onus_offus_indicator,omitempty"`
	SmsDmsIndicator           string   `json:"sms_dms_indicator,omitempty"`
	MeCategoryType            string   `json:"merchant_category_type,omitempty"`
	AuthAmount                string   `json:"auth_amount,omitempty"`
	OriginalRRN               string   `json:"original_rrn,omitempty"`
	AmexMerchantId            string   `json:"amex_merchant_id,omitempty"`
	MerchantContactInfo       string   `json:"merchant_contact_information,omitempty"`
	LocationRegionCode        string   `json:"location_region_code,omitempty"`
	InvoiceNumber             string   `json:"invoiceNumber,omitempty"`
	TipAmount                 string   `json:"tip_amount,omitempty"`
	De48JSON                  *De48Vo  `json:"de48_json,omitempty"`
	De55JSON                  *De55Vo  `json:"de55_json,omitempty"`
	De60JSON                  *De60Vo  `json:"de60_json,omitempty"`
	De61JSON                  *De61Vo  `json:"de61_json,omitempty"`
	De62JSON                  *De62Vo  `json:"de62_json,omitempty"`
	De63JSON                  *De63Vo  `json:"de63_json,omitempty"`
	De111JSON                 *De111Vo `json:"de111_json,omitempty"`
	De126JSON                 *De126Vo `json:"de126_json,omitempty"`
	Dcc                       *DccVo   `json:"dcc,omitempty"`
}

// De48Vo mirrors the Java de48Vo (JSON keys are the GSON field names).
type De48Vo struct {
	Maid           string `json:"maid,omitempty"`
	SetIndicator   string `json:"setIndicator,omitempty"`
	PinServiceCode string `json:"pinServiceCode,omitempty"`
	MeCountryCode  string `json:"meCountryCode,omitempty"`
	MposAccDevType string `json:"mposAccDevType,omitempty"`
}

// De55Vo mirrors the Java de55Vo (EMV tag fields, used when checkForEmv is true).
type De55Vo struct {
	EightyTwo  string `json:"eightyTwo,omitempty"`
	EightyFour string `json:"eightyFour,omitempty"`
	NinetyOne  string `json:"ninetyOne,omitempty"`
	NinetyFive string `json:"ninetyFive,omitempty"`
	NineA      string `json:"nineA,omitempty"`
	NineC      string `json:"nineC,omitempty"`
	FiveF2A    string `json:"fiveF2A,omitempty"`
	NineF02    string `json:"nineF02,omitempty"`
	NineF03    string `json:"nineF03,omitempty"`
	NineF09    string `json:"nineF09,omitempty"`
	NineF10    string `json:"nineF10,omitempty"`
	NineF1A    string `json:"nineF1A,omitempty"`
	NineF1E    string `json:"nineF1E,omitempty"`
	NineF26    string `json:"nineF26,omitempty"`
	NineF27    string `json:"nineF27,omitempty"`
	NineF33    string `json:"nineF33,omitempty"`
	NineF34    string `json:"nineF34,omitempty"`
	NineF35    string `json:"nineF35,omitempty"`
	NineF36    string `json:"nineF36,omitempty"`
	NineF37    string `json:"nineF37,omitempty"`
	NineF53    string `json:"fiveF53,omitempty"`
	NineF6E    string `json:"nineF6E,omitempty"`
}

// De60Vo mirrors the Java de60_jsonVo (Visa terminal data).
type De60Vo struct {
	TerminalCapability     string `json:"terminalCapability,omitempty"`
	EcomIndicator          string `json:"ecomIndicator,omitempty"`
	ChIdMethod             string `json:"chIdMethod,omitempty"`
	AcceptanceTrlIndicator string `json:"acceptanceTrlIndicator,omitempty"`
}

// De61Vo mirrors the Java de61Vo (Mastercard cardholder terminal data).
type De61Vo struct {
	CardInputAbility      string `json:"cardInputAbility,omitempty"`
	CardInputCapability   string `json:"cardInputCapability,omitempty"`
	CardCaptureCapability string `json:"cardCaptureCapability,omitempty"`
	OperationalEnv1       string `json:"operationalEnv1,omitempty"`
	OperationalEnv3       string `json:"operationalEnv3,omitempty"`
	ChPresent             string `json:"chPresent,omitempty"`
	CardPresent           string `json:"cardPresent,omitempty"`
}

// De62Vo mirrors the Java de62Vo (Visa issuer/private use data).
type De62Vo struct {
	TxnId                 string `json:"txnId,omitempty"`
	AuthCharecteresticId  string `json:"authCharecteresticId,omitempty"`
	ValidationCode        string `json:"validationCode,omitempty"`
	MarketSpecAuthDataInd string `json:"marketSpecAuthDataInd,omitempty"`
	Mvv                   string `json:"mvv,omitempty"`
	ProductId             string `json:"productId,omitempty"`
	SpendQualificationInd string `json:"spendQualificationInd,omitempty"`
}

// De63Vo mirrors the Java de63Vo (network data / fee / reimbursement).
type De63Vo struct {
	ReImbursementAttribute string `json:"reImbursementAttribute,omitempty"`
	FeePgmIndicator        string `json:"feePgmIndicator,omitempty"`
	NetworkData            string `json:"networkData,omitempty"`
}

// De111Vo mirrors the Java de111Vo (account funding source).
type De111Vo struct {
	AccountFundingSource string `json:"accountFundingSource,omitempty"`
}

// De126Vo mirrors the Java de126Vo (pos env for oprtEnvironment on Visa).
type De126Vo struct {
	PosEnv string `json:"posEnv,omitempty"`
}

// DccVo mirrors the Java dccVo.
type DccVo struct {
	DccAmount    string `json:"dcc_amount,omitempty"`
	DccCurrency  string `json:"dcc_currency,omitempty"`
	DccIndicator string `json:"dcc_indicator,omitempty"`
	ExchangeRate string `json:"exchange_rate,omitempty"`
}
