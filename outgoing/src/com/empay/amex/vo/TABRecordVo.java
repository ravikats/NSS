/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.amex.vo.TABRecordVo
 */
package com.empay.amex.vo;

public class TABRecordVo {
    private String recordType;
    private String recordNumber;
    private String transactionIdentifier;
    private String formatCode;
    private String mediaCode;
    private String submissionMethod;
    private String reserved1;
    private String approvalCode;
    private String primaryAccountNumber;
    private String cardExpiryDate;
    private String transactionDate;
    private String transactionTime;
    private String reserved2;
    private String transactionAmount;
    private String processingCode;
    private String transactionCurrCode;
    private String extendedPaymentData;
    private String merchantId;
    private String merchantLocationId;
    private String merchantContactInfo;
    private String terminalId;
    private String pointOfServiceDataCode;
    private String reserved3;
    private String reserved4;
    private String reserved5;
    private String invoiceRefNumber;
    private String reserved6;
    private String tabImageSeqNumber;
    private String matchingKeyType;
    private String matchingKey;
    private String ecomIndicator;
    private String reserved7;

    public String getTAB() {
        return this.recordType + this.recordNumber + this.transactionIdentifier + this.formatCode + this.mediaCode + this.submissionMethod + this.reserved1 + this.approvalCode + this.primaryAccountNumber + this.cardExpiryDate + this.transactionDate + this.transactionTime + this.reserved2 + this.transactionAmount + this.processingCode + this.transactionCurrCode + this.extendedPaymentData + this.merchantId + this.merchantLocationId + this.merchantContactInfo + this.terminalId + this.pointOfServiceDataCode + this.reserved3 + this.reserved4 + this.reserved5 + this.invoiceRefNumber + this.reserved6 + this.tabImageSeqNumber + this.matchingKeyType + this.matchingKey + this.ecomIndicator + this.reserved7;
    }

    public String getRecordType() {
        return this.recordType;
    }

    public String getRecordNumber() {
        return this.recordNumber;
    }

    public String getTransactionIdentifier() {
        return this.transactionIdentifier;
    }

    public String getFormatCode() {
        return this.formatCode;
    }

    public String getMediaCode() {
        return this.mediaCode;
    }

    public String getSubmissionMethod() {
        return this.submissionMethod;
    }

    public String getReserved1() {
        return this.reserved1;
    }

    public String getApprovalCode() {
        return this.approvalCode;
    }

    public String getPrimaryAccountNumber() {
        return this.primaryAccountNumber;
    }

    public String getCardExpiryDate() {
        return this.cardExpiryDate;
    }

    public String getTransactionDate() {
        return this.transactionDate;
    }

    public String getTransactionTime() {
        return this.transactionTime;
    }

    public String getReserved2() {
        return this.reserved2;
    }

    public String getTransactionAmount() {
        return this.transactionAmount;
    }

    public String getProcessingCode() {
        return this.processingCode;
    }

    public String getTransactionCurrCode() {
        return this.transactionCurrCode;
    }

    public String getExtendedPaymentData() {
        return this.extendedPaymentData;
    }

    public String getMerchantId() {
        return this.merchantId;
    }

    public String getMerchantLocationId() {
        return this.merchantLocationId;
    }

    public String getMerchantContactInfo() {
        return this.merchantContactInfo;
    }

    public String getTerminalId() {
        return this.terminalId;
    }

    public String getPointOfServiceDataCode() {
        return this.pointOfServiceDataCode;
    }

    public String getReserved3() {
        return this.reserved3;
    }

    public String getReserved4() {
        return this.reserved4;
    }

    public String getReserved5() {
        return this.reserved5;
    }

    public String getInvoiceRefNumber() {
        return this.invoiceRefNumber;
    }

    public String getReserved6() {
        return this.reserved6;
    }

    public String getTabImageSeqNumber() {
        return this.tabImageSeqNumber;
    }

    public String getMatchingKeyType() {
        return this.matchingKeyType;
    }

    public String getMatchingKey() {
        return this.matchingKey;
    }

    public String getEcomIndicator() {
        return this.ecomIndicator;
    }

