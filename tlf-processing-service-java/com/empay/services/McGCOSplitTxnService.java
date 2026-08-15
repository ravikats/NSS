// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.services;

import org.apache.logging.log4j.LogManager;
import jakarta.transaction.Transactional;
import com.empay.common.entities.CurrencyEntity;
import com.empay.common.entities.AcquirerBinEntity;
import org.apache.commons.lang3.StringUtils;
import java.util.Objects;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.empay.entities.McGCOTxnWorkEntity;
import com.empay.entities.PosTransactionEntity;
import com.empay.common.repo.CurrencyRepo;
import com.empay.common.repo.CountriesRepository;
import com.empay.common.repo.InterfacesRepo;
import com.empay.repositories.BusinessDateRepo;
import com.empay.common.functions.PinCaptureAbility;
import com.empay.common.functions.JulianDateConverter;
import com.empay.common.functions.ARNCheckDigit;
import com.empay.common.repo.AcquirerBinRepo;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class McGCOSplitTxnService
{
    private static final Logger log;
    private final AcquirerBinRepo acquirerBinRepo;
    private final ARNCheckDigit arnCheckDigit;
    private final JulianDateConverter julianDateConverter;
    private final PinCaptureAbility pinCaptureAbility;
    private final BusinessDateRepo bussDate;
    private final InterfacesRepo intRepo;
    private final CountriesRepository countryRepo;
    private final CurrencyRepo currencyRepo;
    
    @Transactional
    public McGCOTxnWorkEntity mapToGCOTxnEntity(final PosTransactionEntity posTxnEntity, final Integer userSerialNumber, final Integer insCode, final Integer jobNumber) {
        final StringBuilder posData = new StringBuilder();
        final AcquirerBinEntity acquirerBinData = this.acquirerBinRepo.findByInsCodeAndBinType(insCode, 'M');
        final Integer intCode = this.intRepo.findByIntCategoryAndInsCode("OMANNET", insCode).getIntCode();
        final McGCOTxnWorkEntity.McGCOTxnWorkEntityBuilder mcGCOBuilder = McGCOTxnWorkEntity.builder();
        mcGCOBuilder.lastUpdated(LocalDateTime.now()).updatedUser(userSerialNumber).insCode(insCode).intCode(intCode).generalStatus(3).txnRefSerNumber(posTxnEntity.getSerialNumber()).prjSerNumber(jobNumber).businessDate(this.bussDate.findByInsCode(insCode).getBusinessDate()).procCode("000000").approvalCode(posTxnEntity.getApprovalCode()).serviceCode(posTxnEntity.getServiceCode()).fileProcDate(null).mti("1240").txnType(posTxnEntity.getTxnCode()).txnAmount(posTxnEntity.getTxnAmount()).surchargeAmount(0.0).localDateTime(posTxnEntity.getLocalDateTime()).chPresent(Objects.nonNull(posTxnEntity.getChPresent()) ? posTxnEntity.getChPresent().toString() : "0").cardPresent(Objects.nonNull(posTxnEntity.getCardPresent()) ? posTxnEntity.getCardPresent().toString() : "0").posEntryMode(posTxnEntity.getPosEntryMode()).posDataMode(posData.append(this.getCardInputCapability(posTxnEntity.getCardInputAbility())).append(this.getCardHoldAuthCapability(posTxnEntity.getChAuthAbility())).append(this.getCardCaptureCapability(posTxnEntity.getCardCaptureAbility())).append(this.getOprtEnviornment(posTxnEntity.getOprtEnvironment())).append("0").append("1").append(Objects.nonNull(posTxnEntity.getCardInputMode()) ? posTxnEntity.getCardInputMode() : '0').append(Objects.nonNull(posTxnEntity.getChAuthMethod()) ? posTxnEntity.getChAuthMethod() : '9').append(Objects.nonNull(posTxnEntity.getChAuthEntity()) ? posTxnEntity.getChAuthEntity() : '9').append(Objects.nonNull(posTxnEntity.getCardOutPutAbility()) ? posTxnEntity.getCardOutPutAbility() : '0').append(Objects.nonNull(posTxnEntity.getTrlOutPutAbility()) ? posTxnEntity.getTrlOutPutAbility() : '0').append(this.pinCaptureAbility.getPinCaptureAbility(posTxnEntity.getPosEntryMode())).toString()).mcc(posTxnEntity.getMcc()).rrn(posTxnEntity.getRrn()).terminalId(posTxnEntity.getTerminalId()).merchantId(posTxnEntity.getMerchantId()).meName(posTxnEntity.getMeName()).meAddress(posTxnEntity.getCardAccepStreetAddress()).meCity(posTxnEntity.getMeCity()).meZipCode(posTxnEntity.getMePinCode()).txnCurCode(posTxnEntity.getTxnCurCode()).fileId(null).encryptedCardNumber(posTxnEntity.getEncCardNumber()).settlementIndicator('C').functionCode("200").msgReasonCode("1401").cardType(posTxnEntity.getCardType()).cardDomIntlFlag(posTxnEntity.getCardDomIntlFlag()).network(posTxnEntity.getNetwork()).meCountry(posTxnEntity.getMeCountry()).mposAccDevType(posTxnEntity.getMposAccDevType()).customerServicePhNum(posTxnEntity.getMerchantContactInfo()).accepterUrlAddress(posTxnEntity.getAccepterUrlAddress());
        if (Objects.nonNull(posTxnEntity.getNetworkData())) {
            mcGCOBuilder.txnlifeCycleId(" " + (posTxnEntity.getNetworkData() + "             ").substring(0, 13) + "  ");
        }
        if (Objects.nonNull(acquirerBinData)) {
            mcGCOBuilder.acqInstIdCode(StringUtils.leftPad(acquirerBinData.getMcIcaNum(), 6, "0")).arn(this.getAcqRefData(acquirerBinData.getAcqBin(), posTxnEntity.getRrn()));
        }
        if (Objects.nonNull(posTxnEntity.getTxnCurCode())) {
            final CurrencyEntity currencyEntity = this.currencyRepo.findByCurrencyCode(posTxnEntity.getTxnCurCode());
            if (Objects.nonNull(currencyEntity)) {
                mcGCOBuilder.txnCurrencyExponent(currencyEntity.getCurExponent());
            }
            else {
                mcGCOBuilder.txnCurrencyExponent(48);
            }
        }
        McGCOSplitTxnService.log.info("Method mapToGCOTxnEntity Completed");
        return mcGCOBuilder.build();
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
    
    private String getAcqRefData(final String acqBin, final String rrn) {
        String arn = "2" + acqBin + this.julianDateConverter.getCurrentJulianYDDD(LocalDate.now());
        arn += StringUtils.substring(rrn, -11);
        return this.arnCheckDigit.addCheckDigit(arn);
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
    
    public McGCOSplitTxnService(final AcquirerBinRepo acquirerBinRepo, final ARNCheckDigit arnCheckDigit, final JulianDateConverter julianDateConverter, final PinCaptureAbility pinCaptureAbility, final BusinessDateRepo bussDate, final InterfacesRepo intRepo, final CountriesRepository countryRepo, final CurrencyRepo currencyRepo) {
        this.acquirerBinRepo = acquirerBinRepo;
        this.arnCheckDigit = arnCheckDigit;
        this.julianDateConverter = julianDateConverter;
        this.pinCaptureAbility = pinCaptureAbility;
        this.bussDate = bussDate;
        this.intRepo = intRepo;
        this.countryRepo = countryRepo;
        this.currencyRepo = currencyRepo;
    }
    
    static {
        log = LogManager.getLogger((Class)McGCOSplitTxnService.class);
    }
}
