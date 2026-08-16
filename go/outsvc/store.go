package outsvc

import (
	"context"
	"database/sql"
	"fmt"
	"reflect"
	"strconv"
	"time"

	go_ora "github.com/sijms/go-ora/v2"
)

// Store is the persistence contract for the outgoing file generation flow.
// The production implementation talks to Oracle; tests supply a fake.
type Store interface {
	InsertFileLog(ctx context.Context, e *OutGoingFileProcessingEntity) (int64, error)
	UpdateFileLog(ctx context.Context, e *OutGoingFileProcessingEntity) error
	FindFileLogByFormatCodeAndStatuses(ctx context.Context, formatCode int) ([]*OutGoingFileProcessingEntity, error)
	FindFileLogByInstitutionAndSerial(ctx context.Context, ins int, ser int64) (*OutGoingFileProcessingEntity, error)
	FindFileLogTopByStatusAndInterface(ctx context.Context, status, intCode int) (*OutGoingFileProcessingEntity, error)
	FindFileLogTopByGeneratedStatus(ctx context.Context, status int) (*OutGoingFileProcessingEntity, error)
	DeleteFileLogByInstitutionAndFileIdAndInterface(ctx context.Context, ins int, fileId string, intCode int) error

	FindInterfaceByCategory(ctx context.Context, category string) (*InterfacesEntity, error)
	FindFileFormatBySystemCodeAndType(ctx context.Context, sysCode int, typ string) (*FileFormatsEntity, error)
	FindBusinessDateByInstitution(ctx context.Context, ins int) (*BusinessDateEntity, error)
	FindAcquirerBins(ctx context.Context, ins int, binType string) ([]*AcquirerBinsEntity, error)
	UpdateAcquirerBin(ctx context.Context, e *AcquirerBinsEntity) error

	CountMcWorkBetween(ctx context.Context, ins, status int, from, to time.Time) (int, error)
	CountMcWorkLessThanEqual(ctx context.Context, ins, status int, to time.Time) (int, error)
	FindMcWorkBetween(ctx context.Context, ins, status int, from, to time.Time) ([]*McAcqTxnWorkEntity, error)
	FindMcWorkLessThanEqual(ctx context.Context, ins, status int, to time.Time) ([]*McAcqTxnWorkEntity, error)
	FindMcWorkByStatus(ctx context.Context, ins, status int) ([]*McAcqTxnWorkEntity, error)
	UpdateMcWorkStatuses(ctx context.Context, ents []*McAcqTxnWorkEntity) error
	DeleteMcWork(ctx context.Context, ents []*McAcqTxnWorkEntity) error

	FindViewIpmOutWorkAll(ctx context.Context) ([]*ViewIpmOutWorkEntity, error)
	InsertIpmOutWork(ctx context.Context, ents []*IpmOutWorkEntity) error
	FindIpmOutWorkByInsAndFile(ctx context.Context, ins int, fileId string) ([]*IpmOutWorkEntity, error)

	InsertOutgoingReport(ctx context.Context, ents []*OutgoingReportDataWorkEntity) error

	InsertSummaries(ctx context.Context, ents []*OutgoingSummaryEntity) error
	FindSummaries(ctx context.Context, ins, intCode int, refSerial int64) ([]*OutgoingSummaryEntity, error)
	FindMcSummaryGroups(ctx context.Context, ins int) ([]mcSummaryGroup, error)

	InsertMcData(ctx context.Context, ents []*McAcqTxnDataEntity) error
	FindMcDataByFileId(ctx context.Context, ins int, fileId string) ([]*McAcqTxnDataEntity, error)
	DeleteMcData(ctx context.Context, ents []*McAcqTxnDataEntity) error
	InsertMcWork(ctx context.Context, ents []*McAcqTxnWorkEntity) error

	CountVisaWorkBetween(ctx context.Context, ins, status int, from, to time.Time) (int, error)
	CountVisaWorkLessThanEqual(ctx context.Context, ins, status int, to time.Time) (int, error)
	FindVisaWorkBetween(ctx context.Context, ins, intCode, status int, from, to time.Time) ([]*VisaAcqTxnWorkEntity, error)
	FindVisaWorkLessThanEqual(ctx context.Context, ins, intCode, status int, to time.Time) ([]*VisaAcqTxnWorkEntity, error)
	FindVisaWorkFeeBetween(ctx context.Context, ins, intCode, status int, txnCode []string, from, to time.Time) ([]*VisaAcqTxnWorkEntity, error)
	FindVisaWorkFeeLessThanEqual(ctx context.Context, ins, intCode, status int, txnCode []string, to time.Time) ([]*VisaAcqTxnWorkEntity, error)
	FindVisaWorkTxnBetween(ctx context.Context, ins, intCode, status int, txnCode []string, from, to time.Time) ([]*VisaAcqTxnWorkEntity, error)
	FindVisaWorkTxnLessThanEqual(ctx context.Context, ins, intCode, status int, txnCode []string, to time.Time) ([]*VisaAcqTxnWorkEntity, error)
	FindVisaWorkByStatus(ctx context.Context, ins, status int) ([]*VisaAcqTxnWorkEntity, error)
	FindVisaWorkByFileId(ctx context.Context, ins int, fileId string) ([]*VisaAcqTxnWorkEntity, error)
	FindVisaWorkByArn(ctx context.Context, arn string) ([]*VisaAcqTxnWorkEntity, error)
	UpdateVisaWorkStatuses(ctx context.Context, ents []*VisaAcqTxnWorkEntity) error
	DeleteVisaWork(ctx context.Context, ents []*VisaAcqTxnWorkEntity) error
	InsertVisaData(ctx context.Context, ents []*VisaAcqTxnDataEntity) error
	FindVisaDataByFileId(ctx context.Context, ins int, fileId string) ([]*VisaAcqTxnDataEntity, error)
	DeleteVisaData(ctx context.Context, ents []*VisaAcqTxnDataEntity) error
	InsertVisaWork(ctx context.Context, ents []*VisaAcqTxnWorkEntity) error
	CompleteVisaPosStatus(ctx context.Context, ins int) error

	CountJaywanWorkBetween(ctx context.Context, ins, status int, from, to time.Time) (int, error)
	CountJaywanWorkLessThanEqual(ctx context.Context, ins, status int, to time.Time) (int, error)
	FindJaywanWorkBetween(ctx context.Context, ins, intCode, status int, from, to time.Time) ([]*JaywanAcqTxnWorkEntity, error)
	FindJaywanWorkLessThanEqual(ctx context.Context, ins, intCode, status int, to time.Time) ([]*JaywanAcqTxnWorkEntity, error)
	FindJaywanWorkByIntAndStatus(ctx context.Context, ins, intCode, status int) ([]*JaywanAcqTxnWorkEntity, error)
	FindJaywanWorkByRrn(ctx context.Context, rrn string) ([]*JaywanAcqTxnWorkEntity, error)
	FindJaywanNetworkDataByRef(ctx context.Context, prjSerNumber int64, txnRefNumbers []int64) ([]*JaywanNetworkDataEntity, error)
	UpdateJaywanWorkStatuses(ctx context.Context, ents []*JaywanAcqTxnWorkEntity) error
	DeleteJaywanWork(ctx context.Context, ents []*JaywanAcqTxnWorkEntity) error
	InsertJaywanData(ctx context.Context, ents []*JaywanAcqTxnDataEntity) error
	FindJaywanDataByFileId(ctx context.Context, ins int, fileId string) ([]*JaywanAcqTxnDataEntity, error)
	DeleteJaywanData(ctx context.Context, ents []*JaywanAcqTxnDataEntity) error
	InsertJaywanWork(ctx context.Context, ents []*JaywanAcqTxnWorkEntity) error
	CompleteJaywanPosStatus(ctx context.Context, ins int) error

	CountMercuryWorkBetween(ctx context.Context, ins, status int, from, to time.Time) (int, error)
	CountMercuryWorkLessThanEqual(ctx context.Context, ins, status int, to time.Time) (int, error)
	FindMercuryWorkBetween(ctx context.Context, ins, intCode, status int, from, to time.Time) ([]*MercuryAcqTxnWorkEntity, error)
	FindMercuryWorkLessThanEqual(ctx context.Context, ins, intCode, status int, to time.Time) ([]*MercuryAcqTxnWorkEntity, error)
	FindMercuryWorkByStatus(ctx context.Context, ins, status int) ([]*MercuryAcqTxnWorkEntity, error)
	UpdateMercuryWorkStatuses(ctx context.Context, ents []*MercuryAcqTxnWorkEntity) error
	DeleteMercuryWork(ctx context.Context, ents []*MercuryAcqTxnWorkEntity) error
	InsertMercuryData(ctx context.Context, ents []*MercuryAcqTxnDataEntity) error
	CompleteMercuryPosStatus(ctx context.Context, ins int) error

	CountUnionPayWorkBetween(ctx context.Context, ins, status int, from, to time.Time) (int, error)
	CountUnionPayWorkLessThanEqual(ctx context.Context, ins, status int, to time.Time) (int, error)
	FindUnionPayWorkBetween(ctx context.Context, ins, intCode, status int, from, to time.Time) ([]*UnionPayAcqTxnWorkEntity, error)
	FindUnionPayWorkLessThanEqual(ctx context.Context, ins, intCode, status int, to time.Time) ([]*UnionPayAcqTxnWorkEntity, error)
	FindUnionPayWorkByStatus(ctx context.Context, ins, status int) ([]*UnionPayAcqTxnWorkEntity, error)
	UpdateUnionPayWorkStatuses(ctx context.Context, ents []*UnionPayAcqTxnWorkEntity) error
	DeleteUnionPayWork(ctx context.Context, ents []*UnionPayAcqTxnWorkEntity) error
	InsertUnionPayData(ctx context.Context, ents []*UnionPayAcqTxnDataEntity) error
	CompleteUnionPayPosStatus(ctx context.Context, ins int) error

	FindPosBySerNumbers(ctx context.Context, ser []int64) ([]*PosTransactionEntity, error)
	UpdatePosStatuses(ctx context.Context, ents []*PosTransactionEntity) error

	CompletePosStatus(ctx context.Context, ins int) error
}

