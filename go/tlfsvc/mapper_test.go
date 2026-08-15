package tlfsvc

import (
	"context"
	"encoding/json"
	"log/slog"
	"sync"
	"testing"
	"time"

	"empay/irf/irf"
	"empay/irf/tlf"
)

func TestNetworkMapping_JaywanSMS(t *testing.T) {
	if got := NetworkMapping("SMS", "uaeswitch"); got != "UAESWITCH" {
		t.Fatalf("network = %q, want UAESWITCH", got)
	}
}

func TestOutgoingStatusCheck_JaywanSMS(t *testing.T) {
	// 00/SMS/JAYWAN -> requires DMS (outgoing=false), mirrors the UAT nuance.
	if OutgoingStatusCheck("00", "000000", "S", "O", "JAYWAN", "UAESWITCH") {
		t.Fatalf("expected non-outgoing for JAYWAN + UAESWITCH + SMS")
	}
	// 00/DMS/JAYWAN -> outgoing.
	if !OutgoingStatusCheck("00", "000000", "D", "O", "JAYWAN", "UAESWITCH") {
		t.Fatalf("expected outgoing for JAYWAN + UAESWITCH + DMS")
	}
}

func TestIrfStatusCheck_JaywanSMS(t *testing.T) {
	// 00/000000/SMS -> IRF applies.
	if !IrfStatusCheck("00", "000000", "S", "O") {
		t.Fatalf("expected IRF status true")
	}
	// non-zero response -> no IRF.
	if IrfStatusCheck("01", "000000", "S", "O") {
		t.Fatalf("expected IRF status false for non-zero response")
	}
}

func TestProcessKafkaTxn_JaywanSMSPayload(t *testing.T) {
	reqJSON := `[{"payload":{
	  "bank_id":"CPBA","sub_route":"uaeswitch","scheme":"JAYWAN",
	  "switch_mti":"0130","pan":"6690109700100010",
	  "switch_crypt_token":"8d3fd697df244ac885f70c379623c529",
	  "processing_code":"000000","amount":"000000095100",
	  "transmission_date":"0814103000","stan":"888375",
	  "local_time":"103000","local_date":"0814","exp_date":"2812",
	  "settlement_date":"20260814","mcc":"9211",
	  "acq_inst_country_code":"999","pos_entry_mode":"0710",
	  "pan_sequence_number":"0","pos_condition_code":"00",
	  "txn_fee_amount":"0.00","rrn":"621007888375","auth_code":"962981",
	  "network_response_code":"00","service_restriction_code":"00",
	  "terminal_id":"TERM001","merchant_id":"MERCH001",
	  "card_acceptor_name":"VAULTSPAY","card_acceptor_city":"Dubai",
	  "card_acceptor_state_code":"","card_acceptor_pin_code":"00000",
	  "card_acceptor_country_code":"999","currency_code":"840",
	  "settlement_code":"840","additional_amount":"0.00","channel":"POS",
	  "settlement_indicator":"Y","onus_offus_indicator":"OFFUS",
	  "sms_dms_indicator":"SMS","merchant_category_type":"","auth_amount":"",
	  "location_region_code":"","invoiceNumber":"","tip_amount":""
	}}]`

	var items []*RequestVo
	if err := json.Unmarshal([]byte(reqJSON), &items); err != nil {
		t.Fatalf("unmarshal: %v", err)
	}
	if len(items) != 1 || items[0].Payload == nil {
		t.Fatalf("expected one payload item")
	}

	fs := &fakeStore{serial: 42}
	svc := &Service{
		Store:     fs,
		IrfClient: tlfStubIrfClient{},
		Log:       slog.Default(),
		Cfg:       Config{InsCode: 1, IntCode: 11, UserSerNumber: 4, JobNumber: 1, ExchangeRate: 0.27},
	}

	_, w, err := svc.Stage1(context.Background(), items[0])
	if err != nil {
		t.Fatalf("Stage1: %v", err)
	}
	if w == nil {
		t.Fatal("expected work item")
	}
	if w.overlay.Entity.SerialNumber != 42 {
		t.Fatalf("serial = %d, want 42", w.overlay.Entity.SerialNumber)
	}
	if w.overlay.Scheme != "JAYWAN" {
		t.Fatalf("scheme overlay = %q, want JAYWAN", w.overlay.Scheme)
	}
	if w.overlay.Network != "UAESWITCH" {
		t.Fatalf("network = %q, want UAESWITCH (derived via networkMapping)", w.overlay.Network)
	}
	// In-flight marker: IRF-needed rows must not be visible to the split yet.
	if w.overlay.Entity.GenStatus != 9 {
		t.Fatalf("genStatus = %d, want 9 (in-flight) after stage 1", w.overlay.Entity.GenStatus)
	}
	if err := svc.Stage2(context.Background(), w); err != nil {
		t.Fatalf("Stage2: %v", err)
	}
	// IRF columns were populated on the entity and the row flipped to ready.
	if w.overlay.IrfAmount != 95.1 {
		t.Fatalf("irf amount = %v, want 95.1", w.overlay.IrfAmount)
	}
	if w.overlay.CardDomIntlFlag != "D" {
		t.Fatalf("domIntlFlag = %q, want D", w.overlay.CardDomIntlFlag)
	}
	if len(fs.ready) != 1 || fs.ready[0] != 42 {
		t.Fatalf("ready = %v, want [42] after stage 2", fs.ready)
	}
}

