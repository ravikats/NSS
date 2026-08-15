/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.amex.entities.AmexAcqTxnDataEntity
 *  com.empay.amex.entities.AmexAcqTxnWorkEntity
 *  com.empay.amex.repo.AmexTxnDataRepo
 *  com.empay.amex.repo.AmexTxnWorkRepo
 *  com.empay.amex.service.AmexOutgoingService
 *  com.empay.common.repo.InterfacesRepo
 *  com.empay.common.repo.OutFileLogRepo
 *  com.empay.jaywan.entities.JaywanAcqTxnDataEntity
 *  com.empay.jaywan.entities.JaywanAcqTxnWorkEntity
 *  com.empay.jaywan.repo.JWNAcqTxnDataRepo
 *  com.empay.jaywan.repo.JWNAcqTxnWorkRepo
 *  com.empay.jaywan.service.JaywanOutgoingService
 *  com.empay.mercury.entities.MercuryAcqTxnDataEntity
 *  com.empay.mercury.repo.MercuryAcqTxnDataRepo
 *  com.empay.mercury.repo.MercuryAcqTxnWorkRepo
 *  com.empay.mercury.service.MercuryOutgoingService
 *  com.empay.staging.entities.McAcqTxnDataEntity
 *  com.empay.staging.entities.McAcqTxnWorkEntity
 *  com.empay.staging.entities.VisaAcqTxnDataEntity
 *  com.empay.staging.entities.VisaAcqTxnWorkEntity
 *  com.empay.staging.repo.McAcqTxnDataRepo
 *  com.empay.staging.repo.McAcqTxnWorkRepo
 *  com.empay.staging.repo.PosTransactionRepo
 *  com.empay.staging.repo.VisaAcqTxnDataRepo
 *  com.empay.staging.repo.VisaAcqTxnWorkRepo
 *  com.empay.staging.service.MCOutgoingService
 *  com.empay.staging.service.OutGoingProcessingService
 *  com.empay.staging.service.VisaOutgoingService
 *  com.empay.vo.OutGoingRequestVo
 *  jakarta.transaction.Transactional
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.core.env.Environment
 *  org.springframework.stereotype.Service
 */
package com.empay.staging.service;

import com.empay.amex.entities.AmexAcqTxnDataEntity;
import com.empay.amex.entities.AmexAcqTxnWorkEntity;
import com.empay.amex.repo.AmexTxnDataRepo;
import com.empay.amex.repo.AmexTxnWorkRepo;
import com.empay.amex.service.AmexOutgoingService;
import com.empay.common.repo.InterfacesRepo;
import com.empay.common.repo.OutFileLogRepo;
import com.empay.jaywan.entities.JaywanAcqTxnDataEntity;
import com.empay.jaywan.entities.JaywanAcqTxnWorkEntity;
import com.empay.jaywan.repo.JWNAcqTxnDataRepo;
import com.empay.jaywan.repo.JWNAcqTxnWorkRepo;
import com.empay.jaywan.service.JaywanOutgoingService;
import com.empay.mercury.entities.MercuryAcqTxnDataEntity;
import com.empay.mercury.repo.MercuryAcqTxnDataRepo;
import com.empay.mercury.repo.MercuryAcqTxnWorkRepo;
import com.empay.mercury.service.MercuryOutgoingService;
import com.empay.staging.entities.McAcqTxnDataEntity;
import com.empay.staging.entities.McAcqTxnWorkEntity;
import com.empay.staging.entities.VisaAcqTxnDataEntity;
import com.empay.staging.entities.VisaAcqTxnWorkEntity;
import com.empay.staging.repo.McAcqTxnDataRepo;
import com.empay.staging.repo.McAcqTxnWorkRepo;
import com.empay.staging.repo.PosTransactionRepo;
import com.empay.staging.repo.VisaAcqTxnDataRepo;
import com.empay.staging.repo.VisaAcqTxnWorkRepo;
import com.empay.staging.service.MCOutgoingService;
import com.empay.staging.service.VisaOutgoingService;
import com.empay.vo.OutGoingRequestVo;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
public class OutGoingProcessingService {
    private static final Logger log = LoggerFactory.getLogger(OutGoingProcessingService.class);
    private final McAcqTxnWorkRepo mcAcqTxnWorkRepo;
    private final MCOutgoingService mcOutGoingService;
    private final McAcqTxnDataRepo mcAcqTxnDataRepo;
    private final OutFileLogRepo outFileLogRepo;
    private final InterfacesRepo interfacesRepo;
    private final VisaAcqTxnWorkRepo visaTxnWorkRepo;
    private final VisaOutgoingService visaOutService;
    private final VisaAcqTxnDataRepo visaTxnDataRepo;
    private final PosTransactionRepo posTransactionRepo;
    private final JaywanOutgoingService jaywanService;
    private final JWNAcqTxnWorkRepo jaywanTxnWorkRepo;
    private final JWNAcqTxnDataRepo jWNAcqTxnDataRepo;
    private final AmexOutgoingService amexService;
    private final AmexTxnWorkRepo amexTxnWorkRepo;
    private final AmexTxnDataRepo amexTxnDataRepo;
    private final MercuryOutgoingService mercuryService;
    private final MercuryAcqTxnWorkRepo mercuryTxnWorkRepo;
    private final MercuryAcqTxnDataRepo mercuryTxnDataRepo;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    @Autowired
    Environment env;

