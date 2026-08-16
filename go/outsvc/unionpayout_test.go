package outsvc

import (
	"context"
	"os"
	"path/filepath"
	"strings"
	"testing"
	"time"
)

// unionPayFakeStore implements the Store subset used by ProcessUnionPayOutgoing
// via an embedded Store interface for the untouched methods.
type unionPayFakeStore struct {
	Store
	format     *FileFormatsEntity
	interfaces *InterfacesEntity
	fileLogs   []*OutGoingFileProcessingEntity
	nextSerial int64
	acqBin     *AcquirerBinsEntity
	work       []*UnionPayAcqTxnWorkEntity
	data       []*UnionPayAcqTxnDataEntity
	summaries  []*OutgoingSummaryEntity
	posDone    bool
}

func (f *unionPayFakeStore) FindFileFormatBySystemCodeAndType(ctx context.Context, sysCode int, typ string) (*FileFormatsEntity, error) {
	return f.format, nil
}

func (f *unionPayFakeStore) FindInterfaceByCategory(ctx context.Context, category string) (*InterfacesEntity, error) {
	return f.interfaces, nil
}

func (f *unionPayFakeStore) FindFileLogByFormatCodeAndStatuses(ctx context.Context, formatCode int) ([]*OutGoingFileProcessingEntity, error) {
	var out []*OutGoingFileProcessingEntity
	for _, l := range f.fileLogs {
		if l.FormatCode == formatCode && (l.GeneratedStatus == 1 || l.GeneratedStatus == 9) {
			out = append(out, l)
		}
	}
	return out, nil
}

func (f *unionPayFakeStore) InsertFileLog(ctx context.Context, e *OutGoingFileProcessingEntity) (int64, error) {
	f.nextSerial++
	e.SerialNumber = f.nextSerial
	f.fileLogs = append(f.fileLogs, e)
	return e.SerialNumber, nil
}

func (f *unionPayFakeStore) FindFileLogByInstitutionAndSerial(ctx context.Context, ins int, ser int64) (*OutGoingFileProcessingEntity, error) {
	for _, l := range f.fileLogs {
		if l.SerialNumber == ser {
			return l, nil
		}
	}
	return nil, nil
}

func (f *unionPayFakeStore) UpdateFileLog(ctx context.Context, e *OutGoingFileProcessingEntity) error {
	for i, l := range f.fileLogs {
		if l.SerialNumber == e.SerialNumber {
			f.fileLogs[i] = e
		}
	}
	return nil
}

func (f *unionPayFakeStore) FindBusinessDateByInstitution(ctx context.Context, ins int) (*BusinessDateEntity, error) {
	return nil, nil
}

func (f *unionPayFakeStore) FindAcquirerBins(ctx context.Context, ins int, binType string) ([]*AcquirerBinsEntity, error) {
	return []*AcquirerBinsEntity{f.acqBin}, nil
}

func (f *unionPayFakeStore) UpdateAcquirerBin(ctx context.Context, e *AcquirerBinsEntity) error {
	f.acqBin = e
	return nil
}

func (f *unionPayFakeStore) findUnionPayByStatus(status int) []*UnionPayAcqTxnWorkEntity {
	var out []*UnionPayAcqTxnWorkEntity
	for _, w := range f.work {
		if w.GenStatus == status {
			out = append(out, w)
		}
	}
	return out
}

func (f *unionPayFakeStore) FindUnionPayWorkBetween(ctx context.Context, ins, intCode, status int, from, to time.Time) ([]*UnionPayAcqTxnWorkEntity, error) {
	return f.findUnionPayByStatus(status), nil
}

func (f *unionPayFakeStore) FindUnionPayWorkLessThanEqual(ctx context.Context, ins, intCode, status int, to time.Time) ([]*UnionPayAcqTxnWorkEntity, error) {
	return f.findUnionPayByStatus(status), nil
}

func (f *unionPayFakeStore) FindUnionPayWorkByStatus(ctx context.Context, ins, status int) ([]*UnionPayAcqTxnWorkEntity, error) {
	return f.findUnionPayByStatus(status), nil
}