// oracleStore is the production Store backed by Oracle (go-ora v2 driver).
type oracleStore struct {
	db *sql.DB
}

// NewOracleStore returns a Store backed by an existing database/sql handle.
func NewOracleStore(db *sql.DB) Store {
	return &oracleStore{db: db}
}

// ---- row binding helpers (db tag -> column) ----

func rowsToMaps(rows *sql.Rows) ([]map[string]any, error) {
	cols, err := rows.Columns()
	if err != nil {
		return nil, err
	}
	var out []map[string]any
	for rows.Next() {
		vals := make([]any, len(cols))
		ptrs := make([]any, len(cols))
		for i := range vals {
			ptrs[i] = &vals[i]
		}
		if err := rows.Scan(ptrs...); err != nil {
			return nil, err
		}
		m := make(map[string]any, len(cols))
		for i, c := range cols {
			m[c] = vals[i]
		}
		out = append(out, m)
	}
	return out, rows.Err()
}

func cellInt(v any) int64 {
	switch t := v.(type) {
	case int64:
		return t
	case int:
		return int64(t)
	case float64:
		return int64(t)
	case string:
		return int64(atoi(t))
	case []byte:
		return int64(atoi(string(t)))
	}
	return 0
}

func cellFloat(v any) float64 {
	switch t := v.(type) {
	case float64:
		return t
	case int64:
		return float64(t)
	case int:
		return float64(t)
	case string:
		return atof(t)
	case []byte:
		return atof(string(t))
	}
	return 0
}

func atof(s string) float64 {
	f, err := strconv.ParseFloat(s, 64)
	if err != nil {
		return 0
	}
	return f
}

func cellTime(v any) (time.Time, bool) {
	switch t := v.(type) {
	case time.Time:
		return t, true
	case string:
		for _, layout := range []string{"2006-01-02 15:04:05", "2006-01-02 15:04:05.0", "2006-01-02"} {
			if tt, err := time.Parse(layout, t); err == nil {
				return tt, true
			}
		}
	case []byte:
		for _, layout := range []string{"2006-01-02 15:04:05", "2006-01-02 15:04:05.0", "2006-01-02"} {
			if tt, err := time.Parse(layout, string(t)); err == nil {
				return tt, true
			}
		}
	}
	return time.Time{}, false
}

// bindRow maps a result row onto a struct using `db` tags.
func bindRow(m map[string]any, dst any) {
	v := reflect.ValueOf(dst).Elem()
	t := v.Type()
	for i := 0; i < t.NumField(); i++ {
		f := t.Field(i)
		tag := f.Tag.Get("db")
		if tag == "" || tag == "-" {
			continue
		}
		raw, ok := m[tag]
		if !ok {
			continue
		}
		fv := v.Field(i)
		if raw == nil {
			continue
		}
		switch fv.Kind() {
		case reflect.String:
			fv.SetString(fmt.Sprint(raw))
		case reflect.Int, reflect.Int64:
			fv.SetInt(cellInt(raw))
		case reflect.Float64:
			fv.SetFloat(cellFloat(raw))
		case reflect.Struct:
			if fv.Type() == reflect.TypeOf(time.Time{}) {
				if tt, ok := cellTime(raw); ok {
					fv.Set(reflect.ValueOf(tt))
				}
			}
		case reflect.Ptr:
			elem := fv.Type().Elem()
			switch elem.Kind() {
			case reflect.String:
				s := fmt.Sprint(raw)
				fv.Set(reflect.ValueOf(&s))
			case reflect.Int64:
				n := cellInt(raw)
				fv.Set(reflect.ValueOf(&n))
			case reflect.Float64:
				fl := cellFloat(raw)
				fv.Set(reflect.ValueOf(&fl))
			case reflect.Struct:
				if elem == reflect.TypeOf(time.Time{}) {
					if tt, ok := cellTime(raw); ok {
						fv.Set(reflect.ValueOf(&tt))
					}
				}
			}
		}
	}
}

// nullStr maps an empty string to NULL (Java null semantics), else the value.
func nullStr(s string) any {
	if s == "" {
		return sql.NullString{}
	}
	return s
}

// nullStrP maps a nil *string to NULL.
func nullStrP(s *string) any {
	if s == nil {
		return sql.NullString{}
	}
	return *s
}

// nullTime maps a nil *time.Time to NULL.
func nullTimeP(t *time.Time) any {
	if t == nil {
		return sql.NullTime{}
	}
	return *t
}

func nullIntP(n *int64) any {
	if n == nil {
		return sql.NullInt64{}
	}
	return *n
}

