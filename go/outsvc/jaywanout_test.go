package outsvc

import (
	"bytes"
	"context"
	"os"
	"path/filepath"
	"testing"
	"time"
)

// jaywanFakeStore implements just the Store methods exercised by the
// ProcessJaywanOutgoing path; the embedded Store interface panics on any other
// method.
type jaywanFakeStore struct {
	Store
	work        []*JaywanAcqTxnWorkEntity
	netData     []*JaywanNetworkDataEntity
	data        []*JaywanAcqTxnDataEntity
	fileLogs    []*OutGoingFileProcessingEntity
	nextSerial  int64
	acqBin      *AcquirerBinsEntity
	interfaces  *InterfacesEntity
	format      *FileFormatsEntity
	summaries   []*OutgoingSummaryEntity
	posDone     bool
}

func (f *jaywanFakeStore) FindFileFormatBySystemCodeAndType(ctx context.Context, sysCode int, typ string) (*FileFormatsEntity, error) {
	return f.format, nil
}

func (f *jaywanFakeStore) FindInterfaceByCategory(ctx context.Context, category string) (*InterfacesEntity, error) {
	return f.interfaces, nil
}

func (f *jaywanFakeStore) FindFileLogByFormatCodeAndStatuses(ctx context.Context, formatCode int) ([]*OutGoingFileProcessingEntity, error) {
	var out []*OutGoingFileProcessingEntity
	for _, l := range f.fileLogs {
		if l.FormatCode == formatCode && (l.GeneratedStatus == 1 || l.GeneratedStatus == 9) {
			out = append(out, l)
		}
	}
	return out, nil
}

func (f *jaywanFakeStore) InsertFileLog(ctx context.Context, e *OutGoingFileProcessingEntity) (int64, error) {
	f.nextSerial++
	e.SerialNumber = f.nextSerial
	f.fileLogs = append(f.fileLogs, e)
	return e.SerialNumber, nil
}

func (f *jaywanFakeStore) FindFileLogByInstitutionAndSerial(ctx context.Context, ins int, ser int64) (*OutGoingFileProcessingEntity, error) {
	for _, l := range f.fileLogs {
		if l.SerialNumber == ser {
			return l, nil
		}
	}
	return nil, nil
}

func (f *jaywanFakeStore) UpdateFileLog(ctx context.Context, e *OutGoingFileProcessingEntity) error {
	for i, l := range f.fileLogs {
		if l.SerialNumber == e.SerialNumber {
			f.fileLogs[i] = e
		}
	}
	return nil
}

func (f *jaywanFakeStore) FindBusinessDateByInstitution(ctx context.Context, ins int) (*BusinessDateEntity, error) {
	return nil, nil
}

func (f *jaywanFakeStore) FindAcquirerBins(ctx context.Context, ins int, binType string) ([]*AcquirerBinsEntity, error) {
	return []*AcquirerBinsEntity{f.acqBin}, nil
}

func (f *jaywanFakeStore) UpdateAcquirerBin(ctx context.Context, e *AcquirerBinsEntity) error {
	f.acqBin = e
	return nil
}

func (f *jaywanFakeStore) findJaywanByStatus(status int) []*JaywanAcqTxnWorkEntity {
	var out []*JaywanAcqTxnWorkEntity
	for _, w := range f.work {
		if w.GenStatus == status {
			out = append(out, w)
		}
	}
	return out
}

func (f *jaywanFakeStore) FindJaywanWorkBetween(ctx context.Context, ins, intCode, status int, from, to time.Time) ([]*JaywanAcqTxnWorkEntity, error) {
	return f.findJaywanByStatus(status), nil
}

func (f *jaywanFakeStore) FindJaywanWorkLessThanEqual(ctx context.Context, ins, intCode, status int, to time.Time) ([]*JaywanAcqTxnWorkEntity, error) {
	return f.findJaywanByStatus(status), nil
}

func (f *jaywanFakeStore) FindJaywanWorkByIntAndStatus(ctx context.Context, ins, intCode, status int) ([]*JaywanAcqTxnWorkEntity, error) {
	return f.findJaywanByStatus(status), nil
}

func (f *jaywanFakeStore) FindJaywanWorkByRrn(ctx context.Context, rrn string) ([]*JaywanAcqTxnWorkEntity, error) {
	var out []*JaywanAcqTxnWorkEntity
	for _, w := range f.work {
		if w.Rrn == rrn {
			out = append(out, w)
		}
	}
	return out, nil
}

func (f *jaywanFakeStore) FindJaywanNetworkDataByRef(ctx context.Context, prjSerNumber int64, txnRefNumbers []int64) ([]*JaywanNetworkDataEntity, error) {
	in := func(r int64) bool {
		for _, x := range txnRefNumbers {
			if x == r {
				return true
			}
		}
		return false
	}
	var out []*JaywanNetworkDataEntity
	for _, nd := range f.netData {
		if nd.PrjSerNumber == prjSerNumber && in(nd.TxnRefNumber) {
			out = append(out, nd)
		}
	}
	return out, nil
}

func (f *jaywanFakeStore) UpdateJaywanWorkStatuses(ctx context.Context, ents []*JaywanAcqTxnWorkEntity) error {
	return nil
}

func (f *jaywanFakeStore) InsertJaywanData(ctx context.Context, ents []*JaywanAcqTxnDataEntity) error {
	f.data = ents
	return nil
}

func (f *jaywanFakeStore) DeleteJaywanWork(ctx context.Context, ents []*JaywanAcqTxnWorkEntity) error {
	f.work = nil
	return nil
}

func (f *jaywanFakeStore) CompleteJaywanPosStatus(ctx context.Context, ins int) error {
	f.posDone = true
	return nil
}

