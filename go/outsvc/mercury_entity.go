package outsvc

import "time"

// MercuryAcqTxnWorkEntity maps MERCURY_ACQ_TXN_WORK (the Mercury outgoing
// staging table). The Java field set is mirrored 1:1 (MAT_* columns).
type MercuryAcqTxnWorkEntity struct {
	SerialNumber            int64      `db:"MAT_SER_NUMBER"`
	LastUpdated             time.Time  `db:"MAT_LAST_UPDATED"`
	UpdatedUser             int        `db:"MAT_UPDATED_USER"`
	InstitutionCode         int        `db:"MAT_INS_CODE"`
	IntCode                 int        `db:"MAT_INT_CODE"`
	PrjSerNumber            int64      `db:"MAT_PRJ_SER_NUMBER"`
	GenStatus               int        `db:"MAT_GEN_STATUS"`
	TxnRefNumber            int64      `db:"MAT_TXN_REF_NUMBER"`
	Rrn                     string     `db:"MAT_RET_REF_NUMBER"`
	MerchantId              string     `db:"MAT_MERCHANT_ID"`
	TerminalId              string     `db:"MAT_TERMINAL_ID"`
	TxnType                 string     `db:"MAT_TXN_TYPE"`
	CardNumber              string     `db:"MAT_CARD_NUMBER"`
	TxnAmount               float64    `db:"MAT_TXN_AMOUNT"`
	SurchargeAmount         float64    `db:"MAT_SCHG_AMOUNT"`
	LocalDateTime           *time.Time `db:"MAT_LOCAL_DATE_TIME"`
	TxnDate                 *time.Time `db:"MAT_TXN_DATE"`
	ChargeType              string     `db:"MAT_CHARGE_TYPE"`
	TypeOfCharge            string     `db:"MAT_TYPE_OF_CHARGE"`
	GeoArea                 string     `db:"MAT_GEO_AREA"`
	MeName                  string     `db:"MAT_ME_NAME"`
	MeCity                  string     `db:"MAT_ME_CITY"`
	MeCountry               string     `db:"MAT_ME_COUNTRY"`
	CardAccepStreetAddress  string     `db:"MAT_CARD_ACC_STREET_ADDRESS"`
	CardAccepStateCode      string     `db:"MAT_CARD_ACC_STATE_CODE"`
	MePinCode               string     `db:"MAT_ME_ZIP_CODE"`
	EstPhoneNumber          string     `db:"MAT_EST_PHONE_NO"`
	Mcc                     string     `db:"MAT_MCC"`
	CardType                string     `db:"MAT_CARD_TYPE"`
	ApprovalCode            string     `db:"MAT_APPR_CODE"`
	TxnCurrencyExponent     int        `db:"MAT_TXN_CURR_EXP"`
	TxnCurCode              string     `db:"MAT_TXN_CUR_CODE"`
	MercuryRefId            string     `db:"MAT_MERCURY_REF_ID"`
	CardDomIntlFlag         string     `db:"MAT_DOM_INTL_FLAG"`
	DmsSmsMode              string     `db:"MAT_SMS_DMS_FLAG"`
	EncryptedCardNumber     string     `db:"MAT_ENC_CARD_NUMBER"`
	OrgInstIdCode           string     `db:"MAT_ORG_INST_ID_CODE"`
	TrlType                 string     `db:"MAT_TRL_TYPE"`
	SettlementIndicator     string     `db:"MAT_SETL_INDICATOR"`
	TxnFeeAmount            float64    `db:"MAT_TXN_FEE_AMOUNT"`
	MotoEcomIndicator       string     `db:"MAT_ECOM_INDICATOR"`
	ResponseCode            string     `db:"MAT_RESP_CODE"`
	AcqinstIdCode           string     `db:"MAT_ACQ_INST_ID_CODE"`
	AcqRefData              string     `db:"MAT_ACQ_REF_DATA"`
	CardInputMode           string     `db:"MAT_CARD_INPUT_MODE"`
	CardInputCapability     string     `db:"MAT_CARD_INPUT_CAPABILITY"`
	CardSeqNumber           string     `db:"MAT_CARD_SEQ_NUMBER"`
	AppICProfile            string     `db:"MAT_APP_IC_PROFILE"`
	AppTxnCounter           string     `db:"MAT_APP_TXN_COUNTER"`
	AppCryptogram           string     `db:"MAT_APP_CRYPTOGRAM"`
	CryptAmount             float64    `db:"MAT_CRYPT_AMOUNT"`
	CashBackAmount          float64    `db:"MAT_CASHBACK_AMOUNT"`
	CryptInfoData           string     `db:"MAT_CRYPT_INFO_DATA"`
	CvmResult               string     `db:"MAT_CVM_RESULTS"`
	DedicatedFileName       string     `db:"MAT_DEDICATED_FILE_NAME"`
	IfdSerNumber            string     `db:"MAT_IFD_SER_NUMBER"`
	IssAppData              string     `db:"MAT_ISS_APP_DATA"`
	IssAuthData             string     `db:"MAT_ISS_AUTH_DATA"`
	TrlConCode              string     `db:"MAT_TRL_CON_CODE"`
	TrlAppVerNumber         string     `db:"MAT_TRL_APP_VER_NUMBER"`
	ChipTrlCapabilities     string     `db:"MAT_CHIP_TRL_CAPABILITIES"`
	ChipTrlType             string     `db:"MAT_CHIP_TRL_TYPE"`
	TrlVerResult            string     `db:"MAT_TRL_VER_RESULTS"`
	ChipTxnDate             string     `db:"MAT_CHIP_TXN_DATE"`
	ChipTxnType             string     `db:"MAT_CHIP_TXN_TYPE"`
	ChipCurCode             string     `db:"MAT_CHIP_CUR_CODE"`
	UpblNumber              string     `db:"MAT_UPBL_NUMBER"`
	CentreProcDate          *time.Time `db:"MAT_CENTRE_PROC_DATE"`
	FileProcDate            *time.Time `db:"MAT_OUT_FILE_DATE"`
	FileID                  string     `db:"MAT_FILE_ID"`
	CardPresent             string     `db:"MAT_CARD_PRESENT"`
	ChPresent               string     `db:"MAT_CH_PRESENT"`
	PanSequenceNumber       string     `db:"MAT_APP_PAN_SEQ_NUMBER"`
	PosEntryMode            string     `db:"MAT_POS_ENTRY_MODE"`
}

// MercuryAcqTxnDataEntity maps MERCURY_ACQ_TXN_DATA. Columns mirror the work
// table, so the work struct shape is reused (Java copies every column).
type MercuryAcqTxnDataEntity = MercuryAcqTxnWorkEntity
