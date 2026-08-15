package binsvc

import (
	"context"
	"os"
	"strings"
	"testing"
	"time"
)

// writeFile writes content to path, failing the test on error.
func writeFile(t *testing.T, path, content string) {
	t.Helper()
	if err := os.WriteFile(path, []byte(content), 0o644); err != nil {
		t.Fatal(err)
	}
}

// seedUploadLog records an upload log row (status 1) so processor tests can
// observe the updateProcess/handleUploadError outcomes.
func seedUploadLog(store *fakeStore, fileName string) {
	store.uploads = append(store.uploads, &UploadLog{
		SerialNumber: 1, UploadStatus: 1, JobNumber: 1, FileName: fileName, FormatCode: 2,
	})
}

// fakeStore is an in-memory Store for tests.
type fakeStore struct {
	jobs         []*ProcessingJob
	uploads      []*UploadLog
	mcRanges     []*McRange
	visaRanges   []*VisaRange
	jaywanRanges []*JaywanRange
	omanRanges   []*OmanNetRange
	mercury      []*MercuryRange
	bussDate     time.Time
	nextSer      int64
}

func (f *fakeStore) InsertProcessingJob(_ context.Context, user, insCode int) (int64, error) {
	f.nextSer++
	e := &ProcessingJob{SerialNumber: f.nextSer, UpdatedUser: user, InsCode: insCode}
	f.jobs = append(f.jobs, e)
	return f.nextSer, nil
}
func (f *fakeStore) FindJobBySerialNumber(_ context.Context, ser int) (*ProcessingJob, error) {
	for _, e := range f.jobs {
		if e.SerialNumber == int64(ser) {
			return e, nil
		}
	}
	return nil, nil
}
func (f *fakeStore) UpdateJobEndTime(_ context.Context, ser int, end time.Time) error {
	for _, e := range f.jobs {
		if e.SerialNumber == int64(ser) {
			e.EndTime = &end
		}
	}
	return nil
}
func (f *fakeStore) CountByUploadStatus(_ context.Context, status int) (int, error) {
	n := 0
	for _, e := range f.uploads {
		if e.UploadStatus == status {
			n++
		}
	}
	return n, nil
}
func (f *fakeStore) FindUploadLogBySerialNumber(_ context.Context, ser int) (*UploadLog, error) {
	for _, e := range f.uploads {
		if e.SerialNumber == int64(ser) {
			return e, nil
		}
	}
	return nil, nil
}
func (f *fakeStore) FindUploadLogByFileName(_ context.Context, name string) (*UploadLog, error) {
	for _, e := range f.uploads {
		if e.FileName == name {
			return e, nil
		}
	}
	return nil, nil
}
func (f *fakeStore) FindUploadLogByJobNumber(_ context.Context, job int) (*UploadLog, error) {
	for _, e := range f.uploads {
		if e.JobNumber == job {
			return e, nil
		}
	}
	return nil, nil
}
func (f *fakeStore) InsertUploadLog(_ context.Context, e *UploadLog) (int64, error) {
	for _, x := range f.uploads {
		if x.FileName == e.FileName {
			return 0, errDuplicateFilename
		}
	}
	f.nextSer++
	e.SerialNumber = f.nextSer
	f.uploads = append(f.uploads, e)
	return f.nextSer, nil
}
func (f *fakeStore) UpdateUploadLog(_ context.Context, e *UploadLog) error {
	for i, x := range f.uploads {
		if x.SerialNumber == e.SerialNumber {
			f.uploads[i] = e
		}
	}
	return nil
}
func (f *fakeStore) DeleteUploadLogByFileName(_ context.Context, name string) error {
	var out []*UploadLog
	for _, x := range f.uploads {
		if x.FileName != name {
			out = append(out, x)
		}
	}
	f.uploads = out
	return nil
}
func (f *fakeStore) GetBusinessDate(_ context.Context) (time.Time, error) { return f.bussDate, nil }
func (f *fakeStore) FindMcRange(_ context.Context, low, high, priority string) (*McRange, error) {
	for _, e := range f.mcRanges {
		if e.IssRangeLow == low && e.IssRangeHigh == high && e.PriorityCode == priority {
			return e, nil
		}
	}
	return nil, nil
}
func (f *fakeStore) DeleteMcRange(_ context.Context, e *McRange) error {
	var out []*McRange
	for _, x := range f.mcRanges {
		if x.SerialNumber != e.SerialNumber {
			out = append(out, x)
		}
	}
	f.mcRanges = out
	return nil
}
func (f *fakeStore) InsertMcRange(_ context.Context, e *McRange) error {
	f.nextSer++
	e.SerialNumber = f.nextSer
	f.mcRanges = append(f.mcRanges, e)
	return nil
}
func (f *fakeStore) DeleteMcRangeByJob(_ context.Context, job int) error {
	var out []*McRange
	for _, x := range f.mcRanges {
		if x.JobSerialNumber != job {
			out = append(out, x)
		}
	}
	f.mcRanges = out
	return nil
}
func (f *fakeStore) CountVisaRanges(_ context.Context) (int, error) { return len(f.visaRanges), nil }
func (f *fakeStore) FetchVisaRangeBatch(_ context.Context, limit int) ([]*VisaRange, error) {
	n := limit
	if n > len(f.visaRanges) {
		n = len(f.visaRanges)
	}
	return f.visaRanges[:n], nil
}
func (f *fakeStore) DeleteVisaRanges(_ context.Context, serials []int64) error {
	set := map[int64]bool{}
	for _, s := range serials {
		set[s] = true
	}
	var out []*VisaRange
	for _, x := range f.visaRanges {
		if !set[x.SerialNumber] {
			out = append(out, x)
		}
	}
	f.visaRanges = out
	return nil
}
func (f *fakeStore) InsertVisaRange(_ context.Context, e *VisaRange) error {
	f.nextSer++
	e.SerialNumber = f.nextSer
	f.visaRanges = append(f.visaRanges, e)
	return nil
}
func (f *fakeStore) DeleteVisaRangeByJob(_ context.Context, job int) error {
	var out []*VisaRange
	for _, x := range f.visaRanges {
		if x.JobSerialNumber != job {
			out = append(out, x)
		}
	}
	f.visaRanges = out
	return nil
}
func (f *fakeStore) FindJaywanRanges(_ context.Context, low, high int64) ([]*JaywanRange, error) {
	var out []*JaywanRange
	for _, x := range f.jaywanRanges {
		if x.BinRangeLow == low && x.BinRangeHigh == high {
			out = append(out, x)
		}
	}
	return out, nil
}
func (f *fakeStore) DeleteJaywanRanges(_ context.Context, serials []int64) error {
	set := map[int64]bool{}
	for _, s := range serials {
		set[s] = true
	}
	var out []*JaywanRange
	for _, x := range f.jaywanRanges {
		if !set[x.SerialNumber] {
			out = append(out, x)
		}
	}
	f.jaywanRanges = out
	return nil
}
func (f *fakeStore) InsertJaywanRange(_ context.Context, e *JaywanRange) error {
	f.nextSer++
	e.SerialNumber = f.nextSer
	f.jaywanRanges = append(f.jaywanRanges, e)
	return nil
}
func (f *fakeStore) DeleteJaywanRangeByJob(_ context.Context, job int) error {
	var out []*JaywanRange
	for _, x := range f.jaywanRanges {
		if x.JobNumber != job {
			out = append(out, x)
		}
	}
	f.jaywanRanges = out
	return nil
}
func (f *fakeStore) FindOmanNetByBins(_ context.Context, bins []string) ([]*OmanNetRange, error) {
	set := map[string]bool{}
	for _, b := range bins {
		set[b] = true
	}
	var out []*OmanNetRange
	for _, x := range f.omanRanges {
		if set[x.BinNumber] {
			out = append(out, x)
		}
	}
	return out, nil
}
func (f *fakeStore) DeleteOmanNet(_ context.Context, serials []int64) error {
	set := map[int64]bool{}
	for _, s := range serials {
		set[s] = true
	}
	var out []*OmanNetRange
	for _, x := range f.omanRanges {
		if !set[x.SerialNumber] {
			out = append(out, x)
		}
	}
	f.omanRanges = out
	return nil
}
func (f *fakeStore) InsertOmanNetRange(_ context.Context, e *OmanNetRange) error {
	f.nextSer++
	e.SerialNumber = f.nextSer
	f.omanRanges = append(f.omanRanges, e)
	return nil
}
func (f *fakeStore) DeleteOmanNetByJob(_ context.Context, job int) error {
	var out []*OmanNetRange
	for _, x := range f.omanRanges {
		if x.JobNumber != job {
			out = append(out, x)
		}
	}
	f.omanRanges = out
	return nil
}
func (f *fakeStore) FindMercuryRanges(_ context.Context, low, high int64) ([]*MercuryRange, error) {
	var out []*MercuryRange
	for _, x := range f.mercury {
		if x.BinRangeLow == low && x.BinRangeHigh == high {
			out = append(out, x)
		}
	}
	return out, nil
}
func (f *fakeStore) DeleteMercuryRanges(_ context.Context, serials []int64) error {
	set := map[int64]bool{}
	for _, s := range serials {
		set[s] = true
	}
	var out []*MercuryRange
	for _, x := range f.mercury {
		if !set[x.SerialNumber] {
			out = append(out, x)
		}
	}
	f.mercury = out
	return nil
}
func (f *fakeStore) InsertMercuryRange(_ context.Context, e *MercuryRange) error {
	f.nextSer++
	e.SerialNumber = f.nextSer
	f.mercury = append(f.mercury, e)
	return nil
}

