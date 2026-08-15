// Package tlf is the Go port of TLF's IRF integration.
//
// After migration TLF no longer holds calculators or an IRF_CALLBACK table; it
// keeps only this thin layer: a Txn DTO, an entity→IrfTxnData mapper, and an
// HTTP client that talks to irf-service. This file is the Go equivalent of the
// deleted `VisaIrfCalculation` / `IRFCallbackService` + the new `TlfTxnMapper`
// + `HttpIrfCalculator` wiring described in the handover.
package tlf

import (
	"time"

	"empay/irf/irf"
)

// Txn is the IRF-relevant subset of PosTransactionEntity.
type Txn struct {
	SerialNumber            int
	InsCode                 int
	Network                 string
	Scheme                  string
	Mcc                     string
	TxnCode                 string
	TxnId                   string
	ResponseCode            string
	ApprovalCode            string
	Rrn                     string
	PosEntryMode            string
	PosConditionCode        string
	ServiceCode             string
	CardSeqNumber           string
	TerminalType            string
	TxnAmount               float64
	SetlAmount              float64
	CashBackAmount          float64
	NetAmount               float64
	TxnCurCode              string
	SetlCurCode             string
	FeePgmIndicator         string
	ReImbursementAttribute  string // "B"/"D"/...  (Java Character -> string)
	MotoEcomIndicator       string
	TerminalCapability      string
	TrlCapabilities         string
	Mvv                     string
	NetworkData             string
	Maid                    string
	MeCategoryType          string
	ChAuthAbility           string
	CardInputAbility        string
	CardCaptureAbility      string
	CardInputMode           string
	ChPresent               string
	CardPresent             string
	OprtEnvironment         string
	EncCardNumber           string
	CardNumber              string
	TxnDateTime             time.Time
	AcqInstConCode          string
	CardAcceptorCountryCode string
	MsgTypeId               string
	TxnUniqueId             string
}

// ToIrfData maps TLF fields onto the shared DTO the service consumes. Mirrors
// the Java TlfTxnMapper field-for-field: multi-char Character sources
// (trlCapabilities, abilities, oprtEnvironment, …) are narrowed to a single
// char via firstChar, exactly like the Java mapper's helper.
func (t Txn) ToIrfData() *irf.IrfTxnData {
	ins := t.InsCode
	sn := t.SerialNumber
	net := t.Network
	amt := t.TxnAmount
	setl := t.SetlAmount
	cash := t.CashBackAmount
	nett := t.NetAmount
	dt := irf.LocalDateTime(t.TxnDateTime)
	reimb := t.ReImbursementAttribute
	amtPtr := func(v float64) *float64 { return &v }
	return &irf.IrfTxnData{
		SerialNumber:            &sn,
		InsCode:                 &ins,
		Network:                 net,
		Scheme:                  t.Scheme,
		Mcc:                     t.Mcc,
		TxnCode:                 t.TxnCode,
		TxnId:                   t.TxnId,
		ResponseCode:            t.ResponseCode,
		ApprovalCode:            t.ApprovalCode,
		Rrn:                     t.Rrn,
		PosEntryMode:            t.PosEntryMode,
		PosConditionCode:        t.PosConditionCode,
		ServiceCode:             t.ServiceCode,
		CardSeqNumber:           t.CardSeqNumber,
		TerminalType:            t.TerminalType,
		TxnAmount:               amtPtr(amt),
		SetlAmount:              amtPtr(setl),
		CashBackAmount:          amtPtr(cash),
		NetAmount:               amtPtr(nett),
		TxnCurCode:              t.TxnCurCode,
		SetlCurCode:             t.SetlCurCode,
		FeePgmIndicator:         t.FeePgmIndicator,
		ReImbursementAttribute:  firstChar(reimb),
		MotoEcomIndicator:       t.MotoEcomIndicator,
		TerminalCapability:      t.TerminalCapability,
		TrlCapabilities:         firstChar(t.TrlCapabilities),
		Mvv:                     t.Mvv,
		NetworkData:             t.NetworkData,
		Maid:                    t.Maid,
		MeCategoryType:          firstChar(t.MeCategoryType),
		ChAuthAbility:           firstChar(t.ChAuthAbility),
		CardInputAbility:        firstChar(t.CardInputAbility),
		CardCaptureAbility:      firstChar(t.CardCaptureAbility),
		CardInputMode:           firstChar(t.CardInputMode),
		ChPresent:               firstChar(t.ChPresent),
		CardPresent:             firstChar(t.CardPresent),
		OprtEnvironment:         firstChar(t.OprtEnvironment),
		TxnDateTime:             &dt,
		EncCardNumber:           t.EncCardNumber,
		CardNumber:              t.CardNumber,
		AcqInstConCode:          t.AcqInstConCode,
		CardAcceptorCountryCode: t.CardAcceptorCountryCode,
		MsgTypeId:               t.MsgTypeId,
		TxnUniqueId:             t.TxnUniqueId,
	}
}

// IrfClient is the TLF side's handle to irf-service.
type IrfClient struct{ *irf.Client }

// NewClient wires the TLF client to an irf-service base URL + shared secret.
func NewClient(baseURL, secret string) IrfClient {
	return IrfClient{Client: irf.NewClient(baseURL, secret)}
}

// Calculate runs the IRF for one TLF transaction over HTTP. cardNumber must be
// the real PAN (Java passes the decrypted getCardNumber(tokenIdentifier) into
// fetchIrf, and the service calculators do real-PAN BIN lookups on it), not the
// encrypted token.
func (c IrfClient) Calculate(t Txn) (*irf.IrfResultVo, bool, error) {
	return c.Client.Calculate(t.InsCode, t.ToIrfData(), t.Network, t.CardNumber)
}

// EnqueueCallback persists the callback server-side (irf-service owns IRF_CALLBACK).
func (c IrfClient) EnqueueCallback(t Txn, vo *irf.IrfResultVo) (int, error) {
	if vo == nil {
		return 0, nil
	}
	ins := t.InsCode
	ref := t.SerialNumber
	amt := t.TxnAmount
	fixed := vo.IrfFixed
	pct := vo.IrfPercentage
	irfAmt := vo.IrfAmount
	credit := creditBool(vo.CardType)
	data := &irf.CallbackData{
		CpMid:        "",
		UniqueId:     t.TxnUniqueId,
		IrdCode:      vo.IrdCode,
		Fixed:        fixed,
		Percentage:   pct,
		IrfAmount:    irfAmt,
		TxnAmount:    &amt,
		Rrn:          t.Rrn,
		Mti:          t.MsgTypeId,
		DomIntlFlag:  vo.DomIntlFlag,
		IsCredit:     credit,
		InsCode:      &ins,
		RefSerNumber: &ref,
	}
	return c.Client.EnqueueCallback(data)
}

func creditBool(cardType string) *bool {
	if cardType == "" {
		return nil
	}
	b := cardType == "C" || cardType == "P"
	return &b
}

// firstChar mirrors the Java TlfTxnMapper.firstChar: Java IrfTxnData carries
// trlCapabilities as a single Character, so a multi-char entity string ("00")
// becomes nil (Jackson rejects multi-char strings for Character fields).
func firstChar(s string) *string {
	if len(s) != 1 {
		return nil
	}
	c := s[:1]
	return &c
}