func nullFloatP(n *float64) any {
	if n == nil {
		return sql.NullFloat64{}
	}
	return *n
}

// ---- OUT_FILE_LOG ----

func (s *oracleStore) InsertFileLog(ctx context.Context, e *OutGoingFileProcessingEntity) (int64, error) {
	const sqlStmt = `
		INSERT INTO OUT_FILE_LOG (
		  OFL_LAST_UPDATED, OFL_UPDATED_USER, OFL_INS_CODE, OFL_INT_CODE, OFL_FOR_CODE,
		  OFL_FILE_NAME, OFL_GNERATE_DATE, OFL_GENERATE_STATUS, OFL_PROC_DATE, OFL_BUSS_DATE,
		  OFL_PRJ_SER_NUMBER, OFL_TOT_TXN_COUNT, OFL_TOT_TXN_AMOUNT,
		  OFL_TOT_ACCP_TXN_COUNT, OFL_TOT_ACCP_TXN_AMOUNT, OFL_FILE_ID)
		VALUES (:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16)
		RETURNING OFL_SER_NUMBER INTO :17`
	var ser int64
	_, err := s.db.ExecContext(ctx, sqlStmt,
		e.LastUpdated, e.UpdatedUser, e.InstitutionCode, e.InterfaceCode, e.FormatCode,
		nullStr(e.FileName), e.GeneratedDate, e.GeneratedStatus, nullTimeP(e.ProcDate), e.BussDate,
		nullIntP(e.PrjSerNumber), nullIntP(e.TotalTxnCount), nullFloatP(e.TotalTxnAmount),
		nullIntP(e.TotalAcceptanceTxnCount), nullFloatP(e.TotalAcceptanceTxnAmount), nullStrP(e.FileId),
		go_ora.Out{Dest: &ser})
	if err != nil {
		return 0, fmt.Errorf("outsvc: insert out_file_log: %w", err)
	}
	e.SerialNumber = ser
	return ser, nil
}

func (s *oracleStore) UpdateFileLog(ctx context.Context, e *OutGoingFileProcessingEntity) error {
	const sqlStmt = `
		UPDATE OUT_FILE_LOG SET
		  OFL_LAST_UPDATED        = :1,
		  OFL_FILE_NAME           = :2,
		  OFL_GENERATE_STATUS     = :3,
		  OFL_PROC_DATE           = :4,
		  OFL_FILE_ID             = :5,
		  OFL_TOT_TXN_COUNT       = :6,
		  OFL_TOT_TXN_AMOUNT      = :7,
		  OFL_TOT_ACCP_TXN_COUNT  = :8,
		  OFL_TOT_ACCP_TXN_AMOUNT = :9
		WHERE OFL_SER_NUMBER = :10`
	_, err := s.db.ExecContext(ctx, sqlStmt,
		e.LastUpdated, nullStr(e.FileName), e.GeneratedStatus, nullTimeP(e.ProcDate),
		nullStrP(e.FileId), nullIntP(e.TotalTxnCount), nullFloatP(e.TotalTxnAmount),
		nullIntP(e.TotalAcceptanceTxnCount), nullFloatP(e.TotalAcceptanceTxnAmount),
		e.SerialNumber)
	return err
}

func (s *oracleStore) FindFileLogByFormatCodeAndStatuses(ctx context.Context, formatCode int) ([]*OutGoingFileProcessingEntity, error) {
	rows, err := s.db.QueryContext(ctx, `
		SELECT * FROM OUT_FILE_LOG
		WHERE OFL_FOR_CODE = :1 AND OFL_GENERATE_STATUS IN (1,9)`, formatCode)
	if err != nil {
		return nil, err
	}
	defer rows.Close()
	maps, err := rowsToMaps(rows)
	if err != nil {
		return nil, err
	}
	out := make([]*OutGoingFileProcessingEntity, 0, len(maps))
	for _, m := range maps {
		e := &OutGoingFileProcessingEntity{}
		bindRow(m, e)
		out = append(out, e)
	}
	return out, nil
}

func (s *oracleStore) FindFileLogByInstitutionAndSerial(ctx context.Context, ins int, ser int64) (*OutGoingFileProcessingEntity, error) {
	rows, err := s.db.QueryContext(ctx, `
		SELECT * FROM OUT_FILE_LOG WHERE OFL_INS_CODE = :1 AND OFL_SER_NUMBER = :2`, ins, ser)
	if err != nil {
		return nil, err
	}
	defer rows.Close()
	maps, err := rowsToMaps(rows)
	if err != nil {
		return nil, err
	}
	if len(maps) == 0 {
		return nil, nil
	}
	e := &OutGoingFileProcessingEntity{}
	bindRow(maps[0], e)
	return e, nil
}

func (s *oracleStore) FindFileLogTopByStatusAndInterface(ctx context.Context, status, intCode int) (*OutGoingFileProcessingEntity, error) {
	rows, err := s.db.QueryContext(ctx, `
		SELECT * FROM OUT_FILE_LOG
		WHERE OFL_GENERATE_STATUS = :1 AND OFL_INT_CODE = :2
		ORDER BY OFL_LAST_UPDATED DESC
		FETCH FIRST 1 ROW ONLY`, status, intCode)
	if err != nil {
		return nil, err
	}
	defer rows.Close()
	maps, err := rowsToMaps(rows)
	if err != nil {
		return nil, err
	}
	if len(maps) == 0 {
		return nil, nil
	}
	e := &OutGoingFileProcessingEntity{}
	bindRow(maps[0], e)
	return e, nil
}

// FindFileLogTopByGeneratedStatus mirrors
// OutFileLogRepo.findTopBygeneratedStatusOrderByLastUpdatedDateDesc: the most
// recently updated file-log row with the given status, any interface.
func (s *oracleStore) FindFileLogTopByGeneratedStatus(ctx context.Context, status int) (*OutGoingFileProcessingEntity, error) {
	rows, err := s.db.QueryContext(ctx, `
		SELECT * FROM OUT_FILE_LOG
		WHERE OFL_GENERATE_STATUS = :1
		ORDER BY OFL_LAST_UPDATED DESC
		FETCH FIRST 1 ROW ONLY`, status)
	if err != nil {
		return nil, err
	}
	defer rows.Close()
	maps, err := rowsToMaps(rows)
	if err != nil {
		return nil, err
	}
	if len(maps) == 0 {
		return nil, nil
	}
	e := &OutGoingFileProcessingEntity{}
	bindRow(maps[0], e)
	return e, nil
}

func (s *oracleStore) DeleteFileLogByInstitutionAndFileIdAndInterface(ctx context.Context, ins int, fileId string, intCode int) error {
	_, err := s.db.ExecContext(ctx, `
		DELETE FROM OUT_FILE_LOG WHERE OFL_INS_CODE = :1 AND OFL_FILE_ID = :2 AND OFL_INT_CODE = :3`,
		ins, nullStr(fileId), intCode)
	return err
}

// ---- INTERFACES / FILE_FORMATS / BUSINESS_DATE / ACQUIRER_BINS ----

func (s *oracleStore) FindInterfaceByCategory(ctx context.Context, category string) (*InterfacesEntity, error) {
	rows, err := s.db.QueryContext(ctx, `SELECT * FROM INTERFACES WHERE INT_CATEGORY = :1`, category)
	if err != nil {
		return nil, err
	}
	defer rows.Close()
	maps, err := rowsToMaps(rows)
	if err != nil {
		return nil, err
	}
	if len(maps) == 0 {
		return nil, nil
	}
	e := &InterfacesEntity{}
	bindRow(maps[0], e)
	return e, nil
}