// testService builds a Service wired to a fake store and a temp input dir.
func testService(t *testing.T, store *fakeStore, dir string) *Service {
	t.Helper()
	if store == nil {
		store = &fakeStore{bussDate: time.Date(2026, 1, 23, 0, 0, 0, 0, time.UTC)}
	}
	return &Service{
		Store: store,
		Cfg: Config{
			InsCode:          1,
			InsShortName:     "TEST",
			UpdatedUser:      2,
			BinInterfaceCode: 12,
			FormatCodes: map[string]int{
				"MASTERCARD": 2,
				"VISA":       3,
				"JAYWAN":     61,
				"OMANNET":    0,
				"MERCURY":    0,
			},
			ReconIn:        dir + "/",
			ReconProcessed: dir + "/processed/",
			ReconRejected:  dir + "/rejected/",
		},
		Now: func() time.Time { return time.Date(2026, 1, 23, 12, 0, 0, 0, time.UTC) },
	}
}

func TestConvertToGregorianDate(t *testing.T) {
	svc := &Service{}
	for _, tc := range []struct{ julian, want string }{
		{"26023", "2026-01-23"},
		{"26001", "2026-01-01"},
		{"26365", "2026-12-31"},
	} {
		got, err := svc.convertToGregorianDate(tc.julian)
		if err != nil {
			t.Fatalf("convert %s: %v", tc.julian, err)
		}
		if got.Format("2006-01-02") != tc.want {
			t.Fatalf("convert %s = %s, want %s", tc.julian, got.Format("2006-01-02"), tc.want)
		}
	}
}

