package outsvc

import (
	"context"
	"database/sql"
	"time"
)

// ---- AMEX_ACQ_TXN_WORK / AMEX_ACQ_TXN_DATA ----
//
// AmexAcqTxnWorkEntity maps AMEX_ACQ_TXN_WORK. The work table is populated by
// the staging split (the Java splitProcessAndStaging copies POS_TRANSACTIONS
// rows into AMEX_ACQ_TXN_WORK for outgoing-eligible AMEX transactions); in the
// local replica it is seeded manually (see replica/amex_staging_setup.sql).

func (s *oracleStore) CountAmexWorkBetween(ctx context.Context, ins, status int, from, to time.Time) (int, error) {
	var n int
	err := s.db.QueryRowContext(ctx, `
		SELECT COUNT(*) FROM AMEX_ACQ_TXN_WORK
		WHERE ATD_INS_CODE = :1 AND ATD_GEN_STATUS = :2 AND ATD_LOCAL_DATE_TIME BETWEEN TO_DATE(:3,'YYYY-MM-DD HH24:MI:SS') AND TO_DATE(:4,'YYYY-MM-DD HH24:MI:SS')`,
		ins, status, oraTime(from), oraTime(to)).Scan(&n)
	return n, err
}

func (s *oracleStore) CountAmexWorkLessThanEqual(ctx context.Context, ins, status int, to time.Time) (int, error) {
	var n int
	err := s.db.QueryRowContext(ctx, `
		SELECT COUNT(*) FROM AMEX_ACQ_TXN_WORK
		WHERE ATD_INS_CODE = :1 AND ATD_GEN_STATUS = :2 AND ATD_LOCAL_DATE_TIME <= TO_DATE(:3,'YYYY-MM-DD HH24:MI:SS')`,
		ins, status, oraTime(to)).Scan(&n)
	return n, err
}

func (s *oracleStore) FindAmexWorkBetween(ctx context.Context, ins, intCode, status int, from, to time.Time) ([]*AmexAcqTxnWorkEntity, error) {
	rows, err := s.db.QueryContext(ctx, `
		SELECT * FROM AMEX_ACQ_TXN_WORK
		WHERE ATD_INS_CODE = :1 AND ATD_INT_CODE = :2 AND ATD_GEN_STATUS = :3
		  AND ATD_LOCAL_DATE_TIME BETWEEN TO_DATE(:4,'YYYY-MM-DD HH24:MI:SS') AND TO_DATE(:5,'YYYY-MM-DD HH24:MI:SS')`,
		ins, intCode, status, oraTime(from), oraTime(to))
	if err != nil {
		return nil, err
	}
	defer rows.Close()
	return bindAmexWork(rows)
}

func (s *oracleStore) FindAmexWorkLessThanEqual(ctx context.Context, ins, intCode, status int, to time.Time) ([]*AmexAcqTxnWorkEntity, error) {
	rows, err := s.db.QueryContext(ctx, `
		SELECT * FROM AMEX_ACQ_TXN_WORK
		WHERE ATD_INS_CODE = :1 AND ATD_INT_CODE = :2 AND ATD_GEN_STATUS = :3
		  AND ATD_LOCAL_DATE_TIME <= TO_DATE(:4,'YYYY-MM-DD HH24:MI:SS')`,
		ins, intCode, status, oraTime(to))
	if err != nil {
		return nil, err
	}
	defer rows.Close()
	return bindAmexWork(rows)
}

func (s *oracleStore) FindAmexWorkByStatus(ctx context.Context, ins, status int) ([]*AmexAcqTxnWorkEntity, error) {
	rows, err := s.db.QueryContext(ctx, `
		SELECT * FROM AMEX_ACQ_TXN_WORK
		WHERE ATD_INS_CODE = :1 AND ATD_GEN_STATUS = :2`,
		ins, status)
	if err != nil {
		return nil, err
	}
	defer rows.Close()
	return bindAmexWork(rows)
}

func bindAmexWork(rows *sql.Rows) ([]*AmexAcqTxnWorkEntity, error) {
	maps, err := rowsToMaps(rows)
	if err != nil {
		return nil, err
	}
	out := make([]*AmexAcqTxnWorkEntity, 0, len(maps))
	for _, m := range maps {
		e := &AmexAcqTxnWorkEntity{}
		bindRow(m, e)
		out = append(out, e)
	}
	return out, nil
}

// UpdateAmexWorkStatuses mirrors the other networks' work status update:
// lastUpdated, updatedUser, generalStatus and fileID.
func (s *oracleStore) UpdateAmexWorkStatuses(ctx context.Context, ents []*AmexAcqTxnWorkEntity) error {
	for _, e := range ents {
		if _, err := s.db.ExecContext(ctx, `
			UPDATE AMEX_ACQ_TXN_WORK SET
			  ATD_LAST_UPDATED = :1,
			  ATD_UPDATED_USER = :2,
			  ATD_GEN_STATUS = :3,
			  ATD_FILE_ID    = :4
			WHERE ATD_SER_NUMBER = :5`,
			e.LastUpdated, e.UpdatedUser, e.GenStatus, nullStr(e.FileId), e.SerialNumber); err != nil {
			return err
		}
	}
	return nil
}

func (s *oracleStore) DeleteAmexWork(ctx context.Context, ents []*AmexAcqTxnWorkEntity) error {
	for _, e := range ents {
		if _, err := s.db.ExecContext(ctx, `DELETE FROM AMEX_ACQ_TXN_WORK WHERE ATD_SER_NUMBER = :1`, e.SerialNumber); err != nil {
			return err
		}
	}
	return nil
}

