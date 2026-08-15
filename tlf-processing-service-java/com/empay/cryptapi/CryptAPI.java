// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.cryptapi;

import org.apache.logging.log4j.LogManager;
import org.springframework.http.ResponseEntity;
import java.util.Map;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.http.HttpEntity;
import java.util.Base64;
import org.springframework.http.MediaType;
import org.springframework.http.HttpHeaders;
import java.util.HashMap;
import java.util.List;
import org.springframework.web.client.RestTemplate;
import org.springframework.core.env.Environment;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class CryptAPI
{
    private static final Logger log;
    private final Environment env;
    private final RestTemplate restTemplate;
    
    public DecryptResponseVo getCardNumber(final List<String> token) {
        DecryptResponseVo responseVo;
        try {
            final String url = this.env.getProperty("decUrl");
            final String bankId = this.env.getProperty("bankId");
            final String accessToken = this.env.getProperty("accessToken");
            final String username = this.env.getProperty("cryptUserName");
            final String password = this.env.getProperty("cryptPassword");
            final String apiId = this.env.getProperty("cryptAppIdDecryption");
            final String clientId = this.env.getProperty("cryptClientId");
            final Map<String, Object> body = new HashMap<String, Object>();
            body.put("bankId", bankId);
            body.put("accessToken", accessToken);
            body.put("uuids", token);
            final HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            final String auth = username + ":" + password;
            final String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
            headers.set("Authorization", "Basic " + encodedAuth);
            headers.set("apiId", apiId);
            headers.set("clientId", clientId);
            final HttpEntity<Map<String, Object>> requestEntity = (HttpEntity<Map<String, Object>>)new HttpEntity((Object)body, (MultiValueMap)headers);
            CryptAPI.log.info("getCardNumber request :{}", (Object)requestEntity);
            assert url != null;
            final ResponseEntity<DecryptResponseVo> response = (ResponseEntity<DecryptResponseVo>)this.restTemplate.exchange(url, HttpMethod.POST, (HttpEntity)requestEntity, (Class)DecryptResponseVo.class, new Object[0]);
            responseVo = (DecryptResponseVo)response.getBody();
            CryptAPI.log.info("getCardNumber response code : {}", (Object)response.getStatusCode());
        }
        catch (final Exception e) {
            CryptAPI.log.error("CryptAPI Decryption Error:", (Throwable)e);
            responseVo = null;
        }
        return responseVo;
    }
    
    public CryptAPI(final Environment env, final RestTemplate restTemplate) {
        this.env = env;
        this.restTemplate = restTemplate;
    }
    
    static {
        log = LogManager.getLogger((Class)CryptAPI.class);
    }
}
