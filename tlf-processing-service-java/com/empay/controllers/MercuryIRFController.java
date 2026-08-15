// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.controllers;

import org.apache.logging.log4j.LogManager;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.HttpStatus;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;
import com.empay.tlfprocessing.vo.MercuryIRFVo;
import com.empay.services.MercuryIRFService;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({ "/ConfigureMercuryIRF/" })
public class MercuryIRFController
{
    private static final Logger log;
    private final MercuryIRFService service;
    
    @PostMapping(value = { "/v1/insertMercuryIrf" }, produces = { "application/json" })
    public ResponseEntity<Map<String, String>> insertMercuryIrf(@Valid @RequestBody final MercuryIRFVo request) {
        final Map<String, String> response = this.service.insertMercuryIrf(request);
        MercuryIRFController.log.debug("responseBody {}", (Object)response);
        if (response.containsKey("error")) {
            return (ResponseEntity<Map<String, String>>)ResponseEntity.status((HttpStatusCode)HttpStatus.BAD_REQUEST).body((Object)response);
        }
        return (ResponseEntity<Map<String, String>>)ResponseEntity.status((HttpStatusCode)HttpStatus.OK).body((Object)response);
    }
    
    @PutMapping(value = { "/v1/updateMercuryIrf" }, produces = { "application/json" })
    public ResponseEntity<Map<String, String>> updateJaywanIrf(@Valid @RequestBody final MercuryIRFVo request) {
        final Map<String, String> response = this.service.updateMercuryIrf(request);
        MercuryIRFController.log.debug("responseBody {}", (Object)response);
        final String status = response.get("status");
        final String message = response.get("message");
        if ("error".equalsIgnoreCase(status)) {
            final HttpStatus httpStatus = (message != null && message.toLowerCase().contains("not found")) ? HttpStatus.NOT_FOUND : HttpStatus.BAD_REQUEST;
            return (ResponseEntity<Map<String, String>>)ResponseEntity.status((HttpStatusCode)httpStatus).body((Object)response);
        }
        return (ResponseEntity<Map<String, String>>)ResponseEntity.status((HttpStatusCode)HttpStatus.OK).body((Object)response);
    }
    
    @DeleteMapping(value = { "/v1/deleteMercuryIrf" }, produces = { "application/json" })
    public ResponseEntity<Map<String, String>> deleteMercuryIrf(@Valid @RequestBody final Map<String, String> request) {
        final String mcc = request.get("mcc");
        final Map<String, String> response = this.service.deleteByMcc(mcc);
        MercuryIRFController.log.debug("responseBody {}", (Object)response);
        final String error = response.get("error");
        if (error != null) {
            final HttpStatus status = error.toLowerCase().contains("does not exist") ? HttpStatus.NOT_FOUND : HttpStatus.BAD_REQUEST;
            return (ResponseEntity<Map<String, String>>)ResponseEntity.status((HttpStatusCode)status).body((Object)response);
        }
        return (ResponseEntity<Map<String, String>>)ResponseEntity.ok((Object)response);
    }
    
    public MercuryIRFController(final MercuryIRFService service) {
        this.service = service;
    }
    
    static {
        log = LogManager.getLogger((Class)MercuryIRFController.class);
    }
}
