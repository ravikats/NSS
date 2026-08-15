// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.services;

import jakarta.validation.Valid;
import com.empay.tlfprocessing.vo.MercuryIRFVo;
import java.util.StringJoiner;
import com.empay.tlfprocessing.vo.JaywanIRFVo;
import com.empay.tlfprocessing.vo.OmanNetIrfVo;
import java.util.Iterator;
import java.util.HashMap;
import com.empay.tlfprocessing.vo.UAESwitchIRFVo;
import java.util.Map;
import com.empay.entities.PosTransactionEntity;
import com.empay.common.entities.CountriesEntity;
import com.empay.common.entities.CurrencyEntity;
import com.empay.tlfprocessing.vo.ResponseVo;
import org.apache.commons.lang3.StringUtils;
import com.empay.exceptions.ValidationException;
import java.util.Objects;
import java.util.ArrayList;
import java.util.List;
import com.empay.tlfprocessing.vo.SwitchExtractVo;
import com.empay.common.repo.CurrencyRepo;
import com.empay.common.repo.CountriesRepository;
import com.empay.util.CurrencyUtil;
import org.springframework.core.env.Environment;
import com.empay.cryptapi.CryptAPI;
import com.empay.commonservice.CommonManagementsService;
import com.empay.repositories.PosTransactionRepository;
import org.springframework.stereotype.Service;

@Service
public class ValidationService
{
    private final PosTransactionRepository posRepo;
    private final CommonManagementsService CommonService;
    private final CryptAPI cryptApi;
    private final Environment env;
    private final CurrencyUtil currencyUtil;
    private final CountriesRepository countriesRepo;
    private final CurrencyRepo currencyRepo;
    
