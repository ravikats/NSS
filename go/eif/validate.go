package eif

import (
	"fmt"
	"strconv"
	"strings"
)

// amountEpsilon absorbs IEEE-754 round-off when comparing reconciled totals.
const amountEpsilon = 1e-9

// BatchSummary reports one batch's reconciled totals.
type BatchSummary struct {
	Batch    string
	TxnCount int
	SumCAMTR float64
}

// Report is the reconciliation outcome for one EIF file.
type Report struct {
	File          string
	Batches       []BatchSummary
	RecapTxnCount int
	RecapSumCAMTR float64
	Errors        []string
}

// OK reports whether all totals reconcile.
func (r *Report) OK() bool { return len(r.Errors) == 0 }

// String renders the report in the same shape as eif_validate.py.
func (r *Report) String() string {
	var b strings.Builder
	fmt.Fprintf(&b, "File: %s\n", r.File)
	for _, bs := range r.Batches {
		fmt.Fprintf(&b, "  Batch %s: %d txns, sum(CAMTR)=%.2f\n", bs.Batch, bs.TxnCount, bs.SumCAMTR)
	}
	fmt.Fprintf(&b, "  Recap: %d txns, sum(CAMTR)=%.2f\n", r.RecapTxnCount, r.RecapSumCAMTR)
	if r.OK() {
		b.WriteString("\nOK - all totals reconcile")
		return b.String()
	}
	b.WriteString("\nFAIL - totals mismatch:")
	for _, e := range r.Errors {
		fmt.Fprintf(&b, "\n  - %s", e)
	}
	return b.String()
}

func toAmount(v string) float64 {
	if v == "" {
		return 0
	}
	f, err := strconv.ParseFloat(strings.TrimSpace(v), 64)
	if err != nil {
		return 0
	}
	return f
}

func almostEqual(a, b float64) bool {
	d := a - b
	if d < 0 {
		d = -d
	}
	return d <= amountEpsilon
}

// isCreditTxn classifies a detail record as a credit by its charge type
// (CHTYP), mirroring outsvc.mercuryIsCreditTxn / the Mercury EIF charge-type
// codes for credit items.
func isCreditTxn(detail *Record) bool {
	switch detail.Get("CHTYP") {
	case "TF", "TG", "TJ", "TL":
		return true
	}
	return false
}

func (r *Report) checkCount(rec *Record, field, txnType string, actual int) {
	if rec == nil {
		return
	}
	if rec.Get(field) == "" {
		return
	}
	got := toAmount(rec.Get(field))
	if float64(actual) != got && !almostEqual(float64(actual), got) {
		r.Errors = append(r.Errors, fmt.Sprintf(
			"%s: %s=%v != %s count %d", rec.Type, field, got, txnType, actual))
	}
}

func (r *Report) checkAmount(rec *Record, field, txnType string, actual float64) {
	if rec == nil {
		return
	}
	if rec.Get(field) == "" {
		return
	}
	got := toAmount(rec.Get(field))
	if !almostEqual(got, actual) {
		r.Errors = append(r.Errors, fmt.Sprintf(
			"%s: %s=%v != %s amount %v", rec.Type, field, got, txnType, actual))
	}
}

// ValidateResult reconciles the totals of a parsed EIF file against the
// Mercury EIF specification: batch UT tallies (BTNCR/BTACR/BTNDR/BTADR) and
// recap UY tallies (RCNCR/RCACR/RCNDR/RCADR), plus RNAMT = |RCADR - RCACR|.
// Reject reasons J6-J9, L1-L4 and M5 in the manual.
func ValidateResult(res *Result) *Report {
	rep := &Report{}

	var recapCreditCount, recapDebitCount int
	var recapCreditAmount, recapDebitAmount float64

	for _, batch := range res.Batches {
		batNo := ""
		if batch.Header != nil {
			batNo = batch.Header.Get("BATCH")
		}

		var creditCount, debitCount int
		var creditAmount, debitAmount float64
		for _, t := range batch.Transactions {
			amt := toAmount(t.Detail.Get("CAMTR"))
			if isCreditTxn(t.Detail) {
				creditCount++
				creditAmount += amt
			} else {
				debitCount++
				debitAmount += amt
			}
		}
		rep.Batches = append(rep.Batches, BatchSummary{
			Batch:    batNo,
			TxnCount: len(batch.Transactions),
			SumCAMTR: creditAmount + debitAmount,
		})
		recapCreditCount += creditCount
		recapDebitCount += debitCount
		recapCreditAmount += creditAmount
		recapDebitAmount += debitAmount

		if trailer := batch.Trailer; trailer != nil {
			rep.checkCount(trailer, "BTNCR", "batch credit", creditCount)
			rep.checkAmount(trailer, "BTACR", "batch credit", creditAmount)
			rep.checkCount(trailer, "BTNDR", "batch debit", debitCount)
			rep.checkAmount(trailer, "BTADR", "batch debit", debitAmount)
		}
	}

	rep.RecapTxnCount = recapCreditCount + recapDebitCount
	rep.RecapSumCAMTR = recapCreditAmount + recapDebitAmount

	if recap := res.RecapTrailer; recap != nil {
		rep.checkCount(recap, "RCNCR", "recap credit", recapCreditCount)
		rep.checkAmount(recap, "RCACR", "recap credit", recapCreditAmount)
		rep.checkCount(recap, "RCNDR", "recap debit", recapDebitCount)
		rep.checkAmount(recap, "RCADR", "recap debit", recapDebitAmount)

		// RNAMT is the recap net amount = |RCADR - RCACR| (Interchange Service
		// Rate DRATE 01.000, i.e. no discount).
		if recap.Get("RNAMT") != "" {
			net := recapDebitAmount - recapCreditAmount
			if net < 0 {
				net = -net
			}
			if !almostEqual(toAmount(recap.Get("RNAMT")), net) {
				rep.Errors = append(rep.Errors, fmt.Sprintf(
					"UY: RNAMT=%v != |RCADR-RCACR|=%v", toAmount(recap.Get("RNAMT")), net))
			}
		}
	}

	return rep
}

// ValidateFile parses and reconciles the EIF file at path.
func ValidateFile(path string) (*Report, error) {
	res, err := ParseFile(path)
	if err != nil {
		return nil, err
	}
	rep := ValidateResult(res)
	rep.File = path
	return rep, nil
}
