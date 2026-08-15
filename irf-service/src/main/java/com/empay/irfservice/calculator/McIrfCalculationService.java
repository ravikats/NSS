package com.empay.irfservice.calculator;

import com.empay.common.entities.IpmDetailsView;
import com.empay.common.entities.McIssAcqRangeEntity;
import com.empay.common.entities.McIrfParamsEntity;
import com.empay.common.entities.McOverrideRatesEntity;
import com.empay.common.entities.McProductMappingEntity;
import com.empay.common.irf.IrfTxnData;
import com.empay.common.irf.McIrfCalculator;
import com.empay.common.repo.IpmDetailsViewRepo;
import com.empay.common.repo.McIssAcqRangeRepo;
import com.empay.common.repo.McIrfParamsRepo;
import com.empay.common.repo.McOverrideRatesRepo;
import com.empay.common.repo.McProductMappingRepo;
import com.empay.common.vo.IRFResultVo;
import com.empay.common.vo.MCOverRideResultVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

/**
 * Mastercard (UAE/Oman) IRF calculation, ported from the TLF
 * {@code UAEMcIRFCalculation} (full UAE+OMAN path, OMR exchange rates, issuer-region
 * handling, R999 refund override) merged with the MPGS variant (populates
 * {@code pan} on the {@code MC_IRF_PARAMS} audit row).
 *
 * <p>Adaptations to the shared {@link IrfTxnData} contract:
 * <ul>
 *   <li>transaction serial number is read from {@link IrfTxnData#getSerialNumber()}
 *       (drives the {@code MC_IRF_PARAMS} insert key + the {@code IPM_DETAILS_VIEW}
 *       read-back);</li>
 *   <li>{@code txnId} from {@link IrfTxnData#getTxnId()} (trace flag + audit row);</li>
 *   <li>{@code oprtEnvironment} is passed through as-is — {@code IrfTxnData} carries a
 *       single {@code Character} (the first char of the ISO operating-environment code,
 *       i.e. the MPGS semantics). The TLF ISO-code→MC-code normalisation map is dropped
 *       because the raw two-digit string is no longer available at this layer;</li>
 *   <li>card-input/ch-auth/card-capture abilities are normalised with the TLF helper
 *       maps (MPGS persisted them raw).</li>
 * </ul>
 */
@Service
public class McIrfCalculationService implements McIrfCalculator {

    private static final Logger log = LoggerFactory.getLogger(McIrfCalculationService.class);

    private final McIssAcqRangeRepo issRangeRepo;
    private final IpmDetailsViewRepo ipmDetailsViewRepo;
    private final McOverrideRatesRepo mcOverrideRatesRepo;
    private final McIrfParamsRepo mcIrfParamsRepo;
    private final Environment env;
    private final McProductMappingRepo productMappingRepo;

    public McIrfCalculationService(McIssAcqRangeRepo issRangeRepo,
                                   IpmDetailsViewRepo ipmDetailsViewRepo,
                                   McOverrideRatesRepo mcOverrideRatesRepo,
                                   McIrfParamsRepo mcIrfParamsRepo,
                                   Environment env,
                                   McProductMappingRepo productMappingRepo) {
        this.issRangeRepo = issRangeRepo;
        this.ipmDetailsViewRepo = ipmDetailsViewRepo;
        this.mcOverrideRatesRepo = mcOverrideRatesRepo;
        this.mcIrfParamsRepo = mcIrfParamsRepo;
        this.env = env;
        this.productMappingRepo = productMappingRepo;
    }

