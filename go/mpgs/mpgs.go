// Package mpgs is the Go port of MPGS's IRF integration.
//
// After migration MPGS no longer holds calculators or an IRF_CALLBACK table; it
// keeps only this layer: an MpgsTxn DTO, an entity-to-IrfTxnData mapper, and an
// HTTP client against irf-service. Go equivalent of the deleted
// UAEMcIRFCalculation + IRFCallbackService and the new MpgsTxnMapper.
package mpgs

import (
	"time"

	"empay/irf/irf"
)

// Txn is the IRF-relevant subset of MPGSTxnWorkEntity.
type Txn struct {
	SerialNumber           int
	InsCode                int
	Network                string
	Mcc                    string
	TxnCode                string
	TxnId                  string
	ResponseCode           string
	ApprovalCode           string
	Rrn                    string
	PosEntryMode           string
	PosConditionCode       string
	ServiceCode            string
	CardSeqNumber          string
	TerminalType           string
	TxnAmount              float64
	SetlAmount             float64
	CashBackAmount         float64
	NetAmount              float64
	TxnCurCode             string
	SetlCurCode            string
	FeePgmIndicator        string
	ReImbursementAttribute string
	MotoEcomIndicator      string
	TerminalCapability     string
	TrlCapabilities        string
	Mvv                    string
	NetworkData            string
	Maid                   string
	MeCategoryType         string
	ChAuthAbility          string
	CardInputAbility       string
	CardCaptureAbility     string
	CardInputMode          string
	ChPresent              string
	CardPresent            string
	OprtEnvironment        string
	AcqInstConCode         string
	EncCardNumber          string
	CardNumber             string
	TxnDateTime            time.Time
	MsgTypeId              string
	TxnUniqueId            string
}

var amtPtr = func(v float64) *float64 { return &v }

// ToIrfData maps MPGS fields onto the shared DTO the service consumes. Mirrors
// the Java MpgsTxnMapper field-for-field (including firstChar narrowing for
// Character sources). MPGSTxnWorkEntity has no scheme getter, so Scheme stays
// unset, exactly like the Java mapper.
func (t Txn) ToIrfData() *irf.IrfTxnData {
	ins := t.InsCode
	sn := t.SerialNumber
	reimb := t.ReImbursementAttribute
	dt := irf.LocalDateTime(t.TxnDateTime)
	return &irf.IrfTxnData{
		SerialNumber:           &sn,
		InsCode:                &ins,
		Network:                t.Network,
		Mcc:                    t.Mcc,
		TxnCode:                t.TxnCode,
		TxnId:                  t.TxnId,
		ResponseCode:           t.ResponseCode,
		ApprovalCode:           t.ApprovalCode,
		Rrn:                    t.Rrn,
		PosEntryMode:           t.PosEntryMode,
		PosConditionCode:       t.PosConditionCode,
		ServiceCode:            t.ServiceCode,
		CardSeqNumber:          t.CardSeqNumber,
		TerminalType:           t.TerminalType,
		TxnAmount:              amtPtr(t.TxnAmount),
		SetlAmount:             amtPtr(t.SetlAmount),
		CashBackAmount:         amtPtr(t.CashBackAmount),
		NetAmount:              amtPtr(t.NetAmount),
		TxnCurCode:             t.TxnCurCode,
		SetlCurCode:            t.SetlCurCode,
		FeePgmIndicator:        t.FeePgmIndicator,
		ReImbursementAttribute: firstChar(reimb),
		MotoEcomIndicator:      t.MotoEcomIndicator,
		TerminalCapability:     t.TerminalCapability,
		TrlCapabilities:        firstChar(t.TrlCapabilities),
		Mvv:                    t.Mvv,
		NetworkData:            t.NetworkData,
		Maid:                   t.Maid,
		MeCategoryType:         firstChar(t.MeCategoryType),
		ChAuthAbility:          firstChar(t.ChAuthAbility),
		CardInputAbility:       firstChar(t.CardInputAbility),
		CardCaptureAbility:     firstChar(t.CardCaptureAbility),
		CardInputMode:          firstChar(t.CardInputMode),
		ChPresent:              firstChar(t.ChPresent),
		CardPresent:            firstChar(t.CardPresent),
		OprtEnvironment:        firstChar(t.OprtEnvironment),
		AcqInstConCode:         t.AcqInstConCode,
		TxnDateTime:            &dt,
		EncCardNumber:          t.EncCardNumber,
		CardNumber:             t.CardNumber,
		MsgTypeId:              t.MsgTypeId,
		TxnUniqueId:            t.TxnUniqueId,
	}
}

// firstChar mirrors the Java MpgsTxnMapper.firstChar: Java IrfTxnData carries
// trlCapabilities as a single Character, so a multi-char entity string ("00")
// becomes nil (Jackson rejects multi-char strings for Character fields).
func firstChar(s string) *string {
	if len(s) != 1 {
		return nil
	}
	c := s[:1]
	return &c
}

// IrfClient is the MPGS side's handle to irf-service.
type IrfClient struct{ *irf.Client }

// NewClient wires the MPGS client to an irf-service base URL + shared secret.
func NewClient(baseURL, secret string) IrfClient {
	return IrfClient{Client: irf.NewClient(baseURL, secret)}
}

// Calculate runs the IRF for one MPGS transaction over HTTP. cardNumber must be
// the real PAN (Java UAEMcIRFCalculation/VisaIrfCalculation BIN-lookup on
// posData.getCardNumber()), not the encrypted token.
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
	data := &irf.CallbackData{
		UniqueId:     t.TxnUniqueId,
		IrdCode:      vo.IrdCode,
		Fixed:        vo.IrfFixed,
		Percentage:   vo.IrfPercentage,
		IrfAmount:    vo.IrfAmount,
		TxnAmount:    &amt,
		Rrn:          t.Rrn,
		Mti:          t.MsgTypeId,
		DomIntlFlag:  vo.DomIntlFlag,
		InsCode:      &ins,
		RefSerNumber: &ref,
	}
	return c.Client.EnqueueCallback(data)
}
