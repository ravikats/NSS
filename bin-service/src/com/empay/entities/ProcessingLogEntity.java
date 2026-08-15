/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.entities.ProcessingLogEntity
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
import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Table(name="PROCESSING_LOG")
public class ProcessingLogEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="PRL_SER_NUMBER")
    private Integer serialNumber;
    @Column(name="PRL_LAST_UPDATED")
    private LocalDateTime lastupdated;
    @Column(name="PRL_UPDATED_USER")
    private int updatedUser;
    @Column(name="PRL_INS_CODE")
    private int institution;
    @Column(name="PRL_PROC_DATE")
    private Date processDate;
    @Column(name="PRL_PRJ_SER_NUMBER")
    private int processBatch;
    @Column(name="PRL_DESCRIPTION")
    private String description;

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

    public Date getProcessDate() {
        return this.processDate;
    }

    public int getProcessBatch() {
        return this.processBatch;
    }

    public String getDescription() {
        return this.description;
    }

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setLastupdated(LocalDateTime lastupdated) {
        this.lastupdated = lastupdated;
    }

    public void setUpdatedUser(int updatedUser) {
        this.updatedUser = updatedUser;
    }

    public void setInstitution(int institution) {
        this.institution = institution;
    }

    public void setProcessDate(Date processDate) {
        this.processDate = processDate;
    }

    public void setProcessBatch(int processBatch) {
        this.processBatch = processBatch;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ProcessingLogEntity)) {
            return false;
        }
        ProcessingLogEntity other = (ProcessingLogEntity)o;
        if (!other.canEqual((Object)this)) {
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
        Integer this$serialNumber = this.getSerialNumber();
        Integer other$serialNumber = other.getSerialNumber();
        if (this$serialNumber == null ? other$serialNumber != null : !((Object)this$serialNumber).equals(other$serialNumber)) {
            return false;
        }
        LocalDateTime this$lastupdated = this.getLastupdated();
        LocalDateTime other$lastupdated = other.getLastupdated();
        if (this$lastupdated == null ? other$lastupdated != null : !((Object)this$lastupdated).equals(other$lastupdated)) {
            return false;
        }
        Date this$processDate = this.getProcessDate();
        Date other$processDate = other.getProcessDate();
        if (this$processDate == null ? other$processDate != null : !((Object)this$processDate).equals(other$processDate)) {
            return false;
        }
        String this$description = this.getDescription();
        String other$description = other.getDescription();
        return !(this$description == null ? other$description != null : !this$description.equals(other$description));
    }

    protected boolean canEqual(Object other) {
        return other instanceof ProcessingLogEntity;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + this.getUpdatedUser();
        result = result * 59 + this.getInstitution();
        result = result * 59 + this.getProcessBatch();
        Integer $serialNumber = this.getSerialNumber();
        result = result * 59 + ($serialNumber == null ? 43 : ((Object)$serialNumber).hashCode());
        LocalDateTime $lastupdated = this.getLastupdated();
        result = result * 59 + ($lastupdated == null ? 43 : ((Object)$lastupdated).hashCode());
        Date $processDate = this.getProcessDate();
        result = result * 59 + ($processDate == null ? 43 : ((Object)$processDate).hashCode());
        String $description = this.getDescription();
        result = result * 59 + ($description == null ? 43 : $description.hashCode());
        return result;
    }

    public String toString() {
        return "ProcessingLogEntity(serialNumber=" + this.getSerialNumber() + ", lastupdated=" + String.valueOf(this.getLastupdated()) + ", updatedUser=" + this.getUpdatedUser() + ", institution=" + this.getInstitution() + ", processDate=" + String.valueOf(this.getProcessDate()) + ", processBatch=" + this.getProcessBatch() + ", description=" + this.getDescription() + ")";
    }
}

