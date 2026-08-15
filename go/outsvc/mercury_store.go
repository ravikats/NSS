package outsvc

import (
	"context"
	"database/sql"
	"fmt"
	"strings"
	"time"
)

// ---- MERCURY_ACQ_TXN_WORK / MERCURY_ACQ_TXN_DATA ----

func (s *oracleStore) CountMercuryWorkBetween(ctx context.Context, ins, status int, from, to time.Time) (int, error) {
	var n int
	err := s.db.QueryRowContext(ctx, `
		SELECT COUNT(*) FROM MERCURY_ACQ_TXN_WORK
		WHERE MAT_INS_CODE = :1 AND MAT_GEN_STATUS = :2 AND MAT_LOCAL_DATE_TIME BETWEEN :3 AND :4`,
		ins, status, from, to).Scan(&n)
	return n, err
}

func (s *oracleStore) CountMercuryWorkLessThanEqual(ctx context.Context, ins, status int, to time.Time) (int, error) {
	var n int
	err := s.db.QueryRowContext(ctx, `
		SELECT COUNT(*) FROM MERCURY_ACQ_TXN_WORK
		WHERE MAT_INS_CODE = :1 AND MAT_GEN_STATUS = :2 AND MAT_LOCAL_DATE_TIME <= :3`,
		ins, status, to).Scan(&n)
	return n, err
}

func (s *oracleStore) FindMercuryWorkBetween(ctx context.Context, ins, intCode, status int, from, to time.Time) ([]*MercuryAcqTxnWorkEntity, error) {
	rows, err := s.db.QueryContext(ctx, `
		SELECT * FROM MERCURY_ACQ_TXN_WORK
		WHERE MAT_INS_CODE = :1 AND MAT_INT_CODE = :2 AND MAT_GEN_STATUS = :3
		  AND MAT_LOCAL_DATE_TIME BETWEEN :4 AND :5`,
		ins, intCode, status, from, to)
	if err != nil {
		return nil, err
	}
	defer rows.Close()
	return bindMercuryWork(rows)
}

func (s *oracleStore) FindMercuryWorkLessThanEqual(ctx context.Context, ins, intCode, status int, to time.Time) ([]*MercuryAcqTxnWorkEntity, error) {
	rows, err := s.db.QueryContext(ctx, `
		SELECT * FROM MERCURY_ACQ_TXN_WORK
		WHERE MAT_INS_CODE = :1 AND MAT_INT_CODE = :2 AND MAT_GEN_STATUS = :3
		  AND MAT_LOCAL_DATE_TIME <= :4`,
		ins, intCode, status, to)
	if err != nil {
		return nil, err
	}
	defer rows.Close()
	return bindMercuryWork(rows)
}

// FindMercuryWorkByStatus mirrors
// MercuryAcqTxnWorkRepo.findByInstitutionCodeAndGeneralStatus (used by
// moveWorkToData with status 4 and by insertIntoOutgoingSummary with status 9).
func (s *oracleStore) FindMercuryWorkByStatus(ctx context.Context, ins, status int) ([]*MercuryAcqTxnWorkEntity, error) {
	rows, err := s.db.QueryContext(ctx, `
		SELECT * FROM MERCURY_ACQ_TXN_WORK
		WHERE MAT_INS_CODE = :1 AND MAT_GEN_STATUS = :2`,
		ins, status)
	if err != nil {
		return nil, err
	}
	defer rows.Close()
	return bindMercuryWork(rows)
}

func bindMercuryWork(rows *sql.Rows) ([]*MercuryAcqTxnWorkEntity, error) {
	maps, err := rowsToMaps(rows)
	if err != nil {
		return nil, err
	}
	out := make([]*MercuryAcqTxnWorkEntity, 0, len(maps))
	for _, m := range maps {
		e := &MercuryAcqTxnWorkEntity{}
		bindRow(m, e)
		out = append(out, e)
	}
	return out, nil
}

