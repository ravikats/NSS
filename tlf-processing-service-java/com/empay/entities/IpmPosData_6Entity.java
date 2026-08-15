// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;

@Entity
@Table(name = "IPM_POS_DATA_6")
public class IpmPosData_6Entity
{
    @Id
    @Column(name = "ID6_SER_NUMBER")
    private Integer serNumber;
    @Column(name = "ID6_IIC_SER_NUMBER")
    private Integer iicSerNumber;
    @Column(name = "ID6_POS_DATA_6")
    private Character posData6;
    
    public Integer getSerNumber() {
        return this.serNumber;
    }
    
    public Integer getIicSerNumber() {
        return this.iicSerNumber;
    }
    
    public Character getPosData6() {
        return this.posData6;
    }
    
    public void setSerNumber(final Integer serNumber) {
        this.serNumber = serNumber;
    }
    
    public void setIicSerNumber(final Integer iicSerNumber) {
        this.iicSerNumber = iicSerNumber;
    }
    
    public void setPosData6(final Character posData6) {
        this.posData6 = posData6;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof IpmPosData_6Entity)) {
            return false;
        }
        final IpmPosData_6Entity other = (IpmPosData_6Entity)o;
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
        final Object this$posData6 = this.getPosData6();
        final Object other$posData6 = other.getPosData6();
        if (this$posData6 == null) {
            if (other$posData6 == null) {
                return true;
            }
        }
        else if (this$posData6.equals(other$posData6)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof IpmPosData_6Entity;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $serNumber = this.getSerNumber();
        result = result * 59 + (($serNumber == null) ? 43 : $serNumber.hashCode());
        final Object $iicSerNumber = this.getIicSerNumber();
        result = result * 59 + (($iicSerNumber == null) ? 43 : $iicSerNumber.hashCode());
        final Object $posData6 = this.getPosData6();
        result = result * 59 + (($posData6 == null) ? 43 : $posData6.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "IpmPosData_6Entity(serNumber=" + this.getSerNumber() + ", iicSerNumber=" + this.getIicSerNumber() + ", posData6=" + this.getPosData6();
    }
}
