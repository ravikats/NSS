package outsvc

import (
	"context"
	"encoding/json"
	"fmt"
	"math/big"
	"os"
	"path/filepath"
	"strconv"
	"strings"
	"time"

	"empay/irf/upval"
)

// UnionPay outgoing constants per the UnionPay "Technical Specifications on
// Bankcard Interoperability - Part III File Interface" (version 25.2), section
// 4 Settlement File and section 2 Basic Specifications.
const (
	unionPayIntCategory      = "UNIONPAY"
	unionPayDefaultCurrency  = "784" // AED, numeric form
	unionPayDualMessage      = "1"
	unionPayMaxTxnsPerFile   = 50000
	unionPayVersionNumber    = "00000001"
	unionPayIIN              = ""
	unionPayTC000BlockBitmap = "8000" // header/trailer: Block 0 only
	unionPayBlock01Bitmap    = "C000" // Block 0 + Block 1
	unionPayBlock012Bitmap   = "E000" // Block 0 + Block 1 + Block 2

	// Part III section 2.7: in the OUTGOING direction (Member -> GSCS) every
	// field that is only valid in the incoming direction carries its DEFAULT
	// value, not a blank -- the records are fixed length. Numeric fields are
	// 0-filled, alphabetic/ans fields are space-filled, and x+n11 amount fields
	// carry the 'D' credit indicator followed by zeros. These are the verified
	// defaults, not placeholders.
	unionPayZero12        = "000000000000" // n12 amount, default zero
	unionPayZero8         = "00000000"     // n8 conversion rate, default zero
	unionPaySpace3        = "   "          // ans3 currency, space-filled
	unionPayCreditZero11  = "D00000000000" // x+n11 credit indicator + zeros
	unionPayZero11        = "00000000000"  // n11, default zero
	unionPayZero3         = "000"          // n3, default zero
	unionPayECINonEcom    = "00"           // n2, F60.2.8 non-ecommerce
	unionPayInitAttended  = "1"            // ans1, F60.3.5 attended POS
	unionPayOrigAuthFixed = "100"          // n3, original auth fixed-amount
	unionPayOrigAuthNone  = "   "          // n3, spaces on a TC101 refund

	// Block 1 positions 76-78: abbreviation of international organization.
	// UnionPay International is CUP. This is NOT blank -- a real UAT file
	// (OFC26090851C) carries "CUP" on every transaction record.
	unionPayOrgCodeCUP = "CUP"

	// Block 2 position 23 terminal entry capability (ISO F60.2.2, an1).
	// Mandatory; the manual's default when it cannot be captured from the
	// authorization is "0" (unknown). Observed "0" in the UAT file.
	unionPayEntryCapUnknown = "0"

	// Block 2 position 24 IC card condition code (ISO F60.2.3, an1): "2" when
	// the entry mode indicates a fallback read, otherwise "0".
	unionPayICCondFallback = "2"
	unionPayICCondNormal   = "0"

	// Transaction codes. A refund (processing code 20xx) is TC101 and carries
	// bitmap C000 -- Block 2 is not present on a refund record.
	unionPayTC100 = "100" // sale / original authorization
	unionPayTC101 = "101" // refund
	// Refund processing codes start with these two digits.
	unionPayRefundPrefix = "20"
)

