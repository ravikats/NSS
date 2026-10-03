package outsvc

import (
	"os"
	"reflect"
	"regexp"
	"strings"
	"testing"
	"time"
)

// Every *_CENTRE_PROC_DATE / *_FILE_PROC_DATE / *_TXN_DATE / *_LOCAL_DATE_TIME
// column is a DATE in the replica, and Oracle only accepts a time.Time bind
// there: a Go string goes through an implicit TO_DATE with NLS_DATE_FORMAT and
// fails with ORA-01861. The mastercard entity/bind pair regressed exactly that
// way (string field + nullStr), which silently aborted every moveWorkToData.
func TestDateColumnsBindAsTime(t *testing.T) {
	src, err := os.ReadFile("store.go")
	if err != nil {
		t.Fatal(err)
	}
	// Columns whose entity field must be *time.Time / time.Time.
	dateCols := map[string]bool{
		"MCT_CENTRE_PROC_DATE": true, "VTD_CENTRE_PROC_DATE": true,
		"MAT_CENTRE_PROC_DATE": true, "UPT_CENTRE_PROC_DATE": true,
		"JWN_CENTRE_PROC_DATE": true,
		"MCT_TXN_DATE":         true, "MCT_LOCAL_DATE_TIME": true, "MCT_OUT_FILE_DATE": true,
	}
	re := regexp.MustCompile(`nullStr\(e\.([A-Za-z]+)\)`)
	for _, m := range re.FindAllStringSubmatch(string(src), -1) {
		_ = m
	}

	// Walk every INSERT/SELECT column list in store.go together with the bind
	// list that follows it: a DATE column must never be fed by nullStr.
	ins := regexp.MustCompile(`(?s)INSERT INTO (\w+) \((.*?)\) VALUES \(:1,:2(.*?)\)\n\tfor _, e := range ents \{\n\t\t_, err := s\.db\.ExecContext\(ctx, sqlStmt,\n(.*?)\)\n\t\tif err != nil`)
	for _, m := range ins.FindAllStringSubmatch(string(src), -1) {
		table, colBlock, binds := m[1], m[2], m[4]
		if !strings.Contains(colBlock, "CENTRE_PROC_DATE") &&
			!strings.Contains(colBlock, "TXN_DATE") &&
			!strings.Contains(colBlock, "LOCAL_DATE_TIME") {
			continue
		}
		cols := strings.Split(colBlock, ",")
		args := splitBinds(binds)
		if len(cols) != len(args) {
			t.Errorf("%s: %d columns but %d binds", table, len(cols), len(args))
			continue
		}
		for i, c := range cols {
			c = strings.TrimSpace(c)
			if !dateCols[c] {
				continue
			}
			if strings.HasPrefix(args[i], "nullStr(") {
				t.Errorf("%s.%s (bind %d) is a DATE column bound with %s — Oracle raises ORA-01861; use nullTimeP",
					table, c, i+1, args[i])
			}
		}
	}
}

// TestCentreProcDateIsTime guards the entity side of the same rule.
func TestCentreProcDateIsTime(t *testing.T) {
	for _, ent := range []struct {
		name string
		v    any
	}{
		{"McAcqTxnWorkEntity", McAcqTxnWorkEntity{}},
		{"McAcqTxnDataEntity", McAcqTxnDataEntity{}},
		{"VisaAcqTxnWorkEntity", VisaAcqTxnWorkEntity{}},
		{"MercuryAcqTxnWorkEntity", MercuryAcqTxnWorkEntity{}},
	} {
		typ := reflect.TypeOf(ent.v)
		f, ok := typ.FieldByName("CentreProcDate")
		if !ok {
			t.Errorf("%s has no CentreProcDate field", ent.name)
			continue
		}
		if f.Type != reflect.TypeOf((*time.Time)(nil)) {
			t.Errorf("%s.CentreProcDate is %s, want *time.Time (DATE column)", ent.name, f.Type)
		}
	}
}

// splitBinds splits the ExecContext argument list on top-level commas.
func splitBinds(s string) []string {
	var out []string
	depth, start := 0, 0
	for i, r := range s {
		switch r {
		case '(', '[':
			depth++
		case ')', ']':
			depth--
		case ',':
			if depth == 0 {
				out = append(out, strings.TrimSpace(s[start:i]))
				start = i + 1
			}
		}
	}
	last := strings.TrimSpace(s[start:])
	if last != "" {
		out = append(out, last)
	}
	return out
}