    @Override
    @Transactional
    public IRFResultVo calculate(Integer insCode, IrfTxnData txnData, String cardNumber) {
        double txnAmount = 0.0;
        double irfFixed = 0.0;
        double irfAmount = 0.0;
        double irfMax = 0.0;
        double irfPercentage = 0.0;
        String network = "";
        String txnType = "";
        String gcmsProdId = "";
        String cardProgId = "";
        final String overRideId = "";
        String countryCode = "";
        String mcc = "";
        String irfDesc = "";
        String morIrd = "";
        char cardDomIntlFlag = 'I';
        int magstripeFlag = 0;
        int approvalCodeFlag = 0;
        int irdSerNumber = 0;
        int traceIdFlag = 0;
        int mcAssigIdFlag = 0;
        char limitIndicator = 'A';
        char cardCrDrInd = ' ';
        char progRegion = ' ';
        char issuerRegion = ' ';
        double irfMinAmount = 0.0;
        double irfMaxAmount = 0.0;
        String irdCode = null;
        final IRFResultVo irdResult = new IRFResultVo();
        final Double exchangeRateAED = Double.parseDouble(
                Objects.requireNonNull(env.getProperty("irf.exchange-rate-aed"), "ExchangeRateAED code is Null"));
        final Double exchangeRateOMR = Double.parseDouble(
                Objects.requireNonNull(env.getProperty("irf.exchange-rate-omr"), "ExchangeRateOMR code is Null"));
        char countryCodeFlag = ' ';
        try {
            log.info("UAE Mc Irf Calculation Start: ");
            network = txnData.getNetwork();
            txnType = txnData.getTxnCode();
            mcc = txnData.getMcc();
            if (Objects.nonNull(txnData.getApprovalCode())) {
                approvalCodeFlag = 1;
            }
            final String acqCode = txnData.getAcqInstConCode();
            countryCodeFlag = switch (acqCode) {
                case "784" -> 'U';
                case "512" -> 'O';
                default -> {
                    log.info("Acq Ins code is not AED or OMR: {}", acqCode);
                    yield ' ';
                }
            };
            if (Objects.nonNull(txnData.getPosEntryMode())
                    && (txnData.getPosEntryMode().startsWith("02") || txnData.getPosEntryMode().startsWith("90")
                    || txnData.getPosEntryMode().startsWith("05") || txnData.getPosEntryMode().startsWith("07")
                    || txnData.getPosEntryMode().startsWith("08"))) {
                magstripeFlag = 1;
            }
            if (Objects.nonNull(txnData.getTxnId())) {
                traceIdFlag = 1;
            }
            if (Objects.nonNull(txnData.getMaid())) {
                mcAssigIdFlag = 1;
            }
            final McIssAcqRangeEntity issAccRangeEntity =
                    issRangeRepo.findTopByIssRangeLowLessThanEqualAndIssRangeHighGreaterThanEqualAndActiveCodeOrderByPriorityCodeAscEffectiveDateDesc(
                            cardNumber, cardNumber, 'A');
            if (Objects.nonNull(issAccRangeEntity)) {
                gcmsProdId = issAccRangeEntity.getGcmsProductId();
                cardProgId = issAccRangeEntity.getCardProgId();
                countryCode = issAccRangeEntity.getCountryCode();
                issuerRegion = issAccRangeEntity.getRegion();
                if (Objects.nonNull(countryCode) && countryCode.equals("784") && countryCodeFlag == 'U') {
                    progRegion = 'I';
                    cardDomIntlFlag = 'D';
                } else if (Objects.nonNull(countryCode) && countryCode.equals("512") && countryCodeFlag == 'O') {
                    progRegion = 'I';
                    cardDomIntlFlag = 'D';
                } else if (issuerRegion == 'E') {
                    progRegion = 'E';
                    cardDomIntlFlag = 'I';
                } else {
                    progRegion = 'R';
                    cardDomIntlFlag = 'I';
                }
                if (Objects.nonNull(cardProgId) && cardProgId.toUpperCase().matches("MSI|DMC")) {
                    cardCrDrInd = 'D';
                    if (countryCodeFlag == 'U') {
                        txnAmount = txnData.getTxnAmount();
                    } else if (countryCodeFlag == 'O') {
                        txnAmount = txnData.getSetlAmount();
                    }
                } else {
                    cardCrDrInd = 'C';
                    txnAmount = txnData.getSetlAmount();
                }
                if (Objects.nonNull(txnType) && txnType.equals("01") && cardCrDrInd == 'C') {
                    txnType = "09";
                }
                if (Objects.nonNull(network) && network.equals("MDS")) {
                    irdCode = null;
                    log.info(" MC IRF Cal : MDS network found Null IRD ");
                } else {
                    final McIrfParamsEntity irfParamEntity = mcIrfParamInsert(
                            insCode, txnData, progRegion, approvalCodeFlag, magstripeFlag, traceIdFlag,
                            mcAssigIdFlag, issuerRegion, cardProgId, gcmsProdId, txnAmount);
                    mcIrfParamsRepo.saveAndFlush(irfParamEntity);
                    final IpmDetailsView ipmData = ipmDetailsViewRepo.findByTxnSerNumber(txnData.getSerialNumber());
                    if (Objects.nonNull(ipmData)) {
                        if (Objects.nonNull(ipmData.getSerialNumber())) {
                            irdSerNumber = ipmData.getSerialNumber();
                        }
                        if (Objects.nonNull(ipmData.getIrd())) {
                            irdCode = ipmData.getIrd();
                        }
                        if (Objects.nonNull(ipmData.getRatePercent())) {
                            irfPercentage = ipmData.getRatePercent();
                        }
                        if (Objects.nonNull(ipmData.getIrfMinAmount())) {
                            irfMinAmount = ipmData.getIrfMinAmount();
                        }
                        if (Objects.nonNull(ipmData.getIrfMaxAmount())) {
                            irfMaxAmount = ipmData.getIrfMaxAmount();
                        }
                    } else {
                        log.info(" IPM View Details Null ");
                    }
                }
                if (Objects.isNull(irdCode)) {
                    irdCode = "85";
                    irfPercentage = 2.5;
                }
                morIrd = irdCode;
                Integer morSerNumber = null;
                double morPrecentage = 0.0;
                double morFixed = 0.0;
                double morMax = 999999.0;
                log.info(" MC IRF Cal morIrd:" + morIrd);
                log.info(" MC IRF Cal cardCrDrInd:" + cardCrDrInd);
                log.info(" MC IRF Cal gcmsProdId:" + gcmsProdId);
                if (countryCodeFlag == 'U') {
                    final McProductMappingEntity prodtmappingData = productMappingRepo.findByIrdAndGcmsProductId(morIrd, gcmsProdId);
                    if (prodtmappingData != null) {
                        cardCrDrInd = prodtmappingData.getCardType();
                    }
                }
                if (Objects.nonNull(cardCrDrInd) && cardCrDrInd == 'C') {
                    txnAmount = txnData.getSetlAmount();
                } else if (countryCodeFlag == 'U') {
                    txnAmount = txnData.getTxnAmount();
                } else if (countryCodeFlag == 'O') {
                    txnAmount = txnData.getSetlAmount();
                }
                MCOverRideResultVo overRideResultVo = new MCOverRideResultVo();
                if (cardDomIntlFlag == 'D') {
                    limitIndicator = getLimitIndicator(txnAmount, mcc, morIrd, cardCrDrInd, countryCodeFlag);
                    if (countryCodeFlag == 'O' && morIrd.equals("61") && mcc.equals("9999") && gcmsProdId.matches("MEO|MCO|MWO|MAB|MIO")) {
                        limitIndicator = 'A';
                        mcc = gcmsProdId;
                    }
                    irfDesc = "IRF Check1";
                    overRideResultVo = getDomOverRide(overRideId, morIrd, cardCrDrInd, mcc, limitIndicator);
                    if (Objects.isNull(overRideResultVo) || Objects.isNull(overRideResultVo.getMorSerNumber())) {
                        irfDesc = "IRF Check2";
                        overRideResultVo = getDomOverRide(overRideId, morIrd, cardCrDrInd, gcmsProdId, limitIndicator);
                        if (Objects.isNull(overRideResultVo) || Objects.isNull(overRideResultVo.getMorSerNumber())) {
                            irfDesc = "IRF Check3";
                            limitIndicator = getLimitIndicator(txnAmount, "9999", morIrd, cardCrDrInd, countryCodeFlag);
                            overRideResultVo = getDomOverRide(overRideId, morIrd, cardCrDrInd, "9999", limitIndicator);
                        }
                    }
                } else if (Objects.nonNull(txnType) && txnType.equals("20")) {
                    irfDesc = "International Refund";
                    log.info("International Refund");
                    overRideResultVo = getIntlRefundOverRide(morIrd);
                }
                if (Objects.nonNull(overRideResultVo)) {
                    morSerNumber = overRideResultVo.getMorSerNumber();
                    morPrecentage = overRideResultVo.getMorPercentage();
                    morFixed = overRideResultVo.getMorFixed();
                    morMax = overRideResultVo.getMorMax();
                }
                if (Objects.nonNull(morSerNumber)) {
                    irfPercentage = morPrecentage;
                    irfFixed = morFixed;
                    irfMax = morMax;
                    irfDesc = "IRF Amount Override " + morSerNumber;
                    log.info(irfDesc);
                }
                irfAmount = irfFixed + txnAmount * irfPercentage * 0.01;
                if (irfMax > 0.0 && irfAmount > irfMax) {
                    irfAmount = irfMax;
                }
                irdResult.setIrdSerNumber(irdSerNumber);
                irdResult.setIrdCode(irdCode);
                irdResult.setIrfPercentage(irfPercentage);
                irdResult.setIrfFixed(irfFixed);
                if (countryCodeFlag == 'U') {
                    if (cardCrDrInd == 'C') {
                        irdResult.setIrfAmount(irfAmount / exchangeRateAED);
                        irdResult.setIrfAmountUSD(irfAmount);
                    } else {
                        irdResult.setIrfAmount(irfAmount);
                        irdResult.setIrfAmountUSD(irfAmount * exchangeRateAED);
                    }
                } else if (countryCodeFlag == 'O') {
                    irdResult.setIrfAmount(irfAmount / exchangeRateOMR);
                    irdResult.setIrfAmountUSD(irfAmount);
                }
                irdResult.setDomIntlFlag(cardDomIntlFlag);
                irdResult.setCardType(cardCrDrInd);
                irdResult.setGcmsProductID(gcmsProdId);
                irdResult.setIrfDesc(irfDesc);
                irdResult.setIrfMinAmount(irfMinAmount);
                irdResult.setIrfMaxAmount(irfMaxAmount);
                log.info("UAE Mc Irf Calculation END: ");
                return irdResult;
            }
            log.info(" MC IRF Cal : No Valid CardNumber found ");
            irdResult.setIrdSerNumber(0);
            irdResult.setIrdCode("85");
            irdResult.setIrfPercentage(0.0);
            irdResult.setIrfFixed(0.0);
            irdResult.setIrfAmount(0.0);
            irdResult.setIrfAmountUSD(0.0);
            irdResult.setIrfDesc("Crypt API Failed.Invalid cardnumber");
            return irdResult;
        } catch (Exception e) {
            log.error("Error ,Get McIrf UAE !! :", e);
            return null;
        }
    }

