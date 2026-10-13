package outsvc

import (
	"context"
	"log"
	"time"
)

// maxValidationHistory caps the in-memory validation results kept for the
// inquiry UI (newest first). New validations are appended at the front and the
// history is truncated to this size. Results are also persisted to the store
// (OUT_FILE_LOG validation columns) so the UI can recover the last run after a
// restart.
const maxValidationHistory = 50

// ValidationResult is the outcome of the EIF reconciliation check performed
// after a generated settlement file is written. It is kept in memory (most
// recent first, capped at maxValidationHistory) and persisted to OUT_FILE_LOG.
type ValidationResult struct {
	Network     string    `json:"network"`
	File        string    `json:"file"`
	OK          bool      `json:"ok"`
	Batches     int       `json:"batches"`
	Records     int       `json:"records,omitempty"`
	TxnCount    int       `json:"txnCount"`
	SumAmount   float64   `json:"sumAmount,omitempty"`
	Errors      []string  `json:"errors,omitempty"`
	Summary     string    `json:"summary,omitempty"`
	ValidatedAt time.Time `json:"validatedAt"`
}


// loadValidations loads validation history from the persistent store.
// It is called once on first access to Validations().
func (s *OutgoingService) loadValidations(ctx context.Context) error {
	s.validationMu.Lock()
	defer s.validationMu.Unlock()
	if s.validationsLoaded {
		return nil
	}
	s.validationsLoaded = true
	if s.store == nil {
		return nil
	}
	results, err := s.store.LoadValidationResults(ctx, maxValidationHistory)
	if err != nil {
		log.Printf("outsvc: loadValidations error: %v", err)
		return err
	}
	log.Printf("outsvc: loadValidations loaded %d results", len(results))
	s.validations = results
	return nil
}

// recordValidation stores the validation result in memory and persists it.
func (s *OutgoingService) recordValidation(ctx context.Context, res *ValidationResult) {
	s.validationMu.Lock()
	s.validations = append([]*ValidationResult{res}, s.validations...)
	if len(s.validations) > maxValidationHistory {
		s.validations = s.validations[:maxValidationHistory]
	}
	s.validationMu.Unlock()

	// Persist to store if available (synchronous so the store stays consistent
	// with the in-memory slice; the store is the recovery source after restart).
	if s.store != nil {
		ctx, cancel := context.WithTimeout(ctx, 5*time.Second)
		defer cancel()
		err := s.store.SaveValidationResult(ctx, res.File, res)
		if err != nil {
			log.Printf("outsvc: SaveValidationResult error: %v", err)
		}
	}
}

// Validations returns the recorded validation results, newest first.
// It loads from persistent storage on first call.
func (s *OutgoingService) Validations(ctx context.Context) []*ValidationResult {
	if err := s.loadValidations(ctx); err != nil {
		// Log error but return in-memory results
		log.Printf("outsvc: loadValidations error: %v", err)
	}
	s.validationMu.Lock()
	defer s.validationMu.Unlock()
	if s.validations == nil {
		s.validations = []*ValidationResult{}
	}
	log.Printf("outsvc: Validations returning %d results", len(s.validations))
	out := make([]*ValidationResult, len(s.validations))
	copy(out, s.validations)
	return out
}
