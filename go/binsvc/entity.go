package binsvc

import "time"

// UploadLog is the Go port of FileUploadLogEntity (FILE_UPLOAD_LOG).
type UploadLog struct {
	SerialNumber        int64
	LastUpdated         time.Time
	UpdatedUser         int
	InstitutionCode     int
	InterfaceCode       int
	JobNumber           int
	FileName            string
	UploadDate          time.Time
	UploadStatus        int
	ProcessingDate      time.Time
	BusinessDate        time.Time
	FileID              string
	TotalAcceptedTxnCount int
	TotalTxnCount       int
	FormatCode          int
	Remarks             *string
}

// ProcessingJob is the Go port of ProcessingJobsEntity (PROCESSING_JOBS).
type ProcessingJob struct {
	SerialNumber int64
	LastUpdated  time.Time
	UpdatedUser  int
	InsCode      int
	RefNumber    int
	ProcessName  string
	StartTime    time.Time
	EndTime      *time.Time
	Status       *int
}

// McRange is the Go port of MCIssAcqRangeEntity (MC_ISS_ACC_RANGE).
type McRange struct {
	SerialNumber        int64
	LastUpdated         time.Time
	UpdatedUser         int
	JobSerialNumber     int
	EffectiveDate       time.Time
	ActiveCode          string
	IssRangeLow         string
	GcmsProductID       string
	IssRangeHigh        string
	CardProgID          string
	PriorityCode        string
	MemberID            string
	ProdTypeID          string
	EndPoint            string
	CountryAlphaCode    string
	CountryCode         string
	Region              string
	ProductClass        string
	TxnRoutInd          string
	FpReasignSwitch     string
	ProdReasignSwitch   string
	PwcbSwitch          string
	LicProdID           string
	MapServInd          string
	AccLevelInd         string
	ChBillCurr          string
	ChBillCurrExp       string
	ChipServInd         string
	FloorExpDate        string
	CoBrandSwitch       string
	SpendControlSwitch  string
	MeCleansingService  string
	MePayPassInd        string
	RateTypeInd         string
	PsnRouteInd         string
	CbWithoutPurchase   string
	RepowerReloadInd    string
	MoneySendInd        string
	DurbinRateInd       string
	BussDate            *time.Time
	GenStatus           int
}

// VisaRange is the Go port of VisaIssAcqRangeEntity (VISA_ISS_ACC_RANGE).
type VisaRange struct {
	SerialNumber     int64
	LastUpdated      time.Time
	UpdatedUser      int
	JobSerialNumber  int
	IssRangeHigh     string
	IssRangeLow      string
	Bin              string
	BinLength        int
	ProcessingBin    string
	Domain           string
	Region           string
	CountryAlphaCode string
	CardProduct      string
	CrdrIndicator    string
	ProductSubType   string
}

// JaywanRange is the Go port of JaywanIssAccRangeEntity (JAYWAN_ISS_ACC_RANGE).
type JaywanRange struct {
	SerialNumber   int64
	LastUpdated    time.Time
	UpdatedUser    int
	JobNumber      int
	IssuerBank     string
	InstitutionID  int
	BinRangeLow    int64
	BinRangeHigh   int64
	PanLength      int
	BinLength      int
	ProductType    string
	SchemeCode     string
	SchemeProduct  string
	CardType       int
	Service        int
	CurrencyCode   int
	IsoNumCurrCode int
	ActionTaken    string
	IssAccCap      string
	ProdClssfy     string
	BadgeInd       string
}

// OmanNetRange is the Go port of OmanNetBinEntity (OMANNET_BIN_DATA).
type OmanNetRange struct {
	SerialNumber int64
	LastUpdated  time.Time
	User         int
	JobNumber    int
	GenStatus    int
	Route        string
	SubRoute     string
	BinNumber    string
	CardType     string
	Remarks      *string
}

// MercuryRange is the Go port of MercuryIssAccRangeEntity (MERCURY_ISS_ACC_RANGE).
type MercuryRange struct {
	SerialNumber  int64
	LastUpdated   time.Time
	UpdatedUser   int
	JobNumber     int
	BinRangeLow   int64
	BinRangeHigh  int64
	CardType      int
	CardProductID int
	CardVariant   int
	CardScheme    int
	CurrencyCode  int
	CountryCode   int
	Status        string
}