    public List<String> validateTxnRequest(final SwitchExtractVo transactionData, final boolean kafkaFlag, final String fileName) {
        final List<String> validationErrors = new ArrayList<String>();
        if (Objects.isNull(transactionData.getRetRefNumber()) || transactionData.getRetRefNumber().isEmpty()) {
            throw new ValidationException("INVALID RRN ; RRN is missing");
        }
        if (Objects.nonNull(this.posRepo.findByMsgTypeIdAndRrnAndProcCode(transactionData.getMti(), transactionData.getRetRefNumber(), transactionData.getProcessCode()))) {
            validationErrors.add("DUPLICATE RRN ; RRN already Exist");
        }
        if ("0130".equals(transactionData.getMti()) && (Objects.isNull(transactionData.getAuthAmount()) || transactionData.getAuthAmount().isEmpty() || transactionData.getAuthAmount().isBlank())) {
            validationErrors.add("INVALID AUTH AMOUNT; AUTH AMOUNT is mandatory for MTI 0130");
        }
        final String authAmount = transactionData.getAuthAmount();
        if (authAmount != null && !authAmount.isEmpty()) {
            final boolean isDigitsOnly = authAmount.matches("\\d+");
            final boolean isLiteralNull = authAmount.equalsIgnoreCase("null");
            if (!isDigitsOnly || isLiteralNull) {
                validationErrors.add("INVALID AUTH AMOUNT; AUTH AMOUNT must be numeric value");
            }
        }
        if (transactionData.getDcc() != null && "Y".equalsIgnoreCase(transactionData.getDcc().getDcc_indicator())) {
            final boolean isInvalid = StringUtils.isBlank((CharSequence)transactionData.getDcc().getDcc_amount()) || StringUtils.isBlank((CharSequence)transactionData.getDcc().getDcc_currency()) || StringUtils.isBlank((CharSequence)transactionData.getDcc().getExchange_rate());
            if (isInvalid) {
                throw new IllegalArgumentException("DCC Amount, DCC Currency, and Exchange Rate are mandatory when DCC Indicator is 'Y'.");
            }
        }
        if (transactionData.getTxnCurrencyCode() != null && !transactionData.getTxnCurrencyCode().isBlank()) {
            final String txnCurrency = transactionData.getTxnCurrencyCode();
            final CurrencyEntity entity = this.currencyRepo.findBycurrencyCodeOrAlphaCode(txnCurrency, txnCurrency);
            if (entity == null) {
                throw new IllegalArgumentException("Invalid Transaction Currency .");
            }
        }
        if (transactionData.getDcc() != null && "Y".equalsIgnoreCase(transactionData.getDcc().getDcc_indicator())) {
            final String dccCurrency = transactionData.getDcc().getDcc_currency();
            if (Objects.nonNull(dccCurrency) && !dccCurrency.isBlank()) {
                final CurrencyEntity entity = this.currencyRepo.findBycurrencyCodeOrAlphaCode(dccCurrency, dccCurrency);
                if (entity == null) {
                    throw new IllegalArgumentException("Invalid DCC Currency .");
                }
            }
        }
        if (StringUtils.isNotBlank((CharSequence)transactionData.getCardAcceptorCountryCode())) {
            final String countryCode = transactionData.getCardAcceptorCountryCode();
            if ("VISA".equalsIgnoreCase(transactionData.getScheme()) && countryCode.length() != 2) {
                throw new IllegalArgumentException("Invalid card acceptor country code. It must be 2 digits for VISA .");
            }
            CountriesEntity entity2;
            if (countryCode.length() == 2) {
                entity2 = this.countriesRepo.findByCountryAlpha2Code(countryCode);
            }
            else {
                entity2 = this.countriesRepo.findByCountryAlpha3CodeOrCountryCode(countryCode, countryCode);
            }
            if (entity2 == null) {
                throw new IllegalArgumentException("Invalid card acceptor country code .");
            }
        }
        if (Objects.nonNull(transactionData.getAmountTransaction()) && Double.parseDouble(transactionData.getAmountTransaction()) == 0.0) {
            validationErrors.add("INVALID AMOUNT ; AMOUNT Should be Greater than Zero");
        }
        if ("MASTERCARD".equalsIgnoreCase(transactionData.getScheme())) {
            if (Objects.isNull(transactionData.getDe48_json())) {
                validationErrors.add("INVALID INPUT ;  Missing Additional Private data");
            }
            else if (transactionData.getDe48_json().getMposAccDevType() != null && !transactionData.getDe48_json().getMposAccDevType().isBlank() && (transactionData.getDe48_json().getMposAccDevType().length() > 1 || (transactionData.getDe48_json().getMposAccDevType().charAt(0) != '0' && transactionData.getDe48_json().getMposAccDevType().charAt(0) != '1'))) {
                validationErrors.add("INVALID INPUT ;  Invalid mposAccDevType . Allowed values are '0' and '1'.");
            }
        }
        if (Objects.isNull(transactionData.getServiceRestrictionCode()) && !transactionData.getTxnSource().equalsIgnoreCase("PG")) {
            validationErrors.add("INVALID INPUT ;  The Service Restriction Code cannot be null");
        }
        if (Objects.isNull(transactionData.getAuthIdResponse()) && Objects.nonNull(transactionData.getResponseCode()) && transactionData.getResponseCode().equals("00")) {
            validationErrors.add("INVALID INPUT ;  The Auth_code Code cannot be null");
        }
        if (!transactionData.getNetwork().equalsIgnoreCase("AMEX") && (Objects.isNull(transactionData.getSettlementDate()) || transactionData.getSettlementDate().isEmpty() || transactionData.getSettlementDate().isBlank())) {
            validationErrors.add("INVALID INPUT ;  The settlement_date cannot be blank /empty");
        }
        PosTransactionEntity posData = null;
        final String procCode = transactionData.getProcessCode();
        final String rrn = transactionData.getRetRefNumber();
        final String mti = transactionData.getMti();
        final String orgRrn = transactionData.getOriginalRRN();
        Double txnAmount = null;
        final String amountStr = transactionData.getAmountTransaction();
        boolean isValid = false;
        if (mti.matches("0410|0430")) {
            if (procCode.startsWith("00")) {
                posData = this.posRepo.findByRrnAndProcCodeAndMsgTypeIdAndGenStatusNot(rrn, procCode, "0110", 6);
                if (posData == null) {
                    posData = this.posRepo.findByRrnAndProcCodeAndMsgTypeIdAndGenStatusNot(rrn, procCode, "0210", 6);
                }
            }
            else if (procCode.startsWith("61") || procCode.startsWith("62")) {
                posData = this.posRepo.findByRrnAndProcCodeAndGenStatusNot(rrn, procCode, 6);
            }
            else if (procCode.startsWith("20")) {
                PosTransactionEntity refundTxn = this.posRepo.findByRrnAndProcCodeAndMsgTypeIdAndGenStatusNot(rrn, procCode, "0110", 7);
                if (refundTxn == null) {
                    refundTxn = this.posRepo.findByRrnAndProcCodeAndMsgTypeIdAndGenStatusNot(rrn, procCode, "0210", 7);
                }
                final boolean refundExists = refundTxn != null;
                final PosTransactionEntity voidRefundTxn = this.posRepo.findByRrnAndProcCodeAndMsgTypeIdAndGenStatusNot(rrn, procCode, mti, 7);
                final boolean duplicateVoidRefund = voidRefundTxn != null;
                if (duplicateVoidRefund) {
                    validationErrors.add("DUPLICATE_TRANSACTION;duplicate transaction not allowed");
                    isValid = false;
                }
                else if (!refundExists) {
                    validationErrors.add("INVALID_TRANSACTION;original transaction not found.");
                    isValid = false;
                }
                else {
                    posData = refundTxn;
                    isValid = true;
                }
            }
            else if (procCode.startsWith("71")) {
                posData = this.posRepo.findByRrnAndProcCodeAndMsgTypeIdAndGenStatusNot(rrn, "000000", "0130", 6);
            }
            else if (procCode.startsWith("21")) {
                posData = this.posRepo.findByRrnAndProcCodeAndMsgTypeIdAndGenStatusNot(rrn, "200000", "0130", 6);
            }
            else {
                validationErrors.add("INVALID_TRANSACTION;original transaction not found.");
            }
            if (posData != null) {
                final String originalMti = posData.getMsgTypeId();
                final String originalProc = posData.getProcCode();
                if (procCode.startsWith("61")) {
                    isValid = ("0110".equals(originalMti) && originalProc.startsWith("61"));
                }
                else if (procCode.startsWith("00")) {
                    isValid = (("0110".equals(originalMti) || "0210".equals(originalMti)) && originalProc.startsWith("00"));
                }
                else if (procCode.startsWith("20")) {
                    isValid = (("0110".equals(originalMti) || "0210".equals(originalMti)) && originalProc.startsWith("20"));
                }
                else if (procCode.startsWith("71")) {
                    isValid = ("0130".equals(originalMti) && originalProc.startsWith("00"));
                }
                else if (procCode.startsWith("62")) {
                    isValid = ("0110".equals(originalMti) && originalProc.startsWith("62"));
                }
                else {
                    isValid = (procCode.startsWith("21") && "0130".equals(originalMti) && originalProc.startsWith("20"));
                }
                if (!isValid) {
                    validationErrors.add("INVALID_TRANSACTION;original transaction not found.");
                }
            }
            else {
                validationErrors.add("INVALID_TRANSACTION;original transaction not found.");
            }
        }
        else {
            if (procCode != null && procCode.startsWith("00") && mti.matches("0110|0210|0130")) {
                isValid = true;
            }
            else if (mti.equals("0110") && procCode.startsWith("61")) {
                isValid = true;
            }
            else if (mti.matches("0110|0210|0130") && procCode.startsWith("20")) {
                final CurrencyUtil currencyUtil = this.currencyUtil;
                final int divisor = CurrencyUtil.getDivisor(transactionData.getTxnCurrencyCode());
                if (amountStr != null && !amountStr.trim().isEmpty()) {
                    txnAmount = Double.valueOf(amountStr) / divisor;
                }
                final PosTransactionEntity saleTxn = this.posRepo.findByRrnAndProcCodeAndMsgTypeIdAndGenStatusNot(orgRrn, "000000", mti, 7);
                final boolean saleExists = saleTxn != null;
                final boolean amountMatches = saleExists && saleTxn.getTxnAmount() != null && Double.compare(saleTxn.getTxnAmount(), txnAmount) == 0;
                final PosTransactionEntity refundTxn2 = this.posRepo.findByOriginalRRNAndProcCodeAndMsgTypeIdAndGenStatusNot(orgRrn, procCode, mti, 7);
                final boolean duplicateRefund = refundTxn2 != null;
                isValid = (saleExists && amountMatches && !duplicateRefund);
                if (isValid) {
                    posData = saleTxn;
                }
                else {
                    validationErrors.add("DUPLICATE_TRANSACTION;duplicate transaction not allowed");
                }
            }
            else if (mti.equals("0130") && procCode.startsWith("00")) {
                posData = this.posRepo.findByRrnAndProcCodeAndMsgTypeIdAndGenStatusNot(rrn, "610000", "0110", 6);
                isValid = (posData != null);
            }
            else if (mti.equals("0110") && procCode.startsWith("62")) {
                posData = this.posRepo.findByRrnAndProcCodeAndMsgTypeIdAndGenStatusNot(rrn, "610000", "0110", 6);
                isValid = (posData != null);
            }
            else {
                isValid = false;
            }
            if (!isValid) {
                validationErrors.add("INVALID_TRANSACTION;original transaction not found.");
            }
        }
        if (!validationErrors.isEmpty() && !kafkaFlag) {
            final ResponseVo responseVo = new ResponseVo();
            responseVo.setRrn(transactionData.getRetRefNumber());
            responseVo.setMti(transactionData.getMti());
            responseVo.setCardAcceptorId(transactionData.getCardAcceptorId());
            responseVo.setCardAcceptorTid(transactionData.getCardAcceptorTid());
            responseVo.setUniqueId(transactionData.getUniqueId());
            responseVo.setAmountTransaction(transactionData.getAmountTransaction());
            responseVo.setResponseMessage("Transaction Rejected");
            responseVo.setValidationErrors(validationErrors);
            this.CommonService.insertRejectedTxns(responseVo, validationErrors, transactionData.getTxnDateTime(), 1, "TLF", fileName);
            throw new ValidationException(responseVo.toString());
        }
        return validationErrors;
    }
    
