package outsvc

import (
	"bytes"
	"context"
	"fmt"
	"io"
	"os"
	"sort"
	"strconv"
	"strings"
	"time"
)

// IpmOutProcessor is the Go port of com.empay.IPMProcessing.IpmOutEbcidic: it
// turns MC_ACQ_TXN_WORK rows (via VW_IPM_OUT_WORK) into a Mastercard IPM file.
type IpmOutProcessor struct {
	reconOutDir    string
	processingMode string
	store          Store
	crypto         CardCrypto
}

// NewIpmOutProcessor wires the processor with its config and dependencies.
func NewIpmOutProcessor(reconOutDir, processingMode string, store Store, crypto CardCrypto) *IpmOutProcessor {
	return &IpmOutProcessor{reconOutDir: reconOutDir, processingMode: processingMode, store: store, crypto: crypto}
}

func orEmpty(s *string) string {
	if s == nil {
		return ""
	}
	return *s
}

// jcatNull concatenates strings with Java `a + b` semantics: a nil reference
// becomes the literal "null" (String.valueOf(null)).
func jcatNull(parts ...*string) string {
	var b strings.Builder
	for _, p := range parts {
		if p == nil {
			b.WriteString("null")
		} else {
			b.WriteString(*p)
		}
	}
	return b.String()
}

// jcatEmpty mirrors Java ternary `x == null ? "" : x` concatenation.
func jcatEmpty(parts ...*string) string {
	var b strings.Builder
	for _, p := range parts {
		if p != nil {
			b.WriteString(*p)
		}
	}
	return b.String()
}

func pad11Zero(s string) string {
	return strings.ReplaceAll(fmt.Sprintf("%11s", s), " ", "0")
}

func pad16Zero(s string) string {
	return strings.ReplaceAll(fmt.Sprintf("%16s", s), " ", "0")
}

func pad8Zero(s string) string {
	return strings.ReplaceAll(fmt.Sprintf("%8s", s), " ", "0")
}

func asciiToEbcdicStr(s string) []byte {
	out := make([]byte, len(s))
	for i := 0; i < len(s); i++ {
		out[i] = asciiToEbcdic(int(s[i]))
	}
	return out
}

