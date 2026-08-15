/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.common.entity.BusinessDateEntity
 *  com.empay.common.entity.FileFormatsEntity
 *  com.empay.common.entity.InterfacesEntity
 *  com.empay.common.entity.OutGoingFileProcessingEntity
 *  com.empay.common.entity.OutgoingSummaryEntity
 *  com.empay.common.repo.BusinessDateRepo
 *  com.empay.common.repo.FileFormatsRepo
 *  com.empay.common.repo.InterfacesRepo
 *  com.empay.common.repo.OutFileLogRepo
 *  com.empay.common.repo.OutgoingSummaryRepo
 *  com.empay.cryptapi.CryptAPI
 *  com.empay.cryptapi.DecryptResponseVo
 *  com.empay.jaywan.entities.JaywanAcqTxnDataEntity
 *  com.empay.jaywan.entities.JaywanAcqTxnWorkEntity
 *  com.empay.jaywan.repo.JWNAcqTxnDataRepo
 *  com.empay.jaywan.repo.JWNAcqTxnWorkRepo
 *  com.empay.jaywan.service.JaywanOutgoingService
 *  com.empay.jaywan.serviceImpl.JaywanOutgoingServiceImpl
 *  com.empay.jaywan.vo.JaywanXmlHeaderValueObject
 *  com.empay.jaywan.vo.JaywanXmlTrailerValueObject
 *  com.empay.jaywan.vo.JaywanXmlTransactionsBlockValueObject
 *  com.empay.jaywan.vo.JaywanXmlTransactionsValueObject
 *  com.empay.jaywan.vo.JaywanXmlWrapperValueObject
 *  com.empay.staging.entities.AcquirerBinsEntity
 *  com.empay.staging.repo.AcquirerBinsRepo
 *  com.empay.staging.repo.PosTransactionRepo
 *  com.empay.staging.service.IOutGoingSummaryService
 *  com.fasterxml.jackson.dataformat.xml.XmlMapper
 *  com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator$Feature
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 *  org.springframework.core.env.Environment
 *  org.springframework.stereotype.Service
 *  org.springframework.transaction.annotation.Transactional
 */
package com.empay.jaywan.serviceImpl;

