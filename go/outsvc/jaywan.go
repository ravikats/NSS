package outsvc

import (
	"context"
	"fmt"
	"os"
	"path/filepath"
	"strconv"
	"time"
)

// NOTE (2026-10-01): Restored the full Java-fidelity Jaywan port verbatim from
// IRF1 (all Txn tags, Java declaration order), replacing the reduced V1.3
// mapper that IRF had reworked. DECIDE LATER whether the following tags, which
// the rework had dropped, are actually unused by the network sample and can be
// trimmed: nAddData, nAmtBil, nAmtSet, nARD, nCcyCdBil, nCcyCdSet, nConvRtBil,
// nConvRtSet, nDtSet, nIntrnTrackNum, nLtPrsntInd, nProcSts, nRecrPymtCd,
// nRejRsnCd, nSetDCInd, nTxnDesInstCd, nUnFlNm. Kept as-is for now.

// ProcessJaywanOutgoing is the Go port of
// JaywanOutgoingServiceImpl.generateJaywanOutgoing. It generates the Jaywan XML
// file for JAYWAN_ACQ_TXN_WORK rows with gen_status=3 in the date range.
func (s *OutgoingService) ProcessJaywanOutgoing(ctx context.Context, insCode, user, formatCode int, insShortName string, fromDate, toDate *time.Time) string {
	intCategory := "JAYWAN"
	now := time.Now()

	fileFormatEntity, err := s.store.FindFileFormatBySystemCodeAndType(ctx, formatCode, "O")
	if err != nil {
		logOutsvc("FindFileFormatBySystemCodeAndType", err)
		return "Failed"
	}
	forCode := 0
	if fileFormatEntity != nil {
		forCode = fileFormatEntity.Code
	}
	interfaces, err := s.store.FindInterfaceByCategory(ctx, intCategory)
	if err != nil {
		logOutsvc("FindInterfaceByCategory", err)
		return "Failed"
	}
	intCode := 0
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

	outgoingLogSerialNumber := s.inserOutFileLog(ctx, user, insCode, intCode, forCode)
	if outgoingLogSerialNumber == 0 {
		return "Failed"
	}

	acqBinList, err := s.store.FindAcquirerBins(ctx, insCode, "J")
	if err != nil {
		logOutsvc("FindAcquirerBins", err)
		return "Failed"
	}
	// Java throws IllegalStateException("No Acquirer Bins available") when the
	// list is empty; that bubbles up to the outer catch -> "Failed".
	if len(acqBinList) == 0 || acqBinList[0] == nil {
		return "Failed"
	}
	acq := acqBinList[0]
	fileSequence := 0
	if acq.OutfileDate != nil && dateOnly(*acq.OutfileDate) == dateOnly(now) {
		fileSequence = acq.OutFileSeq
	} else {
		fileSequence = 1
	}
	acq.OutFileSeq = fileSequence + 1
	t := now
	acq.OutfileDate = &t
	if err := s.store.UpdateAcquirerBin(ctx, acq); err != nil {
		logOutsvc("UpdateAcquirerBin", err)
		return "Failed"
	}

	year := now.Year()
	dayOfYear := now.YearDay()
	julianDateStr := fmt.Sprintf("%02d%03d", year%100, dayOfYear)
	fileId := "000" + strOrNull(acq.ParticipantId) + julianDateStr + strconv.Itoa(fileSequence)
	fileName := fileId + ".xml"

	var entities []*JaywanAcqTxnWorkEntity
	if fromDate == nil {
		entities, err = s.store.FindJaywanWorkLessThanEqual(ctx, insCode, intCode, 3, *toDate)
	} else {
		entities, err = s.store.FindJaywanWorkBetween(ctx, insCode, intCode, 3, *fromDate, *toDate)
	}
	if err != nil {
		logOutsvc("FindJaywanWork", err)
		return "Failed"
	}
	if len(entities) == 0 {
		return "Failed"
	}

	// Mark 3 -> 9 (in preparation) and save, mirroring the Java saveAll.
	for _, e := range entities {
		e.GenStatus = 9
	}
	if err := s.store.UpdateJaywanWorkStatuses(ctx, entities); err != nil {
		logOutsvc("UpdateJaywanWorkStatuses", err)
		return "Failed"
	}
	// Java: updateOutFilelog(fileName, fileId, ...) with the raw file id.
	s.updateOutFilelog(ctx, insCode, outgoingLogSerialNumber, fileName, &fileId)

	seen := map[string]struct{}{}
	tokens := make([]string, 0, len(entities))
	for _, d := range entities {
		if d.EncCardNumber == "" {
			continue
		}
		if _, ok := seen[d.EncCardNumber]; ok {
			continue
		}
		seen[d.EncCardNumber] = struct{}{}
		tokens = append(tokens, d.EncCardNumber)
	}
	decrypted := s.crypto.GetCardNumber(tokens)
	if decrypted == nil {
		for _, e := range entities {
			e.GenStatus = 7
		}
		if err := s.store.UpdateJaywanWorkStatuses(ctx, entities); err != nil {
			logOutsvc("UpdateJaywanWorkStatuses", err)
		}
		return "Outgoing Failed"
	}

	header := s.mapJaywanHeader(ctx, entities, acq, fileId)
	recordCounter := 2
	totalTxnAmount := 0.0
	var txns []string
	for _, e := range entities {
		if e.LocalDateTime == nil {
			return "Failed"
		}
		tagList, ok := s.mapJaywanEntityToTxn(ctx, e, decrypted, recordCounter, insCode)
		if !ok {
			continue
		}
		txns = append(txns, buildJaywanTxn(tagList))
		totalTxnAmount += e.TxnAmount * 100.0
		recordCounter++
	}
	trailer := &jaywanXmlTrailerVO{
		NMTI:      "1644",
		NFunCd:    "671",
		NRecNum:   fmt.Sprintf("%08d", recordCounter),
		NUnFlNm:   fileId,
		NTxnCnt:   strconv.Itoa(len(txns)),
		NRnTtlAmt: javaDoubleString(totalTxnAmount),
	}
	xmlContent := buildJaywanFile(header, txns, buildJaywanTrailer(trailer))

	xmlFilePath := s.writeJaywanXmlFile(xmlContent, insShortName, fileName)
	if xmlFilePath == "" {
		return "Outgoing Failed"
	}

	// Java: updateOutFilelog(fileName, fileName, ...) sets FileId = the file
	// name and keeps generated_status = 4.
	s.updateOutFilelog(ctx, insCode, outgoingLogSerialNumber, fileName, &fileName)
	// Mark 9 -> 4 (completed), mirroring the Java re-save of the entities.
	for _, e := range entities {
		e.GenStatus = 4
	}
	if err := s.store.UpdateJaywanWorkStatuses(ctx, entities); err != nil {
		logOutsvc("UpdateJaywanWorkStatuses", err)
	}
	s.insertJaywanIntoOutgoingSummary(ctx, user, insCode, intCode, fileName, outgoingLogSerialNumber)
	if err := s.store.CompleteJaywanPosStatus(ctx, insCode); err != nil {
		logOutsvc("CompleteJaywanPosStatus", err)
	}
	s.moveJaywanWorkToData(ctx, entities, fileName)
	// generateOutgoingSummaryPDF is not ported yet.
	return "Success"
}

