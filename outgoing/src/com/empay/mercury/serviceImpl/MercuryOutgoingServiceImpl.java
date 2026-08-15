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
 *  com.empay.mercury.entities.MercuryAcqTxnDataEntity
 *  com.empay.mercury.entities.MercuryAcqTxnWorkEntity
 *  com.empay.mercury.repo.MercuryAcqTxnDataRepo
 *  com.empay.mercury.repo.MercuryAcqTxnWorkRepo
 *  com.empay.mercury.service.MercuryFileService
 *  com.empay.mercury.service.MercuryOutgoingService
 *  com.empay.mercury.serviceImpl.MercuryOutgoingServiceImpl
 *  com.empay.staging.entities.AcquirerBinsEntity
 *  com.empay.staging.repo.AcquirerBinsRepo
 *  com.empay.staging.repo.PosTransactionRepo
 *  com.empay.staging.service.IOutGoingSummaryService
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 *  org.springframework.stereotype.Service
 *  org.springframework.transaction.annotation.Transactional
 */
package com.empay.mercury.serviceImpl;

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
import com.empay.mercury.entities.MercuryAcqTxnDataEntity;
import com.empay.mercury.entities.MercuryAcqTxnWorkEntity;
import com.empay.mercury.repo.MercuryAcqTxnDataRepo;
import com.empay.mercury.repo.MercuryAcqTxnWorkRepo;
import com.empay.mercury.service.MercuryFileService;
import com.empay.mercury.service.MercuryOutgoingService;
import com.empay.staging.entities.AcquirerBinsEntity;
import com.empay.staging.repo.AcquirerBinsRepo;
import com.empay.staging.repo.PosTransactionRepo;
import com.empay.staging.service.IOutGoingSummaryService;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MercuryOutgoingServiceImpl
implements MercuryOutgoingService {
    private static final Logger log = LoggerFactory.getLogger(MercuryOutgoingServiceImpl.class);
    private final AcquirerBinsRepo acqBinRepo;
    private final FileFormatsRepo fileFormatsRepo;
    private final OutFileLogRepo outFileRepo;
    private final InterfacesRepo interfacesRepo;
    private final BusinessDateRepo businessDateRepo;
    private final OutgoingSummaryRepo summaryRepo;
    private final PosTransactionRepo posTxnRepo;
    private final MercuryAcqTxnWorkRepo mercuryTxnWorkRepo;
    private final MercuryAcqTxnDataRepo mercuryTxnDataRepo;
    private final IOutGoingSummaryService iOutGoingSummaryService;
    private final MercuryFileService mercuryFileService;
    private final CryptAPI cryptApi;
    private static final DateTimeFormatter FILE_DATE_FORMAT = DateTimeFormatter.ofPattern("ddMMyyyy");
    private static final int MAX_BATCH_RECORDS = 60;
    private static final int MAX_BATCHES = 998;
    private static final int MAX_TXNS_PER_FILE = 59880;

    public String processMercuryOutgoing(Integer insCode, Integer user, Integer formatCode, String insShortName, LocalDateTime startDate, LocalDateTime toDate) {
        Integer intCode = 0;
        Integer forCode = 0;
        try {
            List results;
            InterfacesEntity interfaces;
            Character forType = Character.valueOf('O');
            String intCategory = "MERCURY";
            Integer[] status = new Integer[]{1, 9};
            FileFormatsEntity fileFormatEntity = this.fileFormatsRepo.findBySystemCodeAndTypeAndInstitutionCode(formatCode, forType, insCode);
            if (fileFormatEntity != null) {
                forCode = fileFormatEntity.getCode();
            }
            if ((interfaces = this.interfacesRepo.findByInterfaceCategoryAndInstitutionCode(intCategory, insCode)) != null) {
                intCode = interfaces.getInterfaceCode();
            }
            if ((results = this.outFileRepo.findByFormatCodeAndGeneratedStatusIn(forCode, status)) != null && !results.isEmpty()) {
                return "File Generation already Scheduled";
            }
            AcquirerBinsEntity acqBin = this.getAcquirerBin(insCode);
            if (acqBin == null) {
                return "Acquirer bin not found";
            }
            String recapNumber = acqBin.getMcIcaNo();
            List txnList = startDate == null ? this.mercuryTxnWorkRepo.findByInstitutionCodeAndIntCodeAndGeneralStatusAndLocalDateTimeLessThanEqual(insCode, intCode, Integer.valueOf(3), toDate) : this.mercuryTxnWorkRepo.findByInstitutionCodeAndIntCodeAndGeneralStatusAndLocalDateTimeBetween(insCode, intCode, 3, startDate, toDate);
            if (txnList == null || txnList.isEmpty()) {
                return "No data found";
            }
            Set encryptedCardTokens = txnList.stream().map(MercuryAcqTxnWorkEntity::getEncryptedCardNumber).collect(Collectors.toSet());
            DecryptResponseVo response = this.cryptApi.getCardNumber(encryptedCardTokens);
            this.updateMercuryAcqWork(insCode, user, intCode, "", Integer.valueOf(3), startDate, toDate);
            List businessDate = this.businessDateRepo.findByInstitutionCode(insCode.intValue());
            if (Objects.nonNull(response) && Objects.nonNull(response.getCardNumbers())) {
                List fileGroups = this.splitTransactions(txnList);
                for (List fileTxns : fileGroups) {
                    int sequence = this.updateAndGetFileSequence(acqBin);
                    String fileName = "EIF_" + LocalDate.now().format(FILE_DATE_FORMAT) + "." + String.format("%03d", sequence);
                    Integer outgoingLogSerialNumber = this.inserOutFileLog(this.outFileRepo.findByFormatCodeAndGeneratedStatusIn(forCode, status), user, insCode, intCode, forCode);
                    String fileId = this.mercuryFileService.writeMercuryFile(fileTxns, insShortName, fileName, recapNumber, response);
                    if (fileId == null) {
                        this.updateOutFilelog(fileName, null, insCode, outgoingLogSerialNumber);
                        return "Outgoing Failed";
                    }
                    this.updateMercuryAcqWork(fileTxns, user, fileId, 9);
                    this.updateOutFilelog(fileName, fileId, insCode, outgoingLogSerialNumber);
                    this.insertIntoOutgoingSummary(user, insCode, intCode, fileName, outgoingLogSerialNumber);
                    OutGoingFileProcessingEntity outGoingSummary = new OutGoingFileProcessingEntity();
                    outGoingSummary.setFileName(fileName);
                    outGoingSummary.setFileId(fileId);
                    outGoingSummary.setBusinessDate(businessDate != null && !businessDate.isEmpty() ? ((BusinessDateEntity)businessDate.get(0)).getBusinessDate() : null);
                    this.iOutGoingSummaryService.generateOutgoingSummaryPDF(user.intValue(), insCode.intValue(), intCode.intValue(), outgoingLogSerialNumber.intValue(), intCategory, insShortName, outGoingSummary, "");
                }
                this.posTxnRepo.completeMercuryPosStatus(insCode.intValue());
                this.posTxnRepo.flush();
                this.moveWorkToData(insCode.intValue(), user.intValue());
                return "Success";
            }
            txnList.forEach(entity -> entity.setGeneralStatus(7));
            this.mercuryTxnWorkRepo.saveAll((Iterable)txnList);
            return "Outgoing Failed";
        }
        catch (Exception e) {
            log.error("processMercuryOutgoing()", (Throwable)e);
            return "Failed";
        }
    }

    private List<List<MercuryAcqTxnWorkEntity>> splitTransactions(List<MercuryAcqTxnWorkEntity> txnList) {
        ArrayList<List<MercuryAcqTxnWorkEntity>> result = new ArrayList<List<MercuryAcqTxnWorkEntity>>();
        for (int i = 0; i < txnList.size(); i += 59880) {
            result.add(txnList.subList(i, Math.min(i + 59880, txnList.size())));
        }
        return result;
    }

    private AcquirerBinsEntity getAcquirerBin(Integer insCode) {
        List bins = this.acqBinRepo.findByInstitutionCodeAndBinType(insCode, "E");
        return bins == null || bins.isEmpty() ? null : (AcquirerBinsEntity)bins.get(0);
    }

    private int updateAndGetFileSequence(AcquirerBinsEntity acqBin) {
        int fileSequence = acqBin.getOutfileDate() != null && acqBin.getOutfileDate().toLocalDate().equals(LocalDate.now()) ? acqBin.getOutFileSeq() : 1;
        acqBin.setOutFileSeq(Integer.valueOf(fileSequence + 1));
        acqBin.setOutfileDate(LocalDateTime.now());
        this.acqBinRepo.saveAndFlush((Object)acqBin);
        return fileSequence;
    }

    @Transactional
    public void moveWorkToData(int institutionCode, int user) {
        try {
            List workEntities = this.mercuryTxnWorkRepo.findByInstitutionCodeAndGeneralStatus(Integer.valueOf(institutionCode), 4);
            ArrayList<MercuryAcqTxnDataEntity> dataEntities = new ArrayList<MercuryAcqTxnDataEntity>();
            LocalDateTime localDateTime = LocalDateTime.now();
            for (MercuryAcqTxnWorkEntity workEntity : workEntities) {
                MercuryAcqTxnDataEntity dataEntity = new MercuryAcqTxnDataEntity();
                dataEntity.setSerNumber(workEntity.getSerNumber());
                dataEntity.setLastUpdated(localDateTime);
                dataEntity.setUpdatedUser(workEntity.getUpdatedUser());
                dataEntity.setInstitutionCode(workEntity.getInstitutionCode());
                dataEntity.setIntCode(workEntity.getIntCode());
                dataEntity.setPrjSerNumber(workEntity.getPrjSerNumber());
                dataEntity.setGeneralStatus(workEntity.getGeneralStatus());
                dataEntity.setTxnRefNumber(workEntity.getTxnRefNumber());
                dataEntity.setRrn(workEntity.getRrn());
                dataEntity.setMerchantId(workEntity.getMerchantId());
                dataEntity.setTerminalId(workEntity.getTerminalId());
                dataEntity.setTxnType(workEntity.getTxnType());
                dataEntity.setCardNumber(workEntity.getCardNumber());
                dataEntity.setTxnAmount(workEntity.getTxnAmount());
                dataEntity.setSurchargeAmount(workEntity.getSurchargeAmount());
                dataEntity.setLocalDateTime(workEntity.getLocalDateTime());
                dataEntity.setTxnDate(workEntity.getTxnDate());
                dataEntity.setChargeType(workEntity.getChargeType());
                dataEntity.setTypeOfCharge(workEntity.getTypeOfCharge());
                dataEntity.setGeoArea(workEntity.getGeoArea());
                dataEntity.setMeName(workEntity.getMeName());
                dataEntity.setMeCity(workEntity.getMeCity());
                dataEntity.setMeCountry(workEntity.getMeCountry());
                dataEntity.setCardAccepStreetAddress(workEntity.getCardAccepStreetAddress());
                dataEntity.setCardAccepStateCode(workEntity.getCardAccepStateCode());
                dataEntity.setMePinCode(workEntity.getMePinCode());
                dataEntity.setEstPhoneNumber(workEntity.getEstPhoneNumber());
                dataEntity.setMcc(workEntity.getMcc());
                dataEntity.setCardType(workEntity.getCardType());
                dataEntity.setApprovalCode(workEntity.getApprovalCode());
                dataEntity.setTxnCurrencyExponent(workEntity.getTxnCurrencyExponent());
                dataEntity.setTxnCurCode(workEntity.getTxnCurCode());
                dataEntity.setMercuryRefId(workEntity.getMercuryRefId());
                dataEntity.setCardDomIntlFlag(workEntity.getCardDomIntlFlag());
                dataEntity.setDmsSmsMode(workEntity.getDmsSmsMode());
                dataEntity.setEncryptedCardNumber(workEntity.getEncryptedCardNumber());
                dataEntity.setOrgInstIdCode(workEntity.getOrgInstIdCode());
                dataEntity.setTrlType(workEntity.getTrlType());
                dataEntity.setSettlementIndicator(workEntity.getSettlementIndicator());
                dataEntity.setTxnFeeAmount(workEntity.getTxnFeeAmount());
                dataEntity.setMotoEcomIndicator(workEntity.getMotoEcomIndicator());
                dataEntity.setResponseCode(workEntity.getResponseCode());
                dataEntity.setAcqinstIdCode(workEntity.getAcqinstIdCode());
                dataEntity.setAcqRefData(workEntity.getAcqRefData());
                dataEntity.setCardInputMode(workEntity.getCardInputMode());
                dataEntity.setCardInputCapability(workEntity.getCardInputCapability());
                dataEntity.setCardSeqNumber(workEntity.getCardSeqNumber());
                dataEntity.setAppICProfile(workEntity.getAppICProfile());
                dataEntity.setAppTxnCounter(workEntity.getAppTxnCounter());
                dataEntity.setAppCryptogram(workEntity.getAppCryptogram());
                dataEntity.setCryptAmount(workEntity.getCryptAmount());
                dataEntity.setCashBackAmount(workEntity.getCashBackAmount());
                dataEntity.setCryptInfoData(workEntity.getCryptInfoData());
                dataEntity.setCvmResult(workEntity.getCvmResult());
                dataEntity.setDedicatedFileName(workEntity.getDedicatedFileName());
                dataEntity.setIfdSerNumber(workEntity.getIfdSerNumber());
                dataEntity.setIssAppData(workEntity.getIssAppData());
                dataEntity.setIssAuthData(workEntity.getIssAuthData());
                dataEntity.setTrlConCode(workEntity.getTrlConCode());
                dataEntity.setTrlAppVerNumber(workEntity.getTrlAppVerNumber());
                dataEntity.setChipTrlCapabilities(workEntity.getChipTrlCapabilities());
                dataEntity.setChipTrlType(workEntity.getChipTrlType());
                dataEntity.setTrlVerResult(workEntity.getTrlVerResult());
                dataEntity.setChipTxnDate(workEntity.getChipTxnDate());
                dataEntity.setChipTxnType(workEntity.getChipTxnType());
                dataEntity.setChipCurCode(workEntity.getChipCurCode());
                dataEntity.setUpblNumber(workEntity.getUpblNumber());
                dataEntity.setCentreProcDate(workEntity.getCentreProcDate());
                dataEntity.setChPresent(workEntity.getChPresent());
                dataEntity.setCardPresent(workEntity.getCardPresent());
                dataEntity.setPanSequenceNumber(workEntity.getPanSequenceNumber());
                dataEntity.setPosEntryMode(workEntity.getPosEntryMode());
                dataEntity.setFileProcDate(workEntity.getFileProcDate());
                dataEntity.setFileID(workEntity.getFileID());
                dataEntities.add(dataEntity);
                dataEntity = null;
            }
            this.mercuryTxnDataRepo.saveAll(dataEntities);
            this.mercuryTxnWorkRepo.deleteAll((Iterable)workEntities);
            dataEntities = null;
        }
        catch (Exception e) {
            log.error("MoveWorkToData()", (Throwable)e);
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

    private void updateMercuryAcqWork(Integer institutionCode, Integer userId, Integer intCode, String fileId, Integer currentStatus, LocalDateTime startDate, LocalDateTime toDate) {
        try {
            List mercuryEntities = new ArrayList();
            mercuryEntities = startDate == null ? this.mercuryTxnWorkRepo.findByInstitutionCodeAndIntCodeAndGeneralStatusAndLocalDateTimeLessThanEqual(institutionCode, intCode, currentStatus, toDate) : this.mercuryTxnWorkRepo.findByInstitutionCodeAndIntCodeAndGeneralStatusAndLocalDateTimeBetween(institutionCode, intCode, currentStatus.intValue(), startDate, toDate);
            log.info("mercuryEntities : " + mercuryEntities.size());
            if (mercuryEntities != null && !mercuryEntities.isEmpty()) {
                this.updateMercuryAcqWork(mercuryEntities, userId, fileId, currentStatus.intValue());
            } else {
                log.info("No Mercury entities found for Institution");
            }
        }
        catch (Exception e) {
            log.error("Error in updateMercuryAcqWork for InstitutionCode: {}, IntCode: {}, Status: {}. Details: ", new Object[]{institutionCode, intCode, currentStatus, e});
        }
    }

    private void updateMercuryAcqWork(List<MercuryAcqTxnWorkEntity> fileTxns, Integer userId, String fileId, int currentStatus) {
        if (fileTxns != null && !fileTxns.isEmpty()) {
            int updatedStatus = currentStatus == 3 ? 9 : 4;
            fileTxns.forEach(entity -> this.updateMercuryEntity(entity, userId, Integer.valueOf(updatedStatus), fileId));
            this.mercuryTxnWorkRepo.saveAllAndFlush(fileTxns);
        } else {
            log.info("No Mercury entities found for Institution");
        }
    }

    private void updateMercuryEntity(MercuryAcqTxnWorkEntity entity, Integer user, Integer genStatus, String fileId) {
        entity.setLastUpdated(LocalDateTime.now());
        entity.setUpdatedUser(user);
        entity.setGeneralStatus(genStatus.intValue());
        entity.setFileID(fileId);
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

    private void insertIntoOutgoingSummary(Integer user, Integer insCode, Integer intCode, String fileName, Integer outgoingLogSerialNumber) {
        try {
            List txnEntity = this.mercuryTxnWorkRepo.findByInstitutionCodeAndGeneralStatus(insCode, 9);
            Map<String, Map> groupedData = txnEntity.stream().collect(Collectors.groupingBy(MercuryAcqTxnWorkEntity::getTxnType, Collectors.collectingAndThen(Collectors.toList(), list -> Map.of("count", Double.valueOf(list.size()), "totalTxnAmount", list.stream().mapToDouble(MercuryAcqTxnWorkEntity::getTxnAmount).sum(), "totalSchgAmount", list.stream().mapToDouble(MercuryAcqTxnWorkEntity::getSurchargeAmount).sum()))));
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

    public MercuryOutgoingServiceImpl(AcquirerBinsRepo acqBinRepo, FileFormatsRepo fileFormatsRepo, OutFileLogRepo outFileRepo, InterfacesRepo interfacesRepo, BusinessDateRepo businessDateRepo, OutgoingSummaryRepo summaryRepo, PosTransactionRepo posTxnRepo, MercuryAcqTxnWorkRepo mercuryTxnWorkRepo, MercuryAcqTxnDataRepo mercuryTxnDataRepo, IOutGoingSummaryService iOutGoingSummaryService, MercuryFileService mercuryFileService, CryptAPI cryptApi) {
        this.acqBinRepo = acqBinRepo;
        this.fileFormatsRepo = fileFormatsRepo;
        this.outFileRepo = outFileRepo;
        this.interfacesRepo = interfacesRepo;
        this.businessDateRepo = businessDateRepo;
        this.summaryRepo = summaryRepo;
        this.posTxnRepo = posTxnRepo;
        this.mercuryTxnWorkRepo = mercuryTxnWorkRepo;
        this.mercuryTxnDataRepo = mercuryTxnDataRepo;
        this.iOutGoingSummaryService = iOutGoingSummaryService;
        this.mercuryFileService = mercuryFileService;
        this.cryptApi = cryptApi;
    }
}

