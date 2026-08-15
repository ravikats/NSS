/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.vo.CollectionOnlyRequestVo
 *  jakarta.validation.constraints.NotEmpty
 *  jakarta.validation.constraints.NotNull
 *  jakarta.validation.constraints.Pattern
 */
package com.empay.vo;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class CollectionOnlyRequestVo {
    @NotNull(message="Network must not be null")
    @NotEmpty(message="Network must not be empty!")
    @Pattern(regexp="^(UAESWITCH|OMANNET)$", message="Invalid network")
    private @NotNull(message="Network must not be null") @NotEmpty(message="Network must not be empty!") @Pattern(regexp="^(UAESWITCH|OMANNET)$", message="Invalid network") String network;
    @NotNull(message="Network must not be null")
    @NotEmpty(message="Network must not be empty!")
    @Pattern(regexp="^(MASTERCARD|VISA)$", message="Invalid schemes")
    private @NotNull(message="Network must not be null") @NotEmpty(message="Network must not be empty!") @Pattern(regexp="^(MASTERCARD|VISA)$", message="Invalid schemes") String scheme;
    @NotNull(message="FromDate must not be null")
    @NotEmpty(message="FromDate must not be empty!")
    @Pattern(regexp="(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4} ([01][0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])", message="Invalid from date")
    private @NotNull(message="FromDate must not be null") @NotEmpty(message="FromDate must not be empty!") @Pattern(regexp="(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4} ([01][0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])", message="Invalid from date") String fromDate;
    @NotNull(message="ToDate must not be null")
    @NotEmpty(message="ToDate must not be empty!")
    @Pattern(regexp="(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4} ([01][0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])", message="Invalid to date")
    private @NotNull(message="ToDate must not be null") @NotEmpty(message="ToDate must not be empty!") @Pattern(regexp="(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4} ([01][0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])", message="Invalid to date") String toDate;

    public String getNetwork() {
        return this.network;
    }

    public String getScheme() {
        return this.scheme;
    }

    public String getFromDate() {
        return this.fromDate;
    }

    public String getToDate() {
        return this.toDate;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof CollectionOnlyRequestVo)) {
            return false;
        }
        CollectionOnlyRequestVo other = (CollectionOnlyRequestVo)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        String this$network = this.getNetwork();
        String other$network = other.getNetwork();
        if (this$network == null ? other$network != null : !this$network.equals(other$network)) {
            return false;
        }
        String this$scheme = this.getScheme();
        String other$scheme = other.getScheme();
        if (this$scheme == null ? other$scheme != null : !this$scheme.equals(other$scheme)) {
            return false;
        }
        String this$fromDate = this.getFromDate();
        String other$fromDate = other.getFromDate();
        if (this$fromDate == null ? other$fromDate != null : !this$fromDate.equals(other$fromDate)) {
            return false;
        }
        String this$toDate = this.getToDate();
        String other$toDate = other.getToDate();
        return !(this$toDate == null ? other$toDate != null : !this$toDate.equals(other$toDate));
    }

    protected boolean canEqual(Object other) {
        return other instanceof CollectionOnlyRequestVo;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        String $network = this.getNetwork();
        result = result * 59 + ($network == null ? 43 : $network.hashCode());
        String $scheme = this.getScheme();
        result = result * 59 + ($scheme == null ? 43 : $scheme.hashCode());
        String $fromDate = this.getFromDate();
        result = result * 59 + ($fromDate == null ? 43 : $fromDate.hashCode());
        String $toDate = this.getToDate();
        result = result * 59 + ($toDate == null ? 43 : $toDate.hashCode());
        return result;
    }

    public String toString() {
        return "CollectionOnlyRequestVo(network=" + this.getNetwork() + ", scheme=" + this.getScheme() + ", fromDate=" + this.getFromDate() + ", toDate=" + this.getToDate() + ")";
    }
}

