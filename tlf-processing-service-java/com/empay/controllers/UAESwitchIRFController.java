// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.controllers;

import org.apache.logging.log4j.LogManager;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.empay.tlfprocessing.vo.UAESwitchIRFVo;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.HttpStatus;
import java.util.HashMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;
import java.util.Map;
import com.empay.services.UAESwitchIRFService;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({ "/ConfigureUAEIRF/" })
public class UAESwitchIRFController
{
    private static final Logger log;
    private final UAESwitchIRFService service;
    
    @DeleteMapping(value = { "/v1/deleteUAEIrf" }, produces = { "application/json" })
    public ResponseEntity<Map<String, String>> deleteUAEIrf(@Valid @RequestBody final Map<String, String> request) {
        final String mccCode = request.get("mcc");
        Map<String, String> response = new HashMap<String, String>();
        response = this.service.deleteByMcc(mccCode);
        if (!response.containsKey("error")) {
            UAESwitchIRFController.log.debug("responseBody {}", (Object)response);
            return (ResponseEntity<Map<String, String>>)ResponseEntity.status((HttpStatusCode)HttpStatus.OK).body((Object)response);
        }
        final String errorMsg = response.get("error");
        if (errorMsg.contains("does not exist")) {
            UAESwitchIRFController.log.debug("responseBody {}", (Object)response);
            return (ResponseEntity<Map<String, String>>)ResponseEntity.status((HttpStatusCode)HttpStatus.NOT_FOUND).body((Object)response);
        }
        UAESwitchIRFController.log.debug("responseBody {}", (Object)response);
        return (ResponseEntity<Map<String, String>>)ResponseEntity.status((HttpStatusCode)HttpStatus.BAD_REQUEST).body((Object)response);
    }
    
    @PostMapping(value = { "/v1/insertUAEIrf" }, produces = { "application/json" })
    public ResponseEntity<Map<String, String>> insertUAEIrf(@Valid @RequestBody final UAESwitchIRFVo request) {
        final Map<String, String> response = this.service.insertUaeIrf(request);
        if (response.containsKey("error")) {
            UAESwitchIRFController.log.debug("responseBody {}", (Object)response);
            return (ResponseEntity<Map<String, String>>)ResponseEntity.status((HttpStatusCode)HttpStatus.BAD_REQUEST).body((Object)response);
        }
        UAESwitchIRFController.log.debug("responseBody {}", (Object)response);
        return (ResponseEntity<Map<String, String>>)ResponseEntity.status((HttpStatusCode)HttpStatus.OK).body((Object)response);
    }
    
    @PutMapping(value = { "/v1/updateUAEIRF" }, produces = { "application/json" })
    public ResponseEntity<Map<String, String>> updateUaeIrf(@Valid @RequestBody final UAESwitchIRFVo request) {
        final Map<String, String> response = this.service.updateUaeIrf(request);
        if (!"error".equals(response.get("status"))) {
            UAESwitchIRFController.log.debug("responseBody {}", (Object)response);
            return (ResponseEntity<Map<String, String>>)ResponseEntity.status((HttpStatusCode)HttpStatus.OK).body((Object)response);
        }
        if (response.get("message").contains("not found")) {
            UAESwitchIRFController.log.debug("responseBody {}", (Object)response);
            return (ResponseEntity<Map<String, String>>)ResponseEntity.status((HttpStatusCode)HttpStatus.NOT_FOUND).body((Object)response);
        }
        UAESwitchIRFController.log.debug("responseBody {}", (Object)response);
        return (ResponseEntity<Map<String, String>>)ResponseEntity.status((HttpStatusCode)HttpStatus.BAD_REQUEST).body((Object)response);
    }
    
    public UAESwitchIRFController(final UAESwitchIRFService service) {
        this.service = service;
    }
    
    static {
        log = LogManager.getLogger((Class)UAESwitchIRFController.class);
    }
}
