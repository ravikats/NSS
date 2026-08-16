package outsvc

import (
	"context"
	"fmt"
	"math/big"
	"os"
	"path/filepath"
	"strconv"
	"strings"
	"time"
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
	unionPayConversion1To1   = "20000100" // decimals=2, rate 1.00 (n8)
	unionPayIRFRate          = "30001000" // fixed RF->settlement rate (n8)
	unionPayIIN              = ""
	unionPayTC000BlockBitmap = "8000" // header/trailer: Block 0 only
	unionPayBlock01Bitmap    = "C000" // Block 0 + Block 1
	unionPayBlock012Bitmap   = "E000" // Block 0 + Block 1 + Block 2
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
	iin := unionPayIIN
	if acqBin.McIcaNo != nil {
		iin = *acqBin.McIcaNo
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

		fileId := s.writeUnionPayFile(ctx, fileTxns, insShortName, fileName, iin, response)
		if fileId == "" {
			s.updateOutFilelog(ctx, insCode, outgoingLogSerialNumber, fileName, nil)
			return "Outgoing Failed"
		}

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
		fid := fileId
		s.updateOutFilelog(ctx, insCode, outgoingLogSerialNumber, fileName, &fid)
		s.insertUnionPayIntoOutgoingSummary(ctx, user, insCode, intCode, fileName, outgoingLogSerialNumber)
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
			MessageTypeId:   txnCode,
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

// writeUnionPayFile builds the sequential file (TC000 + TC100/101/102 +
// TC001) and writes it to RECON_OUT_{insShortName}/{fileName} with CRLF
// terminators; returns the file name or "" on failure.
func (s *OutgoingService) writeUnionPayFile(ctx context.Context, txnList []*UnionPayAcqTxnWorkEntity, insShortName, fileName, iin string, response map[string]string) string {
	if len(txnList) == 0 {
		return ""
	}
	fd := fractionalDigits(s.cfg.CurrencyCodeKafka)
	mult := new(big.Rat).SetInt64(pow10int(fd))
	now := s.now()

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
	var sb strings.Builder
	for _, line := range lines {
		sb.WriteString(line)
		sb.WriteString("\r\n")
	}
	if err := os.WriteFile(path, []byte(sb.String()), 0o644); err != nil {
		logOutsvc("writeUnionPayLinesToFile", err)
		return ""
	}
	return fileName
}

// ---- record builders (Part III sections 2.5 and 4.2) ----

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
func unionPayTxnRecord(txn *UnionPayAcqTxnWorkEntity, response map[string]string, mult *big.Rat) string {
	pan := response[txn.EncryptedCardNumber]
	chip := unionPayIsChipTxn(txn)
	bitmap := unionPayBlock01Bitmap
	if chip {
		bitmap = unionPayBlock012Bitmap
	}
	rec := unionPayBlock0(txn, pan, bitmap, mult)
	rec += unionPayBlock1(txn, mult)
	if chip {
		rec += unionPayBlock2(txn, mult)
	}
	return rec
}

// unionPayBlock0 builds the basic settlement information (269 chars).
func unionPayBlock0(txn *UnionPayAcqTxnWorkEntity, pan, bitmap string, mult *big.Rat) string {
	mmddhhmmss := "0000000000"
	authDate := "    "
	if txn.LocalDateTime != nil {
		mmddhhmmss = txn.LocalDateTime.Format("0102150405")
		authDate = txn.LocalDateTime.Format("0102")
	}
	currency := unionPayPad(txn.TxnCurCode, 3)
	if strings.TrimSpace(currency) == "" {
		currency = unionPayDefaultCurrency
	}
	channel := unionPayPad(txn.TxnInitiatingChannel, 2)
	if strings.TrimSpace(channel) == "" {
		channel = "00"
	}
	pricing := unionPayPad(txn.PricingSchemeCode, 2)
	if strings.TrimSpace(pricing) == "" {
		pricing = "00"
	}
	// Positions 240-269 "other information": installment(2) + stand-in(1) +
	// POS condition(2) + merchant country(3) + initiation method(1) +
	// original auth type(3) + card level(1) + pricing scheme(2) + reserved(15).
	otherInfo := "00" + " " +
		unionPayPad(txn.PosConditionCode, 2) +
		unionPayPad(txn.MeCountry, 3) +
		" " + "100" + " " +
		pricing +
		unionPayPad("", 15)

	return "100" + bitmap +
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
		unionPayPad(txn.MeName, 40) +
		strings.Repeat("0", 23) + // original transaction information
		"0000" + // message reason code
		unionPayDualMessage +
		"000000000" + // GSCS serial number (filled by Member with zeros)
		unionPayPad(txn.ReceivingInstIdCode, 11) +
		unionPayPad(txn.OrgInstIdCode, 11) +
		"0" + // identifier of GSCS notice
		channel +
		" " + // identifier of transaction features
		"   " + // transaction scenario indicator
		"     " + // reserved
		otherInfo
}

// unionPayBlock1 builds the exchange-rate features information (118 chars).
func unionPayBlock1(txn *UnionPayAcqTxnWorkEntity, mult *big.Rat) string {
	currency := unionPayPad(txn.TxnCurCode, 3)
	if strings.TrimSpace(currency) == "" {
		currency = unionPayDefaultCurrency
	}
	return unionPayPad(txn.PosEntryMode, 3) +
		"0" + // floor limit identifier (online authorization)
		"0 " + // type of payment service requested
		unionPayMinor12(txn.TxnAmount, mult) + // amount, settlement
		currency + // currency code, settlement
		unionPayConversion1To1 + // conversion rate, settlement
		unionPayMinor12(txn.TxnAmount, mult) + // amount, cardholder billing
		currency + // currency code, cardholder billing
		unionPayConversion1To1 + // conversion rate, cardholder billing
		" 00000000000" + // net fee amount (x+n11)
		"000" + // IRF billing currency
		unionPayIRFRate + // exchange rate RF billing -> settlement
		"   " + // abbreviation of international organization
		" " + // Mainland China transaction indicator
		" 00000000000" + // amount, transaction fee (x+n11)
		unionPayPad("", 20) + // QRC voucher number
		unionPayPad("", 7) // reserved
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
	return unionPayPad(txn.AppCryptogram, 16) +
		unionPayPad(txn.PosEntryMode, 3) +
		panSeq +
		unionPayPad(txn.CardInputCapability, 1) +
		" " + // IC card condition code
		unionPayPad(txn.ChipTrlCapabilities, 6) +
		unionPayPad(txn.TrlVerResult, 10) +
		unionPayPad(txn.UpblNumber, 8) +
		unionPayPad(txn.IfdSerNumber, 8) +
		unionPayPad(txn.IssAppData, 64) +
		unionPayPad(txn.AppTxnCounter, 4) +
		unionPayPad(txn.AppICProfile, 4) +
		unionPayPad(txn.ChipTxnDate, 6) +
		unionPayPad(txn.TrlConCode, 3) +
		unionPayPad(txn.IssAuthData, 42) +
		"00" + // authorization response code (100/101/102)
		unionPayPad(txn.ChipTxnType, 2) +
		unionPayMinor12(txn.CryptAmount, mult) + // authorized amount (Tag9F02)
		currency + // currency code, transaction (Tag5F2A)
		unionPayPad(txn.CryptInfoData, 2) +
		unionPayMinor12(txn.CashBackAmount, mult) + // other amount (Tag9F03)
		unionPayPad(txn.CvmResult, 6) +
		unionPayPad(txn.ChipTrlType, 2) +
		unionPayPad(txn.DedicatedFileName, 32) +
		unionPayPad(txn.TrlAppVerNumber, 4) +
		unionPayPad("", 8) + // transaction serial counter
		unionPayPad("", 30) // reserved
}

// ---- predicates & formatting helpers ----

func unionPayIsChipTxn(txn *UnionPayAcqTxnWorkEntity) bool {
	return strings.HasPrefix(txn.PosEntryMode, "05") || strings.HasPrefix(txn.PosEntryMode, "07") || strings.HasPrefix(txn.PosEntryMode, "95")
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
