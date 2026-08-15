package outsvc

import (
	"context"
	"database/sql"
	"fmt"
	"strings"
	"time"
)

// ---- VISA_ACQ_TXN_WORK / VISA_ACQ_TXN_DATA ----

func (s *oracleStore) CountVisaWorkBetween(ctx context.Context, ins, status int, from, to time.Time) (int, error) {
	var n int
	err := s.db.QueryRowContext(ctx, `
		SELECT COUNT(*) FROM VISA_ACQ_TXN_WORK
		WHERE VTD_INS_CODE = :1 AND VTD_GEN_STATUS = :2 AND VTD_PURCHASE_DATE BETWEEN :3 AND :4`,
		ins, status, from, to).Scan(&n)
	return n, err
}

func (s *oracleStore) CountVisaWorkLessThanEqual(ctx context.Context, ins, status int, to time.Time) (int, error) {
	var n int
	err := s.db.QueryRowContext(ctx, `
		SELECT COUNT(*) FROM VISA_ACQ_TXN_WORK
		WHERE VTD_INS_CODE = :1 AND VTD_GEN_STATUS = :2 AND VTD_PURCHASE_DATE <= :3`,
		ins, status, to).Scan(&n)
	return n, err
}

func (s *oracleStore) FindVisaWorkBetween(ctx context.Context, ins, intCode, status int, from, to time.Time) ([]*VisaAcqTxnWorkEntity, error) {
	rows, err := s.db.QueryContext(ctx, `
		SELECT * FROM VISA_ACQ_TXN_WORK
		WHERE VTD_INS_CODE = :1 AND VTD_INT_CODE = :2 AND VTD_GEN_STATUS = :3
		  AND VTD_PURCHASE_DATE BETWEEN :4 AND :5`,
		ins, intCode, status, from, to)
	if err != nil {
		return nil, err
	}
	defer rows.Close()
	return bindVisaWork(rows)
}

func (s *oracleStore) FindVisaWorkLessThanEqual(ctx context.Context, ins, intCode, status int, to time.Time) ([]*VisaAcqTxnWorkEntity, error) {
	rows, err := s.db.QueryContext(ctx, `
		SELECT * FROM VISA_ACQ_TXN_WORK
		WHERE VTD_INS_CODE = :1 AND VTD_INT_CODE = :2 AND VTD_GEN_STATUS = :3
		  AND VTD_PURCHASE_DATE <= :4`,
		ins, intCode, status, to)
	if err != nil {
		return nil, err
	}
	defer rows.Close()
	return bindVisaWork(rows)
}

// inClause builds a ":n" list for the txn-code IN/NOT IN predicates. Oracle
// supports up to 1000 elements in an IN list; the caller passes at most a few.
func visaInClause(codes []string) string {
	if len(codes) == 0 {
		return "''"
	}
	parts := make([]string, len(codes))
	for i := range codes {
		parts[i] = fmt.Sprintf(":%d", i+1)
	}
	return strings.Join(parts, ",")
}

func (s *oracleStore) FindVisaWorkFeeBetween(ctx context.Context, ins, intCode, status int, txnCode []string, from, to time.Time) ([]*VisaAcqTxnWorkEntity, error) {
	query := `
		SELECT * FROM VISA_ACQ_TXN_WORK
		WHERE VTD_INS_CODE = :1 AND VTD_INT_CODE = :2 AND VTD_GEN_STATUS = :3
		  AND VTD_TXN_CODE IN (` + visaInClause(txnCode) + `)
		  AND VTD_PURCHASE_DATE BETWEEN ` + fmt.Sprintf(":%d", len(txnCode)+1) + ` AND ` + fmt.Sprintf(":%d", len(txnCode)+2)
	args := visaCodeArgs(ins, intCode, status, txnCode)
	args = append(args, from, to)
	rows, err := s.db.QueryContext(ctx, query, args...)
	if err != nil {
		return nil, err
	}
	defer rows.Close()
	return bindVisaWork(rows)
}

