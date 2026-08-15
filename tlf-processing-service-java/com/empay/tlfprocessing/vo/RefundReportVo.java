// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.tlfprocessing.vo;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class RefundReportVo
{
    @Size(min = 19, max = 19, message = "Invalid date length. It must be exactly 19 characters (dd/MM/yyyy hh:mm:ss).")
    @Pattern(regexp = "^\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}:\\d{2}$", message = "Invalid date format. It must be in the format dd/MM/yyyy hh:mm:ss.")
    private String fromDate;
    @Size(min = 19, max = 19, message = "Invalid date length. It must be exactly 19 characters (dd/MM/yyyy hh:mm:ss).")
    @Pattern(regexp = "^\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}:\\d{2}$", message = "Invalid date format. It must be in the format dd/MM/yyyy hh:mm:ss.")
    private String toDate;
    
    public String getFromDate() {
        return this.fromDate;
    }
    
    public String getToDate() {
        return this.toDate;
    }
    
    public void setFromDate(final String fromDate) {
        this.fromDate = fromDate;
    }
    
    public void setToDate(final String toDate) {
        this.toDate = toDate;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof RefundReportVo)) {
            return false;
        }
        final RefundReportVo other = (RefundReportVo)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$fromDate = this.getFromDate();
        final Object other$fromDate = other.getFromDate();
        Label_0065: {
            if (this$fromDate == null) {
                if (other$fromDate == null) {
                    break Label_0065;
                }
            }
            else if (this$fromDate.equals(other$fromDate)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$toDate = this.getToDate();
        final Object other$toDate = other.getToDate();
        if (this$toDate == null) {
            if (other$toDate == null) {
                return true;
            }
        }
        else if (this$toDate.equals(other$toDate)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof RefundReportVo;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $fromDate = this.getFromDate();
        result = result * 59 + (($fromDate == null) ? 43 : $fromDate.hashCode());
        final Object $toDate = this.getToDate();
        result = result * 59 + (($toDate == null) ? 43 : $toDate.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "RefundReportVo(fromDate=" + this.getFromDate() + ", toDate=" + this.getToDate();
    }
}
