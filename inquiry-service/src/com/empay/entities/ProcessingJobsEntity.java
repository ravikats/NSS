/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.entities.ProcessingJobsEntity
 *  jakarta.persistence.Column
 *  jakarta.persistence.Entity
 *  jakarta.persistence.GeneratedValue
 *  jakarta.persistence.GenerationType
 *  jakarta.persistence.Id
 *  jakarta.persistence.Table
 */
package com.empay.entities;

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
    private Integer processSerialNo;
    @Column(name="PRJ_LAST_UPDATED")
    private LocalDateTime lastUpdated;
    @Column(name="PRJ_UPDATED_USER")
    private Integer user;
    @Column(name="PRJ_INS_CODE")
    private Integer insCode;
    @Column(name="PRJ_REF_NUMBER")
    private int processRefNumber;
    @Column(name="PRJ_PARENT_SER_NUMBER")
    private Integer parentSerNumber;
    @Column(name="PRJ_PROCESS_NAME")
    private String processName;
    @Column(name="PRJ_START_TIME")
    private LocalDateTime processStartTime;
    @Column(name="PRJ_END_TIME")
    private LocalDateTime processEndTime;
    @Column(name="PRJ_STATUS")
    private Integer status;

    public Integer getProcessSerialNo() {
        return this.processSerialNo;
    }

    public LocalDateTime getLastUpdated() {
        return this.lastUpdated;
    }

    public Integer getUser() {
        return this.user;
    }

    public Integer getInsCode() {
        return this.insCode;
    }

    public int getProcessRefNumber() {
        return this.processRefNumber;
    }

    public Integer getParentSerNumber() {
        return this.parentSerNumber;
    }

    public String getProcessName() {
        return this.processName;
    }

    public LocalDateTime getProcessStartTime() {
        return this.processStartTime;
    }

    public LocalDateTime getProcessEndTime() {
        return this.processEndTime;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setProcessSerialNo(Integer processSerialNo) {
        this.processSerialNo = processSerialNo;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public void setInsCode(Integer insCode) {
        this.insCode = insCode;
    }

    public void setProcessRefNumber(int processRefNumber) {
        this.processRefNumber = processRefNumber;
    }

    public void setParentSerNumber(Integer parentSerNumber) {
        this.parentSerNumber = parentSerNumber;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public void setProcessStartTime(LocalDateTime processStartTime) {
        this.processStartTime = processStartTime;
    }

    public void setProcessEndTime(LocalDateTime processEndTime) {
        this.processEndTime = processEndTime;
    }

    public void setStatus(Integer status) {
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
        if (this.getProcessRefNumber() != other.getProcessRefNumber()) {
            return false;
        }
        Integer this$processSerialNo = this.getProcessSerialNo();
        Integer other$processSerialNo = other.getProcessSerialNo();
        if (this$processSerialNo == null ? other$processSerialNo != null : !((Object)this$processSerialNo).equals(other$processSerialNo)) {
            return false;
        }
        Integer this$user = this.getUser();
        Integer other$user = other.getUser();
        if (this$user == null ? other$user != null : !((Object)this$user).equals(other$user)) {
            return false;
        }
        Integer this$insCode = this.getInsCode();
        Integer other$insCode = other.getInsCode();
        if (this$insCode == null ? other$insCode != null : !((Object)this$insCode).equals(other$insCode)) {
            return false;
        }
        Integer this$parentSerNumber = this.getParentSerNumber();
        Integer other$parentSerNumber = other.getParentSerNumber();
        if (this$parentSerNumber == null ? other$parentSerNumber != null : !((Object)this$parentSerNumber).equals(other$parentSerNumber)) {
            return false;
        }
        Integer this$status = this.getStatus();
        Integer other$status = other.getStatus();
        if (this$status == null ? other$status != null : !((Object)this$status).equals(other$status)) {
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
        LocalDateTime this$processStartTime = this.getProcessStartTime();
        LocalDateTime other$processStartTime = other.getProcessStartTime();
        if (this$processStartTime == null ? other$processStartTime != null : !((Object)this$processStartTime).equals(other$processStartTime)) {
            return false;
        }
        LocalDateTime this$processEndTime = this.getProcessEndTime();
        LocalDateTime other$processEndTime = other.getProcessEndTime();
        return !(this$processEndTime == null ? other$processEndTime != null : !((Object)this$processEndTime).equals(other$processEndTime));
    }

    protected boolean canEqual(Object other) {
        return other instanceof ProcessingJobsEntity;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + this.getProcessRefNumber();
        Integer $processSerialNo = this.getProcessSerialNo();
        result = result * 59 + ($processSerialNo == null ? 43 : ((Object)$processSerialNo).hashCode());
        Integer $user = this.getUser();
        result = result * 59 + ($user == null ? 43 : ((Object)$user).hashCode());
        Integer $insCode = this.getInsCode();
        result = result * 59 + ($insCode == null ? 43 : ((Object)$insCode).hashCode());
        Integer $parentSerNumber = this.getParentSerNumber();
        result = result * 59 + ($parentSerNumber == null ? 43 : ((Object)$parentSerNumber).hashCode());
        Integer $status = this.getStatus();
        result = result * 59 + ($status == null ? 43 : ((Object)$status).hashCode());
        LocalDateTime $lastUpdated = this.getLastUpdated();
        result = result * 59 + ($lastUpdated == null ? 43 : ((Object)$lastUpdated).hashCode());
        String $processName = this.getProcessName();
        result = result * 59 + ($processName == null ? 43 : $processName.hashCode());
        LocalDateTime $processStartTime = this.getProcessStartTime();
        result = result * 59 + ($processStartTime == null ? 43 : ((Object)$processStartTime).hashCode());
        LocalDateTime $processEndTime = this.getProcessEndTime();
        result = result * 59 + ($processEndTime == null ? 43 : ((Object)$processEndTime).hashCode());
        return result;
    }

    public String toString() {
        return "ProcessingJobsEntity(processSerialNo=" + this.getProcessSerialNo() + ", lastUpdated=" + String.valueOf(this.getLastUpdated()) + ", user=" + this.getUser() + ", insCode=" + this.getInsCode() + ", processRefNumber=" + this.getProcessRefNumber() + ", parentSerNumber=" + this.getParentSerNumber() + ", processName=" + this.getProcessName() + ", processStartTime=" + String.valueOf(this.getProcessStartTime()) + ", processEndTime=" + String.valueOf(this.getProcessEndTime()) + ", status=" + this.getStatus() + ")";
    }
}

