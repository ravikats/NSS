package tlfsvc

import (
	"context"
	"database/sql"
	"fmt"
	"time"

	"empay/irf/mpgsdcf"

	go_ora "github.com/sijms/go-ora/v2"
)

// Store is the persistence contract used by Service. The production
// implementation talks to Oracle (POS_TRANSACTIONS); tests supply a fake.
type Store interface {
	// Insert persists the core POS_TRANSACTIONS row via the shared DCF INSERT
	// (proven NOT NULL-safe) plus the TLF-only overlay UPDATE, in one transaction.
	Insert(ctx context.Context, e *Entity) (int, error)
	// UpdateIrf writes the IRF result columns. `cleared` => updateDefaultIrf shape.
	UpdateIrf(ctx context.Context, e *Entity, cleared bool) error
	// SetOutgoing writes the outgoing/incoming status columns.
	SetOutgoing(ctx context.Context, ser int, out, in string) error
	// SetReady flips an in-flight row (PTR_GEN_STATUS=9) to 3 (ready for the
	// settlement split) after stage 2 has written the IRF/outgoing columns.
	SetReady(ctx context.Context, ser int) error
	// FindPendingIRFRows returns serial numbers of rows still marked in-flight
	// (PTR_GEN_STATUS=9) and not updated since olderThan — the reaper's recovery
	// scan (rows whose stage 2 never completed).
	FindPendingIRFRows(ctx context.Context, olderThan time.Time) ([]int, error)
	// FindInterfaceCode resolves the interface code for a category (e.g. "MERCURY")
	// within an institution; returns 0 if not found.
	FindInterfaceCode(ctx context.Context, category string, insCode int) (int, error)
	// FindAcquirerBin returns the acquirer-bin reference row (ACQ_BIN, ACQ_MC_ICA_NO)
	// for the given bin type; ok=false when absent.
	FindAcquirerBin(ctx context.Context, insCode int, binType string) (*AcquirerBin, bool, error)
	// FindCurrencyExponent returns the CUR_EXPONENT for a currency code; ok=false
	// when absent.
	FindCurrencyExponent(ctx context.Context, code string) (int, bool, error)
	// InsertMercuryWork stages one row into MERCURY_ACQ_TXN_WORK and returns the
	// identity serial number.
	InsertMercuryWork(ctx context.Context, m *MercuryWorkEntity) (int, error)
	// FindBusinessDate returns the business date (BDT_BUSINESS_DATE) for an
	// institution; nil when no row exists.
	FindBusinessDate(ctx context.Context, insCode int) (*time.Time, error)
	// FindByMsgTypeIdAndRrnAndProcCode reports whether a POS_TRANSACTIONS row
	// already exists, mirroring PosTransactionRepository.findByMsgTypeIdAndRrnAndProcCode
	// used by the Kafka duplicate-RRN validation.
	FindByMsgTypeIdAndRrnAndProcCode(ctx context.Context, mti, rrn, procCode string) (bool, error)
	// InsertRejectedTxn persists a REJECTED_TRANSACTIONS row (mirrors
	// CommonManagementsService.insertRejectedTxns) and returns the serial number.
	InsertRejectedTxn(ctx context.Context, r *RejectedTxn) (int, error)
}

// AcquirerBin is the subset of ACQUIRER_BINS used by Mercury staging.
type AcquirerBin struct {
	AcqBin   string
	McIcaNum string
}

// RejectedTxn mirrors the REJECTED_TRANSACTIONS columns set by
// CommonManagementsService.insertRejectedTxns.
type RejectedTxn struct {
	LastUpdated time.Time
	User        int
	InsCode     int
	IntCode     int
	BussDate    *time.Time
	JobNumber   int
	FileName    string
	Rrn         string
	Amount      float64
	TxnDateTime *time.Time
	Mid         string
	Tid         string
	RejReason   string
}

// oracleStore is the production Store backed by Oracle.
type oracleStore struct {
	db *sql.DB
}

func (s *oracleStore) Insert(ctx context.Context, e *Entity) (int, error) {
	tx, err := s.db.BeginTx(ctx, nil)
	if err != nil {
		return 0, fmt.Errorf("tlfsvc: begin: %w", err)
	}
	defer tx.Rollback()

	if err := (&mpgsdcf.FileService{}).InsertEntity(ctx, tx, &e.Entity); err != nil {
		return 0, fmt.Errorf("tlfsvc: insert pos_transactions: %w", err)
	}
	if err := s.updateOverlay(ctx, tx, e); err != nil {
		return 0, fmt.Errorf("tlfsvc: update overlay: %w", err)
	}
	if err := tx.Commit(); err != nil {
		return 0, fmt.Errorf("tlfsvc: commit: %w", err)
	}
	return e.Entity.SerialNumber, nil
}

