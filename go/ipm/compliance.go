package ipm

import (
	"encoding/json"
	"fmt"
	"strconv"
	"strings"
)

// Violation is one compliance failure (ComplianceViolation in Python).
type Violation struct {
	RuleID   string `json:"rule_id"`
	Severity string `json:"severity"`
	Message  string `json:"message"`
	RecordNo int    `json:"record"`
	MTI      string `json:"mti"`
}

// Rule is one record-level rule from compliance_rules.json.
type Rule struct {
	ID          string   `json:"id"`
	Description string   `json:"description"`
	Severity    string   `json:"severity"`
	Type        string   `json:"type"`
	Path        string   `json:"path"`
	MTIs        []string `json:"mtis"`
	Strip       bool     `json:"strip"`
	Min         int      `json:"min"`
	Max         int      `json:"max"`
	Delimiter   string   `json:"delimiter"`
	Subfield    int      `json:"subfield"`
	Element     string   `json:"element"`
}

// FileRule is one file-level rule from compliance_rules.json.
type FileRule struct {
	ID          string `json:"id"`
	Description string `json:"description"`
	Severity    string `json:"severity"`
	Type        string `json:"type"`
	FooterPath  string `json:"footer_path"`
	FieldPath   string `json:"field_path"`
	RecordMTI   string `json:"record_mti"`
}

type ruleFile struct {
	Rules     []Rule     `json:"rules"`
	FileRules []FileRule `json:"file_rules"`
}

// RulesEngine evaluates the configured compliance rules. It is the Go port
// of parser/compliance.py.
type RulesEngine struct {
	Rules     []Rule
	FileRules []FileRule
}

// NewRulesEngine loads the embedded compliance_rules.json.
func NewRulesEngine() (*RulesEngine, error) {
	var cfg ruleFile
	if err := json.Unmarshal(rulesJSON, &cfg); err != nil {
		return nil, fmt.Errorf("ipm: parse compliance_rules.json: %w", err)
	}
	return &RulesEngine{Rules: cfg.Rules, FileRules: cfg.FileRules}, nil
}

// AllRuleIDs returns every record-level and file-level rule id in order.
func (e *RulesEngine) AllRuleIDs() []string {
	ids := make([]string, 0, len(e.Rules)+len(e.FileRules))
	for _, r := range e.Rules {
		ids = append(ids, r.ID)
	}
	for _, r := range e.FileRules {
		ids = append(ids, r.ID)
	}
	return ids
}

// Record is the parsed view of one physical record that rules are evaluated
// against. It mirrors the dict ipm_parser.py builds.
type RecordResult struct {
	RecordNo int
	MTI      string

	// Fields maps the data element number to its decoded value. DE48 of a
	// 1644 header/footer is stored here too, so pds.* lookups resolve.
	Fields map[int]string
	// De48PDS holds the PDS chunks parsed out of a transaction DE48.
	De48PDS PDSDict
	// PDS holds the PDS chunks parsed out of a 1644 header/footer.
	PDS PDSDict

	// Function and MessageNumber are 1644 header/footer only.
	Function      string
	MessageNumber string
	// De48 is the raw DE48 of a 1644 header/footer. Python exposes it as a
	// separate key (never under "fields"), and the pds.* rule paths read it
	// from there, so it is kept out of Fields on purpose.
	De48 string

	Errors     []string
	Repairs    []string
	Violations []Violation

	// Complete/Consumed/Remaining describe how much of a transaction record
	// the field reader consumed. They are only meaningful for transaction
	// records: the Python original sets complete/remaining only in its
	// non-1644 branch, so 1644 headers/footers leave them unset there. Here
	// they keep their zero values for those records, which no rule reads.
	Complete  bool
	Consumed  int
	Remaining int
}

// Lookup resolves a dotted path against the record. Supported roots are
// "fields.<n>", "de48_pds.<id>" and "pds.<id>".
func (r *RecordResult) Lookup(path string) (string, bool) {
	parts := strings.Split(path, ".")
	if len(parts) < 2 {
		return "", false
	}
	switch parts[0] {
	case "fields":
		if len(parts) != 2 {
			return "", false
		}
		n, err := strconv.Atoi(parts[1])
		if err != nil {
			return "", false
		}
		v, ok := r.Fields[n]
		return v, ok
	case "de48_pds", "pds":
		if len(parts) != 3 {
			return "", false
		}
		src := r.De48PDS
		if parts[0] == "pds" {
			src = r.PDS
		}
		e, ok := src[parts[1]]
		if !ok {
			return "", false
		}
		return e.Value, true
	}
	return "", false
}

// EvaluateRecord applies every record-level rule to rec.
func (e *RulesEngine) EvaluateRecord(rec *RecordResult) []Violation {
	var out []Violation
	for _, rule := range e.Rules {
		if len(rule.MTIs) > 0 && !contains(rule.MTIs, rec.MTI) {
			continue
		}
		value, present := rec.Lookup(rule.Path)
		var target *string
		if present {
			target = &value
		}
		if msg := checkRule(rule, target); msg != "" {
			sev := rule.Severity
			if sev == "" {
				sev = "ERROR"
			}
			out = append(out, Violation{
				RuleID:   rule.ID,
				Severity: sev,
				Message:  msg,
				RecordNo: rec.RecordNo,
				MTI:      rec.MTI,
			})
		}
	}
	return out
}

