package outsvc

import (
	"context"
	"database/sql"
	"time"
)

// ---- UP_ACQ_TXN_WORK / UP_ACQ_TXN_DATA ----

func (s *oracleStore) CountUnionPayWorkBetween(ctx context.Context, ins, status int, from, to time.Time) (int, error) {
	var n int
	err := s.db.QueryRowContext(ctx, `
		SELECT COUNT(*) FROM UP_ACQ_TXN_WORK
		WHERE UPT_INS_CODE = :1 AND UPT_GEN_STATUS = :2 AND UPT_LOCAL_DATE_TIME BETWEEN :3 AND :4`,
		ins, status, from, to).Scan(&n)
	return n, err
}

func (s *oracleStore) CountUnionPayWorkLessThanEqual(ctx context.Context, ins, status int, to time.Time) (int, error) {
	var n int
	err := s.db.QueryRowContext(ctx, `
		SELECT COUNT(*) FROM UP_ACQ_TXN_WORK
		WHERE UPT_INS_CODE = :1 AND UPT_GEN_STATUS = :2 AND UPT_LOCAL_DATE_TIME <= :3`,
		ins, status, to).Scan(&n)
	return n, err
}

func (s *oracleStore) FindUnionPayWorkBetween(ctx context.Context, ins, intCode, status int, from, to time.Time) ([]*UnionPayAcqTxnWorkEntity, error) {
	rows, err := s.db.QueryContext(ctx, `
		SELECT * FROM UP_ACQ_TXN_WORK
		WHERE UPT_INS_CODE = :1 AND UPT_INT_CODE = :2 AND UPT_GEN_STATUS = :3
		  AND UPT_LOCAL_DATE_TIME BETWEEN :4 AND :5`,
		ins, intCode, status, from, to)
	if err != nil {
		return nil, err
	}
	defer rows.Close()
	return bindUnionPayWork(rows)
}

func (s *oracleStore) FindUnionPayWorkLessThanEqual(ctx context.Context, ins, intCode, status int, to time.Time) ([]*UnionPayAcqTxnWorkEntity, error) {
	rows, err := s.db.QueryContext(ctx, `
		SELECT * FROM UP_ACQ_TXN_WORK
		WHERE UPT_INS_CODE = :1 AND UPT_INT_CODE = :2 AND UPT_GEN_STATUS = :3
		  AND UPT_LOCAL_DATE_TIME <= :4`,
		ins, intCode, status, to)
	if err != nil {
		return nil, err
	}
	defer rows.Close()
	return bindUnionPayWork(rows)
}

func (s *oracleStore) FindUnionPayWorkByStatus(ctx context.Context, ins, status int) ([]*UnionPayAcqTxnWorkEntity, error) {
	rows, err := s.db.QueryContext(ctx, `
		SELECT * FROM UP_ACQ_TXN_WORK
		WHERE UPT_INS_CODE = :1 AND UPT_GEN_STATUS = :2`,
		ins, status)
	if err != nil {
		return nil, err
	}
	defer rows.Close()
	return bindUnionPayWork(rows)
}

func bindUnionPayWork(rows *sql.Rows) ([]*UnionPayAcqTxnWorkEntity, error) {
	maps, err := rowsToMaps(rows)
	if err != nil {
		return nil, err
	}
	out := make([]*UnionPayAcqTxnWorkEntity, 0, len(maps))
	for _, m := range maps {
		e := &UnionPayAcqTxnWorkEntity{}
		bindRow(m, e)
		out = append(out, e)
	}
	return out, nil
}

// UpdateUnionPayWorkStatuses mirrors the other networks' work status update:
// lastUpdated, updatedUser, generalStatus and fileID.
func (s *oracleStore) UpdateUnionPayWorkStatuses(ctx context.Context, ents []*UnionPayAcqTxnWorkEntity) error {
	for _, e := range ents {
		if _, err := s.db.ExecContext(ctx, `
			UPDATE UP_ACQ_TXN_WORK SET
			  UPT_LAST_UPDATED = :1,
			  UPT_UPDATED_USER = :2,
			  UPT_GEN_STATUS = :3,
			  UPT_FILE_ID = :4
			WHERE UPT_SER_NUMBER = :5`,
			e.LastUpdated, e.UpdatedUser, e.GenStatus, nullStr(e.FileID), e.SerialNumber); err != nil {
			return err
		}
	}
	return nil
}

func (s *oracleStore) DeleteUnionPayWork(ctx context.Context, ents []*UnionPayAcqTxnWorkEntity) error {
	for _, e := range ents {
		if _, err := s.db.ExecContext(ctx, `DELETE FROM UP_ACQ_TXN_WORK WHERE UPT_SER_NUMBER = :1`, e.SerialNumber); err != nil {
			return err
		}
	}
	return nil
}

