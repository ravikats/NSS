// Package mpgssvc is the runnable Go MPGS IRF processor. It mirrors the Java
// MPGS IrfCalculationService.calculateIrf(jobNumber, insCode): every
// POS_TRANSACTIONS row for a job (PTR_PRJ_SER_NUMBER) is mapped onto the shared
// IrfTxnData via the mpgs mapper, sent to irf-service, and the IRF result
// columns are written back to the row. The scheme callback is enqueued
// server-side (irf-service owns IRF_CALLBACK).
package mpgssvc

import (
	"context"
	"database/sql"
	"fmt"
	"log/slog"
	"time"

	"empay/irf/irf"
	"empay/irf/mpgs"

	_ "github.com/sijms/go-ora/v2"
)

// selectSQL reads the exact field set the mpgs.Txn mapper consumes. Column
// names come from the Java MPGSTxnWorkEntity mapping (PTR_* on POS_TRANSACTIONS).
const selectSQL = `SELECT
  PTR_SER_NUMBER, PTR_INS_CODE, PTR_NETWORK, PTR_MCC, PTR_TXN_CODE,
  PTR_RESP_CODE, PTR_APPR_CODE, PTR_RET_REF_NUMBER, PTR_POS_ENTRY_MODE,
  PTR_POS_CONDITION_CODE, PTR_SERVICE_CODE, PTR_CARD_SEQ_NUMBER, PTR_TRL_TYPE,
  PTR_TXN_AMOUNT, PTR_SETL_AMOUNT, PTR_CASHBACK_AMOUNT, PTR_MER_NET_AMOUNT,
  PTR_TXN_CUR_CODE, PTR_SETL_CUR_CODE, PTR_FEE_PRG_INDICATOR,
  PTR_MOTO_ECOM_INDICATOR, PTR_MVV, PTR_NETWORK_DATA, PTR_MAID, PTR_TXN_ID,
  PTR_ME_CATEGORY_TYPE, PTR_CH_AUTH_ABILITY, PTR_CARD_INPUT_ABILITY,
  PTR_CARD_CAPTURE_ABILITY, PTR_CARD_INPUT_MODE, PTR_CH_PRESENT,
  PTR_CARD_PRESENT, PTR_TXN_DATE_TIME, PTR_MSG_TYPE_ID, PTR_TXN_UNIQUE_ID,
  PTR_CARD_NUMBER, PTR_ENC_CARD_NUMBER, PTR_ACQ_INST_CON_CODE,
  PTR_TRL_CAPABILITIES, PTR_OPRT_ENVIRONMENT
FROM POS_TRANSACTIONS
WHERE PTR_PRJ_SER_NUMBER = :1`

// updateSQL writes the IRF result columns back (mirrors populateIrfData /
// clearIrfData in the Java IrfCalculationService).
const updateSQL = `UPDATE POS_TRANSACTIONS SET
  PTR_IRD_SER_NUMBER = :1, PTR_IRD = :2, PTR_IRF_FIXED = :3, PTR_IRF_PERCENT = :4,
  PTR_IRF_AMOUNT = :5, PTR_IRF_AMOUNT_USD = :6, PTR_CARD_TYPE = :7,
  PTR_CARD_DOM_INTL_FLAG = :8, PTR_CARD_CATEGORY = :9, PTR_REMARKS = :10,
  PTR_IRF_MIN_AMOUNT = :11, PTR_IRF_MAX_AMOUNT = :12, PTR_LAST_UPDATED = SYSDATE
WHERE PTR_SER_NUMBER = :13`

// row mirrors the nullable columns of POS_TRANSACTIONS that feed the mapper.
type row struct {
	SerNumber          sql.NullInt64
	InsCode            sql.NullInt64
	Network            sql.NullString
	Mcc                sql.NullString
	TxnCode            sql.NullString
	TxnId              sql.NullString
	ResponseCode       sql.NullString
	ApprovalCode       sql.NullString
	Rrn                sql.NullString
	PosEntryMode       sql.NullString
	PosConditionCode   sql.NullString
	ServiceCode        sql.NullString
	CardSeqNumber      sql.NullString
	TerminalType       sql.NullString
	TxnAmount          sql.NullFloat64
	SetlAmount         sql.NullFloat64
	CashBackAmount     sql.NullFloat64
	NetAmount          sql.NullFloat64
	TxnCurCode         sql.NullString
	SetlCurCode        sql.NullString
	FeePgmIndicator    sql.NullString
	MotoEcomIndicator  sql.NullString
	Mvv                sql.NullString
	NetworkData        sql.NullString
	Maid               sql.NullString
	MeCategoryType     sql.NullString
	ChAuthAbility      sql.NullString
	CardInputAbility   sql.NullString
	CardCaptureAbility sql.NullString
	CardInputMode      sql.NullString
	ChPresent          sql.NullString
	CardPresent        sql.NullString
	TxnDateTime        sql.NullTime
	MsgTypeId          sql.NullString
	TxnUniqueId        sql.NullString
	CardNumber         sql.NullString
	EncCardNumber      sql.NullString
	AcqInstConCode     sql.NullString
	TrlCapabilities    sql.NullString
	OprtEnvironment    sql.NullString
}