// checkRule returns a violation message, or "" when the value passes.
func checkRule(rule Rule, value *string) string {
	switch rule.Type {
	case "required":
		if value == nil || strings.TrimSpace(*value) == "" {
			return fmt.Sprintf("%s (field '%s' is missing or empty)", rule.Description, rule.Path)
		}

	case "min_length":
		if value == nil {
			return ""
		}
		text := maybeStrip(*value, rule.Strip)
		if len(text) < rule.Min {
			return fmt.Sprintf("%s: value %d characters is shorter than min length %d (field '%s')",
				rule.Description, len(text), rule.Min, rule.Path)
		}

	case "max_length":
		if value == nil {
			return ""
		}
		text := maybeStrip(*value, rule.Strip)
		if len(text) > rule.Max {
			return fmt.Sprintf("%s: value %d characters exceeds max length %d (field '%s')",
				rule.Description, len(text), rule.Max, rule.Path)
		}

	case "subfield_min_length", "subfield_max_length":
		if value == nil {
			return ""
		}
		delim := rule.Delimiter
		if delim == "" {
			delim = `\`
		}
		parts := strings.Split(*value, delim)
		idx := rule.Subfield - 1
		if idx < 0 || idx >= len(parts) {
			return ""
		}
		text := maybeStrip(parts[idx], rule.Strip)
		if rule.Type == "subfield_min_length" && len(text) < rule.Min {
			return fmt.Sprintf("%s: subfield %d is %d characters, shorter than min length %d (field '%s')",
				rule.Description, rule.Subfield, len(text), rule.Min, rule.Path)
		}
		if rule.Type == "subfield_max_length" && len(text) > rule.Max {
			return fmt.Sprintf("%s: subfield %d is %d characters, exceeds max length %d (field '%s')",
				rule.Description, rule.Subfield, len(text), rule.Max, rule.Path)
		}

	case "subelement_min_length", "subelement_max_length":
		if value == nil {
			return ""
		}
		el, ok := extractSubelement(*value, rule.Element)
		if !ok {
			return fmt.Sprintf("%s: subelement %s is missing (field '%s')",
				rule.Description, rule.Element, rule.Path)
		}
		text := maybeStrip(el, rule.Strip)
		if rule.Type == "subelement_min_length" && len(text) < rule.Min {
			return fmt.Sprintf("%s: subelement %s is %d characters, shorter than min length %d (field '%s')",
				rule.Description, rule.Element, len(text), rule.Min, rule.Path)
		}
		if rule.Type == "subelement_max_length" && len(text) > rule.Max {
			return fmt.Sprintf("%s: subelement %s is %d characters, exceeds max length %d (field '%s')",
				rule.Description, rule.Element, len(text), rule.Max, rule.Path)
		}
	}
	return ""
}

// extractSubelement pulls subelement element out of a value encoded as
// concatenated <3-digit ID><3-digit length><value> chunks.
func extractSubelement(value, elementID string) (string, bool) {
	pos, n := 0, len(value)
	for pos+6 <= n {
		sid := value[pos : pos+3]
		lenPart := value[pos+3 : pos+6]
		if !isAllDigits(lenPart) {
			return "", false
		}
		vlen := atoiSafe(lenPart)
		start := pos + 6
		end := start + vlen
		if sid == elementID {
			if end <= n {
				return value[start:end], true
			}
			return value[start:], true
		}
		if end > n {
			return "", false
		}
		pos = end
	}
	return "", false
}

// EvaluateFile applies the file-level rules. The last record is the footer and
// supplies the declared value.
func (e *RulesEngine) EvaluateFile(recs []*RecordResult) []Violation {
	if len(recs) == 0 {
		return nil
	}
	footer := recs[len(recs)-1]
	var out []Violation

	for _, rule := range e.FileRules {
		declaredRaw, present := footer.Lookup(rule.FooterPath)
		declared, hasDeclared := toNumber(present, declaredRaw)

		var actual int64
		var label string

		switch rule.Type {
		case "file_count":
			// PDS 0306 declares every physical record in the file, including
			// the 1644 header and the trailer itself.
			for _, r := range recs {
				if rule.RecordMTI == "" || r.MTI == rule.RecordMTI {
					actual++
				}
			}
			label = fmt.Sprintf("count %d", actual)

		case "file_amount":
			for _, r := range recs {
				if rule.RecordMTI != "" && r.MTI != rule.RecordMTI {
					continue
				}
				raw, ok := r.Lookup(rule.FieldPath)
				if !ok {
					continue
				}
				if v, ok := toNumber(true, raw); ok {
					actual += v
				}
			}
			label = fmt.Sprintf("total %d", actual)

		default:
			continue
		}

		if !hasDeclared || declared != actual {
			out = append(out, Violation{
				RuleID:   rule.ID,
				Severity: rule.Severity,
				Message: fmt.Sprintf("%s: declared %s (%q), expected %s",
					rule.Description, optInt(declaredRaw, hasDeclared), declaredRaw, label),
				RecordNo: footer.RecordNo,
				MTI:      footer.MTI,
			})
		}
	}
	return out
}

func optInt(s string, ok bool) string {
	if !ok {
		return "None"
	}
	return s
}

func toNumber(present bool, raw string) (int64, bool) {
	if !present {
		return 0, false
	}
	t := strings.TrimSpace(raw)
	if t == "" {
		return 0, false
	}
	v, err := strconv.ParseInt(t, 10, 64)
	if err != nil {
		return 0, false
	}
	return v, true
}

func maybeStrip(s string, strip bool) string {
	if strip {
		return strings.TrimSpace(s)
	}
	return s
}

func contains(list []string, v string) bool {
	for _, s := range list {
		if s == v {
			return true
		}
	}
	return false
}