    public void validateLength(final String fileName, final int length) {
        if (fileName == null || fileName.isEmpty()) {
            throw new ValidationException("INVALID FILE NAME;Filename is missing");
        }
        if (fileName.length() > length) {
            throw new ValidationException("INVALID FILE NAME;Filename exceeds the allowed length");
        }
    }
    
    public boolean validateTxnFile(final Map<String, Object> requestMap) {
        return requestMap.size() == 1 && requestMap.containsKey("fileName") && requestMap.get("fileName") instanceof String;
    }
    
    public boolean validateTxnFileLength(final String fileName) {
        return fileName != null && fileName.length() <= 60;
    }
    
    public boolean isValidAuth(final String authorization) {
        return Objects.equals(Objects.requireNonNull(this.env.getProperty("RTS_AuthKey"), "The property RTS_AuthKey not found!"), authorization);
    }
    
    public Map<String, String> validateUaeIrfRequest(final UAESwitchIRFVo request) {
        final Map<String, String> response = new HashMap<String, String>();
        final Map<String, Object> mandatoryFields = new HashMap<String, Object>();
        if (Objects.isNull(request.getSegment())) {
            response.put("status", "error");
            response.put("message", "The segment cannot be null");
            return response;
        }
        final String segment = request.getSegment().trim().toUpperCase();
        if (segment.isEmpty()) {
            response.put("status", "error");
            response.put("message", "The segment cannot be blank");
            return response;
        }
        if (!segment.equals("SPECIAL") && !segment.equals("GENERAL")) {
            response.put("status", "error");
            response.put("message", "Invalid Segement. Allowed Segment values are SPECIAL or GENERAL");
            return response;
        }
        if (Objects.isNull(request.getSegmentDesc())) {
            response.put("status", "error");
            response.put("message", "The segmentDesc cannot be null");
            return response;
        }
        if (request.getSegmentDesc().trim().isEmpty()) {
            response.put("status", "error");
            response.put("message", "The segmentDesc cannot be blank");
            return response;
        }
        if ("SPECIAL".equalsIgnoreCase(segment)) {
            final Map<String, String> mccResponse = this.validateMcc(request.getMcc(), 4);
            if (!"success".equals(mccResponse.get("status"))) {
                return mccResponse;
            }
            if (Objects.isNull(request.getMccDescription())) {
                response.put("status", "error");
                response.put("message", "The mccDescription cannot be null");
                return response;
            }
            if (request.getMccDescription().trim().isEmpty()) {
                response.put("status", "error");
                response.put("message", "The mccDescription cannot be blank");
                return response;
            }
            mandatoryFields.put("IRF Rate", request.getIrfRate());
            mandatoryFields.put("IRF Max", request.getIrfMax());
            mandatoryFields.put("IRF Fixed", request.getIrfFixed());
        }
        else {
            mandatoryFields.put("POS IRF", request.getPosIrf());
            mandatoryFields.put("ECOM IRF", request.getEcomIrf());
            mandatoryFields.put("POS IRF Max", request.getPosIrfMax());
            mandatoryFields.put("ECOM IRF Max", request.getEcomIrfMax());
        }
        final StringBuilder missingFields = new StringBuilder();
        for (final Map.Entry<String, Object> entry : mandatoryFields.entrySet()) {
            if (entry.getValue() == null) {
                if (missingFields.length() > 0) {
                    missingFields.append(", ");
                }
                missingFields.append(entry.getKey());
            }
        }
        if (missingFields.length() > 0) {
            response.put("status", "error");
            response.put("message", "Missing mandatory fields for " + segment + " segment: " + missingFields.toString());
            return response;
        }
        response.put("status", "success");
        return response;
    }
    