func (f *unionPayFakeStore) UpdateUnionPayWorkStatuses(ctx context.Context, ents []*UnionPayAcqTxnWorkEntity) error {
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

func (f *unionPayFakeStore) DeleteUnionPayWork(ctx context.Context, ents []*UnionPayAcqTxnWorkEntity) error {
	f.work = nil
	return nil
}

func (f *unionPayFakeStore) InsertUnionPayData(ctx context.Context, ents []*UnionPayAcqTxnDataEntity) error {
	f.data = ents
	return nil
}

func (f *unionPayFakeStore) CompleteUnionPayPosStatus(ctx context.Context, ins int) error {
	f.posDone = true
	return nil
}

func (f *unionPayFakeStore) InsertSummaries(ctx context.Context, ents []*OutgoingSummaryEntity) error {
	f.summaries = append(f.summaries, ents...)
	return nil
}

func unionPayWorkEntity() *UnionPayAcqTxnWorkEntity {
	ldt := time.Date(2026, 8, 3, 16, 4, 59, 0, time.UTC)
	td := time.Date(2026, 8, 3, 0, 0, 0, 0, time.UTC)
	return &UnionPayAcqTxnWorkEntity{
		InstitutionCode:     1,
		IntCode:             22,
		GenStatus:           3,
		MerchantId:          "M00000000000009",
		TerminalId:          "T0000005",
		TxnType:             "1240",
		TxnAmount:           10.00,
		LocalDateTime:       &ldt,
		TxnDate:             &td,
		MeName:              "ALI MANZ STORE",
		MeCountry:           "AE",
		Mcc:                 "9211",
		ApprovalCode:        "232068",
		Rrn:                 "621516000001",
		StanNumber:          "123456",
		TxnCurCode:          "784",
		PosEntryMode:        "021",
		AcqinstIdCode:       "12345678",
		EncryptedCardNumber: "tok1",
	}
}

// TestUnionPayFileBuilder drives the full ProcessUnionPayOutgoing flow and
// asserts the file layout matches the UnionPay Part III spec: TC000 header,
// TC100 records (Block 0/1 for magstripe, +Block 2 for chip), TC001 trailer,
// CRLF terminators, OFCYYMMDD5?C naming.
func TestUnionPayFileBuilder(t *testing.T) {
	dir := t.TempDir()
	seqDate := time.Date(2026, 8, 3, 0, 0, 0, 0, time.UTC)
	ica := "970962"
	st := &unionPayFakeStore{
		format:     &FileFormatsEntity{Code: 9},
		interfaces: &InterfacesEntity{InterfaceCode: 22},
		acqBin: &AcquirerBinsEntity{
			InstitutionCode: 1,
			BinType:         "U",
			McIcaNo:         &ica,
			OutFileSeq:      1,
			OutfileDate:     &seqDate,
		},
	}

	t1 := unionPayWorkEntity()
	t1.SerialNumber = 1
	t2 := unionPayWorkEntity()
	t2.SerialNumber = 2
	t2.TxnAmount = 951.00
	t2.PosEntryMode = "071"
	t2.AppCryptogram = "ABCDEF1234567890"
	t2.AppTxnCounter = "00A1"
	t3 := unionPayWorkEntity()
	t3.SerialNumber = 3
	t3.TxnAmount = 100.00
	t3.PosEntryMode = "051"
	t3.SurchargeAmount = 1.00

	st.work = []*UnionPayAcqTxnWorkEntity{t1, t2, t3}

	s := NewOutgoingService(OutgoingConfig{
		InsCode:            1,
		InsShortName:       "IRF",
		UpdatedUser:        4,
		ReconOutDir:        dir,
		CurrencyCodeKafka:  "AED000",
		UnionPayVersionTag: "TEST",
	}, st, &fakeCrypto{dec: map[string]string{"tok1": "6690109700100010"}})
	frozen := time.Date(2026, 8, 3, 13, 37, 57, 0, time.UTC)
	s.now = func() time.Time { return frozen }

	from := time.Date(2026, 8, 3, 0, 0, 0, 0, time.UTC)
	to := time.Date(2026, 8, 3, 23, 59, 59, 0, time.UTC)
	got := s.ProcessUnionPayOutgoing(context.Background(), 1, 4, 9, "IRF", &from, &to)
	if got != "Success" {
		t.Fatalf("ProcessUnionPayOutgoing = %q, want Success", got)
	}

	outPath := filepath.Join(dir, "OFC26080351C")
	gotBytes, err := os.ReadFile(outPath)
	if err != nil {
		t.Fatalf("output file missing: %v", err)
	}
	raw := string(gotBytes)
	lines := strings.Split(strings.TrimRight(raw, "\r\n"), "\r\n")
	if len(lines) != 5 {
		t.Fatalf("got %d lines, want 5 (header + 3 txns + trailer)", len(lines))
	}

	// TC000 header: 46 chars, Block0 bitmap, IIN, file date, TEST tag.
	hdr := lines[0]
	if len(hdr) != 46 {
		t.Errorf("header length = %d, want 46", len(hdr))
	}
	if hdr[:7] != "0008000" {
		t.Errorf("header prefix = %q, want 0008000", hdr[:7])
	}
	if hdr[7:18] != "970962     " {
		t.Errorf("header IIN field = %q", hdr[7:18])
	}
	if hdr[18:26] != "20260803" {
		t.Errorf("header date = %q", hdr[18:26])
	}
	if !strings.HasSuffix(hdr, "TEST00000001") {
		t.Errorf("header tail = %q, want ...TEST00000001", hdr[len(hdr)-12:])
	}

	// t1: magstripe (pos entry 021) -> Block 0 + Block 1 only (C000, 387 chars).
	rec1 := lines[1]
	if len(rec1) != 387 {
		t.Errorf("magstripe record length = %d, want 387", len(rec1))
	}
	if rec1[:7] != "100C000" {
		t.Errorf("magstripe record prefix = %q, want 100C000", rec1[:7])
	}
	if rec1[7:26] != "6690109700100010   " {
		t.Errorf("PAN field = %q", rec1[7:26])
	}
	if rec1[26:38] != "000000001000" {
		t.Errorf("amount field = %q, want 000000001000", rec1[26:38])
	}
	if rec1[38:41] != "784" {
		t.Errorf("currency field = %q, want 784", rec1[38:41])
	}
	if rec1[41:51] != "0803160459" {
		t.Errorf("datetime field = %q, want 0803160459", rec1[41:51])
	}

	// t2: chip (pos entry 071) -> Block 0 + 1 + 2 (E000, 681 chars).
	rec2 := lines[2]
	if len(rec2) != 681 {
		t.Errorf("chip record length = %d, want 681", len(rec2))
	}
	if rec2[:7] != "100E000" {
		t.Errorf("chip record prefix = %q, want 100E000", rec2[:7])
	}
	if rec2[387:403] != "ABCDEF1234567890" {
		t.Errorf("Block2 cryptogram = %q", rec2[387:403])
	}

	// t3: chip (pos entry 051).
	rec3 := lines[3]
	if len(rec3) != 681 {
		t.Errorf("chip record 3 length = %d, want 681", len(rec3))
	}

	// TC001 trailer: 49 chars, total record count incl. header+trailer.
	trl := lines[4]
	if len(trl) != 49 {
		t.Errorf("trailer length = %d, want 49", len(trl))
	}
	if trl[:7] != "0018000" {
		t.Errorf("trailer prefix = %q, want 0018000", trl[:7])
	}
	if trl[7:17] != "0000000005" {
		t.Errorf("trailer count = %q, want 0000000005", trl[7:17])
	}

	// CRLF terminators, no stray LF-only or trailing garbage.
	if !strings.Contains(raw, "\r\n") || strings.Contains(strings.ReplaceAll(raw, "\r\n", ""), "\n") {
		t.Errorf("file must use CRLF line endings")
	}

	// Post-processing: work moved to data, POS completed, bin sequence bumped.
	if len(st.data) != 3 {
		t.Errorf("data rows = %d, want 3", len(st.data))
	}
	if !st.posDone {
		t.Errorf("CompleteUnionPayPosStatus not called")
	}
	if st.acqBin.OutFileSeq != 2 {
		t.Errorf("acq bin file seq = %d, want 2", st.acqBin.OutFileSeq)
	}
}

func TestUnionPayFileName(t *testing.T) {
	now := time.Date(2026, 8, 3, 13, 37, 57, 0, time.UTC)
	if got := unionPayFileName(now, 1); got != "OFC26080351C" {
		t.Errorf("unionPayFileName(seq=1) = %q, want OFC26080351C", got)
	}
	if got := unionPayFileName(now, 10); got != "OFC26080350C" {
		t.Errorf("unionPayFileName(seq=10) = %q, want OFC26080350C", got)
	}
}

func TestUnionPayIsChipTxn(t *testing.T) {
	cases := []struct {
		posEntry string
		chip     bool
	}{
		{"021", false},
		{"051", true},
		{"071", true},
		{"951", true},
		{"", false},
	}
	for _, c := range cases {
		txn := &UnionPayAcqTxnWorkEntity{PosEntryMode: c.posEntry}
		if got := unionPayIsChipTxn(txn); got != c.chip {
			t.Errorf("unionPayIsChipTxn(%q) = %v, want %v", c.posEntry, got, c.chip)
		}
	}
}
