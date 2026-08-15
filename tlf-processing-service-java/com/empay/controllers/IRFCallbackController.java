// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import com.empay.services.IRFCallbackService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({ "/tlf" })
public class IRFCallbackController
{
    private final IRFCallbackService service;
    
    @GetMapping(value = { "/v1/retryIRFCallback" }, produces = { "application/json" })
    public ResponseEntity<Map<String, String>> retryIRFRequest(@RequestParam final Integer refCode) {
        return this.service.retryIRFRequest(refCode);
    }
    
    @GetMapping(value = { "/v1/getIRFCallbackFailed" }, produces = { "application/json" })
    public ResponseEntity<Object> fetchFailedData() {
        return this.service.fetchFailedData();
    }
    
    public IRFCallbackController(final IRFCallbackService service) {
        this.service = service;
    }
}
