package mpgssvc

import (
	"database/sql"
	"testing"
	"time"

	"empay/irf/irf"
)

func TestRowToTxn(t *testing.T) {
	ts := time.Date(2026, 8, 13, 12, 30, 0, 0, time.UTC)
	r := row{
		SerNumber:      sql.NullInt64{Int64: 42, Valid: true},
		InsCode:        sql.NullInt64{Int64: 1, Valid: true},
		Network:        sql.NullString{String: "VISA", Valid: true},
		Mcc:            sql.NullString{String: "5499", Valid: true},
		TxnAmount:      sql.NullFloat64{Float64: 100.0, Valid: true},
		TrlCapabilities: sql.NullString{String: "00", Valid: true},
		TxnDateTime:    sql.NullTime{Time: ts, Valid: true},
	}
	txn := r.txn()
	if txn.SerialNumber != 42 || txn.InsCode != 1 || txn.Network != "VISA" {
		t.Fatalf("bad txn: %+v", txn)
	}
	if !txn.TxnDateTime.Equal(ts) {
		t.Fatalf("bad txnDateTime: %v", txn.TxnDateTime)
	}
}

func TestUpdateArgsNilVO(t *testing.T) {
	args := updateArgs(7, nil)
	if len(args) != 13 {
		t.Fatalf("want 13 args, got %d", len(args))
	}
	// clearIrfData: domIntlFlag blank, irdSerNumber null.
	if args[7] != " " {
		t.Fatalf("domIntlFlag should be blank, got %#v", args[7])
	}
	if _, ok := args[0].(sql.NullInt64); !ok {
		t.Fatalf("irdSerNumber should be NULL-typed, got %#v", args[0])
	}
}

func TestUpdateArgsPopulated(t *testing.T) {
	sn := 99
	vo := &irf.IrfResultVo{
		IrdSerNumber:  &sn,
		IrdCode:       "85",
		IrfFixed:      fptr(0.5),
		IrfPercentage: fptr(2.5),
		IrfAmount:     fptr(3.0),
		IrfAmountUSD:  fptr(3.0),
		CardType:      "C",
		DomIntlFlag:   "I",
		GcmsProductID: "MBH",
		IrfDesc:       "override",
	}
	args := updateArgs(7, vo)
	if args[0] != 99 || args[1] != "85" || args[6] != "C" || args[7] != "I" {
		t.Fatalf("bad populated args: %#v", args)
	}
	// Java forces min/max to 0.0.
	if args[10] != 0.0 || args[11] != 0.0 {
		t.Fatalf("min/max should be 0.0: %#v", args[10:12])
	}
}

func fptr(v float64) *float64 { return &v }