func (s *oracleStore) FindVisaWorkFeeLessThanEqual(ctx context.Context, ins, intCode, status int, txnCode []string, to time.Time) ([]*VisaAcqTxnWorkEntity, error) {
	query := `
		SELECT * FROM VISA_ACQ_TXN_WORK
		WHERE VTD_INS_CODE = :1 AND VTD_INT_CODE = :2 AND VTD_GEN_STATUS = :3
		  AND VTD_TXN_CODE IN (` + visaInClause(txnCode) + `)
		  AND VTD_PURCHASE_DATE <= ` + fmt.Sprintf(":%d", len(txnCode)+1)
	args := visaCodeArgs(ins, intCode, status, txnCode)
	args = append(args, to)
	rows, err := s.db.QueryContext(ctx, query, args...)
	if err != nil {
		return nil, err
	}
	defer rows.Close()
	return bindVisaWork(rows)
}

func (s *oracleStore) FindVisaWorkTxnBetween(ctx context.Context, ins, intCode, status int, txnCode []string, from, to time.Time) ([]*VisaAcqTxnWorkEntity, error) {
	query := `
		SELECT * FROM VISA_ACQ_TXN_WORK
		WHERE VTD_INS_CODE = :1 AND VTD_INT_CODE = :2 AND VTD_GEN_STATUS = :3
		  AND VTD_TXN_CODE NOT IN (` + visaInClause(txnCode) + `)
		  AND VTD_PURCHASE_DATE BETWEEN ` + fmt.Sprintf(":%d", len(txnCode)+1) + ` AND ` + fmt.Sprintf(":%d", len(txnCode)+2)
	args := visaCodeArgs(ins, intCode, status, txnCode)
	args = append(args, from, to)
	rows, err := s.db.QueryContext(ctx, query, args...)
	if err != nil {
		return nil, err
	}
	defer rows.Close()
	return bindVisaWork(rows)
}

func (s *oracleStore) FindVisaWorkTxnLessThanEqual(ctx context.Context, ins, intCode, status int, txnCode []string, to time.Time) ([]*VisaAcqTxnWorkEntity, error) {
	query := `
		SELECT * FROM VISA_ACQ_TXN_WORK
		WHERE VTD_INS_CODE = :1 AND VTD_INT_CODE = :2 AND VTD_GEN_STATUS = :3
		  AND VTD_TXN_CODE NOT IN (` + visaInClause(txnCode) + `)
		  AND VTD_PURCHASE_DATE <= ` + fmt.Sprintf(":%d", len(txnCode)+1)
	args := visaCodeArgs(ins, intCode, status, txnCode)
	args = append(args, to)
	rows, err := s.db.QueryContext(ctx, query, args...)
	if err != nil {
		return nil, err
	}
	defer rows.Close()
	return bindVisaWork(rows)
}

func visaCodeArgs(ins, intCode, status int, txnCode []string) []any {
	args := make([]any, 0, len(txnCode)+3)
	args = append(args, ins, intCode, status)
	for _, c := range txnCode {
		args = append(args, c)
	}
	return args
}

func (s *oracleStore) FindVisaWorkByStatus(ctx context.Context, ins, status int) ([]*VisaAcqTxnWorkEntity, error) {
	rows, err := s.db.QueryContext(ctx, `
		SELECT * FROM VISA_ACQ_TXN_WORK WHERE VTD_INS_CODE = :1 AND VTD_GEN_STATUS = :2`, ins, status)
	if err != nil {
		return nil, err
	}
	defer rows.Close()
	return bindVisaWork(rows)
}

func (s *oracleStore) FindVisaWorkByFileId(ctx context.Context, ins int, fileId string) ([]*VisaAcqTxnWorkEntity, error) {
	rows, err := s.db.QueryContext(ctx, `
		SELECT * FROM VISA_ACQ_TXN_WORK WHERE VTD_INS_CODE = :1 AND VTD_FILE_ID = :2`,
		ins, nullStr(fileId))
	if err != nil {
		return nil, err
	}
	defer rows.Close()
	return bindVisaWork(rows)
}

