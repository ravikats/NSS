package outsvc

import (
	"context"
	"strings"
	"testing"
	"time"
)

func (f *mercuryFakeStore) FindFileLogTopByStatusAndInterface(ctx context.Context, status, intCode int) (*OutGoingFileProcessingEntity, error) {
	for _, l := range f.fileLogs {
		if l.GeneratedStatus == status && l.InterfaceCode == intCode {
			return l, nil
		}
	}
	return nil, nil
}

func (f *mercuryFakeStore) FindMercuryDataByFileId(ctx context.Context, ins int, fileId string) ([]*MercuryAcqTxnDataEntity, error) {
	var out []*MercuryAcqTxnDataEntity
	for _, d := range f.data {
		if d.InstitutionCode == ins && d.FileID == fileId {
			out = append(out, d)
		}
	}
	return out, nil
}

func (f *mercuryFakeStore) InsertMercuryWork(ctx context.Context, ents []*MercuryAcqTxnWorkEntity) error {
	for _, e := range ents {
		cp := *e
		f.nextSerial++
		cp.SerialNumber = f.nextSerial
		f.work = append(f.work, &cp)
	}
	return nil
}

func (f *mercuryFakeStore) DeleteMercuryData(ctx context.Context, ents []*MercuryAcqTxnDataEntity) error {
	del := make(map[int64]bool, len(ents))
	for _, e := range ents {
		del[e.SerialNumber] = true
	}
	kept := f.data[:0]
	for _, d := range f.data {
		if !del[d.SerialNumber] {
			kept = append(kept, d)
		}
	}
	f.data = kept
	return nil
}

func (f *mercuryFakeStore) DeleteFileLogByInstitutionAndFileIdAndInterface(ctx context.Context, ins int, fileId string, intCode int) error {
	kept := f.fileLogs[:0]
	for _, l := range f.fileLogs {
		if l.InstitutionCode == ins && l.FileId != nil && *l.FileId == fileId && l.InterfaceCode == intCode {
			continue
		}
		kept = append(kept, l)
	}
	f.fileLogs = kept
	return nil
}

func (f *mercuryFakeStore) FindPosBySerNumbers(ctx context.Context, ser []int64) ([]*PosTransactionEntity, error) {
	out := make([]*PosTransactionEntity, 0, len(ser))
	for _, n := range ser {
		out = append(out, &PosTransactionEntity{SerialNumber: n})
	}
	return out, nil
}

func (f *mercuryFakeStore) UpdatePosStatuses(ctx context.Context, ents []*PosTransactionEntity) error {
	f.posUpdated = append(f.posUpdated, ents...)
	return nil
}

func mercuryDataRow(ser int64, fileID string, txnRef int64) *MercuryAcqTxnDataEntity {
	row := mercuryWorkEntity()
	row.SerialNumber = ser
	row.FileID = fileID
	row.GenStatus = 4
	row.TxnRefNumber = txnRef
	row.LastUpdated = time.Date(2026, 8, 3, 14, 0, 0, 0, time.UTC)
	return row
}

func TestRevertLastOutgoingMercuryRequeuesRows(t *testing.T) {
	fileID := "Documents.2401392320260803"
	st := newMercuryFakeStore()
	st.fileLogs = []*OutGoingFileProcessingEntity{{
		InstitutionCode: 1,
		InterfaceCode:   21,
		GeneratedStatus: 4,
		FileName:        fileID,
		FileId:          &fileID,
	}}
	st.data = []*MercuryAcqTxnDataEntity{
		mercuryDataRow(4, fileID, 499),
		mercuryDataRow(5, fileID, 500),
		mercuryDataRow(6, fileID, 501),
	}

	s := NewOutgoingService(OutgoingConfig{InsCode: 1, UpdatedUser: 4, ReconOutDir: t.TempDir()}, st, nil)

	if got := s.RevertLastOutgoingData(context.Background(), "MERCURY", 1); got != "Revert Successfully Completed" {
		t.Fatalf("RevertLastOutgoingData = %q, want success", got)
	}
	if len(st.data) != 0 {
		t.Errorf("data rows left = %d, want 0", len(st.data))
	}
	if len(st.fileLogs) != 0 {
		t.Errorf("file log rows left = %d, want 0", len(st.fileLogs))
	}
	if len(st.work) != 3 {
		t.Fatalf("work rows = %d, want 3", len(st.work))
	}
	for _, w := range st.work {
		if w.GenStatus != 3 {
			t.Errorf("work serial %d gen status = %d, want 3", w.SerialNumber, w.GenStatus)
		}
		if w.FileID != "" {
			t.Errorf("work serial %d file id = %q, want empty", w.SerialNumber, w.FileID)
		}
		if w.SerialNumber == 0 {
			t.Error("work row kept archived serial; identity should reassign")
		}
	}
	if len(st.posUpdated) != 3 {
		t.Fatalf("POS rows updated = %d, want 3", len(st.posUpdated))
	}
	for _, p := range st.posUpdated {
		if p.GenStatus != 4 || p.OutStatus != "Marked for Outgoing" {
			t.Errorf("POS %d = status %d/%q, want 4/Marked for Outgoing", p.SerialNumber, p.GenStatus, p.OutStatus)
		}
	}
}

func TestRevertLastOutgoingMercuryNoFile(t *testing.T) {
	st := newMercuryFakeStore()
	st.fileLogs = nil
	s := NewOutgoingService(OutgoingConfig{InsCode: 1}, st, nil)
	if got := s.RevertLastOutgoingData(context.Background(), "MERCURY", 1); got != "No Outgoing Data for the file ID" {
		t.Errorf("got %q, want No Outgoing Data for the file ID", got)
	}
}

func TestMercuryWorkColumnsMatchArgs(t *testing.T) {
	if got := strings.Count(mercuryColumns, ",") + 1; got != 74 {
		t.Errorf("mercuryColumns = %d columns, want 74", got)
	}
	if got := strings.Count(mercuryWorkColumns, ",") + 1; got != 73 {
		t.Errorf("mercuryWorkColumns = %d columns, want 73", got)
	}
	if got := len(mercuryDataArgs(mercuryWorkEntity())[1:]); got != 73 {
		t.Errorf("work insert binds = %d, want 73", got)
	}
}