type jaywanXmlHeaderVO struct {
	NMTI, NFunCd, NRecNum, NDtTmFlGen, NDtSet, NMemInstCd string
	NUnFlNm, NProdCd, NFlCatg, NVerNum, NFlRejInd         string
}

type jaywanXmlTrailerVO struct {
	NMTI, NFunCd, NRecNum, NUnFlNm, NTxnCnt, NRnTtlAmt string
}

// mapJaywanHeader mirrors JaywanOutgoingServiceImpl.mapToHeader.
func (s *OutgoingService) mapJaywanHeader(ctx context.Context, entities []*JaywanAcqTxnWorkEntity, acq *AcquirerBinsEntity, fileId string) string {
	now := time.Now()
	return buildJaywanHeader(&jaywanXmlHeaderVO{
		NMTI:       "1644",
		NFunCd:     "670",
		NRecNum:    "00000001",
		NDtTmFlGen: now.Format("0102030405"),
		NDtSet:     jaywanSettlDate(entities[0].SettlDate),
		NMemInstCd: strOrNull(acq.ParticipantId),
		NUnFlNm:    fileId,
		NProdCd:    s.cfg.ProductCode,
		NFlCatg:    s.cfg.FileCategory,
		NVerNum:    s.cfg.VersionNumber,
		NFlRejInd:  "N",
	})
}

