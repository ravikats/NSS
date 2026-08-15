package com.empay.irfservice.calculator;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import com.empay.common.entities.VisaIrfProgramEntity;
import com.empay.common.entities.VisaIssAccRangeEntity;
import com.empay.common.repo.VisaIrfProgramRepo;
import com.empay.common.repo.VisaIssAccRangeRepo;
import com.empay.common.vo.IRFRequestVo;
import com.empay.common.vo.IRFResultVo;
import com.empay.common.irf.IrfTxnData;
import com.empay.common.irf.VisaIrfCalculator;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * Relocated Visa IRF calculation, adapted to consume {@link IrfTxnData} (built by
 * the calling service from its own transaction entity and sent over REST) instead
 * of {@code PosTransactionEntity}. All reference-data lookups come from the shared
 * {@code irf-common} repositories.
 *
 * TODO: once the MPGS/TLF per-network calculators are also relocated, remove the
 * duplicated copies there. Until then this single source-of-truth fixes bug #3
 * (missing max-cap on UAE/Oman) and bug #4 (empty-result writes) for Visa.
 */
@Service
public class VisaIrfCalculationService implements VisaIrfCalculator {

    private static final Logger log = LoggerFactory.getLogger(VisaIrfCalculationService.class);

    private final VisaIssAccRangeRepo visaIssAccRangeRepo;
    private final VisaIrfProgramRepo visaIrfProgramRepo;
    private final Environment env;

    public VisaIrfCalculationService(VisaIssAccRangeRepo visaIssAccRangeRepo,
                                     VisaIrfProgramRepo visaIrfProgramRepo,
                                     Environment env) {
        this.visaIssAccRangeRepo = visaIssAccRangeRepo;
        this.visaIrfProgramRepo = visaIrfProgramRepo;
        this.env = env;
    }

    @Override
    @Transactional
    public IRFResultVo calculate(Integer insCode, IrfTxnData txnData, String cardNumber) {
        return getVisaIrf(insCode, txnData, cardNumber);
    }

