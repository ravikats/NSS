package outsvc

import (
	"strings"
)

// Jaywan XML generation (UAE Switch Clearing Specification V1.3).
//
// The output matches jaywan.xml byte-for-byte: a single line carrying the XML
// declaration plus <File><Hdr>...</Hdr><TxnBlock>, one <Txn> per line, and a
// final line with </TxnBlock><Trl>...</Trl></File>. Line separators are CRLF
// and there is no trailing newline. Tags inside <Hdr>, <Txn> and <Trl> are
// concatenated without whitespace.

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

// jaywanTag writes <name>value</name> with no surrounding whitespace.
func jaywanTag(name, value string) string {
	return "<" + name + ">" + jaywanXmlEscape(value) + "</" + name + ">"
}

// jaywanTxnTag is a single ordered field of the <Txn> element. A nil value
// means the tag is omitted (NON_NULL); an empty string is still emitted.
type jaywanTxnTag struct {
	name  string
	value *string
}

func jaywanStrPtr(s string) *string { return &s }

// buildJaywanHeader renders the <Hdr> block. Every field is always present.
func buildJaywanHeader(h *jaywanXmlHeaderVO) string {
	var b strings.Builder
	b.WriteString("<Hdr>")
	b.WriteString(jaywanTag("nMTI", h.NMTI))
	b.WriteString(jaywanTag("nFunCd", h.NFunCd))
	b.WriteString(jaywanTag("nRecNum", h.NRecNum))
	b.WriteString(jaywanTag("nDtTmFlGen", h.NDtTmFlGen))
	b.WriteString(jaywanTag("nMemInstCd", h.NMemInstCd))
	b.WriteString(jaywanTag("nUnFlNm", h.NUnFlNm))
	b.WriteString(jaywanTag("nFlCatg", h.NFlCatg))
	b.WriteString(jaywanTag("nVerNum", h.NVerNum))
	b.WriteString("</Hdr>")
	return b.String()
}

// buildJaywanTxn renders a single <Txn> block. Only non-nil tags are emitted,
// in the declaration order of JaywanXmlTransactionsValueObject.
func buildJaywanTxn(tags []jaywanTxnTag) string {
	var b strings.Builder
	b.WriteString("<Txn>")
	for _, t := range tags {
		if t.value == nil {
			continue
		}
		b.WriteString(jaywanTag(t.name, *t.value))
	}
	b.WriteString("</Txn>")
	return b.String()
}

// buildJaywanTrailer renders the <Trl> block.
func buildJaywanTrailer(t *jaywanXmlTrailerVO) string {
	var b strings.Builder
	b.WriteString("<Trl>")
	b.WriteString(jaywanTag("nMTI", t.NMTI))
	b.WriteString(jaywanTag("nFunCd", t.NFunCd))
	b.WriteString(jaywanTag("nRecNum", t.NRecNum))
	b.WriteString(jaywanTag("nUnFlNm", t.NUnFlNm))
	b.WriteString(jaywanTag("nTxnCnt", t.NTxnCnt))
	b.WriteString(jaywanTag("nRnTtlAmt", t.NRnTtlAmt))
	b.WriteString("</Trl>")
	return b.String()
}

// buildJaywanFile renders the whole document with the compact V1.3 layout:
// header and <TxnBlock> on the declaration line, one <Txn> per line, and
// </TxnBlock><Trl>...</Trl></File> as the final line. CRLF separators, no
// trailing newline.
func buildJaywanFile(header string, txns []string, trailer string) string {
	var b strings.Builder
	b.WriteString(jaywanXmlDecl)
	b.WriteString("<File>")
	b.WriteString(header)
	b.WriteString("<TxnBlock>\r\n")
	for i, t := range txns {
		b.WriteString(t)
		if i < len(txns)-1 {
			b.WriteString("\r\n")
		}
	}
	b.WriteString("\r\n")
	b.WriteString("</TxnBlock>")
	b.WriteString(trailer)
	b.WriteString("</File>")
	return b.String()
}