func TestCalculateBatchSize(t *testing.T) {
	for _, tc := range []struct {
		lines, want int
	}{
		{0, 1000},
		{100, 1000},
		{10000, 1000},
		{15000, 1500},
		{30000, 2000}, // 3000 > max -> clamped to 2000
		{40000, 2000},
	} {
		if got := calculateBatchSize(tc.lines); got != tc.want {
			t.Fatalf("calculateBatchSize(%d) = %d, want %d", tc.lines, got, tc.want)
		}
	}
}

func TestJavaBuilderLines(t *testing.T) {
	// Java String.split drops trailing empty strings.
	got := javaBuilderLines("a\nb\n")
	if len(got) != 2 || got[0] != "a" || got[1] != "b" {
		t.Fatalf("trailing-newline split = %q, want [a b]", got)
	}
	got = javaBuilderLines("a\nb")
	if len(got) != 2 || got[0] != "a" || got[1] != "b" {
		t.Fatalf("no-trailing-newline split = %q, want [a b]", got)
	}
	// middle empty strings are preserved.
	got = javaBuilderLines("a\n\nb\n")
	if len(got) != 3 || got[0] != "a" || got[1] != "" || got[2] != "b" {
		t.Fatalf("middle-empty split = %q, want [a \"\" b]", got)
	}
}

