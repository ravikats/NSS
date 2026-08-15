// Package live runs the real tlf/mpgs mappers + Go client against a running
// irf-service jar (no stub). Requires the Java service to be up; pass
//
//	IRF_BASE_URL  (default http://localhost:18085)
//	IRF_SECRET    (default test-secret)
//
// Run from go/:  go test ./live/ -v
package live

import (
	"net/http"
	"os"
	"testing"
	"time"

	"empay/irf/irf"
	"empay/irf/mpgs"
	"empay/irf/tlf"
)

func baseURL() string {
	if v := os.Getenv("IRF_BASE_URL"); v != "" {
		return v
	}
	return "http://localhost:18085"
}

func secret() string {
	if v := os.Getenv("IRF_SECRET"); v != "" {
		return v
	}
	return "test-secret"
}

func reachable(t *testing.T) bool {
	t.Helper()
	resp, err := http.Get(baseURL() + "/")
	if err != nil {
		return false
	}
	defer resp.Body.Close()
	return resp.StatusCode == http.StatusOK
}

func tlfTxn(network string) tlf.Txn {
	dt := time.Date(2026, 8, 13, 9, 0, 0, 0, time.Local)
	return tlf.Txn{
		SerialNumber:           42,
		InsCode:                1,
		Network:                network,
		Scheme:                 "VISA",
		Mcc:                    "5499",
		TxnCode:                "01",
		TxnId:                  "TLF-42",
		ResponseCode:           "00",
		ApprovalCode:           "ABC123",
		Rrn:                    "123456789012",
		PosEntryMode:           "012",
		PosConditionCode:       "01",
		ServiceCode:            "101",
		CardSeqNumber:          "001",
		TerminalType:           "POS",
		TxnAmount:              100.0,
		SetlAmount:             100.0,
		CashBackAmount:         0.0,
		NetAmount:              100.0,
		TxnCurCode:             "784",
		SetlCurCode:            "784",
		FeePgmIndicator:        "N",
		ReImbursementAttribute: "B",
		MotoEcomIndicator:      "S",
		TerminalCapability:     "00",
		TrlCapabilities:        "00",
		Mvv:                    "0A01020304",
		NetworkData:            "",
		Maid:                   "",
		MeCategoryType:         "5",
		ChAuthAbility:          "2",
		CardInputAbility:       "2",
		CardCaptureAbility:     "1",
		CardInputMode:          "0",
		ChPresent:              "1",
		CardPresent:            "0",
		OprtEnvironment:        "5",
		EncCardNumber:          "4111111111111111",
		CardNumber:             "4111111111111111",
		TxnDateTime:            dt,
		AcqInstConCode:         "784",
		CardAcceptorCountryCode: "784",
		MsgTypeId:              "0100",
		TxnUniqueId:            "TXN-1",
	}
}

func mpgsTxn(network string) mpgs.Txn {
	dt := time.Date(2026, 8, 13, 9, 0, 0, 0, time.Local)
	return mpgs.Txn{
		SerialNumber:           43,
		InsCode:                1,
		Network:                network,
		Mcc:                    "5499",
		TxnCode:                "01",
		TxnId:                  "MPGS-43",
		ResponseCode:           "00",
		ApprovalCode:           "ABC123",
		Rrn:                    "987654321098",
		PosEntryMode:           "012",
		PosConditionCode:       "01",
		ServiceCode:            "101",
		CardSeqNumber:          "001",
		TerminalType:           "POS",
		TxnAmount:              100.0,
		SetlAmount:             100.0,
		CashBackAmount:         0.0,
		NetAmount:              100.0,
		TxnCurCode:             "784",
		SetlCurCode:            "784",
		FeePgmIndicator:        "N",
		ReImbursementAttribute: "B",
		MotoEcomIndicator:      "S",
		TerminalCapability:     "00",
		TrlCapabilities:        "00",
		Mvv:                    "0A01020304",
		NetworkData:            "",
		Maid:                   "",
		MeCategoryType:         "5",
		ChAuthAbility:          "2",
		CardInputAbility:       "2",
		CardCaptureAbility:     "1",
		CardInputMode:          "0",
		ChPresent:              "1",
		CardPresent:            "0",
		OprtEnvironment:        "5",
		AcqInstConCode:         "784",
		EncCardNumber:          "4111111111111111",
		CardNumber:             "4111111111111111",
		TxnDateTime:            dt,
		MsgTypeId:              "0100",
		TxnUniqueId:            "TXN-M1",
	}
}

// VISA via the TLF mapper against the live jar.
func TestLive_Tlf_Calculate_Visa(t *testing.T) {
	if !reachable(t) {
		t.Skip("irf-service not reachable at " + baseURL())
	}
	c := tlf.NewClient(baseURL(), secret())
	vo, calculated, err := c.Calculate(tlfTxn("VISA"))
	if err != nil {
		t.Fatalf("error: %v", err)
	}
	if !calculated {
		t.Fatal("expected calculated=true")
	}
	if vo == nil || vo.IrfCountry != "XX" {
		t.Fatalf("expected VISA fallback XX/0.0, got %+v", vo)
	}
}

// Every network key the service wires must answer (200) through the TLF mapper.
func TestLive_Tlf_Calculate_AllNetworks(t *testing.T) {
	if !reachable(t) {
		t.Skip("irf-service not reachable at " + baseURL())
	}
	c := tlf.NewClient(baseURL(), secret())
	for _, net := range []string{"VISA", "VSMS", "MCI", "MDS", "UAESWITCH", "JAYWAN", "OMANNET", "ONUS"} {
		vo, calculated, err := c.Calculate(tlfTxn(net))
		if err != nil {
			t.Fatalf("%s: %v", net, err)
		}
		if !calculated {
			t.Fatalf("%s: expected calculated=true", net)
		}
		if vo == nil {
			t.Fatalf("%s: expected a result VO", net)
		}
		t.Logf("%s => calculated=true ird=%q country=%q amount=%v", net, vo.IrdCode, vo.IrfCountry, vo.IrfAmount)
	}
}

// Same sweep through the MPGS mapper.
func TestLive_Mpgs_Calculate_AllNetworks(t *testing.T) {
	if !reachable(t) {
		t.Skip("irf-service not reachable at " + baseURL())
	}
	c := mpgs.NewClient(baseURL(), secret())
	for _, net := range []string{"VISA", "MCI", "MDS", "UAESWITCH", "JAYWAN", "OMANNET", "ONUS"} {
		vo, calculated, err := c.Calculate(mpgsTxn(net))
		if err != nil {
			t.Fatalf("%s: %v", net, err)
		}
		if !calculated {
			t.Fatalf("%s: expected calculated=true", net)
		}
		if vo == nil {
			t.Fatalf("%s: expected a result VO", net)
		}
		t.Logf("%s => calculated=true ird=%q country=%q amount=%v", net, vo.IrdCode, vo.IrfCountry, vo.IrfAmount)
	}
}

// Callback enqueue round-trips against the jar's own H2 (returns a real serial).
func TestLive_Callback_Enqueue(t *testing.T) {
	if !reachable(t) {
		t.Skip("irf-service not reachable at " + baseURL())
	}
	c := tlf.NewClient(baseURL(), secret())
	vo := &irf.IrfResultVo{IrdCode: "AO"}
	serial, err := c.EnqueueCallback(tlfTxn("VISA"), vo)
	if err != nil {
		t.Fatalf("enqueue: %v", err)
	}
	if serial <= 0 {
		t.Fatalf("expected a positive serial from the jar, got %d", serial)
	}
	t.Logf("enqueued callback serial=%d", serial)
}
