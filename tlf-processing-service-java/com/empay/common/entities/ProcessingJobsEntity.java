// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.common.entities;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;

@Entity
@Table(name = "PROCESSING_JOBS")
public class ProcessingJobsEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRJ_SER_NUMBER")
    private Integer processSerialNo;
    @Column(name = "PRJ_LAST_UPDATED")
    private LocalDateTime lastUpdated;
    @Column(name = "PRJ_UPDATED_USER")
    private Integer user;
    @Column(name = "PRJ_INS_CODE")
    private Integer insCode;
    @Column(name = "PRJ_REF_NUMBER")
    private int processRefNumber;
    @Column(name = "PRJ_PARENT_SER_NUMBER")
    private Integer parentSerNumber;
    @Column(name = "PRJ_PROCESS_NAME")
    private String processName;
    @Column(name = "PRJ_START_TIME")
    private LocalDateTime processStartTime;
    @Column(name = "PRJ_END_TIME")
    private LocalDateTime processEndTime;
    @Column(name = "PRJ_STATUS")
    private Integer status;
    
    public static ProcessingJobsEntityBuilder builder() {
        return new ProcessingJobsEntityBuilder();
    }
    
    public ProcessingJobsEntity() {
    }
    
    public ProcessingJobsEntity(final Integer processSerialNo, final LocalDateTime lastUpdated, final Integer user, final Integer insCode, final int processRefNumber, final Integer parentSerNumber, final String processName, final LocalDateTime processStartTime, final LocalDateTime processEndTime, final Integer status) {
        this.processSerialNo = processSerialNo;
        this.lastUpdated = lastUpdated;
        this.user = user;
        this.insCode = insCode;
        this.processRefNumber = processRefNumber;
        this.parentSerNumber = parentSerNumber;
        this.processName = processName;
        this.processStartTime = processStartTime;
        this.processEndTime = processEndTime;
        this.status = status;
    }
    
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
    
    public void setProcessSerialNo(final Integer processSerialNo) {
        this.processSerialNo = processSerialNo;
    }
    
    public void setLastUpdated(final LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
    
    public void setUser(final Integer user) {
        this.user = user;
    }
    
    public void setInsCode(final Integer insCode) {
        this.insCode = insCode;
    }
    
    public void setProcessRefNumber(final int processRefNumber) {
        this.processRefNumber = processRefNumber;
    }
    
    public void setParentSerNumber(final Integer parentSerNumber) {
        this.parentSerNumber = parentSerNumber;
    }
    
    public void setProcessName(final String processName) {
        this.processName = processName;
    }
    
    public void setProcessStartTime(final LocalDateTime processStartTime) {
        this.processStartTime = processStartTime;
    }
    
    public void setProcessEndTime(final LocalDateTime processEndTime) {
        this.processEndTime = processEndTime;
    }
    
    public void setStatus(final Integer status) {
        this.status = status;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ProcessingJobsEntity)) {
            return false;
        }
        final ProcessingJobsEntity other = (ProcessingJobsEntity)o;
        if (!other.canEqual(this)) {
            return false;
        }
        if (this.getProcessRefNumber() != other.getProcessRefNumber()) {
            return false;
        }
        final Object this$processSerialNo = this.getProcessSerialNo();
        final Object other$processSerialNo = other.getProcessSerialNo();
        Label_0078: {
            if (this$processSerialNo == null) {
                if (other$processSerialNo == null) {
                    break Label_0078;
                }
            }
            else if (this$processSerialNo.equals(other$processSerialNo)) {
                break Label_0078;
            }
            return false;
        }
        final Object this$user = this.getUser();
        final Object other$user = other.getUser();
        Label_0115: {
            if (this$user == null) {
                if (other$user == null) {
                    break Label_0115;
                }
            }
            else if (this$user.equals(other$user)) {
                break Label_0115;
            }
            return false;
        }
        final Object this$insCode = this.getInsCode();
        final Object other$insCode = other.getInsCode();
        Label_0152: {
            if (this$insCode == null) {
                if (other$insCode == null) {
                    break Label_0152;
                }
            }
            else if (this$insCode.equals(other$insCode)) {
                break Label_0152;
            }
            return false;
        }
        final Object this$parentSerNumber = this.getParentSerNumber();
        final Object other$parentSerNumber = other.getParentSerNumber();
        Label_0189: {
            if (this$parentSerNumber == null) {
                if (other$parentSerNumber == null) {
                    break Label_0189;
                }
            }
            else if (this$parentSerNumber.equals(other$parentSerNumber)) {
                break Label_0189;
            }
            return false;
        }
        final Object this$status = this.getStatus();
        final Object other$status = other.getStatus();
        Label_0226: {
            if (this$status == null) {
                if (other$status == null) {
                    break Label_0226;
                }
            }
            else if (this$status.equals(other$status)) {
                break Label_0226;
            }
            return false;
        }
        final Object this$lastUpdated = this.getLastUpdated();
        final Object other$lastUpdated = other.getLastUpdated();
        Label_0263: {
            if (this$lastUpdated == null) {
                if (other$lastUpdated == null) {
                    break Label_0263;
                }
            }
            else if (this$lastUpdated.equals(other$lastUpdated)) {
                break Label_0263;
            }
            return false;
        }
        final Object this$processName = this.getProcessName();
        final Object other$processName = other.getProcessName();
        Label_0300: {
            if (this$processName == null) {
                if (other$processName == null) {
                    break Label_0300;
                }
            }
            else if (this$processName.equals(other$processName)) {
                break Label_0300;
            }
            return false;
        }
        final Object this$processStartTime = this.getProcessStartTime();
        final Object other$processStartTime = other.getProcessStartTime();
        Label_0337: {
            if (this$processStartTime == null) {
                if (other$processStartTime == null) {
                    break Label_0337;
                }
            }
            else if (this$processStartTime.equals(other$processStartTime)) {
                break Label_0337;
            }
            return false;
        }
        final Object this$processEndTime = this.getProcessEndTime();
        final Object other$processEndTime = other.getProcessEndTime();
        if (this$processEndTime == null) {
            if (other$processEndTime == null) {
                return true;
            }
        }
        else if (this$processEndTime.equals(other$processEndTime)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof ProcessingJobsEntity;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * 59 + this.getProcessRefNumber();
        final Object $processSerialNo = this.getProcessSerialNo();
        result = result * 59 + (($processSerialNo == null) ? 43 : $processSerialNo.hashCode());
        final Object $user = this.getUser();
        result = result * 59 + (($user == null) ? 43 : $user.hashCode());
        final Object $insCode = this.getInsCode();
        result = result * 59 + (($insCode == null) ? 43 : $insCode.hashCode());
        final Object $parentSerNumber = this.getParentSerNumber();
        result = result * 59 + (($parentSerNumber == null) ? 43 : $parentSerNumber.hashCode());
        final Object $status = this.getStatus();
        result = result * 59 + (($status == null) ? 43 : $status.hashCode());
        final Object $lastUpdated = this.getLastUpdated();
        result = result * 59 + (($lastUpdated == null) ? 43 : $lastUpdated.hashCode());
        final Object $processName = this.getProcessName();
        result = result * 59 + (($processName == null) ? 43 : $processName.hashCode());
        final Object $processStartTime = this.getProcessStartTime();
        result = result * 59 + (($processStartTime == null) ? 43 : $processStartTime.hashCode());
        final Object $processEndTime = this.getProcessEndTime();
        result = result * 59 + (($processEndTime == null) ? 43 : $processEndTime.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "ProcessingJobsEntity(processSerialNo=" + this.getProcessSerialNo() + ", lastUpdated=" + String.valueOf(this.getLastUpdated()) + ", user=" + this.getUser() + ", insCode=" + this.getInsCode() + ", processRefNumber=" + this.getProcessRefNumber() + ", parentSerNumber=" + this.getParentSerNumber() + ", processName=" + this.getProcessName() + ", processStartTime=" + String.valueOf(this.getProcessStartTime()) + ", processEndTime=" + String.valueOf(this.getProcessEndTime()) + ", status=" + this.getStatus();
    }
    
    public static class ProcessingJobsEntityBuilder
    {
        private Integer processSerialNo;
        private LocalDateTime lastUpdated;
        private Integer user;
        private Integer insCode;
        private int processRefNumber;
        private Integer parentSerNumber;
        private String processName;
        private LocalDateTime processStartTime;
        private LocalDateTime processEndTime;
        private Integer status;
        
        ProcessingJobsEntityBuilder() {
        }
        
        public ProcessingJobsEntityBuilder processSerialNo(final Integer processSerialNo) {
            this.processSerialNo = processSerialNo;
            return this;
        }
        
        public ProcessingJobsEntityBuilder lastUpdated(final LocalDateTime lastUpdated) {
            this.lastUpdated = lastUpdated;
            return this;
        }
        
        public ProcessingJobsEntityBuilder user(final Integer user) {
            this.user = user;
            return this;
        }
        
        public ProcessingJobsEntityBuilder insCode(final Integer insCode) {
            this.insCode = insCode;
            return this;
        }
        
        public ProcessingJobsEntityBuilder processRefNumber(final int processRefNumber) {
            this.processRefNumber = processRefNumber;
            return this;
        }
        
        public ProcessingJobsEntityBuilder parentSerNumber(final Integer parentSerNumber) {
            this.parentSerNumber = parentSerNumber;
            return this;
        }
        
        public ProcessingJobsEntityBuilder processName(final String processName) {
            this.processName = processName;
            return this;
        }
        
        public ProcessingJobsEntityBuilder processStartTime(final LocalDateTime processStartTime) {
            this.processStartTime = processStartTime;
            return this;
        }
        
        public ProcessingJobsEntityBuilder processEndTime(final LocalDateTime processEndTime) {
            this.processEndTime = processEndTime;
            return this;
        }
        
        public ProcessingJobsEntityBuilder status(final Integer status) {
            this.status = status;
            return this;
        }
        
        public ProcessingJobsEntity build() {
            return new ProcessingJobsEntity(this.processSerialNo, this.lastUpdated, this.user, this.insCode, this.processRefNumber, this.parentSerNumber, this.processName, this.processStartTime, this.processEndTime, this.status);
        }
        
        @Override
        public String toString() {
            return "ProcessingJobsEntity.ProcessingJobsEntityBuilder(processSerialNo=" + this.processSerialNo + ", lastUpdated=" + String.valueOf(this.lastUpdated) + ", user=" + this.user + ", insCode=" + this.insCode + ", processRefNumber=" + this.processRefNumber + ", parentSerNumber=" + this.parentSerNumber + ", processName=" + this.processName + ", processStartTime=" + String.valueOf(this.processStartTime) + ", processEndTime=" + String.valueOf(this.processEndTime) + ", status=" + this.status;
        }
    }
}
