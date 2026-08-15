/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.entities.ViewTxnInquiryDetails
 *  jakarta.persistence.Column
 *  jakarta.persistence.Entity
 *  jakarta.persistence.Id
 *  jakarta.persistence.Table
 */
package com.empay.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name="VW_TXN_INQUIRY_DETAILS")
public class ViewTxnInquiryDetails {
    @Id
    @Column(name="PTR_SER_NUMBER")
    private Integer serialNumber;
    @Column(name="PTR_TXN_ID")
    private String txnId;
    @Column(name="PTR_MERCHANT_ID")
    private String merchantId;
    @Column(name="PTR_TERMINAL_ID")
    private String terminalId;
    @Column(name="PTR_RET_REF_NUMBER")
    private String retrievalRefNumber;
    @Column(name="PTR_IRD")
    private String ird;
    @Column(name="PTR_IRF_FIXED")
    private Double irfFixed;
    @Column(name="PTR_IRF_PERCENT")
    private Double irfPercent;
    @Column(name="PTR_IRF_AMOUNT")
    private Double irfAmount;
    @Column(name="PTR_IRF_MIN_AMOUNT")
    private Double irfMinAmount;
    @Column(name="PTR_IRF_MAX_AMOUNT")
    private Double irfMaxAmount;
    @Column(name="PTR_TXN_AMOUNT")
    private Double txnAmount;
    @Column(name="PTR_CARD_DOM_INTL_FLAG")
    private String cardDomIntlFlag;
    @Column(name="PTR_CARD_TYPE")
    private String cardType;
    @Column(name="PTR_CARD_NUMBER")
    private String cardNumber;
    @Column(name="PTR_TXN_TYPE")
    private String txnType;
    @Column(name="PTR_TXN_CODE")
    private String txnCode;
    @Column(name="PTR_REMARKS")
    private String remarks;
    @Column(name="PTR_NETWORK")
    private String network;
    @Column(name="PTR_TXN_DATE_TIME")
    private LocalDateTime txnDateTime;
    @Column(name="PTR_RESP_CODE")
    private String respCode;
    @Column(name="PTR_OUT_STATUS")
    private String outStatus;
    @Column(name="PTR_INC_STATUS")
    private String incStatus;
    @Column(name="PTR_MSG_TYPE_ID")
    private String msgTypeId;
    @Column(name="PTR_BANK_CODE")
    private String bankCode;
    @Column(name="PTR_REV_INDICATOR")
    private Character revIndicator;
    @Column(name="PTR_IRF_AMOUNT_USD")
    private Double irfAmountUsd;
    @Column(name="PTR_ORG_RRN")
    private String originalRrn;
    @Column(name="PTR_LOCAL_DATE_TIME")
    private LocalDateTime localDateTime;
    @Column(name="PTR_NETWORK_DATA")
    private String networkData;
    @Column(name="PTR_PROC_CODE")
    private String procCode;
    @Column(name="VND_INCHG_FEE_AMOUNT")
    private Double visaInchgFeeAmount;
    @Column(name="VND_INCHG_FEE_SIGN")
    private String visaInchgFeeSign;
    @Column(name="VND_FEE_DESC")
    private String visaFeeDesc;
    @Column(name="VND_IRF_MATCH")
    private String visaIrfMatch;
    @Column(name="VND_IRF_DIFF")
    private Double visaIrfDiff;
    @Column(name="VND_TXN_AMOUNT")
    private Double visaTxnAmount;
    @Column(name="MND_TXN_AMOUNT")
    private Double mciTxnAmount;
    @Column(name="MND_RECON_AMOUNT")
    private Double mciReconAmount;
    @Column(name="MND_TXN_IRF_AMOUNT")
    private Double mciTxnIrfAmount;
    @Column(name="MND_TXN_IRF_USD_AMOUNT")
    private Double mciTxnIrfUsdAmount;
    @Column(name="MND_IRD")
    private String mciIrd;
    @Column(name="MND_IRF_MATCH")
    private String mciIrfMatch;
    @Column(name="MND_IRF_DIFF")
    private Double mciIrfDiff;
    @Column(name="MND_BUSINESS_CYCLE")
    private String businessCycle;
    @Column(name="MND_FILE_ID")
    private String fileId;
    @Column(name="MND_APPROVAL_CODE")
    private String approvalCode;
    @Column(name="USN_IRF")
    private Double uaeSwitchIrf;
    @Column(name="USN_PF1")
    private Double uaeSwitchPF1;
    @Column(name="USN_PF2")
    private Double uaeSwitchPF2;
    @Column(name="NWD_TXN_ID")
    private String visaSmsTxnId;
    @Column(name="NWD_INCHG_FEE")
    private Double visaSmsIrfAmount;
    @Column(name="NWD_INCHG_FEE_SIGN")
    private String visaSmsInchgFeeSign;
    @Column(name="NWD_IRF_MATCH")
    private String visaSmsIrfMatch;
    @Column(name="NWD_IRF_DIFF")
    private Double visaSmsIrfDiff;
    @Column(name="PTR_SCHEME")
    private String schema;
    @Column(name="PTR_DCC_AMOUNT")
    private Double dccAmount;
    @Column(name="PTR_DCC_CURRENCY")
    private String dccCurrency;
    @Column(name="PTR_DCC_INDICATOR")
    private Character dccIndicator;
    @Column(name="PTR_DCC_EXCHANGE_RATE")
    private Double dccExchangeRate;

    public Integer getSerialNumber() {
        return this.serialNumber;
    }

    public String getTxnId() {
        return this.txnId;
    }

    public String getMerchantId() {
        return this.merchantId;
    }

    public String getTerminalId() {
        return this.terminalId;
    }