// IpmPro runs the whole IPM generation flow and returns the fileId ("" on
// failure, mirroring the Java null). fileType "GCO" routes to the collection
// flow; any other value runs the regular MC flow.
func (p *IpmOutProcessor) IpmPro(ctx context.Context, fileName, processorId string, seqNo, insCode, intCode int, businessDate time.Time, refSerNumber, userSerNumber int, fromDate, toDate *time.Time, fileType string) string {
	recCnt := 0
	var amount int64
	var fileID string

	if fileType == "GCO" {
		if err := p.callGCOIpmOutWorkEntity(ctx, insCode, fileName, fromDate, toDate); err != nil {
			logOutsvc("callGCOIpmOutWorkEntity()", err)
		}
	} else {
		if err := p.callIpmOutWorkEntity(ctx, insCode, fileName, fromDate, toDate); err != nil {
			logOutsvc("callIpmOutWorkEntity()", err)
		}
	}

	dir := p.reconOutDir
	if dir != "" && !strings.HasSuffix(dir, string(os.PathSeparator)) {
		dir += string(os.PathSeparator)
	}

	logf, err := os.Create(dir + fileName + ".log")
	if err != nil {
		logOutsvc("create log", err)
		return ""
	}
	defer logf.Close()

	tmp, err := os.CreateTemp(dir, "IPM.*.tmp")
	if err != nil {
		logOutsvc("create IPM.tmp", err)
		return ""
	}
	defer os.Remove(tmp.Name())
	defer tmp.Close()

	outNew, err := os.Create(dir + fileName)
	if err != nil {
		logOutsvc("create out file", err)
		return ""
	}
	defer outNew.Close()

	logf.WriteString("IPM Process Started at - " + time.Now().Format("2006/01/02 15:04:05") + "\n")

	fileID, err = p.createHeaderMessage(logf, tmp, processorId, seqNo)
	if err != nil {
		logOutsvc("createHeaderMessage()", err)
		return ""
	}

	results, err := p.store.FindIpmOutWorkByInsAndFile(ctx, insCode, fileName)
	if err != nil {
		logOutsvc("findIpmOutWork", err)
		return ""
	}
	tokens := make([]string, 0, len(results))
	seen := map[string]struct{}{}
	for _, rs := range results {
		tok := orEmpty(rs.DE002)
		if tok != "" {
			if _, ok := seen[tok]; !ok {
				seen[tok] = struct{}{}
				tokens = append(tokens, tok)
			}
		}
	}
	decrypted := p.crypto.GetCardNumber(tokens)
	if decrypted == nil {
		return ""
	}

	for _, rs := range results {
		rec, dumpMsg, err := p.buildDetail(rs, decrypted)
		if err != nil {
			logOutsvc("buildDetail", err)
			return ""
		}
		if _, err := tmp.Write(rec); err != nil {
			logOutsvc("write detail", err)
			return ""
		}
		dumpMessage(logf, dumpMsg, "ASCII")
		recCnt++
		if amt, err := strconv.ParseInt(orEmpty(rs.DE004), 10, 64); err == nil && amt > 0 {
			amount += amt
		}
	}

	if err := p.createFooterMessage(logf, tmp, strconv.FormatInt(amount, 10), strconv.Itoa(recCnt+2), processorId, seqNo); err != nil {
		logOutsvc("createFooterMessage()", err)
		return ""
	}

	if _, err := tmp.Seek(0, io.SeekStart); err != nil {
		logOutsvc("seek tmp", err)
		return ""
	}
	if err := transformIPM(tmp, outNew); err != nil {
		logOutsvc("transform IPM", err)
		return ""
	}

	if err := p.buildAndSaveSummaries(ctx, insCode, intCode, businessDate, refSerNumber, userSerNumber, fileID, fileType); err != nil {
		logOutsvc("summaries", err)
		return ""
	}

	if fileType == "GCO" {
		if err := p.completeGCO(ctx, insCode, userSerNumber, fileID); err != nil {
			logOutsvc("completeGCO", err)
			return ""
		}
	} else {
		if err := p.completeMC(ctx, insCode, userSerNumber, fileID); err != nil {
			logOutsvc("completeMC", err)
			return ""
		}
	}
	return fileID
}

// transformIPM reads the raw temp file and applies the 1012->1014 byte
// interleaving (2 zero bytes inserted after every 1012 data bytes) plus the
// final zero padding.
func transformIPM(in io.Reader, out io.Writer) error {
	buf := make([]byte, 4096)
	chrCnt := 0
	zeros := []byte{0, 0}
	for {
		n, err := in.Read(buf)
		for i := 0; i < n; i++ {
			if _, err := out.Write(buf[i : i+1]); err != nil {
				return err
			}
			chrCnt++
			if chrCnt%1012 == 0 {
				if _, err := out.Write(zeros); err != nil {
					return err
				}
			}
		}
		if err == io.EOF {
			break
		}
		if err != nil {
			return err
		}
	}
	rem1014 := 1012 - chrCnt%1012
	if _, err := out.Write(make([]byte, rem1014+2)); err != nil {
		return err
	}
	_, err := out.Write(make([]byte, 1014))
	return err
}

func (p *IpmOutProcessor) createHeaderMessage(logf io.Writer, out io.Writer, processorId string, seqNo int) (string, error) {
	var bm [16]byte
	var sb strings.Builder
	yyMMdd := time.Now().Format("060105")
	de048 := "0105025002" + yyMMdd + pad11Zero(processorId) + fmt.Sprintf("%05d", seqNo) + "0122001" + p.processingMode
	fileID := ""
	if len(de048) >= 32 {
		fileID = de048[7:32]
	}
	if err := addFieldSB(&sb, 23, "697", &bm); err != nil {
		return "", err
	}
	if err := addFieldSB(&sb, 47, de048, &bm); err != nil {
		return "", err
	}
	if err := addFieldSB(&sb, 70, "00000001", &bm); err != nil {
		return "", err
	}
	ebcdic := asciiToEbcdicStr(sb.String())
	if err := createDetails("1644", &bm, ebcdic, out); err != nil {
		return "", err
	}
	dumpMessage(logf, "1644"+string(bm[:])+sb.String(), "ASCII")
	return fileID, nil
}