func (s *oracleStore) FindFileFormatBySystemCodeAndType(ctx context.Context, sysCode int, typ string) (*FileFormatsEntity, error) {
	rows, err := s.db.QueryContext(ctx, `
		SELECT * FROM FILE_FORMATS WHERE FOR_SYSTEM_CODE = :1 AND FOR_TYPE = :2`, sysCode, typ)
	if err != nil {
		return nil, err
	}
	defer rows.Close()
	maps, err := rowsToMaps(rows)
	if err != nil {
		return nil, err
	}
	if len(maps) == 0 {
		return nil, nil
	}
	e := &FileFormatsEntity{}
	bindRow(maps[0], e)
	return e, nil
}

func (s *oracleStore) FindBusinessDateByInstitution(ctx context.Context, ins int) (*BusinessDateEntity, error) {
	rows, err := s.db.QueryContext(ctx, `SELECT * FROM BUSINESS_DATE WHERE BDT_INS_CODE = :1`, ins)
	if err != nil {
		return nil, err
	}
	defer rows.Close()
	maps, err := rowsToMaps(rows)
	if err != nil {
		return nil, err
	}
	if len(maps) == 0 {
		return nil, nil
	}
	e := &BusinessDateEntity{}
	bindRow(maps[0], e)
	return e, nil
}

func (s *oracleStore) FindAcquirerBins(ctx context.Context, ins int, binType string) ([]*AcquirerBinsEntity, error) {
	rows, err := s.db.QueryContext(ctx, `
		SELECT * FROM ACQUIRER_BINS WHERE ACQ_INS_CODE = :1 AND ACQ_BIN_TYPE = :2`, ins, binType)
	if err != nil {
		return nil, err
	}
	defer rows.Close()
	maps, err := rowsToMaps(rows)
	if err != nil {
		return nil, err
	}
	out := make([]*AcquirerBinsEntity, 0, len(maps))
	for _, m := range maps {
		e := &AcquirerBinsEntity{}
		bindRow(m, e)
		out = append(out, e)
	}
	return out, nil
}

func (s *oracleStore) UpdateAcquirerBin(ctx context.Context, e *AcquirerBinsEntity) error {
	_, err := s.db.ExecContext(ctx, `
		UPDATE ACQUIRER_BINS SET
		  ACQ_LAST_UPDATED  = :1,
		  ACQ_OUT_FILE_DATE = :2,
		  ACQ_OUT_FILE_SEQ  = :3,
		  ACQ_OUT_BATCH_NO  = :4
		WHERE ACQ_BIN = :5`,
		e.LastUpdated, nullTimeP(e.OutfileDate), e.OutFileSeq, nullIntP(e.OutBatchNo), e.Bin)
	return err
}

// ---- MC_ACQ_TXN_WORK ----

func (s *oracleStore) CountMcWorkBetween(ctx context.Context, ins, status int, from, to time.Time) (int, error) {
	var n int
	err := s.db.QueryRowContext(ctx, `
		SELECT COUNT(*) FROM MC_ACQ_TXN_WORK
		WHERE MCT_INS_CODE = :1 AND MCT_GEN_STATUS = :2 AND MCT_LOCAL_DATE_TIME BETWEEN :3 AND :4`,
		ins, status, from, to).Scan(&n)
	return n, err
}

func (s *oracleStore) CountMcWorkLessThanEqual(ctx context.Context, ins, status int, to time.Time) (int, error) {
	var n int
	err := s.db.QueryRowContext(ctx, `
		SELECT COUNT(*) FROM MC_ACQ_TXN_WORK
		WHERE MCT_INS_CODE = :1 AND MCT_GEN_STATUS = :2 AND MCT_LOCAL_DATE_TIME <= :3`,
		ins, status, to).Scan(&n)
	return n, err
}

func (s *oracleStore) FindMcWorkBetween(ctx context.Context, ins, status int, from, to time.Time) ([]*McAcqTxnWorkEntity, error) {
	rows, err := s.db.QueryContext(ctx, `
		SELECT * FROM MC_ACQ_TXN_WORK
		WHERE MCT_INS_CODE = :1 AND MCT_GEN_STATUS = :2 AND MCT_LOCAL_DATE_TIME BETWEEN :3 AND :4`,
		ins, status, from, to)
	if err != nil {
		return nil, err
	}
	defer rows.Close()
	return bindMcWork(rows)
}

func (s *oracleStore) FindMcWorkLessThanEqual(ctx context.Context, ins, status int, to time.Time) ([]*McAcqTxnWorkEntity, error) {
	rows, err := s.db.QueryContext(ctx, `
		SELECT * FROM MC_ACQ_TXN_WORK
		WHERE MCT_INS_CODE = :1 AND MCT_GEN_STATUS = :2 AND MCT_LOCAL_DATE_TIME <= :3`,
		ins, status, to)
	if err != nil {
		return nil, err
	}
	defer rows.Close()
	return bindMcWork(rows)
}

func (s *oracleStore) FindMcWorkByStatus(ctx context.Context, ins, status int) ([]*McAcqTxnWorkEntity, error) {
	rows, err := s.db.QueryContext(ctx, `
		SELECT * FROM MC_ACQ_TXN_WORK WHERE MCT_INS_CODE = :1 AND MCT_GEN_STATUS = :2`, ins, status)
	if err != nil {
		return nil, err
	}
	defer rows.Close()
	return bindMcWork(rows)
}

func bindMcWork(rows *sql.Rows) ([]*McAcqTxnWorkEntity, error) {
	maps, err := rowsToMaps(rows)
	if err != nil {
		return nil, err
	}
	out := make([]*McAcqTxnWorkEntity, 0, len(maps))
	for _, m := range maps {
		e := &McAcqTxnWorkEntity{}
		bindRow(m, e)
		out = append(out, e)
	}
	return out, nil
}

func (s *oracleStore) UpdateMcWorkStatuses(ctx context.Context, ents []*McAcqTxnWorkEntity) error {
	for _, e := range ents {
		if _, err := s.db.ExecContext(ctx, `
			UPDATE MC_ACQ_TXN_WORK SET
			  MCT_GEN_STATUS = :1,
			  MCT_FILE_ID    = :2,
			  MCT_OUT_FILE_DATE = :3
			WHERE MCT_SER_NUMBER = :4`,
			e.GeneralStatus, nullStr(e.FileID), nullTimeP(e.FileProcDate), e.SerNumber); err != nil {
			return err
		}
	}
	return nil
}

func (s *oracleStore) DeleteMcWork(ctx context.Context, ents []*McAcqTxnWorkEntity) error {
	for _, e := range ents {
		if _, err := s.db.ExecContext(ctx, `DELETE FROM MC_ACQ_TXN_WORK WHERE MCT_SER_NUMBER = :1`, e.SerNumber); err != nil {
			return err
		}
	}
	return nil
}

// ---- VW_IPM_OUT_WORK / IPM_OUT_WORK / OUTGOING_REPORT_DATA_WORK ----

