// Package eif parses Mercury EIF files (FRRC ">"-delimited records) and
// reconciles their batch/recap totals. It is the Go port of
// switch/NSS/eif_decode.py + eif_validate.py.
package eif

import (
	"bufio"
	"io"
	"os"
	"strings"
)

// fieldMap maps a record type (the second ">"-delimited field) to its ordered
// field names. Mirrors FIELD_MAP in eif_decode.py.
var fieldMap = map[string][]string{
	"UX": {"TRANS", "FUNCD", "SFTER", "RCPNO", "DFTER", "CURKY", "RCPDT", "RCPPTY", "STLCUR"},
	"UH": {"TRANS", "FUNCD", "SFTER", "RCPNO", "DFTER", "BATCH", "RCPDT"},
	"UT": {"TRANS", "FUNCD", "SFTER", "RCPNO", "DFTER", "BATCH", "BTNCR", "BTACR", "BTNDR", "BTADR"},
	"UY": {
		"TRANS", "FUNCD", "SFTER", "RCPNO", "DFTER",
		"RCNCR", "RCACR", "RCNDR", "RCADR", "DRATE",
		"RNAMT", "ACRKY", "AGAMT", "ACAMT", "NEWRN",
		"BGAMT", "BCAMT",
	},
	"XD": {
		"TRANS", "FUNCD", "SFTER", "RCPNO", "DFTER",
		"BATCH", "SEQNO", "ACCT", "CAMTR", "CHGDT",
		"DATYP", "CHTYP", "ESTAB", "LCITY", "GEOCD",
		"APPCD", "TYPCH", "REFNO", "ANBR", "SENUM",
		"BLCUR", "BLAMT", "INTES", "ESTST", "ESTCO",
		"ESTZP", "ESTPN", "MSCCD", "MCCCD", "TAX1",
		"TAX2", "ORIGD", "RRN", "TERMID", "CUSRF3",
		"CUSRF4", "CUSRF5", "CUSRF6", "CHOLDP",
		"CARDP", "CPTRM", "ECI", "CAVV", "NRID",
		"CRDINP", "SURFEE", "TRMTYP", "AQGEO",
		"VCRDD", "TKNID", "TKRQID", "TKLVL",
		"CVVRST", "AUTYP", "AURCDE", "SECFAR",
		"CVVIND", "AUTHTR", "VERACT", "IPADDR",
		"SCAEXE", "TRAIND", "ORNRID", "OFFIND",
	},
	"XM": {
		"TRANS", "FUNCD", "SFTER", "RCPNO", "DFTER",
		"BATCH", "SEQNO", "SUSEQ",
		"CAPPSN", "CAIDT", "CAIPFL", "CATCTR", "CACRG", "CAUCN",
		"CAMTA", "CAMTO", "CCPIF", "CCVMR",
		"CDFDE", "CDISN",
		"CADA1", "CADAT", "CISRT",
		"CTRMG", "CTAVN", "CTRMC", "CTRMT", "CTRMR", "CTRND", "CTRNT", "CTRNC", "CUNPN",
	},
	"XC": {"TRANS", "FUNCD", "SFTER", "RCPNO", "DFTER", "BATCH", "SEQNO", "SUSEQ", "SCGMT", "SCDAT", "LCTIM", "LCDAT", "ATMID"},
	"MC": {"TRANS", "FUNCD", "SFTER", "RCPNO", "DFTER", "BATCH", "SEQNO", "SUSEQ", "CBAMT"},
}

// Record is a decoded EIF line. Fields is nil for an unknown record type (Raw
// then holds the raw ">"-split fields).
type Record struct {
	Type   string
	Fields map[string]string
	Raw    []string
	LineNo int
}

// Get returns the named field value ("" when absent).
func (r *Record) Get(name string) string {
	if r == nil || r.Fields == nil {
		return ""
	}
	return r.Fields[name]
}

// Txn groups an XD detail record with its optional continuations.
type Txn struct {
	Detail   *Record
	Emv      *Record
	Atm      *Record
	Cashback *Record
}

// Batch is a UH header, its transactions and a UT trailer.
type Batch struct {
	Header       *Record
	Transactions []*Txn
	Trailer      *Record
}

// Result is a parsed EIF file.
type Result struct {
	RecapHeader  *Record
	Batches      []*Batch
	RecapTrailer *Record
}

// MaskPAN mirrors mask_pan: keep the first 6 and last 4 digits.
func MaskPAN(pan string) string {
	if pan == "" || len(pan) < 10 {
		return pan
	}
	return pan[:6] + strings.Repeat("*", len(pan)-10) + pan[len(pan)-4:]
}

// DecodeLine mirrors decode_line. Returns nil when the line has fewer than two
// fields.
func DecodeLine(line string) *Record {
	fields := strings.Split(strings.TrimRight(line, "\r\n"), ">")
	for i := range fields {
		fields[i] = strings.TrimSpace(fields[i])
	}
	if len(fields) < 2 {
		return nil
	}
	funcd := fields[1]
	names, known := fieldMap[funcd]
	if !known {
		return &Record{Type: funcd, Raw: fields}
	}
	rec := &Record{Type: funcd, Fields: make(map[string]string, len(names))}
	for i, name := range names {
		if i < len(fields) {
			rec.Fields[name] = fields[i]
		} else {
			rec.Fields[name] = ""
		}
	}
	return rec
}

// Parse reads EIF records from r.
func Parse(r io.Reader) (*Result, error) {
	result := &Result{}
	scanner := bufio.NewScanner(r)
	scanner.Buffer(make([]byte, 0, 64*1024), 4*1024*1024)
	lineNo := 0
	var currentBatch *Batch
	var currentTxn *Txn
	for scanner.Scan() {
		lineNo++
		line := strings.TrimSpace(scanner.Text())
		if line == "" {
			continue
		}
		rec := DecodeLine(line)
		if rec == nil {
			continue
		}
		rec.LineNo = lineNo
		switch rec.Type {
		case "UX":
			result.RecapHeader = rec
		case "UH":
			currentBatch = &Batch{Header: rec}
			result.Batches = append(result.Batches, currentBatch)
		case "XD":
			if rec.Fields != nil {
				rec.Fields["ACCT"] = MaskPAN(rec.Fields["ACCT"])
			}
			currentTxn = &Txn{Detail: rec}
			if currentBatch != nil {
				currentBatch.Transactions = append(currentBatch.Transactions, currentTxn)
			}
		case "XM":
			if currentTxn != nil {
				currentTxn.Emv = rec
			}
		case "XC":
			if currentTxn != nil {
				currentTxn.Atm = rec
			}
		case "MC":
			if currentTxn != nil {
				currentTxn.Cashback = rec
			}
		case "UT":
			if currentBatch != nil {
				currentBatch.Trailer = rec
			}
		case "UY":
			result.RecapTrailer = rec
		}
	}
	if err := scanner.Err(); err != nil {
		return nil, err
	}
	return result, nil
}

// ParseFile parses the EIF file at path.
func ParseFile(path string) (*Result, error) {
	f, err := os.Open(path)
	if err != nil {
		return nil, err
	}
	defer f.Close()
	return Parse(f)
}
