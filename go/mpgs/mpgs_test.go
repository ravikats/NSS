package mpgs

import (
	"testing"
	"time"

	"empay/irf/irf"
	"empay/irf/irf/stub"
)

func sampleTxn() Txn {
	return Txn{
		SerialNumber:           777,
		InsCode:                7085,
		Network:                "VISA",
		Mcc:                    "5499",
		TxnAmount:              100.0,
		SetlAmount:             100.0,
		TxnCurCode:             "784",
		SetlCurCode:            "784",
		PosEntryMode:           "012",
		ApprovalCode:           "ABC123",
		AcqInstConCode:         "784",
		Rrn:                    "rrn-mpgs-001",
		ReImbursementAttribute: "B",
		CardNumber:             "4111111111111111",
		EncCardNumber:          "tok-001",
		TxnDateTime:            time.Date(2024, 1, 2, 3, 4, 5, 0, time.UTC),
	}
}

func TestMpgs_CalculateVisaFallback(t *testing.T) {
	c, _ := stub.NewClient(t)
	client := IrfClient{Client: c}

	vo, calculated, err := client.Calculate(sampleTxn())
	if err != nil {
		t.Fatalf("Calculate: %v", err)
	}
	if !calculated {
		t.Fatal("expected calculated=true")
	}
	if vo == nil || vo.IrfCountry != "XX" {
		t.Fatalf("expected fallback VO (XX), got %+v", vo)
	}

	serial, err := client.EnqueueCallback(sampleTxn(), vo)
	if err != nil {
		t.Fatalf("EnqueueCallback: %v", err)
	}
	if serial != 50001 {
		t.Fatalf("expected serial 50001, got %d", serial)
	}
}

func TestMpgs_FlushAndRetry(t *testing.T) {
	c, _ := stub.NewClient(t)
	client := IrfClient{Client: c}
	ins := sampleTxn().InsCode
	if err := client.Client.FlushCallbacks(&ins, nil); err != nil {
		t.Fatalf("FlushCallbacks: %v", err)
	}
	ok, err := client.Client.RetryCallback(50001)
	if err != nil {
		t.Fatalf("RetryCallback: %v", err)
	}
	if !ok {
		t.Fatal("expected retry ok=true")
	}
}

func TestMpgs_BadSecret_Unauthorized(t *testing.T) {
	_, srv := stub.NewClient(t)
	bad := &irf.Client{BaseURL: srv.URL, Secret: "nope", HTTP: srv.Client()}
	client := IrfClient{Client: bad}
	if _, _, err := client.Calculate(sampleTxn()); err == nil {
		t.Fatal("expected unauthorized error")
	}
}
