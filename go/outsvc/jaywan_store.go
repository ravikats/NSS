package outsvc

import (
	"context"
	"database/sql"
	"fmt"
	"strings"
	"time"
)

// ---- JAYWAN_ACQ_TXN_WORK / JAYWAN_ACQ_TXN_DATA ----

func (s *oracleStore) CountJaywanWorkBetween(ctx context.Context, ins, status int, from, to time.Time) (int, error) {
	var n int
	err := s.db.QueryRowContext(ctx, `
		SELECT COUNT(*) FROM JAYWAN_ACQ_TXN_WORK
		WHERE JWN_INS_CODE = :1 AND JWN_GEN_STATUS = :2 AND JWN_LOCAL_DATE_TIME BETWEEN :3 AND :4`,
		ins, status, from, to).Scan(&n)
	return n, err
}

func (s *oracleStore) CountJaywanWorkLessThanEqual(ctx context.Context, ins, status int, to time.Time) (int, error) {
	var n int
	err := s.db.QueryRowContext(ctx, `
		SELECT COUNT(*) FROM JAYWAN_ACQ_TXN_WORK
		WHERE JWN_INS_CODE = :1 AND JWN_GEN_STATUS = :2 AND JWN_LOCAL_DATE_TIME <= :3`,
		ins, status, to).Scan(&n)
	return n, err
}

func (s *oracleStore) FindJaywanWorkBetween(ctx context.Context, ins, intCode, status int, from, to time.Time) ([]*JaywanAcqTxnWorkEntity, error) {
	rows, err := s.db.QueryContext(ctx, `
		SELECT * FROM JAYWAN_ACQ_TXN_WORK
		WHERE JWN_INS_CODE = :1 AND JWN_INT_CODE = :2 AND JWN_GEN_STATUS = :3
		  AND JWN_LOCAL_DATE_TIME BETWEEN :4 AND :5`,
		ins, intCode, status, from, to)
	if err != nil {
		return nil, err
	}
	defer rows.Close()
	return bindJaywanWork(rows)
}

func (s *oracleStore) FindJaywanWorkLessThanEqual(ctx context.Context, ins, intCode, status int, to time.Time) ([]*JaywanAcqTxnWorkEntity, error) {
	rows, err := s.db.QueryContext(ctx, `
		SELECT * FROM JAYWAN_ACQ_TXN_WORK
		WHERE JWN_INS_CODE = :1 AND JWN_INT_CODE = :2 AND JWN_GEN_STATUS = :3
		  AND JWN_LOCAL_DATE_TIME <= :4`,
		ins, intCode, status, to)
	if err != nil {
		return nil, err
	}
	defer rows.Close()
	return bindJaywanWork(rows)
}

// FindJaywanWorkByIntAndStatus mirrors
// JWNAcqTxnWorkRepo.findByInstitutionCodeAndIntCodeAndGenStatus (used for the
// OUTGOING_SUMMARY grouping after status 4).
func (s *oracleStore) FindJaywanWorkByIntAndStatus(ctx context.Context, ins, intCode, status int) ([]*JaywanAcqTxnWorkEntity, error) {
	rows, err := s.db.QueryContext(ctx, `
		SELECT * FROM JAYWAN_ACQ_TXN_WORK
		WHERE JWN_INS_CODE = :1 AND JWN_INT_CODE = :2 AND JWN_GEN_STATUS = :3`,
		ins, intCode, status)
	if err != nil {
		return nil, err
	}
	defer rows.Close()
	return bindJaywanWork(rows)
}

// FindJaywanWorkByRrn mirrors JWNAcqTxnWorkRepo.findByRrn (updateFailedTxn).
func (s *oracleStore) FindJaywanWorkByRrn(ctx context.Context, rrn string) ([]*JaywanAcqTxnWorkEntity, error) {
	rows, err := s.db.QueryContext(ctx, `
		SELECT * FROM JAYWAN_ACQ_TXN_WORK WHERE JWN_RET_REF_NUMBER = :1`,
		nullStr(rrn))
	if err != nil {
		return nil, err
	}
	defer rows.Close()
	return bindJaywanWork(rows)
}

func bindJaywanWork(rows *sql.Rows) ([]*JaywanAcqTxnWorkEntity, error) {
	maps, err := rowsToMaps(rows)
	if err != nil {
		return nil, err
	}
	out := make([]*JaywanAcqTxnWorkEntity, 0, len(maps))
	for _, m := range maps {
		e := &JaywanAcqTxnWorkEntity{}
		bindRow(m, e)
		out = append(out, e)
	}
	return out, nil
}