    public String getRetrievalRefNumber() {
        return this.retrievalRefNumber;
    }

    public String getIrd() {
        return this.ird;
    }

    public Double getIrfFixed() {
        return this.irfFixed;
    }

    public Double getIrfPercent() {
        return this.irfPercent;
    }

    public Double getIrfAmount() {
        return this.irfAmount;
    }

    public Double getIrfMinAmount() {
        return this.irfMinAmount;
    }

    public Double getIrfMaxAmount() {
        return this.irfMaxAmount;
    }

    public Double getTxnAmount() {
        return this.txnAmount;
    }

    public String getCardDomIntlFlag() {
        return this.cardDomIntlFlag;
    }

    public String getCardType() {
        return this.cardType;
    }

    public String getCardNumber() {
        return this.cardNumber;
    }

    public String getTxnType() {
        return this.txnType;
    }

    public String getTxnCode() {
        return this.txnCode;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public String getNetwork() {
        return this.network;
    }

    public LocalDateTime getTxnDateTime() {
        return this.txnDateTime;
    }

    public String getRespCode() {
        return this.respCode;
    }

    public String getOutStatus() {
        return this.outStatus;
    }

    public String getIncStatus() {
        return this.incStatus;
    }

    public String getMsgTypeId() {
        return this.msgTypeId;
    }

    public String getBankCode() {
        return this.bankCode;
    }

    public Character getRevIndicator() {
        return this.revIndicator;
    }

    public Double getIrfAmountUsd() {
        return this.irfAmountUsd;
    }

    public String getOriginalRrn() {
        return this.originalRrn;
    }

    public LocalDateTime getLocalDateTime() {
        return this.localDateTime;
    }

    public String getNetworkData() {
        return this.networkData;
    }

    public String getProcCode() {
        return this.procCode;
    }

    public Double getVisaInchgFeeAmount() {
        return this.visaInchgFeeAmount;
    }

    public String getVisaInchgFeeSign() {
        return this.visaInchgFeeSign;
    }

    public String getVisaFeeDesc() {
        return this.visaFeeDesc;
    }

    public String getVisaIrfMatch() {
        return this.visaIrfMatch;
    }

    public Double getVisaIrfDiff() {
        return this.visaIrfDiff;
    }

    public Double getVisaTxnAmount() {
        return this.visaTxnAmount;
    }

    public Double getMciTxnAmount() {
        return this.mciTxnAmount;
    }

    public Double getMciReconAmount() {
        return this.mciReconAmount;
    }

    public Double getMciTxnIrfAmount() {
        return this.mciTxnIrfAmount;
    }

    public Double getMciTxnIrfUsdAmount() {
        return this.mciTxnIrfUsdAmount;
    }

    public String getMciIrd() {
        return this.mciIrd;
    }

    public String getMciIrfMatch() {
        return this.mciIrfMatch;
    }

    public Double getMciIrfDiff() {
        return this.mciIrfDiff;
    }

    public String getBusinessCycle() {
        return this.businessCycle;
    }

    public String getFileId() {
        return this.fileId;
    }

    public String getApprovalCode() {
        return this.approvalCode;
    }

    public Double getUaeSwitchIrf() {
        return this.uaeSwitchIrf;
    }

    public Double getUaeSwitchPF1() {
        return this.uaeSwitchPF1;
    }

    public Double getUaeSwitchPF2() {
        return this.uaeSwitchPF2;
    }

    public String getVisaSmsTxnId() {
        return this.visaSmsTxnId;
    }

    public Double getVisaSmsIrfAmount() {
        return this.visaSmsIrfAmount;
    }

    public String getVisaSmsInchgFeeSign() {
        return this.visaSmsInchgFeeSign;
    }

    public String getVisaSmsIrfMatch() {
        return this.visaSmsIrfMatch;
    }

    public Double getVisaSmsIrfDiff() {
        return this.visaSmsIrfDiff;
    }

    public String getSchema() {
        return this.schema;
    }

    public Double getDccAmount() {
        return this.dccAmount;
    }

    public String getDccCurrency() {
        return this.dccCurrency;
    }

    public Character getDccIndicator() {
        return this.dccIndicator;
    }

    public Double getDccExchangeRate() {
        return this.dccExchangeRate;
    }

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public void setRetrievalRefNumber(String retrievalRefNumber) {
        this.retrievalRefNumber = retrievalRefNumber;
    }

    public void setIrd(String ird) {
        this.ird = ird;
    }

    public void setIrfFixed(Double irfFixed) {
        this.irfFixed = irfFixed;
    }

    public void setIrfPercent(Double irfPercent) {
        this.irfPercent = irfPercent;
    }

    public void setIrfAmount(Double irfAmount) {
        this.irfAmount = irfAmount;
    }

    public void setIrfMinAmount(Double irfMinAmount) {
        this.irfMinAmount = irfMinAmount;
    }

    public void setIrfMaxAmount(Double irfMaxAmount) {
        this.irfMaxAmount = irfMaxAmount;
    }

    public void setTxnAmount(Double txnAmount) {
        this.txnAmount = txnAmount;
    }

    public void setCardDomIntlFlag(String cardDomIntlFlag) {
        this.cardDomIntlFlag = cardDomIntlFlag;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setTxnType(String txnType) {
        this.txnType = txnType;
    }

    public void setTxnCode(String txnCode) {
        this.txnCode = txnCode;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public void setTxnDateTime(LocalDateTime txnDateTime) {
        this.txnDateTime = txnDateTime;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public void setOutStatus(String outStatus) {
        this.outStatus = outStatus;
    }

    public void setIncStatus(String incStatus) {
        this.incStatus = incStatus;
    }

    public void setMsgTypeId(String msgTypeId) {
        this.msgTypeId = msgTypeId;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public void setRevIndicator(Character revIndicator) {
        this.revIndicator = revIndicator;
    }

    public void setIrfAmountUsd(Double irfAmountUsd) {
        this.irfAmountUsd = irfAmountUsd;
    }

    public void setOriginalRrn(String originalRrn) {
        this.originalRrn = originalRrn;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public void setNetworkData(String networkData) {
        this.networkData = networkData;
    }

    public void setProcCode(String procCode) {
        this.procCode = procCode;
    }

    public void setVisaInchgFeeAmount(Double visaInchgFeeAmount) {
        this.visaInchgFeeAmount = visaInchgFeeAmount;
    }

    public void setVisaInchgFeeSign(String visaInchgFeeSign) {
        this.visaInchgFeeSign = visaInchgFeeSign;
    }

    public void setVisaFeeDesc(String visaFeeDesc) {
        this.visaFeeDesc = visaFeeDesc;
    }

    public void setVisaIrfMatch(String visaIrfMatch) {
        this.visaIrfMatch = visaIrfMatch;
    }

    public void setVisaIrfDiff(Double visaIrfDiff) {
        this.visaIrfDiff = visaIrfDiff;
    }

    public void setVisaTxnAmount(Double visaTxnAmount) {
        this.visaTxnAmount = visaTxnAmount;
    }

    public void setMciTxnAmount(Double mciTxnAmount) {
        this.mciTxnAmount = mciTxnAmount;
    }

    public void setMciReconAmount(Double mciReconAmount) {
        this.mciReconAmount = mciReconAmount;
    }

    public void setMciTxnIrfAmount(Double mciTxnIrfAmount) {
        this.mciTxnIrfAmount = mciTxnIrfAmount;
    }

    public void setMciTxnIrfUsdAmount(Double mciTxnIrfUsdAmount) {
        this.mciTxnIrfUsdAmount = mciTxnIrfUsdAmount;
    }

    public void setMciIrd(String mciIrd) {
        this.mciIrd = mciIrd;
    }

    public void setMciIrfMatch(String mciIrfMatch) {
        this.mciIrfMatch = mciIrfMatch;
    }

    public void setMciIrfDiff(Double mciIrfDiff) {
        this.mciIrfDiff = mciIrfDiff;
    }

    public void setBusinessCycle(String businessCycle) {
        this.businessCycle = businessCycle;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public void setApprovalCode(String approvalCode) {
        this.approvalCode = approvalCode;
    }

    public void setUaeSwitchIrf(Double uaeSwitchIrf) {
        this.uaeSwitchIrf = uaeSwitchIrf;
    }

    public void setUaeSwitchPF1(Double uaeSwitchPF1) {
        this.uaeSwitchPF1 = uaeSwitchPF1;
    }

    public void setUaeSwitchPF2(Double uaeSwitchPF2) {
        this.uaeSwitchPF2 = uaeSwitchPF2;
    }

    public void setVisaSmsTxnId(String visaSmsTxnId) {
        this.visaSmsTxnId = visaSmsTxnId;
    }

    public void setVisaSmsIrfAmount(Double visaSmsIrfAmount) {
        this.visaSmsIrfAmount = visaSmsIrfAmount;
    }

    public void setVisaSmsInchgFeeSign(String visaSmsInchgFeeSign) {
        this.visaSmsInchgFeeSign = visaSmsInchgFeeSign;
    }

    public void setVisaSmsIrfMatch(String visaSmsIrfMatch) {
        this.visaSmsIrfMatch = visaSmsIrfMatch;
    }

    public void setVisaSmsIrfDiff(Double visaSmsIrfDiff) {
        this.visaSmsIrfDiff = visaSmsIrfDiff;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public void setDccAmount(Double dccAmount) {
        this.dccAmount = dccAmount;
    }

    public void setDccCurrency(String dccCurrency) {
        this.dccCurrency = dccCurrency;
    }

    public void setDccIndicator(Character dccIndicator) {
        this.dccIndicator = dccIndicator;
    }

    public void setDccExchangeRate(Double dccExchangeRate) {
        this.dccExchangeRate = dccExchangeRate;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ViewTxnInquiryDetails)) {
            return false;
        }
        ViewTxnInquiryDetails other = (ViewTxnInquiryDetails)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        Integer this$serialNumber = this.getSerialNumber();
        Integer other$serialNumber = other.getSerialNumber();
        if (this$serialNumber == null ? other$serialNumber != null : !((Object)this$serialNumber).equals(other$serialNumber)) {
            return false;
        }
        Double this$irfFixed = this.getIrfFixed();
        Double other$irfFixed = other.getIrfFixed();
        if (this$irfFixed == null ? other$irfFixed != null : !((Object)this$irfFixed).equals(other$irfFixed)) {
            return false;
        }
        Double this$irfPercent = this.getIrfPercent();
        Double other$irfPercent = other.getIrfPercent();
        if (this$irfPercent == null ? other$irfPercent != null : !((Object)this$irfPercent).equals(other$irfPercent)) {
            return false;
        }
        Double this$irfAmount = this.getIrfAmount();
        Double other$irfAmount = other.getIrfAmount();
        if (this$irfAmount == null ? other$irfAmount != null : !((Object)this$irfAmount).equals(other$irfAmount)) {
            return false;
        }
        Double this$irfMinAmount = this.getIrfMinAmount();
        Double other$irfMinAmount = other.getIrfMinAmount();
        if (this$irfMinAmount == null ? other$irfMinAmount != null : !((Object)this$irfMinAmount).equals(other$irfMinAmount)) {
            return false;
        }
        Double this$irfMaxAmount = this.getIrfMaxAmount();
        Double other$irfMaxAmount = other.getIrfMaxAmount();
        if (this$irfMaxAmount == null ? other$irfMaxAmount != null : !((Object)this$irfMaxAmount).equals(other$irfMaxAmount)) {
            return false;
        }
        Double this$txnAmount = this.getTxnAmount();
        Double other$txnAmount = other.getTxnAmount();
        if (this$txnAmount == null ? other$txnAmount != null : !((Object)this$txnAmount).equals(other$txnAmount)) {
            return false;
        }
        Character this$revIndicator = this.getRevIndicator();
        Character other$revIndicator = other.getRevIndicator();
        if (this$revIndicator == null ? other$revIndicator != null : !((Object)this$revIndicator).equals(other$revIndicator)) {
            return false;
        }
        Double this$irfAmountUsd = this.getIrfAmountUsd();
        Double other$irfAmountUsd = other.getIrfAmountUsd();
        if (this$irfAmountUsd == null ? other$irfAmountUsd != null : !((Object)this$irfAmountUsd).equals(other$irfAmountUsd)) {
            return false;
        }
        Double this$visaInchgFeeAmount = this.getVisaInchgFeeAmount();
        Double other$visaInchgFeeAmount = other.getVisaInchgFeeAmount();
        if (this$visaInchgFeeAmount == null ? other$visaInchgFeeAmount != null : !((Object)this$visaInchgFeeAmount).equals(other$visaInchgFeeAmount)) {
            return false;
        }
        Double this$visaIrfDiff = this.getVisaIrfDiff();
        Double other$visaIrfDiff = other.getVisaIrfDiff();
        if (this$visaIrfDiff == null ? other$visaIrfDiff != null : !((Object)this$visaIrfDiff).equals(other$visaIrfDiff)) {
            return false;
        }
        Double this$visaTxnAmount = this.getVisaTxnAmount();
        Double other$visaTxnAmount = other.getVisaTxnAmount();
        if (this$visaTxnAmount == null ? other$visaTxnAmount != null : !((Object)this$visaTxnAmount).equals(other$visaTxnAmount)) {
            return false;
        }
        Double this$mciTxnAmount = this.getMciTxnAmount();
        Double other$mciTxnAmount = other.getMciTxnAmount();
        if (this$mciTxnAmount == null ? other$mciTxnAmount != null : !((Object)this$mciTxnAmount).equals(other$mciTxnAmount)) {
            return false;
        }
        Double this$mciReconAmount = this.getMciReconAmount();
        Double other$mciReconAmount = other.getMciReconAmount();
        if (this$mciReconAmount == null ? other$mciReconAmount != null : !((Object)this$mciReconAmount).equals(other$mciReconAmount)) {
            return false;
        }
        Double this$mciTxnIrfAmount = this.getMciTxnIrfAmount();
        Double other$mciTxnIrfAmount = other.getMciTxnIrfAmount();
        if (this$mciTxnIrfAmount == null ? other$mciTxnIrfAmount != null : !((Object)this$mciTxnIrfAmount).equals(other$mciTxnIrfAmount)) {
            return false;
        }
        Double this$mciTxnIrfUsdAmount = this.getMciTxnIrfUsdAmount();
        Double other$mciTxnIrfUsdAmount = other.getMciTxnIrfUsdAmount();
        if (this$mciTxnIrfUsdAmount == null ? other$mciTxnIrfUsdAmount != null : !((Object)this$mciTxnIrfUsdAmount).equals(other$mciTxnIrfUsdAmount)) {
            return false;
        }
        Double this$mciIrfDiff = this.getMciIrfDiff();
        Double other$mciIrfDiff = other.getMciIrfDiff();
        if (this$mciIrfDiff == null ? other$mciIrfDiff != null : !((Object)this$mciIrfDiff).equals(other$mciIrfDiff)) {
            return false;
        }
        Double this$uaeSwitchIrf = this.getUaeSwitchIrf();
        Double other$uaeSwitchIrf = other.getUaeSwitchIrf();
        if (this$uaeSwitchIrf == null ? other$uaeSwitchIrf != null : !((Object)this$uaeSwitchIrf).equals(other$uaeSwitchIrf)) {
            return false;
        }
        Double this$uaeSwitchPF1 = this.getUaeSwitchPF1();
        Double other$uaeSwitchPF1 = other.getUaeSwitchPF1();
        if (this$uaeSwitchPF1 == null ? other$uaeSwitchPF1 != null : !((Object)this$uaeSwitchPF1).equals(other$uaeSwitchPF1)) {
            return false;
        }
        Double this$uaeSwitchPF2 = this.getUaeSwitchPF2();
        Double other$uaeSwitchPF2 = other.getUaeSwitchPF2();
        if (this$uaeSwitchPF2 == null ? other$uaeSwitchPF2 != null : !((Object)this$uaeSwitchPF2).equals(other$uaeSwitchPF2)) {
            return false;
        }
        Double this$visaSmsIrfAmount = this.getVisaSmsIrfAmount();
        Double other$visaSmsIrfAmount = other.getVisaSmsIrfAmount();
        if (this$visaSmsIrfAmount == null ? other$visaSmsIrfAmount != null : !((Object)this$visaSmsIrfAmount).equals(other$visaSmsIrfAmount)) {
            return false;
        }
        Double this$visaSmsIrfDiff = this.getVisaSmsIrfDiff();
        Double other$visaSmsIrfDiff = other.getVisaSmsIrfDiff();
        if (this$visaSmsIrfDiff == null ? other$visaSmsIrfDiff != null : !((Object)this$visaSmsIrfDiff).equals(other$visaSmsIrfDiff)) {
            return false;
        }
        Double this$dccAmount = this.getDccAmount();
        Double other$dccAmount = other.getDccAmount();
        if (this$dccAmount == null ? other$dccAmount != null : !((Object)this$dccAmount).equals(other$dccAmount)) {
            return false;
        }
        Character this$dccIndicator = this.getDccIndicator();
        Character other$dccIndicator = other.getDccIndicator();
        if (this$dccIndicator == null ? other$dccIndicator != null : !((Object)this$dccIndicator).equals(other$dccIndicator)) {
            return false;
        }
        Double this$dccExchangeRate = this.getDccExchangeRate();
        Double other$dccExchangeRate = other.getDccExchangeRate();
        if (this$dccExchangeRate == null ? other$dccExchangeRate != null : !((Object)this$dccExchangeRate).equals(other$dccExchangeRate)) {
            return false;
        }
        String this$txnId = this.getTxnId();
        String other$txnId = other.getTxnId();
        if (this$txnId == null ? other$txnId != null : !this$txnId.equals(other$txnId)) {
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
        String this$retrievalRefNumber = this.getRetrievalRefNumber();
        String other$retrievalRefNumber = other.getRetrievalRefNumber();
        if (this$retrievalRefNumber == null ? other$retrievalRefNumber != null : !this$retrievalRefNumber.equals(other$retrievalRefNumber)) {
            return false;
        }
        String this$ird = this.getIrd();
        String other$ird = other.getIrd();
        if (this$ird == null ? other$ird != null : !this$ird.equals(other$ird)) {
            return false;
        }
        String this$cardDomIntlFlag = this.getCardDomIntlFlag();
        String other$cardDomIntlFlag = other.getCardDomIntlFlag();
        if (this$cardDomIntlFlag == null ? other$cardDomIntlFlag != null : !this$cardDomIntlFlag.equals(other$cardDomIntlFlag)) {
            return false;
        }
        String this$cardType = this.getCardType();
        String other$cardType = other.getCardType();
        if (this$cardType == null ? other$cardType != null : !this$cardType.equals(other$cardType)) {
            return false;
        }
        String this$cardNumber = this.getCardNumber();
        String other$cardNumber = other.getCardNumber();
        if (this$cardNumber == null ? other$cardNumber != null : !this$cardNumber.equals(other$cardNumber)) {
            return false;
        }
        String this$txnType = this.getTxnType();
        String other$txnType = other.getTxnType();
        if (this$txnType == null ? other$txnType != null : !this$txnType.equals(other$txnType)) {
            return false;
        }
        String this$txnCode = this.getTxnCode();
        String other$txnCode = other.getTxnCode();
        if (this$txnCode == null ? other$txnCode != null : !this$txnCode.equals(other$txnCode)) {
            return false;
        }
        String this$remarks = this.getRemarks();
        String other$remarks = other.getRemarks();
        if (this$remarks == null ? other$remarks != null : !this$remarks.equals(other$remarks)) {
            return false;
        }
        String this$network = this.getNetwork();
        String other$network = other.getNetwork();
        if (this$network == null ? other$network != null : !this$network.equals(other$network)) {
            return false;
        }
        LocalDateTime this$txnDateTime = this.getTxnDateTime();
        LocalDateTime other$txnDateTime = other.getTxnDateTime();
        if (this$txnDateTime == null ? other$txnDateTime != null : !((Object)this$txnDateTime).equals(other$txnDateTime)) {
            return false;
        }
        String this$respCode = this.getRespCode();
        String other$respCode = other.getRespCode();
        if (this$respCode == null ? other$respCode != null : !this$respCode.equals(other$respCode)) {
            return false;
        }
        String this$outStatus = this.getOutStatus();
        String other$outStatus = other.getOutStatus();
        if (this$outStatus == null ? other$outStatus != null : !this$outStatus.equals(other$outStatus)) {
            return false;
        }
        String this$incStatus = this.getIncStatus();
        String other$incStatus = other.getIncStatus();
        if (this$incStatus == null ? other$incStatus != null : !this$incStatus.equals(other$incStatus)) {
            return false;
        }
        String this$msgTypeId = this.getMsgTypeId();
        String other$msgTypeId = other.getMsgTypeId();
        if (this$msgTypeId == null ? other$msgTypeId != null : !this$msgTypeId.equals(other$msgTypeId)) {
            return false;
        }
        String this$bankCode = this.getBankCode();
        String other$bankCode = other.getBankCode();
        if (this$bankCode == null ? other$bankCode != null : !this$bankCode.equals(other$bankCode)) {
            return false;
        }
        String this$originalRrn = this.getOriginalRrn();
        String other$originalRrn = other.getOriginalRrn();
        if (this$originalRrn == null ? other$originalRrn != null : !this$originalRrn.equals(other$originalRrn)) {
            return false;
        }
        LocalDateTime this$localDateTime = this.getLocalDateTime();
        LocalDateTime other$localDateTime = other.getLocalDateTime();
        if (this$localDateTime == null ? other$localDateTime != null : !((Object)this$localDateTime).equals(other$localDateTime)) {
            return false;
        }
        String this$networkData = this.getNetworkData();
        String other$networkData = other.getNetworkData();
        if (this$networkData == null ? other$networkData != null : !this$networkData.equals(other$networkData)) {
            return false;
        }
        String this$procCode = this.getProcCode();
        String other$procCode = other.getProcCode();
        if (this$procCode == null ? other$procCode != null : !this$procCode.equals(other$procCode)) {
            return false;
        }
        String this$visaInchgFeeSign = this.getVisaInchgFeeSign();
        String other$visaInchgFeeSign = other.getVisaInchgFeeSign();
        if (this$visaInchgFeeSign == null ? other$visaInchgFeeSign != null : !this$visaInchgFeeSign.equals(other$visaInchgFeeSign)) {
            return false;
        }
        String this$visaFeeDesc = this.getVisaFeeDesc();
        String other$visaFeeDesc = other.getVisaFeeDesc();
        if (this$visaFeeDesc == null ? other$visaFeeDesc != null : !this$visaFeeDesc.equals(other$visaFeeDesc)) {
            return false;
        }
        String this$visaIrfMatch = this.getVisaIrfMatch();
        String other$visaIrfMatch = other.getVisaIrfMatch();
        if (this$visaIrfMatch == null ? other$visaIrfMatch != null : !this$visaIrfMatch.equals(other$visaIrfMatch)) {
            return false;
        }
        String this$mciIrd = this.getMciIrd();
        String other$mciIrd = other.getMciIrd();
        if (this$mciIrd == null ? other$mciIrd != null : !this$mciIrd.equals(other$mciIrd)) {
            return false;
        }
        String this$mciIrfMatch = this.getMciIrfMatch();
        String other$mciIrfMatch = other.getMciIrfMatch();
        if (this$mciIrfMatch == null ? other$mciIrfMatch != null : !this$mciIrfMatch.equals(other$mciIrfMatch)) {
            return false;
        }
        String this$businessCycle = this.getBusinessCycle();
        String other$businessCycle = other.getBusinessCycle();
        if (this$businessCycle == null ? other$businessCycle != null : !this$businessCycle.equals(other$businessCycle)) {
            return false;
        }
        String this$fileId = this.getFileId();
        String other$fileId = other.getFileId();
        if (this$fileId == null ? other$fileId != null : !this$fileId.equals(other$fileId)) {
            return false;
        }
        String this$approvalCode = this.getApprovalCode();
        String other$approvalCode = other.getApprovalCode();
        if (this$approvalCode == null ? other$approvalCode != null : !this$approvalCode.equals(other$approvalCode)) {
            return false;
        }
        String this$visaSmsTxnId = this.getVisaSmsTxnId();
        String other$visaSmsTxnId = other.getVisaSmsTxnId();
        if (this$visaSmsTxnId == null ? other$visaSmsTxnId != null : !this$visaSmsTxnId.equals(other$visaSmsTxnId)) {
            return false;
        }
        String this$visaSmsInchgFeeSign = this.getVisaSmsInchgFeeSign();
        String other$visaSmsInchgFeeSign = other.getVisaSmsInchgFeeSign();
        if (this$visaSmsInchgFeeSign == null ? other$visaSmsInchgFeeSign != null : !this$visaSmsInchgFeeSign.equals(other$visaSmsInchgFeeSign)) {
            return false;
        }
        String this$visaSmsIrfMatch = this.getVisaSmsIrfMatch();
        String other$visaSmsIrfMatch = other.getVisaSmsIrfMatch();
        if (this$visaSmsIrfMatch == null ? other$visaSmsIrfMatch != null : !this$visaSmsIrfMatch.equals(other$visaSmsIrfMatch)) {
            return false;
        }
        String this$schema = this.getSchema();
        String other$schema = other.getSchema();
        if (this$schema == null ? other$schema != null : !this$schema.equals(other$schema)) {
            return false;
        }
        String this$dccCurrency = this.getDccCurrency();
        String other$dccCurrency = other.getDccCurrency();
        return !(this$dccCurrency == null ? other$dccCurrency != null : !this$dccCurrency.equals(other$dccCurrency));
    }

    protected boolean canEqual(Object other) {
        return other instanceof ViewTxnInquiryDetails;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Integer $serialNumber = this.getSerialNumber();
        result = result * 59 + ($serialNumber == null ? 43 : ((Object)$serialNumber).hashCode());
        Double $irfFixed = this.getIrfFixed();
        result = result * 59 + ($irfFixed == null ? 43 : ((Object)$irfFixed).hashCode());
        Double $irfPercent = this.getIrfPercent();
        result = result * 59 + ($irfPercent == null ? 43 : ((Object)$irfPercent).hashCode());
        Double $irfAmount = this.getIrfAmount();
        result = result * 59 + ($irfAmount == null ? 43 : ((Object)$irfAmount).hashCode());
        Double $irfMinAmount = this.getIrfMinAmount();
        result = result * 59 + ($irfMinAmount == null ? 43 : ((Object)$irfMinAmount).hashCode());
        Double $irfMaxAmount = this.getIrfMaxAmount();
        result = result * 59 + ($irfMaxAmount == null ? 43 : ((Object)$irfMaxAmount).hashCode());
        Double $txnAmount = this.getTxnAmount();
        result = result * 59 + ($txnAmount == null ? 43 : ((Object)$txnAmount).hashCode());
        Character $revIndicator = this.getRevIndicator();
        result = result * 59 + ($revIndicator == null ? 43 : ((Object)$revIndicator).hashCode());
        Double $irfAmountUsd = this.getIrfAmountUsd();
        result = result * 59 + ($irfAmountUsd == null ? 43 : ((Object)$irfAmountUsd).hashCode());
        Double $visaInchgFeeAmount = this.getVisaInchgFeeAmount();
        result = result * 59 + ($visaInchgFeeAmount == null ? 43 : ((Object)$visaInchgFeeAmount).hashCode());
        Double $visaIrfDiff = this.getVisaIrfDiff();
        result = result * 59 + ($visaIrfDiff == null ? 43 : ((Object)$visaIrfDiff).hashCode());
        Double $visaTxnAmount = this.getVisaTxnAmount();
        result = result * 59 + ($visaTxnAmount == null ? 43 : ((Object)$visaTxnAmount).hashCode());
        Double $mciTxnAmount = this.getMciTxnAmount();
        result = result * 59 + ($mciTxnAmount == null ? 43 : ((Object)$mciTxnAmount).hashCode());
        Double $mciReconAmount = this.getMciReconAmount();
        result = result * 59 + ($mciReconAmount == null ? 43 : ((Object)$mciReconAmount).hashCode());
        Double $mciTxnIrfAmount = this.getMciTxnIrfAmount();
        result = result * 59 + ($mciTxnIrfAmount == null ? 43 : ((Object)$mciTxnIrfAmount).hashCode());
        Double $mciTxnIrfUsdAmount = this.getMciTxnIrfUsdAmount();
        result = result * 59 + ($mciTxnIrfUsdAmount == null ? 43 : ((Object)$mciTxnIrfUsdAmount).hashCode());
        Double $mciIrfDiff = this.getMciIrfDiff();
        result = result * 59 + ($mciIrfDiff == null ? 43 : ((Object)$mciIrfDiff).hashCode());
        Double $uaeSwitchIrf = this.getUaeSwitchIrf();
        result = result * 59 + ($uaeSwitchIrf == null ? 43 : ((Object)$uaeSwitchIrf).hashCode());
        Double $uaeSwitchPF1 = this.getUaeSwitchPF1();
        result = result * 59 + ($uaeSwitchPF1 == null ? 43 : ((Object)$uaeSwitchPF1).hashCode());
        Double $uaeSwitchPF2 = this.getUaeSwitchPF2();
        result = result * 59 + ($uaeSwitchPF2 == null ? 43 : ((Object)$uaeSwitchPF2).hashCode());
        Double $visaSmsIrfAmount = this.getVisaSmsIrfAmount();
        result = result * 59 + ($visaSmsIrfAmount == null ? 43 : ((Object)$visaSmsIrfAmount).hashCode());
        Double $visaSmsIrfDiff = this.getVisaSmsIrfDiff();
        result = result * 59 + ($visaSmsIrfDiff == null ? 43 : ((Object)$visaSmsIrfDiff).hashCode());
        Double $dccAmount = this.getDccAmount();
        result = result * 59 + ($dccAmount == null ? 43 : ((Object)$dccAmount).hashCode());
        Character $dccIndicator = this.getDccIndicator();
        result = result * 59 + ($dccIndicator == null ? 43 : ((Object)$dccIndicator).hashCode());
        Double $dccExchangeRate = this.getDccExchangeRate();
        result = result * 59 + ($dccExchangeRate == null ? 43 : ((Object)$dccExchangeRate).hashCode());
        String $txnId = this.getTxnId();
        result = result * 59 + ($txnId == null ? 43 : $txnId.hashCode());
        String $merchantId = this.getMerchantId();
        result = result * 59 + ($merchantId == null ? 43 : $merchantId.hashCode());
        String $terminalId = this.getTerminalId();
        result = result * 59 + ($terminalId == null ? 43 : $terminalId.hashCode());
        String $retrievalRefNumber = this.getRetrievalRefNumber();
        result = result * 59 + ($retrievalRefNumber == null ? 43 : $retrievalRefNumber.hashCode());
        String $ird = this.getIrd();
        result = result * 59 + ($ird == null ? 43 : $ird.hashCode());
        String $cardDomIntlFlag = this.getCardDomIntlFlag();
        result = result * 59 + ($cardDomIntlFlag == null ? 43 : $cardDomIntlFlag.hashCode());
        String $cardType = this.getCardType();
        result = result * 59 + ($cardType == null ? 43 : $cardType.hashCode());
        String $cardNumber = this.getCardNumber();
        result = result * 59 + ($cardNumber == null ? 43 : $cardNumber.hashCode());
        String $txnType = this.getTxnType();
        result = result * 59 + ($txnType == null ? 43 : $txnType.hashCode());
        String $txnCode = this.getTxnCode();
        result = result * 59 + ($txnCode == null ? 43 : $txnCode.hashCode());
        String $remarks = this.getRemarks();
        result = result * 59 + ($remarks == null ? 43 : $remarks.hashCode());
        String $network = this.getNetwork();
        result = result * 59 + ($network == null ? 43 : $network.hashCode());
        LocalDateTime $txnDateTime = this.getTxnDateTime();
        result = result * 59 + ($txnDateTime == null ? 43 : ((Object)$txnDateTime).hashCode());
        String $respCode = this.getRespCode();
        result = result * 59 + ($respCode == null ? 43 : $respCode.hashCode());
        String $outStatus = this.getOutStatus();
        result = result * 59 + ($outStatus == null ? 43 : $outStatus.hashCode());
        String $incStatus = this.getIncStatus();
        result = result * 59 + ($incStatus == null ? 43 : $incStatus.hashCode());
        String $msgTypeId = this.getMsgTypeId();
        result = result * 59 + ($msgTypeId == null ? 43 : $msgTypeId.hashCode());
        String $bankCode = this.getBankCode();
        result = result * 59 + ($bankCode == null ? 43 : $bankCode.hashCode());
        String $originalRrn = this.getOriginalRrn();
        result = result * 59 + ($originalRrn == null ? 43 : $originalRrn.hashCode());
        LocalDateTime $localDateTime = this.getLocalDateTime();
        result = result * 59 + ($localDateTime == null ? 43 : ((Object)$localDateTime).hashCode());
        String $networkData = this.getNetworkData();
        result = result * 59 + ($networkData == null ? 43 : $networkData.hashCode());
        String $procCode = this.getProcCode();
        result = result * 59 + ($procCode == null ? 43 : $procCode.hashCode());
        String $visaInchgFeeSign = this.getVisaInchgFeeSign();
        result = result * 59 + ($visaInchgFeeSign == null ? 43 : $visaInchgFeeSign.hashCode());
        String $visaFeeDesc = this.getVisaFeeDesc();
        result = result * 59 + ($visaFeeDesc == null ? 43 : $visaFeeDesc.hashCode());
        String $visaIrfMatch = this.getVisaIrfMatch();
        result = result * 59 + ($visaIrfMatch == null ? 43 : $visaIrfMatch.hashCode());
        String $mciIrd = this.getMciIrd();
        result = result * 59 + ($mciIrd == null ? 43 : $mciIrd.hashCode());
        String $mciIrfMatch = this.getMciIrfMatch();
        result = result * 59 + ($mciIrfMatch == null ? 43 : $mciIrfMatch.hashCode());
        String $businessCycle = this.getBusinessCycle();
        result = result * 59 + ($businessCycle == null ? 43 : $businessCycle.hashCode());
        String $fileId = this.getFileId();
        result = result * 59 + ($fileId == null ? 43 : $fileId.hashCode());
        String $approvalCode = this.getApprovalCode();
        result = result * 59 + ($approvalCode == null ? 43 : $approvalCode.hashCode());
        String $visaSmsTxnId = this.getVisaSmsTxnId();
        result = result * 59 + ($visaSmsTxnId == null ? 43 : $visaSmsTxnId.hashCode());
        String $visaSmsInchgFeeSign = this.getVisaSmsInchgFeeSign();
        result = result * 59 + ($visaSmsInchgFeeSign == null ? 43 : $visaSmsInchgFeeSign.hashCode());
        String $visaSmsIrfMatch = this.getVisaSmsIrfMatch();
        result = result * 59 + ($visaSmsIrfMatch == null ? 43 : $visaSmsIrfMatch.hashCode());
        String $schema = this.getSchema();
        result = result * 59 + ($schema == null ? 43 : $schema.hashCode());
        String $dccCurrency = this.getDccCurrency();
        result = result * 59 + ($dccCurrency == null ? 43 : $dccCurrency.hashCode());
        return result;
    }

    public String toString() {
        return "ViewTxnInquiryDetails(serialNumber=" + this.getSerialNumber() + ", txnId=" + this.getTxnId() + ", merchantId=" + this.getMerchantId() + ", terminalId=" + this.getTerminalId() + ", retrievalRefNumber=" + this.getRetrievalRefNumber() + ", ird=" + this.getIrd() + ", irfFixed=" + this.getIrfFixed() + ", irfPercent=" + this.getIrfPercent() + ", irfAmount=" + this.getIrfAmount() + ", irfMinAmount=" + this.getIrfMinAmount() + ", irfMaxAmount=" + this.getIrfMaxAmount() + ", txnAmount=" + this.getTxnAmount() + ", cardDomIntlFlag=" + this.getCardDomIntlFlag() + ", cardType=" + this.getCardType() + ", cardNumber=" + this.getCardNumber() + ", txnType=" + this.getTxnType() + ", txnCode=" + this.getTxnCode() + ", remarks=" + this.getRemarks() + ", network=" + this.getNetwork() + ", txnDateTime=" + String.valueOf(this.getTxnDateTime()) + ", respCode=" + this.getRespCode() + ", outStatus=" + this.getOutStatus() + ", incStatus=" + this.getIncStatus() + ", msgTypeId=" + this.getMsgTypeId() + ", bankCode=" + this.getBankCode() + ", revIndicator=" + this.getRevIndicator() + ", irfAmountUsd=" + this.getIrfAmountUsd() + ", originalRrn=" + this.getOriginalRrn() + ", localDateTime=" + String.valueOf(this.getLocalDateTime()) + ", networkData=" + this.getNetworkData() + ", procCode=" + this.getProcCode() + ", visaInchgFeeAmount=" + this.getVisaInchgFeeAmount() + ", visaInchgFeeSign=" + this.getVisaInchgFeeSign() + ", visaFeeDesc=" + this.getVisaFeeDesc() + ", visaIrfMatch=" + this.getVisaIrfMatch() + ", visaIrfDiff=" + this.getVisaIrfDiff() + ", visaTxnAmount=" + this.getVisaTxnAmount() + ", mciTxnAmount=" + this.getMciTxnAmount() + ", mciReconAmount=" + this.getMciReconAmount() + ", mciTxnIrfAmount=" + this.getMciTxnIrfAmount() + ", mciTxnIrfUsdAmount=" + this.getMciTxnIrfUsdAmount() + ", mciIrd=" + this.getMciIrd() + ", mciIrfMatch=" + this.getMciIrfMatch() + ", mciIrfDiff=" + this.getMciIrfDiff() + ", businessCycle=" + this.getBusinessCycle() + ", fileId=" + this.getFileId() + ", approvalCode=" + this.getApprovalCode() + ", uaeSwitchIrf=" + this.getUaeSwitchIrf() + ", uaeSwitchPF1=" + this.getUaeSwitchPF1() + ", uaeSwitchPF2=" + this.getUaeSwitchPF2() + ", visaSmsTxnId=" + this.getVisaSmsTxnId() + ", visaSmsIrfAmount=" + this.getVisaSmsIrfAmount() + ", visaSmsInchgFeeSign=" + this.getVisaSmsInchgFeeSign() + ", visaSmsIrfMatch=" + this.getVisaSmsIrfMatch() + ", visaSmsIrfDiff=" + this.getVisaSmsIrfDiff() + ", schema=" + this.getSchema() + ", dccAmount=" + this.getDccAmount() + ", dccCurrency=" + this.getDccCurrency() + ", dccIndicator=" + this.getDccIndicator() + ", dccExchangeRate=" + this.getDccExchangeRate() + ")";
    }
}