func (s *oracleStore) FindViewIpmOutWorkAll(ctx context.Context) ([]*ViewIpmOutWorkEntity, error) {
	rows, err := s.db.QueryContext(ctx, `SELECT * FROM VW_IPM_OUT_WORK`)
	if err != nil {
		return nil, err
	}
	defer rows.Close()
	maps, err := rowsToMaps(rows)
	if err != nil {
		return nil, err
	}
	out := make([]*ViewIpmOutWorkEntity, 0, len(maps))
	for _, m := range maps {
		e := &ViewIpmOutWorkEntity{}
		bindRow(m, e)
		out = append(out, e)
	}
	return out, nil
}

func (s *oracleStore) InsertIpmOutWork(ctx context.Context, ents []*IpmOutWorkEntity) error {
	const sqlStmt = `
		INSERT INTO IPM_OUT_WORK (
		  IOW_IN_CODE, IOW_FILE_ID, IOW_REF_SER_NUMBER, IOW_DE001, IOW_DE002, IOW_DE003,
		  IOW_DE004, IOW_DE012, IOW_DE014, IOW_DE022, IOW_DE023, IOW_DE024, IOW_DE025,
		  IOW_DE026, IOW_DE030, IOW_DE031, IOW_DE032, IOW_DE033, IOW_DE037, IOW_DE038,
		  IOW_DE040, IOW_DE041, IOW_DE042, IOW_DE043, IOW_DE049, IOW_DE054, IOW_DE063,
		  IOW_DE071, IOW_DE072, IOW_DE093, IOW_DE094, IOW_DE095, IOW_PDS23, IOW_PDS25,
		  IOW_PDS52, IOW_PDS137, IOW_PDS148, IOW_PDS149, IOW_PDS155, IOW_PDS165, IOW_PDS176,
		  IOW_PDS211, IOW_PDS262, IOW_DE055_9F26, IOW_DE055_9F27, IOW_DE055_9F10,
		  IOW_DE055_9F37, IOW_DE055_9F36, IOW_DE055_95, IOW_DE055_9A, IOW_DE055_9C,
		  IOW_DE055_9F02, IOW_DE055_5F2A, IOW_DE055_82, IOW_DE055_9F1A, IOW_DE055_9F03,
		  IOW_DE048_PDS0213, IOW_DE055_84, IOW_DE055_9F33, IOW_DE055_9F34, IOW_DE048_PDS0170,
		  IOW_PDS0018, IOW_DE048_PDS0175)
		VALUES (:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16,:17,:18,
		  :19,:20,:21,:22,:23,:24,:25,:26,:27,:28,:29,:30,:31,:32,:33,:34,:35,:36,:37,
		  :38,:39,:40,:41,:42,:43,:44,:45,:46,:47,:48,:49,:50,:51,:52,:53,:54,:55,:56,
		  :57,:58,:59,:60,:61,:62)`
	for _, e := range ents {
		_, err := s.db.ExecContext(ctx, sqlStmt,
			e.InsCode, nullStr(e.FileId), e.RefSerNumber,
			nullStrP(e.DE001), nullStrP(e.DE002), nullStrP(e.DE003),
			nullStrP(e.DE004), nullStrP(e.DE012), nullStrP(e.DE014),
			nullStrP(e.DE022), nullStrP(e.DE023), nullStrP(e.DE024),
			nullStrP(e.DE025), nullStrP(e.DE026), nullStrP(e.DE030),
			nullStrP(e.DE031), nullStrP(e.DE032), nullStrP(e.DE033),
			nullStrP(e.DE037), nullStrP(e.DE038), nullStrP(e.DE040),
			nullStrP(e.DE041), nullStrP(e.DE042), nullStrP(e.DE043),
			nullStrP(e.DE049), nullStrP(e.DE054), nullStrP(e.DE063),
			nullStrP(e.DE071), nullStrP(e.DE072), nullStrP(e.DE093),
			nullStrP(e.DE094), nullStrP(e.DE095),
			nullStrP(e.PDS23), nullStr(e.PDS25), nullStr(e.PDS52),
			nullStrP(e.PDS137), nullStrP(e.PDS148), nullStr(e.PDS149),
			nullStrP(e.PDS155), nullStrP(e.PDS165), nullStr(e.PDS176),
			nullStrP(e.PDS211), nullStr(e.PDS262),
			nullStr(e.DE055_9F26), nullStr(e.DE055_9F27), nullStr(e.DE055_9F10),
			nullStr(e.DE055_9F37), nullStr(e.DE055_9F36), nullStr(e.DE055_95),
			nullStr(e.DE055_9A), nullStr(e.DE055_9C), nullStr(e.DE055_9F02),
			nullStr(e.DE055_5F2A), nullStr(e.DE055_82), nullStr(e.DE055_9F1A),
			nullStr(e.DE055_9F03), nullStr(e.DE048_PDS0213), nullStr(e.DE055_84),
			nullStr(e.DE055_9F33), nullStr(e.DE055_9F34), nullStr(e.DE048_PDS0170),
			nullStrP(e.PDS0018), nullStrP(e.DE048_PDS0175))
		if err != nil {
			return err
		}
	}
	return nil
}

func (s *oracleStore) FindIpmOutWorkByInsAndFile(ctx context.Context, ins int, fileId string) ([]*IpmOutWorkEntity, error) {
	rows, err := s.db.QueryContext(ctx, `
		SELECT * FROM IPM_OUT_WORK
		WHERE IOW_IN_CODE = :1 AND IOW_FILE_ID = :2
		ORDER BY IOW_SER_NUMBER ASC`, ins, fileId)
	if err != nil {
		return nil, err
	}
	defer rows.Close()
	maps, err := rowsToMaps(rows)
	if err != nil {
		return nil, err
	}
	out := make([]*IpmOutWorkEntity, 0, len(maps))
	for _, m := range maps {
		e := &IpmOutWorkEntity{}
		bindRow(m, e)
		out = append(out, e)
	}
	return out, nil
}

func (s *oracleStore) InsertOutgoingReport(ctx context.Context, ents []*OutgoingReportDataWorkEntity) error {
	const sqlStmt = `
		INSERT INTO OUTGOING_REPORT_DATA_WORK (
		  ORD_LAST_UPDATED, ORD_UPDATED_USER, ORD_INS_CODE, ORD_OUT_FILE_ID,
		  ORD_OUTGOING_DATE, ORD_TXN_DATE, ORD_NETWORK, ORD_POS_OR_PG, ORD_TXN_TYPE,
		  ORD_COUNT, ORD_AMOUNT)
		VALUES (:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11)`
	for _, e := range ents {
		if _, err := s.db.ExecContext(ctx, sqlStmt,
			e.LastUpdated, e.UpdatedUser, e.InstitutionCode, nullStr(e.OutFileId),
			e.OutgoingDate, e.TxnDate, nullStr(e.Network), nullStr(e.PosOrgPg),
			nullStr(e.TxnType), e.Count, e.Amount); err != nil {
			return err
		}
	}
	return nil
}

// ---- OUTGOING_SUMMARY ----

