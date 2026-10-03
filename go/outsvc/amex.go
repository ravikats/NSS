package outsvc

import (
	"context"
	"fmt"
	"os"
	"path/filepath"
	"strings"
	"time"
)

// Amex outgoing constants. Amex settlement files follow the FSF (Financial
// Statement Format) / VAPAY layout produced by AmexGFSGOutgoingService.
//
// Filename:  AMEX_FSF_VAPAY000001_{yyyyMMdd}_{seq:02d}
// File id:   IT{yyDDD}{seq:02d}   (Julian date = 2-digit year + 3-digit day-of-year)
//
// Record types:
//
//	TFH  file header (one)
//	TAB  transaction record (one per txn)
//	TAA  location addenda (one per txn) + TAA-EMV (one per txn when DE55/EMV present)
//	TBT  batch total (on merchant/batch break + final)
//	TFS  file total (one)
const (
	amexIntCategory      = "AMEX"
	amexAcqBinType       = "A"
	amexSubmitterId      = "VAPAY000001"
	amexFileVersion      = "12010000"
	amexFormatCode       = "02"
	amexSubmissionMethod = "02"
	amexBatchReserved    = "000"
)

// ProcessAmexOutgoing is the Go port of AmexOutgoingServiceImpl.generateAmexOutgoing.
// It mirrors ProcessUnionPayOutgoing: look up the format/interface, insert an
// OUT_FILE_LOG row (gen_status=9), mark gen_status=3 work rows -> 9, decrypt
// the PAN tokens, build the AMEX FSF file, insert OUTGOING_SUMMARY, flip rows
// 9->4, complete POS status, then archive work -> data and delete the work rows.
func (s *OutgoingService) ProcessAmexOutgoing(ctx context.Context, insCode, user, formatCode int, insShortName string, startDate, toDate *time.Time) string {
	intCode := 0
	forCode := 0

	fileFormatEntity, err := s.store.FindFileFormatBySystemCodeAndType(ctx, formatCode, "O")
	if err != nil {
		logOutsvc("FindFileFormatBySystemCodeAndType", err)
		return "Failed"
	}
	if fileFormatEntity != nil {
		forCode = fileFormatEntity.Code
	}
	interfaces, err := s.store.FindInterfaceByCategory(ctx, amexIntCategory)
	if err != nil {
		logOutsvc("FindInterfaceByCategory", err)
		return "Failed"
	}
	if interfaces != nil {
		intCode = interfaces.InterfaceCode
	}
	results, err := s.store.FindFileLogByFormatCodeAndStatuses(ctx, forCode)
	if err != nil {
		logOutsvc("FindFileLogByFormatCodeAndStatuses", err)
		return "Failed"
	}
	if len(results) > 0 {
		return "File Generation already Scheduled"
	}

	acqBinList, err := s.store.FindAcquirerBins(ctx, insCode, amexAcqBinType)
	if err != nil {
		logOutsvc("FindAcquirerBins", err)
		return "Failed"
	}
	if len(acqBinList) == 0 || acqBinList[0] == nil {
		return "Acquirer bin not found"
	}
	acqBin := acqBinList[0]

	var txnList []*AmexAcqTxnWorkEntity
	if startDate == nil {
		txnList, err = s.store.FindAmexWorkLessThanEqual(ctx, insCode, intCode, 3, *toDate)
	} else {
		txnList, err = s.store.FindAmexWorkBetween(ctx, insCode, intCode, 3, *startDate, *toDate)
	}
	if err != nil {
		logOutsvc("FindAmexWork", err)
		return "Failed"
	}
	if len(txnList) == 0 {
		return "No data found"
	}

	// Collect encrypted card tokens (deduped, like UnionPay/Mercury).
	seen := map[string]struct{}{}
	tokens := make([]string, 0, len(txnList))
	for _, e := range txnList {
		if e.EncryptedCardNumber == "" {
			continue
		}
		if _, ok := seen[e.EncryptedCardNumber]; ok {
			continue
		}
		seen[e.EncryptedCardNumber] = struct{}{}
		tokens = append(tokens, e.EncryptedCardNumber)
	}
	response := s.crypto.GetCardNumber(tokens)

	now := s.now()
	outgoingLogSerialNumber := s.inserOutFileLog(ctx, user, insCode, intCode, forCode)
	if outgoingLogSerialNumber == 0 {
		return "Failed"
	}

	// Mark gen_status 3 -> 9 (marked for outgoing), file id cleared.
	for _, e := range txnList {
		e.LastUpdated = now
		e.UpdatedUser = user
		e.GenStatus = 9
		e.FileId = ""
	}
	if err := s.store.UpdateAmexWorkStatuses(ctx, txnList); err != nil {
		logOutsvc("UpdateAmexWorkStatuses", err)
		return "Failed"
	}

	if response == nil {
		for _, e := range txnList {
			e.GenStatus = 7
		}
		if err := s.store.UpdateAmexWorkStatuses(ctx, txnList); err != nil {
			logOutsvc("UpdateAmexWorkStatuses", err)
		}
		s.updateOutFilelog(ctx, insCode, outgoingLogSerialNumber, "", nil)
		return "Outgoing Failed"
	}

	fileSequence := s.updateAndGetAmexFileSequence(ctx, acqBin, now)
	fileId := amexFileId(now, fileSequence)
	fileName := amexFileName(now, fileSequence)

	cards := response
	linesList := buildAmexFile(txnList, fileSequence, fileId, cards, s.cfg.Region, now)
	if linesList == nil || len(linesList) == 0 {
		s.updateOutFilelog(ctx, insCode, outgoingLogSerialNumber, fileName, nil)
		return "Outgoing Failed"
	}

	if err := s.writeAmexLinesToFile(linesList, insShortName, fileName); err != nil {
		s.updateOutFilelog(ctx, insCode, outgoingLogSerialNumber, fileName, nil)
		return "Outgoing Failed"
	}

	fid := fileId
	s.updateOutFilelog(ctx, insCode, outgoingLogSerialNumber, fileName, &fid)

	// Insert the summary while the file rows are still gen_status=9 so the
	// grouping query finds them (see the "empty summary" Java bug + fix note in
	// unionpay.go).
	s.insertAmexIntoOutgoingSummary(ctx, user, insCode, intCode, fileName, outgoingLogSerialNumber)

	now = s.now()
	for _, e := range txnList {
		e.LastUpdated = now
		e.UpdatedUser = user
		e.GenStatus = 4
		e.FileId = fileName
	}
	if err := s.store.UpdateAmexWorkStatuses(ctx, txnList); err != nil {
		logOutsvc("UpdateAmexWorkStatuses", err)
		return "Failed"
	}

	if err := s.store.CompleteAmexPosStatus(ctx, insCode); err != nil {
		logOutsvc("CompleteAmexPosStatus", err)
		return "Failed"
	}
	s.moveAmexWorkToData(ctx, insCode, user)
	if err := s.store.DeleteAmexWork(ctx, txnList); err != nil {
		logOutsvc("DeleteAmexWork", err)
	}
	return "Success"
}

