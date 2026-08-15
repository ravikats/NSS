// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.services;

import org.apache.commons.lang3.StringUtils;
import com.empay.common.entities.AcquirerBinEntity;
import java.util.Objects;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.empay.entities.JaywanAcqTxnWorkEntity;
import com.empay.entities.PosTransactionEntity;
import com.empay.common.functions.JulianDateConverter;
import com.empay.common.functions.ARNCheckDigit;
import com.empay.common.repo.AcquirerBinRepo;
import com.empay.common.repo.InterfacesRepo;
import org.springframework.stereotype.Service;

@Service
public class JaywanSplitTxnService
{
    private final InterfacesRepo intRepo;
    private final AcquirerBinRepo acquirerBinRepo;
    private final ARNCheckDigit arnCheckDigit;
    private final JulianDateConverter julianDateConverter;
    
    public JaywanAcqTxnWorkEntity mapToJaywanAcqTxnEntity(final PosTransactionEntity posTxnEntity, final Integer user, final Integer insCode, final Integer jobNumber) {
        final AcquirerBinEntity acquirerBinData = this.acquirerBinRepo.findByInsCodeAndBinType(insCode, 'J');
        final Integer intCode = this.intRepo.findByIntCategoryAndInsCode("JAYWAN", insCode).getIntCode();
        final JaywanAcqTxnWorkEntity.JaywanAcqTxnWorkEntityBuilder jaywanBuilder = JaywanAcqTxnWorkEntity.builder();
        final String originalMti = posTxnEntity.getMsgTypeId();
        final String processingCode = posTxnEntity.getProcCode();
        final String mtiToSet = this.getMti(originalMti, processingCode);
        final String functionCodeToSet = this.getFunctionCode(originalMti, processingCode);
        jaywanBuilder.lastUpdated(LocalDateTime.now()).updatedUser(user).institutionCode(jobNumber).intCode(intCode).prjSerNumber(jobNumber).txnRefNumber(posTxnEntity.getSerialNumber()).txnType(posTxnEntity.getTxnType()).txnCode(posTxnEntity.getTxnCode()).messageTypeId(mtiToSet).functionCode(functionCodeToSet).localDateTime(posTxnEntity.getLocalDateTime()).cardNumber(posTxnEntity.getCardNumber()).approvalCode(posTxnEntity.getApprovalCode()).terminalId(posTxnEntity.getTerminalId()).txnAmount(posTxnEntity.getTxnAmount()).settledAmount(posTxnEntity.getSetlAmount()).billAmount(null).surchargeAmount(0.0).convRate(null).txnCurCode(posTxnEntity.getTxnCurCode()).cashBackAmount(posTxnEntity.getCashBackAmount()).rrn(posTxnEntity.getRrn()).merchantId(posTxnEntity.getMerchantId()).meName(posTxnEntity.getMeName()).meCity(posTxnEntity.getMeCity()).meStateCode(posTxnEntity.getMePinCode()).meCountry(posTxnEntity.getMeCountry()).mcc(posTxnEntity.getMcc()).posEntryMode(posTxnEntity.getPosEntryMode()).acqinstIdCode(posTxnEntity.getAcqinstIdCode()).revIndiCator(posTxnEntity.getRevIndiCator()).cardDomIntlFlag(posTxnEntity.getCardDomIntlFlag()).trlType(posTxnEntity.getChipTrlType()).meCategoryType(posTxnEntity.getMeCategoryType()).cardType(posTxnEntity.getCardType()).dmsSmsMode(posTxnEntity.getDmsSmsMode()).centreProcDate(posTxnEntity.getCentreProcDate()).fileProcDate(null).fileID(null).genStatus(3).encryptedCardNumber(posTxnEntity.getEncCardNumber()).responseCode(posTxnEntity.getResponseCode()).motoEcomIndicator(posTxnEntity.getMotoEcomIndicator()).settlDate(posTxnEntity.getSetlDate()).settlIndiCator(posTxnEntity.getSettlementIndicator()).posConditionCode(posTxnEntity.getPosConditionCode()).fullPartialIndiCator(null);
        if (Objects.nonNull(acquirerBinData)) {
            jaywanBuilder.acqRefData(this.getAcqRefData(acquirerBinData.getAcqBin(), posTxnEntity.getRrn()));
        }
        return jaywanBuilder.build();
    }
    
    private String getMti(final String originalMti, final String processingCode) {
        if (originalMti == null || processingCode == null) {
            throw new IllegalArgumentException("MTI or Processing Code cannot be null");
        }
        if ("0430".equals(originalMti)) {
            return "8144";
        }
        if ("0410".equals(originalMti)) {
            if (processingCode.startsWith("20")) {
                return "1240";
            }
            if (processingCode.startsWith("00")) {
                return "1420";
            }
        }
        else if ("0110".equals(originalMti) || "0210".equals(originalMti) || "0130".equals(originalMti)) {
            if ("0110".equals(originalMti) && processingCode.startsWith("20")) {
                return "1240";
            }
            if (processingCode.startsWith("00")) {
                return "1240";
            }
        }
        throw new IllegalArgumentException("Unsupported MTI and Processing Code combination: " + originalMti + ", " + processingCode);
    }
    
    private String getFunctionCode(final String originalMti, final String processingCode) {
        if (originalMti == null || processingCode == null) {
            throw new IllegalArgumentException("MTI or Processing Code cannot be null");
        }
        if ("0430".equals(originalMti)) {
            return "266";
        }
        if ("0410".equals(originalMti)) {
            if (processingCode.startsWith("20")) {
                return "269";
            }
            if (processingCode.startsWith("00")) {
                return "420";
            }
        }
        else if ("0110".equals(originalMti)) {
            if (processingCode.startsWith("20")) {
                return "262";
            }
            if (processingCode.startsWith("00")) {
                return "200";
            }
        }
        else if (("0210".equals(originalMti) || "0130".equals(originalMti)) && processingCode.startsWith("00")) {
            return "200";
        }
        throw new IllegalArgumentException("Unsupported MTI and Processing Code combination: " + originalMti + ", " + processingCode);
    }
    
    private String getAcqRefData(final String acqBin, final String rrn) {
        String arn = "2" + acqBin + this.julianDateConverter.getCurrentJulianYDDD(LocalDate.now());
        arn += StringUtils.substring(rrn, -11);
        return this.arnCheckDigit.addCheckDigit(arn);
    }
    
    public JaywanSplitTxnService(final InterfacesRepo intRepo, final AcquirerBinRepo acquirerBinRepo, final ARNCheckDigit arnCheckDigit, final JulianDateConverter julianDateConverter) {
        this.intRepo = intRepo;
        this.acquirerBinRepo = acquirerBinRepo;
        this.arnCheckDigit = arnCheckDigit;
        this.julianDateConverter = julianDateConverter;
    }
}
