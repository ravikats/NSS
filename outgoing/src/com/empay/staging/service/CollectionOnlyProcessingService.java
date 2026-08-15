/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.common.repo.InterfacesRepo
 *  com.empay.common.repo.OutFileLogRepo
 *  com.empay.staging.entities.McGCOTxnDataEntity
 *  com.empay.staging.entities.McGCOTxnWorkEntity
 *  com.empay.staging.entities.VisaGOCDataEntity
 *  com.empay.staging.entities.VisaGOCWorkEntity
 *  com.empay.staging.repo.McGCODataRepo
 *  com.empay.staging.repo.McGCOWorkRepo
 *  com.empay.staging.repo.PosTransactionRepo
 *  com.empay.staging.repo.VisaGOCDataRepo
 *  com.empay.staging.repo.VisaGOCTxnRepo
 *  com.empay.staging.service.CollectionOnlyProcessingService
 *  com.empay.staging.service.GCOService
 *  com.empay.staging.service.GOCService
 *  com.empay.vo.CollectionOnlyRequestVo
 *  jakarta.transaction.Transactional
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.core.env.Environment
 *  org.springframework.stereotype.Service
 */
package com.empay.staging.service;

import com.empay.common.repo.InterfacesRepo;
import com.empay.common.repo.OutFileLogRepo;
import com.empay.staging.entities.McGCOTxnDataEntity;
import com.empay.staging.entities.McGCOTxnWorkEntity;
import com.empay.staging.entities.VisaGOCDataEntity;
import com.empay.staging.entities.VisaGOCWorkEntity;
import com.empay.staging.repo.McGCODataRepo;
import com.empay.staging.repo.McGCOWorkRepo;
import com.empay.staging.repo.PosTransactionRepo;
import com.empay.staging.repo.VisaGOCDataRepo;
import com.empay.staging.repo.VisaGOCTxnRepo;
import com.empay.staging.service.GCOService;
import com.empay.staging.service.GOCService;
import com.empay.vo.CollectionOnlyRequestVo;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class CollectionOnlyProcessingService {
    private static final Logger log = LoggerFactory.getLogger(CollectionOnlyProcessingService.class);
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    @Autowired
    Environment env;
    private final VisaGOCTxnRepo visaGOCTxnRepo;
    private final GOCService gocService;
    private final OutFileLogRepo outFileLogRepo;
    private final InterfacesRepo interfacesRepo;
    private final VisaGOCDataRepo visaGOCDataRepo;
    private final PosTransactionRepo posTransactionRepo;
    private final GCOService gcoService;
    private final McGCOWorkRepo mcGCOWorkRepo;
    private final McGCODataRepo mcGCODataRepo;

    public String processAndMoveData(CollectionOnlyRequestVo requestVo, Integer institutionCode, Integer user, Integer forCode, String insShortName) {
        try {
            LocalDateTime toDate;
            LocalDateTime fromDate;
            String scheme = requestVo.getScheme();
            String network = requestVo.getNetwork();
            try {
                fromDate = LocalDateTime.parse(requestVo.getFromDate(), this.formatter);
                toDate = LocalDateTime.parse(requestVo.getToDate(), this.formatter);
                if (toDate.isBefore(fromDate)) {
                    return "From date is greater than To Date date time format ";
                }
            }
            catch (Exception e) {
                log.info("Error in Date Time Mapping !!");
                return "Invalid date time format ";
            }
            Integer txnCount = this.getTxnCount(scheme, institutionCode, fromDate, toDate, network);
            log.info("txnCount :" + txnCount);
            if (txnCount == 0) {
                return "There are no transactions to stage!";
            }
            this.scheduleFileProcessing(institutionCode, user, forCode, insShortName, scheme, fromDate, toDate, network);
            return "CollectionOnly File Processing Scheduled Successfully.";
        }
        catch (Exception e) {
            log.error("Error : processAndMoveData : ", (Throwable)e);
            return "";
        }
    }

    private Integer getTxnCount(String scheme, Integer institutionCode, LocalDateTime fromDate, LocalDateTime toDate, String network) {
        if (scheme == null) {
            return 0;
        }
        if (fromDate == null) {
            return switch (scheme) {
                case "MASTERCARD" -> this.mcGCOWorkRepo.countByInsCodeAndGeneralStatusAndNetworkAndLocalDateTimeLessThanEqual(institutionCode, 3, network, toDate);
                case "VISA" -> this.visaGOCTxnRepo.countByInstitutionCodeAndGenStatusAndNetworkAndPurchaseDateLessThanEqual(institutionCode, 3, network, toDate);
                default -> 0;
            };
        }
        return switch (scheme) {
            case "MASTERCARD" -> this.mcGCOWorkRepo.countByInsCodeAndGeneralStatusAndNetworkAndLocalDateTimeBetween(institutionCode, 3, network, fromDate, toDate);
            case "VISA" -> this.visaGOCTxnRepo.countByInstitutionCodeAndGenStatusAndNetworkAndPurchaseDateBetween(institutionCode, 3, network, fromDate, toDate);
            default -> 0;
        };
    }

    public void scheduleFileProcessing(Integer institutionCode, Integer user, Integer forCode, String insShortName, String scheme, LocalDateTime fromDate, LocalDateTime toDate, String network) {
        log.info("Outgoing Scheduled :" + String.valueOf(new Date()));
        new Thread(switch (scheme) {
            case "MASTERCARD" -> () -> this.gcoService.generateMcCollectionOnly(institutionCode, user, forCode, insShortName, fromDate, toDate, network);
            case "VISA" -> () -> this.gocService.generateVisaCollectionOnly(institutionCode, user, forCode, insShortName, fromDate, toDate, network);
            default -> throw new IllegalArgumentException("Unsupported scheme: " + scheme);
        }).start();
    }

    @Transactional
    public String revertLastCollectionOnlyData(String network, int insCode) {
        try {
            switch (network) {
                case "VISA": {
                    int intCode = this.interfacesRepo.findByInterfaceCategory("VISA").getInterfaceCode();
                    String outFileId = this.outFileLogRepo.findTopBygeneratedStatusAndInterfaceCodeOrderByLastUpdatedDateDesc(4, intCode).getFileId();
                    List visaGOCData = this.visaGOCDataRepo.findByInstitutionCodeAndFileId(insCode, outFileId);
                    if (Objects.nonNull(visaGOCData) && !visaGOCData.isEmpty()) {
                        List visaGOCWorkEntity = visaGOCData.stream().map(arg_0 -> this.mapToVisaGOCWorkEntity(arg_0)).collect(Collectors.toList());
                        this.visaGOCTxnRepo.saveAllAndFlush(visaGOCWorkEntity);
                        this.updatePOSData(visaGOCData, null);
                        this.visaGOCDataRepo.deleteAll((Iterable)visaGOCData);
                        this.visaGOCDataRepo.flush();
                        this.outFileLogRepo.deleteByInstitutionCodeAndFileIdAndInterfaceCode(Integer.valueOf(insCode), outFileId, intCode);
                        this.outFileLogRepo.flush();
                        return "Revert Successfully Completed";
                    }
                    return "No Collection Only Data for the file ID";
                }
                case "MASTERCARD": {
                    int intCode = this.interfacesRepo.findByInterfaceCategory("MCI").getInterfaceCode();
                    String outFileId = this.outFileLogRepo.findTopBygeneratedStatusAndInterfaceCodeOrderByLastUpdatedDateDesc(4, intCode).getFileId();
                    List mcGcoData = this.mcGCODataRepo.findByInsCodeAndFileId(Integer.valueOf(insCode), outFileId);
                    if (Objects.nonNull(mcGcoData) && !mcGcoData.isEmpty()) {
                        List mcGcoWorkEntity = mcGcoData.stream().map(arg_0 -> this.mapToMcGcoWorkEntity(arg_0)).collect(Collectors.toList());
                        this.mcGCOWorkRepo.saveAllAndFlush(mcGcoWorkEntity);
                        this.updatePOSData(null, mcGcoData);
                        this.mcGCODataRepo.deleteAll((Iterable)mcGcoData);
                        this.outFileLogRepo.deleteByInstitutionCodeAndFileIdAndInterfaceCode(Integer.valueOf(insCode), outFileId, intCode);
                        this.outFileLogRepo.flush();
                        return "Revert Successfully Completed";
                    }
                    return "No Outgoing Data for the file ID";
                }
            }
            return "Please provide valid network";
        }
        catch (Exception e) {
            log.error("Error:revertLastCollectionOnlyData()", (Throwable)e);
            return "Please provide valid network";
        }
    }

    private void updatePOSData(List<VisaGOCDataEntity> visaGOCData, List<McGCOTxnDataEntity> mcGcoData) {
        try {
            List<Integer> posCodes = null;
            if (Objects.nonNull(visaGOCData)) {
                posCodes = visaGOCData.stream().map(VisaGOCDataEntity::getTxnRefNumber).filter(Objects::nonNull).toList();
            } else if (Objects.nonNull(mcGcoData)) {
                posCodes = mcGcoData.stream().map(McGCOTxnDataEntity::getTxnRefSerNumber).filter(Objects::nonNull).toList();
            }
            List posData = this.posTransactionRepo.findBySerialNumberIn(posCodes);
            posData.forEach(posCode -> {
                posCode.setGenStatus(Integer.valueOf(4));
                posCode.setOutStatus("Marked for Outgoing");
            });
            this.posTransactionRepo.saveAllAndFlush((Iterable)posData);
        }
        catch (Exception e) {
            log.error("Error updatePOSData() :", (Throwable)e);
        }
    }

    private VisaGOCWorkEntity mapToVisaGOCWorkEntity(VisaGOCDataEntity visaGOCData) {
        try {
            VisaGOCWorkEntity entity = new VisaGOCWorkEntity();
            entity.setSerNumber(visaGOCData.getSerNumber());
            entity.setLastUpdated(LocalDateTime.now());
            entity.setUpdatedUser(visaGOCData.getUpdatedUser());
            entity.setInstitutionCode(visaGOCData.getInstitutionCode());
            entity.setIntCode(visaGOCData.getIntCode());
            entity.setPrjSerNumber(visaGOCData.getPrjSerNumber());
            entity.setGenStatus(Integer.valueOf(3));
            entity.setTxnRefNumber(visaGOCData.getTxnRefNumber());
            entity.setTxnType(visaGOCData.getTxnType());
            entity.setTxnCode(visaGOCData.getTxnCode());
            entity.setEncCardNumber(visaGOCData.getEncCardNumber());
            entity.setArn(visaGOCData.getArn());
            entity.setPurchaseDate(visaGOCData.getPurchaseDate());
            entity.setTxnCurCode(visaGOCData.getTxnCurCode());
            entity.setTxnAmount(visaGOCData.getTxnAmount());
            entity.setSchgAmount(visaGOCData.getSchgAmount());
            entity.setMeName(visaGOCData.getMeName());
            entity.setMeCity(visaGOCData.getMeCity());
            entity.setMeCountry(visaGOCData.getMeCountry());
            entity.setMcc(visaGOCData.getMcc());
            entity.setApprovalCode(visaGOCData.getApprovalCode());
            entity.setChIdMethod(visaGOCData.getChIdMethod());
            entity.setPosEntryMode(visaGOCData.getPosEntryMode());
            entity.setMemberText(visaGOCData.getMemberText());
            entity.setFeePrgIndicator(visaGOCData.getFeePrgIndicator());
            entity.setMerchantId(visaGOCData.getMerchantId());
            entity.setTerminalId(visaGOCData.getTerminalId());
            entity.setMotoEcomIndicator(visaGOCData.getMotoEcomIndicator());
            entity.setAccSelection(visaGOCData.getAccSelection());
            entity.setAcqBussId(visaGOCData.getAcqBussId());
            entity.setPosEnvironment(visaGOCData.getPosEnvironment());
            entity.setRespCode(visaGOCData.getRespCode());
            entity.setTrlTxnDate(visaGOCData.getTrlTxnDate());
            entity.setCryptAmount(visaGOCData.getCryptAmount());
            entity.setCashbackAmount(visaGOCData.getCashbackAmount());
            entity.setTxnId(visaGOCData.getTxnId());
            entity.setVisaToken(visaGOCData.getVisaToken());
            entity.setAuthCharIndicator(visaGOCData.getAuthCharIndicator());
            entity.setAccFundSource(visaGOCData.getAccFundSource());
            entity.setMarketSpecDataInd(visaGOCData.getMarketSpecDataInd());
            entity.setProductId(visaGOCData.getProductId());
            entity.setValidationCode(visaGOCData.getValidationCode());
            entity.setSpendQualiIndictor(visaGOCData.getSpendQualiIndictor());
            entity.setCollOnlyFlag(visaGOCData.getCollOnlyFlag());
            entity.setFileId(visaGOCData.getFileId());
            entity.setRetRefNumber(visaGOCData.getRetRefNumber());
            entity.setReasonCode(visaGOCData.getReasonCode());
            entity.setUsageCode(visaGOCData.getUsageCode());
            entity.setProcCode(visaGOCData.getProcCode());
            entity.setSetlFlag(visaGOCData.getSetlFlag());
            entity.setTerminalCapability(visaGOCData.getTerminalCapability());
            entity.setReimAttribute(visaGOCData.getReimAttribute());
            entity.setStan(visaGOCData.getStan());
            entity.setAuthAmount(visaGOCData.getAuthAmount());
            entity.setTrlCapProfile(visaGOCData.getTrlCapProfile());
            entity.setTrlCountryCode(visaGOCData.getTrlCountryCode());
            entity.setUpblNumber(visaGOCData.getUpblNumber());
            entity.setCardSeqNumber(visaGOCData.getCardSeqNumber());
            entity.setAppTxnCounter(visaGOCData.getAppTxnCounter());
            entity.setAppIcProfile(visaGOCData.getAppIcProfile());
            entity.setAppCryptogram(visaGOCData.getAppCryptogram());
            entity.setIssAppDataB2(visaGOCData.getIssAppDataB2());
            entity.setIssAppDataB3(visaGOCData.getIssAppDataB3());
            entity.setIssAppDataB4(visaGOCData.getIssAppDataB4());
            entity.setIssAppDataB8(visaGOCData.getIssAppDataB8());
            entity.setIssAppDataB9(visaGOCData.getIssAppDataB9());
            entity.setIssAppDataB1(visaGOCData.getIssAppDataB1());
            entity.setIssAppDataB17(visaGOCData.getIssAppDataB17());
            entity.setIssAppDataB18(visaGOCData.getIssAppDataB18());
            entity.setTrlVerResult(visaGOCData.getTrlVerResult());
            entity.setFormFactorIndicator(visaGOCData.getFormFactorIndicator());
            entity.setIssScriptResult(visaGOCData.getIssScriptResult());
            entity.setServiceCode(visaGOCData.getServiceCode());
            entity.setTxnFeeAmount(visaGOCData.getTxnFeeAmount());
            entity.setSenderName(visaGOCData.getSenderName());
            entity.setRecipientName(visaGOCData.getRecipientName());
            entity.setBussAppId(visaGOCData.getBussAppId());
            entity.setSenderAccount(visaGOCData.getSenderAccount());
            entity.setDccIndicator(visaGOCData.getDccIndicator());
            entity.setNetwork(visaGOCData.getNetwork());
            entity.setSmsDmsFlag(visaGOCData.getSmsDmsFlag());
            entity.setDomIntlFlag(visaGOCData.getDomIntlFlag());
            entity.setCardType(visaGOCData.getCardType());
            entity.setDccAmount(visaGOCData.getDccAmount());
            entity.setDccCurrency(visaGOCData.getDccCurrency());
            entity.setAcceptanceTrlIndicator(visaGOCData.getAcceptanceTrlIndicator());
            return entity;
        }
        catch (Exception e) {
            log.error("Error : mapToVisaGOCWorkEntity() :", (Throwable)e);
            return null;
        }
    }

    private McGCOTxnWorkEntity mapToMcGcoWorkEntity(McGCOTxnDataEntity mcGcoData) {
        try {
            McGCOTxnWorkEntity entity = new McGCOTxnWorkEntity();
            entity.setSerialNumber(mcGcoData.getSerialNumber());
            entity.setLastUpdated(mcGcoData.getLastUpdated());
            entity.setUpdatedUser(mcGcoData.getUpdatedUser());
            entity.setInsCode(mcGcoData.getInsCode());
            entity.setIntCode(mcGcoData.getIntCode());
            entity.setTxnRefSerNumber(mcGcoData.getTxnRefSerNumber());
            entity.setGeneralStatus(Integer.valueOf(3));
            entity.setPrjSerNumber(mcGcoData.getPrjSerNumber());
            entity.setBusinessDate(mcGcoData.getBusinessDate());
            entity.setProcCode(mcGcoData.getProcCode());
            entity.setApprovalCode(mcGcoData.getApprovalCode());
            entity.setServiceCode(mcGcoData.getServiceCode());
            entity.setFileProcDate(mcGcoData.getFileProcDate());
            entity.setMti(mcGcoData.getMti());
            entity.setTxnType(mcGcoData.getTxnType());
            entity.setTxnAmount(mcGcoData.getTxnAmount());
            entity.setSurchargeAmount(mcGcoData.getSurchargeAmount());
            entity.setLocalDateTime(mcGcoData.getLocalDateTime());
            entity.setChPresent(mcGcoData.getChPresent());
            entity.setCardPresent(mcGcoData.getCardPresent());
            entity.setPosEntryMode(mcGcoData.getPosEntryMode());
            entity.setPosDataMode(mcGcoData.getPosDataMode());
            entity.setMcc(mcGcoData.getMcc());
            entity.setAcqInstIdCode(mcGcoData.getAcqInstIdCode());
            entity.setRrn(mcGcoData.getRrn());
            entity.setArn(mcGcoData.getArn());
            entity.setTerminalId(mcGcoData.getTerminalId());
            entity.setMerchantId(mcGcoData.getMerchantId());
            entity.setMeName(mcGcoData.getMeName());
            entity.setMeAddress(mcGcoData.getMeAddress());
            entity.setMeCity(mcGcoData.getMeCity());
            entity.setMeZipCode(mcGcoData.getMeZipCode());
            entity.setMeCountry(mcGcoData.getMeCountry());
            entity.setTxnCurCode(mcGcoData.getTxnCurCode());
            entity.setFileId(mcGcoData.getFileId());
            entity.setEncryptedCardNumber(mcGcoData.getEncryptedCardNumber());
            entity.setSettlementIndicator(mcGcoData.getSettlementIndicator());
            entity.setTxnlifeCycleId(mcGcoData.getTxnlifeCycleId());
            entity.setFunctionCode(mcGcoData.getFunctionCode());
            entity.setMsgReasonCode(mcGcoData.getMsgReasonCode());
            entity.setCardType(mcGcoData.getCardType());
            entity.setCardDomIntlFlag(mcGcoData.getCardDomIntlFlag());
            entity.setNetwork(mcGcoData.getNetwork());
            entity.setMposAccDevType(mcGcoData.getMposAccDevType());
            entity.setAccepterUrlAddress(mcGcoData.getAccepterUrlAddress());
            entity.setTxnCurrencyExponent(mcGcoData.getTxnCurrencyExponent());
            return entity;
        }
        catch (Exception e) {
            log.error("Error : mapToMcGcoWorkEntity() : ", (Throwable)e);
            return null;
        }
    }

    public CollectionOnlyProcessingService(VisaGOCTxnRepo visaGOCTxnRepo, GOCService gocService, OutFileLogRepo outFileLogRepo, InterfacesRepo interfacesRepo, VisaGOCDataRepo visaGOCDataRepo, PosTransactionRepo posTransactionRepo, GCOService gcoService, McGCOWorkRepo mcGCOWorkRepo, McGCODataRepo mcGCODataRepo) {
        this.visaGOCTxnRepo = visaGOCTxnRepo;
        this.gocService = gocService;
        this.outFileLogRepo = outFileLogRepo;
        this.interfacesRepo = interfacesRepo;
        this.visaGOCDataRepo = visaGOCDataRepo;
        this.posTransactionRepo = posTransactionRepo;
        this.gcoService = gcoService;
        this.mcGCOWorkRepo = mcGCOWorkRepo;
        this.mcGCODataRepo = mcGCODataRepo;
    }
}