    public Map<String, String> validateMcc(final String mccCode, final int length) {
        final Map<String, String> response = new HashMap<String, String>();
        if (mccCode == null || mccCode.isEmpty()) {
            response.put("status", "error");
            response.put("message", "The MCC cannot be null or blank");
        }
        else if (mccCode.length() > length) {
            response.put("status", "error");
            response.put("message", "The maximum length of MCC is 4");
        }
        else {
            response.put("status", "success");
        }
        return response;
    }
    
    public Map<String, String> validateOmanNetIrfRequest(final OmanNetIrfVo request, final boolean editFlag) {
        Map<String, String> omanNetResponse = new HashMap<String, String>();
        if (request.getMccDescription() != null && request.getMcc() == null) {
            omanNetResponse.put("status", "error");
            omanNetResponse.put("message", "The MCC cannot be null or blank");
        }
        omanNetResponse = this.validateMcc(request.getMcc(), 4);
        if (!"success".equals(omanNetResponse.get("status"))) {
            omanNetResponse.put("status", "error");
            return omanNetResponse;
        }
        if (editFlag && request.getReferenceNumber() == null) {
            omanNetResponse.put("status", "error");
            omanNetResponse.put("message", "ReferenceNumber is mandaory for update");
        }
        if (!request.getRoute().toLowerCase().matches("omannet|onus")) {
            omanNetResponse.put("status", "error");
            omanNetResponse.put("message", "Route must be Omannet / ONUS");
        }
        if (!request.getSubRoute().toLowerCase().matches("omannet|onus|maal|visa|mastercard|amex")) {
            omanNetResponse.put("status", "error");
            omanNetResponse.put("message", "Sub Route must be omannet/onus/maal/visa/mastercard/amex");
        }
        return omanNetResponse;
    }
    
