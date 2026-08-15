// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.services;

import org.apache.logging.log4j.LogManager;
import com.empay.entities.VisaAcqTxnWorkEntity;
import com.empay.entities.McAcqTxnWorkEntity;
import com.empay.cryptapi.DecryptResponseVo;
import com.empay.tlfprocessing.vo.IRFResultVo;
import java.util.Arrays;
import java.time.LocalDateTime;
import org.springframework.beans.BeanUtils;
import com.empay.entities.PosTransactionEntity;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.HttpStatus;
import java.util.Objects;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import com.empay.tlfprocessing.vo.RefundVo;
import com.empay.util.CurrencyUtil;
import com.empay.cryptapi.CryptAPI;
import com.empay.repositories.VisaAcqTxnRepo;
import com.empay.repositories.McAcqTxnWorkRepo;
import org.springframework.core.env.Environment;
import com.empay.visa.VisaIrfCalculation;
import com.empay.common.functions.UAEMcIRFCalculation;
import com.empay.repositories.BusinessDateRepo;
import com.empay.repositories.PosTransactionRepository;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class RefundProcessingService
{
    private static final Logger log;
    private final PosTransactionRepository posTxnRepo;
    private final BusinessDateRepo businessDateRepo;
    private final UAEMcIRFCalculation uaeMcIrf;
    private final VisaIrfCalculation visaIrf;
    private final Environment env;
    private final VisaSplitTxnService visaSplitService;
    private final McSplitTransactionService mcSplitTxnService;
    private final McAcqTxnWorkRepo mcAcqTxnWorkRepo;
    private final VisaAcqTxnRepo visaAcqTxnRepo;
    private final CryptAPI cryptApi;
    private final CurrencyUtil currencyUtil;
    
    public ResponseEntity<Map<String, String>> refundMapping(final RefundVo refundVo) {
        final Map<String, String> response = new HashMap<String, String>();
        try {
            final List<PosTransactionEntity> posData = this.posTxnRepo.findByRrnAndTerminalIdAndMerchantIdAndApprovalCodeAndTxnCodeInAndMsgTypeIdIn(refundVo.getRrn(), refundVo.getTerminalId(), refundVo.getMerchantId(), refundVo.getAuthCode(), List.of("00", "05"), List.of("0110", "0210"));
            final String refundMapping = this.processRefundTxn(refundVo, posData);
            if (Objects.isNull(refundMapping)) {
                response.put("Refund Data Mapping Failed ", refundMapping);
            }
            else {
                response.put("Refund process Success ", refundMapping);
            }
            return (ResponseEntity<Map<String, String>>)new ResponseEntity((Object)response, (HttpStatusCode)HttpStatus.OK);
        }
        catch (final Exception e) {
            RefundProcessingService.log.error("RefundMapping failed :", (Throwable)e);
            response.put("RefundMapping Error: ", e.getMessage());
            return (ResponseEntity<Map<String, String>>)new ResponseEntity((Object)response, (HttpStatusCode)HttpStatus.NOT_FOUND);
        }
    }
    
    public String inputDatavalidation(final RefundVo refundVo, final List<PosTransactionEntity> posData) {
        try {
            if (Objects.isNull(posData) || posData.isEmpty()) {
                return "INVALID INPUT ; No Sale transaction found.";
            }
            if (posData.size() > 1) {
                return "INVALID INPUT ; Duplicate Sale Details found";
            }
            final List<PosTransactionEntity> refundData = this.posTxnRepo.findByRrnAndTerminalIdAndMerchantIdAndApprovalCodeAndTxnCode(refundVo.getRrn(), refundVo.getTerminalId(), refundVo.getMerchantId(), refundVo.getAuthCode(), "06");
            if (!refundData.isEmpty()) {
                final Double totalRefund = refundData.stream().mapToDouble(PosTransactionEntity::getTxnAmount).sum();
                if (totalRefund + Double.parseDouble(refundVo.getRefundAmount()) > posData.get(0).getTxnAmount()) {
                    return "INVALID INPUT ; Refund amount greater than sale amount.";
                }
            }
            return null;
        }
        catch (final Exception e) {
            RefundProcessingService.log.error("Refund Input Data validation failed :", (Throwable)e);
            return "Validation Failed";
        }
    }
    
    public String processRefundTxn(final RefundVo refundVo, final List<PosTransactionEntity> posData) {
        try {
            final String responseMsg = this.inputDatavalidation(refundVo, posData);
            if (!Objects.isNull(responseMsg)) {
                RefundProcessingService.log.info(" --- Refund Failed Validation excep :", (Object)responseMsg);
                return responseMsg;
            }
            final Integer insCode = Integer.parseInt(Objects.requireNonNull(this.env.getProperty("insCode"), "Institution code is Null"));
            final Integer user = Integer.parseInt(Objects.requireNonNull(this.env.getProperty("userSerNumber"), "User serial Number is Null"));
            final Integer jobNumber = Integer.parseInt(Objects.requireNonNull(this.env.getProperty("jobNumber"), "Job Number is Null"));
            PosTransactionEntity refundEntity = new PosTransactionEntity();
            BeanUtils.copyProperties((Object)posData.get(0), (Object)refundEntity);
            final CurrencyUtil currencyUtil = this.currencyUtil;
            final int divisor = CurrencyUtil.getDivisor(refundEntity.getTxnCurCode());
            refundEntity.setSerialNumber(null);
            refundEntity.setLastUpdated(LocalDateTime.now());
            refundEntity.setInsCode(insCode);
            refundEntity.setJobNumber(jobNumber);
            refundEntity.setUpdatedUser(user);
            refundEntity.setGenStatus(3);
            refundEntity.setBussDate(this.businessDateRepo.findByInsCode(1).getBusinessDate());
            refundEntity.setStagingFlag('N');
            refundEntity.setPaymentFlag('N');
            refundEntity.setIncomingStatus("Pending");
            refundEntity.setOutStatus("Pending");
            refundEntity.setTxnAmount(Double.valueOf(refundVo.getRefundAmount()) / divisor);
            refundEntity.setTxnType("200000");
            refundEntity.setTxnCode("06");
            refundEntity.setProcCode("200000");
            refundEntity.setRefundIndicator('R');
            refundEntity.setReImbursementAttribute('B');
            refundEntity.setAuthAmount(0.0);
            refundEntity = (PosTransactionEntity)this.posTxnRepo.saveAndFlush((Object)refundEntity);
            final List<String> posDataCardNumber = Arrays.asList(refundEntity.getEncCardNumber());
            final DecryptResponseVo decryptRespVo = this.cryptApi.getCardNumber(posDataCardNumber);
            if (Objects.isNull(decryptRespVo) || Objects.isNull(decryptRespVo.getCardNumbers())) {
                return "INVALID INPUT ; Crypt API Failed";
            }
            final String cardNumber = decryptRespVo.getCardNumbers().get(posDataCardNumber);
            IRFResultVo irfVo = new IRFResultVo();
            if (refundEntity.getNetwork().toUpperCase().matches("MCI|MDS")) {
                irfVo = this.uaeMcIrf.getMcIrfUAE(insCode, refundEntity, cardNumber);
            }
            else if (refundEntity.getNetwork().equalsIgnoreCase("VISA")) {
                irfVo = this.visaIrf.getVisaIrf(insCode, refundEntity, cardNumber);
            }
            if (Objects.nonNull(irfVo)) {
                refundEntity.setIrdSerNumber(irfVo.getIrdSerNumber());
                refundEntity.setIrd(irfVo.getIrdCode());
                refundEntity.setIrfFixed(irfVo.getIrfFixed());
                refundEntity.setIrfPercent(irfVo.getIrfPercentage());
                refundEntity.setIrfAmount(irfVo.getIrfAmount());
                refundEntity.setIrfAmountUSD(irfVo.getIrfAmountUSD());
                refundEntity.setCardType(irfVo.getCardType());
                refundEntity.setCardDomIntlFlag(irfVo.getDomIntlFlag());
                refundEntity.setCardCategory(irfVo.getGcmsProductID());
                refundEntity.setRemarks(irfVo.getIrfDesc());
                refundEntity.setIrfMinAmount(irfVo.getIrfMinAmount());
                refundEntity.setIrfMaxAmount(irfVo.getIrfMaxAmount());
            }
            else {
                RefundProcessingService.log.info("---- No IRF Data Found for the refund details ----");
                refundEntity.setIrdSerNumber(null);
                refundEntity.setIrd(null);
                refundEntity.setIrfFixed(0.0);
                refundEntity.setIrfPercent(0.0);
                refundEntity.setIrfAmount(0.0);
                refundEntity.setIrfAmountUSD(0.0);
                refundEntity.setRemarks(null);
                refundEntity.setIrfMinAmount(0.0);
                refundEntity.setIrfMaxAmount(0.0);
            }
            refundEntity = (PosTransactionEntity)this.posTxnRepo.saveAndFlush((Object)refundEntity);
            if (refundEntity.getNetwork().toUpperCase().matches("MCI|MDS") && refundEntity.getResponseCode().equals("00")) {
                final McAcqTxnWorkEntity splitTxn = this.mcSplitTxnService.mapToMcAcqTxnWorkEntity(refundEntity, user, insCode, jobNumber);
                if (Objects.nonNull(splitTxn)) {
                    this.mcAcqTxnWorkRepo.saveAndFlush((Object)splitTxn);
                    refundEntity.setGenStatus(4);
                    refundEntity.setOutStatus("Marked for Outgoing");
                    this.posTxnRepo.saveAndFlush((Object)refundEntity);
                }
            }
            if (refundEntity.getNetwork().toUpperCase().matches("VISA") && refundEntity.getResponseCode().equals("00")) {
                final VisaAcqTxnWorkEntity splitTxn2 = this.visaSplitService.mapToVisaAcqTxnEntity(refundEntity, user, insCode, jobNumber);
                if (Objects.nonNull(splitTxn2)) {
                    this.visaAcqTxnRepo.saveAndFlush((Object)splitTxn2);
                    refundEntity.setGenStatus(4);
                    refundEntity.setOutStatus("Marked for Outgoing");
                    this.posTxnRepo.saveAndFlush((Object)refundEntity);
                }
            }
            return "Processing Completed Successfully";
        }
        catch (final Exception e) {
            RefundProcessingService.log.error("ProcessRefund failed :", (Throwable)e);
            return null;
        }
    }
    
    public RefundProcessingService(final PosTransactionRepository posTxnRepo, final BusinessDateRepo businessDateRepo, final UAEMcIRFCalculation uaeMcIrf, final VisaIrfCalculation visaIrf, final Environment env, final VisaSplitTxnService visaSplitService, final McSplitTransactionService mcSplitTxnService, final McAcqTxnWorkRepo mcAcqTxnWorkRepo, final VisaAcqTxnRepo visaAcqTxnRepo, final CryptAPI cryptApi, final CurrencyUtil currencyUtil) {
        this.posTxnRepo = posTxnRepo;
        this.businessDateRepo = businessDateRepo;
        this.uaeMcIrf = uaeMcIrf;
        this.visaIrf = visaIrf;
        this.env = env;
        this.visaSplitService = visaSplitService;
        this.mcSplitTxnService = mcSplitTxnService;
        this.mcAcqTxnWorkRepo = mcAcqTxnWorkRepo;
        this.visaAcqTxnRepo = visaAcqTxnRepo;
        this.cryptApi = cryptApi;
        this.currencyUtil = currencyUtil;
    }
    
    static {
        log = LogManager.getLogger((Class)RefundProcessingService.class);
    }
}
