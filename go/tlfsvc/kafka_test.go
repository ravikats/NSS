package tlfsvc

import (
	"context"
	"encoding/json"
	"log/slog"
	"strings"
	"testing"
	"time"

	"github.com/segmentio/kafka-go"
)

func TestKafkaProcessSuccess(t *testing.T) {
	fs := &fakeStore{serial: 42}
	svc := &Service{
		Store:     fs,
		IrfClient: tlfStubIrfClient{},
		Log:       slog.Default(),
		Cfg:       Config{InsCode: 1, IntCode: 11, KafkaIntCode: 99, UserSerNumber: 4, JobNumber: 1, ExchangeRate: 0.27},
	}
	ack := &stubSink{}
	errSink := &stubSink{}
	c := &Consumer{Svc: svc, Log: slog.Default(), Cfg: kafkaTestCfg(), Ack: ack, Err: errSink}

	c.process(context.Background(), kafkaJSON(kafkaSuccessPayload()))

	if len(ack.msgs) != 1 {
		t.Fatalf("ack messages = %d, want 1", len(ack.msgs))
	}
	if !strings.Contains(ack.msgs[0], "Transaction Successfully Accepted") {
		t.Fatalf("ack = %q, want accepted message", ack.msgs[0])
	}
	if !strings.Contains(ack.msgs[0], "rrn=621007888375") {
		t.Fatalf("ack = %q, want rrn echoed", ack.msgs[0])
	}
	if len(errSink.msgs) != 0 {
		t.Fatalf("err messages = %d, want 0", len(errSink.msgs))
	}
	if len(fs.rejected) != 0 {
		t.Fatalf("rejected inserts = %d, want 0", len(fs.rejected))
	}
}

func TestKafkaProcessValidationReject(t *testing.T) {
	fs := &fakeStore{serial: 42}
	svc := &Service{
		Store:     fs,
		IrfClient: tlfStubIrfClient{},
		Log:       slog.Default(),
		Cfg:       Config{InsCode: 1, IntCode: 11, KafkaIntCode: 99, UserSerNumber: 4, JobNumber: 1, ExchangeRate: 0.27},
	}
	ack := &stubSink{}
	errSink := &stubSink{}
	c := &Consumer{Svc: svc, Log: slog.Default(), Cfg: kafkaTestCfg(), Ack: ack, Err: errSink}

	p := kafkaSuccessPayload()
	p.AmountTransaction = "000000000000" // zero -> validation error
	c.process(context.Background(), kafkaJSON(p))

	if len(ack.msgs) != 1 {
		t.Fatalf("ack messages = %d, want 1", len(ack.msgs))
	}
	if !strings.Contains(ack.msgs[0], "Transaction Rejected") {
		t.Fatalf("ack = %q, want rejected message", ack.msgs[0])
	}
	if !strings.Contains(ack.msgs[0], "INVALID AMOUNT") {
		t.Fatalf("ack = %q, want INVALID AMOUNT validation error", ack.msgs[0])
	}
	if len(errSink.msgs) != 0 {
		t.Fatalf("err messages = %d, want 0", len(errSink.msgs))
	}
	if len(fs.rejected) != 1 || !strings.Contains(fs.rejected[0], "INVALID AMOUNT") {
		t.Fatalf("rejected inserts = %v, want [INVALID AMOUNT...]", fs.rejected)
	}
}

