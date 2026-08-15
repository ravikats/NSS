package mpgsdcf

import (
	"context"
	"database/sql"
	"fmt"
	"time"

	go_ora "github.com/sijms/go-ora/v2"
)

// InsertEntity persists one POS_TRANSACTIONS row, mirroring the Java entity's
// saveAndFlush. Only the columns the processor may set are bound; NULL is
// written for unset optional values. The identity serial number is returned.
//
// Exported so other services sharing the POS_TRANSACTIONS table (e.g. the Go
// TLF service) can reuse the exact column/bind set proven by the DCF loader.
func (s *FileService) InsertEntity(ctx context.Context, tx *sql.Tx, e *Entity) error {
	const insertSQL = `INSERT INTO POS_TRANSACTIONS (
		PTR_INS_CODE, PTR_LAST_UPDATED, PTR_UPDATED_USER, PTR_INT_CODE,
		PTR_PRJ_SER_NUMBER, PTR_GEN_STATUS, PTR_OUT_STATUS, PTR_INC_STATUS,
		PTR_PROC_CODE, PTR_MSG_TYPE_ID, PTR_MERCHANT_ID, PTR_TERMINAL_ID,
		PTR_TXN_AMOUNT, PTR_STAN, PTR_RET_REF_NUMBER, PTR_RESP_CODE,
		PTR_POS_ENTRY_MODE, PTR_LOCAL_DATE_TIME, PTR_TXN_DATE_TIME,
		PTR_APPR_CODE, PTR_SETL_DATE, PTR_TXN_CUR_CODE, PTR_SETL_CUR_CODE,
		PTR_TXN_TYPE, PTR_TXN_CODE, PTR_CARD_NUMBER, PTR_SETL_AMOUNT,
		PTR_MCC, PTR_EXPIRY_DATE, PTR_MOTO_ECOM_INDICATOR, PTR_TRL_TYPE,
		PTR_NETWORK_DATA, PTR_POS_CONDITION_CODE, PTR_CARD_INPUT_ABILITY,
		PTR_CH_AUTH_ABILITY, PTR_CARD_CAPTURE_ABILITY, PTR_OPRT_ENVIRONMENT,
		PTR_CH_PRESENT, PTR_CARD_PRESENT, PTR_CARD_INPUT_MODE, PTR_CH_AUTH_METHOD,
		PTR_ONUS_OFFUS_FLAG, PTR_ME_NAME, PTR_ME_CITY, PTR_ME_COUNTRY,
		PTR_CARD_ACC_STATE_CODE, PTR_ME_PIN_CODE, PTR_TXN_FEE_AMOUNT,
		PTR_CASHBACK_AMOUNT, PTR_TXN_ID, PTR_ME_CATEGORY_TYPE, PTR_NETWORK,
		PTR_SERVICE_CODE, PTR_TXN_UNIQUE_ID, PTR_MAID, PTR_DMS_SMS_MODE,
		PTR_ME_COUNTRY_OF_ORIGIN, PTR_VALIDATION_CODE, PTR_MARKET_SPEC_DATA_IND,
		PTR_SPEND_QUALI_IND, PTR_MVV, PTR_AUTH_CHAR_INDICATOR, PTR_CARD_SEQ_NUMBER,
		PTR_APP_CRYPTOGRAM, PTR_CRYPT_INFO_DATA, PTR_ISS_APP_DATA, PTR_UPBL_NUMBER,
		PTR_APP_TXN_COUNTER, PTR_TRL_VER_RESULTS, PTR_CHIP_TXN_DATE,
		PTR_CHIP_TXN_TYPE, PTR_CRYPT_AMOUNT, PTR_AUTH_AMOUNT, PTR_CHIP_CUR_CODE,
		PTR_APP_IC_PROFILE, PTR_TRL_CON_CODE, PTR_CHIP_CASHBACK, PTR_CVM_RESULTS,
		PTR_TRL_CAPABILITIES, PTR_CHIP_TRL_CAPABILITIES, PTR_IFD_SER_NUMBER,
		PTR_TCC, PTR_TRL_APP_VER_NUMBER, PTR_ISS_AUTH_DATA,
		PTR_MER_CONTACT_PHONE_NO, PTR_ACC_URL_ADDRESS)
	VALUES (:1, :2, :3, :4, :5, :6, :7, :8, :9, :10, :11, :12, :13, :14, :15, :16,
		:17, :18, :19, :20, :21, :22, :23, :24, :25, :26, :27, :28, :29, :30,
		:31, :32, :33, :34, :35, :36, :37, :38, :39, :40, :41, :42, :43, :44,
		:45, :46, :47, :48, :49, :50, :51, :52, :53, :54, :55, :56, :57, :58,
		:59, :60, :61, :62, :63, :64, :65, :66, :67, :68, :69, :70, :71, :72,
		:73, :74, :75, :76, :77, :78, :79, :80, :81, :82, :83, :84, :85, :86)
	RETURNING PTR_SER_NUMBER INTO :87`

	var ser int64
	args := bindArgs(e, &ser)
	_, err := tx.ExecContext(ctx, insertSQL, args...)
	if err != nil {
		return fmt.Errorf("mpgsdcf: insert pos_transactions: %w", err)
	}
	e.SerialNumber = int(ser)
	return nil
}

