/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.split.controller.OutGoingController
 *  com.empay.staging.service.CollectionOnlyProcessingService
 *  com.empay.staging.service.MCOutgoingService
 *  com.empay.staging.service.OutGoingProcessingService
 *  com.empay.staging.service.OutgoingUpdateService
 *  com.empay.vo.CollectionOnlyRequestVo
 *  com.empay.vo.OutGoingRequestVo
 *  com.empay.vo.RejectedTxnUpdateRequestVo
 *  jakarta.validation.Valid
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.core.env.Environment
 *  org.springframework.http.HttpStatus
 *  org.springframework.http.HttpStatusCode
 *  org.springframework.http.ResponseEntity
 *  org.springframework.web.bind.annotation.PostMapping
 *  org.springframework.web.bind.annotation.PutMapping
 *  org.springframework.web.bind.annotation.RequestBody
 *  org.springframework.web.bind.annotation.RequestMapping
 *  org.springframework.web.bind.annotation.RestController
 */
package com.empay.split.controller;

import com.empay.staging.service.CollectionOnlyProcessingService;
import com.empay.staging.service.MCOutgoingService;
import com.empay.staging.service.OutGoingProcessingService;
import com.empay.staging.service.OutgoingUpdateService;
import com.empay.vo.CollectionOnlyRequestVo;
import com.empay.vo.OutGoingRequestVo;
import com.empay.vo.RejectedTxnUpdateRequestVo;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value={"/outgoing/"})
@RestController
public class OutGoingController {
    @Autowired
    MCOutgoingService mcOutGoing;
    @Autowired
    OutGoingProcessingService outGoingProcessing;
    @Autowired
    Environment env;
    @Autowired
    OutgoingUpdateService outgoingUpdateService;
    @Autowired
    CollectionOnlyProcessingService collectionOnlyService;

    @PostMapping(value={"/v1/generateOutgoing"}, produces={"application/json"})
    public ResponseEntity<Map<String, String>> generateOutGoing(@Valid @RequestBody OutGoingRequestVo outgoingRequestVo) {
        String network;
        int userSerialNumber = Integer.parseInt(Objects.requireNonNull(this.env.getProperty("UPDATED_USER")));
        int insCode = Integer.parseInt(Objects.requireNonNull(this.env.getProperty("INS_CODE")));
        String insShortName = this.env.getProperty("INS_SHORT_NAME");
        int formatCode = switch (network = outgoingRequestVo.getNetwork()) {
            case "MASTERCARD" -> Integer.parseInt(Objects.requireNonNull(this.env.getProperty("MASTERCARD_SYSTEM_CODE")));
            case "VISA" -> Integer.parseInt(Objects.requireNonNull(this.env.getProperty("VISA_SYSTEM_CODE")));
            case "JAYWAN" -> Integer.parseInt(Objects.requireNonNull(this.env.getProperty("JAYWAN_SYSTEM_CODE")));
            case "AMEX" -> Integer.parseInt(Objects.requireNonNull(this.env.getProperty("AMEX_SYSTEM_CODE")));
            default -> 0;
        };
        HashMap<String, String> responseBody = new HashMap<String, String>();
        String message = this.outGoingProcessing.processAndMoveData(outgoingRequestVo, Integer.valueOf(insCode), Integer.valueOf(userSerialNumber), Integer.valueOf(formatCode), insShortName);
        responseBody.put("message", message);
        return ResponseEntity.status((HttpStatusCode)HttpStatus.OK).body(responseBody);
    }

    @PostMapping(value={"/v1/revertLastOutgoing"}, produces={"application/json"})
    public ResponseEntity<Map<String, String>> revertOutgoing(@Valid @RequestBody OutGoingRequestVo intCategory) {
        int insCode = Integer.parseInt(Objects.requireNonNull(this.env.getProperty("INS_CODE")));
        HashMap<String, String> responseBody = new HashMap<String, String>();
        String message = this.outGoingProcessing.revertLastOutgoingData(intCategory.getNetwork(), Integer.valueOf(insCode));
        responseBody.put("message", message);
        return ResponseEntity.status((HttpStatusCode)HttpStatus.OK).body(responseBody);
    }

    @PutMapping(value={"/v1/updateRejectedData"}, produces={"application/json"})
    public ResponseEntity<Map<String, String>> updateRejectedOutGoing(@Valid @RequestBody RejectedTxnUpdateRequestVo reqVo) {
        HashMap<String, String> responseBody = new HashMap<String, String>();
        int insCode = Integer.parseInt(Objects.requireNonNull(this.env.getProperty("INS_CODE")));
        int userSerialNumber = Integer.parseInt(Objects.requireNonNull(this.env.getProperty("UPDATED_USER")));
        String message = this.outgoingUpdateService.updateOutGoingData(Integer.valueOf(insCode), Integer.valueOf(userSerialNumber), reqVo);
        responseBody.put("message", message);
        return ResponseEntity.status((HttpStatusCode)HttpStatus.OK).body(responseBody);
    }

    @PostMapping(value={"/v1/generateCollectionOnly"}, produces={"application/json"})
    public ResponseEntity<Map<String, String>> generateCollectionOnlyOutgoing(@Valid @RequestBody CollectionOnlyRequestVo requestVo) {
        String scheme;
        int userSerialNumber = Integer.parseInt(Objects.requireNonNull(this.env.getProperty("UPDATED_USER")));
        int insCode = Integer.parseInt(Objects.requireNonNull(this.env.getProperty("INS_CODE")));
        String insShortName = this.env.getProperty("INS_SHORT_NAME");
        int formatCode = switch (scheme = requestVo.getScheme()) {
            case "MASTERCARD" -> Integer.parseInt(Objects.requireNonNull(this.env.getProperty("GCO_SYSTEM_CODE")));
            case "VISA" -> Integer.parseInt(Objects.requireNonNull(this.env.getProperty("GOC_SYSTEM_CODE")));
            default -> 0;
        };
        HashMap<String, String> responseBody = new HashMap<String, String>();
        String message = this.collectionOnlyService.processAndMoveData(requestVo, Integer.valueOf(insCode), Integer.valueOf(userSerialNumber), Integer.valueOf(formatCode), insShortName);
        responseBody.put("message", message);
        return ResponseEntity.status((HttpStatusCode)HttpStatus.OK).body(responseBody);
    }

    @PostMapping(value={"/v1/revertLastCollectionOnly"}, produces={"application/json"})
    public ResponseEntity<Map<String, String>> revertCollectionOnly(@Valid @RequestBody OutGoingRequestVo request) {
        int insCode = Integer.parseInt(Objects.requireNonNull(this.env.getProperty("INS_CODE")));
        HashMap<String, String> responseBody = new HashMap<String, String>();
        String message = this.collectionOnlyService.revertLastCollectionOnlyData(request.getNetwork(), insCode);
        responseBody.put("message", message);
        return ResponseEntity.status((HttpStatusCode)HttpStatus.OK).body(responseBody);
    }
}

