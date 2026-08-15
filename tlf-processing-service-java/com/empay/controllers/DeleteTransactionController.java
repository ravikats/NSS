// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.controllers;

import org.apache.logging.log4j.LogManager;
import org.springframework.web.bind.annotation.DeleteMapping;
import com.google.gson.JsonObject;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.HttpStatus;
import com.google.gson.JsonParser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;
import com.empay.tlfprocessing.vo.DeleteTxnVo;
import com.empay.services.ValidationService;
import com.empay.services.DeleteTransactionService;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({ "/tlf/" })
public class DeleteTransactionController
{
    private static final Logger log;
    private final DeleteTransactionService service;
    private final ValidationService validateService;
    
    @PostMapping(value = { "/v1/deleteTxn" }, produces = { "application/json" })
    public Map<String, String> deleteTxn(@Valid @RequestBody final DeleteTxnVo inputVo) {
        return this.service.deleteTxn(inputVo);
    }
    
    @DeleteMapping(value = { "/v1/deleteTxnFile" }, produces = { "application/json" })
    public ResponseEntity<Map<String, String>> deleteTxnFile(@Valid @RequestBody final String jsonString) {
        String fileName = null;
        final JsonObject inputJson = JsonParser.parseString(jsonString).getAsJsonObject();
        if (inputJson.has("fileName")) {
            fileName = inputJson.get("fileName").getAsString();
        }
        this.validateService.validateLength(fileName, 60);
        final Map<String, String> responseBody = this.service.deleteTxnFile(fileName);
        DeleteTransactionController.log.debug("responseBody {}", (Object)responseBody);
        return (ResponseEntity<Map<String, String>>)ResponseEntity.status((HttpStatusCode)HttpStatus.OK).body((Object)responseBody);
    }
    
    public DeleteTransactionController(final DeleteTransactionService service, final ValidationService validateService) {
        this.service = service;
        this.validateService = validateService;
    }
    
    static {
        log = LogManager.getLogger((Class)DeleteTransactionController.class);
    }
}
