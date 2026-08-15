package outsvc

import (
	"context"
	"os"
	"path/filepath"
	"strings"
	"testing"
	"time"
)

// visaFakeStore implements just the Store methods exercised by the
// ProcessVisaOutgoing success/failure paths; the embedded Store interface
// panics on any other method.
type visaFakeStore struct {
	Store
	work         []*VisaAcqTxnWorkEntity
	data         []*VisaAcqTxnDataEntity
	fileLogs     []*OutGoingFileProcessingEntity
	nextSerial   int64
	acqBin       *AcquirerBinsEntity
	interfaces   *InterfacesEntity
	format       *FileFormatsEntity
	businessDate *BusinessDateEntity
	summaries    []*OutgoingSummaryEntity
	posDone      bool
}

func (f *visaFakeStore) FindFileFormatBySystemCodeAndType(ctx context.Context, sysCode int, typ string) (*FileFormatsEntity, error) {
	return f.format, nil
}

func (f *visaFakeStore) FindInterfaceByCategory(ctx context.Context, category string) (*InterfacesEntity, error) {
	return f.interfaces, nil
}

func (f *visaFakeStore) FindFileLogByFormatCodeAndStatuses(ctx context.Context, formatCode int) ([]*OutGoingFileProcessingEntity, error) {
	var out []*OutGoingFileProcessingEntity
	for _, l := range f.fileLogs {
		if l.FormatCode == formatCode && (l.GeneratedStatus == 1 || l.GeneratedStatus == 9) {
			out = append(out, l)
		}
	}
	return out, nil
}

func (f *visaFakeStore) InsertFileLog(ctx context.Context, e *OutGoingFileProcessingEntity) (int64, error) {
	f.nextSerial++
	e.SerialNumber = f.nextSerial
	f.fileLogs = append(f.fileLogs, e)
	return e.SerialNumber, nil
}

func (f *visaFakeStore) FindFileLogByInstitutionAndSerial(ctx context.Context, ins int, ser int64) (*OutGoingFileProcessingEntity, error) {
	for _, l := range f.fileLogs {
		if l.SerialNumber == ser {
			return l, nil
		}
	}
	return nil, nil
}

func (f *visaFakeStore) UpdateFileLog(ctx context.Context, e *OutGoingFileProcessingEntity) error {
	for i, l := range f.fileLogs {
		if l.SerialNumber == e.SerialNumber {
			f.fileLogs[i] = e
		}
	}
	return nil
}

func (f *visaFakeStore) FindFileLogTopByGeneratedStatus(ctx context.Context, status int) (*OutGoingFileProcessingEntity, error) {
	var top *OutGoingFileProcessingEntity
	for _, l := range f.fileLogs {
		if l.GeneratedStatus == status && (top == nil || l.LastUpdated.After(top.LastUpdated)) {
			top = l
		}
	}
	return top, nil
}

func (f *visaFakeStore) FindBusinessDateByInstitution(ctx context.Context, ins int) (*BusinessDateEntity, error) {
	return f.businessDate, nil
}

func (f *visaFakeStore) FindAcquirerBins(ctx context.Context, ins int, binType string) ([]*AcquirerBinsEntity, error) {
	return []*AcquirerBinsEntity{f.acqBin}, nil
}

func (f *visaFakeStore) UpdateAcquirerBin(ctx context.Context, e *AcquirerBinsEntity) error {
	f.acqBin = e
	return nil
}

func (f *visaFakeStore) findVisaByStatus(status int) []*VisaAcqTxnWorkEntity {
	var out []*VisaAcqTxnWorkEntity
	for _, w := range f.work {
		if w.GeneralStatus == status {
			out = append(out, w)
		}
	}
	return out
}

func (f *visaFakeStore) FindVisaWorkBetween(ctx context.Context, ins, intCode, status int, from, to time.Time) ([]*VisaAcqTxnWorkEntity, error) {
	return f.findVisaByStatus(status), nil
}

func (f *visaFakeStore) FindVisaWorkLessThanEqual(ctx context.Context, ins, intCode, status int, to time.Time) ([]*VisaAcqTxnWorkEntity, error) {
	return f.findVisaByStatus(status), nil
}

func (f *visaFakeStore) findVisaFee(status int, txnCode []string) []*VisaAcqTxnWorkEntity {
	in := func(c string) bool {
		for _, x := range txnCode {
			if x == c {
				return true
			}
		}
		return false
	}
	var out []*VisaAcqTxnWorkEntity
	for _, w := range f.work {
		if w.GeneralStatus == status && in(w.TxnCode) {
			out = append(out, w)
		}
	}
	return out
}

