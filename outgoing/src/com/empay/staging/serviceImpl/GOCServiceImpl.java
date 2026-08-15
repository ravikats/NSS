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
 *  com.empay.staging.entities.AcquirerBinsEntity
 *  com.empay.staging.entities.VisaGOCDataEntity
 *  com.empay.staging.entities.VisaGOCWorkEntity
 *  com.empay.staging.repo.AcquirerBinsRepo
 *  com.empay.staging.repo.PosTransactionRepo
 *  com.empay.staging.repo.VisaGOCDataRepo
 *  com.empay.staging.repo.VisaGOCTxnRepo
 *  com.empay.staging.service.BaseIIGOCService
 *  com.empay.staging.service.GOCService
 *  com.empay.staging.service.IOutGoingSummaryService
 *  com.empay.staging.serviceImpl.GOCServiceImpl
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 *  org.springframework.core.env.Environment
 *  org.springframework.stereotype.Service
 *  org.springframework.transaction.annotation.Transactional
 */
package com.empay.staging.serviceImpl;

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
import com.empay.staging.entities.VisaGOCDataEntity;
import com.empay.staging.entities.VisaGOCWorkEntity;
import com.empay.staging.repo.AcquirerBinsRepo;
import com.empay.staging.repo.PosTransactionRepo;
import com.empay.staging.repo.VisaGOCDataRepo;
import com.empay.staging.repo.VisaGOCTxnRepo;
import com.empay.staging.service.BaseIIGOCService;
import com.empay.staging.service.GOCService;
import com.empay.staging.service.IOutGoingSummaryService;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
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
public class GOCServiceImpl
implements GOCService {
    private static final Logger log = LoggerFactory.getLogger(GOCServiceImpl.class);
    private final OutgoingSummaryRepo summaryRepo;
    private final FileFormatsRepo fileFormatsRepo;
    private final InterfacesRepo interfacesRepo;
    private final OutFileLogRepo outFileLogRepo;
    private final OutFileLogRepo outFileRepo;
    private final AcquirerBinsRepo acqBinRepo;
    private final VisaGOCTxnRepo visaGOCTxnRepo;
    private final CryptAPI cryptApi;
    private final PosTransactionRepo posTxnRepo;
    private final IOutGoingSummaryService iOutGoingSummaryService;
    private final BusinessDateRepo businessDateRepo;
    private final Environment env;
    private final BaseIIGOCService baseIIGOCService;
    private final VisaGOCDataRepo visaGOCDataRepo;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");

    @Transactional
    public String generateVisaCollectionOnly(Integer insCode, Integer user, Integer formatCode, String insShortName, LocalDateTime startDate, LocalDateTime toDate, String network) {
        String fileName = null;
        AcquirerBinsEntity acqBin = new AcquirerBinsEntity();
        int fileSequence = 0;
        String intCategory = "VISA";
        String fileId = null;
        Character forType = Character.valueOf('O');
        try {
            List data;
            List txnEntity;
            Integer[] status = new Integer[]{1, 9};
            FileFormatsEntity fileFormatEntity = this.fileFormatsRepo.findBySystemCodeAndTypeAndInstitutionCode(formatCode, forType, insCode);
            Integer forCode = fileFormatEntity == null ? 0 : fileFormatEntity.getCode();
            InterfacesEntity interfaces = this.interfacesRepo.findByInterfaceCategoryAndInstitutionCode(intCategory, insCode);
            Integer intCode = interfaces == null ? 0 : interfaces.getInterfaceCode();
            List results = this.outFileRepo.findByFormatCodeAndGeneratedStatusIn(forCode, status);
            if (!results.isEmpty()) {
                return "File Generation already Scheduled";
            }
            Integer outgoingLogSerialNumber = this.inserOutFileLog(results, user, insCode, intCode, forCode);
            List acqBinList = this.acqBinRepo.findByInstitutionCodeAndBinType(insCode, "V");
            if (!acqBinList.isEmpty() && (acqBin = (AcquirerBinsEntity)acqBinList.get(0)) != null) {
                fileSequence = acqBin.getOutfileDate() != null && acqBin.getOutfileDate().toLocalDate().equals(LocalDate.now()) ? acqBin.getOutFileSeq() : 1;
                acqBin.setOutFileSeq(Integer.valueOf(fileSequence + 1));
                acqBin.setOutfileDate(LocalDateTime.now());
                this.acqBinRepo.saveAndFlush((Object)acqBin);
            }
            fileName = insShortName + "_" + acqBin.getAcquirerBins() + "_" + LocalDate.now().format(this.formatter) + "." + String.format("%03d", fileSequence);
            log.info("outgoingLogSerialNumber:" + outgoingLogSerialNumber);
            log.info("fileName:" + fileName);
            List<String> txnCode = Arrays.asList("10", "20");
            if (startDate == null) {
                txnEntity = this.visaGOCTxnRepo.findByInstitutionCodeAndIntCodeAndGenStatusAndNetworkAndTxnCodeNotInAndPurchaseDateLessThanEqual(insCode, intCode, 3, network, txnCode, toDate);
                data = this.visaGOCTxnRepo.findByInstitutionCodeAndIntCodeAndGenStatusAndNetworkAndPurchaseDateLessThanEqual(insCode, intCode, 3, network, toDate);
            } else {
                txnEntity = this.visaGOCTxnRepo.findByInstitutionCodeAndIntCodeAndGenStatusAndNetworkAndTxnCodeNotInAndPurchaseDateBetween(insCode, intCode, 3, network, txnCode, startDate, toDate);
                data = this.visaGOCTxnRepo.findByInstitutionCodeAndIntCodeAndGenStatusAndNetworkAndPurchaseDateBetween(insCode, intCode, 3, network, startDate, toDate);
            }
            this.updateVisaGOCWork(insCode, user, intCode, "", Integer.valueOf(3), startDate, toDate, network);
            this.updateOutFilelog(fileName, fileId, insCode, outgoingLogSerialNumber);
            Set encryptedCardTokens = data.stream().map(VisaGOCWorkEntity::getEncCardNumber).collect(Collectors.toSet());
            DecryptResponseVo response = this.cryptApi.getCardNumber(encryptedCardTokens);
            List lines = new ArrayList();
            if (!Objects.nonNull(response) || !Objects.nonNull(response.getCardNumbers())) {
                data.forEach(entity -> entity.setGenStatus(Integer.valueOf(7)));
                this.visaGOCTxnRepo.saveAll((Iterable)data);
                this.updateOutFilelog(fileName, null, insCode, outgoingLogSerialNumber);
                return "Outgoing Failed";
            }
            lines = this.baseIIGOCService.getGOCTxnData(txnEntity, response, acqBin.getAcquirerBins(), fileSequence);
            fileId = this.writeLinesToFile(lines, insShortName, fileName);
            if (Objects.nonNull(fileId) && Objects.nonNull(lines)) {
                this.updateOutFilelog(fileName, fileId, insCode, outgoingLogSerialNumber);
                this.insertIntoOutgoingSummary(user, insCode, intCode, fileName, outgoingLogSerialNumber, network);
                this.updateVisaGOCWork(insCode, user, intCode, fileId, Integer.valueOf(9), startDate, toDate, network);
                this.posTxnRepo.completeVISAGocPosStatus(insCode.intValue());
                this.posTxnRepo.flush();
                this.moveWorkToData(insCode, Integer.valueOf(4), network);
                List result = this.businessDateRepo.findByInstitutionCode(insCode.intValue());
                OutGoingFileProcessingEntity outGoingFileProcEntity = new OutGoingFileProcessingEntity();
                outGoingFileProcEntity.setFileName(fileName);
                outGoingFileProcEntity.setBusinessDate(!result.isEmpty() ? ((BusinessDateEntity)result.get(0)).getBusinessDate() : null);
                outGoingFileProcEntity.setFileId(fileId);
                this.iOutGoingSummaryService.generateOutgoingSummaryPDF(user.intValue(), insCode.intValue(), intCode.intValue(), outgoingLogSerialNumber.intValue(), intCategory, insShortName, outGoingFileProcEntity, "GOC");
                return "Success";
            }
            return "Outgoing Failed";
        }
        catch (Exception e) {
            log.error("GenerateVisaOutgoing()", (Throwable)e);
            return "Failed";
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

    private void updateVisaGOCWork(Integer institutionCode, Integer userId, Integer intCode, String fileId, Integer currentStatus, LocalDateTime startDate, LocalDateTime toDate, String network) {
        try {
            List visaEntities = new ArrayList();
            visaEntities = startDate == null ? this.visaGOCTxnRepo.findByInstitutionCodeAndIntCodeAndGenStatusAndNetworkAndPurchaseDateLessThanEqual(institutionCode, intCode, currentStatus.intValue(), network, toDate) : this.visaGOCTxnRepo.findByInstitutionCodeAndIntCodeAndGenStatusAndNetworkAndPurchaseDateBetween(institutionCode, intCode, currentStatus.intValue(), network, startDate, toDate);
            log.info("visaEntities : " + visaEntities.size());
            if (visaEntities != null && !visaEntities.isEmpty()) {
                int updatedStatus = currentStatus == 3 ? 9 : 4;
                visaEntities.forEach(entity -> this.updateVisaEntity(entity, userId, Integer.valueOf(updatedStatus), fileId));
                this.visaGOCTxnRepo.saveAllAndFlush(visaEntities);
            } else {
                log.info("No Visa entities found for Institution");
            }
        }
        catch (Exception e) {
            log.error("Error in updateVisaAcqWork for InstitutionCode: {}, IntCode: {}, Status: {}. Details: ", new Object[]{institutionCode, intCode, currentStatus, e});
        }
    }

    private void updateVisaEntity(VisaGOCWorkEntity entity, Integer user, Integer genStatus, String fileId) {
        entity.setLastUpdated(LocalDateTime.now());
        entity.setUpdatedUser(user);
        entity.setGenStatus(genStatus);
        entity.setFileId(fileId);
    }

    private void updateOutFilelog(String fileName, String fileId, Integer insCode, Integer outgoingLogSerialNumber) {
        try {
            OutGoingFileProcessingEntity outGoingFileProcEntity = this.outFileRepo.findByInstitutionCodeAndSerialNumber(insCode, outgoingLogSerialNumber);
            outGoingFileProcEntity.setLastUpdatedDate(LocalDateTime.now());
            outGoingFileProcEntity.setFileName(fileName);
            if (Objects.nonNull(fileId)) {
                outGoingFileProcEntity.setFileId(fileId);
                outGoingFileProcEntity.setGeneratedStatus(4);
            } else {
                outGoingFileProcEntity.setGeneratedStatus(5);
            }
            this.outFileRepo.saveAndFlush((Object)outGoingFileProcEntity);
        }
        catch (Exception e) {
            log.error("Error : updateOutFilelog() :", (Throwable)e);
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

    private void insertIntoOutgoingSummary(Integer user, Integer insCode, Integer intCode, String fileName, Integer outgoingLogSerialNumber, String network) {
        try {
            List txnEntity = this.visaGOCTxnRepo.findByInstitutionCodeAndGenStatusAndNetwork(insCode, 9, network);
            Map<String, Map> groupedData = txnEntity.stream().collect(Collectors.groupingBy(VisaGOCWorkEntity::getTxnCode, Collectors.collectingAndThen(Collectors.toList(), list -> Map.of("count", Double.valueOf(list.size()), "totalTxnAmount", list.stream().mapToDouble(VisaGOCWorkEntity::getTxnAmount).sum(), "totalSchgAmount", list.stream().mapToDouble(VisaGOCWorkEntity::getSchgAmount).sum()))));
            groupedData.forEach((txnCode, totals) -> {
                double totalNetAmount = (Double)totals.get("totalTxnAmount") + (Double)totals.get("totalSchgAmount");
                OutgoingSummaryEntity otsEntity = new OutgoingSummaryEntity();
                otsEntity.setLastupdated(LocalDateTime.now());
                otsEntity.setUpdatedUser(user.intValue());
                otsEntity.setInstitution(insCode.intValue());
                otsEntity.setInterfaceCode(intCode.intValue());
                otsEntity.setOutFileDate(LocalDate.now());
                otsEntity.setFileId(fileName);
                otsEntity.setRefSerialNumber(outgoingLogSerialNumber.intValue());
                otsEntity.setMessageTypeId(txnCode);
                otsEntity.setFunctionCode("1");
                otsEntity.setProcCode("");
                otsEntity.setCount(Integer.valueOf(((Double)totals.get("count")).intValue()));
                otsEntity.setTxnAmount((Double)totals.get("totalTxnAmount"));
                otsEntity.setSurchargeAmount((Double)totals.get("totalSchgAmount"));
                otsEntity.setNetAmount(Double.valueOf(totalNetAmount));
                otsEntity.setGeneralStatus(Integer.valueOf(3));
                this.summaryRepo.saveAndFlush((Object)otsEntity);
            });
        }
        catch (Exception e) {
            log.error("Error in insertIntoOutgoingSummary(): ", (Throwable)e);
        }
    }

    private void moveWorkToData(Integer insCode, Integer status, String network) {
        try {
            List entities = this.visaGOCTxnRepo.findByInstitutionCodeAndGenStatusAndNetwork(insCode, status.intValue(), network);
            ArrayList<VisaGOCDataEntity> visaEntities = new ArrayList<VisaGOCDataEntity>();
            if (Objects.nonNull(entities)) {
                for (VisaGOCWorkEntity loop : entities) {
                    VisaGOCDataEntity entity = new VisaGOCDataEntity();
                    entity.setSerNumber(loop.getSerNumber());
                    entity.setLastUpdated(LocalDateTime.now());
                    entity.setUpdatedUser(loop.getUpdatedUser());
                    entity.setInstitutionCode(loop.getInstitutionCode());
                    entity.setIntCode(loop.getIntCode());
                    entity.setPrjSerNumber(loop.getPrjSerNumber());
                    entity.setGenStatus(loop.getGenStatus());
                    entity.setTxnRefNumber(loop.getTxnRefNumber());
                    entity.setTxnType(loop.getTxnType());
                    entity.setTxnCode(loop.getTxnCode());
                    entity.setEncCardNumber(loop.getEncCardNumber());
                    entity.setArn(loop.getArn());
                    entity.setPurchaseDate(loop.getPurchaseDate());
                    entity.setTxnCurCode(loop.getTxnCurCode());
                    entity.setTxnAmount(loop.getTxnAmount());
                    entity.setSchgAmount(loop.getSchgAmount());
                    entity.setMeName(loop.getMeName());
                    entity.setMeCity(loop.getMeCity());
                    entity.setMeCountry(loop.getMeCountry());
                    entity.setMcc(loop.getMcc());
                    entity.setApprovalCode(loop.getApprovalCode());
                    entity.setChIdMethod(loop.getChIdMethod());
                    entity.setPosEntryMode(loop.getPosEntryMode());
                    entity.setMemberText(loop.getMemberText());
                    entity.setFeePrgIndicator(loop.getFeePrgIndicator());
                    entity.setMerchantId(loop.getMerchantId());
                    entity.setTerminalId(loop.getTerminalId());
                    entity.setMotoEcomIndicator(loop.getMotoEcomIndicator());
                    entity.setAccSelection(loop.getAccSelection());
                    entity.setAcqBussId(loop.getAcqBussId());
                    entity.setPosEnvironment(loop.getPosEnvironment());
                    entity.setRespCode(loop.getRespCode());
                    entity.setTrlTxnDate(loop.getTrlTxnDate());
                    entity.setCryptAmount(loop.getCryptAmount());
                    entity.setCashbackAmount(loop.getCashbackAmount());
                    entity.setTxnId(loop.getTxnId());
                    entity.setVisaToken(loop.getVisaToken());
                    entity.setAuthCharIndicator(loop.getAuthCharIndicator());
                    entity.setAccFundSource(loop.getAccFundSource());
                    entity.setMarketSpecDataInd(loop.getMarketSpecDataInd());
                    entity.setProductId(loop.getProductId());
                    entity.setValidationCode(loop.getValidationCode());
                    entity.setSpendQualiIndictor(loop.getSpendQualiIndictor());
                    entity.setCollOnlyFlag(loop.getCollOnlyFlag());
                    entity.setFileId(loop.getFileId());
                    entity.setRetRefNumber(loop.getRetRefNumber());
                    entity.setReasonCode(loop.getReasonCode());
                    entity.setUsageCode(loop.getUsageCode());
                    entity.setProcCode(loop.getProcCode());
                    entity.setSetlFlag(loop.getSetlFlag());
                    entity.setTerminalCapability(loop.getTerminalCapability());
                    entity.setReimAttribute(loop.getReimAttribute());
                    entity.setStan(loop.getStan());
                    entity.setAuthAmount(loop.getAuthAmount());
                    entity.setTrlCapProfile(loop.getTrlCapProfile());
                    entity.setTrlCountryCode(loop.getTrlCountryCode());
                    entity.setUpblNumber(loop.getUpblNumber());
                    entity.setCardSeqNumber(loop.getCardSeqNumber());
                    entity.setAppTxnCounter(loop.getAppTxnCounter());
                    entity.setAppIcProfile(loop.getAppIcProfile());
                    entity.setAppCryptogram(loop.getAppCryptogram());
                    entity.setIssAppDataB2(loop.getIssAppDataB2());
                    entity.setIssAppDataB3(loop.getIssAppDataB3());
                    entity.setIssAppDataB4(loop.getIssAppDataB4());
                    entity.setIssAppDataB8(loop.getIssAppDataB8());
                    entity.setIssAppDataB9(loop.getIssAppDataB9());
                    entity.setIssAppDataB1(loop.getIssAppDataB1());
                    entity.setIssAppDataB17(loop.getIssAppDataB17());
                    entity.setIssAppDataB18(loop.getIssAppDataB18());
                    entity.setTrlVerResult(loop.getTrlVerResult());
                    entity.setFormFactorIndicator(loop.getFormFactorIndicator());
                    entity.setIssScriptResult(loop.getIssScriptResult());
                    entity.setServiceCode(loop.getServiceCode());
                    entity.setTxnFeeAmount(loop.getTxnFeeAmount());
                    entity.setSenderName(loop.getSenderName());
                    entity.setRecipientName(loop.getRecipientName());
                    entity.setBussAppId(loop.getBussAppId());
                    entity.setSenderAccount(loop.getSenderAccount());
                    entity.setDccIndicator(loop.getDccIndicator());
                    entity.setNetwork(loop.getNetwork());
                    entity.setSmsDmsFlag(loop.getSmsDmsFlag());
                    entity.setDomIntlFlag(loop.getDomIntlFlag());
                    entity.setCardType(loop.getCardType());
                    entity.setDccAmount(loop.getDccAmount());
                    entity.setDccCurrency(loop.getDccCurrency());
                    entity.setAcceptanceTrlIndicator(loop.getAcceptanceTrlIndicator());
                    visaEntities.add(entity);
                }
                this.visaGOCDataRepo.saveAllAndFlush(visaEntities);
                this.deleteFromVisaGOCWork(insCode);
            }
        }
        catch (Exception e) {
            log.error("Error : moveWorkToData() : ", (Throwable)e);
        }
    }

    @Transactional
    private void deleteFromVisaGOCWork(Integer insCode) {
        try {
            String outFileId = this.outFileLogRepo.findTopBygeneratedStatusOrderByLastUpdatedDateDesc(Integer.valueOf(4)).getFileId();
            List visaAcqWork = this.visaGOCTxnRepo.findByInstitutionCodeAndFileId(insCode, outFileId);
            if (Objects.nonNull(visaAcqWork) && !visaAcqWork.isEmpty()) {
                this.visaGOCTxnRepo.deleteAll((Iterable)visaAcqWork);
            }
        }
        catch (Exception e) {
            log.error("Error : deleteFromVisaGOCWork : ", (Throwable)e);
        }
    }

    public GOCServiceImpl(OutgoingSummaryRepo summaryRepo, FileFormatsRepo fileFormatsRepo, InterfacesRepo interfacesRepo, OutFileLogRepo outFileLogRepo, OutFileLogRepo outFileRepo, AcquirerBinsRepo acqBinRepo, VisaGOCTxnRepo visaGOCTxnRepo, CryptAPI cryptApi, PosTransactionRepo posTxnRepo, IOutGoingSummaryService iOutGoingSummaryService, BusinessDateRepo businessDateRepo, Environment env, BaseIIGOCService baseIIGOCService, VisaGOCDataRepo visaGOCDataRepo) {
        this.summaryRepo = summaryRepo;
        this.fileFormatsRepo = fileFormatsRepo;
        this.interfacesRepo = interfacesRepo;
        this.outFileLogRepo = outFileLogRepo;
        this.outFileRepo = outFileRepo;
        this.acqBinRepo = acqBinRepo;
        this.visaGOCTxnRepo = visaGOCTxnRepo;
        this.cryptApi = cryptApi;
        this.posTxnRepo = posTxnRepo;
        this.iOutGoingSummaryService = iOutGoingSummaryService;
        this.businessDateRepo = businessDateRepo;
        this.env = env;
        this.baseIIGOCService = baseIIGOCService;
        this.visaGOCDataRepo = visaGOCDataRepo;
    }
}

