// Package cryptapi implements the external PAN encryption/decryption REST API
// client (Java com.empay.cryptapi.CryptAPI). It is shared by outsvc (outgoing
// file generation) and tlfsvc (online TLF processing).
package cryptapi

import (
	"bytes"
	"encoding/base64"
	"encoding/json"
	"fmt"
	"net/http"
	"time"
)

// CryptoConfig holds the CryptAPI connection parameters (mirrors the Java
// application.properties keys).
type CryptoConfig struct {
	EncURL               string
	DecURL               string
	BankID               string
	AccessToken          string
	Username             string
	Password             string
	CryptAppIDEncryption string
	CryptAppIDDecryption string
	ClientID             string
}

// CardCrypto is the PAN encryption/decryption contract. The production
// implementation talks to the external CryptAPI; tests use a fake.
type CardCrypto interface {
	GetCardNumber(tokens []string) map[string]string
	GetToken(cardNumbers []string) map[string]string
}

// CryptoClient talks to the external PAN encryption/decryption REST API.
type CryptoClient struct {
	cfg   CryptoConfig
	httpc *http.Client
}

// NewCryptoClient builds a CryptoClient with a shared HTTP client.
func NewCryptoClient(cfg CryptoConfig) *CryptoClient {
	return &CryptoClient{
		cfg:   cfg,
		httpc: &http.Client{Timeout: 60 * time.Second},
	}
}

var _ CardCrypto = (*CryptoClient)(nil)

// decryptResponse mirrors DecryptResponseVo.
type decryptResponse struct {
	LogRefId    string            `json:"logRefId"`
	RespMsg     string            `json:"respMsg"`
	RespCode    string            `json:"respCode"`
	CardNumbers map[string]string `json:"cardNumbers"`
}

// encryptResponse mirrors EncryptResponseVo.
type encryptResponse struct {
	RespCode string            `json:"respCode"`
	RespMsg  string            `json:"respMsg"`
	LogRefId string            `json:"logRefId"`
	Uuids    map[string]string `json:"uuids"`
}

// GetCardNumber decrypts PAN tokens. A nil map signals total failure (Java
// returns a null DecryptResponseVo on any error).
func (c *CryptoClient) GetCardNumber(tokens []string) map[string]string {
	out := map[string]string{}
	chunk := make([]string, 0, 16)
	counter := 0
	for i, tok := range tokens {
		chunk = append(chunk, tok)
		counter++
		if counter != 16 && i != len(tokens)-1 {
			continue
		}
		resp, err := c.callDecrypt(chunk)
		if err != nil || resp == nil || resp.CardNumbers == nil {
			return nil
		}
		for k, v := range resp.CardNumbers {
			out[k] = v
		}
		chunk = chunk[:0]
		counter = 0
	}
	return out
}

// GetToken encrypts PANs to tokens. A nil map signals total failure.
func (c *CryptoClient) GetToken(cardNumbers []string) map[string]string {
	out := map[string]string{}
	chunk := make([]string, 0, 16)
	counter := 0
	for i, card := range cardNumbers {
		chunk = append(chunk, card)
		counter++
		if counter != 16 && i != len(cardNumbers)-1 {
			continue
		}
		resp, err := c.callEncrypt(chunk)
		if err != nil || resp == nil || resp.Uuids == nil {
			return nil
		}
		for k, v := range resp.Uuids {
			out[k] = v
		}
		chunk = chunk[:0]
		counter = 0
	}
	return out
}

func (c *CryptoClient) callEncrypt(cardNumbers []string) (*encryptResponse, error) {
	body := map[string]any{
		"bankId":      c.cfg.BankID,
		"accessToken": c.cfg.AccessToken,
		"cardNumbers": cardNumbers,
	}
	raw, err := c.post(c.cfg.EncURL, c.cfg.CryptAppIDEncryption, body)
	if err != nil {
		return nil, err
	}
	var out encryptResponse
	if err := json.Unmarshal(raw, &out); err != nil {
		return nil, err
	}
	return &out, nil
}

func (c *CryptoClient) callDecrypt(tokens []string) (*decryptResponse, error) {
	body := map[string]any{
		"bankId":      c.cfg.BankID,
		"accessToken": c.cfg.AccessToken,
		"uuids":       tokens,
	}
	raw, err := c.post(c.cfg.DecURL, c.cfg.CryptAppIDDecryption, body)
	if err != nil {
		return nil, err
	}
	var out decryptResponse
	if err := json.Unmarshal(raw, &out); err != nil {
		return nil, err
	}
	return &out, nil
}

func (c *CryptoClient) post(url, apiID string, body map[string]any) ([]byte, error) {
	payload, err := json.Marshal(body)
	if err != nil {
		return nil, err
	}
	req, err := http.NewRequest(http.MethodPost, url, bytes.NewReader(payload))
	if err != nil {
		return nil, err
	}
	req.Header.Set("Content-Type", "application/json")
	req.Header.Set("Authorization", "Basic "+base64.StdEncoding.EncodeToString([]byte(c.cfg.Username+":"+c.cfg.Password)))
	req.Header.Set("apiId", apiID)
	req.Header.Set("clientId", c.cfg.ClientID)
	resp, err := c.httpc.Do(req)
	if err != nil {
		return nil, err
	}
	defer resp.Body.Close()
	if resp.StatusCode != http.StatusOK {
		return nil, fmt.Errorf("cryptapi: http %d", resp.StatusCode)
	}
	var buf bytes.Buffer
	if _, err := buf.ReadFrom(resp.Body); err != nil {
		return nil, err
	}
	return buf.Bytes(), nil
}