    public String processAndMoveData(OutGoingRequestVo binRequestVo, Integer institutionCode, Integer user, Integer forCode, String insShortName) {
        try {
            LocalDateTime toDate;
            LocalDateTime fromDate;
            String network = binRequestVo.getNetwork();
            try {
                fromDate = LocalDateTime.parse(binRequestVo.getFromDate(), this.formatter);
                toDate = LocalDateTime.parse(binRequestVo.getToDate(), this.formatter);
                if (toDate.isBefore(fromDate)) {
                    return "From date is greater than To Date date time format ";
                }
            }
            catch (Exception e) {
                log.info("Error in Date Time Mapping !!");
                return "Invalid date time format ";
            }
            Integer txnCount = this.getTxnCount(network, institutionCode, fromDate, toDate);
            log.info("txnCount :" + txnCount);
            if (txnCount == 0) {
                return "There are no transactions to stage!";
            }
            this.scheduleFileProcessing(institutionCode, user, forCode, insShortName, network, fromDate, toDate);
            return "Outgoing File Processing Scheduled Successfully.";
        }
        catch (Exception e) {
            log.error("Error : processAndMoveData : ", (Throwable)e);
            return "";
        }
    }

    private Integer getTxnCount(String network, Integer institutionCode, LocalDateTime fromDate, LocalDateTime toDate) {
        if (network == null) {
            return 0;
        }
        if (fromDate == null) {
            return switch (network) {
                case "MASTERCARD" -> this.mcAcqTxnWorkRepo.countByInstitutionCodeAndGeneralStatusAndLocalDateTimeLessThanEqual(institutionCode.intValue(), 3, toDate);
                case "VISA" -> this.visaTxnWorkRepo.countByInstitutionCodeAndGenStatusAndPurchaseDateLessThanEqual(institutionCode, Integer.valueOf(3), toDate);
                case "JAYWAN" -> this.jaywanTxnWorkRepo.countByInstitutionCodeAndGenStatusAndLocalDateTimeLessThanEqual(institutionCode.intValue(), 3, toDate);
                case "AMEX" -> this.amexTxnWorkRepo.countByInstitutionCodeAndGenStatusAndLocalDateTimeLessThanEqual(institutionCode, 3, toDate);
                default -> 0;
            };
        }
        return switch (network) {
            case "MASTERCARD" -> this.mcAcqTxnWorkRepo.countByInstitutionCodeAndGeneralStatusAndLocalDateTimeBetween(institutionCode.intValue(), 3, fromDate, toDate);
            case "VISA" -> this.visaTxnWorkRepo.countByInstitutionCodeAndGenStatusAndPurchaseDateBetween(institutionCode, Integer.valueOf(3), fromDate, toDate);
            case "JAYWAN" -> this.jaywanTxnWorkRepo.countByInstitutionCodeAndGenStatusAndLocalDateTimeBetween(institutionCode.intValue(), 3, fromDate, toDate);
            case "AMEX" -> this.amexTxnWorkRepo.countByInstitutionCodeAndGenStatusAndLocalDateTimeBetween(institutionCode, 3, fromDate, toDate);
            default -> 0;
        };
    }

    public void scheduleFileProcessing(Integer institutionCode, Integer user, Integer forCode, String insShortName, String network, LocalDateTime fromDate, LocalDateTime toDate) {
        log.info("Outgoing Scheduled :" + String.valueOf(new Date()));
        new Thread(switch (network) {
            case "MASTERCARD" -> () -> this.mcOutGoingService.processMCOutgoing(institutionCode.intValue(), user.intValue(), forCode.intValue(), insShortName, fromDate, toDate);
            case "VISA" -> () -> this.visaOutService.generateVisaOutgoing(institutionCode, user, forCode, insShortName, fromDate, toDate);
            case "JAYWAN" -> () -> this.jaywanService.generateJaywanOutgoing(institutionCode, user, forCode, insShortName, fromDate, toDate);
            case "AMEX" -> () -> this.amexService.generateAmexOutgoing(institutionCode, user, forCode, insShortName, fromDate, toDate);
            default -> throw new IllegalArgumentException("Unsupported network: " + network);
        }).start();
    }