func (f *jaywanFakeStore) InsertSummaries(ctx context.Context, ents []*OutgoingSummaryEntity) error {
	f.summaries = append(f.summaries, ents...)
	return nil
}

func newJaywanFakeStore() *jaywanFakeStore {
	seqDate := time.Date(2026, 8, 3, 0, 0, 0, 0, time.UTC)
	pid := "784666661"
	return &jaywanFakeStore{
		nextSerial: 0,
		acqBin: &AcquirerBinsEntity{
			InstitutionCode: 1,
			BinType:         "J",
			OutFileSeq:      10,
			OutfileDate:     &seqDate,
			ParticipantId:   &pid,
		},
		interfaces: &InterfacesEntity{InterfaceCode: 11},
		format:     &FileFormatsEntity{Code: 12},
	}
}

func jaywanWorkEntity() *JaywanAcqTxnWorkEntity {
	pid := "784666661"
	return &JaywanAcqTxnWorkEntity{
		InstitutionCode: 1,
		IntCode:         11,
		PrjSerNumber:    1,
		GenStatus:       3,
		MessageTypeId:   "1240",
		FunctionCode:    "200",
		AcqinstIdCode:   pid,
		TerminalId:      "T0000005",
		TxnCurCode:      "784",
		MerchantId:      "M00000000000009",
		MeName:          "ALI MANZ STORE",
		MeCity:          "dubai",
		MeStateCode:     "DU",
		MeCountry:       "AE",
		Mcc:             "9211",
		PosEntryMode:    "071",
		PosConditionCode: "00",
		ResponseCode:    "00",
		EncCardNumber:   "tok1",
	}
}

// TestProcessJaywanOutgoingMatchesSample drives ProcessJaywanOutgoing with the
// payload-derived fixture values and asserts the written file is byte-for-byte
// identical to the reference jaywan.xml sample.
func TestProcessJaywanOutgoingMatchesSample(t *testing.T) {
	dir := t.TempDir()
	st := newJaywanFakeStore()

	t1 := jaywanWorkEntity()
	t1.TxnRefNumber = 1
	ldt1 := time.Date(2026, 8, 3, 16, 4, 59, 0, time.UTC)
	t1.LocalDateTime = &ldt1
	t1.Rrn = "621516000001"
	t1.ApprovalCode = "232068"
	t1.TxnAmount = 10.00

	t2 := jaywanWorkEntity()
	t2.TxnRefNumber = 2
	ldt2 := time.Date(2026, 7, 29, 11, 9, 27, 0, time.UTC)
	t2.LocalDateTime = &ldt2
	t2.Rrn = "621007888375"
	t2.ApprovalCode = "962981"
	t2.TxnAmount = 951.00

	t3 := jaywanWorkEntity()
	t3.TxnRefNumber = 3
	ldt3 := time.Date(2026, 7, 29, 11, 10, 29, 0, time.UTC)
	t3.LocalDateTime = &ldt3
	t3.Rrn = "621007888375"
	t3.ApprovalCode = "962981"
	t3.TxnAmount = 951.00

	st.work = []*JaywanAcqTxnWorkEntity{t1, t2, t3}
	st.netData = []*JaywanNetworkDataEntity{
		{PrjSerNumber: 1, TxnRefNumber: 1, ProcCode: "200000", TransIdentifier: "080312045900001", PosTxnStatus: "0", PosCPInd: "0"},
		{PrjSerNumber: 1, TxnRefNumber: 2, ProcCode: "000000", TransIdentifier: "072907092788837", PosTxnStatus: "4", PosCPInd: "0"},
		{PrjSerNumber: 1, TxnRefNumber: 3, ProcCode: "000000", TransIdentifier: "072907092788837", PosTxnStatus: "0", PosCPInd: "0"},
	}

	s := NewOutgoingService(OutgoingConfig{
		InsCode:       1,
		InsShortName:  "IRF",
		UpdatedUser:   4,
		ReconOutDir:   dir,
		FileCategory:  "T",
		VersionNumber: "01.01",
	}, st, &fakeCrypto{dec: map[string]string{"tok1": "6690109700100010"}})
	frozen := time.Date(2026, 8, 3, 13, 37, 57, 0, time.UTC)
	s.now = func() time.Time { return frozen }

	from := time.Date(2026, 8, 3, 0, 0, 0, 0, time.UTC)
	to := time.Date(2026, 8, 3, 23, 59, 59, 0, time.UTC)
	got := s.ProcessJaywanOutgoing(context.Background(), 1, 4, 5, "IRF", &from, &to)
	if got != "Success" {
		t.Fatalf("ProcessJaywanOutgoing = %q, want Success", got)
	}

	outPath := filepath.Join(dir, "0007846666612621510.xml")
	gotBytes, err := os.ReadFile(outPath)
	if err != nil {
		t.Fatalf("output file missing: %v", err)
	}

	sample, err := os.ReadFile(filepath.Join("..", "..", "jaywan.xml"))
	if err != nil {
		t.Fatalf("cannot read reference sample: %v", err)
	}
	if !bytes.Equal(gotBytes, sample) {
		t.Fatalf("generated file (%d bytes) does not match jaywan.xml (%d bytes)\n"+
			"--- got ---\n%s\n--- want ---\n%s",
			len(gotBytes), len(sample), gotBytes, sample)
	}

	if len(st.data) != 3 {
		t.Fatalf("moved data rows = %d, want 3", len(st.data))
	}
	if !st.posDone {
		t.Error("CompleteJaywanPosStatus not called")
	}
	if len(st.summaries) != 1 {
		t.Fatalf("summaries = %d, want 1 (single function code 200)", len(st.summaries))
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
}