    public char getLimitIndicator(double txnAmount, String mcc, String morIrd, char cardCrDrInd, char countryCodeFlag) {
        final char limitIndicator = 'A';
        if (countryCodeFlag == 'U') {
            if (txnAmount < 1000.0 && "6513".equals(mcc)) {
                return 'B';
            }
            if (txnAmount <= 1000.0 && "6051".equals(mcc)) {
                return 'B';
            }
            if (cardCrDrInd == 'C' && txnAmount < 10000.0 && mcc.matches("4468|5013|5511|5521|5531|5532|5533|5551|5561|5571|5592|5599|7531|7534|7535|7538")) {
                return 'B';
            }
            if (morIrd.equals("61") && "9999".equals(mcc) && txnAmount < 15000.0) {
                return 'B';
            }
            if (morIrd.equals("61") && "6513".equals(mcc) && txnAmount < 5000.0) {
                return 'B';
            }
        } else if (countryCodeFlag == 'O' && morIrd.equals("61") && "9999".equals(mcc) && txnAmount < 5000.0) {
            return 'B';
        }
        return limitIndicator;
    }

    public MCOverRideResultVo getDomOverRide(String overRideId, String morIrd, Character cardCrDrInd, String mcc, Character limitIndicator) {
        final MCOverRideResultVo overRideResultVo = new MCOverRideResultVo();
        McOverrideRatesEntity mcOvrRdRateEntity;
        if (Objects.isNull(overRideId) || overRideId.isEmpty()) {
            mcOvrRdRateEntity = mcOverrideRatesRepo.findByIrdAndCardTypeAndMccAndTxnLimitIndicator(morIrd, cardCrDrInd, mcc, limitIndicator);
        } else {
            mcOvrRdRateEntity = mcOverrideRatesRepo.findByIrdAndOverRideIDAndCardTypeAndMccAndTxnLimitIndicator(morIrd, overRideId, cardCrDrInd, mcc, limitIndicator);
        }
        if (Objects.nonNull(mcOvrRdRateEntity)) {
            overRideResultVo.setMorSerNumber(mcOvrRdRateEntity.getSerNumber());
            overRideResultVo.setMorPercentage(Objects.isNull(mcOvrRdRateEntity.getPercent()) ? 0.0 : (double) mcOvrRdRateEntity.getPercent());
            overRideResultVo.setMorFixed((mcOvrRdRateEntity.getFixed() == null) ? 0.0 : (double) mcOvrRdRateEntity.getFixed());
            overRideResultVo.setMorMax((mcOvrRdRateEntity.getMax() == null) ? 999999.0 : (double) mcOvrRdRateEntity.getMax());
        }
        return overRideResultVo;
    }

