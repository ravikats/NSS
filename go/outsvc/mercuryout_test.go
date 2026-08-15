package outsvc

import (
	"context"
	"math/big"
	"os"
	"path/filepath"
	"strings"
	"testing"
	"time"
)

// mercuryFakeStore implements just the Store methods exercised by the
// ProcessMercuryOutgoing path; the embedded Store interface panics on any other
// method.
type mercuryFakeStore struct {
	Store
	work        []*MercuryAcqTxnWorkEntity
	data        []*MercuryAcqTxnDataEntity
	fileLogs    []*OutGoingFileProcessingEntity
	nextSerial  int64
	acqBin      *AcquirerBinsEntity
	interfaces  *InterfacesEntity
	format      *FileFormatsEntity
	summaries   []*OutgoingSummaryEntity
	posDone     bool
	statusUpd   map[int64]int
	fileIDUpd   map[int64]string
	userUpd     map[int64]int
}

func (f *mercuryFakeStore) FindFileFormatBySystemCodeAndType(ctx context.Context, sysCode int, typ string) (*FileFormatsEntity, error) {
	return f.format, nil
}

func (f *mercuryFakeStore) FindInterfaceByCategory(ctx context.Context, category string) (*InterfacesEntity, error) {
	return f.interfaces, nil
}

func (f *mercuryFakeStore) FindFileLogByFormatCodeAndStatuses(ctx context.Context, formatCode int) ([]*OutGoingFileProcessingEntity, error) {
	var out []*OutGoingFileProcessingEntity
	for _, l := range f.fileLogs {
		if l.FormatCode == formatCode && (l.GeneratedStatus == 1 || l.GeneratedStatus == 9) {
			out = append(out, l)
		}
	}
	return out, nil
}

func (f *mercuryFakeStore) InsertFileLog(ctx context.Context, e *OutGoingFileProcessingEntity) (int64, error) {
	f.nextSerial++
	e.SerialNumber = f.nextSerial
	f.fileLogs = append(f.fileLogs, e)
	return e.SerialNumber, nil
}

func (f *mercuryFakeStore) FindFileLogByInstitutionAndSerial(ctx context.Context, ins int, ser int64) (*OutGoingFileProcessingEntity, error) {
	for _, l := range f.fileLogs {
		if l.SerialNumber == ser {
			return l, nil
		}
	}
	return nil, nil
}

func (f *mercuryFakeStore) UpdateFileLog(ctx context.Context, e *OutGoingFileProcessingEntity) error {
	for i, l := range f.fileLogs {
		if l.SerialNumber == e.SerialNumber {
			f.fileLogs[i] = e
		}
	}
	return nil
}

func (f *mercuryFakeStore) FindBusinessDateByInstitution(ctx context.Context, ins int) (*BusinessDateEntity, error) {
	return nil, nil
}

func (f *mercuryFakeStore) FindAcquirerBins(ctx context.Context, ins int, binType string) ([]*AcquirerBinsEntity, error) {
	return []*AcquirerBinsEntity{f.acqBin}, nil
}

func (f *mercuryFakeStore) UpdateAcquirerBin(ctx context.Context, e *AcquirerBinsEntity) error {
	f.acqBin = e
	return nil
}

func (f *mercuryFakeStore) findMercuryByStatus(status int) []*MercuryAcqTxnWorkEntity {
	var out []*MercuryAcqTxnWorkEntity
	for _, w := range f.work {
		if w.GenStatus == status {
			out = append(out, w)
		}
	}
	return out
}

func (f *mercuryFakeStore) FindMercuryWorkBetween(ctx context.Context, ins, intCode, status int, from, to time.Time) ([]*MercuryAcqTxnWorkEntity, error) {
	return f.findMercuryByStatus(status), nil
}

func (f *mercuryFakeStore) FindMercuryWorkLessThanEqual(ctx context.Context, ins, intCode, status int, to time.Time) ([]*MercuryAcqTxnWorkEntity, error) {
	return f.findMercuryByStatus(status), nil
}