// FindVisaWorkByArn mirrors VisaAcqTxnWorkRepo.findByArn (used by
// BaseIIOutgoingServiceImpl.updateFailedTxn).
func (s *oracleStore) FindVisaWorkByArn(ctx context.Context, arn string) ([]*VisaAcqTxnWorkEntity, error) {
	rows, err := s.db.QueryContext(ctx, `
		SELECT * FROM VISA_ACQ_TXN_WORK WHERE VTD_ACQ_REF_NUMBER = :1`,
		nullStr(arn))
	if err != nil {
		return nil, err
	}
	defer rows.Close()
	return bindVisaWork(rows)
}

func bindVisaWork(rows *sql.Rows) ([]*VisaAcqTxnWorkEntity, error) {
	maps, err := rowsToMaps(rows)
	if err != nil {
		return nil, err
	}
	out := make([]*VisaAcqTxnWorkEntity, 0, len(maps))
	for _, m := range maps {
		e := &VisaAcqTxnWorkEntity{}
		bindRow(m, e)
		out = append(out, e)
	}
	return out, nil
}

// UpdateVisaWorkStatuses mirrors saveAll on the Visa work repo: only the four
// fields touched by updateVisaEntity are written.
func (s *oracleStore) UpdateVisaWorkStatuses(ctx context.Context, ents []*VisaAcqTxnWorkEntity) error {
	for _, e := range ents {
		if _, err := s.db.ExecContext(ctx, `
			UPDATE VISA_ACQ_TXN_WORK SET
			  VTD_LAST_UPDATED = :1,
			  VTD_UPDATED_USER = :2,
			  VTD_GEN_STATUS   = :3,
			  VTD_FILE_ID      = :4
			WHERE VTD_SER_NUMBER = :5`,
			e.LastUpdated, e.UpdatedUser, e.GeneralStatus, nullStr(e.FileId), e.SerNumber); err != nil {
			return err
		}
	}
	return nil
}

func (s *oracleStore) DeleteVisaWork(ctx context.Context, ents []*VisaAcqTxnWorkEntity) error {
	for _, e := range ents {
		if _, err := s.db.ExecContext(ctx, `DELETE FROM VISA_ACQ_TXN_WORK WHERE VTD_SER_NUMBER = :1`, e.SerNumber); err != nil {
			return err
		}
	}
	return nil
}

// visaDataColumns is the shared column list for VISA_ACQ_TXN_DATA and
// VISA_ACQ_TXN_WORK inserts (moveWorkToData / revert copy the same field set).
const visaDataColumns = `
	VTD_SER_NUMBER, VTD_LAST_UPDATED, VTD_UPDATED_USER, VTD_INS_CODE, VTD_INT_CODE,
	VTD_PRJ_SER_NUMBER, VTD_GEN_STATUS, VTD_TXN_REF_NUMBER, VTD_TXN_TYPE, VTD_TXN_CODE,
	VTD_ENC_CARD_NUMBER, VTD_ACQ_REF_NUMBER, VTD_PURCHASE_DATE, VTD_TXN_CUR_CODE, VTD_TXN_AMOUNT,
	VTD_SCHG_AMOUNT, VTD_ME_NAME, VTD_ME_CITY, VTD_ME_COUNTRY, VTD_MCC,
	VTD_APPR_CODE, VTD_CH_ID_METHOD, VTD_POS_ENTRY_MODE, VTD_MEMBER_TEXT, VTD_FEE_PRG_INDICATOR,
	VTD_MERCHANT_ID, VTD_TERMINAL_ID, VTD_MOTO_ECOM_INDICATOR, VTD_ACC_SELECTION, VTD_ACQ_BUSS_ID,
	VTD_POS_ENVIRONMENT, VTD_RESP_CODE, VTD_TRL_TXN_DATE, VTD_CRYPT_AMOUNT, VTD_CASHBACK_AMOUNT,
	VTD_TXN_ID, VTD_VISA_TOKEN, VTD_AUTH_CHAR_INDICATOR, VTD_ACC_FUND_SOURCE, VTD_MARKET_SPEC_DATA_IND,
	VTD_PRODUCT_ID, VTD_VALIDATION_CODE, VTD_SPEND_QUALI_IND, VTD_COLL_ONLY_FLAG, VTD_FILE_ID,
	VTD_RET_REF_NUMBER, VTD_REASON_CODE, VTD_USAGE_CODE, VTD_PROC_CODE, VTD_SETL_FLAG,
	VTD_TRL_CAPABILITY, VTD_REIMB_ATTRIBUTE, VTD_STAN, VTD_AUTH_AMOUNT, VTD_TRL_CAP_PROFILE,
	VTD_TRL_CON_CODE, VTD_UPBL_NUMBER, VTD_CARD_SEQ_NUMBER, VTD_APP_TXN_COUNTER, VTD_APP_IC_PROFILE,
	VTD_APP_CRYPTOGRAM, VTD_ISS_APP_DATA_B2, VTD_ISS_APP_DATA_B3, VTD_ISS_APP_DATA_B4, VTD_ISS_APP_DATA_B8,
	VTD_ISS_APP_DATA_B9, VTD_ISS_APP_DATA_B1, VTD_ISS_APP_DATA_B17, VTD_ISS_APP_DATA_B18, VTD_TRL_VER_RESULTS,
	VTD_FORM_FACT_INDICATOR, VTD_ISS_SCRIPT_RESULTS, VTD_SERVICE_CODE, VTD_TXN_FEE_AMOUNT, VTD_SENDER_NAME,
	VTD_RECIPIENT_NAME, VTD_BUSS_APP_ID, VTD_SENDER_ACCOUNT, VTD_DCC_INDICATOR, VTD_NETWORK,
	VTD_DCC_CURRENCY, VTD_DCC_AMOUNT, VTD_ACC_TRL_INDICATOR`

