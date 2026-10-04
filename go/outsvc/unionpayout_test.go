package outsvc

import (
	"context"
	"os"
	"path/filepath"
	"strings"
	"testing"
	"time"
)

// unionpayFakeStore implements just the Store methods exercised by the
// ProcessUnionPayOutgoing success/failure paths.
type unionpayFakeStore struct {
	Store
	work       []*UnionPayAcqTxnWorkEntity
	data       []*UnionPayAcqTxnDataEntity
	fileLogs   []*OutGoingFileProcessingEntity
	nextSerial int64
	acqBin     *AcquirerBinsEntity
	interfaces *InterfacesEntity
	format     *FileFormatsEntity
	businessDate *BusinessDateEntity
	summaries  []*OutgoingSummaryEntity
	posDone    bool
}

func (f *unionpayFakeStore) FindFileFormatBySystemCodeAndType(ctx context.Context, sysCode int, typ string) (*FileFormatsEntity, error) {
	return f.format, nil
}

func (f *unionpayFakeStore) FindInterfaceByCategory(ctx context.Context, category string) (*InterfacesEntity, error) {
	return f.interfaces, nil
}

func (f *unionpayFakeStore) FindFileLogByFormatCodeAndStatuses(ctx context.Context, formatCode int) ([]*OutGoingFileProcessingEntity, error) {
	var out []*OutGoingFileProcessingEntity
	for _, l := range f.fileLogs {
		if l.FormatCode == formatCode && (l.GeneratedStatus == 1 || l.GeneratedStatus == 9) {
			out = append(out, l)
		}
	}
	return out, nil
}

func (f *unionpayFakeStore) InsertFileLog(ctx context.Context, e *OutGoingFileProcessingEntity) (int64, error) {
	f.nextSerial++
	e.SerialNumber = f.nextSerial
	f.fileLogs = append(f.fileLogs, e)
	return e.SerialNumber, nil
}

func (f *unionpayFakeStore) FindFileLogByInstitutionAndSerial(ctx context.Context, ins int, ser int64) (*OutGoingFileProcessingEntity, error) {
	for _, l := range f.fileLogs {
		if l.SerialNumber == ser {
			return l, nil
		}
	}
	return nil, nil
}

func (f *unionpayFakeStore) UpdateFileLog(ctx context.Context, e *OutGoingFileProcessingEntity) error {
	for i, l := range f.fileLogs {
		if l.SerialNumber == e.SerialNumber {
			f.fileLogs[i] = e
		}
	}
	return nil
}

func (f *unionpayFakeStore) FindFileLogTopByStatusAndInterface(ctx context.Context, status int, intCode int) (*OutGoingFileProcessingEntity, error) {
	var top *OutGoingFileProcessingEntity
	for _, l := range f.fileLogs {
		if l.GeneratedStatus == status && l.InterfaceCode == intCode && (top == nil || l.LastUpdated.After(top.LastUpdated)) {
			top = l
		}
	}
	return top, nil
}

func (f *unionpayFakeStore) FindBusinessDateByInstitution(ctx context.Context, ins int) (*BusinessDateEntity, error) {
	return f.businessDate, nil
}

func (f *unionpayFakeStore) FindAcquirerBins(ctx context.Context, ins int, binType string) ([]*AcquirerBinsEntity, error) {
	return []*AcquirerBinsEntity{f.acqBin}, nil
}

func (f *unionpayFakeStore) UpdateAcquirerBin(ctx context.Context, e *AcquirerBinsEntity) error {
	f.acqBin = e
	return nil
}

func (f *unionpayFakeStore) findUnionPayByStatus(status int) []*UnionPayAcqTxnWorkEntity {
	var out []*UnionPayAcqTxnWorkEntity
	for _, w := range f.work {
		if w.GenStatus == status {
			out = append(out, w)
		}
	}
	return out
}

func (f *unionpayFakeStore) FindUnionPayWorkBetween(ctx context.Context, ins, intCode, status int, from, to time.Time) ([]*UnionPayAcqTxnWorkEntity, error) {
	return f.findUnionPayByStatus(status), nil
}

func (f *unionpayFakeStore) FindUnionPayWorkLessThanEqual(ctx context.Context, ins, intCode, status int, to time.Time) ([]*UnionPayAcqTxnWorkEntity, error) {
	return f.findUnionPayByStatus(status), nil
}

func (f *unionpayFakeStore) FindUnionPayWorkByStatus(ctx context.Context, ins, status int) ([]*UnionPayAcqTxnWorkEntity, error) {
	return f.findUnionPayByStatus(status), nil
}

func (f *unionpayFakeStore) FindUnionPayDataByFileId(ctx context.Context, ins int, fileId string) ([]*UnionPayAcqTxnDataEntity, error) {
	var out []*UnionPayAcqTxnDataEntity
	for _, d := range f.data {
		if d.FileID == fileId {
			out = append(out, d)
		}
	}
	return out, nil
}

func (f *unionpayFakeStore) UpdateUnionPayWorkStatuses(ctx context.Context, ents []*UnionPayAcqTxnWorkEntity) error {
	return nil
}

