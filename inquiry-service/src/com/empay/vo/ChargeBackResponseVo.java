/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.vo.ChargeBackResponseVo
 */
package com.empay.vo;

public class ChargeBackResponseVo {
    private int totalCount;
    private int totalPage;
    private String network;
    private String cardNumber;
    private double txnAmount;
    private String txnCurrency;
    private String approvalCode;
    private String terminalId;
    private String merchantId;
    private String cardAccepterName;
    private String cardAccepterCity;
    private String cardAccepterCountry;
    private String caseId;
    private String businessDate;
    private String arn;

    public int getTotalCount() {
        return this.totalCount;
    }

    public int getTotalPage() {
        return this.totalPage;
    }

    public String getNetwork() {
        return this.network;
    }

    public String getCardNumber() {
        return this.cardNumber;
    }

    public double getTxnAmount() {
        return this.txnAmount;
    }

    public String getTxnCurrency() {
        return this.txnCurrency;
    }

    public String getApprovalCode() {
        return this.approvalCode;
    }

    public String getTerminalId() {
        return this.terminalId;
    }

    public String getMerchantId() {
        return this.merchantId;
    }

    public String getCardAccepterName() {
        return this.cardAccepterName;
    }

    public String getCardAccepterCity() {
        return this.cardAccepterCity;
    }

    public String getCardAccepterCountry() {
        return this.cardAccepterCountry;
    }

    public String getCaseId() {
        return this.caseId;
    }

    public String getBusinessDate() {
        return this.businessDate;
    }