// txn maps a DB row onto the mpgs.Txn DTO the mapper consumes.
func (r row) txn() mpgs.Txn {
	var txnDateTime time.Time
	if r.TxnDateTime.Valid {
		txnDateTime = r.TxnDateTime.Time
	}
	return mpgs.Txn{
		SerialNumber:       int(r.SerNumber.Int64),
		InsCode:            int(r.InsCode.Int64),
		Network:            r.Network.String,
		Mcc:                r.Mcc.String,
		TxnCode:            r.TxnCode.String,
		TxnId:              r.TxnId.String,
		ResponseCode:       r.ResponseCode.String,
		ApprovalCode:       r.ApprovalCode.String,
		Rrn:                r.Rrn.String,
		PosEntryMode:       r.PosEntryMode.String,
		PosConditionCode:   r.PosConditionCode.String,
		ServiceCode:        r.ServiceCode.String,
		CardSeqNumber:      r.CardSeqNumber.String,
		TerminalType:       r.TerminalType.String,
		TxnAmount:          r.TxnAmount.Float64,
		SetlAmount:         r.SetlAmount.Float64,
		CashBackAmount:     r.CashBackAmount.Float64,
		NetAmount:          r.NetAmount.Float64,
		TxnCurCode:         r.TxnCurCode.String,
		SetlCurCode:        r.SetlCurCode.String,
		FeePgmIndicator:    r.FeePgmIndicator.String,
		MotoEcomIndicator:  r.MotoEcomIndicator.String,
		Mvv:                r.Mvv.String,
		NetworkData:        r.NetworkData.String,
		Maid:               r.Maid.String,
		MeCategoryType:     r.MeCategoryType.String,
		ChAuthAbility:      r.ChAuthAbility.String,
		CardInputAbility:   r.CardInputAbility.String,
		CardCaptureAbility: r.CardCaptureAbility.String,
		CardInputMode:      r.CardInputMode.String,
		ChPresent:          r.ChPresent.String,
		CardPresent:        r.CardPresent.String,
		OprtEnvironment:    r.OprtEnvironment.String,
		AcqInstConCode:     r.AcqInstConCode.String,
		EncCardNumber:      r.EncCardNumber.String,
		CardNumber:         r.CardNumber.String,
		TxnDateTime:        txnDateTime,
		MsgTypeId:          r.MsgTypeId.String,
		TxnUniqueId:        r.TxnUniqueId.String,
		TrlCapabilities:    r.TrlCapabilities.String,
	}
}

// Summary is the result of one job run.
type Summary struct {
	JobNumber int      `json:"jobNumber"`
	Rows      int      `json:"rows"`
	Updated   int      `json:"updated"`
	Callbacks int      `json:"callbacks"`
	Errors    []string `json:"errors,omitempty"`
}

// Service processes MPGS jobs against irf-service.
type Service struct {
	DB     *sql.DB
	Client mpgs.IrfClient
	Log    *slog.Logger
}

// New wires the service to an Oracle pool and the irf-service client.
func New(db *sql.DB, baseURL, secret string, log *slog.Logger) *Service {
	return &Service{DB: db, Client: mpgs.NewClient(baseURL, secret), Log: log}
}

