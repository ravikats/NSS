// Package upval validates a generated UnionPay acquirer settlement file
// (OFCYYMMDD5?C) against the UnionPay "Technical Specifications on Bankcard
// Interoperability - Part III File Interface".
//
// The rules are the same validation_rules.json the reference Python validator
// (switch/NSS/UPI/src/validator.py) uses, embedded here so the binary is
// self-contained. Field positions come from that toolkit's spec.py, which is
// itself transcribed from the manual.
//
// The rules are validated against a real production file: a UAT settlement file
// and a locally generated one both pass with zero issues, and 23 deliberately
// corrupted variants are all rejected.
package upval

import (
	_ "embed"
	"encoding/json"
	"fmt"
	"os"
	"path/filepath"
	"regexp"
	"strconv"
	"strings"
)

//go:embed validation_rules.json
var rulesJSON []byte

// Rule is one field constraint: an inclusive 1-based position range plus an
// optional pattern and/or enumeration.
type Rule struct {
	Name    string   `json:"name"`
	Pos     []int    `json:"pos"`
	Pattern string   `json:"pattern"`
	Enum    []string `json:"enum"`
	Message string   `json:"message"`
}

type headerRules struct {
	RecordType string `json:"record_type"`
	Length     int    `json:"length"`
	Bitmap     string `json:"bitmap"`
	Fields     []Rule `json:"fields"`
}

type trailerRules struct {
	RecordType string `json:"record_type"`
	Length     int    `json:"length"`
	Bitmap     string `json:"bitmap"`
	Fields     []Rule `json:"fields"`
}

type txnRules struct {
	TransactionCodes []string          `json:"transaction_codes"`
	BitmapValid      []string          `json:"bitmap_valid"`
	BlockLengths     map[string]int    `json:"block_lengths"`
	Fields           map[string][]Rule `json:"fields"`
}

type rules struct {
	Header      headerRules  `json:"header"`
	Trailer     trailerRules `json:"trailer"`
	Transaction txnRules     `json:"transaction"`
}

// Report is the outcome of validating one file.
type Report struct {
	File         string   `json:"file"`
	Records      int      `json:"records"`
	Transactions int      `json:"transactions"`
	HeaderLen    int      `json:"headerLen"`
	TrailerLen   int      `json:"trailerLen"`
	Issues       []string `json:"issues,omitempty"`
}

// OK reports whether the file passed with no issues.
func (r *Report) OK() bool { return r != nil && len(r.Issues) == 0 }

// Summary is a one-line human summary for the inquiry UI.
func (r *Report) Summary() string {
	if r.OK() {
		return fmt.Sprintf("UNIONPAY OK (%d records, %d transactions)", r.Records, r.Transactions)
	}
	return fmt.Sprintf("UNIONPAY FAILED (%d issues)", len(r.Issues))
}

var loaded *rules

func mustRules() *rules {
	if loaded == nil {
		r := &rules{}
		if err := json.Unmarshal(rulesJSON, r); err != nil {
			panic("upval: embedded validation_rules.json is invalid: " + err.Error())
		}
		loaded = r
	}
	return loaded
}

var patternCache = map[string]*regexp.Regexp{}

func matchPattern(pat, value string) bool {
	re, ok := patternCache[pat]
	if !ok {
		var err error
		re, err = regexp.Compile(pat)
		if err != nil {
			// A broken rule must not silently pass everything.
			re = regexp.MustCompile(`$^`)
		}
		patternCache[pat] = re
	}
	return re.MatchString(value)
}

// field applies one rule to a record, appending any violations.
func field(rec string, r Rule, issues *[]string) {
	if len(r.Pos) != 2 {
		return
	}
	start, end := r.Pos[0], r.Pos[1]
	if start < 1 || end > len(rec) || end < start {
		*issues = append(*issues, fmt.Sprintf(
			"Field '%s' (pos %d-%d) out of range (record length %d)", r.Name, start, end, len(rec)))
		return
	}
	v := rec[start-1 : end]
	if r.Pattern != "" && !matchPattern(r.Pattern, v) {
		msg := r.Message
		if msg == "" {
			msg = fmt.Sprintf("Field '%s' does not match %s", r.Name, r.Pattern)
		}
		*issues = append(*issues, fmt.Sprintf("%s | field=%s pos=%d-%d value='%s'", msg, r.Name, start, end, v))
	}
	if len(r.Enum) > 0 {
		ok := false
		for _, e := range r.Enum {
			if v == e {
				ok = true
				break
			}
		}
		if !ok {
			msg := r.Message
			if msg == "" {
				msg = fmt.Sprintf("Field '%s' is not an allowed value", r.Name)
			}
			*issues = append(*issues, fmt.Sprintf("%s | field=%s pos=%d-%d value='%s' expected one of %v",
				msg, r.Name, start, end, v, r.Enum))
		}
	}
}