func TestKafkaProcessSchemeLengthReject(t *testing.T) {
	fs := &fakeStore{serial: 42}
	svc := &Service{
		Store:     fs,
		IrfClient: tlfStubIrfClient{},
		Log:       slog.Default(),
		Cfg:       Config{InsCode: 1, IntCode: 11, KafkaIntCode: 99, UserSerNumber: 4, JobNumber: 1, ExchangeRate: 0.27},
	}
	ack := &stubSink{}
	errSink := &stubSink{}
	c := &Consumer{Svc: svc, Log: slog.Default(), Cfg: kafkaTestCfg(), Ack: ack, Err: errSink}

	p := kafkaSuccessPayload()
	p.Scheme = "DINERS CLUB INTERNATIONAL" // 25 chars -> @Length(max=20)
	c.process(context.Background(), kafkaJSON(p))

	if len(ack.msgs) != 1 {
		t.Fatalf("ack messages = %d, want 1", len(ack.msgs))
	}
	if !strings.Contains(ack.msgs[0], "Transaction Rejected") {
		t.Fatalf("ack = %q, want rejected message", ack.msgs[0])
	}
	if !strings.Contains(ack.msgs[0], "scheme:The maximum length is 20") {
		t.Fatalf("ack = %q, want scheme length validation error", ack.msgs[0])
	}
	if len(errSink.msgs) != 0 {
		t.Fatalf("err messages = %d, want 0", len(errSink.msgs))
	}
	if len(fs.rejected) != 1 || !strings.Contains(fs.rejected[0], "scheme:The maximum length is 20") {
		t.Fatalf("rejected inserts = %v, want [scheme:The maximum length is 20]", fs.rejected)
	}
}

func TestKafkaProcessParseError(t *testing.T) {
	fs := &fakeStore{serial: 42}
	svc := &Service{
		Store:     fs,
		IrfClient: tlfStubIrfClient{},
		Log:       slog.Default(),
		Cfg:       Config{InsCode: 1, IntCode: 11, KafkaIntCode: 99, UserSerNumber: 4, JobNumber: 1, ExchangeRate: 0.27},
	}
	ack := &stubSink{}
	errSink := &stubSink{}
	c := &Consumer{Svc: svc, Log: slog.Default(), Cfg: kafkaTestCfg(), Ack: ack, Err: errSink}

	raw := "{not valid json"
	c.process(context.Background(), raw)

	if len(ack.msgs) != 0 {
		t.Fatalf("ack messages = %d, want 0", len(ack.msgs))
	}
	if len(errSink.msgs) != 1 || errSink.msgs[0] != raw {
		t.Fatalf("err messages = %v, want raw input %q", errSink.msgs, raw)
	}
	if len(fs.rejected) != 0 {
		t.Fatalf("rejected inserts = %d, want 0", len(fs.rejected))
	}
}

func TestKafkaProcessMissingRRN(t *testing.T) {
	fs := &fakeStore{serial: 42}
	svc := &Service{
		Store:     fs,
		IrfClient: tlfStubIrfClient{},
		Log:       slog.Default(),
		Cfg:       Config{InsCode: 1, IntCode: 11, KafkaIntCode: 99, UserSerNumber: 4, JobNumber: 1, ExchangeRate: 0.27},
	}
	ack := &stubSink{}
	errSink := &stubSink{}
	c := &Consumer{Svc: svc, Log: slog.Default(), Cfg: kafkaTestCfg(), Ack: ack, Err: errSink}

	p := kafkaSuccessPayload()
	p.RetRefNumber = ""
	c.process(context.Background(), kafkaJSON(p))

	if len(ack.msgs) != 0 {
		t.Fatalf("ack messages = %d, want 0 (missing RRN is fatal in Java)", len(ack.msgs))
	}
	if len(errSink.msgs) != 1 {
		t.Fatalf("err messages = %d, want 1", len(errSink.msgs))
	}
	if len(fs.rejected) != 0 {
		t.Fatalf("rejected inserts = %d, want 0", len(fs.rejected))
	}
}

