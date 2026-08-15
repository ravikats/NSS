package main

import (
	"context"
	"database/sql"
	"fmt"
	"os"
	"time"

	_ "github.com/sijms/go-ora/v2"
)

var exclude = map[string]bool{
	"POS_TRANSACTIONS": true, "REJECTED_TRANSACTIONS": true, "IRF_CALLBACK": true,
	"OUTGOING_SUMMARY": true, "OUT_FILE_LOG": true, "UAE_REFUND_TXN": true,
	"VISA_SETTLEMENTS": true, "API_UPDATE_BACK": true, "GG_TMP_RET": true,
	"FILE_UPLOAD_LOG": true, "PROCESSING_JOBS": true, "PROCESSING_LOG": true,
	"REPORT_GENERATION_LOG": true, "OUTGOING_REPORT_DATA_WORK": true,
	"BATCH_JOB_EXECUTION": true, "BATCH_JOB_EXECUTION_CONTEXT": true,
	"BATCH_JOB_EXECUTION_PARAMS": true, "BATCH_JOB_INSTANCE": true,
	"BATCH_STEP_EXECUTION": true, "BATCH_STEP_EXECUTION_CONTEXT": true,
	"CBO_TRANSACTIONS_DATA": true, "TPS_TRANSACTIONS_DATA": true, "MC_RTS_TXN_DATA": true,
	"MC_REPR_GENERATED": true, "MC_GCO_DATA": true, "MC_GCO_WORK": true,
	"VISA_GOC_DATA": true, "VISA_GOC_WORK": true, "MC_ISS_WORK": true, "VISA_ISS_WORK": true,
	"MC_ACQ_TXN_HIST": true, "VISA_ACQ_TXN_HIST": true,
	"AMEX_ACQ_TXN_WORK": true, "AMEX_ACQ_TXN_DATA": true,
	"JAYWAN_ACQ_TXN_WORK": true, "JAYWAN_ACQ_TXN_DATA": true,
	"MC_ACQ_TXN_WORK": true, "MC_ACQ_TXN_DATA": true,
	"VISA_ACQ_TXN_WORK": true, "VISA_ACQ_TXN_DATA": true,
	"IPM_OUT_WORK": true, "IPM_OUT_WORK_060126_BK": true, "IPM_OUT_WORK_100726_BK": true,
	"MC_IRF_PARAMS_BKP_12172025": true, "MC_OVERRIDE_RATES_BKP": true,
	"MC_OVERRIDE_RATES_JULY": true, "VISA_IRF_PROGRAMS_JULY": true,
	"VISA_ADJUSTMENTS": true, "VISA_CB_RECEIVED": true, "VISA_DISPUTE_FINANCE_DATA": true,
	"VISA_EXCEPTION_TRACKING": true, "VISA_FEE_RECEIVED": true, "VISA_PR_RECEIVED": true,
	"VISA_REJECTIONS": true, "VISA_RR_RECEIVED": true,
	"MC_EXCEPTION_TRACKING": true, "MC_IPM_FEES": true, "MC_SETL_POS_DETAILS": true,
	"UAE_SWITCH_EXCEPTIONS": true, "JAYWAN_SWITCH_EXCEPTIONS": true, "JAYWAN_NETWORK_DATA": true,
	"CURRENCY_RATES": true, "ERROR_CODES": true, "FILE_LISTS": true,
	"MC_FILE_CURR_SUMMARY": true, "MC_FIN_POS_DETAILS": true,
}

func main() {
	dbL, err := sql.Open("oracle", os.Getenv("ORACLE_DSN"))
	if err != nil {
		panic(err)
	}
	defer dbL.Close()
	ctx, cancel := context.WithTimeout(context.Background(), 10*time.Minute)
	defer cancel()
	rows, err := dbL.QueryContext(ctx, `SELECT table_name FROM user_tables ORDER BY table_name`)
	if err != nil {
		panic(err)
	}
	var tables []string
	for rows.Next() {
		var t string
		rows.Scan(&t)
		tables = append(tables, t)
	}
	rows.Close()
	for _, t := range tables {
		if exclude[t] {
			continue
		}
		if _, err := dbL.ExecContext(ctx, "TRUNCATE TABLE "+t); err != nil {
			fmt.Printf("%-40s ERR %v\n", t, err)
			continue
		}
		fmt.Println("TRUNCATED", t)
	}
	fmt.Println("done")
}
