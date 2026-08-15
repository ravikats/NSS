/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.amex.entities.AmexAcqTxnDataEntity
 *  com.empay.amex.entities.AmexAcqTxnWorkEntity
 *  com.empay.amex.repo.AmexTxnDataRepo
 *  com.empay.amex.repo.AmexTxnWorkRepo
 *  com.empay.amex.service.AmexGFSGOutgoingService
 *  com.empay.amex.service.AmexOutgoingService
 *  com.empay.amex.serviceImpl.AmexOutgoingServiceImpl
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
 *  com.empay.staging.entities.AcquirerBinsEntity
 *  com.empay.staging.repo.AcquirerBinsRepo
 *  com.empay.staging.repo.PosTransactionRepo
 *  com.empay.staging.service.IOutGoingSummaryService
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 *  org.springframework.core.env.Environment
 *  org.springframework.stereotype.Service
 *  org.springframework.transaction.annotation.Transactional
 */
package com.empay.amex.serviceImpl;

import com.empay.amex.entities.AmexAcqTxnDataEntity;
import com.empay.amex.entities.AmexAcqTxnWorkEntity;
import com.empay.amex.repo.AmexTxnDataRepo;
import com.empay.amex.repo.AmexTxnWorkRepo;
import com.empay.amex.service.AmexGFSGOutgoingService;
import com.empay.amex.service.AmexOutgoingService;
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
import com.empay.staging.entities.AcquirerBinsEntity;
import com.empay.staging.repo.AcquirerBinsRepo;
import com.empay.staging.repo.PosTransactionRepo;
import com.empay.staging.service.IOutGoingSummaryService;
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
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AmexOutgoingServiceImpl
implements AmexOutgoingService {
    private static final Logger log = LoggerFactory.getLogger(AmexOutgoingServiceImpl.class);
    private final Environment env;
    private final OutgoingSummaryRepo summaryRepo;
    private final AmexTxnWorkRepo amexTxnWorkRepo;
    private final AmexTxnDataRepo amexTxnDataRepo;
    private final FileFormatsRepo fileFormatsRepo;
    private final InterfacesRepo interfacesRepo;
    private final OutFileLogRepo outFileRepo;
    private final BusinessDateRepo businessDateRepo;
    private final AcquirerBinsRepo acqBinRepo;
    private final CryptAPI cryptApi;
    private final IOutGoingSummaryService iOutGoingSummaryService;
    private final AmexGFSGOutgoingService amexGFSGOutgoingService;
    private final PosTransactionRepo posTxnRepo;

    @Transactional
    public String generateAmexOutgoing(Integer institutionCode, Integer user, Integer forCode, String insShortName, LocalDateTime fromDate, LocalDateTime toDate) {
        String fileName = null;
        int fileSequence = 0;
        String intCategory = "AMEX";
        Object fileId = null;
        Character forType = Character.valueOf('O');
        int outgoingLogSerialNumber = 0;
        try {
            FileFormatsEntity fileFormatEntity = this.fileFormatsRepo.findBySystemCodeAndTypeAndInstitutionCode(forCode, forType, institutionCode);
            Integer formatCode = fileFormatEntity == null ? 0 : fileFormatEntity.getCode();
            InterfacesEntity interfaces = this.interfacesRepo.findByInterfaceCategoryAndInstitutionCode(intCategory, institutionCode);
            Integer intCode = interfaces == null ? 0 : interfaces.getInterfaceCode();
            List results = this.outFileRepo.findByFormatCodeAndGeneratedStatusIn(formatCode, new Integer[]{1, 9});
            if (!results.isEmpty()) {
                return "File Generation already Scheduled";
            }
            outgoingLogSerialNumber = this.inserOutFileLog(user, institutionCode, intCode, formatCode);
            LocalDate date = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            String currentDate = date.format(formatter);
            String julianDate = this.getJulianDate();
            fileSequence = this.updateAcquirerBinSequence(institutionCode);
            fileId = "IT" + julianDate + String.format("%02d", fileSequence);
            fileName = "AMEX_FSF_VAPAY000001_" + currentDate + "_" + String.format("%02d", fileSequence);
            log.info("fileName:" + fileName);
            List amexData = new ArrayList();
            amexData = fromDate == null ? this.amexTxnWorkRepo.findByInstitutionCodeAndIntCodeAndGenStatusAndLocalDateTimeLessThanEqualOrderByMerchantIdAscTerminalIdAscTrlBthNumberAscMappedMidAsc(institutionCode, intCode, 3, toDate) : this.amexTxnWorkRepo.findByInstitutionCodeAndIntCodeAndGenStatusAndLocalDateTimeBetweenOrderByMerchantIdAscTerminalIdAscTrlBthNumberAscMappedMidAsc(institutionCode, intCode, 3, fromDate, toDate);
            this.updateAmexAcqWork(institutionCode, user, intCode, (String)fileId, 3, fromDate, toDate);
            this.updateOutFilelog(fileName, (String)fileId, institutionCode, Integer.valueOf(outgoingLogSerialNumber));
            Set encryptedCardTokens = amexData.stream().map(AmexAcqTxnWorkEntity::getEncryptedCardNumber).collect(Collectors.toSet());
            DecryptResponseVo response = this.cryptApi.getCardNumber(encryptedCardTokens);
            log.info("response :" + String.valueOf(response));
            List linesList = new ArrayList();
            if (Objects.nonNull(response) && Objects.nonNull(response.getCardNumbers())) {
                linesList = this.amexGFSGOutgoingService.amexOutData(amexData, fileSequence, (String)fileId, response);
                if (Objects.nonNull(linesList) && !linesList.isEmpty()) {
                    fileId = this.writeLinesToFile(linesList, insShortName, fileName);
                }
            } else {
                amexData.forEach(entity -> entity.setGenStatus(Integer.valueOf(7)));
                this.amexTxnWorkRepo.saveAll(amexData);
                this.updateOutFilelog(fileName, null, institutionCode, Integer.valueOf(outgoingLogSerialNumber));
                return "Outgoing Failed";
            }
            if (Objects.nonNull(fileId) && Objects.nonNull(linesList)) {
                this.updateOutFilelog(fileName, (String)fileId, institutionCode, Integer.valueOf(outgoingLogSerialNumber));
                this.insertIntoOutgoingSummary(user, institutionCode, intCode, (String)fileId, Integer.valueOf(outgoingLogSerialNumber));
                this.updateAmexAcqWork(institutionCode, user, intCode, (String)fileId, 9, fromDate, toDate);
                this.posTxnRepo.completeAmexPosStatus(institutionCode.intValue());
                this.moveWorkToData(institutionCode, amexData, intCode);
                this.amexTxnWorkRepo.deleteAll(amexData);
                OutGoingFileProcessingEntity outGoingFileProcEntity = this.outFileRepo.findByInstitutionCodeAndSerialNumber(institutionCode, Integer.valueOf(outgoingLogSerialNumber));
                List result = this.businessDateRepo.findByInstitutionCode(institutionCode.intValue());
                outGoingFileProcEntity.setFileName(fileName);
                outGoingFileProcEntity.setBusinessDate(!result.isEmpty() ? ((BusinessDateEntity)result.get(0)).getBusinessDate() : null);
                outGoingFileProcEntity.setFileId((String)fileId);
                this.iOutGoingSummaryService.generateOutgoingSummaryPDF(user.intValue(), institutionCode.intValue(), intCode.intValue(), outgoingLogSerialNumber, intCategory, insShortName, outGoingFileProcEntity, "");
                return "Success";
            }
            this.updateOutFilelog(fileName, null, institutionCode, Integer.valueOf(outgoingLogSerialNumber));
            return "Outgoing Failed";
        }
        catch (Exception e) {
            log.error("generateAmexOutgoing()", (Throwable)e);
            if (outgoingLogSerialNumber != 0) {
                this.updateOutFilelog(fileName, null, institutionCode, Integer.valueOf(outgoingLogSerialNumber));
            }
            return "Failed";
        }
    }

    private String writeLinesToFile(List<StringBuilder> lines, String insShortName, String fileName) {
        try {
            String filePath = this.env.getProperty("RECON_OUT_" + insShortName);
            File file = new File(filePath + fileName);
            if (Objects.nonNull(lines)) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file));){
                    lines.stream().map(StringBuilder::toString).forEach(line -> {
                        try {
                            writer.write((String)line);
                            writer.newLine();
                        }
                        catch (IOException e) {
                            log.error("Error in writeLinesToFile():", (Throwable)e);
                        }
                    });
                }
                return fileName;
            }
            return null;
        }
        catch (IOException e) {
            log.error("Error in writeLinesToFile():", (Throwable)e);
            return null;
        }
    }

    private void insertIntoOutgoingSummary(Integer user, Integer institutionCode, Integer intCode, String fileId, Integer outgoingLogSerialNumber) {
        try {
            List amexTxnEntity = this.amexTxnWorkRepo.findByInstitutionCodeAndGenStatus(institutionCode, 9);
            Map<String, Map> groupedData = amexTxnEntity.stream().collect(Collectors.groupingBy(txn -> txn.getProcCode().substring(0, 2), Collectors.collectingAndThen(Collectors.toList(), list -> {
                double count = list.size();
                double txnSum = list.stream().mapToDouble(e -> Optional.ofNullable(e.getTxnAmount()).orElse(0.0)).sum();
                double schgSum = list.stream().mapToDouble(e -> Optional.ofNullable(e.getSurchargeAmount()).orElse(0.0)).sum();
                return Map.of("count", count, "txnSum", txnSum, "schgSum", schgSum);
            })));
            groupedData.forEach((procCode, totals) -> {
                OutgoingSummaryEntity summary = new OutgoingSummaryEntity();
                summary.setLastupdated(LocalDateTime.now());
                summary.setUpdatedUser(user.intValue());
                summary.setInstitution(institutionCode.intValue());
                summary.setInterfaceCode(intCode.intValue());
                summary.setOutFileDate(LocalDate.now());
                summary.setFileId(fileId);
                summary.setRefSerialNumber(outgoingLogSerialNumber.intValue());
                summary.setFunctionCode(null);
                summary.setMessageTypeId(null);
                summary.setProcCode(procCode);
                summary.setCount(Integer.valueOf(((Double)totals.get("count")).intValue()));
                summary.setTxnAmount((Double)totals.get("txnSum"));
                summary.setSurchargeAmount((Double)totals.get("schgSum"));
                summary.setNetAmount(Double.valueOf((Double)totals.get("txnSum") + (Double)totals.get("schgSum")));
                summary.setGeneralStatus(Integer.valueOf(3));
                this.summaryRepo.save((Object)summary);
            });
        }
        catch (Exception e) {
            log.error("Error in insertIntoOutgoingSummary(): ", (Throwable)e);
        }
    }

    private void updateAmexAcqWork(Integer institutionCode, Integer user, Integer intCode, String fileId, int currentStatus, LocalDateTime fromDate, LocalDateTime toDate) {
        try {
            List amexEntities = new ArrayList();
            amexEntities = fromDate == null ? this.amexTxnWorkRepo.findByInstitutionCodeAndIntCodeAndGenStatusAndLocalDateTimeLessThanEqual(institutionCode, intCode, currentStatus, toDate) : this.amexTxnWorkRepo.findByInstitutionCodeAndIntCodeAndGenStatusAndLocalDateTimeBetween(institutionCode, intCode, currentStatus, fromDate, toDate);
            log.info("amexEntities : " + amexEntities.size());
            if (amexEntities != null && !amexEntities.isEmpty()) {
                int updatedStatus = currentStatus == 3 ? 9 : 4;
                amexEntities.forEach(entity -> {
                    entity.setLastUpdated(LocalDateTime.now());
                    entity.setUpdatedUser(user);
                    entity.setGenStatus(Integer.valueOf(updatedStatus));
                    entity.setFileId(fileId);
                });
                this.amexTxnWorkRepo.saveAllAndFlush(amexEntities);
            } else {
                log.info("No Amex entities found for InstitutionCode");
            }
        }
        catch (Exception e) {
            log.error("Error updateAmexAcqWork()", (Throwable)e);
        }
    }

    private void moveWorkToData(Integer institutionCode, List<AmexAcqTxnWorkEntity> amexData, Integer intCode) {
        ArrayList<AmexAcqTxnDataEntity> amexAcqTxnDataEntities = new ArrayList<AmexAcqTxnDataEntity>();
        if (Objects.nonNull(amexData)) {
            for (AmexAcqTxnWorkEntity loop : amexData) {
                AmexAcqTxnDataEntity entity = new AmexAcqTxnDataEntity();
                entity.setSerNumber(loop.getSerNumber());
                entity.setLastUpdated(LocalDateTime.now());
                entity.setUpdatedUser(loop.getUpdatedUser());
                entity.setInstitutionCode(loop.getInstitutionCode());
                entity.setIntCode(loop.getIntCode());
                entity.setPrjSerNumber(loop.getPrjSerNumber());
                entity.setTxnRefSerNumber(loop.getTxnRefSerNumber());
                entity.setTxnType(loop.getTxnType());
                entity.setCardNumber(loop.getCardNumber());
                entity.setProcCode(loop.getProcCode());
                entity.setTxnAmount(loop.getTxnAmount());
                entity.setSurchargeAmount(loop.getSurchargeAmount());
                entity.setLocalDateTime(loop.getLocalDateTime());
                entity.setPosDataCode(loop.getPosDataCode());
                entity.setMcc(loop.getMcc());
                entity.setRrn(loop.getRrn());
                entity.setApprovalCode(loop.getApprovalCode());
                entity.setTerminalId(loop.getTerminalId());
                entity.setMerchantId(loop.getMerchantId());
                entity.setMappedMid(loop.getMappedMid());
                entity.setMeName(loop.getMeName());
                entity.setMeCity(loop.getMeCity());
                entity.setMePinCode(loop.getMePinCode());
                entity.setMeCountry(loop.getMeCountry());
                entity.setMotoEcomIndicator(loop.getMotoEcomIndicator());
                entity.setTxnCurCode(loop.getTxnCurCode());
                entity.setCardSeqNumber(loop.getCardSeqNumber());
                entity.setAppCryptogram(loop.getAppCryptogram());
                entity.setCryptInfoData(loop.getCryptInfoData());
                entity.setIssAppData(loop.getIssAppData());
                entity.setUpblNumber(loop.getUpblNumber());
                entity.setAppTxnCounter(loop.getAppTxnCounter());
                entity.setTrlVerResult(loop.getTrlVerResult());
                entity.setTxnDate(loop.getTxnDate());
                entity.setCryptAmount(loop.getCryptAmount());
                entity.setAppICProfile(loop.getAppICProfile());
                entity.setTrlConCode(loop.getTrlConCode());
                entity.setChipCashBack(loop.getChipCashBack());
                entity.setTxnId(loop.getTxnId());
                entity.setTrlBthNumber(loop.getTrlBthNumber());
                entity.setCardType(loop.getCardType());
                entity.setCardDomIntlFlag(loop.getCardDomIntlFlag());
                entity.setDmsSmsMode(loop.getDmsSmsMode());
                entity.setTrlType(loop.getTrlType());
                entity.setCentreProcDate(loop.getCentreProcDate());
                entity.setOutFileDate(loop.getOutFileDate());
                entity.setFileId(loop.getFileId());
                entity.setGenStatus(loop.getGenStatus());
                entity.setEncryptedCardNumber(loop.getEncryptedCardNumber());
                entity.setExpiryDate(loop.getExpiryDate());
                entity.setEmv(loop.getEmv());
                entity.setLocationAddress(loop.getLocationAddress());
                entity.setContactEmail(loop.getContactEmail());
                entity.setTrlLocation(loop.getTrlLocation());
                entity.setLocRegionCode(loop.getLocRegionCode());
                entity.setStan(loop.getStan());
                entity.setInvoiceNumber(loop.getInvoiceNumber());
                amexAcqTxnDataEntities.add(entity);
            }
            this.amexTxnDataRepo.saveAllAndFlush(amexAcqTxnDataEntities);
        }
    }

    private Integer inserOutFileLog(Integer user, Integer insCode, Integer intCode, Integer forCode) {
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

    private int updateAcquirerBinSequence(Integer institutionCode) {
        List acqBinList = this.acqBinRepo.findByInstitutionCodeAndBinType(institutionCode, "A");
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

    private String getJulianDate() {
        LocalDate currentDate = LocalDate.now();
        return String.format("%02d%03d", currentDate.getYear() % 100, currentDate.getDayOfYear());
    }

    public AmexOutgoingServiceImpl(Environment env, OutgoingSummaryRepo summaryRepo, AmexTxnWorkRepo amexTxnWorkRepo, AmexTxnDataRepo amexTxnDataRepo, FileFormatsRepo fileFormatsRepo, InterfacesRepo interfacesRepo, OutFileLogRepo outFileRepo, BusinessDateRepo businessDateRepo, AcquirerBinsRepo acqBinRepo, CryptAPI cryptApi, IOutGoingSummaryService iOutGoingSummaryService, AmexGFSGOutgoingService amexGFSGOutgoingService, PosTransactionRepo posTxnRepo) {
        this.env = env;
        this.summaryRepo = summaryRepo;
        this.amexTxnWorkRepo = amexTxnWorkRepo;
        this.amexTxnDataRepo = amexTxnDataRepo;
        this.fileFormatsRepo = fileFormatsRepo;
        this.interfacesRepo = interfacesRepo;
        this.outFileRepo = outFileRepo;
        this.businessDateRepo = businessDateRepo;
        this.acqBinRepo = acqBinRepo;
        this.cryptApi = cryptApi;
        this.iOutGoingSummaryService = iOutGoingSummaryService;
        this.amexGFSGOutgoingService = amexGFSGOutgoingService;
        this.posTxnRepo = posTxnRepo;
    }
}

