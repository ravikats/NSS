// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;

@Entity
@Table(name = "IPM_MCC_CAB_LIST")
public class IpmMccCabListEntity
{
    @Id
    @Column(name = "MCL_SER_NUMBER")
    private Integer serNumber;
    @Column(name = "MCL_ME_CATEGORY")
    private String meCategory;
    @Column(name = "MCL_CAB_PROGRAM")
    private String cabProgram;
    
    public Integer getSerNumber() {
        return this.serNumber;
    }
    
    public String getMeCategory() {
        return this.meCategory;
    }
    
    public String getCabProgram() {
        return this.cabProgram;
    }
    
    public void setSerNumber(final Integer serNumber) {
        this.serNumber = serNumber;
    }
    
    public void setMeCategory(final String meCategory) {
        this.meCategory = meCategory;
    }
    
    public void setCabProgram(final String cabProgram) {
        this.cabProgram = cabProgram;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof IpmMccCabListEntity)) {
            return false;
        }
        final IpmMccCabListEntity other = (IpmMccCabListEntity)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$serNumber = this.getSerNumber();
        final Object other$serNumber = other.getSerNumber();
        Label_0065: {
            if (this$serNumber == null) {
                if (other$serNumber == null) {
                    break Label_0065;
                }
            }
            else if (this$serNumber.equals(other$serNumber)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$meCategory = this.getMeCategory();
        final Object other$meCategory = other.getMeCategory();
        Label_0102: {
            if (this$meCategory == null) {
                if (other$meCategory == null) {
                    break Label_0102;
                }
            }
            else if (this$meCategory.equals(other$meCategory)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$cabProgram = this.getCabProgram();
        final Object other$cabProgram = other.getCabProgram();
        if (this$cabProgram == null) {
            if (other$cabProgram == null) {
                return true;
            }
        }
        else if (this$cabProgram.equals(other$cabProgram)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof IpmMccCabListEntity;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $serNumber = this.getSerNumber();
        result = result * 59 + (($serNumber == null) ? 43 : $serNumber.hashCode());
        final Object $meCategory = this.getMeCategory();
        result = result * 59 + (($meCategory == null) ? 43 : $meCategory.hashCode());
        final Object $cabProgram = this.getCabProgram();
        result = result * 59 + (($cabProgram == null) ? 43 : $cabProgram.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "IpmMccCabListEntity(serNumber=" + this.getSerNumber() + ", meCategory=" + this.getMeCategory() + ", cabProgram=" + this.getCabProgram();
    }
}
