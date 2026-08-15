// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.services;

import org.apache.logging.log4j.LogManager;
import com.empay.entities.McRTSTxnDataEntity;
import jakarta.transaction.Transactional;
import com.empay.common.entities.CurrencyEntity;
import com.empay.common.entities.AcquirerBinEntity;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;
import java.time.LocalDateTime;
import java.util.Set;
import com.empay.entities.McAcqTxnWorkEntity;
import com.empay.entities.PosTransactionEntity;
import com.empay.common.functions.PinCaptureAbility;
import com.empay.common.functions.JulianDateConverter;
import com.empay.common.functions.ARNCheckDigit;
import com.empay.common.repo.AcquirerBinRepo;
import com.empay.common.repo.CurrencyRepo;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class McSplitTransactionService
{
    private static final Logger log;
    private final CurrencyRepo currencyRepo;
    private final AcquirerBinRepo acquirerBinRepo;
    private final ARNCheckDigit arnCheckDigit;
    private final JulianDateConverter julianDateConverter;
    private final PinCaptureAbility pinCaptureAbility;
    
    @Transactional
    public McAcqTxnWorkEntity mapToMcAcqTxnWorkEntity(final PosTransactionEntity posTxnEntity, final Integer userSerialNumber, final Integer insCode, final Integer jobNumber) {
        final StringBuilder posData = new StringBuilder();
        final AcquirerBinEntity acquirerBinData = this.acquirerBinRepo.findByInsCodeAndBinType(insCode, 'M');
        final Set<String> MCC_COUNTRY_ORIGIN_SET = Set.of("9211", "9222", "9311", "9399", "9402", "9405", "9406");
        final McAcqTxnWorkEntity.McAcqTxnWorkEntityBuilder mcAsqBuilder = McAcqTxnWorkEntity.builder();
        mcAsqBuilder.lastUpdated(LocalDateTime.now()).updatedUser(userSerialNumber).institutionCode(insCode).intCode(posTxnEntity.getIntCode()).prjSerNumber(jobNumber).generalStatus(3).txnRefNumber(posTxnEntity.getSerialNumber()).txnType(posTxnEntity.getTxnCode()).messageTypeId("1240").cardNumber(posTxnEntity.getCardNumber()).procCode(posTxnEntity.getTxnCode() + StringUtils.right(posTxnEntity.getProcCode(), 4)).txnAmount(posTxnEntity.getTxnAmount()).surchargeAmount(0.0).localDateTime(posTxnEntity.getLocalDateTime()).expiryDate(posTxnEntity.getExpiryDate()).posDataCode(posData.append(this.getCardInputCapability(posTxnEntity.getCardInputAbility())).append(this.getCardHoldAuthCapability(posTxnEntity.getChAuthAbility())).append(this.getCardCaptureCapability(posTxnEntity.getCardCaptureAbility())).append(this.getOprtEnviornment(posTxnEntity.getOprtEnvironment())).append(this.getCHPresent(posTxnEntity.getChPresent())).append(this.getCardPresent(posTxnEntity.getCardPresent())).append(Objects.nonNull(posTxnEntity.getCardInputMode()) ? posTxnEntity.getCardInputMode() : '0').append(Objects.nonNull(posTxnEntity.getChAuthMethod()) ? posTxnEntity.getChAuthMethod() : '9').append(Objects.nonNull(posTxnEntity.getChAuthEntity()) ? posTxnEntity.getChAuthEntity() : '9').append(Objects.nonNull(posTxnEntity.getCardOutPutAbility()) ? posTxnEntity.getCardOutPutAbility() : '0').append(Objects.nonNull(posTxnEntity.getTrlOutPutAbility()) ? posTxnEntity.getTrlOutPutAbility() : '0').append(this.pinCaptureAbility.getPinCaptureAbility(posTxnEntity.getPosEntryMode())).toString()).functionCode("200").msgReasonCode("1401").mcc(posTxnEntity.getMcc()).rrn(posTxnEntity.getRrn()).approvalCode(posTxnEntity.getApprovalCode()).responseCode(posTxnEntity.getResponseCode()).serviceCode(posTxnEntity.getServiceCode()).terminalId(posTxnEntity.getTerminalId()).merchantId(posTxnEntity.getMerchantId()).meName(posTxnEntity.getMeName()).meCity(posTxnEntity.getMeCity()).meCountry(posTxnEntity.getMeCountry()).mePinCode(posTxnEntity.getMePinCode()).chipTrlType(posTxnEntity.getChipTrlType()).txnFeeAmount(posTxnEntity.getTxnFeeAmount()).txnCurCode(posTxnEntity.getTxnCurCode()).ird(posTxnEntity.getIrd()).settlementIndicator((posTxnEntity.getOnusOffusFlag() == 'O') ? 'C' : 'M').cardSeqNumber(posTxnEntity.getCardSeqNumber()).appCryptogram(posTxnEntity.getAppCryptogram()).cryptInfoData(posTxnEntity.getCryptInfoData()).issAppData(posTxnEntity.getIssAppData()).upblNumber(posTxnEntity.getUpblNumber()).appTxnCounter(posTxnEntity.getAppTxnCounter()).trlVerResult(posTxnEntity.getTrlVerResult()).chipTxnType(posTxnEntity.getChipTxnType()).cryptAmount(posTxnEntity.getCryptAmount()).appICProfile(posTxnEntity.getAppICProfile()).trlConCode(posTxnEntity.getTrlConCode()).cvmResult(posTxnEntity.getCvmResult()).trlCapabilities(posTxnEntity.getTrlCapabilities()).ifdSerNumber(posTxnEntity.getIfdSerNumber()).tcc(posTxnEntity.getTcc()).chipCurCode(posTxnEntity.getChipCurCode()).chipTrlType(posTxnEntity.getChipTrlType()).trlAppVerNumber(posTxnEntity.getTrlAppVerNumber()).txnSeqCounter(null).issAuthData(posTxnEntity.getIssAuthData()).msgNumber(null).memberText(null).revIndiCator(posTxnEntity.getRevIndiCator()).maid(posTxnEntity.getMaid()).cardType(posTxnEntity.getCardType()).cardDomIntlFlag(posTxnEntity.getCardDomIntlFlag()).dmsSmsMode(posTxnEntity.getDmsSmsMode()).centreProcDate(posTxnEntity.getCentreProcDate()).fileProcDate(null).fileID(null).encryptedCardNumber(posTxnEntity.getEncCardNumber()).tipAmount(posTxnEntity.getTipAmount()).chipTrlCapabilities(posTxnEntity.getChipTrlCapabilities()).dedicatedFileName(posTxnEntity.getDedicatedFileName()).cardAccepStreetAddress(posTxnEntity.getCardAccepStreetAddress()).chipTxnDate(posTxnEntity.getChipTxnDate()).dccAmount(posTxnEntity.getDccAmount()).dccCurrency(posTxnEntity.getDccCurrency()).dccIndicator(posTxnEntity.getDccIndicator()).mposAccDevType(posTxnEntity.getMposAccDevType()).customerServicePhNum(posTxnEntity.getMerchantContactInfo()).accepterUrlAddress(posTxnEntity.getAccepterUrlAddress());
        if (Objects.nonNull(posTxnEntity.getTxnCurCode())) {
            final CurrencyEntity currencyEntity = this.currencyRepo.findByCurrencyCode(posTxnEntity.getTxnCurCode());
            if (Objects.nonNull(currencyEntity)) {
                mcAsqBuilder.txnCurrencyExponent(currencyEntity.getCurExponent());
            }
            else {
                mcAsqBuilder.txnCurrencyExponent(48);
            }
        }
        if (Character.valueOf('Y').equals(posTxnEntity.getDccIndicator())) {
            if (posTxnEntity.getDccCurrency() != null) {
                final CurrencyEntity currencyEntity = this.currencyRepo.findByCurrencyCode(posTxnEntity.getDccCurrency());
                if (currencyEntity != null) {
                    mcAsqBuilder.dccTxnCurrencyExponent(currencyEntity.getCurExponent());
                }
            }
            else {
                mcAsqBuilder.dccTxnCurrencyExponent(48);
            }
        }
        if (MCC_COUNTRY_ORIGIN_SET.contains(posTxnEntity.getMcc())) {
            mcAsqBuilder.meCountryOfOrigin(posTxnEntity.getMeCountryOfOrigin());
        }
        if (posTxnEntity.getTerminalType().equalsIgnoreCase("MPOS")) {
            mcAsqBuilder.trlType("CT9");
        }
        else if (posTxnEntity.getPosEntryMode().startsWith("81")) {
            mcAsqBuilder.trlType("CT6");
        }
        else if (Objects.nonNull(posTxnEntity.getChPresent()) && posTxnEntity.getChPresent() == '5' && posTxnEntity.getCardInputMode() == '7') {
            mcAsqBuilder.trlType("CT6");
        }
        else {
            mcAsqBuilder.trlType("POI");
        }
        if (Objects.nonNull(posTxnEntity.getPosEntryMode()) && posTxnEntity.getPosEntryMode().startsWith("81")) {
            mcAsqBuilder.motoEcomIndicator("21" + posTxnEntity.getMotoEcomIndicator().substring(posTxnEntity.getMotoEcomIndicator().length() - 1));
        }
        if (posTxnEntity.getNetwork().equals("MCI")) {
            if (Objects.nonNull(posTxnEntity.getCardSeqNumber()) && posTxnEntity.getPosEntryMode().startsWith("05")) {
                mcAsqBuilder.cardSeqNumber(StringUtils.substring("000" + posTxnEntity.getCardSeqNumber(), ("000" + posTxnEntity.getCardSeqNumber()).length() - 3));
            }
            if (posTxnEntity.getTxnCode().matches("09")) {
                mcAsqBuilder.cashBackAmount(posTxnEntity.getCashBackAmount());
            }
            else if (Objects.nonNull(posTxnEntity.getAppCryptogram())) {
                mcAsqBuilder.cashBackAmount(posTxnEntity.getChipCashBack());
            }
            if (Objects.nonNull(posTxnEntity.getNetworkData())) {
                mcAsqBuilder.txnlifeCycleId(" " + (posTxnEntity.getNetworkData() + "             ").substring(0, 13) + "  ");
            }
        }
        else if (posTxnEntity.getNetwork().equals("MDS")) {
            if (Objects.isNull(posTxnEntity.getTxnId()) || posTxnEntity.getTxnId().isEmpty()) {
                mcAsqBuilder.txnlifeCycleId(null);
            }
            else if (posTxnEntity.getTxnId().length() >= 13 && (posTxnEntity.getTxnId().substring(9, 12).equals("0000") || posTxnEntity.getTxnId().substring(9, 12).equals("    "))) {
                final DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyMMdd");
                final String leftPart = (posTxnEntity.getTxnId() + "         ").substring(0, 9);
                if (Objects.nonNull(posTxnEntity.getLocalDateTime())) {
                    final String rightPart = posTxnEntity.getLocalDateTime().format(pattern);
                    mcAsqBuilder.txnlifeCycleId(" " + leftPart + rightPart + "  ");
                }
            }
            else {
                mcAsqBuilder.txnlifeCycleId(" " + (posTxnEntity.getTxnId() + "         ").substring(0, 13) + "  ").cardSeqNumber("");
            }
        }
        if (posTxnEntity.getTerminalType().equalsIgnoreCase("E-COM")) {
            mcAsqBuilder.posPgType("PG");
        }
        else {
            mcAsqBuilder.posPgType(posTxnEntity.getTerminalType());
        }
        if (Objects.nonNull(acquirerBinData)) {
            mcAsqBuilder.acqinstIdCode(StringUtils.leftPad(acquirerBinData.getMcIcaNum(), 6, "0")).orgInstIdCode(StringUtils.leftPad(acquirerBinData.getMcIcaNum(), 6, "0")).acqRefData(this.getAcqRefData(acquirerBinData.getAcqBin(), posTxnEntity.getRrn()));
        }
        McSplitTransactionService.log.info("Method mapToMcAcqTxnWorkEntity Completed");
        return mcAsqBuilder.build();
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
                case '3' -> '3';
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
    
    private String getAcqRefData(final String acqBin, final String rrn) {
        String arn = "2" + acqBin + this.julianDateConverter.getCurrentJulianYDDD(LocalDate.now());
        arn += StringUtils.substring(rrn, -11);
        return this.arnCheckDigit.addCheckDigit(arn);
    }
    
    @Transactional
    public McRTSTxnDataEntity mapToMcRtsTxnData(final PosTransactionEntity posTxnEntity, final Integer userSerialNumber, final Integer insCode, final Integer jobNumber) {
        final StringBuilder posData = new StringBuilder();
        final AcquirerBinEntity acquirerBinData = this.acquirerBinRepo.findByInsCodeAndBinType(insCode, 'M');
        final McRTSTxnDataEntity.McRTSTxnDataEntityBuilder mcRTSBuilder = McRTSTxnDataEntity.builder();
        mcRTSBuilder.lastUpdated(LocalDateTime.now()).updatedUser(userSerialNumber).institutionCode(insCode).intCode(posTxnEntity.getIntCode()).prjSerNumber(jobNumber).generalStatus(3).txnRefNumber(posTxnEntity.getSerialNumber()).txnType(posTxnEntity.getTxnCode()).messageTypeId(posTxnEntity.getMsgTypeId()).cardNumber(posTxnEntity.getCardNumber()).procCode(posTxnEntity.getProcCode()).txnAmount(posTxnEntity.getTxnAmount()).surchargeAmount(0.0).localDateTime(posTxnEntity.getLocalDateTime()).expiryDate(posTxnEntity.getExpiryDate()).posDataCode(posData.append(this.getCardInputCapability(posTxnEntity.getCardInputAbility())).append(this.getCardHoldAuthCapability(posTxnEntity.getChAuthAbility())).append(this.getCardCaptureCapability(posTxnEntity.getCardCaptureAbility())).append(this.getOprtEnviornment(posTxnEntity.getOprtEnvironment())).append(this.getCHPresent(posTxnEntity.getChPresent())).append(this.getCardPresent(posTxnEntity.getCardPresent())).append(Objects.nonNull(posTxnEntity.getCardInputMode()) ? posTxnEntity.getCardInputMode() : '0').append(Objects.nonNull(posTxnEntity.getChAuthMethod()) ? posTxnEntity.getChAuthMethod() : '9').append(Objects.nonNull(posTxnEntity.getChAuthEntity()) ? posTxnEntity.getChAuthEntity() : '9').append(Objects.nonNull(posTxnEntity.getCardOutPutAbility()) ? posTxnEntity.getCardOutPutAbility() : '0').append(Objects.nonNull(posTxnEntity.getTrlOutPutAbility()) ? posTxnEntity.getTrlOutPutAbility() : '0').append(this.pinCaptureAbility.getPinCaptureAbility(posTxnEntity.getPosEntryMode())).toString()).functionCode("200").msgReasonCode("1401").mcc(posTxnEntity.getMcc()).rrn(posTxnEntity.getRrn()).approvalCode(posTxnEntity.getApprovalCode()).responseCode(posTxnEntity.getResponseCode()).serviceCode(posTxnEntity.getServiceCode()).terminalId(posTxnEntity.getTerminalId()).merchantId(posTxnEntity.getMerchantId()).meName(posTxnEntity.getMeName()).meCity(posTxnEntity.getMeCity()).meCountry(posTxnEntity.getMeCountry()).mePinCode(posTxnEntity.getMePinCode()).chipTrlType(posTxnEntity.getChipTrlType()).txnFeeAmount(posTxnEntity.getTxnFeeAmount()).txnCurCode(posTxnEntity.getTxnCurCode()).ird(posTxnEntity.getIrd()).settlementIndicator((posTxnEntity.getOnusOffusFlag() == 'O') ? 'C' : 'M').cardSeqNumber(posTxnEntity.getCardSeqNumber()).appCryptogram(posTxnEntity.getAppCryptogram()).cryptInfoData(posTxnEntity.getCryptInfoData()).issAppData(posTxnEntity.getIssAppData()).upblNumber(posTxnEntity.getUpblNumber()).appTxnCounter(posTxnEntity.getAppTxnCounter()).trlVerResult(posTxnEntity.getTrlVerResult()).chipTxnType(posTxnEntity.getChipTxnType()).cryptAmount(posTxnEntity.getCryptAmount()).appICProfile(posTxnEntity.getAppICProfile()).trlConCode(posTxnEntity.getTrlConCode()).cvmResult(posTxnEntity.getCvmResult()).trlCapabilities(posTxnEntity.getTrlCapabilities()).ifdSerNumber(posTxnEntity.getIfdSerNumber()).tcc(posTxnEntity.getTcc()).chipCurCode(posTxnEntity.getChipCurCode()).chipTrlType(posTxnEntity.getChipTrlType()).trlAppVerNumber(posTxnEntity.getTrlAppVerNumber()).txnSeqCounter(null).issAuthData(posTxnEntity.getIssAuthData()).msgNumber(null).memberText(null).revIndiCator(posTxnEntity.getRevIndiCator()).maid(posTxnEntity.getMaid()).cardType(posTxnEntity.getCardType()).cardDomIntlFlag(posTxnEntity.getCardDomIntlFlag()).dmsSmsMode(posTxnEntity.getDmsSmsMode()).centreProcDate(posTxnEntity.getCentreProcDate()).fileProcDate(null).fileID(null).encryptedCardNumber(posTxnEntity.getEncCardNumber()).tipAmount(posTxnEntity.getTipAmount()).meCountryOfOrigin(posTxnEntity.getMeCountryOfOrigin()).mposAccDevType(posTxnEntity.getMposAccDevType());
        if (Objects.nonNull(posTxnEntity.getTxnCurCode())) {
            final CurrencyEntity currencyEntity = this.currencyRepo.findByCurrencyCode(posTxnEntity.getTxnCurCode());
            if (Objects.nonNull(currencyEntity)) {
                mcRTSBuilder.txnCurrencyExponent(currencyEntity.getCurExponent());
            }
        }
        if (posTxnEntity.getTerminalType().equalsIgnoreCase("MPOS")) {
            mcRTSBuilder.trlType("CT9");
        }
        else if (posTxnEntity.getPosEntryMode().startsWith("81")) {
            mcRTSBuilder.trlType("CT6");
        }
        else if (Objects.nonNull(posTxnEntity.getChPresent()) && posTxnEntity.getChPresent() == '5' && posTxnEntity.getCardInputMode() == '7') {
            mcRTSBuilder.trlType("CT6");
        }
        else {
            mcRTSBuilder.trlType("POI");
        }
        if (Objects.nonNull(posTxnEntity.getPosEntryMode()) && posTxnEntity.getPosEntryMode().startsWith("81")) {
            mcRTSBuilder.motoEcomIndicator("21" + posTxnEntity.getMotoEcomIndicator().substring(posTxnEntity.getMotoEcomIndicator().length() - 1));
        }
        if (posTxnEntity.getNetwork().equals("MCI")) {
            if (Objects.nonNull(posTxnEntity.getCardSeqNumber()) && posTxnEntity.getPosEntryMode().startsWith("05")) {
                mcRTSBuilder.cardSeqNumber(StringUtils.substring("000" + posTxnEntity.getCardSeqNumber(), ("000" + posTxnEntity.getCardSeqNumber()).length() - 3));
            }
            if (posTxnEntity.getTxnCode().matches("09")) {
                mcRTSBuilder.cashBackAmount(posTxnEntity.getCashBackAmount());
            }
            else if (Objects.nonNull(posTxnEntity.getAppCryptogram())) {
                mcRTSBuilder.cashBackAmount(posTxnEntity.getChipCashBack());
            }
            if (Objects.nonNull(posTxnEntity.getNetworkData())) {
                mcRTSBuilder.txnlifeCycleId(" " + (posTxnEntity.getNetworkData() + "             ").substring(0, 13) + "  ");
            }
        }
        else if (posTxnEntity.getNetwork().equals("MDS")) {
            if (Objects.isNull(posTxnEntity.getTxnId()) || posTxnEntity.getTxnId().isEmpty()) {
                mcRTSBuilder.txnlifeCycleId(null);
            }
            else if (posTxnEntity.getTxnId().length() >= 13 && (posTxnEntity.getTxnId().substring(9, 12).equals("0000") || posTxnEntity.getTxnId().substring(9, 12).equals("    "))) {
                final DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyMMdd");
                final String leftPart = (posTxnEntity.getTxnId() + "         ").substring(0, 9);
                if (Objects.nonNull(posTxnEntity.getLocalDateTime())) {
                    final String rightPart = posTxnEntity.getLocalDateTime().format(pattern);
                    mcRTSBuilder.txnlifeCycleId(" " + leftPart + rightPart + "  ");
                }
            }
            else {
                mcRTSBuilder.txnlifeCycleId(" " + (posTxnEntity.getTxnId() + "         ").substring(0, 13) + "  ").cardSeqNumber("");
            }
        }
        if (posTxnEntity.getTerminalType().equalsIgnoreCase("E-COM")) {
            mcRTSBuilder.posPgType("PG");
        }
        else {
            mcRTSBuilder.posPgType(posTxnEntity.getTerminalType());
        }
        if (Objects.nonNull(acquirerBinData)) {
            mcRTSBuilder.acqinstIdCode(StringUtils.leftPad(acquirerBinData.getMcIcaNum(), 6, "0")).orgInstIdCode(StringUtils.leftPad(acquirerBinData.getMcIcaNum(), 6, "0")).acqRefData(this.getAcqRefData(acquirerBinData.getAcqBin(), posTxnEntity.getRrn()));
        }
        McSplitTransactionService.log.info("Method mapToMcRtsTxnData Completed");
        return mcRTSBuilder.build();
    }
    
    public McSplitTransactionService(final CurrencyRepo currencyRepo, final AcquirerBinRepo acquirerBinRepo, final ARNCheckDigit arnCheckDigit, final JulianDateConverter julianDateConverter, final PinCaptureAbility pinCaptureAbility) {
        this.currencyRepo = currencyRepo;
        this.acquirerBinRepo = acquirerBinRepo;
        this.arnCheckDigit = arnCheckDigit;
        this.julianDateConverter = julianDateConverter;
        this.pinCaptureAbility = pinCaptureAbility;
    }
    
    static {
        log = LogManager.getLogger((Class)McSplitTransactionService.class);
    }
}
