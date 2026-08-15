/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.entities.ProcessingJobsEntity
 *  com.empay.entities.ProcessingJobsEntity$ProcessingJobsEntityBuilder
 *  jakarta.persistence.Column
 *  jakarta.persistence.Entity
 *  jakarta.persistence.GeneratedValue
 *  jakarta.persistence.GenerationType
 *  jakarta.persistence.Id
 *  jakarta.persistence.Table
 */
package com.empay.entities;

import com.empay.entities.ProcessingJobsEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name="PROCESSING_JOBS")
public class ProcessingJobsEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="PRJ_SER_NUMBER")
    private Integer serialNumber;
    @Column(name="PRJ_LAST_UPDATED")
    private LocalDateTime lastUpdated;
    @Column(name="PRJ_UPDATED_USER")
    private int updatedUser;
    @Column(name="PRJ_INS_CODE")
    private int institutionCode;
    @Column(name="PRJ_REF_NUMBER")
    private int refNumber;
    @Column(name="PRJ_PARENT_SER_NUMBER")
    private int parentSerialNumber;
    @Column(name="PRJ_PROCESS_NAME")
    private String processName;
    @Column(name="PRJ_START_TIME")
    private LocalDateTime startTime;
    @Column(name="PRJ_END_TIME")
    private LocalDateTime endTime;
    @Column(name="PRJ_STATUS")
    private int status;

    public static ProcessingJobsEntityBuilder builder() {
        return new ProcessingJobsEntityBuilder();
    }

    public Integer getSerialNumber() {
        return this.serialNumber;
    }

    public LocalDateTime getLastUpdated() {
        return this.lastUpdated;
    }

    public int getUpdatedUser() {
        return this.updatedUser;
    }

    public int getInstitutionCode() {
        return this.institutionCode;
    }

    public int getRefNumber() {
        return this.refNumber;
    }

    public int getParentSerialNumber() {
        return this.parentSerialNumber;
    }

    public String getProcessName() {
        return this.processName;
    }

    public LocalDateTime getStartTime() {
        return this.startTime;
    }

    public LocalDateTime getEndTime() {
        return this.endTime;
    }

    public int getStatus() {
        return this.status;
    }

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public void setUpdatedUser(int updatedUser) {
        this.updatedUser = updatedUser;
    }

    public void setInstitutionCode(int institutionCode) {
        this.institutionCode = institutionCode;
    }

    public void setRefNumber(int refNumber) {
        this.refNumber = refNumber;
    }

    public void setParentSerialNumber(int parentSerialNumber) {
        this.parentSerialNumber = parentSerialNumber;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ProcessingJobsEntity)) {
            return false;
        }
        ProcessingJobsEntity other = (ProcessingJobsEntity)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        if (this.getUpdatedUser() != other.getUpdatedUser()) {
            return false;
        }
        if (this.getInstitutionCode() != other.getInstitutionCode()) {
            return false;
        }
        if (this.getRefNumber() != other.getRefNumber()) {
            return false;
        }
        if (this.getParentSerialNumber() != other.getParentSerialNumber()) {
            return false;
        }
        if (this.getStatus() != other.getStatus()) {
            return false;
        }
        Integer this$serialNumber = this.getSerialNumber();
        Integer other$serialNumber = other.getSerialNumber();
        if (this$serialNumber == null ? other$serialNumber != null : !((Object)this$serialNumber).equals(other$serialNumber)) {
            return false;
        }
        LocalDateTime this$lastUpdated = this.getLastUpdated();
        LocalDateTime other$lastUpdated = other.getLastUpdated();
        if (this$lastUpdated == null ? other$lastUpdated != null : !((Object)this$lastUpdated).equals(other$lastUpdated)) {
            return false;
        }
        String this$processName = this.getProcessName();
        String other$processName = other.getProcessName();
        if (this$processName == null ? other$processName != null : !this$processName.equals(other$processName)) {
            return false;
        }
        LocalDateTime this$startTime = this.getStartTime();
        LocalDateTime other$startTime = other.getStartTime();
        if (this$startTime == null ? other$startTime != null : !((Object)this$startTime).equals(other$startTime)) {
            return false;
        }
        LocalDateTime this$endTime = this.getEndTime();
        LocalDateTime other$endTime = other.getEndTime();
        return !(this$endTime == null ? other$endTime != null : !((Object)this$endTime).equals(other$endTime));
    }

    protected boolean canEqual(Object other) {
        return other instanceof ProcessingJobsEntity;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + this.getUpdatedUser();
        result = result * 59 + this.getInstitutionCode();
        result = result * 59 + this.getRefNumber();
        result = result * 59 + this.getParentSerialNumber();
        result = result * 59 + this.getStatus();
        Integer $serialNumber = this.getSerialNumber();
        result = result * 59 + ($serialNumber == null ? 43 : ((Object)$serialNumber).hashCode());
        LocalDateTime $lastUpdated = this.getLastUpdated();
        result = result * 59 + ($lastUpdated == null ? 43 : ((Object)$lastUpdated).hashCode());
        String $processName = this.getProcessName();
        result = result * 59 + ($processName == null ? 43 : $processName.hashCode());
        LocalDateTime $startTime = this.getStartTime();
        result = result * 59 + ($startTime == null ? 43 : ((Object)$startTime).hashCode());
        LocalDateTime $endTime = this.getEndTime();
        result = result * 59 + ($endTime == null ? 43 : ((Object)$endTime).hashCode());
        return result;
    }

    public String toString() {
        return "ProcessingJobsEntity(serialNumber=" + this.getSerialNumber() + ", lastUpdated=" + String.valueOf(this.getLastUpdated()) + ", updatedUser=" + this.getUpdatedUser() + ", institutionCode=" + this.getInstitutionCode() + ", refNumber=" + this.getRefNumber() + ", parentSerialNumber=" + this.getParentSerialNumber() + ", processName=" + this.getProcessName() + ", startTime=" + String.valueOf(this.getStartTime()) + ", endTime=" + String.valueOf(this.getEndTime()) + ", status=" + this.getStatus() + ")";
    }

    public ProcessingJobsEntity() {
    }

    public ProcessingJobsEntity(Integer serialNumber, LocalDateTime lastUpdated, int updatedUser, int institutionCode, int refNumber, int parentSerialNumber, String processName, LocalDateTime startTime, LocalDateTime endTime, int status) {
        this.serialNumber = serialNumber;
        this.lastUpdated = lastUpdated;
        this.updatedUser = updatedUser;
        this.institutionCode = institutionCode;
        this.refNumber = refNumber;
        this.parentSerialNumber = parentSerialNumber;
        this.processName = processName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }
}

