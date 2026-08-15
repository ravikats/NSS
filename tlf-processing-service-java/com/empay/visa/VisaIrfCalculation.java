// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.visa;

import org.apache.logging.log4j.LogManager;
import java.time.DayOfWeek;
import java.time.chrono.ChronoLocalDateTime;
import java.time.temporal.Temporal;
import java.time.temporal.ChronoUnit;
import java.time.LocalDateTime;
import com.empay.visa.entities.VisaIrfProgramEntity;
import jakarta.transaction.Transactional;
import com.empay.visa.entities.VisaIssAccRangeEntity;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;
import com.empay.tlfprocessing.vo.IRFResultVo;
import com.empay.entities.PosTransactionEntity;
import org.springframework.core.env.Environment;
import com.empay.visa.repo.VisaIrfProgramRepo;
import com.empay.visa.repo.VisaIssAccRangeRepo;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class VisaIrfCalculation
{
    private static final Logger log;
    private final VisaIssAccRangeRepo visaIssAccRangeRepo;
    private final VisaIrfProgramRepo visaIrfProgramRepo;
    private final Environment env;
    
    @Transactional
    public IRFResultVo getVisaIrf(final Integer insCode, final PosTransactionEntity posData, String cardNumber) {
        IRFResultVo resultVo = new IRFResultVo();
        char countryCodeFlag = ' ';
        try {
            final IRFRequestVo irfRequestObj = new IRFRequestVo();
            final String acqInstConCode;
            final String acqCode = acqInstConCode = posData.getAcqInstConCode();
            countryCodeFlag = switch (acqInstConCode) {
                case "784" -> 85;
                case "512" -> 79;
                default -> {
                    VisaIrfCalculation.log.info("Acq Ins code is not AED or OMR: {}", (Object)acqCode);
                    yield 32;
                }
            };
            irfRequestObj.setInsCode(insCode);
            irfRequestObj.setTerminalType("POS");
            irfRequestObj.setCardDomIntlFlag('I');
            irfRequestObj.setTxnLimitIndicator('A');
            irfRequestObj.setProgRegion('R');
            if (countryCodeFlag == 'O') {
                if (posData.getReImbursementAttribute() == null || posData.getReImbursementAttribute() == ' ') {
                    irfRequestObj.setReimbAttribute('B');
                }
                else {
                    irfRequestObj.setReimbAttribute(posData.getReImbursementAttribute());
                }
            }
            else {
                irfRequestObj.setReimbAttribute('B');
            }
            irfRequestObj.setPan(posData.getEncCardNumber());
            irfRequestObj.setTxnDateTime(posData.getTxnDateTime());
            irfRequestObj.setTxnAmount(posData.getTxnAmount());
            irfRequestObj.setMcc(posData.getMcc());
            irfRequestObj.setFeePgmIndicator(posData.getFeePgmIndicator());
            irfRequestObj.setTerminalCapability(posData.getTrlCapabilities());
            irfRequestObj.setAuthCode(posData.getApprovalCode());
            irfRequestObj.setResponseCode(posData.getResponseCode());
            irfRequestObj.setMotoEcomIndicator(posData.getMotoEcomIndicator());
            irfRequestObj.setFwdinstCode(null);
            irfRequestObj.setMvv(posData.getMvv());
            irfRequestObj.setPosEntryMode(StringUtils.left(posData.getPosEntryMode(), 2));
            cardNumber = StringUtils.left(cardNumber, 9);
            final VisaIssAccRangeEntity visaBinRange = this.visaIssAccRangeRepo.findTopByIssRangeLowLessThanEqualAndIssRangeHighGreaterThanEqual(cardNumber, cardNumber);
            if (!Objects.nonNull(visaBinRange)) {
                VisaIrfCalculation.log.info(" /------ No Bin Data Found --------/");
                resultVo.setIrdCode(irfRequestObj.getIrdCode());
                resultVo.setIrfPercentage(0.0);
                resultVo.setIrfFixed(0.0);
                resultVo.setIrfAmount(0.0);
                resultVo.setIrfAmountUSD(0.0);
                resultVo.setDomIntlFlag(irfRequestObj.getCardDomIntlFlag());
                resultVo.setCardType(irfRequestObj.getCrDrIndicatorActual());
                resultVo.setIrfCountry("XX");
                return resultVo;
            }
            irfRequestObj.setIssuerRegion(visaBinRange.getRegion());
            final char indicator = switch ((char)visaBinRange.getCrDrIndicator()) {
                case 'R' -> 68;
                case 'H' -> 67;
                default -> visaBinRange.getCrDrIndicator();
            };
            irfRequestObj.setCrDrIndicator(indicator);
            irfRequestObj.setCrDrIndicatorActual(indicator);
            irfRequestObj.setCountryCode(visaBinRange.getCountryAlphaCode());
            irfRequestObj.setCardProduct(Objects.isNull(visaBinRange.getCardProduct()) ? "AO" : visaBinRange.getCardProduct().trim());
            irfRequestObj.setIrdCode(irfRequestObj.getCardProduct());
            if (Objects.nonNull(visaBinRange.getProductSubTytpe()) && visaBinRange.getProductSubTytpe().equalsIgnoreCase("TK")) {
                irfRequestObj.setQualifierIndicator('Q');
            }
            else {
                irfRequestObj.setQualifierIndicator('N');
            }
            if (irfRequestObj.getCountryCode().equals("AE") && countryCodeFlag == 'U') {
                irfRequestObj.setCardDomIntlFlag('D');
                irfRequestObj.setProgRegion('I');
            }
            else if (irfRequestObj.getCountryCode().equals("OM") && countryCodeFlag == 'O') {
                irfRequestObj.setCardDomIntlFlag('D');
                irfRequestObj.setProgRegion('I');
            }
            else if (irfRequestObj.getIssuerRegion() == '6') {
                irfRequestObj.setProgRegion('E');
            }
            else {
                irfRequestObj.setProgRegion('R');
            }
            VisaIrfCalculation.log.info("IRF Request : RRN :" + posData.getRrn() + " :" + irfRequestObj.toString());
            if (irfRequestObj.getProgRegion() == 'I' && countryCodeFlag == 'U') {
                resultVo = this.uaeIrfCalculation(irfRequestObj);
            }
            else if (irfRequestObj.getProgRegion() == 'I' && countryCodeFlag == 'O') {
                resultVo = this.omanIrfCalculation(irfRequestObj);
            }
            else if (irfRequestObj.getProgRegion() == 'E') {
                resultVo = this.meaIrfCalculation(irfRequestObj);
            }
            else if (irfRequestObj.getProgRegion() == 'R') {
                resultVo = this.interationalIrfCalculation(irfRequestObj);
            }
            if (resultVo != null) {
                VisaIrfCalculation.log.info("IRF Result :" + resultVo.toString());
            }
            else {
                resultVo = new IRFResultVo();
                resultVo.setIrdCode(irfRequestObj.getIrdCode());
                resultVo.setIrfPercentage(0.0);
                resultVo.setIrfFixed(0.0);
                resultVo.setIrfAmount(0.0);
                resultVo.setIrfAmountUSD(0.0);
                resultVo.setDomIntlFlag(irfRequestObj.getCardDomIntlFlag());
                resultVo.setCardType(irfRequestObj.getCrDrIndicatorActual());
                resultVo.setIrfCountry("XX");
            }
            VisaIrfCalculation.log.info("VISA IRF Completed ");
        }
        catch (final Exception e) {
            VisaIrfCalculation.log.error("", (Throwable)e);
            return null;
        }
        return resultVo;
    }
    
    public IRFResultVo uaeIrfCalculation(final IRFRequestVo irfRequestObj) {
        VisaIrfCalculation.log.info("//------- uaeIrfCalculation -------//");
        IRFResultVo irfResultVo = new IRFResultVo();
        try {
            final Double exchangeRate = Double.parseDouble(Objects.requireNonNull(this.env.getProperty("exchangeRateAED"), "ExchangeRate code is Null"));
            if (irfRequestObj.getCrDrIndicator() == 'D' && irfRequestObj.getCardProduct().matches("G|K|S|G1|G3|G4|G5")) {
                irfRequestObj.setCrDrIndicator('C');
            }
            if (irfRequestObj.getCrDrIndicator() != 'C' && irfRequestObj.getQualifierIndicator() == 'Q') {
                irfRequestObj.setQualifierIndicator('N');
            }
            if (Objects.nonNull(irfRequestObj) && (irfRequestObj.getCrDrIndicator() == 'D' || irfRequestObj.getCrDrIndicator() == 'P')) {
                irfResultVo = this.getVisaIrfRate_uae(irfRequestObj, "INDUSTRY FEE PROGRAM", irfRequestObj.getMcc(), null, null);
                if (Objects.nonNull(irfResultVo) && irfResultVo.getIrdSerNumber() != null) {
                    VisaIrfCalculation.log.info("-- Industry Fee Program \u2013 Consumer Debit and Prepaid --");
                    return irfResultVo;
                }
                if (Objects.nonNull(irfRequestObj.getPosEntryMode()) && irfRequestObj.getPosEntryMode().matches("02|03|05|06|07|90|91|95") && Objects.isNull(irfRequestObj.getMotoEcomIndicator())) {
                    irfResultVo = this.getVisaIrfRate_uae(irfRequestObj, "CP", null, irfRequestObj.getCardProduct(), irfRequestObj.getQualifierIndicator());
                    if (Objects.nonNull(irfResultVo) && irfResultVo.getIrdSerNumber() != null) {
                        VisaIrfCalculation.log.info("-- Card Present Fee Program \u2013 Consumer Debit and Prepaid --");
                        return irfResultVo;
                    }
                }
                irfResultVo = this.getVisaIrfRate_uae(irfRequestObj, "CNP", null, null, null);
                if (Objects.nonNull(irfResultVo) && irfResultVo.getIrdSerNumber() != null) {
                    VisaIrfCalculation.log.info("-- Card Not Present Fee Program \u2013 Consumer Debit and Prepaid --");
                    return irfResultVo;
                }
            }
            else {
                if (this.getVisaTimeLines(irfRequestObj.getTxnDateTime()) <= 3) {
                    if (Objects.nonNull(irfRequestObj.getReimbAttribute()) && irfRequestObj.getReimbAttribute() == 'B' && irfRequestObj.getMcc().matches("5541|5542")) {
                        irfResultVo = this.getVisaIrfRate_uae(irfRequestObj, "INDUSTRY FEE PROGRAM", irfRequestObj.getMcc(), irfRequestObj.getCardProduct(), irfRequestObj.getQualifierIndicator());
                        if (Objects.nonNull(irfResultVo) && irfResultVo.getIrdSerNumber() != null) {
                            VisaIrfCalculation.log.info("-- Credit PETROL INDUSTRY FEE PROGRAM (Industry Fee Program  - Petrol)--");
                            return irfResultVo;
                        }
                    }
                    if (irfRequestObj.getMcc().matches("5511|5521") && irfRequestObj.getTxnAmount() * exchangeRate <= 10000.0) {
                        irfRequestObj.setTxnLimitIndicator('B');
                    }
                    irfResultVo = this.getVisaIrfRate_uae(irfRequestObj, "INDUSTRY FEE PROGRAM", irfRequestObj.getMcc(), irfRequestObj.getCardProduct(), irfRequestObj.getQualifierIndicator());
                    if (Objects.nonNull(irfResultVo) && irfResultVo.getIrdSerNumber() != null) {
                        VisaIrfCalculation.log.info("-- Credit INDUSTRY FEE PROGRAM (Industry Fee Program - ALL Others)--");
                        return irfResultVo;
                    }
                    if (irfRequestObj.getCardProduct().matches("F2") && irfRequestObj.getTxnAmount() < 200.0) {
                        irfRequestObj.setTxnLimitIndicator('B');
                    }
                    if (Objects.nonNull(irfRequestObj.getReimbAttribute()) && irfRequestObj.getReimbAttribute() == 'B' && Objects.nonNull(irfRequestObj.getPosEntryMode()) && irfRequestObj.getPosEntryMode().matches("05|07|90|91")) {
                        irfResultVo = this.getVisaIrfRate_uae(irfRequestObj, "PROD-RATE", null, irfRequestObj.getCardProduct(), irfRequestObj.getQualifierIndicator());
                        if (Objects.nonNull(irfResultVo) && irfResultVo.getIrdSerNumber() != null) {
                            VisaIrfCalculation.log.info("-- Credit PROD-RATE (Product Rate Fee Program)--");
                            return irfResultVo;
                        }
                    }
                    if (Objects.nonNull(irfRequestObj.getPosEntryMode()) && irfRequestObj.getPosEntryMode().matches("01|10")) {
                        irfResultVo = this.getVisaIrfRate_uae(irfRequestObj, "ALT-RATE", null, irfRequestObj.getCardProduct(), irfRequestObj.getQualifierIndicator());
                        if (Objects.nonNull(irfResultVo) && irfResultVo.getIrdSerNumber() != null) {
                            VisaIrfCalculation.log.info("-- Credit ALT-RATE (Alternative Rate Fee Program)--");
                            return irfResultVo;
                        }
                    }
                }
                if (irfRequestObj.getCardProduct().matches("F2") && irfRequestObj.getTxnAmount() < 200.0) {
                    irfRequestObj.setTxnLimitIndicator('B');
                }
                irfResultVo = this.getVisaIrfRate_uae(irfRequestObj, "ACQ-DGR", null, irfRequestObj.getCardProduct(), irfRequestObj.getQualifierIndicator());
                if (Objects.nonNull(irfResultVo) && irfResultVo.getIrdSerNumber() != null) {
                    VisaIrfCalculation.log.info("-- Credit ACQ-DGR (Acquirer Downgrade Fee Program) --");
                    return irfResultVo;
                }
            }
        }
        catch (final Exception e) {
            VisaIrfCalculation.log.error("Error uae Irf Calculation :", (Throwable)e);
        }
        VisaIrfCalculation.log.info(" /-- No Match Found !! UAE Irf Calculation --/");
        return null;
    }
    
    public IRFResultVo omanIrfCalculation(final IRFRequestVo irfRequestObj) {
        VisaIrfCalculation.log.info("//------- omanIrfCalculation -------//");
        IRFResultVo irfResultVo = new IRFResultVo();
        try {
            final Double exchangeRate = Double.parseDouble(Objects.requireNonNull(this.env.getProperty("exchangeRateOMR"), "ExchangeRate code is Null"));
            if (this.getVisaTimeLines(irfRequestObj.getTxnDateTime()) <= 3) {
                if (Objects.nonNull(irfRequestObj.getReimbAttribute()) && irfRequestObj.getReimbAttribute() == 'D' && irfRequestObj.getMcc().matches("4214|8062|8641")) {
                    irfResultVo = this.getVisaIrfRate_oman(irfRequestObj, "PUBLIC SERVICES FEE PROGRAM", irfRequestObj.getMcc(), irfRequestObj.getCardProduct(), irfRequestObj.getQualifierIndicator());
                    if (Objects.nonNull(irfResultVo) && irfResultVo.getIrdSerNumber() != null) {
                        VisaIrfCalculation.log.info("-- PUBLIC SERVICES FEE PROGRAM (SPECIAL MERCHANT)--");
                        return irfResultVo;
                    }
                }
                if (Objects.nonNull(irfRequestObj.getReimbAttribute()) && irfRequestObj.getReimbAttribute() == 'B' && Objects.nonNull(irfRequestObj.getPosEntryMode()) && irfRequestObj.getPosEntryMode().matches("05|07|90|91") && "4121".equals(irfRequestObj.getMcc())) {
                    irfResultVo = this.getVisaIrfRate_oman(irfRequestObj, "INDUSTRY FEE PROGRAM TAXI", irfRequestObj.getMcc(), irfRequestObj.getCardProduct(), irfRequestObj.getQualifierIndicator());
                    if (Objects.nonNull(irfResultVo) && irfResultVo.getIrdSerNumber() != null) {
                        VisaIrfCalculation.log.info("-- INDUSTRY FEE PROGRAM TAXI FEE PROGRAM --");
                        return irfResultVo;
                    }
                }
                irfResultVo = this.getVisaIrfRate_oman(irfRequestObj, "INDUSTRY FEE PROGRAM", irfRequestObj.getMcc(), irfRequestObj.getCardProduct(), irfRequestObj.getQualifierIndicator());
                if (Objects.nonNull(irfResultVo) && irfResultVo.getIrdSerNumber() != null) {
                    VisaIrfCalculation.log.info("-- Credit INDUSTRY FEE PROGRAM (Industry Fee Program - ALL Others)--");
                    return irfResultVo;
                }
                if (irfRequestObj.getCardProduct().matches("F2") && irfRequestObj.getTxnAmount() < 20.0 && irfRequestObj.getCrDrIndicator() == 'P') {
                    irfRequestObj.setTxnLimitIndicator('B');
                }
                if (Objects.nonNull(irfRequestObj.getReimbAttribute()) && irfRequestObj.getReimbAttribute() == 'B' && Objects.nonNull(irfRequestObj.getPosEntryMode()) && irfRequestObj.getPosEntryMode().matches("05|07|90|91")) {
                    irfResultVo = this.getVisaIrfRate_oman(irfRequestObj, "PROD-RATE", null, irfRequestObj.getCardProduct(), irfRequestObj.getQualifierIndicator());
                    if (Objects.nonNull(irfResultVo) && irfResultVo.getIrdSerNumber() != null) {
                        VisaIrfCalculation.log.info("-- Credit PROD-RATE (Product Rate Fee Program)--");
                        return irfResultVo;
                    }
                }
                if (Objects.nonNull(irfRequestObj.getPosEntryMode()) && irfRequestObj.getPosEntryMode().matches("01|10")) {
                    irfResultVo = this.getVisaIrfRate_oman(irfRequestObj, "ALT-RATE", null, irfRequestObj.getCardProduct(), irfRequestObj.getQualifierIndicator());
                    if (Objects.nonNull(irfResultVo) && irfResultVo.getIrdSerNumber() != null) {
                        VisaIrfCalculation.log.info("-- Credit ALT-RATE (Alternative Rate Fee Program)--");
                        return irfResultVo;
                    }
                }
            }
            if (irfRequestObj.getCardProduct().matches("F2") && irfRequestObj.getTxnAmount() < 20.0 && irfRequestObj.getCrDrIndicator() == 'P') {
                irfRequestObj.setTxnLimitIndicator('B');
            }
            irfResultVo = this.getVisaIrfRate_oman(irfRequestObj, "ACQ-DGR", null, irfRequestObj.getCardProduct(), irfRequestObj.getQualifierIndicator());
            if (Objects.nonNull(irfResultVo) && irfResultVo.getIrdSerNumber() != null) {
                VisaIrfCalculation.log.info("-- Credit ACQ-DGR (Acquirer Downgrade Fee Program) --");
                return irfResultVo;
            }
        }
        catch (final Exception e) {
            VisaIrfCalculation.log.error("Error OMAN Irf Calculation :", (Throwable)e);
        }
        VisaIrfCalculation.log.info(" /-- No Match Found !! OMAN Irf Calculation --/");
        return null;
    }
    
    public IRFResultVo meaIrfCalculation(final IRFRequestVo irfRequestObj) {
        VisaIrfCalculation.log.info("//------- meaIrfCalculation -------//");
        try {
            if (Objects.nonNull(irfRequestObj.getReimbAttribute()) && irfRequestObj.getReimbAttribute() == 'B' && irfRequestObj.getPosEntryMode().matches("02|05|07|90|91|95") && (irfRequestObj.getResponseCode().matches("Y1|Y3") || StringUtils.length((CharSequence)irfRequestObj.getAuthCode()) == 6) && this.getVisaTimeLines(irfRequestObj.getTxnDateTime()) <= 3) {
                final IRFResultVo irfResultVo = this.getVisaIrfRate(irfRequestObj, "PROD-RATE", irfRequestObj.getCardProduct(), "Product Rate Fee Program");
                if (Objects.nonNull(irfResultVo) && irfResultVo.getIrdSerNumber() != null) {
                    return irfResultVo;
                }
            }
            if (Objects.nonNull(irfRequestObj.getReimbAttribute()) && irfRequestObj.getReimbAttribute() == 'B' && !irfRequestObj.getPosEntryMode().matches("02|05|07|90|91|95") && StringUtils.length((CharSequence)irfRequestObj.getAuthCode()) == 6 && this.getVisaTimeLines(irfRequestObj.getTxnDateTime()) <= 3) {
                final IRFResultVo irfResultVo = this.getVisaIrfRate(irfRequestObj, "ALT-RATE", irfRequestObj.getCardProduct(), "Alternative Rate Fee Program");
                if (Objects.nonNull(irfResultVo) && irfResultVo.getIrdSerNumber() != null) {
                    return irfResultVo;
                }
            }
            final IRFResultVo irfResultVo = this.getVisaIrfRate(irfRequestObj, "ACQ-DGR", irfRequestObj.getCardProduct(), "Acquirer Downgrade Fee Program");
            if (Objects.nonNull(irfResultVo) && irfResultVo.getIrdSerNumber() != null) {
                return irfResultVo;
            }
        }
        catch (final Exception e) {
            VisaIrfCalculation.log.error("Error: MEA Irf Calculation :", (Throwable)e);
        }
        VisaIrfCalculation.log.info(" /--No match found !! MEA IRF Calculation completed --/");
        return null;
    }
    
    public IRFResultVo interationalIrfCalculation(final IRFRequestVo irfRequestObj) {
        VisaIrfCalculation.log.info("//------- interationalIrfCalculation -------//");
        try {
            if (irfRequestObj.getCardProduct().equalsIgnoreCase("P") && irfRequestObj.getCountryCode().matches("JP|FR|RE|YT|GF")) {
                irfRequestObj.setQualifierIndicator('P');
                if (irfRequestObj.getIssuerRegion() == '4' && irfRequestObj.getCountryCode().matches("JP")) {
                    irfRequestObj.setIssuerRegion('J');
                }
            }
            if (Objects.nonNull(irfRequestObj.getReimbAttribute()) && irfRequestObj.getReimbAttribute() == 'B' && irfRequestObj.getPosEntryMode().matches("02|05|07|90|91|95") && (StringUtils.length((CharSequence)irfRequestObj.getAuthCode()) == 6 || irfRequestObj.getResponseCode().matches("Y1|Y3")) && this.getVisaTimeLines(irfRequestObj.getTxnDateTime()) <= 3) {
                final IRFResultVo irfResultVo = this.getVisaIrfRate(irfRequestObj, "BASE-FEE", irfRequestObj.getCardProduct(), "BASE Fee Programs");
                if (Objects.nonNull(irfResultVo) && irfResultVo.getIrdSerNumber() != null) {
                    return irfResultVo;
                }
            }
            if (Objects.nonNull(irfRequestObj.getReimbAttribute()) && irfRequestObj.getReimbAttribute() == 'B' && !irfRequestObj.getPosEntryMode().matches("02|05|07|90|91|95") && (StringUtils.length((CharSequence)irfRequestObj.getAuthCode()) == 6 || irfRequestObj.getResponseCode().matches("Y1|Y3")) && this.getVisaTimeLines(irfRequestObj.getTxnDateTime()) <= 3) {
                final IRFResultVo irfResultVo = this.getVisaIrfRate(irfRequestObj, "ALT-FEE", irfRequestObj.getCardProduct(), "Alternative Rate Fee Program");
                if (Objects.nonNull(irfResultVo) && irfResultVo.getIrdSerNumber() != null) {
                    return irfResultVo;
                }
            }
            IRFResultVo irfResultVo = this.getVisaIrfRate(irfRequestObj, "ACQ-DGR", irfRequestObj.getCardProduct(), "Acquirer Downgrade Fee Program");
            if (Objects.nonNull(irfResultVo) && irfResultVo.getIrdSerNumber() != null) {
                return irfResultVo;
            }
            irfRequestObj.setTxnLimitIndicator('A');
            irfRequestObj.setQualifierIndicator('N');
            irfResultVo = this.getVisaIrfRate(irfRequestObj, "UNCAT", "UN", "UNCATEGORIZED");
            return irfResultVo;
        }
        catch (final Exception e) {
            VisaIrfCalculation.log.error("Error: Interational IrfCalculation :", (Throwable)e);
            VisaIrfCalculation.log.info(" /-- No match Found !! Interational IrfCalculation completed --/");
            return null;
        }
    }
    
    public IRFResultVo getVisaIrfRate_uae(final IRFRequestVo irfRequestObj, final String fpType, final String mcc, final String cardProduct, final Character qualifierIndicator) {
        Double irfAmount = 0.0;
        Double irfFixed = 0.0;
        Double irfPercent = 0.0;
        Double irfMaximum = 0.0;
        Double irfMinimum = 0.0;
        final IRFResultVo irdResult = new IRFResultVo();
        try {
            final Double exchangeRate = Double.parseDouble(Objects.requireNonNull(this.env.getProperty("exchangeRateAED"), "ExchangeRate code is Null"));
            VisaIrfProgramEntity visaIrfProgramData = new VisaIrfProgramEntity();
            if (Objects.nonNull(mcc) && Objects.nonNull(cardProduct)) {
                visaIrfProgramData = this.visaIrfProgramRepo.findByRegionAndCardTypeAndFpTypeAndMccAndFpValueAndTxnLimitIndicatorAndQualifierIndicator(irfRequestObj.getProgRegion(), irfRequestObj.getCrDrIndicator(), fpType, mcc.trim(), cardProduct.trim(), irfRequestObj.getTxnLimitIndicator(), qualifierIndicator);
            }
            else if (Objects.isNull(mcc) && Objects.isNull(cardProduct)) {
                visaIrfProgramData = this.visaIrfProgramRepo.findByRegionAndCardTypeAndFpTypeAndTxnLimitIndicator(irfRequestObj.getProgRegion(), irfRequestObj.getCrDrIndicator(), fpType, irfRequestObj.getTxnLimitIndicator());
            }
            else if (Objects.nonNull(mcc) && Objects.isNull(cardProduct)) {
                visaIrfProgramData = this.visaIrfProgramRepo.findByRegionAndCardTypeAndFpTypeAndMccAndTxnLimitIndicator(irfRequestObj.getProgRegion(), irfRequestObj.getCrDrIndicator(), fpType, mcc.trim(), irfRequestObj.getTxnLimitIndicator());
            }
            else if (Objects.nonNull(cardProduct) && Objects.isNull(mcc)) {
                visaIrfProgramData = this.visaIrfProgramRepo.findByRegionAndCardTypeAndFpTypeAndFpValueAndTxnLimitIndicatorAndQualifierIndicator(irfRequestObj.getProgRegion(), irfRequestObj.getCrDrIndicator(), fpType, cardProduct.trim(), irfRequestObj.getTxnLimitIndicator(), qualifierIndicator);
            }
            if (Objects.nonNull(visaIrfProgramData) && Objects.nonNull(visaIrfProgramData.getSerNumber())) {
                VisaIrfCalculation.log.info("#IRF Result >> Desc:: " + visaIrfProgramData.getFpDesc() + ", Mcc:: " + mcc + ", FpType:: " + cardProduct + ", QualifierIndicator :" + qualifierIndicator);
                irfFixed = ((visaIrfProgramData.getFixed() == null) ? 0.0 : visaIrfProgramData.getFixed());
                irfPercent = ((visaIrfProgramData.getPercent() == null) ? 0.0 : visaIrfProgramData.getPercent());
                irfMaximum = ((visaIrfProgramData.getMaximum() == null) ? 0.0 : visaIrfProgramData.getMaximum());
                irfMinimum = ((visaIrfProgramData.getMinimum() == null) ? 0.0 : visaIrfProgramData.getMinimum());
                if (Objects.nonNull(mcc) && mcc.matches("5511|5521")) {
                    irfFixed *= exchangeRate;
                }
                if (Objects.nonNull(mcc) && mcc.matches("5541|5542")) {
                    irfMinimum *= exchangeRate;
                }
                irfAmount = irfFixed + irfRequestObj.getTxnAmount() * irfPercent * 0.01;
                if (irfAmount < irfMinimum) {
                    irfAmount = irfMinimum;
                }
                if (irfAmount > irfMaximum) {
                    irfAmount = irfMaximum;
                }
                irdResult.setIrdSerNumber(visaIrfProgramData.getSerNumber());
                irdResult.setIrdCode(irfRequestObj.getIrdCode());
                irdResult.setIrfPercentage(irfPercent);
                irdResult.setIrfFixed(irfFixed);
                irdResult.setIrfAmount(irfAmount);
                irdResult.setIrfAmountUSD(irfAmount * exchangeRate);
                irdResult.setDomIntlFlag((irfRequestObj.getCardDomIntlFlag() == null) ? 'I' : irfRequestObj.getCardDomIntlFlag());
                irdResult.setCardType(irfRequestObj.getCrDrIndicatorActual());
                irdResult.setIrfDesc(visaIrfProgramData.getFpDesc());
                irdResult.setIrfCountry(irfRequestObj.getCountryCode());
                return irdResult;
            }
        }
        catch (final Exception e) {
            VisaIrfCalculation.log.error("", (Throwable)e);
        }
        return irdResult;
    }
    
    public IRFResultVo getVisaIrfRate_oman(final IRFRequestVo irfRequestObj, final String fpType, final String mcc, final String cardProduct, final Character qualifierIndicator) {
        Double irfAmount = 0.0;
        Double irfFixed = 0.0;
        Double irfPercent = 0.0;
        Double irfMaximum = 0.0;
        Double irfMinimum = 0.0;
        Double irfDollarAmount = 0.0;
        final IRFResultVo irdResult = new IRFResultVo();
        try {
            final Double exchangeRate = Double.parseDouble(Objects.requireNonNull(this.env.getProperty("exchangeRateOMR"), "ExchangeRate code is Null"));
            VisaIrfProgramEntity visaIrfProgramData = new VisaIrfProgramEntity();
            if (Objects.nonNull(mcc) && Objects.nonNull(cardProduct)) {
                visaIrfProgramData = this.visaIrfProgramRepo.findByRegionAndCardTypeAndFpTypeAndMccAndFpValueAndTxnLimitIndicatorAndQualifierIndicator(irfRequestObj.getProgRegion(), irfRequestObj.getCrDrIndicator(), fpType, mcc.trim(), cardProduct.trim(), irfRequestObj.getTxnLimitIndicator(), qualifierIndicator);
            }
            else if (Objects.nonNull(cardProduct) && Objects.isNull(mcc)) {
                visaIrfProgramData = this.visaIrfProgramRepo.findByRegionAndCardTypeAndFpTypeAndFpValueAndTxnLimitIndicatorAndQualifierIndicator(irfRequestObj.getProgRegion(), irfRequestObj.getCrDrIndicator(), fpType, cardProduct.trim(), irfRequestObj.getTxnLimitIndicator(), qualifierIndicator);
            }
            if (Objects.nonNull(visaIrfProgramData) && Objects.nonNull(visaIrfProgramData.getSerNumber())) {
                VisaIrfCalculation.log.info("#IRF Result >> Desc:: " + visaIrfProgramData.getFpDesc() + ", Mcc:: " + mcc + ", FpType:: " + cardProduct + ", QualifierIndicator :" + qualifierIndicator);
                irfFixed = ((visaIrfProgramData.getFixed() == null) ? 0.0 : visaIrfProgramData.getFixed());
                irfPercent = ((visaIrfProgramData.getPercent() == null) ? 0.0 : visaIrfProgramData.getPercent());
                irfMaximum = ((visaIrfProgramData.getMaximum() == null) ? 0.0 : visaIrfProgramData.getMaximum());
                irfMinimum = ((visaIrfProgramData.getMinimum() == null) ? 0.0 : visaIrfProgramData.getMinimum());
                irfAmount = irfFixed + irfRequestObj.getTxnAmount() * irfPercent * 0.01;
                if (Objects.nonNull(mcc) && mcc.trim().matches("5511|5521") && cardProduct.trim().matches("C|F|Q|P|I|I2|N|N1|F2")) {
                    irfDollarAmount = irfAmount * exchangeRate;
                }
                if (irfAmount < irfMinimum) {
                    irfAmount = irfMinimum;
                }
                if (Objects.nonNull(mcc) && mcc.trim().matches("5511|5521") && cardProduct.trim().matches("C|F|Q|P|I|I2|N|N1|F2")) {
                    if (irfDollarAmount > irfMaximum) {
                        irfAmount = irfMaximum / exchangeRate;
                    }
                }
                else if (irfAmount > irfMaximum) {
                    irfAmount = irfMaximum;
                }
                irdResult.setIrdSerNumber(visaIrfProgramData.getSerNumber());
                irdResult.setIrdCode(irfRequestObj.getIrdCode());
                irdResult.setIrfPercentage(irfPercent);
                irdResult.setIrfFixed(irfFixed);
                irdResult.setIrfAmount(irfAmount);
                irdResult.setIrfAmountUSD(irfAmount * exchangeRate);
                irdResult.setDomIntlFlag((irfRequestObj.getCardDomIntlFlag() == null) ? 'I' : irfRequestObj.getCardDomIntlFlag());
                irdResult.setCardType(irfRequestObj.getCrDrIndicatorActual());
                irdResult.setIrfDesc(visaIrfProgramData.getFpDesc());
                irdResult.setIrfCountry(irfRequestObj.getCountryCode());
                return irdResult;
            }
        }
        catch (final Exception e) {
            VisaIrfCalculation.log.error("", (Throwable)e);
        }
        return irdResult;
    }
    
    public IRFResultVo getVisaIrfRate(final IRFRequestVo irfRequestObj, final String fpType, final String fpValue, final String programType) {
        final IRFResultVo irdResult = new IRFResultVo();
        Double irfAmount = 0.0;
        Double irfFixed = 0.0;
        Double irfPercent = 0.0;
        Double irfMaximum = 0.0;
        final Double exchangeRate = Double.parseDouble(Objects.requireNonNull(this.env.getProperty("exchangeRate"), "ExchangeRate code is Null"));
        try {
            VisaIrfProgramEntity visaIrfProgramData = new VisaIrfProgramEntity();
            irdResult.setCardType(irfRequestObj.getCrDrIndicator());
            if (programType.matches("Ecom Non-authenticated Merchant|E-Commerce Fee Program Authenticated Secure|Chip Acquirer Fee Program|Chip Issuer Fee Program ELECTRONIC")) {
                visaIrfProgramData = this.visaIrfProgramRepo.findByRegionAndCardTypeAndFpTypeAndTxnLimitIndicator(irfRequestObj.getProgRegion(), irfRequestObj.getCrDrIndicator(), fpType, irfRequestObj.getTxnLimitIndicator());
                if (visaIrfProgramData == null) {
                    VisaIrfCalculation.log.info("irfRequestObj.getIssuerRegion():" + irfRequestObj.getIssuerRegion());
                    visaIrfProgramData = this.visaIrfProgramRepo.findByRegionAndCardTypeAndFpTypeAndTxnLimitIndicator(irfRequestObj.getProgRegion(), irfRequestObj.getCrDrIndicator(), fpType, irfRequestObj.getIssuerRegion());
                }
            }
            else {
                visaIrfProgramData = this.visaIrfProgramRepo.findByRegionAndCardTypeAndFpTypeAndFpValueAndTxnLimitIndicatorAndQualifierIndicator(irfRequestObj.getProgRegion(), irfRequestObj.getCrDrIndicator(), fpType, fpValue.trim(), irfRequestObj.getTxnLimitIndicator(), irfRequestObj.getQualifierIndicator());
                if (visaIrfProgramData == null) {
                    VisaIrfCalculation.log.info("irfRequestObj.getIssuerRegion():" + irfRequestObj.getIssuerRegion());
                    visaIrfProgramData = this.visaIrfProgramRepo.findByRegionAndCardTypeAndFpTypeAndFpValueAndTxnLimitIndicatorAndQualifierIndicator(irfRequestObj.getProgRegion(), irfRequestObj.getCrDrIndicator(), fpType, fpValue.trim(), irfRequestObj.getIssuerRegion(), irfRequestObj.getQualifierIndicator());
                }
            }
            if (Objects.nonNull(visaIrfProgramData) && Objects.nonNull(visaIrfProgramData.getSerNumber())) {
                VisaIrfCalculation.log.info("#IRF Result >> ProgramType :: " + programType + ", Desc:: " + visaIrfProgramData.getFpDesc() + ", FpValue:: " + fpValue + ", FpType:: " + fpType + " QualifierIndicator :" + irfRequestObj.getQualifierIndicator());
                irfFixed = ((visaIrfProgramData.getFixed() == null) ? 0.0 : visaIrfProgramData.getFixed());
                irfPercent = ((visaIrfProgramData.getPercent() == null) ? 0.0 : visaIrfProgramData.getPercent());
                irfMaximum = ((visaIrfProgramData.getMaximum() == null) ? 0.0 : visaIrfProgramData.getMaximum());
                if (programType.equals("Industry Progarmme Others") && irfRequestObj.getMcc().matches("5511|5521") && irfRequestObj.getTxnLimitIndicator() == 'A') {
                    irfRequestObj.setTxnAmount(irfRequestObj.getTxnAmount() - 10000.0);
                }
                irfAmount = irfFixed + irfRequestObj.getTxnAmount() * irfPercent * 0.01;
                if (irfMaximum > 0.0 && irfAmount > irfMaximum) {
                    irfAmount = irfMaximum;
                }
                if (programType.equals("Industry Progarmme Petrol") && irfAmount < 0.05) {
                    irfAmount = 0.05;
                }
                irdResult.setIrdSerNumber(visaIrfProgramData.getSerNumber());
                irdResult.setIrdCode(irfRequestObj.getIrdCode());
                irdResult.setIrfPercentage(irfPercent);
                irdResult.setIrfFixed(irfFixed);
                irdResult.setIrfAmount(irfAmount);
                irdResult.setIrfAmountUSD(irfAmount * exchangeRate);
                irdResult.setDomIntlFlag((irfRequestObj.getCardDomIntlFlag() == null) ? 'I' : irfRequestObj.getCardDomIntlFlag());
                irdResult.setCardType(irfRequestObj.getCrDrIndicator());
                irdResult.setIrfDesc(visaIrfProgramData.getFpDesc());
                irdResult.setIrfCountry(irfRequestObj.getCountryCode());
                irdResult.setIrfMinAmount(visaIrfProgramData.getMinimum());
                irdResult.setIrfMaxAmount(visaIrfProgramData.getMaximum());
                return irdResult;
            }
        }
        catch (final Exception e) {
            VisaIrfCalculation.log.error("Error :getVisaIrfRate :", (Throwable)e);
        }
        return irdResult;
    }
    
    public int getVisaTimeLines(LocalDateTime txnDateTIme) {
        int offDayCount = 0;
        int intTimeLine = 0;
        try {
            final LocalDateTime systemDate = LocalDateTime.now();
            intTimeLine = (int)ChronoUnit.DAYS.between(txnDateTIme, systemDate);
            while (!txnDateTIme.isAfter(systemDate)) {
                if (txnDateTIme.getDayOfWeek() == DayOfWeek.SUNDAY || (txnDateTIme.getMonthValue() == 12 && txnDateTIme.getDayOfMonth() == 25)) {
                    ++offDayCount;
                }
                txnDateTIme = txnDateTIme.plusDays(1L);
            }
            intTimeLine -= offDayCount;
            return Math.max(intTimeLine, 0);
        }
        catch (final Exception e) {
            VisaIrfCalculation.log.error("Error :getMcTimeLines :", (Throwable)e);
            return 0;
        }
    }
    
    public VisaIrfCalculation(final VisaIssAccRangeRepo visaIssAccRangeRepo, final VisaIrfProgramRepo visaIrfProgramRepo, final Environment env) {
        this.visaIssAccRangeRepo = visaIssAccRangeRepo;
        this.visaIrfProgramRepo = visaIrfProgramRepo;
        this.env = env;
    }
    
    static {
        log = LogManager.getLogger((Class)VisaIrfCalculation.class);
    }
}
