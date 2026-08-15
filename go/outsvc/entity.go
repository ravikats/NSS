package outsvc

import "time"

// Entities mirror the Java JPA entities. Field types follow the DB shapes:
// Oracle NUMBER -> int64/int/float64, VARCHAR2 -> string, DATE/TIMESTAMP -> time.Time.
// The `db` tag carries the physical column name.

// OutGoingFileProcessingEntity maps OUT_FILE_LOG.
type OutGoingFileProcessingEntity struct {
	SerialNumber              int64      `db:"OFL_SER_NUMBER"`
	LastUpdated               time.Time  `db:"OFL_LAST_UPDATED"`
	UpdatedUser               int        `db:"OFL_UPDATED_USER"`
	InstitutionCode           int        `db:"OFL_INS_CODE"`
	InterfaceCode             int        `db:"OFL_INT_CODE"`
	FormatCode                int        `db:"OFL_FOR_CODE"`
	FileName                  string     `db:"OFL_FILE_NAME"`
	GeneratedDate             time.Time  `db:"OFL_GNERATE_DATE"`
	GeneratedStatus           int        `db:"OFL_GENERATE_STATUS"`
	ProcDate                  *time.Time `db:"OFL_PROC_DATE"`
	BussDate                  time.Time  `db:"OFL_BUSS_DATE"`
	PrjSerNumber              *int64     `db:"OFL_PRJ_SER_NUMBER"`
	TotalTxnCount             *int64     `db:"OFL_TOT_TXN_COUNT"`
	TotalTxnAmount            *float64   `db:"OFL_TOT_TXN_AMOUNT"`
	TotalAcceptanceTxnCount   *int64     `db:"OFL_TOT_ACCP_TXN_COUNT"`
	TotalAcceptanceTxnAmount  *float64   `db:"OFL_TOT_ACCP_TXN_AMOUNT"`
	FileId                    *string    `db:"OFL_FILE_ID"`
}

// InterfacesEntity maps INTERFACES.
type InterfacesEntity struct {
	InterfaceCode int       `db:"INT_CODE"`
	Type          string    `db:"INT_TYPE"`
	Name          string    `db:"INT_NAME"`
	Category      string    `db:"INT_CATEGORY"`
	InstitutionCode int     `db:"INT_INS_CODE"`
}

// FileFormatsEntity maps FILE_FORMATS.
type FileFormatsEntity struct {
	Code           int       `db:"FOR_CODE"`
	LastUpdated    time.Time `db:"FOR_LAST_UPDATED"`
	UpdatedUser    int       `db:"FOR_UPDATED_USER"`
	InstitutionCode int      `db:"FOR_INS_CODE"`
	Type           string    `db:"FOR_TYPE"`
	IntType        string    `db:"FOR_INT_TYPE"`
	Description    string    `db:"FOR_DESCRIPTION"`
	SystemCode     int       `db:"FOR_SYSTEM_CODE"`
	FileType       string    `db:"FOR_FILE_TYPE"`
	FieldSeperator string    `db:"FOR_FIELD_SEPERATOR"`
}

// BusinessDateEntity maps BUSINESS_DATE.
type BusinessDateEntity struct {
	InstitutionCode int       `db:"BDT_INS_CODE"`
	LastUpdated     time.Time `db:"BDT_LAST_UPDATED"`
	UpdatedUser     int       `db:"BDT_UPDATED_USER"`
	BusinessDate    time.Time `db:"BDT_BUSINESS_DATE"`
	LastBusinessDate time.Time `db:"BDT_LAST_BUSINESS_DATE"`
}

// AcquirerBinsEntity maps ACQUIRER_BINS.
type AcquirerBinsEntity struct {
	Bin              string    `db:"ACQ_BIN"`
	LastUpdated      time.Time `db:"ACQ_LAST_UPDATED"`
	UpdatedUser      int       `db:"ACQ_UPDATED_USER"`
	InstitutionCode  int       `db:"ACQ_INS_CODE"`
	BinType          string    `db:"ACQ_BIN_TYPE"`
	ArnSeqNo         *int64    `db:"ACQ_ARN_SEQ_NO"`
	OutfileDate      *time.Time `db:"ACQ_OUT_FILE_DATE"`
	McIcaNo          *string   `db:"ACQ_MC_ICA_NO"`
	OutBatchNo       *int64    `db:"ACQ_OUT_BATCH_NO"`
	OutFileId        *string   `db:"ACQ_OUT_FILE_ID"`
	OutFileSeq       int       `db:"ACQ_OUT_FILE_SEQ"`
	ParticipantId    *string   `db:"ACQ_PARTICIPANT_ID"`
	TransactionType  *string   `db:"ACQ_TRANSACTION_TYPE"`
}

