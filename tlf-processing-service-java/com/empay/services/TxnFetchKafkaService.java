// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.services;

import org.apache.logging.log4j.LogManager;
import org.springframework.http.ResponseEntity;
import jakarta.validation.ConstraintViolation;
import java.util.Set;
import jakarta.validation.Validator;
import com.empay.tlfprocessing.vo.SwitchExtractVo;
import java.util.Map;
import java.util.List;
import org.apache.commons.collections4.ListUtils;
import jakarta.validation.Validation;
import java.util.Objects;
import java.util.ArrayList;
import com.empay.tlfprocessing.vo.ResponseVo;
import org.springframework.kafka.annotation.KafkaListener;
import com.empay.tlfprocessing.vo.RequestVo;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.empay.commonservice.CommonManagementsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class TxnFetchKafkaService
{
    private static final Logger log;
    private static final String TOPIC = "oracle_TRANSACTIONS";
    private static final String TOPIC_ACK = "ack_TOPIC";
    private static final String TOPIC_ERR = "err_TOPIC";
    private final ValidationService validationService;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    private final TxnProcessingService txnProcessingService;
    private final CommonManagementsService CommonService;
    
    @KafkaListener(topics = { "oracle_TRANSACTIONS" }, groupId = "fetch-txn-group")
    public void consume(final String inputData) {
        try {
            TxnFetchKafkaService.log.info("//------------Kafka Transaction Start : " + inputData);
            final ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            final RequestVo requestObject = (RequestVo)mapper.readValue(inputData, (Class)RequestVo.class);
            this.parseMessage(requestObject);
            TxnFetchKafkaService.log.info(" Kafka Transaction Completed ------------// ");
        }
        catch (final Exception e) {
            TxnFetchKafkaService.log.error("!! Consume Kafka service failed :", (Throwable)e);
            this.kafkaTemplate.send("err_TOPIC", (Object)inputData);
            TxnFetchKafkaService.log.info("Sent response to :err_TOPIC");
        }
    }
    
    public void parseMessage(final RequestVo requestObject) {
        final ResponseVo responseVo = new ResponseVo();
        SwitchExtractVo requestData = null;
        List<String> testerror = new ArrayList<String>();
        try {
            requestData = requestObject.getPayload();
            if (Objects.nonNull(requestData)) {
                final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
                final Set<ConstraintViolation<SwitchExtractVo>> validationSet = validator.validate((Object)requestData, new Class[0]);
                if (Objects.nonNull(validationSet) && !validationSet.isEmpty()) {
                    testerror = validationSet.stream().map(f -> String.valueOf(f.getPropertyPath()) + ":" + f.getMessage()).toList();
                }
                final List<String> validateTxnRequest = this.validationService.validateTxnRequest(requestData, true, "KAFKA");
                if (validateTxnRequest != null) {
                    testerror = ListUtils.union((List)testerror, (List)validateTxnRequest);
                }
                responseVo.setRrn(requestData.getRetRefNumber());
                responseVo.setMti(requestData.getMti());
                responseVo.setCardAcceptorId(requestData.getCardAcceptorId());
                responseVo.setCardAcceptorTid(requestData.getCardAcceptorTid());
                responseVo.setUniqueId(requestData.getUniqueId());
                responseVo.setAmountTransaction(requestData.getAmountTransaction());
            }
            if (testerror.isEmpty()) {
                final ResponseEntity<Map<String, String>> response = this.txnProcessingService.processOnlineTxn(requestObject, true, "KAFKA", 1, "KAFKA");
                responseVo.setResponseCode(requestData.getResponseCode());
                responseVo.setResponseMessage("Transaction Successfully Accepted");
                if (Objects.nonNull(response) && response.getStatusCode().value() != 200) {
                    testerror.add(((Map)response.getBody()).get("status"));
                    responseVo.setValidationErrors(testerror);
                    responseVo.setResponseMessage("Transaction Rejected");
                    this.CommonService.insertRejectedTxns(responseVo, testerror, requestData.getTxnDateTime(), 1, "KAFKA", "KAFKA");
                }
            }
            else {
                responseVo.setResponseCode(requestData.getResponseCode());
                responseVo.setResponseMessage("Transaction Rejected");
                responseVo.setValidationErrors(testerror);
                this.CommonService.insertRejectedTxns(responseVo, testerror, requestData.getTxnDateTime(), 1, "KAFKA", "KAFKA");
            }
            TxnFetchKafkaService.log.info("Response :" + String.valueOf(responseVo));
            this.kafkaTemplate.send("ack_TOPIC", (Object)responseVo.toString());
            TxnFetchKafkaService.log.info("Sent response to :ack_TOPIC");
        }
        catch (final Exception e) {
            TxnFetchKafkaService.log.error("Request not loaded:", (Throwable)e);
            responseVo.setResponseMessage("Transaction loading Failed " + e.getMessage());
            this.kafkaTemplate.send("err_TOPIC", (Object)requestObject.toString());
            TxnFetchKafkaService.log.info("Sent response to :err_TOPIC");
            return;
        }
        TxnFetchKafkaService.log.info("Request :" + String.valueOf(requestData));
    }
    
    public TxnFetchKafkaService(final ValidationService validationService, final TxnProcessingService txnProcessingService, final CommonManagementsService CommonService) {
        this.validationService = validationService;
        this.txnProcessingService = txnProcessingService;
        this.CommonService = CommonService;
    }
    
    static {
        log = LogManager.getLogger((Class)TxnFetchKafkaService.class);
    }
}
