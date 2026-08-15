package tlfsvc

import (
	"context"
	"errors"
	"log/slog"
	"testing"
	"time"

	"empay/irf/cryptapi"
	"empay/irf/irf"
	"empay/irf/tlf"
)

// recordingIrfClient records the tlf.Txn passed to Calculate and returns the
// deterministic IRF result (same values as tlfStubIrfClient).
type recordingIrfClient struct {
	got tlf.Txn
}

func (r *recordingIrfClient) Calculate(t tlf.Txn) (*irf.IrfResultVo, bool, error) {
	r.got = t
	return tlfStubIrfClient{}.Calculate(t)
}

func (r *recordingIrfClient) EnqueueCallback(t tlf.Txn, vo *irf.IrfResultVo) (int, error) {
	return 7, nil
}

// stubCrypto resolves a fixed token->pan map (implements cryptapi.CardCrypto).
type stubCrypto struct {
	dec map[string]string
}

func (s stubCrypto) GetCardNumber(tokens []string) map[string]string {
	if s.dec == nil {
		return nil
	}
	out := map[string]string{}
	for _, t := range tokens {
		if v, ok := s.dec[t]; ok {
			out[t] = v
		}
	}
	if len(out) == 0 {
		return nil
	}
	return out
}

func (s stubCrypto) GetToken(cardNumbers []string) map[string]string { return nil }

var _ cryptapi.CardCrypto = stubCrypto{}

func newService(irf IrfCalc, fs *fakeStore, crypto cryptapi.CardCrypto) *Service {
	if fs == nil {
		fs = &fakeStore{serial: 42}
	}
	return &Service{
		Store:     fs,
		IrfClient: irf,
		Log:       slog.Default(),
		Cfg:       Config{InsCode: 1, IntCode: 11, KafkaIntCode: 99, UserSerNumber: 4, JobNumber: 1, ExchangeRate: 0.27},
		Crypto:    crypto,
	}
}

func TestStage1DecryptsCard(t *testing.T) {
	fs := &fakeStore{serial: 42}
	svc := newService(&recordingIrfClient{}, fs, stubCrypto{dec: map[string]string{"tok-123": "4111111111111111"}})

	_, w, err := svc.Stage1(context.Background(), &RequestVo{Payload: kafkaSuccessPayload()})
	if err != nil {
		t.Fatalf("Stage1: %v", err)
	}
	if w.cardNumber != "4111111111111111" {
		t.Fatalf("cardNumber = %q, want decrypted 4111111111111111", w.cardNumber)
	}
	// The stored entity keeps the payload (masked) pan.
	if w.overlay.Entity.CardNumber != "6690109700100010" {
		t.Fatalf("stored pan = %q, want payload pan 6690109700100010", w.overlay.Entity.CardNumber)
	}
	if w.overlay.Entity.GenStatus != 9 {
		t.Fatalf("genStatus = %d, want 9 (in-flight) after stage 1", w.overlay.Entity.GenStatus)
	}
}

func TestStage1NoCryptoUsesPayloadPan(t *testing.T) {
	svc := newService(&recordingIrfClient{}, nil, nil)

	_, w, err := svc.Stage1(context.Background(), &RequestVo{Payload: kafkaSuccessPayload()})
	if err != nil {
		t.Fatalf("Stage1: %v", err)
	}
	if w.cardNumber != "6690109700100010" {
		t.Fatalf("cardNumber = %q, want payload pan (no crypto configured)", w.cardNumber)
	}
}

func TestStage1CryptFailedEmptyToken(t *testing.T) {
	svc := newService(&recordingIrfClient{}, nil, stubCrypto{dec: map[string]string{"tok-123": "4111111111111111"}})
	p := kafkaSuccessPayload()
	p.TokenIdentifier = ""

	resp, _, err := svc.Stage1(context.Background(), &RequestVo{Payload: p})
	if !errors.Is(err, ErrCryptAPIFailed) {
		t.Fatalf("err = %v, want ErrCryptAPIFailed", err)
	}
	if resp["status"] != "Crypt API Failed , No CardNumber found" {
		t.Fatalf("status = %q, want crypt failed status", resp["status"])
	}
}

