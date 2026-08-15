// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.services;

import org.apache.logging.log4j.LogManager;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Expression;
import java.util.ArrayList;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.lang.invoke.SerializedLambda;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.ResponseEntity;
import com.empay.entities.PosTransactionEntity;
import org.springframework.http.HttpStatus;
import java.util.HashMap;
import java.util.Map;
import jakarta.validation.Valid;
import java.util.Optional;
import com.empay.tlfprocessing.vo.RTSde48Vo;
import com.empay.entities.McRTSTxnDataEntity;
import java.util.Collections;
import com.empay.tlfprocessing.vo.RtsResponseVo;
import java.util.List;
import com.empay.tlfprocessing.vo.RtsRequestVo;
import com.empay.repositories.PosTransactionRepository;
import com.empay.repositories.McRTSTxnDataRepo;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class RtsTxnService
{
    private static final Logger log;
    private final McRTSTxnDataRepo mcRTSTxnDataRepo;
    private final TxnProcessingService txnProcessingService;
    private final PosTransactionRepository posTransactionRepo;
    
    public List<RtsResponseVo> rtsTxnMapping(final RtsRequestVo reqVo) {
        final McRTSTxnDataEntity data = this.txnDetails(reqVo);
        if (data == null) {
            RtsTxnService.log.warn("No transaction found for RRN: {}, MTI: {}, PROC: {}", (Object)reqVo.getRrn(), (Object)reqVo.getMti(), (Object)reqVo.getProcessCode());
            return Collections.emptyList();
        }
        return List.of(this.mapToRtsDetailsResponseVo(data));
    }
    
    private RtsResponseVo mapToRtsDetailsResponseVo(final McRTSTxnDataEntity data) {
        final RtsResponseVo vo = new RtsResponseVo();
        final RTSde48Vo de48 = new RTSde48Vo();
        try {
            vo.setRrn(Optional.ofNullable(data.getRrn()).orElse(""));
            vo.setMti(Optional.ofNullable(data.getMessageTypeId()).orElse(""));
            vo.setProcessCode(Optional.ofNullable(data.getProcCode()).orElse(""));
            de48.setPDS23(Optional.ofNullable(data.getTrlType()).orElse(""));
            de48.setPDS25((data.getRevIndiCator() != null) ? data.getRevIndiCator().toString() : "");
            de48.setPDS52(Optional.ofNullable(data.getMotoEcomIndicator()).orElse(""));
            de48.setPDS148(Optional.ofNullable(data.getTxnCurCode()).orElse(""));
            de48.setPDS149(Optional.ofNullable(data.getFunctionCode()).orElse(""));
            de48.setPDS158(Optional.ofNullable(data.getIrd()).orElse(""));
            de48.setPDS165((data.getSettlementIndicator() != null) ? data.getSettlementIndicator().toString() : "");
            de48.setPDS176(Optional.ofNullable(data.getMaid()).orElse(""));
            de48.setPDS211(Optional.ofNullable(data.getMotoEcomIndicator()).orElse(""));
            de48.setPDS0213(Optional.ofNullable(data.getMeCountryOfOrigin()).orElse(""));
            vo.setDe22_json(Optional.ofNullable(data.getPosDataCode()).orElse(""));
            vo.setDe24_json(Optional.ofNullable(data.getFunctionCode()).orElse(""));
            vo.setDe25_json(Optional.ofNullable(data.getMsgReasonCode()).orElse(""));
            vo.setDe31_json(Optional.ofNullable(data.getAcqRefData()).orElse(""));
            vo.setDe95_json("");
            vo.setDe48_json(de48);
            vo.setStatus(Optional.ofNullable(data.getApiResponse()).orElse(""));
        }
        catch (final Exception e) {
            RtsTxnService.log.error("Error mapping to RtsResponseVo: ", (Throwable)e);
        }
        return vo;
    }
    
    private McRTSTxnDataEntity txnDetails(final RtsRequestVo reqVo) {
        return this.mcRTSTxnDataRepo.findOne((root, query, cb) -> {
            final List<Predicate> predicates = new ArrayList<Predicate>();
            if (reqVo.getRrn() != null && !reqVo.getRrn().isEmpty()) {
                predicates.add(cb.equal((Expression)root.get("rrn"), (Object)reqVo.getRrn()));
            }
            if (reqVo.getMti() != null && !reqVo.getMti().isEmpty()) {
                predicates.add(cb.equal((Expression)root.get("messageTypeId"), (Object)reqVo.getMti()));
            }
            if (reqVo.getProcessCode() != null && !reqVo.getProcessCode().isEmpty()) {
                predicates.add(cb.equal((Expression)root.get("procCode"), (Object)reqVo.getProcessCode()));
            }
            query.orderBy(new Order[] { cb.desc((Expression)root.get("serNumber")) });
            return cb.and((Predicate[])predicates.toArray(new Predicate[0]));
        }).orElse(null);
    }
    
    public Map<String, String> retryRtsRequest(@Valid final RtsRequestVo rstRequestVo) {
        final Map<String, String> response = new HashMap<String, String>();
        final McRTSTxnDataEntity data = this.txnDetails(rstRequestVo);
        if (data == null) {
            RtsTxnService.log.warn("No transaction found for RRN: {}, MTI: {}, PROC: {}", (Object)rstRequestVo.getRrn(), (Object)rstRequestVo.getMti(), (Object)rstRequestVo.getProcessCode());
            response.put("Message", "No transaction found");
            return response;
        }
        if ("Success".equalsIgnoreCase(data.getApiResponse())) {
            RtsTxnService.log.warn("No failed transaction found for RRN: {}, MTI: {}, PROC: {}", (Object)rstRequestVo.getRrn(), (Object)rstRequestVo.getMti(), (Object)rstRequestVo.getProcessCode());
            response.put("Message", "No failed transaction found");
            return response;
        }
        final PosTransactionEntity posTxnEntity = this.posTransactionRepo.findFirstByMsgTypeIdAndRrnOrderBySerialNumberAsc(rstRequestVo.getMti(), rstRequestVo.getRrn());
        final boolean isFailed = "Failed".equalsIgnoreCase(data.getApiResponse());
        final boolean isReversalFailed = "Reversal against Sale Transaction API is Failed".equalsIgnoreCase(data.getApiResponse());
        if (isFailed || isReversalFailed) {
            if (isReversalFailed) {
                final McRTSTxnDataEntity saleTxn = this.mcRTSTxnDataRepo.findFirstByRrnOrderBySerNumberAsc(rstRequestVo.getRrn());
                if (saleTxn == null || !"Success".equalsIgnoreCase(saleTxn.getApiResponse())) {
                    RtsTxnService.log.warn("Reversal retry skipped: Sale transaction not successful for RRN: {}", (Object)rstRequestVo.getRrn());
                    response.put("Message", "Sale Transaction not successful, reversal retry skipped");
                    return response;
                }
            }
            final Map<String, Object> rtsRequestMap = this.txnProcessingService.buildRtsRequest(data);
            final ResponseEntity<JsonNode> rtsResponse = this.txnProcessingService.sendApiRequest(rtsRequestMap);
            final boolean success = rtsResponse.getStatusCode() == HttpStatus.OK;
            this.updateTransactionStatus(data, posTxnEntity, success);
            final String msg = isReversalFailed ? (success ? "RTS Reversal API processed successfully" : "RTS Reversal API Failed") : (success ? "RTS API processed successfully" : "RTS API Failed");
            response.put("Message", msg);
        }
        return response;
    }
    
    private void updateTransactionStatus(final McRTSTxnDataEntity data, final PosTransactionEntity posTxnEntity, final boolean success) {
        data.setApiResponse(success ? "Success" : "Failed");
        if (success) {
            data.setGeneralStatus(4);
        }
        this.mcRTSTxnDataRepo.saveAndFlush((Object)data);
        if (posTxnEntity != null) {
            posTxnEntity.setGenStatus(success ? 4 : posTxnEntity.getGenStatus());
            posTxnEntity.setOutStatus(success ? "Completed" : "Marked for Outgoing");
            posTxnEntity.setIncomingStatus("NA");
            this.posTransactionRepo.saveAndFlush((Object)posTxnEntity);
        }
        if (success) {
            RtsTxnService.log.info("RTS API retry succeeded for RRN: {}", (Object)data.getRrn());
        }
        else {
            RtsTxnService.log.error("RTS API retry failed for RRN: {}", (Object)data.getRrn());
        }
    }
    
    public RtsTxnService(final McRTSTxnDataRepo mcRTSTxnDataRepo, final TxnProcessingService txnProcessingService, final PosTransactionRepository posTransactionRepo) {
        this.mcRTSTxnDataRepo = mcRTSTxnDataRepo;
        this.txnProcessingService = txnProcessingService;
        this.posTransactionRepo = posTransactionRepo;
    }
    
    static {
        log = LogManager.getLogger((Class)RtsTxnService.class);
    }
}
