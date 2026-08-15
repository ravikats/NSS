/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.common.entity.OutGoingFileProcessingEntity
 *  jakarta.persistence.Column
 *  jakarta.persistence.Entity
 *  jakarta.persistence.GeneratedValue
 *  jakarta.persistence.GenerationType
 *  jakarta.persistence.Id
 *  jakarta.persistence.Table
 */
package com.empay.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="OUT_FILE_LOG")
public class OutGoingFileProcessingEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="OFL_SER_NUMBER")
    private Integer serialNumber;
    @Column(name="OFL_LAST_UPDATED")
    private LocalDateTime lastUpdatedDate;
    @Column(name="OFL_UPDATED_USER")
    private int lastUpdatedUser;
    @Column(name="OFL_INS_CODE")
    private int institutionCode;
    @Column(name="OFL_INT_CODE")
    private Integer interfaceCode;
    @Column(name="OFL_FOR_CODE")
    private Integer formatCode;
    @Column(name="OFL_FILE_NAME")
    private String fileName;
    @Column(name="OFL_GNERATE_DATE")
    private LocalDateTime generatedDate;
    @Column(name="OFL_GENERATE_STATUS")
    private int generatedStatus;
    @Column(name="OFL_PROC_DATE")
    private Date processDate;
    @Column(name="OFL_BUSS_DATE")
    private LocalDate businessDate;
    @Column(name="OFL_PRJ_SER_NUMBER")
    private Integer processingJob;
    @Column(name="OFL_TOT_TXN_COUNT")
    private Integer totalTxnCount;
    @Column(name="OFL_TOT_TXN_AMOUNT")
    private Float totalTxnAmount;
    @Column(name="OFL_TOT_ACCP_TXN_COUNT")
    private Integer totalAcceptanceTxnCount;
    @Column(name="OFL_TOT_ACCP_TXN_AMOUNT")
    private Float totalAcceptanceTxnAmount;
    @Column(name="OFL_FILE_ID")
    private String fileId;

    public Integer getSerialNumber() {
        return this.serialNumber;
    }

    public LocalDateTime getLastUpdatedDate() {
        return this.lastUpdatedDate;
    }

    public int getLastUpdatedUser() {
        return this.lastUpdatedUser;
    }

    public int getInstitutionCode() {
        return this.institutionCode;
    }

    public Integer getInterfaceCode() {
        return this.interfaceCode;
    }

    public Integer getFormatCode() {
        return this.formatCode;
    }

    public String getFileName() {
        return this.fileName;
    }

    public LocalDateTime getGeneratedDate() {
        return this.generatedDate;
    }

    public int getGeneratedStatus() {
        return this.generatedStatus;
    }

    public Date getProcessDate() {
        return this.processDate;
    }

    public LocalDate getBusinessDate() {
        return this.businessDate;
    }

    public Integer getProcessingJob() {
        return this.processingJob;
    }

    public Integer getTotalTxnCount() {
        return this.totalTxnCount;
    }

    public Float getTotalTxnAmount() {
        return this.totalTxnAmount;
    }

    public Integer getTotalAcceptanceTxnCount() {
        return this.totalAcceptanceTxnCount;
    }

    public Float getTotalAcceptanceTxnAmount() {
        return this.totalAcceptanceTxnAmount;
    }

    public String getFileId() {
        return this.fileId;
    }

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setLastUpdatedDate(LocalDateTime lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public void setLastUpdatedUser(int lastUpdatedUser) {
        this.lastUpdatedUser = lastUpdatedUser;
    }

    public void setInstitutionCode(int institutionCode) {
        this.institutionCode = institutionCode;
    }

    public void setInterfaceCode(Integer interfaceCode) {
        this.interfaceCode = interfaceCode;
    }

    public void setFormatCode(Integer formatCode) {
        this.formatCode = formatCode;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setGeneratedDate(LocalDateTime generatedDate) {
        this.generatedDate = generatedDate;
    }

    public void setGeneratedStatus(int generatedStatus) {
        this.generatedStatus = generatedStatus;
    }

    public void setProcessDate(Date processDate) {
        this.processDate = processDate;
    }

    public void setBusinessDate(LocalDate businessDate) {
        this.businessDate = businessDate;
    }

    public void setProcessingJob(Integer processingJob) {
        this.processingJob = processingJob;
    }

    public void setTotalTxnCount(Integer totalTxnCount) {
        this.totalTxnCount = totalTxnCount;
    }

    public void setTotalTxnAmount(Float totalTxnAmount) {
        this.totalTxnAmount = totalTxnAmount;
    }

    public void setTotalAcceptanceTxnCount(Integer totalAcceptanceTxnCount) {
        this.totalAcceptanceTxnCount = totalAcceptanceTxnCount;
    }

    public void setTotalAcceptanceTxnAmount(Float totalAcceptanceTxnAmount) {
        this.totalAcceptanceTxnAmount = totalAcceptanceTxnAmount;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof OutGoingFileProcessingEntity)) {
            return false;
        }
        OutGoingFileProcessingEntity other = (OutGoingFileProcessingEntity)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        if (this.getLastUpdatedUser() != other.getLastUpdatedUser()) {
            return false;
        }
        if (this.getInstitutionCode() != other.getInstitutionCode()) {
            return false;
        }
        if (this.getGeneratedStatus() != other.getGeneratedStatus()) {
            return false;
        }
        Integer this$serialNumber = this.getSerialNumber();
        Integer other$serialNumber = other.getSerialNumber();
        if (this$serialNumber == null ? other$serialNumber != null : !((Object)this$serialNumber).equals(other$serialNumber)) {
            return false;
        }
        Integer this$interfaceCode = this.getInterfaceCode();
        Integer other$interfaceCode = other.getInterfaceCode();
        if (this$interfaceCode == null ? other$interfaceCode != null : !((Object)this$interfaceCode).equals(other$interfaceCode)) {
            return false;
        }
        Integer this$formatCode = this.getFormatCode();
        Integer other$formatCode = other.getFormatCode();
        if (this$formatCode == null ? other$formatCode != null : !((Object)this$formatCode).equals(other$formatCode)) {
            return false;
        }
        Integer this$processingJob = this.getProcessingJob();
        Integer other$processingJob = other.getProcessingJob();
        if (this$processingJob == null ? other$processingJob != null : !((Object)this$processingJob).equals(other$processingJob)) {
            return false;
        }
        Integer this$totalTxnCount = this.getTotalTxnCount();
        Integer other$totalTxnCount = other.getTotalTxnCount();
        if (this$totalTxnCount == null ? other$totalTxnCount != null : !((Object)this$totalTxnCount).equals(other$totalTxnCount)) {
            return false;
        }
        Float this$totalTxnAmount = this.getTotalTxnAmount();
        Float other$totalTxnAmount = other.getTotalTxnAmount();
        if (this$totalTxnAmount == null ? other$totalTxnAmount != null : !((Object)this$totalTxnAmount).equals(other$totalTxnAmount)) {
            return false;
        }
        Integer this$totalAcceptanceTxnCount = this.getTotalAcceptanceTxnCount();
        Integer other$totalAcceptanceTxnCount = other.getTotalAcceptanceTxnCount();
        if (this$totalAcceptanceTxnCount == null ? other$totalAcceptanceTxnCount != null : !((Object)this$totalAcceptanceTxnCount).equals(other$totalAcceptanceTxnCount)) {
            return false;
        }
        Float this$totalAcceptanceTxnAmount = this.getTotalAcceptanceTxnAmount();
        Float other$totalAcceptanceTxnAmount = other.getTotalAcceptanceTxnAmount();
        if (this$totalAcceptanceTxnAmount == null ? other$totalAcceptanceTxnAmount != null : !((Object)this$totalAcceptanceTxnAmount).equals(other$totalAcceptanceTxnAmount)) {
            return false;
        }
        LocalDateTime this$lastUpdatedDate = this.getLastUpdatedDate();
        LocalDateTime other$lastUpdatedDate = other.getLastUpdatedDate();
        if (this$lastUpdatedDate == null ? other$lastUpdatedDate != null : !((Object)this$lastUpdatedDate).equals(other$lastUpdatedDate)) {
            return false;
        }
        String this$fileName = this.getFileName();
        String other$fileName = other.getFileName();
        if (this$fileName == null ? other$fileName != null : !this$fileName.equals(other$fileName)) {
            return false;
        }
        LocalDateTime this$generatedDate = this.getGeneratedDate();
        LocalDateTime other$generatedDate = other.getGeneratedDate();
        if (this$generatedDate == null ? other$generatedDate != null : !((Object)this$generatedDate).equals(other$generatedDate)) {
            return false;
        }
        Date this$processDate = this.getProcessDate();
        Date other$processDate = other.getProcessDate();
        if (this$processDate == null ? other$processDate != null : !((Object)this$processDate).equals(other$processDate)) {
            return false;
        }
        LocalDate this$businessDate = this.getBusinessDate();
        LocalDate other$businessDate = other.getBusinessDate();
        if (this$businessDate == null ? other$businessDate != null : !((Object)this$businessDate).equals(other$businessDate)) {
            return false;
        }
        String this$fileId = this.getFileId();
        String other$fileId = other.getFileId();
        return !(this$fileId == null ? other$fileId != null : !this$fileId.equals(other$fileId));
    }

    protected boolean canEqual(Object other) {
        return other instanceof OutGoingFileProcessingEntity;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + this.getLastUpdatedUser();
        result = result * 59 + this.getInstitutionCode();
        result = result * 59 + this.getGeneratedStatus();
        Integer $serialNumber = this.getSerialNumber();
        result = result * 59 + ($serialNumber == null ? 43 : ((Object)$serialNumber).hashCode());
        Integer $interfaceCode = this.getInterfaceCode();
        result = result * 59 + ($interfaceCode == null ? 43 : ((Object)$interfaceCode).hashCode());
        Integer $formatCode = this.getFormatCode();
        result = result * 59 + ($formatCode == null ? 43 : ((Object)$formatCode).hashCode());
        Integer $processingJob = this.getProcessingJob();
        result = result * 59 + ($processingJob == null ? 43 : ((Object)$processingJob).hashCode());
        Integer $totalTxnCount = this.getTotalTxnCount();
        result = result * 59 + ($totalTxnCount == null ? 43 : ((Object)$totalTxnCount).hashCode());
        Float $totalTxnAmount = this.getTotalTxnAmount();
        result = result * 59 + ($totalTxnAmount == null ? 43 : ((Object)$totalTxnAmount).hashCode());
        Integer $totalAcceptanceTxnCount = this.getTotalAcceptanceTxnCount();
        result = result * 59 + ($totalAcceptanceTxnCount == null ? 43 : ((Object)$totalAcceptanceTxnCount).hashCode());
        Float $totalAcceptanceTxnAmount = this.getTotalAcceptanceTxnAmount();
        result = result * 59 + ($totalAcceptanceTxnAmount == null ? 43 : ((Object)$totalAcceptanceTxnAmount).hashCode());
        LocalDateTime $lastUpdatedDate = this.getLastUpdatedDate();
        result = result * 59 + ($lastUpdatedDate == null ? 43 : ((Object)$lastUpdatedDate).hashCode());
        String $fileName = this.getFileName();
        result = result * 59 + ($fileName == null ? 43 : $fileName.hashCode());
        LocalDateTime $generatedDate = this.getGeneratedDate();
        result = result * 59 + ($generatedDate == null ? 43 : ((Object)$generatedDate).hashCode());
        Date $processDate = this.getProcessDate();
        result = result * 59 + ($processDate == null ? 43 : ((Object)$processDate).hashCode());
        LocalDate $businessDate = this.getBusinessDate();
        result = result * 59 + ($businessDate == null ? 43 : ((Object)$businessDate).hashCode());
        String $fileId = this.getFileId();
        result = result * 59 + ($fileId == null ? 43 : $fileId.hashCode());
        return result;
    }

    public String toString() {
        return "OutGoingFileProcessingEntity(serialNumber=" + this.getSerialNumber() + ", lastUpdatedDate=" + String.valueOf(this.getLastUpdatedDate()) + ", lastUpdatedUser=" + this.getLastUpdatedUser() + ", institutionCode=" + this.getInstitutionCode() + ", interfaceCode=" + this.getInterfaceCode() + ", formatCode=" + this.getFormatCode() + ", fileName=" + this.getFileName() + ", generatedDate=" + String.valueOf(this.getGeneratedDate()) + ", generatedStatus=" + this.getGeneratedStatus() + ", processDate=" + String.valueOf(this.getProcessDate()) + ", businessDate=" + String.valueOf(this.getBusinessDate()) + ", processingJob=" + this.getProcessingJob() + ", totalTxnCount=" + this.getTotalTxnCount() + ", totalTxnAmount=" + this.getTotalTxnAmount() + ", totalAcceptanceTxnCount=" + this.getTotalAcceptanceTxnCount() + ", totalAcceptanceTxnAmount=" + this.getTotalAcceptanceTxnAmount() + ", fileId=" + this.getFileId() + ")";
    }
}

