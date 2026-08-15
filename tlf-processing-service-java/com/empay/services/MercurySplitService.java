// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.services;

import org.apache.logging.log4j.LogManager;
import com.empay.common.entities.CurrencyEntity;
import com.empay.common.entities.AcquirerBinEntity;
import org.apache.commons.lang3.StringUtils;
import java.util.Objects;
import java.time.LocalDate;
import com.empay.util.MercuryPosUtil;
import com.empay.util.MercuryChargeTypeUtil;
import java.time.LocalDateTime;
import com.empay.entities.MercuryAcqTxnWorkEntity;
import com.empay.entities.PosTransactionEntity;
import com.empay.common.repo.InterfacesRepo;
import com.empay.common.functions.ARNCheckDigit;
import com.empay.common.functions.JulianDateConverter;
import com.empay.common.repo.AcquirerBinRepo;
import com.empay.common.repo.CurrencyRepo;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class MercurySplitService
{
    private static final Logger log;
    private final CurrencyRepo currencyRepo;
    private final AcquirerBinRepo acquirerBinRepo;
    private final JulianDateConverter julianDateConverter;
    private final ARNCheckDigit arnCheckDigit;
    private final InterfacesRepo intRepo;
    
    public MercuryAcqTxnWorkEntity mapToMercuryAcqTxnEntity(final PosTransactionEntity posTxnEntity, final Integer userSerialNumber, final Integer insCode, final Integer jobNumber) {
        final AcquirerBinEntity acquirerBinData = this.acquirerBinRepo.findByInsCodeAndBinType(insCode, 'E');
        final Integer intCode = this.intRepo.findByIntCategoryAndInsCode("MERCURY", insCode).getIntCode();
        final MercuryAcqTxnWorkEntity.MercuryAcqTxnWorkEntityBuilder mercuryAsqBuilder = MercuryAcqTxnWorkEntity.builder();
        mercuryAsqBuilder.lastUpdated(LocalDateTime.now()).updatedUser(userSerialNumber).institutionCode(insCode).intCode(intCode).prjSerNumber(jobNumber).generalStatus(3).txnRefNumber(posTxnEntity.getSerialNumber()).rrn(posTxnEntity.getRrn()).merchantId(posTxnEntity.getMerchantId()).terminalId(posTxnEntity.getTerminalId()).txnType(posTxnEntity.getTxnCode()).cardNumber(posTxnEntity.getCardNumber()).txnAmount(posTxnEntity.getTxnAmount()).surchargeAmount(0.0).localDateTime(posTxnEntity.getLocalDateTime()).chargeType(MercuryChargeTypeUtil.getChargeTypeCode(posTxnEntity.getMcc())).typeOfCharge(MercuryPosUtil.getTYPCH(posTxnEntity.getPosEntryMode())).geoArea(this.getGEO(posTxnEntity.getMeCountry())).txnDate(posTxnEntity.getTxnDateTime().toLocalDate()).cardInputMode(MercuryPosUtil.getCPTRM(posTxnEntity.getPosEntryMode())).cardInputCapability(MercuryPosUtil.getCRDINP(posTxnEntity.getPosEntryMode())).meName(posTxnEntity.getMeName()).meCity(posTxnEntity.getMeCity()).meCountry(posTxnEntity.getMeCountry()).cardAccepStreetAddress(posTxnEntity.getCardAccepStreetAddress()).cardAccepStateCode(posTxnEntity.getCardAccepStateCode()).mePinCode(posTxnEntity.getMePinCode()).estPhoneNumber((posTxnEntity.getMerchantContactInfo() == null) ? null : posTxnEntity.getMerchantContactInfo().substring(0, Math.min(posTxnEntity.getMerchantContactInfo().length(), 20))).mcc(posTxnEntity.getMcc()).cardType(posTxnEntity.getCardType()).approvalCode(posTxnEntity.getApprovalCode()).cardDomIntlFlag(posTxnEntity.getCardDomIntlFlag()).dmsSmsMode(posTxnEntity.getDmsSmsMode()).encryptedCardNumber(posTxnEntity.getEncCardNumber()).settlementIndicator((posTxnEntity.getOnusOffusFlag() == 'O') ? 'C' : 'M').txnFeeAmount(posTxnEntity.getTxnFeeAmount()).responseCode(posTxnEntity.getResponseCode()).cardSeqNumber(posTxnEntity.getCardSeqNumber()).appICProfile(posTxnEntity.getAppICProfile()).appTxnCounter(posTxnEntity.getAppTxnCounter()).appCryptogram(posTxnEntity.getAppCryptogram()).cryptAmount(posTxnEntity.getCryptAmount()).cryptInfoData(posTxnEntity.getCryptInfoData()).cvmResult(posTxnEntity.getCvmResult()).dedicatedFileName(posTxnEntity.getDedicatedFileName()).ifdSerNumber(posTxnEntity.getIfdSerNumber()).issAppData(posTxnEntity.getIssAppData()).issAuthData(posTxnEntity.getIssAuthData()).trlConCode(posTxnEntity.getTrlConCode()).trlAppVerNumber(posTxnEntity.getTrlAppVerNumber()).chipTrlCapabilities(posTxnEntity.getChipTrlCapabilities()).chipTrlType(posTxnEntity.getChipTrlType()).trlVerResult(posTxnEntity.getTrlVerResult()).chipTxnDate(posTxnEntity.getChipTxnDate()).chipTxnType(posTxnEntity.getChipTxnType()).chipCurCode(posTxnEntity.getChipCurCode()).upblNumber(posTxnEntity.getUpblNumber()).centreProcDate(posTxnEntity.getCentreProcDate()).chPresent(posTxnEntity.getChPresent()).posEntryMode(posTxnEntity.getPosEntryMode()).fileProcDate(null).fileID(null);
        if (Objects.nonNull(posTxnEntity.getTxnCurCode())) {
            final CurrencyEntity currencyEntity = this.currencyRepo.findByCurrencyCode(posTxnEntity.getTxnCurCode());
            if (Objects.nonNull(currencyEntity)) {
                mercuryAsqBuilder.txnCurrencyExponent(currencyEntity.getCurExponent());
            }
        }
        if (Objects.nonNull(acquirerBinData)) {
            mercuryAsqBuilder.acqinstIdCode(StringUtils.leftPad(acquirerBinData.getMcIcaNum(), 6, "0")).orgInstIdCode(StringUtils.leftPad(acquirerBinData.getMcIcaNum(), 6, "0")).acqRefData(this.getAcqRefData(acquirerBinData.getAcqBin(), posTxnEntity.getRrn()));
        }
        if (posTxnEntity.getTerminalType().equalsIgnoreCase("MPOS")) {
            mercuryAsqBuilder.trlType("CT9");
        }
        else if (posTxnEntity.getPosEntryMode().startsWith("81")) {
            mercuryAsqBuilder.trlType("CT6");
        }
        else if (Objects.nonNull(posTxnEntity.getChPresent()) && posTxnEntity.getChPresent() == '5' && posTxnEntity.getCardInputMode() == '7') {
            mercuryAsqBuilder.trlType("CT6");
        }
        else {
            mercuryAsqBuilder.trlType("POI");
        }
        if (posTxnEntity.getTxnCode().matches("09")) {
            mercuryAsqBuilder.cashBackAmount(posTxnEntity.getCashBackAmount());
        }
        else if (Objects.nonNull(posTxnEntity.getAppCryptogram())) {
            mercuryAsqBuilder.cashBackAmount(posTxnEntity.getChipCashBack());
        }
        if (Objects.nonNull(posTxnEntity.getPosEntryMode()) && posTxnEntity.getPosEntryMode().startsWith("81")) {
            mercuryAsqBuilder.motoEcomIndicator("21" + posTxnEntity.getMotoEcomIndicator().substring(posTxnEntity.getMotoEcomIndicator().length() - 1));
        }
        MercurySplitService.log.info("Method mapToMercuryAcqTxnEntity Completed");
        return mercuryAsqBuilder.build();
    }
    
    private String getAcqRefData(final String acqBin, final String rrn) {
        String arn = "2" + acqBin + this.julianDateConverter.getCurrentJulianYDDD(LocalDate.now());
        arn += StringUtils.substring(rrn, -11);
        return this.arnCheckDigit.addCheckDigit(arn);
    }
    
    public String getGEO(final String country) {
        if (country == null || country.isBlank()) {
            return "";
        }
        final String upperCase = country.trim().toUpperCase();
        switch (upperCase) {
            case "AED": {
                return "784";
            }
            case "USD": {
                return "840";
            }
            case "EUR": {
                return "978";
            }
            default: {
                return "784";
            }
        }
    }
    
    public MercurySplitService(final CurrencyRepo currencyRepo, final AcquirerBinRepo acquirerBinRepo, final JulianDateConverter julianDateConverter, final ARNCheckDigit arnCheckDigit, final InterfacesRepo intRepo) {
        this.currencyRepo = currencyRepo;
        this.acquirerBinRepo = acquirerBinRepo;
        this.julianDateConverter = julianDateConverter;
        this.arnCheckDigit = arnCheckDigit;
        this.intRepo = intRepo;
    }
    
    static {
        log = LogManager.getLogger((Class)MercurySplitService.class);
    }
}