// OutgoingSummaryEntity maps OUTGOING_SUMMARY.
type OutgoingSummaryEntity struct {
	SerialNumber    int64     `db:"OTS_SER_NUMBER"`
	LastUpdated     time.Time `db:"OTS_LAST_UPDATED"`
	UpdatedUser     int       `db:"OTS_UPDATED_USER"`
	InstitutionCode int       `db:"OTS_INS_CODE"`
	InterfaceCode   int       `db:"OTS_INT_CODE"`
	OutFileDate     time.Time `db:"OTS_OUT_FILE_DATE"`
	FileId          string    `db:"OTS_FILE_ID"`
	RefSerialNumber int64     `db:"OTS_OFL_SER_NUMBER"`
	MessageTypeId   string    `db:"OTS_MTI"`
	FunctionCode    string    `db:"OTS_FUNCTION_CODE"`
	ProcCode        string    `db:"OTS_PROC_CODE"`
	Count           int       `db:"OTS_COUNT"`
	Amount          float64   `db:"OTS_AMOUNT"`
	SurchargeAmount float64   `db:"OTS_SCHG_AMOUNT"`
	NetAmount       float64   `db:"OTS_NET_AMOUNT"`
	GeneralStatus   int       `db:"OTS_GEN_STATUS"`
}

// OutgoingReportDataWorkEntity maps OUTGOING_REPORT_DATA_WORK.
type OutgoingReportDataWorkEntity struct {
	SerialNumber    int64     `db:"ORD_SER_NUMBER"`
	LastUpdated     time.Time `db:"ORD_LAST_UPDATED"`
	UpdatedUser     int       `db:"ORD_UPDATED_USER"`
	InstitutionCode int       `db:"ORD_INS_CODE"`
	OutFileId       string    `db:"ORD_OUT_FILE_ID"`
	OutgoingDate    time.Time `db:"ORD_OUTGOING_DATE"`
	TxnDate         time.Time `db:"ORD_TXN_DATE"`
	Network         string    `db:"ORD_NETWORK"`
	PosOrgPg        string    `db:"ORD_POS_OR_PG"`
	TxnType         string    `db:"ORD_TXN_TYPE"`
	Count           int64     `db:"ORD_COUNT"`
	Amount          float64   `db:"ORD_AMOUNT"`
}

