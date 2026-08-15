package irf_test

import (
	"net/http/httptest"
	"testing"
	"time"

	"empay/irf/irf"
	"empay/irf/irf/stub"
)

func newClient(t *testing.T) (*irf.Client, *httptest.Server) {
	t.Helper()
	c, srv := stub.NewClient(t)
	return c, srv
}

func ptrF(v float64) *float64 { return &v }
func ptrI(v int) *int         { return &v }

// 1) calculate happy path: Visa fallback VO (XX / 0.0), auth via ?sec=
func TestClient_Calculate_VisaFallback(t *testing.T) {
	c, _ := newClient(t)
	tt := irf.LocalDateTime(time.Date(2024, 1, 2, 3, 4, 5, 0, time.UTC))
	data := &irf.IrfTxnData{
		InsCode:        ptrI(7085),
		Mcc:            "5499",
		TxnAmount:      ptrF(100.0),
		SetlAmount:     ptrF(100.0),
		TxnCurCode:     "784",
		SetlCurCode:    "784",
		PosEntryMode:   "012",
		ApprovalCode:   "ABC123",
		AcqInstConCode: "784",
		TxnDateTime:    &tt,
		Rrn:            "123456789012",
	}
	vo, calculated, err := c.Calculate(7085, data, "VISA", "411111111")
	if err != nil {
		t.Fatalf("unexpected error: %v", err)
	}
	if !calculated {
		t.Fatal("expected calculated=true")
	}
	if vo == nil || vo.IrfCountry != "XX" {
		t.Fatalf("expected fallback XX VO, got %+v", vo)
	}
}

// 2) bad secret -> 401 -> client error
func TestClient_Calculate_BadSecret_Unauthorized(t *testing.T) {
	c, _ := newClient(t)
	c.Secret = "wrong"
	vo, calculated, err := c.Calculate(7085, &irf.IrfTxnData{}, "VISA", "411111111")
	if err == nil {
		t.Fatal("expected unauthorized error")
	}
	if calculated {
		t.Fatal("expected calculated=false")
	}
	if vo != nil {
		t.Fatalf("expected nil result, got %+v", vo)
	}
}

// 3) unsupported network -> 501 -> client error (stub mirrors Java stubs)
func TestClient_Calculate_UnsupportedNetwork_NotImplemented(t *testing.T) {
	c, _ := newClient(t)
	_, _, err := c.Calculate(7085, &irf.IrfTxnData{}, "AMEX", "378282246310005")
	if err == nil {
		t.Fatal("expected not-implemented error")
	}
}

// 4) callback enqueue -> serial number returned
func TestClient_EnqueueCallback(t *testing.T) {
	c, _ := newClient(t)
	data := &irf.CallbackData{Rrn: "rrn-1", CpMid: "12345", IrdCode: "IRD001"}
	serial, err := c.EnqueueCallback(data)
	if err != nil {
		t.Fatalf("unexpected error: %v", err)
	}
	if serial != 50001 {
		t.Fatalf("expected serial 50001, got %d", serial)
	}
}

// 5) flush + retry round trips with sec
func TestClient_FlushAndRetry(t *testing.T) {
	c, _ := newClient(t)
	ins := 7085
	job := 99
	if err := c.FlushCallbacks(&ins, &job); err != nil {
		t.Fatalf("flush: %v", err)
	}
	if err := c.FlushCallbacks(nil, nil); err != nil {
		t.Fatalf("flush(nil,nil): %v", err)
	}
	ok, err := c.RetryCallback(50001)
	if err != nil {
		t.Fatalf("retry: %v", err)
	}
	if !ok {
		t.Fatal("expected retry ok=true")
	}
}
