// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;

@Entity
@Table(name = "IPM_ECOM_INDICATOR")
public class IpmEcomIndicatorEntity
{
    @Id
    @Column(name = "IEI_SER_NUMBER")
    private Integer serNumber;
    @Column(name = "IEI_IIC_SER_NUMBER")
    private Integer iicSerNumber;
    @Column(name = "IEI_ECOM_INDICATOR")
    private Character ecomIndicator;
    
    public Integer getSerNumber() {
        return this.serNumber;
    }
    
    public Integer getIicSerNumber() {
        return this.iicSerNumber;
    }
    
    public Character getEcomIndicator() {
        return this.ecomIndicator;
    }
    
    public void setSerNumber(final Integer serNumber) {
        this.serNumber = serNumber;
    }
    
    public void setIicSerNumber(final Integer iicSerNumber) {
        this.iicSerNumber = iicSerNumber;
    }
    
    public void setEcomIndicator(final Character ecomIndicator) {
        this.ecomIndicator = ecomIndicator;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof IpmEcomIndicatorEntity)) {
            return false;
        }
        final IpmEcomIndicatorEntity other = (IpmEcomIndicatorEntity)o;
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
        final Object this$iicSerNumber = this.getIicSerNumber();
        final Object other$iicSerNumber = other.getIicSerNumber();
        Label_0102: {
            if (this$iicSerNumber == null) {
                if (other$iicSerNumber == null) {
                    break Label_0102;
                }
            }
            else if (this$iicSerNumber.equals(other$iicSerNumber)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$ecomIndicator = this.getEcomIndicator();
        final Object other$ecomIndicator = other.getEcomIndicator();
        if (this$ecomIndicator == null) {
            if (other$ecomIndicator == null) {
                return true;
            }
        }
        else if (this$ecomIndicator.equals(other$ecomIndicator)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof IpmEcomIndicatorEntity;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $serNumber = this.getSerNumber();
        result = result * 59 + (($serNumber == null) ? 43 : $serNumber.hashCode());
        final Object $iicSerNumber = this.getIicSerNumber();
        result = result * 59 + (($iicSerNumber == null) ? 43 : $iicSerNumber.hashCode());
        final Object $ecomIndicator = this.getEcomIndicator();
        result = result * 59 + (($ecomIndicator == null) ? 43 : $ecomIndicator.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "IpmEcomIndicatorEntity(serNumber=" + this.getSerNumber() + ", iicSerNumber=" + this.getIicSerNumber() + ", ecomIndicator=" + this.getEcomIndicator();
    }
}
