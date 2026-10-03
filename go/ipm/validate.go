package ipm

import (
	"fmt"
	"os"
	"strings"
)

func readFile(path string) ([]byte, error) {
	b, err := os.ReadFile(path)
	if err != nil {
		return nil, fmt.Errorf("ipm: read %s: %w", path, err)
	}
	return b, nil
}

// Validator parses an IPM file and evaluates compliance against it. It is the
// Go port of ipm_parser.py's process_file.
type Validator struct {
	meta   *Metadata
	engine *RulesEngine
}

// NewValidator loads the embedded metadata and rules.
func NewValidator() (*Validator, error) {
	meta, err := LoadMetadata()
	if err != nil {
		return nil, err
	}
	engine, err := NewRulesEngine()
	if err != nil {
		return nil, err
	}
	return &Validator{meta: meta, engine: engine}, nil
}

// ValidateFile parses path and returns every record result plus the
// file-level violations.
func (v *Validator) ValidateFile(path string) ([]*RecordResult, []Violation, error) {
	data, err := readFile(path)
	if err != nil {
		return nil, nil, err
	}
	return v.Validate(data)
}

// Validate parses an in-memory IPM file.
func (v *Validator) Validate(data []byte) ([]*RecordResult, []Violation, error) {
	records := NewScanner().Scan(data)
	results := make([]*RecordResult, 0, len(records))

	for i, rec := range records {
		res := &RecordResult{
			RecordNo: i + 1,
			MTI:      rec.MTI,
			Fields:   map[int]string{},
			Repairs:  rec.Repairs,
		}
		v.parseRecord(res, rec)
		res.Violations = v.engine.EvaluateRecord(res)
		results = append(results, res)
	}

	var fileViolations []Violation
	if len(results) > 0 {
		fileViolations = v.engine.EvaluateFile(results)
	}
	return results, fileViolations, nil
}

// parseRecord fills res from one scanned record. A parse error is recorded on
// the result rather than aborting, so one bad record cannot hide the rest.
func (v *Validator) parseRecord(res *RecordResult, rec Record) {
	if rec.MTI == "1644" {
		if err := v.parse1644(res, rec.Payload); err != nil {
			res.Errors = append(res.Errors, "record-level: "+err.Error())
			return
		}
		res.PDS = pdsToDict(ParsePDS(res.De48), v.meta.PDS)
		return
	}

	fields, consumed, err := v.parse1240(rec.Payload)
	if err != nil {
		res.Errors = append(res.Errors, err.Error())
		return
	}

	// Python reports complete/remaining against whichever buffer it last
	// parsed, so an extended read must also carry its own length forward.
	effectiveLen := len(rec.Payload)

	// A record whose declared length fell short of its true length leaves a
	// gap before the next record. Retry with the trailing bytes appended and
	// accept that reading only when it consumes everything.
	if len(rec.Trailing) > 0 {
		combined := make([]byte, 0, len(rec.Payload)+len(rec.Trailing))
		combined = append(combined, rec.Payload...)
		combined = append(combined, rec.Trailing...)
		if f2, c2, err2 := v.parse1240(combined); err2 == nil && c2 == len(combined) {
			fields, consumed, effectiveLen = f2, c2, len(combined)
			res.Repairs = append(res.Repairs,
				fmt.Sprintf("extended-by-%d", len(rec.Trailing)))
		}
	}

	res.Fields = fields
	res.Consumed = consumed
	res.Complete = consumed == effectiveLen
	res.Remaining = effectiveLen - consumed

	if de48 := fields[48]; de48 != "" {
		res.De48PDS = pdsToDict(ParsePDS(de48), v.meta.PDS)
	}
}