// updateAndGetAmexFileSequence mirrors the other networks: reuse the
// ACQUIRER_BINS (bin_type 'A') file sequence, incrementing it if the file
// date is today, else resetting to 1.
func (s *OutgoingService) updateAndGetAmexFileSequence(ctx context.Context, acqBin *AcquirerBinsEntity, now time.Time) int {
	fileSequence := 1
	if acqBin.OutfileDate != nil && sameCalendarDay(*acqBin.OutfileDate, now) {
		fileSequence = acqBin.OutFileSeq
	}
	acqBin.OutFileSeq = fileSequence + 1
	t := now
	acqBin.OutfileDate = &t
	if err := s.store.UpdateAcquirerBin(ctx, acqBin); err != nil {
		logOutsvc("UpdateAcquirerBin", err)
	}
	return fileSequence
}

// amexFileId renders the IT{yyDDD}{seq:02d} file id.
func amexFileId(now time.Time, seq int) string {
	julian := now.Format("06002") // 2-digit year + 3-digit day-of-year
	return "IT" + julian + fmt.Sprintf("%02d", seq)
}

// amexFileName renders AMEX_FSF_VAPAY000001_{yyyyMMdd}_{seq:02d}.
func amexFileName(now time.Time, seq int) string {
	return "AMEX_FSF_" + amexSubmitterId + "_" + now.Format("20060102") + "_" + fmt.Sprintf("%02d", seq)
}