// mapJaywanEntityToTxn mirrors JaywanOutgoingServiceImpl.mapEntityToTransaction.
// It returns the ordered field list for the <Txn> element and ok=false when the
// card cannot be decrypted (Java: the transaction is skipped and updateFailedTxn
// marks the row failed).
func (s *OutgoingService) mapJaywanEntityToTxn(ctx context.Context, e *JaywanAcqTxnWorkEntity, decrypted map[string]string, recordNumber, insCode int) ([]jaywanTxnTag, bool) {
	decCard := decrypted[e.EncCardNumber]
	if decCard == "" {
		s.updateJaywanFailedTxn(ctx, e.Rrn)
		return nil, false
	}
	mti := e.MessageTypeId
	fc := e.FunctionCode

	tags := []jaywanTxnTag{
		{"nMTI", jaywanStrPtr(e.MessageTypeId)},
		{"nFunCd", jaywanStrPtr(e.FunctionCode)},
		{"nRecNum", jaywanStrPtr(fmt.Sprintf("%08d", recordNumber))},
		{"nDtTmLcTxn", jaywanStrPtr(jaywanTxnDateTime(e.LocalDateTime))},
		{"nPAN", jaywanStrPtr(decCard)},
	}
	if mti != "8144" {
		tags = append(tags, jaywanTxnTag{"nARD", jaywanStrPtr(e.AcqRefData)})
	} else {
		tags = append(tags, jaywanTxnTag{"nRRN", jaywanStrPtr(e.Rrn)})
	}
	tags = append(tags,
		jaywanTxnTag{"nAcqInstCd", jaywanStrPtr(e.AcqinstIdCode)},
		jaywanTxnTag{"nApprvlCd", jaywanStrPtr(e.ApprovalCode)},
		jaywanTxnTag{"nCrdAcptTrmId", jaywanStrPtr(e.TerminalId)},
		jaywanTxnTag{"nAmtTxn", jaywanStrPtr(jaywanAmt12(e.TxnAmount, true))},
		jaywanTxnTag{"nCcyCdTxn", jaywanStrPtr(e.TxnCurCode)},
		jaywanTxnTag{"nTxnOrgInstCd", jaywanStrPtr(strconv.Itoa(insCode))},
		jaywanTxnTag{"nTxnDesInstCd", jaywanStrPtr("")},
		jaywanTxnTag{"nUnFlNm", jaywanStrPtr(e.FileID)},
		jaywanTxnTag{"nDtSet", jaywanStrPtr("")},
		jaywanTxnTag{"nSetDCInd", jaywanStrPtr(e.CardType)},
	)
	if mti != "8144" {
		tags = append(tags,
			jaywanTxnTag{"nAmtSet", jaywanStrPtr(jaywanAmt12(e.SettledAmount, false))},
			jaywanTxnTag{"nCcyCdSet", jaywanStrPtr(e.TxnCurCode)},
			jaywanTxnTag{"nConvRtSet", jaywanStrPtr(jaywanConvRate(e.ConvRate))},
			jaywanTxnTag{"nAmtBil", jaywanStrPtr(jaywanAmt12(e.BillAmount, false))},
			jaywanTxnTag{"nConvRtBil", jaywanStrPtr("")},
			jaywanTxnTag{"nCcyCdBil", jaywanStrPtr("")},
		)
	} else {
		tags = append(tags,
			jaywanTxnTag{"nAmtSet", nil},
			jaywanTxnTag{"nCcyCdSet", nil},
			jaywanTxnTag{"nConvRtSet", nil},
			jaywanTxnTag{"nAmtBil", nil},
			jaywanTxnTag{"nConvRtBil", nil},
			jaywanTxnTag{"nCcyCdBil", nil},
		)
	}
	if mti == "1240" && fc == "200" {
		tags = append(tags,
			jaywanTxnTag{"nLtPrsntInd", jaywanStrPtr("Y")},
		)
	} else {
		tags = append(tags, jaywanTxnTag{"nLtPrsntInd", nil})
	}
	tags = append(tags,
		jaywanTxnTag{"nProcSts", jaywanStrPtr("S")},
		jaywanTxnTag{"nRejRsnCd", jaywanStrPtr("")},
	)
	if mti == "1240" || fc == "263" || fc == "269" {
		tags = append(tags, jaywanTxnTag{"nAddData", nil})
	} else {
		tags = append(tags, jaywanTxnTag{"nAddData", jaywanStrPtr("")})
	}
	if mti == "1240" && fc == "200" {
		tags = append(tags, jaywanTxnTag{"nECIInd", jaywanStrPtr(e.MotoEcomIndicator)})
	} else {
		tags = append(tags, jaywanTxnTag{"nECIInd", nil})
	}
	tags = append(tags,
		jaywanTxnTag{"nCrdAcpIDCd", jaywanStrPtr(e.MerchantId)},
		jaywanTxnTag{"nCrdAcpNm", jaywanStrPtr(e.MeName)},
		jaywanTxnTag{"nCrdAcpCity", jaywanStrPtr(e.MeCity)},
		jaywanTxnTag{"nCrdAcpStNm", jaywanStrPtr("")},
		jaywanTxnTag{"nCrdAcpCtryCd", jaywanStrPtr(e.MeCountry)},
	)
	if mti == "1240" || fc == "263" {
		tags = append(tags, jaywanTxnTag{"nRecrPymtCd", nil})
	} else {
		tags = append(tags, jaywanTxnTag{"nRecrPymtCd", jaywanStrPtr("")})
	}
	tags = append(tags, jaywanTxnTag{"nCrdAcpBussCd", jaywanStrPtr(e.Mcc)})
	if mti != "8144" {
		tags = append(tags, jaywanTxnTag{"nProcCd", jaywanStrPtr(e.TxnType)})
	} else {
		tags = append(tags, jaywanTxnTag{"nProcCd", nil})
	}
	if mti == "1240" && (fc == "200" || fc == "262") {
		tags = append(tags,
			jaywanTxnTag{"nPosEntMode", jaywanStrPtr(e.PosEntryMode)},
			jaywanTxnTag{"nPosCondCd", jaywanStrPtr("")},
			jaywanTxnTag{"nActnCd", jaywanStrPtr("")},
		)
	} else {
		tags = append(tags,
			jaywanTxnTag{"nPosEntMode", nil},
			jaywanTxnTag{"nPosCondCd", nil},
			jaywanTxnTag{"nActnCd", nil},
		)
	}
	if mti == "1240" && fc == "269" {
		tags = append(tags, jaywanTxnTag{"nFulParInd", jaywanStrPtr("")})
	} else {
		tags = append(tags, jaywanTxnTag{"nFulParInd", nil})
	}
	if mti == "1240" && (fc == "263" || fc == "269") {
		tags = append(tags, jaywanTxnTag{"nIntrnTrackNum", jaywanStrPtr(e.FileID)})
	} else {
		tags = append(tags, jaywanTxnTag{"nIntrnTrackNum", nil})
	}
	return tags, true
}

