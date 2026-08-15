package outsvc

import (
	"bytes"
	"context"
	"os"
	"path/filepath"
	"strings"
	"testing"
	"time"
)

// ebc2asc is the inverse of the ASCII->EBCDIC table, used only in tests to
// decode payload bytes back to ASCII for assertions.
func ebc2asc() [256]byte {
	var m [256]byte
	for i := 0; i < 16; i++ {
		for j := 0; j < 16; j++ {
			m[tcode[i][j]] = byte(i<<4 | j)
		}
	}
	return m
}

var decodeEbc = func() func([]byte) string {
	m := ebc2asc()
	return func(s []byte) string {
		out := make([]byte, len(s))
		for i, b := range s {
			out[i] = m[b]
		}
		return string(out)
	}
}()

func TestEBCDICBasics(t *testing.T) {
	cases := map[byte]byte{
		'0': 0xF0, '1': 0xF1, '2': 0xF2, '9': 0xF9,
		'A': 0xC1, 'Z': 0xE9, ' ': 0x40,
	}
	for in, want := range cases {
		if got := asciiToEbcdic(int(in)); got != want {
			t.Errorf("asciiToEbcdic(%q) = %#x, want %#x", in, got, want)
		}
	}
}

func TestPadHelpers(t *testing.T) {
	if got := pad11Zero("123"); got != "00000000123" {
		t.Errorf("pad11Zero = %q", got)
	}
	if got := pad16Zero("123"); got != "0000000000000123" {
		t.Errorf("pad16Zero = %q", got)
	}
	if got := pad8Zero("12"); got != "00000012" {
		t.Errorf("pad8Zero = %q", got)
	}
	if got := jcatNull(nil, ptrStr("x")); got != "nullx" {
		t.Errorf("jcatNull = %q", got)
	}
	if got := jcatEmpty(nil, ptrStr("x"), nil); got != "x" {
		t.Errorf("jcatEmpty = %q", got)
	}
}

func TestTransformIPM(t *testing.T) {
	var in bytes.Buffer
	in.Write(bytes.Repeat([]byte{'A'}, 1012))
	in.Write([]byte{'B', 'C'})

	var out bytes.Buffer
	if err := transformIPM(&in, &out); err != nil {
		t.Fatal(err)
	}
	got := out.Bytes()
	// 1012 A's, then 2 zeros, then B C, then padding.
	wantLen := 1012 + 2 + 2 + (1012 - 2) + 2 + 1014
	if len(got) != wantLen {
		t.Fatalf("len = %d, want %d", len(got), wantLen)
	}
	for i := 0; i < 1012; i++ {
		if got[i] != 'A' {
			t.Fatalf("byte %d = %#x, want 'A'", i, got[i])
		}
	}
	if got[1012] != 0 || got[1013] != 0 {
		t.Fatalf("expected 2 zero bytes after block 1, got %#x %#x", got[1012], got[1013])
	}
	if got[1014] != 'B' || got[1015] != 'C' {
		t.Fatalf("expected B C after zeros, got %#x %#x", got[1014], got[1015])
	}
}

func TestCreateHeaderMessage(t *testing.T) {
	p := NewIpmOutProcessor("/tmp", "X", nil, nil)
	var log bytes.Buffer
	var out bytes.Buffer
	procID := "12345678901"
	fileID, err := p.createHeaderMessage(&log, &out, procID, 1)
	if err != nil {
		t.Fatal(err)
	}
	de048 := "0105025002" + time.Now().Format("060105") + pad11Zero(procID) + "00001" + "0122001X"
	wantFileID := de048[7:32]
	if fileID != wantFileID {
		t.Errorf("fileID = %q, want %q", fileID, wantFileID)
	}
	rec := out.Bytes()
	if len(rec) != 4+16+len(de048)+16+1+1 {
		t.Errorf("record len = %d", len(rec))
	}
	if rec[4] != 0xF1 || rec[5] != 0xF6 || rec[6] != 0xF4 || rec[7] != 0xF4 {
		t.Errorf("MTI not EBCDIC 1644: %#x %#x %#x %#x", rec[4], rec[5], rec[6], rec[7])
	}
	bm := rec[8:24]
	if bm[0]&0x80 == 0 {
		t.Error("secondary bitmap flag not set (DE070 present)")
	}
	if bm[2] != 0x01 {
		t.Errorf("bm[2] = %#x, want 0x01 (DE023)", bm[2])
	}
	if bm[8]&0x02 == 0 {
		t.Errorf("bm[8] = %#x, want bit 1 (DE070)", bm[8])
	}
	decoded := decodeEbc(rec[24:])
	if !strings.Contains(decoded, "697") || !strings.Contains(decoded, "00000001") {
		t.Errorf("decoded header payload missing fields: %q", decoded)
	}
	if !strings.Contains(decoded, de048) {
		t.Errorf("decoded header payload missing DE048: %q", decoded)
	}
}

