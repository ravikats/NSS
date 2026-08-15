// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.services;

import com.empay.common.entities.AcquirerBinEntity;
import java.util.Objects;
import java.time.LocalDate;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import com.empay.entities.VisaAcqTxnWorkEntity;
import com.empay.entities.PosTransactionEntity;
import com.empay.common.repo.InterfacesRepo;
import com.empay.common.functions.JulianDateConverter;
import com.empay.common.functions.ARNCheckDigit;
import com.empay.common.repo.AcquirerBinRepo;
import org.springframework.stereotype.Service;

@Service
public class VisaSplitTxnService
{
    private final AcquirerBinRepo acquirerBinRepo;
    private final ARNCheckDigit arnCheckDigit;
    private final JulianDateConverter julianDateConverter;
    private final InterfacesRepo intRepo;
    
    public VisaAcqTxnWorkEntity mapToVisaAcqTxnEntity(final PosTransactionEntity posTxnEntity, final Integer user, final Integer insCode, final Integer jobNumber) {
        final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyMMdd");
        final AcquirerBinEntity acquirerBinData = this.acquirerBinRepo.findByInsCodeAndBinType(insCode, 'V');
        final Integer intCode = this.intRepo.findByIntCategoryAndInsCode("VISA", insCode).getIntCode();
        final VisaAcqTxnWorkEntity.VisaAcqTxnWorkEntityBuilder visaBuilder = VisaAcqTxnWorkEntity.builder();
        visaBuilder.lastUpdated(LocalDateTime.now()).updatedUser(user).institutionCode(insCode).intCode(intCode).prjSerNumber(jobNumber).genStatus(3).txnRefNumber(posTxnEntity.getSerialNumber()).txnType(posTxnEntity.getTxnCode()).txnCode(posTxnEntity.getTxnCode()).memberText("00000000").txnAmount(posTxnEntity.getTxnAmount()).schgAmount(0.0).meName(posTxnEntity.getMeName()).meCity(posTxnEntity.getMeCity()).meCountry(StringUtils.substring(posTxnEntity.getMeCountry(), 0, 2)).mcc(posTxnEntity.getMcc()).approvalCode(posTxnEntity.getApprovalCode()).chIdMethod(posTxnEntity.getChIdMethod()).collOnlyFlag((posTxnEntity.getOnusOffusFlag() == 'O') ? 'C' : ' ').posEntryMode(posTxnEntity.getPosEntryMode().substring(0, 2)).memberText(null).merchantId(posTxnEntity.getMerchantId()).terminalId(posTxnEntity.getTerminalId()).accSelection(posTxnEntity.getProcCode().charAt(3)).cashbackAmount(posTxnEntity.getCashBackAmount()).txnId(String.format("%015d", Long.parseLong(Optional.ofNullable(posTxnEntity.getTxnId()).orElse("0")))).respCode(String.format("%02d", Long.parseLong(Optional.ofNullable(posTxnEntity.getResponseCode()).orElse("0")))).trlTxnDate(Optional.ofNullable(posTxnEntity.getChipTxnDate()).filter(date -> !date.isEmpty()).map(date -> LocalDate.parse(date, inputFormatter)).orElse(null)).cryptAmount(posTxnEntity.getCryptAmount()).encCardNumber(posTxnEntity.getEncCardNumber()).authCharIndicator(posTxnEntity.getAuthCharecteresticId()).accFundSource(posTxnEntity.getAccountFundingSource()).productId(posTxnEntity.getProductId()).validationCode(posTxnEntity.getValidationCode()).spendQualiIndictor(posTxnEntity.getSpendQualificationInd()).retRefNumber(posTxnEntity.getRrn()).purchaseDate(posTxnEntity.getTxnDateTime()).txnCurCode(posTxnEntity.getTxnCurCode()).acqBussId("00000000").usageCode('1').reasonCode("00").setlFlag('9').terminalCapability((posTxnEntity.getTrlCapabilities() == null) ? ' ' : posTxnEntity.getTrlCapabilities().charAt(0)).stan(posTxnEntity.getStan()).trlCapProfile(posTxnEntity.getChipTrlCapabilities()).trlCountryCode(posTxnEntity.getTrlConCode()).upblNumber(posTxnEntity.getUpblNumber()).cardSeqNumber(posTxnEntity.getCardSeqNumber()).appTxnCounter(posTxnEntity.getAppTxnCounter()).appIcProfile(posTxnEntity.getAppICProfile()).appCryptogram(posTxnEntity.getAppCryptogram()).trlVerResult(posTxnEntity.getTrlVerResult()).formFactorIndicator(posTxnEntity.getFormFactorIndicator()).issScriptResult("").serviceCode(posTxnEntity.getServiceCode()).txnFeeAmount(posTxnEntity.getTxnFeeAmount()).authAmount(posTxnEntity.getAuthAmount()).dccIndicator(posTxnEntity.getDccIndicator()).network(posTxnEntity.getNetwork()).smsDmsFlag(posTxnEntity.getDmsSmsMode()).domIntlFlag(posTxnEntity.getCardDomIntlFlag()).cardType(posTxnEntity.getCardType()).dccAmount(posTxnEntity.getDccAmount()).dccCurrency(posTxnEntity.getDccCurrency()).acceptanceTrlIndicator(posTxnEntity.getAcceptanceTrlIndicator());
        if (Objects.nonNull(posTxnEntity.getOprtEnvironment()) && !posTxnEntity.getOprtEnvironment().isEmpty()) {
            visaBuilder.posEnvironment(posTxnEntity.getOprtEnvironment().charAt(0));
        }
        if (posTxnEntity.getIssAppData() != null) {
            try {
                visaBuilder.issAppDataB1(StringUtils.substring(posTxnEntity.getIssAppData(), 0, 2)).issAppDataB2(StringUtils.substring(posTxnEntity.getIssAppData(), 2, 4)).issAppDataB3(StringUtils.substring(posTxnEntity.getIssAppData(), 4, 6)).issAppDataB4(StringUtils.substring(posTxnEntity.getIssAppData(), 6, 14)).issAppDataB8(StringUtils.substring(posTxnEntity.getIssAppData(), 14, 16)).issAppDataB9(StringUtils.substring(posTxnEntity.getIssAppData(), 16, 32)).issAppDataB17(StringUtils.substring(posTxnEntity.getIssAppData(), 32, 34)).issAppDataB18(StringUtils.substring(posTxnEntity.getIssAppData(), 34, 64));
            }
            catch (final Exception ex) {}
        }
        if (posTxnEntity.getProcCode() != null) {
            visaBuilder.procCode(posTxnEntity.getProcCode());
        }
        else {
            visaBuilder.procCode("000000");
        }
        if (posTxnEntity.getReImbursementAttribute() != null) {
            visaBuilder.reimAttribute(posTxnEntity.getReImbursementAttribute());
        }
        else {
            visaBuilder.reimAttribute('B');
        }
        try {
            visaBuilder.marketSpecDataInd(Optional.ofNullable(posTxnEntity.getMarketSpecAuthDataInd()).orElse("0").charAt(0));
        }
        catch (final Exception e) {
            visaBuilder.marketSpecDataInd('0');
        }
        if (posTxnEntity.getMeCategoryType() == 'S' && posTxnEntity.getCardDomIntlFlag() == 'D' && posTxnEntity.getCardType() == 'D') {
            visaBuilder.feePrgIndicator("I10");
        }
        else {
            visaBuilder.feePrgIndicator("  ");
        }
        if (Objects.nonNull(acquirerBinData)) {
            visaBuilder.arn(this.getAcqRefData(acquirerBinData.getAcqBin(), posTxnEntity.getRrn()));
        }
        if (posTxnEntity.getMotoEcomIndicator() != null) {
            visaBuilder.motoEcomIndicator(posTxnEntity.getMotoEcomIndicator().charAt(posTxnEntity.getMotoEcomIndicator().length() / 2));
        }
        return visaBuilder.build();
    }
    
    private String getAcqRefData(final String acqBin, final String rrn) {
        String arn = "2" + acqBin + this.julianDateConverter.getCurrentJulianYDDD(LocalDate.now());
        arn += StringUtils.substring(rrn, -11);
        return this.arnCheckDigit.addCheckDigit(arn);
    }
    
    public VisaSplitTxnService(final AcquirerBinRepo acquirerBinRepo, final ARNCheckDigit arnCheckDigit, final JulianDateConverter julianDateConverter, final InterfacesRepo intRepo) {
        this.acquirerBinRepo = acquirerBinRepo;
        this.arnCheckDigit = arnCheckDigit;
        this.julianDateConverter = julianDateConverter;
        this.intRepo = intRepo;
    }
}