// parse1240 reads a transaction record and returns its fields plus the number
// of payload bytes consumed.
func (v *Validator) parse1240(payload []byte) (map[int]string, int, error) {
	r := NewReader(payload)
	r.ReadEbcdic(4) // MTI
	bm := BitmapFromReader(r)

	fields := make(map[int]string)
	for _, de := range bm.PresentDEs() {
		if de == 1 {
			continue
		}
		def, ok := v.meta.DE[de]
		if !ok {
			continue // unknown DE: skip without consuming, as in Python
		}
		val, err := ReadField(r, def)
		if err != nil {
			return nil, 0, fmt.Errorf("DE%03d: %w", de, err)
		}
		fields[de] = val
	}
	return fields, r.Tell(), nil
}

// parse1644 reads a file header/footer record.
func (v *Validator) parse1644(res *RecordResult, payload []byte) error {
	r := NewReader(payload)
	r.ReadEbcdic(4) // MTI
	r.ReadBitmap()  // bitmap
	r.ReadBytes(8)  // binary header
	function := r.ReadEbcdic(3)
	de48Len, err := r.ReadLength(3)
	if err != nil {
		return err
	}
	de48 := r.ReadEbcdic(atoiSafe(de48Len))
	messageNumber := r.ReadEbcdic(8)

	res.Fields = map[int]string{}
	res.Function = function
	res.MessageNumber = messageNumber
	res.De48 = de48
	return nil
}

// RecordSummary is a compact per-record view for reporting.
type RecordSummary struct {
	RecordNo   int
	MTI        string
	DE2        string
	DE3        string
	DE4        string
	DE37       string
	DE41       string
	DE42       string
	Errors     []string
	Violations []Violation
}

// Summary returns the compact per-record view used by the file-job report.
func (v *Validator) Summary(results []*RecordResult) []RecordSummary {
	out := make([]RecordSummary, 0, len(results))
	for _, r := range results {
		out = append(out, RecordSummary{
			RecordNo:   r.RecordNo,
			MTI:        r.MTI,
			DE2:        r.Fields[2],
			DE3:        r.Fields[3],
			DE4:        r.Fields[4],
			DE37:       r.Fields[37],
			DE41:       r.Fields[41],
			DE42:       r.Fields[42],
			Errors:     r.Errors,
			Violations: r.Violations,
		})
	}
	return out
}

// ValidationReport is the outcome of validating one IPM file.
type ValidationReport struct {
	Records        int
	Errors         int
	Violations     int
	FileViolations []Violation
	Messages       []string
}

// ValidateFileReport runs the full pipeline and summarises it.
func (v *Validator) ValidateFileReport(path string) (*ValidationReport, error) {
	results, fileViolations, err := v.ValidateFile(path)
	if err != nil {
		return nil, err
	}
	rep := &ValidationReport{
		Records:        len(results),
		FileViolations: fileViolations,
	}
	for _, r := range results {
		rep.Errors += len(r.Errors)
		rep.Violations += len(r.Violations)
		for _, e := range r.Errors {
			rep.Messages = append(rep.Messages,
				fmt.Sprintf("record %d - %s", r.RecordNo, e))
		}
		for _, x := range r.Violations {
			rep.Messages = append(rep.Messages, fmt.Sprintf(
				"record %d - RRN %s - Acceptor ID %s: [%s] %s",
				r.RecordNo, r.Fields[37], r.Fields[42], x.Severity, x.Message))
		}
	}
	for _, x := range fileViolations {
		rep.Violations++
		rep.Messages = append(rep.Messages,
			fmt.Sprintf("[%s] %s", x.Severity, x.Message))
	}
	return rep, nil
}

// OK reports whether the file parsed cleanly and passed every rule.
func (r *ValidationReport) OK() bool {
	return r.Errors == 0 && r.Violations == 0
}

// Summary renders a one-line human summary.
func (r *ValidationReport) Summary() string {
	var b strings.Builder
	status := "OK"
	if !r.OK() {
		status = "FAILED"
	}
	fmt.Fprintf(&b, "%s (%d records, %d errors, %d violations)",
		status, r.Records, r.Errors, r.Violations)
	return b.String()
}
