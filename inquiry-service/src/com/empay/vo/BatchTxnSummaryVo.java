/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.vo.BatchTxnSummaryVo
 */
package com.empay.vo;

public class BatchTxnSummaryVo {
    private int success_count;
    private int failed_count;
    private double success_total_amount;
    private double failed_total_amount;

    public int getSuccess_count() {
        return this.success_count;
    }

    public int getFailed_count() {
        return this.failed_count;
    }

    public double getSuccess_total_amount() {
        return this.success_total_amount;
    }

    public double getFailed_total_amount() {
        return this.failed_total_amount;
    }

    public void setSuccess_count(int success_count) {
        this.success_count = success_count;
    }

    public void setFailed_count(int failed_count) {
        this.failed_count = failed_count;
    }

    public void setSuccess_total_amount(double success_total_amount) {
        this.success_total_amount = success_total_amount;
    }

    public void setFailed_total_amount(double failed_total_amount) {
        this.failed_total_amount = failed_total_amount;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof BatchTxnSummaryVo)) {
            return false;
        }
        BatchTxnSummaryVo other = (BatchTxnSummaryVo)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        if (this.getSuccess_count() != other.getSuccess_count()) {
            return false;
        }
        if (this.getFailed_count() != other.getFailed_count()) {
            return false;
        }
        if (Double.compare(this.getSuccess_total_amount(), other.getSuccess_total_amount()) != 0) {
            return false;
        }
        return Double.compare(this.getFailed_total_amount(), other.getFailed_total_amount()) == 0;
    }

    protected boolean canEqual(Object other) {
        return other instanceof BatchTxnSummaryVo;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + this.getSuccess_count();
        result = result * 59 + this.getFailed_count();
        long $success_total_amount = Double.doubleToLongBits(this.getSuccess_total_amount());
        result = result * 59 + (int)($success_total_amount >>> 32 ^ $success_total_amount);
        long $failed_total_amount = Double.doubleToLongBits(this.getFailed_total_amount());
        result = result * 59 + (int)($failed_total_amount >>> 32 ^ $failed_total_amount);
        return result;
    }

    public String toString() {
        return "BatchTxnSummaryVo(success_count=" + this.getSuccess_count() + ", failed_count=" + this.getFailed_count() + ", success_total_amount=" + this.getSuccess_total_amount() + ", failed_total_amount=" + this.getFailed_total_amount() + ")";
    }

    public BatchTxnSummaryVo(int success_count, int failed_count, double success_total_amount, double failed_total_amount) {
        this.success_count = success_count;
        this.failed_count = failed_count;
        this.success_total_amount = success_total_amount;
        this.failed_total_amount = failed_total_amount;
    }

    public BatchTxnSummaryVo() {
    }
}