func (f *unionpayFakeStore) InsertUnionPayData(ctx context.Context, ents []*UnionPayAcqTxnDataEntity) error {
	f.data = ents
	return nil
}

func (f *unionpayFakeStore) DeleteUnionPayWork(ctx context.Context, ents []*UnionPayAcqTxnWorkEntity) error {
	f.work = nil
	return nil
}

func (f *unionpayFakeStore) CompleteUnionPayPosStatus(ctx context.Context, ins int) error {
	f.posDone = true
	return nil
}

func (f *unionpayFakeStore) InsertSummaries(ctx context.Context, ents []*OutgoingSummaryEntity) error {
	f.summaries = append(f.summaries, ents...)
	return nil
}

func (f *unionpayFakeStore) DeleteUnionPayData(ctx context.Context, ents []*UnionPayAcqTxnDataEntity) error {
	f.data = nil
	return nil
}

func (f *unionpayFakeStore) InsertUnionPayWork(ctx context.Context, ents []*UnionPayAcqTxnWorkEntity) error {
	f.work = ents
	return nil
}

func newUnionPayFakeStore() *unionpayFakeStore {
	d := time.Date(2026, 8, 15, 0, 0, 0, 0, time.UTC)
	ica := "034540"
	return &unionpayFakeStore{
		nextSerial: 0,
		acqBin: &AcquirerBinsEntity{
			Bin:               "970962",
			InstitutionCode:   1,
			BinType:           "U",
			OutFileSeq:        0,
			McIcaNo:           &ica,
		},
		interfaces: &InterfacesEntity{InterfaceCode: 15},
		format:     &FileFormatsEntity{Code: 12},
		businessDate: &BusinessDateEntity{
			InstitutionCode:  1,
			BusinessDate:     d,
			LastBusinessDate: d.Add(-24 * time.Hour),
		},
	}
}

func unionpayEntity() *UnionPayAcqTxnWorkEntity {
	pt := func(t time.Time) *time.Time { return &t }
	return &UnionPayAcqTxnWorkEntity{
		TxnRefNumber:         1,
		Rrn:                  "123456789012",
		MerchantId:           "MID1234567",
		TerminalId:           "TERM123",
		TxnType:              "00",
		CardNumber:           "6212345678901234",
		TxnAmount:            100.5,
		SurchargeAmount:      1.25,
		LocalDateTime:        pt(time.Date(2026, 8, 15, 10, 30, 0, 0, time.UTC)),
		TxnDate:              pt(time.Date(2026, 8, 15, 0, 0, 0, 0, time.UTC)),
		MeName:               "MERCHANT",
		MeCity:               "DUBAI",
		MeCountry:            "AE",
		Mcc:                  "5812",
		ApprovalCode:         "123456",
		TxnCurCode:           "784",
		StanNumber:           "123456",
		OrgInstIdCode:        "100000",
		AcqinstIdCode:        "200000",
		FwdInstIdCode:        "300000",
		AcqRefData:           "REF123",
		ResponseCode:         "00",
		ReceivingInstIdCode:  "400000",
		PosConditionCode:     "00",
		TxnInitiatingChannel: "01",
		PricingSchemeCode:    "00",
		EncryptedCardNumber:  "tok1",
		CardInputMode:        "05",
		CardInputCapability:  "1",
		CardSeqNumber:        "001",
		AppICProfile:         "000000",
		AppTxnCounter:        "0000",
		AppCryptogram:        strings.Repeat(" ", 16),
		CryptAmount:          100.5,
		CashBackAmount:       0,
		CryptInfoData:        "00",
		CvmResult:            "000000",
		DedicatedFileName:    strings.Repeat(" ", 32),
		IfdSerNumber:         "12345678",
		IssAppData:           strings.Repeat(" ", 64),
		IssAuthData:          strings.Repeat(" ", 42),
		TrlConCode:           "000",
		TrlAppVerNumber:      "0000",
		ChipTrlCapabilities:  "000000",
		ChipTrlType:          "00",
		TrlVerResult:         "0000000000",
		ChipTxnDate:          "150826",
		ChipTxnType:          "00",
		ChipCurCode:          "784",
		UpblNumber:           "00000000",
		CentreProcDate:       pt(time.Date(2026, 8, 15, 0, 0, 0, 0, time.UTC)),
		FileProcDate:         pt(time.Date(2026, 8, 15, 0, 0, 0, 0, time.UTC)),
		FileID:               "",
		CardPresent:          "1",
		ChPresent:            "1",
		PanSequenceNumber:    "001",
		PosEntryMode:         "05",
		SettlementIndicator:  "1",
		TxnFeeAmount:         0,
	}
}