// ProcessUnionPayOutgoing is the Go port for the UnionPay outgoing settlement
// file (OFCYYMMDD5?C). It marks gen_status=3 UP_ACQ_TXN_WORK rows for
// outgoing, decrypts the PAN tokens, generates the settlement file, archives
// the rows and completes the POS statuses. Mirrors ProcessMercuryOutgoing.
func (s *OutgoingService) ProcessUnionPayOutgoing(ctx context.Context, insCode, user, formatCode int, insShortName string, startDate, toDate *time.Time) string {
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
	interfaces, err := s.store.FindInterfaceByCategory(ctx, unionPayIntCategory)
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

	acqBinList, err := s.store.FindAcquirerBins(ctx, insCode, "U")
	if err != nil {
		logOutsvc("FindAcquirerBins", err)
		return "Failed"
	}
	if len(acqBinList) == 0 || acqBinList[0] == nil {
		return "Acquirer bin not found"
	}
	acqBin := acqBinList[0]
	// Fallback only: the header IIN normally comes from the transactions.
	acqBinIIN := unionPayIIN
	if acqBin.McIcaNo != nil {
		acqBinIIN = *acqBin.McIcaNo
	}

	var txnList []*UnionPayAcqTxnWorkEntity
	if startDate == nil {
		txnList, err = s.store.FindUnionPayWorkLessThanEqual(ctx, insCode, intCode, 3, *toDate)
	} else {
		txnList, err = s.store.FindUnionPayWorkBetween(ctx, insCode, intCode, 3, *startDate, *toDate)
	}
	if err != nil {
		logOutsvc("FindUnionPayWork", err)
		return "Failed"
	}
	if len(txnList) == 0 {
		return "No data found"
	}

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
	for _, e := range txnList {
		e.LastUpdated = now
		e.UpdatedUser = user
		e.GenStatus = 9
		e.FileID = ""
	}
	if err := s.store.UpdateUnionPayWorkStatuses(ctx, txnList); err != nil {
		logOutsvc("UpdateUnionPayWorkStatuses", err)
		return "Failed"
	}

	if response == nil {
		for _, e := range txnList {
			e.GenStatus = 7
		}
		if err := s.store.UpdateUnionPayWorkStatuses(ctx, txnList); err != nil {
			logOutsvc("UpdateUnionPayWorkStatuses", err)
		}
		return "Outgoing Failed"
	}

	for _, fileTxns := range splitUnionPayTransactions(txnList) {
		sequence := s.updateAndGetUnionPayFileSequence(ctx, acqBin, now)
		fileName := unionPayFileName(now, sequence)
		outgoingLogSerialNumber := s.inserOutFileLog(ctx, user, insCode, intCode, forCode)
		if outgoingLogSerialNumber == 0 {
			return "Failed"
		}

		fileId := s.writeUnionPayFile(ctx, fileTxns, insShortName, fileName, acqBinIIN, response)
		if fileId == "" {
			s.updateOutFilelog(ctx, insCode, outgoingLogSerialNumber, fileName, nil)
			return "Outgoing Failed"
		}

		// Validate the generated file BEFORE any rows move and before the file
		// log is marked complete, so a malformed file never reaches the rows or
		// the settlement cycle. Report-only by default; UNIONPAY_VALIDATION_STRICT
		// aborts (work stays 9, file log -> failure).
		if rep := s.validateUnionPayFile(fileName, insShortName); rep != nil && !rep.OK() {
			if s.cfg.UnionPayValidationStrict {
				s.updateOutFilelog(ctx, insCode, outgoingLogSerialNumber, fileName, nil)
				return "Outgoing Failed"
			}
			fmt.Fprintf(os.Stderr, "outsvc: UnionPay validation: %s: %d issue(s), file kept\n",
				fileName, len(rep.Issues))
		}

		fid := fileId
		s.updateOutFilelog(ctx, insCode, outgoingLogSerialNumber, fileName, &fid)

		// Insert the summary while the file rows are still gen_status=9 so the
		// insertIntoOutgoingSummary grouping query finds them (see the Mercury
		// port note: Java flipped rows 9->4 first, producing empty summaries).
		s.insertUnionPayIntoOutgoingSummary(ctx, user, insCode, intCode, fileName, outgoingLogSerialNumber)

		now := s.now()
		for _, e := range fileTxns {
			e.LastUpdated = now
			e.UpdatedUser = user
			e.GenStatus = 4
			e.FileID = fileName
		}
		if err := s.store.UpdateUnionPayWorkStatuses(ctx, fileTxns); err != nil {
			logOutsvc("UpdateUnionPayWorkStatuses", err)
			return "Failed"
		}
	}

	if err := s.store.CompleteUnionPayPosStatus(ctx, insCode); err != nil {
		logOutsvc("CompleteUnionPayPosStatus", err)
		return "Failed"
	}
	s.moveUnionPayWorkToData(ctx, insCode, user)
	return "Success"
}

func splitUnionPayTransactions(txnList []*UnionPayAcqTxnWorkEntity) [][]*UnionPayAcqTxnWorkEntity {
	var result [][]*UnionPayAcqTxnWorkEntity
	for i := 0; i < len(txnList); i += unionPayMaxTxnsPerFile {
		end := i + unionPayMaxTxnsPerFile
		if end > len(txnList) {
			end = len(txnList)
		}
		result = append(result, txnList[i:end])
	}
	return result
}

