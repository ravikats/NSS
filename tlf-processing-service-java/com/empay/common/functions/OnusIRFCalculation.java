// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.common.functions;

import org.apache.logging.log4j.LogManager;
import jakarta.transaction.Transactional;
import com.empay.common.entities.OmanNetBinDataEntity;
import org.apache.commons.lang3.StringUtils;
import java.util.function.Function;
import java.util.Optional;
import com.empay.entities.OmanNetIRFEntity;
import com.empay.tlfprocessing.vo.IRFResultVo;
import com.empay.entities.PosTransactionEntity;
import com.empay.common.repo.OmanNetBinRepo;
import com.empay.repositories.OmanNetIRFRepo;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class OnusIRFCalculation
{
    private static final Logger log;
    private final OmanNetIRFRepo omanNetIRFRepo;
    private final OmanNetBinRepo omanNetBinRepo;
    
    @Transactional
    public IRFResultVo getOnusIRFValue(final Integer insCode, final PosTransactionEntity posData, final String cardNumber) {
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
            txnAmount = Optional.ofNullable(posData).map((Function<? super PosTransactionEntity, ? extends Double>)PosTransactionEntity::getTxnAmount).orElse(0.0);
            final OmanNetBinDataEntity binData = this.omanNetBinRepo.findByBinNumberAndSubRoute(StringUtils.substring(cardNumber, 0, 8), posData.getNetwork());
            if (binData != null) {
                cardType = binData.getCardType();
            }
            if (binData != null && binData.getRoute().toLowerCase().matches("onus")) {
                cardType = binData.getCardType();
                subRoute = binData.getRoute().toLowerCase();
                route = binData.getSubRoute().toLowerCase();
                OnusIRFCalculation.log.info("......... Omannet IRF Request....");
                OnusIRFCalculation.log.info("cardType: {}, subRoute: {}, route: {}, Mcc: {}", (Object)cardType, (Object)subRoute, (Object)route, (Object)posData.getMcc());
                omanNetEntity = this.omanNetIRFRepo.findByRouteAndSubRouteAndCardTypeAndMcc(route, subRoute, cardType, posData.getMcc());
            }
            else {
                OnusIRFCalculation.log.info(".........No Valid Bin number found for this transaction...........");
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
        }
        catch (final Exception e) {
            OnusIRFCalculation.log.error("Error : ", (Throwable)e);
            return null;
        }
        return irfVo;
    }
    
    public OnusIRFCalculation(final OmanNetIRFRepo omanNetIRFRepo, final OmanNetBinRepo omanNetBinRepo) {
        this.omanNetIRFRepo = omanNetIRFRepo;
        this.omanNetBinRepo = omanNetBinRepo;
    }
    
    static {
        log = LogManager.getLogger((Class)OnusIRFCalculation.class);
    }
}
