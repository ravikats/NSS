// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.controllers;

import org.apache.logging.log4j.LogManager;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import com.empay.tlfprocessing.vo.RtsResponseVo;
import java.util.List;
import java.util.Collections;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.HttpStatus;
import com.empay.exceptions.UnauthorizedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;
import com.empay.tlfprocessing.vo.RtsRequestVo;
import org.springframework.web.bind.annotation.RequestHeader;
import com.empay.services.ValidationService;
import com.empay.services.RtsTxnService;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({ "/rts/" })
public class RealTimeSettlementController
{
    private static final Logger log;
    private final RtsTxnService rtsTxnService;
    private final ValidationService validateService;
    
    @GetMapping(value = { "/v1/RtsResponse" }, produces = { "application/json" })
    public ResponseEntity<?> processRtsResponse(@RequestHeader("Authorization") final String authorization, @Valid @RequestBody final RtsRequestVo rstRequestVo) {
        RealTimeSettlementController.log.info("Received RTS request: {}", (Object)rstRequestVo);
        if (!this.validateService.isValidAuth(authorization)) {
            throw new UnauthorizedException("The request was unauthorized.");
        }
        final List<RtsResponseVo> responseList = this.rtsTxnService.rtsTxnMapping(rstRequestVo);
        if (responseList.isEmpty()) {
            return (ResponseEntity<?>)ResponseEntity.status((HttpStatusCode)HttpStatus.NOT_FOUND).body((Object)Collections.singletonMap("message", "No History Found"));
        }
        return (ResponseEntity<?>)ResponseEntity.ok((Object)responseList);
    }
    
    @PostMapping({ "/v1/RtsRetry" })
    public Map<String, String> retryRtsRequest(@RequestHeader("Authorization") final String authorization, @Valid @RequestBody final RtsRequestVo rstRequestVo) {
        RealTimeSettlementController.log.info("RTS Retry request: {}", (Object)rstRequestVo);
        if (!this.validateService.isValidAuth(authorization)) {
            throw new UnauthorizedException("The request was unauthorized.");
        }
        return this.rtsTxnService.retryRtsRequest(rstRequestVo);
    }
    
    public RealTimeSettlementController(final RtsTxnService rtsTxnService, final ValidationService validateService) {
        this.rtsTxnService = rtsTxnService;
        this.validateService = validateService;
    }
    
    static {
        log = LogManager.getLogger((Class)RealTimeSettlementController.class);
    }
}
