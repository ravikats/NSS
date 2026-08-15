package outsvc

import "time"

// VisaAcqTxnWorkEntity maps VISA_ACQ_TXN_WORK (VTD_* columns). Char-typed
// columns use string to mirror Java Character + bindRow semantics (empty string
// <-> NULL on read; see store.go bindRow which skips NULL raw values, leaving
// the Go zero value "").
type VisaAcqTxnWorkEntity struct {
	SerNumber             int64      `db:"VTD_SER_NUMBER"`
	LastUpdated           time.Time  `db:"VTD_LAST_UPDATED"`
	UpdatedUser           int        `db:"VTD_UPDATED_USER"`
	InstitutionCode       int        `db:"VTD_INS_CODE"`
	IntCode               int        `db:"VTD_INT_CODE"`
	PrjSerNumber          int        `db:"VTD_PRJ_SER_NUMBER"`
	TxnRefNumber          int64      `db:"VTD_TXN_REF_NUMBER"`
	TxnType               string     `db:"VTD_TXN_TYPE"`
	TxnCode               string     `db:"VTD_TXN_CODE"`
	ProcCode              string     `db:"VTD_PROC_CODE"`
	Arn                   string     `db:"VTD_ACQ_REF_NUMBER"`
	AcqBussId             string     `db:"VTD_ACQ_BUSS_ID"`
	PurchaseDate          *time.Time `db:"VTD_PURCHASE_DATE"`
	TxnAmount             float64    `db:"VTD_TXN_AMOUNT"`
	SchgAmount            float64    `db:"VTD_SCHG_AMOUNT"`
	TxnCurCode            string     `db:"VTD_TXN_CUR_CODE"`
	MeName                string     `db:"VTD_ME_NAME"`
	MeCity                string     `db:"VTD_ME_CITY"`
	MeCountry             string     `db:"VTD_ME_COUNTRY"`
	Mcc                   string     `db:"VTD_MCC"`
	UsageCode             string     `db:"VTD_USAGE_CODE"`
	ReasonCode            string     `db:"VTD_REASON_CODE"`
	SetlFlag              string     `db:"VTD_SETL_FLAG"`
	ApprovalCode          string     `db:"VTD_APPR_CODE"`
	TerminalCapability    string     `db:"VTD_TRL_CAPABILITY"`
	ChIdMethod            string     `db:"VTD_CH_ID_METHOD"`
	CollOnlyFlag          string     `db:"VTD_COLL_ONLY_FLAG"`
	PosEntryMode          string     `db:"VTD_POS_ENTRY_MODE"`
	ReimAttribute         string     `db:"VTD_REIMB_ATTRIBUTE"`
	MemberText            string     `db:"VTD_MEMBER_TEXT"`
	FeePrgIndicator       string     `db:"VTD_FEE_PRG_INDICATOR"`
	MerchantId            string     `db:"VTD_MERCHANT_ID"`
	TerminalId            string     `db:"VTD_TERMINAL_ID"`
	MotoEcomIndicator     string     `db:"VTD_MOTO_ECOM_INDICATOR"`
	Stan                  string     `db:"VTD_STAN"`
	AccSelection          string     `db:"VTD_ACC_SELECTION"`
	CashbackAmount        *float64   `db:"VTD_CASHBACK_AMOUNT"`
	TxnId                 string     `db:"VTD_TXN_ID"`
	AuthAmount            *float64   `db:"VTD_AUTH_AMOUNT"`
	RespCode              string     `db:"VTD_RESP_CODE"`
	TrlTxnDate            *time.Time `db:"VTD_TRL_TXN_DATE"`
	TrlCapProfile         string     `db:"VTD_TRL_CAP_PROFILE"`
	TrlCountryCode        string     `db:"VTD_TRL_CON_CODE"`
	UpblNumber            string     `db:"VTD_UPBL_NUMBER"`
	CardSeqNumber         string     `db:"VTD_CARD_SEQ_NUMBER"`
	AppTxnCounter         string     `db:"VTD_APP_TXN_COUNTER"`
	AppIcProfile          string     `db:"VTD_APP_IC_PROFILE"`
	AppCryptogram         string     `db:"VTD_APP_CRYPTOGRAM"`
	IssAppDataB2          string     `db:"VTD_ISS_APP_DATA_B2"`
	IssAppDataB3          string     `db:"VTD_ISS_APP_DATA_B3"`
	IssAppDataB4          string     `db:"VTD_ISS_APP_DATA_B4"`
	IssAppDataB8          string     `db:"VTD_ISS_APP_DATA_B8"`
	IssAppDataB9          string     `db:"VTD_ISS_APP_DATA_B9"`
	IssAppDataB1          string     `db:"VTD_ISS_APP_DATA_B1"`
	IssAppDataB17         string     `db:"VTD_ISS_APP_DATA_B17"`
	IssAppDataB18         string     `db:"VTD_ISS_APP_DATA_B18"`
	TrlVerResult          string     `db:"VTD_TRL_VER_RESULTS"`
	CryptAmount           *float64   `db:"VTD_CRYPT_AMOUNT"`
	FormFactorIndicator   string     `db:"VTD_FORM_FACT_INDICATOR"`
	IssScriptResult       string     `db:"VTD_ISS_SCRIPT_RESULTS"`
	ServiceCode           string     `db:"VTD_SERVICE_CODE"`
	RetRefNumber          string     `db:"VTD_RET_REF_NUMBER"`
	TxnFeeAmount          *float64   `db:"VTD_TXN_FEE_AMOUNT"`
	CardType              string     `db:"VTD_CARD_TYPE"`
	DomIntlFlag           string     `db:"VTD_DOM_INTL_FLAG"`
	DmsSmsMode            string     `db:"VTD_SMS_DMS_FLAG"`
	TrlType               string     `db:"VTD_TRL_TYPE"`
	CentreProcDate        *time.Time `db:"VTD_CENTRE_PROC_DATE"`
	OutFileDate           *time.Time `db:"VTD_OUT_FILE_DATE"`
	FileId                string     `db:"VTD_FILE_ID"`
	GeneralStatus         int        `db:"VTD_GEN_STATUS"`
	EncCardNumber         string     `db:"VTD_ENC_CARD_NUMBER"`
	PosEnvironment        string     `db:"VTD_POS_ENVIRONMENT"`
	VisaToken             string     `db:"VTD_VISA_TOKEN"`
	AuthCharIndicator     string     `db:"VTD_AUTH_CHAR_INDICATOR"`
	AccFundSource         string     `db:"VTD_ACC_FUND_SOURCE"`
	MarketSpecDataInd     string     `db:"VTD_MARKET_SPEC_DATA_IND"`
	ProductId             string     `db:"VTD_PRODUCT_ID"`
	ValidationCode        string     `db:"VTD_VALIDATION_CODE"`
	SpendQualiIndictor    string     `db:"VTD_SPEND_QUALI_IND"`
	SenderName            string     `db:"VTD_SENDER_NAME"`
	RecipientName         string     `db:"VTD_RECIPIENT_NAME"`
	BussAppId             string     `db:"VTD_BUSS_APP_ID"`
	SenderAccount         string     `db:"VTD_SENDER_ACCOUNT"`
	DccIndicator          string     `db:"VTD_DCC_INDICATOR"`
	Network               string     `db:"VTD_NETWORK"`
	DccAmount             *float64   `db:"VTD_DCC_AMOUNT"`
	DccCurrency           string     `db:"VTD_DCC_CURRENCY"`
	AcceptanceTrlIndicator string    `db:"VTD_ACC_TRL_INDICATOR"`
}

// VisaAcqTxnDataEntity maps VISA_ACQ_TXN_DATA. Java copies every work column
// into the data table, so we reuse the work struct shape.
type VisaAcqTxnDataEntity = VisaAcqTxnWorkEntity