// UpdateMercuryWorkStatuses mirrors saveAll on the Mercury work repo: Java's
// updateMercuryEntity sets lastUpdated, updatedUser, generalStatus and fileID.
func (s *oracleStore) UpdateMercuryWorkStatuses(ctx context.Context, ents []*MercuryAcqTxnWorkEntity) error {
	for _, e := range ents {
		if _, err := s.db.ExecContext(ctx, `
			UPDATE MERCURY_ACQ_TXN_WORK SET
			  MAT_LAST_UPDATED = :1,
			  MAT_UPDATED_USER = :2,
			  MAT_GEN_STATUS = :3,
			  MAT_FILE_ID = :4
			WHERE MAT_SER_NUMBER = :5`,
			e.LastUpdated, e.UpdatedUser, e.GenStatus, nullStr(e.FileID), e.SerialNumber); err != nil {
			return err
		}
	}
	return nil
}

func (s *oracleStore) DeleteMercuryWork(ctx context.Context, ents []*MercuryAcqTxnWorkEntity) error {
	for _, e := range ents {
		if _, err := s.db.ExecContext(ctx, `DELETE FROM MERCURY_ACQ_TXN_WORK WHERE MAT_SER_NUMBER = :1`, e.SerialNumber); err != nil {
			return err
		}
	}
	return nil
}

// mercuryColumns is the shared column list for MERCURY_ACQ_TXN_DATA and
// MERCURY_ACQ_TXN_WORK inserts (moveWorkToData / revert copy the same field set).
const mercuryColumns = `
	MAT_SER_NUMBER, MAT_LAST_UPDATED, MAT_UPDATED_USER, MAT_INS_CODE, MAT_INT_CODE,
	MAT_PRJ_SER_NUMBER, MAT_GEN_STATUS, MAT_TXN_REF_NUMBER, MAT_RET_REF_NUMBER,
	MAT_MERCHANT_ID, MAT_TERMINAL_ID, MAT_TXN_TYPE, MAT_CARD_NUMBER, MAT_TXN_AMOUNT,
	MAT_SCHG_AMOUNT, MAT_LOCAL_DATE_TIME, MAT_TXN_DATE, MAT_CHARGE_TYPE, MAT_TYPE_OF_CHARGE,
	MAT_GEO_AREA, MAT_ME_NAME, MAT_ME_CITY, MAT_ME_COUNTRY, MAT_CARD_ACC_STREET_ADDRESS,
	MAT_CARD_ACC_STATE_CODE, MAT_ME_ZIP_CODE, MAT_EST_PHONE_NO, MAT_MCC, MAT_CARD_TYPE,
	MAT_APPR_CODE, MAT_TXN_CURR_EXP, MAT_TXN_CUR_CODE, MAT_MERCURY_REF_ID, MAT_DOM_INTL_FLAG,
	MAT_SMS_DMS_FLAG, MAT_ENC_CARD_NUMBER, MAT_ORG_INST_ID_CODE, MAT_TRL_TYPE,
	MAT_SETL_INDICATOR, MAT_TXN_FEE_AMOUNT, MAT_ECOM_INDICATOR, MAT_RESP_CODE,
	MAT_ACQ_INST_ID_CODE, MAT_ACQ_REF_DATA, MAT_CARD_INPUT_MODE, MAT_CARD_INPUT_CAPABILITY,
	MAT_CARD_SEQ_NUMBER, MAT_APP_IC_PROFILE, MAT_APP_TXN_COUNTER, MAT_APP_CRYPTOGRAM,
	MAT_CRYPT_AMOUNT, MAT_CASHBACK_AMOUNT, MAT_CRYPT_INFO_DATA, MAT_CVM_RESULTS,
	MAT_DEDICATED_FILE_NAME, MAT_IFD_SER_NUMBER, MAT_ISS_APP_DATA, MAT_ISS_AUTH_DATA,
	MAT_TRL_CON_CODE, MAT_TRL_APP_VER_NUMBER, MAT_CHIP_TRL_CAPABILITIES, MAT_CHIP_TRL_TYPE,
	MAT_TRL_VER_RESULTS, MAT_CHIP_TXN_DATE, MAT_CHIP_TXN_TYPE, MAT_CHIP_CUR_CODE,
	MAT_UPBL_NUMBER, MAT_CENTRE_PROC_DATE, MAT_OUT_FILE_DATE, MAT_FILE_ID, MAT_CARD_PRESENT,
	MAT_CH_PRESENT, MAT_APP_PAN_SEQ_NUMBER, MAT_POS_ENTRY_MODE`