    public String getReserved7() {
        return this.reserved7;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    public void setRecordNumber(String recordNumber) {
        this.recordNumber = recordNumber;
    }

    public void setTransactionIdentifier(String transactionIdentifier) {
        this.transactionIdentifier = transactionIdentifier;
    }

    public void setFormatCode(String formatCode) {
        this.formatCode = formatCode;
    }

    public void setMediaCode(String mediaCode) {
        this.mediaCode = mediaCode;
    }

    public void setSubmissionMethod(String submissionMethod) {
        this.submissionMethod = submissionMethod;
    }

    public void setReserved1(String reserved1) {
        this.reserved1 = reserved1;
    }

    public void setApprovalCode(String approvalCode) {
        this.approvalCode = approvalCode;
    }

    public void setPrimaryAccountNumber(String primaryAccountNumber) {
        this.primaryAccountNumber = primaryAccountNumber;
    }

    public void setCardExpiryDate(String cardExpiryDate) {
        this.cardExpiryDate = cardExpiryDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public void setTransactionTime(String transactionTime) {
        this.transactionTime = transactionTime;
    }

    public void setReserved2(String reserved2) {
        this.reserved2 = reserved2;
    }

    public void setTransactionAmount(String transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public void setProcessingCode(String processingCode) {
        this.processingCode = processingCode;
    }

    public void setTransactionCurrCode(String transactionCurrCode) {
        this.transactionCurrCode = transactionCurrCode;
    }

    public void setExtendedPaymentData(String extendedPaymentData) {
        this.extendedPaymentData = extendedPaymentData;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public void setMerchantLocationId(String merchantLocationId) {
        this.merchantLocationId = merchantLocationId;
    }

    public void setMerchantContactInfo(String merchantContactInfo) {
        this.merchantContactInfo = merchantContactInfo;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public void setPointOfServiceDataCode(String pointOfServiceDataCode) {
        this.pointOfServiceDataCode = pointOfServiceDataCode;
    }

    public void setReserved3(String reserved3) {
        this.reserved3 = reserved3;
    }

    public void setReserved4(String reserved4) {
        this.reserved4 = reserved4;
    }

    public void setReserved5(String reserved5) {
        this.reserved5 = reserved5;
    }

    public void setInvoiceRefNumber(String invoiceRefNumber) {
        this.invoiceRefNumber = invoiceRefNumber;
    }

    public void setReserved6(String reserved6) {
        this.reserved6 = reserved6;
    }

    public void setTabImageSeqNumber(String tabImageSeqNumber) {
        this.tabImageSeqNumber = tabImageSeqNumber;
    }

    public void setMatchingKeyType(String matchingKeyType) {
        this.matchingKeyType = matchingKeyType;
    }

    public void setMatchingKey(String matchingKey) {
        this.matchingKey = matchingKey;
    }

    public void setEcomIndicator(String ecomIndicator) {
        this.ecomIndicator = ecomIndicator;
    }

    public void setReserved7(String reserved7) {
        this.reserved7 = reserved7;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof TABRecordVo)) {
            return false;
        }
        TABRecordVo other = (TABRecordVo)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        String this$recordType = this.getRecordType();
        String other$recordType = other.getRecordType();
        if (this$recordType == null ? other$recordType != null : !this$recordType.equals(other$recordType)) {
            return false;
        }
        String this$recordNumber = this.getRecordNumber();
        String other$recordNumber = other.getRecordNumber();
        if (this$recordNumber == null ? other$recordNumber != null : !this$recordNumber.equals(other$recordNumber)) {
            return false;
        }
        String this$transactionIdentifier = this.getTransactionIdentifier();
        String other$transactionIdentifier = other.getTransactionIdentifier();
        if (this$transactionIdentifier == null ? other$transactionIdentifier != null : !this$transactionIdentifier.equals(other$transactionIdentifier)) {
            return false;
        }
        String this$formatCode = this.getFormatCode();
        String other$formatCode = other.getFormatCode();
        if (this$formatCode == null ? other$formatCode != null : !this$formatCode.equals(other$formatCode)) {
            return false;
        }
        String this$mediaCode = this.getMediaCode();
        String other$mediaCode = other.getMediaCode();
        if (this$mediaCode == null ? other$mediaCode != null : !this$mediaCode.equals(other$mediaCode)) {
            return false;
        }
        String this$submissionMethod = this.getSubmissionMethod();
        String other$submissionMethod = other.getSubmissionMethod();
        if (this$submissionMethod == null ? other$submissionMethod != null : !this$submissionMethod.equals(other$submissionMethod)) {
            return false;
        }
        String this$reserved1 = this.getReserved1();
        String other$reserved1 = other.getReserved1();
        if (this$reserved1 == null ? other$reserved1 != null : !this$reserved1.equals(other$reserved1)) {
            return false;
        }
        String this$approvalCode = this.getApprovalCode();
        String other$approvalCode = other.getApprovalCode();
        if (this$approvalCode == null ? other$approvalCode != null : !this$approvalCode.equals(other$approvalCode)) {
            return false;
        }
        String this$primaryAccountNumber = this.getPrimaryAccountNumber();
        String other$primaryAccountNumber = other.getPrimaryAccountNumber();
        if (this$primaryAccountNumber == null ? other$primaryAccountNumber != null : !this$primaryAccountNumber.equals(other$primaryAccountNumber)) {
            return false;
        }
        String this$cardExpiryDate = this.getCardExpiryDate();
        String other$cardExpiryDate = other.getCardExpiryDate();
        if (this$cardExpiryDate == null ? other$cardExpiryDate != null : !this$cardExpiryDate.equals(other$cardExpiryDate)) {
            return false;
        }
        String this$transactionDate = this.getTransactionDate();
        String other$transactionDate = other.getTransactionDate();
        if (this$transactionDate == null ? other$transactionDate != null : !this$transactionDate.equals(other$transactionDate)) {
            return false;
        }
        String this$transactionTime = this.getTransactionTime();
        String other$transactionTime = other.getTransactionTime();
        if (this$transactionTime == null ? other$transactionTime != null : !this$transactionTime.equals(other$transactionTime)) {
            return false;
        }
        String this$reserved2 = this.getReserved2();
        String other$reserved2 = other.getReserved2();
        if (this$reserved2 == null ? other$reserved2 != null : !this$reserved2.equals(other$reserved2)) {
            return false;
        }
        String this$transactionAmount = this.getTransactionAmount();
        String other$transactionAmount = other.getTransactionAmount();
        if (this$transactionAmount == null ? other$transactionAmount != null : !this$transactionAmount.equals(other$transactionAmount)) {
            return false;
        }
        String this$processingCode = this.getProcessingCode();
        String other$processingCode = other.getProcessingCode();
        if (this$processingCode == null ? other$processingCode != null : !this$processingCode.equals(other$processingCode)) {
            return false;
        }
        String this$transactionCurrCode = this.getTransactionCurrCode();
        String other$transactionCurrCode = other.getTransactionCurrCode();
        if (this$transactionCurrCode == null ? other$transactionCurrCode != null : !this$transactionCurrCode.equals(other$transactionCurrCode)) {
            return false;
        }
        String this$extendedPaymentData = this.getExtendedPaymentData();
        String other$extendedPaymentData = other.getExtendedPaymentData();
        if (this$extendedPaymentData == null ? other$extendedPaymentData != null : !this$extendedPaymentData.equals(other$extendedPaymentData)) {
            return false;
        }
        String this$merchantId = this.getMerchantId();
        String other$merchantId = other.getMerchantId();
        if (this$merchantId == null ? other$merchantId != null : !this$merchantId.equals(other$merchantId)) {
            return false;
        }
        String this$merchantLocationId = this.getMerchantLocationId();
        String other$merchantLocationId = other.getMerchantLocationId();
        if (this$merchantLocationId == null ? other$merchantLocationId != null : !this$merchantLocationId.equals(other$merchantLocationId)) {
            return false;
        }
        String this$merchantContactInfo = this.getMerchantContactInfo();
        String other$merchantContactInfo = other.getMerchantContactInfo();
        if (this$merchantContactInfo == null ? other$merchantContactInfo != null : !this$merchantContactInfo.equals(other$merchantContactInfo)) {
            return false;
        }
        String this$terminalId = this.getTerminalId();
        String other$terminalId = other.getTerminalId();
        if (this$terminalId == null ? other$terminalId != null : !this$terminalId.equals(other$terminalId)) {
            return false;
        }
        String this$pointOfServiceDataCode = this.getPointOfServiceDataCode();
        String other$pointOfServiceDataCode = other.getPointOfServiceDataCode();
        if (this$pointOfServiceDataCode == null ? other$pointOfServiceDataCode != null : !this$pointOfServiceDataCode.equals(other$pointOfServiceDataCode)) {
            return false;
        }
        String this$reserved3 = this.getReserved3();
        String other$reserved3 = other.getReserved3();
        if (this$reserved3 == null ? other$reserved3 != null : !this$reserved3.equals(other$reserved3)) {
            return false;
        }
        String this$reserved4 = this.getReserved4();
        String other$reserved4 = other.getReserved4();
        if (this$reserved4 == null ? other$reserved4 != null : !this$reserved4.equals(other$reserved4)) {
            return false;
        }
        String this$reserved5 = this.getReserved5();
        String other$reserved5 = other.getReserved5();
        if (this$reserved5 == null ? other$reserved5 != null : !this$reserved5.equals(other$reserved5)) {
            return false;
        }
        String this$invoiceRefNumber = this.getInvoiceRefNumber();
        String other$invoiceRefNumber = other.getInvoiceRefNumber();
        if (this$invoiceRefNumber == null ? other$invoiceRefNumber != null : !this$invoiceRefNumber.equals(other$invoiceRefNumber)) {
            return false;
        }
        String this$reserved6 = this.getReserved6();
        String other$reserved6 = other.getReserved6();
        if (this$reserved6 == null ? other$reserved6 != null : !this$reserved6.equals(other$reserved6)) {
            return false;
        }
        String this$tabImageSeqNumber = this.getTabImageSeqNumber();
        String other$tabImageSeqNumber = other.getTabImageSeqNumber();
        if (this$tabImageSeqNumber == null ? other$tabImageSeqNumber != null : !this$tabImageSeqNumber.equals(other$tabImageSeqNumber)) {
            return false;
        }
        String this$matchingKeyType = this.getMatchingKeyType();
        String other$matchingKeyType = other.getMatchingKeyType();
        if (this$matchingKeyType == null ? other$matchingKeyType != null : !this$matchingKeyType.equals(other$matchingKeyType)) {
            return false;
        }
        String this$matchingKey = this.getMatchingKey();
        String other$matchingKey = other.getMatchingKey();
        if (this$matchingKey == null ? other$matchingKey != null : !this$matchingKey.equals(other$matchingKey)) {
            return false;
        }
        String this$ecomIndicator = this.getEcomIndicator();
        String other$ecomIndicator = other.getEcomIndicator();
        if (this$ecomIndicator == null ? other$ecomIndicator != null : !this$ecomIndicator.equals(other$ecomIndicator)) {
            return false;
        }
        String this$reserved7 = this.getReserved7();
        String other$reserved7 = other.getReserved7();
        return !(this$reserved7 == null ? other$reserved7 != null : !this$reserved7.equals(other$reserved7));
    }

    protected boolean canEqual(Object other) {
        return other instanceof TABRecordVo;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        String $recordType = this.getRecordType();
        result = result * 59 + ($recordType == null ? 43 : $recordType.hashCode());
        String $recordNumber = this.getRecordNumber();
        result = result * 59 + ($recordNumber == null ? 43 : $recordNumber.hashCode());
        String $transactionIdentifier = this.getTransactionIdentifier();
        result = result * 59 + ($transactionIdentifier == null ? 43 : $transactionIdentifier.hashCode());
        String $formatCode = this.getFormatCode();
        result = result * 59 + ($formatCode == null ? 43 : $formatCode.hashCode());
        String $mediaCode = this.getMediaCode();
        result = result * 59 + ($mediaCode == null ? 43 : $mediaCode.hashCode());
        String $submissionMethod = this.getSubmissionMethod();
        result = result * 59 + ($submissionMethod == null ? 43 : $submissionMethod.hashCode());
        String $reserved1 = this.getReserved1();
        result = result * 59 + ($reserved1 == null ? 43 : $reserved1.hashCode());
        String $approvalCode = this.getApprovalCode();
        result = result * 59 + ($approvalCode == null ? 43 : $approvalCode.hashCode());
        String $primaryAccountNumber = this.getPrimaryAccountNumber();
        result = result * 59 + ($primaryAccountNumber == null ? 43 : $primaryAccountNumber.hashCode());
        String $cardExpiryDate = this.getCardExpiryDate();
        result = result * 59 + ($cardExpiryDate == null ? 43 : $cardExpiryDate.hashCode());
        String $transactionDate = this.getTransactionDate();
        result = result * 59 + ($transactionDate == null ? 43 : $transactionDate.hashCode());
        String $transactionTime = this.getTransactionTime();
        result = result * 59 + ($transactionTime == null ? 43 : $transactionTime.hashCode());
        String $reserved2 = this.getReserved2();
        result = result * 59 + ($reserved2 == null ? 43 : $reserved2.hashCode());
        String $transactionAmount = this.getTransactionAmount();
        result = result * 59 + ($transactionAmount == null ? 43 : $transactionAmount.hashCode());
        String $processingCode = this.getProcessingCode();
        result = result * 59 + ($processingCode == null ? 43 : $processingCode.hashCode());
        String $transactionCurrCode = this.getTransactionCurrCode();
        result = result * 59 + ($transactionCurrCode == null ? 43 : $transactionCurrCode.hashCode());
        String $extendedPaymentData = this.getExtendedPaymentData();
        result = result * 59 + ($extendedPaymentData == null ? 43 : $extendedPaymentData.hashCode());
        String $merchantId = this.getMerchantId();
        result = result * 59 + ($merchantId == null ? 43 : $merchantId.hashCode());
        String $merchantLocationId = this.getMerchantLocationId();
        result = result * 59 + ($merchantLocationId == null ? 43 : $merchantLocationId.hashCode());
        String $merchantContactInfo = this.getMerchantContactInfo();
        result = result * 59 + ($merchantContactInfo == null ? 43 : $merchantContactInfo.hashCode());
        String $terminalId = this.getTerminalId();
        result = result * 59 + ($terminalId == null ? 43 : $terminalId.hashCode());
        String $pointOfServiceDataCode = this.getPointOfServiceDataCode();
        result = result * 59 + ($pointOfServiceDataCode == null ? 43 : $pointOfServiceDataCode.hashCode());
        String $reserved3 = this.getReserved3();
        result = result * 59 + ($reserved3 == null ? 43 : $reserved3.hashCode());
        String $reserved4 = this.getReserved4();
        result = result * 59 + ($reserved4 == null ? 43 : $reserved4.hashCode());
        String $reserved5 = this.getReserved5();
        result = result * 59 + ($reserved5 == null ? 43 : $reserved5.hashCode());
        String $invoiceRefNumber = this.getInvoiceRefNumber();
        result = result * 59 + ($invoiceRefNumber == null ? 43 : $invoiceRefNumber.hashCode());
        String $reserved6 = this.getReserved6();
        result = result * 59 + ($reserved6 == null ? 43 : $reserved6.hashCode());
        String $tabImageSeqNumber = this.getTabImageSeqNumber();
        result = result * 59 + ($tabImageSeqNumber == null ? 43 : $tabImageSeqNumber.hashCode());
        String $matchingKeyType = this.getMatchingKeyType();
        result = result * 59 + ($matchingKeyType == null ? 43 : $matchingKeyType.hashCode());
        String $matchingKey = this.getMatchingKey();
        result = result * 59 + ($matchingKey == null ? 43 : $matchingKey.hashCode());
        String $ecomIndicator = this.getEcomIndicator();
        result = result * 59 + ($ecomIndicator == null ? 43 : $ecomIndicator.hashCode());
        String $reserved7 = this.getReserved7();
        result = result * 59 + ($reserved7 == null ? 43 : $reserved7.hashCode());
        return result;
    }

    public String toString() {
        return "TABRecordVo(recordType=" + this.getRecordType() + ", recordNumber=" + this.getRecordNumber() + ", transactionIdentifier=" + this.getTransactionIdentifier() + ", formatCode=" + this.getFormatCode() + ", mediaCode=" + this.getMediaCode() + ", submissionMethod=" + this.getSubmissionMethod() + ", reserved1=" + this.getReserved1() + ", approvalCode=" + this.getApprovalCode() + ", primaryAccountNumber=" + this.getPrimaryAccountNumber() + ", cardExpiryDate=" + this.getCardExpiryDate() + ", transactionDate=" + this.getTransactionDate() + ", transactionTime=" + this.getTransactionTime() + ", reserved2=" + this.getReserved2() + ", transactionAmount=" + this.getTransactionAmount() + ", processingCode=" + this.getProcessingCode() + ", transactionCurrCode=" + this.getTransactionCurrCode() + ", extendedPaymentData=" + this.getExtendedPaymentData() + ", merchantId=" + this.getMerchantId() + ", merchantLocationId=" + this.getMerchantLocationId() + ", merchantContactInfo=" + this.getMerchantContactInfo() + ", terminalId=" + this.getTerminalId() + ", pointOfServiceDataCode=" + this.getPointOfServiceDataCode() + ", reserved3=" + this.getReserved3() + ", reserved4=" + this.getReserved4() + ", reserved5=" + this.getReserved5() + ", invoiceRefNumber=" + this.getInvoiceRefNumber() + ", reserved6=" + this.getReserved6() + ", tabImageSeqNumber=" + this.getTabImageSeqNumber() + ", matchingKeyType=" + this.getMatchingKeyType() + ", matchingKey=" + this.getMatchingKey() + ", ecomIndicator=" + this.getEcomIndicator() + ", reserved7=" + this.getReserved7() + ")";
    }
}

