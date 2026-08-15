/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.entities.PosTransactionEntity
 *  com.empay.repositories.PosTransactionRepository
 *  com.empay.services.BatchInquiryService
 *  com.empay.vo.BatchTxnSummaryVo
 *  org.springframework.stereotype.Service
 */
package com.empay.services;

import com.empay.entities.PosTransactionEntity;
import com.empay.repositories.PosTransactionRepository;
import com.empay.vo.BatchTxnSummaryVo;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class BatchInquiryService {
    private final PosTransactionRepository posTxnRepo;

    public Map<String, Map<String, BatchTxnSummaryVo>> getBatchTotals(LocalDateTime startTime, LocalDateTime endTime) {
        List txns = this.posTxnRepo.findByTxnDateTimeBetween(startTime, endTime);
        return txns.stream().collect(Collectors.groupingBy(txn -> txn.getTrlType() + "|" + txn.getNetwork(), LinkedHashMap::new, Collectors.collectingAndThen(Collectors.toList(), txnDetails -> Map.of("sales", this.getDetails(txnDetails, "00", "0110"), "refund", this.getDetails(txnDetails, "20", "0110"), "refund_reversal", this.getDetails(txnDetails, "20", "0410"), "preauth", this.getDetails(txnDetails, "61", "0110"), "preauth_increment", this.getDetails(txnDetails, "71", "0110"), "void", this.getDetails(txnDetails, "00", "0430"), "reversal", this.getDetails(txnDetails, "00", "0410"), "refund_void", this.getDetails(txnDetails, "20", "0430"), "preauth_complete", this.getDetails(txnDetails, "00", "0130"))))).entrySet().stream().collect(Collectors.toMap(e -> {
            String[] parts = ((String)e.getKey()).split("\\|");
            return this.mapTxnType(parts[0], parts[1]);
        }, Map.Entry::getValue, (a, b) -> b, LinkedHashMap::new));
    }

    private String mapTxnType(String trlType, String network) {
        String net = network.toLowerCase().replace("mci", "mastercard");
        return switch (trlType.toUpperCase()) {
            case "POS" -> {
                if ("omannet".equalsIgnoreCase(network)) {
                    yield "omannet_tps_pos";
                }
                yield net + "_pos";
            }
            default -> "omannet".equalsIgnoreCase(network) ? "omannet_tps_ecom" : net + "_ecom";
        };
    }

    private BatchTxnSummaryVo getDetails(List<PosTransactionEntity> txns, String processCode, String msgTypeId) {
        int successCount = 0;
        int failedCount = 0;
        double successAmount = 0.0;
        double failedAmount = 0.0;
        for (PosTransactionEntity txn : txns) {
            double amount;
            boolean matches = false;
            if (txn.getProcCode().startsWith(processCode) && msgTypeId.equals(txn.getMti())) {
                matches = true;
            }
            if (!matches) continue;
            String responseCode = txn.getResponseCode();
            double d = amount = txn.getTxnAmount() != null ? txn.getTxnAmount() : 0.0;
            if (responseCode.equals("00")) {
                ++successCount;
                successAmount += amount;
                continue;
            }
            ++failedCount;
            failedAmount += amount;
        }
        double roundedSuccessAmount = BigDecimal.valueOf(successAmount).setScale(2, RoundingMode.HALF_UP).doubleValue();
        double roundedFailedAmount = BigDecimal.valueOf(failedAmount).setScale(2, RoundingMode.HALF_UP).doubleValue();
        return new BatchTxnSummaryVo(successCount, failedCount, roundedSuccessAmount, roundedFailedAmount);
    }

    public BatchInquiryService(PosTransactionRepository posTxnRepo) {
        this.posTxnRepo = posTxnRepo;
    }
}