    @Transactional
    public String revertLastOutgoingData(String intCategory, Integer insCode) {
        try {
            switch (intCategory) {
                case "MASTERCARD": {
                    int intCode = this.interfacesRepo.findByInterfaceCategory("MCI").getInterfaceCode();
                    String outFileId = this.outFileLogRepo.findTopBygeneratedStatusAndInterfaceCodeOrderByLastUpdatedDateDesc(4, intCode).getFileId();
                    List mcAcqTxnData = this.mcAcqTxnDataRepo.findByInstitutionCodeAndFileID(insCode, outFileId);
                    if (Objects.nonNull(mcAcqTxnData) && !mcAcqTxnData.isEmpty()) {
                        List mcAcqWorkEntity = mcAcqTxnData.stream().map(arg_0 -> this.mapToMcAcqWorkEntity(arg_0)).collect(Collectors.toList());
                        this.mcAcqTxnWorkRepo.saveAllAndFlush(mcAcqWorkEntity);
                        this.updatePOSData(null, mcAcqTxnData, null, null, null);
                        this.mcAcqTxnDataRepo.deleteAll((Iterable)mcAcqTxnData);
                        this.mcAcqTxnDataRepo.flush();
                        this.outFileLogRepo.deleteByInstitutionCodeAndFileIdAndInterfaceCode(insCode, outFileId, intCode);
                        this.outFileLogRepo.flush();
                        return "Revert Successfully Completed";
                    }
                    return "No Outgoing Data for the file ID";
                }
                case "VISA": {
                    int intCode = this.interfacesRepo.findByInterfaceCategory("VISA").getInterfaceCode();
                    String outFileId = this.outFileLogRepo.findTopBygeneratedStatusAndInterfaceCodeOrderByLastUpdatedDateDesc(4, intCode).getFileId();
                    List visaAcqData = this.visaTxnDataRepo.findByInstitutionCodeAndFileId(insCode, outFileId);
                    if (Objects.nonNull(visaAcqData) && !visaAcqData.isEmpty()) {
                        List visaAcqWorkEntity = visaAcqData.stream().map(arg_0 -> this.mapToVisaAcqWorkEntity(arg_0)).collect(Collectors.toList());
                        this.visaTxnWorkRepo.saveAllAndFlush(visaAcqWorkEntity);
                        this.updatePOSData(visaAcqData, null, null, null, null);
                        this.visaTxnDataRepo.deleteAll((Iterable)visaAcqData);
                        this.visaTxnDataRepo.flush();
                        this.outFileLogRepo.deleteByInstitutionCodeAndFileIdAndInterfaceCode(insCode, outFileId, intCode);
                        this.outFileLogRepo.flush();
                        return "Revert Successfully Completed";
                    }
                    return "No Outgoing Data for the file ID";
                }
                case "JAYWAN": {
                    int intCode = this.interfacesRepo.findByInterfaceCategory("JAYWAN").getInterfaceCode();
                    String outFileId = this.outFileLogRepo.findTopBygeneratedStatusAndInterfaceCodeOrderByLastUpdatedDateDesc(4, intCode).getFileId();
                    List jaywanAcqData = this.jWNAcqTxnDataRepo.findByInstitutionCodeAndFileID(insCode, outFileId);
                    if (Objects.nonNull(jaywanAcqData) && !jaywanAcqData.isEmpty()) {
                        List jaywanAcqWorkEntity = jaywanAcqData.stream().map(arg_0 -> this.mapToJaywanAcqWorkEntity(arg_0)).collect(Collectors.toList());
                        this.jaywanTxnWorkRepo.saveAllAndFlush(jaywanAcqWorkEntity);
                        this.updatePOSData(null, null, jaywanAcqData, null, null);
                        this.jWNAcqTxnDataRepo.deleteAll((Iterable)jaywanAcqData);
                        this.jWNAcqTxnDataRepo.flush();
                        this.outFileLogRepo.deleteByInstitutionCodeAndFileIdAndInterfaceCode(insCode, outFileId, intCode);
                        this.outFileLogRepo.flush();
                        return "Revert Successfully Completed";
                    }
                    return "No Outgoing Data for the file ID";
                }
                case "AMEX": {
                    int intCode = this.interfacesRepo.findByInterfaceCategory("AMEX").getInterfaceCode();
                    String outFileId = this.outFileLogRepo.findTopBygeneratedStatusAndInterfaceCodeOrderByLastUpdatedDateDesc(4, intCode).getFileId();
                    List amexAcqData = this.amexTxnDataRepo.findByInstitutionCodeAndFileId(insCode, outFileId);
                    if (Objects.nonNull(amexAcqData) && !amexAcqData.isEmpty()) {
                        List amexAcqWorkEntity = amexAcqData.stream().map(arg_0 -> this.mapToAmexAcqTxnWorkEntity(arg_0)).collect(Collectors.toList());
                        this.amexTxnWorkRepo.saveAllAndFlush(amexAcqWorkEntity);
                        this.updatePOSData(null, null, null, amexAcqData, null);
                        this.amexTxnDataRepo.deleteAll((Iterable)amexAcqData);
                        this.amexTxnDataRepo.flush();
                        this.outFileLogRepo.deleteByInstitutionCodeAndFileIdAndInterfaceCode(insCode, outFileId, intCode);
                        this.outFileLogRepo.flush();
                        return "Revert Successfully Completed";
                    }
                    return "No Outgoing Data for the file ID";
                }
            }
            return "Please provide valid network";
        }
        catch (Exception e) {
            log.error("Error:revertLastOutgoingData()", (Throwable)e);
            return "Please provide valid network";
        }
    }

