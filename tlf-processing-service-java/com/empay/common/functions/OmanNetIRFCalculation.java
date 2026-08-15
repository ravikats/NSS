// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.common.functions;

import org.apache.logging.log4j.LogManager;
import jakarta.transaction.Transactional;
import java.util.Comparator;
import com.empay.common.entities.OmanNetBinDataEntity;
import java.util.List;
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
public class OmanNetIRFCalculation
{
    private static final Logger log;
    private final OmanNetIRFRepo omanNetIRFRepo;
    private final OmanNetBinRepo omanNetBinRepo;
    
    @Transactional
    public IRFResultVo getOmanNetIRFValue(final Integer insCode, final PosTransactionEntity posData, final String cardNumber) {
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
            txnAmount = Optional.ofNullable(posData).map((Function<? super PosTransactionEntity, ? extends Double>)PosTransactionEntity::getTxnAmount).orElse(0.0);
            final List<String> binNumbers = List.of(cardNumber.substring(0, 6), cardNumber.substring(0, 7), cardNumber.substring(0, 8), cardNumber.substring(0, 9));
            final List<OmanNetBinDataEntity> binDataList = this.omanNetBinRepo.findByBinNumberInAndSubRoute(binNumbers, posData.getNetwork().toUpperCase());
            final OmanNetBinDataEntity binData = binDataList.stream().max(Comparator.comparingInt(data -> data.getBinNumber().length())).orElse(null);
            if (binData != null) {
                cardType = binData.getCardType();
            }
            if (binData != null && binData.getRoute().equalsIgnoreCase("MAAL")) {
                cardType = binData.getCardType();
                subRoute = binData.getRoute().toLowerCase();
                route = binData.getSubRoute().toLowerCase();
                OmanNetIRFCalculation.log.info("......... Omannet IRF Request....");
                OmanNetIRFCalculation.log.info("cardType: {}, subRoute: {}, route: {}, Mcc: {}", (Object)cardType, (Object)subRoute, (Object)route, (Object)posData.getMcc());
                omanNetEntity = this.omanNetIRFRepo.findByRouteAndSubRouteAndCardTypeAndMcc(route, subRoute, cardType, posData.getMcc());
            }
            else {
                OmanNetIRFCalculation.log.info(".........No Valid Bin number found for this transaction...........");
                omanNetEntity = this.omanNetIRFRepo.findBySegment("all segment");
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
        }
        catch (final Exception e) {
            OmanNetIRFCalculation.log.error("", (Throwable)e);
            return null;
        }
        return irfVo;
    }
    
    public OmanNetIRFCalculation(final OmanNetIRFRepo omanNetIRFRepo, final OmanNetBinRepo omanNetBinRepo) {
        this.omanNetIRFRepo = omanNetIRFRepo;
        this.omanNetBinRepo = omanNetBinRepo;
    }
    
    static {
        log = LogManager.getLogger((Class)OmanNetIRFCalculation.class);
    }
}
