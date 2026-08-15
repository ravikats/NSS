/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.entities.ViewMCNetReconId
 */
package com.empay.entities;

import java.io.Serializable;
import java.time.LocalDate;

public class ViewMCNetReconId
implements Serializable {
    private LocalDate businessDate;
    private String txnType;

    public LocalDate getBusinessDate() {
        return this.businessDate;
    }

    public String getTxnType() {
        return this.txnType;
    }

    public void setBusinessDate(LocalDate businessDate) {
        this.businessDate = businessDate;
    }

    public void setTxnType(String txnType) {
        this.txnType = txnType;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ViewMCNetReconId)) {
            return false;
        }
        ViewMCNetReconId other = (ViewMCNetReconId)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        LocalDate this$businessDate = this.getBusinessDate();
        LocalDate other$businessDate = other.getBusinessDate();
        if (this$businessDate == null ? other$businessDate != null : !((Object)this$businessDate).equals(other$businessDate)) {
            return false;
        }
        String this$txnType = this.getTxnType();
        String other$txnType = other.getTxnType();
        return !(this$txnType == null ? other$txnType != null : !this$txnType.equals(other$txnType));
    }

    protected boolean canEqual(Object other) {
        return other instanceof ViewMCNetReconId;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        LocalDate $businessDate = this.getBusinessDate();
        result = result * 59 + ($businessDate == null ? 43 : ((Object)$businessDate).hashCode());
        String $txnType = this.getTxnType();
        result = result * 59 + ($txnType == null ? 43 : $txnType.hashCode());
        return result;
    }

    public String toString() {
        return "ViewMCNetReconId(businessDate=" + String.valueOf(this.getBusinessDate()) + ", txnType=" + this.getTxnType() + ")";
    }

    public ViewMCNetReconId() {
    }

    public ViewMCNetReconId(LocalDate businessDate, String txnType) {
        this.businessDate = businessDate;
        this.txnType = txnType;
    }
}