    private McAcqTxnWorkEntity mapToMcAcqWorkEntity(McAcqTxnDataEntity mcAcqTxnData) {
        try {
            McAcqTxnWorkEntity entity = new McAcqTxnWorkEntity();
            entity.setLastUpdated(mcAcqTxnData.getLastUpdated());
            entity.setUpdatedUser(mcAcqTxnData.getUpdatedUser());
            entity.setInstitutionCode(mcAcqTxnData.getInstitutionCode().intValue());
            entity.setIntCode(mcAcqTxnData.getIntCode());
            entity.setGeneralStatus(3);
            entity.setPrjSerNumber(mcAcqTxnData.getPrjSerNumber());
            entity.setTxnRefSerNumber(mcAcqTxnData.getTxnRefSerNumber());
            entity.setTxnType(mcAcqTxnData.getTxnType());
            entity.setMessageTypeId(mcAcqTxnData.getMessageTypeId());
            entity.setCardNumber(mcAcqTxnData.getCardNumber());
            entity.setProcCode(mcAcqTxnData.getProcCode());
            entity.setTxnAmount(mcAcqTxnData.getTxnAmount());
            entity.setSurchargeAmount(mcAcqTxnData.getSurchargeAmount());
            entity.setLocalDateTime(mcAcqTxnData.getLocalDateTime());
            entity.setExpiryDate(mcAcqTxnData.getExpiryDate());
            entity.setPosDataCode(mcAcqTxnData.getPosDataCode());
            entity.setFunctionCode(mcAcqTxnData.getFunctionCode());
            entity.setMsgReasonCode(mcAcqTxnData.getMsgReasonCode());
            entity.setMcc(mcAcqTxnData.getMcc());
            entity.setAcqRefData(mcAcqTxnData.getAcqRefData());
            entity.setAcqinstIdCode(mcAcqTxnData.getAcqinstIdCode());
            entity.setRrn(mcAcqTxnData.getRrn());
            entity.setApprovalCode(mcAcqTxnData.getApprovalCode());
            entity.setResponseCode(mcAcqTxnData.getResponseCode());
            entity.setServiceCode(mcAcqTxnData.getServiceCode());
            entity.setTerminalId(mcAcqTxnData.getTerminalId());
            entity.setMerchantId(mcAcqTxnData.getMerchantId());
            entity.setMeName(mcAcqTxnData.getMeName());
            entity.setMeCity(mcAcqTxnData.getMeCity());
            entity.setMePinCode(mcAcqTxnData.getMePinCode());
            entity.setMeCountry(mcAcqTxnData.getMeCountry());
            entity.setTrlType(mcAcqTxnData.getTrlType());
            entity.setMotoEcomIndicator(mcAcqTxnData.getMotoEcomIndicator());
            entity.setTxnFeeAmount(mcAcqTxnData.getTxnFeeAmount());
            entity.setTxnCurrExp(mcAcqTxnData.getTxnCurrExp());
            entity.setTxnCurCode(mcAcqTxnData.getTxnCurCode());
            entity.setIrd(mcAcqTxnData.getIrd());
            entity.setSettlementIndicator(mcAcqTxnData.getSettlementIndicator());
            entity.setCardSeqNumber(mcAcqTxnData.getCardSeqNumber());
            entity.setAppCryptogram(mcAcqTxnData.getAppCryptogram());
            entity.setCryptInfoData(mcAcqTxnData.getCryptInfoData());
            entity.setIssAppData(mcAcqTxnData.getIssAppData());
            entity.setUpblNumber(mcAcqTxnData.getUpblNumber());
            entity.setAppTxnCounter(mcAcqTxnData.getAppTxnCounter());
            entity.setTrlVerResult(mcAcqTxnData.getTrlVerResult());
            entity.setChipTxnDate(mcAcqTxnData.getChipTxnDate());
            entity.setChipTxnType(mcAcqTxnData.getChipTxnType());
            entity.setCryptAmount(mcAcqTxnData.getCryptAmount());
            entity.setAppICProfile(mcAcqTxnData.getAppICProfile());
            entity.setTrlConCode(mcAcqTxnData.getTrlConCode());
            entity.setChipCashBack(mcAcqTxnData.getChipCashBack());
            entity.setCvmResult(mcAcqTxnData.getCvmResult());
            entity.setTrlCapabilities(mcAcqTxnData.getTrlCapabilities());
            entity.setIfdSerNumber(mcAcqTxnData.getIfdSerNumber());
            entity.setTcc(mcAcqTxnData.getTcc());
            entity.setChipCurCode(mcAcqTxnData.getChipCurCode());
            entity.setChipTrlType(mcAcqTxnData.getChipTrlType());
            entity.setTrlAppVerNumber(mcAcqTxnData.getTrlAppVerNumber());
            entity.setTxnSeqCounter(mcAcqTxnData.getTxnSeqCounter());
            entity.setIssAuthData(mcAcqTxnData.getIssAuthData());
            entity.setTxnlifeCycleId(mcAcqTxnData.getTxnlifeCycleId());
            entity.setMsgNumber(mcAcqTxnData.getMsgNumber());
            entity.setMemberText(mcAcqTxnData.getMemberText());
            entity.setOrgInstIdCode(mcAcqTxnData.getOrgInstIdCode());
            entity.setRevIndiCator(mcAcqTxnData.getRevIndiCator());
            entity.setMaid(mcAcqTxnData.getMaid());
            entity.setCardType(mcAcqTxnData.getCardType());
            entity.setCardDomIntlFlag(mcAcqTxnData.getCardDomIntlFlag());
            entity.setDmsSmsMode(mcAcqTxnData.getDmsSmsMode());
            entity.setPosPgType(mcAcqTxnData.getPosPgType());
            entity.setCentreProcDate(mcAcqTxnData.getCentreProcDate());
            entity.setFileProcDate(mcAcqTxnData.getFileProcDate());
            entity.setFileID(mcAcqTxnData.getFileID());
            entity.setEncryptedCardNumber(mcAcqTxnData.getEncryptedCardNumber());
            entity.setMrpSerNumber(mcAcqTxnData.getMrpSerNumber());
            entity.setMeCountryOfOrigin(mcAcqTxnData.getMeCountryOfOrigin());
            entity.setTipAmount(mcAcqTxnData.getTipAmount());
            entity.setChipTrlCapabilities(mcAcqTxnData.getChipTrlCapabilities());
            entity.setDedicatedFileName(mcAcqTxnData.getDedicatedFileName());
            entity.setCardAccepStreetAddress(mcAcqTxnData.getCardAccepStreetAddress());
            entity.setCustomerServicePhNum(mcAcqTxnData.getCustomerServicePhNum());
            entity.setDccIndicator(mcAcqTxnData.getDccIndicator());
            entity.setDccAmount(mcAcqTxnData.getDccAmount());
            entity.setDccCurrency(mcAcqTxnData.getDccCurrency());
            entity.setDccTxnCurrencyExponent(mcAcqTxnData.getDccTxnCurrencyExponent());
            entity.setMposAccDevType(mcAcqTxnData.getMposAccDevType());
            entity.setAccepterUrlAddress(mcAcqTxnData.getAccepterUrlAddress());
            return entity;
        }
        catch (Exception e) {
            log.error("Error : mapToMcAcqWorkEntity() : ", (Throwable)e);
            return null;
        }
    }

