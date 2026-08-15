// Kafka ingest for the Go TLF service: the Go port of TxnFetchKafkaService
// (tlf-processing-service-java). It consumes a TLF payload from the transaction
// topic, validates it, runs the online IRF flow and replies on the ack/err
// topics.
//
// IMPORTANT: IRF calculation is NOT part of this service. The online flow
// (Service.Stage2) delegates the actual rate lookup to the external
// irf-service over HTTP via the shared tlf.IrfClient; this file only adds the
// Kafka consume -> validate -> stage 1 (insert) -> enqueue -> ack/err
// orchestration. Stage 2 (IRF) runs on the WorkerPool asynchronously (§7.12),
// so the consumer never blocks on irf-service. No calculator or
// IRF_RATE/IRF_CALLBACK logic lives here (irf-service owns IRF_CALLBACK).
package tlfsvc

import (
	"context"
	"encoding/json"
	"fmt"
	"log/slog"
	"strings"
	"time"
	"unicode/utf8"

	"github.com/segmentio/kafka-go"
)

// Topic/group defaults mirror the TxnFetchKafkaService constants.
const (
	DefaultKafkaTxnTopic = "oracle_TRANSACTIONS"
	DefaultKafkaAckTopic = "ack_TOPIC"
	DefaultKafkaErrTopic = "err_TOPIC"
	DefaultKafkaGroup    = "fetch-txn-group"
)

// KafkaConfig mirrors the TxnFetchKafkaService constants plus the spring-kafka
// listener wiring (@KafkaListener(topics=oracle_TRANSACTIONS, groupId=fetch-txn-group)).
type KafkaConfig struct {
	Brokers  []string
	GroupID  string
	TxnTopic string
	AckTopic string
	ErrTopic string
	// JobNumber and FileName mirror the literals TxnFetchKafkaService passes to
	// processOnlineTxn / insertRejectedTxns: (1, "KAFKA").
	JobNumber int
	FileName  string
}

// messageSource abstracts the kafka-go consumer for tests.
type messageSource interface {
	FetchMessage(ctx context.Context) (kafka.Message, error)
	CommitMessages(ctx context.Context, msgs ...kafka.Message) error
	Close() error
}

// messageSink abstracts the kafka-go producer for tests.
type messageSink interface {
	WriteMessages(ctx context.Context, msgs ...kafka.Message) error
	Close() error
}

// Consumer is the Go port of TxnFetchKafkaService. Src/Ack/Err are the
// injectable reader/producers; when nil they are created from Cfg in Run.
// Workers runs the async stage-2 IRF/finalize half; when nil (e.g. tests) the
// row is finalized inline so the ack still reflects a completed transaction.
type Consumer struct {
	Svc     *Service
	Workers *WorkerPool
	Log     *slog.Logger
	Cfg     KafkaConfig

	Src messageSource
	Ack messageSink
	Err messageSink
}

// ResponseVo mirrors com.empay.tlfprocessing.vo.ResponseVo, including the
// Lombok-generated toString that TxnFetchKafkaService sends to ack_TOPIC.
type ResponseVo struct {
	UniqueId          string   `json:"ref_id"`
	AmountTransaction string   `json:"amount"`
	Rrn               string   `json:"rrn"`
	CardAcceptorTid   string   `json:"terminal_id"`
	CardAcceptorId    string   `json:"merchant_id"`
	ResponseMessage   string   `json:"-"`
	ResponseCode      string   `json:"-"`
	Mti               string   `json:"mti"`
	ValidationErrors  []string `json:"-"`
}

// String mirrors ResponseVo.toString(): the exact message on ack_TOPIC.
func (r ResponseVo) String() string {
	return "ResponseVo(uniqueId=" + r.UniqueId +
		", amountTransaction=" + r.AmountTransaction +
		", rrn=" + r.Rrn +
		", cardAcceptorTid=" + r.CardAcceptorTid +
		", cardAcceptorId=" + r.CardAcceptorId +
		", responseMessage=" + r.ResponseMessage +
		", responseCode=" + r.ResponseCode +
		", mti=" + r.Mti +
		", validationErrors=" + fmt.Sprintf("%v", r.ValidationErrors) + ")"
}

