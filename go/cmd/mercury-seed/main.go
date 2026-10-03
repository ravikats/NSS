package main

import (
	"context"
	"database/sql"
	"fmt"
	"os"
	"time"

	_ "github.com/sijms/go-ora/v2"
)

type txn struct {
	rrn, merchantID, terminalID, txnType string
	amount, surcharge                    float64
	localDateTime                        time.Time
	txnDate                              time.Time
	chargeType, typeOfCharge, geoArea    string
	merchantName, merchantCity           string
	country, streetAddr, stateCode       string
	zipCode, phone, mcc                  string
	approvalCode, posEntryMode           string
	encryptedCard, responseCode          string
	cashback                             float64
}

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

func main() {
	dsn := os.Getenv("ORACLE_DSN")
	if dsn == "" {
		dsn = "oracle://NETWORK_SETTLEMENT_UAT:J6erQ%24o6E24@localhost:1521/FREEPDB1"
	}
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

	txns := []txn{
		{"RR00000001", "M00000000000009", "T0000001", "12", 10.00, 0, ts("2026-08-17 10:00:00"), td("2026-08-17"), "AA", "AA", "D", "ALI MANZ STORE", "DUBAI", "AE", "STREET 1", "DXB", "00000", "0501234567", "9211", "232068", "021", "tok_mer_001", "00", 0},
		{"RR00000002", "M00000000000009", "T0000002", "12", 951.00, 1.00, ts("2026-08-17 10:05:00"), td("2026-08-17"), "TF", "TF", "D", "DUBAI MALL STORE", "DUBAI", "AE", "MALL ROAD", "DXB", "00000", "0509876543", "5411", "962981", "051", "tok_mer_002", "00", 0},
		{"RR00000003", "M00000000000009", "T0000003", "12", 100.00, 0, ts("2026-08-17 10:10:00"), td("2026-08-17"), "83", "83", "D", "CARREFOUR MARKET", "SHARJAH", "AE", "MARKET STREET", "SHJ", "00000", "0501112233", "5311", "334455", "951", "tok_mer_003", "00", 0},
		{"RR00000004", "M00000000000009", "T0000004", "00", 250.00, 5.00, ts("2026-08-17 11:00:00"), td("2026-08-17"), "TF", "TF", "D", "EMIRATES MALL", "DUBAI", "AE", "SHEIKH ZAYED ROAD", "DXB", "00000", "0502223344", "5399", "778899", "051", "tok_mer_004", "00", 0},
		{"RR00000005", "M00000000000009", "T0000005", "12", 32.00, 0, ts("2026-08-17 11:30:00"), td("2026-08-17"), "18", "18", "D", "VAULT PAK", "ABU DHABI", "AE", "CORNISH RD", "AUH", "00000", "0503334455", "4722", "112233", "071", "tok_mer_005", "00", 0},
		{"RR00000006", "M00000000000009", "T0000006", "12", 45.50, 0, ts("2026-08-17 12:00:00"), td("2026-08-17"), "AA", "AA", "D", "SPINNEYS", "DUBAI", "AE", "AL WASL ROAD", "DXB", "00000", "0504445566", "5411", "445566", "021", "tok_mer_006", "00", 0},
		{"RR00000007", "M00000000000009", "T0000007", "00", 150.00, 2.50, ts("2026-08-17 12:15:00"), td("2026-08-17"), "TF", "TF", "D", "LULU HYPERMARKET", "AJMAN", "AE", "AL NUAIMIYAH", "AJM", "00000", "0505556677", "5311", "556677", "051", "tok_mer_007", "00", 0},
		{"RR00000008", "M00000000000009", "T0000008", "12", 20.00, 0, ts("2026-08-17 13:00:00"), td("2026-08-17"), "83", "83", "D", "PETROL STATION", "DUBAI", "AE", "AL KHAIL ROAD", "DXB", "00000", "0506667788", "5541", "667788", "951", "tok_mer_008", "00", 0},
		{"RR00000009", "M00000000000009", "T0000009", "12", 500.00, 10.00, ts("2026-08-17 13:30:00"), td("2026-08-17"), "TF", "TF", "D", "APPLE STORE", "DUBAI", "AE", "DUBAI MALL", "DXB", "00000", "0507778899", "5045", "778899", "051", "tok_mer_009", "00", 0},
		{"RR00000010", "M00000000000009", "T0000010", "00", 75.25, 0, ts("2026-08-17 14:00:00"), td("2026-08-17"), "AA", "AA", "D", "NOTHING BUNDT CAKES", "ABU DHABI", "AE", "YAS MALL", "AUH", "00000", "0508889900", "5812", "889900", "021", "tok_mer_010", "00", 0},
		{"RR00000011", "M00000000000009", "T0000011", "12", 1200.00, 15.00, ts("2026-08-17 14:15:00"), td("2026-08-17"), "TF", "TF", "D", "ROLEX BOUTIQUE", "DUBAI", "AE", "DUBAI MALL", "DXB", "00000", "0509990011", "5970", "990011", "051", "tok_mer_011", "00", 0},
		{"RR00000012", "M00000000000009", "T0000012", "12", 55.00, 0, ts("2026-08-17 15:00:00"), td("2026-08-17"), "AA", "AA", "D", "STARBUCKS", "SHARJAH", "AE", "SAHOOL AL QASIMIAH", "SHJ", "00000", "0501002003", "5812", "100200", "071", "tok_mer_012", "00", 0},
		{"RR00000013", "M00000000000009", "T0000013", "00", 320.00, 0, ts("2026-08-17 15:30:00"), td("2026-08-17"), "TF", "TF", "D", "ZARA FASHION", "DUBAI", "AE", "IBN BATTUTA MALL", "DXB", "00000", "0502003004", "5651", "200300", "051", "tok_mer_013", "00", 0},
		{"RR00000014", "M00000000000009", "T0000014", "12", 88.00, 0, ts("2026-08-17 16:00:00"), td("2026-08-17"), "AA", "AA", "D", "PAPA JOHNS", "DUBAI", "AE", "JUMEIRAH ST", "DXB", "00000", "0503004005", "5812", "300400", "021", "tok_mer_014", "00", 0},
		{"RR00000015", "M00000000000009", "T0000015", "12", 750.00, 8.00, ts("2026-08-17 16:30:00"), td("2026-08-17"), "TF", "TF", "D", "BLOOMINGDALES", "DUBAI", "AE", "DUBAI MALL", "DXB", "00000", "0504005006", "5300", "400500", "051", "tok_mer_015", "00", 0},
		{"RR00000016", "M00000000000009", "T0000016", "00", 15.75, 0, ts("2026-08-17 17:00:00"), td("2026-08-17"), "18", "18", "D", "SHAWARMA EXPRESS", "DUBAI", "AE", "KARAMA", "DXB", "00000", "0505006007", "5812", "500600", "021", "tok_mer_017", "00", 0},
		{"RR00000017", "M00000000000009", "T0000017", "12", 200.00, 0, ts("2026-08-17 17:15:00"), td("2026-08-17"), "TF", "TF", "D", "HOME CENTRE", "SHARJAH", "AE", "SAHARA CENTRE", "SHJ", "00000", "0506007008", "5719", "600700", "051", "tok_mer_018", "00", 0},
		{"RR00000018", "M00000000000009", "T0000018", "12", 35.00, 0, ts("2026-08-17 18:00:00"), td("2026-08-17"), "AA", "AA", "D", "CHOCHOLOUKA", "DUBAI", "AE", "MIRDIF CITY", "DXB", "00000", "0507008009", "5812", "700800", "071", "tok_mer_019", "00", 0},
		{"RR00000019", "M00000000000009", "T0000019", "00", 185.50, 3.00, ts("2026-08-17 18:30:00"), td("2026-08-17"), "TF", "TF", "D", "MASSIMO DUTTI", "ABU DHABI", "AE", "MARINA MALL", "AUH", "00000", "0508009010", "5651", "800900", "051", "tok_mer_020", "00", 0},
		{"RR00000020", "M00000000000009", "T0000020", "12", 420.00, 0, ts("2026-08-17 19:00:00"), td("2026-08-17"), "AA", "AA", "D", "ACE HARDWARE", "DUBAI", "AE", "AL BARSHA", "DXB", "00000", "0509010011", "5200", "901000", "021", "tok_mer_021", "00", 0},
		{"RR00000021", "M00000000000009", "T0000021", "12", 60.00, 0, ts("2026-08-17 19:30:00"), td("2026-08-17"), "TF", "TF", "D", "SALADIGILS", "DUBAI", "AE", "DIFC", "DXB", "00000", "0501011012", "5812", "101100", "051", "tok_mer_022", "00", 0},
		{"RR00000022", "M00000000000009", "T0000022", "00", 999.00, 0, ts("2026-08-17 20:00:00"), td("2026-08-17"), "TF", "TF", "D", "NORDSTROM", "DUBAI", "AE", "MALL OF THE EMIRATES", "DXB", "00000", "0501122334", "5651", "112200", "051", "tok_mer_023", "00", 0},
		{"RR00000023", "M00000000000009", "T0000023", "12", 25.00, 0, ts("2026-08-17 20:15:00"), td("2026-08-17"), "AA", "AA", "D", "SUBWAY", "DUBAI", "AE", "DEIRA", "DXB", "00000", "0502233445", "5812", "223300", "021", "tok_mer_024", "00", 0},
		{"RR00000024", "M00000000000009", "T0000024", "12", 275.00, 5.00, ts("2026-08-17 21:00:00"), td("2026-08-17"), "TF", "TF", "D", "IKEA", "DUBAI", "AE", "FESTIVAL CITY", "DXB", "00000", "0503344556", "5719", "334400", "051", "tok_mer_025", "00", 0},
		{"RR00000025", "M00000000000009", "T0000025", "00", 410.00, 0, ts("2026-08-17 21:30:00"), td("2026-08-17"), "TF", "TF", "D", "H AND M", "ABU DHABI", "AE", "AL WAHDA MALL", "AUH", "00000", "0504455667", "5651", "445500", "051", "tok_mer_026", "00", 0},
		{"RR00000026", "M00000000000009", "T0000026", "12", 50.00, 0, ts("2026-08-17 22:00:00"), td("2026-08-17"), "AA", "AA", "D", "JUMBO ELECTRONICS", "DUBAI", "AE", "AL RIGGA", "DXB", "00000", "0505566778", "5732", "556600", "021", "tok_mer_027", "00", 0},
	}

	for i, t := range txns {
		now := time.Now()
		// 73 positional bind params matching tlfsvc/store.go InsertMercuryWork
		_, err := db.ExecContext(ctx, insertSQL,
			now, 4, 1, 21, // :1-:4  last_updated, updated_user, ins_code, int_code
			int64(1), 3, int64(0), t.rrn, // :5-:8  prj_ser, gen_status, txn_ref, ret_ref
			t.merchantID, t.terminalID, t.txnType, "4761730000000000", // :9-:12 merchant, terminal, txn_type, card_number
			t.amount, t.surcharge, t.localDateTime, t.txnDate, // :13-:16 amounts, dates
			t.chargeType, t.typeOfCharge, t.geoArea, t.merchantName, // :17-:20
			t.merchantCity, t.country, t.streetAddr, t.stateCode, // :21-:24
			t.zipCode, t.phone, t.mcc, "", t.approvalCode, // :25-:29
			0, "", "", "", "", // :30-:34 cur_exp, cur_code, mercury_ref, dom_intl, sms_dms
			t.encryptedCard, "", "", // :35-:37 enc_card, org_inst, trl_type
			"", 0.0, "", t.responseCode, // :38-:41 setl_ind, fee, ecom, resp_code
			"", "", cardInputMode(t.posEntryMode), // :42-:44 acq_inst, acq_ref, card_input_mode
			"", "", "", // :45-:47 card_cap, card_seq, app_ic
			"", "", 0.0, t.cashback, // :48-:50 atc, acrypt, crypt_amt, cashback
			"", "", "", "", // :51-:54 crypt_info, cvm, ded_file, ifd
			"", "", "", "", // :55-:58 iss_app, iss_auth, trl_con, trl_ver
			"", "", "", // :59-:61 chip_cap, chip_type, trl_ver_result
			"", "", "", "", // :62-:65 chip_date, chip_type2, chip_cur, upbl
			"", "", "", "", // :66-:69 centre_proc, out_file, file_id, card_present
			"", "", t.posEntryMode) // :70-:73 ch_present, pan_seq, pos_entry
		if err != nil {
			fmt.Fprintf(os.Stderr, "insert %d: %v\n", i+1, err)
			os.Exit(1)
		}
	}
	fmt.Printf("Inserted %d transactions\n", len(txns))

	var count int
	db.QueryRowContext(ctx, "SELECT COUNT(*) FROM MERCURY_ACQ_TXN_WORK").Scan(&count)
	fmt.Printf("Verify count: %d\n", count)
}

func cardInputMode(posEntryMode string) string {
	v := posEntryMode
	if len(v) > 3 {
		v = v[:3]
	}
	switch v {
	case "051", "052":
		return "5"
	case "071", "072":
		return "U"
	case "801", "802":
		return "9"
	case "021", "022", "901", "902":
		return "2"
	case "012":
		return "1"
	default:
		return "1"
	}
}

func ts(s string) time.Time {
	t, _ := time.Parse("2006-01-02 15:04:05", s)
	return t
}

func td(s string) time.Time {
	t, _ := time.Parse("2006-01-02", s)
	return t
}