    public Map<String, String> validateJaywanIrfRequest(final JaywanIRFVo request) {
        final Map<String, String> response = new HashMap<String, String>();
        if (Objects.isNull(request.getSegment())) {
            return this.error("The segment cannot be null");
        }
        final String segment = request.getSegment().trim().toUpperCase();
        if (segment.isEmpty()) {
            return this.error("The segment cannot be blank");
        }
        if (!"SPECIAL".equals(segment) && !"GENERAL".equals(segment)) {
            return this.error("Invalid Segment. Allowed values are SPECIAL or GENERAL");
        }
        if (Objects.isNull(request.getSegmentDesc())) {
            return this.error("The segmentDesc cannot be null");
        }
        if (request.getSegmentDesc().trim().isEmpty()) {
            return this.error("The segmentDesc cannot be blank");
        }
        final Map<String, Object> mandatoryFields = new HashMap<String, Object>();
        if ("SPECIAL".equals(segment)) {
            final Map<String, String> mccResponse = this.validateMcc(request.getMcc(), 4);
            if (!"success".equalsIgnoreCase(mccResponse.get("status"))) {
                return mccResponse;
            }
            if (Objects.isNull(request.getMccDescription())) {
                return this.error("The mccDescription cannot be null");
            }
            if (request.getMccDescription().trim().isEmpty()) {
                return this.error("The mccDescription cannot be blank");
            }
            mandatoryFields.put("IRF Rate", request.getIrfRate());
            mandatoryFields.put("IRF Max", request.getIrfMax());
            mandatoryFields.put("IRF Fixed", request.getIrfFixed());
        }
        else {
            mandatoryFields.put("POS IRF", request.getPosIrf());
            mandatoryFields.put("ECOM IRF", request.getEcomIrf());
            mandatoryFields.put("POS IRF Max", request.getPosIrfMax());
            mandatoryFields.put("ECOM IRF Max", request.getEcomIrfMax());
        }
        final StringJoiner missingFields = new StringJoiner(", ");
        for (final Map.Entry<String, Object> entry : mandatoryFields.entrySet()) {
            if (entry.getValue() == null) {
                missingFields.add(entry.getKey());
            }
        }
        if (missingFields.length() > 0) {
            return this.error("Missing mandatory fields for " + segment + " segment: " + String.valueOf(missingFields));
        }
        response.put("status", "success");
        return response;
    }
    
