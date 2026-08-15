package tlfsvc

import (
	"context"
	"database/sql"
	"errors"
	"fmt"
	"log/slog"
	"strconv"
	"strings"
	"time"

	"empay/irf/cryptapi"
	"empay/irf/irf"
	"empay/irf/tlf"
)

// ErrCryptAPIFailed is returned when the CryptAPI PAN decryption fails,
// mirroring TxnProcessingService.getCardNumber returning null. The Kafka
// consumer maps it to a rejection (m["status"] = "Crypt API Failed , No
// CardNumber found" + insertRejected).
var ErrCryptAPIFailed = errors.New("tlfsvc: crypt api failed")

// Service is the Go port of tlf-processing-service's online TLF flow
// (TxnProcessingService.processOnlineTxn, Kafka-driven).
// IrfCalc is the contract used by Service for the external irf-service. The
// production implementation is tlf.IrfClient; tests inject a stub.
type IrfCalc interface {
	Calculate(t tlf.Txn) (*irf.IrfResultVo, bool, error)
	EnqueueCallback(t tlf.Txn, vo *irf.IrfResultVo) (int, error)
}

type Service struct {
	Store     Store
	IrfClient IrfCalc
	Log       *slog.Logger
	Cfg       Config
	// CountryLookup optionally maps a 2-alpha country code to 3-alpha for the
	// Mastercard meCountryOfOrigin derivation (Java CountriesRepository).
	CountryLookup func(ctx context.Context, code string) (string, error)
	// Crypto optionally decrypts switch_crypt_token -> PAN via the external
	// CryptAPI (Java getCardNumber). When nil, the payload pan is used as-is
	// (UAT mode with masked pans).
	Crypto cryptapi.CardCrypto
}

// New wires the service to Oracle, the irf-service client and config.
func New(db *sql.DB, baseURL, secret string, log *slog.Logger, cfg Config) *Service {
	return &Service{
		Store:     &oracleStore{db: db},
		IrfClient: tlf.NewClient(baseURL, secret),
		Log:       log,
		Cfg:       cfg,
	}
}

// countryAlpha3 wraps the optional DB-backed 2->3 alpha country lookup used by
// the MASTERCARD meCountry derivation (Java CountriesRepository.findByCountryCode).
func (s *Service) countryAlpha3(code string) string {
	if s.CountryLookup == nil || code == "" {
		return code
	}
	a3, err := s.CountryLookup(context.Background(), code)
	if err != nil || a3 == "" {
		return code
	}
	return a3
}

// workItem carries everything stage 2 needs to finalize one transaction after
// stage 1 has inserted the POS_TRANSACTIONS row: the mapped entity, the raw
// payload (merchant/terminal data + token) and the resolved (decrypted) PAN.
type workItem struct {
	ser        int
	overlay    *Entity
	payload    *SwitchExtractVo
	cardNumber string
}

