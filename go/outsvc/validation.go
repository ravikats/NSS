package outsvc

import (
	"time"

	"empay/irf/eif"
)

// maxValidationHistory caps the in-memory validation results kept for the
// inquiry UI (newest first). Results are not persisted: after a service
// restart the UI shows "not validated in this run" for older files.
const maxValidationHistory = 50

// ValidationResult is the outcome of the EIF reconciliation check performed
// after a generated settlement file is written. It is kept in memory (last run
// only) and exposed to the inquiry UI via GET /outgoing/v1/lastValidation.
type ValidationResult struct {
	Network     string    `json:"network"`
	File        string    `json:"file"`
	OK          bool      `json:"ok"`
	Batches     int       `json:"batches"`
	Records     int       `json:"records,omitempty"`
	TxnCount    int       `json:"txnCount"`
	SumAmount   float64   `json:"sumAmount"`
	Errors      []string  `json:"errors,omitempty"`
	Summary     string    `json:"summary,omitempty"`
	ValidatedAt time.Time `json:"validatedAt"`
}

// newValidationResult builds the result payload from a reconciliation report.
// pass is the error that caused the validation to fail (nil when it passed).
func newValidationResult(network, file string, report *eif.Report, pass error, now time.Time) *ValidationResult {
	res := &ValidationResult{Network: network, File: file, ValidatedAt: now}
	if report != nil {
		res.Batches = len(report.Batches)
		res.TxnCount = report.RecapTxnCount
		res.SumAmount = report.RecapSumCAMTR
		res.Summary = report.String()
		res.Errors = append(res.Errors, report.Errors...)
	}
	if pass != nil {
		res.OK = false
		res.Errors = append(res.Errors, pass.Error())
	} else {
		res.OK = true
	}
	return res
}

func (s *OutgoingService) recordValidation(res *ValidationResult) {
	s.validationMu.Lock()
	s.validations = append([]*ValidationResult{res}, s.validations...)
	if len(s.validations) > maxValidationHistory {
		s.validations = s.validations[:maxValidationHistory]
	}
	s.validationMu.Unlock()
}

// Validations returns the recorded validation results, newest first.
func (s *OutgoingService) Validations() []*ValidationResult {
	s.validationMu.Lock()
	defer s.validationMu.Unlock()
	out := make([]*ValidationResult, len(s.validations))
	copy(out, s.validations)
	return out
}