func (s *oracleStore) InsertSummaries(ctx context.Context, ents []*OutgoingSummaryEntity) error {
	const sqlStmt = `
		INSERT INTO OUTGOING_SUMMARY (
		  OTS_LAST_UPDATED, OTS_UPDATED_USER, OTS_INS_CODE, OTS_INT_CODE, OTS_OUT_FILE_DATE,
		  OTS_FILE_ID, OTS_OFL_SER_NUMBER, OTS_MTI, OTS_FUNCTION_CODE, OTS_PROC_CODE,
		  OTS_COUNT, OTS_AMOUNT, OTS_SCHG_AMOUNT, OTS_NET_AMOUNT, OTS_GEN_STATUS)
		VALUES (:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15)`
	for _, e := range ents {
		if _, err := s.db.ExecContext(ctx, sqlStmt,
			e.LastUpdated, e.UpdatedUser, e.InstitutionCode, e.InterfaceCode, e.OutFileDate,
			nullStr(e.FileId), e.RefSerialNumber, nullStr(e.MessageTypeId), nullStr(e.FunctionCode),
			nullStr(e.ProcCode), e.Count, e.Amount, e.SurchargeAmount, e.NetAmount, e.GeneralStatus); err != nil {
			return err
		}
	}
	return nil
}

func (s *oracleStore) FindSummaries(ctx context.Context, ins, intCode int, refSerial int64) ([]*OutgoingSummaryEntity, error) {
	rows, err := s.db.QueryContext(ctx, `
		SELECT * FROM OUTGOING_SUMMARY
		WHERE OTS_INS_CODE = :1 AND OTS_INT_CODE = :2 AND OTS_OFL_SER_NUMBER = :3`,
		ins, intCode, refSerial)
	if err != nil {
		return nil, err
	}
	defer rows.Close()
	maps, err := rowsToMaps(rows)
	if err != nil {
		return nil, err
	}
	out := make([]*OutgoingSummaryEntity, 0, len(maps))
	for _, m := range maps {
		e := &OutgoingSummaryEntity{}
		bindRow(m, e)
		out = append(out, e)
	}
	return out, nil
}

// ---- MC_ACQ_TXN_DATA ----

// mcSummaryGroup is one aggregated OUTGOING_SUMMARY row for the MC flow.
type mcSummaryGroup struct {
	MessageTypeId string
	FunctionCode  string
	ProcCode      string
	Count         int
	Amount        float64
	SurAmount     float64
	NetAmount     float64
}

func (s *oracleStore) FindMcSummaryGroups(ctx context.Context, ins int) ([]mcSummaryGroup, error) {
	rows, err := s.db.QueryContext(ctx, `
		SELECT MCT_MSG_TYPE_ID, MCT_FUNC_CODE, SUBSTR(MCT_PROC_CODE, 1, 2),
		       COUNT(MCT_MSG_TYPE_ID),
		       SUM(NVL(MCT_TXN_AMOUNT, 0)),
		       SUM(NVL(MCT_SCHG_AMOUNT, 0)),
		       SUM(NVL(MCT_TXN_AMOUNT, 0) + NVL(MCT_SCHG_AMOUNT, 0))
		FROM MC_ACQ_TXN_WORK
		WHERE MCT_GEN_STATUS = 9 AND MCT_INS_CODE = :1
		GROUP BY MCT_MSG_TYPE_ID, MCT_FUNC_CODE, SUBSTR(MCT_PROC_CODE, 1, 2)`, ins)
	if err != nil {
		return nil, err
	}
	defer rows.Close()
	var out []mcSummaryGroup
	for rows.Next() {
		var g mcSummaryGroup
		if err := rows.Scan(&g.MessageTypeId, &g.FunctionCode, &g.ProcCode, &g.Count, &g.Amount, &g.SurAmount, &g.NetAmount); err != nil {
			return nil, err
		}
		out = append(out, g)
	}
	return out, rows.Err()
}

func (s *oracleStore) InsertMcData(ctx context.Context, ents []*McAcqTxnDataEntity) error {
	const sqlStmt = `
		INSERT INTO MC_ACQ_TXN_DATA (
		  MCT_SER_NUMBER, MCT_LAST_UPDATED, MCT_UPDATED_USER, MCT_INS_CODE, MCT_INT_CODE,
		  MCT_PRJ_SER_NUMBER, MCT_GEN_STATUS, MCT_TXN_REF_NUMBER, MCT_TXN_TYPE, MCT_FILE_ID,
		  MCT_MSG_TYPE_ID, MCT_PROC_CODE, MCT_FUNC_CODE, MCT_TXN_AMOUNT, MCT_SCHG_AMOUNT,
		  MCT_OUT_FILE_DATE, MCT_CARD_NUMBER, MCT_LOCAL_DATE_TIME, MCT_EXPIRY_DATE, MCT_POS_DATA_CODE,
		  MCT_MSG_REASON_CODE, MCT_MCC, MCT_ACQ_REF_DATA, MCT_ACQ_INST_ID_CODE, MCT_RET_REF_NUMBER,
		  MCT_APPR_CODE, MCT_RESP_CODE, MCT_SERVICE_CODE, MCT_TERMINAL_ID, MCT_MERCHANT_ID,
		  MCT_ME_NAME, MCT_ME_CITY, MCT_ME_ZIP_CODE, MCT_ME_COUNTRY, MCT_TRL_TYPE,
		  MCT_ECOM_INDICATOR, MCT_TXN_FEE_AMOUNT, MCT_TXN_CURR_EXP, MCT_TXN_CUR_CODE, MCT_IRD,
		  MCT_SETL_INDICATOR, MCT_CARD_SEQ_NUMBER, MCT_APP_CRYPTOGRAM, MCT_CRYPT_INFO_DATA, MCT_ISS_APP_DATA,
		  MCT_UPBL_NUMBER, MCT_APP_TXN_COUNTER, MCT_TRL_VER_RESULTS, MCT_TXN_DATE, MCT_CHIP_TXN_DATE,
		  MCT_CHIP_TXN_TYPE, MCT_CRYPT_AMOUNT, MCT_APP_IC_PROFILE, MCT_TRL_CON_CODE, MCT_CASHBACK_AMOUNT,
		  MCT_CVM_RESULTS, MCT_TRL_CAPABILITIES, MCT_IFD_SER_NUMBER, MCT_TCC, MCT_CHIP_CUR_CODE,
		  MCT_CHIP_TRL_TYPE, MCT_TRL_APP_VER_NUMBER, MCT_TXN_SEQ_COUNTER, MCT_ISS_AUTH_DATA, MCT_TXN_LIFE_CYCL_ID,
		  MCT_MSG_NUMBER, MCT_MEMBER_TEXT, MCT_ORG_INST_ID_CODE, MCT_REV_INDICATOR, MCT_MER_MC_ASSIGNED_ID,
		  MCT_CARD_TYPE, MCT_DOM_INTL_FLAG, MCT_SMS_DMS_FLAG, MCT_POS_PG_TYPE, MCT_CENTRE_PROC_DATE,
		  MCT_ENC_CARD_NUMBER, MCT_MRP_SER_NUMBER, MCT_ME_COUNTRY_OF_ORIGIN, MCT_TIP_AMOUNT, MCT_CHIP_TRL_CAPABILITIES,
		  MCT_DEDICATED_FILE_NAME, MCT_CARD_ACC_STREET_ADDRESS, MCT_CUSTOMER_SERVICE_PHONE_NO, MCT_DCC_INDICATOR, MCT_DCC_CURRENCY,
		  MCT_DCC_AMOUNT, MCT_DCC_CURR_EXP, MCT_MPOS_ACC_DEV_TYPE, MCT_ACC_URL_ADDRESS)
		VALUES (:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16,:17,:18,:19,:20,
		  :21,:22,:23,:24,:25,:26,:27,:28,:29,:30,:31,:32,:33,:34,:35,:36,:37,:38,:39,:40,
		  :41,:42,:43,:44,:45,:46,:47,:48,:49,:50,:51,:52,:53,:54,:55,:56,:57,:58,:59,:60,
		  :61,:62,:63,:64,:65,:66,:67,:68,:69,:70,:71,:72,:73,:74,:75,:76,:77,:78,:79,:80,
		  :81,:82,:83,:84,:85,:86,:87,:88,:89)`
	for _, e := range ents {
		_, err := s.db.ExecContext(ctx, sqlStmt,
			e.SerNumber, e.LastUpdated, e.UpdatedUser, e.InstitutionCode, e.IntCode,
			e.PrjSerNumber, e.GeneralStatus, e.TxnRefSerNumber, nullStr(e.TxnType), nullStr(e.FileID),
			nullStr(e.MessageTypeId), nullStr(e.ProcCode), nullStr(e.FunctionCode), e.TxnAmount, e.SurchargeAmount,
			nullTimeP(e.FileProcDate), nullStr(e.CardNumber), nullTimeP(e.LocalDateTime), nullStr(e.ExpiryDate), nullStr(e.PosDataCode),
			nullStr(e.MsgReasonCode), nullStr(e.Mcc), nullStr(e.AcqRefData), nullStr(e.AcqinstIdCode), nullStr(e.Rrn),
			nullStr(e.ApprovalCode), nullStr(e.ResponseCode), nullStr(e.ServiceCode), nullStr(e.TerminalId), nullStr(e.MerchantId),
			nullStr(e.MeName), nullStr(e.MeCity), nullStr(e.MePinCode), nullStr(e.MeCountry), nullStr(e.TrlType),
			nullStr(e.MotoEcomIndicator), e.TxnFeeAmount, e.TxnCurrExp, nullStr(e.TxnCurCode), nullStr(e.Ird),
			nullStr(e.SettlementIndicator), nullStr(e.CardSeqNumber), nullStr(e.AppCryptogram), nullStr(e.CryptInfoData), nullStr(e.IssAppData),
			nullStr(e.UpblNumber), nullStr(e.AppTxnCounter), nullStr(e.TrlVerResult), nullTimeP(e.TxnDate), nullStr(e.ChipTxnDate),
			nullStr(e.ChipTxnType), e.CryptAmount, nullStr(e.AppICProfile), nullStr(e.TrlConCode), e.ChipCashBack,
			nullStr(e.CvmResult), nullStr(e.TrlCapabilities), nullStr(e.IfdSerNumber), nullStr(e.Tcc), nullStr(e.ChipCurCode),
			nullStr(e.ChipTrlType), nullStr(e.TrlAppVerNumber), nullStr(e.TxnSeqCounter), nullStr(e.IssAuthData), nullStr(e.TxnlifeCycleId),
			nullStr(e.MsgNumber), nullStr(e.MemberText), nullStr(e.OrgInstIdCode), nullStr(e.RevIndiCator), nullStr(e.Maid),
			nullStr(e.CardType), nullStr(e.CardDomIntlFlag), nullStr(e.DmsSmsMode), nullStr(e.PosPgType), nullStr(e.CentreProcDate),
			nullStr(e.EncryptedCardNumber), nullIntP(e.MrpSerNumber), nullStr(e.MeCountryOfOrigin), e.TipAmount, nullStr(e.ChipTrlCapabilities),
			nullStr(e.DedicatedFileName), nullStr(e.CardAccepStreetAddress), nullStr(e.CustomerServicePhNum), nullStr(e.DccIndicator), nullStr(e.DccCurrency),
			e.DccAmount, e.DccTxnCurrencyExponent, nullStr(e.MposAccDevType), nullStr(e.AccepterUrlAddress))
		if err != nil {
			return err
		}
	}
	return nil
}

