// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.entities;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;

@Entity
@Table(name = "BUSINESS_DATE")
public class BusinessDateEntity
{
    @Id
    @Column(name = "BDT_INS_CODE")
    private Integer insCode;
    @Column(name = "BDT_BUSINESS_DATE")
    private LocalDate businessDate;
    
    public Integer getInsCode() {
        return this.insCode;
    }
    
    public LocalDate getBusinessDate() {
        return this.businessDate;
    }
    
    public void setInsCode(final Integer insCode) {
        this.insCode = insCode;
    }
    
    public void setBusinessDate(final LocalDate businessDate) {
        this.businessDate = businessDate;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof BusinessDateEntity)) {
            return false;
        }
        final BusinessDateEntity other = (BusinessDateEntity)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$insCode = this.getInsCode();
        final Object other$insCode = other.getInsCode();
        Label_0065: {
            if (this$insCode == null) {
                if (other$insCode == null) {
                    break Label_0065;
                }
            }
            else if (this$insCode.equals(other$insCode)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$businessDate = this.getBusinessDate();
        final Object other$businessDate = other.getBusinessDate();
        if (this$businessDate == null) {
            if (other$businessDate == null) {
                return true;
            }
        }
        else if (this$businessDate.equals(other$businessDate)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof BusinessDateEntity;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $insCode = this.getInsCode();
        result = result * 59 + (($insCode == null) ? 43 : $insCode.hashCode());
        final Object $businessDate = this.getBusinessDate();
        result = result * 59 + (($businessDate == null) ? 43 : $businessDate.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "BusinessDateEntity(insCode=" + this.getInsCode() + ", businessDate=" + String.valueOf(this.getBusinessDate());
    }
}
