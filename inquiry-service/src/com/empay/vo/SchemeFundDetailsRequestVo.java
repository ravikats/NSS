/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.vo.SchemeFundDetailsRequestVo
 *  jakarta.validation.constraints.Pattern
 */
package com.empay.vo;

import jakarta.validation.constraints.Pattern;

public class SchemeFundDetailsRequestVo {
    @Pattern(regexp="^(?i)(MASTERCARD|VISA)$", message="INVALID_NETWORK;NETWORK is not recognized")
    private @Pattern(regexp="^(?i)(MASTERCARD|VISA)$", message="INVALID_NETWORK;NETWORK is not recognized") String network;
    @Pattern(regexp="^\\d{2}/\\d{2}/\\d{4}$", message="Business date must be in dd/MM/yyyy format")
    private @Pattern(regexp="^\\d{2}/\\d{2}/\\d{4}$", message="Business date must be in dd/MM/yyyy format") String businessDate;

    public String getNetwork() {
        return this.network;
    }

    public String getBusinessDate() {
        return this.businessDate;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public void setBusinessDate(String businessDate) {
        this.businessDate = businessDate;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof SchemeFundDetailsRequestVo)) {
            return false;
        }
        SchemeFundDetailsRequestVo other = (SchemeFundDetailsRequestVo)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        String this$network = this.getNetwork();
        String other$network = other.getNetwork();
        if (this$network == null ? other$network != null : !this$network.equals(other$network)) {
            return false;
        }
        String this$businessDate = this.getBusinessDate();
        String other$businessDate = other.getBusinessDate();
        return !(this$businessDate == null ? other$businessDate != null : !this$businessDate.equals(other$businessDate));
    }

    protected boolean canEqual(Object other) {
        return other instanceof SchemeFundDetailsRequestVo;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        String $network = this.getNetwork();
        result = result * 59 + ($network == null ? 43 : $network.hashCode());
        String $businessDate = this.getBusinessDate();
        result = result * 59 + ($businessDate == null ? 43 : $businessDate.hashCode());
        return result;
    }

    public String toString() {
        return "SchemeFundDetailsRequestVo(network=" + this.getNetwork() + ", businessDate=" + this.getBusinessDate() + ")";
    }
}