// addFieldSB is a convenience wrapper over addIsoField for string builders.
func addFieldSB(sb *strings.Builder, idx int, value string, bm *[16]byte) error {
	msg, err := addIsoField(sb.String(), idx, value, bm)
	if err != nil {
		return err
	}
	sb.Reset()
	sb.WriteString(msg)
	return nil
}

func createDetails(sMTI string, bm *[16]byte, payload []byte, out io.Writer) error {
	iMsgLen := len(payload) + 16 + 4
	lengthBytes := []byte{0, 0, byte(iMsgLen / 256), byte(iMsgLen % 256)}
	bData := make([]byte, iMsgLen)
	for i := 0; i < 4; i++ {
		bData[i] = asciiToEbcdic(int(sMTI[i]))
	}
	setSecondaryBitMap(bm)
	for i := 0; i < 16; i++ {
		bData[4+i] = bm[i]
	}
	for i := 0; i < len(payload); i++ {
		bData[20+i] = payload[i]
	}
	if _, err := out.Write(lengthBytes); err != nil {
		return err
	}
	_, err := out.Write(bData)
	return err
}

// buildDetail encodes one transaction record. It returns the framed record
// bytes plus the ASCII message string used for the diagnostic dump.
func (p *IpmOutProcessor) buildDetail(rs *IpmOutWorkEntity, decrypted map[string]string) ([]byte, string, error) {
	var bm [16]byte
	var sb strings.Builder

	sMTI := orEmpty(rs.DE001)
	de002 := decrypted[orEmpty(rs.DE002)]
	var de003, de004, de012, de022, de023, de024, de025, de026, de030, de031, de032, de033, de037, de038, de040, de041, de042, de043 *string
	de003, de004, de012 = rs.DE003, rs.DE004, rs.DE012
	de022, de023, de024 = rs.DE022, rs.DE023, rs.DE024
	de025, de026, de030 = rs.DE025, rs.DE026, rs.DE030
	de031, de032, de033 = rs.DE031, rs.DE032, rs.DE033
	de037, de038, de040 = rs.DE037, rs.DE038, rs.DE040
	de041, de042, de043 = rs.DE041, rs.DE042, rs.DE043
	de049 := rs.DE049
	de054 := rs.DE054
	var de048 string
	de063 := rs.DE063
	de071 := rs.DE071
	de072 := rs.DE072
	de093 := rs.DE093
	de094 := rs.DE094
	de095 := rs.DE095

	if sMTI == "1740" {
		de012, de022, de023, de026 = nil, nil, nil, nil
		de030, de031, de032, de037, de038 = nil, nil, nil, nil, nil
		de040, de041 = nil, nil
		de048 = rs.PDS25 + jcatNull(rs.PDS137, rs.PDS148, rs.PDS165)
		de054, de063 = nil, nil
	} else {
		de048 = jcatNull(rs.PDS23, ptrStr(rs.PDS25), ptrStr(rs.PDS52), rs.PDS148, ptrStr(rs.PDS149)) +
			jcatEmpty(rs.PDS155) +
			jcatNull(rs.PDS165) +
			jcatEmpty(ptrStr(rs.PDS176), rs.PDS211, ptrStr(rs.PDS262))
	}

	var de055 *string
	pds165 := orEmpty(rs.PDS165)
	if !strings.HasSuffix(pds165, "C") {
		de0559F10 := ""
		if rs.DE055_9F10 != "" {
			de0559F10 = "9F10" + pad2Hex(len(rs.DE055_9F10)/2) + rs.DE055_9F10
		}
		de05584 := ""
		if rs.DE055_84 != "" {
			de05584 = "84" + pad2Hex(len(rs.DE055_84)/2) + rs.DE055_84
		}
		v := rs.DE055_9F26 + rs.DE055_9F27 + de0559F10 + rs.DE055_9F34 + rs.DE055_9F33 +
			rs.DE055_9F37 + rs.DE055_9F36 + rs.DE055_95 + rs.DE055_9A + rs.DE055_9C +
			rs.DE055_9F02 + rs.DE055_5F2A + rs.DE055_82 + rs.DE055_9F1A + rs.DE055_9F03 +
			de05584
		de055 = &v
		de048 = jcatNull(rs.PDS23, ptrStr(rs.PDS25), ptrStr(rs.PDS52), rs.PDS148, ptrStr(rs.PDS149), rs.PDS155, rs.PDS165) +
			jcatEmpty(ptrStr(rs.PDS176), rs.PDS211, ptrStr(rs.PDS262), ptrStr(rs.DE048_PDS0213), ptrStr(rs.DE048_PDS0170), rs.PDS0018, rs.DE048_PDS0175)
	}

	if de002 != "" {
		if err := addFieldSB(&sb, 1, de002, &bm); err != nil {
			return nil, "", err
		}
	}
	of := func(de int, v *string) error { return optionalField(&sb, &bm, de, v) }
	for _, f := range []struct {
		de  int
		val *string
	}{
		{2, de003}, {3, de004}, {11, de012}, {21, de022}, {22, de023}, {23, de024},
		{24, de025}, {25, de026}, {29, de030}, {30, de031}, {31, de032}, {32, de033},
		{36, de037}, {37, de038}, {39, de040}, {40, de041}, {41, de042}, {42, de043},
	} {
		if err := of(f.de, f.val); err != nil {
			return nil, "", err
		}
	}
	if err := addFieldSB(&sb, 47, de048, &bm); err != nil {
		return nil, "", err
	}
	for _, f := range []struct {
		de  int
		val *string
	}{
		{48, de049}, {53, de054},
	} {
		if err := of(f.de, f.val); err != nil {
			return nil, "", err
		}
	}

	ebcdic := asciiToEbcdicStr(sb.String())
	if de055 != nil && *de055 != "" {
		if err := addFieldSB(&sb, 54, *de055, &bm); err != nil {
			return nil, "", err
		}
		full := sb.String()
		lenMark := len(ebcdic) + 3
		for i := len(ebcdic); i < lenMark; i++ {
			ebcdic = append(ebcdic, asciiToEbcdic(int(full[i])))
		}
		for i := lenMark; i < len(full); i++ {
			ebcdic = append(ebcdic, full[i])
		}
	}
	for _, f := range []struct {
		de  int
		val *string
	}{
		{62, de063}, {70, de071}, {70, de072}, {92, de093}, {93, de094}, {94, de095},
	} {
		if err := of(f.de, f.val); err != nil {
			return nil, "", err
		}
	}
	full := sb.String()
	for i := len(ebcdic); i < len(full); i++ {
		ebcdic = append(ebcdic, asciiToEbcdic(int(full[i])))
	}

	rec, err := frameRecord(sMTI, &bm, ebcdic)
	if err != nil {
		return nil, "", err
	}
	dumpMsg := sMTI + string(bm[:]) + full
	return rec, dumpMsg, nil
}

