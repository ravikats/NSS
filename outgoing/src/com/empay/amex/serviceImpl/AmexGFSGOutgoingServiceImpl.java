/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.amex.entities.AmexAcqTxnWorkEntity
 *  com.empay.amex.repo.AmexTxnWorkRepo
 *  com.empay.amex.service.AmexGFSGOutgoingService
 *  com.empay.amex.serviceImpl.AmexGFSGOutgoingServiceImpl
 *  com.empay.amex.vo.TAAEMVRecordVo
 *  com.empay.amex.vo.TAALocationRecordVo
 *  com.empay.amex.vo.TABRecordVo
 *  com.empay.amex.vo.TBTRecordVo
 *  com.empay.amex.vo.TFHRecordVo
 *  com.empay.amex.vo.TFSRecordVo
 *  com.empay.cryptapi.DecryptResponseVo
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.core.env.Environment
 *  org.springframework.stereotype.Service
 */
package com.empay.amex.serviceImpl;

import com.empay.amex.entities.AmexAcqTxnWorkEntity;
import com.empay.amex.repo.AmexTxnWorkRepo;
import com.empay.amex.service.AmexGFSGOutgoingService;
import com.empay.amex.vo.TAAEMVRecordVo;
import com.empay.amex.vo.TAALocationRecordVo;
import com.empay.amex.vo.TABRecordVo;
import com.empay.amex.vo.TBTRecordVo;
import com.empay.amex.vo.TFHRecordVo;
import com.empay.amex.vo.TFSRecordVo;
import com.empay.cryptapi.DecryptResponseVo;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class AmexGFSGOutgoingServiceImpl
implements AmexGFSGOutgoingService {
    private static final Logger log = LoggerFactory.getLogger(AmexGFSGOutgoingServiceImpl.class);
    @Autowired
    AmexTxnWorkRepo amexTxnWorkRepo;
    @Autowired
    Environment env;

    public List<StringBuilder> amexOutData(List<AmexAcqTxnWorkEntity> amexDataEntities, int fileSequence, String fileId, DecryptResponseVo response) {
        try {
            ArrayList<StringBuilder> linesList = new ArrayList<StringBuilder>();
            TFHRecordVo tfhRecord = null;
            TABRecordVo tabRecord = null;
            TAALocationRecordVo taaLocationRecord = null;
            TAAEMVRecordVo taaEMVRecord = null;
            TBTRecordVo tbtRecord = null;
            TFSRecordVo tfsRecord = null;
            int recordSeqNo = 0;
            Integer tabCount = 0;
            Double tabAmount = 0.0;
            Double txnAmount = 0.0;
            Double creditsAmount = 0.0;
            Integer creditsCount = 0;
            Double debitsAmount = 0.0;
            Integer debitsCount = 0;
            String currentTid = null;
            String nextTid = null;
            Integer currentTrlBatchNumber = 0;
            Integer nextTrlBatchNumber = 0;
            String currentMappedMid = null;
            String nextMappedMid = null;
            String currentCurCode = null;
            String nextCurCode = null;
            String region = this.env.getProperty("REGION");
            if (Objects.nonNull(amexDataEntities) && !amexDataEntities.isEmpty()) {
                tfhRecord = this.getTFHRecord(++recordSeqNo, fileId, fileSequence);
                linesList.add(new StringBuilder(tfhRecord.getTFH()));
                AmexAcqTxnWorkEntity entity = null;
                ListIterator<AmexAcqTxnWorkEntity> iterator = amexDataEntities.listIterator();
                while (iterator.hasNext()) {
                    entity = iterator.next();
                    String decryptedCardNumber = (String)response.getCardNumbers().get(entity.getEncryptedCardNumber());
                    try {
                        txnAmount = entity.getTxnAmount();
                        ++recordSeqNo;
                        Integer n = tabCount;
                        tabCount = tabCount + 1;
                        if (entity.getProcCode().substring(0, 2).equals("20")) {
                            tabAmount = tabAmount - txnAmount;
                            creditsAmount = creditsAmount + txnAmount;
                            creditsCount = creditsCount + 1;
                        } else {
                            tabAmount = tabAmount + txnAmount;
                            debitsAmount = debitsAmount + txnAmount;
                            debitsCount = debitsCount + 1;
                        }
                        tabRecord = this.getTAB(recordSeqNo, entity, decryptedCardNumber, region);
                        linesList.add(new StringBuilder(tabRecord.getTAB()));
                        taaLocationRecord = this.getTAALocation(++recordSeqNo, entity);
                        linesList.add(new StringBuilder(taaLocationRecord.getTAALocationDetail()));
                        if (Objects.nonNull(entity.getEmv())) {
                            taaEMVRecord = this.getTAAEMV(++recordSeqNo, entity);
                            linesList.add(new StringBuilder(taaEMVRecord.getEMVchipCardData()));
                        }
                        currentTid = entity.getTerminalId();
                        nextTid = iterator.hasNext() ? amexDataEntities.get(iterator.nextIndex()).getTerminalId() : entity.getTerminalId();
                        currentTrlBatchNumber = entity.getTrlBthNumber();
                        nextTrlBatchNumber = iterator.hasNext() ? amexDataEntities.get(iterator.nextIndex()).getTrlBthNumber() : entity.getTrlBthNumber();
                        currentMappedMid = entity.getMappedMid();
                        nextMappedMid = iterator.hasNext() ? amexDataEntities.get(iterator.nextIndex()).getMappedMid() : entity.getMappedMid();
                        currentCurCode = entity.getTxnCurCode();
                        String string = nextCurCode = iterator.hasNext() ? amexDataEntities.get(iterator.nextIndex()).getTxnCurCode() : entity.getTxnCurCode();
                        if (Objects.nonNull(currentMappedMid) && !currentMappedMid.equals(nextMappedMid) || currentTrlBatchNumber != nextTrlBatchNumber) {
                            tbtRecord = this.getTBT(++recordSeqNo, tabCount, tabAmount, currentMappedMid, currentTrlBatchNumber, nextCurCode);
                            linesList.add(new StringBuilder(tbtRecord.getTBT()));
                        }
                        entity = null;
                    }
                    catch (Exception e) {
                        this.updateAmexAcqWork(amexDataEntities, 7);
                        log.error("Error processing record for RRN: {}", (Object)entity.getRrn());
                        log.error("Error processing record Exception: {}", (Object)e.getMessage());
                        return null;
                    }
                }
            } else {
                return null;
            }
            tbtRecord = this.getTBT(++recordSeqNo, tabCount, tabAmount, nextMappedMid, currentTrlBatchNumber, currentCurCode);
            linesList.add(new StringBuilder(tbtRecord.getTBT()));
            tfsRecord = this.getTFS(++recordSeqNo, debitsAmount, debitsCount, creditsAmount, creditsCount);
            linesList.add(new StringBuilder(tfsRecord.getTFS()));
            return linesList;
        }
        catch (Exception e) {
            this.updateAmexAcqWork(amexDataEntities, 7);
            log.error("error : amexOutData() : " + String.valueOf(e));
            return null;
        }
    }

    private TFHRecordVo getTFHRecord(int recordSeqNo, String fileId, int fileSequence) {
        TFHRecordVo tfhRecord = new TFHRecordVo();
        try {
            tfhRecord.setRecordType("TFH");
            tfhRecord.setRecordNumber(String.format("%08d", recordSeqNo));
            tfhRecord.setSubmitterId("VAPAY000001");
            tfhRecord.setReserved1(" ".repeat(21));
            tfhRecord.setSubmitterFileRefNumber(fileId);
            tfhRecord.setSubmitterFileSeqNumber(String.format("%09d", fileSequence));
            tfhRecord.setFileCreationDate(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
            tfhRecord.setFileCreationTime(LocalTime.now().format(DateTimeFormatter.ofPattern("HHmmss")));
            tfhRecord.setFileVersionNumber("12010000");
            tfhRecord.setReserved2(" ".repeat(617));
        }
        catch (Exception e) {
            tfhRecord = null;
            log.error("error : getTFHRecord() : " + String.valueOf(e));
        }
        return tfhRecord;
    }

    private TABRecordVo getTAB(int recordSeqNo, AmexAcqTxnWorkEntity entity, String decryptedCardNumber, String region) {
        TABRecordVo tabRecord = new TABRecordVo();
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmmss");
            tabRecord.setRecordType("TAB");
            tabRecord.setRecordNumber(String.format("%08d", recordSeqNo));
            tabRecord.setTransactionIdentifier(String.format("%1$15s", Optional.ofNullable(entity.getTxnId()).orElse("")).replace(' ', '0').substring(0, 15));
            tabRecord.setFormatCode("02");
            tabRecord.setMediaCode(" ".repeat(2));
            tabRecord.setSubmissionMethod("02");
            if ("MENA".equals(region)) {
                tabRecord.setReserved1(String.format("%-10s", Optional.ofNullable(entity.getStan()).orElse("")).substring(0, 10));
            } else {
                tabRecord.setReserved1(" ".repeat(10));
            }
            tabRecord.setApprovalCode(String.format("%-6s", Optional.ofNullable(entity.getApprovalCode()).orElse("")).substring(0, 6));
            tabRecord.setPrimaryAccountNumber(String.format("%-19s", Optional.ofNullable(decryptedCardNumber).orElse("")).substring(0, 19));
            tabRecord.setCardExpiryDate(Optional.ofNullable(entity.getExpiryDate()).orElse("").concat(" ".repeat(4)).substring(0, 4));
            tabRecord.setTransactionDate(Optional.ofNullable(entity.getLocalDateTime()).map(dt -> dt.format(formatter)).orElse(""));
            tabRecord.setTransactionTime(Optional.ofNullable(entity.getLocalDateTime()).map(dt -> dt.format(timeFormatter)).orElse(""));
            tabRecord.setReserved2("000");
            tabRecord.setTransactionAmount(String.format("%012d", (long)((Optional.ofNullable(entity.getTxnAmount()).orElse(0.0) + Optional.ofNullable(entity.getSurchargeAmount()).orElse(0.0)) * 100.0)));
            tabRecord.setProcessingCode((Optional.ofNullable(entity.getProcCode()).orElse("") + "000000").substring(0, 6));
            tabRecord.setTransactionCurrCode(String.format("%-3s", Optional.ofNullable(entity.getTxnCurCode()).orElse("")).substring(0, 3));
            tabRecord.setExtendedPaymentData("01");
            tabRecord.setMerchantId(String.format("%-15s", Optional.ofNullable(entity.getMappedMid()).orElse("")).substring(0, 15));
            tabRecord.setMerchantLocationId(String.format("%-15s", Optional.ofNullable(entity.getTrlLocation()).orElse("")).substring(0, 15));
            tabRecord.setMerchantContactInfo(String.format("%-40s", Optional.ofNullable(entity.getContactEmail()).orElse("")).substring(0, 40));
            tabRecord.setTerminalId(String.format("%-8s", Optional.ofNullable(entity.getTerminalId()).orElse("")).substring(0, 8));
            tabRecord.setPointOfServiceDataCode((Optional.ofNullable(entity.getPosDataCode()).orElse("") + "000000000000").substring(0, 12));
            tabRecord.setReserved3("000");
            tabRecord.setReserved4("MENA".equals(region) ? Optional.ofNullable(entity.getMerchantId()).map(mid -> mid.substring(3)).orElse("") : "000000000000");
            tabRecord.setReserved5(" ".repeat(3));
            tabRecord.setInvoiceRefNumber(String.format("%-30s", Optional.ofNullable(entity.getInvoiceNumber()).orElse("")).substring(0, 30));
            tabRecord.setReserved6("MENA".equals(region) ? String.format("%-15s", Optional.ofNullable(entity.getRrn()).orElse("")).substring(0, 15) : " ".repeat(15));
            tabRecord.setTabImageSeqNumber(" ".repeat(8));
            tabRecord.setMatchingKeyType(" ".repeat(2));
            tabRecord.setMatchingKey(" ".repeat(21));
            tabRecord.setEcomIndicator(String.format("%-2s", Optional.ofNullable(entity.getMotoEcomIndicator()).orElse("")).substring(0, 2));
            tabRecord.setReserved7(" ".repeat(403));
        }
        catch (Exception e) {
            tabRecord = null;
            log.error("error : getTAB() : " + String.valueOf(e));
        }
        return tabRecord;
    }

    private TAAEMVRecordVo getTAAEMV(int recordSeqNo, AmexAcqTxnWorkEntity entity) {
        TAAEMVRecordVo taaEMVRecord = new TAAEMVRecordVo();
        try {
            taaEMVRecord.setRecordType("TAA");
            taaEMVRecord.setRecordNumber(String.format("%08d", recordSeqNo));
            taaEMVRecord.setTransactionIdentifier(String.format("%1$15s", Optional.ofNullable(entity.getTxnId()).orElse("")).replace(' ', '0').substring(0, 15));
            taaEMVRecord.setEmvFormatType("01");
            taaEMVRecord.setAddenaTypeCode("07");
            taaEMVRecord.setIccSystemRelatedData(String.format("%-256s", Optional.ofNullable(entity.getEmv()).orElse("")).substring(0, 256));
            taaEMVRecord.setReserved(" ".repeat(414));
        }
        catch (Exception e) {
            taaEMVRecord = null;
            log.error("error : getTAAEMV() : " + String.valueOf(e));
        }
        return taaEMVRecord;
    }

    private TAALocationRecordVo getTAALocation(int recordSeqNo, AmexAcqTxnWorkEntity entity) {
        TAALocationRecordVo taaLocationRecord = new TAALocationRecordVo();
        try {
            taaLocationRecord.setRecordType("TAA");
            taaLocationRecord.setRecordNumber(String.format("%08d", recordSeqNo));
            taaLocationRecord.setTransactionIdentifier(String.format("%1$15s", Optional.ofNullable(entity.getTxnId()).orElse("")).replace(' ', '0').substring(0, 15));
            taaLocationRecord.setReserved1("00");
            taaLocationRecord.setAddenaTypeCode("99");
            taaLocationRecord.setLocationName(String.format("%-38s", Optional.ofNullable(entity.getMeName()).orElse("")).substring(0, 38));
            taaLocationRecord.setLocationAddress(String.format("%-27s", Optional.ofNullable(entity.getLocationAddress()).orElse("")).substring(0, 27) + " ".repeat(11));
            taaLocationRecord.setLocationCity(String.format("%-21s", Optional.ofNullable(entity.getMeCity()).orElse("")).substring(0, 21));
            taaLocationRecord.setLocationRegionCode(String.format("%-3s", Optional.ofNullable(entity.getLocRegionCode()).orElse("")).substring(0, 3));
            taaLocationRecord.setLocationCountryCode(String.format("%-3s", Optional.ofNullable(entity.getMeCountry()).orElse("")).substring(0, 3));
            taaLocationRecord.setLocationPostalCode(String.format("%-15s", Optional.ofNullable(entity.getMePinCode()).orElse("")).substring(0, 15));
            taaLocationRecord.setMerchantCategoryCode(String.format("%-4s", Optional.ofNullable(entity.getMcc()).orElse("")).substring(0, 4));
            taaLocationRecord.setSellerId(String.format("%-20s", Optional.ofNullable(entity.getMerchantId()).orElse("")).substring(0, 20));
            taaLocationRecord.setReserved2(" ".repeat(528));
        }
        catch (Exception e) {
            taaLocationRecord = null;
            log.error("error : getTAALocation() : " + String.valueOf(e));
        }
        return taaLocationRecord;
    }

    private TBTRecordVo getTBT(int recordSeqNo, Integer tabCount, Double tabAmount, String mappedMid, Integer trlBatchNumber, String CurrencyCode) {
        TBTRecordVo tbtRecord = new TBTRecordVo();
        try {
            tbtRecord.setRecordType("TBT");
            tbtRecord.setRecordNumber(String.format("%08d", recordSeqNo));
            tbtRecord.setMerchantId(String.format("%-15s", Optional.ofNullable(mappedMid).orElse("")).substring(0, 15));
            tbtRecord.setReserved1(" ".repeat(15));
            tbtRecord.setTbtIdenticationNumber(String.format("%015d", Optional.ofNullable(trlBatchNumber).orElse(0)).substring(0, 15));
            tbtRecord.setTbtCreationDate(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
            tbtRecord.setTotalNumOfTabs(String.format("%08d", Optional.ofNullable(tabCount).orElse(0)).substring(0, 8));
            tbtRecord.setReserved2("000");
            tbtRecord.setTbtAmount(Optional.ofNullable(tabAmount).map(amount -> String.format("%020d", Math.abs((long)(amount * 100.0))).substring(0, 20)).orElse("00000000000000000000"));
            tbtRecord.setTbtAmountSign(Optional.ofNullable(tabAmount).map(amount -> amount < 0.0 ? "-" : "+").orElse("+"));
            tbtRecord.setTbtCurrencyCode(String.format("%-3s", Optional.ofNullable(CurrencyCode).orElse("")).substring(0, 3));
            tbtRecord.setReserved3("000");
            tbtRecord.setReserved4("0".repeat(20));
            tbtRecord.setReserved5(" ".repeat(3));
            tbtRecord.setTbtImageSeqNumber(" ".repeat(8));
            tbtRecord.setReserved6(" ".repeat(567));
        }
        catch (Exception e) {
            tbtRecord = null;
            log.error("error : getTBT() : " + String.valueOf(e));
        }
        return tbtRecord;
    }

    private TFSRecordVo getTFS(int recordSeqNo, Double debitsAmount, Integer debitsCount, Double creditsAmount, Integer creditsCount) {
        TFSRecordVo tfsRecord = new TFSRecordVo();
        try {
            tfsRecord.setRecordType("TFS");
            tfsRecord.setRecordNumber(String.format("%08d", recordSeqNo));
            tfsRecord.setNumberOfDebits(String.format("%08d", Optional.ofNullable(debitsCount).orElse(0)).substring(0, 8));
            tfsRecord.setReserved1("000");
            tfsRecord.setHashTotalDebAmount(Optional.ofNullable(debitsAmount).map(amount -> String.format("%020d", Math.abs((long)(amount * 100.0))).substring(0, 20)).orElse("00000000000000000000"));
            tfsRecord.setNumberOfCredits(String.format("%08d", Optional.ofNullable(creditsCount).orElse(0)).substring(0, 8));
            tfsRecord.setReserved2("000");
            tfsRecord.setHashTotalCreditAmount(Optional.ofNullable(creditsAmount).map(amount -> String.format("%020d", Math.abs((long)(amount * 100.0))).substring(0, 20)).orElse("00000000000000000000"));
            tfsRecord.setReserved3("000");
            tfsRecord.setHashTotalAmount(String.format("%020d", Math.round((debitsAmount + creditsAmount) * 100.0)).substring(0, 20));
            tfsRecord.setReserved4(" ".repeat(604));
        }
        catch (Exception e) {
            tfsRecord = null;
            log.error("error : getTFS() : " + String.valueOf(e));
        }
        return tfsRecord;
    }

    private void updateAmexAcqWork(List<AmexAcqTxnWorkEntity> amexDataEntities, int status) {
        try {
            if (amexDataEntities != null && !amexDataEntities.isEmpty()) {
                amexDataEntities.forEach(entity -> entity.setGenStatus(Integer.valueOf(status)));
                this.amexTxnWorkRepo.saveAllAndFlush(amexDataEntities);
            }
        }
        catch (Exception e) {
            log.error("Error updateAmexAcqWork()", (Throwable)e);
        }
    }
}

