package outsvc

import (
	"math"
	"strconv"
	"strings"
)

// Jaywan XML generation. The Java side uses Jackson's XmlMapper with a pretty
// printer (2-space indent) and an XML declaration patched to
// standalone="no". This file reproduces that output, including:
//
//   - the root <File> element with <Hdr>/<TxnBlock>/<Trl> children,
//   - the header where every tag is emitted (nFlRejRsnCd is set to null in
//     Java and serialized as an empty element),
//   - the per-transaction element whose null fields are omitted
//     (@JsonInclude(NON_NULL)),
//   - Java Double.toString semantics for the numeric-to-string fields.

const jaywanXmlDecl = `<?xml version="1.0" encoding="UTF-8" standalone="no"?>`

// jaywanXmlEscape escapes text content the way Jackson's XML writer does
// (&, < and >); quotes in text are left untouched.
func jaywanXmlEscape(s string) string {
	var b strings.Builder
	b.Grow(len(s) + 8)
	for i := 0; i < len(s); i++ {
		switch s[i] {
		case '&':
			b.WriteString("&amp;")
		case '<':
			b.WriteString("&lt;")
		case '>':
			b.WriteString("&gt;")
		default:
			b.WriteByte(s[i])
		}
	}
	return b.String()
}

// jaywanTag writes <name>value</name> at the given indent level (2 spaces per
// level).
func jaywanTag(indent int, name, value string) string {
	return strings.Repeat("  ", indent) + "<" + name + ">" + jaywanXmlEscape(value) + "</" + name + ">"
}

// jaywanEmptyTag writes <name/> (Jackson serializes a null String as an empty
// element).
func jaywanEmptyTag(indent int, name string) string {
	return strings.Repeat("  ", indent) + "<" + name + "/>"
}

// jaywanTxnTag is a single ordered field of the <Txn> element. A nil value
// means the tag is omitted (NON_NULL); an empty string is still emitted.
type jaywanTxnTag struct {
	name  string
	value *string
}

func jaywanStrPtr(s string) *string { return &s }

// buildJaywanHeader renders the <Hdr> block. Every field is always present;
// nFlRejRsnCd is set to null by the Java service and appears as an empty tag.
func buildJaywanHeader(h *jaywanXmlHeaderVO) string {
	var b strings.Builder
	b.WriteString("<Hdr>\n")
	b.WriteString(jaywanTag(1, "nMTI", h.NMTI) + "\n")
	b.WriteString(jaywanTag(1, "nFunCd", h.NFunCd) + "\n")
	b.WriteString(jaywanTag(1, "nRecNum", h.NRecNum) + "\n")
	b.WriteString(jaywanTag(1, "nDtTmFlGen", h.NDtTmFlGen) + "\n")
	b.WriteString(jaywanTag(1, "nDtSet", h.NDtSet) + "\n")
	b.WriteString(jaywanTag(1, "nMemInstCd", h.NMemInstCd) + "\n")
	b.WriteString(jaywanTag(1, "nUnFlNm", h.NUnFlNm) + "\n")
	b.WriteString(jaywanTag(1, "nProdCd", h.NProdCd) + "\n")
	b.WriteString(jaywanTag(1, "nFlCatg", h.NFlCatg) + "\n")
	b.WriteString(jaywanTag(1, "nVerNum", h.NVerNum) + "\n")
	b.WriteString(jaywanTag(1, "nFlRejInd", h.NFlRejInd) + "\n")
	b.WriteString(jaywanEmptyTag(1, "nFlRejRsnCd"))
	b.WriteString("\n</Hdr>")
	return b.String()
}

// buildJaywanTxn renders a single <Txn> block. Only non-nil tags are emitted,
// in the declaration order of JaywanXmlTransactionsValueObject.
func buildJaywanTxn(tags []jaywanTxnTag) string {
	var b strings.Builder
	b.WriteString("<Txn>\n")
	for _, t := range tags {
		if t.value == nil {
			continue
		}
		b.WriteString(jaywanTag(1, t.name, *t.value) + "\n")
	}
	b.WriteString("</Txn>")
	return b.String()
}

