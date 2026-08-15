package com.empay.irfservice.calculator;

import com.empay.common.entities.JaywanIRFEntity;
import com.empay.common.irf.IrfCalculator;
import com.empay.common.irf.IrfTxnData;
import com.empay.common.repo.JaywanIRFRepo;
import com.empay.common.vo.IRFResultVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Jaywan IRF calculation, ported verbatim from the TLF {@code JaywanIRFCalculation}.
 * Resolves the rate row by MCC (falling back to the GENERAL segment), then applies
 * POS/E-COM rates by terminal type when the row is a segment-level (MCC-less) entry.
 */
@Service
public class JaywanIrfCalculationService implements IrfCalculator {

    private static final Logger log = LoggerFactory.getLogger(JaywanIrfCalculationService.class);

    private final JaywanIRFRepo jaywanIRFRepo;

    public JaywanIrfCalculationService(JaywanIRFRepo jaywanIRFRepo) {
        this.jaywanIRFRepo = jaywanIRFRepo;
    }

    @Override
    @Transactional
    public IRFResultVo calculate(Integer insCode, IrfTxnData txnData, String cardNumber) {
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
            log.info("JAYWAN Irf Calculation Start: ");
            final IRFResultVo irdResult = new IRFResultVo();
            txnAmount = Optional.ofNullable(txnData).map(IrfTxnData::getTxnAmount).orElse(0.0);
            mcc = Optional.ofNullable(txnData).map(IrfTxnData::getMcc).orElse("");
            terminalType = Optional.ofNullable(txnData).map(IrfTxnData::getTerminalType).orElse("");
            final JaywanIRFEntity jaywanIrfEntity = Optional.ofNullable(jaywanIRFRepo.findByMcc(mcc)).orElseGet(() -> {
                log.info("MCC not found – falling back to GENERAL segment");
                return jaywanIRFRepo.findBySegment("GENERAL").orElse(null);
            });
            if (jaywanIrfEntity == null) {
                log.warn("No IRF configuration found (SPECIAL or GENERAL)");
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
            } else if ("POS".equalsIgnoreCase(terminalType)) {
                log.info("Calculating based on POS IRF rates");
                irfPercentage = Optional.ofNullable(jaywanIrfEntity.getPosIrf()).orElse(0.0);
                irfMaxAmount = Optional.ofNullable(jaywanIrfEntity.getPosIrfMax()).orElse(0.0);
            } else if ("E-COM".equalsIgnoreCase(terminalType)) {
                log.info("Calculating based on ECOM IRF rates");
                irfPercentage = Optional.ofNullable(jaywanIrfEntity.getEcomIrf()).orElse(0.0);
                irfMaxAmount = Optional.ofNullable(jaywanIrfEntity.getEcomIrfMax()).orElse(0.0);
            } else {
                log.info("Unknown terminal type – using default GENERAL IRF rates");
            }
            irfAmount = irfFixed + txnAmount * irfPercentage * 0.01;
            if (irfAmount > irfMaxAmount && irfMaxAmount != 0.0) {
                irfAmount = irfMaxAmount;
            }
            populateIrfResult(irdResult, irdSerNumber, irfAmount, irfDesc, irfFixed, irfMaxAmount, irfMinAmount, irfPercentage);
            log.info("JAYWAN IRF Calculation End");
            return irdResult;
        } catch (Exception e) {
            log.error("Error ,Error in JAYWAN IRF Calculation :", e);
            return null;
        }
    }

    private void populateIrfResult(IRFResultVo result, int serNumber, double amount, String desc, double fixed, double maxAmount, double minAmount, double percentage) {
        result.setIrdSerNumber(serNumber);
        result.setIrfAmount(amount);
        result.setIrfDesc(desc);
        result.setIrfFixed(fixed);
        result.setIrfMaxAmount(maxAmount);
        result.setIrfMinAmount(minAmount);
        result.setIrfPercentage(percentage);
    }
}
