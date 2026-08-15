// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.services;

import org.apache.logging.log4j.LogManager;
import jakarta.validation.ConstraintViolation;
import com.empay.entities.IRFCallbackEntity;
import java.util.Optional;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.HttpHeaders;
import com.empay.cryptapi.DecryptResponseVo;
import java.util.Arrays;
import java.util.Collections;
import java.nio.file.Path;
import java.io.FileInputStream;
import java.nio.file.Paths;
import java.util.Set;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import java.io.Reader;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.InputStream;
import com.empay.common.entities.FileUploadLogEntity;
import java.time.chrono.ChronoLocalDate;
import com.empay.common.entities.CountriesEntity;
import java.time.LocalDate;
import org.apache.commons.lang3.StringUtils;
import com.empay.tlfprocessing.vo.SwitchExtractVo;
import java.time.LocalDateTime;
import jakarta.transaction.Transactional;
import com.empay.entities.McGCOTxnWorkEntity;
import com.empay.entities.JaywanAcqTxnWorkEntity;
import com.empay.entities.AmexAcqTxnWorkEntity;
import com.empay.entities.VisaAcqTxnWorkEntity;
import com.fasterxml.jackson.databind.JsonNode;
import com.empay.entities.McRTSTxnDataEntity;
import com.empay.entities.McAcqTxnWorkEntity;
import com.empay.entities.UAERefundTranasactionEntity;
import com.empay.tlfprocessing.vo.IRFResultVo;
import java.util.List;
import org.springframework.beans.BeanUtils;
import com.empay.entities.VisaGOCWorkEntity;
import java.util.function.Function;
import java.util.Comparator;
import com.empay.entities.PosTransactionEntity;
import com.empay.tlfprocessing.vo.ResponseVo;
import java.util.ArrayList;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.HttpStatus;
import java.util.Objects;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import com.empay.tlfprocessing.vo.RequestVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestTemplate;
import com.empay.common.repo.CountriesRepository;
import com.empay.repositories.VisaGOCTxnRepo;
import com.empay.repositories.McGCOTxnRepo;
import com.empay.common.functions.JaywanIRFCalculation;
import com.empay.common.functions.OnusIRFCalculation;
import com.empay.common.functions.OmanNetIRFCalculation;
import com.empay.repositories.McRTSTxnDataRepo;
import com.empay.util.CurrencyUtil;
import com.empay.common.repo.FileUploadRepo;
import com.empay.commonservice.CommonManagementsService;
import com.empay.common.functions.UAESwitchIRFCalculation;
import com.empay.cryptapi.CryptAPI;
import java.time.format.DateTimeFormatter;
import com.empay.repositories.JaywanAcqTxnWorkRepo;
import com.empay.repositories.UAERefundTransactionRepo;
import com.empay.repositories.AmexAcqTxnRepo;
import com.empay.repositories.VisaAcqTxnRepo;
import org.springframework.core.env.Environment;
import com.empay.visa.VisaIrfCalculation;
import com.empay.common.functions.UAEMcIRFCalculation;
import com.empay.repositories.McAcqTxnWorkRepo;
import com.empay.repositories.BusinessDateRepo;
import com.empay.repositories.PosTransactionRepository;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class TxnProcessingService
{
    private static final Logger log;
    private final PosTransactionRepository posTxnRepo;
    private final BusinessDateRepo businessDateRepo;
    private final PosTransactionRepository posTransactionRepo;
    private final ValidationService validationService;
    private final McAcqTxnWorkRepo mcAcqTxnWorkRepo;
    private final McSplitTransactionService mcSplitTxnService;
    private final UAEMcIRFCalculation uaeMcIrf;
    private final VisaIrfCalculation visaIrf;
    private final IRFCallbackService irfCallbackService;
    private final Environment env;
    private final VisaSplitTxnService visaSplitService;
    private final AmexSplitTxnService amexSplitService;
    private final VisaAcqTxnRepo visaRepo;
    private final AmexAcqTxnRepo amexRepo;
    private final UAERefundTransactionRepo UaeRefundTxnRepo;
    private final JaywanAcqTxnWorkRepo jaywanWorkRepo;
    private final JaywanSplitTxnService jaywanSplitService;
    private static final String yyyyMMdd = "yyyyMMdd";
    private DateTimeFormatter formatter;
    private final CryptAPI cryptApi;
    private final UAESwitchIRFCalculation uaeSwitchIrf;
    private final CommonManagementsService commonService;
    private final FileUploadRepo fileUploadLogRepo;
    private final CurrencyUtil currencyUtil;
    private final McRTSTxnDataRepo mcRTSTxnDataRepo;
    private final OmanNetIRFCalculation omanNetIrf;
    private final OnusIRFCalculation onusIrf;
    private final JaywanAcqTxnWorkRepo jaywanRepo;
    private final JaywanIRFCalculation jaywanIrf;
    private final McGCOTxnRepo mcGCOTxnRepo;
    private final McGCOSplitTxnService mcGCOSplitService;
    private final VisaGOCTxnRepo visaGOCTxnRepo;
    private final CountriesRepository countriesRepo;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    
    @Transactional
    public ResponseEntity<Map<String, String>> processOnlineTxn(final RequestVo switchVo, final boolean kafkaFlag, final String type, final int jobnumber, final String fileName) {
        final Map<String, String> response = new HashMap<String, String>();
        try {
            TxnProcessingService.log.info("/-------------------- Txn Loading Start --------------------/");
            final Integer jobNumber = jobnumber;
            Integer intCode;
            if (type.equals("KAFKA")) {
                intCode = Integer.parseInt(Objects.requireNonNull(this.env.getProperty("INTERFACE_CODE_KAFKA"), "Interface Code is Null"));
            }
            else {
                intCode = Integer.parseInt(Objects.requireNonNull(this.env.getProperty("INTERFACE_CODE_TLF"), "Interface Code is Null"));
            }
            final Integer insCode = Integer.parseInt(Objects.requireNonNull(this.env.getProperty("insCode"), "Institution code is Null"));
            final Integer user = Integer.parseInt(Objects.requireNonNull(this.env.getProperty("userSerNumber"), "User serial Number is Null"));
            if (Objects.nonNull(switchVo) && Objects.nonNull(switchVo.getPayload())) {
                this.validationService.validateTxnRequest(switchVo.getPayload(), kafkaFlag, fileName);
            }
            final String cardNumber = this.getCardNumber(switchVo.getPayload().getTokenIdentifier());
            if (Objects.isNull(cardNumber)) {
                if (type.equals("KAFKA")) {
                    response.put("status", "Crypt API Failed , No CardNumber found");
                    return (ResponseEntity<Map<String, String>>)new ResponseEntity((Object)response, (HttpStatusCode)HttpStatus.NOT_FOUND);
                }
                final String errorMsg = "Crypt API Failed, No CardNumber found";
                response.put("status", errorMsg);
                final List<String> validationErrors = new ArrayList<String>();
                validationErrors.add(errorMsg);
                final ResponseVo responseVo = new ResponseVo();
                responseVo.setResponseCode(switchVo.getPayload().getResponseCode());
                responseVo.setResponseMessage("Transaction Rejected");
                responseVo.setValidationErrors(validationErrors);
                final String txnDateTime = switchVo.getPayload().getTxnDateTime();
                this.commonService.insertRejectedTxns(responseVo, validationErrors, txnDateTime, jobNumber, type, fileName);
                return (ResponseEntity<Map<String, String>>)new ResponseEntity((Object)response, (HttpStatusCode)HttpStatus.NOT_FOUND);
            }
            else {
                final PosTransactionEntity txnEntity = this.mapTxnToPosData(switchVo.getPayload(), insCode, jobNumber, intCode, user);
                final boolean irfStatusCheck = this.irfStatusCheck(txnEntity.getResponseCode(), txnEntity.getProcCode(), txnEntity.getDmsSmsMode(), txnEntity.getRevIndiCator());
                this.posTxnRepo.saveAndFlush((Object)txnEntity);
                TxnProcessingService.log.info("-------------irfStatusCheck :-----------" + irfStatusCheck);
                if (irfStatusCheck) {
                    final IRFResultVo irfVo = this.fetchIrf(insCode, txnEntity, cardNumber);
                    if (irfVo == null) {
                        TxnProcessingService.log.info("No IRF Data Found..");
                        this.updateDefaultIrf(txnEntity);
                    }
                    else {
                        this.updateIrfTxn(txnEntity, irfVo);
                    }
                    this.irfCallback(txnEntity);
                }
                else {
                    this.updateDefaultIrf(txnEntity);
                }
                this.posTxnRepo.saveAndFlush((Object)txnEntity);
                final boolean outgoingStatusCheck = this.outgoingStatusCheck(txnEntity.getResponseCode(), txnEntity.getProcCode(), txnEntity.getDmsSmsMode(), txnEntity.getRevIndiCator(), txnEntity.getScheme(), txnEntity.getNetwork());
                TxnProcessingService.log.info("-------------OutgoingStatusCheck :-----------" + outgoingStatusCheck);
                if (!outgoingStatusCheck) {
                    txnEntity.setOutStatus("NA");
                    txnEntity.setIncomingStatus("NA");
                    this.posTxnRepo.saveAndFlush((Object)txnEntity);
                    response.put("status", " Transaction status is not successful ");
                    return (ResponseEntity<Map<String, String>>)new ResponseEntity((Object)response, (HttpStatusCode)HttpStatus.OK);
                }
                TxnProcessingService.log.info("/--------------------Start: " + txnEntity.getRrn() + " --------------------/");
                if (switchVo.getPayload().getNetwork().equalsIgnoreCase("UAESWITCH") && (switchVo.getPayload().getProcessCode().startsWith("20") || switchVo.getPayload().getProcessCode().startsWith("06")) && switchVo.getPayload().getMti().equals("0110")) {
                    final UAERefundTranasactionEntity refundEntity = this.buildUaeRefundEntity(txnEntity, user, insCode, intCode, jobNumber);
                    this.UaeRefundTxnRepo.save((Object)refundEntity);
                }
                final List<PosTransactionEntity> posTxnEntities = this.posTransactionRepo.findByRrnAndRevIndiCatorNotAndGenStatusNot(txnEntity.getRrn(), 'R', 6);
                PosTransactionEntity posTxnEntity = null;
                if (posTxnEntities != null && !posTxnEntities.isEmpty()) {
                    posTxnEntity = posTxnEntities.stream().max(Comparator.comparing((Function<? super PosTransactionEntity, ? extends Comparable>)PosTransactionEntity::getSerialNumber)).orElse(null);
                }
                if (Objects.nonNull(posTxnEntity) && Objects.nonNull(posTxnEntity.getNetwork()) && Objects.nonNull(posTxnEntity.getRevIndiCator()) && posTxnEntity.getRevIndiCator() != 'R') {
                    if (posTxnEntity.getNetwork().toUpperCase().matches("MCI|MDS") && posTxnEntity.getResponseCode().equals("00")) {
                        if (posTxnEntity.getDmsSmsMode() == 'D') {
                            final McAcqTxnWorkEntity splitTxn = this.mcSplitTxnService.mapToMcAcqTxnWorkEntity(posTxnEntity, user, insCode, jobNumber);
                            if (Objects.nonNull(splitTxn)) {
                                this.mcAcqTxnWorkRepo.saveAndFlush((Object)splitTxn);
                                posTxnEntity.setGenStatus(4);
                                posTxnEntity.setOutStatus("Marked for Outgoing");
                                this.posTxnRepo.saveAndFlush((Object)posTxnEntity);
                                if (jobnumber != 1) {
                                    final List<PosTransactionEntity> posEntityList = this.posTxnRepo.findByJobNumber(jobnumber);
                                    final double totalTxnAmount = posEntityList.stream().filter(txn -> txn.getTxnAmount() != null).mapToDouble(PosTransactionEntity::getTxnAmount).sum();
                                    this.commonService.updateFileUploadLog(jobnumber, 4, posEntityList.size(), totalTxnAmount);
                                }
                            }
                        }
                        else if (posTxnEntity.getDmsSmsMode() == 'R') {
                            final McRTSTxnDataEntity splitTxn2 = this.mcSplitTxnService.mapToMcRtsTxnData(posTxnEntity, user, insCode, jobNumber);
                            if (Objects.nonNull(splitTxn2)) {
                                this.mcRTSTxnDataRepo.saveAndFlush((Object)splitTxn2);
                                if ("Sale Transaction API Failed".equalsIgnoreCase(posTxnEntity.getOutStatus())) {
                                    splitTxn2.setApiResponse("Reversal against Sale Transaction API is Failed");
                                    this.mcRTSTxnDataRepo.saveAndFlush((Object)splitTxn2);
                                    posTxnEntity.setRevIndiCator('R');
                                    posTxnEntity.setOutStatus("NA");
                                    posTxnEntity.setIncomingStatus("NA");
                                    this.posTxnRepo.saveAndFlush((Object)posTxnEntity);
                                    TxnProcessingService.log.warn("Reversal skipped: Sale transaction API previously failed for job {}", (Object)jobNumber);
                                }
                                else {
                                    final Map<String, Object> rtsRequestMap = this.buildRtsRequest(splitTxn2);
                                    final ResponseEntity<JsonNode> rtsResponse = this.sendApiRequest(rtsRequestMap);
                                    if (rtsResponse.getStatusCode() == HttpStatus.OK) {
                                        splitTxn2.setGeneralStatus(4);
                                        splitTxn2.setApiResponse("Success");
                                        this.mcRTSTxnDataRepo.saveAndFlush((Object)splitTxn2);
                                        if ("Sale Transaction API Success".equalsIgnoreCase(posTxnEntity.getOutStatus())) {
                                            posTxnEntity.setRevIndiCator('R');
                                            posTxnEntity.setGenStatus(2);
                                        }
                                        else {
                                            posTxnEntity.setGenStatus(4);
                                        }
                                        posTxnEntity.setOutStatus("Completed");
                                        posTxnEntity.setIncomingStatus("NA");
                                        this.posTxnRepo.saveAndFlush((Object)posTxnEntity);
                                        if (jobnumber != 1) {
                                            final List<PosTransactionEntity> posEntityList2 = this.posTxnRepo.findByJobNumber(jobnumber);
                                            final double totalTxnAmount2 = posEntityList2.stream().filter(txn -> txn.getTxnAmount() != null).mapToDouble(PosTransactionEntity::getTxnAmount).sum();
                                            this.commonService.updateFileUploadLog(jobnumber, 4, posEntityList2.size(), totalTxnAmount2);
                                        }
                                    }
                                    else {
                                        splitTxn2.setApiResponse("Failed");
                                        this.mcRTSTxnDataRepo.saveAndFlush((Object)splitTxn2);
                                        posTxnEntity.setOutStatus("Marked for Outgoing");
                                        posTxnEntity.setIncomingStatus("NA");
                                        this.posTxnRepo.saveAndFlush((Object)posTxnEntity);
                                        TxnProcessingService.log.error("RTS API call failed with status: {}", (Object)rtsResponse);
                                    }
                                }
                            }
                        }
                    }
                    if (posTxnEntity.getNetwork().toUpperCase().matches("VISA") && posTxnEntity.getResponseCode().equals("00") && posTxnEntity.getDmsSmsMode() == 'D') {
                        final VisaAcqTxnWorkEntity splitTxn3 = this.visaSplitService.mapToVisaAcqTxnEntity(posTxnEntity, user, insCode, jobNumber);
                        if (Objects.nonNull(splitTxn3)) {
                            this.visaRepo.saveAndFlush((Object)splitTxn3);
                            posTxnEntity.setGenStatus(4);
                            posTxnEntity.setOutStatus("Marked for Outgoing");
                            this.posTxnRepo.saveAndFlush((Object)posTxnEntity);
                            if (jobnumber != 1) {
                                final List<PosTransactionEntity> posEntityList = this.posTxnRepo.findByJobNumber(jobnumber);
                                final double totalTxnAmount = posEntityList.stream().filter(txn -> txn.getTxnAmount() != null).mapToDouble(PosTransactionEntity::getTxnAmount).sum();
                                this.commonService.updateFileUploadLog(jobnumber, 4, posEntityList.size(), totalTxnAmount);
                            }
                        }
                    }
                    if (posTxnEntity.getNetwork().toUpperCase().matches("AMEX") && posTxnEntity.getResponseCode().equals("00") && posTxnEntity.getDmsSmsMode() == 'D') {
                        final AmexAcqTxnWorkEntity splitTxn4 = this.amexSplitService.mapToAmexAcqTxnEntity(posTxnEntity, user, insCode, jobNumber);
                        if (Objects.nonNull(splitTxn4)) {
                            this.amexRepo.saveAndFlush((Object)splitTxn4);
                            posTxnEntity.setGenStatus(4);
                            posTxnEntity.setOutStatus("Marked for Outgoing");
                            this.posTxnRepo.saveAndFlush((Object)posTxnEntity);
                        }
                    }
                    if (posTxnEntity.getScheme().toUpperCase().matches("JAYWAN") && posTxnEntity.getResponseCode().equals("00") && posTxnEntity.getDmsSmsMode() == 'D') {
                        final JaywanAcqTxnWorkEntity splitTxn5 = this.jaywanSplitService.mapToJaywanAcqTxnEntity(posTxnEntity, user, insCode, jobNumber);
                        if (Objects.nonNull(splitTxn5)) {
                            this.jaywanWorkRepo.saveAndFlush((Object)splitTxn5);
                            posTxnEntity.setGenStatus(4);
                            posTxnEntity.setOutStatus("Marked for Outgoing");
                            this.posTxnRepo.saveAndFlush((Object)posTxnEntity);
                        }
                    }
                    if (posTxnEntity.getNetwork().toUpperCase().matches("OMANNET|UAESWITCH") && posTxnEntity.getResponseCode().equals("00") && posTxnEntity.getScheme().equalsIgnoreCase("MCI") && posTxnEntity.getDmsSmsMode() != 'R') {
                        final McGCOTxnWorkEntity splitTxn6 = this.mcGCOSplitService.mapToGCOTxnEntity(posTxnEntity, user, insCode, jobNumber);
                        if (Objects.nonNull(splitTxn6)) {
                            this.mcGCOTxnRepo.saveAndFlush((Object)splitTxn6);
                            posTxnEntity.setGenStatus(4);
                            posTxnEntity.setOutStatus("Marked for Outgoing");
                            this.posTxnRepo.saveAndFlush((Object)posTxnEntity);
                        }
                    }
                    if (posTxnEntity.getNetwork().toUpperCase().matches("OMANNET|UAESWITCH") && posTxnEntity.getResponseCode().equals("00") && posTxnEntity.getScheme().equalsIgnoreCase("VISA") && posTxnEntity.getDmsSmsMode() != 'R') {
                        final VisaAcqTxnWorkEntity splitTxn3 = this.visaSplitService.mapToVisaAcqTxnEntity(posTxnEntity, user, insCode, jobNumber);
                        if (Objects.nonNull(splitTxn3)) {
                            final VisaGOCWorkEntity gocTxn = new VisaGOCWorkEntity();
                            BeanUtils.copyProperties((Object)splitTxn3, (Object)gocTxn);
                            this.visaGOCTxnRepo.saveAndFlush((Object)gocTxn);
                            posTxnEntity.setGenStatus(4);
                            posTxnEntity.setOutStatus("Marked for Outgoing");
                            this.posTxnRepo.saveAndFlush((Object)posTxnEntity);
                        }
                    }
                }
                if (Objects.nonNull(posTxnEntity)) {
                    try {
                        response.put("irdCode", Objects.nonNull(posTxnEntity.getIrd()) ? posTxnEntity.getIrd() : "");
                        response.put("fixed", Objects.nonNull(posTxnEntity.getIrfFixed()) ? posTxnEntity.getIrfFixed().toString() : "");
                        response.put("percentage", Objects.nonNull(posTxnEntity.getIrfPercent()) ? posTxnEntity.getIrfPercent().toString() : "");
                        response.put("amount", Objects.nonNull(posTxnEntity.getIrfAmount()) ? posTxnEntity.getIrfAmount().toString() : "");
                        response.put("domIntlFlag", Objects.nonNull(posTxnEntity.getCardDomIntlFlag()) ? posTxnEntity.getCardDomIntlFlag().toString() : "");
                        response.put("cardType", Objects.nonNull(posTxnEntity.getCardType()) ? posTxnEntity.getCardType().toString() : "");
                        response.put("description", Objects.nonNull(posTxnEntity.getRemarks()) ? posTxnEntity.getRemarks() : "No Remarks");
                        response.put("status", "Completed");
                    }
                    catch (final Exception e) {
                        TxnProcessingService.log.error("IRF Callback error :", (Throwable)e);
                    }
                    TxnProcessingService.log.info("/-------------------- OnlineTxn End: " + txnEntity.getRrn() + " --------------------/");
                    return (ResponseEntity<Map<String, String>>)new ResponseEntity((Object)response, (HttpStatusCode)HttpStatus.OK);
                }
                response.put("status", "No Data Found");
                return (ResponseEntity<Map<String, String>>)new ResponseEntity((Object)response, (HttpStatusCode)HttpStatus.NOT_FOUND);
            }
        }
        catch (final Exception e2) {
            if (e2.getMessage() != null) {
                response.put("status", "Transaction Processing Failed:" + e2.getMessage().replaceAll("([\\w]+\\.)+", ""));
            }
            TxnProcessingService.log.error("ERROR:processOnlineTxn():", (Throwable)e2);
            return (ResponseEntity<Map<String, String>>)new ResponseEntity((Object)response, (HttpStatusCode)HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    private boolean outgoingStatusCheck(final String responseCode, final String procCode, final char dmsSmsMode, final char revIndicator, final String scheme, final String network) {
        if (scheme == null || network == null) {
            return false;
        }
        if (scheme.toUpperCase().matches("MCI|VISA") && network.toUpperCase().matches("OMANNET|UAESWITCH")) {
            return "00".equals(responseCode) && revIndicator != 'R' && (dmsSmsMode == 'D' || dmsSmsMode == 'S');
        }
        if (scheme.toUpperCase().matches("JAYWAN") && network.toUpperCase().matches("OMANNET|UAESWITCH")) {
            return "00".equals(responseCode) && revIndicator != 'R' && dmsSmsMode == 'D';
        }
        if (scheme.toUpperCase().matches("DISCOVER|DINERS|MERCURY|RUPAY") && network.toUpperCase().matches("MERCURY")) {
            return "00".equals(responseCode) && revIndicator != 'R' && (dmsSmsMode == 'D' || dmsSmsMode == 'S');
        }
        if (!scheme.equalsIgnoreCase(network)) {
            return false;
        }
        if ("ONUS".equalsIgnoreCase(scheme) || "OMANNET".equalsIgnoreCase(scheme)) {
            return false;
        }
        if (!"00".equals(responseCode) || revIndicator == 'R' || dmsSmsMode == 'S') {
            return false;
        }
        if (dmsSmsMode == 'R') {
            return !"610000".equals(procCode) && !"620000".equals(procCode);
        }
        return dmsSmsMode == 'D' && ("000000".equals(procCode) || "200000".equals(procCode));
    }
    
    private boolean irfStatusCheck(final String responseCode, final String procCode, final char dmsSmsMode, final char revIndicator) {
        if (!"00".equals(responseCode) || revIndicator == 'R') {
            return false;
        }
        if (dmsSmsMode == 'R') {
            return !"610000".equals(procCode) && !"620000".equals(procCode);
        }
        return (dmsSmsMode == 'D' || dmsSmsMode == 'S') && ("000000".equals(procCode) || "200000".equals(procCode));
    }
    
    private UAERefundTranasactionEntity buildUaeRefundEntity(final PosTransactionEntity txnEntity, final Integer user, final Integer insCode, final Integer intCode, final Integer jobNumber) {
        return UAERefundTranasactionEntity.builder().lastUpdated(LocalDateTime.now()).updatedUser(user).insCode(insCode).intCode(intCode).txnSerNumber(txnEntity.getSerialNumber()).genStatus(3).jobNumber(jobNumber).pan(txnEntity.getCardNumber()).localDateTime(txnEntity.getLocalDateTime()).txnAmount(txnEntity.getTxnAmount()).txnCurrency("784").acqinstIdCode(txnEntity.getAcqinstIdCode()).rrn(txnEntity.getRrn()).stan(txnEntity.getStan()).issuingNetwork("UAP").mcc(txnEntity.getMcc()).acqCountryCode("784").authCode(txnEntity.getApprovalCode()).terminalId(txnEntity.getTerminalId()).merchantName(txnEntity.getMeName()).merchantId(txnEntity.getMerchantId()).build();
    }
    
    @Transactional
    public PosTransactionEntity mapTxnToPosData(final SwitchExtractVo switchVo, final Integer insCode, final Integer jobNumber, final Integer intCode, final Integer user) {
        try {
            TxnProcessingService.log.info("//------------MapTxnToPosData Start : ");
            int rtsTxnFlag = 0;
            int reversalStagingFlag = 0;
            int rtsFlag = 0;
            final Double exchangeRate = Double.parseDouble(Objects.requireNonNull(this.env.getProperty("exchangeRate"), "ExchangeRate code is Null"));
            if (Objects.nonNull(switchVo.getMti()) && "00".equals(switchVo.getResponseCode()) && switchVo.getMti().matches("0410|0430")) {
                PosTransactionEntity posData = null;
                final String procCode = switchVo.getProcessCode();
                final String rrn = switchVo.getRetRefNumber();
                final String network = switchVo.getNetwork();
                if (procCode.startsWith("00")) {
                    posData = this.posTransactionRepo.findByRrnAndProcCodeAndMsgTypeIdAndGenStatusNot(rrn, procCode, "0110", 6);
                    if (posData == null) {
                        posData = this.posTransactionRepo.findByRrnAndProcCodeAndMsgTypeIdAndGenStatusNot(rrn, procCode, "0210", 6);
                    }
                }
                else if (procCode.startsWith("61") || procCode.startsWith("62")) {
                    posData = this.posTransactionRepo.findByRrnAndProcCodeAndGenStatusNot(rrn, procCode, 6);
                }
                else if (procCode.startsWith("20")) {
                    posData = this.posTransactionRepo.findByRrnAndProcCodeAndMsgTypeIdAndOutStatusNot(rrn, procCode, "0110", "Completed");
                    if (posData == null) {
                        posData = this.posTransactionRepo.findByRrnAndProcCodeAndMsgTypeIdAndOutStatusNot(rrn, procCode, "0210", "Completed");
                    }
                }
                else if (procCode.startsWith("71")) {
                    posData = this.posTransactionRepo.findByRrnAndProcCodeAndMsgTypeIdAndGenStatusNot(rrn, "000000", "0130", 6);
                }
                else if (procCode.startsWith("21")) {
                    posData = this.posTransactionRepo.findByRrnAndProcCodeAndMsgTypeIdAndGenStatusNot(rrn, "200000", "0130", 6);
                }
                if (posData != null) {
                    if (posData.getDmsSmsMode() != 'R') {
                        if ("mastercard".equalsIgnoreCase(network)) {
                            final McAcqTxnWorkEntity txn = this.mcAcqTxnWorkRepo.findByRrn(rrn);
                            if (txn != null) {
                                this.mcAcqTxnWorkRepo.delete((Object)txn);
                                this.mcAcqTxnWorkRepo.flush();
                            }
                        }
                        else if ("visa".equalsIgnoreCase(network)) {
                            final VisaAcqTxnWorkEntity visaTxn = this.visaRepo.findByRetRefNumber(rrn);
                            if (visaTxn != null) {
                                this.visaRepo.delete((Object)visaTxn);
                                this.visaRepo.flush();
                            }
                        }
                        else if ("amex".equalsIgnoreCase(network)) {
                            final AmexAcqTxnWorkEntity amexTxn = this.amexRepo.findByRrn(rrn);
                            if (amexTxn != null) {
                                this.amexRepo.delete((Object)amexTxn);
                                this.amexRepo.flush();
                            }
                        }
                        else if ("jaywan".equalsIgnoreCase(network)) {
                            final JaywanAcqTxnWorkEntity jaywan = this.jaywanRepo.findByRrn(rrn);
                            if (jaywan != null) {
                                this.jaywanRepo.delete((Object)jaywan);
                                this.jaywanRepo.flush();
                            }
                        }
                        posData.setGenStatus(2);
                        posData.setOutStatus("Pending");
                        posData.setRevIndiCator('R');
                        this.posTxnRepo.saveAndFlush((Object)posData);
                        reversalStagingFlag = 1;
                    }
                    else if ("mastercard".equalsIgnoreCase(network)) {
                        McRTSTxnDataEntity txn2 = null;
                        if (procCode.startsWith("00")) {
                            txn2 = this.mcRTSTxnDataRepo.findByRrnAndProcCodeAndMessageTypeId(rrn, posData.getProcCode(), "0110");
                            if (txn2 == null) {
                                txn2 = this.mcRTSTxnDataRepo.findByRrnAndProcCodeAndMessageTypeId(rrn, posData.getProcCode(), "0210");
                            }
                        }
                        else if (procCode.startsWith("20")) {
                            txn2 = this.mcRTSTxnDataRepo.findByRrnAndProcCodeAndMessageTypeId(rrn, posData.getProcCode(), "0110");
                            if (txn2 == null) {
                                txn2 = this.mcRTSTxnDataRepo.findByRrnAndProcCodeAndMessageTypeId(rrn, posData.getProcCode(), "0210");
                            }
                        }
                        else if (procCode.startsWith("71")) {
                            txn2 = this.mcRTSTxnDataRepo.findByRrnAndProcCodeAndMessageTypeId(rrn, "000000", "0130");
                        }
                        else if (procCode.startsWith("21")) {
                            txn2 = this.mcRTSTxnDataRepo.findByRrnAndProcCodeAndMessageTypeId(rrn, "200000", "0130");
                        }
                        else if (procCode.startsWith("61") || procCode.startsWith("62")) {
                            rtsFlag = 1;
                            posData.setGenStatus(2);
                            posData.setOutStatus("NA");
                            posData.setRevIndiCator('R');
                            this.posTxnRepo.saveAndFlush((Object)posData);
                            txn2 = this.mcRTSTxnDataRepo.findByRrnAndProcCodeAndMessageTypeId(rrn, procCode, "0110");
                        }
                        if (txn2 != null) {
                            rtsTxnFlag = ("Success".equalsIgnoreCase(txn2.getApiResponse()) ? 1 : 2);
                        }
                    }
                }
            }
            final PosTransactionEntity.PosTransactionEntityBuilder posBuilder = PosTransactionEntity.builder();
            posBuilder.banKCode(switchVo.getBankCode()).lastUpdated(LocalDateTime.now()).insCode(insCode).updatedUser(user).intCode(intCode).jobNumber(jobNumber).stagingFlag('N').genStatus((reversalStagingFlag == 1 || rtsFlag == 1) ? 2 : 3).paymentFlag('N').bussDate(this.businessDateRepo.findByInsCode(1).getBusinessDate()).txnUniqueId(switchVo.getUniqueId()).msgTypeId(switchVo.getMti()).cardNumber(switchVo.getCardNumber()).incomingStatus("Pending").localDateTime(this.updateLocalDatetime(switchVo.getLocalTxnDate(), switchVo.getLocalTxnTime())).responseCode(switchVo.getResponseCode()).approvalCode(switchVo.getAuthIdResponse()).rrn(switchVo.getRetRefNumber()).terminalId(switchVo.getCardAcceptorTid()).merchantId(switchVo.getCardAcceptorId()).cardAccepStreetAddress(switchVo.getCardAcceptorStreetAddress()).cardAccepStateCode(switchVo.getCardAcceptorStateCode()).mePinCode(switchVo.getCardAcceptorPinCode()).procCode(switchVo.getProcessCode()).stan(switchVo.getStan()).authReason(null).txnType(switchVo.getProcessCode()).txnDateTime(this.updateTxnDatetime(switchVo.getTxnDateTime())).adtlAmounts(Objects.isNull(switchVo.getCashBackAmount()) ? "0.0" : switchVo.getCashBackAmount()).txnCurCode(switchVo.getTxnCurrencyCode()).setlCurCode(switchVo.getSettleCurrencyCode()).terminalType(switchVo.getTxnSource()).posConditionCode(switchVo.getPosCode()).cardSeqNumber(switchVo.getPanSequence()).serviceCode(switchVo.getServiceRestrictionCode()).mcc(switchVo.getMcc()).meName(switchVo.getCardAcceptorName()).meCity(switchVo.getCardAcceptorCity()).network(this.networkMapping(switchVo.getSmsDmsIndicator(), switchVo.getNetwork())).settlementIndicator(switchVo.getSettlementIndicator()).acqinstIdCode(switchVo.getAcqInsIdCode()).acqInstConCode(switchVo.getAcqInsConCode()).chAuthAbility(switchVo.getPosEntryMode().substring(0, 3).charAt(1)).cardInputMode(this.cardInputMode(switchVo.getPosEntryMode())).meCategoryType(switchVo.getMeCategoryType().charAt(0)).expiryDate(switchVo.getExpiryDate()).amexMerchantId(switchVo.getAmexMerchantId()).merchantLocationId(switchVo.getMerchantLocationId()).locationRegionCode(switchVo.getLocationRegionCode()).encCardNumber(switchVo.getTokenIdentifier()).originalRRN(switchVo.getOriginalRRN()).invoiceNumber(switchVo.getInvoiceNumber()).revIndiCator('O').scheme(this.schemeMapping(switchVo.getScheme()));
            final String contactInfo = switchVo.getMerchantContactInfo();
            if (contactInfo != null) {
                final String digitsOnly = contactInfo.replaceAll("\\D", "");
                if (digitsOnly.length() >= 5 && digitsOnly.length() <= 15) {
                    posBuilder.merchantContactInfo(contactInfo);
                }
            }
            final CurrencyUtil currencyUtil = this.currencyUtil;
            final int divisor = CurrencyUtil.getDivisor(switchVo.getTxnCurrencyCode());
            try {
                posBuilder.txnAmount(isNullOrEmpty(switchVo.getAmountTransaction()) ? 0.0 : (Double.valueOf(switchVo.getAmountTransaction()) / divisor)).cashBackAmount(isNullOrEmpty(switchVo.getCashBackAmount()) ? 0.0 : (Double.valueOf(switchVo.getCashBackAmount()) / divisor)).txnFeeAmount(isNullOrEmpty(switchVo.getTxnFeeAmount()) ? 0.0 : (Double.valueOf(switchVo.getTxnFeeAmount()) / divisor)).setlAmount(isNullOrEmpty(switchVo.getAmountTransaction()) ? 0.0 : (Double.valueOf(switchVo.getAmountTransaction()) / divisor * exchangeRate)).netAmount(isNullOrEmpty(switchVo.getAmountTransaction()) ? 0.0 : (Double.valueOf(switchVo.getAmountTransaction()) / divisor)).authAmount(isNullOrEmpty(switchVo.getAuthAmount()) ? 0.0 : (Double.valueOf(switchVo.getAuthAmount()) / divisor)).tipAmount(isNullOrEmpty(switchVo.getTipAmount()) ? 0.0 : (Double.valueOf(switchVo.getTipAmount()) / divisor));
            }
            catch (final Exception e) {
                TxnProcessingService.log.error("Error while amount conversion", (Throwable)e);
            }
            if (StringUtils.isNotBlank((CharSequence)switchVo.getCardAcceptorCountryCode())) {
                final String countryCode = switchVo.getCardAcceptorCountryCode().trim();
                if ("MASTERCARD".equalsIgnoreCase(switchVo.getScheme()) && countryCode.length() == 2) {
                    final CountriesEntity entity = this.countriesRepo.findByCountryAlpha2Code(countryCode);
                    posBuilder.meCountry((entity != null) ? entity.getCountryAlpha3Code() : countryCode);
                }
                else {
                    posBuilder.meCountry(countryCode);
                }
            }
            if (switchVo.getDcc() != null && "Y".equalsIgnoreCase(switchVo.getDcc().getDcc_indicator())) {
                if (Objects.nonNull(switchVo.getDcc().getDcc_amount()) && !switchVo.getDcc().getDcc_amount().isEmpty()) {
                    posBuilder.dccAmount(isNullOrEmpty(switchVo.getDcc().getDcc_amount()) ? 0.0 : (Double.valueOf(switchVo.getDcc().getDcc_amount()) / divisor));
                }
                if (Objects.nonNull(switchVo.getDcc().getDcc_currency()) && !switchVo.getDcc().getDcc_currency().isEmpty()) {
                    posBuilder.dccCurrency(switchVo.getDcc().getDcc_currency());
                }
                if (Objects.nonNull(switchVo.getDcc().getDcc_indicator()) && !switchVo.getDcc().getDcc_indicator().isEmpty()) {
                    posBuilder.dccIndicator(switchVo.getDcc().getDcc_indicator().charAt(0));
                }
                if (Objects.nonNull(switchVo.getDcc().getExchange_rate()) && !switchVo.getDcc().getExchange_rate().isEmpty()) {
                    posBuilder.dccExchangeRate(Double.parseDouble(switchVo.getDcc().getExchange_rate()));
                }
            }
            if (switchVo.getNetwork().equalsIgnoreCase("UAESWITCH")) {
                if ((switchVo.getProcessCode().startsWith("20") || switchVo.getProcessCode().startsWith("06")) && switchVo.getMti().equals("0110")) {
                    posBuilder.outStatus("Pending for Refund file");
                }
                else {
                    posBuilder.outStatus("NA");
                }
            }
            else if ("MASTERCARD".equalsIgnoreCase(switchVo.getScheme()) && "RTS".equalsIgnoreCase(switchVo.getSmsDmsIndicator())) {
                switch (rtsTxnFlag) {
                    case 1: {
                        posBuilder.outStatus("Sale Transaction API Success");
                        break;
                    }
                    case 2: {
                        posBuilder.outStatus("Sale Transaction API Failed");
                        break;
                    }
                    default: {
                        posBuilder.outStatus("Pending");
                        break;
                    }
                }
            }
            else {
                posBuilder.outStatus("Pending");
            }
            if (Objects.nonNull(switchVo.getPosEntryMode())) {
                if (switchVo.getPosEntryMode().length() == 4) {
                    posBuilder.posEntryMode(StringUtils.substring(switchVo.getPosEntryMode(), 0, 3));
                }
                else {
                    posBuilder.posEntryMode(switchVo.getPosEntryMode());
                }
            }
            if (Objects.nonNull(switchVo.getSettlementDate()) && !switchVo.getSettlementDate().isEmpty()) {
                final String setlDate = LocalDate.now().getYear() + switchVo.getSettlementDate();
                posBuilder.setlDate(LocalDate.parse(setlDate, DateTimeFormatter.ofPattern("yyyyMMdd")));
            }
            if (switchVo.getDe48_json() != null) {
                if (switchVo.getDe48_json().getSetIndicator() != null && !switchVo.getDe48_json().getSetIndicator().equals("")) {
                    posBuilder.motoEcomIndicator(switchVo.getDe48_json().getSetIndicator());
                }
                if (Objects.nonNull(switchVo.getDe48_json().getPinServiceCode()) && switchVo.getDe48_json().getPinServiceCode().equalsIgnoreCase("PV")) {
                    posBuilder.chAuthMethod('1');
                }
                else if (Objects.nonNull(switchVo.getDe48_json().getPinServiceCode()) && switchVo.getDe48_json().getPinServiceCode().equalsIgnoreCase("TV")) {
                    posBuilder.chAuthMethod('1');
                }
                else {
                    posBuilder.chAuthMethod('9');
                }
                if (Objects.nonNull(switchVo.getDe48_json().getMaid())) {
                    posBuilder.maid(switchVo.getDe48_json().getMaid());
                }
                if (Objects.nonNull(switchVo.getDe48_json().getMeCountryCode())) {
                    posBuilder.meCountryOfOrigin(switchVo.getDe48_json().getMeCountryCode());
                }
                if (switchVo.getDe48_json().getMposAccDevType() != null && !switchVo.getDe48_json().getMposAccDevType().isBlank()) {
                    posBuilder.mposAccDevType(switchVo.getDe48_json().getMposAccDevType().charAt(0));
                }
            }
            if (Objects.nonNull(switchVo.getServerDateTime())) {
                final LocalDate formttedDate = LocalDate.parse(switchVo.getServerDateTime().substring(0, 8), DateTimeFormatter.ofPattern("yyyyMMdd"));
                posBuilder.centreProcDate(formttedDate);
            }
            if (Objects.nonNull(switchVo.getOnusOffusIndicator())) {
                posBuilder.onusOffusFlag(switchVo.getOnusOffusIndicator().equalsIgnoreCase("ONUS") ? 'O' : 'F');
            }
            if (Objects.nonNull(switchVo.getMti()) && switchVo.getResponseCode().equals("00")) {
                if (reversalStagingFlag == 0) {
                    final PosTransactionEntity.PosTransactionEntityBuilder posTransactionEntityBuilder = posBuilder;
                    final String mti = switchVo.getMti();
                    posTransactionEntityBuilder.revIndiCator(switch (mti) {
                        case "0410",  "0420",  "0430" -> 'F';
                        default -> 'O';
                    });
                }
                else {
                    posBuilder.revIndiCator('R');
                }
            }
            if (Objects.nonNull(switchVo.getNetwork())) {
                final String upperCase = switchVo.getNetwork().toUpperCase();
                switch (upperCase) {
                    case "MDS": {
                        posBuilder.cardCategory("Maestro").cardSubCategory("Premium");
                        break;
                    }
                    case "AMEX": {
                        posBuilder.cardCategory("AMEX").cardSubCategory("");
                        break;
                    }
                    default: {
                        posBuilder.cardCategory(null).cardSubCategory(null);
                        break;
                    }
                }
            }
            if (Objects.nonNull(switchVo.getSmsDmsIndicator())) {
                final String upperCase2 = switchVo.getSmsDmsIndicator().toUpperCase();
                switch (upperCase2) {
                    case "SMS": {
                        posBuilder.dmsSmsMode('S');
                        break;
                    }
                    case "DMS": {
                        posBuilder.dmsSmsMode('D');
                        break;
                    }
                    case "RTS": {
                        posBuilder.dmsSmsMode('R');
                        break;
                    }
                    default: {
                        posBuilder.dmsSmsMode('\0');
                        break;
                    }
                }
            }
            if (Objects.nonNull(switchVo.getScheme())) {
                final String lowerCase = switchVo.getScheme().toLowerCase();
                switch (lowerCase) {
                    case "mastercard": {
                        posBuilder.scheme("MCI");
                        break;
                    }
                    case "visa": {
                        posBuilder.scheme("VISA");
                        break;
                    }
                    case "amex": {
                        posBuilder.scheme("AMEX");
                        break;
                    }
                    case "uaeswitch": {
                        posBuilder.scheme("UAESWITCH");
                        break;
                    }
                    case "jaywan": {
                        posBuilder.scheme("JAYWAN");
                        break;
                    }
                    case "onus": {
                        posBuilder.scheme("ONUS");
                        break;
                    }
                    case "maal": {
                        posBuilder.scheme("MAAL");
                        break;
                    }
                    case "rupay": {
                        posBuilder.scheme("RUPAY");
                        break;
                    }
                    case "discover": {
                        posBuilder.scheme("DISCOVER");
                        break;
                    }
                    case "diners": {
                        posBuilder.scheme("DINERS");
                        break;
                    }
                    case "mercury": {
                        posBuilder.scheme("MERCURY");
                        break;
                    }
                    default: {
                        posBuilder.scheme(null);
                        break;
                    }
                }
            }
            if (Objects.nonNull(switchVo.getSmsDmsIndicator()) && Objects.nonNull(switchVo.getNetwork())) {
                if (switchVo.getSmsDmsIndicator().equalsIgnoreCase("SMS")) {
                    final String lowerCase2 = switchVo.getNetwork().toLowerCase();
                    switch (lowerCase2) {
                        case "mastercard": {
                            posBuilder.network("MDS");
                            break;
                        }
                        case "visa": {
                            posBuilder.network("VSMS");
                            break;
                        }
                        case "rupay": {
                            posBuilder.network("RSMS");
                            break;
                        }
                        case "amex": {
                            posBuilder.network("AMEX");
                            break;
                        }
                        case "uaeswitch": {
                            posBuilder.network("UAESWITCH");
                            break;
                        }
                        case "jaywan": {
                            posBuilder.network("JAYWAN");
                            break;
                        }
                        case "omannet_tps": {
                            posBuilder.network("OMANNET");
                            break;
                        }
                        case "onus": {
                            posBuilder.network("ONUS");
                            break;
                        }
                        case "mercury": {
                            posBuilder.network("MERCURY");
                            break;
                        }
                        default: {
                            posBuilder.network(null);
                            break;
                        }
                    }
                }
                else if (switchVo.getSmsDmsIndicator().equalsIgnoreCase("DMS")) {
                    final String lowerCase3 = switchVo.getNetwork().toLowerCase();
                    switch (lowerCase3) {
                        case "mastercard": {
                            posBuilder.network("MCI");
                            break;
                        }
                        case "visa": {
                            posBuilder.network("VISA");
                            break;
                        }
                        case "amex": {
                            posBuilder.network("AMEX");
                            break;
                        }
                        case "uaeswitch": {
                            posBuilder.network("UAESWITCH");
                            break;
                        }
                        case "jaywan": {
                            posBuilder.network("JAYWAN");
                            break;
                        }
                        case "omannet_tps": {
                            posBuilder.network("OMANNET");
                            break;
                        }
                        case "onus": {
                            posBuilder.network("ONUS");
                            break;
                        }
                        case "mercury": {
                            posBuilder.network("MERCURY");
                            break;
                        }
                        default: {
                            posBuilder.network(null);
                            break;
                        }
                    }
                }
                else if (switchVo.getSmsDmsIndicator().equalsIgnoreCase("RTS")) {
                    final String lowerCase4 = switchVo.getNetwork().toLowerCase();
                    switch (lowerCase4) {
                        case "mastercard": {
                            posBuilder.network("MCI");
                            break;
                        }
                        case "visa": {
                            posBuilder.network("VISA");
                            break;
                        }
                        case "amex": {
                            posBuilder.network("AMEX");
                            break;
                        }
                        case "uaeswitch": {
                            posBuilder.network("UAESWITCH");
                            break;
                        }
                        case "jaywan": {
                            posBuilder.network("JAYWAN");
                            break;
                        }
                        case "omannet_tps": {
                            posBuilder.network("OMANNET");
                            break;
                        }
                        case "onus": {
                            posBuilder.network("ONUS");
                            break;
                        }
                        case "mercury": {
                            posBuilder.network("MERCURY");
                            break;
                        }
                        default: {
                            posBuilder.network(null);
                            break;
                        }
                    }
                }
            }
            if (switchVo.getScheme().equalsIgnoreCase("mastercard")) {
                posBuilder.chAuthEntity('9').cardOutPutAbility('0').trlOutPutAbility('0');
            }
            if (Boolean.TRUE.equals(this.checkForEmv(switchVo.getNetwork(), switchVo.getPosEntryMode()))) {
                if (switchVo.getDe55_json().getEightyTwo() != null) {
                    posBuilder.appICProfile(switchVo.getDe55_json().getEightyTwo());
                }
                if (switchVo.getDe55_json().getEightyFour() != null) {
                    posBuilder.dedicatedFileName(switchVo.getDe55_json().getEightyFour());
                }
                if (switchVo.getDe55_json().getNinetyOne() != null) {
                    posBuilder.issAuthData(switchVo.getDe55_json().getNinetyOne());
                }
                if (switchVo.getDe55_json().getNinetyFive() != null) {
                    posBuilder.trlVerResult(switchVo.getDe55_json().getNinetyFive());
                }
                if (switchVo.getDe55_json().getNineA() != null || switchVo.getDe55_json().getNineA() != "") {
                    posBuilder.chipTxnDate(switchVo.getDe55_json().getNineA());
                }
                else {
                    posBuilder.chipTxnDate(null);
                }
                if (switchVo.getDe55_json().getNineC() != null) {
                    posBuilder.chipTxnType(switchVo.getDe55_json().getNineC());
                }
                if (switchVo.getDe55_json().getFiveF2A() != null) {
                    if (switchVo.getDe55_json().getFiveF2A().length() == 4) {
                        posBuilder.chipCurCode(StringUtils.substring(switchVo.getDe55_json().getFiveF2A(), 1));
                    }
                    else {
                        posBuilder.chipCurCode(switchVo.getDe55_json().getFiveF2A());
                    }
                }
                try {
                    if (switchVo.getDe55_json().getNineF02() != null) {
                        posBuilder.cryptAmount(Double.parseDouble(switchVo.getDe55_json().getNineF02()) / 100.0);
                    }
                }
                catch (final Exception ex) {}
                try {
                    if (switchVo.getDe55_json().getNineF03() != null) {
                        posBuilder.chipCashBack(Double.parseDouble(switchVo.getDe55_json().getNineF03()) / 100.0);
                    }
                }
                catch (final Exception ex2) {}
                if (switchVo.getDe55_json().getNineF09() != null) {
                    posBuilder.trlAppVerNumber(switchVo.getDe55_json().getNineF09());
                }
                if (switchVo.getDe55_json().getNineF10() != null && !switchVo.getDe55_json().getNineF10().trim().isEmpty()) {
                    posBuilder.issAppData(StringUtils.rightPad(switchVo.getDe55_json().getNineF10(), 64, ' '));
                }
                if (switchVo.getDe55_json().getNineF1A() != null) {
                    if (switchVo.getDe55_json().getNineF1A().length() == 4) {
                        posBuilder.trlConCode(StringUtils.substring(switchVo.getDe55_json().getNineF1A(), 1));
                    }
                    else {
                        posBuilder.trlConCode(switchVo.getDe55_json().getNineF1A());
                    }
                }
                if (switchVo.getDe55_json().getNineF1E() != null) {
                    posBuilder.ifdSerNumber(switchVo.getDe55_json().getNineF1E());
                }
                if (switchVo.getDe55_json().getNineF26() != null) {
                    posBuilder.appCryptogram(switchVo.getDe55_json().getNineF26());
                }
                if (switchVo.getDe55_json().getNineF27() != null) {
                    posBuilder.cryptInfoData(switchVo.getDe55_json().getNineF27());
                }
                if (switchVo.getDe55_json().getNineF33() != null) {
                    posBuilder.chipTrlCapabilities(switchVo.getDe55_json().getNineF33());
                }
                if (switchVo.getDe55_json().getNineF34() != null) {
                    posBuilder.cvmResult(switchVo.getDe55_json().getNineF34());
                }
                if (switchVo.getDe55_json().getNineF35() != null) {
                    posBuilder.chipTrlType(switchVo.getDe55_json().getNineF35());
                }
                if (switchVo.getDe55_json().getNineF36() != null) {
                    posBuilder.appTxnCounter(switchVo.getDe55_json().getNineF36());
                }
                if (switchVo.getDe55_json().getNineF37() != null) {
                    posBuilder.upblNumber(switchVo.getDe55_json().getNineF37());
                }
                if (switchVo.getDe55_json().getNineF53() != null) {
                    posBuilder.tcc(switchVo.getDe55_json().getNineF53());
                }
                if (switchVo.getDe55_json().getNineF6E() != null || !switchVo.getDe55_json().getNineF6E().isEmpty()) {
                    posBuilder.formFactorIndicator(switchVo.getDe55_json().getNineF6E());
                }
                else {
                    posBuilder.formFactorIndicator(null);
                }
            }
            if ("mastercard".equalsIgnoreCase(switchVo.getScheme()) && Objects.nonNull(switchVo.getDe61_json())) {
                posBuilder.cardPresent(Objects.nonNull(switchVo.getDe61_json().getCardPresent()) ? switchVo.getDe61_json().getCardPresent() : null).cardInputAbility(StringUtils.isNotBlank((CharSequence)switchVo.getDe61_json().getCardInputAbility()) ? Character.valueOf(switchVo.getDe61_json().getCardInputAbility().charAt(0)) : null).oprtEnvironment((Objects.nonNull(switchVo.getDe61_json().getOperationalEnv1()) || Objects.nonNull(switchVo.getDe61_json().getOperationalEnv3())) ? new StringBuilder().append(Objects.nonNull(switchVo.getDe61_json().getOperationalEnv1()) ? switchVo.getDe61_json().getOperationalEnv1() : "").append(Objects.nonNull(switchVo.getDe61_json().getOperationalEnv3()) ? switchVo.getDe61_json().getOperationalEnv3() : "").toString() : null).cardCaptureAbility(StringUtils.isNotBlank((CharSequence)switchVo.getDe61_json().getCardCaptureCapability()) ? Character.valueOf(switchVo.getDe61_json().getCardCaptureCapability().charAt(0)) : null).chPresent(Objects.nonNull(switchVo.getDe61_json().getChPresent()) ? switchVo.getDe61_json().getChPresent() : null);
            }
            if (switchVo.getDe63_json() != null) {
                try {
                    posBuilder.reImbursementAttribute((switchVo.getDe63_json().getReImbursementAttribute() == null && StringUtils.isBlank((CharSequence)switchVo.getDe61_json().getCardInputAbility())) ? null : Character.valueOf(switchVo.getDe63_json().getReImbursementAttribute().charAt(0)));
                }
                catch (final Exception e) {
                    posBuilder.reImbursementAttribute(null);
                }
                if (switchVo.getDe63_json().getFeePgmIndicator() != null) {
                    posBuilder.feePgmIndicator(switchVo.getDe63_json().getFeePgmIndicator());
                }
                if (switchVo.getScheme().equalsIgnoreCase("mastercard")) {
                    posBuilder.txnId(switchVo.getDe63_json().getNetworkData()).networkData(switchVo.getDe63_json().getNetworkData() + switchVo.getSettlementDate());
                }
                else {
                    posBuilder.networkData(switchVo.getDe63_json().getNetworkData());
                }
            }
            if (switchVo.getScheme().equalsIgnoreCase("visa")) {
                if (Objects.nonNull(switchVo.getDe60_json())) {
                    posBuilder.chIdMethod((switchVo.getDe60_json().getChIdMethod() != null) ? switchVo.getDe60_json().getChIdMethod() : null);
                    final String terminalCapability = switchVo.getDe60_json().getTerminalCapability();
                    posBuilder.trlCapabilities((terminalCapability != null && terminalCapability.matches("[0-5 89]")) ? terminalCapability : null);
                    if (switchVo.getDe60_json().getEcomIndicator() != null && !switchVo.getDe60_json().getEcomIndicator().equals("")) {
                        posBuilder.motoEcomIndicator(switchVo.getDe60_json().getEcomIndicator());
                    }
                    final Character acceptanceTrlIndicator = switchVo.getDe60_json().getAcceptanceTrlIndicator();
                    posBuilder.acceptanceTrlIndicator((acceptanceTrlIndicator != null && ((acceptanceTrlIndicator >= '1' && acceptanceTrlIndicator <= '5') || acceptanceTrlIndicator == '9')) ? acceptanceTrlIndicator : null);
                }
                if (Objects.nonNull(switchVo.getDe62_json())) {
                    posBuilder.txnId(StringUtils.isNotBlank((CharSequence)switchVo.getDe62_json().getTxnId()) ? switchVo.getDe62_json().getTxnId() : null).authCharecteresticId(StringUtils.isNotBlank((CharSequence)switchVo.getDe62_json().getAuthCharecteresticId()) ? Character.valueOf(switchVo.getDe62_json().getAuthCharecteresticId().charAt(0)) : null).validationCode(StringUtils.isNotBlank((CharSequence)switchVo.getDe62_json().getValidationCode()) ? switchVo.getDe62_json().getValidationCode() : null).marketSpecAuthDataInd(StringUtils.isNotBlank((CharSequence)switchVo.getDe62_json().getMarketSpecAuthDataInd()) ? switchVo.getDe62_json().getMarketSpecAuthDataInd() : null).mvv(StringUtils.isNotBlank((CharSequence)switchVo.getDe62_json().getMvv()) ? switchVo.getDe62_json().getMvv() : null).productId(StringUtils.isNotBlank((CharSequence)switchVo.getDe62_json().getProductId()) ? switchVo.getDe62_json().getProductId() : null);
                    try {
                        posBuilder.spendQualificationInd(StringUtils.isNotBlank((CharSequence)switchVo.getDe62_json().getSpendQualificationInd()) ? Character.valueOf(switchVo.getDe62_json().getSpendQualificationInd().charAt(0)) : null);
                    }
                    catch (final Exception e) {
                        posBuilder.spendQualificationInd(null);
                    }
                }
                if (Objects.nonNull(switchVo.getDe111_json())) {
                    try {
                        posBuilder.accountFundingSource(StringUtils.isNotBlank((CharSequence)switchVo.getDe111_json().getAccountFundingSource()) ? Character.valueOf(switchVo.getDe111_json().getAccountFundingSource().charAt(0)) : null);
                    }
                    catch (final Exception ex3) {}
                }
                if (Objects.nonNull(switchVo.getDe126_json())) {
                    posBuilder.oprtEnvironment((switchVo.getDe126_json().getPosEnv() != null) ? switchVo.getDe126_json().getPosEnv() : null);
                }
            }
            TxnProcessingService.log.info("Network :" + switchVo.getNetwork());
            if (switchVo.getNetwork().equalsIgnoreCase("amex") && Objects.nonNull(switchVo.getDe62_json())) {
                TxnProcessingService.log.info("Amex txn ID :" + switchVo.getDe62_json().getTxnId());
                posBuilder.txnId(switchVo.getDe62_json().getTxnId());
            }
            if (Objects.nonNull(switchVo.getMti()) && Objects.nonNull(switchVo.getProcessCode()) && Objects.nonNull(switchVo.getMcc())) {
                posBuilder.txnCode(this.getTxnCode(switchVo.getMti(), switchVo.getProcessCode(), posBuilder.build().getScheme(), switchVo.getMcc()));
            }
            return posBuilder.build();
        }
        catch (final Exception e2) {
            TxnProcessingService.log.error("Error::mapTxnToPosData():", (Throwable)e2);
            return null;
        }
    }
    
    private LocalDateTime updateLocalDatetime(final String date, final String time) {
        Integer year = LocalDateTime.now().getYear();
        final String txnDate = year + date;
        final LocalDate localTxnDate = LocalDate.parse(txnDate, DateTimeFormatter.ofPattern("yyyyMMdd"));
        if (localTxnDate.isAfter(LocalDate.now())) {
            --year;
        }
        final String stringtobepadded = year + date + time;
        final StringBuilder sb = new StringBuilder(stringtobepadded);
        if (stringtobepadded.length() < 14) {
            sb.append("0");
        }
        final String paddedString = sb.toString();
        return LocalDateTime.parse(paddedString.substring(0, 8) + " " + paddedString.substring(8, 10) + ":" + String.valueOf(paddedString.subSequence(10, 12)) + ":" + paddedString.substring(12, 14), this.formatter);
    }
    
    public LocalDateTime updateTxnDatetime(final String dateTime) {
        Integer year = LocalDateTime.now().getYear();
        final String txnDate = year + dateTime.substring(0, 4);
        final LocalDate dateToCheck = LocalDate.parse(txnDate, DateTimeFormatter.ofPattern("yyyyMMdd"));
        if (dateToCheck.isAfter(LocalDate.now())) {
            --year;
        }
        final String stringTobePadded = year + dateTime;
        final StringBuilder sb = new StringBuilder(stringTobePadded);
        if (stringTobePadded.length() < 14) {
            sb.append("0");
        }
        final String paddedString = sb.toString();
        return LocalDateTime.parse(paddedString.substring(0, 8) + " " + paddedString.substring(8, 10) + ":" + String.valueOf(paddedString.subSequence(10, 12)) + ":" + paddedString.substring(12, 14), this.formatter);
    }
    
    public char cardInputMode(final String posDataCode) {
        final String substring = posDataCode.substring(0, 2);
        return switch (substring) {
            case "00" -> '0';
            case "01" -> '1';
            case "02" -> '2';
            case "03" -> '0';
            case "04" -> '0';
            case "05" -> 'C';
            case "07" -> 'M';
            case "08" -> 'N';
            case "09" -> 'R';
            case "79" -> '6';
            case "80" -> 'B';
            case "81" -> 'S';
            case "82" -> 'T';
            case "90" -> 'B';
            case "91" -> 'A';
            case "92" -> 'N';
            case "95" -> 'C';
            case "10" -> '7';
            default -> '0';
        };
    }
    
    private String getTxnCode(final String mti, final String procCode, final String scheme, final String merchantType) {
        String result = StringUtils.substring(procCode, 0, 2);
        if ("JAYWAN|UAESWITCH|ONUS|MAAL|MERCURY|DISCOVER|DINERS".equalsIgnoreCase(scheme)) {
            return result;
        }
        final String visaMatch = "VISA|VSMS";
        final String mcMatch = "MCI|MDS";
        final String rupayMatch = "RUPAY|RSMS";
        final String amexMatch = "AMEX";
        if (mti.matches("0110|0210|0130")) {
            if (procCode.startsWith("00")) {
                if (scheme.toUpperCase().matches(visaMatch)) {
                    result = "05";
                }
                else {
                    result = "00";
                }
            }
            else if (procCode.startsWith("09")) {
                if (scheme.toUpperCase().matches(mcMatch)) {
                    result = "09";
                }
                else {
                    result = "09";
                }
            }
            else if (procCode.startsWith("20")) {
                if (scheme.toUpperCase().matches(mcMatch) || scheme.toUpperCase().matches(amexMatch)) {
                    result = "20";
                }
                else {
                    result = "06";
                }
            }
            else if (procCode.startsWith("01")) {
                if (scheme.toUpperCase().matches(rupayMatch)) {
                    if (merchantType.equals("6010")) {
                        result = "07";
                    }
                    else {
                        result = "01";
                    }
                }
                else if (scheme.toUpperCase().matches(visaMatch)) {
                    result = "07";
                }
            }
            else if (procCode.startsWith("17") && scheme.toUpperCase().matches(mcMatch)) {
                result = "12";
            }
            else if (procCode.startsWith("71")) {
                result = "71";
            }
            else if (procCode.startsWith("61")) {
                result = "61";
            }
            else if (procCode.startsWith("62")) {
                result = "62";
            }
        }
        else if (mti.matches("0410|0420|0430")) {
            if (procCode.startsWith("00")) {
                if (scheme.toUpperCase().matches(visaMatch)) {
                    result = "25";
                }
                else if (scheme.toUpperCase().matches(amexMatch)) {
                    result = "02";
                }
                else {
                    result = "20";
                }
            }
            else if (procCode.startsWith("09")) {
                if (scheme.toUpperCase().matches(mcMatch)) {
                    result = "09";
                }
                else {
                    result = "09";
                }
            }
            else if (procCode.startsWith("20")) {
                if (scheme.toUpperCase().matches(mcMatch)) {
                    result = "20";
                }
                else if (scheme.toUpperCase().matches(amexMatch)) {
                    result = "22";
                }
                else {
                    result = "26";
                }
            }
            else if (procCode.startsWith("01")) {
                if (scheme.toUpperCase().matches(rupayMatch)) {
                    if (merchantType.equals("6010")) {
                        result = "27";
                    }
                    else {
                        result = "21";
                    }
                }
                else if (scheme.toUpperCase().matches(visaMatch)) {
                    result = "27";
                }
            }
            else if (procCode.startsWith("17") && scheme.toUpperCase().matches(mcMatch)) {
                result = "12";
            }
            else if (procCode.startsWith("71")) {
                result = "71";
            }
            else if (procCode.startsWith("61")) {
                result = "61";
            }
            else if (procCode.startsWith("62")) {
                result = "62";
            }
            else if (procCode.startsWith("21")) {
                result = "21";
            }
        }
        return result;
    }
    
    public static boolean isNullOrEmpty(final String str) {
        return str == null || str.isEmpty();
    }
    
    public String interChangeRecalculation(final Integer insCode, final Integer posCode, final String rrn) {
        try {
            final PosTransactionEntity posTxnData = this.posTxnRepo.findBySerialNumberAndRrn(posCode, rrn);
            final String cardNumber = this.getCardNumber(posTxnData.getEncCardNumber());
            if (Objects.isNull(cardNumber)) {
                return "INVALID INPUT ;  Crypt API Failed";
            }
            IRFResultVo irfVo = new IRFResultVo();
            if (posTxnData.getNetwork().toUpperCase().matches("MCI|MDS")) {
                irfVo = this.uaeMcIrf.getMcIrfUAE(insCode, posTxnData, cardNumber);
            }
            else if (posTxnData.getNetwork().equalsIgnoreCase("VISA")) {
                irfVo = this.visaIrf.getVisaIrf(insCode, posTxnData, cardNumber);
            }
            if (Objects.nonNull(irfVo)) {
                posTxnData.setIrdSerNumber(irfVo.getIrdSerNumber());
                posTxnData.setIrd(irfVo.getIrdCode());
                posTxnData.setIrfFixed(irfVo.getIrfFixed());
                posTxnData.setIrfPercent(irfVo.getIrfPercentage());
                posTxnData.setIrfAmount(irfVo.getIrfAmount());
                posTxnData.setIrfAmountUSD(irfVo.getIrfAmountUSD());
                posTxnData.setCardType(irfVo.getCardType());
                posTxnData.setCardDomIntlFlag(irfVo.getDomIntlFlag());
                posTxnData.setCardCategory(irfVo.getGcmsProductID());
                posTxnData.setRemarks(irfVo.getIrfDesc());
                posTxnData.setIrfMinAmount(irfVo.getIrfMinAmount());
                posTxnData.setIrfMaxAmount(irfVo.getIrfMaxAmount());
                this.posTxnRepo.saveAndFlush((Object)posTxnData);
            }
            return "Success";
        }
        catch (final Exception e) {
            TxnProcessingService.log.error("interChangeRecalculation Failed", (Throwable)e);
            return "interChangeRecalculation Failed";
        }
    }
    
    public ResponseEntity<Map<String, String>> processTLFTxn(final String fileName) {
        Map<String, String> responseBody = new HashMap<String, String>();
        try {
            final int userSerialNumber = Integer.parseInt(Objects.requireNonNull(this.env.getProperty("userSerNumber"), "The key userSerNumber not found in the property."));
            final int insCode = Integer.parseInt(Objects.requireNonNull(this.env.getProperty("insCode"), "The key insCode not found in the property."));
            final String insShortName = Objects.requireNonNull(this.env.getProperty("INS_SHORT_NAME"), "The key INS_SHORT_NAME not found in the property.");
            TxnProcessingService.log.info("Checking file existence...");
            responseBody = this.commonService.checkFileExistance(fileName, insShortName);
            if (!responseBody.isEmpty()) {
                return (ResponseEntity<Map<String, String>>)ResponseEntity.ok((Object)responseBody);
            }
            final FileUploadLogEntity file = this.fileUploadLogRepo.findByFileName(fileName);
            if (file != null) {
                responseBody.put("Message", "DUPLICATE_FILENAME; provided filename is already exist.");
                return (ResponseEntity<Map<String, String>>)ResponseEntity.ok((Object)responseBody);
            }
            if (!this.commonService.checkFileUploadLog(insCode)) {
                responseBody.put("Message", "Another process is running. Please wait...");
                return (ResponseEntity<Map<String, String>>)ResponseEntity.ok((Object)responseBody);
            }
            this.commonService.getFileUpload(insCode, userSerialNumber, fileName);
            final FileUploadLogEntity uploadLog = this.fileUploadLogRepo.findByFileName(fileName);
            final int jobnumber = (uploadLog != null) ? uploadLog.getJobNumber() : 0;
            TxnProcessingService.log.info("Job number: {}", (Object)jobnumber);
            if (uploadLog == null) {
                responseBody.put("Message", "Failed to insert upload log or processing job.");
                return (ResponseEntity<Map<String, String>>)ResponseEntity.ok((Object)responseBody);
            }
            final String path = this.env.getProperty("RECON_IN_" + insShortName);
            if (path == null) {
                responseBody.put("Message", "Input path not configured for institution: " + insShortName);
                return (ResponseEntity<Map<String, String>>)ResponseEntity.ok((Object)responseBody);
            }
            final InputStream dataStream = this.resolveInputStream(fileName, insShortName);
            this.startProcessingInThread(dataStream, fileName, jobnumber, uploadLog.getSerialNumber(), insShortName);
            responseBody.put("Message", "TLF processing scheduled successfully.");
        }
        catch (final Exception e) {
            TxnProcessingService.log.error("Error in TLF processing: {}", (Object)e.getMessage(), (Object)e);
            responseBody.put("Message", "Unexpected error occurred: " + e.getMessage());
        }
        return (ResponseEntity<Map<String, String>>)ResponseEntity.ok((Object)responseBody);
    }
    
    private void startProcessingInThread(final InputStream dataStream, final String fileName, final int jobNumber, final int uploadSerialNumber, final String insShortName) {
        final Runnable tlfRead = () -> {
            int processed = 0;
            int rejected = 0;
            ResponseEntity<Map<String, String>> response = null;
            try {
                new BufferedReader(new InputStreamReader(dataStream));
                final BufferedReader bufferedReader;
                try (final BufferedReader reader = bufferedReader) {
                    final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
                    final Validator validator = factory.getValidator();
                    while (true) {
                        final String line = reader.readLine();
                        final Object o;
                        if (o != null) {
                            try {
                                final RequestVo reqVo = (RequestVo)this.objectMapper.readValue(line, (Class)RequestVo.class);
                                final Set violations = validator.validate((Object)reqVo, new Class[0]);
                                if (!violations.isEmpty()) {
                                    throw new ConstraintViolationException(violations);
                                }
                                else {
                                    response = this.processOnlineTxn(reqVo, false, "TLF", jobNumber, fileName);
                                    if (response.getStatusCode() == HttpStatus.OK) {
                                        ++processed;
                                    }
                                    else {
                                        ++rejected;
                                    }
                                }
                            }
                            catch (final Exception ex) {
                                ++rejected;
                                this.handleRejectedRecord(line, ex, jobNumber, fileName);
                            }
                        }
                        else {
                            break;
                        }
                    }
                    reader.close();
                    final String moveDir = (response != null && response.getStatusCodeValue() == 200) ? "RECON_PROCESSED_" : "RECON_REJECTED_";
                    this.commonService.updateFileUploadLog(uploadSerialNumber, 4);
                    this.commonService.moveFile("RECON_IN_", fileName, insShortName, moveDir);
                    this.commonService.updateProcessingJob(jobNumber, 4);
                    TxnProcessingService.log.info("Processed: {}, Rejected: {}", (Object)processed, (Object)rejected);
                }
            }
            catch (final Exception e) {
                TxnProcessingService.log.error("TLF processing error: {}", (Object)e.getMessage(), (Object)e);
                this.commonService.updateFileUploadLog(uploadSerialNumber, 5);
                this.commonService.moveFile("RECON_IN_", fileName, insShortName, "RECON_REJECTED_");
                this.commonService.updateProcessingJob(jobNumber, 5);
            }
            return;
        };
        new Thread(tlfRead).start();
    }
    
    private InputStream resolveInputStream(final String fileName, final String insShortName) throws Exception {
        final String path = this.env.getProperty("RECON_IN_" + insShortName);
        final Path filePath = Paths.get(path, fileName);
        return fileName.toLowerCase().endsWith(".pgp") ? PGPFileDecryptor.decryptPGPFile(filePath.toString(), this.env.getProperty("privateKeyFilePGTLF"), this.env.getProperty("passphrasePGTLF")) : new FileInputStream(filePath.toFile());
    }
    
    private void handleRejectedRecord(final String line, final Exception ex, final Integer jobNumber, final String fileName) {
        final List<String> errors = Collections.singletonList(ex.getMessage());
        final ResponseVo responseVo = new ResponseVo();
        String txnDateTime = null;
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final JsonNode rootNode = mapper.readTree(line);
            JsonNode payloadNode = rootNode.path("payload");
            if (payloadNode.isMissingNode() || payloadNode.isNull()) {
                payloadNode = rootNode.path("");
            }
            responseVo.setRrn(payloadNode.path("rrn").asText());
            responseVo.setMti(payloadNode.path("mti").asText());
            responseVo.setCardAcceptorId(payloadNode.path("merchant_id").asText());
            responseVo.setCardAcceptorTid(payloadNode.path("terminal_id").asText());
            responseVo.setUniqueId(payloadNode.path("ref_id").asText());
            responseVo.setAmountTransaction(payloadNode.path("amount").asText());
            responseVo.setResponseMessage("Transaction Rejected");
            responseVo.setValidationErrors(errors);
            txnDateTime = payloadNode.path("transmission_date").asText();
        }
        catch (final Exception e) {
            TxnProcessingService.log.warn("Could not extract info from rejected record: {}", (Object)e.getMessage());
        }
        this.commonService.insertRejectedTxns(responseVo, errors, txnDateTime, jobNumber, "TLF", fileName);
        TxnProcessingService.log.error("Skipping invalid record: {}", (Object)ex.getMessage());
    }
    
    public String getCardNumber(final String tokenIdentifier) {
        String cardNumber = null;
        try {
            final List<String> posDataCardNumber = Arrays.asList(tokenIdentifier);
            final DecryptResponseVo decryptRespVo = this.cryptApi.getCardNumber(posDataCardNumber);
            if (Objects.nonNull(decryptRespVo) && Objects.nonNull(decryptRespVo.getCardNumbers())) {
                cardNumber = decryptRespVo.getCardNumbers().get(posDataCardNumber.get(0));
            }
        }
        catch (final Exception e) {
            cardNumber = null;
        }
        return cardNumber;
    }
    
    public ResponseEntity<JsonNode> sendApiRequest(final Map<String, Object> requestBody) {
        try {
            final String requestURL = this.env.getRequiredProperty("rtsAPIRequestUrl");
            final String token = this.env.getRequiredProperty("RTS_AuthKey");
            final HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + token);
            final HttpEntity<Map<String, Object>> requestEntity = (HttpEntity<Map<String, Object>>)new HttpEntity((Object)requestBody, (MultiValueMap)headers);
            TxnProcessingService.log.info("Calling RTS API: {}", (Object)requestURL);
            TxnProcessingService.log.debug("Request Body: {}", (Object)this.objectMapper.writeValueAsString((Object)requestBody));
            return (ResponseEntity<JsonNode>)this.restTemplate.exchange(requestURL, HttpMethod.POST, (HttpEntity)requestEntity, (Class)JsonNode.class, new Object[0]);
        }
        catch (final Exception e) {
            TxnProcessingService.log.error("RTS API call failed: {}", (Object)e.getMessage(), (Object)e);
            final ObjectNode errorResponse = this.objectMapper.createObjectNode();
            errorResponse.put("status", "FAILED");
            errorResponse.put("responseCode", "500");
            errorResponse.put("message", "RTS API call failed: " + e.getMessage());
            return (ResponseEntity<JsonNode>)ResponseEntity.status((HttpStatusCode)HttpStatus.INTERNAL_SERVER_ERROR).body((Object)errorResponse);
        }
    }
    
    private boolean checkForEmv(final String network, final String posEntryMode) {
        return ("AMEX".equalsIgnoreCase(network) && posEntryMode.length() >= 7 && posEntryMode.charAt(6) == '5') || posEntryMode.startsWith("05") || posEntryMode.startsWith("07") || posEntryMode.startsWith("95");
    }
    
    public Map<String, Object> buildRtsRequest(final McRTSTxnDataEntity entity) {
        final Map<String, String> de48Map = new HashMap<String, String>();
        final CurrencyUtil currencyUtil = this.currencyUtil;
        final int exponent = CurrencyUtil.getExponent(entity.getTxnCurCode());
        de48Map.put("p0023", Optional.ofNullable(entity.getTrlType()).orElse(""));
        de48Map.put("p0025", (entity.getRevIndiCator() != null && entity.getRevIndiCator() == 'R') ? "R" : "");
        de48Map.put("p0052", Optional.ofNullable(entity.getMotoEcomIndicator()).orElse(""));
        de48Map.put("p0148", Optional.ofNullable(entity.getTxnCurCode()).map(code -> code + exponent).orElse(""));
        de48Map.put("p0149", Optional.ofNullable(entity.getTxnCurCode()).orElse(""));
        de48Map.put("SF4", Optional.ofNullable(entity.getIrd()).orElse(""));
        de48Map.put("p0165", Optional.ofNullable(entity.getSettlementIndicator()).map((Function<? super Character, ? extends String>)String::valueOf).orElse(""));
        de48Map.put("p0176", Optional.ofNullable(entity.getMaid()).orElse(""));
        de48Map.put("p0211", Optional.ofNullable(entity.getMotoEcomIndicator()).orElse(""));
        de48Map.put("p0213", Optional.ofNullable(entity.getMeCountryOfOrigin()).orElse(""));
        final Map<String, Object> requestMap = new HashMap<String, Object>();
        requestMap.put("rrn", Optional.ofNullable(entity.getRrn()).orElse(""));
        requestMap.put("mti", Optional.ofNullable(entity.getMessageTypeId()).orElse(""));
        requestMap.put("processing_code", Optional.ofNullable(entity.getProcCode()).orElse(""));
        requestMap.put("DE48", de48Map);
        requestMap.put("DE22", Optional.ofNullable(entity.getPosDataCode()).orElse(""));
        requestMap.put("DE24", Optional.ofNullable(entity.getFunctionCode()).orElse(""));
        requestMap.put("DE25", Optional.ofNullable(entity.getMsgReasonCode()).orElse(""));
        requestMap.put("DE31", Optional.ofNullable(entity.getAcqRefData()).orElse(""));
        requestMap.put("DE95", "");
        TxnProcessingService.log.info("RTS API Request:" + String.valueOf(requestMap));
        return requestMap;
    }
    
    private void updateDefaultIrf(final PosTransactionEntity txnEntity) {
        txnEntity.setIrdSerNumber(null);
        txnEntity.setIrd(null);
        txnEntity.setIrfFixed(0.0);
        txnEntity.setIrfPercent(0.0);
        txnEntity.setIrfAmount(0.0);
        txnEntity.setIrfAmountUSD(0.0);
        txnEntity.setIrfMinAmount(0.0);
        txnEntity.setIrfMaxAmount(0.0);
        txnEntity.setCardDomIntlFlag(' ');
    }
    
    private IRFResultVo fetchIrf(final Integer insCode, final PosTransactionEntity txnEntity, final String cardNumber) {
        final String upperCase;
        final String network = upperCase = txnEntity.getNetwork().toUpperCase();
        return switch (upperCase) {
            case "MCI",  "MDS" -> this.uaeMcIrf.getMcIrfUAE(insCode, txnEntity, cardNumber);
            case "VISA",  "VSMS" -> this.visaIrf.getVisaIrf(insCode, txnEntity, cardNumber);
            case "UAESWITCH" -> this.uaeSwitchIrf.getUAESwitchIrf(insCode, txnEntity);
            case "OMANNET" -> this.omanNetIrf.getOmanNetIRFValue(insCode, txnEntity, cardNumber);
            case "JAYWAN" -> this.jaywanIrf.getJaywanIrf(insCode, txnEntity);
            case "ONUS" -> this.onusIrf.getOnusIRFValue(insCode, txnEntity, cardNumber);
            default -> null;
        };
    }
    
    private void updateIrfTxn(final PosTransactionEntity txn, final IRFResultVo irf) {
        txn.setIrdSerNumber(irf.getIrdSerNumber());
        txn.setIrd(irf.getIrdCode());
        txn.setIrfFixed(irf.getIrfFixed());
        txn.setIrfPercent(irf.getIrfPercentage());
        txn.setIrfAmount(irf.getIrfAmount());
        txn.setIrfAmountUSD(irf.getIrfAmountUSD());
        txn.setCardType(irf.getCardType());
        txn.setCardDomIntlFlag(irf.getDomIntlFlag());
        txn.setCardCategory(irf.getGcmsProductID());
        txn.setRemarks(irf.getIrfDesc());
        txn.setIrfMinAmount(irf.getIrfMinAmount());
        txn.setIrfMaxAmount(irf.getIrfMaxAmount());
    }
    
    private void irfCallback(final PosTransactionEntity txnEntity) {
        try {
            final IRFCallbackEntity callback = this.irfCallbackService.insertIntoIRFCallback(txnEntity);
            this.irfCallbackService.updateApiResponse(callback);
        }
        catch (final Exception e) {
            TxnProcessingService.log.error("IRF Callback error :", (Throwable)e);
        }
    }
    
    private String networkMapping(final String smsDmsValue, final String networkValue) {
        if (Objects.isNull(smsDmsValue) || Objects.isNull(networkValue)) {
            return null;
        }
        final String smsDmsIndicator = smsDmsValue.toUpperCase();
        final String network = networkValue.toLowerCase();
        final String s = smsDmsIndicator;
        switch (s) {
            case "SMS": {
                final String s2 = network;
                return switch (s2) {
                    case "mastercard" -> "MDS";
                    case "visa" -> "VSMS";
                    case "rupay" -> "RSMS";
                    case "amex" -> "AMEX";
                    case "uaeswitch" -> "UAESWITCH";
                    case "jaywan" -> "JAYWAN";
                    case "omannet_tps" -> "OMANNET";
                    case "onus" -> "ONUS";
                    case "mercury" -> "MERCURY";
                    default -> null;
                };
            }
            case "DMS":
            case "RTS": {
                final String s4 = network;
                return switch (s4) {
                    case "mastercard" -> "MCI";
                    case "visa" -> "VISA";
                    case "amex" -> "AMEX";
                    case "uaeswitch" -> "UAESWITCH";
                    case "jaywan" -> "JAYWAN";
                    case "omannet_tps" -> "OMANNET";
                    case "onus" -> "ONUS";
                    case "mercury" -> "MERCURY";
                    default -> null;
                };
            }
            default: {
                return null;
            }
        }
    }
    
    private String schemeMapping(final String scheme) {
        if (Objects.isNull(scheme)) {
            return null;
        }
        final String lowerCase = scheme.toLowerCase();
        return switch (lowerCase) {
            case "mastercard" -> "MCI";
            default -> scheme.toUpperCase();
        };
    }
    
    public TxnProcessingService(final PosTransactionRepository posTxnRepo, final BusinessDateRepo businessDateRepo, final PosTransactionRepository posTransactionRepo, final ValidationService validationService, final McAcqTxnWorkRepo mcAcqTxnWorkRepo, final McSplitTransactionService mcSplitTxnService, final UAEMcIRFCalculation uaeMcIrf, final VisaIrfCalculation visaIrf, final IRFCallbackService irfCallbackService, final Environment env, final VisaSplitTxnService visaSplitService, final AmexSplitTxnService amexSplitService, final VisaAcqTxnRepo visaRepo, final AmexAcqTxnRepo amexRepo, final UAERefundTransactionRepo UaeRefundTxnRepo, final JaywanAcqTxnWorkRepo jaywanWorkRepo, final JaywanSplitTxnService jaywanSplitService, final CryptAPI cryptApi, final UAESwitchIRFCalculation uaeSwitchIrf, final CommonManagementsService commonService, final FileUploadRepo fileUploadLogRepo, final CurrencyUtil currencyUtil, final McRTSTxnDataRepo mcRTSTxnDataRepo, final OmanNetIRFCalculation omanNetIrf, final OnusIRFCalculation onusIrf, final JaywanAcqTxnWorkRepo jaywanRepo, final JaywanIRFCalculation jaywanIrf, final McGCOTxnRepo mcGCOTxnRepo, final McGCOSplitTxnService mcGCOSplitService, final VisaGOCTxnRepo visaGOCTxnRepo, final CountriesRepository countriesRepo) {
        this.formatter = DateTimeFormatter.ofPattern("yyyyMMdd HH:mm:ss");
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
        this.posTxnRepo = posTxnRepo;
        this.businessDateRepo = businessDateRepo;
        this.posTransactionRepo = posTransactionRepo;
        this.validationService = validationService;
        this.mcAcqTxnWorkRepo = mcAcqTxnWorkRepo;
        this.mcSplitTxnService = mcSplitTxnService;
        this.uaeMcIrf = uaeMcIrf;
        this.visaIrf = visaIrf;
        this.irfCallbackService = irfCallbackService;
        this.env = env;
        this.visaSplitService = visaSplitService;
        this.amexSplitService = amexSplitService;
        this.visaRepo = visaRepo;
        this.amexRepo = amexRepo;
        this.UaeRefundTxnRepo = UaeRefundTxnRepo;
        this.jaywanWorkRepo = jaywanWorkRepo;
        this.jaywanSplitService = jaywanSplitService;
        this.cryptApi = cryptApi;
        this.uaeSwitchIrf = uaeSwitchIrf;
        this.commonService = commonService;
        this.fileUploadLogRepo = fileUploadLogRepo;
        this.currencyUtil = currencyUtil;
        this.mcRTSTxnDataRepo = mcRTSTxnDataRepo;
        this.omanNetIrf = omanNetIrf;
        this.onusIrf = onusIrf;
        this.jaywanRepo = jaywanRepo;
        this.jaywanIrf = jaywanIrf;
        this.mcGCOTxnRepo = mcGCOTxnRepo;
        this.mcGCOSplitService = mcGCOSplitService;
        this.visaGOCTxnRepo = visaGOCTxnRepo;
        this.countriesRepo = countriesRepo;
    }
    
    static {
        log = LogManager.getLogger((Class)TxnProcessingService.class);
    }
}