func (f *mercuryFakeStore) FindMercuryWorkByStatus(ctx context.Context, ins, status int) ([]*MercuryAcqTxnWorkEntity, error) {
	return f.findMercuryByStatus(status), nil
}

func (f *mercuryFakeStore) UpdateMercuryWorkStatuses(ctx context.Context, ents []*MercuryAcqTxnWorkEntity) error {
	for _, e := range ents {
		for _, w := range f.work {
			if w.SerialNumber == e.SerialNumber {
				w.GenStatus = e.GenStatus
				w.FileID = e.FileID
				w.UpdatedUser = e.UpdatedUser
				w.LastUpdated = e.LastUpdated
			}
		}
	}
	return nil
}

func (f *mercuryFakeStore) DeleteMercuryWork(ctx context.Context, ents []*MercuryAcqTxnWorkEntity) error {
	f.work = nil
	return nil
}

func (f *mercuryFakeStore) InsertMercuryData(ctx context.Context, ents []*MercuryAcqTxnDataEntity) error {
	f.data = ents
	return nil
}

func (f *mercuryFakeStore) CompleteMercuryPosStatus(ctx context.Context, ins int) error {
	f.posDone = true
	return nil
}

func (f *mercuryFakeStore) InsertSummaries(ctx context.Context, ents []*OutgoingSummaryEntity) error {
	f.summaries = append(f.summaries, ents...)
	return nil
}

func newMercuryFakeStore() *mercuryFakeStore {
	seqDate := time.Date(2026, 8, 3, 0, 0, 0, 0, time.UTC)
	ica := "970962"
	return &mercuryFakeStore{
		nextSerial: 0,
		acqBin: &AcquirerBinsEntity{
			InstitutionCode: 1,
			BinType:         "E",
			OutFileSeq:      10,
			OutfileDate:     &seqDate,
			McIcaNo:         &ica,
		},
		interfaces: &InterfacesEntity{InterfaceCode: 21},
		format:     &FileFormatsEntity{Code: 22},
	}
}

func mercuryWorkEntity() *MercuryAcqTxnWorkEntity {
	ldt := time.Date(2026, 8, 3, 16, 4, 59, 0, time.UTC)
	td := time.Date(2026, 8, 3, 0, 0, 0, 0, time.UTC)
	return &MercuryAcqTxnWorkEntity{
		SerialNumber:          1,
		InstitutionCode:       1,
		IntCode:               21,
		PrjSerNumber:          1,
		GenStatus:             3,
		MerchantId:            "M00000000000009",
		TerminalId:            "T0000005",
		TxnType:               "1240",
		TxnAmount:             10.00,
		LocalDateTime:         &ldt,
		TxnDate:               &td,
		ChargeType:            "AA",
		MeName:                "ALI MANZ STORE",
		MeCity:                "dubai",
		MeCountry:             "AE",
		CardAccepStreetAddress: "STREET 1",
		EstPhoneNumber:        "0501234567",
		Mcc:                   "9211",
		ApprovalCode:          "232068",
		Rrn:                   "621516000001",
		ResponseCode:          "00",
		PosEntryMode:          "071",
		EncryptedCardNumber:   "tok1",
	}
}

