// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.services;

import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import com.empay.entities.JaywanIRFEntity;
import java.util.HashMap;
import com.empay.tlfprocessing.vo.JaywanIRFVo;
import java.util.Map;
import com.empay.repositories.JaywanIRFRepo;
import org.springframework.stereotype.Service;

@Service
public class JaywanIRFService
{
    private final ValidationService validateService;
    private final JaywanIRFRepo jaywanIRFRepo;
    private static final Map<String, Double> IRF_MAX_BY_SEGMENT;
    
    public Map<String, String> insertJaywanIrf(final JaywanIRFVo request) {
        final Map<String, String> response = new HashMap<String, String>();
        final String segment = (request.getSegment() != null) ? request.getSegment().trim().toUpperCase() : "";
        if ("GENERAL".equalsIgnoreCase(segment)) {
            response.put("message", "Insert operation is not enabled for the segment " + segment);
            return response;
        }
        final Map<String, String> validationResponse = this.validateService.validateJaywanIrfRequest(request);
        if ("error".equalsIgnoreCase(validationResponse.get("status"))) {
            return validationResponse;
        }
        final String validMcc = (request.getMcc() != null) ? request.getMcc().trim() : "";
        if (this.jaywanIRFRepo.existsByMcc(validMcc)) {
            response.put("error", "MCC " + validMcc + " already exists in the system");
            return response;
        }
        final String segmentDesc = (request.getSegmentDesc() != null) ? request.getSegmentDesc().trim().toLowerCase() : "";
        final double irfMaxValue = JaywanIRFService.IRF_MAX_BY_SEGMENT.getOrDefault(segmentDesc, request.getIrfMax());
        final JaywanIRFEntity entity = JaywanIRFEntity.builder().segment(segment).segmentDesc(request.getSegmentDesc()).mcc(validMcc).mccDesc(request.getMccDescription()).irfRate(request.getIrfRate()).irfMax(irfMaxValue).irfFixed(request.getIrfFixed()).posIrf(0.0).ecomIrf(0.0).posIrfMax(0.0).ecomIrfMax(0.0).lastUpdated(LocalDateTime.now()).build();
        this.jaywanIRFRepo.save((Object)entity);
        response.put("message", "Insert operation successful for MCC: " + validMcc);
        return response;
    }
    
    public Map<String, String> updateJaywanIrf(final JaywanIRFVo request) {
        final Map<String, String> response = this.validateService.validateJaywanIrfRequest(request);
        if ("error".equalsIgnoreCase(response.get("status"))) {
            return response;
        }
        final String upperCase;
        final String segment = upperCase = request.getSegment().trim().toUpperCase();
        return switch (upperCase) {
            case "SPECIAL" -> {
                final String mcc = request.getMcc().trim();
                final String segmentDesc = (request.getSegmentDesc() != null) ? request.getSegmentDesc().trim().toLowerCase() : "";
                final double irfMax = JaywanIRFService.IRF_MAX_BY_SEGMENT.getOrDefault(segmentDesc, request.getIrfMax());
                final JaywanIRFEntity entity = this.jaywanIRFRepo.findBySegmentAndMcc(segment, mcc).orElse(null);
                if (entity == null) {
                    yield this.error("MCC " + mcc + " with segment " + segment + " not found");
                }
                entity.setSegmentDesc(request.getSegmentDesc());
                entity.setMccDesc(request.getMccDescription());
                entity.setIrfRate(request.getIrfRate());
                entity.setIrfMax(irfMax);
                entity.setIrfFixed(request.getIrfFixed());
                entity.setLastUpdated(LocalDateTime.now());
                this.jaywanIRFRepo.save((Object)entity);
                yield this.success("Update successful for MCC " + mcc);
            }
            case "GENERAL" -> {
                final JaywanIRFEntity entity2 = this.jaywanIRFRepo.findBySegment(segment).orElse(null);
                if (entity2 == null) {
                    yield this.error("Segment " + segment + " not found");
                }
                entity2.setSegmentDesc(request.getSegmentDesc());
                entity2.setPosIrf(request.getPosIrf());
                entity2.setEcomIrf(request.getEcomIrf());
                entity2.setPosIrfMax(request.getPosIrfMax());
                entity2.setEcomIrfMax(request.getEcomIrfMax());
                entity2.setLastUpdated(LocalDateTime.now());
                this.jaywanIRFRepo.save((Object)entity2);
                yield this.success("Update successful for segment " + segment);
            }
            default -> this.error("Invalid segment. Allowed values are SPECIAL or GENERAL");
        };
    }
    
    private Map<String, String> success(final String message) {
        return Map.of("status", "success", "message", message);
    }
    
    private Map<String, String> error(final String message) {
        return Map.of("status", "error", "message", message);
    }
    
    @Transactional
    public Map<String, String> deleteByMcc(final String mccCode) {
        Map<String, String> response = new HashMap<String, String>();
        final String mcc = mccCode.trim();
        response = this.validateService.validateMcc(mcc, 4);
        if (response.containsKey("error")) {
            return response;
        }
        if (!this.jaywanIRFRepo.existsByMcc(mcc)) {
            response.put("error", "MCC " + mcc + " does not exist in the system");
            return response;
        }
        this.jaywanIRFRepo.deleteByMcc(mcc);
        response.put("message", "Delete operation successful for MCC: " + mcc);
        return response;
    }
    
    public JaywanIRFService(final ValidationService validateService, final JaywanIRFRepo jaywanIRFRepo) {
        this.validateService = validateService;
        this.jaywanIRFRepo = jaywanIRFRepo;
    }
    
    static {
        IRF_MAX_BY_SEGMENT = Map.of("government and utilities", 25.0, "transport", 25.0, "petrol", 25.0, "education", 32.5, "real estate", 32.5, "charity", 1.0);
    }
}
