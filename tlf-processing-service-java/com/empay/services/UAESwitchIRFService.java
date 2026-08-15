// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.services;

import java.time.LocalDateTime;
import com.empay.entities.UAESwitchIRFEntity;
import com.empay.tlfprocessing.vo.UAESwitchIRFVo;
import jakarta.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;
import com.empay.repositories.UAESwitchIRFRepo;
import org.springframework.stereotype.Service;

@Service
public class UAESwitchIRFService
{
    private final UAESwitchIRFRepo uaeSwitchIRFRepo;
    private final ValidationService validateService;
    private static final Map<String, Double> IRF_MAX_BY_SEGMENT;
    
    @Transactional
    public Map<String, String> deleteByMcc(final String mccCode) {
        Map<String, String> response = new HashMap<String, String>();
        final String mcc = mccCode.trim();
        response = this.validateService.validateMcc(mcc, 4);
        if (response.containsKey("error")) {
            return response;
        }
        if (!this.uaeSwitchIRFRepo.existsByMcc(mcc)) {
            response.put("error", "MCC " + mcc + " does not exist in the system");
            return response;
        }
        this.uaeSwitchIRFRepo.deleteByMcc(mcc);
        response.put("message", "Delete operation successful for MCC: " + mcc);
        return response;
    }
    
    public Map<String, String> insertUaeIrf(final UAESwitchIRFVo request) {
        Map<String, String> response = new HashMap<String, String>();
        final String segment = (request.getSegment() != null) ? request.getSegment().trim().toUpperCase() : "";
        if (segment.equalsIgnoreCase("GENERAL")) {
            response.put("message", "Insert operation is not enabled for the segment " + segment);
            return response;
        }
        response = this.validateService.validateUaeIrfRequest(request);
        if ("error".equals(response.get("status"))) {
            return response;
        }
        final String validMcc = request.getMcc().trim();
        if (this.uaeSwitchIRFRepo.existsByMcc(validMcc)) {
            response.put("error", "MCC " + validMcc + " already exists in the system");
            return response;
        }
        final String segmentDesc = (request.getSegmentDesc() != null) ? request.getSegmentDesc().trim().toLowerCase() : "";
        final double irfMaxValue = UAESwitchIRFService.IRF_MAX_BY_SEGMENT.getOrDefault(segmentDesc, request.getIrfMax());
        final UAESwitchIRFEntity entity = UAESwitchIRFEntity.builder().segment(segment).segmentDesc(request.getSegmentDesc()).mcc(validMcc).mccDesc(request.getMccDescription()).irfRate(request.getIrfRate()).irfMax(irfMaxValue).irfFixed(request.getIrfFixed()).posIrf(0.0).ecomIrf(0.0).posIrfMax(0.0).ecomIrfMax(0.0).lastUpdated(LocalDateTime.now()).build();
        this.uaeSwitchIRFRepo.save((Object)entity);
        response.put("message", "Insert operation successful for MCC: " + validMcc);
        return response;
    }
    
    public Map<String, String> updateUaeIrf(final UAESwitchIRFVo request) {
        Map<String, String> response = new HashMap<String, String>();
        response = this.validateService.validateUaeIrfRequest(request);
        if ("error".equals(response.get("status"))) {
            return response;
        }
        final String segment = request.getSegment().trim().toUpperCase();
        if ("SPECIAL".equalsIgnoreCase(segment)) {
            final String mccCode = request.getMcc().trim();
            final String segmentDesc = (request.getSegmentDesc() != null) ? request.getSegmentDesc().trim().toLowerCase() : "";
            final double irfMaxValue = UAESwitchIRFService.IRF_MAX_BY_SEGMENT.getOrDefault(segmentDesc, request.getIrfMax());
            final UAESwitchIRFEntity entity = this.uaeSwitchIRFRepo.findBySegmentAndMcc(segment, mccCode).orElse(null);
            if (entity == null) {
                response.put("status", "error");
                response.put("message", "MCC " + mccCode + " with segment " + segment + " not found");
                return response;
            }
            entity.setSegmentDesc(request.getSegmentDesc());
            entity.setMccDesc(request.getMccDescription());
            entity.setIrfRate(request.getIrfRate());
            entity.setIrfMax(irfMaxValue);
            entity.setIrfFixed(request.getIrfFixed());
            entity.setLastUpdated(LocalDateTime.now());
            this.uaeSwitchIRFRepo.save((Object)entity);
            response.put("status", "success");
            response.put("message", "Update operation successful for MCC " + mccCode + " in segment " + segment);
            return response;
        }
        else {
            if (!"GENERAL".equalsIgnoreCase(segment)) {
                response.put("status", "error");
                response.put("message", "Invalid segment. Allowed values are SPECIAL or GENERAL");
                return response;
            }
            final UAESwitchIRFEntity entity2 = this.uaeSwitchIRFRepo.findBySegment(segment).orElse(null);
            if (entity2 == null) {
                response.put("status", "error");
                response.put("message", "Segment " + segment + " not found");
                return response;
            }
            entity2.setSegmentDesc(request.getSegmentDesc());
            entity2.setPosIrf(request.getPosIrf());
            entity2.setEcomIrf(request.getEcomIrf());
            entity2.setPosIrfMax(request.getPosIrfMax());
            entity2.setEcomIrfMax(request.getEcomIrfMax());
            entity2.setLastUpdated(LocalDateTime.now());
            this.uaeSwitchIRFRepo.save((Object)entity2);
            response.put("status", "success");
            response.put("message", "Update operation successful for segment: " + segment);
            return response;
        }
    }
    
    public UAESwitchIRFService(final UAESwitchIRFRepo uaeSwitchIRFRepo, final ValidationService validateService) {
        this.uaeSwitchIRFRepo = uaeSwitchIRFRepo;
        this.validateService = validateService;
    }
    
    static {
        IRF_MAX_BY_SEGMENT = Map.of("government and utilities", 25.0, "transport", 25.0, "petrol", 25.0, "education", 32.5, "real estate", 32.5, "charity", 1.0);
    }
}
