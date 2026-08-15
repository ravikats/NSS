/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.entities.McNetworkDataEntity
 *  com.empay.entities.McNetworkDataEntity$McNetworkDataEntityBuilder
 *  jakarta.persistence.Column
 *  jakarta.persistence.Entity
 *  jakarta.persistence.GeneratedValue
 *  jakarta.persistence.GenerationType
 *  jakarta.persistence.Id
 *  jakarta.persistence.Table
 */
package com.empay.entities;

import com.empay.entities.McNetworkDataEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="MC_NETWORK_DATA")
public class McNetworkDataEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="MND_SER_NUMBER")
    private Integer serialNumber;
    @Column(name="MND_LAST_UPDATED")
    private LocalDateTime lastUpdated;
    @Column(name="MND_UPDATED_USER")
    private Integer user;
    @Column(name="MND_INS_CODE")
    private Integer insCode;
    @Column(name="MND_INT_CODE")
    private Integer intCode;
    @Column(name="MND_PRJ_SER_NUMBER")
    private Integer jobNumber;
    @Column(name="MND_BUSS_DATE")
    private LocalDate businessDate;
    @Column(name="MND_MERCHANT_ID")
    private String merchantId;
    @Column(name="MND_TERMINAL_ID")
    private String terminalId;
    @Column(name="MND_MERCHANT_NAME")
    private String merchantName;
    @Column(name="MND_COUNTRY_CODE")
    private String countryCode;
    @Column(name="MND_DISTRIBUTION_ICA")
    private String distributionICA;
    @Column(name="MND_MTI")
    private String mti;
    @Column(name="MND_CARD_NUMBER")
    private String cardNumber;
    @Column(name="MND_PROC_CODE")
    private String processingCode;
    @Column(name="MND_TXN_AMOUNT")
    private Double txnAmount;
    @Column(name="MND_RECON_AMOUNT")
    private Double reconAmount;
    @Column(name="MND_CURRENCY_CODE")
    private String currencyCode;
    @Column(name="MND_IRF_AMOUNT")
    private Double irfAmount;
    @Column(name="MND_RECON_FEE_CUR_CODE")
    private String reconFeeCurrencyCode;
    @Column(name="MND_IRF_AMOUNT_USD")
    private Double irfAmountUsd;
    @Column(name="MND_RECON_CONV_RATE")
    private Double reconConversionRate;
    @Column(name="MND_LOCAL_TXN_DATETIME")
    private LocalDateTime localTxnDateTime;
    @Column(name="MND_POS_DATA_CODE")
    private String posDataCode;
    @Column(name="MND_FUNCTION_CODE")
    private String functionCode;
    @Column(name="MND_MSG_REASON_CODE")
    private String msgReasonCode;
    @Column(name="MND_ACCEPTOR_BUSS_CODE")
    private String acceptorBussCode;
    @Column(name="MND_ORIGINAL_TXN_AMOUNT")
    private Double originalTxnAmount;
    @Column(name="MND_ACQ_REF_NUMBER")
    private String arn;
    @Column(name="MND_APPROVAL_CODE")
    private String approvalCode;
    @Column(name="MND_SERVICE_CODE")
    private String serviceCode;
    @Column(name="MND_GCMS_PRODUCT_ID")
    private String gcmsProductId;
    @Column(name="MND_PRODUCT_IDENTIFIER")
    private String productId;
    @Column(name="MND_TERMINAL_TYPE")
    private String terminalType;
    @Column(name="MND_REVERSAL_INDICATOR")
    private Character reversalIndicator;
    @Column(name="MND_IRD")
    private String ird;
    @Column(name="MND_FILE_ID")
    private String fileId;
    @Column(name="MND_SETTLEMENT_DATE")
    private LocalDate settlementDate;
    @Column(name="MND_TRACE_ID")
    private String traceId;
    @Column(name="MND_TXN_REF_NUMBER")
    private Integer txnRefNumber;
    @Column(name="MND_TXN_IRD")
    private String txnIrd;
    @Column(name="MND_IRF_MATCH")
    private Character matchStatus;
    @Column(name="MND_TXN_IRF_AMOUNT")
    private Double txnIrfAmount;
    @Column(name="MND_TXN_IRF_USD_AMOUNT")
    private Double txnIrfAmountUsd;
    @Column(name="MND_IRF_DIFF")
    private Double irfDifference;
    @Column(name="MND_IRF_DIFF_USD")
    private Double irfDifferenceUsd;
    @Column(name="MND_RET_REF_NUMBER")
    private String retRefNumber;
    @Column(name="MND_BUSINESS_CYCLE")
    private String businessCycle;

    public static McNetworkDataEntityBuilder builder() {
        return new McNetworkDataEntityBuilder();
    }

    public Integer getSerialNumber() {
        return this.serialNumber;
    }

    public LocalDateTime getLastUpdated() {
        return this.lastUpdated;
    }

    public Integer getUser() {
        return this.user;
    }

    public Integer getInsCode() {
        return this.insCode;
    }

    public Integer getIntCode() {
        return this.intCode;
    }

    public Integer getJobNumber() {
        return this.jobNumber;
    }

    public LocalDate getBusinessDate() {
        return this.businessDate;
    }

    public String getMerchantId() {
        return this.merchantId;
    }

    public String getTerminalId() {
        return this.terminalId;
    }

    public String getMerchantName() {
        return this.merchantName;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public String getDistributionICA() {
        return this.distributionICA;
    }

    public String getMti() {
        return this.mti;
    }

    public String getCardNumber() {
        return this.cardNumber;
    }

    public String getProcessingCode() {
        return this.processingCode;
    }

    public Double getTxnAmount() {
        return this.txnAmount;
    }

    public Double getReconAmount() {
        return this.reconAmount;
    }

    public String getCurrencyCode() {
        return this.currencyCode;
    }

    public Double getIrfAmount() {
        return this.irfAmount;
    }

    public String getReconFeeCurrencyCode() {
        return this.reconFeeCurrencyCode;
    }

    public Double getIrfAmountUsd() {
        return this.irfAmountUsd;
    }

    public Double getReconConversionRate() {
        return this.reconConversionRate;
    }

    public LocalDateTime getLocalTxnDateTime() {
        return this.localTxnDateTime;
    }

    public String getPosDataCode() {
        return this.posDataCode;
    }

    public String getFunctionCode() {
        return this.functionCode;
    }

    public String getMsgReasonCode() {
        return this.msgReasonCode;
    }

    public String getAcceptorBussCode() {
        return this.acceptorBussCode;
    }

    public Double getOriginalTxnAmount() {
        return this.originalTxnAmount;
    }

    public String getArn() {
        return this.arn;
    }

    public String getApprovalCode() {
        return this.approvalCode;
    }

    public String getServiceCode() {
        return this.serviceCode;
    }

    public String getGcmsProductId() {
        return this.gcmsProductId;
    }

    public String getProductId() {
        return this.productId;
    }

    public String getTerminalType() {
        return this.terminalType;
    }

    public Character getReversalIndicator() {
        return this.reversalIndicator;
    }

    public String getIrd() {
        return this.ird;
    }

    public String getFileId() {
        return this.fileId;
    }

    public LocalDate getSettlementDate() {
        return this.settlementDate;
    }

    public String getTraceId() {
        return this.traceId;
    }

    public Integer getTxnRefNumber() {
        return this.txnRefNumber;
    }

    public String getTxnIrd() {
        return this.txnIrd;
    }

    public Character getMatchStatus() {
        return this.matchStatus;
    }

    public Double getTxnIrfAmount() {
        return this.txnIrfAmount;
    }

    public Double getTxnIrfAmountUsd() {
        return this.txnIrfAmountUsd;
    }

    public Double getIrfDifference() {
        return this.irfDifference;
    }

    public Double getIrfDifferenceUsd() {
        return this.irfDifferenceUsd;
    }

    public String getRetRefNumber() {
        return this.retRefNumber;
    }

    public String getBusinessCycle() {
        return this.businessCycle;
    }

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public void setInsCode(Integer insCode) {
        this.insCode = insCode;
    }

    public void setIntCode(Integer intCode) {
        this.intCode = intCode;
    }

    public void setJobNumber(Integer jobNumber) {
        this.jobNumber = jobNumber;
    }

    public void setBusinessDate(LocalDate businessDate) {
        this.businessDate = businessDate;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public void setDistributionICA(String distributionICA) {
        this.distributionICA = distributionICA;
    }

    public void setMti(String mti) {
        this.mti = mti;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setProcessingCode(String processingCode) {
        this.processingCode = processingCode;
    }

    public void setTxnAmount(Double txnAmount) {
        this.txnAmount = txnAmount;
    }

    public void setReconAmount(Double reconAmount) {
        this.reconAmount = reconAmount;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public void setIrfAmount(Double irfAmount) {
        this.irfAmount = irfAmount;
    }

    public void setReconFeeCurrencyCode(String reconFeeCurrencyCode) {
        this.reconFeeCurrencyCode = reconFeeCurrencyCode;
    }

    public void setIrfAmountUsd(Double irfAmountUsd) {
        this.irfAmountUsd = irfAmountUsd;
    }

    public void setReconConversionRate(Double reconConversionRate) {
        this.reconConversionRate = reconConversionRate;
    }

    public void setLocalTxnDateTime(LocalDateTime localTxnDateTime) {
        this.localTxnDateTime = localTxnDateTime;
    }

    public void setPosDataCode(String posDataCode) {
        this.posDataCode = posDataCode;
    }

    public void setFunctionCode(String functionCode) {
        this.functionCode = functionCode;
    }

    public void setMsgReasonCode(String msgReasonCode) {
        this.msgReasonCode = msgReasonCode;
    }

    public void setAcceptorBussCode(String acceptorBussCode) {
        this.acceptorBussCode = acceptorBussCode;
    }

    public void setOriginalTxnAmount(Double originalTxnAmount) {
        this.originalTxnAmount = originalTxnAmount;
    }

    public void setArn(String arn) {
        this.arn = arn;
    }

    public void setApprovalCode(String approvalCode) {
        this.approvalCode = approvalCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public void setGcmsProductId(String gcmsProductId) {
        this.gcmsProductId = gcmsProductId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setTerminalType(String terminalType) {
        this.terminalType = terminalType;
    }

    public void setReversalIndicator(Character reversalIndicator) {
        this.reversalIndicator = reversalIndicator;
    }

    public void setIrd(String ird) {
        this.ird = ird;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public void setSettlementDate(LocalDate settlementDate) {
        this.settlementDate = settlementDate;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public void setTxnRefNumber(Integer txnRefNumber) {
        this.txnRefNumber = txnRefNumber;
    }

    public void setTxnIrd(String txnIrd) {
        this.txnIrd = txnIrd;
    }

    public void setMatchStatus(Character matchStatus) {
        this.matchStatus = matchStatus;
    }

    public void setTxnIrfAmount(Double txnIrfAmount) {
        this.txnIrfAmount = txnIrfAmount;
    }

    public void setTxnIrfAmountUsd(Double txnIrfAmountUsd) {
        this.txnIrfAmountUsd = txnIrfAmountUsd;
    }

    public void setIrfDifference(Double irfDifference) {
        this.irfDifference = irfDifference;
    }

    public void setIrfDifferenceUsd(Double irfDifferenceUsd) {
        this.irfDifferenceUsd = irfDifferenceUsd;
    }

    public void setRetRefNumber(String retRefNumber) {
        this.retRefNumber = retRefNumber;
    }

    public void setBusinessCycle(String businessCycle) {
        this.businessCycle = businessCycle;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof McNetworkDataEntity)) {
            return false;
        }
        McNetworkDataEntity other = (McNetworkDataEntity)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        Integer this$serialNumber = this.getSerialNumber();
        Integer other$serialNumber = other.getSerialNumber();
        if (this$serialNumber == null ? other$serialNumber != null : !((Object)this$serialNumber).equals(other$serialNumber)) {
            return false;
        }
        Integer this$user = this.getUser();
        Integer other$user = other.getUser();
        if (this$user == null ? other$user != null : !((Object)this$user).equals(other$user)) {
            return false;
        }
        Integer this$insCode = this.getInsCode();
        Integer other$insCode = other.getInsCode();
        if (this$insCode == null ? other$insCode != null : !((Object)this$insCode).equals(other$insCode)) {
            return false;
        }
        Integer this$intCode = this.getIntCode();
        Integer other$intCode = other.getIntCode();
        if (this$intCode == null ? other$intCode != null : !((Object)this$intCode).equals(other$intCode)) {
            return false;
        }
        Integer this$jobNumber = this.getJobNumber();
        Integer other$jobNumber = other.getJobNumber();
        if (this$jobNumber == null ? other$jobNumber != null : !((Object)this$jobNumber).equals(other$jobNumber)) {
            return false;
        }
        Double this$txnAmount = this.getTxnAmount();
        Double other$txnAmount = other.getTxnAmount();
        if (this$txnAmount == null ? other$txnAmount != null : !((Object)this$txnAmount).equals(other$txnAmount)) {
            return false;
        }
        Double this$reconAmount = this.getReconAmount();
        Double other$reconAmount = other.getReconAmount();
        if (this$reconAmount == null ? other$reconAmount != null : !((Object)this$reconAmount).equals(other$reconAmount)) {
            return false;
        }
        Double this$irfAmount = this.getIrfAmount();
        Double other$irfAmount = other.getIrfAmount();
        if (this$irfAmount == null ? other$irfAmount != null : !((Object)this$irfAmount).equals(other$irfAmount)) {
            return false;
        }
        Double this$irfAmountUsd = this.getIrfAmountUsd();
        Double other$irfAmountUsd = other.getIrfAmountUsd();
        if (this$irfAmountUsd == null ? other$irfAmountUsd != null : !((Object)this$irfAmountUsd).equals(other$irfAmountUsd)) {
            return false;
        }
        Double this$reconConversionRate = this.getReconConversionRate();
        Double other$reconConversionRate = other.getReconConversionRate();
        if (this$reconConversionRate == null ? other$reconConversionRate != null : !((Object)this$reconConversionRate).equals(other$reconConversionRate)) {
            return false;
        }
        Double this$originalTxnAmount = this.getOriginalTxnAmount();
        Double other$originalTxnAmount = other.getOriginalTxnAmount();
        if (this$originalTxnAmount == null ? other$originalTxnAmount != null : !((Object)this$originalTxnAmount).equals(other$originalTxnAmount)) {
            return false;
        }
        Character this$reversalIndicator = this.getReversalIndicator();
        Character other$reversalIndicator = other.getReversalIndicator();
        if (this$reversalIndicator == null ? other$reversalIndicator != null : !((Object)this$reversalIndicator).equals(other$reversalIndicator)) {
            return false;
        }
        Integer this$txnRefNumber = this.getTxnRefNumber();
        Integer other$txnRefNumber = other.getTxnRefNumber();
        if (this$txnRefNumber == null ? other$txnRefNumber != null : !((Object)this$txnRefNumber).equals(other$txnRefNumber)) {
            return false;
        }
        Character this$matchStatus = this.getMatchStatus();
        Character other$matchStatus = other.getMatchStatus();
        if (this$matchStatus == null ? other$matchStatus != null : !((Object)this$matchStatus).equals(other$matchStatus)) {
            return false;
        }
        Double this$txnIrfAmount = this.getTxnIrfAmount();
        Double other$txnIrfAmount = other.getTxnIrfAmount();
        if (this$txnIrfAmount == null ? other$txnIrfAmount != null : !((Object)this$txnIrfAmount).equals(other$txnIrfAmount)) {
            return false;
        }
        Double this$txnIrfAmountUsd = this.getTxnIrfAmountUsd();
        Double other$txnIrfAmountUsd = other.getTxnIrfAmountUsd();
        if (this$txnIrfAmountUsd == null ? other$txnIrfAmountUsd != null : !((Object)this$txnIrfAmountUsd).equals(other$txnIrfAmountUsd)) {
            return false;
        }
        Double this$irfDifference = this.getIrfDifference();
        Double other$irfDifference = other.getIrfDifference();
        if (this$irfDifference == null ? other$irfDifference != null : !((Object)this$irfDifference).equals(other$irfDifference)) {
            return false;
        }
        Double this$irfDifferenceUsd = this.getIrfDifferenceUsd();
        Double other$irfDifferenceUsd = other.getIrfDifferenceUsd();
        if (this$irfDifferenceUsd == null ? other$irfDifferenceUsd != null : !((Object)this$irfDifferenceUsd).equals(other$irfDifferenceUsd)) {
            return false;
        }
        LocalDateTime this$lastUpdated = this.getLastUpdated();
        LocalDateTime other$lastUpdated = other.getLastUpdated();
        if (this$lastUpdated == null ? other$lastUpdated != null : !((Object)this$lastUpdated).equals(other$lastUpdated)) {
            return false;
        }
        LocalDate this$businessDate = this.getBusinessDate();
        LocalDate other$businessDate = other.getBusinessDate();
        if (this$businessDate == null ? other$businessDate != null : !((Object)this$businessDate).equals(other$businessDate)) {
            return false;
        }
        String this$merchantId = this.getMerchantId();
        String other$merchantId = other.getMerchantId();
        if (this$merchantId == null ? other$merchantId != null : !this$merchantId.equals(other$merchantId)) {
            return false;
        }
        String this$terminalId = this.getTerminalId();
        String other$terminalId = other.getTerminalId();
        if (this$terminalId == null ? other$terminalId != null : !this$terminalId.equals(other$terminalId)) {
            return false;
        }
        String this$merchantName = this.getMerchantName();
        String other$merchantName = other.getMerchantName();
        if (this$merchantName == null ? other$merchantName != null : !this$merchantName.equals(other$merchantName)) {
            return false;
        }
        String this$countryCode = this.getCountryCode();
        String other$countryCode = other.getCountryCode();
        if (this$countryCode == null ? other$countryCode != null : !this$countryCode.equals(other$countryCode)) {
            return false;
        }
        String this$distributionICA = this.getDistributionICA();
        String other$distributionICA = other.getDistributionICA();
        if (this$distributionICA == null ? other$distributionICA != null : !this$distributionICA.equals(other$distributionICA)) {
            return false;
        }
        String this$mti = this.getMti();
        String other$mti = other.getMti();
        if (this$mti == null ? other$mti != null : !this$mti.equals(other$mti)) {
            return false;
        }
        String this$cardNumber = this.getCardNumber();
        String other$cardNumber = other.getCardNumber();
        if (this$cardNumber == null ? other$cardNumber != null : !this$cardNumber.equals(other$cardNumber)) {
            return false;
        }
        String this$processingCode = this.getProcessingCode();
        String other$processingCode = other.getProcessingCode();
        if (this$processingCode == null ? other$processingCode != null : !this$processingCode.equals(other$processingCode)) {
            return false;
        }
        String this$currencyCode = this.getCurrencyCode();
        String other$currencyCode = other.getCurrencyCode();
        if (this$currencyCode == null ? other$currencyCode != null : !this$currencyCode.equals(other$currencyCode)) {
            return false;
        }
        String this$reconFeeCurrencyCode = this.getReconFeeCurrencyCode();
        String other$reconFeeCurrencyCode = other.getReconFeeCurrencyCode();
        if (this$reconFeeCurrencyCode == null ? other$reconFeeCurrencyCode != null : !this$reconFeeCurrencyCode.equals(other$reconFeeCurrencyCode)) {
            return false;
        }
        LocalDateTime this$localTxnDateTime = this.getLocalTxnDateTime();
        LocalDateTime other$localTxnDateTime = other.getLocalTxnDateTime();
        if (this$localTxnDateTime == null ? other$localTxnDateTime != null : !((Object)this$localTxnDateTime).equals(other$localTxnDateTime)) {
            return false;
        }
        String this$posDataCode = this.getPosDataCode();
        String other$posDataCode = other.getPosDataCode();
        if (this$posDataCode == null ? other$posDataCode != null : !this$posDataCode.equals(other$posDataCode)) {
            return false;
        }
        String this$functionCode = this.getFunctionCode();
        String other$functionCode = other.getFunctionCode();
        if (this$functionCode == null ? other$functionCode != null : !this$functionCode.equals(other$functionCode)) {
            return false;
        }
        String this$msgReasonCode = this.getMsgReasonCode();
        String other$msgReasonCode = other.getMsgReasonCode();
        if (this$msgReasonCode == null ? other$msgReasonCode != null : !this$msgReasonCode.equals(other$msgReasonCode)) {
            return false;
        }
        String this$acceptorBussCode = this.getAcceptorBussCode();
        String other$acceptorBussCode = other.getAcceptorBussCode();
        if (this$acceptorBussCode == null ? other$acceptorBussCode != null : !this$acceptorBussCode.equals(other$acceptorBussCode)) {
            return false;
        }
        String this$arn = this.getArn();
        String other$arn = other.getArn();
        if (this$arn == null ? other$arn != null : !this$arn.equals(other$arn)) {
            return false;
        }
        String this$approvalCode = this.getApprovalCode();
        String other$approvalCode = other.getApprovalCode();
        if (this$approvalCode == null ? other$approvalCode != null : !this$approvalCode.equals(other$approvalCode)) {
            return false;
        }
        String this$serviceCode = this.getServiceCode();
        String other$serviceCode = other.getServiceCode();
        if (this$serviceCode == null ? other$serviceCode != null : !this$serviceCode.equals(other$serviceCode)) {
            return false;
        }
        String this$gcmsProductId = this.getGcmsProductId();
        String other$gcmsProductId = other.getGcmsProductId();
        if (this$gcmsProductId == null ? other$gcmsProductId != null : !this$gcmsProductId.equals(other$gcmsProductId)) {
            return false;
        }
        String this$productId = this.getProductId();
        String other$productId = other.getProductId();
        if (this$productId == null ? other$productId != null : !this$productId.equals(other$productId)) {
            return false;
        }
        String this$terminalType = this.getTerminalType();
        String other$terminalType = other.getTerminalType();
        if (this$terminalType == null ? other$terminalType != null : !this$terminalType.equals(other$terminalType)) {
            return false;
        }
        String this$ird = this.getIrd();
        String other$ird = other.getIrd();
        if (this$ird == null ? other$ird != null : !this$ird.equals(other$ird)) {
            return false;
        }
        String this$fileId = this.getFileId();
        String other$fileId = other.getFileId();
        if (this$fileId == null ? other$fileId != null : !this$fileId.equals(other$fileId)) {
            return false;
        }
        LocalDate this$settlementDate = this.getSettlementDate();
        LocalDate other$settlementDate = other.getSettlementDate();
        if (this$settlementDate == null ? other$settlementDate != null : !((Object)this$settlementDate).equals(other$settlementDate)) {
            return false;
        }
        String this$traceId = this.getTraceId();
        String other$traceId = other.getTraceId();
        if (this$traceId == null ? other$traceId != null : !this$traceId.equals(other$traceId)) {
            return false;
        }
        String this$txnIrd = this.getTxnIrd();
        String other$txnIrd = other.getTxnIrd();
        if (this$txnIrd == null ? other$txnIrd != null : !this$txnIrd.equals(other$txnIrd)) {
            return false;
        }
        String this$retRefNumber = this.getRetRefNumber();
        String other$retRefNumber = other.getRetRefNumber();
        if (this$retRefNumber == null ? other$retRefNumber != null : !this$retRefNumber.equals(other$retRefNumber)) {
            return false;
        }
        String this$businessCycle = this.getBusinessCycle();
        String other$businessCycle = other.getBusinessCycle();
        return !(this$businessCycle == null ? other$businessCycle != null : !this$businessCycle.equals(other$businessCycle));
    }

    protected boolean canEqual(Object other) {
        return other instanceof McNetworkDataEntity;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Integer $serialNumber = this.getSerialNumber();
        result = result * 59 + ($serialNumber == null ? 43 : ((Object)$serialNumber).hashCode());
        Integer $user = this.getUser();
        result = result * 59 + ($user == null ? 43 : ((Object)$user).hashCode());
        Integer $insCode = this.getInsCode();
        result = result * 59 + ($insCode == null ? 43 : ((Object)$insCode).hashCode());
        Integer $intCode = this.getIntCode();
        result = result * 59 + ($intCode == null ? 43 : ((Object)$intCode).hashCode());
        Integer $jobNumber = this.getJobNumber();
        result = result * 59 + ($jobNumber == null ? 43 : ((Object)$jobNumber).hashCode());
        Double $txnAmount = this.getTxnAmount();
        result = result * 59 + ($txnAmount == null ? 43 : ((Object)$txnAmount).hashCode());
        Double $reconAmount = this.getReconAmount();
        result = result * 59 + ($reconAmount == null ? 43 : ((Object)$reconAmount).hashCode());
        Double $irfAmount = this.getIrfAmount();
        result = result * 59 + ($irfAmount == null ? 43 : ((Object)$irfAmount).hashCode());
        Double $irfAmountUsd = this.getIrfAmountUsd();
        result = result * 59 + ($irfAmountUsd == null ? 43 : ((Object)$irfAmountUsd).hashCode());
        Double $reconConversionRate = this.getReconConversionRate();
        result = result * 59 + ($reconConversionRate == null ? 43 : ((Object)$reconConversionRate).hashCode());
        Double $originalTxnAmount = this.getOriginalTxnAmount();
        result = result * 59 + ($originalTxnAmount == null ? 43 : ((Object)$originalTxnAmount).hashCode());
        Character $reversalIndicator = this.getReversalIndicator();
        result = result * 59 + ($reversalIndicator == null ? 43 : ((Object)$reversalIndicator).hashCode());
        Integer $txnRefNumber = this.getTxnRefNumber();
        result = result * 59 + ($txnRefNumber == null ? 43 : ((Object)$txnRefNumber).hashCode());
        Character $matchStatus = this.getMatchStatus();
        result = result * 59 + ($matchStatus == null ? 43 : ((Object)$matchStatus).hashCode());
        Double $txnIrfAmount = this.getTxnIrfAmount();
        result = result * 59 + ($txnIrfAmount == null ? 43 : ((Object)$txnIrfAmount).hashCode());
        Double $txnIrfAmountUsd = this.getTxnIrfAmountUsd();
        result = result * 59 + ($txnIrfAmountUsd == null ? 43 : ((Object)$txnIrfAmountUsd).hashCode());
        Double $irfDifference = this.getIrfDifference();
        result = result * 59 + ($irfDifference == null ? 43 : ((Object)$irfDifference).hashCode());
        Double $irfDifferenceUsd = this.getIrfDifferenceUsd();
        result = result * 59 + ($irfDifferenceUsd == null ? 43 : ((Object)$irfDifferenceUsd).hashCode());
        LocalDateTime $lastUpdated = this.getLastUpdated();
        result = result * 59 + ($lastUpdated == null ? 43 : ((Object)$lastUpdated).hashCode());
        LocalDate $businessDate = this.getBusinessDate();
        result = result * 59 + ($businessDate == null ? 43 : ((Object)$businessDate).hashCode());
        String $merchantId = this.getMerchantId();
        result = result * 59 + ($merchantId == null ? 43 : $merchantId.hashCode());
        String $terminalId = this.getTerminalId();
        result = result * 59 + ($terminalId == null ? 43 : $terminalId.hashCode());
        String $merchantName = this.getMerchantName();
        result = result * 59 + ($merchantName == null ? 43 : $merchantName.hashCode());
        String $countryCode = this.getCountryCode();
        result = result * 59 + ($countryCode == null ? 43 : $countryCode.hashCode());
        String $distributionICA = this.getDistributionICA();
        result = result * 59 + ($distributionICA == null ? 43 : $distributionICA.hashCode());
        String $mti = this.getMti();
        result = result * 59 + ($mti == null ? 43 : $mti.hashCode());
        String $cardNumber = this.getCardNumber();
        result = result * 59 + ($cardNumber == null ? 43 : $cardNumber.hashCode());
        String $processingCode = this.getProcessingCode();
        result = result * 59 + ($processingCode == null ? 43 : $processingCode.hashCode());
        String $currencyCode = this.getCurrencyCode();
        result = result * 59 + ($currencyCode == null ? 43 : $currencyCode.hashCode());
        String $reconFeeCurrencyCode = this.getReconFeeCurrencyCode();
        result = result * 59 + ($reconFeeCurrencyCode == null ? 43 : $reconFeeCurrencyCode.hashCode());
        LocalDateTime $localTxnDateTime = this.getLocalTxnDateTime();
        result = result * 59 + ($localTxnDateTime == null ? 43 : ((Object)$localTxnDateTime).hashCode());
        String $posDataCode = this.getPosDataCode();
        result = result * 59 + ($posDataCode == null ? 43 : $posDataCode.hashCode());
        String $functionCode = this.getFunctionCode();
        result = result * 59 + ($functionCode == null ? 43 : $functionCode.hashCode());
        String $msgReasonCode = this.getMsgReasonCode();
        result = result * 59 + ($msgReasonCode == null ? 43 : $msgReasonCode.hashCode());
        String $acceptorBussCode = this.getAcceptorBussCode();
        result = result * 59 + ($acceptorBussCode == null ? 43 : $acceptorBussCode.hashCode());
        String $arn = this.getArn();
        result = result * 59 + ($arn == null ? 43 : $arn.hashCode());
        String $approvalCode = this.getApprovalCode();
        result = result * 59 + ($approvalCode == null ? 43 : $approvalCode.hashCode());
        String $serviceCode = this.getServiceCode();
        result = result * 59 + ($serviceCode == null ? 43 : $serviceCode.hashCode());
        String $gcmsProductId = this.getGcmsProductId();
        result = result * 59 + ($gcmsProductId == null ? 43 : $gcmsProductId.hashCode());
        String $productId = this.getProductId();
        result = result * 59 + ($productId == null ? 43 : $productId.hashCode());
        String $terminalType = this.getTerminalType();
        result = result * 59 + ($terminalType == null ? 43 : $terminalType.hashCode());
        String $ird = this.getIrd();
        result = result * 59 + ($ird == null ? 43 : $ird.hashCode());
        String $fileId = this.getFileId();
        result = result * 59 + ($fileId == null ? 43 : $fileId.hashCode());
        LocalDate $settlementDate = this.getSettlementDate();
        result = result * 59 + ($settlementDate == null ? 43 : ((Object)$settlementDate).hashCode());
        String $traceId = this.getTraceId();
        result = result * 59 + ($traceId == null ? 43 : $traceId.hashCode());
        String $txnIrd = this.getTxnIrd();
        result = result * 59 + ($txnIrd == null ? 43 : $txnIrd.hashCode());
        String $retRefNumber = this.getRetRefNumber();
        result = result * 59 + ($retRefNumber == null ? 43 : $retRefNumber.hashCode());
        String $businessCycle = this.getBusinessCycle();
        result = result * 59 + ($businessCycle == null ? 43 : $businessCycle.hashCode());
        return result;
    }

    public String toString() {
        return "McNetworkDataEntity(serialNumber=" + this.getSerialNumber() + ", lastUpdated=" + String.valueOf(this.getLastUpdated()) + ", user=" + this.getUser() + ", insCode=" + this.getInsCode() + ", intCode=" + this.getIntCode() + ", jobNumber=" + this.getJobNumber() + ", businessDate=" + String.valueOf(this.getBusinessDate()) + ", merchantId=" + this.getMerchantId() + ", terminalId=" + this.getTerminalId() + ", merchantName=" + this.getMerchantName() + ", countryCode=" + this.getCountryCode() + ", distributionICA=" + this.getDistributionICA() + ", mti=" + this.getMti() + ", cardNumber=" + this.getCardNumber() + ", processingCode=" + this.getProcessingCode() + ", txnAmount=" + this.getTxnAmount() + ", reconAmount=" + this.getReconAmount() + ", currencyCode=" + this.getCurrencyCode() + ", irfAmount=" + this.getIrfAmount() + ", reconFeeCurrencyCode=" + this.getReconFeeCurrencyCode() + ", irfAmountUsd=" + this.getIrfAmountUsd() + ", reconConversionRate=" + this.getReconConversionRate() + ", localTxnDateTime=" + String.valueOf(this.getLocalTxnDateTime()) + ", posDataCode=" + this.getPosDataCode() + ", functionCode=" + this.getFunctionCode() + ", msgReasonCode=" + this.getMsgReasonCode() + ", acceptorBussCode=" + this.getAcceptorBussCode() + ", originalTxnAmount=" + this.getOriginalTxnAmount() + ", arn=" + this.getArn() + ", approvalCode=" + this.getApprovalCode() + ", serviceCode=" + this.getServiceCode() + ", gcmsProductId=" + this.getGcmsProductId() + ", productId=" + this.getProductId() + ", terminalType=" + this.getTerminalType() + ", reversalIndicator=" + this.getReversalIndicator() + ", ird=" + this.getIrd() + ", fileId=" + this.getFileId() + ", settlementDate=" + String.valueOf(this.getSettlementDate()) + ", traceId=" + this.getTraceId() + ", txnRefNumber=" + this.getTxnRefNumber() + ", txnIrd=" + this.getTxnIrd() + ", matchStatus=" + this.getMatchStatus() + ", txnIrfAmount=" + this.getTxnIrfAmount() + ", txnIrfAmountUsd=" + this.getTxnIrfAmountUsd() + ", irfDifference=" + this.getIrfDifference() + ", irfDifferenceUsd=" + this.getIrfDifferenceUsd() + ", retRefNumber=" + this.getRetRefNumber() + ", businessCycle=" + this.getBusinessCycle() + ")";
    }

    public McNetworkDataEntity(Integer serialNumber, LocalDateTime lastUpdated, Integer user, Integer insCode, Integer intCode, Integer jobNumber, LocalDate businessDate, String merchantId, String terminalId, String merchantName, String countryCode, String distributionICA, String mti, String cardNumber, String processingCode, Double txnAmount, Double reconAmount, String currencyCode, Double irfAmount, String reconFeeCurrencyCode, Double irfAmountUsd, Double reconConversionRate, LocalDateTime localTxnDateTime, String posDataCode, String functionCode, String msgReasonCode, String acceptorBussCode, Double originalTxnAmount, String arn, String approvalCode, String serviceCode, String gcmsProductId, String productId, String terminalType, Character reversalIndicator, String ird, String fileId, LocalDate settlementDate, String traceId, Integer txnRefNumber, String txnIrd, Character matchStatus, Double txnIrfAmount, Double txnIrfAmountUsd, Double irfDifference, Double irfDifferenceUsd, String retRefNumber, String businessCycle) {
        this.serialNumber = serialNumber;
        this.lastUpdated = lastUpdated;
        this.user = user;
        this.insCode = insCode;
        this.intCode = intCode;
        this.jobNumber = jobNumber;
        this.businessDate = businessDate;
        this.merchantId = merchantId;
        this.terminalId = terminalId;
        this.merchantName = merchantName;
        this.countryCode = countryCode;
        this.distributionICA = distributionICA;
        this.mti = mti;
        this.cardNumber = cardNumber;
        this.processingCode = processingCode;
        this.txnAmount = txnAmount;
        this.reconAmount = reconAmount;
        this.currencyCode = currencyCode;
        this.irfAmount = irfAmount;
        this.reconFeeCurrencyCode = reconFeeCurrencyCode;
        this.irfAmountUsd = irfAmountUsd;
        this.reconConversionRate = reconConversionRate;
        this.localTxnDateTime = localTxnDateTime;
        this.posDataCode = posDataCode;
        this.functionCode = functionCode;
        this.msgReasonCode = msgReasonCode;
        this.acceptorBussCode = acceptorBussCode;
        this.originalTxnAmount = originalTxnAmount;
        this.arn = arn;
        this.approvalCode = approvalCode;
        this.serviceCode = serviceCode;
        this.gcmsProductId = gcmsProductId;
        this.productId = productId;
        this.terminalType = terminalType;
        this.reversalIndicator = reversalIndicator;
        this.ird = ird;
        this.fileId = fileId;
        this.settlementDate = settlementDate;
        this.traceId = traceId;
        this.txnRefNumber = txnRefNumber;
        this.txnIrd = txnIrd;
        this.matchStatus = matchStatus;
        this.txnIrfAmount = txnIrfAmount;
        this.txnIrfAmountUsd = txnIrfAmountUsd;
        this.irfDifference = irfDifference;
        this.irfDifferenceUsd = irfDifferenceUsd;
        this.retRefNumber = retRefNumber;
        this.businessCycle = businessCycle;
    }

    public McNetworkDataEntity() {
    }
}

