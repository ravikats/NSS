package main

import (
	"context"
	"database/sql"
	"encoding/json"
	"fmt"
	"os"
	"time"

	"empay/irf/tlfsvc"

	_ "github.com/sijms/go-ora/v2"
)

func main() {
	dsn := os.Getenv("ORACLE_DSN")
	if dsn == "" {
		dsn = "oracle://NETWORK_SETTLEMENT_UAT:J6erQ%24o6E24@localhost:1521/FREEPDB1"
	}
	payloadPath := "/tmp/opencode/payload.json"
	if len(os.Args) > 1 {
		payloadPath = os.Args[1]
	}

	data, err := os.ReadFile(payloadPath)
	if err != nil {
		fmt.Fprintln(os.Stderr, "read payload:", err)
		os.Exit(1)
	}

	var payloads []tlfsvc.SwitchExtractVo
	if err := json.Unmarshal(data, &payloads); err != nil {
		fmt.Fprintln(os.Stderr, "parse payload:", err)
		os.Exit(1)
	}
	fmt.Printf("Loaded %d payloads\n", len(payloads))

	db, err := sql.Open("oracle", dsn)
	if err != nil {
		fmt.Fprintln(os.Stderr, "open:", err)
		os.Exit(1)
	}
	defer db.Close()
	if err := db.Ping(); err != nil {
		fmt.Fprintln(os.Stderr, "ping:", err)
		os.Exit(1)
	}
	fmt.Println("Connected OK")

	ctx := context.Background()

	for _, q := range []string{
		"DELETE FROM OUT_FILE_LOG WHERE OFL_INT_CODE = 21",
		"DELETE FROM MERCURY_ACQ_TXN_DATA",
		"DELETE FROM MERCURY_ACQ_TXN_WORK",
		"UPDATE ACQUIRER_BINS SET ACQ_OUT_FILE_SEQ = 1, ACQ_OUT_FILE_DATE = NULL WHERE ACQ_BIN = '970962'",
	} {
		if _, err := db.ExecContext(ctx, q); err != nil {
			fmt.Fprintf(os.Stderr, "cleanup: %v\n", err)
			os.Exit(1)
		}
	}
	fmt.Println("Cleanup done")

	now := time.Now()
	cfg := tlfsvc.Config{
		InsCode:       1,
		IntCode:       21,
		UserSerNumber: 4,
		JobNumber:     1,
		ExchangeRate:  0.27,
		Now:           func() time.Time { return now },
		KafkaIntCode:  21,
		StageMercury:  true,
	}

	for i, p := range payloads {
		entity := tlfsvc.MapToEntity(&p, cfg, nil)

		mParams := tlfsvc.MercurySplitParams{
			Entity:           entity,
			Payload:          &p,
			UserSerialNumber: 4,
			InsCode:          1,
			IntCode:          21,
			JobNumber:        1,
			CurrencyExponent: 2,
			McIcaNum:         "034540",
			AcqBin:           "970962",
			Now:              now,
		}
		m := tlfsvc.MapToMercuryAcqTxnEntity(mParams)

		_, err := insertMercuryWork(ctx, db, m)
		if err != nil {
			fmt.Fprintf(os.Stderr, "insert %d (rrn=%s): %v\n", i+1, p.RetRefNumber, err)
			os.Exit(1)
		}
	}
	fmt.Printf("Inserted %d transactions\n", len(payloads))

	var count int
	db.QueryRowContext(ctx, "SELECT COUNT(*) FROM MERCURY_ACQ_TXN_WORK").Scan(&count)
	fmt.Printf("Verify count: %d\n", count)
}

func insertMercuryWork(ctx context.Context, db *sql.DB, m *tlfsvc.MercuryWorkEntity) (int, error) {
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
	VALUES (
		:1, :2, :3, :4, :5, :6, :7, :8, :9, :10, :11, :12, :13, :14, :15, :16,
		:17, :18, :19, :20, :21, :22, :23, :24, :25, :26, :27, :28, :29, :30, :31, :32,
		:33, :34, :35, :36, :37, :38, :39, :40, :41, :42, :43, :44, :45, :46, :47, :48,
		:49, :50, :51, :52, :53, :54, :55, :56, :57, :58, :59, :60, :61, :62, :63, :64,
		:65, :66, :67, :68, :69, :70, :71, :72, :73)`

	lastUpdated := m.LastUpdated
	if lastUpdated.IsZero() {
		lastUpdated = time.Now()
	}
	_, err := db.ExecContext(ctx, insertSQL,
		lastUpdated, m.UpdatedUser, m.InstitutionCode, m.IntCode,
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
	)
	if err != nil {
		return 0, fmt.Errorf("insert mercury_work: %w", err)
	}
	return 0, nil
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