    private Map<String, String> error(final String message) {
        final Map<String, String> errorMap = new HashMap<String, String>();
        errorMap.put("status", "error");
        errorMap.put("message", message);
        return errorMap;
    }
    
    public Map<String, String> validateMercuryIrfRequest(@Valid final MercuryIRFVo request) {
        final Map<String, String> response = new HashMap<String, String>();
        final Map<String, String> mccResponse = this.validateMcc(request.getMcc(), 4);
        if (!"success".equalsIgnoreCase(mccResponse.get("status"))) {
            return mccResponse;
        }
        if (request.getMccDescription() == null || request.getMccDescription().isBlank()) {
            return this.error("MCC Description is required");
        }
        if (request.getIrfPercentage() == null) {
            return this.error("IRF Percentage is required");
        }
        if (request.getIrfFixed() == null) {
            return this.error("IRF Fixed is required");
        }
        if (request.getIrfPercentage() < 0.0 || request.getIrfFixed() < 0.0) {
            return this.error("IRF Percentage values cannot be negative");
        }
        if (request.getIrfPercentage() == null || request.getIrfFixed() == null) {
            return this.error("IRF Percentage and IRF Fixed are required");
        }
        if (request.getIrfPercentage() == 0.0 && request.getIrfFixed() == 0.0) {
            return this.error("IRF Percentage / IRF Fixed: any one field must have value");
        }
        response.put("status", "success");
        return response;
    }
    
    public ValidationService(final PosTransactionRepository posRepo, final CommonManagementsService CommonService, final CryptAPI cryptApi, final Environment env, final CurrencyUtil currencyUtil, final CountriesRepository countriesRepo, final CurrencyRepo currencyRepo) {
        this.posRepo = posRepo;
        this.CommonService = CommonService;
        this.cryptApi = cryptApi;
        this.env = env;
        this.currencyUtil = currencyUtil;
        this.countriesRepo = countriesRepo;
        this.currencyRepo = currencyRepo;
    }
}
