/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.vo.ReportRequestVo
 *  jakarta.validation.constraints.Pattern
 *  jakarta.validation.constraints.Size
 */
package com.empay.vo;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ReportRequestVo {
    @Size(min=19, max=19, message="Invalid date length. It must be exactly 19 characters (dd/MM/yyyy hh:mm:ss).")
    @Pattern(regexp="^\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}:\\d{2}$", message="Invalid date format. It must be in the format dd/MM/yyyy hh:mm:ss.")
    private @Size(min=19, max=19, message="Invalid date length. It must be exactly 19 characters (dd/MM/yyyy hh:mm:ss).") @Pattern(regexp="^\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}:\\d{2}$", message="Invalid date format. It must be in the format dd/MM/yyyy hh:mm:ss.") String fromDate;
    @Size(min=19, max=19, message="Invalid date length. It must be exactly 19 characters (dd/MM/yyyy hh:mm:ss).")
    @Pattern(regexp="^\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}:\\d{2}$", message="Invalid date format. It must be in the format dd/MM/yyyy hh:mm:ss.")
    private @Size(min=19, max=19, message="Invalid date length. It must be exactly 19 characters (dd/MM/yyyy hh:mm:ss).") @Pattern(regexp="^\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}:\\d{2}$", message="Invalid date format. It must be in the format dd/MM/yyyy hh:mm:ss.") String toDate;
    @Pattern(regexp="^(?i)(TransactionReport|SchemeFundTransferReport|RejectionReport|OutgoingTransactionReport|IpmVsIncomingReport|OmanNetReconReport)$", message="Invalid reportName; Please provide valid reportName.")
    private @Pattern(regexp="^(?i)(TransactionReport|SchemeFundTransferReport|RejectionReport|OutgoingTransactionReport|IpmVsIncomingReport|OmanNetReconReport)$", message="Invalid reportName; Please provide valid reportName.") String reportName;
    @Pattern(regexp="^(?!\\d+$).*$", message="INVALID_FILE; Please provide a valid file name")
    private @Pattern(regexp="^(?!\\d+$).*$", message="INVALID_FILE; Please provide a valid file name") String fileName;

    public String getFromDate() {
        return this.fromDate;
    }

    public String getToDate() {
        return this.toDate;
    }

    public String getReportName() {
        return this.reportName;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ReportRequestVo)) {
            return false;
        }
        ReportRequestVo other = (ReportRequestVo)o;
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
        String this$reportName = this.getReportName();
        String other$reportName = other.getReportName();
        if (this$reportName == null ? other$reportName != null : !this$reportName.equals(other$reportName)) {
            return false;
        }
        String this$fileName = this.getFileName();
        String other$fileName = other.getFileName();
        return !(this$fileName == null ? other$fileName != null : !this$fileName.equals(other$fileName));
    }

    protected boolean canEqual(Object other) {
        return other instanceof ReportRequestVo;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        String $fromDate = this.getFromDate();
        result = result * 59 + ($fromDate == null ? 43 : $fromDate.hashCode());
        String $toDate = this.getToDate();
        result = result * 59 + ($toDate == null ? 43 : $toDate.hashCode());
        String $reportName = this.getReportName();
        result = result * 59 + ($reportName == null ? 43 : $reportName.hashCode());
        String $fileName = this.getFileName();
        result = result * 59 + ($fileName == null ? 43 : $fileName.hashCode());
        return result;
    }

    public String toString() {
        return "ReportRequestVo(fromDate=" + this.getFromDate() + ", toDate=" + this.getToDate() + ", reportName=" + this.getReportName() + ", fileName=" + this.getFileName() + ")";
    }
}