func (f *visaFakeStore) findVisaTxn(status int, txnCode []string) []*VisaAcqTxnWorkEntity {
	in := func(c string) bool {
		for _, x := range txnCode {
			if x == c {
				return true
			}
		}
		return false
	}
	var out []*VisaAcqTxnWorkEntity
	for _, w := range f.work {
		if w.GeneralStatus == status && !in(w.TxnCode) {
			out = append(out, w)
		}
	}
	return out
}

func (f *visaFakeStore) FindVisaWorkFeeBetween(ctx context.Context, ins, intCode, status int, txnCode []string, from, to time.Time) ([]*VisaAcqTxnWorkEntity, error) {
	return f.findVisaFee(status, txnCode), nil
}

func (f *visaFakeStore) FindVisaWorkFeeLessThanEqual(ctx context.Context, ins, intCode, status int, txnCode []string, to time.Time) ([]*VisaAcqTxnWorkEntity, error) {
	return f.findVisaFee(status, txnCode), nil
}

func (f *visaFakeStore) FindVisaWorkTxnBetween(ctx context.Context, ins, intCode, status int, txnCode []string, from, to time.Time) ([]*VisaAcqTxnWorkEntity, error) {
	return f.findVisaTxn(status, txnCode), nil
}

func (f *visaFakeStore) FindVisaWorkTxnLessThanEqual(ctx context.Context, ins, intCode, status int, txnCode []string, to time.Time) ([]*VisaAcqTxnWorkEntity, error) {
	return f.findVisaTxn(status, txnCode), nil
}

func (f *visaFakeStore) FindVisaWorkByStatus(ctx context.Context, ins, status int) ([]*VisaAcqTxnWorkEntity, error) {
	return f.findVisaByStatus(status), nil
}

func (f *visaFakeStore) FindVisaWorkByFileId(ctx context.Context, ins int, fileId string) ([]*VisaAcqTxnWorkEntity, error) {
	var out []*VisaAcqTxnWorkEntity
	for _, w := range f.work {
		if w.FileId == fileId {
			out = append(out, w)
		}
	}
	return out, nil
}

func (f *visaFakeStore) FindVisaWorkByArn(ctx context.Context, arn string) ([]*VisaAcqTxnWorkEntity, error) {
	var out []*VisaAcqTxnWorkEntity
	for _, w := range f.work {
		if w.Arn == arn {
			out = append(out, w)
		}
	}
	return out, nil
}

func (f *visaFakeStore) UpdateVisaWorkStatuses(ctx context.Context, ents []*VisaAcqTxnWorkEntity) error {
	return nil
}

func (f *visaFakeStore) InsertVisaData(ctx context.Context, ents []*VisaAcqTxnDataEntity) error {
	f.data = ents
	return nil
}

func (f *visaFakeStore) DeleteVisaWork(ctx context.Context, ents []*VisaAcqTxnWorkEntity) error {
	f.work = nil
	return nil
}

func (f *visaFakeStore) CompleteVisaPosStatus(ctx context.Context, ins int) error {
	f.posDone = true
	return nil
}

func (f *visaFakeStore) InsertSummaries(ctx context.Context, ents []*OutgoingSummaryEntity) error {
	f.summaries = append(f.summaries, ents...)
	return nil
}

func newVisaFakeStore() *visaFakeStore {
	d := time.Date(2026, 8, 15, 0, 0, 0, 0, time.UTC)
	return &visaFakeStore{
		nextSerial: 0,
		acqBin:     &AcquirerBinsEntity{Bin: "10087096", InstitutionCode: 1, BinType: "V", OutFileSeq: 0},
		interfaces: &InterfacesEntity{InterfaceCode: 8},
		format:     &FileFormatsEntity{Code: 9},
		businessDate: &BusinessDateEntity{
			InstitutionCode: 1,
			BusinessDate:    d,
			LastBusinessDate: d.Add(-24 * time.Hour),
		},
	}
}