// writeAmexLinesToFile writes the record builders to RECON_OUT/{fileName}. The
// Java port writes one line per StringBuilder with a platform line separator;
// the local replica (Linux) uses "\n".
func (s *OutgoingService) writeAmexLinesToFile(lines []string, insShortName, fileName string) error {
	if len(lines) == 0 {
		return nil
	}
	path := filepath.Join(s.cfg.ReconOutDir, fileName)
	var sb strings.Builder
	for _, line := range lines {
		sb.WriteString(line)
		sb.WriteString("\n")
	}
	return os.WriteFile(path, []byte(sb.String()), 0o644)
}

// ---- AMEX FSF record builders (mirror AmexGFSGOutgoingServiceImpl) ----

// buildAmexFile mirrors amexOutData: header, per-txn TAB+TAA[+(TAA-EMV)],
// batch-total (TBT) breaks, file-total (TFS). Returns nil on error (Java
// returns null -> "Outgoing Failed").
func buildAmexFile(amexData []*AmexAcqTxnWorkEntity, fileSequence int, fileId string, cards map[string]string, region string, now time.Time) []string {
	if len(amexData) == 0 {
		return nil
	}
	lines := make([]string, 0, len(amexData)*4+6)
	recordSeqNo := 0

	recordSeqNo++
	lines = append(lines, getTFHRecord(recordSeqNo, fileId, fileSequence, now))

	tabCount := 0
	tabAmount := 0.0
	debitsAmount := 0.0
	debitsCount := 0
	creditsAmount := 0.0
	creditsCount := 0

	for i, entity := range amexData {
		decryptedCardNumber := ""
		if entity.EncryptedCardNumber != "" && cards != nil {
			decryptedCardNumber = cards[entity.EncryptedCardNumber]
		}
		txnAmount := entity.TxnAmount
		if len(entity.ProcCode) >= 2 && entity.ProcCode[:2] == "20" {
			tabAmount -= txnAmount
			creditsAmount += txnAmount
			creditsCount++
		} else {
			tabAmount += txnAmount
			debitsAmount += txnAmount
			debitsCount++
		}
		tabCount++

		recordSeqNo++
		lines = append(lines, getTAB(recordSeqNo, entity, decryptedCardNumber, region))
		recordSeqNo++
		lines = append(lines, getTAALocation(recordSeqNo, entity))
		if entity.Emv != "" {
			recordSeqNo++
			lines = append(lines, getTAAEMV(recordSeqNo, entity))
		}

		currentTrlBatchNumber := entity.TrlBthNumber
		currentMappedMid := entity.MappedMid
		currentCurCode := entity.TxnCurCode
		nextIdx := i + 1
		if nextIdx < len(amexData) {
			nx := amexData[nextIdx]
			midChanged := currentMappedMid != "" && nx.MappedMid != "" && currentMappedMid != nx.MappedMid
			batchChanged := currentTrlBatchNumber != nx.TrlBthNumber
			if midChanged || batchChanged {
				recordSeqNo++
				lines = append(lines, getTBT(recordSeqNo, tabCount, tabAmount, currentMappedMid, currentTrlBatchNumber, currentCurCode, now))
				tabCount = 0
				tabAmount = 0.0
			}
		}
	}

	if len(amexData) > 0 {
		last := amexData[len(amexData)-1]
		recordSeqNo++
		lines = append(lines, getTBT(recordSeqNo, tabCount, tabAmount, last.MappedMid, last.TrlBthNumber, last.TxnCurCode, now))
	}

	recordSeqNo++
	lines = append(lines, getTFS(recordSeqNo, debitsAmount, debitsCount, creditsAmount, creditsCount, now))
	return lines
}