// tlfStubIrfClient is an in-process IrfClient returning a deterministic IRF
// result so the online flow can be validated without a live irf-service.
type tlfStubIrfClient struct{}

func (tlfStubIrfClient) Calculate(t tlf.Txn) (*irf.IrfResultVo, bool, error) {
	ird := 101
	fixed := 0.25
	pct := 1.0
	amt := 95.1
	amtUSD := 95.1
	return &irf.IrfResultVo{
		IrdSerNumber:  &ird,
		IrdCode:       "JAY1",
		IrfFixed:      &fixed,
		IrfPercentage: &pct,
		IrfAmount:     &amt,
		IrfAmountUSD:  &amtUSD,
		CardType:      "C",
		DomIntlFlag:   "D",
		GcmsProductID: "JAYWAN",
		IrfDesc:       "Jaywan fee",
	}, true, nil
}

func (tlfStubIrfClient) EnqueueCallback(t tlf.Txn, vo *irf.IrfResultVo) (int, error) {
	return 7, nil
}

type fakeStore struct {
	mu              sync.Mutex
	serial          int
	rejected        []string
	duplicate       bool
	ready           []int
	pending         []int
	mercuryInserted bool
}

func (f *fakeStore) Insert(ctx context.Context, e *Entity) (int, error) {
	if e.Entity.SerialNumber == 0 {
		e.Entity.SerialNumber = f.serial
	}
	return e.Entity.SerialNumber, nil
}

func (f *fakeStore) UpdateIrf(ctx context.Context, e *Entity, cleared bool) error { return nil }

func (f *fakeStore) SetOutgoing(ctx context.Context, ser int, out, in string) error { return nil }

func (f *fakeStore) SetReady(ctx context.Context, ser int) error {
	f.mu.Lock()
	defer f.mu.Unlock()
	f.ready = append(f.ready, ser)
	return nil
}

func (f *fakeStore) FindPendingIRFRows(ctx context.Context, olderThan time.Time) ([]int, error) {
	return f.pending, nil
}

// readyList is a thread-safe snapshot of the serials flipped to gen_status=3.
func (f *fakeStore) readyList() []int {
	f.mu.Lock()
	defer f.mu.Unlock()
	return append([]int{}, f.ready...)
}

func (f *fakeStore) FindInterfaceCode(ctx context.Context, category string, insCode int) (int, error) {
	return 21, nil
}

func (f *fakeStore) FindAcquirerBin(ctx context.Context, insCode int, binType string) (*AcquirerBin, bool, error) {
	return &AcquirerBin{AcqBin: "970962", McIcaNum: "034540"}, true, nil
}

func (f *fakeStore) FindCurrencyExponent(ctx context.Context, code string) (int, bool, error) {
	return 2, true, nil
}

func (f *fakeStore) InsertMercuryWork(ctx context.Context, m *MercuryWorkEntity) (int, error) {
	f.mercuryInserted = true
	return 999, nil
}

func (f *fakeStore) FindBusinessDate(ctx context.Context, insCode int) (*time.Time, error) {
	return nil, nil
}

func (f *fakeStore) FindByMsgTypeIdAndRrnAndProcCode(ctx context.Context, mti, rrn, procCode string) (bool, error) {
	return f.duplicate, nil
}

func (f *fakeStore) InsertRejectedTxn(ctx context.Context, r *RejectedTxn) (int, error) {
	f.rejected = append(f.rejected, r.RejReason)
	return 1, nil
}
