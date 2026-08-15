/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.entities.BusinessDateEntity
 *  jakarta.persistence.Column
 *  jakarta.persistence.Entity
 *  jakarta.persistence.Id
 *  jakarta.persistence.Table
 */
package com.empay.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name="BUSINESS_DATE")
public class BusinessDateEntity {
    @Id
    @Column(name="BDT_INS_CODE")
    private Integer insCode;
    @Column(name="BDT_BUSINESS_DATE")
    private LocalDate businessDate;
    @Column(name="BDT_LAST_BUSINESS_DATE")
    private LocalDate lastBusinessDate;

    public Integer getInsCode() {
        return this.insCode;
    }

    public LocalDate getBusinessDate() {
        return this.businessDate;
    }

    public LocalDate getLastBusinessDate() {
        return this.lastBusinessDate;
    }

    public void setInsCode(Integer insCode) {
        this.insCode = insCode;
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
        Integer this$insCode = this.getInsCode();
        Integer other$insCode = other.getInsCode();
        if (this$insCode == null ? other$insCode != null : !((Object)this$insCode).equals(other$insCode)) {
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
        Integer $insCode = this.getInsCode();
        result = result * 59 + ($insCode == null ? 43 : ((Object)$insCode).hashCode());
        LocalDate $businessDate = this.getBusinessDate();
        result = result * 59 + ($businessDate == null ? 43 : ((Object)$businessDate).hashCode());
        LocalDate $lastBusinessDate = this.getLastBusinessDate();
        result = result * 59 + ($lastBusinessDate == null ? 43 : ((Object)$lastBusinessDate).hashCode());
        return result;
    }

    public String toString() {
        return "BusinessDateEntity(insCode=" + this.getInsCode() + ", businessDate=" + String.valueOf(this.getBusinessDate()) + ", lastBusinessDate=" + String.valueOf(this.getLastBusinessDate()) + ")";
    }
}

