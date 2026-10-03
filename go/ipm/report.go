package ipm

import (
	"encoding/csv"
	"encoding/json"
	"fmt"
	"os"
	"sort"
	"strings"
)

// WriteReports writes both CSV and JSONL compliance reports for a validated
// IPM file. The formats mirror the Python ipm_parser.py output.
func WriteReports(results []*RecordResult, fileViolations []Violation, csvPath, jsonlPath string) error {
	if csvPath != "" {
		if err := writeCSV(csvPath, results, fileViolations); err != nil {
			return fmt.Errorf("write csv: %w", err)
		}
	}
	if jsonlPath != "" {
		if err := writeJSONL(jsonlPath, results, fileViolations); err != nil {
			return fmt.Errorf("write jsonl: %w", err)
		}
	}
	return nil
}

func writeCSV(path string, results []*RecordResult, fileViolations []Violation) error {
	f, err := os.Create(path)
	if err != nil {
		return err
	}
	defer f.Close()

	w := csv.NewWriter(f)
	defer w.Flush()

	// Collect all rule IDs for per-rule PASS/FAIL columns (development mode)
	ruleIDs := collectRuleIDs(results, fileViolations)
	sort.Strings(ruleIDs)

	header := []string{"record", "mti", "complete", "remaining"}
	// Standard DE columns matching Python implementation
	deColumns := []int{2, 3, 4, 12, 22, 23, 24, 25, 26, 37, 38, 40, 41, 42, 43, 48, 49, 54, 55, 63, 71, 94}
	for _, de := range deColumns {
		header = append(header, fmt.Sprintf("de%03d", de))
	}
	// PDS 0170
	header = append(header, "de48_0170")
	// Per-rule columns
	for _, rid := range ruleIDs {
		header = append(header, fmt.Sprintf("rule:%s", rid))
	}
	header = append(header, "errors", "compliance")

	if err := w.Write(header); err != nil {
		return err
	}

	// File violations map for lookup
	fileMap := make(map[string]Violation)
	for _, v := range fileViolations {
		fileMap[v.RuleID] = v
	}

	for _, r := range results {
		fields := r.Fields
		row := make([]string, 0, len(header))
		row = append(row,
			fmt.Sprintf("%d", r.RecordNo),
			r.MTI,
			fmt.Sprintf("%t", r.Complete),
			fmt.Sprintf("%d", r.Remaining),
		)
		for _, de := range deColumns {
			row = append(row, fields[de])
		}
		// de48_0170
		pds0170 := ""
		if pds := r.De48PDS; pds != nil {
			if v, ok := pds["0170"]; ok {
				pds0170 = v.Value
			}
		}
		row = append(row, pds0170)

		// Per-rule PASS/FAIL
		violMap := make(map[string]Violation)
		for _, v := range r.Violations {
			violMap[v.RuleID] = v
		}
		for _, rid := range ruleIDs {
			if _, has := violMap[rid]; has {
				row = append(row, "FAIL")
			} else if _, has := fileMap[rid]; has {
				row = append(row, "FAIL")
			} else {
				row = append(row, "PASS")
			}
		}

		// Errors and compliance messages
		row = append(row, strings.Join(r.Errors, "|"))
		var complianceMsgs []string
		for _, v := range r.Violations {
			complianceMsgs = append(complianceMsgs, v.Message)
		}
		row = append(row, strings.Join(complianceMsgs, "|"))

		if err := w.Write(row); err != nil {
			return err
		}
	}

	// File-level violations as trailing rows
	for _, v := range fileViolations {
		row := make([]string, len(header))
		row[1] = "FILE"
		row[len(row)-1] = fmt.Sprintf("%s: %s", v.RuleID, v.Message)
		if err := w.Write(row); err != nil {
			return err
		}
	}

	return nil
}

func collectRuleIDs(results []*RecordResult, fileViolations []Violation) []string {
	ids := make(map[string]struct{})
	for _, r := range results {
		for _, v := range r.Violations {
			ids[v.RuleID] = struct{}{}
		}
	}
	for _, v := range fileViolations {
		ids[v.RuleID] = struct{}{}
	}
	out := make([]string, 0, len(ids))
	for id := range ids {
		out = append(out, id)
	}
	return out
}

func writeJSONL(path string, results []*RecordResult, fileViolations []Violation) error {
	f, err := os.Create(path)
	if err != nil {
		return err
	}
	defer f.Close()

	enc := json.NewEncoder(f)
	enc.SetEscapeHTML(false)

	for _, r := range results {
out := map[string]interface{}{
		"record":    r.RecordNo,
		"offset":    0, // not tracked in Go scanner
		"length":    0,
		"mti":       r.MTI,
		"bitmap":    nil,
		"fields":    r.Fields,
		"errors":    r.Errors,
		"compliance": func() []map[string]interface{} {
				var cv []map[string]interface{}
				for _, v := range r.Violations {
					cv = append(cv, map[string]interface{}{
						"rule_id":  v.RuleID,
						"severity": v.Severity,
						"message":  v.Message,
					})
				}
				return cv
			}(),
		}
		if len(r.Repairs) > 0 {
			out["repaired"] = r.Repairs
		}
		if r.Complete {
			out["complete"] = r.Complete
		}
		if r.Remaining > 0 {
			out["remaining"] = r.Remaining
		}
		if r.Function != "" {
			out["function"] = r.Function
		}
		if r.MessageNumber != "" {
			out["message_number"] = r.MessageNumber
		}
		if r.De48 != "" {
			out["de48"] = r.De48
		}
		if r.De48PDS != nil {
			out["de48_pds"] = r.De48PDS
		}
		if pds := r.PDS; pds != nil {
			out["pds"] = pds
		}

		if err := enc.Encode(out); err != nil {
			return err
		}
	}

	// File-level violations appended to last record (mirrors Python)
	if len(fileViolations) > 0 && len(results) > 0 {
		fv := make([]map[string]interface{}, 0, len(fileViolations))
		for _, v := range fileViolations {
			fv = append(fv, map[string]interface{}{
				"rule_id":  v.RuleID,
				"severity": v.Severity,
				"message":  v.Message,
			})
		}
		// Rewrite last line with file_compliance
		// Since we already wrote it, we'd need to truncate. For simplicity,
		// just add as a separate record with MTI=FILE.
		enc.Encode(map[string]interface{}{
			"mti":              "FILE",
			"file_compliance": fv,
		})
	}

	return nil
}