func (s *oracleStore) FindMcDataByFileId(ctx context.Context, ins int, fileId string) ([]*McAcqTxnDataEntity, error) {
	rows, err := s.db.QueryContext(ctx, `
		SELECT * FROM MC_ACQ_TXN_DATA WHERE MCT_INS_CODE = :1 AND MCT_FILE_ID = :2`, ins, fileId)
	if err != nil {
		return nil, err
	}
	defer rows.Close()
	return bindMcWork(rows)
}

func (s *oracleStore) DeleteMcData(ctx context.Context, ents []*McAcqTxnDataEntity) error {
	for _, e := range ents {
		if _, err := s.db.ExecContext(ctx, `DELETE FROM MC_ACQ_TXN_DATA WHERE MCT_SER_NUMBER = :1`, e.SerNumber); err != nil {
			return err
		}
	}
	return nil
}

// InsertMcWork re-inserts rows into MC_ACQ_TXN_WORK during revert (the serial
// number is DB-assigned, matching a fresh JPA entity).
func (s *oracleStore) InsertMcWork(ctx context.Context, ents []*McAcqTxnWorkEntity) error {
	const sqlStmt = `
		INSERT INTO MC_ACQ_TXN_WORK (
		  MCT_LAST_UPDATED, MCT_UPDATED_USER, MCT_INS_CODE, MCT_INT_CODE,
		  MCT_PRJ_SER_NUMBER, MCT_GEN_STATUS, MCT_TXN_REF_NUMBER, MCT_TXN_TYPE, MCT_FILE_ID,
		  MCT_MSG_TYPE_ID, MCT_PROC_CODE, MCT_FUNC_CODE, MCT_TXN_AMOUNT, MCT_SCHG_AMOUNT,
		  MCT_OUT_FILE_DATE, MCT_CARD_NUMBER, MCT_LOCAL_DATE_TIME, MCT_EXPIRY_DATE, MCT_POS_DATA_CODE,
		  MCT_MSG_REASON_CODE, MCT_MCC, MCT_ACQ_REF_DATA, MCT_ACQ_INST_ID_CODE, MCT_RET_REF_NUMBER,
		  MCT_APPR_CODE, MCT_RESP_CODE, MCT_SERVICE_CODE, MCT_TERMINAL_ID, MCT_MERCHANT_ID,
		  MCT_ME_NAME, MCT_ME_CITY, MCT_ME_ZIP_CODE, MCT_ME_COUNTRY, MCT_TRL_TYPE,
		  MCT_ECOM_INDICATOR, MCT_TXN_FEE_AMOUNT, MCT_TXN_CURR_EXP, MCT_TXN_CUR_CODE, MCT_IRD,
		  MCT_SETL_INDICATOR, MCT_CARD_SEQ_NUMBER, MCT_APP_CRYPTOGRAM, MCT_CRYPT_INFO_DATA, MCT_ISS_APP_DATA,
		  MCT_UPBL_NUMBER, MCT_APP_TXN_COUNTER, MCT_TRL_VER_RESULTS, MCT_TXN_DATE, MCT_CHIP_TXN_DATE,
		  MCT_CHIP_TXN_TYPE, MCT_CRYPT_AMOUNT, MCT_APP_IC_PROFILE, MCT_TRL_CON_CODE, MCT_CASHBACK_AMOUNT,
		  MCT_CVM_RESULTS, MCT_TRL_CAPABILITIES, MCT_IFD_SER_NUMBER, MCT_TCC, MCT_CHIP_CUR_CODE,
		  MCT_CHIP_TRL_TYPE, MCT_TRL_APP_VER_NUMBER, MCT_TXN_SEQ_COUNTER, MCT_ISS_AUTH_DATA, MCT_TXN_LIFE_CYCL_ID,
		  MCT_MSG_NUMBER, MCT_MEMBER_TEXT, MCT_ORG_INST_ID_CODE, MCT_REV_INDICATOR, MCT_MER_MC_ASSIGNED_ID,
		  MCT_CARD_TYPE, MCT_DOM_INTL_FLAG, MCT_SMS_DMS_FLAG, MCT_POS_PG_TYPE, MCT_CENTRE_PROC_DATE,
		  MCT_ENC_CARD_NUMBER, MCT_MRP_SER_NUMBER, MCT_ME_COUNTRY_OF_ORIGIN, MCT_TIP_AMOUNT, MCT_CHIP_TRL_CAPABILITIES,
		  MCT_DEDICATED_FILE_NAME, MCT_CARD_ACC_STREET_ADDRESS, MCT_CUSTOMER_SERVICE_PHONE_NO, MCT_DCC_INDICATOR, MCT_DCC_CURRENCY,
		  MCT_DCC_AMOUNT, MCT_DCC_CURR_EXP, MCT_MPOS_ACC_DEV_TYPE, MCT_ACC_URL_ADDRESS)
		VALUES (:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16,:17,:18,:19,:20,
		  :21,:22,:23,:24,:25,:26,:27,:28,:29,:30,:31,:32,:33,:34,:35,:36,:37,:38,:39,:40,
		  :41,:42,:43,:44,:45,:46,:47,:48,:49,:50,:51,:52,:53,:54,:55,:56,:57,:58,:59,:60,
		  :61,:62,:63,:64,:65,:66,:67,:68,:69,:70,:71,:72,:73,:74,:75,:76,:77,:78,:79,:80,
		  :81,:82,:83,:84,:85,:86,:87,:88)`
	for _, e := range ents {
		_, err := s.db.ExecContext(ctx, sqlStmt,
			e.LastUpdated, e.UpdatedUser, e.InstitutionCode, e.IntCode,
			e.PrjSerNumber, e.GeneralStatus, e.TxnRefSerNumber, nullStr(e.TxnType), nullStr(e.FileID),
			nullStr(e.MessageTypeId), nullStr(e.ProcCode), nullStr(e.FunctionCode), e.TxnAmount, e.SurchargeAmount,
			nullTimeP(e.FileProcDate), nullStr(e.CardNumber), nullTimeP(e.LocalDateTime), nullStr(e.ExpiryDate), nullStr(e.PosDataCode),
			nullStr(e.MsgReasonCode), nullStr(e.Mcc), nullStr(e.AcqRefData), nullStr(e.AcqinstIdCode), nullStr(e.Rrn),
			nullStr(e.ApprovalCode), nullStr(e.ResponseCode), nullStr(e.ServiceCode), nullStr(e.TerminalId), nullStr(e.MerchantId),
			nullStr(e.MeName), nullStr(e.MeCity), nullStr(e.MePinCode), nullStr(e.MeCountry), nullStr(e.TrlType),
			nullStr(e.MotoEcomIndicator), e.TxnFeeAmount, e.TxnCurrExp, nullStr(e.TxnCurCode), nullStr(e.Ird),
			nullStr(e.SettlementIndicator), nullStr(e.CardSeqNumber), nullStr(e.AppCryptogram), nullStr(e.CryptInfoData), nullStr(e.IssAppData),
			nullStr(e.UpblNumber), nullStr(e.AppTxnCounter), nullStr(e.TrlVerResult), nullTimeP(e.TxnDate), nullStr(e.ChipTxnDate),
			nullStr(e.ChipTxnType), e.CryptAmount, nullStr(e.AppICProfile), nullStr(e.TrlConCode), e.ChipCashBack,
			nullStr(e.CvmResult), nullStr(e.TrlCapabilities), nullStr(e.IfdSerNumber), nullStr(e.Tcc), nullStr(e.ChipCurCode),
			nullStr(e.ChipTrlType), nullStr(e.TrlAppVerNumber), nullStr(e.TxnSeqCounter), nullStr(e.IssAuthData), nullStr(e.TxnlifeCycleId),
			nullStr(e.MsgNumber), nullStr(e.MemberText), nullStr(e.OrgInstIdCode), nullStr(e.RevIndiCator), nullStr(e.Maid),
			nullStr(e.CardType), nullStr(e.CardDomIntlFlag), nullStr(e.DmsSmsMode), nullStr(e.PosPgType), nullStr(e.CentreProcDate),
			nullStr(e.EncryptedCardNumber), nullIntP(e.MrpSerNumber), nullStr(e.MeCountryOfOrigin), e.TipAmount, nullStr(e.ChipTrlCapabilities),
			nullStr(e.DedicatedFileName), nullStr(e.CardAccepStreetAddress), nullStr(e.CustomerServicePhNum), nullStr(e.DccIndicator), nullStr(e.DccCurrency),
			e.DccAmount, e.DccTxnCurrencyExponent, nullStr(e.MposAccDevType), nullStr(e.AccepterUrlAddress))
		if err != nil {
			return err
		}
	}
	return nil
}