func (s *oracleStore) updateOverlay(ctx context.Context, tx *sql.Tx, e *Entity) error {
	_, err := tx.ExecContext(ctx, `
		UPDATE POS_TRANSACTIONS SET
		  PTR_SCHEME            = :1,
		  PTR_BANK_CODE         = :2,
		  PTR_REV_INDICATOR     = :3,
		  PTR_SETL_FLAG         = :4,
		  PTR_REIMB_ATTRIBUTE   = :5,
		  PTR_FEE_PRG_INDICATOR = :6,
		  PTR_CH_ID_METHOD      = :7,
		  PTR_PRODUCT_ID        = :8,
		  PTR_ACC_FUND_SOURCE   = :9,
		  PTR_MPOS_ACC_DEV_TYPE = :10
		WHERE PTR_SER_NUMBER = :11`,
		caseOrNull(e.Scheme), caseOrNull(e.BankCode), caseOrNull(e.RevIndiCator),
		caseOrNull(e.SettlementIndicator), caseOrNull(e.ReImbursementAttribute),
		caseOrNull(e.FeePgmIndicator), caseOrNull(e.ChIdMethod), caseOrNull(e.ProductId),
		caseOrNull(e.AccountFundingSource), caseOrNull(e.MposAccDevType),
		e.Entity.SerialNumber)
	return err
}

func (s *oracleStore) UpdateIrf(ctx context.Context, e *Entity, cleared bool) error {
	if cleared || (e.IrdSerNumber == nil && e.Ird == "" && e.NetAmount == 0 && e.CardDomIntlFlag == "") {
		const sqlStmt = `
			UPDATE POS_TRANSACTIONS SET
			  PTR_IRD_SER_NUMBER     = NULL,
			  PTR_IRD                = NULL,
			  PTR_IRF_FIXED          = 0,
			  PTR_IRF_PERCENT        = 0,
			  PTR_IRF_AMOUNT         = 0,
			  PTR_IRF_AMOUNT_USD     = 0,
			  PTR_CARD_TYPE          = NULL,
			  PTR_CARD_DOM_INTL_FLAG = ' ',
			  PTR_CARD_CATEGORY      = NULL,
			  PTR_REMARKS            = NULL,
			  PTR_IRF_MIN_AMOUNT     = 0,
			  PTR_IRF_MAX_AMOUNT     = 0,
			  PTR_LAST_UPDATED       = SYSDATE
			WHERE PTR_SER_NUMBER = :1`
		_, err := s.db.ExecContext(ctx, sqlStmt, e.Entity.SerialNumber)
		return err
	}
	var irdSer any = sql.NullInt64{}
	if e.IrdSerNumber != nil {
		irdSer = *e.IrdSerNumber
	}
	const sqlStmt = `
		UPDATE POS_TRANSACTIONS SET
		  PTR_IRD_SER_NUMBER     = :1,
		  PTR_IRD                = :2,
		  PTR_IRF_FIXED          = :3,
		  PTR_IRF_PERCENT        = :4,
		  PTR_IRF_AMOUNT         = :5,
		  PTR_IRF_AMOUNT_USD     = :6,
		  PTR_CARD_TYPE          = :7,
		  PTR_CARD_DOM_INTL_FLAG = :8,
		  PTR_CARD_CATEGORY      = :9,
		  PTR_REMARKS            = :10,
		  PTR_IRF_MIN_AMOUNT     = :11,
		  PTR_IRF_MAX_AMOUNT     = :12,
		  PTR_LAST_UPDATED       = SYSDATE
		WHERE PTR_SER_NUMBER = :13`
	_, err := s.db.ExecContext(ctx, sqlStmt,
		irdSer, e.Ird, e.IrfFixed, e.IrfPercent, e.IrfAmount, e.IrfAmountUSD,
		caseOrNull(e.CardType), e.CardDomIntlFlag, e.CardCategory, e.Remarks,
		e.IrfMinAmount, e.IrfMaxAmount, e.Entity.SerialNumber)
	return err
}

func (s *oracleStore) SetOutgoing(ctx context.Context, ser int, out, in string) error {
	_, err := s.db.ExecContext(ctx, `
		UPDATE POS_TRANSACTIONS SET
		  PTR_OUT_STATUS      = :1,
		  PTR_INC_STATUS = :2
		WHERE PTR_SER_NUMBER = :3`, out, in, ser)
	return err
}