// Stage1 is the fast, non-blocking ingest half of the online TLF flow
// (Kafka-driven). It validates the payload, resolves the PAN (CryptAPI, same
// rules as Java getCardNumber), maps and INSERTs the POS_TRANSACTIONS row and
// returns a workItem for stage 2. Every ingested row is inserted at
// PTR_GEN_STATUS=9 (in-flight) so the settlement split never reads it before
// the IRF/outgoing columns are written; the worker pool finalizes the row and
// flips the status to 3 (§7.12 of IRF_SERVICE_HANDOVER.md).
func (s *Service) Stage1(ctx context.Context, vo *RequestVo) (map[string]string, *workItem, error) {
	resp := map[string]string{}
	if vo == nil || vo.Payload == nil {
		return resp, nil, fmt.Errorf("tlfsvc: empty request body; expected {payload:{...}}")
	}
	p := vo.Payload
	if err := validate(p); err != nil {
		return resp, nil, err
	}

	// Resolve the PAN: Java TxnProcessingService.getCardNumber(tokenIdentifier)
	// decrypts switch_crypt_token via CryptAPI and fails with 404 when no
	// cardNumber comes back. When no CryptAPI is configured (UAT mode) the
	// payload pan is used as-is.
	cardNumber := p.CardNumber
	if s.Crypto != nil {
		if p.TokenIdentifier == "" {
			resp["status"] = "Crypt API Failed , No CardNumber found"
			return resp, nil, ErrCryptAPIFailed
		}
		dec := s.Crypto.GetCardNumber([]string{p.TokenIdentifier})
		if dec == nil || dec[p.TokenIdentifier] == "" {
			resp["status"] = "Crypt API Failed , No CardNumber found"
			return resp, nil, ErrCryptAPIFailed
		}
		cardNumber = dec[p.TokenIdentifier]
	}

	now := s.now()
	if s.Cfg.Now == nil {
		s.Cfg.Now = now
	}

	cfg := s.Cfg
	cfg.IntCode = s.Cfg.KafkaIntCode
	e := MapToEntity(p, cfg, s.countryAlpha3)

	// Completion marker: ingest at 9 (in-flight, invisible to the settlement
	// split); stage 2 flips to 3 after writing the IRF/outgoing columns.
	e.Entity.GenStatus = 9

	ser, err := s.Store.Insert(ctx, e)
	if err != nil {
		resp["rrn"] = p.RetRefNumber
		resp["pan"] = maskPan(p.CardNumber)
		return resp, nil, err
	}
	resp["rrn"] = p.RetRefNumber
	resp["pan"] = maskPan(p.CardNumber)
	return resp, &workItem{ser: ser, overlay: e, payload: p, cardNumber: cardNumber}, nil
}

// Stage2 is the IRF/finalize half of the online TLF flow, run by the worker
// pool: irf-service calculate (when the txn needs IRF), write the IRF columns,
// set the outgoing/incoming status, optionally stage MERCURY txns and enqueue
// the scheme callback, then flip PTR_GEN_STATUS 9 -> 3 so the row becomes
// visible to the settlement split. A calculate failure is non-fatal (cleared
// IRF shape), mirroring the pre-decoupling behavior; only store failures abort
// and leave the row in-flight for the reaper.
func (s *Service) Stage2(ctx context.Context, w *workItem) error {
	if w == nil || w.overlay == nil {
		return fmt.Errorf("tlfsvc: nil work item")
	}
	overlay := w.overlay
	p := w.payload
	ser := w.ser

	dms := DmsSmsMode(p.SmsDmsIndicator)
	rev := RevIndicator(p.Mti, 0)
	net := NetworkMapping(p.SmsDmsIndicator, p.Network)
	scheme := SchemeMapping(p.Scheme)

	irf := IrfStatusCheck(p.ResponseCode, p.ProcessCode, dms, rev)
	cleared := !irf
	if irf {
		voRes, _, err := s.IrfClient.Calculate(overlay.ToTxn(p, w.cardNumber))
		if err != nil {
			s.Log.Warn("irf calculate failed", "ser", ser, "err", err)
		}
		if voRes == nil {
			overlay.ApplyResult(nil, true)
		} else {
			overlay.ApplyResult(voRes, false)
		}
	} else {
		overlay.ApplyResult(nil, true)
	}
	if err := s.Store.UpdateIrf(ctx, overlay, cleared); err != nil {
		return err
	}

	outgoing := OutgoingStatusCheck(p.ResponseCode, p.ProcessCode, dms, rev, scheme, net)
	if !outgoing {
		if err := s.Store.SetOutgoing(ctx, ser, "NA", "NA"); err != nil {
			return err
		}
	}

	// Mercury staging: when enabled, stage outgoing-eligible MERCURY-network
	// transactions into MERCURY_ACQ_TXN_WORK (Java MercurySplitService is a
	// standalone @Service with no in-app call-site; wiring is opt-in).
	if s.Cfg.StageMercury && net == "MERCURY" && outgoing {
		if ms, err := s.StageMercury(ctx, overlay, p); err != nil {
			s.Log.Warn("mercury staging failed", "ser", ser, "err", err)
		} else if ms != 0 {
			s.Log.Info("mercury staged", "ser", ser, "matSer", ms)
		}
	}

	if irf && overlay.Ird != "" {
		if cb, err := s.IrfClient.EnqueueCallback(overlay.ToTxn(p, w.cardNumber), nil); err != nil {
			s.Log.Warn("enqueue callback failed", "ser", ser, "err", err)
		} else if cb != 0 {
			s.Log.Info("callback enqueued", "ser", ser, "cb", cb)
		}
	}

	// Last write: make the row visible to the settlement split.
	return s.Store.SetReady(ctx, ser)
}

