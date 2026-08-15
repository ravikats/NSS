package outsvc

import (
	"context"
	"crypto/rand"
	"encoding/hex"
	"fmt"
	"math/big"
	"os"
	"path/filepath"
	"strconv"
	"strings"
	"time"
)

// Mercury outgoing constants mirroring MercuryFileServiceImpl and
// MercuryOutgoingServiceImpl.
const (
	mercuryTRANS          = "FRRC"
	mercurySFTER          = "RK"
	mercuryDFTER          = "MP"
	mercuryRecapCurrency  = "AED"
	mercuryMaxBatchRecord = 60
	mercuryMaxTxnsPerFile = 59880
	mercuryIntCategory    = "MERCURY"
)

// ProcessMercuryOutgoing is the Go port of
// MercuryOutgoingServiceImpl.processMercuryOutgoing: marks gen_status=3
// MERCURY_ACQ_TXN_WORK rows for outgoing, decrypts the PAN tokens, generates
// the EIF file, archives the rows and completes the POS statuses.
func (s *OutgoingService) ProcessMercuryOutgoing(ctx context.Context, insCode, user, formatCode int, insShortName string, startDate, toDate *time.Time) string {
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
	interfaces, err := s.store.FindInterfaceByCategory(ctx, mercuryIntCategory)
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

	acqBinList, err := s.store.FindAcquirerBins(ctx, insCode, "E")
	if err != nil {
		logOutsvc("FindAcquirerBins", err)
		return "Failed"
	}
	if len(acqBinList) == 0 || acqBinList[0] == nil {
		return "Acquirer bin not found"
	}
	acqBin := acqBinList[0]
	recapNumber := ""
	if acqBin.McIcaNo != nil {
		recapNumber = *acqBin.McIcaNo
	}

	var txnList []*MercuryAcqTxnWorkEntity
	if startDate == nil {
		txnList, err = s.store.FindMercuryWorkLessThanEqual(ctx, insCode, intCode, 3, *toDate)
	} else {
		txnList, err = s.store.FindMercuryWorkBetween(ctx, insCode, intCode, 3, *startDate, *toDate)
	}
	if err != nil {
		logOutsvc("FindMercuryWork", err)
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

	// Mark 3 -> 9 (in preparation) with empty file id, mirroring the Java
	// updateMercuryAcqWork(insCode, user, intCode, "", 3, startDate, toDate).
	now := s.now()
	for _, e := range txnList {
		e.LastUpdated = now
		e.UpdatedUser = user
		e.GenStatus = 9
		e.FileID = ""
	}
	if err := s.store.UpdateMercuryWorkStatuses(ctx, txnList); err != nil {
		logOutsvc("UpdateMercuryWorkStatuses", err)
		return "Failed"
	}

	if response == nil {
		for _, e := range txnList {
			e.GenStatus = 7
		}
		if err := s.store.UpdateMercuryWorkStatuses(ctx, txnList); err != nil {
			logOutsvc("UpdateMercuryWorkStatuses", err)
		}
		return "Outgoing Failed"
	}

	for _, fileTxns := range splitMercuryTransactions(txnList) {
		sequence := s.updateAndGetMercuryFileSequence(ctx, acqBin, now)
		fileName := "EIF_" + now.Format("02012006") + fmt.Sprintf(".%03d", sequence)
		outgoingLogSerialNumber := s.inserOutFileLog(ctx, user, insCode, intCode, forCode)
		if outgoingLogSerialNumber == 0 {
			return "Failed"
		}

		fileId := s.writeMercuryFile(ctx, fileTxns, insShortName, fileName, recapNumber, response)
		if fileId == "" {
			s.updateOutFilelog(ctx, insCode, outgoingLogSerialNumber, fileName, nil)
			return "Outgoing Failed"
		}

		// Set the file rows 9 -> 4 with the file id.
		now := s.now()
		for _, e := range fileTxns {
			e.LastUpdated = now
			e.UpdatedUser = user
			e.GenStatus = 4
			e.FileID = fileName
		}
		if err := s.store.UpdateMercuryWorkStatuses(ctx, fileTxns); err != nil {
			logOutsvc("UpdateMercuryWorkStatuses", err)
			return "Failed"
		}
		fid := fileId
		s.updateOutFilelog(ctx, insCode, outgoingLogSerialNumber, fileName, &fid)
		s.insertMercuryIntoOutgoingSummary(ctx, user, insCode, intCode, fileName, outgoingLogSerialNumber)
		// generateOutgoingSummaryPDF is not ported.
	}

	if err := s.store.CompleteMercuryPosStatus(ctx, insCode); err != nil {
		logOutsvc("CompleteMercuryPosStatus", err)
		return "Failed"
	}
	s.moveMercuryWorkToData(ctx, insCode, user)
	return "Success"
}

func splitMercuryTransactions(txnList []*MercuryAcqTxnWorkEntity) [][]*MercuryAcqTxnWorkEntity {
	var result [][]*MercuryAcqTxnWorkEntity
	for i := 0; i < len(txnList); i += mercuryMaxTxnsPerFile {
		end := i + mercuryMaxTxnsPerFile
		if end > len(txnList) {
			end = len(txnList)
		}
		result = append(result, txnList[i:end])
	}
	return result
}

func (s *OutgoingService) updateAndGetMercuryFileSequence(ctx context.Context, acqBin *AcquirerBinsEntity, now time.Time) int {
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

// insertMercuryIntoOutgoingSummary mirrors
// MercuryOutgoingServiceImpl.insertIntoOutgoingSummary: groups gen_status=9
// work rows by txn type and writes one OUTGOING_SUMMARY row per group.
func (s *OutgoingService) insertMercuryIntoOutgoingSummary(ctx context.Context, user, insCode, intCode int, fileName string, outgoingLogSerialNumber int64) {
	ents, err := s.store.FindMercuryWorkByStatus(ctx, insCode, 9)
	if err != nil {
		logOutsvc("FindMercuryWorkByStatus", err)
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

// moveMercuryWorkToData mirrors MercuryOutgoingServiceImpl.moveWorkToData:
// copies gen_status=4 work rows into MERCURY_ACQ_TXN_DATA (serial preserved,
// lastUpdated reset) and deletes them from the work table.
func (s *OutgoingService) moveMercuryWorkToData(ctx context.Context, insCode, user int) {
	workEntities, err := s.store.FindMercuryWorkByStatus(ctx, insCode, 4)
	if err != nil {
		logOutsvc("FindMercuryWorkByStatus", err)
		return
	}
	if len(workEntities) == 0 {
		return
	}
	now := time.Now()
	dataEntities := make([]*MercuryAcqTxnDataEntity, 0, len(workEntities))
	for _, we := range workEntities {
		d := *we
		d.LastUpdated = now
		dataEntities = append(dataEntities, &d)
	}
	if err := s.store.InsertMercuryData(ctx, dataEntities); err != nil {
		logOutsvc("InsertMercuryData", err)
		return
	}
	if err := s.store.DeleteMercuryWork(ctx, workEntities); err != nil {
		logOutsvc("DeleteMercuryWork", err)
	}
}

// writeMercuryFile is the Go port of MercuryFileServiceImpl.writeMercuryFile.
// It writes the FRRC lines joined by '>' into RECON_OUT_{insShortName}/{fileName}
// and returns the file name, or "" when generation fails.
func (s *OutgoingService) writeMercuryFile(ctx context.Context, txnList []*MercuryAcqTxnWorkEntity, insShortName, fileName, recapNumber string, response map[string]string) string {
	if len(txnList) == 0 {
		return ""
	}
	if s.cfg.CurrencyCodeKafka == "NIL" {
		return ""
	}
	fd := fractionalDigits(s.cfg.CurrencyCodeKafka)
	mult := new(big.Rat).SetInt64(pow10int(fd))
	now := s.now()
	recapDate := now.Format("020106")

	var lines []string
	batchNo := 1
	seqNo := 1
	batchTxnCount := 0
	batchCreditCount := 0
	batchDebitCount := 0
	recapCreditCount := 0
	recapDebitCount := 0
	batchCreditAmount := new(big.Rat)
	batchDebitAmount := new(big.Rat)
	recapCreditAmount := new(big.Rat)
	recapDebitAmount := new(big.Rat)

	lines = append(lines, mercuryUX(recapNumber, recapDate))
	lines = append(lines, mercuryUH(recapNumber, mercuryFormat3(batchNo), recapDate))

	for _, txn := range txnList {
		if batchTxnCount == mercuryMaxBatchRecord {
			lines = append(lines, mercuryUT(recapNumber, mercuryFormat3(batchNo), batchCreditCount, batchCreditAmount, batchDebitCount, batchDebitAmount))
			batchNo++
			seqNo = 1
			batchTxnCount = 0
			batchCreditCount = 0
			batchDebitCount = 0
			batchCreditAmount = new(big.Rat)
			batchDebitAmount = new(big.Rat)
			lines = append(lines, mercuryUH(recapNumber, mercuryFormat3(batchNo), recapDate))
		}
		batchNumber := mercuryFormat3(batchNo)
		sequenceNumber := mercuryFormat3(seqNo)
		lines = append(lines, mercuryXD(txn, recapNumber, batchNumber, sequenceNumber, response, mult))
		if mercuryIsXMRequired(txn.PosEntryMode) {
			lines = append(lines, mercuryXM(txn, recapNumber, batchNumber, sequenceNumber, mult))
		}
		if mercuryIsXCRequired(txn) {
			lines = append(lines, mercuryXC(txn, recapNumber, batchNumber, sequenceNumber))
		}
		if mercuryIsMCRequired(txn, mult) {
			lines = append(lines, mercuryMC(txn, recapNumber, batchNumber, sequenceNumber, mult))
		}
		txnAmount := mercuryAmount(txn.TxnAmount, mult)
		batchTxnCount++
		seqNo++
		if mercuryIsCreditTxn(txn) {
			batchCreditCount++
			batchCreditAmount = new(big.Rat).Add(batchCreditAmount, txnAmount)
			recapCreditCount++
			recapCreditAmount = new(big.Rat).Add(recapCreditAmount, txnAmount)
			continue
		}
		batchDebitCount++
		batchDebitAmount = new(big.Rat).Add(batchDebitAmount, txnAmount)
		recapDebitCount++
		recapDebitAmount = new(big.Rat).Add(recapDebitAmount, txnAmount)
	}
	lines = append(lines, mercuryUT(recapNumber, mercuryFormat3(batchNo), batchCreditCount, batchCreditAmount, batchDebitCount, batchDebitAmount))
	lines = append(lines, mercuryUY(recapNumber, recapCreditCount, recapCreditAmount, recapDebitCount, recapDebitAmount))

	return s.writeMercuryLinesToFile(lines, insShortName, fileName)
}

// writeMercuryLinesToFile writes the file lines to
// RECON_OUT_{insShortName}/{fileName}; returns fileName or "" on failure.
func (s *OutgoingService) writeMercuryLinesToFile(lines []string, insShortName, fileName string) string {
	if len(lines) == 0 {
		return ""
	}
	path := filepath.Join(s.cfg.ReconOutDir, fileName)
	var sb strings.Builder
	for _, line := range lines {
		sb.WriteString(line)
		sb.WriteByte('\n')
	}
	if err := os.WriteFile(path, []byte(sb.String()), 0o644); err != nil {
		logOutsvc("writeMercuryLinesToFile", err)
		return ""
	}
	return fileName
}

// ---- FRRC record builders ----

func mercuryUX(recapNumber, recapDate string) string {
	return strings.Join([]string{mercuryTRANS, "UX", mercurySFTER, recapNumber, mercuryDFTER, mercuryRecapCurrency, recapDate}, ">")
}

func mercuryUH(recapNumber, batchNo, recapDate string) string {
	return strings.Join([]string{mercuryTRANS, "UH", mercurySFTER, recapNumber, mercuryDFTER, batchNo, recapDate}, ">")
}

func mercuryUT(recapNumber, batchNo string, creditCount int, creditAmount *big.Rat, debitCount int, debitAmount *big.Rat) string {
	return strings.Join([]string{
		mercuryTRANS, "UT", mercurySFTER, recapNumber, mercuryDFTER, batchNo,
		fmt.Sprintf("%01d", creditCount), mercuryRatAmount(creditAmount),
		fmt.Sprintf("%01d", debitCount), mercuryRatAmount(debitAmount),
	}, ">")
}

func mercuryUY(recapNumber string, creditCount int, creditAmount *big.Rat, debitCount int, debitAmount *big.Rat) string {
	netAmount := new(big.Rat).Sub(creditAmount, debitAmount)
	return strings.Join([]string{
		mercuryTRANS, "UY", mercurySFTER, recapNumber, mercuryDFTER,
		fmt.Sprintf("%01d", creditCount), mercuryRatAmount(creditAmount),
		fmt.Sprintf("%01d", debitCount), mercuryRatAmount(debitAmount),
		"01.000", mercuryRatAmount(netAmount),
		"", "", "", "", "", "",
	}, ">")
}

func mercuryXD(txn *MercuryAcqTxnWorkEntity, recapNumber, batchNo, seqNo string, response map[string]string, mult *big.Rat) string {
	p := response[txn.EncryptedCardNumber]
	return strings.Join([]string{
		mercuryTRANS, "XD", mercurySFTER, recapNumber, mercuryDFTER, batchNo, seqNo,
		p, mercuryFormatAmount(txn.TxnAmount, mult), mercuryDate(txn.TxnDate), "TS",
		mercuryText(txn.ChargeType), mercuryFixed(txn.MeName, 36), mercuryFixed(txn.MeCity, 26),
		mercuryText(txn.GeoArea), "000", mercuryText(txn.TypeOfCharge), mercuryReferenceNumber(),
		mercuryText(txn.ApprovalCode), mercuryText(txn.MerchantId), "", "", "",
		mercuryFixed(txn.CardAccepStreetAddress, 35), mercuryTextLen(txn.MeCountry, 20),
		mercuryText(txn.MePinCode), mercuryTrimTo(txn.EstPhoneNumber, 20), "",
		mercuryText(txn.Mcc), "", "", "", mercuryText(txn.Rrn), mercuryText(txn.TerminalId),
		"", "", "", "", mercuryText(txn.ChPresent), mercuryText(txn.CardPresent),
		mercuryText(txn.CardInputMode), "", "", mercuryTextLen(txn.MercuryRefId, 15),
		mercuryText(txn.CardInputCapability), mercuryFormatAmount(txn.SurchargeAmount, mult),
		"", mercuryText(txn.GeoArea), "", "", "", "", "", "",
		mercuryText(txn.ResponseCode), "", "", "", "", "", "", "", "", "",
	}, ">")
}

func mercuryXM(txn *MercuryAcqTxnWorkEntity, recapNumber, batchNo, seqNo string, mult *big.Rat) string {
	return strings.Join([]string{
		mercuryTRANS, "XM", mercurySFTER, recapNumber, mercuryDFTER, batchNo, seqNo, "001",
		mercuryTextLen(txn.PanSequenceNumber, 3), mercuryPad("", 32),
		mercuryTextLen(txn.AppICProfile, 4), mercuryTextLen(txn.AppTxnCounter, 4),
		mercuryTextLen(txn.AppCryptogram, 16), mercuryPad("", 4),
		mercuryAmount12(txn.CryptAmount, mult), mercuryAmount12(txn.CashBackAmount, mult),
		mercuryTextLen(txn.CryptInfoData, 2), mercuryTextLen(txn.CvmResult, 6),
		mercuryTextLen(txn.DedicatedFileName, 32), mercuryTextLen(txn.IfdSerNumber, 16),
		mercuryTextLen(txn.IssAppData, 64), mercuryTextLen(txn.IssAuthData, 32),
		mercuryPad("", 50), mercuryTextLen(txn.TrlConCode, 3), mercuryTextLen(txn.TrlAppVerNumber, 4),
		mercuryTextLen(txn.ChipTrlCapabilities, 6), mercuryTextLen(txn.ChipTrlType, 2),
		mercuryTextLen(txn.TrlVerResult, 10), mercuryTextLen(txn.ChipTxnDate, 6),
		mercuryTextLen(txn.ChipTxnType, 2), mercuryTextLen(txn.ChipCurCode, 3),
		mercuryTextLen(txn.UpblNumber, 8),
	}, ">")
}

func mercuryXC(txn *MercuryAcqTxnWorkEntity, recapNumber, batchNo, seqNo string) string {
	return strings.Join([]string{
		mercuryTRANS, "XC", mercurySFTER, recapNumber, mercuryDFTER, batchNo, seqNo, "001",
		mercuryLocalTime(txn.LocalDateTime), mercuryLocalDate(txn.LocalDateTime),
		mercuryLocalTime(txn.LocalDateTime), mercuryLocalDate(txn.LocalDateTime),
		txn.TerminalId,
	}, ">")
}

func mercuryMC(txn *MercuryAcqTxnWorkEntity, recapNumber, batchNo, seqNo string, mult *big.Rat) string {
	return strings.Join([]string{
		mercuryTRANS, "MC", mercurySFTER, recapNumber, mercuryDFTER, batchNo, seqNo, "001",
		mercuryAmount12(txn.CashBackAmount, mult),
	}, ">")
}

// ---- predicates ----

func mercuryIsXMRequired(posEntryMode string) bool {
	return strings.HasPrefix(posEntryMode, "05") || strings.HasPrefix(posEntryMode, "07") || strings.HasPrefix(posEntryMode, "95")
}

func mercuryIsXCRequired(txn *MercuryAcqTxnWorkEntity) bool {
	chargeType := strings.TrimSpace(txn.ChargeType)
	return chargeType == "830" || chargeType == "831" || chargeType == "832"
}

func mercuryIsMCRequired(txn *MercuryAcqTxnWorkEntity, mult *big.Rat) bool {
	return mercuryAmount(txn.CashBackAmount, mult).Sign() > 0
}

func mercuryIsCreditTxn(txn *MercuryAcqTxnWorkEntity) bool {
	switch txn.ChargeType {
	case "TF", "TG", "TJ", "TL":
		return true
	}
	return false
}

// ---- formatting helpers ----

func mercuryFormat3(v int) string {
	return fmt.Sprintf("%03d", v)
}

func pow10int(exp int) int64 {
	r := int64(1)
	for i := 0; i < exp; i++ {
		r *= 10
	}
	return r
}

// mercuryAmount mirrors amount(Double): abs(value) * multiplier as a Rat.
func mercuryAmount(v float64, mult *big.Rat) *big.Rat {
	rat := decimalRat(v)
	if rat.Sign() < 0 {
		rat.Neg(rat)
	}
	return new(big.Rat).Mul(rat, mult)
}

// mercuryFormatAmount mirrors formatAmount(Double): value*multiplier rounded
// half-up to an integer plain string (NOT abs; null -> "0").
func mercuryFormatAmount(v float64, mult *big.Rat) string {
	rat := decimalRat(v)
	scaled := new(big.Rat).Mul(rat, mult)
	return mercuryRatHalfUp(scaled)
}

// mercuryAmount12 mirrors amount12(Double): amount(value).movePointRight(2)
// .multiply(multiplier) rounded half-up, zero-padded to 12 digits.
func mercuryAmount12(v float64, mult *big.Rat) string {
	amt := mercuryAmount(v, mult)
	scaled := new(big.Rat).Mul(amt, big.NewRat(100, 1))
	scaled = new(big.Rat).Mul(scaled, mult)
	n := mercuryRatHalfUpInt(scaled)
	return fmt.Sprintf("%012d", n)
}

// mercuryRatAmount mirrors amount(BigDecimal): abs, scale 0 half-up, plain.
func mercuryRatAmount(r *big.Rat) string {
	a := new(big.Rat).Abs(r)
	return mercuryRatHalfUp(a)
}

// mercuryRatHalfUp rounds a Rat to the nearest integer (half up, away from
// zero) as a plain decimal string.
func mercuryRatHalfUp(r *big.Rat) string {
	return strconv.FormatInt(mercuryRatHalfUpInt(r), 10)
}

// mercuryRatHalfUpInt rounds a Rat to the nearest integer, half up (rounds
// -2.5 -> -3), mirroring BigDecimal.setScale(0, RoundingMode.HALF_UP).
func mercuryRatHalfUpInt(r *big.Rat) int64 {
	sign := r.Sign()
	a := new(big.Rat).Abs(r)
	num, den := a.Num(), a.Denom()
	q := new(big.Int).Quo(num, den)
	rem := new(big.Int).Rem(num, den)
	rem2 := new(big.Int).Mul(rem, big.NewInt(2))
	if rem2.Cmp(den) >= 0 {
		q.Add(q, big.NewInt(1))
	}
	if sign < 0 {
		q.Neg(q)
	}
	return q.Int64()
}

// mercuryText mirrors text(Object): null -> "".
func mercuryText(s string) string {
	return s
}

// mercuryTextLen mirrors text(String, length): "%-<n>.<n>s" -> truncate to n
// runes and left-pad to exactly n.
func mercuryTextLen(s string, n int) string {
	r := []rune(s)
	if len(r) > n {
		r = r[:n]
	}
	return fmt.Sprintf("%-*s", n, string(r))
}

// mercuryPad mirrors pad(String, size): "%-<size>s" -> left-justify, no
// truncation.
func mercuryPad(s string, size int) string {
	return fmt.Sprintf("%-*s", size, s)
}

// mercuryFixed mirrors formatToFixedLength: null -> ""; len>size -> first size;
// else left-justified padded to size.
func mercuryFixed(s string, size int) string {
	if s == "" {
		return ""
	}
	return mercuryTextLen(s, size)
}

// mercuryTrimTo mirrors trimTo: null -> ""; len>size -> first size; else value.
func mercuryTrimTo(s string, size int) string {
	if s == "" {
		return ""
	}
	r := []rune(s)
	if len(r) > size {
		return string(r[:size])
	}
	return s
}

func mercuryDate(t *time.Time) string {
	if t == nil {
		return ""
	}
	return t.Format("060102")
}

func mercuryLocalTime(t *time.Time) string {
	if t == nil {
		return ""
	}
	return t.Format("150405")
}

func mercuryLocalDate(t *time.Time) string {
	if t == nil {
		return ""
	}
	return t.Format("060102")
}

// mercuryReferenceNumber mirrors generateReferenceNumber: 8 hex chars upper.
func mercuryReferenceNumber() string {
	b := make([]byte, 4)
	if _, err := rand.Read(b); err != nil {
		return strings.Repeat("0", 8)
	}
	return strings.ToUpper(hex.EncodeToString(b))
}

// decimalRat converts a float64 to the same decimal value Java's
// BigDecimal.valueOf(value) sees (shortest round-trip decimal).
func decimalRat(v float64) *big.Rat {
	r := new(big.Rat)
	r.SetString(strconv.FormatFloat(v, 'f', -1, 64))
	return r
}