// McAcqTxnWorkEntity maps MC_ACQ_TXN_WORK.
type McAcqTxnWorkEntity struct {
	SerNumber                 int64      `db:"MCT_SER_NUMBER"`
	LastUpdated               time.Time  `db:"MCT_LAST_UPDATED"`
	UpdatedUser               int        `db:"MCT_UPDATED_USER"`
	InstitutionCode           int        `db:"MCT_INS_CODE"`
	IntCode                   int        `db:"MCT_INT_CODE"`
	PrjSerNumber              int        `db:"MCT_PRJ_SER_NUMBER"`
	GeneralStatus             int        `db:"MCT_GEN_STATUS"`
	TxnRefSerNumber           int64      `db:"MCT_TXN_REF_NUMBER"`
	TxnType                   string     `db:"MCT_TXN_TYPE"`
	FileID                    string     `db:"MCT_FILE_ID"`
	MessageTypeId             string     `db:"MCT_MSG_TYPE_ID"`
	ProcCode                  string     `db:"MCT_PROC_CODE"`
	FunctionCode              string     `db:"MCT_FUNC_CODE"`
	TxnAmount                 float64    `db:"MCT_TXN_AMOUNT"`
	SurchargeAmount           float64    `db:"MCT_SCHG_AMOUNT"`
	FileProcDate              *time.Time `db:"FILE_PROC_DATE"`
	OutFileDate               *time.Time `db:"MCT_OUT_FILE_DATE"`
	CardNumber                string     `db:"MCT_CARD_NUMBER"`
	LocalDateTime             *time.Time `db:"MCT_LOCAL_DATE_TIME"`
	ExpiryDate                string     `db:"MCT_EXPIRY_DATE"`
	PosDataCode               string     `db:"MCT_POS_DATA_CODE"`
	MsgReasonCode             string     `db:"MCT_MSG_REASON_CODE"`
	Mcc                       string     `db:"MCT_MCC"`
	AcqRefData                string     `db:"MCT_ACQ_REF_DATA"`
	AcqinstIdCode             string     `db:"MCT_ACQ_INST_ID_CODE"`
	Rrn                       string     `db:"MCT_RET_REF_NUMBER"`
	ApprovalCode              string     `db:"MCT_APPR_CODE"`
	ResponseCode              string     `db:"MCT_RESP_CODE"`
	ServiceCode               string     `db:"MCT_SERVICE_CODE"`
	TerminalId                string     `db:"MCT_TERMINAL_ID"`
	MerchantId                string     `db:"MCT_MERCHANT_ID"`
	MeName                    string     `db:"MCT_ME_NAME"`
	MeCity                    string     `db:"MCT_ME_CITY"`
	MePinCode                 string     `db:"MCT_ME_ZIP_CODE"`
	MeCountry                 string     `db:"MCT_ME_COUNTRY"`
	TrlType                   string     `db:"MCT_TRL_TYPE"`
	MotoEcomIndicator         string     `db:"MCT_ECOM_INDICATOR"`
	TxnFeeAmount              float64    `db:"MCT_TXN_FEE_AMOUNT"`
	TxnCurrExp                int        `db:"MCT_TXN_CURR_EXP"`
	TxnCurCode                string     `db:"MCT_TXN_CUR_CODE"`
	Ird                       string     `db:"MCT_IRD"`
	SettlementIndicator       string     `db:"MCT_SETL_INDICATOR"`
	CardSeqNumber             string     `db:"MCT_CARD_SEQ_NUMBER"`
	AppCryptogram             string     `db:"MCT_APP_CRYPTOGRAM"`
	CryptInfoData             string     `db:"MCT_CRYPT_INFO_DATA"`
	IssAppData                string     `db:"MCT_ISS_APP_DATA"`
	UpblNumber                string     `db:"MCT_UPBL_NUMBER"`
	AppTxnCounter             string     `db:"MCT_APP_TXN_COUNTER"`
	TrlVerResult              string     `db:"MCT_TRL_VER_RESULTS"`
	TxnDate                   *time.Time `db:"MCT_TXN_DATE"`
	ChipTxnDate               string     `db:"MCT_CHIP_TXN_DATE"`
	ChipTxnType               string     `db:"MCT_CHIP_TXN_TYPE"`
	CryptAmount               float64    `db:"MCT_CRYPT_AMOUNT"`
	AppICProfile              string     `db:"MCT_APP_IC_PROFILE"`
	TrlConCode                string     `db:"MCT_TRL_CON_CODE"`
	ChipCashBack              float64    `db:"MCT_CASHBACK_AMOUNT"`
	CvmResult                 string     `db:"MCT_CVM_RESULTS"`
	TrlCapabilities           string     `db:"MCT_TRL_CAPABILITIES"`
	IfdSerNumber              string     `db:"MCT_IFD_SER_NUMBER"`
	Tcc                       string     `db:"MCT_TCC"`
	ChipCurCode               string     `db:"MCT_CHIP_CUR_CODE"`
	ChipTrlType               string     `db:"MCT_CHIP_TRL_TYPE"`
	TrlAppVerNumber           string     `db:"MCT_TRL_APP_VER_NUMBER"`
	TxnSeqCounter             string     `db:"MCT_TXN_SEQ_COUNTER"`
	IssAuthData               string     `db:"MCT_ISS_AUTH_DATA"`
	TxnlifeCycleId            string     `db:"MCT_TXN_LIFE_CYCL_ID"`
	MsgNumber                 string     `db:"MCT_MSG_NUMBER"`
	MemberText                string     `db:"MCT_MEMBER_TEXT"`
	OrgInstIdCode             string     `db:"MCT_ORG_INST_ID_CODE"`
	RevIndiCator              string     `db:"MCT_REV_INDICATOR"`
	Maid                      string     `db:"MCT_MER_MC_ASSIGNED_ID"`
	CardType                  string     `db:"MCT_CARD_TYPE"`
	CardDomIntlFlag           string     `db:"MCT_DOM_INTL_FLAG"`
	DmsSmsMode                string     `db:"MCT_SMS_DMS_FLAG"`
	PosPgType                 string     `db:"MCT_POS_PG_TYPE"`
	CentreProcDate            string     `db:"MCT_CENTRE_PROC_DATE"`
	EncryptedCardNumber       string     `db:"MCT_ENC_CARD_NUMBER"`
	MrpSerNumber              *int64     `db:"MCT_MRP_SER_NUMBER"`
	MeCountryOfOrigin         string     `db:"MCT_ME_COUNTRY_OF_ORIGIN"`
	TipAmount                 float64    `db:"MCT_TIP_AMOUNT"`
	ChipTrlCapabilities       string     `db:"MCT_CHIP_TRL_CAPABILITIES"`
	DedicatedFileName         string     `db:"MCT_DEDICATED_FILE_NAME"`
	CardAccepStreetAddress    string     `db:"MCT_CARD_ACC_STREET_ADDRESS"`
	CustomerServicePhNum      string     `db:"MCT_CUSTOMER_SERVICE_PHONE_NO"`
	DccIndicator              string     `db:"MCT_DCC_INDICATOR"`
	DccCurrency               string     `db:"MCT_DCC_CURRENCY"`
	DccAmount                 float64    `db:"MCT_DCC_AMOUNT"`
	DccTxnCurrencyExponent    int        `db:"MCT_DCC_CURR_EXP"`
	MposAccDevType            string     `db:"MCT_MPOS_ACC_DEV_TYPE"`
	AccepterUrlAddress        string     `db:"MCT_ACC_URL_ADDRESS"`
}