// visaInsertValues builds the :N placeholders for visaDataColumns.
func visaInsertValues(n int) string {
	parts := make([]string, n)
	for i := range parts {
		parts[i] = fmt.Sprintf(":%d", i+1)
	}
	return strings.Join(parts, ",")
}

// visaDataArgs maps a work entity to the visaDataColumns insert arguments.
func visaDataArgs(e *VisaAcqTxnWorkEntity) []any {
	return []any{
		e.SerNumber, e.LastUpdated, e.UpdatedUser, e.InstitutionCode, e.IntCode,
		e.PrjSerNumber, e.GeneralStatus, e.TxnRefNumber, nullStr(e.TxnType), nullStr(e.TxnCode),
		nullStr(e.EncCardNumber), nullStr(e.Arn), nullTimeP(e.PurchaseDate), nullStr(e.TxnCurCode), e.TxnAmount,
		e.SchgAmount, nullStr(e.MeName), nullStr(e.MeCity), nullStr(e.MeCountry), nullStr(e.Mcc),
		nullStr(e.ApprovalCode), nullStr(e.ChIdMethod), nullStr(e.PosEntryMode), nullStr(e.MemberText), nullStr(e.FeePrgIndicator),
		nullStr(e.MerchantId), nullStr(e.TerminalId), nullStr(e.MotoEcomIndicator), nullStr(e.AccSelection), nullStr(e.AcqBussId),
		nullStr(e.PosEnvironment), nullStr(e.RespCode), nullTimeP(e.TrlTxnDate), nullFloatP(e.CryptAmount), nullFloatP(e.CashbackAmount),
		nullStr(e.TxnId), nullStr(e.VisaToken), nullStr(e.AuthCharIndicator), nullStr(e.AccFundSource), nullStr(e.MarketSpecDataInd),
		nullStr(e.ProductId), nullStr(e.ValidationCode), nullStr(e.SpendQualiIndictor), nullStr(e.CollOnlyFlag), nullStr(e.FileId),
		nullStr(e.RetRefNumber), nullStr(e.ReasonCode), nullStr(e.UsageCode), nullStr(e.ProcCode), nullStr(e.SetlFlag),
		nullStr(e.TerminalCapability), nullStr(e.ReimAttribute), nullStr(e.Stan), nullFloatP(e.AuthAmount), nullStr(e.TrlCapProfile),
		nullStr(e.TrlCountryCode), nullStr(e.UpblNumber), nullStr(e.CardSeqNumber), nullStr(e.AppTxnCounter), nullStr(e.AppIcProfile),
		nullStr(e.AppCryptogram), nullStr(e.IssAppDataB2), nullStr(e.IssAppDataB3), nullStr(e.IssAppDataB4), nullStr(e.IssAppDataB8),
		nullStr(e.IssAppDataB9), nullStr(e.IssAppDataB1), nullStr(e.IssAppDataB17), nullStr(e.IssAppDataB18), nullStr(e.TrlVerResult),
		nullStr(e.FormFactorIndicator), nullStr(e.IssScriptResult), nullStr(e.ServiceCode), nullFloatP(e.TxnFeeAmount), nullStr(e.SenderName),
		nullStr(e.RecipientName), nullStr(e.BussAppId), nullStr(e.SenderAccount), nullStr(e.DccIndicator), nullStr(e.Network),
		nullStr(e.DccCurrency), nullFloatP(e.DccAmount), nullStr(e.AcceptanceTrlIndicator),
	}
}