// Run consumes TxnTopic until ctx is cancelled, processing each payload and
// replying on AckTopic/ErrTopic. At-least-once semantics: each message is
// committed only after processing returns.
func (c *Consumer) Run(ctx context.Context) error {
	src := c.reader()
	defer src.Close()
	ack, errw := c.writers()
	defer ack.Close()
	defer errw.Close()

	for {
		m, err := src.FetchMessage(ctx)
		if err != nil {
			if ctx.Err() != nil {
				return ctx.Err()
			}
			c.Log.Error("kafka fetch", "err", err)
			continue
		}
		c.process(ctx, string(m.Value))
		if err := src.CommitMessages(ctx, m); err != nil {
			c.Log.Warn("kafka commit", "topic", c.Cfg.TxnTopic, "err", err)
		}
	}
}

func (c *Consumer) reader() messageSource {
	if c.Src != nil {
		return c.Src
	}
	return kafka.NewReader(kafka.ReaderConfig{
		Brokers:  c.Cfg.Brokers,
		GroupID:  c.Cfg.GroupID,
		Topic:    c.Cfg.TxnTopic,
		MinBytes: 10e3,
		MaxBytes: 10e6,
	})
}

func (c *Consumer) writers() (messageSink, messageSink) {
	if c.Ack == nil {
		c.Ack = &kafka.Writer{Addr: kafka.TCP(c.Cfg.Brokers...), Topic: c.Cfg.AckTopic}
	}
	if c.Err == nil {
		c.Err = &kafka.Writer{Addr: kafka.TCP(c.Cfg.Brokers...), Topic: c.Cfg.ErrTopic}
	}
	return c.Ack, c.Err
}

// process mirrors TxnFetchKafkaService.consume: deserialise the payload, run the
// per-message flow, and on a parse failure send the raw input to ErrTopic.
func (c *Consumer) process(ctx context.Context, input string) {
	log := c.Log.With("src", "kafka")
	log.Info("kafka txn start")
	vo, err := unmarshalRequest(input)
	if err != nil {
		log.Error("consume kafka failed", "err", err)
		c.sendErr(ctx, input)
		return
	}
	c.processRequest(ctx, vo)
	log.Info("kafka txn complete")
}

// unmarshalRequest mirrors the ObjectMapper use in consume: FAIL_ON_UNKNOWN_PROPERTIES
// disabled (Go json ignores unknown fields by default).
func unmarshalRequest(input string) (*RequestVo, error) {
	var vo RequestVo
	if err := json.Unmarshal([]byte(input), &vo); err != nil {
		return nil, err
	}
	return &vo, nil
}

