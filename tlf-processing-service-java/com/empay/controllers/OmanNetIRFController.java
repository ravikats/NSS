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
import com.empay.tlfprocessing.vo.OmanNetIrfVo;
import com.empay.services.OmanNetIRFService;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({ "/ConfigureOmanNetIRF/v1" })
public class OmanNetIRFController
{
    private static final Logger log;
    private final OmanNetIRFService omanNetIRFService;
    
    @PostMapping(value = { "/insertIrfConfig" }, produces = { "application/json" })
    public ResponseEntity<Map<String, String>> insertIrfConfig(@Valid @RequestBody final OmanNetIrfVo request) {
        final Map<String, String> response = this.omanNetIRFService.insertOmanNetIrfConfig(request);
        if (response.containsKey("error")) {
            OmanNetIRFController.log.debug("responseBody {}", (Object)response);
            return (ResponseEntity<Map<String, String>>)ResponseEntity.status((HttpStatusCode)HttpStatus.BAD_REQUEST).body((Object)response);
        }
        OmanNetIRFController.log.debug("responseBody {}", (Object)response);
        return (ResponseEntity<Map<String, String>>)ResponseEntity.status((HttpStatusCode)HttpStatus.OK).body((Object)response);
    }
    
    @PutMapping(value = { "/updateIrfConfig" }, produces = { "application/json" })
    public ResponseEntity<Map<String, String>> updateIrfConfig(@Valid @RequestBody final OmanNetIrfVo request) {
        final Map<String, String> response = this.omanNetIRFService.updateOmanNetIrfConfig(request);
        if (response.containsKey("error")) {
            OmanNetIRFController.log.debug("responseBody {}", (Object)response);
            return (ResponseEntity<Map<String, String>>)ResponseEntity.status((HttpStatusCode)HttpStatus.BAD_REQUEST).body((Object)response);
        }
        OmanNetIRFController.log.debug("responseBody {}", (Object)response);
        return (ResponseEntity<Map<String, String>>)ResponseEntity.status((HttpStatusCode)HttpStatus.OK).body((Object)response);
    }
    
    @DeleteMapping(value = { "/deleteIrf" }, produces = { "application/json" })
    public ResponseEntity<Map<String, String>> deleteUAEIrf(@Valid @RequestBody final Map<String, String> request) {
        final Map<String, String> response = this.omanNetIRFService.deleteOmanNetIrfConfig(request);
        if (response.containsKey("error")) {
            OmanNetIRFController.log.debug("responseBody {}", (Object)response);
            return (ResponseEntity<Map<String, String>>)ResponseEntity.status((HttpStatusCode)HttpStatus.BAD_REQUEST).body((Object)response);
        }
        OmanNetIRFController.log.debug("responseBody {}", (Object)response);
        return (ResponseEntity<Map<String, String>>)ResponseEntity.status((HttpStatusCode)HttpStatus.OK).body((Object)response);
    }
    
    public OmanNetIRFController(final OmanNetIRFService omanNetIRFService) {
        this.omanNetIRFService = omanNetIRFService;
    }
    
    static {
        log = LogManager.getLogger((Class)OmanNetIRFController.class);
    }
}
