// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.services;

import java.util.Optional;
import java.time.LocalDateTime;
import com.empay.entities.OmanNetIRFEntity;
import java.util.HashMap;
import java.util.Map;
import com.empay.tlfprocessing.vo.OmanNetIrfVo;
import com.empay.repositories.OmanNetIRFRepo;
import org.springframework.stereotype.Service;

@Service
public class OmanNetIRFService
{
    private final ValidationService validateService;
    private final OmanNetIRFRepo omanNetIRFRepo;
    
    public Map<String, String> insertOmanNetIrfConfig(final OmanNetIrfVo request) {
        Map<String, String> response = new HashMap<String, String>();
        response = this.validateService.validateOmanNetIrfRequest(request, false);
        if ("error".equals(response.get("status"))) {
            return response;
        }
        if (this.omanNetIRFRepo.existsByMccAndRouteAndSubRouteAndCardType(request.getMcc().trim(), request.getRoute(), request.getSubRoute(), request.getCardType().charAt(0))) {
            response.put("error", "MCC " + request.getMcc() + " already exists in the system");
            return response;
        }
        final OmanNetIRFEntity entity = OmanNetIRFEntity.builder().cardType(request.getCardType().charAt(0)).segment(request.getSegment()).segmentDesc(request.getSegmentDesc()).mcc(request.getMcc()).mccDesc(request.getMccDescription()).irfPercentage(request.getIrfRatePercent()).irfMax(request.getIrfMax()).irfFixed(request.getIrfFixed()).lastUpdated(LocalDateTime.now()).subRoute(request.getSubRoute()).route(request.getRoute()).build();
        this.omanNetIRFRepo.saveAndFlush((Object)entity);
        response.put("message", "Insert operation successful for MCC: " + request.getMcc());
        return response;
    }
    
    public Map<String, String> updateOmanNetIrfConfig(final OmanNetIrfVo request) {
        Map<String, String> response = new HashMap<String, String>();
        response = this.validateService.validateOmanNetIrfRequest(request, true);
        if ("error".equals(response.get("status"))) {
            return response;
        }
        final Optional<OmanNetIRFEntity> entity = this.omanNetIRFRepo.findById((Object)request.getReferenceNumber());
        if (entity.isPresent()) {
            this.omanNetIRFRepo.saveAndFlush((Object)entity.get().toBuilder().cardType(request.getCardType().charAt(0)).segment(request.getSegment()).segmentDesc(request.getSegmentDesc()).mcc(request.getMcc()).mccDesc(request.getMccDescription()).irfPercentage(request.getIrfRatePercent()).irfMax(request.getIrfMax()).irfFixed(request.getIrfFixed()).lastUpdated(LocalDateTime.now()).build());
            response.put("message", "Update operation successful for MCC: " + request.getMcc());
            return response;
        }
        response.put("error", "Failed to update, Data not available in the system");
        return response;
    }
    
    public Map<String, String> deleteOmanNetIrfConfig(final Map<String, String> request) {
        final Map<String, String> response = new HashMap<String, String>();
        if (request.containsKey("referenceNumber")) {
            final Integer referenceNumber = Integer.parseInt(request.get("referenceNumber"));
            final Optional<OmanNetIRFEntity> entity = this.omanNetIRFRepo.findById((Object)referenceNumber);
            if (entity.isPresent()) {
                this.omanNetIRFRepo.deleteById((Object)referenceNumber);
                response.put("message", "Delete operation successful for Reference Number: " + referenceNumber);
            }
            else {
                response.put("error", "Failed to delete, Data not available in the system");
            }
        }
        else {
            response.put("error", "Reference Number is mandatory for delete");
        }
        return response;
    }
    
    public OmanNetIRFService(final ValidationService validateService, final OmanNetIRFRepo omanNetIRFRepo) {
        this.validateService = validateService;
        this.omanNetIRFRepo = omanNetIRFRepo;
    }
}
