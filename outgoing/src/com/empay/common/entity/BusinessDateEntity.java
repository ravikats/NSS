/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.common.entity.BusinessDateEntity
 *  jakarta.persistence.Column
 *  jakarta.persistence.Entity
 *  jakarta.persistence.Id
 *  jakarta.persistence.Table
 */
package com.empay.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name="BUSINESS_DATE")
public class BusinessDateEntity {
    @Id
    @Column(name="BDT_INS_CODE")
    private int institutionCode;
    @Column(name="BDT_LAST_UPDATED")
    private Date lastUpdatedDate;
    @Column(name="BDT_UPDATED_USER")
    private int lastUpdatedUser;
    @Column(name="BDT_BUSINESS_DATE")
    private LocalDate businessDate;
    @Column(name="BDT_LAST_BUSINESS_DATE")
    private LocalDate lastBusinessDate;

    public int getInstitutionCode() {
        return this.institutionCode;
    }

    public Date getLastUpdatedDate() {
        return this.lastUpdatedDate;
    }

    public int getLastUpdatedUser() {
        return this.lastUpdatedUser;
    }

    public LocalDate getBusinessDate() {
        return this.businessDate;
    }

    public LocalDate getLastBusinessDate() {
        return this.lastBusinessDate;
    }

    public void setInstitutionCode(int institutionCode) {
        this.institutionCode = institutionCode;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public void setLastUpdatedUser(int lastUpdatedUser) {
        this.lastUpdatedUser = lastUpdatedUser;
    }

    public void setBusinessDate(LocalDate businessDate) {
        this.businessDate = businessDate;
    }

    public void setLastBusinessDate(LocalDate lastBusinessDate) {
        this.lastBusinessDate = lastBusinessDate;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof BusinessDateEntity)) {
            return false;
        }
        BusinessDateEntity other = (BusinessDateEntity)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        if (this.getInstitutionCode() != other.getInstitutionCode()) {
            return false;
        }
        if (this.getLastUpdatedUser() != other.getLastUpdatedUser()) {
            return false;
        }
        Date this$lastUpdatedDate = this.getLastUpdatedDate();
        Date other$lastUpdatedDate = other.getLastUpdatedDate();
        if (this$lastUpdatedDate == null ? other$lastUpdatedDate != null : !((Object)this$lastUpdatedDate).equals(other$lastUpdatedDate)) {
            return false;
        }
        LocalDate this$businessDate = this.getBusinessDate();
        LocalDate other$businessDate = other.getBusinessDate();
        if (this$businessDate == null ? other$businessDate != null : !((Object)this$businessDate).equals(other$businessDate)) {
            return false;
        }
        LocalDate this$lastBusinessDate = this.getLastBusinessDate();
        LocalDate other$lastBusinessDate = other.getLastBusinessDate();
        return !(this$lastBusinessDate == null ? other$lastBusinessDate != null : !((Object)this$lastBusinessDate).equals(other$lastBusinessDate));
    }

    protected boolean canEqual(Object other) {
        return other instanceof BusinessDateEntity;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + this.getInstitutionCode();
        result = result * 59 + this.getLastUpdatedUser();
        Date $lastUpdatedDate = this.getLastUpdatedDate();
        result = result * 59 + ($lastUpdatedDate == null ? 43 : ((Object)$lastUpdatedDate).hashCode());
        LocalDate $businessDate = this.getBusinessDate();
        result = result * 59 + ($businessDate == null ? 43 : ((Object)$businessDate).hashCode());
        LocalDate $lastBusinessDate = this.getLastBusinessDate();
        result = result * 59 + ($lastBusinessDate == null ? 43 : ((Object)$lastBusinessDate).hashCode());
        return result;
    }

    public String toString() {
        return "BusinessDateEntity(institutionCode=" + this.getInstitutionCode() + ", lastUpdatedDate=" + String.valueOf(this.getLastUpdatedDate()) + ", lastUpdatedUser=" + this.getLastUpdatedUser() + ", businessDate=" + String.valueOf(this.getBusinessDate()) + ", lastBusinessDate=" + String.valueOf(this.getLastBusinessDate()) + ")";
    }
}