    public MCOverRideResultVo getIntlRefundOverRide(String morIrd) {
        final MCOverRideResultVo overRideResultVo = new MCOverRideResultVo();
        final McOverrideRatesEntity mcOvrRdRateEntity = mcOverrideRatesRepo.findByIrdAndOverRideIDAndMccAndTxnLimitIndicator(morIrd, "REFUND", "R999", 'A');
        if (Objects.nonNull(mcOvrRdRateEntity)) {
            overRideResultVo.setMorSerNumber(mcOvrRdRateEntity.getSerNumber());
            overRideResultVo.setMorPercentage(Objects.isNull(mcOvrRdRateEntity.getPercent()) ? 0.0 : (double) mcOvrRdRateEntity.getPercent());
            overRideResultVo.setMorFixed(Objects.isNull(mcOvrRdRateEntity.getFixed()) ? 0.0 : (double) mcOvrRdRateEntity.getFixed());
            overRideResultVo.setMorMax(Objects.isNull(mcOvrRdRateEntity.getMax()) ? 999999.0 : (double) mcOvrRdRateEntity.getMax());
        }
        return overRideResultVo;
    }

    public int getMcTimeLines(LocalDateTime txnDateTIme) {
        int offDayCount = 0;
        int intTimeLine = 0;
        try {
            final LocalDateTime systemDate = LocalDateTime.now();
            intTimeLine = (int) ChronoUnit.DAYS.between(txnDateTIme, systemDate);
            while (!txnDateTIme.isAfter(systemDate)) {
                if (txnDateTIme.getDayOfWeek() == DayOfWeek.SUNDAY || (txnDateTIme.getMonthValue() == 12 && txnDateTIme.getDayOfMonth() == 25)) {
                    ++offDayCount;
                }
                txnDateTIme = txnDateTIme.plusDays(1L);
            }
            intTimeLine -= offDayCount;
            return Math.max(intTimeLine, 0);
        } catch (Exception e) {
            log.error("getMcTimeLines :", e);
            return 0;
        }
    }

