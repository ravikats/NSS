// Package irf is the Go HTTP client for the standalone IRF Calculation Service
// (the Java Spring Boot app at irf-service/). It mirrors the REST contract:
//
//	POST /irf/v1/calculate?sec=<secret>   {network,insCode,cardNumber,txnData}   ->  {calculated,result}
//	POST /irf/v1/callback?sec=<secret>    {IrfResultData}                        ->  serialNumber
//	POST /irf/v1/callback/flush?sec=...   ?insCode=&jobNumber=                   ->  200
//	POST /irf/v1/callback/retry?sec=...   ?refSerNumber=                         ->  true|false
//
// The DTO field names / JSON shapes match the Java counterparts in irf-common so a
// drop-in Java↔Go swap of the TLF/MPGS IRF client is behaviour-identical.
package irf

import (
	"bytes"
	"encoding/json"
	"fmt"
	"io"
	"net/http"
	"net/url"
	"strings"
	"time"
)

// LocalDateTime serialises like Java's LocalDateTime: ISO-8601 with NO timezone
// offset ("2024-01-02T03:04:05"). Go's time.Time marshals RFC3339 with an offset
// ("+05:30" or "Z"), which Jackson refuses for LocalDateTime.
type LocalDateTime time.Time

func (t LocalDateTime) MarshalJSON() ([]byte, error) {
	return []byte(`"` + time.Time(t).Format("2006-01-02T15:04:05") + `"`), nil
}

// UnmarshalJSON accepts the offset-less layout we emit plus RFC3339 variants
// (so the stub/server round-trip works regardless of the producer).
func (t *LocalDateTime) UnmarshalJSON(b []byte) error {
	s := strings.Trim(string(b), `"`)
	if s == "" || s == "null" {
		return nil
	}
	for _, layout := range []string{
		"2006-01-02T15:04:05",
		"2006-01-02T15:04:05.999999999",
		time.RFC3339Nano,
	} {
		if v, err := time.Parse(layout, s); err == nil {
			*t = LocalDateTime(v)
			return nil
		}
	}
	return fmt.Errorf("irf: cannot parse LocalDateTime %q", s)
}

// IrfTxnData is the txn-agnostic DTO the service's calculators consume.
// JSON tags use the exact camelCase names from com.empay.common.irf.IrfTxnData
// so the Java server deserialises it unchanged.
type IrfTxnData struct {
	SerialNumber            *int     `json:"serialNumber,omitempty"`
	InsCode                 *int     `json:"insCode,omitempty"`
	Network                 string   `json:"network,omitempty"`
	Scheme                  string   `json:"scheme,omitempty"`
	Mcc                     string   `json:"mcc,omitempty"`
	TxnCode                 string   `json:"txnCode,omitempty"`
	TxnId                   string   `json:"txnId,omitempty"`
	ResponseCode            string   `json:"responseCode,omitempty"`
	ApprovalCode            string   `json:"approvalCode,omitempty"`
	Rrn                     string   `json:"rrn,omitempty"`
	PosEntryMode            string   `json:"posEntryMode,omitempty"`
	TerminalType            string   `json:"terminalType,omitempty"`
	TxnSource               string   `json:"txnSource,omitempty"`
	PosConditionCode        string   `json:"posConditionCode,omitempty"`
	ServiceCode             string   `json:"serviceCode,omitempty"`
	CardSeqNumber           string   `json:"cardSeqNumber,omitempty"`
	TxnAmount               *float64 `json:"txnAmount,omitempty"`
	SetlAmount              *float64 `json:"setlAmount,omitempty"`
	CashBackAmount          *float64 `json:"cashBackAmount,omitempty"`
	AuthAmount              *float64 `json:"authAmount,omitempty"`
	NetAmount               *float64 `json:"netAmount,omitempty"`
	TipAmount               *float64 `json:"tipAmount,omitempty"`
	TxnFeeAmount            *float64 `json:"txnFeeAmount,omitempty"`
	CryptAmount             *float64 `json:"cryptAmount,omitempty"`
	ChipCashBack            *float64 `json:"chipCashBack,omitempty"`
	DccAmount               *float64 `json:"dccAmount,omitempty"`
	TxnCurCode              string   `json:"txnCurCode,omitempty"`
	SetlCurCode             string   `json:"setlCurCode,omitempty"`
	DccCurrency             string   `json:"dccCurrency,omitempty"`
	CardAcceptorCountryCode string   `json:"cardAcceptorCountryCode,omitempty"`
	FeePgmIndicator         string   `json:"feePgmIndicator,omitempty"`
	// Java Character -> single-char string ("B", "I", …).
	ReImbursementAttribute *string `json:"reImbursementAttribute,omitempty"`
	MotoEcomIndicator      string  `json:"motoEcomIndicator,omitempty"`
	TerminalCapability     string  `json:"terminalCapability,omitempty"`
	TrlCapabilities        *string `json:"trlCapabilities,omitempty"`
	Mvv                    string  `json:"mvv,omitempty"`
	NetworkData            string  `json:"networkData,omitempty"`
	Maid                   string  `json:"maid,omitempty"`
	// Java Character -> single-char string ("" for absent).
	MeCategoryType   *string `json:"meCategoryType,omitempty"`
	ChAuthAbility    *string `json:"chAuthAbility,omitempty"`
	CardInputAbility *string `json:"cardInputAbility,omitempty"`
	CardCaptureAbility *string `json:"cardCaptureAbility,omitempty"`
	CardInputMode    *string `json:"cardInputMode,omitempty"`
	ChPresent        *string `json:"chPresent,omitempty"`
	CardPresent      *string `json:"cardPresent,omitempty"`
	OprtEnvironment  *string `json:"oprtEnvironment,omitempty"`
	// Java LocalDateTime serialises as ISO-8601 (e.g. "2024-01-02T03:04:05").
	TxnDateTime     *LocalDateTime `json:"txnDateTime,omitempty"`
	Stan            string         `json:"stan,omitempty"`
	MsgTypeId       string         `json:"msgTypeId,omitempty"`
	TxnUniqueId     string         `json:"txnUniqueId,omitempty"`
	CardNumber      string         `json:"cardNumber,omitempty"`
	EncCardNumber   string         `json:"encCardNumber,omitempty"`
	TokenIdentifier string         `json:"tokenIdentifier,omitempty"`
	AcqInstConCode  string         `json:"acqInstConCode,omitempty"`
}