// McAcqTxnDataEntity maps MC_ACQ_TXN_DATA. Columns mirror the work table, so we
// reuse the work struct shape (Java copies every column work->data).
type McAcqTxnDataEntity = McAcqTxnWorkEntity

// IpmOutWorkEntity maps IPM_OUT_WORK. Nullable columns are pointers so we can
// reproduce the Java `String concat with null -> "null"` behaviour faithfully.
type IpmOutWorkEntity struct {
	SerNumber     int64   `db:"IOW_SER_NUMBER"`
	InsCode       int     `db:"IOW_IN_CODE"`
	FileId        string  `db:"IOW_FILE_ID"`
	RefSerNumber  int64   `db:"IOW_REF_SER_NUMBER"`
	DE001         *string `db:"IOW_DE001"`
	DE002         *string `db:"IOW_DE002"`
	DE003         *string `db:"IOW_DE003"`
	DE004         *string `db:"IOW_DE004"`
	DE012         *string `db:"IOW_DE012"`
	DE014         *string `db:"IOW_DE014"`
	DE022         *string `db:"IOW_DE022"`
	DE023         *string `db:"IOW_DE023"`
	DE024         *string `db:"IOW_DE024"`
	DE025         *string `db:"IOW_DE025"`
	DE026         *string `db:"IOW_DE026"`
	DE030         *string `db:"IOW_DE030"`
	DE031         *string `db:"IOW_DE031"`
	DE032         *string `db:"IOW_DE032"`
	DE033         *string `db:"IOW_DE033"`
	DE037         *string `db:"IOW_DE037"`
	DE038         *string `db:"IOW_DE038"`
	DE040         *string `db:"IOW_DE040"`
	DE041         *string `db:"IOW_DE041"`
	DE042         *string `db:"IOW_DE042"`
	DE043         *string `db:"IOW_DE043"`
	DE049         *string `db:"IOW_DE049"`
	DE054         *string `db:"IOW_DE054"`
	DE063         *string `db:"IOW_DE063"`
	DE071         *string `db:"IOW_DE071"`
	DE072         *string `db:"IOW_DE072"`
	DE093         *string `db:"IOW_DE093"`
	DE094         *string `db:"IOW_DE094"`
	DE095         *string `db:"IOW_DE095"`
	PDS23         *string `db:"IOW_PDS23"`
	PDS25         string  `db:"IOW_PDS25"`
	PDS52         string  `db:"IOW_PDS52"`
	PDS137        *string `db:"IOW_PDS137"`
	PDS148        *string `db:"IOW_PDS148"`
	PDS149        string  `db:"IOW_PDS149"`
	PDS155        *string `db:"IOW_PDS155"`
	PDS165        *string `db:"IOW_PDS165"`
	PDS176        string  `db:"IOW_PDS176"`
	PDS211        *string `db:"IOW_PDS211"`
	PDS262        string  `db:"IOW_PDS262"`
	DE055_9F26    string  `db:"IOW_DE055_9F26"`
	DE055_9F27    string  `db:"IOW_DE055_9F27"`
	DE055_9F10    string  `db:"IOW_DE055_9F10"`
	DE055_9F37    string  `db:"IOW_DE055_9F37"`
	DE055_9F36    string  `db:"IOW_DE055_9F36"`
	DE055_95      string  `db:"IOW_DE055_95"`
	DE055_9A      string  `db:"IOW_DE055_9A"`
	DE055_9C      string  `db:"IOW_DE055_9C"`
	DE055_9F02    string  `db:"IOW_DE055_9F02"`
	DE055_5F2A    string  `db:"IOW_DE055_5F2A"`
	DE055_82      string  `db:"IOW_DE055_82"`
	DE055_9F1A    string  `db:"IOW_DE055_9F1A"`
	DE055_9F03    string  `db:"IOW_DE055_9F03"`
	DE048_PDS0213 string  `db:"IOW_DE048_PDS0213"`
	DE055_84      string  `db:"IOW_DE055_84"`
	DE055_9F33    string  `db:"IOW_DE055_9F33"`
	DE055_9F34    string  `db:"IOW_DE055_9F34"`
	DE048_PDS0170 string  `db:"IOW_DE048_PDS0170"`
	PDS0018       *string `db:"IOW_PDS0018"`
	DE048_PDS0175 *string `db:"IOW_DE048_PDS0175"`
}