    private VisaAcqTxnWorkEntity mapToVisaAcqWorkEntity(VisaAcqTxnDataEntity visaAcqTxnData) {
        try {
            VisaAcqTxnWorkEntity entity = new VisaAcqTxnWorkEntity();
            entity.setSerNumber(visaAcqTxnData.getSerNumber());
            entity.setLastUpdated(LocalDateTime.now());
            entity.setUpdatedUser(visaAcqTxnData.getUpdatedUser());
            entity.setInstitutionCode(Integer.valueOf(visaAcqTxnData.getInstitutionCode()));
            entity.setIntCode(visaAcqTxnData.getIntCode());
            entity.setPrjSerNumber(visaAcqTxnData.getPrjSerNumber());
            entity.setGenStatus(Integer.valueOf(3));
            entity.setTxnRefNumber(visaAcqTxnData.getTxnRefNumber());
            entity.setTxnType(visaAcqTxnData.getTxnType());
            entity.setTxnCode(visaAcqTxnData.getTxnCode());
            entity.setEncCardNumber(visaAcqTxnData.getEncCardNumber());
            entity.setArn(visaAcqTxnData.getArn());
            entity.setPurchaseDate(visaAcqTxnData.getPurchaseDate());
            entity.setTxnCurCode(visaAcqTxnData.getTxnCurCode());
            entity.setTxnAmount(visaAcqTxnData.getTxnAmount());
            entity.setSchgAmount(visaAcqTxnData.getSchgAmount());
            entity.setMeName(visaAcqTxnData.getMeName());
            entity.setMeCity(visaAcqTxnData.getMeCity());
            entity.setMeCountry(visaAcqTxnData.getMeCountry());
            entity.setMcc(visaAcqTxnData.getMcc());
            entity.setApprovalCode(visaAcqTxnData.getApprovalCode());
            entity.setChIdMethod(visaAcqTxnData.getChIdMethod());
            entity.setPosEntryMode(visaAcqTxnData.getPosEntryMode());
            entity.setMemberText(visaAcqTxnData.getMemberText());
            entity.setFeePrgIndicator(visaAcqTxnData.getFeePrgIndicator());
            entity.setMerchantId(visaAcqTxnData.getMerchantId());
            entity.setTerminalId(visaAcqTxnData.getTerminalId());
            entity.setMotoEcomIndicator(visaAcqTxnData.getMotoEcomIndicator());
            entity.setAccSelection(visaAcqTxnData.getAccSelection());
            entity.setAcqBussId(visaAcqTxnData.getAcqBussId());
            entity.setPosEnvironment(visaAcqTxnData.getPosEnvironment());
            entity.setRespCode(visaAcqTxnData.getRespCode());
            entity.setTrlTxnDate(visaAcqTxnData.getTrlTxnDate());
            entity.setCryptAmount(visaAcqTxnData.getCryptAmount());
            entity.setCashbackAmount(visaAcqTxnData.getCashbackAmount());
            entity.setTxnId(visaAcqTxnData.getTxnId());
            entity.setVisaToken(visaAcqTxnData.getVisaToken());
            entity.setAuthCharIndicator(visaAcqTxnData.getAuthCharIndicator());
            entity.setAccFundSource(visaAcqTxnData.getAccFundSource());
            entity.setMarketSpecDataInd(visaAcqTxnData.getMarketSpecDataInd());
            entity.setProductId(visaAcqTxnData.getProductId());
            entity.setValidationCode(visaAcqTxnData.getValidationCode());
            entity.setSpendQualiIndictor(visaAcqTxnData.getSpendQualiIndictor());
            entity.setCollOnlyFlag(visaAcqTxnData.getCollOnlyFlag());
            entity.setFileId(visaAcqTxnData.getFileId());
            entity.setRetRefNumber(visaAcqTxnData.getRetRefNumber());
            entity.setReasonCode(visaAcqTxnData.getReasonCode());
            entity.setUsageCode(visaAcqTxnData.getUsageCode());
            entity.setProcCode(visaAcqTxnData.getProcCode());
            entity.setSetlFlag(visaAcqTxnData.getSetlFlag());
            entity.setTerminalCapability(visaAcqTxnData.getTerminalCapability());
            entity.setReimAttribute(visaAcqTxnData.getReimAttribute());
            entity.setStan(visaAcqTxnData.getStan());
            entity.setAuthAmount(visaAcqTxnData.getAuthAmount());
            entity.setTrlCapProfile(visaAcqTxnData.getTrlCapProfile());
            entity.setTrlCountryCode(visaAcqTxnData.getTrlCountryCode());
            entity.setUpblNumber(visaAcqTxnData.getUpblNumber());
            entity.setCardSeqNumber(visaAcqTxnData.getCardSeqNumber());
            entity.setAppTxnCounter(visaAcqTxnData.getAppTxnCounter());
            entity.setAppIcProfile(visaAcqTxnData.getAppIcProfile());
            entity.setAppCryptogram(visaAcqTxnData.getAppCryptogram());
            entity.setIssAppDataB2(visaAcqTxnData.getIssAppDataB2());
            entity.setIssAppDataB3(visaAcqTxnData.getIssAppDataB3());
            entity.setIssAppDataB4(visaAcqTxnData.getIssAppDataB4());
            entity.setIssAppDataB8(visaAcqTxnData.getIssAppDataB8());
            entity.setIssAppDataB9(visaAcqTxnData.getIssAppDataB9());
            entity.setIssAppDataB1(visaAcqTxnData.getIssAppDataB1());
            entity.setIssAppDataB17(visaAcqTxnData.getIssAppDataB17());
            entity.setIssAppDataB18(visaAcqTxnData.getIssAppDataB18());
            entity.setTrlVerResult(visaAcqTxnData.getTrlVerResult());
            entity.setFormFactorIndicator(visaAcqTxnData.getFormFactorIndicator());
            entity.setIssScriptResult(visaAcqTxnData.getIssScriptResult());
            entity.setServiceCode(visaAcqTxnData.getServiceCode());
            entity.setTxnFeeAmount(visaAcqTxnData.getTxnFeeAmount());
            entity.setSenderName(visaAcqTxnData.getSenderName());
            entity.setRecipientName(visaAcqTxnData.getRecipientName());
            entity.setBussAppId(visaAcqTxnData.getBussAppId());
            entity.setSenderAccount(visaAcqTxnData.getSenderAccount());
            entity.setDccIndicator(visaAcqTxnData.getDccIndicator());
            entity.setNetwork(visaAcqTxnData.getNetwork());
            entity.setDccCurrency(visaAcqTxnData.getDccCurrency());
            entity.setDccAmount(visaAcqTxnData.getDccAmount());
            entity.setAcceptanceTrlIndicator(visaAcqTxnData.getAcceptanceTrlIndicator());
            return entity;
        }
        catch (Exception e) {
            log.error("Error : mapToVisaAcqWorkEntity() :", (Throwable)e);
            return null;
        }
    }