func (s *oracleStore) SetReady(ctx context.Context, ser int) error {
	_, err := s.db.ExecContext(ctx, `
		UPDATE POS_TRANSACTIONS SET
		  PTR_GEN_STATUS = 3,
		  PTR_LAST_UPDATED = SYSDATE
		WHERE PTR_SER_NUMBER = :1`, ser)
	return err
}

func (s *oracleStore) FindPendingIRFRows(ctx context.Context, olderThan time.Time) ([]int, error) {
	rows, err := s.db.QueryContext(ctx, `
		SELECT PTR_SER_NUMBER FROM POS_TRANSACTIONS
		WHERE PTR_GEN_STATUS = 9 AND PTR_LAST_UPDATED < :1`, olderThan)
	if err != nil {
		return nil, fmt.Errorf("tlfsvc: find pending irf rows: %w", err)
	}
	defer rows.Close()
	var out []int
	for rows.Next() {
		var ser int
		if err := rows.Scan(&ser); err != nil {
			return nil, fmt.Errorf("tlfsvc: scan pending irf row: %w", err)
		}
		out = append(out, ser)
	}
	if err := rows.Err(); err != nil {
		return nil, fmt.Errorf("tlfsvc: scan pending irf rows: %w", err)
	}
	return out, nil
}

func (s *oracleStore) FindInterfaceCode(ctx context.Context, category string, insCode int) (int, error) {
	var code int
	err := s.db.QueryRowContext(ctx, `
		SELECT INT_CODE FROM INTERFACES
		WHERE INT_CATEGORY = :1 AND INT_INS_CODE = :2`, category, insCode).Scan(&code)
	if err == sql.ErrNoRows {
		return 0, nil
	}
	if err != nil {
		return 0, fmt.Errorf("tlfsvc: find interface code %q: %w", category, err)
	}
	return code, nil
}

func (s *oracleStore) FindAcquirerBin(ctx context.Context, insCode int, binType string) (*AcquirerBin, bool, error) {
	var bin, ica string
	err := s.db.QueryRowContext(ctx, `
		SELECT ACQ_BIN, ACQ_MC_ICA_NO FROM ACQUIRER_BINS
		WHERE ACQ_INS_CODE = :1 AND ACQ_BIN_TYPE = :2`, insCode, binType).Scan(&bin, &ica)
	if err == sql.ErrNoRows {
		return nil, false, nil
	}
	if err != nil {
		return nil, false, fmt.Errorf("tlfsvc: find acquirer bin %q: %w", binType, err)
	}
	return &AcquirerBin{AcqBin: bin, McIcaNum: ica}, true, nil
}

func (s *oracleStore) FindCurrencyExponent(ctx context.Context, code string) (int, bool, error) {
	var exp int
	err := s.db.QueryRowContext(ctx, `
		SELECT CUR_EXPONENT FROM CURRENCIES
		WHERE CUR_CODE = :1`, code).Scan(&exp)
	if err == sql.ErrNoRows {
		return 0, false, nil
	}
	if err != nil {
		return 0, false, fmt.Errorf("tlfsvc: find currency %q: %w", code, err)
	}
	return exp, true, nil
}