const unionPayColumns = `
	UPT_SER_NUMBER, UPT_LAST_UPDATED, UPT_UPDATED_USER, UPT_INS_CODE, UPT_INT_CODE,
	UPT_PRJ_SER_NUMBER, UPT_GEN_STATUS, UPT_TXN_REF_NUMBER, UPT_RET_REF_NUMBER,
	UPT_MERCHANT_ID, UPT_TERMINAL_ID, UPT_TXN_TYPE, UPT_CARD_NUMBER, UPT_TXN_AMOUNT,
	UPT_SCHG_AMOUNT, UPT_LOCAL_DATE_TIME, UPT_TXN_DATE, UPT_ME_NAME, UPT_ME_CITY,
	UPT_ME_COUNTRY, UPT_MCC, UPT_APPR_CODE, UPT_TXN_CUR_CODE, UPT_STAN,
	UPT_ORG_INST_ID_CODE, UPT_ACQ_INST_ID_CODE, UPT_FWD_INST_ID_CODE, UPT_ACQ_REF_DATA,
	UPT_RESP_CODE, UPT_RECV_INST_ID_CODE, UPT_POS_CONDITION_CODE, UPT_TXN_INIT_CHANNEL,
	UPT_PRICING_SCHEME_CODE, UPT_ENC_CARD_NUMBER, UPT_CARD_INPUT_MODE,
	UPT_CARD_INPUT_CAPABILITY, UPT_CARD_SEQ_NUMBER, UPT_APP_IC_PROFILE,
	UPT_APP_TXN_COUNTER, UPT_APP_CRYPTOGRAM, UPT_CRYPT_AMOUNT, UPT_CASHBACK_AMOUNT,
	UPT_CRYPT_INFO_DATA, UPT_CVM_RESULTS, UPT_DEDICATED_FILE_NAME, UPT_IFD_SER_NUMBER,
	UPT_ISS_APP_DATA, UPT_ISS_AUTH_DATA, UPT_TRL_CON_CODE, UPT_TRL_APP_VER_NUMBER,
	UPT_CHIP_TRL_CAPABILITIES, UPT_CHIP_TRL_TYPE, UPT_TRL_VER_RESULTS, UPT_CHIP_TXN_DATE,
	UPT_CHIP_TXN_TYPE, UPT_CHIP_CUR_CODE, UPT_UPBL_NUMBER, UPT_CENTRE_PROC_DATE,
	UPT_OUT_FILE_DATE, UPT_FILE_ID, UPT_CARD_PRESENT, UPT_CH_PRESENT,
	UPT_APP_PAN_SEQ_NUMBER, UPT_POS_ENTRY_MODE, UPT_SETL_INDICATOR, UPT_TXN_FEE_AMOUNT`

func unionPayDataArgs(e *UnionPayAcqTxnWorkEntity) []any {
	return []any{
		e.SerialNumber, e.LastUpdated, e.UpdatedUser, e.InstitutionCode, e.IntCode,
		e.PrjSerNumber, e.GenStatus, e.TxnRefNumber, nullStr(e.Rrn),
		nullStr(e.MerchantId), nullStr(e.TerminalId), nullStr(e.TxnType), nullStr(e.CardNumber), e.TxnAmount,
		e.SurchargeAmount, nullTimeP(e.LocalDateTime), nullTimeP(e.TxnDate), nullStr(e.MeName), nullStr(e.MeCity),
		nullStr(e.MeCountry), nullStr(e.Mcc), nullStr(e.ApprovalCode), nullStr(e.TxnCurCode), nullStr(e.StanNumber),
		nullStr(e.OrgInstIdCode), nullStr(e.AcqinstIdCode), nullStr(e.FwdInstIdCode), nullStr(e.AcqRefData),
		nullStr(e.ResponseCode), nullStr(e.ReceivingInstIdCode), nullStr(e.PosConditionCode), nullStr(e.TxnInitiatingChannel),
		nullStr(e.PricingSchemeCode), nullStr(e.EncryptedCardNumber), nullStr(e.CardInputMode),
		nullStr(e.CardInputCapability), nullStr(e.CardSeqNumber), nullStr(e.AppICProfile),
		nullStr(e.AppTxnCounter), nullStr(e.AppCryptogram), e.CryptAmount, e.CashBackAmount,
		nullStr(e.CryptInfoData), nullStr(e.CvmResult), nullStr(e.DedicatedFileName), nullStr(e.IfdSerNumber),
		nullStr(e.IssAppData), nullStr(e.IssAuthData), nullStr(e.TrlConCode), nullStr(e.TrlAppVerNumber),
		nullStr(e.ChipTrlCapabilities), nullStr(e.ChipTrlType), nullStr(e.TrlVerResult), nullStr(e.ChipTxnDate),
		nullStr(e.ChipTxnType), nullStr(e.ChipCurCode), nullStr(e.UpblNumber), nullTimeP(e.CentreProcDate),
		nullTimeP(e.FileProcDate), nullStr(e.FileID), nullStr(e.CardPresent), nullStr(e.ChPresent),
		nullStr(e.PanSequenceNumber), nullStr(e.PosEntryMode), nullStr(e.SettlementIndicator), e.TxnFeeAmount,
	}
}

// InsertUnionPayData mirrors moveWorkToData: the serial number is preserved.
func (s *oracleStore) InsertUnionPayData(ctx context.Context, ents []*UnionPayAcqTxnDataEntity) error {
	sqlStmt := "INSERT INTO UP_ACQ_TXN_DATA (" + unionPayColumns + ") VALUES (" + mercuryInsertValues(66) + ")"
	for _, e := range ents {
		if _, err := s.db.ExecContext(ctx, sqlStmt, unionPayDataArgs(e)...); err != nil {
			return err
		}
	}
	return nil
}

// CompleteUnionPayPosStatus mirrors the other networks' POS completion update.
func (s *oracleStore) CompleteUnionPayPosStatus(ctx context.Context, ins int) error {
	_, err := s.db.ExecContext(ctx, `
		UPDATE POS_TRANSACTIONS pos SET
		  pos.PTR_GEN_STATUS = 6,
		  pos.PTR_OUT_STATUS = 'Completed'
		WHERE pos.PTR_RET_REF_NUMBER IN (
		  SELECT upt.UPT_RET_REF_NUMBER FROM UP_ACQ_TXN_WORK upt WHERE upt.UPT_GEN_STATUS = 4
		)
		AND pos.PTR_NETWORK IN ('UNIONPAY')
		AND pos.PTR_GEN_STATUS = 4
		AND pos.PTR_INS_CODE = :1`, ins)
	return err
}