// processRequest mirrors TxnFetchKafkaService.parseMessage.
func (c *Consumer) processRequest(ctx context.Context, vo *RequestVo) {
	log := c.Log.With("src", "kafka")
	resp := &ResponseVo{}
	if vo == nil || vo.Payload == nil {
		c.sendErr(ctx, fmt.Sprintf("ResponseVo(uniqueId=null, amountTransaction=null, rrn=null, cardAcceptorTid=null, cardAcceptorId=null, responseMessage=Transaction loading Failed: payload is null, responseCode=null, mti=null, validationErrors=[]) "))
		return
	}
	p := vo.Payload
	resp.Rrn = p.RetRefNumber
	resp.Mti = p.Mti
	resp.CardAcceptorId = p.MerchantId
	resp.CardAcceptorTid = p.TerminalId
	resp.UniqueId = p.UniqueId
	resp.AmountTransaction = p.AmountTransaction

	// Missing RRN is fatal in Java (validateTxnRequest throws ValidationException
	// -> caught in parseMessage -> ErrTopic with requestObject.toString()). The Go
	// port sends the JSON form instead of the Java toString.
	if strings.TrimSpace(p.RetRefNumber) == "" {
		log.Error("request not loaded: INVALID RRN ; RRN is missing")
		c.sendErr(ctx, marshalRequest(vo))
		return
	}

	errors := c.validate(ctx, p)
	if len(errors) == 0 {
		resp.ResponseCode = p.ResponseCode
		m, w, err := c.Svc.Stage1(ctx, vo)
		if err != nil {
			// Stage 1 failed (validation, crypt, insert): reject and persist the
			// rejection, mirroring processOnlineTxn's non-200 path.
			status := m["status"]
			if status == "" {
				status = err.Error()
			}
			errors = append(errors, status)
			resp.ResponseMessage = "Transaction Rejected"
			resp.ValidationErrors = errors
			c.insertRejected(ctx, resp, p.TxnDateTime)
		} else {
			// Fast path: the row is inserted at PTR_GEN_STATUS=9. The ack is a
			// plain acceptance — the switch never receives the IRF result — so
			// stage 2 (IRF/finalize) can run asynchronously on the worker pool.
			resp.ResponseMessage = "Transaction Successfully Accepted"
			if c.Workers != nil {
				c.Workers.Submit(ctx, w)
			} else if err := c.Svc.Stage2(ctx, w); err != nil {
				c.Log.Warn("stage2 failed", "ser", w.ser, "err", err)
			}
		}
	} else {
		resp.ResponseCode = p.ResponseCode
		resp.ResponseMessage = "Transaction Rejected"
		resp.ValidationErrors = errors
		c.insertRejected(ctx, resp, p.TxnDateTime)
	}
	log.Info("response", "resp", resp.String())
	c.sendAck(ctx, resp.String())
}

func (c *Consumer) sendAck(ctx context.Context, msg string) {
	if err := c.Ack.WriteMessages(ctx, kafka.Message{Value: []byte(msg)}); err != nil {
		c.Log.Warn("kafka ack write failed", "topic", c.Cfg.AckTopic, "err", err)
	}
}

func (c *Consumer) sendErr(ctx context.Context, msg string) {
	if err := c.Err.WriteMessages(ctx, kafka.Message{Value: []byte(msg)}); err != nil {
		c.Log.Warn("kafka err write failed", "topic", c.Cfg.ErrTopic, "err", err)
	}
}

// validate mirrors ValidationService.validateTxnRequest with kafkaFlag=true: it
// collects (rather than throws on) validation errors. The duplicate-RRN lookup is
// DB-backed; the original-transaction/refund matching and currency/country
// existence checks (ValidationService lines 71-103, 123-240) are NOT ported —
// they require additional repository lookups (parity gap, consistent with the
// HTTP path's simplified validation).
func (c *Consumer) validate(ctx context.Context, p *SwitchExtractVo) []string {
	var errors []string
	// Mirror SwitchExtractVo's @Length(max=20) on scheme and sub_route
	// (network). The Java port runs Bean Validation before validateTxnRequest,
	// so these run first and reject over-length values with the same messages
	// insertRejectedTxns persisted (e.g. "scheme:The maximum length is 20").
	if utf8.RuneCountInString(p.Scheme) > 20 {
		errors = append(errors, "scheme:The maximum length is 20")
	}
	if utf8.RuneCountInString(p.Network) > 20 {
		errors = append(errors, "network:The maximum length is 20")
	}
	if err := validate(p); err != nil {
		errors = append(errors, err.Error())
	}
	if dup, err := c.Svc.Store.FindByMsgTypeIdAndRrnAndProcCode(ctx, p.Mti, p.RetRefNumber, p.ProcessCode); err != nil {
		c.Log.Warn("duplicate rrn check failed", "rrn", p.RetRefNumber, "err", err)
	} else if dup {
		errors = append(errors, "DUPLICATE RRN ; RRN already Exist")
	}
	if p.Mti == "0130" && strings.TrimSpace(p.AuthAmount) == "" {
		errors = append(errors, "INVALID AUTH AMOUNT; AUTH AMOUNT is mandatory for MTI 0130")
	}
	if a := strings.TrimSpace(p.AuthAmount); a != "" && !isAllDigits(a) {
		errors = append(errors, "INVALID AUTH AMOUNT; AUTH AMOUNT must be numeric value")
	}
	if p.Dcc != nil && strings.EqualFold(p.Dcc.DccIndicator, "Y") {
		if strings.TrimSpace(p.Dcc.DccAmount) == "" || strings.TrimSpace(p.Dcc.DccCurrency) == "" || strings.TrimSpace(p.Dcc.ExchangeRate) == "" {
			errors = append(errors, "DCC Amount, DCC Currency, and Exchange Rate are mandatory when DCC Indicator is 'Y'.")
		}
	}
	if ParseAmount(p.AmountTransaction) == 0 {
		errors = append(errors, "INVALID AMOUNT ; AMOUNT Should be Greater than Zero")
	}
	if strings.EqualFold(p.Scheme, "MASTERCARD") {
		if p.De48JSON == nil {
			errors = append(errors, "INVALID INPUT ;  Missing Additional Private data")
		} else if m := p.De48JSON.MposAccDevType; m != "" && (len(m) > 1 || (m[0] != '0' && m[0] != '1')) {
			errors = append(errors, "INVALID INPUT ;  Invalid mposAccDevType . Allowed values are '0' and '1'.")
		}
	}
	if p.ServiceRestrictionCode == "" && !strings.EqualFold(p.TxnSource, "PG") {
		errors = append(errors, "INVALID INPUT ;  The Service Restriction Code cannot be null")
	}
	if p.AuthIdResponse == "" && p.ResponseCode == "00" {
		errors = append(errors, "INVALID INPUT ;  The Auth_code Code cannot be null")
	}
	if !strings.EqualFold(p.Network, "AMEX") && strings.TrimSpace(p.SettlementDate) == "" {
		errors = append(errors, "INVALID INPUT ;  The settlement_date cannot be blank /empty")
	}
	return errors
}

