package outsvc

import "time"

// UnionPayAcqTxnWorkEntity maps UP_ACQ_TXN_WORK (the UnionPay outgoing
// staging table). Columns follow the UPT_* convention mirroring the other
// network work tables. Fields absent from the physical table stay zero
// because bindRow only maps columns present in the result set.
type UnionPayAcqTxnWorkEntity struct {
	SerialNumber         int64      `db:"UPT_SER_NUMBER"`
	LastUpdated          time.Time  `db:"UPT_LAST_UPDATED"`
	UpdatedUser          int        `db:"UPT_UPDATED_USER"`
	InstitutionCode      int        `db:"UPT_INS_CODE"`
	IntCode              int        `db:"UPT_INT_CODE"`
	PrjSerNumber         int64      `db:"UPT_PRJ_SER_NUMBER"`
	GenStatus            int        `db:"UPT_GEN_STATUS"`
	TxnRefNumber         int64      `db:"UPT_TXN_REF_NUMBER"`
	Rrn                  string     `db:"UPT_RET_REF_NUMBER"`
	MerchantId           string     `db:"UPT_MERCHANT_ID"`
	TerminalId           string     `db:"UPT_TERMINAL_ID"`
	TxnType              string     `db:"UPT_TXN_TYPE"`
	CardNumber           string     `db:"UPT_CARD_NUMBER"`
	TxnAmount            float64    `db:"UPT_TXN_AMOUNT"`
	SurchargeAmount      float64    `db:"UPT_SCHG_AMOUNT"`
	LocalDateTime        *time.Time `db:"UPT_LOCAL_DATE_TIME"`
	TxnDate              *time.Time `db:"UPT_TXN_DATE"`
	MeName               string     `db:"UPT_ME_NAME"`
	MeCity               string     `db:"UPT_ME_CITY"`
	MeCountry            string     `db:"UPT_ME_COUNTRY"`
	Mcc                  string     `db:"UPT_MCC"`
	ApprovalCode         string     `db:"UPT_APPR_CODE"`
	TxnCurCode           string     `db:"UPT_TXN_CUR_CODE"`
	StanNumber           string     `db:"UPT_STAN"`
	OrgInstIdCode        string     `db:"UPT_ORG_INST_ID_CODE"`
	AcqinstIdCode        string     `db:"UPT_ACQ_INST_ID_CODE"`
	FwdInstIdCode        string     `db:"UPT_FWD_INST_ID_CODE"`
	AcqRefData           string     `db:"UPT_ACQ_REF_DATA"`
	ResponseCode         string     `db:"UPT_RESP_CODE"`
	ReceivingInstIdCode  string     `db:"UPT_RECV_INST_ID_CODE"`
	PosConditionCode     string     `db:"UPT_POS_CONDITION_CODE"`
	TxnInitiatingChannel string     `db:"UPT_TXN_INIT_CHANNEL"`
	PricingSchemeCode    string     `db:"UPT_PRICING_SCHEME_CODE"`
	EncryptedCardNumber  string     `db:"UPT_ENC_CARD_NUMBER"`
	CardInputMode        string     `db:"UPT_CARD_INPUT_MODE"`
	CardInputCapability  string     `db:"UPT_CARD_INPUT_CAPABILITY"`
	CardSeqNumber        string     `db:"UPT_CARD_SEQ_NUMBER"`
	AppICProfile         string     `db:"UPT_APP_IC_PROFILE"`
	AppTxnCounter        string     `db:"UPT_APP_TXN_COUNTER"`
	AppCryptogram        string     `db:"UPT_APP_CRYPTOGRAM"`
	CryptAmount          float64    `db:"UPT_CRYPT_AMOUNT"`
	CashBackAmount       float64    `db:"UPT_CASHBACK_AMOUNT"`
	CryptInfoData        string     `db:"UPT_CRYPT_INFO_DATA"`
	CvmResult            string     `db:"UPT_CVM_RESULTS"`
	DedicatedFileName    string     `db:"UPT_DEDICATED_FILE_NAME"`
	IfdSerNumber         string     `db:"UPT_IFD_SER_NUMBER"`
	IssAppData           string     `db:"UPT_ISS_APP_DATA"`
	IssAuthData          string     `db:"UPT_ISS_AUTH_DATA"`
	TrlConCode           string     `db:"UPT_TRL_CON_CODE"`
	TrlAppVerNumber      string     `db:"UPT_TRL_APP_VER_NUMBER"`
	ChipTrlCapabilities  string     `db:"UPT_CHIP_TRL_CAPABILITIES"`
	ChipTrlType          string     `db:"UPT_CHIP_TRL_TYPE"`
	TrlVerResult         string     `db:"UPT_TRL_VER_RESULTS"`
	ChipTxnDate          string     `db:"UPT_CHIP_TXN_DATE"`
	ChipTxnType          string     `db:"UPT_CHIP_TXN_TYPE"`
	ChipCurCode          string     `db:"UPT_CHIP_CUR_CODE"`
	UpblNumber           string     `db:"UPT_UPBL_NUMBER"`
	CentreProcDate       *time.Time `db:"UPT_CENTRE_PROC_DATE"`
	FileProcDate         *time.Time `db:"UPT_OUT_FILE_DATE"`
	FileID               string     `db:"UPT_FILE_ID"`
	CardPresent          string     `db:"UPT_CARD_PRESENT"`
	ChPresent            string     `db:"UPT_CH_PRESENT"`
	PanSequenceNumber    string     `db:"UPT_APP_PAN_SEQ_NUMBER"`
	PosEntryMode         string     `db:"UPT_POS_ENTRY_MODE"`
	SettlementIndicator  string     `db:"UPT_SETL_INDICATOR"`
	TxnFeeAmount         float64    `db:"UPT_TXN_FEE_AMOUNT"`
}

// UnionPayAcqTxnDataEntity maps UP_ACQ_TXN_DATA. Columns mirror the work
// table, so the work struct shape is reused.
type UnionPayAcqTxnDataEntity = UnionPayAcqTxnWorkEntity