// mercuryDataArgs maps a work entity to the mercuryColumns insert arguments.
func mercuryDataArgs(e *MercuryAcqTxnWorkEntity) []any {
	return []any{
		e.SerialNumber, e.LastUpdated, e.UpdatedUser, e.InstitutionCode, e.IntCode,
		e.PrjSerNumber, e.GenStatus, e.TxnRefNumber, nullStr(e.Rrn),
		nullStr(e.MerchantId), nullStr(e.TerminalId), nullStr(e.TxnType), nullStr(e.CardNumber), e.TxnAmount,
		e.SurchargeAmount, nullTimeP(e.LocalDateTime), nullTimeP(e.TxnDate), nullStr(e.ChargeType), nullStr(e.TypeOfCharge),
		nullStr(e.GeoArea), nullStr(e.MeName), nullStr(e.MeCity), nullStr(e.MeCountry), nullStr(e.CardAccepStreetAddress),
		nullStr(e.CardAccepStateCode), nullStr(e.MePinCode), nullStr(e.EstPhoneNumber), nullStr(e.Mcc), nullStr(e.CardType),
		nullStr(e.ApprovalCode), e.TxnCurrencyExponent, nullStr(e.TxnCurCode), nullStr(e.MercuryRefId), nullStr(e.CardDomIntlFlag),
		nullStr(e.DmsSmsMode), nullStr(e.EncryptedCardNumber), nullStr(e.OrgInstIdCode), nullStr(e.TrlType),
		nullStr(e.SettlementIndicator), e.TxnFeeAmount, nullStr(e.MotoEcomIndicator), nullStr(e.ResponseCode),
		nullStr(e.AcqinstIdCode), nullStr(e.AcqRefData), nullStr(e.CardInputMode), nullStr(e.CardInputCapability),
		nullStr(e.CardSeqNumber), nullStr(e.AppICProfile), nullStr(e.AppTxnCounter), nullStr(e.AppCryptogram),
		e.CryptAmount, e.CashBackAmount, nullStr(e.CryptInfoData), nullStr(e.CvmResult),
		nullStr(e.DedicatedFileName), nullStr(e.IfdSerNumber), nullStr(e.IssAppData), nullStr(e.IssAuthData),
		nullStr(e.TrlConCode), nullStr(e.TrlAppVerNumber), nullStr(e.ChipTrlCapabilities), nullStr(e.ChipTrlType),
		nullStr(e.TrlVerResult), nullStr(e.ChipTxnDate), nullStr(e.ChipTxnType), nullStr(e.ChipCurCode),
		nullStr(e.UpblNumber), nullTimeP(e.CentreProcDate), nullTimeP(e.FileProcDate), nullStr(e.FileID), nullStr(e.CardPresent),
		nullStr(e.ChPresent), nullStr(e.PanSequenceNumber), nullStr(e.PosEntryMode),
	}
}

// InsertMercuryData mirrors MercuryAcqTxnDataRepo.saveAll (moveWorkToData). The
// serial number is preserved.
func (s *oracleStore) InsertMercuryData(ctx context.Context, ents []*MercuryAcqTxnDataEntity) error {
	sqlStmt := "INSERT INTO MERCURY_ACQ_TXN_DATA (" + mercuryColumns + ") VALUES (" + mercuryInsertValues(74) + ")"
	for _, e := range ents {
		if _, err := s.db.ExecContext(ctx, sqlStmt, mercuryDataArgs(e)...); err != nil {
			return err
		}
	}
	return nil
}

func mercuryInsertValues(n int) string {
	parts := make([]string, n)
	for i := range parts {
		parts[i] = fmt.Sprintf(":%d", i+1)
	}
	return strings.Join(parts, ",")
}

// CompleteMercuryPosStatus mirrors PosTransactionRepo.completeMercuryPosStatus.
func (s *oracleStore) CompleteMercuryPosStatus(ctx context.Context, ins int) error {
	_, err := s.db.ExecContext(ctx, `
		UPDATE POS_TRANSACTIONS pos SET
		  pos.PTR_GEN_STATUS = 6,
		  pos.PTR_OUT_STATUS = 'Completed'
		WHERE pos.PTR_RET_REF_NUMBER IN (
		  SELECT mat.MAT_RET_REF_NUMBER FROM MERCURY_ACQ_TXN_WORK mat WHERE mat.MAT_GEN_STATUS = 4
		)
		AND pos.PTR_NETWORK IN ('MERCURY')
		AND pos.PTR_GEN_STATUS = 4
		AND pos.PTR_INS_CODE = :1`, ins)
	return err
}