// InsertVisaData mirrors VisaAcqTxnDataRepo.saveAll (moveWorkToData). The serial
// number is preserved (set explicitly in Java).
func (s *oracleStore) InsertVisaData(ctx context.Context, ents []*VisaAcqTxnDataEntity) error {
	sqlStmt := "INSERT INTO VISA_ACQ_TXN_DATA (" + visaDataColumns + ") VALUES (" + visaInsertValues(83) + ")"
	for _, e := range ents {
		if _, err := s.db.ExecContext(ctx, sqlStmt, visaDataArgs(e)...); err != nil {
			return err
		}
	}
	return nil
}

func (s *oracleStore) FindVisaDataByFileId(ctx context.Context, ins int, fileId string) ([]*VisaAcqTxnDataEntity, error) {
	rows, err := s.db.QueryContext(ctx, `
		SELECT * FROM VISA_ACQ_TXN_DATA WHERE VTD_INS_CODE = :1 AND VTD_FILE_ID = :2`,
		ins, nullStr(fileId))
	if err != nil {
		return nil, err
	}
	defer rows.Close()
	return bindVisaWork(rows)
}

func (s *oracleStore) DeleteVisaData(ctx context.Context, ents []*VisaAcqTxnDataEntity) error {
	for _, e := range ents {
		if _, err := s.db.ExecContext(ctx, `DELETE FROM VISA_ACQ_TXN_DATA WHERE VTD_SER_NUMBER = :1`, e.SerNumber); err != nil {
			return err
		}
	}
	return nil
}

// InsertVisaWork mirrors VisaAcqTxnWorkRepo.saveAll during revert
// (mapToVisaAcqWorkEntity preserves the serial number, genStatus=3).
func (s *oracleStore) InsertVisaWork(ctx context.Context, ents []*VisaAcqTxnWorkEntity) error {
	sqlStmt := "INSERT INTO VISA_ACQ_TXN_WORK (" + visaDataColumns + ") VALUES (" + visaInsertValues(83) + ")"
	for _, e := range ents {
		if _, err := s.db.ExecContext(ctx, sqlStmt, visaDataArgs(e)...); err != nil {
			return err
		}
	}
	return nil
}

// CompleteVisaPosStatus mirrors PosTransactionRepo.completeVISAPosStatus. The
// Java PosTransactionEntity.rrn property maps to column PTR_RET_REF_NUMBER.
func (s *oracleStore) CompleteVisaPosStatus(ctx context.Context, ins int) error {
	_, err := s.db.ExecContext(ctx, `
		UPDATE POS_TRANSACTIONS pos SET
		  pos.PTR_GEN_STATUS = 6,
		  pos.PTR_OUT_STATUS = 'Completed'
		WHERE pos.PTR_RET_REF_NUMBER IN (
		  SELECT vis.VTD_RET_REF_NUMBER FROM VISA_ACQ_TXN_WORK vis WHERE vis.VTD_GEN_STATUS = 4
		)
		AND pos.PTR_NETWORK = 'VISA'
		AND pos.PTR_GEN_STATUS = 4
		AND pos.PTR_INS_CODE = :1`, ins)
	return err
}