func viewEntity() *ViewIpmOutWorkEntity {
	ptrs := func(s string) *string { return &s }
	de030 := "000000000000000000000000"
	de031 := "012345678901234567890123"
	de043 := "0123456789012345678901234567890123456789"
	return &ViewIpmOutWorkEntity{
		SerialNo:       1,
		De001:          ptrs("0200"),
		De002:          ptrs("tok1"),
		De003:          ptrs("000000"),
		De004:          ptrs("000000000100"),
		De012:          ptrs("123456123456"),
		De022:          ptrs("021021021021"),
		De023:          ptrs("001"),
		De024:          ptrs("003"),
		De025:          ptrs("0101"),
		De026:          ptrs("0001"),
		De030:          &de030,
		De031:          &de031,
		De033:          ptrs("001"),
		De037:          ptrs("012345678901"),
		De038:          ptrs("012345"),
		De040:          ptrs("512"),
		De041:          ptrs("01234567"),
		De042:          ptrs("012345678901234"),
		De043:          &de043,
		De049:          ptrs("USD"),
		De063:          ptrs("01234567"),
		De071:          ptrs("00000001"),
		De093:          ptrs("01234567"),
		De095:          ptrs("01234567"),
		De0480025:      ptrs("P25"),
		De0480052:      ptrs("P52"),
		De0480149:      ptrs("P149"),
		De0480165:      ptrs("P165"),
		De0559f26:      ptrs("9F260400000000"),
		De0559f27:      ptrs("5F27020000"),
		De0559f10:      ptrs("9F10080000000000000000"),
		De0559f34:      ptrs("5F340100"),
		De0559f33:      ptrs("9F3303000000"),
		De0559f37:      ptrs("9F370400000000"),
		De0559f36:      ptrs("9F36020000"),
		De05595:        ptrs("95050000000000"),
		De0559a:        ptrs("9A03010101"),
		De0559c:        ptrs("9C0100"),
		De0559f02:      ptrs("9F0206000000000000"),
		De0555f2a:      ptrs("5F2A020826"),
		De05582:        ptrs("82020000"),
		De0559f1a:      ptrs("9F1A020826"),
		De0559f03:      ptrs("9F0306000000000000"),
		De05584:        ptrs("840400000000"),
		LocalDateTime:  ptrs("2026-08-14 12:00:00.0"),
		TxnType:        ptrs("POS"),
		TxnAmount:      ptrs("100.00"),
		De048Pds0213:   ptrs("P213"),
		De0480170:      ptrs("P170"),
		De0480018:      ptrs("P018"),
		De0480175:      ptrs("P175"),
	}
}

