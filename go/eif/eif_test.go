package eif

import (
	"strings"
	"testing"
)

const sample = `FRRC>UX>RK>001>MP>AED>011026
FRRC>UH>RK>001>MP>001>011026
FRRC>XD>RK>001>MP>001>001>6690109700100010>10.00
FRRC>XD>RK>001>MP>001>002>4104999999999998>5.50
FRRC>UT>RK>001>MP>001>0>0.00>2>15.50
FRRC>UY>RK>001>MP>0>0.00>2>15.50>01.000>15.50
`

func TestValidateReconciles(t *testing.T) {
	res, err := Parse(strings.NewReader(sample))
	if err != nil {
		t.Fatalf("parse: %v", err)
	}
	rep := ValidateResult(res)
	if !rep.OK() {
		t.Fatalf("expected OK, got errors: %v", rep.Errors)
	}
	if len(rep.Batches) != 1 {
		t.Fatalf("batches = %d, want 1", len(rep.Batches))
	}
	if rep.Batches[0].TxnCount != 2 || rep.Batches[0].SumCAMTR != 15.50 {
		t.Errorf("batch summary = %+v", rep.Batches[0])
	}
	if rep.RecapTxnCount != 2 || rep.RecapSumCAMTR != 15.50 {
		t.Errorf("recap = %d txns / %.2f", rep.RecapTxnCount, rep.RecapSumCAMTR)
	}
}

func TestValidateDetectsMismatch(t *testing.T) {
	bad := strings.Replace(sample, ">2>15.50\nFRRC>UY", ">2>99.00\nFRRC>UY", 1)
	bad = strings.Replace(bad, ">2>15.50>01.000", ">2>99.00>01.000", 1)
	res, err := Parse(strings.NewReader(bad))
	if err != nil {
		t.Fatalf("parse: %v", err)
	}
	rep := ValidateResult(res)
	if rep.OK() {
		t.Fatal("expected FAIL, got OK")
	}
	if len(rep.Errors) != 2 {
		t.Fatalf("errors = %v, want 2", rep.Errors)
	}
}

// TestValidateSplitsCreditsAndDebits pins the manual's UT/UY tally semantics
// (BTNCR/BTACR vs BTNDR/BTADR, RNAMT = |RCADR - RCACR|): the earlier
// all-CAMTR-sum check falsely failed mixed credit/debit files.
func TestValidateSplitsCreditsAndDebits(t *testing.T) {
	const mixed = `FRRC>UX>RK>001>MP>AED>011026
FRRC>UH>RK>001>MP>001>011026
FRRC>XD>RK>001>MP>001>001>6690109700100010>10.00>260927>TS>TF
FRRC>XD>RK>001>MP>001>002>6690109700100010>5.50>260927>TS>AA
FRRC>UT>RK>001>MP>001>1>10.00>1>5.50
FRRC>UY>RK>001>MP>1>10.00>1>5.50>01.000>4.50
`
	res, err := Parse(strings.NewReader(mixed))
	if err != nil {
		t.Fatalf("parse: %v", err)
	}
	rep := ValidateResult(res)
	if !rep.OK() {
		t.Fatalf("expected OK, got errors: %v", rep.Errors)
	}
	if rep.Batches[0].TxnCount != 2 || rep.Batches[0].SumCAMTR != 15.50 {
		t.Errorf("batch summary = %+v", rep.Batches[0])
	}
}

func TestMaskPAN(t *testing.T) {
	if got := MaskPAN("4104999999999998"); got != "410499******9998" {
		t.Errorf("mask = %q", got)
	}
	if got := MaskPAN("123456789"); got != "123456789" {
		t.Errorf("short pan = %q", got)
	}
}

func TestDecodeUnknownRecord(t *testing.T) {
	rec := DecodeLine("FRRC>ZZ>foo")
	if rec == nil || rec.Type != "ZZ" || rec.Fields != nil || len(rec.Raw) != 3 {
		t.Fatalf("unknown rec = %+v", rec)
	}
	if DecodeLine("onlyone") != nil {
		t.Fatal("expected nil for single field")
	}
}
