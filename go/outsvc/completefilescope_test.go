package outsvc

import (
	"go/ast"
	"go/parser"
	"go/token"
	"os"
	"path/filepath"
	"regexp"
	"strings"
	"testing"
)

// The five non-Mastercard networks used to complete POS rows with an unscoped
// query: it matched ANY work row at gen_status 4, regardless of which file
// produced it. Two overlapping generations of the same network therefore
// completed each other's transactions -- and because the archive step then
// deleted the work rows, any row whose own file had not completed was left at
// PTR_GEN_STATUS=4 forever with no work row left to drive it (the "STAGED but
// never COMPLETED" population found on Mastercard).
//
// The fix scopes each completion to one file. These tests pin that wiring.

func TestCompletionQueriesAreFileScoped(t *testing.T) {
	cases := []struct {
		file    string
		fn      string
		col     string
		workTbl string
	}{
		{"visa_store.go", "CompleteVisaPosStatus", "VTD_FILE_ID", "VISA_ACQ_TXN_WORK"},
		{"amex_store.go", "CompleteAmexPosStatus", "ATD_FILE_ID", "AMEX_ACQ_TXN_WORK"},
		{"jaywan_store.go", "CompleteJaywanPosStatus", "JWN_FILE_ID", "JAYWAN_ACQ_TXN_WORK"},
		{"mercury_store.go", "CompleteMercuryPosStatus", "MAT_FILE_ID", "MERCURY_ACQ_TXN_WORK"},
		{"unionpay_store.go", "CompleteUnionPayPosStatus", "UPT_FILE_ID", "UP_ACQ_TXN_WORK"},
		{"store.go", "CompletePosStatusForFile", "MCT_FILE_ID", "MC_ACQ_TXN_WORK"},
	}
	for _, tc := range cases {
		t.Run(tc.file, func(t *testing.T) {
			body := funcBody(t, readStoreSource(t, tc.file), tc.fn)
			if body == "" {
				t.Fatalf("%s: function %s not found", tc.file, tc.fn)
			}
			if !strings.Contains(body, tc.workTbl) {
				t.Errorf("%s: %s does not reference %s", tc.file, tc.fn, tc.workTbl)
			}
			// Without "<COL> = :2" the query completes every network's
			// in-flight rows, not just this run's. The optional alias prefix
			// matches the "vis.VTD_GEN_STATUS = 4 AND vis.VTD_FILE_ID = :2"
			// form the queries actually use.
			re := regexp.MustCompile(`(?i)GEN_STATUS\s*=\s*4\s+AND\s+(?:\w+\.)?` + tc.col + `\s*=\s*:2`)
			if !re.MatchString(body) {
				t.Errorf("%s: %s work-row predicate is not (gen_status 4 AND %s = :2); it is unscoped by file",
					tc.file, tc.fn, tc.col)
			}
		})
	}
}

// TestCompletionCallSitesPassTheWrittenFile guards the other half of the fix:
// the identifier each network writes into its FILE_ID column is not uniform.
// Visa stamps the generated file id; Amex, Mercury, UnionPay and Jaywan stamp
// the file NAME. Passing the wrong one makes the scoped query match nothing and
// strands every POS row at gen_status 4 -- silently, because the UPDATE simply
// affects zero rows.
func TestCompletionCallSitesPassTheWrittenFile(t *testing.T) {
	cases := []struct {
		file string
		fn   string
		want string
	}{
		{"visa.go", "CompleteVisaPosStatus", "*fileId"},
		{"amex.go", "CompleteAmexPosStatus", "fileName"},
		{"mercury.go", "CompleteMercuryPosStatus", "fileName"},
		{"unionpay.go", "CompleteUnionPayPosStatus", "fileName"},
		{"jaywan.go", "CompleteJaywanPosStatus", "fileName"},
	}
	for _, tc := range cases {
		t.Run(tc.file, func(t *testing.T) {
			body := funcBody(t, readStoreSource(t, tc.file), tc.fn)
			if body == "" {
				t.Fatalf("%s: call to %s not found", tc.file, tc.fn)
			}
			want := tc.fn + "(ctx, insCode, " + tc.want + ")"
			if !strings.Contains(body, want) {
				t.Errorf("%s: %s must be called as %q (that is what this network writes into FILE_ID)", tc.file, tc.fn, want)
			}
		})
	}
}

