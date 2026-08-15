/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.vo.RequestVo
 *  jakarta.validation.constraints.Pattern
 *  jakarta.validation.constraints.Size
 */
package com.empay.vo;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class RequestVo {
    @Size(min=10, max=10, message="Invalid date length. It must be exactly 10 characters.")
    @Pattern(regexp="^\\d{2}/\\d{2}/\\d{4}$", message="Invalid date format. It must be in the format dd/MM/yyyy.")
    private @Size(min=10, max=10, message="Invalid date length. It must be exactly 10 characters.") @Pattern(regexp="^\\d{2}/\\d{2}/\\d{4}$", message="Invalid date format. It must be in the format dd/MM/yyyy.") String fromDate;
    @Size(min=10, max=10, message="Invalid date length. It must be exactly 10 characters.")
    @Pattern(regexp="^\\d{2}/\\d{2}/\\d{4}$", message="Invalid date format. It must be in the format dd/MM/yyyy.")
    private @Size(min=10, max=10, message="Invalid date length. It must be exactly 10 characters.") @Pattern(regexp="^\\d{2}/\\d{2}/\\d{4}$", message="Invalid date format. It must be in the format dd/MM/yyyy.") String toDate;
    private String rrn;
    @Pattern(regexp="^(?!\\d+$).*$", message="INVALID_FILE; Please provide a valid file name")
    private @Pattern(regexp="^(?!\\d+$).*$", message="INVALID_FILE; Please provide a valid file name") String fileName;
    private String bussDate;
    private String network;
    @Pattern(regexp="^(?i)(Processed|Processing Failed|Completed|Deletion in Processing|In processing|Failed|Pending|Marked for Outgoing)$", message="Invalid status; Please provide valid status.")
    private @Pattern(regexp="^(?i)(Processed|Processing Failed|Completed|Deletion in Processing|In processing|Failed|Pending|Marked for Outgoing)$", message="Invalid status; Please provide valid status.") String status;
    @Size(min=19, max=19, message="Invalid date length. It must be exactly 19 characters (dd/MM/yyyy hh:mm:ss).")
    @Pattern(regexp="^\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}:\\d{2}$", message="Invalid date format. It must be in the format dd/MM/yyyy hh:mm:ss.")
    private @Size(min=19, max=19, message="Invalid date length. It must be exactly 19 characters (dd/MM/yyyy hh:mm:ss).") @Pattern(regexp="^\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}:\\d{2}$", message="Invalid date format. It must be in the format dd/MM/yyyy hh:mm:ss.") String fromProcessedDate;
    @Size(min=19, max=19, message="Invalid date length. It must be exactly 19 characters (dd/MM/yyyy hh:mm:ss).")
    @Pattern(regexp="^\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}:\\d{2}$", message="Invalid date format. It must be in the format dd/MM/yyyy hh:mm:ss.")
    private @Size(min=19, max=19, message="Invalid date length. It must be exactly 19 characters (dd/MM/yyyy hh:mm:ss).") @Pattern(regexp="^\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}:\\d{2}$", message="Invalid date format. It must be in the format dd/MM/yyyy hh:mm:ss.") String toProcessedDate;
    @Size(min=19, max=19, message="Invalid date length. It must be exactly 19 characters (dd/MM/yyyy hh:mm:ss).")
    @Pattern(regexp="^\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}:\\d{2}$", message="Invalid date format. It must be in the format dd/MM/yyyy hh:mm:ss.")
    private @Size(min=19, max=19, message="Invalid date length. It must be exactly 19 characters (dd/MM/yyyy hh:mm:ss).") @Pattern(regexp="^\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}:\\d{2}$", message="Invalid date format. It must be in the format dd/MM/yyyy hh:mm:ss.") String fromGeneratedDate;
    @Size(min=19, max=19, message="Invalid date length. It must be exactly 19 characters (dd/MM/yyyy hh:mm:ss).")
    @Pattern(regexp="^\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}:\\d{2}$", message="Invalid date format. It must be in the format dd/MM/yyyy hh:mm:ss.")
    private @Size(min=19, max=19, message="Invalid date length. It must be exactly 19 characters (dd/MM/yyyy hh:mm:ss).") @Pattern(regexp="^\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}:\\d{2}$", message="Invalid date format. It must be in the format dd/MM/yyyy hh:mm:ss.") String toGeneratedDate;

    public String getFromDate() {
        return this.fromDate;
    }

    public String getToDate() {
        return this.toDate;
    }

    public String getRrn() {
        return this.rrn;
    }

    public String getFileName() {
        return this.fileName;
    }

    public String getBussDate() {
        return this.bussDate;
    }

    public String getNetwork() {
        return this.network;
    }

    public String getStatus() {
        return this.status;
    }

    public String getFromProcessedDate() {
        return this.fromProcessedDate;
    }

    public String getToProcessedDate() {
        return this.toProcessedDate;
    }

    public String getFromGeneratedDate() {
        return this.fromGeneratedDate;
    }

    public String getToGeneratedDate() {
        return this.toGeneratedDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public void setRrn(String rrn) {
        this.rrn = rrn;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setBussDate(String bussDate) {
        this.bussDate = bussDate;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setFromProcessedDate(String fromProcessedDate) {
        this.fromProcessedDate = fromProcessedDate;
    }

    public void setToProcessedDate(String toProcessedDate) {
        this.toProcessedDate = toProcessedDate;
    }

    public void setFromGeneratedDate(String fromGeneratedDate) {
        this.fromGeneratedDate = fromGeneratedDate;
    }

    public void setToGeneratedDate(String toGeneratedDate) {
        this.toGeneratedDate = toGeneratedDate;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof RequestVo)) {
            return false;
        }
        RequestVo other = (RequestVo)o;
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
        String this$rrn = this.getRrn();
        String other$rrn = other.getRrn();
        if (this$rrn == null ? other$rrn != null : !this$rrn.equals(other$rrn)) {
            return false;
        }
        String this$fileName = this.getFileName();
        String other$fileName = other.getFileName();
        if (this$fileName == null ? other$fileName != null : !this$fileName.equals(other$fileName)) {
            return false;
        }
        String this$bussDate = this.getBussDate();
        String other$bussDate = other.getBussDate();
        if (this$bussDate == null ? other$bussDate != null : !this$bussDate.equals(other$bussDate)) {
            return false;
        }
        String this$network = this.getNetwork();
        String other$network = other.getNetwork();
        if (this$network == null ? other$network != null : !this$network.equals(other$network)) {
            return false;
        }
        String this$status = this.getStatus();
        String other$status = other.getStatus();
        if (this$status == null ? other$status != null : !this$status.equals(other$status)) {
            return false;
        }
        String this$fromProcessedDate = this.getFromProcessedDate();
        String other$fromProcessedDate = other.getFromProcessedDate();
        if (this$fromProcessedDate == null ? other$fromProcessedDate != null : !this$fromProcessedDate.equals(other$fromProcessedDate)) {
            return false;
        }
        String this$toProcessedDate = this.getToProcessedDate();
        String other$toProcessedDate = other.getToProcessedDate();
        if (this$toProcessedDate == null ? other$toProcessedDate != null : !this$toProcessedDate.equals(other$toProcessedDate)) {
            return false;
        }
        String this$fromGeneratedDate = this.getFromGeneratedDate();
        String other$fromGeneratedDate = other.getFromGeneratedDate();
        if (this$fromGeneratedDate == null ? other$fromGeneratedDate != null : !this$fromGeneratedDate.equals(other$fromGeneratedDate)) {
            return false;
        }
        String this$toGeneratedDate = this.getToGeneratedDate();
        String other$toGeneratedDate = other.getToGeneratedDate();
        return !(this$toGeneratedDate == null ? other$toGeneratedDate != null : !this$toGeneratedDate.equals(other$toGeneratedDate));
    }

    protected boolean canEqual(Object other) {
        return other instanceof RequestVo;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        String $fromDate = this.getFromDate();
        result = result * 59 + ($fromDate == null ? 43 : $fromDate.hashCode());
        String $toDate = this.getToDate();
        result = result * 59 + ($toDate == null ? 43 : $toDate.hashCode());
        String $rrn = this.getRrn();
        result = result * 59 + ($rrn == null ? 43 : $rrn.hashCode());
        String $fileName = this.getFileName();
        result = result * 59 + ($fileName == null ? 43 : $fileName.hashCode());
        String $bussDate = this.getBussDate();
        result = result * 59 + ($bussDate == null ? 43 : $bussDate.hashCode());
        String $network = this.getNetwork();
        result = result * 59 + ($network == null ? 43 : $network.hashCode());
        String $status = this.getStatus();
        result = result * 59 + ($status == null ? 43 : $status.hashCode());
        String $fromProcessedDate = this.getFromProcessedDate();
        result = result * 59 + ($fromProcessedDate == null ? 43 : $fromProcessedDate.hashCode());
        String $toProcessedDate = this.getToProcessedDate();
        result = result * 59 + ($toProcessedDate == null ? 43 : $toProcessedDate.hashCode());
        String $fromGeneratedDate = this.getFromGeneratedDate();
        result = result * 59 + ($fromGeneratedDate == null ? 43 : $fromGeneratedDate.hashCode());
        String $toGeneratedDate = this.getToGeneratedDate();
        result = result * 59 + ($toGeneratedDate == null ? 43 : $toGeneratedDate.hashCode());
        return result;
    }

    public String toString() {
        return "RequestVo(fromDate=" + this.getFromDate() + ", toDate=" + this.getToDate() + ", rrn=" + this.getRrn() + ", fileName=" + this.getFileName() + ", bussDate=" + this.getBussDate() + ", network=" + this.getNetwork() + ", status=" + this.getStatus() + ", fromProcessedDate=" + this.getFromProcessedDate() + ", toProcessedDate=" + this.getToProcessedDate() + ", fromGeneratedDate=" + this.getFromGeneratedDate() + ", toGeneratedDate=" + this.getToGeneratedDate() + ")";
    }
}

