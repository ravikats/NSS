// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.common.functions;

import org.apache.logging.log4j.LogManager;
import jakarta.transaction.Transactional;
import com.empay.entities.UAESwitchIRFEntity;
import java.util.function.Function;
import java.util.Optional;
import com.empay.tlfprocessing.vo.IRFResultVo;
import com.empay.entities.PosTransactionEntity;
import com.empay.repositories.UAESwitchIRFRepo;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class UAESwitchIRFCalculation
{
    private static final Logger log;
    private final UAESwitchIRFRepo uaeSwitchIRFRepo;
    
    @Transactional
    public IRFResultVo getUAESwitchIrf(final Integer insCode, final PosTransactionEntity posData) {
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
            UAESwitchIRFCalculation.log.info("UAE Switch Irf Calculation Start: ");
            final IRFResultVo irdResult = new IRFResultVo();
            txnAmount = Optional.ofNullable(posData).map((Function<? super PosTransactionEntity, ? extends Double>)PosTransactionEntity::getTxnAmount).orElse(0.0);
            mcc = Optional.ofNullable(posData).map((Function<? super PosTransactionEntity, ? extends String>)PosTransactionEntity::getMcc).orElse("");
            terminalType = Optional.ofNullable(posData).map((Function<? super PosTransactionEntity, ? extends String>)PosTransactionEntity::getTerminalType).orElse("");
            final UAESwitchIRFEntity uaeSwitchIrfEntity = Optional.ofNullable(this.uaeSwitchIRFRepo.findByMcc(mcc)).orElseGet(() -> {
                UAESwitchIRFCalculation.log.info("MCC not found \u2013 falling back to GENERAL segment");
                return (UAESwitchIRFEntity)this.uaeSwitchIRFRepo.findBySegment("GENERAL").orElse(null);
            });
            if (uaeSwitchIrfEntity == null) {
                UAESwitchIRFCalculation.log.warn("No IRF configuration found (SPECIAL or GENERAL)");
                return irdResult;
            }
            irdResult.setCardType(cardType);
            irdResult.setDomIntlFlag(cardDomIntlFlag);
            irdResult.setGcmsProductID("");
            irdResult.setIrdCode("");
            irdResult.setIrfCountry("");
            irfFixed = Optional.ofNullable(uaeSwitchIrfEntity.getIrfFixed()).orElse(0.0);
            irfDesc = Optional.ofNullable(uaeSwitchIrfEntity.getSegmentDesc()).orElse("");
            irdSerNumber = uaeSwitchIrfEntity.getSerialNumber();
            if (uaeSwitchIrfEntity.getMcc() != null) {
                irfPercentage = Optional.ofNullable(uaeSwitchIrfEntity.getIrfRate()).orElse(0.0);
                irfMaxAmount = Optional.ofNullable(uaeSwitchIrfEntity.getIrfMax()).orElse(0.0);
            }
            else if ("POS".equalsIgnoreCase(terminalType)) {
                UAESwitchIRFCalculation.log.info("Calculating based on POS IRF rates");
                irfPercentage = Optional.ofNullable(uaeSwitchIrfEntity.getPosIrf()).orElse(0.0);
                irfMaxAmount = Optional.ofNullable(uaeSwitchIrfEntity.getPosIrfMax()).orElse(0.0);
            }
            else if ("E-COM".equalsIgnoreCase(terminalType)) {
                UAESwitchIRFCalculation.log.info("Calculating based on ECOM IRF rates");
                irfPercentage = Optional.ofNullable(uaeSwitchIrfEntity.getEcomIrf()).orElse(0.0);
                irfMaxAmount = Optional.ofNullable(uaeSwitchIrfEntity.getEcomIrfMax()).orElse(0.0);
            }
            else {
                UAESwitchIRFCalculation.log.info("Unknown terminal type \u2013 using default GENERAL IRF rates");
            }
            irfAmount = irfFixed + txnAmount * irfPercentage * 0.01;
            if (irfAmount > irfMaxAmount && irfMaxAmount != 0.0) {
                irfAmount = irfMaxAmount;
            }
            this.populateIrfResult(irdResult, irdSerNumber, irfAmount, irfDesc, irfFixed, irfMaxAmount, irfMinAmount, irfPercentage);
            UAESwitchIRFCalculation.log.info("UAE Switch IRF Calculation End");
            return irdResult;
        }
        catch (final Exception e) {
            UAESwitchIRFCalculation.log.error("Error ,Error in UAE Switch IRF Calculation :", (Throwable)e);
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
    
    public UAESwitchIRFCalculation(final UAESwitchIRFRepo uaeSwitchIRFRepo) {
        this.uaeSwitchIRFRepo = uaeSwitchIRFRepo;
    }
    
    static {
        log = LogManager.getLogger((Class)UAESwitchIRFCalculation.class);
    }
}
