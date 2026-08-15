/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.vo.InquiryRequestVo
 *  jakarta.validation.constraints.Pattern
 *  jakarta.validation.constraints.Size
 */
package com.empay.vo;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class InquiryRequestVo {
    @Size(min=19, max=19, message="Invalid date length. It must be exactly 19 characters (dd/MM/yyyy hh:mm:ss).")
    @Pattern(regexp="^\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}:\\d{2}$", message="Invalid date format. It must be in the format dd/MM/yyyy hh:mm:ss.")
    private @Size(min=19, max=19, message="Invalid date length. It must be exactly 19 characters (dd/MM/yyyy hh:mm:ss).") @Pattern(regexp="^\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}:\\d{2}$", message="Invalid date format. It must be in the format dd/MM/yyyy hh:mm:ss.") String fromDate;
    @Size(min=19, max=19, message="Invalid date length. It must be exactly 19 characters (dd/MM/yyyy hh:mm:ss).")
    @Pattern(regexp="^\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}:\\d{2}$", message="Invalid date format. It must be in the format dd/MM/yyyy hh:mm:ss.")
    private @Size(min=19, max=19, message="Invalid date length. It must be exactly 19 characters (dd/MM/yyyy hh:mm:ss).") @Pattern(regexp="^\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}:\\d{2}$", message="Invalid date format. It must be in the format dd/MM/yyyy hh:mm:ss.") String toDate;
    @Pattern(regexp="^(?i)(MASTERCARD)$", message="INVALID_NETWORK;NETWORK is not recognized")
    private @Pattern(regexp="^(?i)(MASTERCARD)$", message="INVALID_NETWORK;NETWORK is not recognized") String network;

    public String getFromDate() {
        return this.fromDate;
    }

    public String getToDate() {
        return this.toDate;
    }

    public String getNetwork() {
        return this.network;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof InquiryRequestVo)) {
            return false;
        }
        InquiryRequestVo other = (InquiryRequestVo)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        String this$fromDate = this.getFromDate();
        String other$fromDate = other.getFromDate();
        if (this$fromDate == null ? other$fromDate != null : !this$fromDate.equals(other$fromDate)) {
            return false;
        }
        String this$toDate = this.getToDate();
        String other$toDate = other.getToDate();
        if (this$toDate == null ? other$toDate != null : !this$toDate.equals(other$toDate)) {
            return false;
        }
        String this$network = this.getNetwork();
        String other$network = other.getNetwork();
        return !(this$network == null ? other$network != null : !this$network.equals(other$network));
    }

    protected boolean canEqual(Object other) {
        return other instanceof InquiryRequestVo;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        String $fromDate = this.getFromDate();
        result = result * 59 + ($fromDate == null ? 43 : $fromDate.hashCode());
        String $toDate = this.getToDate();
        result = result * 59 + ($toDate == null ? 43 : $toDate.hashCode());
        String $network = this.getNetwork();
        result = result * 59 + ($network == null ? 43 : $network.hashCode());
        return result;
    }

    public String toString() {
        return "InquiryRequestVo(fromDate=" + this.getFromDate() + ", toDate=" + this.getToDate() + ", network=" + this.getNetwork() + ")";
    }
}