    public McIrfParamsEntity mcIrfParamInsert(Integer insCode, IrfTxnData txnData, Character progRegion, Integer apprCodeFlag,
                                              Integer magStripeFlag, Integer traceIdFlag, Integer mcAssignIdFlag,
                                              Character issuerRegion, String cardProgId, String gcmsProdId, Double txnAmount) {
        final McIrfParamsEntity.McIrfParamsEntityBuilder mcIrfParambuilder = McIrfParamsEntity.builder();
        try {
            mcIrfParambuilder.serNumber(txnData.getSerialNumber())
                    .insCode(insCode)
                    .pan(txnData.getCardNumber())
                    .txnAmount(txnAmount)
                    .cashBackAmount(Objects.isNull(txnData.getCashBackAmount()) ? 0.0 : txnData.getCashBackAmount())
                    .terminalType(txnData.getTerminalType())
                    .txnDateTime(txnData.getTxnDateTime())
                    .mcc(txnData.getMcc())
                    .network(txnData.getNetwork())
                    .txnType(txnData.getTxnCode())
                    .approvalCode(txnData.getApprovalCode())
                    .txnId(txnData.getTxnId())
                    .posEntryMode(txnData.getPosEntryMode())
                    .serviceCode(txnData.getServiceCode())
                    .cardInputAbility(getCardInputCapability(txnData.getCardInputAbility()))
                    .chAuthAbility(getCardHoldAuthCapability(txnData.getChAuthAbility()))
                    .cardCaptureAbility(getCardCaptureCapability(txnData.getCardCaptureAbility()))
                    .oprtEnvironment(txnData.getOprtEnvironment())
                    .chPresent(getCHPresent(txnData.getChPresent()))
                    .cardPresent(getCardPresent(txnData.getCardPresent()))
                    .cardInputMode(txnData.getCardInputMode())
                    .merType(txnData.getMeCategoryType())
                    .maid(txnData.getMaid())
                    .progRegion(progRegion)
                    .timeLine(getMcTimeLines(txnData.getTxnDateTime()))
                    .apprCodeFlag(apprCodeFlag)
                    .magStripeFlag(magStripeFlag)
                    .traceIdFlag(traceIdFlag)
                    .mcAssignIdFlag(mcAssignIdFlag)
                    .issuerRegion(issuerRegion)
                    .cardProgId(cardProgId)
                    .gcmsProdId(gcmsProdId);
            if (Objects.nonNull(txnData.getMotoEcomIndicator())) {
                mcIrfParambuilder.ecomIndicator(StringUtils.right(txnData.getMotoEcomIndicator(), 1));
            }
        } catch (Exception e) {
            log.error("Error mcIrfParamInsert() :", e);
        }
        return mcIrfParambuilder.build();
    }