// buildJaywanTrailer renders the <Trl> block.
func buildJaywanTrailer(t *jaywanXmlTrailerVO) string {
	var b strings.Builder
	b.WriteString("<Trl>\n")
	b.WriteString(jaywanTag(1, "nMTI", t.NMTI) + "\n")
	b.WriteString(jaywanTag(1, "nFunCd", t.NFunCd) + "\n")
	b.WriteString(jaywanTag(1, "nRecNum", t.NRecNum) + "\n")
	b.WriteString(jaywanTag(1, "nUnFlNm", t.NUnFlNm) + "\n")
	b.WriteString(jaywanTag(1, "nTxnCnt", t.NTxnCnt) + "\n")
	b.WriteString(jaywanTag(1, "nRnTtlAmt", t.NRnTtlAmt))
	b.WriteString("\n</Trl>")
	return b.String()
}

// buildJaywanFile renders the whole <File> document including the XML
// declaration (Jackson writes the declaration first, then the pretty content).
func buildJaywanFile(header string, txns []string, trailer string) string {
	var b strings.Builder
	b.WriteString(jaywanXmlDecl + "\n")
	b.WriteString("<File>\n")
	b.WriteString("  " + strings.ReplaceAll(header, "\n", "\n  ") + "\n")
	if len(txns) > 0 {
		b.WriteString("  <TxnBlock>\n")
		for _, t := range txns {
			b.WriteString("    " + strings.ReplaceAll(t, "\n", "\n    ") + "\n")
		}
		b.WriteString("  </TxnBlock>\n")
	} else {
		b.WriteString("  <TxnBlock>\n  </TxnBlock>\n")
	}
	b.WriteString("  " + strings.ReplaceAll(trailer, "\n", "\n  ") + "\n")
	b.WriteString("</File>")
	return b.String()
}

// javaDoubleString replicates Java Double.toString(double) for the values that
// appear in the Jaywan XML (conversion rates, settlement totals). Java uses
// decimal notation when 1e-3 <= |value| < 1e7 and scientific notation
// otherwise; integral values always carry ".0".
func javaDoubleString(f float64) string {
	if math.IsNaN(f) {
		return "NaN"
	}
	if math.IsInf(f, 1) {
		return "Infinity"
	}
	if math.IsInf(f, -1) {
		return "-Infinity"
	}
	neg := math.Signbit(f)
	if f == 0 {
		if neg {
			return "-0.0"
		}
		return "0.0"
	}
	a := math.Abs(f)
	s := strconv.FormatFloat(a, 'g', -1, 64)
	if !strings.ContainsRune(s, 'e') && !strings.ContainsRune(s, 'E') {
		if !strings.ContainsRune(s, '.') {
			s += ".0"
		}
		if neg {
			return "-" + s
		}
		return s
	}
	eIdx := strings.IndexAny(s, "eE")
	mant := s[:eIdx]
	expPart := s[eIdx+1:]
	expSign := 1
	if strings.HasPrefix(expPart, "-") {
		expSign = -1
		expPart = expPart[1:]
	} else if strings.HasPrefix(expPart, "+") {
		expPart = expPart[1:]
	}
	exp := 0
	for i := 0; i < len(expPart); i++ {
		exp = exp*10 + int(expPart[i]-'0')
	}
	exp *= expSign

	digits := strings.ReplaceAll(mant, ".", "")
	// value = 0.digits x 10^(exp+1); exp is the decimal exponent of the
	// leading digit (value in [1e-3, 1e7) -> decimal notation).
	if exp >= -3 && exp < 7 {
		var b strings.Builder
		if neg {
			b.WriteByte('-')
		}
		point := exp + 1 // digits before the decimal point
		switch {
		case point <= 0:
			b.WriteString("0.")
			for i := 0; i < -point; i++ {
				b.WriteByte('0')
			}
			b.WriteString(digits)
		case point >= len(digits):
			b.WriteString(digits)
			for i := 0; i < point-len(digits); i++ {
				b.WriteByte('0')
			}
			b.WriteString(".0")
		default:
			b.WriteString(digits[:point])
			b.WriteByte('.')
			b.WriteString(digits[point:])
		}
		return b.String()
	}
	var b strings.Builder
	if neg {
		b.WriteByte('-')
	}
	b.WriteByte(digits[0])
	b.WriteByte('.')
	if len(digits) > 1 {
		b.WriteString(digits[1:])
	} else {
		b.WriteByte('0')
	}
	b.WriteByte('E')
	b.WriteString(strconv.Itoa(exp))
	return b.String()
}
