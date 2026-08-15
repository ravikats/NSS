/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.entities.FileUploadLogEntity
 *  com.empay.entities.FileUploadLogEntity$FileUploadLogEntityBuilder
 *  jakarta.persistence.Column
 *  jakarta.persistence.Entity
 *  jakarta.persistence.GeneratedValue
 *  jakarta.persistence.GenerationType
 *  jakarta.persistence.Id
 *  jakarta.persistence.Table
 */
package com.empay.entities;

import com.empay.entities.FileUploadLogEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="FILE_UPLOAD_LOG")
public class FileUploadLogEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="UPL_SER_NUMBER")
    private Integer serialNumber;
    @Column(name="UPL_LAST_UPDATED")
    private LocalDateTime lastUpdated;
    @Column(name="UPL_UPDATED_USER")
    private Integer updatedUser;
    @Column(name="UPL_INS_CODE")
    private Integer institutionCode;
    @Column(name="UPL_INT_CODE")
    private Integer interfaceCode;
    @Column(name="UPL_PRJ_SER_NUMBER")
    private Integer jobNumber;
    @Column(name="UPL_FILE_NAME")
    private String fileName;
    @Column(name="UPL_UPLOAD_DATE")
    private LocalDate uploadDate;
    @Column(name="UPL_UPLOAD_STATUS")
    private Integer uploadStatus;
    @Column(name="UPL_PROC_DATE")
    private LocalDate processingDate;
    @Column(name="UPL_BUSS_DATE")
    private LocalDate businessDate;
    @Column(name="UPL_FILE_ID")
    private String fileId;
    @Column(name="UPL_TOT_ACCP_TXN_COUNT")
    private Integer totalAcceptedTxnCount;
    @Column(name="UPL_TOT_TXN_COUNT")
    private Integer totalTxnCount;
    @Column(name="UPL_FOR_CODE")
    private int formatCode;
    @Column(name="UPL_REMARKS")
    private String remarks;

    public static FileUploadLogEntityBuilder builder() {
        return new FileUploadLogEntityBuilder();
    }

    public Integer getSerialNumber() {
        return this.serialNumber;
    }

    public LocalDateTime getLastUpdated() {
        return this.lastUpdated;
    }

    public Integer getUpdatedUser() {
        return this.updatedUser;
    }

    public Integer getInstitutionCode() {
        return this.institutionCode;
    }

    public Integer getInterfaceCode() {
        return this.interfaceCode;
    }

    public Integer getJobNumber() {
        return this.jobNumber;
    }

    public String getFileName() {
        return this.fileName;
    }

    public LocalDate getUploadDate() {
        return this.uploadDate;
    }

    public Integer getUploadStatus() {
        return this.uploadStatus;
    }

    public LocalDate getProcessingDate() {
        return this.processingDate;
    }

    public LocalDate getBusinessDate() {
        return this.businessDate;
    }

    public String getFileId() {
        return this.fileId;
    }

    public Integer getTotalAcceptedTxnCount() {
        return this.totalAcceptedTxnCount;
    }

    public Integer getTotalTxnCount() {
        return this.totalTxnCount;
    }

    public int getFormatCode() {
        return this.formatCode;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public void setUpdatedUser(Integer updatedUser) {
        this.updatedUser = updatedUser;
    }

    public void setInstitutionCode(Integer institutionCode) {
        this.institutionCode = institutionCode;
    }

    public void setInterfaceCode(Integer interfaceCode) {
        this.interfaceCode = interfaceCode;
    }

    public void setJobNumber(Integer jobNumber) {
        this.jobNumber = jobNumber;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setUploadDate(LocalDate uploadDate) {
        this.uploadDate = uploadDate;
    }

    public void setUploadStatus(Integer uploadStatus) {
        this.uploadStatus = uploadStatus;
    }

    public void setProcessingDate(LocalDate processingDate) {
        this.processingDate = processingDate;
    }

    public void setBusinessDate(LocalDate businessDate) {
        this.businessDate = businessDate;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public void setTotalAcceptedTxnCount(Integer totalAcceptedTxnCount) {
        this.totalAcceptedTxnCount = totalAcceptedTxnCount;
    }

    public void setTotalTxnCount(Integer totalTxnCount) {
        this.totalTxnCount = totalTxnCount;
    }

    public void setFormatCode(int formatCode) {
        this.formatCode = formatCode;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof FileUploadLogEntity)) {
            return false;
        }
        FileUploadLogEntity other = (FileUploadLogEntity)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        if (this.getFormatCode() != other.getFormatCode()) {
            return false;
        }
        Integer this$serialNumber = this.getSerialNumber();
        Integer other$serialNumber = other.getSerialNumber();
        if (this$serialNumber == null ? other$serialNumber != null : !((Object)this$serialNumber).equals(other$serialNumber)) {
            return false;
        }
        Integer this$updatedUser = this.getUpdatedUser();
        Integer other$updatedUser = other.getUpdatedUser();
        if (this$updatedUser == null ? other$updatedUser != null : !((Object)this$updatedUser).equals(other$updatedUser)) {
            return false;
        }
        Integer this$institutionCode = this.getInstitutionCode();
        Integer other$institutionCode = other.getInstitutionCode();
        if (this$institutionCode == null ? other$institutionCode != null : !((Object)this$institutionCode).equals(other$institutionCode)) {
            return false;
        }
        Integer this$interfaceCode = this.getInterfaceCode();
        Integer other$interfaceCode = other.getInterfaceCode();
        if (this$interfaceCode == null ? other$interfaceCode != null : !((Object)this$interfaceCode).equals(other$interfaceCode)) {
            return false;
        }
        Integer this$jobNumber = this.getJobNumber();
        Integer other$jobNumber = other.getJobNumber();
        if (this$jobNumber == null ? other$jobNumber != null : !((Object)this$jobNumber).equals(other$jobNumber)) {
            return false;
        }
        Integer this$uploadStatus = this.getUploadStatus();
        Integer other$uploadStatus = other.getUploadStatus();
        if (this$uploadStatus == null ? other$uploadStatus != null : !((Object)this$uploadStatus).equals(other$uploadStatus)) {
            return false;
        }
        Integer this$totalAcceptedTxnCount = this.getTotalAcceptedTxnCount();
        Integer other$totalAcceptedTxnCount = other.getTotalAcceptedTxnCount();
        if (this$totalAcceptedTxnCount == null ? other$totalAcceptedTxnCount != null : !((Object)this$totalAcceptedTxnCount).equals(other$totalAcceptedTxnCount)) {
            return false;
        }
        Integer this$totalTxnCount = this.getTotalTxnCount();
        Integer other$totalTxnCount = other.getTotalTxnCount();
        if (this$totalTxnCount == null ? other$totalTxnCount != null : !((Object)this$totalTxnCount).equals(other$totalTxnCount)) {
            return false;
        }
        LocalDateTime this$lastUpdated = this.getLastUpdated();
        LocalDateTime other$lastUpdated = other.getLastUpdated();
        if (this$lastUpdated == null ? other$lastUpdated != null : !((Object)this$lastUpdated).equals(other$lastUpdated)) {
            return false;
        }
        String this$fileName = this.getFileName();
        String other$fileName = other.getFileName();
        if (this$fileName == null ? other$fileName != null : !this$fileName.equals(other$fileName)) {
            return false;
        }
        LocalDate this$uploadDate = this.getUploadDate();
        LocalDate other$uploadDate = other.getUploadDate();
        if (this$uploadDate == null ? other$uploadDate != null : !((Object)this$uploadDate).equals(other$uploadDate)) {
            return false;
        }
        LocalDate this$processingDate = this.getProcessingDate();
        LocalDate other$processingDate = other.getProcessingDate();
        if (this$processingDate == null ? other$processingDate != null : !((Object)this$processingDate).equals(other$processingDate)) {
            return false;
        }
        LocalDate this$businessDate = this.getBusinessDate();
        LocalDate other$businessDate = other.getBusinessDate();
        if (this$businessDate == null ? other$businessDate != null : !((Object)this$businessDate).equals(other$businessDate)) {
            return false;
        }
        String this$fileId = this.getFileId();
        String other$fileId = other.getFileId();
        if (this$fileId == null ? other$fileId != null : !this$fileId.equals(other$fileId)) {
            return false;
        }
        String this$remarks = this.getRemarks();
        String other$remarks = other.getRemarks();
        return !(this$remarks == null ? other$remarks != null : !this$remarks.equals(other$remarks));
    }

    protected boolean canEqual(Object other) {
        return other instanceof FileUploadLogEntity;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + this.getFormatCode();
        Integer $serialNumber = this.getSerialNumber();
        result = result * 59 + ($serialNumber == null ? 43 : ((Object)$serialNumber).hashCode());
        Integer $updatedUser = this.getUpdatedUser();
        result = result * 59 + ($updatedUser == null ? 43 : ((Object)$updatedUser).hashCode());
        Integer $institutionCode = this.getInstitutionCode();
        result = result * 59 + ($institutionCode == null ? 43 : ((Object)$institutionCode).hashCode());
        Integer $interfaceCode = this.getInterfaceCode();
        result = result * 59 + ($interfaceCode == null ? 43 : ((Object)$interfaceCode).hashCode());
        Integer $jobNumber = this.getJobNumber();
        result = result * 59 + ($jobNumber == null ? 43 : ((Object)$jobNumber).hashCode());
        Integer $uploadStatus = this.getUploadStatus();
        result = result * 59 + ($uploadStatus == null ? 43 : ((Object)$uploadStatus).hashCode());
        Integer $totalAcceptedTxnCount = this.getTotalAcceptedTxnCount();
        result = result * 59 + ($totalAcceptedTxnCount == null ? 43 : ((Object)$totalAcceptedTxnCount).hashCode());
        Integer $totalTxnCount = this.getTotalTxnCount();
        result = result * 59 + ($totalTxnCount == null ? 43 : ((Object)$totalTxnCount).hashCode());
        LocalDateTime $lastUpdated = this.getLastUpdated();
        result = result * 59 + ($lastUpdated == null ? 43 : ((Object)$lastUpdated).hashCode());
        String $fileName = this.getFileName();
        result = result * 59 + ($fileName == null ? 43 : $fileName.hashCode());
        LocalDate $uploadDate = this.getUploadDate();
        result = result * 59 + ($uploadDate == null ? 43 : ((Object)$uploadDate).hashCode());
        LocalDate $processingDate = this.getProcessingDate();
        result = result * 59 + ($processingDate == null ? 43 : ((Object)$processingDate).hashCode());
        LocalDate $businessDate = this.getBusinessDate();
        result = result * 59 + ($businessDate == null ? 43 : ((Object)$businessDate).hashCode());
        String $fileId = this.getFileId();
        result = result * 59 + ($fileId == null ? 43 : $fileId.hashCode());
        String $remarks = this.getRemarks();
        result = result * 59 + ($remarks == null ? 43 : $remarks.hashCode());
        return result;
    }

    public String toString() {
        return "FileUploadLogEntity(serialNumber=" + this.getSerialNumber() + ", lastUpdated=" + String.valueOf(this.getLastUpdated()) + ", updatedUser=" + this.getUpdatedUser() + ", institutionCode=" + this.getInstitutionCode() + ", interfaceCode=" + this.getInterfaceCode() + ", jobNumber=" + this.getJobNumber() + ", fileName=" + this.getFileName() + ", uploadDate=" + String.valueOf(this.getUploadDate()) + ", uploadStatus=" + this.getUploadStatus() + ", processingDate=" + String.valueOf(this.getProcessingDate()) + ", businessDate=" + String.valueOf(this.getBusinessDate()) + ", fileId=" + this.getFileId() + ", totalAcceptedTxnCount=" + this.getTotalAcceptedTxnCount() + ", totalTxnCount=" + this.getTotalTxnCount() + ", formatCode=" + this.getFormatCode() + ", remarks=" + this.getRemarks() + ")";
    }

    public FileUploadLogEntity() {
    }

    public FileUploadLogEntity(Integer serialNumber, LocalDateTime lastUpdated, Integer updatedUser, Integer institutionCode, Integer interfaceCode, Integer jobNumber, String fileName, LocalDate uploadDate, Integer uploadStatus, LocalDate processingDate, LocalDate businessDate, String fileId, Integer totalAcceptedTxnCount, Integer totalTxnCount, int formatCode, String remarks) {
        this.serialNumber = serialNumber;
        this.lastUpdated = lastUpdated;
        this.updatedUser = updatedUser;
        this.institutionCode = institutionCode;
        this.interfaceCode = interfaceCode;
        this.jobNumber = jobNumber;
        this.fileName = fileName;
        this.uploadDate = uploadDate;
        this.uploadStatus = uploadStatus;
        this.processingDate = processingDate;
        this.businessDate = businessDate;
        this.fileId = fileId;
        this.totalAcceptedTxnCount = totalAcceptedTxnCount;
        this.totalTxnCount = totalTxnCount;
        this.formatCode = formatCode;
        this.remarks = remarks;
    }
}

