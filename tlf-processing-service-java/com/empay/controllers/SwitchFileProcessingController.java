// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.controllers;

import com.empay.tlfprocessing.vo.RefundReportVo;
import com.empay.tlfprocessing.vo.TLFVo;
import com.empay.exceptions.ValidationException;
import java.util.HashMap;
import org.springframework.web.bind.annotation.RequestHeader;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.ConstraintViolation;
import java.util.Set;
import jakarta.validation.Validator;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.HttpStatus;
import java.util.Objects;
import java.util.List;
import org.apache.commons.collections4.ListUtils;
import jakarta.validation.Validation;
import com.empay.tlfprocessing.vo.SwitchExtractVo;
import java.util.ArrayList;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.empay.tlfprocessing.vo.ResponseVo;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;
import com.empay.tlfprocessing.vo.RequestVo;
import org.springframework.core.env.Environment;
import com.empay.services.ConsolidatedRefundReportService;
import com.empay.commonservice.CommonManagementsService;
import com.empay.services.ValidationService;
import com.empay.services.TxnProcessingService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({ "/tlf/" })
public class SwitchFileProcessingController
{
    private final TxnProcessingService txnProcessingService;
    private final ValidationService validationService;
    private final CommonManagementsService CommonService;
    private final ConsolidatedRefundReportService refundReort;
    private final Environment env;
    private final ValidationService validateService;
    
    @PostMapping(value = { "/v1/PostmanTxn" }, produces = { "application/json" })
    public ResponseEntity<Map<String, String>> processTxn(@Valid @RequestBody final RequestVo switchVo) {
        return this.txnProcessingService.processOnlineTxn(switchVo, false, "KAFKA", 1, "KAFKA");
    }
    
    @PostMapping(value = { "/v1/processKafkaTxn" }, produces = { "application/json" })
    public ResponseEntity<ResponseVo> processDummyTxn(@Valid @RequestBody final String inputData) throws JsonProcessingException {
        final ObjectMapper mapper = new ObjectMapper();
        List<String> testerror = new ArrayList<String>();
        final ResponseVo responseVo = new ResponseVo();
        SwitchExtractVo requestObject = null;
        requestObject = (SwitchExtractVo)mapper.readValue(inputData, (Class)SwitchExtractVo.class);
        final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        final Set<ConstraintViolation<SwitchExtractVo>> validationSet = validator.validate((Object)requestObject, new Class[0]);
        if (!validationSet.isEmpty()) {
            testerror = validationSet.stream().map(f -> String.valueOf(f.getPropertyPath()) + ":" + f.getMessage()).toList();
        }
        testerror = ListUtils.union((List)testerror, (List)this.validationService.validateTxnRequest(requestObject, true, "KAFKA"));
        responseVo.setRrn(requestObject.getRetRefNumber());
        responseVo.setMti(requestObject.getMti());
        responseVo.setCardAcceptorId(requestObject.getCardAcceptorId());
        responseVo.setCardAcceptorTid(requestObject.getCardAcceptorTid());
        responseVo.setUniqueId(requestObject.getUniqueId());
        responseVo.setAmountTransaction(requestObject.getAmountTransaction());
        if (testerror.isEmpty()) {
            final RequestVo requestVo = new RequestVo();
            requestVo.setPayload(requestObject);
            final ResponseEntity<Map<String, String>> response = this.txnProcessingService.processOnlineTxn(requestVo, true, "KAFKA", 1, "KAFKA");
            if (Objects.nonNull(response) && response.getStatusCode().value() != 200) {
                testerror.add(((Map)response.getBody()).get("status"));
                responseVo.setValidationErrors(testerror);
                responseVo.setResponseMessage("Transaction Rejected");
                this.CommonService.insertRejectedTxns(responseVo, testerror, requestObject.getTxnDateTime(), 1, "KAFKA", "KAFKA");
                return (ResponseEntity<ResponseVo>)new ResponseEntity((Object)responseVo, response.getStatusCode());
            }
            responseVo.setResponseCode(requestObject.getResponseCode());
            responseVo.setResponseMessage("Transaction Successfully Accepted");
        }
        else {
            responseVo.setResponseCode(requestObject.getResponseCode());
            responseVo.setResponseMessage("Transaction Rejected");
            responseVo.setValidationErrors(testerror);
            this.CommonService.insertRejectedTxns(responseVo, testerror, requestObject.getTxnDateTime(), 1, "KAFKA", "KAFKA");
        }
        return (ResponseEntity<ResponseVo>)new ResponseEntity((Object)responseVo, (HttpStatusCode)HttpStatus.OK);
    }
    
    @PostMapping(value = { "/v1/RecalculateInterchange" }, produces = { "application/json" })
    public ResponseEntity<Map<String, String>> recalculateInterchange(@RequestHeader("POSCODE") final String posCode, @RequestHeader("RRN") final String rrn) {
        final Map<String, String> responseBody = new HashMap<String, String>();
        final String message = this.txnProcessingService.interChangeRecalculation(1, Integer.parseInt(posCode), rrn);
        responseBody.put("message", message);
        return (ResponseEntity<Map<String, String>>)ResponseEntity.status((HttpStatusCode)HttpStatus.OK).body((Object)responseBody);
    }
    
    @PostMapping(value = { "/v1/processTxnFile" }, produces = { "application/json" })
    public ResponseEntity<?> tlfTxnProcessing(@RequestBody final Map<String, Object> rawRequest) {
        if (!this.validateService.validateTxnFile(rawRequest)) {
            throw new ValidationException("INVALID_REQUEST_FIELDS; Please provide valid request fields.");
        }
        final TLFVo vo = new TLFVo();
        vo.setFileName(rawRequest.get("fileName"));
        if (!this.validateService.validateTxnFileLength(vo.getFileName())) {
            throw new ValidationException("INVALID FILE NAME;Filename exceeds the allowed length");
        }
        return this.txnProcessingService.processTLFTxn(vo.getFileName());
    }
    
    @PostMapping(value = { "/v1/consolidatedRefundReport" }, produces = { "application/json" })
    public Map<String, String> generateConsolidatedRefundReport(@Valid @RequestBody final RefundReportVo reportVo) {
        final Integer insCode = Integer.parseInt(Objects.requireNonNull(this.env.getProperty("insCode"), "The key insCode not found in the property."));
        final Integer jobNumber = Integer.parseInt(Objects.requireNonNull(this.env.getProperty("jobNumber"), "The key jobNumber not found in the property."));
        final Integer userSerNumber = Integer.parseInt(Objects.requireNonNull(this.env.getProperty("userSerNumber"), "The key userSerNumber not found in the property."));
        final String insShortName = Objects.requireNonNull(this.env.getProperty("INS_SHORT_NAME"), "The key INS_SHORT_NAME not found in the property.");
        final String institutionID = Objects.requireNonNull(this.env.getProperty("insCode"), "The key InstitutionID not found in the property.");
        final String BankID = Objects.requireNonNull(this.env.getProperty("bankId"), "The key BankID not found in the property.");
        return this.refundReort.generateConsolidatedRefundReport(reportVo, insCode, jobNumber, userSerNumber, insShortName, institutionID, BankID);
    }
    
    public SwitchFileProcessingController(final TxnProcessingService txnProcessingService, final ValidationService validationService, final CommonManagementsService CommonService, final ConsolidatedRefundReportService refundReort, final Environment env, final ValidationService validateService) {
        this.txnProcessingService = txnProcessingService;
        this.validationService = validationService;
        this.CommonService = CommonService;
        this.refundReort = refundReort;
        this.env = env;
        this.validateService = validateService;
    }
}