// updateJaywanFailedTxn mirrors JaywanOutgoingServiceImpl.updateFailedTxn: all
// work rows with the matching RRN are set to gen_status=7.
func (s *OutgoingService) updateJaywanFailedTxn(ctx context.Context, rrn string) {
	if rrn == "" {
		return
	}
	ents, err := s.store.FindJaywanWorkByRrn(ctx, rrn)
	if err != nil {
		logOutsvc("FindJaywanWorkByRrn", err)
		return
	}
	if len(ents) == 0 {
		return
	}
	for _, e := range ents {
		e.GenStatus = 7
	}
	if err := s.store.UpdateJaywanWorkStatuses(ctx, ents); err != nil {
		logOutsvc("UpdateJaywanWorkStatuses", err)
	}
}

// writeJaywanXmlFile mirrors JaywanOutgoingServiceImpl.generateXmlFile: writes
// the pretty XML to RECON_OUT_{insShortName}/{fileName}; returns "" on error.
func (s *OutgoingService) writeJaywanXmlFile(content, insShortName, fileName string) string {
	path := filepath.Join(s.cfg.ReconOutDir, fileName)
	if err := os.WriteFile(path, []byte(content), 0o644); err != nil {
		logOutsvc("writeJaywanXmlFile", err)
		return ""
	}
	return path
}