func frameRecord(sMTI string, bm *[16]byte, payload []byte) ([]byte, error) {
	if len(sMTI) != 4 {
		return nil, fmt.Errorf("bad MTI %q", sMTI)
	}
	var buf bytes.Buffer
	createDetails(sMTI, bm, payload, &buf)
	return buf.Bytes(), nil
}

func optionalField(sb *strings.Builder, bm *[16]byte, idx int, v *string) error {
	if v == nil {
		return nil
	}
	return addFieldSB(sb, idx, *v, bm)
}

func ptrStr(s string) *string { return &s }

func pad2Hex(n int) string {
	h := strconv.FormatInt(int64(n), 16)
	return padZeroLeft(h, 2)
}

func concat(parts ...string) string {
	var b strings.Builder
	for _, p := range parts {
		b.WriteString(p)
	}
	return b.String()
}

func (p *IpmOutProcessor) createFooterMessage(logf io.Writer, out io.Writer, amount, recCnt, processorId string, seqNo int) error {
	var bm [16]byte
	var sb strings.Builder
	de048 := "0105025002" + time.Now().Format("060105") + pad11Zero(processorId) + fmt.Sprintf("%05d", seqNo) + "0301016"
	de048 += pad16Zero(amount) + "0306008" + pad8Zero(recCnt)
	if err := addFieldSB(&sb, 23, "695", &bm); err != nil {
		return err
	}
	if err := addFieldSB(&sb, 47, de048, &bm); err != nil {
		return err
	}
	if err := addFieldSB(&sb, 70, pad8Zero(recCnt), &bm); err != nil {
		return err
	}
	ebcdic := asciiToEbcdicStr(sb.String())
	if err := createDetails("1644", &bm, ebcdic, out); err != nil {
		return err
	}
	dumpMessage(logf, "1644"+string(bm[:])+sb.String(), "ASCII")
	return nil
}