// updateAndGetUnionPayFileSequence reuses the ACQUIRER_BINS file sequence
// (same row used by the other networks) for the UnionPay file batch number.
func (s *OutgoingService) updateAndGetUnionPayFileSequence(ctx context.Context, acqBin *AcquirerBinsEntity, now time.Time) int {
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

// unionPayFileName renders the outgoing settlement file name OFCYYMMDD5?C
// (Part III section 4.1): O = outgoing, F = cross-border, C = dual-message
// settlement, YYMMDD = file date, '5' = member digit, '?' = batch number.
func unionPayFileName(now time.Time, sequence int) string {
	return "OFC" + now.Format("060102") + "5" + strconv.Itoa(sequence%10) + "C"
}

// insertUnionPayIntoOutgoingSummary mirrors the other networks: groups
// gen_status=9 work rows by txn type and writes one OUTGOING_SUMMARY row.
func (s *OutgoingService) insertUnionPayIntoOutgoingSummary(ctx context.Context, user, insCode, intCode int, fileName string, outgoingLogSerialNumber int64) {
	ents, err := s.store.FindUnionPayWorkByStatus(ctx, insCode, 9)
	if err != nil {
		logOutsvc("FindUnionPayWorkByStatus", err)
		return
	}
	groups := map[string]*struct {
		count                int
		totalTxnAmount       float64
		totalSurchargeAmount float64
	}{}
	for _, e := range ents {
		g := groups[e.TxnType]
		if g == nil {
			g = &struct {
				count                int
				totalTxnAmount       float64
				totalSurchargeAmount float64
			}{}
			groups[e.TxnType] = g
		}
		g.count++
		g.totalTxnAmount += e.TxnAmount
		g.totalSurchargeAmount += e.SurchargeAmount
	}
	now := time.Now()
	for txnCode, totals := range groups {
		totalNetAmount := totals.totalTxnAmount + totals.totalSurchargeAmount
		ots := &OutgoingSummaryEntity{
			LastUpdated:     now,
			UpdatedUser:     user,
			InstitutionCode: insCode,
			InterfaceCode:   intCode,
			OutFileDate:     dateOnly(now),
			FileId:          fileName,
			RefSerialNumber: outgoingLogSerialNumber,
			MessageTypeId:   summaryMessageType(txnCode),
			FunctionCode:    "1",
			ProcCode:        "",
			Count:           totals.count,
			Amount:          totals.totalTxnAmount,
			SurchargeAmount: totals.totalSurchargeAmount,
			NetAmount:       totalNetAmount,
			GeneralStatus:   3,
		}
		if err := s.store.InsertSummaries(ctx, []*OutgoingSummaryEntity{ots}); err != nil {
			logOutsvc("InsertSummaries", err)
		}
	}
}

// moveUnionPayWorkToData mirrors moveWorkToData: copies gen_status=4 work rows
// into UP_ACQ_TXN_DATA (serial preserved) and deletes them from the work table.
func (s *OutgoingService) moveUnionPayWorkToData(ctx context.Context, insCode, user int) {
	workEntities, err := s.store.FindUnionPayWorkByStatus(ctx, insCode, 4)
	if err != nil {
		logOutsvc("FindUnionPayWorkByStatus", err)
		return
	}
	if len(workEntities) == 0 {
		return
	}
	now := time.Now()
	dataEntities := make([]*UnionPayAcqTxnDataEntity, 0, len(workEntities))
	for _, we := range workEntities {
		d := *we
		d.LastUpdated = now
		dataEntities = append(dataEntities, &d)
	}
	if err := s.store.InsertUnionPayData(ctx, dataEntities); err != nil {
		logOutsvc("InsertUnionPayData", err)
		return
	}
	if err := s.store.DeleteUnionPayWork(ctx, workEntities); err != nil {
		logOutsvc("DeleteUnionPayWork", err)
	}
}

// validateUnionPayFile validates a generated settlement file and records the
// outcome for the inquiry UI (GET /outgoing/v1/validations). Returns nil when
// the file cannot be read, so a missing file is not double-reported.
func (s *OutgoingService) validateUnionPayFile(fileName, insShortName string) *upval.Report {
	path := filepath.Join(s.cfg.ReconOutDir, fileName)
	rep, err := upval.ValidateFile(path)
	if err != nil {
		logOutsvc("validateUnionPayFile", err)
		return nil
	}
	res := &ValidationResult{
		Network:     "UNIONPAY",
		File:        fileName,
		Records:     rep.Records,
		TxnCount:    rep.Transactions,
		OK:          rep.OK(),
		Errors:      rep.Issues,
		Summary:     rep.Summary(),
		ValidatedAt: s.now(),
	}
	s.recordValidation(res)
	s.writeUnionPayValidationReport(rep)
	return rep
}

// writeUnionPayValidationReport writes the machine-readable issue list next to
// the settlement file (override with UNIONPAY_VALIDATION_REPORT_DIR).
func (s *OutgoingService) writeUnionPayValidationReport(rep *upval.Report) {
	dir := s.cfg.UnionPayValidationReportDir
	if dir == "" {
		dir = filepath.Join(s.cfg.ReconOutDir, "unionpay_validation")
	}
	if err := os.MkdirAll(dir, 0o755); err != nil {
		logOutsvc("writeUnionPayValidationReport", err)
		return
	}
	jsonPath := filepath.Join(dir, rep.File+".validation.json")
	if data, err := json.MarshalIndent(rep, "", "  "); err == nil {
		if err := os.WriteFile(jsonPath, data, 0o644); err != nil {
			logOutsvc("writeUnionPayValidationReport", err)
		}
	}
}

// writeUnionPayFile builds the sequential file (TC000 + TC100/101/102 +
// TC001) and writes it to RECON_OUT_{insShortName}/{fileName} with CRLF
// terminators; returns the file name or "" on failure.
func (s *OutgoingService) writeUnionPayFile(ctx context.Context, txnList []*UnionPayAcqTxnWorkEntity, insShortName, fileName, acqBinIIN string, response map[string]string) string {
	if len(txnList) == 0 {
		return ""
	}
	fd := fractionalDigits(s.cfg.CurrencyCodeKafka)
	mult := new(big.Rat).SetInt64(pow10int(fd))
	now := s.now()

	// The TC000 header identifies the submitting acquirer. Take it from the
	// transactions' own acquiring IIN (ISO Field 32) rather than from
	// ACQUIRER_BINS.ACQ_MC_ICA_NO, which is only VARCHAR2(6) and so cannot
	// hold a real 8-digit UnionPay acquirer IIN: a UAT file carries 24160784
	// while that column holds 034540 (Mercury's). Falls back to the bin, then
	// to empty.
	iin := unionPayHeaderIIN(txnList)
	if iin == "" && acqBinIIN != "" {
		iin = acqBinIIN
	}
	lines := []string{unionPayTC000(iin, now, s.cfg.UnionPayVersionTag)}
	for _, txn := range txnList {
		lines = append(lines, unionPayTxnRecord(txn, response, mult))
	}
	lines = append(lines, unionPayTC001(len(lines)+1))

	return s.writeUnionPayLinesToFile(lines, insShortName, fileName)
}

func (s *OutgoingService) writeUnionPayLinesToFile(lines []string, insShortName, fileName string) string {
	if len(lines) == 0 {
		return ""
	}
	path := filepath.Join(s.cfg.ReconOutDir, fileName)
	// Records are separated by CRLF with NO trailing CRLF after the last
	// record. The reference joins without a trailing newline, and a real UAT
	// file (OFC26090851C, 3996 bytes) ends on the trailer's padding rather
	// than on a line break; emitting one adds 2 stray bytes.
	var sb strings.Builder
	for i, line := range lines {
		if i > 0 {
			sb.WriteString("\r\n")
		}
		sb.WriteString(line)
	}
	if err := os.WriteFile(path, []byte(sb.String()), 0o644); err != nil {
		logOutsvc("writeUnionPayLinesToFile", err)
		return ""
	}
	return fileName
}

// ---- record builders (Part III sections 2.5 and 4.2) ----

// unionPayHeaderIIN returns the acquiring IIN shared by the batch, taken from
// UPT_ACQ_INST_ID_CODE (ISO Field 32). Returns "" when no row carries one.
func unionPayHeaderIIN(txnList []*UnionPayAcqTxnWorkEntity) string {
	for _, txn := range txnList {
		if txn == nil {
			continue
		}
		if v := strings.TrimSpace(txn.AcqinstIdCode); v != "" {
			return v
		}
	}
	return ""
}

// unionPayTC000 builds the file header record (Block 0 only, 46 chars).
func unionPayTC000(iin string, batchDate time.Time, versionTag string) string {
	if versionTag == "" {
		versionTag = "TEST"
	}
	return "000" + unionPayTC000BlockBitmap +
		unionPayPad(iin, 11) +
		batchDate.Format("20060102") +
		unionPayPad("", 8) +
		unionPayPad(versionTag, 4) +
		unionPayVersionNumber
}

// unionPayTC001 builds the file trailer record (Block 0 only, 49 chars).
func unionPayTC001(totalRecords int) string {
	return "001" + unionPayTC000BlockBitmap +
		fmt.Sprintf("%010d", totalRecords) +
		unionPayPad("", 16) + // MAK
		unionPayPad("", 16) // MAC
}

// unionPayTxnRecord assembles the TC100 transaction record: Block 0 always,
// Block 1 (exchange-rate features) always, Block 2 (IC card data) only for
// chip transactions.
//
// A refund (processing code 20xx) is TC101 and carries bitmap C000 -- Block 2
// is not present on a refund record, so a refunded chip txn emits 387 chars,
// not 681.
func unionPayTxnRecord(txn *UnionPayAcqTxnWorkEntity, response map[string]string, mult *big.Rat) string {
	pan := response[txn.EncryptedCardNumber]
	tc := unionPayTransactionCode(txn.TxnType)
	bitmap := unionPayBlock01Bitmap
	if tc == unionPayTC101 {
		// Refund: Block 0 + Block 1 only.
		return unionPayBlock0(txn, pan, tc, bitmap, mult) + unionPayBlock1(txn, mult)
	}
	if unionPayIsChipTxn(txn) {
		bitmap = unionPayBlock012Bitmap
	}
	rec := unionPayBlock0(txn, pan, tc, bitmap, mult)
	rec += unionPayBlock1(txn, mult)
	if unionPayIsChipTxn(txn) {
		rec += unionPayBlock2(txn, mult)
	}
	return rec
}

// unionPayTransactionCode maps the processing code to the UnionPay transaction
// code: a refund (20xx) is 101, everything else is 100.
func unionPayTransactionCode(procCode string) string {
	if strings.HasPrefix(procCode, unionPayRefundPrefix) {
		return unionPayTC101
	}
	return unionPayTC100
}

// unionPayIsRefund reports whether the processing code denotes a refund.
func unionPayIsRefund(procCode string) bool {
	return strings.HasPrefix(procCode, unionPayRefundPrefix)
}

// unionPayBlock0 builds the basic settlement information (269 chars).
func unionPayBlock0(txn *UnionPayAcqTxnWorkEntity, pan, tc, bitmap string, mult *big.Rat) string {
	// Block 0 42-51 is "Transmission Date Time" = ISO Field 7, NOT the switch's
	// local time. A real UAT file proves the distinction: it carries 0825070326
	// while the transaction's own local_time is 110326 -- a 4 hour offset. The
	// two must not be conflated or every record is filed under the wrong hour.
	mmddhhmmss := unionPayPad(txn.TransDateTime, 10)
	authDate := "    "
	if strings.TrimSpace(mmddhhmmss) == "" {
		if txn.LocalDateTime != nil {
			mmddhhmmss = txn.LocalDateTime.Format("0102150405")
		}
	}
	if txn.LocalDateTime != nil {
		authDate = txn.LocalDateTime.Format("0102")
	}
	currency := unionPayPad(txn.TxnCurCode, 3)
	if strings.TrimSpace(currency) == "" {
		currency = unionPayDefaultCurrency
	}
	channel := unionPayPad(txn.TxnInitiatingChannel, 2)
	if strings.TrimSpace(channel) == "" {
		channel = "03" // POS
	}
	origTxnInfo := strings.Repeat("0", 23)
	if unionPayIsRefund(txn.TxnType) {
		origTxnInfo = unionPayOriginalTxnInfo(txn)
	}
	return tc + bitmap +
		unionPayPad(pan, 19) +
		unionPayMinor12(txn.TxnAmount, mult) +
		currency +
		mmddhhmmss +
		unionPayStan(txn.StanNumber) +
		unionPayPad(txn.ApprovalCode, 6) +
		authDate +
		unionPayPad(txn.Rrn, 12) +
		unionPayPad(txn.AcqinstIdCode, 11) +
		unionPayPad(txn.FwdInstIdCode, 11) +
		unionPayPad(txn.Mcc, 4) +
		unionPayPad(txn.TerminalId, 8) +
		unionPayPad(txn.MerchantId, 15) +
		unionPayMerchantName(txn) +
		origTxnInfo +
		"0000" + // message reason code
		unionPayDualMessage +
		"000000000" + // GSCS serial number (filled by Member with zeros)
		unionPayPad(txn.ReceivingInstIdCode, 11) +
		unionPayPad(txn.OrgInstIdCode, 11) +
		"0" + // identifier of GSCS notice
		channel +
		unionPayFeatureIndicator(txn) +
		"   " + // transaction scenario indicator
		"     " + // reserved
		unionPayOtherInformation(txn)
}

// unionPayOriginalTxnInfo builds Block 0 positions 169-191 (n23) for a refund:
// the ORIGINAL transaction code (n3), the original's date/time (n10), the
// original's STAN (n6) and the original's settlement date (n4).
//
// These all come from the original sale, not from the refund. A real UAT file
// (OFC26090851C) shows this: refund rrn 623707911124 carries
// "100" + "0825071821" + "343027" + "0825", i.e. the original sale's
// transmission date-time, its STAN and its settlement date. Deriving them from
// the refund's own timestamp would file the refund against the wrong day.
//
// A refund with no original is all zeros, matching the reference's behaviour
// when the original cannot be located.
func unionPayOriginalTxnInfo(txn *UnionPayAcqTxnWorkEntity) string {
	zeros := strings.Repeat("0", 23)
	if strings.TrimSpace(txn.OriginalRRN) == "" {
		return zeros
	}
	dateTime := unionPayPad(txn.OrigTxnDatetime, 10)
	if strings.TrimSpace(dateTime) == "" {
		// The original sale was not located at split time. The reference
		// implementation logs a warning and emits zeros in this case; it does
		// NOT substitute the refund's own values, which would file the refund
		// against the wrong day.
		return zeros
	}
	code := unionPayPad(txn.OrigTxnCode, 3)
	if strings.TrimSpace(code) == "" {
		code = unionPayTC100
	}
	stan := unionPayPad(txn.OrigStan, 6)
	if strings.TrimSpace(stan) == "" {
		// The STAN is the last 6 digits of the original RRN.
		stan = unionPayPadLast(txn.OriginalRRN, 6)
	}
	settle := unionPayPad(txn.OrigSettleDate, 4)
	if strings.TrimSpace(settle) == "" {
		settle = "0000"
	}
	return code + dateTime + stan + settle
}

// unionPayPadLast left-justifies v to width n, keeping the LAST n characters
// when v is longer (used for the STAN suffix of a 12-digit RRN).
func unionPayPadLast(v string, n int) string {
	v = strings.TrimSpace(v)
	if len(v) > n {
		return v[len(v)-n:]
	}
	return v + strings.Repeat("0", n-len(v))
}

// unionPayFeatureIndicator builds Block 0 position 231: a space on a TC100
// sale, 'R' on a TC101 refund.
func unionPayFeatureIndicator(txn *UnionPayAcqTxnWorkEntity) string {
	if unionPayIsRefund(txn.TxnType) {
		return "R"
	}
	return " "
}

// unionPayMerchantName builds Block 0 positions 129-168 (ans40, ISO Field 43):
// name left-justified in 25, city in 12, alpha-2 country in 3.
//
// The value must EQUAL Field 43 of the original authorization, which carries
// the alpha-2 country ("AE"), even though the payload/terminal config holds the
// alpha-3 form ("ARE"). Do NOT truncate the 40-char field naively -- slicing an
// alpha-3 suffix at 39 chars yields "AR".
func unionPayMerchantName(txn *UnionPayAcqTxnWorkEntity) string {
	name := unionPayClamp(txn.MeName, 25)
	city := unionPayClamp(txn.MeCity, 12)
	// Left-justified in 3 so the total is exactly 40 even for an alpha-2 code.
	country := unionPayClamp(unionPayAlpha2(txn.MeCountry), 3)
	return name + city + country
}

// unionPayClamp left-justifies and space-pads v to width n, truncating when
// longer -- the Python reference uses f"{v:<n}"[:n].
func unionPayClamp(v string, n int) string {
	if len(v) >= n {
		return v[:n]
	}
	return v + strings.Repeat(" ", n-len(v))
}

// unionPayAlpha3ToAlpha2 maps an ISO alpha-3 country code to the alpha-2 form
// used in Field 43.3. Unknown alpha-3 codes map to "" so the sub-field stays
// blank rather than silently carrying a wrong country.
var unionPayAlpha3ToAlpha2 = map[string]string{
	"ARE": "AE", "USA": "US", "GBR": "GB", "CHN": "CN", "HKG": "HK", "MAC": "MO",
	"SGP": "SG", "JPN": "JP", "KOR": "KR", "IND": "IN", "THA": "TH", "MYS": "MY",
	"IDN": "ID", "PHL": "PH", "VNM": "VN", "AUS": "AU", "NZL": "NZ", "CAN": "CA",
	"DEU": "DE", "FRA": "FR", "ITA": "IT", "ESP": "ES", "NLD": "NL", "CHE": "CH",
	"SAU": "SA", "QAT": "QA", "KWT": "KW", "BHR": "BH", "OMN": "OM", "EGY": "EG",
	"JOR": "JO", "LBN": "LB", "TUR": "TR", "ZAF": "ZA", "NGA": "NG", "KEN": "KE",
	"BRA": "BR", "MEX": "MX", "ARG": "AR", "RUS": "RU", "UKR": "UA", "POL": "PL",
	"SWE": "SE", "NOR": "NO", "DNK": "DK", "FIN": "FI", "PRT": "PT", "IRL": "IE",
}

// unionPayAlpha2 normalises a country code to alpha-2.
func unionPayAlpha2(code string) string {
	c := strings.ToUpper(strings.TrimSpace(code))
	switch len(c) {
	case 3:
		return unionPayAlpha3ToAlpha2[c]
	case 2:
		return c
	default:
		return ""
	}
}

// unionPayOtherInformation builds Block 0 positions 240-269 (30 chars,
// Part III Table 36 Note a). Each sub-field below is annotated with its
// position. Sub-fields valid only in the INCOMING direction are default-filled
// per section 2.7 rather than blanked.
func unionPayOtherInformation(txn *UnionPayAcqTxnWorkEntity) string {
	// 243-244 n2 POS condition code (ISO Field 25).
	pscc := unionPayPad(txn.PosConditionCode, 2)
	if strings.TrimSpace(pscc) == "" {
		pscc = "00"
	}
	// 245-247 n3 merchant country (ISO Field 19) -- NUMERIC, e.g. 784. This is
	// the acquirer's country code, not the alpha merchant country in MeCountry.
	country := unionPayPad(txn.AcqInstCountryCode, 3)
	if strings.TrimSpace(country) == "" {
		country = unionPayDefaultCurrency
	}
	// 249-251 n3 original authorization type: spaces on a refund, otherwise
	// "100" (original authorization, fixed amount).
	origAuthType := unionPayOrigAuthFixed
	if unionPayIsRefund(txn.TxnType) {
		origAuthType = unionPayOrigAuthNone
	}
	// 253-254 n2 pricing scheme -- the manual requires the default "00".
	pricing := unionPayPad(txn.PricingSchemeCode, 2)
	if strings.TrimSpace(pricing) == "" {
		pricing = "00"
	}
	// 258-259 n2 ECI (ISO F60.2.8): "00" for a non-ecommerce transaction.
	eci := unionPayPad(txn.ECI, 2)
	if strings.TrimSpace(eci) == "" {
		eci = unionPayECINonEcom
	}
	value := "  " + // 240-241 n2 installment terms   (incoming only)
		" " + // 242 ans1 stand-in authorization   (incoming only)
		pscc +
		country +
		unionPayInitAttended + // 248 ans1 initiation method (attended POS)
		origAuthType +
		" " + // 252 ans1 card level              (incoming only)
		pricing +
		unionPaySpace3 + // 255-257 n3 special currency   (incoming only)
		eci +
		"  " + // 260-261 n2 card product           (incoming only)
		"  " + // 262-263 n2 account attribute      (incoming only)
		" " + // 264 ans1 UPI indicator             (incoming only)
		"  " + // 265-266 n2 B2B type               (incoming only)
		" " + // 267 ans1 B2B medium                (incoming only)
		"  " // 268-269 n2 special pricing         (incoming only)
	if len(value) != 30 {
		// Defensive: the field layout above is fixed at 30 chars.
		return unionPayPad(value, 30)
	}
	return value
}

// unionPayBlock1 builds the exchange-rate features information (118 chars).
//
// Every field from position 7 onward is incoming-direction only, so per Part
// III section 2.7 each carries its default rather than a real value: the
// Member does not settle, GSCS does, so repeating the transaction amount and
// inventing conversion rates here would be wrong.
func unionPayBlock1(txn *UnionPayAcqTxnWorkEntity, mult *big.Rat) string {
	return unionPayPad(txn.PosEntryMode, 3) +
		"0" + // floor limit identifier (online authorization)
		"0 " + // type of payment service requested
		unionPayZero12 + // 7-18  amount, settlement
		unionPaySpace3 + // 19-21 currency code, settlement
		unionPayZero8 + // 22-29 conversion rate, settlement
		unionPayZero12 + // 30-41 amount, cardholder billing
		unionPaySpace3 + // 42-44 currency code, cardholder billing
		unionPayZero8 + // 45-52 conversion rate, cardholder billing
		unionPayCreditZero11 + // 53-64 net fee amount (x+n11)
		unionPayZero3 + // 65-67 IRF billing currency
		unionPayZero8 + // 68-75 exchange rate, RF billing -> settlement
		unionPayOrgCodeCUP + // 76-78 abbreviation of international organization
		" " + // 79    Mainland China transaction indicator
		unionPayCreditZero11 + // 80-91 amount, transaction fee (x+n11)
		unionPayPad("", 20) + // 92-111 QRC voucher number
		unionPayPad("", 7) // 112-118 reserved
}

// unionPayBlock2 builds the IC card characteristic information (294 chars),
// only for chip (UICS debit/credit) transactions.
func unionPayBlock2(txn *UnionPayAcqTxnWorkEntity, mult *big.Rat) string {
	panSeq := unionPayPad(txn.PanSequenceNumber, 3)
	if strings.TrimSpace(panSeq) == "" {
		panSeq = unionPayPad(txn.CardSeqNumber, 3)
	}
	currency := unionPayPad(txn.ChipCurCode, 3)
	if strings.TrimSpace(currency) == "" {
		currency = unionPayDefaultCurrency
	}
	return unionPayPad(unionPayUpper(txn.AppCryptogram), 16) +
		unionPayPad(txn.PosEntryMode, 3) +
		panSeq +
		unionPayEntryCapability(txn) +
		unionPayICCardCondition(txn.PosEntryMode) +
		unionPayPad(unionPayUpper(txn.ChipTrlCapabilities), 6) +
		unionPayPad(unionPayUpper(txn.TrlVerResult), 10) +
		unionPayPad(unionPayUpper(txn.UpblNumber), 8) +
		unionPayPad(txn.IfdSerNumber, 8) +
		unionPayPad(unionPayUpper(txn.IssAppData), 64) +
		unionPayPad(txn.AppTxnCounter, 4) +
		unionPayPad(unionPayUpper(txn.AppICProfile), 4) +
		unionPayPad(txn.ChipTxnDate, 6) +
		unionPayPad(txn.TrlConCode, 3) +
		unionPayPad(txn.IssAuthData, 42) +
		"00" + // authorization response code (100/101/102)
		unionPayTransactionCategory(txn.TxnType) +
		unionPayMinor12(txn.CryptAmount, mult) + // authorized amount (Tag9F02)
		currency + // currency code, transaction (Tag5F2A)
		unionPayPad(txn.CryptInfoData, 2) +
		unionPayMinor12(txn.CashBackAmount, mult) + // other amount (Tag9F03)
		unionPayPad(txn.CvmResult, 6) +
		unionPayPad(txn.ChipTrlType, 2) +
		unionPayPad(unionPayUpper(txn.DedicatedFileName), 32) +
		unionPayPad(txn.TrlAppVerNumber, 4) +
		unionPayPad("", 8) + // transaction serial counter
		unionPayPad("", 30) // reserved
}

// ---- predicates & formatting helpers ----

// unionPayIsChipTxn reports whether the record carries Block 2 IC card data.
func unionPayIsChipTxn(txn *UnionPayAcqTxnWorkEntity) bool {
	// PAN-entry-mode prefixes that carry Block 2: contact (05), contactless
	// (07) and QRC-read (98). Magnetic-stripe records do not.
	for _, pfx := range unionPayChipPANEntryPrefixes {
		if strings.HasPrefix(txn.PosEntryMode, pfx) {
			return true
		}
	}
	return false
}

// unionPayChipPANEntryPrefixes are the DE22 PAN-entry-mode prefixes that carry
// Block 2 IC card data.
var unionPayChipPANEntryPrefixes = []string{"05", "07", "98"}

// unionPayTransactionCategory builds Block 2 positions 182-183: the first two
// digits of the processing code (ISO Field 3, EMV Tag 9C). It is NOT the chip
// transaction type.
func unionPayTransactionCategory(procCode string) string {
	if len(procCode) < 2 {
		return unionPayZero3[:2]
	}
	return procCode[:2]
}

// unionPayEntryCapability builds Block 2 position 23 from ISO F60.2.2,
// defaulting to "0" (unknown) when the switch did not supply it.
func unionPayEntryCapability(txn *UnionPayAcqTxnWorkEntity) string {
	v := strings.TrimSpace(txn.CardInputCapability)
	if v == "" {
		return unionPayEntryCapUnknown
	}
	return unionPayPad(v, 1)
}

// unionPayICCardCondition builds Block 2 position 24 from ISO F60.2.3: "2" when
// the entry mode indicates a fallback read, otherwise "0".
func unionPayICCardCondition(posEntryMode string) string {
	if strings.TrimSpace(posEntryMode) == "90" {
		return unionPayICCondFallback
	}
	return unionPayICCondNormal
}

// unionPayUpper uppercases a value when it is pure hex, so EMV-sourced fields
// land in the file in the uppercase form UnionPay uses (a real UAT file carries
// "7C00" and "A000000333010102", not lowercase). Non-hex values (dates, STANs,
// text) are returned untouched.
func unionPayUpper(v string) string {
	if v == "" {
		return v
	}
	for i := 0; i < len(v); i++ {
		c := v[i]
		isHex := (c >= '0' && c <= '9') || (c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F')
		if !isHex {
			return v
		}
	}
	return strings.ToUpper(v)
}

// unionPayMinor12 renders value*multiplier (minor units) as a 12-digit
// zero-padded string (Part III n12 format).
func unionPayMinor12(v float64, mult *big.Rat) string {
	amt := mercuryAmount(v, mult)
	return fmt.Sprintf("%012d", mercuryRatHalfUpInt(amt))
}

// unionPayStan renders the system trace audit number as n6, zero-padded.
func unionPayStan(stan string) string {
	if n, err := strconv.Atoi(strings.TrimSpace(stan)); err == nil {
		return fmt.Sprintf("%06d", n)
	}
	return "000000"
}

// unionPayPad truncates s to size and right-pads with spaces (left-justified).
func unionPayPad(s string, size int) string {
	if len(s) > size {
		return s[:size]
	}
	return s + strings.Repeat(" ", size-len(s))
}