// insertRejected mirrors CommonManagementsService.insertRejectedTxns (best-effort:
// Java swallows every exception in that method). Business date comes from
// BUSINESS_DATE when present, else NULL.
func (c *Consumer) insertRejected(ctx context.Context, resp *ResponseVo, txnDateTime string) {
	r := &RejectedTxn{
		LastUpdated: time.Now(),
		User:        c.Svc.Cfg.UserSerNumber,
		InsCode:     c.Svc.Cfg.InsCode,
		IntCode:     c.Svc.Cfg.KafkaIntCode,
		JobNumber:   c.Cfg.JobNumber,
		FileName:    c.Cfg.FileName,
		Rrn:         resp.Rrn,
		Amount:      ParseAmount(resp.AmountTransaction),
		TxnDateTime: UpdateTxnDatetime(txnDateTime, time.Now()),
		Mid:         resp.CardAcceptorId,
		Tid:         resp.CardAcceptorTid,
		RejReason:   firstRejectReason(resp.ValidationErrors),
	}
	if bd, err := c.Svc.Store.FindBusinessDate(ctx, r.InsCode); err != nil {
		c.Log.Warn("find business date failed", "err", err)
	} else {
		r.BussDate = bd
	}
	if _, err := c.Svc.Store.InsertRejectedTxn(ctx, r); err != nil {
		c.Log.Warn("insert rejected txn failed", "rrn", r.Rrn, "err", err)
	}
}

// firstRejectReason mirrors insertRejectedTxns: first error's first line, trimmed
// and truncated to 180 chars (177 + "...").
func firstRejectReason(errors []string) string {
	raw := "Unknown error"
	if len(errors) > 0 {
		raw = errors[0]
	}
	first := raw
	if idx := strings.IndexByte(first, '\n'); idx >= 0 {
		first = first[:idx]
	}
	first = strings.TrimSpace(first)
	if len(first) > 180 {
		first = first[:177] + "..."
	}
	return first
}

func isAllDigits(s string) bool {
	for _, r := range s {
		if r < '0' || r > '9' {
			return false
		}
	}
	return true
}

func marshalRequest(vo *RequestVo) string {
	b, err := json.Marshal(vo)
	if err != nil {
		return fmt.Sprintf("%+v", vo)
	}
	return string(b)
}
