// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.common.functions;

import org.apache.logging.log4j.LogManager;
import jakarta.transaction.Transactional;
import com.empay.entities.JaywanIRFEntity;
import java.util.function.Function;
import java.util.Optional;
import com.empay.tlfprocessing.vo.IRFResultVo;
import com.empay.entities.PosTransactionEntity;
import com.empay.repositories.JaywanIRFRepo;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class JaywanIRFCalculation
{
    private static final Logger log;
    private final JaywanIRFRepo jaywanIRFRepo;
    
    @Transactional
    public IRFResultVo getJaywanIrf(final Integer insCode, final PosTransactionEntity posData) {
        final double irfMinAmount = 0.0;
        double irfMaxAmount = 0.0;
        double txnAmount = 0.0;
        double irfFixed = 0.0;
        double irfAmount = 0.0;
        double irfPercentage = 0.0;
        String mcc = "";
        final char cardType = 'D';
        int irdSerNumber = 0;
        final char cardDomIntlFlag = 'D';
        String irfDesc = "";
        String terminalType = "";
        try {
            JaywanIRFCalculation.log.info("JAYWAN Irf Calculation Start: ");
            final IRFResultVo irdResult = new IRFResultVo();
            txnAmount = Optional.ofNullable(posData).map((Function<? super PosTransactionEntity, ? extends Double>)PosTransactionEntity::getTxnAmount).orElse(0.0);
            mcc = Optional.ofNullable(posData).map((Function<? super PosTransactionEntity, ? extends String>)PosTransactionEntity::getMcc).orElse("");
            terminalType = Optional.ofNullable(posData).map((Function<? super PosTransactionEntity, ? extends String>)PosTransactionEntity::getTerminalType).orElse("");
            final JaywanIRFEntity jaywanIrfEntity = Optional.ofNullable(this.jaywanIRFRepo.findByMcc(mcc)).orElseGet(() -> {
                JaywanIRFCalculation.log.info("MCC not found \u2013 falling back to GENERAL segment");
                return (JaywanIRFEntity)this.jaywanIRFRepo.findBySegment("GENERAL").orElse(null);
            });
            if (jaywanIrfEntity == null) {
                JaywanIRFCalculation.log.warn("No IRF configuration found (SPECIAL or GENERAL)");
                return irdResult;
            }
            irdResult.setCardType(cardType);
            irdResult.setDomIntlFlag(cardDomIntlFlag);
            irdResult.setGcmsProductID("");
            irdResult.setIrdCode("");
            irdResult.setIrfCountry("");
            irfFixed = Optional.ofNullable(jaywanIrfEntity.getIrfFixed()).orElse(0.0);
            irfDesc = Optional.ofNullable(jaywanIrfEntity.getSegmentDesc()).orElse("");
            irdSerNumber = jaywanIrfEntity.getSerialNumber();
            if (jaywanIrfEntity.getMcc() != null) {
                irfPercentage = Optional.ofNullable(jaywanIrfEntity.getIrfRate()).orElse(0.0);
                irfMaxAmount = Optional.ofNullable(jaywanIrfEntity.getIrfMax()).orElse(0.0);
            }
            else if ("POS".equalsIgnoreCase(terminalType)) {
                JaywanIRFCalculation.log.info("Calculating based on POS IRF rates");
                irfPercentage = Optional.ofNullable(jaywanIrfEntity.getPosIrf()).orElse(0.0);
                irfMaxAmount = Optional.ofNullable(jaywanIrfEntity.getPosIrfMax()).orElse(0.0);
            }
            else if ("E-COM".equalsIgnoreCase(terminalType)) {
                JaywanIRFCalculation.log.info("Calculating based on ECOM IRF rates");
                irfPercentage = Optional.ofNullable(jaywanIrfEntity.getEcomIrf()).orElse(0.0);
                irfMaxAmount = Optional.ofNullable(jaywanIrfEntity.getEcomIrfMax()).orElse(0.0);
            }
            else {
                JaywanIRFCalculation.log.info("Unknown terminal type \u2013 using default GENERAL IRF rates");
            }
            irfAmount = irfFixed + txnAmount * irfPercentage * 0.01;
            if (irfAmount > irfMaxAmount && irfMaxAmount != 0.0) {
                irfAmount = irfMaxAmount;
            }
            this.populateIrfResult(irdResult, irdSerNumber, irfAmount, irfDesc, irfFixed, irfMaxAmount, irfMinAmount, irfPercentage);
            JaywanIRFCalculation.log.info("JAYWAN IRF Calculation End");
            return irdResult;
        }
        catch (final Exception e) {
            JaywanIRFCalculation.log.error("Error ,Error in JAYWAN IRF Calculation :", (Throwable)e);
            return null;
        }
    }
    
    private void populateIrfResult(final IRFResultVo result, final int serNumber, final double amount, final String desc, final double fixed, final double maxAmount, final double minAmount, final double percentage) {
        result.setIrdSerNumber(serNumber);
        result.setIrfAmount(amount);
        result.setIrfDesc(desc);
        result.setIrfFixed(fixed);
        result.setIrfMaxAmount(maxAmount);
        result.setIrfMinAmount(minAmount);
        result.setIrfPercentage(percentage);
    }
    
    public JaywanIRFCalculation(final JaywanIRFRepo jaywanIRFRepo) {
        this.jaywanIRFRepo = jaywanIRFRepo;
    }
    
    static {
        log = LogManager.getLogger((Class)JaywanIRFCalculation.class);
    }
}
