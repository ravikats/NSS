package com.empay.irfservice.calculator;

import com.empay.common.entities.OmanNetBinDataEntity;
import com.empay.common.entities.OmanNetIRFEntity;
import com.empay.common.irf.IrfCalculator;
import com.empay.common.irf.IrfTxnData;
import com.empay.common.repo.OmanNetBinRepo;
import com.empay.common.repo.OmanNetIRFRepo;
import com.empay.common.vo.IRFResultVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * On-us IRF calculation, ported verbatim from the TLF {@code OnusIRFCalculation}.
 * Resolves the first 8 BIN digits against {@code OMANNET_BIN_DATA} (route must be
 * "onus"), then looks up the rate by route/sub-route/card-type/MCC.
 */
@Service
public class OnusIrfCalculationService implements IrfCalculator {

    private static final Logger log = LoggerFactory.getLogger(OnusIrfCalculationService.class);

    private final OmanNetIRFRepo omanNetIRFRepo;
    private final OmanNetBinRepo omanNetBinRepo;

    public OnusIrfCalculationService(OmanNetIRFRepo omanNetIRFRepo, OmanNetBinRepo omanNetBinRepo) {
        this.omanNetIRFRepo = omanNetIRFRepo;
        this.omanNetBinRepo = omanNetBinRepo;
    }

    @Override
    @Transactional
    public IRFResultVo calculate(Integer insCode, IrfTxnData txnData, String cardNumber) {
        Double irfPercentage = 0.0;
        Double irfFixed = 0.0;
        Double irfMax = 0.0;
        Double txnAmount = 0.0;
        Character cardType = null;
        String route = null;
        String subRoute = null;
        Double irfAmount = 0.0;
        final IRFResultVo irfVo = new IRFResultVo();
        OmanNetIRFEntity omanNetEntity = new OmanNetIRFEntity();
        try {
            txnAmount = Optional.ofNullable(txnData).map(IrfTxnData::getTxnAmount).orElse(0.0);
            final OmanNetBinDataEntity binData = omanNetBinRepo.findByBinNumberAndSubRoute(StringUtils.substring(cardNumber, 0, 8), txnData.getNetwork());
            if (binData != null) {
                cardType = binData.getCardType();
            }
            if (binData != null && binData.getRoute().toLowerCase().matches("onus")) {
                cardType = binData.getCardType();
                subRoute = binData.getRoute().toLowerCase();
                route = binData.getSubRoute().toLowerCase();
                log.info("......... Omannet IRF Request....");
                log.info("cardType: {}, subRoute: {}, route: {}, Mcc: {}", cardType, subRoute, route, txnData.getMcc());
                omanNetEntity = omanNetIRFRepo.findByRouteAndSubRouteAndCardTypeAndMcc(route, subRoute, cardType, txnData.getMcc());
            } else {
                log.info(".........No Valid Bin number found for this transaction...........");
                omanNetEntity = null;
            }
            if (omanNetEntity != null) {
                irfPercentage = Optional.ofNullable(omanNetEntity.getIrfPercentage()).orElse(0.0);
                irfFixed = Optional.ofNullable(omanNetEntity.getIrfFixed()).orElse(0.0);
                irfMax = Optional.ofNullable(omanNetEntity.getIrfMax()).orElse(0.0);
                irfVo.setIrdSerNumber(omanNetEntity.getSerialNumber());
                irfVo.setIrfDesc(omanNetEntity.getSegmentDesc());
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
            log.error("Error : ", e);
            return null;
        }
        return irfVo;
    }
}
