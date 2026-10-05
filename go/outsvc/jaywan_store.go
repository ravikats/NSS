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
		WHERE JWN_INS_CODE = :1 AND JWN_GEN_STATUS = :2 AND JWN_LOCAL_DATE_TIME BETWEEN TO_DATE(:3,'YYYY-MM-DD HH24:MI:SS') AND TO_DATE(:4,'YYYY-MM-DD HH24:MI:SS')`,
		ins, status, oraTime(from), oraTime(to)).Scan(&n)
	return n, err
}

func (s *oracleStore) CountJaywanWorkLessThanEqual(ctx context.Context, ins, status int, to time.Time) (int, error) {
	var n int
	err := s.db.QueryRowContext(ctx, `
		SELECT COUNT(*) FROM JAYWAN_ACQ_TXN_WORK
		WHERE JWN_INS_CODE = :1 AND JWN_GEN_STATUS = :2 AND JWN_LOCAL_DATE_TIME <= TO_DATE(:3,'YYYY-MM-DD HH24:MI:SS')`,
		ins, status, oraTime(to)).Scan(&n)
	return n, err
}

func (s *oracleStore) FindJaywanWorkBetween(ctx context.Context, ins, intCode, status int, from, to time.Time) ([]*JaywanAcqTxnWorkEntity, error) {
	rows, err := s.db.QueryContext(ctx, `
		SELECT * FROM JAYWAN_ACQ_TXN_WORK
		WHERE JWN_INS_CODE = :1 AND JWN_INT_CODE = :2 AND JWN_GEN_STATUS = :3
		  AND JWN_LOCAL_DATE_TIME BETWEEN TO_DATE(:4,'YYYY-MM-DD HH24:MI:SS') AND TO_DATE(:5,'YYYY-MM-DD HH24:MI:SS')`,
		ins, intCode, status, oraTime(from), oraTime(to))
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
		  AND JWN_LOCAL_DATE_TIME <= TO_DATE(:4,'YYYY-MM-DD HH24:MI:SS')`,
		ins, intCode, status, oraTime(to))
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
// JWN_SER_NUMBER is deliberately absent: it is GENERATED ALWAYS AS IDENTITY
// (ISEQ$$_76320.nextval) on both JAYWAN_ACQ_TXN_WORK and JAYWAN_ACQ_TXN_DATA,
// so naming it makes every insert fail with ORA-32795. The Go port used to bind
// the work table's serial number into it, which also silently copied a PK from
// one table into another.
const jaywanColumns = `
	JWN_LAST_UPDATED, JWN_UPDATED_USER, JWN_INS_CODE, JWN_INT_CODE,
	JWN_PRJ_SER_NUMBER, JWN_GEN_STATUS, JWN_TXN_REF_NUMBER, JWN_TXN_TYPE, JWN_TXN_CODE,
	JWN_MSG_TYPE_ID, JWN_FUNC_CODE, JWN_LOCAL_DATE_TIME, JWN_CARD_NUMBER, JWN_ACQ_REF_DATA,
	JWN_APPR_CODE, JWN_TERMINAL_ID, JWN_TXN_AMOUNT, JWN_SETL_AMOUNT, JWN_BILL_AMOUNT,
	JWN_SCHG_AMOUNT, JWN_CONV_RATE, JWN_TXN_CUR_CODE, JWN_CASHBACK_AMOUNT, JWN_RET_REF_NUMBER,
	JWN_MERCHANT_ID, JWN_ME_NAME, JWN_ME_CITY, JWN_ME_STATE_CODE, JWN_ME_COUNTRY,
	JWN_MCC, JWN_POS_ENTRY_MODE, JWN_ACQ_INST_ID, JWN_REV_INDICATOR, JWN_DOM_INTL_FLAG,
	JWN_TRL_TYPE, JWN_ME_CATEGORY_TYPE, JWN_CARD_TYPE, JWN_SMS_DMS_FLAG, JWN_CENTRE_PROC_DATE,
	JWN_OUT_FILE_DATE, JWN_FILE_ID, JWN_ENC_CARD_NUMBER, JWN_RESP_CODE, JWN_ECOM_INDICATOR,
	JWN_SETTL_DATE, JWN_SETTL_INDICATOR, JWN_POS_CONDITION_CODE, JWN_FULL_PARTIAL_INDICATOR`

// jaywanColumnList parses jaywanColumns so callers (and tests) never hardcode
// its length. Both inserts derive their placeholder count from this, so the
// column list and the binder cannot drift apart.
func jaywanColumnList() []string {
	raw := strings.Split(jaywanColumns, ",")
	cols := make([]string, 0, len(raw))
	for _, c := range raw {
		if c = strings.TrimSpace(c); c != "" {
			cols = append(cols, c)
		}
	}
	return cols
}

// jaywanInsertSQL builds the shared INSERT for both jaywan tables. The bind
// count comes from the column list, not a literal.
func jaywanInsertSQL(table string) string {
	return "INSERT INTO " + table + " (" + jaywanColumns + ") VALUES (" + jaywanInsertValues(len(jaywanColumnList())) + ")"
}

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
		e.LastUpdated, e.UpdatedUser, e.InstitutionCode, e.IntCode,
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

// ArchiveJaywanWork implements the archive + retire step as a single
// transaction. Order matters: the rows must land in JAYWAN_ACQ_TXN_DATA BEFORE
// the work rows are marked terminal, otherwise a mid-flight failure leaves work
// rows committed as staged with nothing archived -- and the run reports success.
func (s *oracleStore) ArchiveJaywanWork(ctx context.Context, ents []*JaywanAcqTxnWorkEntity, fileID string) error {
	if len(ents) == 0 {
		return nil
	}
	tx, err := s.db.BeginTx(ctx, nil)
	if err != nil {
		return err
	}
	defer func() { _ = tx.Rollback() }()

	now := time.Now()
	dataSQL := jaywanInsertSQL("JAYWAN_ACQ_TXN_DATA")
	for _, e := range ents {
		d := *e
		d.LastUpdated = now
		d.FileID = fileID
		if _, err := tx.ExecContext(ctx, dataSQL, jaywanDataArgs(&d)...); err != nil {
			return err
		}
	}

	workSQL := `UPDATE JAYWAN_ACQ_TXN_WORK SET JWN_GEN_STATUS = :1, JWN_LAST_UPDATED = :2,
	           JWN_OUT_FILE_DATE = :3, JWN_FILE_ID = :4 WHERE JWN_SER_NUMBER = :5`
	for _, e := range ents {
		e.GenStatus = 4
		if _, err := tx.ExecContext(ctx, workSQL,
			e.GenStatus, now, now, fileID, e.SerialNumber); err != nil {
			return err
		}
	}
	return tx.Commit()
}

// InsertJaywanData mirrors JWNAcqTxnDataRepo.saveAll (moveWorkToData). The
// serial number is preserved.
func (s *oracleStore) InsertJaywanData(ctx context.Context, ents []*JaywanAcqTxnDataEntity) error {
	sqlStmt := jaywanInsertSQL("JAYWAN_ACQ_TXN_DATA")
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
	sqlStmt := jaywanInsertSQL("JAYWAN_ACQ_TXN_WORK")
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