    public String getArn() {
        return this.arn;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setTxnAmount(double txnAmount) {
        this.txnAmount = txnAmount;
    }

    public void setTxnCurrency(String txnCurrency) {
        this.txnCurrency = txnCurrency;
    }

    public void setApprovalCode(String approvalCode) {
        this.approvalCode = approvalCode;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public void setCardAccepterName(String cardAccepterName) {
        this.cardAccepterName = cardAccepterName;
    }

    public void setCardAccepterCity(String cardAccepterCity) {
        this.cardAccepterCity = cardAccepterCity;
    }

    public void setCardAccepterCountry(String cardAccepterCountry) {
        this.cardAccepterCountry = cardAccepterCountry;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public void setBusinessDate(String businessDate) {
        this.businessDate = businessDate;
    }

    public void setArn(String arn) {
        this.arn = arn;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ChargeBackResponseVo)) {
            return false;
        }
        ChargeBackResponseVo other = (ChargeBackResponseVo)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        if (this.getTotalCount() != other.getTotalCount()) {
            return false;
        }
        if (this.getTotalPage() != other.getTotalPage()) {
            return false;
        }
        if (Double.compare(this.getTxnAmount(), other.getTxnAmount()) != 0) {
            return false;
        }
        String this$network = this.getNetwork();
        String other$network = other.getNetwork();
        if (this$network == null ? other$network != null : !this$network.equals(other$network)) {
            return false;
        }
        String this$cardNumber = this.getCardNumber();
        String other$cardNumber = other.getCardNumber();
        if (this$cardNumber == null ? other$cardNumber != null : !this$cardNumber.equals(other$cardNumber)) {
            return false;
        }
        String this$txnCurrency = this.getTxnCurrency();
        String other$txnCurrency = other.getTxnCurrency();
        if (this$txnCurrency == null ? other$txnCurrency != null : !this$txnCurrency.equals(other$txnCurrency)) {
            return false;
        }
        String this$approvalCode = this.getApprovalCode();
        String other$approvalCode = other.getApprovalCode();
        if (this$approvalCode == null ? other$approvalCode != null : !this$approvalCode.equals(other$approvalCode)) {
            return false;
        }
        String this$terminalId = this.getTerminalId();
        String other$terminalId = other.getTerminalId();
        if (this$terminalId == null ? other$terminalId != null : !this$terminalId.equals(other$terminalId)) {
            return false;
        }
        String this$merchantId = this.getMerchantId();
        String other$merchantId = other.getMerchantId();
        if (this$merchantId == null ? other$merchantId != null : !this$merchantId.equals(other$merchantId)) {
            return false;
        }
        String this$cardAccepterName = this.getCardAccepterName();
        String other$cardAccepterName = other.getCardAccepterName();
        if (this$cardAccepterName == null ? other$cardAccepterName != null : !this$cardAccepterName.equals(other$cardAccepterName)) {
            return false;
        }
        String this$cardAccepterCity = this.getCardAccepterCity();
        String other$cardAccepterCity = other.getCardAccepterCity();
        if (this$cardAccepterCity == null ? other$cardAccepterCity != null : !this$cardAccepterCity.equals(other$cardAccepterCity)) {
            return false;
        }
        String this$cardAccepterCountry = this.getCardAccepterCountry();
        String other$cardAccepterCountry = other.getCardAccepterCountry();
        if (this$cardAccepterCountry == null ? other$cardAccepterCountry != null : !this$cardAccepterCountry.equals(other$cardAccepterCountry)) {
            return false;
        }
        String this$caseId = this.getCaseId();
        String other$caseId = other.getCaseId();
        if (this$caseId == null ? other$caseId != null : !this$caseId.equals(other$caseId)) {
            return false;
        }
        String this$businessDate = this.getBusinessDate();
        String other$businessDate = other.getBusinessDate();
        if (this$businessDate == null ? other$businessDate != null : !this$businessDate.equals(other$businessDate)) {
            return false;
        }
        String this$arn = this.getArn();
        String other$arn = other.getArn();
        return !(this$arn == null ? other$arn != null : !this$arn.equals(other$arn));
    }

    protected boolean canEqual(Object other) {
        return other instanceof ChargeBackResponseVo;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + this.getTotalCount();
        result = result * 59 + this.getTotalPage();
        long $txnAmount = Double.doubleToLongBits(this.getTxnAmount());
        result = result * 59 + (int)($txnAmount >>> 32 ^ $txnAmount);
        String $network = this.getNetwork();
        result = result * 59 + ($network == null ? 43 : $network.hashCode());
        String $cardNumber = this.getCardNumber();
        result = result * 59 + ($cardNumber == null ? 43 : $cardNumber.hashCode());
        String $txnCurrency = this.getTxnCurrency();
        result = result * 59 + ($txnCurrency == null ? 43 : $txnCurrency.hashCode());
        String $approvalCode = this.getApprovalCode();
        result = result * 59 + ($approvalCode == null ? 43 : $approvalCode.hashCode());
        String $terminalId = this.getTerminalId();
        result = result * 59 + ($terminalId == null ? 43 : $terminalId.hashCode());
        String $merchantId = this.getMerchantId();
        result = result * 59 + ($merchantId == null ? 43 : $merchantId.hashCode());
        String $cardAccepterName = this.getCardAccepterName();
        result = result * 59 + ($cardAccepterName == null ? 43 : $cardAccepterName.hashCode());
        String $cardAccepterCity = this.getCardAccepterCity();
        result = result * 59 + ($cardAccepterCity == null ? 43 : $cardAccepterCity.hashCode());
        String $cardAccepterCountry = this.getCardAccepterCountry();
        result = result * 59 + ($cardAccepterCountry == null ? 43 : $cardAccepterCountry.hashCode());
        String $caseId = this.getCaseId();
        result = result * 59 + ($caseId == null ? 43 : $caseId.hashCode());
        String $businessDate = this.getBusinessDate();
        result = result * 59 + ($businessDate == null ? 43 : $businessDate.hashCode());
        String $arn = this.getArn();
        result = result * 59 + ($arn == null ? 43 : $arn.hashCode());
        return result;
    }

    public String toString() {
        return "ChargeBackResponseVo(totalCount=" + this.getTotalCount() + ", totalPage=" + this.getTotalPage() + ", network=" + this.getNetwork() + ", cardNumber=" + this.getCardNumber() + ", txnAmount=" + this.getTxnAmount() + ", txnCurrency=" + this.getTxnCurrency() + ", approvalCode=" + this.getApprovalCode() + ", terminalId=" + this.getTerminalId() + ", merchantId=" + this.getMerchantId() + ", cardAccepterName=" + this.getCardAccepterName() + ", cardAccepterCity=" + this.getCardAccepterCity() + ", cardAccepterCountry=" + this.getCardAccepterCountry() + ", caseId=" + this.getCaseId() + ", businessDate=" + this.getBusinessDate() + ", arn=" + this.getArn() + ")";
    }
}