// TestMercuryFileBuilderMatchesJava drives the full ProcessMercuryOutgoing flow
// with fixed fixtures and asserts the file structure mirrors the Java EIF
// writer (FRRC lines joined by '>', batch flip at 60, recap/UT/UY totals).
func TestMercuryFileBuilderMatchesJava(t *testing.T) {
	dir := t.TempDir()
	st := newMercuryFakeStore()

	t1 := mercuryWorkEntity()
	t1.SerialNumber = 1
	t1.TxnAmount = 10.00
	t1.ChargeType = "AA"
	t1.MeName = "ALI MANZ STORE"
	t1.MeCity = "dubai"
	t1.PosEntryMode = "021"

	t2 := mercuryWorkEntity()
	t2.SerialNumber = 2
	t2.TxnAmount = 951.00
	t2.ChargeType = "TF"
	t2.PosEntryMode = "051"
	t2.ApprovalCode = "962981"
	t2.Rrn = "621007888375"
	t2.SurchargeAmount = 1.00

	t3 := mercuryWorkEntity()
	t3.SerialNumber = 3
	t3.TxnAmount = 100.00
	t3.ChargeType = "830"
	t3.PosEntryMode = "951"

	st.work = []*MercuryAcqTxnWorkEntity{t1, t2, t3}

	s := NewOutgoingService(OutgoingConfig{
		InsCode:            1,
		InsShortName:       "IRF",
		UpdatedUser:        4,
		ReconOutDir:        dir,
		CurrencyCodeKafka:  "AED000",
	}, st, &fakeCrypto{dec: map[string]string{"tok1": "6690109700100010"}})
	frozen := time.Date(2026, 8, 3, 13, 37, 57, 0, time.UTC)
	s.now = func() time.Time { return frozen }

	from := time.Date(2026, 8, 3, 0, 0, 0, 0, time.UTC)
	to := time.Date(2026, 8, 3, 23, 59, 59, 0, time.UTC)
	got := s.ProcessMercuryOutgoing(context.Background(), 1, 4, 5, "IRF", &from, &to)
	if got != "Success" {
		t.Fatalf("ProcessMercuryOutgoing = %q, want Success", got)
	}

	outPath := filepath.Join(dir, "EIF_03082026.010")
	gotBytes, err := os.ReadFile(outPath)
	if err != nil {
		t.Fatalf("output file missing: %v", err)
	}
	lines := strings.Split(strings.TrimRight(string(gotBytes), "\n"), "\n")

	if lines[0] != "FRRC>UX>RK>970962>MP>AED>030826" {
		t.Errorf("header line = %q", lines[0])
	}
	if lines[1] != "FRRC>UH>RK>970962>MP>001>030826" {
		t.Errorf("batch header line = %q", lines[1])
	}

	// t1 (debit AA): XD with decrypted PAN and formatted amount 10.00*100=1000.
	xd1 := lines[2]
	if !strings.HasPrefix(xd1, "FRRC>XD>RK>970962>MP>001>001>") {
		t.Fatalf("XD1 = %q", xd1)
	}
	xd1Fields := strings.Split(xd1, ">")
	if xd1Fields[7] != "6690109700100010" {
		t.Errorf("XD1 PAN = %q", xd1Fields[7])
	}
	if xd1Fields[8] != "1000" {
		t.Errorf("XD1 amount = %q, want 1000", xd1Fields[8])
	}
	if xd1Fields[9] != "260803" {
		t.Errorf("XD1 date = %q, want 260803 (yyMMdd)", xd1Fields[9])
	}

	// t2 (credit TF with surcharge): XD + XM (pos entry 051).
	xd2 := lines[3]
	if !strings.HasPrefix(xd2, "FRRC>XD>RK>970962>MP>001>002>") {
		t.Fatalf("XD2 = %q", xd2)
	}
	xd2Fields := strings.Split(xd2, ">")
	if xd2Fields[8] != "95100" {
		t.Errorf("XD2 amount = %q, want 95100", xd2Fields[8])
	}
	if xd2Fields[45] != "100" {
		t.Errorf("XD2 surcharge = %q, want 100 (field 45)", xd2Fields[45])
	}
	// XM record follows XD2 (index 4).
	if !strings.HasPrefix(lines[4], "FRRC>XM>RK>970962>MP>001>002>") {
		t.Fatalf("XM = %q", lines[4])
	}

	// t3 (charge type 830, pos entry 951): XD + XM + XC.
	xd3 := lines[5]
	if !strings.HasPrefix(xd3, "FRRC>XD>RK>970962>MP>001>003>") {
		t.Fatalf("XD3 = %q", xd3)
	}
	if !strings.HasPrefix(lines[6], "FRRC>XM>RK>970962>MP>001>003>") {
		t.Fatalf("XM3 = %q", lines[6])
	}
	xc3 := lines[7]
	if !strings.HasPrefix(xc3, "FRRC>XC>RK>970962>MP>001>003>") {
		t.Fatalf("XC3 = %q", xc3)
	}
	// XC terminal id must be the raw terminal id (13th field).
	xc3Fields := strings.Split(xc3, ">")
	if xc3Fields[12] != "T0000005" {
		t.Errorf("XC3 terminal id = %q, want T0000005", xc3Fields[12])
	}

	// Trailer: UT (1 credit 951.00, 2 debits 10.00+100.00) then UY with
	// net = credit - debit. Surcharges are excluded from these totals (the
	// Java builder only sums txnAmount).
	ut := lines[8]
	if ut != "FRRC>UT>RK>970962>MP>001>1>95100>2>11000" {
		t.Errorf("UT = %q", ut)
	}
	uy := lines[9]
	if uy != "FRRC>UY>RK>970962>MP>1>95100>2>11000>01.000>84100>>>>>>" {
		t.Errorf("UY = %q", uy)
	}

	if st.acqBin.OutFileSeq != 11 {
		t.Errorf("acquirer bin file seq = %d, want 11", st.acqBin.OutFileSeq)
	}
	if !st.posDone {
		t.Error("CompleteMercuryPosStatus not called")
	}
	if len(st.data) != 3 {
		t.Errorf("moved data rows = %d, want 3", len(st.data))
	}
	// Java insertIntoOutgoingSummary groups gen_status=9 rows, but those rows
	// were just marked 4 for this file, so a single-file run produces no
	// summary rows (a faithful port preserves this quirk).
	if len(st.summaries) != 0 {
		t.Errorf("summaries = %d, want 0 (single-file run; Java groups status-9 rows)", len(st.summaries))
	}

	var fileLog *OutGoingFileProcessingEntity
	for _, l := range st.fileLogs {
		if l.SerialNumber == 1 {
			fileLog = l
		}
	}
	if fileLog == nil {
		t.Fatal("file log missing")
	}
	if fileLog.GeneratedStatus != 4 {
		t.Errorf("file log status = %d, want 4", fileLog.GeneratedStatus)
	}
	if fileLog.FileId == nil || *fileLog.FileId != "EIF_03082026.010" {
		t.Errorf("file log file id = %v", fileLog.FileId)
	}
}

