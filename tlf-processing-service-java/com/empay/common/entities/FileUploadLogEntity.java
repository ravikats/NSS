// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.common.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;

@Entity
@Table(name = "FILE_UPLOAD_LOG")
public class FileUploadLogEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UPL_SER_NUMBER")
    private Integer serialNumber;
    @Column(name = "UPL_LAST_UPDATED")
    private LocalDateTime lastUpdated;
    @Column(name = "UPL_UPDATED_USER")
    private Integer updatedUser;
    @Column(name = "UPL_INS_CODE")
    private Integer institutionCode;
    @Column(name = "UPL_INT_CODE")
    private Integer interfaceCode;
    @Column(name = "UPL_FOR_CODE")
    private Integer forCode;
    @Column(name = "UPL_PRJ_SER_NUMBER")
    private Integer jobNumber;
    @Column(name = "UPL_FILE_NAME")
    private String fileName;
    @Column(name = "UPL_UPLOAD_DATE")
    private LocalDate uploadDate;
    @Column(name = "UPL_UPLOAD_STATUS")
    private Integer uploadStatus;
    @Column(name = "UPL_PROC_DATE")
    private LocalDate processingDate;
    @Column(name = "UPL_BUSS_DATE")
    private LocalDate businessDate;
    @Column(name = "UPL_TOT_TXN_COUNT")
    private Integer totalTxnCount;
    @Column(name = "UPL_FILE_ID")
    private String fileId;
    @Column(name = "UPL_TOT_ACCP_TXN_COUNT")
    private Integer totalAcceptedTxnCount;
    @Column(name = "UPL_TOT_TXN_AMOUNT")
    private Double totalTxnAmount;
    @Column(name = "UPL_TOT_ACCP_TXN_AMOUNT")
    private Double totalacceptTxnAmount;
    
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
    
    public Integer getForCode() {
        return this.forCode;
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
    
    public Integer getTotalTxnCount() {
        return this.totalTxnCount;
    }
    
    public String getFileId() {
        return this.fileId;
    }
    
    public Integer getTotalAcceptedTxnCount() {
        return this.totalAcceptedTxnCount;
    }
    
    public Double getTotalTxnAmount() {
        return this.totalTxnAmount;
    }
    
    public Double getTotalacceptTxnAmount() {
        return this.totalacceptTxnAmount;
    }
    
    public void setSerialNumber(final Integer serialNumber) {
        this.serialNumber = serialNumber;
    }
    
    public void setLastUpdated(final LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
    
    public void setUpdatedUser(final Integer updatedUser) {
        this.updatedUser = updatedUser;
    }
    
    public void setInstitutionCode(final Integer institutionCode) {
        this.institutionCode = institutionCode;
    }
    
    public void setInterfaceCode(final Integer interfaceCode) {
        this.interfaceCode = interfaceCode;
    }
    
    public void setForCode(final Integer forCode) {
        this.forCode = forCode;
    }
    
    public void setJobNumber(final Integer jobNumber) {
        this.jobNumber = jobNumber;
    }
    
    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }
    
    public void setUploadDate(final LocalDate uploadDate) {
        this.uploadDate = uploadDate;
    }
    
    public void setUploadStatus(final Integer uploadStatus) {
        this.uploadStatus = uploadStatus;
    }
    
    public void setProcessingDate(final LocalDate processingDate) {
        this.processingDate = processingDate;
    }
    
    public void setBusinessDate(final LocalDate businessDate) {
        this.businessDate = businessDate;
    }
    
    public void setTotalTxnCount(final Integer totalTxnCount) {
        this.totalTxnCount = totalTxnCount;
    }
    
    public void setFileId(final String fileId) {
        this.fileId = fileId;
    }
    
    public void setTotalAcceptedTxnCount(final Integer totalAcceptedTxnCount) {
        this.totalAcceptedTxnCount = totalAcceptedTxnCount;
    }
    
    public void setTotalTxnAmount(final Double totalTxnAmount) {
        this.totalTxnAmount = totalTxnAmount;
    }
    
    public void setTotalacceptTxnAmount(final Double totalacceptTxnAmount) {
        this.totalacceptTxnAmount = totalacceptTxnAmount;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof FileUploadLogEntity)) {
            return false;
        }
        final FileUploadLogEntity other = (FileUploadLogEntity)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$serialNumber = this.getSerialNumber();
        final Object other$serialNumber = other.getSerialNumber();
        Label_0065: {
            if (this$serialNumber == null) {
                if (other$serialNumber == null) {
                    break Label_0065;
                }
            }
            else if (this$serialNumber.equals(other$serialNumber)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$updatedUser = this.getUpdatedUser();
        final Object other$updatedUser = other.getUpdatedUser();
        Label_0102: {
            if (this$updatedUser == null) {
                if (other$updatedUser == null) {
                    break Label_0102;
                }
            }
            else if (this$updatedUser.equals(other$updatedUser)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$institutionCode = this.getInstitutionCode();
        final Object other$institutionCode = other.getInstitutionCode();
        Label_0139: {
            if (this$institutionCode == null) {
                if (other$institutionCode == null) {
                    break Label_0139;
                }
            }
            else if (this$institutionCode.equals(other$institutionCode)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$interfaceCode = this.getInterfaceCode();
        final Object other$interfaceCode = other.getInterfaceCode();
        Label_0176: {
            if (this$interfaceCode == null) {
                if (other$interfaceCode == null) {
                    break Label_0176;
                }
            }
            else if (this$interfaceCode.equals(other$interfaceCode)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$forCode = this.getForCode();
        final Object other$forCode = other.getForCode();
        Label_0213: {
            if (this$forCode == null) {
                if (other$forCode == null) {
                    break Label_0213;
                }
            }
            else if (this$forCode.equals(other$forCode)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$jobNumber = this.getJobNumber();
        final Object other$jobNumber = other.getJobNumber();
        Label_0250: {
            if (this$jobNumber == null) {
                if (other$jobNumber == null) {
                    break Label_0250;
                }
            }
            else if (this$jobNumber.equals(other$jobNumber)) {
                break Label_0250;
            }
            return false;
        }
        final Object this$uploadStatus = this.getUploadStatus();
        final Object other$uploadStatus = other.getUploadStatus();
        Label_0287: {
            if (this$uploadStatus == null) {
                if (other$uploadStatus == null) {
                    break Label_0287;
                }
            }
            else if (this$uploadStatus.equals(other$uploadStatus)) {
                break Label_0287;
            }
            return false;
        }
        final Object this$totalTxnCount = this.getTotalTxnCount();
        final Object other$totalTxnCount = other.getTotalTxnCount();
        Label_0324: {
            if (this$totalTxnCount == null) {
                if (other$totalTxnCount == null) {
                    break Label_0324;
                }
            }
            else if (this$totalTxnCount.equals(other$totalTxnCount)) {
                break Label_0324;
            }
            return false;
        }
        final Object this$totalAcceptedTxnCount = this.getTotalAcceptedTxnCount();
        final Object other$totalAcceptedTxnCount = other.getTotalAcceptedTxnCount();
        Label_0361: {
            if (this$totalAcceptedTxnCount == null) {
                if (other$totalAcceptedTxnCount == null) {
                    break Label_0361;
                }
            }
            else if (this$totalAcceptedTxnCount.equals(other$totalAcceptedTxnCount)) {
                break Label_0361;
            }
            return false;
        }
        final Object this$totalTxnAmount = this.getTotalTxnAmount();
        final Object other$totalTxnAmount = other.getTotalTxnAmount();
        Label_0398: {
            if (this$totalTxnAmount == null) {
                if (other$totalTxnAmount == null) {
                    break Label_0398;
                }
            }
            else if (this$totalTxnAmount.equals(other$totalTxnAmount)) {
                break Label_0398;
            }
            return false;
        }
        final Object this$totalacceptTxnAmount = this.getTotalacceptTxnAmount();
        final Object other$totalacceptTxnAmount = other.getTotalacceptTxnAmount();
        Label_0435: {
            if (this$totalacceptTxnAmount == null) {
                if (other$totalacceptTxnAmount == null) {
                    break Label_0435;
                }
            }
            else if (this$totalacceptTxnAmount.equals(other$totalacceptTxnAmount)) {
                break Label_0435;
            }
            return false;
        }
        final Object this$lastUpdated = this.getLastUpdated();
        final Object other$lastUpdated = other.getLastUpdated();
        Label_0472: {
            if (this$lastUpdated == null) {
                if (other$lastUpdated == null) {
                    break Label_0472;
                }
            }
            else if (this$lastUpdated.equals(other$lastUpdated)) {
                break Label_0472;
            }
            return false;
        }
        final Object this$fileName = this.getFileName();
        final Object other$fileName = other.getFileName();
        Label_0509: {
            if (this$fileName == null) {
                if (other$fileName == null) {
                    break Label_0509;
                }
            }
            else if (this$fileName.equals(other$fileName)) {
                break Label_0509;
            }
            return false;
        }
        final Object this$uploadDate = this.getUploadDate();
        final Object other$uploadDate = other.getUploadDate();
        Label_0546: {
            if (this$uploadDate == null) {
                if (other$uploadDate == null) {
                    break Label_0546;
                }
            }
            else if (this$uploadDate.equals(other$uploadDate)) {
                break Label_0546;
            }
            return false;
        }
        final Object this$processingDate = this.getProcessingDate();
        final Object other$processingDate = other.getProcessingDate();
        Label_0583: {
            if (this$processingDate == null) {
                if (other$processingDate == null) {
                    break Label_0583;
                }
            }
            else if (this$processingDate.equals(other$processingDate)) {
                break Label_0583;
            }
            return false;
        }
        final Object this$businessDate = this.getBusinessDate();
        final Object other$businessDate = other.getBusinessDate();
        Label_0620: {
            if (this$businessDate == null) {
                if (other$businessDate == null) {
                    break Label_0620;
                }
            }
            else if (this$businessDate.equals(other$businessDate)) {
                break Label_0620;
            }
            return false;
        }
        final Object this$fileId = this.getFileId();
        final Object other$fileId = other.getFileId();
        if (this$fileId == null) {
            if (other$fileId == null) {
                return true;
            }
        }
        else if (this$fileId.equals(other$fileId)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof FileUploadLogEntity;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $serialNumber = this.getSerialNumber();
        result = result * 59 + (($serialNumber == null) ? 43 : $serialNumber.hashCode());
        final Object $updatedUser = this.getUpdatedUser();
        result = result * 59 + (($updatedUser == null) ? 43 : $updatedUser.hashCode());
        final Object $institutionCode = this.getInstitutionCode();
        result = result * 59 + (($institutionCode == null) ? 43 : $institutionCode.hashCode());
        final Object $interfaceCode = this.getInterfaceCode();
        result = result * 59 + (($interfaceCode == null) ? 43 : $interfaceCode.hashCode());
        final Object $forCode = this.getForCode();
        result = result * 59 + (($forCode == null) ? 43 : $forCode.hashCode());
        final Object $jobNumber = this.getJobNumber();
        result = result * 59 + (($jobNumber == null) ? 43 : $jobNumber.hashCode());
        final Object $uploadStatus = this.getUploadStatus();
        result = result * 59 + (($uploadStatus == null) ? 43 : $uploadStatus.hashCode());
        final Object $totalTxnCount = this.getTotalTxnCount();
        result = result * 59 + (($totalTxnCount == null) ? 43 : $totalTxnCount.hashCode());
        final Object $totalAcceptedTxnCount = this.getTotalAcceptedTxnCount();
        result = result * 59 + (($totalAcceptedTxnCount == null) ? 43 : $totalAcceptedTxnCount.hashCode());
        final Object $totalTxnAmount = this.getTotalTxnAmount();
        result = result * 59 + (($totalTxnAmount == null) ? 43 : $totalTxnAmount.hashCode());
        final Object $totalacceptTxnAmount = this.getTotalacceptTxnAmount();
        result = result * 59 + (($totalacceptTxnAmount == null) ? 43 : $totalacceptTxnAmount.hashCode());
        final Object $lastUpdated = this.getLastUpdated();
        result = result * 59 + (($lastUpdated == null) ? 43 : $lastUpdated.hashCode());
        final Object $fileName = this.getFileName();
        result = result * 59 + (($fileName == null) ? 43 : $fileName.hashCode());
        final Object $uploadDate = this.getUploadDate();
        result = result * 59 + (($uploadDate == null) ? 43 : $uploadDate.hashCode());
        final Object $processingDate = this.getProcessingDate();
        result = result * 59 + (($processingDate == null) ? 43 : $processingDate.hashCode());
        final Object $businessDate = this.getBusinessDate();
        result = result * 59 + (($businessDate == null) ? 43 : $businessDate.hashCode());
        final Object $fileId = this.getFileId();
        result = result * 59 + (($fileId == null) ? 43 : $fileId.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "FileUploadLogEntity(serialNumber=" + this.getSerialNumber() + ", lastUpdated=" + String.valueOf(this.getLastUpdated()) + ", updatedUser=" + this.getUpdatedUser() + ", institutionCode=" + this.getInstitutionCode() + ", interfaceCode=" + this.getInterfaceCode() + ", forCode=" + this.getForCode() + ", jobNumber=" + this.getJobNumber() + ", fileName=" + this.getFileName() + ", uploadDate=" + String.valueOf(this.getUploadDate()) + ", uploadStatus=" + this.getUploadStatus() + ", processingDate=" + String.valueOf(this.getProcessingDate()) + ", businessDate=" + String.valueOf(this.getBusinessDate()) + ", totalTxnCount=" + this.getTotalTxnCount() + ", fileId=" + this.getFileId() + ", totalAcceptedTxnCount=" + this.getTotalAcceptedTxnCount() + ", totalTxnAmount=" + this.getTotalTxnAmount() + ", totalacceptTxnAmount=" + this.getTotalacceptTxnAmount();
    }
    
    public FileUploadLogEntity() {
    }
    
    public FileUploadLogEntity(final Integer serialNumber, final LocalDateTime lastUpdated, final Integer updatedUser, final Integer institutionCode, final Integer interfaceCode, final Integer forCode, final Integer jobNumber, final String fileName, final LocalDate uploadDate, final Integer uploadStatus, final LocalDate processingDate, final LocalDate businessDate, final Integer totalTxnCount, final String fileId, final Integer totalAcceptedTxnCount, final Double totalTxnAmount, final Double totalacceptTxnAmount) {
        this.serialNumber = serialNumber;
        this.lastUpdated = lastUpdated;
        this.updatedUser = updatedUser;
        this.institutionCode = institutionCode;
        this.interfaceCode = interfaceCode;
        this.forCode = forCode;
        this.jobNumber = jobNumber;
        this.fileName = fileName;
        this.uploadDate = uploadDate;
        this.uploadStatus = uploadStatus;
        this.processingDate = processingDate;
        this.businessDate = businessDate;
        this.totalTxnCount = totalTxnCount;
        this.fileId = fileId;
        this.totalAcceptedTxnCount = totalAcceptedTxnCount;
        this.totalTxnAmount = totalTxnAmount;
        this.totalacceptTxnAmount = totalacceptTxnAmount;
    }
    
    public static class FileUploadLogEntityBuilder
    {
        private Integer serialNumber;
        private LocalDateTime lastUpdated;
        private Integer updatedUser;
        private Integer institutionCode;
        private Integer interfaceCode;
        private Integer forCode;
        private Integer jobNumber;
        private String fileName;
        private LocalDate uploadDate;
        private Integer uploadStatus;
        private LocalDate processingDate;
        private LocalDate businessDate;
        private Integer totalTxnCount;
        private String fileId;
        private Integer totalAcceptedTxnCount;
        private Double totalTxnAmount;
        private Double totalacceptTxnAmount;
        
        FileUploadLogEntityBuilder() {
        }
        
        public FileUploadLogEntityBuilder serialNumber(final Integer serialNumber) {
            this.serialNumber = serialNumber;
            return this;
        }
        
        public FileUploadLogEntityBuilder lastUpdated(final LocalDateTime lastUpdated) {
            this.lastUpdated = lastUpdated;
            return this;
        }
        
        public FileUploadLogEntityBuilder updatedUser(final Integer updatedUser) {
            this.updatedUser = updatedUser;
            return this;
        }
        
        public FileUploadLogEntityBuilder institutionCode(final Integer institutionCode) {
            this.institutionCode = institutionCode;
            return this;
        }
        
        public FileUploadLogEntityBuilder interfaceCode(final Integer interfaceCode) {
            this.interfaceCode = interfaceCode;
            return this;
        }
        
        public FileUploadLogEntityBuilder forCode(final Integer forCode) {
            this.forCode = forCode;
            return this;
        }
        
        public FileUploadLogEntityBuilder jobNumber(final Integer jobNumber) {
            this.jobNumber = jobNumber;
            return this;
        }
        
        public FileUploadLogEntityBuilder fileName(final String fileName) {
            this.fileName = fileName;
            return this;
        }
        
        public FileUploadLogEntityBuilder uploadDate(final LocalDate uploadDate) {
            this.uploadDate = uploadDate;
            return this;
        }
        
        public FileUploadLogEntityBuilder uploadStatus(final Integer uploadStatus) {
            this.uploadStatus = uploadStatus;
            return this;
        }
        
        public FileUploadLogEntityBuilder processingDate(final LocalDate processingDate) {
            this.processingDate = processingDate;
            return this;
        }
        
        public FileUploadLogEntityBuilder businessDate(final LocalDate businessDate) {
            this.businessDate = businessDate;
            return this;
        }
        
        public FileUploadLogEntityBuilder totalTxnCount(final Integer totalTxnCount) {
            this.totalTxnCount = totalTxnCount;
            return this;
        }
        
        public FileUploadLogEntityBuilder fileId(final String fileId) {
            this.fileId = fileId;
            return this;
        }
        
        public FileUploadLogEntityBuilder totalAcceptedTxnCount(final Integer totalAcceptedTxnCount) {
            this.totalAcceptedTxnCount = totalAcceptedTxnCount;
            return this;
        }
        
        public FileUploadLogEntityBuilder totalTxnAmount(final Double totalTxnAmount) {
            this.totalTxnAmount = totalTxnAmount;
            return this;
        }
        
        public FileUploadLogEntityBuilder totalacceptTxnAmount(final Double totalacceptTxnAmount) {
            this.totalacceptTxnAmount = totalacceptTxnAmount;
            return this;
        }
        
        public FileUploadLogEntity build() {
            return new FileUploadLogEntity(this.serialNumber, this.lastUpdated, this.updatedUser, this.institutionCode, this.interfaceCode, this.forCode, this.jobNumber, this.fileName, this.uploadDate, this.uploadStatus, this.processingDate, this.businessDate, this.totalTxnCount, this.fileId, this.totalAcceptedTxnCount, this.totalTxnAmount, this.totalacceptTxnAmount);
        }
        
        @Override
        public String toString() {
            return "FileUploadLogEntity.FileUploadLogEntityBuilder(serialNumber=" + this.serialNumber + ", lastUpdated=" + String.valueOf(this.lastUpdated) + ", updatedUser=" + this.updatedUser + ", institutionCode=" + this.institutionCode + ", interfaceCode=" + this.interfaceCode + ", forCode=" + this.forCode + ", jobNumber=" + this.jobNumber + ", fileName=" + this.fileName + ", uploadDate=" + String.valueOf(this.uploadDate) + ", uploadStatus=" + this.uploadStatus + ", processingDate=" + String.valueOf(this.processingDate) + ", businessDate=" + String.valueOf(this.businessDate) + ", totalTxnCount=" + this.totalTxnCount + ", fileId=" + this.fileId + ", totalAcceptedTxnCount=" + this.totalAcceptedTxnCount + ", totalTxnAmount=" + this.totalTxnAmount + ", totalacceptTxnAmount=" + this.totalacceptTxnAmount;
        }
    }
}
