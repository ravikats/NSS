// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;

@Entity
@Table(name = "IPM_POS_DATA_5")
public class IpmPosData_5Entity
{
    @Id
    @Column(name = "ID5_SER_NUMBER")
    private Integer serNumber;
    @Column(name = "ID5_IIC_SER_NUMBER")
    private Integer iicSerNumber;
    @Column(name = "ID5_POS_DATA_5")
    private Character posData5;
    
    public Integer getSerNumber() {
        return this.serNumber;
    }
    
    public Integer getIicSerNumber() {
        return this.iicSerNumber;
    }
    
    public Character getPosData5() {
        return this.posData5;
    }
    
    public void setSerNumber(final Integer serNumber) {
        this.serNumber = serNumber;
    }
    
    public void setIicSerNumber(final Integer iicSerNumber) {
        this.iicSerNumber = iicSerNumber;
    }
    
    public void setPosData5(final Character posData5) {
        this.posData5 = posData5;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof IpmPosData_5Entity)) {
            return false;
        }
        final IpmPosData_5Entity other = (IpmPosData_5Entity)o;
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
        final Object this$posData5 = this.getPosData5();
        final Object other$posData5 = other.getPosData5();
        if (this$posData5 == null) {
            if (other$posData5 == null) {
                return true;
            }
        }
        else if (this$posData5.equals(other$posData5)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof IpmPosData_5Entity;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $serNumber = this.getSerNumber();
        result = result * 59 + (($serNumber == null) ? 43 : $serNumber.hashCode());
        final Object $iicSerNumber = this.getIicSerNumber();
        result = result * 59 + (($iicSerNumber == null) ? 43 : $iicSerNumber.hashCode());
        final Object $posData5 = this.getPosData5();
        result = result * 59 + (($posData5 == null) ? 43 : $posData5.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "IpmPosData_5Entity(serNumber=" + this.getSerNumber() + ", iicSerNumber=" + this.getIicSerNumber() + ", posData5=" + this.getPosData5();
    }
}