    private JaywanAcqTxnWorkEntity mapToJaywanAcqWorkEntity(JaywanAcqTxnDataEntity jaywanAcqTxnData) {
        try {
            JaywanAcqTxnWorkEntity entity = new JaywanAcqTxnWorkEntity();
            entity.setSerialNumber(jaywanAcqTxnData.getSerialNumber());
            entity.setLastUpdated(LocalDateTime.now());
            entity.setUpdatedUser(jaywanAcqTxnData.getUpdatedUser());
            entity.setInstitutionCode(jaywanAcqTxnData.getInstitutionCode());
            entity.setIntCode(jaywanAcqTxnData.getIntCode());
            entity.setPrjSerNumber(jaywanAcqTxnData.getPrjSerNumber());
            entity.setTxnRefNumber(jaywanAcqTxnData.getTxnRefNumber());
            entity.setTxnType(jaywanAcqTxnData.getTxnType());
            entity.setMessageTypeId(jaywanAcqTxnData.getMessageTypeId());
            entity.setFunctionCode(jaywanAcqTxnData.getFunctionCode());
            entity.setLocalDateTime(jaywanAcqTxnData.getLocalDateTime());
            entity.setCardNumber(jaywanAcqTxnData.getCardNumber());
            entity.setAcqRefData(jaywanAcqTxnData.getAcqRefData());
            entity.setApprovalCode(jaywanAcqTxnData.getApprovalCode());
            entity.setTerminalId(jaywanAcqTxnData.getTerminalId());
            entity.setTxnAmount(jaywanAcqTxnData.getTxnAmount());
            entity.setSettledAmount(jaywanAcqTxnData.getSettledAmount());
            entity.setBillAmount(jaywanAcqTxnData.getBillAmount());
            entity.setSurchargeAmount(jaywanAcqTxnData.getSurchargeAmount());
            entity.setConvRate(jaywanAcqTxnData.getConvRate());
            entity.setTxnCurCode(jaywanAcqTxnData.getTxnCurCode());
            entity.setCashBackAmount(jaywanAcqTxnData.getCashBackAmount());
            entity.setRrn(jaywanAcqTxnData.getRrn());
            entity.setMerchantId(jaywanAcqTxnData.getMerchantId());
            entity.setMeName(jaywanAcqTxnData.getMeName());
            entity.setMeCity(jaywanAcqTxnData.getMeCity());
            entity.setMeStateCode(jaywanAcqTxnData.getMeStateCode());
            entity.setMeCountry(jaywanAcqTxnData.getMeCountry());
            entity.setMcc(jaywanAcqTxnData.getMcc());
            entity.setPosEntryMode(jaywanAcqTxnData.getPosEntryMode());
            entity.setAcqinstIdCode(jaywanAcqTxnData.getAcqinstIdCode());
            entity.setRevIndiCator(jaywanAcqTxnData.getRevIndiCator());
            entity.setCardDomIntlFlag(jaywanAcqTxnData.getCardDomIntlFlag());
            entity.setTrlType(jaywanAcqTxnData.getTrlType());
            entity.setMeCategoryType(jaywanAcqTxnData.getMeCategoryType());
            entity.setCardType(jaywanAcqTxnData.getCardType());
            entity.setDmsSmsMode(jaywanAcqTxnData.getDmsSmsMode());
            entity.setCentreProcDate(jaywanAcqTxnData.getCentreProcDate());
            entity.setFileProcDate(jaywanAcqTxnData.getFileProcDate());
            entity.setFileID(jaywanAcqTxnData.getFileID());
            entity.setGenStatus(Integer.valueOf(3));
            entity.setEncryptedCardNumber(jaywanAcqTxnData.getEncryptedCardNumber());
            entity.setResponseCode(jaywanAcqTxnData.getResponseCode());
            entity.setMotoEcomIndicator(jaywanAcqTxnData.getMotoEcomIndicator());
            entity.setSettlDate(jaywanAcqTxnData.getSettlDate());
            entity.setSettlIndiCator(jaywanAcqTxnData.getSettlIndiCator());
            entity.setPosConditionCode(jaywanAcqTxnData.getPosConditionCode());
            entity.setFullPartialIndiCator(jaywanAcqTxnData.getFullPartialIndiCator());
            return entity;
        }
        catch (Exception e) {
            log.error("Error : mapToJaywanAcqWorkEntity() :", (Throwable)e);
            return null;
        }
    }