// TestUnionPayCompletesEveryBatch guards the batch loop. UnionPay writes one
// file per file sequence, so completing once after the loop -- even with a
// correct file filter -- would leave every batch but the last with POS rows
// still at gen_status 4.
func TestUnionPayCompletesEveryBatch(t *testing.T) {
	f := parseStoreFile(t, "unionpay.go")
	var host *ast.FuncDecl
	var totalCalls int
	ast.Inspect(f, func(n ast.Node) bool {
		fn, ok := n.(*ast.FuncDecl)
		if !ok || fn.Body == nil {
			return true
		}
		calls := 0
		ast.Inspect(fn.Body, func(m ast.Node) bool {
			if c, ok := m.(*ast.CallExpr); ok && renderFuncName(c) == "CompleteUnionPayPosStatus" {
				calls++
			}
			return true
		})
		totalCalls += calls
		if calls > 0 && host == nil {
			host = fn
		}
		return true
	})
	if totalCalls == 0 || host == nil {
		t.Fatal("CompleteUnionPayPosStatus is never called anywhere in unionpay.go")
	}
	if totalCalls > 1 {
		t.Errorf("CompleteUnionPayPosStatus is called %d times; it must appear once, inside the batch loop", totalCalls)
	}
	inLoop := false
	checkLoop := func(n ast.Node) bool {
		if c, ok := n.(*ast.CallExpr); ok && renderFuncName(c) == "CompleteUnionPayPosStatus" {
			inLoop = true
		}
		return true
	}
	for _, stmt := range host.Body.List {
		// `for ... range` is a RangeStmt, not a ForStmt; both count.
		switch loop := stmt.(type) {
		case *ast.ForStmt:
			ast.Inspect(loop, checkLoop)
		case *ast.RangeStmt:
			ast.Inspect(loop, checkLoop)
		}
	}
	if !inLoop {
		t.Errorf("%s: CompleteUnionPayPosStatus must run inside the batch loop: each iteration writes a distinct file name",
			host.Name.Name)
	}
}

func readStoreSource(t *testing.T, name string) string {
	t.Helper()
	b, err := os.ReadFile(filepath.Join(".", name))
	if err != nil {
		t.Fatalf("read %s: %v", name, err)
	}
	return string(b)
}

func parseStoreFile(t *testing.T, name string) *ast.File {
	t.Helper()
	f, err := parser.ParseFile(token.NewFileSet(), filepath.Join(".", name), nil, 0)
	if err != nil {
		t.Fatalf("parse %s: %v", name, err)
	}
	return f
}

// funcBody returns the brace-matched body of the named top-level method, whether
// it is defined or called (a call site resolves to the enclosing statement's
// text, which is enough to assert the argument list).
func funcBody(t *testing.T, src, name string) string {
	t.Helper()
	idx := strings.Index(src, name+"(ctx, insCode")
	if idx < 0 {
		idx = strings.Index(src, "func (s *oracleStore) "+name+"(")
		if idx < 0 {
			return ""
		}
		open := strings.Index(src[idx:], "{")
		if open < 0 {
			return ""
		}
		depth := 0
		for i := idx + open; i < len(src); i++ {
			switch src[i] {
			case '{':
				depth++
			case '}':
				depth--
				if depth == 0 {
					return src[idx+open : i+1]
				}
			}
		}
		return src[idx:]
	}
	// Call site: return a window around it so argument assertions can match.
	end := idx + len(name) + 200
	if end > len(src) {
		end = len(src)
	}
	return src[idx:end]
}

func renderFuncName(n ast.Node) string {
	switch v := n.(type) {
	case *ast.CallExpr:
		return renderFuncName(v.Fun)
	case *ast.Ident:
		return v.Name
	case *ast.SelectorExpr:
		return renderFuncName(v.Sel)
	}
	return ""
}