// callIpmOutWorkEntity marks MC work rows gen_status 9, materialises
// VW_IPM_OUT_WORK into IPM_OUT_WORK for this file and writes the report data.
func (p *IpmOutProcessor) callIpmOutWorkEntity(ctx context.Context, insCode int, fileName string, fromDate, toDate *time.Time) error {
	var fetched []*McAcqTxnWorkEntity
	var err error
	if fromDate == nil {
		fetched, err = p.store.FindMcWorkLessThanEqual(ctx, insCode, 3, *toDate)
	} else {
		fetched, err = p.store.FindMcWorkBetween(ctx, insCode, 3, *fromDate, *toDate)
	}
	if err != nil {
		return err
	}
	for _, e := range fetched {
		e.GeneralStatus = 9
		e.FileID = fileName
	}
	if err := p.store.UpdateMcWorkStatuses(ctx, fetched); err != nil {
		return err
	}
	view, err := p.store.FindViewIpmOutWorkAll(ctx)
	if err != nil {
		return err
	}
	ipmOut := make([]*IpmOutWorkEntity, 0, len(view))
	for _, v := range view {
		ipmOut = append(ipmOut, mapViewToIpmOut(insCode, fileName, v))
	}
	if err := p.store.InsertIpmOutWork(ctx, ipmOut); err != nil {
		return err
	}
	// processData failures are logged, never fatal (Java behaviour).
	if err := p.processData(ctx, view, insCode, fileName); err != nil {
		logOutsvc("processData()", err)
	}
	return nil
}

func mapViewToIpmOut(insCode int, fileName string, v *ViewIpmOutWorkEntity) *IpmOutWorkEntity {
	e := &IpmOutWorkEntity{
		InsCode:        insCode,
		FileId:         fileName,
		RefSerNumber:   v.SerialNo,
		DE001:          v.De001,
		DE002:          v.De002,
		DE003:          v.De003,
		DE004:          v.De004,
		DE012:          v.De012,
		DE014:          v.De014,
		DE022:          v.De022,
		DE023:          v.De023,
		DE024:          v.De024,
		DE025:          v.De025,
		DE026:          v.De026,
		DE030:          v.De030,
		DE031:          v.De031,
		DE032:          v.De033,
		DE033:          v.De033,
		DE037:          v.De037,
		DE038:          v.De038,
		DE040:          v.De040,
		DE041:          v.De041,
		DE042:          v.De042,
		DE043:          v.De043,
		DE049:          v.De049,
		DE054:          v.De054,
		DE063:          v.De063,
		DE071:          v.De071,
		DE072:          v.De072,
		DE093:          v.De093,
		DE094:          v.De033,
		DE095:          v.De095,
		PDS23:          v.De0480023Pds23,
		PDS25:          orEmpty(v.De0480025),
		PDS52:          orEmpty(v.De0480052),
		PDS137:         v.De0480137,
		PDS148:         v.De0480148,
		PDS149:         orEmpty(v.De0480149),
		PDS155:         v.De0480155,
		PDS165:         v.De0480165,
		PDS176:         orEmpty(v.De0480176),
		PDS211:         v.De0480211,
		PDS262:         orEmpty(v.De0480262),
		DE055_9F26:     orEmpty(v.De0559f26),
		DE055_9F27:     orEmpty(v.De0559f27),
		DE055_9F10:     orEmpty(v.De0559f10),
		DE055_9F37:     orEmpty(v.De0559f37),
		DE055_9F36:     orEmpty(v.De0559f36),
		DE055_95:       orEmpty(v.De05595),
		DE055_9A:       orEmpty(v.De0559a),
		DE055_9C:       orEmpty(v.De0559c),
		DE055_9F02:     orEmpty(v.De0559f02),
		DE055_5F2A:     orEmpty(v.De0555f2a),
		DE055_82:       orEmpty(v.De05582),
		DE055_9F1A:     orEmpty(v.De0559f1a),
		DE055_9F03:     orEmpty(v.De0559f03),
		DE048_PDS0213:  orEmpty(v.De048Pds0213),
		DE055_84:       orEmpty(v.De05584),
		DE055_9F33:     orEmpty(v.De0559f33),
		DE055_9F34:     orEmpty(v.De0559f34),
		DE048_PDS0170:  orEmpty(v.De0480170),
		PDS0018:        v.De0480018,
		DE048_PDS0175:  v.De0480175,
	}
	return e
}

