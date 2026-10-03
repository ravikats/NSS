// Package ipm parses and compliance-validates Mastercard IPM (EBCDIC ISO-8583)
// settlement files.
//
// It is a Go port of the Python IPMParser project
// (switch/NSS/IPMParser: parser/scanner.py, parser/de_reader.py,
// parser/bitmap.py, parser/field_reader.py, parser/pds.py,
// parser/compliance.py and ipm_parser.py). The metadata the Python code
// loads from metadata/{de,pds,compliance_rules}.json is embedded here so
// the validator is a self-contained binary.
//
// The pipeline is: Scanner -> (parse_1644 | parse_1240) -> PDS -> RulesEngine.
package ipm

import (
	_ "embed"
	"encoding/json"
	"fmt"
)

//go:embed metadata/de.json
var deJSON []byte

//go:embed metadata/pds.json
var pdsJSON []byte

//go:embed metadata/compliance_rules.json
var rulesJSON []byte

// FieldDef is one entry of metadata/de.json — the format and length used to
// read a single data element out of a record.
type FieldDef struct {
	Name      string `json:"name"`
	Format    string `json:"format"`
	Length    int    `json:"length"`
	MaxLength int    `json:"max_length"`
	Type      string `json:"type"`
	Encoding  string `json:"encoding"`
	Parser    string `json:"parser"`
}

// pdsMeta is one entry of metadata/pds.json. Only the name is used at
// runtime; tag/page are documentation.
type pdsMeta struct {
	Tag  string `json:"tag"`
	Name string `json:"name"`
	Page int    `json:"page"`
}

// Metadata holds the loaded DE and PDS dictionaries.
type Metadata struct {
	// DE maps the 1-based data element number to its definition.
	DE map[int]FieldDef
	// PDS maps a 4-digit PDS id to its name.
	PDS map[string]string
}

// LoadMetadata parses the embedded de.json and pds.json.
func LoadMetadata() (*Metadata, error) {
	m := &Metadata{DE: make(map[int]FieldDef), PDS: make(map[string]string)}

	raw := make(map[string]FieldDef)
	if err := json.Unmarshal(deJSON, &raw); err != nil {
		return nil, fmt.Errorf("ipm: parse de.json: %w", err)
	}
	for k, v := range raw {
		var n int
		if _, err := fmt.Sscanf(k, "%d", &n); err != nil {
			return nil, fmt.Errorf("ipm: de.json: bad element key %q", k)
		}
		m.DE[n] = v
	}

	rawPDS := make(map[string]pdsMeta)
	if err := json.Unmarshal(pdsJSON, &rawPDS); err != nil {
		return nil, fmt.Errorf("ipm: parse pds.json: %w", err)
	}
	for k, v := range rawPDS {
		name := v.Name
		if name == "" {
			name = v.Tag
		}
		m.PDS[k] = name
	}
	return m, nil
}