// TestMercuryBatchFlipAt60 asserts a new batch header (UH) and UT trailer are
// emitted once 60 transaction records accumulate.
func TestMercuryBatchFlipAt60(t *testing.T) {
	dir := t.TempDir()
	st := newMercuryFakeStore()

	var work []*MercuryAcqTxnWorkEntity
	for i := 1; i <= 61; i++ {
		w := mercuryWorkEntity()
		w.SerialNumber = int64(i)
		w.TxnAmount = float64(i)
		w.ChargeType = "AA"
		w.Rrn = "RR" + strings.Repeat("0", 7) + time.Time{}.Format("2")
		st.work = append(st.work, w)
	}
	work = st.work

	s := NewOutgoingService(OutgoingConfig{
		InsCode:           1,
		InsShortName:      "IRF",
		UpdatedUser:       4,
		ReconOutDir:       dir,
		CurrencyCodeKafka: "AED000",
	}, st, &fakeCrypto{dec: map[string]string{"tok1": "6690109700100010"}})
	s.now = func() time.Time { return time.Date(2026, 8, 3, 13, 37, 57, 0, time.UTC) }

	from := time.Date(2026, 8, 3, 0, 0, 0, 0, time.UTC)
	to := time.Date(2026, 8, 3, 23, 59, 59, 0, time.UTC)
	if got := s.ProcessMercuryOutgoing(context.Background(), 1, 4, 5, "IRF", &from, &to); got != "Success" {
		t.Fatalf("ProcessMercuryOutgoing = %q, want Success", got)
	}

	gotBytes, err := os.ReadFile(filepath.Join(dir, "EIF_03082026.010"))
	if err != nil {
		t.Fatalf("output file missing: %v", err)
	}
	lines := strings.Split(strings.TrimRight(string(gotBytes), "\n"), "\n")

	var uh []string
	var ut []string
	for _, l := range lines {
		if strings.HasPrefix(l, "FRRC>UH>") {
			uh = append(uh, l)
		}
		if strings.HasPrefix(l, "FRRC>UT>") {
			ut = append(ut, l)
		}
	}
	if len(uh) != 2 {
		t.Fatalf("batch headers = %d, want 2", len(uh))
	}
	if !strings.HasPrefix(uh[1], "FRRC>UH>RK>970962>MP>002>") {
		t.Errorf("second batch header = %q", uh[1])
	}
	if len(ut) != 2 {
		t.Fatalf("batch trailers = %d, want 2", len(ut))
	}
	// Second UT should report the single batch-2 txn (61.00 * 100 = 6100).
	if ut[1] != "FRRC>UT>RK>970962>MP>002>0>0>1>6100" {
		t.Errorf("second UT = %q", ut[1])
	}
	_ = work
}