func (s *oracleStore) InsertMercuryWork(ctx context.Context, m *MercuryWorkEntity) (int, error) {
	const insertSQL = `INSERT INTO MERCURY_ACQ_TXN_WORK (
		MAT_LAST_UPDATED, MAT_UPDATED_USER, MAT_INS_CODE, MAT_INT_CODE,
		MAT_PRJ_SER_NUMBER, MAT_GEN_STATUS, MAT_TXN_REF_NUMBER, MAT_RET_REF_NUMBER,
		MAT_MERCHANT_ID, MAT_TERMINAL_ID, MAT_TXN_TYPE, MAT_CARD_NUMBER,
		MAT_TXN_AMOUNT, MAT_SCHG_AMOUNT, MAT_LOCAL_DATE_TIME, MAT_TXN_DATE,
		MAT_CHARGE_TYPE, MAT_TYPE_OF_CHARGE, MAT_GEO_AREA, MAT_ME_NAME,
		MAT_ME_CITY, MAT_ME_COUNTRY, MAT_CARD_ACC_STREET_ADDRESS, MAT_CARD_ACC_STATE_CODE,
		MAT_ME_ZIP_CODE, MAT_EST_PHONE_NO, MAT_MCC, MAT_CARD_TYPE, MAT_APPR_CODE,
		MAT_TXN_CURR_EXP, MAT_TXN_CUR_CODE, MAT_MERCURY_REF_ID, MAT_DOM_INTL_FLAG,
		MAT_SMS_DMS_FLAG, MAT_ENC_CARD_NUMBER, MAT_ORG_INST_ID_CODE, MAT_TRL_TYPE,
		MAT_SETL_INDICATOR, MAT_TXN_FEE_AMOUNT, MAT_ECOM_INDICATOR, MAT_RESP_CODE,
		MAT_ACQ_INST_ID_CODE, MAT_ACQ_REF_DATA, MAT_CARD_INPUT_MODE,
		MAT_CARD_INPUT_CAPABILITY, MAT_CARD_SEQ_NUMBER, MAT_APP_IC_PROFILE,
		MAT_APP_TXN_COUNTER, MAT_APP_CRYPTOGRAM, MAT_CRYPT_AMOUNT, MAT_CASHBACK_AMOUNT,
		MAT_CRYPT_INFO_DATA, MAT_CVM_RESULTS, MAT_DEDICATED_FILE_NAME, MAT_IFD_SER_NUMBER,
		MAT_ISS_APP_DATA, MAT_ISS_AUTH_DATA, MAT_TRL_CON_CODE, MAT_TRL_APP_VER_NUMBER,
		MAT_CHIP_TRL_CAPABILITIES, MAT_CHIP_TRL_TYPE, MAT_TRL_VER_RESULTS,
		MAT_CHIP_TXN_DATE, MAT_CHIP_TXN_TYPE, MAT_CHIP_CUR_CODE, MAT_UPBL_NUMBER,
		MAT_CENTRE_PROC_DATE, MAT_OUT_FILE_DATE, MAT_FILE_ID, MAT_CARD_PRESENT,
		MAT_CH_PRESENT, MAT_APP_PAN_SEQ_NUMBER, MAT_POS_ENTRY_MODE)
	VALUES (:1, :2, :3, :4, :5, :6, :7, :8, :9, :10, :11, :12, :13, :14, :15, :16,
		:17, :18, :19, :20, :21, :22, :23, :24, :25, :26, :27, :28, :29, :30, :31, :32,
		:33, :34, :35, :36, :37, :38, :39, :40, :41, :42, :43, :44, :45, :46, :47, :48,
		:49, :50, :51, :52, :53, :54, :55, :56, :57, :58, :59, :60, :61, :62, :63, :64,
		:65, :66, :67, :68, :69, :70, :71, :72, :73)
	RETURNING MAT_SER_NUMBER INTO :74`

	lastUpdated := m.LastUpdated
	if lastUpdated.IsZero() {
		lastUpdated = time.Now()
	}
	var ser int64
	_, err := s.db.ExecContext(ctx, insertSQL, lastUpdated, m.UpdatedUser, m.InstitutionCode, m.IntCode,
		nullInt(m.PrjSerNumber), m.GeneralStatus, nullInt(m.TxnRefNumber), nullStr(m.Rrn),
		nullStr(m.MerchantId), nullStr(m.TerminalId), nullStr(m.TxnType), nullStr(m.CardNumber),
		m.TxnAmount, m.SurchargeAmount, nullTime(m.LocalDateTime), nullTime(m.TxnDate),
		nullStr(m.ChargeType), nullStr(m.TypeOfCharge), nullStr(m.GeoArea), nullStr(m.MeName),
		nullStr(m.MeCity), nullStr(m.MeCountry), nullStr(m.CardAccepStreetAddress), nullStr(m.CardAccepStateCode),
		nullStr(m.MePinCode), nullStr(m.EstPhoneNumber), nullStr(m.Mcc), nullStr(m.CardType), nullStr(m.ApprovalCode),
		nullInt(m.TxnCurrencyExponent), nullStr(m.TxnCurCode), nullStr(m.MercuryRefId), nullStr(m.CardDomIntlFlag),
		nullStr(m.DmsSmsMode), nullStr(m.EncryptedCardNumber), nullStr(m.OrgInstIdCode), nullStr(m.TrlType),
		nullStr(m.SettlementIndicator), m.TxnFeeAmount, nullStr(m.MotoEcomIndicator), nullStr(m.ResponseCode),
		nullStr(m.AcqinstIdCode), nullStr(m.AcqRefData), nullStr(m.CardInputMode),
		nullStr(m.CardInputCapability), nullStr(m.CardSeqNumber), nullStr(m.AppICProfile),
		nullStr(m.AppTxnCounter), nullStr(m.AppCryptogram), m.CryptAmount, nullFloatP(m.CashBackAmount),
		nullStr(m.CryptInfoData), nullStr(m.CvmResult), nullStr(m.DedicatedFileName), nullStr(m.IfdSerNumber),
		nullStr(m.IssAppData), nullStr(m.IssAuthData), nullStr(m.TrlConCode), nullStr(m.TrlAppVerNumber),
		nullStr(m.ChipTrlCapabilities), nullStr(m.ChipTrlType), nullStr(m.TrlVerResult),
		nullStr(m.ChipTxnDate), nullStr(m.ChipTxnType), nullStr(m.ChipCurCode), nullStr(m.UpblNumber),
		nullTime(m.CentreProcDate), nullTime(m.FileProcDate), nullStr(m.FileID), nullStr(m.CardPresent),
		nullStr(m.ChPresent), nullStr(m.PanSequenceNumber), nullStr(m.PosEntryMode),
		go_ora.Out{Dest: &ser})
	if err != nil {
		return 0, fmt.Errorf("tlfsvc: insert mercury_work: %w", err)
	}
	return int(ser), nil
}