func TestStage1CryptFailedNoCardNumber(t *testing.T) {
	svc := newService(&recordingIrfClient{}, nil, stubCrypto{dec: map[string]string{"tok-other": "4111111111111111"}})
	p := kafkaSuccessPayload()
	p.TokenIdentifier = "tok-unknown"

	_, _, err := svc.Stage1(context.Background(), &RequestVo{Payload: p})
	if !errors.Is(err, ErrCryptAPIFailed) {
		t.Fatalf("err = %v, want ErrCryptAPIFailed", err)
	}
}

func TestStage2AppliesIRFAndReadies(t *testing.T) {
	irf := &recordingIrfClient{}
	fs := &fakeStore{serial: 42}
	svc := newService(irf, fs, stubCrypto{dec: map[string]string{"tok-123": "4111111111111111"}})

	_, w, err := svc.Stage1(context.Background(), &RequestVo{Payload: kafkaSuccessPayload()})
	if err != nil {
		t.Fatalf("Stage1: %v", err)
	}
	if err := svc.Stage2(context.Background(), w); err != nil {
		t.Fatalf("Stage2: %v", err)
	}
	// The decrypted PAN feeds the irf-service calculate call.
	if irf.got.CardNumber != "4111111111111111" {
		t.Fatalf("irf card = %q, want decrypted 4111111111111111", irf.got.CardNumber)
	}
	if irf.got.EncCardNumber != "tok-123" {
		t.Fatalf("irf enc card = %q, want tok-123", irf.got.EncCardNumber)
	}
	// The row flipped from 9 (in-flight) to 3 (ready for the split).
	if got := fs.readyList(); len(got) != 1 || got[0] != 42 {
		t.Fatalf("ready = %v, want [42] after stage 2", got)
	}
}

func TestWorkerPoolFinalizesTxn(t *testing.T) {
	irf := &recordingIrfClient{}
	fs := &fakeStore{serial: 42}
	svc := newService(irf, fs, stubCrypto{dec: map[string]string{"tok-123": "4111111111111111"}})

	ctx, cancel := context.WithCancel(context.Background())
	defer cancel()
	pool := NewWorkerPool(svc, 2, 4)
	pool.Start(ctx)

	_, w, err := svc.Stage1(context.Background(), &RequestVo{Payload: kafkaSuccessPayload()})
	if err != nil {
		t.Fatalf("Stage1: %v", err)
	}
	pool.Submit(ctx, w)

	waitReady(t, fs, 42)
	if irf.got.CardNumber != "4111111111111111" {
		t.Fatalf("irf card = %q, want decrypted PAN (async stage 2)", irf.got.CardNumber)
	}
}

func TestWorkerPoolReaperReenqueues(t *testing.T) {
	irf := &recordingIrfClient{}
	fs := &fakeStore{serial: 42, pending: []int{42}}
	svc := newService(irf, fs, nil)

	ctx, cancel := context.WithCancel(context.Background())
	defer cancel()
	pool := NewWorkerPool(svc, 1, 4)
	pool.Start(ctx)

	// Seed the pool's in-memory registry as if stage 1 had submitted this item
	// before its worker failed; the DB row is still at gen_status=9 (fakeStore.pending).
	_, w, err := svc.Stage1(context.Background(), &RequestVo{Payload: kafkaSuccessPayload()})
	if err != nil {
		t.Fatalf("Stage1: %v", err)
	}
	pool.mu.Lock()
	pool.bySer[42] = w
	pool.mu.Unlock()

	if err := pool.reapOnce(ctx, time.Now().Add(-time.Minute)); err != nil {
		t.Fatalf("reapOnce: %v", err)
	}
	waitReady(t, fs, 42)
}

// waitReady polls until the fake store records `ser` as readied (stage-2 flip
// to gen_status=3), failing the test after a short deadline.
func waitReady(t *testing.T, fs *fakeStore, ser int) {
	t.Helper()
	deadline := time.Now().Add(3 * time.Second)
	for time.Now().Before(deadline) {
		if got := fs.readyList(); len(got) == 1 && got[0] == ser {
			return
		}
		time.Sleep(10 * time.Millisecond)
	}
	t.Fatalf("ready = %v, want [%d] (async stage 2 did not complete)", fs.readyList(), ser)
}