// getTFHRecord builds the file header (TFH).
func getTFHRecord(recordSeqNo int, fileId string, fileSequence int, now time.Time) string {
	return "TFH" +
		fmt.Sprintf("%08d", recordSeqNo) +
		amexSubmitterId +
		strings.Repeat(" ", 21) +
		amexFileRefPadded(fileId, 20) +
		fmt.Sprintf("%09d", fileSequence) +
		now.Format("20060102") +
		now.Format("150405") +
		amexFileVersion +
		strings.Repeat(" ", 617)
}

// getTAB builds the transaction record (TAB). transactionDate/time are taken
// from the entity's local date/time (mirrors entity.getLocalDateTime()).
func getTAB(recordSeqNo int, e *AmexAcqTxnWorkEntity, pan, region string) string {
	txnId := padRight(e.TxnId, 15)
	txnId = strings.ReplaceAll(txnId, " ", "0")
	txnId = leftStr(txnId, 15)

	transactionAmount := e.TxnAmount + e.SurchargeAmount
	tabAmountStr := fmt.Sprintf("%012d", int64(transactionAmount*100))

	procCode := (e.ProcCode + "000000")[:6]
	curCode := padRight(e.TxnCurCode, 3)

	var txnDate, txnTime string
	if e.LocalDateTime != nil {
		txnDate = e.LocalDateTime.Format("20060102")
		txnTime = e.LocalDateTime.Format("150405")
	}

	merchantLocationId := padRight(e.TrlLocation, 15)
	merchantContact := padRight(e.MeContactEmail, 40)
	posDataCode := (padRight(e.PosDataCode, 12) + "000000000000")[:12]
	invoiceRef := padRight(e.InvoiceNumber, 30)

	// MENA region: reserved1 = STAN[0:10], reserved4 = merchantId.substring(3),
	// reserved6 = rrn[0:15]; otherwise spaces.
	var reserved1, reserved4, reserved6 string
	if region == "MENA" {
		reserved1 = leftStr(padRight(e.Stan, 10), 10)
		reserved4 = leftStr(padRight(maybeSubstring(e.MerchantId, 3), 12), 12)
		reserved6 = leftStr(padRight(e.Rrn, 15), 15)
	} else {
		reserved1 = strings.Repeat(" ", 10)
		reserved4 = "000000000000"
		reserved6 = strings.Repeat(" ", 15)
	}

	return "TAB" +
		fmt.Sprintf("%08d", recordSeqNo) +
		txnId +
		amexFormatCode +
		strings.Repeat(" ", 2) + // mediaCode
		amexSubmissionMethod +
		padRight(reserved1, 10) +
		padRight(e.ApprovalCode, 6) +
		padRight(pan, 19) +
		padRight(e.ExpiryDate, 4) +
		txnDate +
		txnTime +
		"000" + // reserved2
		tabAmountStr +
		procCode +
		curCode +
		"01" + // extendedPaymentData
		padRight(e.MappedMid, 15) +
		merchantLocationId +
		merchantContact +
		padRight(e.TerminalId, 8) +
		posDataCode +
		"000" + // reserved3
		padRight(reserved4, 12) +
		strings.Repeat(" ", 3) + // reserved5
		invoiceRef +
		padRight(reserved6, 15) +
		strings.Repeat(" ", 8) + // tabImageSeqNumber
		strings.Repeat(" ", 2) + // matchingKeyType
		strings.Repeat(" ", 21) + // matchingKey
		padRight(e.MotoEcomIndicator, 2) +
		strings.Repeat(" ", 403)
}