func TestBuildDetail(t *testing.T) {
	p := &IpmOutProcessor{}
	ipm := mapViewToIpmOut(1, "F001", viewEntity())
	dec := map[string]string{"tok1": "4111111111111111"}

	rec, _, err := p.buildDetail(ipm, dec)
	if err != nil {
		t.Fatal(err)
	}
	if len(rec) < 20 {
		t.Fatalf("record too short: %d", len(rec))
	}
	// 4-byte length prefix = MTI+bitmap+payload, i.e. len(rec)-4.
	if int(rec[2])<<8|int(rec[3]) != len(rec)-4 {
		t.Errorf("length prefix %d != frame len-4 (%d)", int(rec[2])<<8|int(rec[3]), len(rec)-4)
	}
	// MTI EBCDIC "0200".
	if rec[4] != 0xF0 || rec[5] != 0xF2 || rec[6] != 0xF0 || rec[7] != 0xF0 {
		t.Errorf("MTI bytes: %#x %#x %#x %#x", rec[4], rec[5], rec[6], rec[7])
	}
	decoded := decodeEbc(rec[24:])
	if !strings.Contains(decoded, "16"+"4111111111111111") {
		t.Errorf("DE002 missing in payload: %q", decoded)
	}
	// de048 in the de055 branch: PDS23(nil)->"null" ... PDS165(non-"C"),
	// then PDS213/170/018/175 appended (nil PDS176/211/262 dropped).
	wantDe48 := "nullP25P52nullP149nullP165P213P170P018P175"
	if !strings.Contains(decoded, "042"+wantDe48) {
		t.Errorf("DE048 missing in payload (want prefix 042): %q", decoded)
	}
	// de055 LLLVAR binary prefix must be EBCDIC "054" followed by raw bytes.
	// Find the de055 tag 9F26 raw bytes in the payload.
	raw := []byte{0x9F, 0x26, 0x04, 0x00, 0x00, 0x00, 0x00}
	if !bytes.Contains(rec, raw) {
		t.Error("raw de055 bytes (9F26...) missing from record")
	}
}

func TestBuildDetail1740(t *testing.T) {
	p := &IpmOutProcessor{}
	v := viewEntity()
	de001 := "1740"
	v.De001 = &de001
	p165 := "C"
	v.De0480165 = &p165
	ipm := mapViewToIpmOut(1, "F001", v)
	dec := map[string]string{"tok1": "4111111111111111"}
	rec, _, err := p.buildDetail(ipm, dec)
	if err != nil {
		t.Fatal(err)
	}
	decoded := decodeEbc(rec[24:])
	if strings.Contains(decoded, "123456123456") {
		t.Error("DE012 must be nulled for MTI 1740")
	}
	// 1740 de048 = PDS25 + PDS137(nil) + PDS148(nil) + PDS165 = P25nullnullC
	wantDe48 := "P25" + "null" + "null" + "C"
	if !strings.Contains(decoded, "012"+wantDe48) {
		t.Errorf("1740 DE048 missing (want prefix 012): %q", decoded)
	}
}

// ---- end-to-end IpmPro test with fake store + fake crypto ----

type fakeStore struct {
	Store
	work               []*McAcqTxnWorkEntity
	ipmOut             []*IpmOutWorkEntity
	view               []*ViewIpmOutWorkEntity
	summaryGroups      []mcSummaryGroup
	summaries          []*OutgoingSummaryEntity
	report             []*OutgoingReportDataWorkEntity
	data               []*McAcqTxnDataEntity
	posStatusCompleted bool
}

func (f *fakeStore) FindMcWorkBetween(ctx context.Context, ins, status int, from, to time.Time) ([]*McAcqTxnWorkEntity, error) {
	return f.work, nil
}

func (f *fakeStore) FindMcWorkLessThanEqual(ctx context.Context, ins, status int, to time.Time) ([]*McAcqTxnWorkEntity, error) {
	return f.work, nil
}

func (f *fakeStore) UpdateMcWorkStatuses(ctx context.Context, ents []*McAcqTxnWorkEntity) error {
	f.work = ents
	return nil
}

func (f *fakeStore) FindViewIpmOutWorkAll(ctx context.Context) ([]*ViewIpmOutWorkEntity, error) {
	return f.view, nil
}

func (f *fakeStore) InsertIpmOutWork(ctx context.Context, ents []*IpmOutWorkEntity) error {
	f.ipmOut = ents
	return nil
}

func (f *fakeStore) FindIpmOutWorkByInsAndFile(ctx context.Context, ins int, fileId string) ([]*IpmOutWorkEntity, error) {
	return f.ipmOut, nil
}

