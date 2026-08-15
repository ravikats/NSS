/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.vo.SchemeFundRejectionDetailsResponseVo
 */
package com.empay.vo;

public class SchemeFundRejectionDetailsResponseVo {
    private int totalCount;
    private int totalPage;
    private String merchantId;
    private String terminalId;
    private String rrn;
    private String cardNumber;
    private String processingCode;
    private double txnAmountLocal;
    private String approvalCode;
    private String rejectedField;
    private String cardType;

    public int getTotalCount() {
        return this.totalCount;
    }

    public int getTotalPage() {
        return this.totalPage;
    }

    public String getMerchantId() {
        return this.merchantId;
    }

    public String getTerminalId() {
        return this.terminalId;
    }

    public String getRrn() {
        return this.rrn;
    }

    public String getCardNumber() {
        return this.cardNumber;
    }

    public String getProcessingCode() {
        return this.processingCode;
    }

    public double getTxnAmountLocal() {
        return this.txnAmountLocal;
    }

    public String getApprovalCode() {
        return this.approvalCode;
    }

    public String getRejectedField() {
        return this.rejectedField;
    }

    public String getCardType() {
        return this.cardType;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
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

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setProcessingCode(String processingCode) {
        this.processingCode = processingCode;
    }

    public void setTxnAmountLocal(double txnAmountLocal) {
        this.txnAmountLocal = txnAmountLocal;
    }

    public void setApprovalCode(String approvalCode) {
        this.approvalCode = approvalCode;
    }

    public void setRejectedField(String rejectedField) {
        this.rejectedField = rejectedField;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof SchemeFundRejectionDetailsResponseVo)) {
            return false;
        }
        SchemeFundRejectionDetailsResponseVo other = (SchemeFundRejectionDetailsResponseVo)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        if (this.getTotalCount() != other.getTotalCount()) {
            return false;
        }
        if (this.getTotalPage() != other.getTotalPage()) {
            return false;
        }
        if (Double.compare(this.getTxnAmountLocal(), other.getTxnAmountLocal()) != 0) {
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
        String this$approvalCode = this.getApprovalCode();
        String other$approvalCode = other.getApprovalCode();
        if (this$approvalCode == null ? other$approvalCode != null : !this$approvalCode.equals(other$approvalCode)) {
            return false;
        }
        String this$rejectedField = this.getRejectedField();
        String other$rejectedField = other.getRejectedField();
        if (this$rejectedField == null ? other$rejectedField != null : !this$rejectedField.equals(other$rejectedField)) {
            return false;
        }
        String this$cardType = this.getCardType();
        String other$cardType = other.getCardType();
        return !(this$cardType == null ? other$cardType != null : !this$cardType.equals(other$cardType));
    }

    protected boolean canEqual(Object other) {
        return other instanceof SchemeFundRejectionDetailsResponseVo;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + this.getTotalCount();
        result = result * 59 + this.getTotalPage();
        long $txnAmountLocal = Double.doubleToLongBits(this.getTxnAmountLocal());
        result = result * 59 + (int)($txnAmountLocal >>> 32 ^ $txnAmountLocal);
        String $merchantId = this.getMerchantId();
        result = result * 59 + ($merchantId == null ? 43 : $merchantId.hashCode());
        String $terminalId = this.getTerminalId();
        result = result * 59 + ($terminalId == null ? 43 : $terminalId.hashCode());
        String $rrn = this.getRrn();
        result = result * 59 + ($rrn == null ? 43 : $rrn.hashCode());
        String $cardNumber = this.getCardNumber();
        result = result * 59 + ($cardNumber == null ? 43 : $cardNumber.hashCode());
        String $processingCode = this.getProcessingCode();
        result = result * 59 + ($processingCode == null ? 43 : $processingCode.hashCode());
        String $approvalCode = this.getApprovalCode();
        result = result * 59 + ($approvalCode == null ? 43 : $approvalCode.hashCode());
        String $rejectedField = this.getRejectedField();
        result = result * 59 + ($rejectedField == null ? 43 : $rejectedField.hashCode());
        String $cardType = this.getCardType();
        result = result * 59 + ($cardType == null ? 43 : $cardType.hashCode());
        return result;
    }

    public String toString() {
        return "SchemeFundRejectionDetailsResponseVo(totalCount=" + this.getTotalCount() + ", totalPage=" + this.getTotalPage() + ", merchantId=" + this.getMerchantId() + ", terminalId=" + this.getTerminalId() + ", rrn=" + this.getRrn() + ", cardNumber=" + this.getCardNumber() + ", processingCode=" + this.getProcessingCode() + ", txnAmountLocal=" + this.getTxnAmountLocal() + ", approvalCode=" + this.getApprovalCode() + ", rejectedField=" + this.getRejectedField() + ", cardType=" + this.getCardType() + ")";
    }
}