    @Transactional
    public IRFResultVo getVisaIrf(final Integer insCode, final IrfTxnData txnData, String cardNumber) {
        IRFResultVo resultVo = new IRFResultVo();
        char countryCodeFlag = ' ';
        try {
            final IRFRequestVo irfRequestObj = buildIrfRequest(insCode, txnData);
            final String acqInstConCode = txnData.getAcqInstConCode();
            countryCodeFlag = switch (acqInstConCode) {
                case "784" -> 'U';
                case "512" -> 'O';
                default -> {
                    log.info("Acq Ins code is not AED or OMR: {}", acqInstConCode);
                    yield ' ';
                }
            };
            if (countryCodeFlag == 'O') {
                if (txnData.getReImbursementAttribute() == null || txnData.getReImbursementAttribute() == ' ') {
                    irfRequestObj.setReimbAttribute('B');
                } else {
                    irfRequestObj.setReimbAttribute(txnData.getReImbursementAttribute());
                }
            } else {
                irfRequestObj.setReimbAttribute('B');
            }
            irfRequestObj.setPan(txnData.getEncCardNumber());
            irfRequestObj.setTxnDateTime(txnData.getTxnDateTime());
            irfRequestObj.setTxnAmount(txnData.getTxnAmount());
            irfRequestObj.setMcc(txnData.getMcc());
            irfRequestObj.setFeePgmIndicator(txnData.getFeePgmIndicator());
            irfRequestObj.setTerminalCapability(txnData.getTrlCapabilities() == null
                    ? txnData.getTerminalCapability() : String.valueOf(txnData.getTrlCapabilities()));
            irfRequestObj.setAuthCode(txnData.getApprovalCode());
            irfRequestObj.setResponseCode(txnData.getResponseCode());
            irfRequestObj.setMotoEcomIndicator(txnData.getMotoEcomIndicator());
            irfRequestObj.setFwdinstCode(null);
            irfRequestObj.setMvv(txnData.getMvv());
            irfRequestObj.setPosEntryMode(StringUtils.left(txnData.getPosEntryMode(), 2));
            if (cardNumber != null) {
                cardNumber = StringUtils.left(cardNumber, 9);
            }
            final VisaIssAccRangeEntity visaBinRange =
                    visaIssAccRangeRepo.findTopByIssRangeLowLessThanEqualAndIssRangeHighGreaterThanEqual(
                            cardNumber, cardNumber);
            if (!Objects.nonNull(visaBinRange)) {
                log.info(" /------ No Bin Data Found --------/");
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
            final char indicator = switch ((char) visaBinRange.getCrDrIndicator()) {
                case 'R' -> 'P';
                case 'H' -> 'C';
                default -> visaBinRange.getCrDrIndicator();
            };
            irfRequestObj.setCrDrIndicator(indicator);
            irfRequestObj.setCrDrIndicatorActual(indicator);
            irfRequestObj.setCountryCode(visaBinRange.getCountryAlphaCode());
            irfRequestObj.setCardProduct(Objects.isNull(visaBinRange.getCardProduct())
                    ? "AO" : visaBinRange.getCardProduct().trim());
            irfRequestObj.setIrdCode(irfRequestObj.getCardProduct());
            if (Objects.nonNull(visaBinRange.getProductSubTytpe())
                    && visaBinRange.getProductSubTytpe().equalsIgnoreCase("TK")) {
                irfRequestObj.setQualifierIndicator('Q');
            } else {
                irfRequestObj.setQualifierIndicator('N');
            }
            if ("AE".equals(irfRequestObj.getCountryCode()) && countryCodeFlag == 'U') {
                irfRequestObj.setCardDomIntlFlag('D');
                irfRequestObj.setProgRegion('I');
            } else if ("OM".equals(irfRequestObj.getCountryCode()) && countryCodeFlag == 'O') {
                irfRequestObj.setCardDomIntlFlag('D');
                irfRequestObj.setProgRegion('I');
            } else if (irfRequestObj.getIssuerRegion() == '6') {
                irfRequestObj.setProgRegion('E');
            } else {
                irfRequestObj.setProgRegion('R');
            }
            log.info("IRF Request : RRN :{} :{}", txnData.getRrn(), irfRequestObj);
            if (irfRequestObj.getProgRegion() == 'I' && countryCodeFlag == 'U') {
                resultVo = uaeIrfCalculation(irfRequestObj);
            } else if (irfRequestObj.getProgRegion() == 'I' && countryCodeFlag == 'O') {
                resultVo = omanIrfCalculation(irfRequestObj);
            } else if (irfRequestObj.getProgRegion() == 'E') {
                resultVo = meaIrfCalculation(irfRequestObj);
            } else if (irfRequestObj.getProgRegion() == 'R') {
                resultVo = interationalIrfCalculation(irfRequestObj);
            }
            if (resultVo != null) {
                log.info("IRF Result : {}", resultVo);
            } else {
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
            log.info("VISA IRF Completed ");
        } catch (final Exception e) {
            log.error("", e);
            return null;
        }
        return resultVo;
    }

    private IRFRequestVo buildIrfRequest(Integer insCode, IrfTxnData txnData) {
        IRFRequestVo irfRequestObj = new IRFRequestVo();
        irfRequestObj.setInsCode(insCode);
        irfRequestObj.setTerminalType("POS");
        irfRequestObj.setCardDomIntlFlag('I');
        irfRequestObj.setTxnLimitIndicator('A');
        irfRequestObj.setProgRegion('R');
        return irfRequestObj;
    }

    public IRFResultVo uaeIrfCalculation(final IRFRequestVo irfRequestObj) {
        log.info("//------- uaeIrfCalculation -------//");
        IRFResultVo irfResultVo = new IRFResultVo();
        try {
            final Double exchangeRate = Double.parseDouble(
                    Objects.requireNonNull(env.getProperty("irf.exchange-rate-aed"), "ExchangeRate code is Null"));
            if (irfRequestObj.getCrDrIndicator() == 'D' && irfRequestObj.getCardProduct().matches("G|K|S|G1|G3|G4|G5")) {
                irfRequestObj.setCrDrIndicator('C');
            }
            if (irfRequestObj.getCrDrIndicator() != 'C' && irfRequestObj.getQualifierIndicator() == 'Q') {
                irfRequestObj.setQualifierIndicator('N');
            }
            if (Objects.nonNull(irfRequestObj) && (irfRequestObj.getCrDrIndicator() == 'D' || irfRequestObj.getCrDrIndicator() == 'P')) {
                irfResultVo = getVisaIrfRate_uae(irfRequestObj, "INDUSTRY FEE PROGRAM", irfRequestObj.getMcc(), null, null);
                if (Objects.nonNull(irfResultVo) && irfResultVo.getIrdSerNumber() != null) {
                    log.info("-- Industry Fee Program – Consumer Debit and Prepaid --");
                    return irfResultVo;
                }
                if (Objects.nonNull(irfRequestObj.getPosEntryMode())
                        && irfRequestObj.getPosEntryMode().matches("02|03|05|06|07|90|91|95")
                        && Objects.isNull(irfRequestObj.getMotoEcomIndicator())) {
                    irfResultVo = getVisaIrfRate_uae(irfRequestObj, "CP", null, irfRequestObj.getCardProduct(), irfRequestObj.getQualifierIndicator());
                    if (Objects.nonNull(irfResultVo) && irfResultVo.getIrdSerNumber() != null) {
                        log.info("-- Card Present Fee Program – Consumer Debit and Prepaid --");
                        return irfResultVo;
                    }
                }
                irfResultVo = getVisaIrfRate_uae(irfRequestObj, "CNP", null, null, null);
                if (Objects.nonNull(irfResultVo) && irfResultVo.getIrdSerNumber() != null) {
                    log.info("-- Card Not Present Fee Program – Consumer Debit and Prepaid --");
                    return irfResultVo;
                }
            } else {
                if (getVisaTimeLines(irfRequestObj.getTxnDateTime()) <= 3) {
                    if (Objects.nonNull(irfRequestObj.getReimbAttribute()) && irfRequestObj.getReimbAttribute() == 'B'
                            && irfRequestObj.getMcc().matches("5541|5542")) {
                        irfResultVo = getVisaIrfRate_uae(irfRequestObj, "INDUSTRY FEE PROGRAM", irfRequestObj.getMcc(), irfRequestObj.getCardProduct(), irfRequestObj.getQualifierIndicator());
                        if (Objects.nonNull(irfResultVo) && irfResultVo.getIrdSerNumber() != null) {
                            log.info("-- Credit PETROL INDUSTRY FEE PROGRAM (Industry Fee Program - Petrol)--");
                            return irfResultVo;
                        }
                    }
                    if (irfRequestObj.getMcc().matches("5511|5521") && irfRequestObj.getTxnAmount() * exchangeRate <= 10000.0) {
                        irfRequestObj.setTxnLimitIndicator('B');
                    }
                    irfResultVo = getVisaIrfRate_uae(irfRequestObj, "INDUSTRY FEE PROGRAM", irfRequestObj.getMcc(), irfRequestObj.getCardProduct(), irfRequestObj.getQualifierIndicator());
                    if (Objects.nonNull(irfResultVo) && irfResultVo.getIrdSerNumber() != null) {
                        log.info("-- Credit INDUSTRY FEE PROGRAM (Industry Fee Program - ALL Others)--");
                        return irfResultVo;
                    }
                    if (irfRequestObj.getCardProduct().matches("F2") && irfRequestObj.getTxnAmount() < 200.0) {
                        irfRequestObj.setTxnLimitIndicator('B');
                    }
                    if (Objects.nonNull(irfRequestObj.getReimbAttribute()) && irfRequestObj.getReimbAttribute() == 'B'
                            && Objects.nonNull(irfRequestObj.getPosEntryMode())
                            && irfRequestObj.getPosEntryMode().matches("05|07|90|91")) {
                        irfResultVo = getVisaIrfRate_uae(irfRequestObj, "PROD-RATE", null, irfRequestObj.getCardProduct(), irfRequestObj.getQualifierIndicator());
                        if (Objects.nonNull(irfResultVo) && irfResultVo.getIrdSerNumber() != null) {
                            log.info("-- Credit PROD-RATE (Product Rate Fee Program)--");
                            return irfResultVo;
                        }
                    }
                    if (Objects.nonNull(irfRequestObj.getPosEntryMode())
                            && irfRequestObj.getPosEntryMode().matches("01|10")) {
                        irfResultVo = getVisaIrfRate_uae(irfRequestObj, "ALT-RATE", null, irfRequestObj.getCardProduct(), irfRequestObj.getQualifierIndicator());
                        if (Objects.nonNull(irfResultVo) && irfResultVo.getIrdSerNumber() != null) {
                            log.info("-- Credit ALT-RATE (Alternative Rate Fee Program)--");
                            return irfResultVo;
                        }
                    }
                }
                if (irfRequestObj.getCardProduct().matches("F2") && irfRequestObj.getTxnAmount() < 200.0) {
                    irfRequestObj.setTxnLimitIndicator('B');
                }
                irfResultVo = getVisaIrfRate_uae(irfRequestObj, "ACQ-DGR", null, irfRequestObj.getCardProduct(), irfRequestObj.getQualifierIndicator());
                if (Objects.nonNull(irfResultVo) && irfResultVo.getIrdSerNumber() != null) {
                    log.info("-- Credit ACQ-DGR (Acquirer Downgrade Fee Program) --");
                    return irfResultVo;
                }
            }
        } catch (final Exception e) {
            log.error("Error uae Irf Calculation :", e);
        }
        log.info(" /-- No Match Found !! UAE Irf Calculation --/");
        return null;
    }

    public IRFResultVo omanIrfCalculation(final IRFRequestVo irfRequestObj) {
        log.info("//------- omanIrfCalculation -------//");
        IRFResultVo irfResultVo = new IRFResultVo();
        try {
            final Double exchangeRate = Double.parseDouble(
                    Objects.requireNonNull(env.getProperty("irf.exchange-rate-omr"), "ExchangeRate code is Null"));
            if (getVisaTimeLines(irfRequestObj.getTxnDateTime()) <= 3) {
                if (Objects.nonNull(irfRequestObj.getReimbAttribute()) && irfRequestObj.getReimbAttribute() == 'D'
                        && irfRequestObj.getMcc().matches("4214|8062|8641")) {
                    irfResultVo = getVisaIrfRate_oman(irfRequestObj, "PUBLIC SERVICES FEE PROGRAM", irfRequestObj.getMcc(), irfRequestObj.getCardProduct(), irfRequestObj.getQualifierIndicator());
                    if (Objects.nonNull(irfResultVo) && irfResultVo.getIrdSerNumber() != null) {
                        log.info("-- PUBLIC SERVICES FEE PROGRAM (SPECIAL MERCHANT)--");
                        return irfResultVo;
                    }
                }
                if (Objects.nonNull(irfRequestObj.getReimbAttribute()) && irfRequestObj.getReimbAttribute() == 'B'
                        && Objects.nonNull(irfRequestObj.getPosEntryMode())
                        && irfRequestObj.getPosEntryMode().matches("05|07|90|91")
                        && "4121".equals(irfRequestObj.getMcc())) {
                    irfResultVo = getVisaIrfRate_oman(irfRequestObj, "INDUSTRY FEE PROGRAM TAXI", irfRequestObj.getMcc(), irfRequestObj.getCardProduct(), irfRequestObj.getQualifierIndicator());
                    if (Objects.nonNull(irfResultVo) && irfResultVo.getIrdSerNumber() != null) {
                        log.info("-- INDUSTRY FEE PROGRAM TAXI FEE PROGRAM --");
                        return irfResultVo;
                    }
                }
                irfResultVo = getVisaIrfRate_oman(irfRequestObj, "INDUSTRY FEE PROGRAM", irfRequestObj.getMcc(), irfRequestObj.getCardProduct(), irfRequestObj.getQualifierIndicator());
                if (Objects.nonNull(irfResultVo) && irfResultVo.getIrdSerNumber() != null) {
                    log.info("-- Credit INDUSTRY FEE PROGRAM (Industry Fee Program - ALL Others)--");
                    return irfResultVo;
                }
                if (irfRequestObj.getCardProduct().matches("F2") && irfRequestObj.getTxnAmount() < 20.0 && irfRequestObj.getCrDrIndicator() == 'P') {
                    irfRequestObj.setTxnLimitIndicator('B');
                }
                if (Objects.nonNull(irfRequestObj.getReimbAttribute()) && irfRequestObj.getReimbAttribute() == 'B'
                        && Objects.nonNull(irfRequestObj.getPosEntryMode())
                        && irfRequestObj.getPosEntryMode().matches("05|07|90|91")) {
                    irfResultVo = getVisaIrfRate_oman(irfRequestObj, "PROD-RATE", null, irfRequestObj.getCardProduct(), irfRequestObj.getQualifierIndicator());
                    if (Objects.nonNull(irfResultVo) && irfResultVo.getIrdSerNumber() != null) {
                        log.info("-- Credit PROD-RATE (Product Rate Fee Program)--");
                        return irfResultVo;
                    }
                }
                if (Objects.nonNull(irfRequestObj.getPosEntryMode())
                        && irfRequestObj.getPosEntryMode().matches("01|10")) {
                    irfResultVo = getVisaIrfRate_oman(irfRequestObj, "ALT-RATE", null, irfRequestObj.getCardProduct(), irfRequestObj.getQualifierIndicator());
                    if (Objects.nonNull(irfResultVo) && irfResultVo.getIrdSerNumber() != null) {
                        log.info("-- Credit ALT-RATE (Alternative Rate Fee Program)--");
                        return irfResultVo;
                    }
                }
            }
            if (irfRequestObj.getCardProduct().matches("F2") && irfRequestObj.getTxnAmount() < 20.0 && irfRequestObj.getCrDrIndicator() == 'P') {
                irfRequestObj.setTxnLimitIndicator('B');
            }
            irfResultVo = getVisaIrfRate_oman(irfRequestObj, "ACQ-DGR", null, irfRequestObj.getCardProduct(), irfRequestObj.getQualifierIndicator());
            if (Objects.nonNull(irfResultVo) && irfResultVo.getIrdSerNumber() != null) {
                log.info("-- Credit ACQ-DGR (Acquirer Downgrade Fee Program) --");
                return irfResultVo;
            }
        } catch (final Exception e) {
            log.error("Error OMAN Irf Calculation :", e);
        }
        log.info(" /-- No Match Found !! OMAN Irf Calculation --/");
        return null;
    }

    public IRFResultVo meaIrfCalculation(final IRFRequestVo irfRequestObj) {
        log.info("//------- meaIrfCalculation -------//");
        try {
            if (Objects.nonNull(irfRequestObj.getReimbAttribute()) && irfRequestObj.getReimbAttribute() == 'B'
                    && irfRequestObj.getPosEntryMode().matches("02|05|07|90|91|95")
                    && (irfRequestObj.getResponseCode().matches("Y1|Y3")
                    || StringUtils.length(irfRequestObj.getAuthCode()) == 6)
                    && getVisaTimeLines(irfRequestObj.getTxnDateTime()) <= 3) {
                final IRFResultVo irfResultVo = getVisaIrfRate(irfRequestObj, "PROD-RATE", irfRequestObj.getCardProduct(), "Product Rate Fee Program");
                if (Objects.nonNull(irfResultVo) && irfResultVo.getIrdSerNumber() != null) {
                    return irfResultVo;
                }
            }
            if (Objects.nonNull(irfRequestObj.getReimbAttribute()) && irfRequestObj.getReimbAttribute() == 'B'
                    && !irfRequestObj.getPosEntryMode().matches("02|05|07|90|91|95")
                    && StringUtils.length(irfRequestObj.getAuthCode()) == 6
                    && getVisaTimeLines(irfRequestObj.getTxnDateTime()) <= 3) {
                final IRFResultVo irfResultVo = getVisaIrfRate(irfRequestObj, "ALT-RATE", irfRequestObj.getCardProduct(), "Alternative Rate Fee Program");
                if (Objects.nonNull(irfResultVo) && irfResultVo.getIrdSerNumber() != null) {
                    return irfResultVo;
                }
            }
            final IRFResultVo irfResultVo = getVisaIrfRate(irfRequestObj, "ACQ-DGR", irfRequestObj.getCardProduct(), "Acquirer Downgrade Fee Program");
            if (Objects.nonNull(irfResultVo) && irfResultVo.getIrdSerNumber() != null) {
                return irfResultVo;
            }
        } catch (final Exception e) {
            log.error("Error: MEA Irf Calculation :", e);
        }
        log.info(" /--No match found !! MEA IRF Calculation completed --/");
        return null;
    }

    public IRFResultVo interationalIrfCalculation(final IRFRequestVo irfRequestObj) {
        log.info("//------- interationalIrfCalculation -------//");
        try {
            if (irfRequestObj.getCardProduct().equalsIgnoreCase("P")
                    && irfRequestObj.getCountryCode().matches("JP|FR|RE|YT|GF")) {
                irfRequestObj.setQualifierIndicator('P');
                if (irfRequestObj.getIssuerRegion() == '4'
                        && irfRequestObj.getCountryCode().matches("JP")) {
                    irfRequestObj.setIssuerRegion('J');
                }
            }
            if (Objects.nonNull(irfRequestObj.getReimbAttribute()) && irfRequestObj.getReimbAttribute() == 'B'
                    && irfRequestObj.getPosEntryMode().matches("02|05|07|90|91|95")
                    && (StringUtils.length(irfRequestObj.getAuthCode()) == 6 || irfRequestObj.getResponseCode().matches("Y1|Y3"))
                    && getVisaTimeLines(irfRequestObj.getTxnDateTime()) <= 3) {
                final IRFResultVo irfResultVo = getVisaIrfRate(irfRequestObj, "BASE-FEE", irfRequestObj.getCardProduct(), "BASE Fee Programs");
                if (Objects.nonNull(irfResultVo) && irfResultVo.getIrdSerNumber() != null) {
                    return irfResultVo;
                }
            }
            if (Objects.nonNull(irfRequestObj.getReimbAttribute()) && irfRequestObj.getReimbAttribute() == 'B'
                    && !irfRequestObj.getPosEntryMode().matches("02|05|07|90|91|95")
                    && (StringUtils.length(irfRequestObj.getAuthCode()) == 6 || irfRequestObj.getResponseCode().matches("Y1|Y3"))
                    && getVisaTimeLines(irfRequestObj.getTxnDateTime()) <= 3) {
                final IRFResultVo irfResultVo = getVisaIrfRate(irfRequestObj, "ALT-FEE", irfRequestObj.getCardProduct(), "Alternative Rate Fee Program");
                if (Objects.nonNull(irfResultVo) && irfResultVo.getIrdSerNumber() != null) {
                    return irfResultVo;
                }
            }
            IRFResultVo irfResultVo = getVisaIrfRate(irfRequestObj, "ACQ-DGR", irfRequestObj.getCardProduct(), "Acquirer Downgrade Fee Program");
            if (Objects.nonNull(irfResultVo) && irfResultVo.getIrdSerNumber() != null) {
                return irfResultVo;
            }
            irfRequestObj.setTxnLimitIndicator('A');
            irfRequestObj.setQualifierIndicator('N');
            irfResultVo = getVisaIrfRate(irfRequestObj, "UNCAT", "UN", "UNCATEGORIZED");
            return irfResultVo;
        } catch (final Exception e) {
            log.error("Error: Interational IrfCalculation :", e);
            log.info(" /-- No match Found !! Interational IrfCalculation completed --/");
            return null;
        }
    }

    public IRFResultVo getVisaIrfRate_uae(final IRFRequestVo irfRequestObj, final String fpType, final String mcc,
                                          final String cardProduct, final Character qualifierIndicator) {
        Double irfAmount = 0.0;
        Double irfFixed = 0.0;
        Double irfPercent = 0.0;
        Double irfMaximum = 0.0;
        Double irfMinimum = 0.0;
        final IRFResultVo irdResult = new IRFResultVo();
        try {
            final Double exchangeRate = Double.parseDouble(
                    Objects.requireNonNull(env.getProperty("irf.exchange-rate-aed"), "ExchangeRate code is Null"));
            VisaIrfProgramEntity visaIrfProgramData = new VisaIrfProgramEntity();
            if (Objects.nonNull(mcc) && Objects.nonNull(cardProduct)) {
                visaIrfProgramData = visaIrfProgramRepo
                        .findByRegionAndCardTypeAndFpTypeAndMccAndFpValueAndTxnLimitIndicatorAndQualifierIndicator(
                                irfRequestObj.getProgRegion(), irfRequestObj.getCrDrIndicator(), fpType,
                                mcc.trim(), cardProduct.trim(), irfRequestObj.getTxnLimitIndicator(), qualifierIndicator);
            } else if (Objects.isNull(mcc) && Objects.isNull(cardProduct)) {
                visaIrfProgramData = visaIrfProgramRepo
                        .findByRegionAndCardTypeAndFpTypeAndTxnLimitIndicator(
                                irfRequestObj.getProgRegion(), irfRequestObj.getCrDrIndicator(), fpType,
                                irfRequestObj.getTxnLimitIndicator());
            } else if (Objects.nonNull(mcc) && Objects.isNull(cardProduct)) {
                visaIrfProgramData = visaIrfProgramRepo
                        .findByRegionAndCardTypeAndFpTypeAndMccAndTxnLimitIndicator(
                                irfRequestObj.getProgRegion(), irfRequestObj.getCrDrIndicator(), fpType,
                                mcc.trim(), irfRequestObj.getTxnLimitIndicator());
            } else if (Objects.nonNull(cardProduct) && Objects.isNull(mcc)) {
                visaIrfProgramData = visaIrfProgramRepo
                        .findByRegionAndCardTypeAndFpTypeAndFpValueAndTxnLimitIndicatorAndQualifierIndicator(
                                irfRequestObj.getProgRegion(), irfRequestObj.getCrDrIndicator(), fpType,
                                cardProduct.trim(), irfRequestObj.getTxnLimitIndicator(), qualifierIndicator);
            }
            if (Objects.nonNull(visaIrfProgramData) && Objects.nonNull(visaIrfProgramData.getSerNumber())) {
                log.info("#IRF Result >> Desc:: {}, Mcc:: {}, FpType:: {}, QualifierIndicator :{}",
                        visaIrfProgramData.getFpDesc(), mcc, cardProduct, qualifierIndicator);
                irfFixed = (visaIrfProgramData.getFixed() == null) ? 0.0 : visaIrfProgramData.getFixed();
                irfPercent = (visaIrfProgramData.getPercent() == null) ? 0.0 : visaIrfProgramData.getPercent();
                irfMaximum = (visaIrfProgramData.getMaximum() == null) ? 0.0 : visaIrfProgramData.getMaximum();
                irfMinimum = (visaIrfProgramData.getMinimum() == null) ? 0.0 : visaIrfProgramData.getMinimum();
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
        } catch (final Exception e) {
            log.error("", e);
        }
        return irdResult;
    }

    public IRFResultVo getVisaIrfRate_oman(final IRFRequestVo irfRequestObj, final String fpType, final String mcc,
                                           final String cardProduct, final Character qualifierIndicator) {
        Double irfAmount = 0.0;
        Double irfFixed = 0.0;
        Double irfPercent = 0.0;
        Double irfMaximum = 0.0;
        Double irfMinimum = 0.0;
        Double irfDollarAmount = 0.0;
        final IRFResultVo irdResult = new IRFResultVo();
        try {
            final Double exchangeRate = Double.parseDouble(
                    Objects.requireNonNull(env.getProperty("irf.exchange-rate-omr"), "ExchangeRate code is Null"));
            VisaIrfProgramEntity visaIrfProgramData = new VisaIrfProgramEntity();
            if (Objects.nonNull(mcc) && Objects.nonNull(cardProduct)) {
                visaIrfProgramData = visaIrfProgramRepo
                        .findByRegionAndCardTypeAndFpTypeAndMccAndFpValueAndTxnLimitIndicatorAndQualifierIndicator(
                                irfRequestObj.getProgRegion(), irfRequestObj.getCrDrIndicator(), fpType,
                                mcc.trim(), cardProduct.trim(), irfRequestObj.getTxnLimitIndicator(), qualifierIndicator);
            } else if (Objects.nonNull(cardProduct) && Objects.isNull(mcc)) {
                visaIrfProgramData = visaIrfProgramRepo
                        .findByRegionAndCardTypeAndFpTypeAndFpValueAndTxnLimitIndicatorAndQualifierIndicator(
                                irfRequestObj.getProgRegion(), irfRequestObj.getCrDrIndicator(), fpType,
                                cardProduct.trim(), irfRequestObj.getTxnLimitIndicator(), qualifierIndicator);
            }
            if (Objects.nonNull(visaIrfProgramData) && Objects.nonNull(visaIrfProgramData.getSerNumber())) {
                log.info("#IRF Result >> Desc:: {}, Mcc:: {}, FpType:: {}, QualifierIndicator :{}",
                        visaIrfProgramData.getFpDesc(), mcc, cardProduct, qualifierIndicator);
                irfFixed = (visaIrfProgramData.getFixed() == null) ? 0.0 : visaIrfProgramData.getFixed();
                irfPercent = (visaIrfProgramData.getPercent() == null) ? 0.0 : visaIrfProgramData.getPercent();
                irfMaximum = (visaIrfProgramData.getMaximum() == null) ? 0.0 : visaIrfProgramData.getMaximum();
                irfMinimum = (visaIrfProgramData.getMinimum() == null) ? 0.0 : visaIrfProgramData.getMinimum();
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
                } else if (irfAmount > irfMaximum) {
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
        } catch (final Exception e) {
            log.error("", e);
        }
        return irdResult;
    }

    public IRFResultVo getVisaIrfRate(final IRFRequestVo irfRequestObj, final String fpType, final String fpValue,
                                      final String programType) {
        final IRFResultVo irdResult = new IRFResultVo();
        Double irfAmount = 0.0;
        Double irfFixed = 0.0;
        Double irfPercent = 0.0;
        Double irfMaximum = 0.0;
        final Double exchangeRate = Double.parseDouble(
                Objects.requireNonNull(env.getProperty("irf.exchange-rate"), "ExchangeRate code is Null"));
        try {
            VisaIrfProgramEntity visaIrfProgramData = new VisaIrfProgramEntity();
            irdResult.setCardType(irfRequestObj.getCrDrIndicator());
            if (programType.matches("Ecom Non-authenticated Merchant|E-Commerce Fee Program Authenticated Secure|Chip Acquirer Fee Program|Chip Issuer Fee Program ELECTRONIC")) {
                visaIrfProgramData = visaIrfProgramRepo
                        .findByRegionAndCardTypeAndFpTypeAndTxnLimitIndicator(
                                irfRequestObj.getProgRegion(), irfRequestObj.getCrDrIndicator(), fpType,
                                irfRequestObj.getTxnLimitIndicator());
                if (visaIrfProgramData == null) {
                    log.info("irfRequestObj.getIssuerRegion(): {}", irfRequestObj.getIssuerRegion());
                    visaIrfProgramData = visaIrfProgramRepo
                            .findByRegionAndCardTypeAndFpTypeAndTxnLimitIndicator(
                                    irfRequestObj.getProgRegion(), irfRequestObj.getCrDrIndicator(), fpType,
                                    irfRequestObj.getIssuerRegion());
                }
            } else {
                visaIrfProgramData = visaIrfProgramRepo
                        .findByRegionAndCardTypeAndFpTypeAndFpValueAndTxnLimitIndicatorAndQualifierIndicator(
                                irfRequestObj.getProgRegion(), irfRequestObj.getCrDrIndicator(), fpType,
                                fpValue.trim(), irfRequestObj.getTxnLimitIndicator(), irfRequestObj.getQualifierIndicator());
                if (visaIrfProgramData == null) {
                    log.info("irfRequestObj.getIssuerRegion(): {}", irfRequestObj.getIssuerRegion());
                    visaIrfProgramData = visaIrfProgramRepo
                            .findByRegionAndCardTypeAndFpTypeAndFpValueAndTxnLimitIndicatorAndQualifierIndicator(
                                    irfRequestObj.getProgRegion(), irfRequestObj.getCrDrIndicator(), fpType,
                                    fpValue.trim(), irfRequestObj.getIssuerRegion(), irfRequestObj.getQualifierIndicator());
                }
            }
            if (Objects.nonNull(visaIrfProgramData) && Objects.nonNull(visaIrfProgramData.getSerNumber())) {
                log.info("#IRF Result >> ProgramType :: {}, Desc:: {}, FpValue:: {}, FpType:: {} QualifierIndicator :{}",
                        programType, visaIrfProgramData.getFpDesc(), fpValue, fpType, irfRequestObj.getQualifierIndicator());
                irfFixed = (visaIrfProgramData.getFixed() == null) ? 0.0 : visaIrfProgramData.getFixed();
                irfPercent = (visaIrfProgramData.getPercent() == null) ? 0.0 : visaIrfProgramData.getPercent();
                irfMaximum = (visaIrfProgramData.getMaximum() == null) ? 0.0 : visaIrfProgramData.getMaximum();
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
        } catch (final Exception e) {
            log.error("Error :getVisaIrfRate :", e);
        }
        return irdResult;
    }

    public int getVisaTimeLines(LocalDateTime txnDateTime) {
        int offDayCount = 0;
        try {
            final LocalDateTime systemDate = LocalDateTime.now();
            int intTimeLine = (int) ChronoUnit.DAYS.between(txnDateTime, systemDate);
            LocalDateTime cursor = txnDateTime;
            while (!cursor.isAfter(systemDate)) {
                if (cursor.getDayOfWeek() == DayOfWeek.SUNDAY
                        || (cursor.getMonthValue() == 12 && cursor.getDayOfMonth() == 25)) {
                    ++offDayCount;
                }
                cursor = cursor.plusDays(1L);
            }
            intTimeLine -= offDayCount;
            return Math.max(intTimeLine, 0);
        } catch (final Exception e) {
            log.error("Error :getVisaTimeLines :", e);
            return 0;
        }
    }
}