func TestProcessVisaOutgoingHappyPath(t *testing.T) {
	dir := t.TempDir()
	st := newVisaFakeStore()
	fee := visatEntity()
	fee.TxnCode = "10"
	fee.GeneralStatus = 3
	fee2 := visatEntity()
	fee2.TxnCode = "20"
	fee2.GeneralStatus = 3
	txn := visatEntity()
	txn.GeneralStatus = 3
	st.work = []*VisaAcqTxnWorkEntity{fee, fee2, txn}

	s := NewOutgoingService(OutgoingConfig{
		InsCode:           1,
		InsShortName:      "IRF",
		UpdatedUser:       4,
		ReconOutDir:       dir,
		CurrencyCodeKafka: "USD000",
	}, st, &fakeCrypto{dec: map[string]string{"tok1": "1234567890123456"}})

	from := time.Date(2026, 8, 15, 0, 0, 0, 0, time.UTC)
	to := time.Date(2026, 8, 15, 23, 59, 59, 0, time.UTC)
	got := s.ProcessVisaOutgoing(context.Background(), 1, 4, 5, "IRF", &from, &to)
	if got != "Success" {
		t.Fatalf("ProcessVisaOutgoing = %q, want Success", got)
	}

	fileName := "IRF_10087096_" + time.Now().Format("02012006") + ".001"
	outPath := filepath.Join(dir, fileName)
	info, err := os.Stat(outPath)
	if err != nil {
		t.Fatalf("output file missing: %v", err)
	}
	if info.Size() <= 0 {
		t.Fatal("output file empty")
	}
	b, _ := os.ReadFile(outPath)
	if !strings.HasPrefix(string(b), "0500") {
		t.Errorf("file starts %q, want tcr0", string(b[:min(4, len(b))]))
	}

	if len(st.summaries) != 3 {
		t.Fatalf("summaries = %d, want 3 (codes 05/10/20)", len(st.summaries))
	}
	if !st.posDone {
		t.Error("CompleteVisaPosStatus not called")
	}
	if len(st.data) != 3 {
		t.Fatalf("moved data rows = %d, want 3", len(st.data))
	}
	for _, d := range st.data {
		if d.GeneralStatus != 4 {
			t.Errorf("data row %q status = %d, want 4", d.Arn, d.GeneralStatus)
		}
	}
	if len(st.work) != 0 {
		t.Errorf("work rows not deleted: %d remain", len(st.work))
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
	if fileLog.FileId == nil || *fileLog.FileId != fileName {
		t.Errorf("file log fileId = %v, want %q", fileLog.FileId, fileName)
	}
}

func TestProcessVisaOutgoingDecryptFailed(t *testing.T) {
	dir := t.TempDir()
	st := newVisaFakeStore()
	txn := visatEntity()
	txn.GeneralStatus = 3
	st.work = []*VisaAcqTxnWorkEntity{txn}

	s := NewOutgoingService(OutgoingConfig{
		InsCode:           1,
		InsShortName:      "IRF",
		UpdatedUser:       4,
		ReconOutDir:       dir,
		CurrencyCodeKafka: "USD000",
	}, st, &fakeCrypto{dec: nil})

	from := time.Date(2026, 8, 15, 0, 0, 0, 0, time.UTC)
	to := time.Date(2026, 8, 15, 23, 59, 59, 0, time.UTC)
	got := s.ProcessVisaOutgoing(context.Background(), 1, 4, 5, "IRF", &from, &to)
	if got != "Outgoing Failed" {
		t.Fatalf("ProcessVisaOutgoing = %q, want Outgoing Failed", got)
	}
	if txn.GeneralStatus != 7 {
		t.Errorf("work status = %d, want 7 after decrypt failure", txn.GeneralStatus)
	}
	for _, l := range st.fileLogs {
		if l.GeneratedStatus != 5 {
			t.Errorf("file log status = %d, want 5", l.GeneratedStatus)
		}
	}
	if _, err := os.Stat(filepath.Join(dir, "IRF_10087096_"+time.Now().Format("02012006")+".001")); err == nil {
		t.Error("output file should not exist after failure")
	}
}

func TestProcessVisaOutgoingAlreadyScheduled(t *testing.T) {
	dir := t.TempDir()
	st := newVisaFakeStore()
	st.fileLogs = []*OutGoingFileProcessingEntity{{
		SerialNumber:    1,
		FormatCode:      9,
		GeneratedStatus: 9,
	}}
	s := NewOutgoingService(OutgoingConfig{
		InsCode:           1,
		InsShortName:      "IRF",
		UpdatedUser:       4,
		ReconOutDir:       dir,
		CurrencyCodeKafka: "USD000",
	}, st, &fakeCrypto{dec: map[string]string{"tok1": "1234567890123456"}})

	from := time.Date(2026, 8, 15, 0, 0, 0, 0, time.UTC)
	to := time.Date(2026, 8, 15, 23, 59, 59, 0, time.UTC)
	got := s.ProcessVisaOutgoing(context.Background(), 1, 4, 5, "IRF", &from, &to)
	if got != "File Generation already Scheduled" {
		t.Fatalf("ProcessVisaOutgoing = %q, want File Generation already Scheduled", got)
	}
}

func TestCellFloatParsesGoOraStrings(t *testing.T) {
	// go-ora v2 returns NUMBER columns as strings; cellFloat must parse them.
	cases := []struct {
		in   any
		want float64
	}{
		{"100.50", 100.5},
		{[]byte("1.25"), 1.25},
		{"0", 0},
		{float64(3.5), 3.5},
		{int64(7), 7},
		{"not-a-number", 0},
	}
	for _, c := range cases {
		if got := cellFloat(c.in); got != c.want {
			t.Errorf("cellFloat(%v) = %v, want %v", c.in, got, c.want)
		}
	}
	if got := cellInt("42"); got != 42 {
		t.Errorf("cellInt(string) = %d, want 42", got)
	}
}