// ViewIpmOutWorkEntity maps VW_IPM_OUT_WORK.
type ViewIpmOutWorkEntity struct {
	SerialNo          int64   `db:"SERIALNO"`
	De001             *string `db:"DE001"`
	De002             *string `db:"DE002"`
	De003             *string `db:"DE003"`
	De004             *string `db:"DE004"`
	De012             *string `db:"DE012"`
	De014             *string `db:"DE014"`
	De022             *string `db:"DE022"`
	De023             *string `db:"DE023"`
	De024             *string `db:"DE024"`
	De025             *string `db:"DE025"`
	De026             *string `db:"DE026"`
	De030             *string `db:"DE030"`
	De031             *string `db:"DE031"`
	De033             *string `db:"DE033"`
	De037             *string `db:"DE037"`
	De038             *string `db:"DE038"`
	De040             *string `db:"DE040"`
	De041             *string `db:"DE041"`
	De042             *string `db:"DE042"`
	De043             *string `db:"DE043"`
	De049             *string `db:"DE049"`
	De054             *string `db:"DE054"`
	De063             *string `db:"DE063"`
	De071             *string `db:"DE071"`
	De072             *string `db:"DE072"`
	De093             *string `db:"DE093"`
	De095             *string `db:"DE095"`
	De0480023Pds23    *string `db:"DE048_0023_PDS23"`
	De0480025         *string `db:"DE048_0025"`
	De0480052         *string `db:"DE048_0052"`
	De0480137         *string `db:"DE048_0137"`
	De0480148         *string `db:"DE048_0148"`
	De0480149         *string `db:"DE048_0149"`
	De0480155         *string `db:"DE048_0155"`
	De0480165         *string `db:"DE048_0165"`
	De0480176         *string `db:"DE048_0176"`
	De0480211         *string `db:"DE048_0211"`
	De0480262         *string `db:"DE048_0262"`
	De0559f26         *string `db:"DE055_9F26"`
	De0559f27         *string `db:"DE055_9F27"`
	De0559f10         *string `db:"DE055_9F10"`
	De0559f37         *string `db:"DE055_9F37"`
	De0559f36         *string `db:"DE055_9F36"`
	De05595           *string `db:"DE055_95"`
	De0559a           *string `db:"DE055_9A"`
	De0559c           *string `db:"DE055_9C"`
	De0559f02         *string `db:"DE055_9F02"`
	De0555f2a         *string `db:"DE055_5F2A"`
	De05582           *string `db:"DE055_82"`
	De0559f1a         *string `db:"DE055_9F1A"`
	De0559f03         *string `db:"DE055_9F03"`
	LocalDateTime     *string `db:"test"`
	TxnType           *string `db:"test1"`
	TxnAmount         *string `db:"test2"`
	De048Pds0213      *string `db:"DE048_PDS0213"`
	De05584           *string `db:"DE055_84"`
	De0559f33         *string `db:"DE055_9F33"`
	De0559f34         *string `db:"DE055_9F34"`
	De0480170         *string `db:"DE048_0170"`
	De0480018         *string `db:"DE048_0018"`
	De0480175         *string `db:"DE048_0175"`
}

// PosTransactionEntity maps POS_TRANSACTIONS (subset used by outgoing flow).
type PosTransactionEntity struct {
	SerialNumber int64  `db:"PTR_SER_NUMBER"`
	Rrn          string `db:"PTR_RRN"`
	GenStatus    int    `db:"PTR_GEN_STATUS"`
	OutStatus    string `db:"PTR_OUT_STATUS"`
	Network      string `db:"PTR_NETWORK"`
	Scheme       string `db:"PTR_SCHEME"`
	InsCode      int    `db:"PTR_INS_CODE"`
}