// ProcessJob runs IRF for every POS_TRANSACTIONS row of a job.
func (s *Service) ProcessJob(ctx context.Context, jobNumber, insCode int) (Summary, error) {
	sum := Summary{JobNumber: jobNumber}
	rows, err := s.DB.QueryContext(ctx, selectSQL, jobNumber)
	if err != nil {
		return sum, fmt.Errorf("mpgssvc: query job %d: %w", jobNumber, err)
	}
	defer rows.Close()

	for rows.Next() {
		sum.Rows++
		var r row
		if err := rows.Scan(
			&r.SerNumber, &r.InsCode, &r.Network, &r.Mcc, &r.TxnCode,
			&r.ResponseCode, &r.ApprovalCode, &r.Rrn, &r.PosEntryMode,
			&r.PosConditionCode, &r.ServiceCode, &r.CardSeqNumber, &r.TerminalType,
			&r.TxnAmount, &r.SetlAmount, &r.CashBackAmount, &r.NetAmount,
			&r.TxnCurCode, &r.SetlCurCode, &r.FeePgmIndicator,
			&r.MotoEcomIndicator, &r.Mvv, &r.NetworkData, &r.Maid, &r.TxnId,
			&r.MeCategoryType, &r.ChAuthAbility, &r.CardInputAbility,
			&r.CardCaptureAbility, &r.CardInputMode, &r.ChPresent,
			&r.CardPresent, &r.TxnDateTime, &r.MsgTypeId, &r.TxnUniqueId,
			&r.CardNumber, &r.EncCardNumber, &r.AcqInstConCode,
			&r.TrlCapabilities, &r.OprtEnvironment,
		); err != nil {
			sum.Errors = append(sum.Errors, fmt.Sprintf("scan row: %v", err))
			continue
		}

		t := r.txn()
		if t.Network == "AMEX" {
			continue // Java skips AMEX rows
		}
		vo, _, err := s.Client.Calculate(t)
		if err != nil {
			sum.Errors = append(sum.Errors, fmt.Sprintf("ser %d: calculate: %v", t.SerialNumber, err))
			continue
		}

		args := updateArgs(t.SerialNumber, vo)
		if _, err := s.DB.ExecContext(ctx, updateSQL, args...); err != nil {
			sum.Errors = append(sum.Errors, fmt.Sprintf("ser %d: update: %v", t.SerialNumber, err))
			continue
		}
		sum.Updated++

		if vo != nil {
			serial, err := s.Client.EnqueueCallback(t, vo)
			if err != nil {
				sum.Errors = append(sum.Errors, fmt.Sprintf("ser %d: enqueue callback: %v", t.SerialNumber, err))
				continue
			}
			sum.Callbacks++
			s.Log.Info("processed", "ser", t.SerialNumber, "network", t.Network,
				"ird", vo.IrdCode, "callbackSerial", serial)
		} else {
			s.Log.Info("no irf", "ser", t.SerialNumber, "network", t.Network)
		}
	}
	if err := rows.Err(); err != nil {
		return sum, fmt.Errorf("mpgssvc: iterate job %d: %w", jobNumber, err)
	}
	return sum, nil
}

// updateArgs builds the 13 UPDATE bind values. A nil vo mirrors the Java
// clearIrfData (zeroed/blanked IRF columns); a non-nil vo mirrors populateIrfData.
func updateArgs(serialNumber int, vo *irf.IrfResultVo) []any {
	// clearIrfData: irdSerNumber/ird null, amounts 0, domIntlFlag blank.
	if vo == nil {
		return []any{
			sql.NullInt64{}, sql.NullString{}, 0.0, 0.0, 0.0, 0.0,
			sql.NullString{}, " ", sql.NullString{}, sql.NullString{}, 0.0, 0.0, serialNumber,
		}
	}
	var irdSerNumber any = sql.NullInt64{}
	if vo.IrdSerNumber != nil {
		irdSerNumber = *vo.IrdSerNumber
	}
	var fixed, percent, amount, usd any = 0.0, 0.0, 0.0, 0.0
	if vo.IrfFixed != nil {
		fixed = *vo.IrfFixed
	}
	if vo.IrfPercentage != nil {
		percent = *vo.IrfPercentage
	}
	if vo.IrfAmount != nil {
		amount = *vo.IrfAmount
	}
	if vo.IrfAmountUSD != nil {
		usd = *vo.IrfAmountUSD
	}
	var cardType, domIntlFlag, gcmsProductID, irfDesc any
	cardType = sql.NullString{}
	domIntlFlag = sql.NullString{}
	gcmsProductID = sql.NullString{}
	irfDesc = sql.NullString{}
	if vo.CardType != "" {
		cardType = vo.CardType
	}
	if vo.DomIntlFlag != "" {
		domIntlFlag = vo.DomIntlFlag
	}
	if vo.GcmsProductID != "" {
		gcmsProductID = vo.GcmsProductID
	}
	if vo.IrfDesc != "" {
		irfDesc = vo.IrfDesc
	}
	// Java sets min/max from the VO then forces both to 0.0.
	return []any{
		irdSerNumber, vo.IrdCode, fixed, percent, amount, usd,
		cardType, domIntlFlag, gcmsProductID, irfDesc,
		0.0, 0.0, serialNumber,
	}
}