func TestJaywanValidLine(t *testing.T) {
	if !jaywanValidLine([]string{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17"}) {
		t.Fatal("17-field row should be valid")
	}
	if jaywanValidLine([]string{"1", "2"}) {
		t.Fatal("2-field row should be invalid")
	}
}

func TestResolveCardType(t *testing.T) {
	if resolveCardType("credit") != "C" || resolveCardType("debit") != "D" || resolveCardType("prepaid") != "P" {
		t.Fatal("card type resolution wrong")
	}
}

// mcRecord builds a 159-char MC record with the given cardProgID and activeCode.
func mcRecord(cardProgID, activeCode string) string {
	b := make([]byte, 160)
	for i := range b {
		b[i] = 'X'
	}
	copy(b[0:5], "26023")   // effective julian date YYDDD
	copy(b[7:8], activeCode) // active code
	copy(b[11:30], "5000000000000000000")
	copy(b[30:33], "001")
	copy(b[33:52], "5000000000000999999")
	copy(b[52:55], cardProgID)
	copy(b[55:57], "05")
	copy(b[57:68], "MEMBER00001")
	copy(b[68:69], "P")
	copy(b[69:76], "ENDPOINT")
	copy(b[76:79], "ARE")
	copy(b[79:82], "784")
	copy(b[82:83], "M")
	copy(b[83:86], "001")
	copy(b[86:87], "Y")
	copy(b[90:93], "200")
	copy(b[101:104], "840")
	copy(b[104:105], "2")
	copy(b[133:134], "Y")
	copy(b[134:140], "202512")
	copy(b[142:145], "111")
	copy(b[151:152], "Y")
	copy(b[156:157], "Y")
	copy(b[157:158], "Y")
	copy(b[158:159], "N")
	return string(b)
}

func TestProcessMCBin(t *testing.T) {
	store := &fakeStore{bussDate: time.Date(2026, 1, 23, 0, 0, 0, 0, time.UTC)}
	dir := t.TempDir()
	svc := testService(t, store, dir)

	rec1 := mcRecord("MCC", "A")
	rec2 := mcRecord("XXX", "A") // rejected card prog
	file := string([]byte{0xb8}) + rec1 + string([]byte{0x02}) +
		string([]byte{0xb8}) + rec2 + string([]byte{0x02}) +
		"PTRAILER RECORD IP0040T1" + string([]byte{0x03})
	writeFile(t, dir+"/mc.bin", file)

	seedUploadLog(store, "mc.bin")
	(&mcProcessor{svc: svc}).processMCBin(context.Background(), "mc.bin", 1, 1)
	if len(store.mcRanges) != 1 {
		t.Fatalf("inserted %d mc ranges, want 1", len(store.mcRanges))
	}
	r := store.mcRanges[0]
	if r.CardProgID != "MCC" || r.ActiveCode != "A" {
		t.Fatalf("unexpected range: %+v", r)
	}
	if r.EffectiveDate.Format("2006-01-02") != "2026-01-23" {
		t.Fatalf("effective date = %v", r.EffectiveDate)
	}
	if r.BussDate == nil || r.BussDate.Format("2006-01-02") != "2026-01-23" {
		t.Fatalf("buss date not set from business_date table")
	}
	// upload status should be 4 with total count 2
	if store.uploads[0].UploadStatus != 4 || store.uploads[0].TotalTxnCount != 2 {
		t.Fatalf("upload log not updated: %+v", store.uploads[0])
	}
}

func TestProcessVisaBin(t *testing.T) {
	store := &fakeStore{}
	dir := t.TempDir()
	svc := testService(t, store, dir)
	if err := os.MkdirAll(dir+"/processed", 0o755); err != nil {
		t.Fatal(err)
	}
	if err := os.MkdirAll(dir+"/rejected", 0o755); err != nil {
		t.Fatal(err)
	}

	// fixed-width line: issHigh[0:9] issLow[12:21] bin[24:30] binLen[31:33]
	// procBin[35:41] domain[41] region[42] country[43:45] cardProd[58:60]
	// drcr[69] prodSubType[74:76]
	line := make([]byte, 76)
	copy(line[0:9], "540000000")
	copy(line[12:21], "540099999")
	copy(line[24:30], "540099")
	copy(line[31:33], "16")
	copy(line[35:41], "540099")
	line[41] = 'C'
	line[42] = 'M'
	copy(line[43:45], "AE")
	copy(line[58:60], "GP")
	line[69] = 'D'
	copy(line[74:76], "AB")

	writeFile(t, dir+"/visa.txt", string(line)) // no trailing newline -> single line
	seedUploadLog(store, "visa.txt")
	(&visaProcessor{svc: svc}).processVisaBin(context.Background(), "visa.txt", 1, 1)

	if len(store.visaRanges) != 1 {
		t.Fatalf("inserted %d visa ranges, want 1", len(store.visaRanges))
	}
	v := store.visaRanges[0]
	if v.Bin != "540099" || v.BinLength != 16 || v.IssRangeHigh != "540000000" {
		t.Fatalf("unexpected visa range: %+v", v)
	}
	if store.uploads[0].UploadStatus != 4 {
		t.Fatalf("upload status = %d, want 4", store.uploads[0].UploadStatus)
	}
}

func TestProcessVisaBinTrailingNewline(t *testing.T) {
	store := &fakeStore{}
	dir := t.TempDir()
	svc := testService(t, store, dir)
	if err := os.MkdirAll(dir+"/processed", 0o755); err != nil {
		t.Fatal(err)
	}
	line := make([]byte, 76)
	copy(line[0:9], "540000000")
	copy(line[12:21], "540099999")
	copy(line[24:30], "540099")
	copy(line[31:33], "16")
	copy(line[35:41], "540099")
	line[41] = 'C'
	line[42] = 'M'
	copy(line[43:45], "AE")
	writeFile(t, dir+"/visa.txt", string(line)+"\n") // trailing newline is harmless (Java split drops it)
	seedUploadLog(store, "visa.txt")
	(&visaProcessor{svc: svc}).processVisaBin(context.Background(), "visa.txt", 1, 1)
	if store.uploads[0].UploadStatus != 4 {
		t.Fatalf("upload status = %d, want 4", store.uploads[0].UploadStatus)
	}
	if len(store.visaRanges) != 1 {
		t.Fatalf("expected 1 visa range, got %d", len(store.visaRanges))
	}
}

func TestProcessJaywanBin(t *testing.T) {
	store := &fakeStore{}
	dir := t.TempDir()
	svc := testService(t, store, dir)
	if err := os.MkdirAll(dir+"/processed", 0o755); err != nil {
		t.Fatal(err)
	}
	header := "issuer,institution,low,high,panLen,prodType,scheme,product,cardType,service,currency,iso,action,binLen,cap,class,badge"
	row1 := "NBK,1,4000000000000000,4000000999999999,16,D,V,credit,0,1,840,840,A,16,Y,P,B"
	row2 := "NBK,1,4000001000000000,4000001999999999,16,D,V,credit,0,1,840,840,A,16,Y,P,B"
	writeFile(t, dir+"/jaywan.csv", header+"\n"+row1+"\n"+row2+"\n")

	seedUploadLog(store, "jaywan.csv")
	(&jaywanProcessor{svc: svc}).processJaywanBin(context.Background(), "jaywan.csv", 1, 1)
	if len(store.jaywanRanges) != 2 {
		t.Fatalf("inserted %d jaywan ranges, want 2", len(store.jaywanRanges))
	}
	if store.uploads[0].UploadStatus != 4 || store.uploads[0].TotalTxnCount != 2 {
		t.Fatalf("upload log not updated: %+v", store.uploads[0])
	}
}

func TestProcessMercuryBin(t *testing.T) {
	store := &fakeStore{}
	dir := t.TempDir()
	svc := testService(t, store, dir)
	if err := os.MkdirAll(dir+"/processed", 0o755); err != nil {
		t.Fatal(err)
	}
	header := "low,high,cardType,product,var,scheme,currency,country,status"
	writeFile(t, dir+"/mercury.csv",
		header+"\n"+
			"5000000000000000,5000000999999999,1,100,1,1,840,784,A\n"+
			"5000001000000000,5000001999999999,1,100,1,1,840,784,D\n")
	seedUploadLog(store, "mercury.csv")
	(&mercuryProcessor{svc: svc}).processMercuryBin(context.Background(), "mercury.csv", 1, 1)
	if len(store.mercury) != 1 {
		t.Fatalf("saved %d mercury rows, want 1 (A only)", len(store.mercury))
	}
	if store.mercury[0].Status != "A" || store.mercury[0].CardType != 1 {
		t.Fatalf("unexpected mercury row: %+v", store.mercury[0])
	}
	if store.uploads[0].UploadStatus != 4 {
		t.Fatalf("upload status = %d, want 4", store.uploads[0].UploadStatus)
	}
}

func TestProcessBinFlow(t *testing.T) {
	dir := t.TempDir()
	writeFile(t, dir+"/file.bin", "data")
	svc := testService(t, &fakeStore{}, dir)

	// success -> scheduled message
	resp := svc.ProcessBin(context.Background(), "file.bin", "MASTERCARD")
	if resp.Message != "BIN File Processing Scheduled Successfully." {
		t.Fatalf("message = %q", resp.Message)
	}
	if len(svc.Store.(*fakeStore).jobs) != 1 {
		t.Fatalf("expected 1 processing job")
	}

	// duplicate filename -> duplicate message; the processing job is still
	// inserted first (matching Java: insertProcessingJob runs before the
	// upload-log duplicate check).
	resp = svc.ProcessBin(context.Background(), "file.bin", "MASTERCARD")
	if resp.Message != "DUPLICATE_FILENAME; provided filename is already exist." {
		t.Fatalf("message = %q", resp.Message)
	}
	if len(svc.Store.(*fakeStore).jobs) != 2 {
		t.Fatalf("expected 2 processing jobs, got %d", len(svc.Store.(*fakeStore).jobs))
	}

	// one file in processing (status 9) -> blocked
	store2 := &fakeStore{}
	store2.uploads = append(store2.uploads, &UploadLog{UploadStatus: 9})
	svc2 := testService(t, store2, dir)
	resp = svc2.ProcessBin(context.Background(), "file.bin", "MASTERCARD")
	if resp.Message != "FAILED_TO_PROCESS; one file in processing." {
		t.Fatalf("message = %q", resp.Message)
	}

	// missing file -> file-not-found
	store3 := &fakeStore{}
	svc3 := testService(t, store3, dir)
	resp = svc3.ProcessBin(context.Background(), "missing.bin", "MASTERCARD")
	if !strings.HasPrefix(resp.Message, "The file was not found at the specified path.") {
		t.Fatalf("message = %q", resp.Message)
	}

	// visa validation: non-.txt rejected
	writeFile(t, dir+"/visa.dat", "x")
	store4 := &fakeStore{}
	svc4 := testService(t, store4, dir)
	resp = svc4.ProcessBin(context.Background(), "visa.dat", "VISA")
	if resp.Message != "Invalid VISA file" {
		t.Fatalf("message = %q", resp.Message)
	}
}

func TestBinFileDeletion(t *testing.T) {
	dir := t.TempDir()
	store := &fakeStore{}
	store.uploads = append(store.uploads, &UploadLog{SerialNumber: 7, FileName: "gone.bin", UploadStatus: 5, JobNumber: 3})
	store.mcRanges = append(store.mcRanges, &McRange{SerialNumber: 1, JobSerialNumber: 3})
	svc := testService(t, store, dir)

	resp := svc.BinFileDeletion(context.Background(), "gone.bin", "MASTERCARD")
	if resp.Message != "File deleted successfully." {
		t.Fatalf("message = %q", resp.Message)
	}
	if len(store.mcRanges) != 0 || len(store.uploads) != 0 {
		t.Fatalf("expected ranges and upload log to be deleted")
	}

	// not status 5 -> not allowed
	store.uploads = append(store.uploads, &UploadLog{FileName: "busy.bin", UploadStatus: 4})
	resp = svc.BinFileDeletion(context.Background(), "busy.bin", "MASTERCARD")
	if resp.Message != "DELETION_NOT_ALLOWED; file deletion not allowed." {
		t.Fatalf("message = %q", resp.Message)
	}

	// missing -> not found
	resp = svc.BinFileDeletion(context.Background(), "nope.bin", "MASTERCARD")
	if !strings.HasPrefix(resp.Message, "FILE_NOT_FOUND;") {
		t.Fatalf("message = %q", resp.Message)
	}
}