// FindJaywanNetworkDataByRef returns the JAYWAN_NETWORK_DATA rows matching the
// given (prjSerNumber, txnRefNumbers). The Go Jaywan outgoing flow uses these to
// source nTxnId/nPosTxnStat/nProcCd (fields absent from JAYWAN_ACQ_TXN_WORK).
func (s *oracleStore) FindJaywanNetworkDataByRef(ctx context.Context, prjSerNumber int64, txnRefNumbers []int64) ([]*JaywanNetworkDataEntity, error) {
	if len(txnRefNumbers) == 0 {
		return nil, nil
	}
	ph := make([]string, len(txnRefNumbers))
	args := make([]any, 0, len(txnRefNumbers)+1)
	args = append(args, prjSerNumber)
	for i, r := range txnRefNumbers {
		ph[i] = fmt.Sprintf(":%d", i+2)
		args = append(args, r)
	}
	rows, err := s.db.QueryContext(ctx, `
		SELECT * FROM JAYWAN_NETWORK_DATA
		WHERE JND_PRJ_SER_NUMBER = :1 AND JND_TXN_REF_NUMBER IN (`+strings.Join(ph, ",")+`)`,
		args...)
	if err != nil {
		return nil, err
	}
	defer rows.Close()
	return bindJaywanNetworkData(rows)
}

func bindJaywanNetworkData(rows *sql.Rows) ([]*JaywanNetworkDataEntity, error) {
	maps, err := rowsToMaps(rows)
	if err != nil {
		return nil, err
	}
	out := make([]*JaywanNetworkDataEntity, 0, len(maps))
	for _, m := range maps {
		e := &JaywanNetworkDataEntity{}
		bindRow(m, e)
		out = append(out, e)
	}
	return out, nil
}

// UpdateJaywanWorkStatuses mirrors saveAll on the Jaywan work repo: only
// JWN_GEN_STATUS is changed by the Java service.
func (s *oracleStore) UpdateJaywanWorkStatuses(ctx context.Context, ents []*JaywanAcqTxnWorkEntity) error {
	for _, e := range ents {
		if _, err := s.db.ExecContext(ctx, `
			UPDATE JAYWAN_ACQ_TXN_WORK SET
			  JWN_GEN_STATUS = :1
			WHERE JWN_SER_NUMBER = :2`,
			e.GenStatus, e.SerialNumber); err != nil {
			return err
		}
	}
	return nil
}

func (s *oracleStore) DeleteJaywanWork(ctx context.Context, ents []*JaywanAcqTxnWorkEntity) error {
	for _, e := range ents {
		if _, err := s.db.ExecContext(ctx, `DELETE FROM JAYWAN_ACQ_TXN_WORK WHERE JWN_SER_NUMBER = :1`, e.SerialNumber); err != nil {
			return err
		}
	}
	return nil
}

// jaywanColumns is the shared column list for JAYWAN_ACQ_TXN_DATA and
// JAYWAN_ACQ_TXN_WORK inserts (moveWorkToData / revert copy the same field set).
const jaywanColumns = `
	JWN_SER_NUMBER, JWN_LAST_UPDATED, JWN_UPDATED_USER, JWN_INS_CODE, JWN_INT_CODE,
	JWN_PRJ_SER_NUMBER, JWN_GEN_STATUS, JWN_TXN_REF_NUMBER, JWN_TXN_TYPE, JWN_TXN_CODE,
	JWN_MSG_TYPE_ID, JWN_FUNC_CODE, JWN_LOCAL_DATE_TIME, JWN_CARD_NUMBER, JWN_ACQ_REF_DATA,
	JWN_APPR_CODE, JWN_TERMINAL_ID, JWN_TXN_AMOUNT, JWN_SETL_AMOUNT, JWN_BILL_AMOUNT,
	JWN_SCHG_AMOUNT, JWN_CONV_RATE, JWN_TXN_CUR_CODE, JWN_CASHBACK_AMOUNT, JWN_RET_REF_NUMBER,
	JWN_MERCHANT_ID, JWN_ME_NAME, JWN_ME_CITY, JWN_ME_STATE_CODE, JWN_ME_COUNTRY,
	JWN_MCC, JWN_POS_ENTRY_MODE, JWN_ACQ_INST_ID, JWN_REV_INDICATOR, JWN_DOM_INTL_FLAG,
	JWN_TRL_TYPE, JWN_ME_CATEGORY_TYPE, JWN_CARD_TYPE, JWN_SMS_DMS_FLAG, JWN_CENTRE_PROC_DATE,
	JWN_OUT_FILE_DATE, JWN_FILE_ID, JWN_ENC_CARD_NUMBER, JWN_RESP_CODE, JWN_ECOM_INDICATOR,
	JWN_SETTL_DATE, JWN_SETTL_INDICATOR, JWN_POS_CONDITION_CODE, JWN_FULL_PARTIAL_INDICATOR`

func jaywanInsertValues(n int) string {
	parts := make([]string, n)
	for i := range parts {
		parts[i] = fmt.Sprintf(":%d", i+1)
	}
	return strings.Join(parts, ",")
}