// bindArgs flattens the entity into 87 bind values (86 columns + 1 RETURNING).
// It is a free function (no receiver state) so sibling service packages can reuse
// it via FileService.InsertEntity.
// Empty strings and zero amounts map to NULL for the optional columns.
func bindArgs(e *Entity, ser *int64) []any {
	lastUpdated := e.LastUpdated
	if lastUpdated.IsZero() {
		lastUpdated = time.Now()
	}
	return []any{
		// 1-8 job/gen/status
		e.InsCode, lastUpdated, e.User, e.IntCode, e.JobNumber, e.GenStatus,
		nullStr(e.OutStatus), nullStr(e.IncomingStatus),
		// 9-16
		nullStr(e.ProcCode), nullStr(e.MsgTypeId), nullStr(e.MerchantId), nullStr(e.TerminalId),
		e.TxnAmount, nullStr(e.Stan), nullStr(e.Rrn), nullStr(e.ResponseCode),
		// 17-25
		nullStr(e.PosEntryMode), nullTime(e.LocalDateTime), nullTime(e.TxnDateTime),
		nullStrP(e.ApprovalCode), nullTime(e.SetlDate), nullStr(e.TxnCurCode), nullStr(e.SetlCurCode),
		nullStr(e.TxnType), nullStr(e.TxnCode),
		// 26-33
		nullStr(e.CardNumber), e.SetlAmount, nullStr(e.Mcc), nullStr(e.ExpiryDate),
		nullStr(e.MotoEcomIndicator), nullStr(e.TerminalType), nullStr(e.NetworkData),
		nullStr(e.PosConditionCode),
		// 34-42
		nullStr(e.CardInputAbility), nullStr(e.ChAuthAbility), nullStr(e.CardCaptureAbility),
		nullStr(e.OprtEnvironment), nullStr(e.ChPresent), nullStr(e.CardPresent),
		nullStr(e.CardInputMode), nullStr(e.ChAuthMethod), nullStr(e.OnusOffusFlag),
		// 43-50
		nullStr(e.MeName), nullStr(e.MeCity), nullStr(e.MeCountry), nullStr(e.CardAccStateCode),
		nullStr(e.MePinCode), e.TxnFeeAmount, nullFloatP(e.CashBackAmount), nullStr(e.TxnId),
		// 51-58
		nullStr(e.MeCategoryType), nullStr(e.Network), nullStr(e.ServiceCode),
		nullStr(e.TxnUniqueId), nullStr(e.Maid), nullStr(e.DmsSmsMode),
		nullStr(e.MeCountryOfOrigin), nullStr(e.ValidationCode),
		// 59-66
		nullStr(e.MarketSpecAuthDataInd), nullStr(e.SpendQualificationInd), nullStr(e.Mvv),
		nullStr(e.AuthCharecteresticId), nullStr(e.CardSeqNumber), nullStr(e.AppCryptogram),
		nullStr(e.CryptInfoData), nullStr(e.IssAppData),
		// 67-75
		nullStr(e.UpblNumber), nullStr(e.AppTxnCounter), nullStr(e.TrlVerResult),
		nullStrP(e.ChipTxnDate), nullStr(e.ChipTxnType), e.CryptAmount, e.AuthAmount,
		nullStr(e.ChipCurCode), nullStr(e.AppICProfile),
		// 76-86
		nullStr(e.TrlConCode), e.ChipCashBack, nullStr(e.CvmResult), nullStr(e.TrlCapabilities),
		nullStr(e.ChipTrlCapabilities), nullStr(e.IfdSerNumber), nullStr(e.Tcc),
		nullStr(e.TrlAppVerNumber), nullStr(e.IssAuthData), nullStr(e.MerchantContactInfo),
		nullStr(e.AccepterUrlAddress),
		// 87 RETURNING
		go_ora.Out{Dest: ser},
	}
}

// nullStr maps an empty string to NULL (Java null), else the string itself.
func nullStr(s string) any {
	if s == "" {
		return nil
	}
	return s
}

func nullStrP(s *string) any {
	if s == nil {
		return nil
	}
	return *s
}

func nullTime(t *time.Time) any {
	if t == nil {
		return nil
	}
	return *t
}

func nullFloatP(f *float64) any {
	if f == nil {
		return nil
	}
	return *f
}