func TestProcessUnionPayOutgoingHappyPath(t *testing.T) {
	dir := t.TempDir()
	st := newUnionPayFakeStore()
	txn1 := unionpayEntity()
	txn1.GenStatus = 3
	txn1.EncryptedCardNumber = "tok1"
	txn2 := unionpayEntity()
	txn2.GenStatus = 3
	txn2.EncryptedCardNumber = "tok2"
	txn2.TxnRefNumber = 2
	st.work = []*UnionPayAcqTxnWorkEntity{txn1, txn2}

	s := NewOutgoingService(OutgoingConfig{
		InsCode:            1,
		InsShortName:       "IRF",
		UpdatedUser:        4,
		ReconOutDir:        dir,
		CurrencyCodeKafka:  "AED000",
		UnionPayVersionTag: "TEST",
	}, st, &fakeCrypto{dec: map[string]string{
		"tok1": "6212345678901234",
		"tok2": "6212345678901235",
	}})
	s.now = func() time.Time { return time.Date(2026, 8, 15, 0, 0, 0, 0, time.UTC) }

	from := time.Date(2026, 8, 15, 0, 0, 0, 0, time.UTC)
	to := time.Date(2026, 8, 15, 23, 59, 59, 0, time.UTC)
	got := s.ProcessUnionPayOutgoing(context.Background(), 1, 4, 12, "IRF", &from, &to)
	if got != "Success" {
		t.Fatalf("ProcessUnionPayOutgoing = %q, want Success", got)
	}

	// Output file should be OFCYYMMDD5?C
	fileName := "OFC" + time.Date(2026, 8, 15, 0, 0, 0, 0, time.UTC).Format("060102") + "51C"
	outPath := filepath.Join(dir, fileName)
	info, err := os.Stat(outPath)
	if err != nil {
		t.Fatalf("output file missing: %v", err)
	}
	if info.Size() <= 0 {
		t.Fatal("output file empty")
	}
	b, _ := os.ReadFile(outPath)
	content := string(b)
	if !strings.HasPrefix(content, "000") {
		t.Errorf("file starts %q, want header 000", content[:min(3, len(content))])
	}
	if !strings.Contains(content, "6212345678901234") {
		t.Errorf("decrypted PAN not found in output")
	}

	// Should have 2 transaction records + header + trailer = 4 lines
	lines := strings.Split(strings.TrimSpace(content), "\r\n")
	if len(lines) != 4 {
		t.Errorf("lines = %d, want 4 (header + 2 txns + trailer)", len(lines))
	}

	if len(st.summaries) != 1 {
		t.Fatalf("summaries = %d, want 1", len(st.summaries))
	}
	if !st.posDone {
		t.Error("CompleteUnionPayPosStatus not called")
	}
	if len(st.data) != 2 {
		t.Fatalf("moved data rows = %d, want 2", len(st.data))
	}
	for _, d := range st.data {
		if d.GenStatus != 4 {
			t.Errorf("data row %q status = %d, want 4", d.Rrn, d.GenStatus)
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
		t.Errorf("file log file_id = %v, want %s", fileLog.FileId, fileName)
	}
}

func TestProcessUnionPayOutgoingNoData(t *testing.T) {
	dir := t.TempDir()
	st := newUnionPayFakeStore()

	s := NewOutgoingService(OutgoingConfig{
		InsCode:            1,
		InsShortName:       "IRF",
		UpdatedUser:        4,
		ReconOutDir:        dir,
		CurrencyCodeKafka:  "AED000",
		UnionPayVersionTag: "TEST",
	}, st, &fakeCrypto{dec: map[string]string{"tok1": "6212345678901234"}})

	from := time.Date(2026, 8, 15, 0, 0, 0, 0, time.UTC)
	to := time.Date(2026, 8, 15, 23, 59, 59, 0, time.UTC)
	got := s.ProcessUnionPayOutgoing(context.Background(), 1, 4, 12, "IRF", &from, &to)
	if got != "No data found" {
		t.Fatalf("ProcessUnionPayOutgoing = %q, want No data found", got)
	}
}

func TestProcessUnionPayOutgoingDecryptFailed(t *testing.T) {
	dir := t.TempDir()
	st := newUnionPayFakeStore()
	txn := unionpayEntity()
	txn.GenStatus = 3
	txn.EncryptedCardNumber = "tok1"
	st.work = []*UnionPayAcqTxnWorkEntity{txn}

	s := NewOutgoingService(OutgoingConfig{
		InsCode:            1,
		InsShortName:       "IRF",
		UpdatedUser:        4,
		ReconOutDir:        dir,
		CurrencyCodeKafka:  "AED000",
		UnionPayVersionTag: "TEST",
	}, st, &fakeCrypto{dec: nil}) // decrypt fails

	from := time.Date(2026, 8, 15, 0, 0, 0, 0, time.UTC)
	to := time.Date(2026, 8, 15, 23, 59, 59, 0, time.UTC)
	got := s.ProcessUnionPayOutgoing(context.Background(), 1, 4, 12, "IRF", &from, &to)
	if got != "Outgoing Failed" {
		t.Fatalf("ProcessUnionPayOutgoing = %q, want Outgoing Failed", got)
	}
	if txn.GenStatus != 7 {
		t.Errorf("work status = %d, want 7 (failed)", txn.GenStatus)
	}
}