func nullInt(v int) any {
	if v == 0 {
		return nil
	}
	return v
}

func nullStr(v string) any {
	if v == "" {
		return nil
	}
	return v
}

func nullTime(v *time.Time) any {
	if v == nil {
		return nil
	}
	return *v
}

func nullFloatP(v *float64) any {
	if v == nil {
		return nil
	}
	return *v
}

func caseOrNull(v string) any {
	if v == "" {
		return sql.NullString{}
	}
	return v
}

func (s *oracleStore) FindBusinessDate(ctx context.Context, insCode int) (*time.Time, error) {
	var d time.Time
	err := s.db.QueryRowContext(ctx, `
		SELECT BDT_BUSINESS_DATE FROM BUSINESS_DATE
		WHERE BDT_INS_CODE = :1`, insCode).Scan(&d)
	if err == sql.ErrNoRows {
		return nil, nil
	}
	if err != nil {
		return nil, fmt.Errorf("tlfsvc: find business date: %w", err)
	}
	return &d, nil
}

func (s *oracleStore) FindByMsgTypeIdAndRrnAndProcCode(ctx context.Context, mti, rrn, procCode string) (bool, error) {
	var n int
	err := s.db.QueryRowContext(ctx, `
		SELECT COUNT(*) FROM POS_TRANSACTIONS
		WHERE PTR_MSG_TYPE_ID = :1 AND PTR_RET_REF_NUMBER = :2 AND PTR_PROC_CODE = :3`,
		mti, rrn, procCode).Scan(&n)
	if err != nil {
		return false, fmt.Errorf("tlfsvc: duplicate rrn check: %w", err)
	}
	return n > 0, nil
}

func (s *oracleStore) InsertRejectedTxn(ctx context.Context, r *RejectedTxn) (int, error) {
	const insertSQL = `INSERT INTO REJECTED_TRANSACTIONS (
		RTR_LAST_UPDATED, RTR_UPDATED_USER, RTR_INS_CODE, RTR_INT_CODE,
		RTR_PRJ_SER_NUMBER, RTR_FILE_NAME, RTR_RET_REF_NUMBER, RTR_AMOUNT,
		RTR_TXN_DATE_TIME, RTR_MID, RTR_TID, RTR_REJECT_REASON, RTR_BUSS_DATE)
	VALUES (:1, :2, :3, :4, :5, :6, :7, :8, :9, :10, :11, :12, :13)
	RETURNING RTR_SER_NUMBER INTO :14`

	lastUpdated := r.LastUpdated
	if lastUpdated.IsZero() {
		lastUpdated = time.Now()
	}
	var ser int64
	_, err := s.db.ExecContext(ctx, insertSQL,
		lastUpdated, r.User, r.InsCode, r.IntCode, nullInt(r.JobNumber),
		nullStr(r.FileName), nullStr(r.Rrn), r.Amount,
		nullTime(r.TxnDateTime), nullStr(r.Mid), nullStr(r.Tid),
		nullStr(r.RejReason), nullTime(r.BussDate),
		go_ora.Out{Dest: &ser})
	if err != nil {
		return 0, fmt.Errorf("tlfsvc: insert rejected_transactions: %w", err)
	}
	return int(ser), nil
}