func (f *fakeStore) InsertOutgoingReport(ctx context.Context, ents []*OutgoingReportDataWorkEntity) error {
	f.report = ents
	return nil
}

func (f *fakeStore) FindMcSummaryGroups(ctx context.Context, ins int) ([]mcSummaryGroup, error) {
	return f.summaryGroups, nil
}

func (f *fakeStore) InsertSummaries(ctx context.Context, ents []*OutgoingSummaryEntity) error {
	f.summaries = ents
	return nil
}

func (f *fakeStore) FindMcWorkByStatus(ctx context.Context, ins, status int) ([]*McAcqTxnWorkEntity, error) {
	var out []*McAcqTxnWorkEntity
	for _, w := range f.work {
		if w.GeneralStatus == status {
			out = append(out, w)
		}
	}
	return out, nil
}

func (f *fakeStore) CompletePosStatus(ctx context.Context, ins int) error {
	f.posStatusCompleted = true
	return nil
}

func (f *fakeStore) InsertMcData(ctx context.Context, ents []*McAcqTxnDataEntity) error {
	f.data = ents
	return nil
}

func (f *fakeStore) DeleteMcWork(ctx context.Context, ents []*McAcqTxnWorkEntity) error {
	f.work = nil
	return nil
}

type fakeCrypto struct {
	dec map[string]string
}

func (c *fakeCrypto) GetCardNumber(tokens []string) map[string]string { return c.dec }
func (c *fakeCrypto) GetToken(cards []string) map[string]string       { return nil }

func TestIpmProEndToEnd(t *testing.T) {
	dir := t.TempDir()
	st := &fakeStore{
		work: []*McAcqTxnWorkEntity{{SerNumber: 7, InstitutionCode: 1, GeneralStatus: 3, TxnType: "POS"}},
		view: []*ViewIpmOutWorkEntity{viewEntity()},
		summaryGroups: []mcSummaryGroup{
			{MessageTypeId: "0200", FunctionCode: "200", ProcCode: "00", Count: 1, Amount: 100, SurAmount: 5, NetAmount: 105},
		},
	}
	crypto := &fakeCrypto{dec: map[string]string{"tok1": "4111111111111111"}}
	p := NewIpmOutProcessor(dir, "X", st, crypto)

	from := time.Date(2026, 8, 14, 0, 0, 0, 0, time.UTC)
	to := time.Date(2026, 8, 14, 23, 59, 59, 0, time.UTC)
	fileName := "IRFR11114082026.01"
	fileID := p.IpmPro(context.Background(), fileName, "12345678901", 1, 1, 11, time.Date(2026, 8, 14, 0, 0, 0, 0, time.UTC), 42, 4, &from, &to, "")
	if fileID == "" {
		t.Fatal("IpmPro returned empty fileId")
	}
	expected := "002" + time.Now().Format("060105") + "12345678901" + "00001"
	if fileID != expected {
		t.Errorf("fileId = %q, want %q", fileID, expected)
	}
	outPath := filepath.Join(dir, fileName)
	info, err := os.Stat(outPath)
	if err != nil {
		t.Fatalf("output file missing: %v", err)
	}
	if info.Size() <= 0 {
		t.Fatal("output file empty")
	}
	if len(st.summaries) != 1 {
		t.Fatalf("summaries = %d, want 1", len(st.summaries))
	}
	if st.summaries[0].FileId != fileID {
		t.Errorf("summary fileId = %q, want %q", st.summaries[0].FileId, fileID)
	}
	if !st.posStatusCompleted {
		t.Error("CompletePosStatus not called")
	}
	if len(st.data) != 1 {
		t.Fatalf("moved data rows = %d, want 1", len(st.data))
	}
	if st.data[0].ChipTxnType != "POS" {
		t.Errorf("data chipTxnType = %q, want txnType POS", st.data[0].ChipTxnType)
	}
	if len(st.report) != 1 {
		t.Fatalf("report rows = %d, want 1", len(st.report))
	}
}