const amexColumns = `
	ATD_SER_NUMBER, ATD_LAST_UPDATED, ATD_UPDATED_USER, ATD_INS_CODE, ATD_INT_CODE,
	ATD_PRJ_SER_NUMBER, ATD_TXN_REF_NUMBER, ATD_TXN_TYPE, ATD_CARD_NUMBER, ATD_PROC_CODE,
	ATD_TXN_AMOUNT, ATD_SCHG_AMOUNT, ATD_LOCAL_DATE_TIME, ATD_POS_DATA_CODE, ATD_MCC,
	ATD_RET_REF_NUMBER, ATD_APPR_CODE, ATD_TERMINAL_ID, ATD_MERCHANT_ID, ATD_MAPPED_MID,
	ATD_ME_NAME, ATD_ME_CITY, ATD_ME_ZIP_CODE, ATD_ME_COUNTRY, ATD_ECOM_INDICATOR,
	ATD_TXN_CUR_CODE, ATD_CARD_SEQ_NUMBER, ATD_APP_CRYPTOGRAM, ATD_CRYPT_INFO_DATA,
	ATD_ISS_APP_DATA, ATD_UPBL_NUMBER, ATD_APP_TXN_COUNTER, ATD_TRL_VER_RESULTS,
	ATD_TXN_DATE, ATD_CRYPT_AMOUNT, ATD_APP_IC_PROFILE, ATD_TRL_CON_CODE,
	ATD_CASHBACK_AMOUNT, ATD_TXN_ID, ATD_TRL_BTH_NUMBER, ATD_CARD_TYPE,
	ATD_DOM_INTL_FLAG, ATD_SMS_DMS_FLAG, ATD_TRL_TYPE, ATD_CENTRE_PROC_DATE,
	ATD_OUT_FILE_DATE, ATD_FILE_ID, ATD_GEN_STATUS, ATD_ENC_CARD_NUMBER,
	ATD_EXPIRY_DATE, ATD_EMV, ATD_LOCATION_ADDRESS, ATD_ME_CONTACT_EMAIL,
	ATD_TRL_LOCATION, ATD_LOC_REG_CODE, ATD_STAN, ATD_INVOICE_NUMBER`

func amexDataArgs(e *AmexAcqTxnWorkEntity) []any {
	return []any{
		e.SerialNumber, e.LastUpdated, e.UpdatedUser, e.InstitutionCode, e.IntCode,
		e.PrjSerNumber, e.TxnRefSerNumber, nullStr(e.TxnType), nullStr(e.CardNumber), nullStr(e.ProcCode),
		e.TxnAmount, e.SurchargeAmount, nullTimeP(e.LocalDateTime), nullStr(e.PosDataCode), nullStr(e.Mcc),
		nullStr(e.Rrn), nullStr(e.ApprovalCode), nullStr(e.TerminalId), nullStr(e.MerchantId), nullStr(e.MappedMid),
		nullStr(e.MeName), nullStr(e.MeCity), nullStr(e.MePinCode), nullStr(e.MeCountry), nullStr(e.MotoEcomIndicator),
		nullStr(e.TxnCurCode), nullStr(e.CardSeqNumber), nullStr(e.AppCryptogram), nullStr(e.CryptInfoData),
		nullStr(e.IssAppData), nullStr(e.UpblNumber), nullStr(e.AppTxnCounter), nullStr(e.TrlVerResult),
		nullTimeP(e.TxnDate), e.CryptAmount, nullStr(e.AppICProfile), nullStr(e.TrlConCode),
		e.ChipCashBack, nullStr(e.TxnId), e.TrlBthNumber, nullStr(e.CardType),
		nullStr(e.CardDomIntlFlag), nullStr(e.DmsSmsMode), nullStr(e.TrlType), nullTimeP(e.CentreProcDate),
		nullTimeP(e.OutFileDate), nullStr(e.FileId), e.GenStatus, nullStr(e.EncryptedCardNumber),
		nullStr(e.ExpiryDate), nullStr(e.Emv), nullStr(e.LocationAddress), nullStr(e.MeContactEmail),
		nullStr(e.TrlLocation), nullStr(e.LocRegionCode), nullStr(e.Stan), nullStr(e.InvoiceNumber),
	}
}

// InsertAmexData mirrors moveWorkToData: the serial number is preserved.
func (s *oracleStore) InsertAmexData(ctx context.Context, ents []*AmexAcqTxnDataEntity) error {
	sqlStmt := "INSERT INTO AMEX_ACQ_TXN_DATA (" + amexColumns + ") VALUES (" + mercuryInsertValues(57) + ")"
	for _, e := range ents {
		if _, err := s.db.ExecContext(ctx, sqlStmt, amexDataArgs(e)...); err != nil {
			return err
		}
	}
	return nil
}

// CompleteAmexPosStatus mirrors the other networks' POS completion update.
// The work-table subquery (ATD_GEN_STATUS=4) scopes completion to AMEX
// transactions; PTR_NETWORK is empty for AMEX in POS_TRANSACTIONS, so it is
// not filtered (matching CompleteUnionPayPosStatus).
func (s *oracleStore) CompleteAmexPosStatus(ctx context.Context, ins int) error {
	_, err := s.db.ExecContext(ctx, `
		UPDATE POS_TRANSACTIONS pos SET
		  pos.PTR_GEN_STATUS = 6,
		  pos.PTR_OUT_STATUS = 'Completed'
		WHERE pos.PTR_RET_REF_NUMBER IN (
		  SELECT atd.ATD_RET_REF_NUMBER FROM AMEX_ACQ_TXN_WORK atd WHERE atd.ATD_GEN_STATUS = 4
		)
		AND pos.PTR_GEN_STATUS = 4
		AND pos.PTR_INS_CODE = :1`, ins)
	return err
}