// processData populates OUTGOING_REPORT_DATA_WORK grouped by txn date and type.
func (p *IpmOutProcessor) processData(ctx context.Context, dataList []*ViewIpmOutWorkEntity, insCode int, fileName string) error {
	type key struct {
		date time.Time
		typ  string
	}
	groups := map[key]*reportAgg{}
	var order []key
	now := time.Now()
	for _, e := range dataList {
		ts, ok := parseLenientDateTime(orEmpty(e.LocalDateTime))
		if !ok {
			continue
		}
		date := time.Date(ts.Year(), ts.Month(), ts.Day(), 0, 0, 0, 0, ts.Location())
		k := key{date: date, typ: orEmpty(e.TxnType)}
		g, ok := groups[k]
		if !ok {
			g = &reportAgg{}
			groups[k] = g
			order = append(order, k)
		}
		g.count++
		if amt, err := strconv.ParseFloat(orEmpty(e.TxnAmount), 64); err == nil {
			g.amount += amt
		}
	}
	sort.Slice(order, func(i, j int) bool { return order[i].date.Before(order[j].date) })
	var ents []*OutgoingReportDataWorkEntity
	for _, k := range order {
		g := groups[k]
		ents = append(ents, &OutgoingReportDataWorkEntity{
			LastUpdated:     now,
			UpdatedUser:     2,
			InstitutionCode: insCode,
			OutFileId:       fileName,
			OutgoingDate:    now,
			TxnDate:         k.date,
			Network:         "MC",
			PosOrgPg:        "POS",
			TxnType:         k.typ,
			Count:           g.count,
			Amount:          g.amount,
		})
	}
	return p.store.InsertOutgoingReport(ctx, ents)
}

type reportAgg struct {
	count  int64
	amount float64
}

// parseLenientDateTime parses "yyyy-MM-dd HH:mm:ss.S" with 1..9 fraction digits.
func parseLenientDateTime(s string) (time.Time, bool) {
	if s == "" {
		return time.Time{}, false
	}
	layouts := []string{"2006-01-02 15:04:05"}
	if i := strings.IndexByte(s, '.'); i >= 0 {
		n := len(s) - i - 1
		if n < 1 || n > 9 {
			n = 1
		}
		layouts = append(layouts, "2006-01-02 15:04:05."+strings.Repeat("0", n))
	}
	for _, l := range layouts {
		if t, err := time.Parse(l, s); err == nil {
			return t, true
		}
	}
	return time.Time{}, false
}

