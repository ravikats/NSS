package outsvc

// OutGoingRequestVo mirrors the Java request body for /v1/generateOutgoing and
// /v1/revertLastOutgoing.
type OutGoingRequestVo struct {
	Network  string `json:"network"`
	FromDate string `json:"fromDate"`
	ToDate   string `json:"toDate"`
}

// CollectionOnlyRequestVo mirrors the Java request body for
// /v1/generateCollectionOnly.
type CollectionOnlyRequestVo struct {
	Network  string `json:"network"`
	Scheme   string `json:"scheme"`
	FromDate string `json:"fromDate"`
	ToDate   string `json:"toDate"`
}

var outgoingNetworks = map[string]bool{
	"MASTERCARD": true,
	"VISA":       true,
	"RUPAY":      true,
	"AMEX":       true,
	"JAYWAN":     true,
	"MERCURY":    true,
	"UNIONPAY":   true,
}