    private Character getCardInputCapability(Character cardInputCapability) {
        char charValue = '\0';
        if (null == cardInputCapability) {
            charValue = '0';
        } else {
            charValue = switch ((char) cardInputCapability) {
                case '0', '1', '2', '6' -> cardInputCapability;
                case '3' -> 'M';
                case '4' -> 'A';
                case '5' -> 'D';
                case '7' -> 'B';
                case '8' -> 'C';
                case '9' -> '5';
                default -> '0';
            };
        }
        return charValue;
    }

    private Character getCardHoldAuthCapability(Character chAuthAbility) {
        char c = '\0';
        if (null == chAuthAbility) {
            c = '9';
        } else {
            c = switch ((char) chAuthAbility) {
                case '0' -> '9';
                case '1' -> '1';
                case '2' -> '0';
                case '8' -> '5';
                case '4' -> '6';
                default -> '9';
            };
        }
        return c;
    }

    private Character getCardCaptureCapability(Character cardCaptureAbility) {
        char c = '\0';
        if (null == cardCaptureAbility) {
            c = '9';
        } else {
            c = switch ((char) cardCaptureAbility) {
                case '0' -> '0';
                case '1' -> '1';
                default -> '9';
            };
        }
        return c;
    }

    private Character getCardPresent(Character cardPresent) {
        char c = '\0';
        if (null == cardPresent) {
            c = '0';
        } else {
            c = switch ((char) cardPresent) {
                case '1' -> '0';
                case '0' -> '1';
                default -> '0';
            };
        }
        return c;
    }

    private Character getCHPresent(Character chPresent) {
        char c = '\0';
        if (null == chPresent) {
            c = '0';
        } else {
            c = switch ((char) chPresent) {
                case '0' -> '0';
                case '1' -> '1';
                case '2' -> '2';
                case '3' -> '3';
                case '4' -> '4';
                case '5' -> '5';
                default -> '0';
            };
        }
        return c;
    }
}