// buildAndSaveSummaries aggregates MC_ACQ_TXN_WORK (gen_status 9) into
// OUTGOING_SUMMARY for this file.
func (p *IpmOutProcessor) buildAndSaveSummaries(ctx context.Context, insCode, intCode int, businessDate time.Time, refSerNumber, userSerNumber int, fileID, fileType string) error {
	groups, err := p.store.FindMcSummaryGroups(ctx, insCode)
	if err != nil {
		return err
	}
	now := time.Now()
	ents := make([]*OutgoingSummaryEntity, 0, len(groups))
	for _, g := range groups {
		ents = append(ents, &OutgoingSummaryEntity{
			LastUpdated:     now,
			UpdatedUser:     userSerNumber,
			InstitutionCode: insCode,
			InterfaceCode:   intCode,
			OutFileDate:     businessDate,
			FileId:          fileID,
			RefSerialNumber: int64(refSerNumber),
			MessageTypeId:   g.MessageTypeId,
			FunctionCode:    g.FunctionCode,
			ProcCode:        g.ProcCode,
			Count:           g.Count,
			Amount:          g.Amount,
			SurchargeAmount: g.SurAmount,
			NetAmount:       g.NetAmount,
			GeneralStatus:   3,
		})
	}
	return p.store.InsertSummaries(ctx, ents)
}

// completeMC marks work rows complete, updates POS transactions and moves
// work -> data (Java's completePosStatus + moveWorkToData).
func (p *IpmOutProcessor) completeMC(ctx context.Context, insCode, userSerNumber int, fileID string) error {
	work, err := p.store.FindMcWorkByStatus(ctx, insCode, 9)
	if err != nil {
		return err
	}
	now := time.Now()
	for _, e := range work {
		e.GeneralStatus = 4
		e.FileID = fileID
		fp := time.Date(now.Year(), now.Month(), now.Day(), 0, 0, 0, 0, now.Location())
		e.FileProcDate = &fp
	}
	if err := p.store.UpdateMcWorkStatuses(ctx, work); err != nil {
		return err
	}
	if err := p.store.CompletePosStatus(ctx, insCode); err != nil {
		return err
	}
	return p.moveWorkToData(ctx, insCode)
}

func (p *IpmOutProcessor) moveWorkToData(ctx context.Context, insCode int) error {
	work, err := p.store.FindMcWorkByStatus(ctx, insCode, 4)
	if err != nil {
		return err
	}
	now := time.Now()
	data := make([]*McAcqTxnDataEntity, 0, len(work))
	for _, w := range work {
		d := *w
		d.LastUpdated = now
		d.ChipTxnType = w.TxnType
		data = append(data, &d)
	}
	if err := p.store.InsertMcData(ctx, data); err != nil {
		return err
	}
	return p.store.DeleteMcWork(ctx, work)
}

// callGCOIpmOutWorkEntity is the collection-only twin of
// callIpmOutWorkEntity. GCO specifics are implemented together with the GCO
// services; the VW_GCO_IPM_OUT_WORK mapping reuses the same IpmOutWork shape.
func (p *IpmOutProcessor) callGCOIpmOutWorkEntity(ctx context.Context, insCode int, fileName string, fromDate, toDate *time.Time) error {
	return errGCONotImplemented
}

// completeGCO runs the GCO post-processing steps.
func (p *IpmOutProcessor) completeGCO(ctx context.Context, insCode, userSerNumber int, fileID string) error {
	return errGCONotImplemented
}

// dumpMessage writes the diagnostic hex/ISO dump (Java System.out dump).
func dumpMessage(w io.Writer, sStr, sDataFormat string) {
	fmt.Fprintf(w, "Record Length %d\n", len(sStr))
	fmt.Fprintf(w, "Hex dump\n")
	var dump strings.Builder
	for i := 1; i <= len(sStr); i++ {
		fmt.Fprintf(&dump, "%02X ", sStr[i-1])
		if i%30 == 0 {
			fmt.Fprintln(w, dump.String())
			dump.Reset()
		}
	}
	fmt.Fprintln(w, dump.String())
	fmt.Fprintf(w, "%5s: %s\n", "MTI", cutRight(sStr, 4))
	var pos [129]int
	pos = processIsoMessageAscii(sStr)
	for i := 1; i < 128; i++ {
		sub := getIsoField(sStr, i, sDataFormat, pos)
		if sub != "" {
			fmt.Fprintf(w, "DE%03d: %s\n", i, sub)
		}
	}
}

var errGCONotImplemented = fmt.Errorf("outsvc: GCO flow not yet implemented")

func logOutsvc(msg string, err error) {
	// Best-effort diagnostic logging; mirrors Java log.error calls.
	fmt.Fprintf(os.Stderr, "outsvc: %s: %v\n", msg, err)
}
