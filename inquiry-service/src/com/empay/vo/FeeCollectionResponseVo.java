/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.vo.FeeCollectionResponseVo
 */
package com.empay.vo;

public class FeeCollectionResponseVo {
    private int totalCount;
    private int totalPage;
    private String icaNumber;
    private String fileId;
    private String processCode;
    private double txnAmount;
    private String txnCurrency;
    private double reconAmount;
    private String memberText;

    public int getTotalCount() {
        return this.totalCount;
    }

    public int getTotalPage() {
        return this.totalPage;
    }

    public String getIcaNumber() {
        return this.icaNumber;
    }

    public String getFileId() {
        return this.fileId;
    }

    public String getProcessCode() {
        return this.processCode;
    }

    public double getTxnAmount() {
        return this.txnAmount;
    }

    public String getTxnCurrency() {
        return this.txnCurrency;
    }

    public double getReconAmount() {
        return this.reconAmount;
    }

    public String getMemberText() {
        return this.memberText;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public void setIcaNumber(String icaNumber) {
        this.icaNumber = icaNumber;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public void setProcessCode(String processCode) {
        this.processCode = processCode;
    }

    public void setTxnAmount(double txnAmount) {
        this.txnAmount = txnAmount;
    }

    public void setTxnCurrency(String txnCurrency) {
        this.txnCurrency = txnCurrency;
    }

    public void setReconAmount(double reconAmount) {
        this.reconAmount = reconAmount;
    }

    public void setMemberText(String memberText) {
        this.memberText = memberText;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof FeeCollectionResponseVo)) {
            return false;
        }
        FeeCollectionResponseVo other = (FeeCollectionResponseVo)o;
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
        if (Double.compare(this.getReconAmount(), other.getReconAmount()) != 0) {
            return false;
        }
        String this$icaNumber = this.getIcaNumber();
        String other$icaNumber = other.getIcaNumber();
        if (this$icaNumber == null ? other$icaNumber != null : !this$icaNumber.equals(other$icaNumber)) {
            return false;
        }
        String this$fileId = this.getFileId();
        String other$fileId = other.getFileId();
        if (this$fileId == null ? other$fileId != null : !this$fileId.equals(other$fileId)) {
            return false;
        }
        String this$processCode = this.getProcessCode();
        String other$processCode = other.getProcessCode();
        if (this$processCode == null ? other$processCode != null : !this$processCode.equals(other$processCode)) {
            return false;
        }
        String this$txnCurrency = this.getTxnCurrency();
        String other$txnCurrency = other.getTxnCurrency();
        if (this$txnCurrency == null ? other$txnCurrency != null : !this$txnCurrency.equals(other$txnCurrency)) {
            return false;
        }
        String this$memberText = this.getMemberText();
        String other$memberText = other.getMemberText();
        return !(this$memberText == null ? other$memberText != null : !this$memberText.equals(other$memberText));
    }

    protected boolean canEqual(Object other) {
        return other instanceof FeeCollectionResponseVo;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + this.getTotalCount();
        result = result * 59 + this.getTotalPage();
        long $txnAmount = Double.doubleToLongBits(this.getTxnAmount());
        result = result * 59 + (int)($txnAmount >>> 32 ^ $txnAmount);
        long $reconAmount = Double.doubleToLongBits(this.getReconAmount());
        result = result * 59 + (int)($reconAmount >>> 32 ^ $reconAmount);
        String $icaNumber = this.getIcaNumber();
        result = result * 59 + ($icaNumber == null ? 43 : $icaNumber.hashCode());
        String $fileId = this.getFileId();
        result = result * 59 + ($fileId == null ? 43 : $fileId.hashCode());
        String $processCode = this.getProcessCode();
        result = result * 59 + ($processCode == null ? 43 : $processCode.hashCode());
        String $txnCurrency = this.getTxnCurrency();
        result = result * 59 + ($txnCurrency == null ? 43 : $txnCurrency.hashCode());
        String $memberText = this.getMemberText();
        result = result * 59 + ($memberText == null ? 43 : $memberText.hashCode());
        return result;
    }

    public String toString() {
        return "FeeCollectionResponseVo(totalCount=" + this.getTotalCount() + ", totalPage=" + this.getTotalPage() + ", icaNumber=" + this.getIcaNumber() + ", fileId=" + this.getFileId() + ", processCode=" + this.getProcessCode() + ", txnAmount=" + this.getTxnAmount() + ", txnCurrency=" + this.getTxnCurrency() + ", reconAmount=" + this.getReconAmount() + ", memberText=" + this.getMemberText() + ")";
    }
}