// IrfResultVo mirrors com.empay.common.vo.IRFResultVo (server → client).
type IrfResultVo struct {
	IrdSerNumber  *int     `json:"irdSerNumber,omitempty"`
	MorSerNumber  *int     `json:"morSerNumber,omitempty"`
	IrdCode       string   `json:"irdCode,omitempty"`
	IrfPercentage *float64 `json:"irfPercentage,omitempty"`
	IrfFixed      *float64 `json:"irfFixed,omitempty"`
	IrfAmount     *float64 `json:"irfAmount,omitempty"`
	DomIntlFlag   string   `json:"domIntlFlag,omitempty"`
	CardType      string   `json:"cardType,omitempty"`
	GcmsProductID string   `json:"gcmsProductID,omitempty"`
	IrfDesc       string   `json:"irfDesc,omitempty"`
	IrfCountry    string   `json:"irfCountry,omitempty"`
	IrfMinAmount  *float64 `json:"irfMinAmount,omitempty"`
	IrfMaxAmount  *float64 `json:"irfMaxAmount,omitempty"`
	IrfAmountUSD  *float64 `json:"irfAmountUSD,omitempty"`
}

// CallbackData is the IrfResultData payload POSTed to /irf/v1/callback.
type CallbackData struct {
	CpMid              string   `json:"cpMid,omitempty"`
	UniqueId           string   `json:"uniqueId,omitempty"`
	IrdCode            string   `json:"irdCode,omitempty"`
	Fixed              *float64 `json:"fixed,omitempty"`
	Percentage         *float64 `json:"percentage,omitempty"`
	IrfAmount          *float64 `json:"irfAmount,omitempty"`
	TxnAmount          *float64 `json:"txnAmount,omitempty"`
	Rrn                string   `json:"rrn,omitempty"`
	Mti                string   `json:"mti,omitempty"`
	DomIntlFlag        string   `json:"domIntlFlag,omitempty"`
	IsCredit           *bool    `json:"isCredit,omitempty"`
	CardClassification string   `json:"cardClassification,omitempty"`
	InsCode            *int     `json:"insCode,omitempty"`
	RefSerNumber       *int     `json:"refSerNumber,omitempty"`
}

// CalculateRequest is the /irf/v1/calculate body.
type CalculateRequest struct {
	Network    string      `json:"network,omitempty"`
	InsCode    int         `json:"insCode"`
	CardNumber string      `json:"cardNumber,omitempty"`
	TxnData    *IrfTxnData `json:"txnData,omitempty"`
}

// CalculateResponse is the /irf/v1/calculate body.
type CalculateResponse struct {
	Calculated bool         `json:"calculated"`
	Result     *IrfResultVo `json:"result,omitempty"`
}

// Client is the Go client for the IRF Calculation Service.
type Client struct {
	BaseURL string // e.g. "http://localhost:8085"
	Secret  string // irf.service.sec
	HTTP    *http.Client
}

func NewClient(baseURL, secret string) *Client {
	return &Client{
		BaseURL: baseURL,
		Secret:  secret,
		HTTP:    &http.Client{Timeout: 30 * time.Second},
	}
}

