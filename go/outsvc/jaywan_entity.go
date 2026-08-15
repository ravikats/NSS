package outsvc

import "time"

// JaywanAcqTxnWorkEntity maps JAYWAN_ACQ_TXN_WORK.
type JaywanAcqTxnWorkEntity struct {
	SerialNumber       int64      `db:"JWN_SER_NUMBER"`
	LastUpdated        time.Time  `db:"JWN_LAST_UPDATED"`
	UpdatedUser        int        `db:"JWN_UPDATED_USER"`
	InstitutionCode    int        `db:"JWN_INS_CODE"`
	IntCode            int        `db:"JWN_INT_CODE"`
	PrjSerNumber       int64      `db:"JWN_PRJ_SER_NUMBER"`
	GenStatus          int        `db:"JWN_GEN_STATUS"`
	TxnRefNumber       int64      `db:"JWN_TXN_REF_NUMBER"`
	TxnType            string     `db:"JWN_TXN_TYPE"`
	TxnCode            string     `db:"JWN_TXN_CODE"`
	MessageTypeId      string     `db:"JWN_MSG_TYPE_ID"`
	FunctionCode       string     `db:"JWN_FUNC_CODE"`
	LocalDateTime      *time.Time `db:"JWN_LOCAL_DATE_TIME"`
	CardNumber         string     `db:"JWN_CARD_NUMBER"`
	AcqRefData         string     `db:"JWN_ACQ_REF_DATA"`
	ApprovalCode       string     `db:"JWN_APPR_CODE"`
	TerminalId         string     `db:"JWN_TERMINAL_ID"`
	TxnAmount          float64    `db:"JWN_TXN_AMOUNT"`
	SettledAmount      float64    `db:"JWN_SETL_AMOUNT"`
	BillAmount         float64    `db:"JWN_BILL_AMOUNT"`
	SurchargeAmount    float64    `db:"JWN_SCHG_AMOUNT"`
	ConvRate           float64    `db:"JWN_CONV_RATE"`
	TxnCurCode         string     `db:"JWN_TXN_CUR_CODE"`
	CashBackAmount     float64    `db:"JWN_CASHBACK_AMOUNT"`
	Rrn                string     `db:"JWN_RET_REF_NUMBER"`
	MerchantId         string     `db:"JWN_MERCHANT_ID"`
	MeName             string     `db:"JWN_ME_NAME"`
	MeCity             string     `db:"JWN_ME_CITY"`
	MeStateCode        string     `db:"JWN_ME_STATE_CODE"`
	MeCountry          string     `db:"JWN_ME_COUNTRY"`
	Mcc                string     `db:"JWN_MCC"`
	PosEntryMode       string     `db:"JWN_POS_ENTRY_MODE"`
	AcqinstIdCode      string     `db:"JWN_ACQ_INST_ID"`
	RevIndicator       string     `db:"JWN_REV_INDICATOR"`
	CardDomIntlFlag    string     `db:"JWN_DOM_INTL_FLAG"`
	TrlType            string     `db:"JWN_TRL_TYPE"`
	MeCategoryType     string     `db:"JWN_ME_CATEGORY_TYPE"`
	CardType           string     `db:"JWN_CARD_TYPE"`
	DmsSmsMode         string     `db:"JWN_SMS_DMS_FLAG"`
	CentreProcDate     *time.Time `db:"JWN_CENTRE_PROC_DATE"`
	FileProcDate       *time.Time `db:"JWN_OUT_FILE_DATE"`
	FileID             string     `db:"JWN_FILE_ID"`
	EncCardNumber      string     `db:"JWN_ENC_CARD_NUMBER"`
	ResponseCode       string     `db:"JWN_RESP_CODE"`
	MotoEcomIndicator  string     `db:"JWN_ECOM_INDICATOR"`
	SettlDate          *time.Time `db:"JWN_SETTL_DATE"`
	SettlIndicator     string     `db:"JWN_SETTL_INDICATOR"`
	PosConditionCode   string     `db:"JWN_POS_CONDITION_CODE"`
	FullPartialInd     string     `db:"JWN_FULL_PARTIAL_INDICATOR"`
}

// JaywanAcqTxnDataEntity maps JAYWAN_ACQ_TXN_DATA. Columns mirror the work
// table, so the work struct shape is reused (Java copies every column).
type JaywanAcqTxnDataEntity = JaywanAcqTxnWorkEntity

// JaywanNetworkDataEntity maps JAYWAN_NETWORK_DATA. The Go Jaywan outgoing
// flow joins the work rows to this table (by prj_ser_number + txn_ref_number)
// to source fields the work table lacks: JND_TRANS_IDENTIFIER -> nTxnId,
// JND_POS_TXN_STATUS -> nPosTxnStat, JND_PROC_CODE -> nProcCd.
type JaywanNetworkDataEntity struct {
	SerialNumber    int64   `db:"JND_SER_NUMBER"`
	PrjSerNumber    int64   `db:"JND_PRJ_SER_NUMBER"`
	TxnRefNumber    int64   `db:"JND_TXN_REF_NUMBER"`
	ProcCode        string  `db:"JND_PROC_CODE"`
	TransAmount     float64 `db:"JND_TRANS_AMOUNT"`
	RetrievalRefNo  string  `db:"JND_RETRIEVAL_REF_NO"`
	AcqInsId        string  `db:"JND_ACQ_INS_ID"`
	PosTxnStatus    string  `db:"JND_POS_TXN_STATUS"`
	TransIdentifier string  `db:"JND_TRANS_IDENTIFIER"`
	// PosCPInd has no JAYWAN_NETWORK_DATA column in UAT (chPresent lives in the
	// switch payload). It is populated by tests via the fake store; production
	// falls back to the spec default ("5") when empty.
	PosCPInd string
}