// ---- POS_TRANSACTIONS ----

func (s *oracleStore) FindPosBySerNumbers(ctx context.Context, ser []int64) ([]*PosTransactionEntity, error) {
	if len(ser) == 0 {
		return nil, nil
	}
	query := `SELECT * FROM POS_TRANSACTIONS WHERE PTR_SER_NUMBER IN (`
	for i := range ser {
		if i > 0 {
			query += ","
		}
		query += fmt.Sprintf(":%d", i+1)
	}
	query += `)`
	args := make([]any, len(ser))
	for i, v := range ser {
		args[i] = v
	}
	rows, err := s.db.QueryContext(ctx, query, args...)
	if err != nil {
		return nil, err
	}
	defer rows.Close()
	maps, err := rowsToMaps(rows)
	if err != nil {
		return nil, err
	}
	out := make([]*PosTransactionEntity, 0, len(maps))
	for _, m := range maps {
		e := &PosTransactionEntity{}
		bindRow(m, e)
		out = append(out, e)
	}
	return out, nil
}

func (s *oracleStore) UpdatePosStatuses(ctx context.Context, ents []*PosTransactionEntity) error {
	for _, e := range ents {
		if _, err := s.db.ExecContext(ctx, `
			UPDATE POS_TRANSACTIONS SET
			  PTR_GEN_STATUS = :1,
			  PTR_OUT_STATUS = :2
			WHERE PTR_SER_NUMBER = :3`,
			e.GenStatus, nullStr(e.OutStatus), e.SerialNumber); err != nil {
			return err
		}
	}
	return nil
}

func (s *oracleStore) CompletePosStatus(ctx context.Context, ins int) error {
	_, err := s.db.ExecContext(ctx, `
		UPDATE POS_TRANSACTIONS pos SET
		  pos.PTR_GEN_STATUS = 6,
		  pos.PTR_OUT_STATUS = 'Completed'
		WHERE pos.PTR_RRN IN (
		  SELECT mc.MCT_RET_REF_NUMBER FROM MC_ACQ_TXN_WORK mc WHERE mc.MCT_GEN_STATUS = 4
		)
		AND pos.PTR_NETWORK IN ('MCI','MDS')
		AND pos.PTR_GEN_STATUS = 4
		AND pos.PTR_INS_CODE = :1`, ins)
	return err
}