    private AmexAcqTxnWorkEntity mapToAmexAcqTxnWorkEntity(AmexAcqTxnDataEntity amexAcqTxnData) {
        try {
            AmexAcqTxnWorkEntity entity = new AmexAcqTxnWorkEntity();
            entity.setSerNumber(amexAcqTxnData.getSerNumber());
            entity.setLastUpdated(LocalDateTime.now());
            entity.setUpdatedUser(amexAcqTxnData.getUpdatedUser());
            entity.setInstitutionCode(amexAcqTxnData.getInstitutionCode());
            entity.setIntCode(amexAcqTxnData.getIntCode());
            entity.setPrjSerNumber(amexAcqTxnData.getPrjSerNumber());
            entity.setTxnRefSerNumber(amexAcqTxnData.getTxnRefSerNumber());
            entity.setTxnType(amexAcqTxnData.getTxnType());
            entity.setCardNumber(amexAcqTxnData.getCardNumber());
            entity.setProcCode(amexAcqTxnData.getProcCode());
            entity.setTxnAmount(amexAcqTxnData.getTxnAmount());
            entity.setSurchargeAmount(amexAcqTxnData.getSurchargeAmount());
            entity.setLocalDateTime(amexAcqTxnData.getLocalDateTime());
            entity.setPosDataCode(amexAcqTxnData.getPosDataCode());
            entity.setMcc(amexAcqTxnData.getMcc());
            entity.setRrn(amexAcqTxnData.getRrn());
            entity.setApprovalCode(amexAcqTxnData.getApprovalCode());
            entity.setTerminalId(amexAcqTxnData.getTerminalId());
            entity.setMerchantId(amexAcqTxnData.getMerchantId());
            entity.setMappedMid(amexAcqTxnData.getMappedMid());
            entity.setMeName(amexAcqTxnData.getMeName());
            entity.setMeCity(amexAcqTxnData.getMeCity());
            entity.setMePinCode(amexAcqTxnData.getMePinCode());
            entity.setMeCountry(amexAcqTxnData.getMeCountry());
            entity.setMotoEcomIndicator(amexAcqTxnData.getMotoEcomIndicator());
            entity.setTxnCurCode(amexAcqTxnData.getTxnCurCode());
            entity.setCardSeqNumber(amexAcqTxnData.getCardSeqNumber());
            entity.setAppCryptogram(amexAcqTxnData.getAppCryptogram());
            entity.setCryptInfoData(amexAcqTxnData.getCryptInfoData());
            entity.setIssAppData(amexAcqTxnData.getIssAppData());
            entity.setUpblNumber(amexAcqTxnData.getUpblNumber());
            entity.setAppTxnCounter(amexAcqTxnData.getAppTxnCounter());
            entity.setTrlVerResult(amexAcqTxnData.getTrlVerResult());
            entity.setTxnDate(amexAcqTxnData.getTxnDate());
            entity.setCryptAmount(amexAcqTxnData.getCryptAmount());
            entity.setAppICProfile(amexAcqTxnData.getAppICProfile());
            entity.setTrlConCode(amexAcqTxnData.getTrlConCode());
            entity.setChipCashBack(amexAcqTxnData.getChipCashBack());
            entity.setTxnId(amexAcqTxnData.getTxnId());
            entity.setTrlBthNumber(amexAcqTxnData.getTrlBthNumber());
            entity.setCardType(amexAcqTxnData.getCardType());
            entity.setCardDomIntlFlag(amexAcqTxnData.getCardDomIntlFlag());
            entity.setDmsSmsMode(amexAcqTxnData.getDmsSmsMode());
            entity.setTrlType(amexAcqTxnData.getTrlType());
            entity.setCentreProcDate(amexAcqTxnData.getCentreProcDate());
            entity.setOutFileDate(amexAcqTxnData.getOutFileDate());
            entity.setFileId(amexAcqTxnData.getFileId());
            entity.setGenStatus(Integer.valueOf(3));
            entity.setEncryptedCardNumber(amexAcqTxnData.getEncryptedCardNumber());
            entity.setExpiryDate(amexAcqTxnData.getExpiryDate());
            entity.setEmv(amexAcqTxnData.getEmv());
            entity.setLocationAddress(amexAcqTxnData.getLocationAddress());
            entity.setContactEmail(amexAcqTxnData.getContactEmail());
            entity.setTrlLocation(amexAcqTxnData.getTrlLocation());
            entity.setLocRegionCode(amexAcqTxnData.getLocRegionCode());
            return entity;
        }
        catch (Exception e) {
            log.error("Error : mapToAmexAcqTxnWorkEntity() :", (Throwable)e);
            return null;
        }
    }

