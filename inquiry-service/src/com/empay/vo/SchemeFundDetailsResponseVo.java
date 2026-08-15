/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.vo.SchemeFundDetailsResponseVo
 */
package com.empay.vo;

import java.time.LocalDate;

public class SchemeFundDetailsResponseVo {
    private String network;
    private LocalDate businessDate;
    private long txnCount;
    private double creditAmount;
    private double debitAmount;
    private double totalAmount;
    private int totalCount;
    private int totalPage;

    public String getNetwork() {
        return this.network;
    }

    public LocalDate getBusinessDate() {
        return this.businessDate;
    }

    public long getTxnCount() {
        return this.txnCount;
    }

    public double getCreditAmount() {
        return this.creditAmount;
    }

    public double getDebitAmount() {
        return this.debitAmount;
    }

    public double getTotalAmount() {
        return this.totalAmount;
    }

    public int getTotalCount() {
        return this.totalCount;
    }

    public int getTotalPage() {
        return this.totalPage;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public void setBusinessDate(LocalDate businessDate) {
        this.businessDate = businessDate;
    }

    public void setTxnCount(long txnCount) {
        this.txnCount = txnCount;
    }

    public void setCreditAmount(double creditAmount) {
        this.creditAmount = creditAmount;
    }

    public void setDebitAmount(double debitAmount) {
        this.debitAmount = debitAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof SchemeFundDetailsResponseVo)) {
            return false;
        }
        SchemeFundDetailsResponseVo other = (SchemeFundDetailsResponseVo)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        if (this.getTxnCount() != other.getTxnCount()) {
            return false;
        }
        if (Double.compare(this.getCreditAmount(), other.getCreditAmount()) != 0) {
            return false;
        }
        if (Double.compare(this.getDebitAmount(), other.getDebitAmount()) != 0) {
            return false;
        }
        if (Double.compare(this.getTotalAmount(), other.getTotalAmount()) != 0) {
            return false;
        }
        if (this.getTotalCount() != other.getTotalCount()) {
            return false;
        }
        if (this.getTotalPage() != other.getTotalPage()) {
            return false;
        }
        String this$network = this.getNetwork();
        String other$network = other.getNetwork();
        if (this$network == null ? other$network != null : !this$network.equals(other$network)) {
            return false;
        }
        LocalDate this$businessDate = this.getBusinessDate();
        LocalDate other$businessDate = other.getBusinessDate();
        return !(this$businessDate == null ? other$businessDate != null : !((Object)this$businessDate).equals(other$businessDate));
    }

    protected boolean canEqual(Object other) {
        return other instanceof SchemeFundDetailsResponseVo;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        long $txnCount = this.getTxnCount();
        result = result * 59 + (int)($txnCount >>> 32 ^ $txnCount);
        long $creditAmount = Double.doubleToLongBits(this.getCreditAmount());
        result = result * 59 + (int)($creditAmount >>> 32 ^ $creditAmount);
        long $debitAmount = Double.doubleToLongBits(this.getDebitAmount());
        result = result * 59 + (int)($debitAmount >>> 32 ^ $debitAmount);
        long $totalAmount = Double.doubleToLongBits(this.getTotalAmount());
        result = result * 59 + (int)($totalAmount >>> 32 ^ $totalAmount);
        result = result * 59 + this.getTotalCount();
        result = result * 59 + this.getTotalPage();
        String $network = this.getNetwork();
        result = result * 59 + ($network == null ? 43 : $network.hashCode());
        LocalDate $businessDate = this.getBusinessDate();
        result = result * 59 + ($businessDate == null ? 43 : ((Object)$businessDate).hashCode());
        return result;
    }

    public String toString() {
        return "SchemeFundDetailsResponseVo(network=" + this.getNetwork() + ", businessDate=" + String.valueOf(this.getBusinessDate()) + ", txnCount=" + this.getTxnCount() + ", creditAmount=" + this.getCreditAmount() + ", debitAmount=" + this.getDebitAmount() + ", totalAmount=" + this.getTotalAmount() + ", totalCount=" + this.getTotalCount() + ", totalPage=" + this.getTotalPage() + ")";
    }
}