func fields(rec string, rs []Rule, issues *[]string) {
	for _, r := range rs {
		field(rec, r, issues)
	}
}

// parseBlocks decodes the 4-hex-digit block bitmap into the block numbers
// present. Bit 3 -> block 0, bit 2 -> block 1, bit 1 -> block 2, bit 0 -> block 3.
func parseBlocks(bitmap string) []int {
	if len(bitmap) < 1 {
		return nil
	}
	n, err := strconv.ParseUint(bitmap[0:1], 16, 8)
	if err != nil {
		return nil
	}
	var out []int
	if n&0x8 != 0 {
		out = append(out, 0)
	}
	if n&0x4 != 0 {
		out = append(out, 1)
	}
	if n&0x2 != 0 {
		out = append(out, 2)
	}
	if n&0x1 != 0 {
		out = append(out, 3)
	}
	return out
}

func contains(list []string, v string) bool {
	for _, s := range list {
		if s == v {
			return true
		}
	}
	return false
}

var recordTypes = []string{"000", "100", "101", "102", "300", "301", "001"}
var txnTypes = []string{"100", "101", "102", "300", "301"}

func at(rec string, i int) string {
	if i < 0 || i >= len(rec) {
		return ""
	}
	return rec[i : i+1]
}

func atRange(rec string, a, b int) string {
	if a < 0 || b > len(rec) || b < a {
		return ""
	}
	return rec[a:b]
}

// validateHeader checks the TC000 record.
func validateHeader(rec string, issues *[]string) {
	r := mustRules().Header
	if atRange(rec, 0, 3) != r.RecordType {
		*issues = append(*issues, fmt.Sprintf("Header must start with %s, got '%s'",
			r.RecordType, atRange(rec, 0, 3)))
	}
	if len(rec) != r.Length {
		*issues = append(*issues, fmt.Sprintf("Header length must be %d, got %d", r.Length, len(rec)))
	}
	if atRange(rec, 3, 7) != r.Bitmap {
		*issues = append(*issues, fmt.Sprintf("Header bitmap must be %s, got '%s'",
			r.Bitmap, atRange(rec, 3, 7)))
	}
	fields(rec, r.Fields, issues)
}

// validateTrailer checks the TC001 record, including the declared record count.
func validateTrailer(rec string, txnCount int, issues *[]string) {
	r := mustRules().Trailer
	if atRange(rec, 0, 3) != r.RecordType {
		*issues = append(*issues, fmt.Sprintf("Trailer must start with %s, got '%s'",
			r.RecordType, atRange(rec, 0, 3)))
	}
	if len(rec) != r.Length {
		*issues = append(*issues, fmt.Sprintf("Trailer length must be %d, got %d", r.Length, len(rec)))
	}
	if atRange(rec, 3, 7) != r.Bitmap {
		*issues = append(*issues, fmt.Sprintf("Trailer bitmap must be %s, got '%s'",
			r.Bitmap, atRange(rec, 3, 7)))
	}
	fields(rec, r.Fields, issues)

	declared, err := strconv.Atoi(strings.TrimSpace(atRange(rec, 7, 17)))
	if err == nil {
		actual := txnCount + 2
		if declared != actual {
			*issues = append(*issues, fmt.Sprintf(
				"Trailer record count mismatch: declared=%d, actual (incl. header+trailer)=%d",
				declared, actual))
		}
	}
}

