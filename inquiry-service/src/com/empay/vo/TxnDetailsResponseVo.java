/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.vo.TxnDetailsResponseVo
 */
package com.empay.vo;

import java.math.BigDecimal;

public class TxnDetailsResponseVo {
    private String merchantId;
    private String terminalId;
    private String rrn;
    private String irdCode;
    private double fixed;
    private double percentage;
    private String irfMin;
    private String irfMax;
    private double irfAmount;
    private double txnAmount;
    private String domIntlFlag;
    private String cardType;
    private String cardNumber;
    private String txnType;
    private String description;
    private String network;
    private String txnDate;
    private String txnStatus;
    private String outgoingStatus;
    private String incomingStatus;
    private String responseCode;
    private String mti;
    private String irfCallbackStatus;
    private String refCode;
    private int totalCount;
    private int totalPage;
    private String bankId;
    private String revIndicator;
    private double irfAmountUSD;
    private String originalRRN;
    private String localDate;
    private String transactionId;
    private String traceId;
    private double interchangeFeeAmountLocal;
    private String interchangeFeeSign;
    private String feeDescriptor;
    private String interchangeMatched;
    private double interchangeDifferenceAmount;
    private double transactionAmountLocal;
    private double reconciliationAmountUSD;
    private double interchangeAmountLocal;
    private double interchangeAmountUSD;
    private String interchangeRateDesignator;
    private String processingCode;
    private double txnAmountLocal;
    private double uaesIrf;
    private double uaesPF1;
    private double uaesPF2;
    private String scheme;
    private String fileID;
    private String businessCycle;
    private String approvalCode;
    private Character dccIndicator;
    private BigDecimal dccAmount;
    private String dccCurrency;
    private double exchangeRate;

    public String getMerchantId() {
        return this.merchantId;
    }

    public String getTerminalId() {
        return this.terminalId;
    }

    public String getRrn() {
        return this.rrn;
    }

    public String getIrdCode() {
        return this.irdCode;
    }

    public double getFixed() {
        return this.fixed;
    }

    public double getPercentage() {
        return this.percentage;
    }

    public String getIrfMin() {
        return this.irfMin;
    }

    public String getIrfMax() {
        return this.irfMax;
    }

    public double getIrfAmount() {
        return this.irfAmount;
    }

    public double getTxnAmount() {
        return this.txnAmount;
    }

