package outsvc

import "time"

// AmexAcqTxnWorkEntity maps AMEX_ACQ_TXN_WORK (the Amex outgoing staging
// table). Column names follow the ATD_* / ACQ_* DB convention; bindRow maps
// only columns present in the result set, so absent fields stay zero-valued.
//
// Field names mirror the Java AmexAcqTxnWorkEntity getters used by
// AmexGFSGOutgoingServiceImpl (TFH/TAB/TAA/TBT/TFS record builders).
type AmexAcqTxnWorkEntity struct {
	SerialNumber        int64      `db:"ATD_SER_NUMBER"`
	LastUpdated         time.Time  `db:"ATD_LAST_UPDATED"`
	UpdatedUser         int        `db:"ATD_UPDATED_USER"`
	InstitutionCode     int        `db:"ATD_INS_CODE"`
	IntCode             int        `db:"ATD_INT_CODE"`
	PrjSerNumber        *int64     `db:"ATD_PRJ_SER_NUMBER"`
	TxnRefSerNumber     *int64     `db:"ATD_TXN_REF_NUMBER"`
	TxnType             string     `db:"ATD_TXN_TYPE"`
	CardNumber          string     `db:"ATD_CARD_NUMBER"`
	ProcCode            string     `db:"ATD_PROC_CODE"`
	TxnAmount           float64    `db:"ATD_TXN_AMOUNT"`
	SurchargeAmount     float64    `db:"ATD_SCHG_AMOUNT"`
	LocalDateTime       *time.Time `db:"ATD_LOCAL_DATE_TIME"`
	PosDataCode         string     `db:"ATD_POS_DATA_CODE"`
	Mcc                 string     `db:"ATD_MCC"`
	Rrn                 string     `db:"ATD_RET_REF_NUMBER"`
	ApprovalCode        string     `db:"ATD_APPR_CODE"`
	TerminalId          string     `db:"ATD_TERMINAL_ID"`
	MerchantId          string     `db:"ATD_MERCHANT_ID"`
	MappedMid           string     `db:"ATD_MAPPED_MID"`
	MeName              string     `db:"ATD_ME_NAME"`
	MeCity              string     `db:"ATD_ME_CITY"`
	MePinCode           string     `db:"ATD_ME_ZIP_CODE"`
	MeCountry           string     `db:"ATD_ME_COUNTRY"`
	MotoEcomIndicator   string     `db:"ATD_ECOM_INDICATOR"`
	TxnCurCode          string     `db:"ATD_TXN_CUR_CODE"`
	CardSeqNumber       string     `db:"ATD_CARD_SEQ_NUMBER"`
	AppCryptogram       string     `db:"ATD_APP_CRYPTOGRAM"`
	CryptInfoData       string     `db:"ATD_CRYPT_INFO_DATA"`
	IssAppData          string     `db:"ATD_ISS_APP_DATA"`
	UpblNumber          string     `db:"ATD_UPBL_NUMBER"`
	AppTxnCounter       string     `db:"ATD_APP_TXN_COUNTER"`
	TrlVerResult        string     `db:"ATD_TRL_VER_RESULTS"`
	TxnDate             *time.Time `db:"ATD_TXN_DATE"`
	CryptAmount         float64    `db:"ATD_CRYPT_AMOUNT"`
	AppICProfile        string     `db:"ATD_APP_IC_PROFILE"`
	TrlConCode          string     `db:"ATD_TRL_CON_CODE"`
	ChipCashBack        float64    `db:"ATD_CASHBACK_AMOUNT"`
	TxnId               string     `db:"ATD_TXN_ID"`
	TrlBthNumber        int        `db:"ATD_TRL_BTH_NUMBER"`
	CardType            string     `db:"ATD_CARD_TYPE"`
	CardDomIntlFlag     string     `db:"ATD_DOM_INTL_FLAG"`
	DmsSmsMode          string     `db:"ATD_SMS_DMS_FLAG"`
	TrlType             string     `db:"ATD_TRL_TYPE"`
	CentreProcDate      *time.Time `db:"ATD_CENTRE_PROC_DATE"`
	OutFileDate         *time.Time `db:"ATD_OUT_FILE_DATE"`
	FileId              string     `db:"ATD_FILE_ID"`
	GenStatus           int        `db:"ATD_GEN_STATUS"`
	EncryptedCardNumber string     `db:"ATD_ENC_CARD_NUMBER"`
	ExpiryDate          string     `db:"ATD_EXPIRY_DATE"`
	Emv                 string     `db:"ATD_EMV"`
	LocationAddress     string     `db:"ATD_LOCATION_ADDRESS"`
	MeContactEmail      string     `db:"ATD_ME_CONTACT_EMAIL"`
	TrlLocation         string     `db:"ATD_TRL_LOCATION"`
	LocRegionCode       string     `db:"ATD_LOC_REG_CODE"`
	Stan                string     `db:"ATD_STAN"`
	InvoiceNumber       string     `db:"ATD_INVOICE_NUMBER"`
}

// AmexAcqTxnDataEntity maps AMEX_ACQ_TXN_DATA. Columns mirror the work table,
// so the work struct shape is reused (Java copies every column work->data).
type AmexAcqTxnDataEntity = AmexAcqTxnWorkEntity