// Calculate requests an IRF for one transaction.
// Returns (result, calculated, err). When no IRF applies, calculated=false and
// result is nil — callers should never receive a raw nil where a VO was expected.
func (c *Client) Calculate(insCode int, txn *IrfTxnData, network, cardNumber string) (*IrfResultVo, bool, error) {
	var net string
	if txn != nil {
		net = txn.Network
		if network != "" {
			net = network
		}
	} else if network != "" {
		net = network
	}
	req := CalculateRequest{Network: net, InsCode: insCode, CardNumber: cardNumber, TxnData: txn}
	body, err := json.Marshal(req)
	if err != nil {
		return nil, false, fmt.Errorf("irf: marshal request: %w", err)
	}

	endpoint, err := c.join("/irf/v1/calculate")
	if err != nil {
		return nil, false, err
	}
	resp, err := c.do("POST", endpoint.String(), body)
	if err != nil {
		return nil, false, err
	}
	defer resp.body.Close()
	var out CalculateResponse
	if err := json.NewDecoder(resp.body).Decode(&out); err != nil {
		return nil, false, fmt.Errorf("irf: decode calculate response: %w", err)
	}
	return out.Result, out.Calculated, nil
}

// EnqueueCallback persists a callback on the service (status=PENDING) and
// returns the stored serial number.
func (c *Client) EnqueueCallback(data *CallbackData) (int, error) {
	body, err := json.Marshal(data)
	if err != nil {
		return 0, fmt.Errorf("irf: marshal callback: %w", err)
	}
	endpoint, err := c.join("/irf/v1/callback")
	if err != nil {
		return 0, err
	}
	resp, err := c.do("POST", endpoint.String(), body)
	if err != nil {
		return 0, err
	}
	defer resp.body.Close()
	var serial int
	if err := json.NewDecoder(resp.body).Decode(&serial); err != nil {
		return 0, fmt.Errorf("irf: decode callback serial: %w", err)
	}
	return serial, nil
}

// FlushCallbacks sends pending callbacks for an institution/job.
func (c *Client) FlushCallbacks(insCode, jobNumber *int) error {
	u, err := c.join("/irf/v1/callback/flush")
	if err != nil {
		return err
	}
	q := u.Query()
	if insCode != nil {
		q.Set("insCode", fmt.Sprintf("%d", *insCode))
	}
	if jobNumber != nil {
		q.Set("jobNumber", fmt.Sprintf("%d", *jobNumber))
	}
	u.RawQuery = q.Encode()
	resp, err := c.do("POST", u.String(), nil)
	if err != nil {
		return err
	}
	return resp.close()
}

// RetryCallback re-sends a single callback by stored serial number.
func (c *Client) RetryCallback(refSerNumber int) (bool, error) {
	u, err := c.join("/irf/v1/callback/retry")
	if err != nil {
		return false, err
	}
	q := u.Query()
	q.Set("refSerNumber", fmt.Sprintf("%d", refSerNumber))
	u.RawQuery = q.Encode()
	resp, err := c.do("POST", u.String(), nil)
	if err != nil {
		return false, err
	}
	defer resp.body.Close()
	var ok bool
	if err := json.NewDecoder(resp.body).Decode(&ok); err != nil {
		return false, fmt.Errorf("irf: decode retry response: %w", err)
	}
	return ok, nil
}

// ---- internals ----

type httpResp struct {
	body io.ReadCloser
}

func (r *httpResp) close() error {
	if r.body != nil {
		return r.body.Close()
	}
	return nil
}

func (c *Client) join(path string) (*url.URL, error) {
	u, err := url.Parse(c.BaseURL + path)
	if err != nil {
		return nil, fmt.Errorf("irf: parse url %q: %w", c.BaseURL+path, err)
	}
	return u, nil
}

func (c *Client) do(method, endpoint string, payload []byte) (*httpResp, error) {
	var body io.Reader
	if payload != nil {
		body = bytes.NewReader(payload)
	}
	req, err := http.NewRequest(method, endpoint, body)
	if err != nil {
		return nil, fmt.Errorf("irf: build request: %w", err)
	}
	if payload != nil {
		req.Header.Set("Content-Type", "application/json")
	}
	q := req.URL.Query()
	q.Set("sec", c.Secret)
	req.URL.RawQuery = q.Encode()

	resp, err := c.HTTP.Do(req)
	if err != nil {
		return nil, fmt.Errorf("irf: %s %s: %w", method, endpoint, err)
	}
	if resp.StatusCode == http.StatusUnauthorized {
		resp.Body.Close()
		return nil, fmt.Errorf("irf: unauthorized (bad/missing sec)")
	}
	if resp.StatusCode == http.StatusNotImplemented {
		resp.Body.Close()
		return nil, fmt.Errorf("irf: network not implemented on the server")
	}
	if resp.StatusCode < 200 || resp.StatusCode >= 300 {
		resp.Body.Close()
		return nil, fmt.Errorf("irf: unexpected status %d", resp.StatusCode)
	}
	return &httpResp{body: resp.Body}, nil
}