// jaywanDataArgs maps a work entity to the jaywanColumns insert arguments.
func jaywanDataArgs(e *JaywanAcqTxnWorkEntity) []any {
	return []any{
		e.SerialNumber, e.LastUpdated, e.UpdatedUser, e.InstitutionCode, e.IntCode,
		e.PrjSerNumber, e.GenStatus, e.TxnRefNumber, nullStr(e.TxnType), nullStr(e.TxnCode),
		nullStr(e.MessageTypeId), nullStr(e.FunctionCode), nullTimeP(e.LocalDateTime), nullStr(e.CardNumber), nullStr(e.AcqRefData),
		nullStr(e.ApprovalCode), nullStr(e.TerminalId), e.TxnAmount, e.SettledAmount, e.BillAmount,
		e.SurchargeAmount, e.ConvRate, nullStr(e.TxnCurCode), e.CashBackAmount, nullStr(e.Rrn),
		nullStr(e.MerchantId), nullStr(e.MeName), nullStr(e.MeCity), nullStr(e.MeStateCode), nullStr(e.MeCountry),
		nullStr(e.Mcc), nullStr(e.PosEntryMode), nullStr(e.AcqinstIdCode), nullStr(e.RevIndicator), nullStr(e.CardDomIntlFlag),
		nullStr(e.TrlType), nullStr(e.MeCategoryType), nullStr(e.CardType), nullStr(e.DmsSmsMode), nullTimeP(e.CentreProcDate),
		nullTimeP(e.FileProcDate), nullStr(e.FileID), nullStr(e.EncCardNumber), nullStr(e.ResponseCode), nullStr(e.MotoEcomIndicator),
		nullTimeP(e.SettlDate), nullStr(e.SettlIndicator), nullStr(e.PosConditionCode), nullStr(e.FullPartialInd),
	}
}

// InsertJaywanData mirrors JWNAcqTxnDataRepo.saveAll (moveWorkToData). The
// serial number is preserved.
func (s *oracleStore) InsertJaywanData(ctx context.Context, ents []*JaywanAcqTxnDataEntity) error {
	sqlStmt := "INSERT INTO JAYWAN_ACQ_TXN_DATA (" + jaywanColumns + ") VALUES (" + jaywanInsertValues(49) + ")"
	for _, e := range ents {
		if _, err := s.db.ExecContext(ctx, sqlStmt, jaywanDataArgs(e)...); err != nil {
			return err
		}
	}
	return nil
}

func (s *oracleStore) FindJaywanDataByFileId(ctx context.Context, ins int, fileId string) ([]*JaywanAcqTxnDataEntity, error) {
	rows, err := s.db.QueryContext(ctx, `
		SELECT * FROM JAYWAN_ACQ_TXN_DATA WHERE JWN_INS_CODE = :1 AND JWN_FILE_ID = :2`,
		ins, nullStr(fileId))
	if err != nil {
		return nil, err
	}
	defer rows.Close()
	return bindJaywanWork(rows)
}

func (s *oracleStore) DeleteJaywanData(ctx context.Context, ents []*JaywanAcqTxnDataEntity) error {
	for _, e := range ents {
		if _, err := s.db.ExecContext(ctx, `DELETE FROM JAYWAN_ACQ_TXN_DATA WHERE JWN_SER_NUMBER = :1`, e.SerialNumber); err != nil {
			return err
		}
	}
	return nil
}

// InsertJaywanWork mirrors JWNAcqTxnWorkRepo.saveAll during revert
// (mapToJaywanAcqWorkEntity preserves the serial number, genStatus=3).
func (s *oracleStore) InsertJaywanWork(ctx context.Context, ents []*JaywanAcqTxnWorkEntity) error {
	sqlStmt := "INSERT INTO JAYWAN_ACQ_TXN_WORK (" + jaywanColumns + ") VALUES (" + jaywanInsertValues(49) + ")"
	for _, e := range ents {
		if _, err := s.db.ExecContext(ctx, sqlStmt, jaywanDataArgs(e)...); err != nil {
			return err
		}
	}
	return nil
}

// CompleteJaywanPosStatus mirrors PosTransactionRepo.completeJaywanPosStatus.
func (s *oracleStore) CompleteJaywanPosStatus(ctx context.Context, ins int) error {
	_, err := s.db.ExecContext(ctx, `
		UPDATE POS_TRANSACTIONS pos SET
		  pos.PTR_GEN_STATUS = 6,
		  pos.PTR_OUT_STATUS = 'Completed'
		WHERE pos.PTR_RET_REF_NUMBER IN (
		  SELECT jwn.JWN_RET_REF_NUMBER FROM JAYWAN_ACQ_TXN_WORK jwn WHERE jwn.JWN_GEN_STATUS = 4
		)
		AND pos.PTR_SCHEME = 'JAYWAN'
		AND pos.PTR_GEN_STATUS = 4
		AND pos.PTR_INS_CODE = :1`, ins)
	return err
}
