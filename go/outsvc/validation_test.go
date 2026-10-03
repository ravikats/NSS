package outsvc

import (
	"context"
	"encoding/json"
	"net/http"
	"net/http/httptest"
	"testing"
	"time"
)

func mercurySvcForValidation(t *testing.T) (*OutgoingService, *MercuryAcqTxnWorkEntity) {
	t.Helper()
	st := newMercuryFakeStore()
	txn := mercuryWorkEntity()
	txn.SerialNumber = 1
	txn.TxnAmount = 10.00
	txn.ChargeType = "AA"
	st.work = []*MercuryAcqTxnWorkEntity{txn}

	s := NewOutgoingService(OutgoingConfig{
		InsCode:           1,
		InsShortName:      "IRF",
		UpdatedUser:       4,
		ReconOutDir:       t.TempDir(),
		CurrencyCodeKafka: "AED000",
		MercuryMemberId:   "24013923",
	}, st, &fakeCrypto{dec: map[string]string{"tok1": "6690109700100010"}})
	s.now = func() time.Time { return time.Date(2026, 8, 3, 13, 37, 57, 0, time.UTC) }
	return s, txn
}

func TestValidationRecordedOnSuccess(t *testing.T) {
	s, _ := mercurySvcForValidation(t)
	from := time.Date(2026, 8, 3, 0, 0, 0, 0, time.UTC)
	to := time.Date(2026, 8, 3, 23, 59, 59, 0, time.UTC)
	if got := s.ProcessMercuryOutgoing(context.Background(), 1, 4, 5, "IRF", &from, &to); got != "Success" {
		t.Fatalf("ProcessMercuryOutgoing = %q, want Success", got)
	}
	results := s.Validations()
	if len(results) != 1 {
		t.Fatalf("Validations() len = %d, want 1", len(results))
	}
	v := results[0]
	if !v.OK {
		t.Errorf("OK = false, errors = %v", v.Errors)
	}
	if v.File != "Documents.2401392320260803" {
		t.Errorf("File = %q", v.File)
	}
	if v.Network != "MERCURY" || v.Batches != 1 || v.TxnCount != 1 {
		t.Errorf("network/batches/txns = %q/%d/%d, want MERCURY/1/1", v.Network, v.Batches, v.TxnCount)
	}
	if v.SumAmount != 10.00 {
		t.Errorf("SumAmount = %v, want 10", v.SumAmount)
	}
	if v.ValidatedAt.IsZero() {
		t.Error("ValidatedAt is zero")
	}
}

func TestValidationsEndpointReturnsHistory(t *testing.T) {
	s, _ := mercurySvcForValidation(t)
	from := time.Date(2026, 8, 3, 0, 0, 0, 0, time.UTC)
	to := time.Date(2026, 8, 3, 23, 59, 59, 0, time.UTC)
	s.ProcessMercuryOutgoing(context.Background(), 1, 4, 5, "IRF", &from, &to)

	ctl := NewOutgoingController(s)
	rec := httptest.NewRecorder()
	ctl.Validations(rec, httptest.NewRequest(http.MethodGet, "/outgoing/v1/validations", nil))

	var body struct {
		Validations []ValidationResult `json:"validations"`
	}
	if err := json.Unmarshal(rec.Body.Bytes(), &body); err != nil {
		t.Fatalf("decode: %v (%s)", err, rec.Body.String())
	}
	if len(body.Validations) != 1 || !body.Validations[0].OK {
		t.Fatalf("validations = %+v", body.Validations)
	}
}

func TestValidationsEndpointEmptyBeforeFirstRun(t *testing.T) {
	s, _ := mercurySvcForValidation(t)
	rec := httptest.NewRecorder()
	NewOutgoingController(s).Validations(rec, httptest.NewRequest(http.MethodGet, "/outgoing/v1/validations", nil))
	if got := rec.Body.String(); got != "{\"validations\":[]}\n" {
		t.Errorf("body = %s", got)
	}
}

func TestNewValidationResultCarriesErrors(t *testing.T) {
	res := newValidationResult("MERCURY", "Documents.1", nil, context.DeadlineExceeded, time.Now())
	if res.OK {
		t.Error("OK = true, want false")
	}
	if len(res.Errors) != 1 || res.Errors[0] != context.DeadlineExceeded.Error() {
		t.Errorf("Errors = %v", res.Errors)
	}
}

func TestValidationsHistoryIsCapped(t *testing.T) {
	s, _ := mercurySvcForValidation(t)
	for i := 0; i < maxValidationHistory+10; i++ {
		s.recordValidation(&ValidationResult{File: "f", OK: true})
	}
	if got := len(s.Validations()); got != maxValidationHistory {
		t.Errorf("history len = %d, want %d", got, maxValidationHistory)
	}
}