// validateTransaction checks one TC1xx record: structure, per-block field rules
// and the cross-field rules UnionPay requires.
func validateTransaction(rec string, issues *[]string) {
	tr := mustRules().Transaction
	tc := atRange(rec, 0, 3)
	bitmap := atRange(rec, 3, 7)

	if !contains(tr.TransactionCodes, tc) {
		*issues = append(*issues, fmt.Sprintf("Invalid transaction code '%s'. Expected one of %v",
			tc, tr.TransactionCodes))
	}
	if !contains(tr.BitmapValid, bitmap) {
		*issues = append(*issues, fmt.Sprintf("Invalid bitmap '%s'. Expected one of %v",
			bitmap, tr.BitmapValid))
	}

	blocks := parseBlocks(bitmap)
	expected := 0
	for _, b := range blocks {
		expected += tr.BlockLengths[strconv.Itoa(b)]
	}
	if len(rec) != expected {
		*issues = append(*issues, fmt.Sprintf("Transaction record length must be %d (blocks %v), got %d",
			expected, blocks, len(rec)))
	}

	cursor := 0
	var block2 string
	for _, b := range blocks {
		bl := tr.BlockLengths[strconv.Itoa(b)]
		if cursor+bl > len(rec) {
			break
		}
		data := rec[cursor : cursor+bl]
		if b == 2 {
			block2 = data
		}
		fields(data, tr.Fields[strconv.Itoa(b)], issues)
		cursor += bl
	}

	// Cross-field: a sale (TC 100) is full presentment, so the transaction
	// feature indicator is a space, never 'F'.
	if tc == "100" {
		if f := at(rec, 230); f != " " {
			*issues = append(*issues, fmt.Sprintf(
				"Sale / settlement (TC 100) must use a space for the transaction feature indicator (pos 231); found '%s'", f))
		}
		// The original authorization type must be 100 (fixed) or 101 (estimated),
		// never blank.
		if o := atRange(rec, 248, 251); o != "100" && o != "101" {
			*issues = append(*issues, fmt.Sprintf(
				"Sale / settlement (TC 100) must carry the original authorization type 100 (fixed-amount) or 101 (estimated-amount) at other_information pos 249-251; found %q", o))
		}
	}

	// Cross-field: a refund (TC 101) reverses the original, so it carries no
	// fresh chip data.
	if tc == "101" {
		if f := at(rec, 230); f != "R" {
			*issues = append(*issues, fmt.Sprintf(
				"Refund (TC 101) must use 'R' for the transaction feature indicator (pos 231); found %q", f))
		}
		if o := atRange(rec, 248, 251); o != "   " {
			*issues = append(*issues, fmt.Sprintf(
				"Refund (TC 101) must fill the original authorization type (other_information pos 249-251) with spaces; found %q", o))
		}
		if block2 != "" && strings.Trim(block2, " ") != "" {
			*issues = append(*issues,
				"Refund (TC 101) must omit Block 2 or fill it entirely with blanks; found non-blank Block 2 data")
		}
	}
}

// ValidateFileData validates raw file bytes. Records are separated by CRLF and
// the last record is NOT terminated by a line break, matching what the
// generator writes.
func ValidateFileData(fileName string, data []byte) *Report {
	rep := &Report{File: fileName}
	text := strings.ReplaceAll(string(data), "\r\n", "\n")
	var lines []string
	for _, l := range strings.Split(text, "\n") {
		if strings.TrimSpace(l) != "" {
			lines = append(lines, l)
		}
	}
	rep.Records = len(lines)
	if len(lines) == 0 {
		return rep
	}

	header := lines[0]
	trailer := lines[len(lines)-1]
	rep.HeaderLen = len(header)
	rep.TrailerLen = len(trailer)

	var issues []string
	if atRange(header, 0, 3) != "000" {
		issues = append(issues, "First record is not the TC000 header")
	}
	if atRange(trailer, 0, 3) != "001" {
		issues = append(issues, "Last record is not the TC001 trailer")
	}

	validateHeader(header, &issues)
	issues = append(issues, validateFileDateMatch(header, fileName)...)

	txnCount := 0
	for _, l := range lines[1 : len(lines)-1] {
		tc := atRange(l, 0, 3)
		switch {
		case contains(txnTypes, tc):
			txnCount++
			validateTransaction(l, &issues)
		case !contains(recordTypes, tc):
			issues = append(issues, fmt.Sprintf("Unknown record type '%s'", tc))
		}
	}
	rep.Transactions = txnCount

	validateTrailer(trailer, txnCount, &issues)

	rep.Issues = issues
	return rep
}

// ValidateFile reads and validates a settlement file.
func ValidateFile(path string) (*Report, error) {
	data, err := os.ReadFile(path)
	if err != nil {
		return nil, err
	}
	return ValidateFileData(filepath.Base(path), data), nil
}

// validateFileDateMatch checks that the file name date (YYMMDD at positions 4-9)
// and the header batch date (pos 19-26, YYYYMMDD) are the same day, per Part III
// section 2.1 and Table 9.
func validateFileDateMatch(header, fileName string) []string {
	nameDate := parseNameDate(fileName)
	if nameDate == "" {
		return []string{fmt.Sprintf(
			"Cannot parse file date from file name '%s' (expected YYMMDD at positions 4-9)", fileName)}
	}
	batch := atRange(header, 18, 26)
	if len(batch) != 8 {
		return nil // the batch_date field rule already reports this
	}
	if "20"+nameDate != batch {
		return []string{fmt.Sprintf(
			"File name date (%s) and header batch date (%s) must be the same day", nameDate, batch)}
	}
	return nil
}

// parseNameDate extracts YYMMDD from an OFCYYMMDD5?C style file name.
func parseNameDate(name string) string {
	base := filepath.Base(name)
	if len(base) < 9 {
		return ""
	}
	d := base[3:9]
	for _, c := range d {
		if c < '0' || c > '9' {
			return ""
		}
	}
	return d
}
