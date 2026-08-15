// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.services;

import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;
import java.util.Optional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import com.empay.entities.AmexAcqTxnWorkEntity;
import com.empay.entities.PosTransactionEntity;
import com.empay.util.CurrencyUtil;
import com.empay.common.repo.InterfacesRepo;
import org.springframework.stereotype.Service;

@Service
public class AmexSplitTxnService
{
    private final InterfacesRepo intRepo;
    private final CurrencyUtil currencyUtil;
    
    public AmexAcqTxnWorkEntity mapToAmexAcqTxnEntity(final PosTransactionEntity posTxnEntity, final Integer user, final Integer insCode, final Integer jobNumber) {
        final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyMMdd");
        final CurrencyUtil currencyUtil = this.currencyUtil;
        final int divisor = CurrencyUtil.getDivisor(posTxnEntity.getTxnCurCode());
        final Integer intCode = this.intRepo.findByIntCategoryAndInsCode("AMEX", insCode).getIntCode();
        final AmexAcqTxnWorkEntity.AmexAcqTxnWorkEntityBuilder amexBuilder = AmexAcqTxnWorkEntity.builder();
        amexBuilder.lastUpdated(LocalDateTime.now()).updatedUser(user).institutionCode(insCode).intCode(intCode).prjSerNumber(jobNumber).txnRefSerNumber(posTxnEntity.getSerialNumber()).txnType(posTxnEntity.getTxnCode()).cardNumber(posTxnEntity.getCardNumber()).procCode((posTxnEntity.getProcCode() != null) ? posTxnEntity.getProcCode() : "000000").txnAmount(posTxnEntity.getTxnAmount()).surchargeAmount(0.0).localDateTime(posTxnEntity.getLocalDateTime()).posDataCode(posTxnEntity.getPosEntryMode().substring(0, 12)).mcc(posTxnEntity.getMcc()).rrn(posTxnEntity.getRrn()).approvalCode(posTxnEntity.getTxnType().equals("06") ? " ".repeat(6) : posTxnEntity.getApprovalCode()).terminalId(posTxnEntity.getTerminalId()).merchantId(posTxnEntity.getMerchantId()).mappedMid(null).meName(posTxnEntity.getMeName()).meCity(posTxnEntity.getMeCity()).mePinCode((posTxnEntity.getMePinCode() != null) ? posTxnEntity.getMePinCode() : "123456").meCountry(posTxnEntity.getMeCountry()).motoEcomIndicator(posTxnEntity.getMotoEcomIndicator()).txnCurCode(posTxnEntity.getTxnCurCode()).cardSeqNumber(posTxnEntity.getPosEntryMode().substring(1, 2).equals("05") ? String.format("%03d", posTxnEntity.getCardSeqNumber()) : null).appCryptogram(posTxnEntity.getAppCryptogram()).cryptInfoData(posTxnEntity.getCryptInfoData()).issAppData(posTxnEntity.getIssAppData()).upblNumber(posTxnEntity.getUpblNumber()).appTxnCounter(posTxnEntity.getAppTxnCounter()).trlVerResult(posTxnEntity.getTrlVerResult()).txnDate(Optional.ofNullable(posTxnEntity.getChipTxnDate()).filter(date -> !date.isEmpty()).map(date -> LocalDate.parse(date, inputFormatter)).orElse(null)).cryptAmount(posTxnEntity.getCryptAmount()).appICProfile(posTxnEntity.getAppICProfile()).trlConCode(posTxnEntity.getTrlConCode()).cashBackAmount(posTxnEntity.getTxnType().equals("07") ? posTxnEntity.getCashBackAmount() : ((posTxnEntity.getAppCryptogram() != null) ? posTxnEntity.getChipCashBack() : null)).txnId(String.format("%15s", Optional.ofNullable(posTxnEntity.getTxnId()).orElse("").trim()).replace(' ', '0')).trlBthNumber(0).cardType(posTxnEntity.getCardType()).cardDomIntlFlag(posTxnEntity.getCardDomIntlFlag()).dmsSmsMode(posTxnEntity.getDmsSmsMode()).trlType(posTxnEntity.getTerminalType().equals("E-com") ? "PG" : posTxnEntity.getTerminalType()).centreProcDate(posTxnEntity.getCentreProcDate()).genStatus(3).encryptedCardNumber(posTxnEntity.getEncCardNumber()).expiryDate(posTxnEntity.getExpiryDate()).locationAddress(posTxnEntity.getCardAccepStreetAddress()).mappedMid(posTxnEntity.getAmexMerchantId()).contactEmail(posTxnEntity.getMerchantContactInfo()).trlLocation(posTxnEntity.getMerchantLocationId()).locRegionCode(posTxnEntity.getLocationRegionCode()).stan(posTxnEntity.getStan()).invoiceNumber(posTxnEntity.getInvoiceNumber());
        if (posTxnEntity.getPosEntryMode() != null && posTxnEntity.getPosEntryMode().length() >= 7 && posTxnEntity.getPosEntryMode().charAt(6) == '5') {
            amexBuilder.emv(this.emvParsing(posTxnEntity.getPosEntryMode(), posTxnEntity, divisor));
        }
        return amexBuilder.build();
    }
    
    private String emvParsing(final String posEntryMode, final PosTransactionEntity posTxnEntity, final int divisor) {
        return Stream.of(new String[] { "AGNS", "0001", this.pad(posTxnEntity.getAppCryptogram(), 16), this.pad(posTxnEntity.getIssAppData(), 64), this.pad(posTxnEntity.getUpblNumber(), 8), this.pad(posTxnEntity.getAppTxnCounter(), 4), this.pad(posTxnEntity.getTrlVerResult(), 10), this.pad(posTxnEntity.getChipTxnDate(), 6), this.pad(posTxnEntity.getChipTxnType(), 2), this.pad(this.formatAmount(posTxnEntity.getCryptAmount(), divisor), 12), this.pad(StringUtils.leftPad((String)StringUtils.defaultIfBlank((CharSequence)posTxnEntity.getChipCurCode(), (CharSequence)"0"), 4, '0'), 4), this.pad(StringUtils.leftPad((String)StringUtils.defaultIfBlank((CharSequence)posTxnEntity.getTrlConCode(), (CharSequence)"0"), 4, '0'), 4), this.pad(posTxnEntity.getAppICProfile(), 4), this.pad(this.formatAmount(posTxnEntity.getChipCashBack(), divisor), 12), this.pad(posTxnEntity.getCardSeqNumber(), 2), this.pad(posTxnEntity.getCryptInfoData(), 2) }).collect(Collectors.joining());
    }
    
    private String pad(final String value, final int length) {
        return StringUtils.rightPad((String)Optional.ofNullable(value).orElse(""), length).substring(0, length);
    }
    
    private String formatAmount(final Double amount, final int divisor) {
        return String.format("%012d", Math.round(Optional.ofNullable(amount).orElse(0.0) * divisor));
    }
    
    public AmexSplitTxnService(final InterfacesRepo intRepo, final CurrencyUtil currencyUtil) {
        this.intRepo = intRepo;
        this.currencyUtil = currencyUtil;
    }
}
