/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.vo.VisaOutgoingVo
 */
package com.empay.vo;

public class VisaOutgoingVo {
    private Integer fileSequence;
    private Integer txnCount91;
    private Integer tcrCount91;
    private Double totalTxnAmt91;
    private Integer txnCount92;
    private Integer tcrCount92;
    private Integer allTcrCount92;
    private Double totalTxnAmt92;
    private Integer batchNumber;

    public Integer getFileSequence() {
        return this.fileSequence;
    }

    public Integer getTxnCount91() {
        return this.txnCount91;
    }

    public Integer getTcrCount91() {
        return this.tcrCount91;
    }

    public Double getTotalTxnAmt91() {
        return this.totalTxnAmt91;
    }

    public Integer getTxnCount92() {
        return this.txnCount92;
    }

    public Integer getTcrCount92() {
        return this.tcrCount92;
    }

    public Integer getAllTcrCount92() {
        return this.allTcrCount92;
    }

    public Double getTotalTxnAmt92() {
        return this.totalTxnAmt92;
    }

    public Integer getBatchNumber() {
        return this.batchNumber;
    }

    public void setFileSequence(Integer fileSequence) {
        this.fileSequence = fileSequence;
    }

    public void setTxnCount91(Integer txnCount91) {
        this.txnCount91 = txnCount91;
    }

    public void setTcrCount91(Integer tcrCount91) {
        this.tcrCount91 = tcrCount91;
    }

    public void setTotalTxnAmt91(Double totalTxnAmt91) {
        this.totalTxnAmt91 = totalTxnAmt91;
    }

    public void setTxnCount92(Integer txnCount92) {
        this.txnCount92 = txnCount92;
    }

    public void setTcrCount92(Integer tcrCount92) {
        this.tcrCount92 = tcrCount92;
    }

    public void setAllTcrCount92(Integer allTcrCount92) {
        this.allTcrCount92 = allTcrCount92;
    }

    public void setTotalTxnAmt92(Double totalTxnAmt92) {
        this.totalTxnAmt92 = totalTxnAmt92;
    }

    public void setBatchNumber(Integer batchNumber) {
        this.batchNumber = batchNumber;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof VisaOutgoingVo)) {
            return false;
        }
        VisaOutgoingVo other = (VisaOutgoingVo)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        Integer this$fileSequence = this.getFileSequence();
        Integer other$fileSequence = other.getFileSequence();
        if (this$fileSequence == null ? other$fileSequence != null : !((Object)this$fileSequence).equals(other$fileSequence)) {
            return false;
        }
        Integer this$txnCount91 = this.getTxnCount91();
        Integer other$txnCount91 = other.getTxnCount91();
        if (this$txnCount91 == null ? other$txnCount91 != null : !((Object)this$txnCount91).equals(other$txnCount91)) {
            return false;
        }
        Integer this$tcrCount91 = this.getTcrCount91();
        Integer other$tcrCount91 = other.getTcrCount91();
        if (this$tcrCount91 == null ? other$tcrCount91 != null : !((Object)this$tcrCount91).equals(other$tcrCount91)) {
            return false;
        }
        Double this$totalTxnAmt91 = this.getTotalTxnAmt91();
        Double other$totalTxnAmt91 = other.getTotalTxnAmt91();
        if (this$totalTxnAmt91 == null ? other$totalTxnAmt91 != null : !((Object)this$totalTxnAmt91).equals(other$totalTxnAmt91)) {
            return false;
        }
        Integer this$txnCount92 = this.getTxnCount92();
        Integer other$txnCount92 = other.getTxnCount92();
        if (this$txnCount92 == null ? other$txnCount92 != null : !((Object)this$txnCount92).equals(other$txnCount92)) {
            return false;
        }
        Integer this$tcrCount92 = this.getTcrCount92();
        Integer other$tcrCount92 = other.getTcrCount92();
        if (this$tcrCount92 == null ? other$tcrCount92 != null : !((Object)this$tcrCount92).equals(other$tcrCount92)) {
            return false;
        }
        Integer this$allTcrCount92 = this.getAllTcrCount92();
        Integer other$allTcrCount92 = other.getAllTcrCount92();
        if (this$allTcrCount92 == null ? other$allTcrCount92 != null : !((Object)this$allTcrCount92).equals(other$allTcrCount92)) {
            return false;
        }
        Double this$totalTxnAmt92 = this.getTotalTxnAmt92();
        Double other$totalTxnAmt92 = other.getTotalTxnAmt92();
        if (this$totalTxnAmt92 == null ? other$totalTxnAmt92 != null : !((Object)this$totalTxnAmt92).equals(other$totalTxnAmt92)) {
            return false;
        }
        Integer this$batchNumber = this.getBatchNumber();
        Integer other$batchNumber = other.getBatchNumber();
        return !(this$batchNumber == null ? other$batchNumber != null : !((Object)this$batchNumber).equals(other$batchNumber));
    }

    protected boolean canEqual(Object other) {
        return other instanceof VisaOutgoingVo;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Integer $fileSequence = this.getFileSequence();
        result = result * 59 + ($fileSequence == null ? 43 : ((Object)$fileSequence).hashCode());
        Integer $txnCount91 = this.getTxnCount91();
        result = result * 59 + ($txnCount91 == null ? 43 : ((Object)$txnCount91).hashCode());
        Integer $tcrCount91 = this.getTcrCount91();
        result = result * 59 + ($tcrCount91 == null ? 43 : ((Object)$tcrCount91).hashCode());
        Double $totalTxnAmt91 = this.getTotalTxnAmt91();
        result = result * 59 + ($totalTxnAmt91 == null ? 43 : ((Object)$totalTxnAmt91).hashCode());
        Integer $txnCount92 = this.getTxnCount92();
        result = result * 59 + ($txnCount92 == null ? 43 : ((Object)$txnCount92).hashCode());
        Integer $tcrCount92 = this.getTcrCount92();
        result = result * 59 + ($tcrCount92 == null ? 43 : ((Object)$tcrCount92).hashCode());
        Integer $allTcrCount92 = this.getAllTcrCount92();
        result = result * 59 + ($allTcrCount92 == null ? 43 : ((Object)$allTcrCount92).hashCode());
        Double $totalTxnAmt92 = this.getTotalTxnAmt92();
        result = result * 59 + ($totalTxnAmt92 == null ? 43 : ((Object)$totalTxnAmt92).hashCode());
        Integer $batchNumber = this.getBatchNumber();
        result = result * 59 + ($batchNumber == null ? 43 : ((Object)$batchNumber).hashCode());
        return result;
    }

    public String toString() {
        return "VisaOutgoingVo(fileSequence=" + this.getFileSequence() + ", txnCount91=" + this.getTxnCount91() + ", tcrCount91=" + this.getTcrCount91() + ", totalTxnAmt91=" + this.getTotalTxnAmt91() + ", txnCount92=" + this.getTxnCount92() + ", tcrCount92=" + this.getTcrCount92() + ", allTcrCount92=" + this.getAllTcrCount92() + ", totalTxnAmt92=" + this.getTotalTxnAmt92() + ", batchNumber=" + this.getBatchNumber() + ")";
    }
}

