package com.empay.irfservice.calculator;

import com.empay.common.entities.UAESwitchIRFEntity;
import com.empay.common.irf.IrfCalculator;
import com.empay.common.irf.IrfTxnData;
import com.empay.common.repo.UAESwitchIRFRepo;
import com.empay.common.vo.IRFResultVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * UAE-Switch IRF calculation, ported verbatim from the TLF
 * {@code UAESwitchIRFCalculation}. Resolves the rate row by MCC (falling back to
 * the GENERAL segment), then applies POS/E-COM rates by terminal type when the
 * row is a segment-level (MCC-less) entry.
 */
@Service
public class UaeSwitchIrfCalculationService implements IrfCalculator {

    private static final Logger log = LoggerFactory.getLogger(UaeSwitchIrfCalculationService.class);

    private final UAESwitchIRFRepo uaeSwitchIRFRepo;

    public UaeSwitchIrfCalculationService(UAESwitchIRFRepo uaeSwitchIRFRepo) {
        this.uaeSwitchIRFRepo = uaeSwitchIRFRepo;
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
            log.info("UAE Switch Irf Calculation Start: ");
            final IRFResultVo irdResult = new IRFResultVo();
            txnAmount = Optional.ofNullable(txnData).map(IrfTxnData::getTxnAmount).orElse(0.0);
            mcc = Optional.ofNullable(txnData).map(IrfTxnData::getMcc).orElse("");
            terminalType = Optional.ofNullable(txnData).map(IrfTxnData::getTerminalType).orElse("");
            final UAESwitchIRFEntity uaeSwitchIrfEntity = Optional.ofNullable(uaeSwitchIRFRepo.findByMcc(mcc)).orElseGet(() -> {
                log.info("MCC not found – falling back to GENERAL segment");
                return uaeSwitchIRFRepo.findBySegment("GENERAL").orElse(null);
            });
            if (uaeSwitchIrfEntity == null) {
                log.warn("No IRF configuration found (SPECIAL or GENERAL)");
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
            } else if ("POS".equalsIgnoreCase(terminalType)) {
                log.info("Calculating based on POS IRF rates");
                irfPercentage = Optional.ofNullable(uaeSwitchIrfEntity.getPosIrf()).orElse(0.0);
                irfMaxAmount = Optional.ofNullable(uaeSwitchIrfEntity.getPosIrfMax()).orElse(0.0);
            } else if ("E-COM".equalsIgnoreCase(terminalType)) {
                log.info("Calculating based on ECOM IRF rates");
                irfPercentage = Optional.ofNullable(uaeSwitchIrfEntity.getEcomIrf()).orElse(0.0);
                irfMaxAmount = Optional.ofNullable(uaeSwitchIrfEntity.getEcomIrfMax()).orElse(0.0);
            } else {
                log.info("Unknown terminal type – using default GENERAL IRF rates");
            }
            irfAmount = irfFixed + txnAmount * irfPercentage * 0.01;
            if (irfAmount > irfMaxAmount && irfMaxAmount != 0.0) {
                irfAmount = irfMaxAmount;
            }
            populateIrfResult(irdResult, irdSerNumber, irfAmount, irfDesc, irfFixed, irfMaxAmount, irfMinAmount, irfPercentage);
            log.info("UAE Switch IRF Calculation End");
            return irdResult;
        } catch (Exception e) {
            log.error("Error ,Error in UAE Switch IRF Calculation :", e);
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
