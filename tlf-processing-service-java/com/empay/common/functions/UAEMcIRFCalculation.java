// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.common.functions;

import org.apache.logging.log4j.LogManager;
import org.apache.commons.lang3.StringUtils;
import java.time.DayOfWeek;
import java.time.chrono.ChronoLocalDateTime;
import java.time.temporal.Temporal;
import java.time.temporal.ChronoUnit;
import java.time.LocalDateTime;
import com.empay.entities.McOverrideRatesEntity;
import jakarta.transaction.Transactional;
import com.empay.common.entities.McProductMappingEntity;
import com.empay.entities.IpmDetailsView;
import com.empay.entities.McIrfParamsEntity;
import com.empay.common.entities.McIssAcqRangeEntity;
import com.empay.tlfprocessing.vo.MCOverRideResultVo;
import java.util.Objects;
import com.empay.tlfprocessing.vo.IRFResultVo;
import com.empay.entities.PosTransactionEntity;
import com.empay.common.repo.McProductMappingRepo;
import org.springframework.core.env.Environment;
import com.empay.cryptapi.CryptAPI;
import com.empay.repositories.McIrfParamsRepo;
import com.empay.repositories.McOverrideRatesRepo;
import com.empay.repositories.IpmDetailsViewRepo;
import com.empay.common.repo.MCIssAcqRangeRepo;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class UAEMcIRFCalculation
{
    private static final Logger log;
    private final MCIssAcqRangeRepo issRangeRepo;
    private final IpmDetailsViewRepo ipmDetailsViewRepo;
    private final McOverrideRatesRepo mcOverrideRatesRepo;
    private final McIrfParamsRepo mcIrfParamsRepo;
    private final CryptAPI cryptApi;
    private final Environment env;
    private final McProductMappingRepo productMappingRepo;
    
    @Transactional
    public IRFResultVo getMcIrfUAE(final Integer insCode, final PosTransactionEntity posData, final String cardNumber) {
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
        final Double exchangeRateAED = Double.parseDouble(Objects.requireNonNull(this.env.getProperty("exchangeRateAED"), "ExchangeRateAED code is Null"));
        final Double exchangeRateOMR = Double.parseDouble(Objects.requireNonNull(this.env.getProperty("exchangeRateOMR"), "ExchangeRateOMR code is Null"));
        char countryCodeFlag = ' ';
        try {
            UAEMcIRFCalculation.log.info("UAE Mc Irf Calculation Start: ");
            network = posData.getNetwork();
            txnType = posData.getTxnCode();
            mcc = posData.getMcc();
            if (Objects.nonNull(posData.getApprovalCode())) {
                approvalCodeFlag = 1;
            }
            final String acqInstConCode;
            final String acqCode = acqInstConCode = posData.getAcqInstConCode();
            countryCodeFlag = switch (acqInstConCode) {
                case "784" -> 85;
                case "512" -> 79;
                default -> {
                    UAEMcIRFCalculation.log.info("Acq Ins code is not AED or OMR: {}", (Object)acqCode);
                    yield 32;
                }
            };
            if (Objects.nonNull(posData.getPosEntryMode()) && (posData.getPosEntryMode().startsWith("02") || posData.getPosEntryMode().startsWith("90") || posData.getPosEntryMode().startsWith("05") || posData.getPosEntryMode().startsWith("07") || posData.getPosEntryMode().startsWith("08"))) {
                magstripeFlag = 1;
            }
            if (Objects.nonNull(posData.getTxnId())) {
                traceIdFlag = 1;
            }
            if (Objects.nonNull(posData.getMaid())) {
                mcAssigIdFlag = 1;
            }
            final McIssAcqRangeEntity issAccRangeEntity = this.issRangeRepo.findTopByIssRangeLowLessThanEqualAndIssRangeHighGreaterThanEqualAndActiveCodeOrderByPriorityCodeAscEffectiveDateDesc(cardNumber, cardNumber, 'A');
            if (Objects.nonNull(issAccRangeEntity)) {
                gcmsProdId = issAccRangeEntity.getGcmsProductId();
                cardProgId = issAccRangeEntity.getCardProgId();
                countryCode = issAccRangeEntity.getCountryCode();
                issuerRegion = issAccRangeEntity.getRegion();
                if (Objects.nonNull(countryCode) && countryCode.equals("784") && countryCodeFlag == 'U') {
                    progRegion = 'I';
                    cardDomIntlFlag = 'D';
                }
                else if (Objects.nonNull(countryCode) && countryCode.equals("512") && countryCodeFlag == 'O') {
                    progRegion = 'I';
                    cardDomIntlFlag = 'D';
                }
                else if (issuerRegion == 'E') {
                    progRegion = 'E';
                    cardDomIntlFlag = 'I';
                }
                else {
                    progRegion = 'R';
                    cardDomIntlFlag = 'I';
                }
                if (Objects.nonNull(cardProgId) && cardProgId.toUpperCase().matches("MSI|DMC")) {
                    cardCrDrInd = 'D';
                    if (countryCodeFlag == 'U') {
                        txnAmount = posData.getTxnAmount();
                    }
                    else if (countryCodeFlag == 'O') {
                        txnAmount = posData.getSetlAmount();
                    }
                }
                else {
                    cardCrDrInd = 'C';
                    txnAmount = posData.getSetlAmount();
                }
                if (Objects.nonNull(txnType) && txnType.equals("01") && cardCrDrInd == 'C') {
                    txnType = "09";
                }
                if (Objects.nonNull(network) && network.equals("MDS")) {
                    irdCode = null;
                    UAEMcIRFCalculation.log.info(" MC IRF Cal : MDS network found Null IRD ");
                }
                else {
                    final McIrfParamsEntity irfParamEntity = this.mcIrfParamInsert(insCode, posData, progRegion, approvalCodeFlag, magstripeFlag, traceIdFlag, mcAssigIdFlag, issuerRegion, cardProgId, gcmsProdId, txnAmount);
                    this.mcIrfParamsRepo.saveAndFlush((Object)irfParamEntity);
                    final IpmDetailsView ipmData = this.ipmDetailsViewRepo.findByTxnSerNumber(posData.getSerialNumber());
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
                    }
                    else {
                        UAEMcIRFCalculation.log.info(" IPM View Details Null ");
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
                UAEMcIRFCalculation.log.info(" MC IRF Cal morIrd:" + morIrd);
                UAEMcIRFCalculation.log.info(" MC IRF Cal cardCrDrInd:" + cardCrDrInd);
                UAEMcIRFCalculation.log.info(" MC IRF Cal gcmsProdId:" + gcmsProdId);
                if (countryCodeFlag == 'U') {
                    final McProductMappingEntity prodtmappingData = this.productMappingRepo.findByIrdAndGcmsProductId(morIrd, gcmsProdId);
                    if (prodtmappingData != null) {
                        cardCrDrInd = prodtmappingData.getCardType();
                    }
                }
                if (Objects.nonNull(cardCrDrInd) && cardCrDrInd == 'C') {
                    txnAmount = posData.getSetlAmount();
                }
                else if (countryCodeFlag == 'U') {
                    txnAmount = posData.getTxnAmount();
                }
                else if (countryCodeFlag == 'O') {
                    txnAmount = posData.getSetlAmount();
                }
                MCOverRideResultVo overRideResultVo = new MCOverRideResultVo();
                if (cardDomIntlFlag == 'D') {
                    limitIndicator = this.getLimitIndicator(txnAmount, mcc, morIrd, cardCrDrInd, countryCodeFlag);
                    if (countryCodeFlag == 'O' && morIrd.equals("61") && mcc.equals("9999") && gcmsProdId.matches("MEO|MCO|MWO|MAB")) {
                        limitIndicator = 'A';
                        mcc = gcmsProdId;
                    }
                    irfDesc = "IRF Check1";
                    overRideResultVo = this.getDomOverRide(overRideId, morIrd, cardCrDrInd, mcc, limitIndicator);
                    if (Objects.isNull(overRideResultVo) || Objects.isNull(overRideResultVo.getMorSerNumber())) {
                        irfDesc = "IRF Check2";
                        overRideResultVo = this.getDomOverRide(overRideId, morIrd, cardCrDrInd, gcmsProdId, limitIndicator);
                        if (Objects.isNull(overRideResultVo) || Objects.isNull(overRideResultVo.getMorSerNumber())) {
                            irfDesc = "IRF Check3";
                            limitIndicator = this.getLimitIndicator(txnAmount, "9999", morIrd, cardCrDrInd, countryCodeFlag);
                            overRideResultVo = this.getDomOverRide(overRideId, morIrd, cardCrDrInd, "9999", limitIndicator);
                        }
                    }
                }
                else if (Objects.nonNull(txnType) && txnType.equals("20")) {
                    irfDesc = "International Refund";
                    UAEMcIRFCalculation.log.info("International Refund");
                    overRideResultVo = this.getIntlRefundOverRide(morIrd);
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
                    UAEMcIRFCalculation.log.info(irfDesc);
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
                    }
                    else {
                        irdResult.setIrfAmount(irfAmount);
                        irdResult.setIrfAmountUSD(irfAmount * exchangeRateAED);
                    }
                }
                else if (countryCodeFlag == 'O') {
                    irdResult.setIrfAmount(irfAmount / exchangeRateOMR);
                    irdResult.setIrfAmountUSD(irfAmount);
                }
                irdResult.setDomIntlFlag(cardDomIntlFlag);
                irdResult.setCardType(cardCrDrInd);
                irdResult.setGcmsProductID(gcmsProdId);
                irdResult.setIrfDesc(irfDesc);
                irdResult.setIrfMinAmount(irfMinAmount);
                irdResult.setIrfMaxAmount(irfMaxAmount);
                UAEMcIRFCalculation.log.info("UAE Mc Irf Calculation END: ");
                return irdResult;
            }
            UAEMcIRFCalculation.log.info(" MC IRF Cal : No Valid CardNumber found ");
            irdResult.setIrdSerNumber(0);
            irdResult.setIrdCode("85");
            irdResult.setIrfPercentage(0.0);
            irdResult.setIrfFixed(0.0);
            irdResult.setIrfAmount(0.0);
            irdResult.setIrfAmountUSD(0.0);
            irdResult.setIrfDesc("Crypt API Failed.Invalid cardnumber");
            return irdResult;
        }
        catch (final Exception e) {
            UAEMcIRFCalculation.log.error("Error ,Get McIrf UAE !! :", (Throwable)e);
            return null;
        }
    }
    
    public char getLimitIndicator(final double txnAmount, final String mcc, final String morIrd, final char cardCrDrInd, final char countryCodeFlag) {
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
        }
        else if (countryCodeFlag == 'O' && morIrd.equals("61") && "9999".equals(mcc) && txnAmount < 15000.0) {
            return 'B';
        }
        return limitIndicator;
    }
    
    public MCOverRideResultVo getDomOverRide(final String overRideId, final String morIrd, final Character cardCrDrInd, final String mcc, final Character limitIndicator) {
        final MCOverRideResultVo overRideResultVo = new MCOverRideResultVo();
        McOverrideRatesEntity mcOvrRdRateEntity;
        if (Objects.isNull(overRideId) || overRideId.isEmpty()) {
            mcOvrRdRateEntity = this.mcOverrideRatesRepo.findByIrdAndCardTypeAndMccAndTxnLimitIndicator(morIrd, cardCrDrInd, mcc, limitIndicator);
        }
        else {
            mcOvrRdRateEntity = this.mcOverrideRatesRepo.findByIrdAndOverRideIDAndCardTypeAndMccAndTxnLimitIndicator(morIrd, overRideId, cardCrDrInd, mcc, limitIndicator);
        }
        if (Objects.nonNull(mcOvrRdRateEntity)) {
            overRideResultVo.setMorSerNumber(mcOvrRdRateEntity.getSerNumber());
            overRideResultVo.setMorPercentage(Objects.isNull(mcOvrRdRateEntity.getPercent()) ? 0.0 : ((double)mcOvrRdRateEntity.getPercent()));
            overRideResultVo.setMorFixed((mcOvrRdRateEntity.getFixed() == null) ? 0.0 : ((double)mcOvrRdRateEntity.getFixed()));
            overRideResultVo.setMorMax((mcOvrRdRateEntity.getMax() == null) ? 999999.0 : ((double)mcOvrRdRateEntity.getMax()));
        }
        return overRideResultVo;
    }
    
    public MCOverRideResultVo getIntlRefundOverRide(final String morIrd) {
        final MCOverRideResultVo overRideResultVo = new MCOverRideResultVo();
        final McOverrideRatesEntity mcOvrRdRateEntity = this.mcOverrideRatesRepo.findByIrdAndOverRideIDAndMccAndTxnLimitIndicator(morIrd, "REFUND", "R999", 'A');
        if (Objects.nonNull(mcOvrRdRateEntity)) {
            overRideResultVo.setMorSerNumber(mcOvrRdRateEntity.getSerNumber());
            overRideResultVo.setMorPercentage(Objects.isNull(mcOvrRdRateEntity.getPercent()) ? 0.0 : ((double)mcOvrRdRateEntity.getPercent()));
            overRideResultVo.setMorFixed(Objects.isNull(mcOvrRdRateEntity.getFixed()) ? 0.0 : ((double)mcOvrRdRateEntity.getFixed()));
            overRideResultVo.setMorMax(Objects.isNull(mcOvrRdRateEntity.getMax()) ? 999999.0 : ((double)mcOvrRdRateEntity.getMax()));
        }
        return overRideResultVo;
    }
    
    public int getMcTimeLines(LocalDateTime txnDateTIme) {
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
            UAEMcIRFCalculation.log.error("getMcTimeLines :", (Throwable)e);
            return 0;
        }
    }
    
    public McIrfParamsEntity mcIrfParamInsert(final Integer insCode, final PosTransactionEntity posData, final Character progRegion, final Integer apprCodeFlag, final Integer magStripeFlag, final Integer traceIdFlag, final Integer mcAssignIdFlag, final Character issuerRegion, final String cardProgId, final String gcmsProdId, final Double txnAmount) {
        final McIrfParamsEntity.McIrfParamsEntityBuilder mcIrfParambuilder = McIrfParamsEntity.builder();
        try {
            mcIrfParambuilder.serNumber(posData.getSerialNumber()).insCode(insCode).txnAmount(txnAmount).cashBackAmount(Objects.isNull(posData.getCashBackAmount()) ? 0.0 : posData.getCashBackAmount()).terminalType(posData.getTerminalType()).txnDateTime(posData.getTxnDateTime()).mcc(posData.getMcc()).network(posData.getNetwork()).txnType(posData.getTxnCode()).approvalCode(posData.getApprovalCode()).txnId(posData.getTxnId()).posEntryMode(posData.getPosEntryMode()).serviceCode(posData.getServiceCode()).cardInputAbility(this.getCardInputCapability(posData.getCardInputAbility())).chAuthAbility(this.getCardHoldAuthCapability(posData.getChAuthAbility())).cardCaptureAbility(this.getCardCaptureCapability(posData.getCardCaptureAbility())).oprtEnvironment(this.getOprtEnviornment(posData.getOprtEnvironment())).chPresent(this.getCHPresent(posData.getChPresent())).cardPresent(this.getCardPresent(posData.getCardPresent())).cardInputMode(posData.getCardInputMode()).merType(posData.getMeCategoryType()).maid(posData.getMaid()).progRegion(progRegion).timeLine(this.getMcTimeLines(posData.getTxnDateTime())).apprCodeFlag(apprCodeFlag).magStripeFlag(magStripeFlag).traceIdFlag(traceIdFlag).mcAssignIdFlag(mcAssignIdFlag).issuerRegion(issuerRegion).cardProgId(cardProgId).gcmsProdId(gcmsProdId);
            if (Objects.nonNull(posData.getMotoEcomIndicator())) {
                mcIrfParambuilder.ecomIndicator(StringUtils.right(posData.getMotoEcomIndicator(), 1));
            }
        }
        catch (final Exception e) {
            UAEMcIRFCalculation.log.error("Error mcIrfParamInsert() :", (Throwable)e);
        }
        return mcIrfParambuilder.build();
    }
    
    private Character getCardInputCapability(final Character cardInputCapability) {
        char charValue = '\0';
        if (null == cardInputCapability) {
            charValue = '0';
        }
        else {
            charValue = switch ((char)cardInputCapability) {
                case '0',  '1',  '2',  '6' -> cardInputCapability;
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
    
    private Character getCardHoldAuthCapability(final Character chAuthAbility) {
        char c = '\0';
        if (null == chAuthAbility) {
            c = '9';
        }
        else {
            c = switch ((char)chAuthAbility) {
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
    
    private Character getCardCaptureCapability(final Character cardCaptureAbility) {
        char c = '\0';
        if (null == cardCaptureAbility) {
            c = '9';
        }
        else {
            c = switch ((char)cardCaptureAbility) {
                case '0' -> '0';
                case '1' -> '1';
                default -> '9';
            };
        }
        return c;
    }
    
    private Character getCardPresent(final Character cardPresent) {
        char c = '\0';
        if (null == cardPresent) {
            c = '0';
        }
        else {
            c = switch ((char)cardPresent) {
                case '1' -> '0';
                case '0' -> '1';
                default -> '0';
            };
        }
        return c;
    }
    
    private Character getCHPresent(final Character chPresent) {
        char c = '\0';
        if (null == chPresent) {
            c = '0';
        }
        else {
            c = switch ((char)chPresent) {
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
    
    private Character getOprtEnviornment(final String oprtEnv) {
        char c = '\0';
        if (null == oprtEnv) {
            c = '9';
        }
        else {
            c = switch (oprtEnv) {
                case "23" -> '0';
                case "00" -> '1';
                case "10",  "14" -> '2';
                case "01" -> '3';
                case "11" -> '4';
                case "12" -> '5';
                default -> '9';
            };
        }
        return c;
    }
    
    public UAEMcIRFCalculation(final MCIssAcqRangeRepo issRangeRepo, final IpmDetailsViewRepo ipmDetailsViewRepo, final McOverrideRatesRepo mcOverrideRatesRepo, final McIrfParamsRepo mcIrfParamsRepo, final CryptAPI cryptApi, final Environment env, final McProductMappingRepo productMappingRepo) {
        this.issRangeRepo = issRangeRepo;
        this.ipmDetailsViewRepo = ipmDetailsViewRepo;
        this.mcOverrideRatesRepo = mcOverrideRatesRepo;
        this.mcIrfParamsRepo = mcIrfParamsRepo;
        this.cryptApi = cryptApi;
        this.env = env;
        this.productMappingRepo = productMappingRepo;
    }
    
    static {
        log = LogManager.getLogger((Class)UAEMcIRFCalculation.class);
    }
}