// getTAALocation builds the location addenda record (TAA).
func getTAALocation(recordSeqNo int, e *AmexAcqTxnWorkEntity) string {
	txnId := padRight(e.TxnId, 15)
	txnId = strings.ReplaceAll(txnId, " ", "0")
	txnId = leftStr(txnId, 15)
	return "TAA" +
		fmt.Sprintf("%08d", recordSeqNo) +
		txnId +
		"00" + // reserved1
		"99" + // addendaTypeCode
		padRight(e.MeName, 38) +
		padRight(e.LocationAddress, 27) + strings.Repeat(" ", 11) +
		padRight(e.MeCity, 21) +
		padRight(e.LocRegionCode, 3) +
		padRight(e.MeCountry, 3) +
		padRight(e.MePinCode, 15) +
		padRight(e.Mcc, 4) +
		padRight(e.MerchantId, 20) +
		strings.Repeat(" ", 528)
}

// getTAAEMV builds the EMV chip-card addenda (TAA).
func getTAAEMV(recordSeqNo int, e *AmexAcqTxnWorkEntity) string {
	txnId := padRight(e.TxnId, 15)
	txnId = strings.ReplaceAll(txnId, " ", "0")
	txnId = leftStr(txnId, 15)
	return "TAA" +
		fmt.Sprintf("%08d", recordSeqNo) +
		txnId +
		"01" + // emvFormatType
		"07" + // addenaTypeCode
		padRight(e.Emv, 256) +
		strings.Repeat(" ", 414)
}

// getTBT builds the batch total record (TBT). 605 chars.
func getTBT(recordSeqNo int, tabCount int, tabAmount float64, mappedMid string, trlBthNumber int, currencyCode string, now time.Time) string {
	tbtAmount := absInt64(tabAmount * 100)
	sign := "+"
	if tabAmount < 0 {
		sign = "-"
	}
	return "TBT" +
		fmt.Sprintf("%08d", recordSeqNo) +
		padRight(mappedMid, 15) +
		strings.Repeat(" ", 15) + // reserved1
		fmt.Sprintf("%015d", trlBthNumber) +
		now.Format("20060102") +
		leftStr(fmt.Sprintf("%08d", tabCount), 8) +
		"000" + // reserved2
		leftStr(fmt.Sprintf("%020d", tbtAmount), 20) +
		sign +
		padRight(currencyCode, 3) +
		"000" + // reserved3
		strings.Repeat("0", 20) + // reserved4
		strings.Repeat(" ", 3) + // reserved5
		strings.Repeat(" ", 8) + // tbtImageSeqNumber
		strings.Repeat(" ", 567)
}

// getTFS builds the file total / summary record (TFS). 663 chars.
func getTFS(recordSeqNo int, debitsAmount float64, debitsCount int, creditsAmount float64, creditsCount int, now time.Time) string {
	debitsTotal := absInt64(debitsAmount * 100)
	creditsTotal := absInt64(creditsAmount * 100)
	fileTotal := roundInt64((debitsAmount + creditsAmount) * 100)
	return "TFS" +
		fmt.Sprintf("%08d", recordSeqNo) +
		leftStr(fmt.Sprintf("%08d", debitsCount), 8) +
		"000" + // reserved1
		leftStr(fmt.Sprintf("%020d", debitsTotal), 20) +
		leftStr(fmt.Sprintf("%08d", creditsCount), 8) +
		"000" + // reserved2
		leftStr(fmt.Sprintf("%020d", creditsTotal), 20) +
		"000" + // reserved3
		leftStr(fmt.Sprintf("%020d", fileTotal), 20) +
		strings.Repeat(" ", 604)
}

