/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.cryptapi.CryptAPI
 *  com.empay.cryptapi.DecryptResponseVo
 *  com.empay.cryptapi.EncryptResponseVo
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 *  org.springframework.core.env.Environment
 *  org.springframework.http.HttpEntity
 *  org.springframework.http.HttpHeaders
 *  org.springframework.http.HttpMethod
 *  org.springframework.http.MediaType
 *  org.springframework.http.ResponseEntity
 *  org.springframework.stereotype.Service
 *  org.springframework.util.MultiValueMap
 *  org.springframework.web.client.RestTemplate
 */
package com.empay.cryptapi;

import com.empay.cryptapi.DecryptResponseVo;
import com.empay.cryptapi.EncryptResponseVo;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class CryptAPI {
    private static final Logger log = LogManager.getLogger(CryptAPI.class);
    private final Environment env;
    private final RestTemplate restTemplate;
    private static final String BANK_ID = "bankId";
    private static final String ACCESS_TOKEN = "accessToken";

    public EncryptResponseVo getToken(Set<String> cardNumbers) {
        log.info("getToken started...");
        HashMap map = new HashMap();
        EncryptResponseVo response = new EncryptResponseVo();
        int counter = 0;
        int chunkSize = 16;
        ArrayList<String> cardList = new ArrayList<String>(chunkSize);
        try {
            EncryptResponseVo responseVo;
            ArrayList<String> uniqueCardNumbers = new ArrayList<String>(cardNumbers);
            if (uniqueCardNumbers.size() <= chunkSize) {
                responseVo = this.callEncrypt(uniqueCardNumbers);
                map.putAll(responseVo.getUuids());
            } else {
                for (String cards : uniqueCardNumbers) {
                    cardList.add(cards);
                    if (++counter != chunkSize) continue;
                    EncryptResponseVo responseVo2 = this.callEncrypt(cardList);
                    map.putAll(responseVo2.getUuids());
                    counter = 0;
                    cardList.clear();
                }
            }
            if (!cardList.isEmpty()) {
                responseVo = this.callEncrypt(cardList);
                map.putAll(responseVo.getUuids());
            }
            response.setUuids(map);
        }
        catch (Exception e) {
            log.error("Error getToken() :", (Throwable)e);
            response = null;
        }
        log.info("getToken ended.");
        return response;
    }

    public DecryptResponseVo getCardNumber(Set<String> tokens) {
        log.info("getCardNumber started...");
        DecryptResponseVo response = new DecryptResponseVo();
        HashMap map = new HashMap();
        try {
            DecryptResponseVo responseVo;
            ArrayList<String> uniqueTokens = new ArrayList<String>(tokens);
            int counter = 0;
            int chunkSize = 16;
            ArrayList<String> tokenList = new ArrayList<String>(chunkSize);
            if (uniqueTokens.size() <= chunkSize) {
                responseVo = this.callDecrypt(uniqueTokens);
                map.putAll(responseVo.getCardNumbers());
            } else {
                for (String cards : uniqueTokens) {
                    tokenList.add(cards);
                    if (++counter != chunkSize) continue;
                    DecryptResponseVo responseVo2 = this.callDecrypt(tokenList);
                    map.putAll(responseVo2.getCardNumbers());
                    counter = 0;
                    tokenList.clear();
                }
            }
            if (!tokenList.isEmpty()) {
                responseVo = this.callDecrypt(tokenList);
                map.putAll(responseVo.getCardNumbers());
            }
            response.setCardNumbers(map);
        }
        catch (Exception e) {
            log.error("Error getCardNumber() :", (Throwable)e);
            response = null;
        }
        log.info("getCardNumber ended.");
        return response;
    }

    public EncryptResponseVo callEncrypt(List<String> cardNumbers) {
        EncryptResponseVo responseVo;
        log.info("callEncrypt started...");
        try {
            String url = Objects.requireNonNull(this.env.getProperty("encUrl"), "The property encUrl not found!");
            String bankId = Objects.requireNonNull(this.env.getProperty(BANK_ID), "The property bankId not found!");
            String accessToken = Objects.requireNonNull(this.env.getProperty(ACCESS_TOKEN), "The property accessToken not found!");
            String username = Objects.requireNonNull(this.env.getProperty("cryptUserName"), "The property cryptUserName not found!");
            String password = Objects.requireNonNull(this.env.getProperty("cryptPassword"), "The property cryptPassword not found!");
            String apiId = Objects.requireNonNull(this.env.getProperty("cryptAppIdEncryption"), "The property cryptAppIdEncryption not found!");
            String clientId = Objects.requireNonNull(this.env.getProperty("cryptClientId"), "The property cryptClientId not found!");
            HashMap<String, Object> body = new HashMap<String, Object>();
            body.put(BANK_ID, bankId);
            body.put(ACCESS_TOKEN, accessToken);
            body.put("cardNumbers", cardNumbers);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            String auth = username + ":" + password;
            String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
            headers.set("Authorization", "Basic " + encodedAuth);
            headers.set("apiId", apiId);
            headers.set("clientId", clientId);
            HttpEntity requestEntity = new HttpEntity(body, (MultiValueMap)headers);
            ResponseEntity response = this.restTemplate.exchange(url, HttpMethod.POST, requestEntity, EncryptResponseVo.class, new Object[0]);
            responseVo = (EncryptResponseVo)response.getBody();
            log.info("callEncrypt response code : {}", (Object)response.getStatusCode());
        }
        catch (Exception e) {
            log.error("Error callEncrypt() :", (Throwable)e);
            responseVo = null;
        }
        log.info("callEncrypt ended.");
        return responseVo;
    }

    private DecryptResponseVo callDecrypt(List<String> token) {
        DecryptResponseVo responseVo;
        log.info("callDecrypt started...");
        try {
            String url = Objects.requireNonNull(this.env.getProperty("decUrl"), "The property decUrl not found!");
            String bankId = Objects.requireNonNull(this.env.getProperty(BANK_ID), "The property bankId not found!");
            String accessToken = Objects.requireNonNull(this.env.getProperty(ACCESS_TOKEN), "The property accessToken not found!");
            String username = Objects.requireNonNull(this.env.getProperty("cryptUserName"), "The property cryptUserName not found!");
            String password = Objects.requireNonNull(this.env.getProperty("cryptPassword"), "The property cryptPassword not found!");
            String apiId = Objects.requireNonNull(this.env.getProperty("cryptAppIdDecryption"), "The property cryptAppIdDecryption not found!");
            String clientId = Objects.requireNonNull(this.env.getProperty("cryptClientId"), "The property cryptClientId not found!");
            HashMap<String, Object> body = new HashMap<String, Object>();
            body.put(BANK_ID, bankId);
            body.put(ACCESS_TOKEN, accessToken);
            body.put("uuids", token);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            String auth = username + ":" + password;
            String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
            headers.set("Authorization", "Basic " + encodedAuth);
            headers.set("apiId", apiId);
            headers.set("clientId", clientId);
            HttpEntity requestEntity = new HttpEntity(body, (MultiValueMap)headers);
            ResponseEntity response = this.restTemplate.exchange(url, HttpMethod.POST, requestEntity, DecryptResponseVo.class, new Object[0]);
            responseVo = (DecryptResponseVo)response.getBody();
            log.info("getCardNumber response code : {}", (Object)response.getStatusCode());
        }
        catch (Exception e) {
            log.error("Error getCardNumber() :", (Throwable)e);
            responseVo = null;
        }
        log.info("callDecrypt ended.");
        return responseVo;
    }

    public CryptAPI(Environment env, RestTemplate restTemplate) {
        this.env = env;
        this.restTemplate = restTemplate;
    }
}