func TestKafkaRunLoop(t *testing.T) {
	fs := &fakeStore{serial: 42}
	svc := &Service{
		Store:     fs,
		IrfClient: tlfStubIrfClient{},
		Log:       slog.Default(),
		Cfg:       Config{InsCode: 1, IntCode: 11, KafkaIntCode: 99, UserSerNumber: 4, JobNumber: 1, ExchangeRate: 0.27},
	}
	src := &stubSource{msgs: []kafka.Message{{Value: []byte(kafkaJSON(kafkaSuccessPayload()))}}}
	ack := &stubSink{}
	c := &Consumer{Svc: svc, Log: slog.Default(), Cfg: kafkaTestCfg(), Src: src, Ack: ack, Err: &stubSink{}}

	ctx, cancel := context.WithTimeout(context.Background(), 5*time.Second)
	defer cancel()
	go c.Run(ctx)

	deadline := time.Now().Add(3 * time.Second)
	for time.Now().Before(deadline) {
		if len(ack.msgs) >= 1 {
			break
		}
		time.Sleep(10 * time.Millisecond)
	}
	cancel()
	if len(ack.msgs) != 1 {
		t.Fatalf("ack messages = %d, want 1 (from Run loop)", len(ack.msgs))
	}
	if len(src.committed) != 1 {
		t.Fatalf("committed = %d, want 1", len(src.committed))
	}
}

func kafkaTestCfg() KafkaConfig {
	return KafkaConfig{Brokers: []string{"localhost:9092"}, GroupID: DefaultKafkaGroup, TxnTopic: DefaultKafkaTxnTopic, AckTopic: DefaultKafkaAckTopic, ErrTopic: DefaultKafkaErrTopic, JobNumber: 1, FileName: "KAFKA"}
}

func kafkaJSON(p *SwitchExtractVo) string {
	b, err := json.Marshal(RequestVo{Payload: p})
	if err != nil {
		panic(err)
	}
	return string(b)
}

func kafkaSuccessPayload() *SwitchExtractVo {
	return &SwitchExtractVo{
		BankCode:                "CPBA",
		Network:                 "visa",
		Scheme:                  "VISA",
		Mti:                     "0110",
		CardNumber:              "6690109700100010",
		TokenIdentifier:         "tok-123",
		ProcessCode:             "000000",
		AmountTransaction:       "000000095100",
		TxnDateTime:             "0814103000",
		Stan:                    "888375",
		LocalTxnTime:            "103000",
		LocalTxnDate:            "0814",
		ExpiryDate:              "2812",
		SettlementDate:          "20260814",
		Mcc:                     "9211",
		PosEntryMode:            "0710",
		PanSequence:             "0",
		PosCode:                 "00",
		TxnFeeAmount:            "0.00",
		RetRefNumber:            "621007888375",
		AuthIdResponse:          "962981",
		ResponseCode:            "00",
		ServiceRestrictionCode:  "00",
		TerminalId:              "TERM001",
		MerchantId:              "MERCH001",
		CardAcceptorName:        "VAULTSPAY",
		CardAcceptorCity:        "Dubai",
		CardAcceptorCountryCode: "999",
		TxnCurrencyCode:         "840",
		SettleCurrencyCode:      "840",
		CashBackAmount:          "0.00",
		TxnSource:               "POS",
		SettlementIndicator:     "Y",
		OnusOffusIndicator:      "OFFUS",
		SmsDmsIndicator:         "DMS",
		UniqueId:                "ref-1",
	}
}

// stubSource feeds canned messages then blocks until the context is cancelled.
type stubSource struct {
	msgs      []kafka.Message
	i         int
	committed []kafka.Message
}

func (s *stubSource) FetchMessage(ctx context.Context) (kafka.Message, error) {
	if s.i < len(s.msgs) {
		m := s.msgs[s.i]
		s.i++
		return m, nil
	}
	<-ctx.Done()
	return kafka.Message{}, ctx.Err()
}

func (s *stubSource) CommitMessages(_ context.Context, msgs ...kafka.Message) error {
	s.committed = append(s.committed, msgs...)
	return nil
}

func (s *stubSource) Close() error { return nil }

type stubSink struct {
	msgs []string
}

func (s *stubSink) WriteMessages(_ context.Context, msgs ...kafka.Message) error {
	for _, m := range msgs {
		s.msgs = append(s.msgs, string(m.Value))
	}
	return nil
}

func (s *stubSink) Close() error { return nil }