    private void updatePOSData(List<VisaAcqTxnDataEntity> visaAcqData, List<McAcqTxnDataEntity> mcAcqData, List<JaywanAcqTxnDataEntity> jaywanAcqData, List<AmexAcqTxnDataEntity> amexAcqData, List<MercuryAcqTxnDataEntity> mercuryAcqData) {
        try {
            List<Integer> posCodes = null;
            posCodes = Objects.nonNull(visaAcqData) ? visaAcqData.stream().map(VisaAcqTxnDataEntity::getTxnRefNumber).filter(Objects::nonNull).toList() : (Objects.nonNull(mcAcqData) ? mcAcqData.stream().map(McAcqTxnDataEntity::getTxnRefSerNumber).filter(Objects::nonNull).toList() : (Objects.nonNull(jaywanAcqData) ? jaywanAcqData.stream().map(JaywanAcqTxnDataEntity::getTxnRefNumber).filter(Objects::nonNull).toList() : (Objects.nonNull(amexAcqData) ? amexAcqData.stream().map(AmexAcqTxnDataEntity::getTxnRefSerNumber).filter(Objects::nonNull).toList() : mercuryAcqData.stream().map(MercuryAcqTxnDataEntity::getTxnRefNumber).filter(Objects::nonNull).toList())));
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

    public String automateSchedulerTriggering(String endTimeString, String network) {
        int user = Integer.parseInt(Objects.requireNonNull(this.env.getProperty("UPDATED_USER")));
        int insCode = Integer.parseInt(Objects.requireNonNull(this.env.getProperty("INS_CODE")));
        String insShortName = this.env.getProperty("INS_SHORT_NAME");
        int formatCode = switch (network) {
            case "MASTERCARD" -> Integer.parseInt(Objects.requireNonNull(this.env.getProperty("MASTERCARD_SYSTEM_CODE")));
            case "VISA" -> Integer.parseInt(Objects.requireNonNull(this.env.getProperty("VISA_SYSTEM_CODE")));
            case "JAYWAN" -> Integer.parseInt(Objects.requireNonNull(this.env.getProperty("JAYWAN_SYSTEM_CODE")));
            case "AMEX" -> Integer.parseInt(Objects.requireNonNull(this.env.getProperty("AMEX_SYSTEM_CODE")));
            default -> 0;
        };
        LocalTime specificTime = LocalTime.parse(endTimeString, DateTimeFormatter.ofPattern("HH:mm:ss"));
        LocalDateTime endTime = LocalDateTime.of(LocalDate.now(), specificTime);
        log.info("~~~ Network:" + network);
        log.info("~~~ EndTime:" + String.valueOf(endTime));
        Integer txnCount = this.getTxnCount(network, Integer.valueOf(insCode), null, endTime);
        log.info("txnCount :" + txnCount);
        if (txnCount == 0) {
            log.info("~~~ There are no transactions to stage! ~~~");
            return "There are no transactions to stage!";
        }
        this.scheduleFileProcessing(Integer.valueOf(insCode), Integer.valueOf(user), Integer.valueOf(formatCode), insShortName, network, null, endTime);
        log.info("~~~ Outgoing File Processing Scheduled Successfully ~~~");
        return "Outgoing File Processing Scheduled Successfully.";
    }

    public OutGoingProcessingService(McAcqTxnWorkRepo mcAcqTxnWorkRepo, MCOutgoingService mcOutGoingService, McAcqTxnDataRepo mcAcqTxnDataRepo, OutFileLogRepo outFileLogRepo, InterfacesRepo interfacesRepo, VisaAcqTxnWorkRepo visaTxnWorkRepo, VisaOutgoingService visaOutService, VisaAcqTxnDataRepo visaTxnDataRepo, PosTransactionRepo posTransactionRepo, JaywanOutgoingService jaywanService, JWNAcqTxnWorkRepo jaywanTxnWorkRepo, JWNAcqTxnDataRepo jWNAcqTxnDataRepo, AmexOutgoingService amexService, AmexTxnWorkRepo amexTxnWorkRepo, AmexTxnDataRepo amexTxnDataRepo, MercuryOutgoingService mercuryService, MercuryAcqTxnWorkRepo mercuryTxnWorkRepo, MercuryAcqTxnDataRepo mercuryTxnDataRepo) {
        this.mcAcqTxnWorkRepo = mcAcqTxnWorkRepo;
        this.mcOutGoingService = mcOutGoingService;
        this.mcAcqTxnDataRepo = mcAcqTxnDataRepo;
        this.outFileLogRepo = outFileLogRepo;
        this.interfacesRepo = interfacesRepo;
        this.visaTxnWorkRepo = visaTxnWorkRepo;
        this.visaOutService = visaOutService;
        this.visaTxnDataRepo = visaTxnDataRepo;
        this.posTransactionRepo = posTransactionRepo;
        this.jaywanService = jaywanService;
        this.jaywanTxnWorkRepo = jaywanTxnWorkRepo;
        this.jWNAcqTxnDataRepo = jWNAcqTxnDataRepo;
        this.amexService = amexService;
        this.amexTxnWorkRepo = amexTxnWorkRepo;
        this.amexTxnDataRepo = amexTxnDataRepo;
        this.mercuryService = mercuryService;
        this.mercuryTxnWorkRepo = mercuryTxnWorkRepo;
        this.mercuryTxnDataRepo = mercuryTxnDataRepo;
    }
}

