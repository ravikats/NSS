/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.cryptapi.DecryptResponseVo
 *  com.empay.staging.entities.VisaGOCWorkEntity
 *  com.empay.staging.repo.VisaGOCTxnRepo
 *  com.empay.staging.service.BaseIIGOCService
 *  com.empay.staging.serviceImpl.BaseIIGOCServiceImpl
 *  com.empay.vo.TCRZeroVo
 *  jakarta.transaction.Transactional
 *  org.apache.commons.lang3.StringUtils
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 *  org.springframework.core.env.Environment
 *  org.springframework.stereotype.Service
 */
package com.empay.staging.serviceImpl;

import com.empay.cryptapi.DecryptResponseVo;
import com.empay.staging.entities.VisaGOCWorkEntity;
import com.empay.staging.repo.VisaGOCTxnRepo;
import com.empay.staging.service.BaseIIGOCService;
import com.empay.vo.TCRZeroVo;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Currency;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class BaseIIGOCServiceImpl
implements BaseIIGOCService {
    private static final Logger log = LoggerFactory.getLogger(BaseIIGOCServiceImpl.class);
    private static final DateTimeFormatter yyMMddformatter = DateTimeFormatter.ofPattern("yyMMdd");
    private static final DateTimeFormatter eventDateFormatter = DateTimeFormatter.ofPattern("MMdd");
    private final VisaGOCTxnRepo visaGOCTxnRepo;
    private final Environment env;
    private Integer txnCount91 = 0;
    private Integer tcrCount91 = 0;
    private Double totalTxnAmt91 = 0.0;
    private Integer txnCount92 = 0;
    private Integer tcrCount92 = 0;
    private Integer allTcrCount92 = 0;
    private Double totalTxnAmt92 = 0.0;
    private Integer batchNumber = 0;
    private BigDecimal multiplier = BigDecimal.valueOf(100L);

    public List<StringBuilder> getGOCTxnData(List<VisaGOCWorkEntity> txnEntity, DecryptResponseVo response, String acquirerBins, int fileSequence) {
        try {
            String currencyCode = Objects.requireNonNull(this.env.getProperty("CURRENCY_CODE_KAFKA"), "NIL");
            if (currencyCode.equals("NIL")) {
                return null;
            }
            Currency currency = Currency.getInstance(StringUtils.substring((String)currencyCode, (int)0, (int)3));
            int fractionDigits = currency.getDefaultFractionDigits();
            this.multiplier = BigDecimal.TEN.pow(fractionDigits);
            ArrayList<StringBuilder> linesList = new ArrayList<StringBuilder>();
            TCRZeroVo line = null;
            if (Objects.nonNull(txnEntity)) {
                for (VisaGOCWorkEntity entity : txnEntity) {
                    String decryptedCardNumber = (String)response.getCardNumbers().get(entity.getEncCardNumber());
                    String arn = entity.getArn();
                    if (Objects.nonNull(decryptedCardNumber)) {
                        Integer n = this.txnCount91;
                        this.txnCount91 = this.txnCount91 + 1;
                        this.totalTxnAmt91 = Objects.nonNull(entity.getDccIndicator()) && entity.getDccIndicator().charValue() == 'Y' ? Double.valueOf(this.totalTxnAmt91 + entity.getDccAmount()) : Double.valueOf(this.totalTxnAmt91 + entity.getTxnAmount());
                        line = this.getTcr0(entity, decryptedCardNumber);
                        if (Objects.nonNull(line)) {
                            linesList.add(new StringBuilder(line.getTcr0format()));
                            n = this.tcrCount91;
                            this.tcrCount91 = this.tcrCount91 + 1;
                        }
                        if (Objects.nonNull(line = this.getAdditionalData(entity))) {
                            linesList.add(new StringBuilder(line.getAdditionalDataformat()));
                            n = this.tcrCount91;
                            this.tcrCount91 = this.tcrCount91 + 1;
                        }
                        if (Objects.nonNull(line = this.getPaymentServiceData(entity))) {
                            linesList.add(new StringBuilder(line.getPaymentServiceDataformat()));
                            n = this.tcrCount91;
                            this.tcrCount91 = this.tcrCount91 + 1;
                        }
                        if ((entity.getPosEntryMode().equals("05") || entity.getPosEntryMode().equals("07")) && Objects.nonNull(line = this.getChipCardTxnData(entity))) {
                            linesList.add(new StringBuilder(line.getChipCardTxnDataformat()));
                            n = this.tcrCount91;
                            this.tcrCount91 = this.tcrCount91 + 1;
                        }
                        if (entity.getBussAppId() != null && !entity.getBussAppId().isEmpty() && (line = this.getAFTData(entity)) != null) {
                            linesList.add(new StringBuilder(line.getAFTDataformat()));
                            n = this.tcrCount91;
                            this.tcrCount91 = this.tcrCount91 + 1;
                        }
                        if (this.tcrCount91 <= 3250) continue;
                        linesList.add(this.generateFooter91(acquirerBins, Integer.valueOf(fileSequence)));
                        continue;
                    }
                    this.updateFailedTxn(arn);
                }
            } else {
                return null;
            }
            if (this.txnCount91 > 0) {
                linesList.add(this.generateFooter91(acquirerBins, Integer.valueOf(fileSequence)));
            }
            linesList.add(this.generateFooter92(acquirerBins));
            return linesList;
        }
        catch (Exception e) {
            log.error("error : getGOCTxnData() : " + String.valueOf(e));
            return new ArrayList<StringBuilder>();
        }
    }

    private TCRZeroVo getTcr0(VisaGOCWorkEntity entity, String decryptedCardNumber) {
        TCRZeroVo tcrZeroVo = new TCRZeroVo();
        try {
            tcrZeroVo.setTxnCode(Optional.ofNullable(entity.getTxnCode()).orElse("05"));
            if (entity.getBussAppId() != null && !entity.getBussAppId().isEmpty() && "05".equals(entity.getTxnCode())) {
                tcrZeroVo.setTxnCodeQualifier("1");
            } else {
                tcrZeroVo.setTxnCodeQualifier("0");
            }
            tcrZeroVo.setTxnComponentSeqNum("0");
            tcrZeroVo.setAccountNumber(StringUtils.left((String)StringUtils.rightPad((String)decryptedCardNumber, (int)16, (String)"0"), (int)16));
            tcrZeroVo.setAccountNumberExtension(StringUtils.right((String)StringUtils.rightPad((String)decryptedCardNumber, (int)19, (String)"0"), (int)3));
            tcrZeroVo.setFloorLimitIndicator(" ");
            tcrZeroVo.setCrbExceptionFileIndicator(" ");
            tcrZeroVo.setReserved(" ");
            tcrZeroVo.setArn(StringUtils.rightPad((String)entity.getArn(), (int)23, (String)" "));
            tcrZeroVo.setAcquirerBusinessID("10087096");
            tcrZeroVo.setPurchaseDate(Objects.nonNull(entity.getPurchaseDate()) ? entity.getPurchaseDate().format(eventDateFormatter) : StringUtils.repeat((char)'0', (int)4));
            tcrZeroVo.setDestinationAmount(StringUtils.repeat((char)'0', (int)12));
            tcrZeroVo.setDestinationCurrencyCode(StringUtils.repeat((char)' ', (int)3));
            if (Objects.nonNull(entity.getDccIndicator()) && entity.getDccIndicator().charValue() == 'Y') {
                longValueAmount = BigDecimal.valueOf(entity.getDccAmount()).multiply(this.multiplier).setScale(0, RoundingMode.HALF_UP).longValueExact();
                tcrZeroVo.setSourceAmount(StringUtils.leftPad((String)String.valueOf(longValueAmount), (int)12, (String)"0"));
                tcrZeroVo.setSourceCurrencyCode(StringUtils.left((String)(Optional.ofNullable(entity.getDccCurrency()).orElse("") + StringUtils.repeat((char)' ', (int)3)), (int)3));
            } else {
                longValueAmount = BigDecimal.valueOf(entity.getTxnAmount()).multiply(this.multiplier).setScale(0, RoundingMode.HALF_UP).longValueExact();
                tcrZeroVo.setSourceAmount(StringUtils.leftPad((String)String.valueOf(longValueAmount), (int)12, (String)"0"));
                tcrZeroVo.setSourceCurrencyCode(StringUtils.left((String)(Optional.ofNullable(entity.getTxnCurCode()).orElse("") + StringUtils.repeat((char)' ', (int)3)), (int)3));
            }
            if (Objects.nonNull(entity.getMeName()) && entity.getMeName().contains("&")) {
                String replaced = entity.getMeName().replace("&", "^&");
                int adjustedLength = 25 + replaced.length() - entity.getMeName().length();
                tcrZeroVo.setMerchantName(StringUtils.left((String)(StringUtils.defaultString((String)replaced) + StringUtils.repeat((char)' ', (int)adjustedLength)), (int)25));
            } else {
                tcrZeroVo.setMerchantName(StringUtils.left((String)(StringUtils.defaultString((String)entity.getMeName()) + StringUtils.repeat((char)' ', (int)25)), (int)25));
            }
            tcrZeroVo.setMerchantCity(StringUtils.left((String)StringUtils.rightPad((String)entity.getMeCity(), (int)13, (String)" "), (int)13));
            tcrZeroVo.setMerchantCountryCode(StringUtils.rightPad((String)StringUtils.defaultString((String)entity.getMeCountry()), (int)3, (String)" "));
            tcrZeroVo.setMerchantCategoryCode(StringUtils.left((String)(entity.getMcc() + StringUtils.repeat((char)' ', (int)4)), (int)4));
            tcrZeroVo.setMerchantZIPCode(StringUtils.repeat((char)'0', (int)5));
            tcrZeroVo.setMerchantStateOrProvinceCode(StringUtils.repeat((char)' ', (int)3));
            tcrZeroVo.setRequestedPaymentService("9");
            tcrZeroVo.setNumberofPaymentForms(" ");
            tcrZeroVo.setUsageCode("1");
            tcrZeroVo.setReasonCode(StringUtils.repeat((char)'0', (int)2));
            tcrZeroVo.setSettlementFlag("9");
            tcrZeroVo.setAuthorizationCharaIndicator(this.getAuthorizationCharInd(entity.getAuthCharIndicator(), entity.getMotoEcomIndicator()));
            tcrZeroVo.setAuthorizationCode(StringUtils.left((String)(Optional.ofNullable(entity.getApprovalCode()).orElse("") + StringUtils.repeat((char)' ', (int)6)), (int)6));
            tcrZeroVo.setPosTerminalCapability(entity.getTerminalCapability());
            tcrZeroVo.setReserved1(" ");
            tcrZeroVo.setCardholderIDMethod(Optional.ofNullable(entity.getChIdMethod()).orElse(Character.valueOf(' ')));
            tcrZeroVo.setCollectionOnlyFlag("C");
            tcrZeroVo.setPosEntryMode(StringUtils.left((String)(Optional.ofNullable(entity.getPosEntryMode()).orElse("") + StringUtils.repeat((char)' ', (int)2)), (int)2));
            if (Arrays.asList("25", "26").contains(entity.getTxnCode())) {
                String jDate = String.valueOf(LocalDate.now().getYear()).substring(2) + String.format("%03d", LocalDate.now().get(ChronoField.DAY_OF_YEAR));
                tcrZeroVo.setCentralProcessingDate(StringUtils.substring((String)jDate, (int)1, (int)5));
            } else {
                tcrZeroVo.setCentralProcessingDate("0000");
            }
            tcrZeroVo.setReimbursementAttribute("B");
        }
        catch (Exception e) {
            tcrZeroVo = null;
            log.error("error : getTcr0() : " + String.valueOf(e));
        }
        return tcrZeroVo;
    }

    public Character getAuthorizationCharInd(Character authorizationInd, Character ecomIndicator) {
        if (authorizationInd != null && authorizationInd.charValue() != ' ') {
            return authorizationInd;
        }
        if (ecomIndicator == null || ecomIndicator.charValue() == ' ') {
            return Character.valueOf('E');
        }
        return switch (ecomIndicator.charValue()) {
            case '5' -> Character.valueOf('U');
            case '6' -> Character.valueOf('S');
            default -> Character.valueOf('W');
        };
    }

    private TCRZeroVo getAdditionalData(VisaGOCWorkEntity entity) {
        TCRZeroVo tcrZeroVo = new TCRZeroVo();
        try {
            tcrZeroVo.setTxnCode(Optional.ofNullable(entity.getTxnCode()).orElse("05"));
            if (entity.getBussAppId() != null && !entity.getBussAppId().isEmpty() && "05".equals(entity.getTxnCode())) {
                tcrZeroVo.setTxnCodeQualifier("1");
            } else {
                tcrZeroVo.setTxnCodeQualifier("0");
            }
            tcrZeroVo.setTxnComponentSeqNum("1");
            tcrZeroVo.setBusinessFormatCode(" ");
            tcrZeroVo.setTokenAssuranceLevel(StringUtils.repeat((char)' ', (int)2));
            tcrZeroVo.setRateTableID(StringUtils.repeat((char)' ', (int)5));
            tcrZeroVo.setReserved2(StringUtils.repeat((char)' ', (int)4));
            tcrZeroVo.setReserved3(StringUtils.repeat((char)'0', (int)6));
            tcrZeroVo.setDocumentationIndicator(" ");
            tcrZeroVo.setMemberMessageText(StringUtils.left((String)(Optional.ofNullable(entity.getMemberText()).orElse("") + StringUtils.repeat((char)' ', (int)50)), (int)50));
            tcrZeroVo.setSpecialConditionIndicators(StringUtils.repeat((char)' ', (int)2));
            tcrZeroVo.setFeeProgramIndicator(StringUtils.left((String)(Optional.ofNullable(entity.getFeePrgIndicator()).orElse("") + StringUtils.repeat((char)' ', (int)3)), (int)3));
            tcrZeroVo.setIssuerCharge(" ");
            tcrZeroVo.setPersistentFXAppliedIndicator(" ");
            tcrZeroVo.setCardAcceptorID(StringUtils.rightPad((String)entity.getMerchantId(), (int)15, (char)' '));
            tcrZeroVo.setTerminalID(StringUtils.left((String)(Optional.ofNullable(entity.getTerminalId()).orElse("") + StringUtils.repeat((char)' ', (int)8)), (int)8));
            tcrZeroVo.setNationalReimbursementFee(StringUtils.repeat((char)'0', (int)12));
            tcrZeroVo.setEcomIndicator(Optional.ofNullable(entity.getMotoEcomIndicator()).orElse(Character.valueOf(' ')));
            tcrZeroVo.setSpecialChargebackIndicator(" ");
            tcrZeroVo.setConversionDate(StringUtils.repeat((char)'0', (int)4));
            tcrZeroVo.setReserved4(StringUtils.repeat((char)'0', (int)2));
            tcrZeroVo.setAcceptanceTerminalIndicator(entity.getAcceptanceTrlIndicator() == null ? " " : String.valueOf(entity.getAcceptanceTrlIndicator()));
            tcrZeroVo.setPrepaidCardIndicator(" ");
            tcrZeroVo.setServiceDevelopmentField("0");
            tcrZeroVo.setAvsResponseCode(" ");
            tcrZeroVo.setAuthorizationSourceCode("5");
            tcrZeroVo.setPurchaseIdentifierFormat(" ");
            tcrZeroVo.setAccountSelection(Optional.ofNullable(entity.getAccSelection()).orElse(Character.valueOf(' ')));
            tcrZeroVo.setInstallmentPaymentCount(StringUtils.repeat((char)' ', (int)2));
            tcrZeroVo.setPurchaseIdentifier(StringUtils.repeat((char)' ', (int)25));
            long longCashBackAmt = BigDecimal.valueOf(Optional.ofNullable(entity.getCashbackAmount()).orElse(0.0)).multiply(this.multiplier).setScale(0, RoundingMode.HALF_UP).longValueExact();
            tcrZeroVo.setCashback(StringUtils.leftPad((String)String.valueOf(longCashBackAmt), (int)9, (String)"0"));
            tcrZeroVo.setChipConditionCode(" ");
            tcrZeroVo.setPosEnvironment(Optional.ofNullable(entity.getPosEnvironment()).orElse(Character.valueOf(' ')));
        }
        catch (Exception e) {
            tcrZeroVo = null;
            log.error("error : getAdditionalData() : " + String.valueOf(e));
        }
        return tcrZeroVo;
    }

    private TCRZeroVo getPaymentServiceData(VisaGOCWorkEntity entity) {
        TCRZeroVo tcrZeroVo = new TCRZeroVo();
        try {
            tcrZeroVo.setTxnCode(Optional.ofNullable(entity.getTxnCode()).orElse("05"));
            tcrZeroVo.setTxnCodeQualifier("0");
            tcrZeroVo.setTxnComponentSeqNum("5");
            tcrZeroVo.setTransactionIdentifier(StringUtils.right((String)StringUtils.leftPad((String)entity.getTxnId(), (int)15, (char)'0'), (int)15));
            long longAuthorizedAmount = BigDecimal.valueOf(Optional.ofNullable(entity.getAuthAmount()).orElse(0.0)).multiply(this.multiplier).setScale(0, RoundingMode.HALF_UP).longValueExact();
            tcrZeroVo.setAuthorizedAmount(StringUtils.leftPad((String)String.valueOf(longAuthorizedAmount), (int)12, (String)"0"));
            tcrZeroVo.setAuthorizationCurrencyCode(StringUtils.left((String)(Optional.ofNullable(entity.getTxnCurCode()).orElse("") + StringUtils.repeat((char)' ', (int)3)), (int)3));
            tcrZeroVo.setAuthorizationResponseCode(StringUtils.right((String)("00" + entity.getRespCode()), (int)2));
            tcrZeroVo.setValidationCode(Optional.ofNullable(entity.getValidationCode()).orElse(StringUtils.repeat((char)' ', (int)4)));
            tcrZeroVo.setExcludedTransactionIdentifierReason(" ");
            tcrZeroVo.setReserved5(" ");
            tcrZeroVo.setReserved6(StringUtils.repeat((char)' ', (int)2));
            tcrZeroVo.setMultipleClearingSequenceNumber("01");
            tcrZeroVo.setMultipleClearingSequenceCount("01");
            tcrZeroVo.setMarketSpecificAuthDataIndicator(Optional.ofNullable(entity.getMarketSpecDataInd()).orElse(Character.valueOf(' ')));
            tcrZeroVo.setTotalAuthorizedAmount(StringUtils.repeat((char)'0', (int)12));
            tcrZeroVo.setInformationIndicator("N");
            tcrZeroVo.setMerchantTelephoneNumber(StringUtils.repeat((char)' ', (int)14));
            tcrZeroVo.setAdditionalDataIndicator(" ");
            tcrZeroVo.setMerchantVolumeIndicator(StringUtils.repeat((char)' ', (int)2));
            tcrZeroVo.setElectronicCommerceGoodsIndicator(StringUtils.repeat((char)' ', (int)2));
            tcrZeroVo.setMerchantVerificationValue(StringUtils.repeat((char)' ', (int)10));
            tcrZeroVo.setInterchangeFeeAmount(StringUtils.repeat((char)'0', (int)15));
            tcrZeroVo.setInterchangeFeeSign(" ");
            tcrZeroVo.setSourceCurrtoBaseCurrExcRate(StringUtils.repeat((char)'0', (int)8));
            tcrZeroVo.setBaseCurrtoDestinationCurrExcRate(StringUtils.repeat((char)'0', (int)8));
            tcrZeroVo.setOptionalIssuerISAAmount(StringUtils.repeat((char)'0', (int)12));
            tcrZeroVo.setProductID(StringUtils.repeat((char)' ', (int)2));
            tcrZeroVo.setProgramID(StringUtils.repeat((char)' ', (int)6));
            if (Objects.nonNull(entity.getDccIndicator()) && entity.getDccIndicator().equals(Character.valueOf('Y'))) {
                tcrZeroVo.setDccIndicator("1");
            } else {
                tcrZeroVo.setDccIndicator(" ");
            }
            tcrZeroVo.setAccTypeIdentification(StringUtils.repeat((char)' ', (int)4));
            tcrZeroVo.setSpendQualifiedIndicator(Optional.ofNullable(entity.getSpendQualiIndictor()).orElse(Character.valueOf(' ')));
            tcrZeroVo.setPanToken(StringUtils.repeat((char)' ', (int)16));
            tcrZeroVo.setReserved7(" ");
            tcrZeroVo.setAccFundingSource(" ");
            tcrZeroVo.setCvv2ResultCode(" ");
        }
        catch (Exception e) {
            tcrZeroVo = null;
            log.error("error : getPaymentServiceData() : " + String.valueOf(e));
        }
        return tcrZeroVo;
    }

    private TCRZeroVo getChipCardTxnData(VisaGOCWorkEntity entity) {
        TCRZeroVo tcrZeroVo = new TCRZeroVo();
        try {
            tcrZeroVo.setTxnCode(Optional.ofNullable(entity.getTxnCode()).orElse("05"));
            tcrZeroVo.setTxnCodeQualifier("0");
            tcrZeroVo.setTxnComponentSeqNum("7");
            tcrZeroVo.setTransactionType(StringUtils.repeat((char)'0', (int)2));
            tcrZeroVo.setCardSequenceNumber(entity.getCardSeqNumber() == null ? StringUtils.repeat((char)'0', (int)3) : entity.getCardSeqNumber());
            tcrZeroVo.setTerminalTranDate(Objects.nonNull(entity.getTrlTxnDate()) ? entity.getTrlTxnDate().format(yyMMddformatter) : StringUtils.repeat((char)'0', (int)6));
            tcrZeroVo.setTerminalCapabilityProfile(entity.getTrlCapProfile() == null ? StringUtils.repeat((char)'0', (int)6) : entity.getTrlCapProfile());
            tcrZeroVo.setTerminalCountryCode(entity.getTrlCountryCode() == null ? StringUtils.repeat((char)'0', (int)3) : entity.getTrlCountryCode());
            tcrZeroVo.setTerminalSerialNumber(StringUtils.repeat((char)' ', (int)8));
            tcrZeroVo.setUnpredictableNumber(entity.getUpblNumber() == null ? StringUtils.repeat((char)'0', (int)8) : entity.getUpblNumber());
            tcrZeroVo.setApplicationTransactionCounter(entity.getAppTxnCounter() == null ? StringUtils.repeat((char)' ', (int)4) : entity.getAppTxnCounter());
            tcrZeroVo.setApplicationInterchangeProfile(entity.getAppIcProfile() == null ? StringUtils.repeat((char)' ', (int)4) : entity.getAppIcProfile());
            tcrZeroVo.setCryptogram(entity.getAppCryptogram() == null ? StringUtils.repeat((char)' ', (int)16) : entity.getAppCryptogram());
            tcrZeroVo.setIssuerAppDataByte2(entity.getIssAppDataB2() == null ? StringUtils.repeat((char)'0', (int)2) : entity.getIssAppDataB2());
            tcrZeroVo.setIssuerAppDataByte3(entity.getIssAppDataB3() == null ? StringUtils.repeat((char)' ', (int)2) : entity.getIssAppDataB3());
            tcrZeroVo.setTerminalVeriResults(entity.getTrlVerResult() == null ? StringUtils.repeat((char)'0', (int)10) : entity.getTrlVerResult());
            tcrZeroVo.setIssuerAppDataByte4to7(entity.getIssAppDataB4() == null ? StringUtils.repeat((char)'0', (int)8) : entity.getIssAppDataB4());
            long longCryptogramAmount = BigDecimal.valueOf(Optional.ofNullable(entity.getCryptAmount()).orElse(0.0)).multiply(this.multiplier).setScale(0, RoundingMode.HALF_UP).longValueExact();
            tcrZeroVo.setCryptogramAmount(StringUtils.leftPad((String)String.valueOf(longCryptogramAmount), (int)12, (String)"0"));
            tcrZeroVo.setIssuerAppDataByte8(entity.getIssAppDataB8() == null ? StringUtils.repeat((char)'0', (int)2) : entity.getIssAppDataB8());
            tcrZeroVo.setIssuerAppDataByte9to16(entity.getIssAppDataB9() == null ? StringUtils.repeat((char)'0', (int)16) : entity.getIssAppDataB9());
            tcrZeroVo.setIssuerAppDataByte1(entity.getIssAppDataB1() == null ? StringUtils.repeat((char)'0', (int)2) : entity.getIssAppDataB1());
            tcrZeroVo.setIssuerAppDataByte17(entity.getIssAppDataB17() == null ? StringUtils.repeat((char)'0', (int)2) : entity.getIssAppDataB17());
            tcrZeroVo.setIssuerAppDataByte18to32(entity.getIssAppDataB18() == null ? StringUtils.repeat((char)'0', (int)30) : entity.getIssAppDataB18());
            tcrZeroVo.setFormFactorIndicator(StringUtils.isBlank((CharSequence)entity.getFormFactorIndicator()) ? StringUtils.repeat((char)'0', (int)8) : StringUtils.leftPad((String)entity.getFormFactorIndicator(), (int)8, (char)'0'));
            tcrZeroVo.setIssuerScript1Results(StringUtils.repeat((char)'0', (int)10));
        }
        catch (Exception e) {
            tcrZeroVo = null;
            log.error("error : getChipCardTxnData() : " + String.valueOf(e));
        }
        return tcrZeroVo;
    }

    private TCRZeroVo getAFTData(VisaGOCWorkEntity entity) {
        TCRZeroVo tcrZeroVo = new TCRZeroVo();
        try {
            tcrZeroVo.setTransactionCode(entity.getTxnCode());
            tcrZeroVo.setTxnCodeQualifier("1");
            tcrZeroVo.setTxnComponentSeqNum("3");
            tcrZeroVo.setServiceProcessingType("00");
            tcrZeroVo.setReserved9(StringUtils.repeat((char)' ', (int)9));
            tcrZeroVo.setFastFundsIndicator(StringUtils.repeat((char)' ', (int)1));
            tcrZeroVo.setBusinessFormatCodeCR("CR");
            tcrZeroVo.setBusinessApplicationID(entity.getBussAppId());
            tcrZeroVo.setSourceofFunds("3");
            tcrZeroVo.setPaymentReversalReasonCode(StringUtils.repeat((char)' ', (int)2));
            tcrZeroVo.setSenderReferenceNumber(StringUtils.repeat((char)' ', (int)16));
            tcrZeroVo.setSenderAccountNumber(StringUtils.left((String)(Optional.ofNullable(entity.getSenderAccount()).orElse("") + StringUtils.repeat((char)' ', (int)34)), (int)34));
            tcrZeroVo.setSenderName(StringUtils.left((String)(Optional.ofNullable(entity.getSenderName()).orElse("") + StringUtils.repeat((char)' ', (int)30)), (int)30));
            tcrZeroVo.setSenderAddress(StringUtils.repeat((char)' ', (int)35));
            tcrZeroVo.setSenderCity(StringUtils.repeat((char)' ', (int)25));
            tcrZeroVo.setSenderState(StringUtils.repeat((char)' ', (int)2));
            tcrZeroVo.setSenderCountry(StringUtils.repeat((char)' ', (int)3));
        }
        catch (Exception e) {
            tcrZeroVo = null;
            log.error("error : getAFTData() : " + String.valueOf(e));
        }
        return tcrZeroVo;
    }

    @Transactional
    private void updateFailedTxn(String arn) {
        try {
            List visaEntities = this.visaGOCTxnRepo.findByArn(arn);
            if (visaEntities.isEmpty()) {
                log.warn("No visaGOCEntities found for refNumber: {}", (Object)arn);
                return;
            }
            log.info("Updating genStatus for {} visaGOCEntities with refNumber: {}", (Object)visaEntities.size(), (Object)arn);
            visaEntities.forEach(entity -> entity.setGenStatus(Integer.valueOf(7)));
            this.visaGOCTxnRepo.saveAllAndFlush((Iterable)visaEntities);
            log.info("Successfully updated genStatus for all matching visaGOCEntities.");
        }
        catch (Exception e) {
            log.error("Error in updateFailedTxn for refNumber {}: {}", new Object[]{arn, e.getMessage(), e});
        }
    }

    private StringBuilder generateFooter91(String acqBin, Integer fileSeq) {
        try {
            this.batchNumber = this.batchNumber + 1;
            this.allTcrCount92 = this.allTcrCount92 + 1;
            StringBuilder footer = new StringBuilder();
            footer.append("91");
            footer.append("00");
            footer.append(acqBin);
            footer.append(StringUtils.repeat((char)'0', (int)5));
            footer.append(StringUtils.repeat((char)'0', (int)15));
            footer.append(StringUtils.right((String)StringUtils.leftPad((String)String.valueOf(this.txnCount91), (int)12, (char)'0'), (int)12));
            footer.append(StringUtils.right((String)StringUtils.leftPad((String)String.valueOf(this.batchNumber), (int)6, (char)'0'), (int)6));
            footer.append(StringUtils.right((String)StringUtils.leftPad((String)String.valueOf(this.tcrCount91 + 1), (int)12, (char)'0'), (int)12));
            footer.append(StringUtils.repeat((char)' ', (int)6));
            String jDate = String.valueOf(LocalDate.now().getYear()).substring(2) + String.format("%03d", LocalDate.now().get(ChronoField.DAY_OF_YEAR));
            footer.append(jDate + StringUtils.right((String)StringUtils.leftPad((String)String.valueOf(fileSeq), (int)3, (char)'0'), (int)3));
            String paddedTotalStr = StringUtils.leftPad((String)String.valueOf(this.txnCount91 + 1), (int)9, (char)'0');
            footer.append(StringUtils.right((String)paddedTotalStr, (int)9));
            footer.append(StringUtils.repeat((char)' ', (int)18));
            log.info("totalTxnAmt91 :" + this.totalTxnAmt91);
            BigDecimal multiplied = BigDecimal.valueOf(this.totalTxnAmt91).multiply(this.multiplier);
            long longPaddedAmount = multiplied.setScale(0, RoundingMode.HALF_UP).longValue();
            String paddedAmountStr = StringUtils.leftPad((String)String.valueOf(longPaddedAmount), (int)15, (String)"0");
            footer.append(StringUtils.right((String)paddedAmountStr, (int)15));
            footer.append(StringUtils.repeat((char)' ', (int)15));
            footer.append(StringUtils.repeat((char)' ', (int)15));
            footer.append(StringUtils.repeat((char)' ', (int)15));
            footer.append(StringUtils.repeat((char)' ', (int)7));
            this.txnCount92 = this.txnCount92 + this.txnCount91;
            this.tcrCount92 = this.tcrCount92 + this.tcrCount91;
            this.allTcrCount92 = this.allTcrCount92 + this.tcrCount91;
            this.totalTxnAmt92 = this.totalTxnAmt92 + this.totalTxnAmt91;
            this.txnCount91 = 0;
            this.tcrCount91 = 0;
            this.totalTxnAmt91 = 0.0;
            return footer;
        }
        catch (Exception e) {
            log.error("Error in generateFooter91(): ", (Throwable)e);
            return new StringBuilder();
        }
    }

    private StringBuilder generateFooter92(String acqBin) {
        try {
            StringBuilder footer = new StringBuilder();
            footer.append("92");
            footer.append("00");
            footer.append(acqBin);
            footer.append(StringUtils.repeat((char)'0', (int)5));
            footer.append(StringUtils.repeat((char)'0', (int)15));
            footer.append(StringUtils.right((String)StringUtils.leftPad((String)String.valueOf(this.txnCount92), (int)12, (char)'0'), (int)12));
            footer.append(StringUtils.right((String)StringUtils.leftPad((String)String.valueOf(this.batchNumber), (int)6, (char)'0'), (int)6));
            footer.append(StringUtils.right((String)StringUtils.leftPad((String)String.valueOf(this.allTcrCount92 + 1), (int)12, (char)'0'), (int)12));
            footer.append(StringUtils.repeat((char)' ', (int)6));
            footer.append(StringUtils.repeat((char)' ', (int)8));
            String paddedTotalStr = StringUtils.leftPad((String)String.valueOf(this.txnCount92 + this.batchNumber + 1), (int)9, (char)'0');
            footer.append(StringUtils.right((String)paddedTotalStr, (int)9));
            footer.append(StringUtils.repeat((char)' ', (int)18));
            log.info("totalTxnAmt92 :" + this.totalTxnAmt92);
            BigDecimal multiplied = BigDecimal.valueOf(this.totalTxnAmt92).multiply(this.multiplier);
            long londValueAmount = multiplied.setScale(0, RoundingMode.HALF_UP).longValue();
            String paddedAmountStr = StringUtils.leftPad((String)String.valueOf(londValueAmount), (int)15, (String)"0");
            footer.append(StringUtils.right((String)paddedAmountStr, (int)15));
            footer.append(StringUtils.repeat((char)' ', (int)15));
            footer.append(StringUtils.repeat((char)' ', (int)15));
            footer.append(StringUtils.repeat((char)' ', (int)15));
            footer.append(StringUtils.repeat((char)' ', (int)7));
            this.txnCount91 = 0;
            this.tcrCount91 = 0;
            this.totalTxnAmt91 = 0.0;
            this.txnCount92 = 0;
            this.tcrCount92 = 0;
            this.allTcrCount92 = 0;
            this.totalTxnAmt92 = 0.0;
            this.batchNumber = 0;
            return footer;
        }
        catch (Exception e) {
            log.error("Error in generateFooter92(): ", (Throwable)e);
            return new StringBuilder();
        }
    }

    public BaseIIGOCServiceImpl(VisaGOCTxnRepo visaGOCTxnRepo, Environment env) {
        this.visaGOCTxnRepo = visaGOCTxnRepo;
        this.env = env;
    }
}