import com.empay.common.entity.BusinessDateEntity;
import com.empay.common.entity.FileFormatsEntity;
import com.empay.common.entity.InterfacesEntity;
import com.empay.common.entity.OutGoingFileProcessingEntity;
import com.empay.common.entity.OutgoingSummaryEntity;
import com.empay.common.repo.BusinessDateRepo;
import com.empay.common.repo.FileFormatsRepo;
import com.empay.common.repo.InterfacesRepo;
import com.empay.common.repo.OutFileLogRepo;
import com.empay.common.repo.OutgoingSummaryRepo;
import com.empay.cryptapi.CryptAPI;
import com.empay.cryptapi.DecryptResponseVo;
import com.empay.jaywan.entities.JaywanAcqTxnDataEntity;
import com.empay.jaywan.entities.JaywanAcqTxnWorkEntity;
import com.empay.jaywan.repo.JWNAcqTxnDataRepo;
import com.empay.jaywan.repo.JWNAcqTxnWorkRepo;
import com.empay.jaywan.service.JaywanOutgoingService;
import com.empay.jaywan.vo.JaywanXmlHeaderValueObject;
import com.empay.jaywan.vo.JaywanXmlTrailerValueObject;
import com.empay.jaywan.vo.JaywanXmlTransactionsBlockValueObject;
import com.empay.jaywan.vo.JaywanXmlTransactionsValueObject;
import com.empay.jaywan.vo.JaywanXmlWrapperValueObject;
import com.empay.staging.entities.AcquirerBinsEntity;
import com.empay.staging.repo.AcquirerBinsRepo;
import com.empay.staging.repo.PosTransactionRepo;
import com.empay.staging.service.IOutGoingSummaryService;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class JaywanOutgoingServiceImpl
implements JaywanOutgoingService {
    private static final Logger log = LoggerFactory.getLogger(JaywanOutgoingServiceImpl.class);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
    private final AcquirerBinsRepo acqBinRepo;
    private final JWNAcqTxnWorkRepo txnWorkRepo;
    private final Environment env;
    private final InterfacesRepo interfacesRepo;
    private final FileFormatsRepo fileFormatsRepo;
    private final OutFileLogRepo outFileRepo;
    private final BusinessDateRepo businessDateRepo;
    private final OutgoingSummaryRepo summaryRepo;
    private final JWNAcqTxnDataRepo txnDataRepo;
    private final CryptAPI cryptApi;
    private final PosTransactionRepo posTxnRepo;
    private final IOutGoingSummaryService iOutGoingSummaryService;

    @Transactional
    public String generateJaywanOutgoing(Integer institutionCode, Integer user, Integer forCode, String insShortName, LocalDateTime fromDate, LocalDateTime toDate) {
        String fileName = null;
        int fileSequence = 0;
        String intCategory = "JAYWAN";
        String fileId = null;
        Character forType = Character.valueOf('O');
        String xmlFilePath = null;
        try {
            FileFormatsEntity fileFormatEntity = this.fileFormatsRepo.findBySystemCodeAndTypeAndInstitutionCode(forCode, forType, institutionCode);
            Integer formatCode = fileFormatEntity == null ? 0 : fileFormatEntity.getCode();
            InterfacesEntity interfaces = this.interfacesRepo.findByInterfaceCategoryAndInstitutionCode(intCategory, institutionCode);
            Integer intCode = interfaces == null ? 0 : interfaces.getInterfaceCode();
            List results = this.outFileRepo.findByFormatCodeAndGeneratedStatusIn(formatCode, new Integer[]{1, 9});
            if (!results.isEmpty()) {
                return "File Generation already Scheduled";
            }
            Integer outgoingLogSerialNumber = this.inserOutFileLog(results, user, institutionCode, intCode, formatCode);
            LocalDate currentDate = LocalDate.now();
            int year = currentDate.getYear();
            int dayOfYear = currentDate.getDayOfYear();
            String julianDateStr = String.format("%02d%03d", year % 100, dayOfYear);
            fileSequence = this.updateAcquirerBinSequence(institutionCode);
            fileId = this.generateFileID(institutionCode, julianDateStr, fileSequence);
            fileName = fileId + ".xml";
            List entities = new ArrayList();
            entities = fromDate == null ? this.txnWorkRepo.findByInstitutionCodeAndIntCodeAndGenStatusAndLocalDateTimeLessThanEqual(institutionCode, intCode, Integer.valueOf(3), toDate) : this.txnWorkRepo.findByInstitutionCodeAndIntCodeAndGenStatusAndLocalDateTimeBetween(institutionCode, intCode, Integer.valueOf(3), fromDate, toDate);
            log.info("outgoingLogSerialNumber:" + outgoingLogSerialNumber);
            log.info("fileName:" + fileName);
            entities.forEach(entity -> entity.setGenStatus(Integer.valueOf(entity.getGenStatus() == 3 ? 9 : 4)));
            this.txnWorkRepo.saveAll(entities);
            this.updateOutFilelog(fileName, fileId, institutionCode, outgoingLogSerialNumber);
            Set encryptedCardTokens = entities.stream().map(JaywanAcqTxnWorkEntity::getEncryptedCardNumber).collect(Collectors.toSet());
            DecryptResponseVo response = this.cryptApi.getCardNumber(encryptedCardTokens);
            log.info("response :" + String.valueOf(response));
            if (Objects.nonNull(response) && Objects.nonNull(response.getCardNumbers())) {
                JaywanXmlWrapperValueObject wrapper = this.mapToXmlWrapper(entities, institutionCode, user, forCode, response, fileId);
                try {
                    xmlFilePath = this.generateXmlFile(wrapper, insShortName, fileName);
                    log.info("Generated XML file: {}", (Object)xmlFilePath);
                }
                catch (Exception e) {
                    log.error("Error generating XML file", (Throwable)e);
                    throw new RuntimeException("XML generation failed", e);
                }
            } else {
                entities.forEach(entity -> entity.setGenStatus(Integer.valueOf(7)));
                this.txnWorkRepo.saveAll(entities);
                return "Outgoing Failed";
            }
            if (Objects.nonNull(xmlFilePath) && Objects.nonNull(fileName)) {
                this.updateOutFilelog(fileName, fileName, institutionCode, outgoingLogSerialNumber);
                entities.forEach(entity -> entity.setGenStatus(Integer.valueOf(entity.getGenStatus() == 3 ? 9 : 4)));
                this.txnWorkRepo.saveAll(entities);
                this.insertOutgoingSummary(user, institutionCode, intCode, fileName, outgoingLogSerialNumber);
                this.posTxnRepo.completeJaywanPosStatus(institutionCode.intValue());
                this.moveWorkToData(institutionCode, entities, fileName);
                this.txnWorkRepo.deleteAll(entities);
                OutGoingFileProcessingEntity outGoingFileProcEntity = this.outFileRepo.findByInstitutionCodeAndSerialNumber(institutionCode, outgoingLogSerialNumber);
                this.iOutGoingSummaryService.generateOutgoingSummaryPDF(user.intValue(), institutionCode.intValue(), intCode.intValue(), outgoingLogSerialNumber.intValue(), intCategory, insShortName, outGoingFileProcEntity, "");
                return "Success";
            }
            return "Outgoing Failed";
        }
        catch (Exception e) {
            log.error("generateJaywanOutgoing()", (Throwable)e);
            return "Failed";
        }
    }

    private int updateAcquirerBinSequence(Integer institutionCode) {
        List acqBinList = this.acqBinRepo.findByInstitutionCodeAndBinType(institutionCode, "J");
        if (acqBinList.isEmpty()) {
            throw new IllegalStateException("No Acquirer Bins available");
        }
        AcquirerBinsEntity acqBin = (AcquirerBinsEntity)acqBinList.get(0);
        int fileSequence = acqBin.getOutfileDate() != null && acqBin.getOutfileDate().toLocalDate().equals(LocalDate.now()) ? acqBin.getOutFileSeq() : 1;
        acqBin.setOutFileSeq(Integer.valueOf(fileSequence + 1));
        acqBin.setOutfileDate(LocalDateTime.now());
        this.acqBinRepo.saveAndFlush((Object)acqBin);
        return fileSequence;
    }

    private String generateFileID(Integer institutionCode, String julianDateStr, int fileSequence) {
        List acqBinList = this.acqBinRepo.findByInstitutionCodeAndBinType(institutionCode, "J");
        String participantId = ((AcquirerBinsEntity)acqBinList.get(0)).getParticipantID();
        return "000" + participantId + julianDateStr + fileSequence;
    }

    private void updateOutFilelog(String fileName, String fileId, Integer insCode, Integer outgoingLogSerialNumber) {
        try {
            OutGoingFileProcessingEntity outGoingFileProcEntity = this.outFileRepo.findByInstitutionCodeAndSerialNumber(insCode, outgoingLogSerialNumber);
            outGoingFileProcEntity.setLastUpdatedDate(LocalDateTime.now());
            outGoingFileProcEntity.setFileName(fileName);
            if (Objects.nonNull(fileId)) {
                outGoingFileProcEntity.setFileId(fileId);
                outGoingFileProcEntity.setGeneratedStatus(fileId == null ? 5 : 4);
            }
            this.outFileRepo.saveAndFlush((Object)outGoingFileProcEntity);
        }
        catch (Exception e) {
            log.error("Error : updateOutFilelog() :", (Throwable)e);
        }
    }

    private JaywanXmlWrapperValueObject mapToXmlWrapper(List<JaywanAcqTxnWorkEntity> entities, Integer institutionCode, Integer user, Integer forCode, DecryptResponseVo response, String fileId) {
        JaywanXmlHeaderValueObject header = this.mapToHeader(entities, institutionCode, fileId);
        int recordCounter = 2;
        double totalTxnAmount = 0.0;
        ArrayList<JaywanXmlTransactionsValueObject> transactions = new ArrayList<JaywanXmlTransactionsValueObject>();
        for (JaywanAcqTxnWorkEntity entity : entities) {
            JaywanXmlTransactionsValueObject txn = this.mapEntityToTransaction(entity, response, recordCounter);
            if (txn == null) continue;
            transactions.add(txn);
            totalTxnAmount += entity.getTxnAmount() * 100.0;
            ++recordCounter;
        }
        JaywanXmlTransactionsBlockValueObject txnBlock = new JaywanXmlTransactionsBlockValueObject();
        txnBlock.setTransactions(transactions);
        JaywanXmlTrailerValueObject trailer = this.mapToTrailer(transactions.size(), recordCounter, fileId, totalTxnAmount);
        JaywanXmlWrapperValueObject wrapper = new JaywanXmlWrapperValueObject();
        wrapper.setHeader(header);
        wrapper.setTxnBlock(txnBlock);
        wrapper.setTrailer(trailer);
        return wrapper;
    }

    private JaywanXmlHeaderValueObject mapToHeader(List<JaywanAcqTxnWorkEntity> entities, Integer institutionCode, String fileId) {
        JaywanXmlHeaderValueObject header = new JaywanXmlHeaderValueObject();
        List acqBinList = this.acqBinRepo.findByInstitutionCodeAndBinType(institutionCode, "J");
        String participantId = ((AcquirerBinsEntity)acqBinList.get(0)).getParticipantID();
        String productCode = this.env.getProperty("PRODUCT_CODE");
        String fileCategory = this.env.getProperty("FILE_CATEGORY");
        String versionNumber = this.env.getProperty("VERSION_NUMBER");
        header.setNMTI("1644");
        header.setNFunCd("670");
        header.setNRecNum("00000001");
        header.setNDtTmFlGen(LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMddhhmmss")));
        header.setNDtSet(entities.get(0).getSettlDate() != null ? entities.get(0).getSettlDate().format(DateTimeFormatter.ofPattern("yyMMdd")) : "0".repeat(6));
        header.setNMemInstCd(participantId);
        header.setNUnFlNm(fileId);
        header.setNProdCd(productCode);
        header.setNFlCatg(fileCategory);
        header.setNVerNum(versionNumber);
        header.setNFlRejInd("N");
        header.setNFlRejRsnCd(null);
        return header;
    }

    private JaywanXmlTrailerValueObject mapToTrailer(int transactionCount, int recordCounter, String fileId, double totalTxnAmount) {
        JaywanXmlTrailerValueObject trailer = new JaywanXmlTrailerValueObject();
        trailer.setNMTI("1644");
        trailer.setNFunCd("671");
        trailer.setNRecNum(String.format("%08d", recordCounter));
        trailer.setNUnFlNm(fileId);
        trailer.setNTxnCnt(String.valueOf(transactionCount));
        trailer.setNRnTtlAmt(String.valueOf(totalTxnAmount));
        return trailer;
    }

    private JaywanXmlTransactionsValueObject mapEntityToTransaction(JaywanAcqTxnWorkEntity entity, DecryptResponseVo response, int recordNumber) {
        JaywanXmlTransactionsValueObject txn = new JaywanXmlTransactionsValueObject();
        String rrn = entity.getRrn();
        String decryptedCardNumber = (String)response.getCardNumbers().get(entity.getEncryptedCardNumber());
        if (Objects.nonNull(decryptedCardNumber)) {
            txn.setNMTI(entity.getMessageTypeId());
            txn.setNFunCd(entity.getFunctionCode());
            txn.setNRecNum(String.format("%08d", recordNumber));
            txn.setNDtTmLcTxn(entity.getLocalDateTime().format(DateTimeFormatter.ofPattern("YYMMDDhhmmss")));
            txn.setNPAN(decryptedCardNumber);
            txn.setNAcqInstCd(entity.getAcqinstIdCode());
            txn.setNApprvlCd(entity.getApprovalCode());
            txn.setNCrdAcptTrmId(entity.getTerminalId());
            txn.setNAmtTxn(entity.getTxnAmount() != null ? String.format("%012d", (long)(entity.getTxnAmount() * 100.0)) : "000000000000");
            txn.setNCcyCdTxn(entity.getTxnCurCode());
            txn.setNTxnOrgInstCd(String.valueOf(entity.getInstitutionCode()));
            txn.setNTxnDesInstCd("");
            txn.setNUnFlNm(entity.getFileID());
            txn.setNDtSet("");
            txn.setNSetDCInd(Objects.nonNull(entity.getCardType()) ? String.valueOf(entity.getCardType()) : "");
            if (!"8144".equals(entity.getMessageTypeId())) {
                txn.setNARD(entity.getAcqRefData());
                txn.setNAmtSet(entity.getSettledAmount() != null ? String.format("%012d", entity.getSettledAmount().longValue()) : "000000000000");
                txn.setNCcyCdSet(entity.getTxnCurCode());
                txn.setNConvRtSet(Objects.nonNull(entity.getConvRate()) ? String.valueOf(entity.getConvRate()) : "");
                txn.setNAmtBil(entity.getBillAmount() != null ? String.format("%012d", entity.getBillAmount().longValue()) : "000000000000");
                txn.setNConvRtBil("");
                txn.setNCcyCdBil("");
                txn.setNProcCd(entity.getTxnType());
                if (!("1240".equals(entity.getMessageTypeId()) || "263".equals(entity.getFunctionCode()) || "269".equals(entity.getFunctionCode()))) {
                    txn.setNAddData("");
                } else {
                    txn.setNAddData(null);
                }
            } else {
                txn.setNRRN(entity.getRrn());
                txn.setNAmtSet(null);
                txn.setNCcyCdSet(null);
                txn.setNConvRtSet(null);
                txn.setNAmtBil(null);
                txn.setNConvRtBil(null);
                txn.setNCcyCdBil(null);
                txn.setNProcCd(null);
            }
            if ("1240".equals(entity.getMessageTypeId()) && "200".equals(entity.getFunctionCode())) {
                txn.setNLtPrsntInd("Y");
                txn.setNECIInd(entity.getMotoEcomIndicator());
                txn.setNPosEntMode(entity.getPosEntryMode());
                txn.setNPosCondCd("");
                txn.setNActnCd("");
            } else {
                txn.setNLtPrsntInd(null);
                txn.setNECIInd(null);
            }
            txn.setNProcSts("S");
            txn.setNRejRsnCd("");
            txn.setNCrdAcpIDCd(entity.getMerchantId());
            txn.setNCrdAcpNm(entity.getMeName());
            txn.setNCrdAcpCity(entity.getMeCity());
            txn.setNCrdAcpStNm("");
            txn.setNCrdAcpCtryCd(entity.getMeCountry());
            if (!"1240".equals(entity.getMessageTypeId()) && !"263".equals(entity.getFunctionCode())) {
                txn.setNRecrPymtCd("");
            } else {
                txn.setNRecrPymtCd(null);
            }
            txn.setNCrdAcpBussCd(entity.getMcc());
            if ("1240".equals(entity.getMessageTypeId())) {
                String functionCode = entity.getFunctionCode();
                if ("262".equals(functionCode)) {
                    txn.setNPosEntMode(entity.getPosEntryMode());
                    txn.setNPosCondCd("");
                    txn.setNActnCd("");
                }
                if ("269".equals(functionCode)) {
                    txn.setNFulParInd("");
                }
                if ("263".equals(functionCode) || "269".equals(functionCode)) {
                    txn.setNIntrnTrackNum(entity.getFileID());
                }
            } else {
                txn.setNPosEntMode(null);
                txn.setNPosCondCd(null);
                txn.setNActnCd(null);
                txn.setNFulParInd(null);
                txn.setNIntrnTrackNum(null);
            }
            return txn;
        }
        this.updateFailedTxn(rrn);
        return null;
    }

    private String generateXmlFile(JaywanXmlWrapperValueObject wrapper, String insShortName, String fileName) throws IOException {
        try {
            String filePath = this.env.getProperty("RECON_OUT_" + insShortName);
            File file = new File(filePath + File.separator + fileName);
            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true);
            xmlMapper.getFactory().getXMLOutputFactory().setProperty("com.ctc.wstx.useDoubleQuotesInXmlDecl", true);
            String xmlContent = xmlMapper.writerWithDefaultPrettyPrinter().writeValueAsString((Object)wrapper);
            xmlContent = xmlContent.replace("?>", " standalone=\"no\"?>");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file));){
                writer.write(xmlContent);
            }
            return file.getAbsolutePath();
        }
        catch (Exception e) {
            log.error("Error in generateXmlFile():", (Throwable)e);
            return null;
        }
    }

    private void insertOutgoingSummary(Integer user, Integer insCode, Integer intCode, String fileName, Integer outgoingLogSerialNumber) {
        try {
            List txnEntities = this.txnWorkRepo.findByInstitutionCodeAndIntCodeAndGenStatus(insCode, intCode, Integer.valueOf(4));
            if (!txnEntities.isEmpty()) {
                Map<String, Map> groupedData = txnEntities.stream().collect(Collectors.groupingBy(JaywanAcqTxnWorkEntity::getFunctionCode, Collectors.collectingAndThen(Collectors.toList(), list -> Map.of("count", Double.valueOf(list.size()), "totalTxnAmount", list.stream().mapToDouble(JaywanAcqTxnWorkEntity::getTxnAmount).sum(), "totalSurchargeAmount", list.stream().mapToDouble(JaywanAcqTxnWorkEntity::getSurchargeAmount).sum()))));
                groupedData.forEach((messageTypeId, totals) -> {
                    double totalNetAmount = (Double)totals.get("totalTxnAmount") + (Double)totals.get("totalSurchargeAmount");
                    OutgoingSummaryEntity otsEntity = new OutgoingSummaryEntity();
                    otsEntity.setLastupdated(LocalDateTime.now());
                    otsEntity.setUpdatedUser(user.intValue());
                    otsEntity.setInstitution(insCode.intValue());
                    otsEntity.setInterfaceCode(intCode.intValue());
                    otsEntity.setOutFileDate(LocalDate.now());
                    otsEntity.setFileId(fileName);
                    otsEntity.setRefSerialNumber(outgoingLogSerialNumber.intValue());
                    otsEntity.setMessageTypeId(messageTypeId);
                    otsEntity.setFunctionCode("1");
                    otsEntity.setProcCode("");
                    otsEntity.setCount(Integer.valueOf(((Double)totals.get("count")).intValue()));
                    otsEntity.setTxnAmount((Double)totals.get("totalTxnAmount"));
                    otsEntity.setSurchargeAmount((Double)totals.get("totalSurchargeAmount"));
                    otsEntity.setNetAmount(Double.valueOf(totalNetAmount));
                    otsEntity.setGeneralStatus(Integer.valueOf(3));
                    this.summaryRepo.saveAndFlush((Object)otsEntity);
                });
            }
        }
        catch (Exception e) {
            log.error("Error in insertJaywanOutgoingSummary(): ", (Throwable)e);
        }
    }

    private Integer inserOutFileLog(List<OutGoingFileProcessingEntity> results, Integer user, Integer insCode, Integer intCode, Integer forCode) {
        OutGoingFileProcessingEntity entity = new OutGoingFileProcessingEntity();
        try {
            entity.setLastUpdatedDate(LocalDateTime.now());
            entity.setGeneratedDate(LocalDateTime.now());
            entity.setLastUpdatedUser(user.intValue());
            entity.setInstitutionCode(insCode.intValue());
            entity.setInterfaceCode(intCode);
            entity.setFormatCode(forCode);
            entity.setGeneratedStatus(9);
            List result = this.businessDateRepo.findByInstitutionCode(1);
            if (!result.isEmpty()) {
                BusinessDateEntity businessDateEntity = (BusinessDateEntity)result.get(0);
                entity.setBusinessDate(businessDateEntity.getBusinessDate());
            }
            Integer serialNumber = ((OutGoingFileProcessingEntity)this.outFileRepo.saveAndFlush((Object)entity)).getSerialNumber();
            return serialNumber;
        }
        catch (Exception e) {
            log.error("inserOutFileLog() : ", (Throwable)e);
            return 0;
        }
    }

    private void moveWorkToData(Integer insCode, List<JaywanAcqTxnWorkEntity> entities, String fileName) {
        try {
            ArrayList<JaywanAcqTxnDataEntity> jaywanEntities = new ArrayList<JaywanAcqTxnDataEntity>();
            int batchSize = this.calculateBatchSize((long)entities.size());
            if (Objects.nonNull(entities)) {
                for (JaywanAcqTxnWorkEntity loop : entities) {
                    JaywanAcqTxnDataEntity entity = new JaywanAcqTxnDataEntity();
                    entity.setSerialNumber(loop.getSerialNumber());
                    entity.setLastUpdated(LocalDateTime.now());
                    entity.setUpdatedUser(loop.getUpdatedUser());
                    entity.setInstitutionCode(loop.getInstitutionCode());
                    entity.setIntCode(loop.getIntCode());
                    entity.setPrjSerNumber(loop.getPrjSerNumber());
                    entity.setTxnRefNumber(loop.getTxnRefNumber());
                    entity.setTxnType(loop.getTxnType());
                    entity.setTxnCode(loop.getTxnCode());
                    entity.setGenStatus(loop.getGenStatus());
                    entity.setMessageTypeId(loop.getMessageTypeId());
                    entity.setFunctionCode(loop.getFunctionCode());
                    entity.setLocalDateTime(loop.getLocalDateTime());
                    entity.setCardNumber(loop.getCardNumber());
                    entity.setAcqRefData(loop.getAcqRefData());
                    entity.setApprovalCode(loop.getApprovalCode());
                    entity.setTerminalId(loop.getTerminalId());
                    entity.setTxnAmount(loop.getTxnAmount());
                    entity.setSettledAmount(loop.getSettledAmount());
                    entity.setBillAmount(loop.getBillAmount());
                    entity.setSurchargeAmount(loop.getSurchargeAmount());
                    entity.setConvRate(loop.getConvRate());
                    entity.setTxnCurCode(loop.getTxnCurCode());
                    entity.setCashBackAmount(loop.getCashBackAmount());
                    entity.setRrn(loop.getRrn());
                    entity.setMerchantId(loop.getMerchantId());
                    entity.setMeName(loop.getMeName());
                    entity.setMeCity(loop.getMeCity());
                    entity.setMeStateCode(loop.getMeStateCode());
                    entity.setMeCountry(loop.getMeCountry());
                    entity.setMcc(loop.getMcc());
                    entity.setPosEntryMode(loop.getPosEntryMode());
                    entity.setAcqinstIdCode(loop.getAcqinstIdCode());
                    entity.setRevIndiCator(loop.getRevIndiCator());
                    entity.setCardDomIntlFlag(loop.getCardDomIntlFlag());
                    entity.setTrlType(loop.getTrlType());
                    entity.setMeCategoryType(loop.getMeCategoryType());
                    entity.setCardType(loop.getCardType());
                    entity.setDmsSmsMode(loop.getDmsSmsMode());
                    entity.setCentreProcDate(loop.getCentreProcDate());
                    entity.setFileProcDate(loop.getFileProcDate());
                    entity.setFileID(fileName);
                    entity.setEncryptedCardNumber(loop.getEncryptedCardNumber());
                    entity.setResponseCode(loop.getResponseCode());
                    entity.setMotoEcomIndicator(loop.getMotoEcomIndicator());
                    jaywanEntities.add(entity);
                    if (jaywanEntities.size() < batchSize) continue;
                    this.txnDataRepo.saveAllAndFlush(jaywanEntities);
                    jaywanEntities.clear();
                }
                if (!jaywanEntities.isEmpty()) {
                    this.txnDataRepo.saveAllAndFlush(jaywanEntities);
                }
            }
        }
        catch (Exception e) {
            log.error("Error : moveWorkToData() : ", (Throwable)e);
        }
    }

    @Transactional
    private void updateFailedTxn(String rrn) {
        try {
            List jaywanEntities = this.txnWorkRepo.findByRrn(rrn);
            if (jaywanEntities.isEmpty()) {
                log.warn("No visaEntities found for refNumber: {}", (Object)rrn);
                return;
            }
            jaywanEntities.forEach(entity -> entity.setGenStatus(Integer.valueOf(7)));
            this.txnWorkRepo.saveAllAndFlush((Iterable)jaywanEntities);
            log.info("Successfully updated genStatus for all matching jaywanEntities.");
        }
        catch (Exception e) {
            log.error("Error in updateFailedTxn for refNumber {}: {}", new Object[]{rrn, e.getMessage(), e});
        }
    }

    private int calculateBatchSize(long totalFileLineCount) {
        int maxBatchSize = 500;
        int minBatchSize = 100;
        long calculatedBatchSize = totalFileLineCount / 10L;
        if (calculatedBatchSize > (long)maxBatchSize) {
            return maxBatchSize;
        }
        if (calculatedBatchSize < (long)minBatchSize) {
            return minBatchSize;
        }
        return (int)calculatedBatchSize;
    }

    public JaywanOutgoingServiceImpl(AcquirerBinsRepo acqBinRepo, JWNAcqTxnWorkRepo txnWorkRepo, Environment env, InterfacesRepo interfacesRepo, FileFormatsRepo fileFormatsRepo, OutFileLogRepo outFileRepo, BusinessDateRepo businessDateRepo, OutgoingSummaryRepo summaryRepo, JWNAcqTxnDataRepo txnDataRepo, CryptAPI cryptApi, PosTransactionRepo posTxnRepo, IOutGoingSummaryService iOutGoingSummaryService) {
        this.acqBinRepo = acqBinRepo;
        this.txnWorkRepo = txnWorkRepo;
        this.env = env;
        this.interfacesRepo = interfacesRepo;
        this.fileFormatsRepo = fileFormatsRepo;
        this.outFileRepo = outFileRepo;
        this.businessDateRepo = businessDateRepo;
        this.summaryRepo = summaryRepo;
        this.txnDataRepo = txnDataRepo;
        this.cryptApi = cryptApi;
        this.posTxnRepo = posTxnRepo;
        this.iOutGoingSummaryService = iOutGoingSummaryService;
    }
}

