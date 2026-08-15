/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.amex.vo.TFHRecordVo
 */
package com.empay.amex.vo;

public class TFHRecordVo {
    private String recordType;
    private String recordNumber;
    private String submitterId;
    private String reserved1;
    private String submitterFileRefNumber;
    private String submitterFileSeqNumber;
    private String fileCreationDate;
    private String fileCreationTime;
    private String fileVersionNumber;
    private String reserved2;

    public String getTFH() {
        return this.recordType + this.recordNumber + this.submitterId + this.reserved1 + this.submitterFileRefNumber + this.submitterFileSeqNumber + this.fileCreationDate + this.fileCreationTime + this.fileVersionNumber + this.reserved2;
    }

    public String getRecordType() {
        return this.recordType;
    }

    public String getRecordNumber() {
        return this.recordNumber;
    }

    public String getSubmitterId() {
        return this.submitterId;
    }

    public String getReserved1() {
        return this.reserved1;
    }

    public String getSubmitterFileRefNumber() {
        return this.submitterFileRefNumber;
    }

    public String getSubmitterFileSeqNumber() {
        return this.submitterFileSeqNumber;
    }

    public String getFileCreationDate() {
        return this.fileCreationDate;
    }

    public String getFileCreationTime() {
        return this.fileCreationTime;
    }

    public String getFileVersionNumber() {
        return this.fileVersionNumber;
    }

    public String getReserved2() {
        return this.reserved2;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    public void setRecordNumber(String recordNumber) {
        this.recordNumber = recordNumber;
    }

    public void setSubmitterId(String submitterId) {
        this.submitterId = submitterId;
    }

    public void setReserved1(String reserved1) {
        this.reserved1 = reserved1;
    }

    public void setSubmitterFileRefNumber(String submitterFileRefNumber) {
        this.submitterFileRefNumber = submitterFileRefNumber;
    }

    public void setSubmitterFileSeqNumber(String submitterFileSeqNumber) {
        this.submitterFileSeqNumber = submitterFileSeqNumber;
    }

    public void setFileCreationDate(String fileCreationDate) {
        this.fileCreationDate = fileCreationDate;
    }

    public void setFileCreationTime(String fileCreationTime) {
        this.fileCreationTime = fileCreationTime;
    }

    public void setFileVersionNumber(String fileVersionNumber) {
        this.fileVersionNumber = fileVersionNumber;
    }

    public void setReserved2(String reserved2) {
        this.reserved2 = reserved2;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof TFHRecordVo)) {
            return false;
        }
        TFHRecordVo other = (TFHRecordVo)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        String this$recordType = this.getRecordType();
        String other$recordType = other.getRecordType();
        if (this$recordType == null ? other$recordType != null : !this$recordType.equals(other$recordType)) {
            return false;
        }
        String this$recordNumber = this.getRecordNumber();
        String other$recordNumber = other.getRecordNumber();
        if (this$recordNumber == null ? other$recordNumber != null : !this$recordNumber.equals(other$recordNumber)) {
            return false;
        }
        String this$submitterId = this.getSubmitterId();
        String other$submitterId = other.getSubmitterId();
        if (this$submitterId == null ? other$submitterId != null : !this$submitterId.equals(other$submitterId)) {
            return false;
        }
        String this$reserved1 = this.getReserved1();
        String other$reserved1 = other.getReserved1();
        if (this$reserved1 == null ? other$reserved1 != null : !this$reserved1.equals(other$reserved1)) {
            return false;
        }
        String this$submitterFileRefNumber = this.getSubmitterFileRefNumber();
        String other$submitterFileRefNumber = other.getSubmitterFileRefNumber();
        if (this$submitterFileRefNumber == null ? other$submitterFileRefNumber != null : !this$submitterFileRefNumber.equals(other$submitterFileRefNumber)) {
            return false;
        }
        String this$submitterFileSeqNumber = this.getSubmitterFileSeqNumber();
        String other$submitterFileSeqNumber = other.getSubmitterFileSeqNumber();
        if (this$submitterFileSeqNumber == null ? other$submitterFileSeqNumber != null : !this$submitterFileSeqNumber.equals(other$submitterFileSeqNumber)) {
            return false;
        }
        String this$fileCreationDate = this.getFileCreationDate();
        String other$fileCreationDate = other.getFileCreationDate();
        if (this$fileCreationDate == null ? other$fileCreationDate != null : !this$fileCreationDate.equals(other$fileCreationDate)) {
            return false;
        }
        String this$fileCreationTime = this.getFileCreationTime();
        String other$fileCreationTime = other.getFileCreationTime();
        if (this$fileCreationTime == null ? other$fileCreationTime != null : !this$fileCreationTime.equals(other$fileCreationTime)) {
            return false;
        }
        String this$fileVersionNumber = this.getFileVersionNumber();
        String other$fileVersionNumber = other.getFileVersionNumber();
        if (this$fileVersionNumber == null ? other$fileVersionNumber != null : !this$fileVersionNumber.equals(other$fileVersionNumber)) {
            return false;
        }
        String this$reserved2 = this.getReserved2();
        String other$reserved2 = other.getReserved2();
        return !(this$reserved2 == null ? other$reserved2 != null : !this$reserved2.equals(other$reserved2));
    }

    protected boolean canEqual(Object other) {
        return other instanceof TFHRecordVo;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        String $recordType = this.getRecordType();
        result = result * 59 + ($recordType == null ? 43 : $recordType.hashCode());
        String $recordNumber = this.getRecordNumber();
        result = result * 59 + ($recordNumber == null ? 43 : $recordNumber.hashCode());
        String $submitterId = this.getSubmitterId();
        result = result * 59 + ($submitterId == null ? 43 : $submitterId.hashCode());
        String $reserved1 = this.getReserved1();
        result = result * 59 + ($reserved1 == null ? 43 : $reserved1.hashCode());
        String $submitterFileRefNumber = this.getSubmitterFileRefNumber();
        result = result * 59 + ($submitterFileRefNumber == null ? 43 : $submitterFileRefNumber.hashCode());
        String $submitterFileSeqNumber = this.getSubmitterFileSeqNumber();
        result = result * 59 + ($submitterFileSeqNumber == null ? 43 : $submitterFileSeqNumber.hashCode());
        String $fileCreationDate = this.getFileCreationDate();
        result = result * 59 + ($fileCreationDate == null ? 43 : $fileCreationDate.hashCode());
        String $fileCreationTime = this.getFileCreationTime();
        result = result * 59 + ($fileCreationTime == null ? 43 : $fileCreationTime.hashCode());
        String $fileVersionNumber = this.getFileVersionNumber();
        result = result * 59 + ($fileVersionNumber == null ? 43 : $fileVersionNumber.hashCode());
        String $reserved2 = this.getReserved2();
        result = result * 59 + ($reserved2 == null ? 43 : $reserved2.hashCode());
        return result;
    }

    public String toString() {
        return "TFHRecordVo(recordType=" + this.getRecordType() + ", recordNumber=" + this.getRecordNumber() + ", submitterId=" + this.getSubmitterId() + ", reserved1=" + this.getReserved1() + ", submitterFileRefNumber=" + this.getSubmitterFileRefNumber() + ", submitterFileSeqNumber=" + this.getSubmitterFileSeqNumber() + ", fileCreationDate=" + this.getFileCreationDate() + ", fileCreationTime=" + this.getFileCreationTime() + ", fileVersionNumber=" + this.getFileVersionNumber() + ", reserved2=" + this.getReserved2() + ")";
    }
}