// insertJaywanIntoOutgoingSummary mirrors
// JaywanOutgoingServiceImpl.insertOutgoingSummary: groups the gen_status=4 rows
// by function code and writes one OUTGOING_SUMMARY row per group.
func (s *OutgoingService) insertJaywanIntoOutgoingSummary(ctx context.Context, user, insCode, intCode int, fileName string, outgoingLogSerialNumber int64) {
	ents, err := s.store.FindJaywanWorkByIntAndStatus(ctx, insCode, intCode, 4)
	if err != nil {
		logOutsvc("FindJaywanWorkByIntAndStatus", err)
		return
	}
	groups := map[string]*struct {
		count                int
		totalTxnAmount       float64
		totalSurchargeAmount float64
	}{}
	for _, e := range ents {
		g := groups[e.FunctionCode]
		if g == nil {
			g = &struct {
				count                int
				totalTxnAmount       float64
				totalSurchargeAmount float64
			}{}
			groups[e.FunctionCode] = g
		}
		g.count++
		g.totalTxnAmount += e.TxnAmount
		g.totalSurchargeAmount += e.SurchargeAmount
	}
	now := time.Now()
	for fc, totals := range groups {
		ots := &OutgoingSummaryEntity{
			LastUpdated:     now,
			UpdatedUser:     user,
			InstitutionCode: insCode,
			InterfaceCode:   intCode,
			OutFileDate:     dateOnly(now),
			FileId:          fileName,
			RefSerialNumber: outgoingLogSerialNumber,
			MessageTypeId:   fc,
			FunctionCode:    "1",
			ProcCode:        "",
			Count:           totals.count,
			Amount:          totals.totalTxnAmount,
			SurchargeAmount: totals.totalSurchargeAmount,
			NetAmount:       totals.totalTxnAmount + totals.totalSurchargeAmount,
			GeneralStatus:   3,
		}
		if err := s.store.InsertSummaries(ctx, []*OutgoingSummaryEntity{ots}); err != nil {
			logOutsvc("InsertSummaries", err)
		}
	}
}

// moveJaywanWorkToData mirrors JaywanOutgoingServiceImpl.moveWorkToData: copies
// the processed work rows into JAYWAN_ACQ_TXN_DATA (serial preserved,
// lastUpdated reset, JWN_FILE_ID set to the file name) and deletes them from
// the work table.
func (s *OutgoingService) moveJaywanWorkToData(ctx context.Context, entities []*JaywanAcqTxnWorkEntity, fileName string) {
	now := time.Now()
	dataEntities := make([]*JaywanAcqTxnDataEntity, 0, len(entities))
	for _, e := range entities {
		d := *e
		d.LastUpdated = now
		d.FileID = fileName
		dataEntities = append(dataEntities, &d)
	}
	if err := s.store.InsertJaywanData(ctx, dataEntities); err != nil {
		logOutsvc("InsertJaywanData", err)
		return
	}
	if err := s.store.DeleteJaywanWork(ctx, entities); err != nil {
		logOutsvc("DeleteJaywanWork", err)
	}
}

// mapJaywanDataToWork mirrors OutGoingProcessingService.mapToJaywanAcqWorkEntity:
// the serial number is preserved and gen_status set to 3 (revert).
func mapJaywanDataToWork(d *JaywanAcqTxnDataEntity, now time.Time) *JaywanAcqTxnWorkEntity {
	w := *d
	w.LastUpdated = now
	w.GenStatus = 3
	return &w
}

// jaywanSettlDate renders the header nDtSet as yyMMdd, or "000000" when the
// settlement date is null (Java: "0".repeat(6)).
func jaywanSettlDate(t *time.Time) string {
	if t == nil {
		return "000000"
	}
	return t.Format("060102")
}

// jaywanTxnDateTime renders nDtTmLcTxn as YYMMddhhmmss where YY is the
// week-based year (Java pattern "YYMMddhhmmss") and hh is the 12-hour clock.
func jaywanTxnDateTime(t *time.Time) string {
	isoYear, _ := t.ISOWeek()
	return fmt.Sprintf("%02d%s", isoYear%100, t.Format("0102030405"))
}

// jaywanAmt12 formats a 12-digit zero-padded amount. Java multiplies the txn
// amount by 100 (major -> minor) and truncates to long; settled/bill amounts
// are truncated directly.
func jaywanAmt12(v float64, mult100 bool) string {
	if mult100 {
		return fmt.Sprintf("%012d", int64(v*100.0))
	}
	return fmt.Sprintf("%012d", int64(v))
}

// jaywanConvRate renders nConvRtSet: Java emits String.valueOf(convRate) when
// non-null and "" otherwise.
func jaywanConvRate(v float64) string {
	if v == 0 {
		return ""
	}
	return javaDoubleString(v)
}

func strOrNull(p *string) string {
	if p == nil {
		return "null"
	}
	return *p
}
