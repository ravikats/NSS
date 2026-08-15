package com.empay.irfservice.calculator;

import com.empay.common.entities.OmanNetBinDataEntity;
import com.empay.common.entities.OmanNetIRFEntity;
import com.empay.common.irf.IrfCalculator;
import com.empay.common.irf.IrfTxnData;
import com.empay.common.repo.OmanNetBinRepo;
import com.empay.common.repo.OmanNetIRFRepo;
import com.empay.common.vo.IRFResultVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * OmanNet IRF calculation, ported verbatim from the TLF {@code OmanNetIRFCalculation}.
 * Resolves the longest matching BIN (6–9 digits) against {@code OMANNET_BIN_DATA},
 * then looks up the rate by route/sub-route/card-type/MCC (MAAL) or falls back to the
 * "all segment" rate when no valid BIN is found.
 */
@Service
public class OmanNetIrfCalculationService implements IrfCalculator {

    private static final Logger log = LoggerFactory.getLogger(OmanNetIrfCalculationService.class);

    private final OmanNetIRFRepo omanNetIRFRepo;
    private final OmanNetBinRepo omanNetBinRepo;

    public OmanNetIrfCalculationService(OmanNetIRFRepo omanNetIRFRepo, OmanNetBinRepo omanNetBinRepo) {
        this.omanNetIRFRepo = omanNetIRFRepo;
        this.omanNetBinRepo = omanNetBinRepo;
    }

    @Override
    @Transactional
    public IRFResultVo calculate(Integer insCode, IrfTxnData txnData, String cardNumber) {
        OmanNetIRFEntity omanNetEntity = new OmanNetIRFEntity();
        Double irfPercentage = 0.0;
        Double irfFixed = 0.0;
        Double irfMax = 0.0;
        Double txnAmount = 0.0;
        Character cardType = null;
        String route = null;
        String subRoute = null;
        Double irfAmount = 0.0;
        final IRFResultVo irfVo = new IRFResultVo();
        try {
            txnAmount = Optional.ofNullable(txnData).map(IrfTxnData::getTxnAmount).orElse(0.0);
            final List<String> binNumbers = List.of(cardNumber.substring(0, 6), cardNumber.substring(0, 7), cardNumber.substring(0, 8), cardNumber.substring(0, 9));
            final List<OmanNetBinDataEntity> binDataList = omanNetBinRepo.findByBinNumberInAndSubRoute(binNumbers, txnData.getNetwork().toUpperCase());
            final OmanNetBinDataEntity binData = binDataList.stream().max(Comparator.comparingInt(data -> data.getBinNumber().length())).orElse(null);
            if (binData != null) {
                cardType = binData.getCardType();
            }
            if (binData != null && binData.getRoute().equalsIgnoreCase("MAAL")) {
                cardType = binData.getCardType();
                subRoute = binData.getRoute().toLowerCase();
                route = binData.getSubRoute().toLowerCase();
                log.info("......... Omannet IRF Request....");
                log.info("cardType: {}, subRoute: {}, route: {}, Mcc: {}", cardType, subRoute, route, txnData.getMcc());
                omanNetEntity = omanNetIRFRepo.findByRouteAndSubRouteAndCardTypeAndMcc(route, subRoute, cardType, txnData.getMcc());
            } else {
                log.info(".........No Valid Bin number found for this transaction...........");
                omanNetEntity = omanNetIRFRepo.findBySegment("all segment");
            }
            if (omanNetEntity != null) {
                irfPercentage = Optional.ofNullable(omanNetEntity.getIrfPercentage()).orElse(0.0);
                irfFixed = Optional.ofNullable(omanNetEntity.getIrfFixed()).orElse(0.0);
                irfMax = Optional.ofNullable(omanNetEntity.getIrfMax()).orElse(0.0);
                irfVo.setIrdSerNumber(omanNetEntity.getSerialNumber());
                irfVo.setIrfDesc(omanNetEntity.getSegmentDesc());
                cardType = omanNetEntity.getCardType();
            }
            irfAmount = irfFixed + txnAmount * irfPercentage * 0.01;
            if (irfMax > 0.0) {
                irfAmount = Math.min(irfAmount, irfMax);
            }
            irfVo.setCardType(cardType);
            irfVo.setDomIntlFlag('D');
            irfVo.setIrfFixed(irfFixed);
            irfVo.setIrfPercentage(irfPercentage);
            irfVo.setIrfMaxAmount(irfMax);
            irfVo.setIrfAmount(irfAmount);
        } catch (Exception e) {
            log.error("", e);
            return null;
        }
        return irfVo;
    }
}