    public String getDomIntlFlag() {
        return this.domIntlFlag;
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

    public String getDescription() {
        return this.description;
    }

    public String getNetwork() {
        return this.network;
    }

    public String getTxnDate() {
        return this.txnDate;
    }

    public String getTxnStatus() {
        return this.txnStatus;
    }

    public String getOutgoingStatus() {
        return this.outgoingStatus;
    }

    public String getIncomingStatus() {
        return this.incomingStatus;
    }

    public String getResponseCode() {
        return this.responseCode;
    }

    public String getMti() {
        return this.mti;
    }

    public String getIrfCallbackStatus() {
        return this.irfCallbackStatus;
    }

    public String getRefCode() {
        return this.refCode;
    }

    public int getTotalCount() {
        return this.totalCount;
    }

    public int getTotalPage() {
        return this.totalPage;
    }

    public String getBankId() {
        return this.bankId;
    }

    public String getRevIndicator() {
        return this.revIndicator;
    }

    public double getIrfAmountUSD() {
        return this.irfAmountUSD;
    }

    public String getOriginalRRN() {
        return this.originalRRN;
    }

    public String getLocalDate() {
        return this.localDate;
    }

    public String getTransactionId() {
        return this.transactionId;
    }

    public String getTraceId() {
        return this.traceId;
    }

    public double getInterchangeFeeAmountLocal() {
        return this.interchangeFeeAmountLocal;
    }

    public String getInterchangeFeeSign() {
        return this.interchangeFeeSign;
    }

    public String getFeeDescriptor() {
        return this.feeDescriptor;
    }

    public String getInterchangeMatched() {
        return this.interchangeMatched;
    }

    public double getInterchangeDifferenceAmount() {
        return this.interchangeDifferenceAmount;
    }

    public double getTransactionAmountLocal() {
        return this.transactionAmountLocal;
    }

    public double getReconciliationAmountUSD() {
        return this.reconciliationAmountUSD;
    }

    public double getInterchangeAmountLocal() {
        return this.interchangeAmountLocal;
    }

    public double getInterchangeAmountUSD() {
        return this.interchangeAmountUSD;
    }

    public String getInterchangeRateDesignator() {
        return this.interchangeRateDesignator;
    }

    public String getProcessingCode() {
        return this.processingCode;
    }

    public double getTxnAmountLocal() {
        return this.txnAmountLocal;
    }

    public double getUaesIrf() {
        return this.uaesIrf;
    }

    public double getUaesPF1() {
        return this.uaesPF1;
    }

    public double getUaesPF2() {
        return this.uaesPF2;
    }

    public String getScheme() {
        return this.scheme;
    }

    public String getFileID() {
        return this.fileID;
    }

    public String getBusinessCycle() {
        return this.businessCycle;
    }

    public String getApprovalCode() {
        return this.approvalCode;
    }

    public Character getDccIndicator() {
        return this.dccIndicator;
    }

    public BigDecimal getDccAmount() {
        return this.dccAmount;
    }

    public String getDccCurrency() {
        return this.dccCurrency;
    }

    public double getExchangeRate() {
        return this.exchangeRate;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public void setRrn(String rrn) {
        this.rrn = rrn;
    }

    public void setIrdCode(String irdCode) {
        this.irdCode = irdCode;
    }

    public void setFixed(double fixed) {
        this.fixed = fixed;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public void setIrfMin(String irfMin) {
        this.irfMin = irfMin;
    }

    public void setIrfMax(String irfMax) {
        this.irfMax = irfMax;
    }

    public void setIrfAmount(double irfAmount) {
        this.irfAmount = irfAmount;
    }

    public void setTxnAmount(double txnAmount) {
        this.txnAmount = txnAmount;
    }

    public void setDomIntlFlag(String domIntlFlag) {
        this.domIntlFlag = domIntlFlag;
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

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public void setTxnDate(String txnDate) {
        this.txnDate = txnDate;
    }

    public void setTxnStatus(String txnStatus) {
        this.txnStatus = txnStatus;
    }

    public void setOutgoingStatus(String outgoingStatus) {
        this.outgoingStatus = outgoingStatus;
    }

    public void setIncomingStatus(String incomingStatus) {
        this.incomingStatus = incomingStatus;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public void setMti(String mti) {
        this.mti = mti;
    }

    public void setIrfCallbackStatus(String irfCallbackStatus) {
        this.irfCallbackStatus = irfCallbackStatus;
    }

    public void setRefCode(String refCode) {
        this.refCode = refCode;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public void setRevIndicator(String revIndicator) {
        this.revIndicator = revIndicator;
    }

    public void setIrfAmountUSD(double irfAmountUSD) {
        this.irfAmountUSD = irfAmountUSD;
    }

    public void setOriginalRRN(String originalRRN) {
        this.originalRRN = originalRRN;
    }

    public void setLocalDate(String localDate) {
        this.localDate = localDate;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public void setInterchangeFeeAmountLocal(double interchangeFeeAmountLocal) {
        this.interchangeFeeAmountLocal = interchangeFeeAmountLocal;
    }

    public void setInterchangeFeeSign(String interchangeFeeSign) {
        this.interchangeFeeSign = interchangeFeeSign;
    }

    public void setFeeDescriptor(String feeDescriptor) {
        this.feeDescriptor = feeDescriptor;
    }

    public void setInterchangeMatched(String interchangeMatched) {
        this.interchangeMatched = interchangeMatched;
    }

    public void setInterchangeDifferenceAmount(double interchangeDifferenceAmount) {
        this.interchangeDifferenceAmount = interchangeDifferenceAmount;
    }

    public void setTransactionAmountLocal(double transactionAmountLocal) {
        this.transactionAmountLocal = transactionAmountLocal;
    }

    public void setReconciliationAmountUSD(double reconciliationAmountUSD) {
        this.reconciliationAmountUSD = reconciliationAmountUSD;
    }

    public void setInterchangeAmountLocal(double interchangeAmountLocal) {
        this.interchangeAmountLocal = interchangeAmountLocal;
    }

    public void setInterchangeAmountUSD(double interchangeAmountUSD) {
        this.interchangeAmountUSD = interchangeAmountUSD;
    }

    public void setInterchangeRateDesignator(String interchangeRateDesignator) {
        this.interchangeRateDesignator = interchangeRateDesignator;
    }

    public void setProcessingCode(String processingCode) {
        this.processingCode = processingCode;
    }

    public void setTxnAmountLocal(double txnAmountLocal) {
        this.txnAmountLocal = txnAmountLocal;
    }

    public void setUaesIrf(double uaesIrf) {
        this.uaesIrf = uaesIrf;
    }

    public void setUaesPF1(double uaesPF1) {
        this.uaesPF1 = uaesPF1;
    }

    public void setUaesPF2(double uaesPF2) {
        this.uaesPF2 = uaesPF2;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public void setFileID(String fileID) {
        this.fileID = fileID;
    }

    public void setBusinessCycle(String businessCycle) {
        this.businessCycle = businessCycle;
    }

    public void setApprovalCode(String approvalCode) {
        this.approvalCode = approvalCode;
    }

    public void setDccIndicator(Character dccIndicator) {
        this.dccIndicator = dccIndicator;
    }

    public void setDccAmount(BigDecimal dccAmount) {
        this.dccAmount = dccAmount;
    }

    public void setDccCurrency(String dccCurrency) {
        this.dccCurrency = dccCurrency;
    }

    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof TxnDetailsResponseVo)) {
            return false;
        }
        TxnDetailsResponseVo other = (TxnDetailsResponseVo)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        if (Double.compare(this.getFixed(), other.getFixed()) != 0) {
            return false;
        }
        if (Double.compare(this.getPercentage(), other.getPercentage()) != 0) {
            return false;
        }
        if (Double.compare(this.getIrfAmount(), other.getIrfAmount()) != 0) {
            return false;
        }
        if (Double.compare(this.getTxnAmount(), other.getTxnAmount()) != 0) {
            return false;
        }
        if (this.getTotalCount() != other.getTotalCount()) {
            return false;
        }
        if (this.getTotalPage() != other.getTotalPage()) {
            return false;
        }
        if (Double.compare(this.getIrfAmountUSD(), other.getIrfAmountUSD()) != 0) {
            return false;
        }
        if (Double.compare(this.getInterchangeFeeAmountLocal(), other.getInterchangeFeeAmountLocal()) != 0) {
            return false;
        }
        if (Double.compare(this.getInterchangeDifferenceAmount(), other.getInterchangeDifferenceAmount()) != 0) {
            return false;
        }
        if (Double.compare(this.getTransactionAmountLocal(), other.getTransactionAmountLocal()) != 0) {
            return false;
        }
        if (Double.compare(this.getReconciliationAmountUSD(), other.getReconciliationAmountUSD()) != 0) {
            return false;
        }
        if (Double.compare(this.getInterchangeAmountLocal(), other.getInterchangeAmountLocal()) != 0) {
            return false;
        }
        if (Double.compare(this.getInterchangeAmountUSD(), other.getInterchangeAmountUSD()) != 0) {
            return false;
        }
        if (Double.compare(this.getTxnAmountLocal(), other.getTxnAmountLocal()) != 0) {
            return false;
        }
        if (Double.compare(this.getUaesIrf(), other.getUaesIrf()) != 0) {
            return false;
        }
        if (Double.compare(this.getUaesPF1(), other.getUaesPF1()) != 0) {
            return false;
        }
        if (Double.compare(this.getUaesPF2(), other.getUaesPF2()) != 0) {
            return false;
        }
        if (Double.compare(this.getExchangeRate(), other.getExchangeRate()) != 0) {
            return false;
        }
        Character this$dccIndicator = this.getDccIndicator();
        Character other$dccIndicator = other.getDccIndicator();
        if (this$dccIndicator == null ? other$dccIndicator != null : !((Object)this$dccIndicator).equals(other$dccIndicator)) {
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
        String this$rrn = this.getRrn();
        String other$rrn = other.getRrn();
        if (this$rrn == null ? other$rrn != null : !this$rrn.equals(other$rrn)) {
            return false;
        }
        String this$irdCode = this.getIrdCode();
        String other$irdCode = other.getIrdCode();
        if (this$irdCode == null ? other$irdCode != null : !this$irdCode.equals(other$irdCode)) {
            return false;
        }
        String this$irfMin = this.getIrfMin();
        String other$irfMin = other.getIrfMin();
        if (this$irfMin == null ? other$irfMin != null : !this$irfMin.equals(other$irfMin)) {
            return false;
        }
        String this$irfMax = this.getIrfMax();
        String other$irfMax = other.getIrfMax();
        if (this$irfMax == null ? other$irfMax != null : !this$irfMax.equals(other$irfMax)) {
            return false;
        }
        String this$domIntlFlag = this.getDomIntlFlag();
        String other$domIntlFlag = other.getDomIntlFlag();
        if (this$domIntlFlag == null ? other$domIntlFlag != null : !this$domIntlFlag.equals(other$domIntlFlag)) {
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
        String this$description = this.getDescription();
        String other$description = other.getDescription();
        if (this$description == null ? other$description != null : !this$description.equals(other$description)) {
            return false;
        }
        String this$network = this.getNetwork();
        String other$network = other.getNetwork();
        if (this$network == null ? other$network != null : !this$network.equals(other$network)) {
            return false;
        }
        String this$txnDate = this.getTxnDate();
        String other$txnDate = other.getTxnDate();
        if (this$txnDate == null ? other$txnDate != null : !this$txnDate.equals(other$txnDate)) {
            return false;
        }
        String this$txnStatus = this.getTxnStatus();
        String other$txnStatus = other.getTxnStatus();
        if (this$txnStatus == null ? other$txnStatus != null : !this$txnStatus.equals(other$txnStatus)) {
            return false;
        }
        String this$outgoingStatus = this.getOutgoingStatus();
        String other$outgoingStatus = other.getOutgoingStatus();
        if (this$outgoingStatus == null ? other$outgoingStatus != null : !this$outgoingStatus.equals(other$outgoingStatus)) {
            return false;
        }
        String this$incomingStatus = this.getIncomingStatus();
        String other$incomingStatus = other.getIncomingStatus();
        if (this$incomingStatus == null ? other$incomingStatus != null : !this$incomingStatus.equals(other$incomingStatus)) {
            return false;
        }
        String this$responseCode = this.getResponseCode();
        String other$responseCode = other.getResponseCode();
        if (this$responseCode == null ? other$responseCode != null : !this$responseCode.equals(other$responseCode)) {
            return false;
        }
        String this$mti = this.getMti();
        String other$mti = other.getMti();
        if (this$mti == null ? other$mti != null : !this$mti.equals(other$mti)) {
            return false;
        }
        String this$irfCallbackStatus = this.getIrfCallbackStatus();
        String other$irfCallbackStatus = other.getIrfCallbackStatus();
        if (this$irfCallbackStatus == null ? other$irfCallbackStatus != null : !this$irfCallbackStatus.equals(other$irfCallbackStatus)) {
            return false;
        }
        String this$refCode = this.getRefCode();
        String other$refCode = other.getRefCode();
        if (this$refCode == null ? other$refCode != null : !this$refCode.equals(other$refCode)) {
            return false;
        }
        String this$bankId = this.getBankId();
        String other$bankId = other.getBankId();
        if (this$bankId == null ? other$bankId != null : !this$bankId.equals(other$bankId)) {
            return false;
        }
        String this$revIndicator = this.getRevIndicator();
        String other$revIndicator = other.getRevIndicator();
        if (this$revIndicator == null ? other$revIndicator != null : !this$revIndicator.equals(other$revIndicator)) {
            return false;
        }
        String this$originalRRN = this.getOriginalRRN();
        String other$originalRRN = other.getOriginalRRN();
        if (this$originalRRN == null ? other$originalRRN != null : !this$originalRRN.equals(other$originalRRN)) {
            return false;
        }
        String this$localDate = this.getLocalDate();
        String other$localDate = other.getLocalDate();
        if (this$localDate == null ? other$localDate != null : !this$localDate.equals(other$localDate)) {
            return false;
        }
        String this$transactionId = this.getTransactionId();
        String other$transactionId = other.getTransactionId();
        if (this$transactionId == null ? other$transactionId != null : !this$transactionId.equals(other$transactionId)) {
            return false;
        }
        String this$traceId = this.getTraceId();
        String other$traceId = other.getTraceId();
        if (this$traceId == null ? other$traceId != null : !this$traceId.equals(other$traceId)) {
            return false;
        }
        String this$interchangeFeeSign = this.getInterchangeFeeSign();
        String other$interchangeFeeSign = other.getInterchangeFeeSign();
        if (this$interchangeFeeSign == null ? other$interchangeFeeSign != null : !this$interchangeFeeSign.equals(other$interchangeFeeSign)) {
            return false;
        }
        String this$feeDescriptor = this.getFeeDescriptor();
        String other$feeDescriptor = other.getFeeDescriptor();
        if (this$feeDescriptor == null ? other$feeDescriptor != null : !this$feeDescriptor.equals(other$feeDescriptor)) {
            return false;
        }
        String this$interchangeMatched = this.getInterchangeMatched();
        String other$interchangeMatched = other.getInterchangeMatched();
        if (this$interchangeMatched == null ? other$interchangeMatched != null : !this$interchangeMatched.equals(other$interchangeMatched)) {
            return false;
        }
        String this$interchangeRateDesignator = this.getInterchangeRateDesignator();
        String other$interchangeRateDesignator = other.getInterchangeRateDesignator();
        if (this$interchangeRateDesignator == null ? other$interchangeRateDesignator != null : !this$interchangeRateDesignator.equals(other$interchangeRateDesignator)) {
            return false;
        }
        String this$processingCode = this.getProcessingCode();
        String other$processingCode = other.getProcessingCode();
        if (this$processingCode == null ? other$processingCode != null : !this$processingCode.equals(other$processingCode)) {
            return false;
        }
        String this$scheme = this.getScheme();
        String other$scheme = other.getScheme();
        if (this$scheme == null ? other$scheme != null : !this$scheme.equals(other$scheme)) {
            return false;
        }
        String this$fileID = this.getFileID();
        String other$fileID = other.getFileID();
        if (this$fileID == null ? other$fileID != null : !this$fileID.equals(other$fileID)) {
            return false;
        }
        String this$businessCycle = this.getBusinessCycle();
        String other$businessCycle = other.getBusinessCycle();
        if (this$businessCycle == null ? other$businessCycle != null : !this$businessCycle.equals(other$businessCycle)) {
            return false;
        }
        String this$approvalCode = this.getApprovalCode();
        String other$approvalCode = other.getApprovalCode();
        if (this$approvalCode == null ? other$approvalCode != null : !this$approvalCode.equals(other$approvalCode)) {
            return false;
        }
        BigDecimal this$dccAmount = this.getDccAmount();
        BigDecimal other$dccAmount = other.getDccAmount();
        if (this$dccAmount == null ? other$dccAmount != null : !((Object)this$dccAmount).equals(other$dccAmount)) {
            return false;
        }
        String this$dccCurrency = this.getDccCurrency();
        String other$dccCurrency = other.getDccCurrency();
        return !(this$dccCurrency == null ? other$dccCurrency != null : !this$dccCurrency.equals(other$dccCurrency));
    }

    protected boolean canEqual(Object other) {
        return other instanceof TxnDetailsResponseVo;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        long $fixed = Double.doubleToLongBits(this.getFixed());
        result = result * 59 + (int)($fixed >>> 32 ^ $fixed);
        long $percentage = Double.doubleToLongBits(this.getPercentage());
        result = result * 59 + (int)($percentage >>> 32 ^ $percentage);
        long $irfAmount = Double.doubleToLongBits(this.getIrfAmount());
        result = result * 59 + (int)($irfAmount >>> 32 ^ $irfAmount);
        long $txnAmount = Double.doubleToLongBits(this.getTxnAmount());
        result = result * 59 + (int)($txnAmount >>> 32 ^ $txnAmount);
        result = result * 59 + this.getTotalCount();
        result = result * 59 + this.getTotalPage();
        long $irfAmountUSD = Double.doubleToLongBits(this.getIrfAmountUSD());
        result = result * 59 + (int)($irfAmountUSD >>> 32 ^ $irfAmountUSD);
        long $interchangeFeeAmountLocal = Double.doubleToLongBits(this.getInterchangeFeeAmountLocal());
        result = result * 59 + (int)($interchangeFeeAmountLocal >>> 32 ^ $interchangeFeeAmountLocal);
        long $interchangeDifferenceAmount = Double.doubleToLongBits(this.getInterchangeDifferenceAmount());
        result = result * 59 + (int)($interchangeDifferenceAmount >>> 32 ^ $interchangeDifferenceAmount);
        long $transactionAmountLocal = Double.doubleToLongBits(this.getTransactionAmountLocal());
        result = result * 59 + (int)($transactionAmountLocal >>> 32 ^ $transactionAmountLocal);
        long $reconciliationAmountUSD = Double.doubleToLongBits(this.getReconciliationAmountUSD());
        result = result * 59 + (int)($reconciliationAmountUSD >>> 32 ^ $reconciliationAmountUSD);
        long $interchangeAmountLocal = Double.doubleToLongBits(this.getInterchangeAmountLocal());
        result = result * 59 + (int)($interchangeAmountLocal >>> 32 ^ $interchangeAmountLocal);
        long $interchangeAmountUSD = Double.doubleToLongBits(this.getInterchangeAmountUSD());
        result = result * 59 + (int)($interchangeAmountUSD >>> 32 ^ $interchangeAmountUSD);
        long $txnAmountLocal = Double.doubleToLongBits(this.getTxnAmountLocal());
        result = result * 59 + (int)($txnAmountLocal >>> 32 ^ $txnAmountLocal);
        long $uaesIrf = Double.doubleToLongBits(this.getUaesIrf());
        result = result * 59 + (int)($uaesIrf >>> 32 ^ $uaesIrf);
        long $uaesPF1 = Double.doubleToLongBits(this.getUaesPF1());
        result = result * 59 + (int)($uaesPF1 >>> 32 ^ $uaesPF1);
        long $uaesPF2 = Double.doubleToLongBits(this.getUaesPF2());
        result = result * 59 + (int)($uaesPF2 >>> 32 ^ $uaesPF2);
        long $exchangeRate = Double.doubleToLongBits(this.getExchangeRate());
        result = result * 59 + (int)($exchangeRate >>> 32 ^ $exchangeRate);
        Character $dccIndicator = this.getDccIndicator();
        result = result * 59 + ($dccIndicator == null ? 43 : ((Object)$dccIndicator).hashCode());
        String $merchantId = this.getMerchantId();
        result = result * 59 + ($merchantId == null ? 43 : $merchantId.hashCode());
        String $terminalId = this.getTerminalId();
        result = result * 59 + ($terminalId == null ? 43 : $terminalId.hashCode());
        String $rrn = this.getRrn();
        result = result * 59 + ($rrn == null ? 43 : $rrn.hashCode());
        String $irdCode = this.getIrdCode();
        result = result * 59 + ($irdCode == null ? 43 : $irdCode.hashCode());
        String $irfMin = this.getIrfMin();
        result = result * 59 + ($irfMin == null ? 43 : $irfMin.hashCode());
        String $irfMax = this.getIrfMax();
        result = result * 59 + ($irfMax == null ? 43 : $irfMax.hashCode());
        String $domIntlFlag = this.getDomIntlFlag();
        result = result * 59 + ($domIntlFlag == null ? 43 : $domIntlFlag.hashCode());
        String $cardType = this.getCardType();
        result = result * 59 + ($cardType == null ? 43 : $cardType.hashCode());
        String $cardNumber = this.getCardNumber();
        result = result * 59 + ($cardNumber == null ? 43 : $cardNumber.hashCode());
        String $txnType = this.getTxnType();
        result = result * 59 + ($txnType == null ? 43 : $txnType.hashCode());
        String $description = this.getDescription();
        result = result * 59 + ($description == null ? 43 : $description.hashCode());
        String $network = this.getNetwork();
        result = result * 59 + ($network == null ? 43 : $network.hashCode());
        String $txnDate = this.getTxnDate();
        result = result * 59 + ($txnDate == null ? 43 : $txnDate.hashCode());
        String $txnStatus = this.getTxnStatus();
        result = result * 59 + ($txnStatus == null ? 43 : $txnStatus.hashCode());
        String $outgoingStatus = this.getOutgoingStatus();
        result = result * 59 + ($outgoingStatus == null ? 43 : $outgoingStatus.hashCode());
        String $incomingStatus = this.getIncomingStatus();
        result = result * 59 + ($incomingStatus == null ? 43 : $incomingStatus.hashCode());
        String $responseCode = this.getResponseCode();
        result = result * 59 + ($responseCode == null ? 43 : $responseCode.hashCode());
        String $mti = this.getMti();
        result = result * 59 + ($mti == null ? 43 : $mti.hashCode());
        String $irfCallbackStatus = this.getIrfCallbackStatus();
        result = result * 59 + ($irfCallbackStatus == null ? 43 : $irfCallbackStatus.hashCode());
        String $refCode = this.getRefCode();
        result = result * 59 + ($refCode == null ? 43 : $refCode.hashCode());
        String $bankId = this.getBankId();
        result = result * 59 + ($bankId == null ? 43 : $bankId.hashCode());
        String $revIndicator = this.getRevIndicator();
        result = result * 59 + ($revIndicator == null ? 43 : $revIndicator.hashCode());
        String $originalRRN = this.getOriginalRRN();
        result = result * 59 + ($originalRRN == null ? 43 : $originalRRN.hashCode());
        String $localDate = this.getLocalDate();
        result = result * 59 + ($localDate == null ? 43 : $localDate.hashCode());
        String $transactionId = this.getTransactionId();
        result = result * 59 + ($transactionId == null ? 43 : $transactionId.hashCode());
        String $traceId = this.getTraceId();
        result = result * 59 + ($traceId == null ? 43 : $traceId.hashCode());
        String $interchangeFeeSign = this.getInterchangeFeeSign();
        result = result * 59 + ($interchangeFeeSign == null ? 43 : $interchangeFeeSign.hashCode());
        String $feeDescriptor = this.getFeeDescriptor();
        result = result * 59 + ($feeDescriptor == null ? 43 : $feeDescriptor.hashCode());
        String $interchangeMatched = this.getInterchangeMatched();
        result = result * 59 + ($interchangeMatched == null ? 43 : $interchangeMatched.hashCode());
        String $interchangeRateDesignator = this.getInterchangeRateDesignator();
        result = result * 59 + ($interchangeRateDesignator == null ? 43 : $interchangeRateDesignator.hashCode());
        String $processingCode = this.getProcessingCode();
        result = result * 59 + ($processingCode == null ? 43 : $processingCode.hashCode());
        String $scheme = this.getScheme();
        result = result * 59 + ($scheme == null ? 43 : $scheme.hashCode());
        String $fileID = this.getFileID();
        result = result * 59 + ($fileID == null ? 43 : $fileID.hashCode());
        String $businessCycle = this.getBusinessCycle();
        result = result * 59 + ($businessCycle == null ? 43 : $businessCycle.hashCode());
        String $approvalCode = this.getApprovalCode();
        result = result * 59 + ($approvalCode == null ? 43 : $approvalCode.hashCode());
        BigDecimal $dccAmount = this.getDccAmount();
        result = result * 59 + ($dccAmount == null ? 43 : ((Object)$dccAmount).hashCode());
        String $dccCurrency = this.getDccCurrency();
        result = result * 59 + ($dccCurrency == null ? 43 : $dccCurrency.hashCode());
        return result;
    }

    public String toString() {
        return "TxnDetailsResponseVo(merchantId=" + this.getMerchantId() + ", terminalId=" + this.getTerminalId() + ", rrn=" + this.getRrn() + ", irdCode=" + this.getIrdCode() + ", fixed=" + this.getFixed() + ", percentage=" + this.getPercentage() + ", irfMin=" + this.getIrfMin() + ", irfMax=" + this.getIrfMax() + ", irfAmount=" + this.getIrfAmount() + ", txnAmount=" + this.getTxnAmount() + ", domIntlFlag=" + this.getDomIntlFlag() + ", cardType=" + this.getCardType() + ", cardNumber=" + this.getCardNumber() + ", txnType=" + this.getTxnType() + ", description=" + this.getDescription() + ", network=" + this.getNetwork() + ", txnDate=" + this.getTxnDate() + ", txnStatus=" + this.getTxnStatus() + ", outgoingStatus=" + this.getOutgoingStatus() + ", incomingStatus=" + this.getIncomingStatus() + ", responseCode=" + this.getResponseCode() + ", mti=" + this.getMti() + ", irfCallbackStatus=" + this.getIrfCallbackStatus() + ", refCode=" + this.getRefCode() + ", totalCount=" + this.getTotalCount() + ", totalPage=" + this.getTotalPage() + ", bankId=" + this.getBankId() + ", revIndicator=" + this.getRevIndicator() + ", irfAmountUSD=" + this.getIrfAmountUSD() + ", originalRRN=" + this.getOriginalRRN() + ", localDate=" + this.getLocalDate() + ", transactionId=" + this.getTransactionId() + ", traceId=" + this.getTraceId() + ", interchangeFeeAmountLocal=" + this.getInterchangeFeeAmountLocal() + ", interchangeFeeSign=" + this.getInterchangeFeeSign() + ", feeDescriptor=" + this.getFeeDescriptor() + ", interchangeMatched=" + this.getInterchangeMatched() + ", interchangeDifferenceAmount=" + this.getInterchangeDifferenceAmount() + ", transactionAmountLocal=" + this.getTransactionAmountLocal() + ", reconciliationAmountUSD=" + this.getReconciliationAmountUSD() + ", interchangeAmountLocal=" + this.getInterchangeAmountLocal() + ", interchangeAmountUSD=" + this.getInterchangeAmountUSD() + ", interchangeRateDesignator=" + this.getInterchangeRateDesignator() + ", processingCode=" + this.getProcessingCode() + ", txnAmountLocal=" + this.getTxnAmountLocal() + ", uaesIrf=" + this.getUaesIrf() + ", uaesPF1=" + this.getUaesPF1() + ", uaesPF2=" + this.getUaesPF2() + ", scheme=" + this.getScheme() + ", fileID=" + this.getFileID() + ", businessCycle=" + this.getBusinessCycle() + ", approvalCode=" + this.getApprovalCode() + ", dccIndicator=" + this.getDccIndicator() + ", dccAmount=" + String.valueOf(this.getDccAmount()) + ", dccCurrency=" + this.getDccCurrency() + ", exchangeRate=" + this.getExchangeRate() + ")";
    }
}

