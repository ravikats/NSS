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
@Table(name = "PROCESSING_LOG")
public class ProcessingLogEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRL_SER_NUMBER")
    private Integer serialNumber;
    @Column(name = "PRL_LAST_UPDATED")
    private LocalDateTime lastupdated;
    @Column(name = "PRL_UPDATED_USER")
    private int updatedUser;
    @Column(name = "PRL_INS_CODE")
    private int institution;
    @Column(name = "PRL_PROC_DATE")
    private LocalDate processDate;
    @Column(name = "PRL_PRJ_SER_NUMBER")
    private int processBatch;
    @Column(name = "PRL_DESCRIPTION")
    private String description;
    
    public static ProcessingLogEntityBuilder builder() {
        return new ProcessingLogEntityBuilder();
    }
    
    public ProcessingLogEntity() {
    }
    
    public ProcessingLogEntity(final Integer serialNumber, final LocalDateTime lastupdated, final int updatedUser, final int institution, final LocalDate processDate, final int processBatch, final String description) {
        this.serialNumber = serialNumber;
        this.lastupdated = lastupdated;
        this.updatedUser = updatedUser;
        this.institution = institution;
        this.processDate = processDate;
        this.processBatch = processBatch;
        this.description = description;
    }
    
    public Integer getSerialNumber() {
        return this.serialNumber;
    }
    
    public LocalDateTime getLastupdated() {
        return this.lastupdated;
    }
    
    public int getUpdatedUser() {
        return this.updatedUser;
    }
    
    public int getInstitution() {
        return this.institution;
    }
    
    public LocalDate getProcessDate() {
        return this.processDate;
    }
    
    public int getProcessBatch() {
        return this.processBatch;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public void setSerialNumber(final Integer serialNumber) {
        this.serialNumber = serialNumber;
    }
    
    public void setLastupdated(final LocalDateTime lastupdated) {
        this.lastupdated = lastupdated;
    }
    
    public void setUpdatedUser(final int updatedUser) {
        this.updatedUser = updatedUser;
    }
    
    public void setInstitution(final int institution) {
        this.institution = institution;
    }
    
    public void setProcessDate(final LocalDate processDate) {
        this.processDate = processDate;
    }
    
    public void setProcessBatch(final int processBatch) {
        this.processBatch = processBatch;
    }
    
    public void setDescription(final String description) {
        this.description = description;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ProcessingLogEntity)) {
            return false;
        }
        final ProcessingLogEntity other = (ProcessingLogEntity)o;
        if (!other.canEqual(this)) {
            return false;
        }
        if (this.getUpdatedUser() != other.getUpdatedUser()) {
            return false;
        }
        if (this.getInstitution() != other.getInstitution()) {
            return false;
        }
        if (this.getProcessBatch() != other.getProcessBatch()) {
            return false;
        }
        final Object this$serialNumber = this.getSerialNumber();
        final Object other$serialNumber = other.getSerialNumber();
        Label_0104: {
            if (this$serialNumber == null) {
                if (other$serialNumber == null) {
                    break Label_0104;
                }
            }
            else if (this$serialNumber.equals(other$serialNumber)) {
                break Label_0104;
            }
            return false;
        }
        final Object this$lastupdated = this.getLastupdated();
        final Object other$lastupdated = other.getLastupdated();
        Label_0141: {
            if (this$lastupdated == null) {
                if (other$lastupdated == null) {
                    break Label_0141;
                }
            }
            else if (this$lastupdated.equals(other$lastupdated)) {
                break Label_0141;
            }
            return false;
        }
        final Object this$processDate = this.getProcessDate();
        final Object other$processDate = other.getProcessDate();
        Label_0178: {
            if (this$processDate == null) {
                if (other$processDate == null) {
                    break Label_0178;
                }
            }
            else if (this$processDate.equals(other$processDate)) {
                break Label_0178;
            }
            return false;
        }
        final Object this$description = this.getDescription();
        final Object other$description = other.getDescription();
        if (this$description == null) {
            if (other$description == null) {
                return true;
            }
        }
        else if (this$description.equals(other$description)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof ProcessingLogEntity;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * 59 + this.getUpdatedUser();
        result = result * 59 + this.getInstitution();
        result = result * 59 + this.getProcessBatch();
        final Object $serialNumber = this.getSerialNumber();
        result = result * 59 + (($serialNumber == null) ? 43 : $serialNumber.hashCode());
        final Object $lastupdated = this.getLastupdated();
        result = result * 59 + (($lastupdated == null) ? 43 : $lastupdated.hashCode());
        final Object $processDate = this.getProcessDate();
        result = result * 59 + (($processDate == null) ? 43 : $processDate.hashCode());
        final Object $description = this.getDescription();
        result = result * 59 + (($description == null) ? 43 : $description.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "ProcessingLogEntity(serialNumber=" + this.getSerialNumber() + ", lastupdated=" + String.valueOf(this.getLastupdated()) + ", updatedUser=" + this.getUpdatedUser() + ", institution=" + this.getInstitution() + ", processDate=" + String.valueOf(this.getProcessDate()) + ", processBatch=" + this.getProcessBatch() + ", description=" + this.getDescription();
    }
    
    public static class ProcessingLogEntityBuilder
    {
        private Integer serialNumber;
        private LocalDateTime lastupdated;
        private int updatedUser;
        private int institution;
        private LocalDate processDate;
        private int processBatch;
        private String description;
        
        ProcessingLogEntityBuilder() {
        }
        
        public ProcessingLogEntityBuilder serialNumber(final Integer serialNumber) {
            this.serialNumber = serialNumber;
            return this;
        }
        
        public ProcessingLogEntityBuilder lastupdated(final LocalDateTime lastupdated) {
            this.lastupdated = lastupdated;
            return this;
        }
        
        public ProcessingLogEntityBuilder updatedUser(final int updatedUser) {
            this.updatedUser = updatedUser;
            return this;
        }
        
        public ProcessingLogEntityBuilder institution(final int institution) {
            this.institution = institution;
            return this;
        }
        
        public ProcessingLogEntityBuilder processDate(final LocalDate processDate) {
            this.processDate = processDate;
            return this;
        }
        
        public ProcessingLogEntityBuilder processBatch(final int processBatch) {
            this.processBatch = processBatch;
            return this;
        }
        
        public ProcessingLogEntityBuilder description(final String description) {
            this.description = description;
            return this;
        }
        
        public ProcessingLogEntity build() {
            return new ProcessingLogEntity(this.serialNumber, this.lastupdated, this.updatedUser, this.institution, this.processDate, this.processBatch, this.description);
        }
        
        @Override
        public String toString() {
            return "ProcessingLogEntity.ProcessingLogEntityBuilder(serialNumber=" + this.serialNumber + ", lastupdated=" + String.valueOf(this.lastupdated) + ", updatedUser=" + this.updatedUser + ", institution=" + this.institution + ", processDate=" + String.valueOf(this.processDate) + ", processBatch=" + this.processBatch + ", description=" + this.description;
        }
    }
}
