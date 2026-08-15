// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.services;

import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import com.empay.entities.MercuryIRFRateEntity;
import java.util.HashMap;
import java.util.Map;
import jakarta.validation.Valid;
import com.empay.tlfprocessing.vo.MercuryIRFVo;
import com.empay.repositories.MercuryIRFRepo;
import org.springframework.stereotype.Service;

@Service
public class MercuryIRFService
{
    private final ValidationService validateService;
    private final MercuryIRFRepo mercuryIRFRepo;
    
    public Map<String, String> insertMercuryIrf(@Valid final MercuryIRFVo request) {
        final Map<String, String> response = new HashMap<String, String>();
        final Map<String, String> validationResponse = this.validateService.validateMercuryIrfRequest(request);
        if ("error".equalsIgnoreCase(validationResponse.get("status"))) {
            return validationResponse;
        }
        final String validMcc = (request.getMcc() != null) ? request.getMcc().trim() : "";
        if (this.mercuryIRFRepo.existsByMcc(validMcc)) {
            response.put("error", "MCC " + validMcc + " already exists in the system");
            return response;
        }
        final MercuryIRFRateEntity entity = MercuryIRFRateEntity.builder().mcc(validMcc).mccDesc(request.getMccDescription()).irfPercent(request.getIrfPercentage()).irfFixed(request.getIrfFixed()).lastUpdated(LocalDateTime.now()).build();
        this.mercuryIRFRepo.save((Object)entity);
        response.put("message", "Insert operation successful for MCC: " + validMcc);
        return response;
    }
    
    public Map<String, String> updateMercuryIrf(final MercuryIRFVo request) {
        final Map<String, String> response = this.validateService.validateMercuryIrfRequest(request);
        if ("error".equalsIgnoreCase(response.get("status"))) {
            return response;
        }
        final String mcc = request.getMcc();
        final MercuryIRFRateEntity entity = this.mercuryIRFRepo.findByMcc(mcc);
        if (entity == null) {
            return this.error("MCC " + mcc + " not found");
        }
        entity.setMccDesc(request.getMccDescription());
        entity.setIrfPercent(request.getIrfPercentage());
        entity.setIrfFixed(request.getIrfFixed());
        entity.setLastUpdated(LocalDateTime.now());
        this.mercuryIRFRepo.save((Object)entity);
        return this.success("Update successful for MCC " + mcc);
    }
    
    private Map<String, String> error(final String message) {
        return Map.of("status", "error", "message", message);
    }
    
    private Map<String, String> success(final String message) {
        return Map.of("status", "success", "message", message);
    }
    
    @Transactional
    public Map<String, String> deleteByMcc(final String mccCode) {
        Map<String, String> response = new HashMap<String, String>();
        final String mcc = mccCode.trim();
        response = this.validateService.validateMcc(mcc, 4);
        if (response.containsKey("error")) {
            return response;
        }
        if (!this.mercuryIRFRepo.existsByMcc(mcc)) {
            response.put("error", "MCC " + mcc + " does not exist in the system");
            return response;
        }
        this.mercuryIRFRepo.deleteByMcc(mcc);
        response.put("message", "Delete operation successful for MCC: " + mcc);
        return response;
    }
    
    public MercuryIRFService(final ValidationService validateService, final MercuryIRFRepo mercuryIRFRepo) {
        this.validateService = validateService;
        this.mercuryIRFRepo = mercuryIRFRepo;
    }
}
