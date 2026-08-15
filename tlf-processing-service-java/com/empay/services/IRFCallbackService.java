// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.services;

import org.apache.logging.log4j.LogManager;
import java.time.LocalDateTime;
import java.math.RoundingMode;
import java.math.BigDecimal;
import com.google.gson.Gson;
import com.empay.entities.PosTransactionEntity;
import com.empay.tlfprocessing.vo.IRFCallbackVo;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.Collections;
import java.util.Optional;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.HttpHeaders;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.HttpStatus;
import com.empay.entities.IRFCallbackEntity;
import com.empay.exceptions.APIConstants;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import com.empay.util.CurrencyUtil;
import java.time.format.DateTimeFormatter;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;
import com.empay.repositories.IRFCallbackRepo;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class IRFCallbackService
{
    private static final Logger log;
    private final IRFCallbackRepo repo;
    private final RestTemplate restTemplate;
    private final Environment env;
    private DateTimeFormatter formatter;
    private final CurrencyUtil currencyUtil;
    
    public ResponseEntity<Map<String, String>> updatePendingDatas() {
        final Map<String, String> response = new HashMap<String, String>();
        try {
            final List<IRFCallbackEntity> entities = this.repo.findAllByStatus(APIConstants.CALLBACK_PENDING);
            for (final IRFCallbackEntity entity : entities) {
                this.updateApiResponse(entity);
            }
            return (ResponseEntity<Map<String, String>>)new ResponseEntity((Object)response, (HttpStatusCode)HttpStatus.OK);
        }
        catch (final Exception e) {
            if (Objects.nonNull(e.getMessage())) {
                response.put("status", "Data Processing Failed:" + e.getMessage().replaceAll("([\\w]+\\.)+", ""));
            }
            IRFCallbackService.log.error("ERROR: retryIRFRequest():", (Throwable)e);
            return (ResponseEntity<Map<String, String>>)new ResponseEntity((Object)response, (HttpStatusCode)HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    public ResponseEntity<Map<String, String>> retryIRFRequest(final Integer refSerNumber) {
        final Map<String, String> response = new HashMap<String, String>();
        try {
            IRFCallbackEntity entity = this.repo.findByRefSerNumberAndSchemeInchgFlag(refSerNumber, "No");
            if (Objects.nonNull(entity) && !APIConstants.CALLBACK_SUCCESS.equals(entity.getStatus())) {
                entity = this.updateApiResponse(entity);
            }
            response.put("response", entity.getResponse());
            return (ResponseEntity<Map<String, String>>)new ResponseEntity((Object)response, (HttpStatusCode)HttpStatus.OK);
        }
        catch (final Exception e) {
            if (Objects.nonNull(e.getMessage())) {
                response.put("status", "Retry Failed:" + e.getMessage().replaceAll("([\\w]+\\.)+", ""));
            }
            IRFCallbackService.log.error("ERROR: retryIRFRequest():", (Throwable)e);
            return (ResponseEntity<Map<String, String>>)new ResponseEntity((Object)response, (HttpStatusCode)HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    public IRFCallbackEntity updateApiResponse(final IRFCallbackEntity entity) {
        try {
            String requestURL = Objects.requireNonNull(this.env.getProperty("irfRequestUrl"), "The property irfRequestUrl not found!");
            final String secParam = Objects.requireNonNull(this.env.getProperty("secParam"), "The property secParam not found!");
            requestURL = UriComponentsBuilder.fromHttpUrl(requestURL).queryParam("sec", new Object[] { secParam }).toUriString();
            final String requestBody = entity.getRequest();
            IRFCallbackService.log.info("IRF Callback Request:" + requestBody);
            final HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            final HttpEntity<String> requestEntity = (HttpEntity<String>)new HttpEntity((Object)requestBody, (MultiValueMap)headers);
            final ResponseEntity<JsonNode> response = (ResponseEntity<JsonNode>)this.restTemplate.exchange(requestURL, HttpMethod.POST, (HttpEntity)requestEntity, (Class)JsonNode.class, new Object[0]);
            entity.setStatus(APIConstants.CALLBACK_FAILED);
            if (Objects.nonNull(response.getBody())) {
                final JsonNode responseBody = (JsonNode)response.getBody();
                entity.setResponse(responseBody.toString());
                if (response.getStatusCode().equals((Object)HttpStatus.OK)) {
                    entity.setStatus(APIConstants.CALLBACK_SUCCESS);
                    IRFCallbackService.log.info("IRF Callback Response:Success");
                }
            }
            return (IRFCallbackEntity)this.repo.saveAndFlush((Object)entity);
        }
        catch (final Exception e) {
            entity.setResponse(e.getMessage());
            entity.setStatus(APIConstants.CALLBACK_FAILED);
            IRFCallbackService.log.error("ERROR: IRF Callback Response :", (Throwable)e);
            return (IRFCallbackEntity)this.repo.saveAndFlush((Object)entity);
        }
    }
    
    public ResponseEntity<Object> fetchFailedData() {
        final Map<String, Object> response = new HashMap<String, Object>();
        try {
            final List<IRFCallbackEntity> entities = this.repo.findAllByStatus(APIConstants.CALLBACK_FAILED);
            final List<IRFCallbackVo> valueObjects = Optional.ofNullable(entities).orElseGet(Collections::emptyList).stream().map(this::mapToIRFCallbackVo).toList();
            response.put("dataList", valueObjects);
            return (ResponseEntity<Object>)new ResponseEntity((Object)response, (HttpStatusCode)HttpStatus.OK);
        }
        catch (final Exception e) {
            if (Objects.nonNull(e.getMessage())) {
                response.put("status", "Data Fetching Failed:" + e.getMessage().replaceAll("([\\w]+\\.)+", ""));
            }
            IRFCallbackService.log.error("ERROR: fetchFailedData():", (Throwable)e);
            return (ResponseEntity<Object>)new ResponseEntity((Object)response, (HttpStatusCode)HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    private IRFCallbackVo mapToIRFCallbackVo(final IRFCallbackEntity entity) {
        try {
            final IRFCallbackVo valueObject = new IRFCallbackVo();
            valueObject.setStatus(entity.getStatus());
            valueObject.setRefSerNumber(entity.getRefSerNumber());
            valueObject.setRequest(entity.getRequest());
            valueObject.setResponse(entity.getResponse());
            return valueObject;
        }
        catch (final Exception e) {
            IRFCallbackService.log.error("ERROR: mapToIRFCallbackVo():", (Throwable)e);
            return null;
        }
    }
    
    public IRFCallbackEntity insertIntoIRFCallback(final PosTransactionEntity posTxnEntity) {
        try {
            final Gson gson = new Gson();
            final IRFCallbackEntity entity = new IRFCallbackEntity();
            final CurrencyUtil currencyUtil = this.currencyUtil;
            final int exponent = CurrencyUtil.getExponent(posTxnEntity.getTxnCurCode());
            final Map<String, String> jsonMap = new HashMap<String, String>();
            jsonMap.put("cpMid", posTxnEntity.getMerchantId());
            jsonMap.put("bankMid", "");
            jsonMap.put("bankTid", "");
            jsonMap.put("uniqueId", posTxnEntity.getTxnUniqueId());
            jsonMap.put("irdCode", posTxnEntity.getIrd());
            jsonMap.put("fixed", (posTxnEntity.getIrfFixed() != null) ? posTxnEntity.getIrfFixed().toString() : "0");
            jsonMap.put("percentage", (posTxnEntity.getIrfPercent() != null) ? posTxnEntity.getIrfPercent().toString() : "0");
            final String irfamount = BigDecimal.valueOf((posTxnEntity.getIrfAmount() != null) ? posTxnEntity.getIrfAmount() : 0.0).setScale(exponent, RoundingMode.HALF_UP).toString();
            jsonMap.put("irfAmount", irfamount);
            jsonMap.put("rrn", posTxnEntity.getRrn());
            jsonMap.put("bankRrn", "");
            jsonMap.put("txnAmount", (posTxnEntity.getTxnAmount() != null) ? posTxnEntity.getTxnAmount().toString() : "0");
            jsonMap.put("mti", posTxnEntity.getMsgTypeId());
            jsonMap.put("domIntlFlag", (posTxnEntity.getCardDomIntlFlag() != null) ? posTxnEntity.getCardDomIntlFlag().toString() : null);
            jsonMap.put("cardType", Optional.ofNullable(posTxnEntity.getCardType()).map(type -> type.equals('C') ? "Credit" : "Debit").orElse(""));
            jsonMap.put("cardClassification", posTxnEntity.getCardCategory());
            jsonMap.put("description", "");
            jsonMap.put("status", "success");
            jsonMap.put("createdAt", LocalDateTime.now().format(this.formatter));
            jsonMap.put("schemeInterchangeFlag", "No");
            entity.setInstitutionCode(posTxnEntity.getInsCode());
            entity.setLastUpdated(LocalDateTime.now());
            entity.setRefSerNumber(posTxnEntity.getSerialNumber());
            entity.setRequest(gson.toJson((Object)jsonMap));
            entity.setStatus(APIConstants.CALLBACK_PENDING);
            entity.setUpdatedUser(posTxnEntity.getUpdatedUser());
            entity.setSchemeInchgFlag("No");
            return (IRFCallbackEntity)this.repo.saveAndFlush((Object)entity);
        }
        catch (final Exception e) {
            IRFCallbackService.log.error("ERROR: insertIntoIRFCallback():", (Throwable)e);
            return new IRFCallbackEntity();
        }
    }
    
    public IRFCallbackService(final IRFCallbackRepo repo, final RestTemplate restTemplate, final Environment env, final CurrencyUtil currencyUtil) {
        this.formatter = DateTimeFormatter.ofPattern("yyyyMMdd HH:mm:ss");
        this.repo = repo;
        this.restTemplate = restTemplate;
        this.env = env;
        this.currencyUtil = currencyUtil;
    }
    
    static {
        log = LogManager.getLogger((Class)IRFCallbackService.class);
    }
}