// insertAmexIntoOutgoingSummary mirrors the other networks: groups gen_status=9
// Amex work rows by the first two characters of the proc code and writes one
// OUTGOING_SUMMARY row per group. Inserted BEFORE the 9->4 flip so the query
// finds the rows (see unionpay.go summary note).
func (s *OutgoingService) insertAmexIntoOutgoingSummary(ctx context.Context, user, insCode, intCode int, fileName string, outgoingLogSerialNumber int64) {
	ents, err := s.store.FindAmexWorkByStatus(ctx, insCode, 9)
	if err != nil {
		logOutsvc("FindAmexWorkByStatus", err)
		return
	}
	groups := map[string]*struct {
		count, txnSum, schgSum float64
	}{}
	for _, e := range ents {
		pc := e.ProcCode
		if len(pc) < 2 {
			pc = pc + "00"
		}
		key := pc[:2]
		g := groups[key]
		if g == nil {
			g = &struct {
				count, txnSum, schgSum float64
			}{}
			groups[key] = g
		}
		g.count++
		g.txnSum += e.TxnAmount
		g.schgSum += e.SurchargeAmount
	}
	now := time.Now()
	for procCode, totals := range groups {
		ots := &OutgoingSummaryEntity{
			LastUpdated:     now,
			UpdatedUser:     user,
			InstitutionCode: insCode,
			InterfaceCode:   intCode,
			OutFileDate:     dateOnly(now),
			FileId:          fileName,
			RefSerialNumber: outgoingLogSerialNumber,
			MessageTypeId:   "",
			FunctionCode:    "",
			ProcCode:        procCode,
			Count:           int(totals.count),
			Amount:          totals.txnSum,
			SurchargeAmount: totals.schgSum,
			NetAmount:       totals.txnSum + totals.schgSum,
			GeneralStatus:   3,
		}
		if err := s.store.InsertSummaries(ctx, []*OutgoingSummaryEntity{ots}); err != nil {
			logOutsvc("InsertSummaries", err)
		}
	}
}

// moveAmexWorkToData mirrors moveWorkToData: copies gen_status=4 work rows into
// AMEX_ACQ_TXN_DATA (serial preserved) and deletes them from the work table.
func (s *OutgoingService) moveAmexWorkToData(ctx context.Context, insCode, user int) {
	workEntities, err := s.store.FindAmexWorkByStatus(ctx, insCode, 4)
	if err != nil {
		logOutsvc("FindAmexWorkByStatus", err)
		return
	}
	if len(workEntities) == 0 {
		return
	}
	now := time.Now()
	dataEntities := make([]*AmexAcqTxnDataEntity, 0, len(workEntities))
	for _, we := range workEntities {
		d := *we
		d.LastUpdated = now
		dataEntities = append(dataEntities, &d)
	}
	if err := s.store.InsertAmexData(ctx, dataEntities); err != nil {
		logOutsvc("InsertAmexData", err)
		return
	}
	if err := s.store.DeleteAmexWork(ctx, workEntities); err != nil {
		logOutsvc("DeleteAmexWork", err)
	}
}

// ---- formatting helpers (fixed-width, space-padded) ----

func amexFileRefPadded(s string, size int) string {
	return leftStr(padRight(s, size), size)
}

func leftStr(s string, n int) string {
	if len(s) > n {
		return s[:n]
	}
	return s
}

func maybeSubstring(s string, start int) string {
	if len(s) <= start {
		return ""
	}
	return s[start:]
}

func absInt64(v float64) int64 {
	i := int64(v)
	if i < 0 {
		return -i
	}
	return i
}

func roundInt64(v float64) int64 {
	if v < 0 {
		return int64(v - 0.5)
	}
	return int64(v + 0.5)
}

// padRight pads s with trailing spaces up to width n (fixed-length AN fields;
// formerly defined in IRF's jaywan.go, now that Jaywan is the IRF1 verbatim port).
func padRight(s string, n int) string {
	if len(s) >= n {
		return s[:n]
	}
	return s + strings.Repeat(" ", n-len(s))
}
