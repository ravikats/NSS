// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;

@Entity
@Table(name = "IPM_SERVICE_CODES")
public class IpmServiceCodesEntity
{
    @Id
    @Column(name = "ISC_SER_NUMBER")
    private Integer serNumber;
    @Column(name = "ISC_IIC_SER_NUMBER")
    private Integer iicSerNumber;
    @Column(name = "ISC_SERV_CODE")
    private Integer serviceCode;
    
    public Integer getSerNumber() {
        return this.serNumber;
    }
    
    public Integer getIicSerNumber() {
        return this.iicSerNumber;
    }
    
    public Integer getServiceCode() {
        return this.serviceCode;
    }
    
    public void setSerNumber(final Integer serNumber) {
        this.serNumber = serNumber;
    }
    
    public void setIicSerNumber(final Integer iicSerNumber) {
        this.iicSerNumber = iicSerNumber;
    }
    
    public void setServiceCode(final Integer serviceCode) {
        this.serviceCode = serviceCode;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof IpmServiceCodesEntity)) {
            return false;
        }
        final IpmServiceCodesEntity other = (IpmServiceCodesEntity)o;
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
        final Object this$serviceCode = this.getServiceCode();
        final Object other$serviceCode = other.getServiceCode();
        if (this$serviceCode == null) {
            if (other$serviceCode == null) {
                return true;
            }
        }
        else if (this$serviceCode.equals(other$serviceCode)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof IpmServiceCodesEntity;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $serNumber = this.getSerNumber();
        result = result * 59 + (($serNumber == null) ? 43 : $serNumber.hashCode());
        final Object $iicSerNumber = this.getIicSerNumber();
        result = result * 59 + (($iicSerNumber == null) ? 43 : $iicSerNumber.hashCode());
        final Object $serviceCode = this.getServiceCode();
        result = result * 59 + (($serviceCode == null) ? 43 : $serviceCode.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "IpmServiceCodesEntity(serNumber=" + this.getSerNumber() + ", iicSerNumber=" + this.getIicSerNumber() + ", serviceCode=" + this.getServiceCode();
    }
}