// TestMercuryDecryptFailure asserts status 7 is set when decryption fails.
func TestMercuryDecryptFailure(t *testing.T) {
	dir := t.TempDir()
	st := newMercuryFakeStore()

	w := mercuryWorkEntity()
	w.SerialNumber = 1
	st.work = []*MercuryAcqTxnWorkEntity{w}

	s := NewOutgoingService(OutgoingConfig{
		InsCode:           1,
		InsShortName:      "IRF",
		UpdatedUser:       4,
		ReconOutDir:       dir,
		CurrencyCodeKafka: "AED000",
	}, st, &fakeCrypto{dec: nil})

	from := time.Date(2026, 8, 3, 0, 0, 0, 0, time.UTC)
	to := time.Date(2026, 8, 3, 23, 59, 59, 0, time.UTC)
	if got := s.ProcessMercuryOutgoing(context.Background(), 1, 4, 5, "IRF", &from, &to); got != "Outgoing Failed" {
		t.Fatalf("ProcessMercuryOutgoing = %q, want Outgoing Failed", got)
	}
	if st.work[0].GenStatus != 7 {
		t.Errorf("row gen status = %d, want 7", st.work[0].GenStatus)
	}
}

// TestMercuryHelpers exercises the numeric formatting helpers used by the EIF
// builder for the AED (2 fraction digits) multiplier of 100.
func TestMercuryHelpers(t *testing.T) {
	mult := big.NewRat(100, 1)
	cases := []struct {
		name string
		got  string
		want string
	}{
		{"amount 10", mercuryFormatAmount(10.00, mult), "1000"},
		{"amount 951", mercuryFormatAmount(951.00, mult), "95100"},
		{"amount half-up", mercuryFormatAmount(10.005, mult), "1001"},
		{"amount12 10", mercuryAmount12(10.00, mult), "000010000000"},
		{"amount12 1.5", mercuryAmount12(1.50, mult), "000001500000"},
		{"textLen short", mercuryTextLen("AB", 6), "AB    "},
		{"textLen long", mercuryTextLen("ABCDEFGH", 4), "ABCD"},
		{"textLen empty", mercuryTextLen("", 3), "   "},
		{"fixed empty", mercuryFixed("", 10), ""},
		{"trimTo short", mercuryTrimTo("AB", 6), "AB"},
		{"trimTo long", mercuryTrimTo("ABCDEFGH", 4), "ABCD"},
		{"trimTo empty", mercuryTrimTo("", 6), ""},
		{"date", mercuryDate(timePtr(2026, 8, 3, 16, 4, 59)), "260803"},
		{"localTime", mercuryLocalTime(timePtr(2026, 8, 3, 16, 4, 59)), "160459"},
		{"neg half-up", mercuryFormatAmount(-2.5, mult), "-250"},
	}
	for _, c := range cases {
		if c.got != c.want {
			t.Errorf("%s = %q, want %q", c.name, c.got, c.want)
		}
	}
}

func timePtr(y int, mo time.Month, d, h, mi, s int) *time.Time {
	t := time.Date(y, mo, d, h, mi, s, 0, time.UTC)
	return &t
}
