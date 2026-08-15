package cryptapi

import (
	"encoding/base64"
	"encoding/json"
	"net/http"
	"net/http/httptest"
	"sync"
	"testing"
)

func TestGetCardNumberSingleToken(t *testing.T) {
	var mu sync.Mutex
	var gotURL, gotAuth, gotAPIID, gotClientID string
	var gotBody map[string]any

	srv := httptest.NewServer(http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {
		mu.Lock()
		gotURL = r.URL.String()
		gotAuth = r.Header.Get("Authorization")
		gotAPIID = r.Header.Get("apiId")
		gotClientID = r.Header.Get("clientId")
		_ = json.NewDecoder(r.Body).Decode(&gotBody)
		mu.Unlock()
		w.Header().Set("Content-Type", "application/json")
		_, _ = w.Write([]byte(`{"logRefId":"l1","respMsg":"OK","respCode":"00","cardNumbers":{"tok-123":"4111111111111111"}}`))
	}))
	defer srv.Close()

	c := NewCryptoClient(CryptoConfig{
		DecURL:               srv.URL,
		BankID:               "B1",
		AccessToken:          "AT",
		Username:             "u",
		Password:             "p",
		CryptAppIDDecryption: "decApp",
		ClientID:             "c1",
	})

	dec := c.GetCardNumber([]string{"tok-123"})
	if dec == nil || dec["tok-123"] != "4111111111111111" {
		t.Fatalf("decrypted = %v, want tok-123 -> 4111111111111111", dec)
	}

	mu.Lock()
	defer mu.Unlock()
	if gotURL != "/" {
		t.Fatalf("request url = %q, want /", gotURL)
	}
	if gotAuth != "Basic "+base64.StdEncoding.EncodeToString([]byte("u:p")) {
		t.Fatalf("auth = %q, want Basic u:p", gotAuth)
	}
	if gotAPIID != "decApp" {
		t.Fatalf("apiId = %q, want decApp", gotAPIID)
	}
	if gotClientID != "c1" {
		t.Fatalf("clientId = %q, want c1", gotClientID)
	}
	uuids, _ := gotBody["uuids"].([]any)
	if len(uuids) != 1 || uuids[0] != "tok-123" {
		t.Fatalf("body uuids = %v, want [tok-123]", gotBody["uuids"])
	}
	if gotBody["bankId"] != "B1" || gotBody["accessToken"] != "AT" {
		t.Fatalf("body = %v, want bankId B1 accessToken AT", gotBody)
	}
}

func TestGetCardNumberChunksBy16(t *testing.T) {
	var calls int
	var mu sync.Mutex
	srv := httptest.NewServer(http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {
		mu.Lock()
		calls++
		mu.Unlock()
		var body map[string]any
		_ = json.NewDecoder(r.Body).Decode(&body)
		uuids, _ := body["uuids"].([]any)
		cm := map[string]string{}
		for _, u := range uuids {
			cm[u.(string)] = "4111" + u.(string)
		}
		w.Header().Set("Content-Type", "application/json")
		_ = json.NewEncoder(w).Encode(map[string]any{"cardNumbers": cm})
	}))
	defer srv.Close()

	c := NewCryptoClient(CryptoConfig{DecURL: srv.URL, CryptAppIDDecryption: "d"})

	tokens := make([]string, 17)
	for i := range tokens {
		tokens[i] = string(rune('a' + i))
	}
	dec := c.GetCardNumber(tokens)
	if dec == nil || len(dec) != 17 {
		t.Fatalf("decrypted size = %d, want 17", len(dec))
	}
	if calls != 2 {
		t.Fatalf("http calls = %d, want 2 (16 + 1 chunk)", calls)
	}
}

func TestGetCardNumberFailureReturnsNil(t *testing.T) {
	srv := httptest.NewServer(http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {
		http.Error(w, "boom", http.StatusInternalServerError)
	}))
	defer srv.Close()

	c := NewCryptoClient(CryptoConfig{DecURL: srv.URL, CryptAppIDDecryption: "d"})
	if dec := c.GetCardNumber([]string{"tok"}); dec != nil {
		t.Fatalf("decrypted = %v, want nil on non-200", dec)
	}
}

func TestGetTokenEncrypt(t *testing.T) {
	var gotBody map[string]any
	srv := httptest.NewServer(http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {
		_ = json.NewDecoder(r.Body).Decode(&gotBody)
		w.Header().Set("Content-Type", "application/json")
		_, _ = w.Write([]byte(`{"respCode":"00","uuids":{"4111111111111111":"tok-abc"}}`))
	}))
	defer srv.Close()

	c := NewCryptoClient(CryptoConfig{EncURL: srv.URL, CryptAppIDEncryption: "encApp"})
	enc := c.GetToken([]string{"4111111111111111"})
	if enc == nil || enc["4111111111111111"] != "tok-abc" {
		t.Fatalf("encrypted = %v, want 4111111111111111 -> tok-abc", enc)
	}
	cards, _ := gotBody["cardNumbers"].([]any)
	if len(cards) != 1 || cards[0] != "4111111111111111" {
		t.Fatalf("body cardNumbers = %v, want [4111111111111111]", gotBody["cardNumbers"])
	}
}