func (s *Service) now() func() time.Time {
	if s.Cfg.Now != nil {
		return s.Cfg.Now
	}
	return time.Now
}

// StageMercury mirrors MercurySplitService.mapToMercuryAcqTxnEntity end-to-end:
// resolves the MERCURY interface code, the 'E' acquirer bin and the txn
// currency exponent, maps the processed POS entity onto a MERCURY_ACQ_TXN_WORK
// row and inserts it. It is safe to call after a transaction has been processed;
// errors are returned to the caller (which decides whether to fail the response).
func (s *Service) StageMercury(ctx context.Context, e *Entity, p *SwitchExtractVo) (int, error) {
	intCode, err := s.Store.FindInterfaceCode(ctx, "MERCURY", s.Cfg.InsCode)
	if err != nil {
		return 0, err
	}
	if intCode == 0 {
		return 0, fmt.Errorf("tlfsvc: MERCURY interface not configured for ins %d", s.Cfg.InsCode)
	}

	var ica, bin string
	if ab, ok, err := s.Store.FindAcquirerBin(ctx, s.Cfg.InsCode, "E"); err != nil {
		return 0, err
	} else if ok {
		ica = ab.McIcaNum
		bin = ab.AcqBin
	}

	exp := 0
	if e.TxnCurCode != "" {
		if v, ok, err := s.Store.FindCurrencyExponent(ctx, e.TxnCurCode); err != nil {
			return 0, err
		} else if ok {
			exp = v
		}
	}

	m := MapToMercuryAcqTxnEntity(MercurySplitParams{
		Entity:           e,
		Payload:          p,
		UserSerialNumber: s.Cfg.UserSerNumber,
		InsCode:          s.Cfg.InsCode,
		IntCode:          intCode,
		JobNumber:        s.Cfg.JobNumber,
		CurrencyExponent: exp,
		McIcaNum:         ica,
		AcqBin:           bin,
		Now:              s.now()(),
	})
	return s.Store.InsertMercuryWork(ctx, m)
}

func validate(p *SwitchExtractVo) error {
	if p.CardNumber == "" {
		return fmt.Errorf("tlfsvc: pan is required")
	}
	if p.Mti == "" {
		return fmt.Errorf("tlfsvc: switch_mti is required")
	}
	if p.ProcessCode == "" {
		return fmt.Errorf("tlfsvc: processing_code is required")
	}
	if p.RetRefNumber == "" {
		return fmt.Errorf("tlfsvc: rrn is required")
	}
	if p.ResponseCode == "" {
		return fmt.Errorf("tlfsvc: network_response_code is required")
	}
	if p.Mcc == "" {
		return fmt.Errorf("tlfsvc: mcc is required")
	}
	if p.TerminalId == "" {
		return fmt.Errorf("tlfsvc: terminal_id is required")
	}
	if p.MerchantId == "" {
		return fmt.Errorf("tlfsvc: merchant_id is required")
	}
	if p.Network == "" {
		return fmt.Errorf("tlfsvc: sub_route is required")
	}
	if p.Scheme == "" {
		return fmt.Errorf("tlfsvc: scheme is required")
	}
	if p.SmsDmsIndicator == "" {
		return fmt.Errorf("tlfsvc: sms_dms_indicator is required")
	}
	return nil
}

func maskPan(pan string) string {
	if len(pan) <= 4 {
		return strings.Repeat("*", len(pan))
	}
	return strings.Repeat("*", len(pan)-4) + pan[len(pan)-4:]
}

func nullOr(s string) string {
	if s == "" {
		return ""
	}
	return s
}

func floatOrEmpty(f float64) string {
	if f == 0 {
		return ""
	}
	return strconv.FormatFloat(f, 'f', -1, 64)
}
