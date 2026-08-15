// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;

@Entity
@Table(name = "IPM_POS_DATA_7")
public class IpmPosData_7Entity
{
    @Id
    @Column(name = "ID7_SER_NUMBER")
    private Integer serNumber;
    @Column(name = "ID7_IIC_SER_NUMBER")
    private Integer iicSerNumber;
    @Column(name = "ID7_POS_DATA_7")
    private Character posData7;
    
    public Integer getSerNumber() {
        return this.serNumber;
    }
    
    public Integer getIicSerNumber() {
        return this.iicSerNumber;
    }
    
    public Character getPosData7() {
        return this.posData7;
    }
    
    public void setSerNumber(final Integer serNumber) {
        this.serNumber = serNumber;
    }
    
    public void setIicSerNumber(final Integer iicSerNumber) {
        this.iicSerNumber = iicSerNumber;
    }
    
    public void setPosData7(final Character posData7) {
        this.posData7 = posData7;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof IpmPosData_7Entity)) {
            return false;
        }
        final IpmPosData_7Entity other = (IpmPosData_7Entity)o;
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
        final Object this$posData7 = this.getPosData7();
        final Object other$posData7 = other.getPosData7();
        if (this$posData7 == null) {
            if (other$posData7 == null) {
                return true;
            }
        }
        else if (this$posData7.equals(other$posData7)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof IpmPosData_7Entity;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $serNumber = this.getSerNumber();
        result = result * 59 + (($serNumber == null) ? 43 : $serNumber.hashCode());
        final Object $iicSerNumber = this.getIicSerNumber();
        result = result * 59 + (($iicSerNumber == null) ? 43 : $iicSerNumber.hashCode());
        final Object $posData7 = this.getPosData7();
        result = result * 59 + (($posData7 == null) ? 43 : $posData7.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "IpmPosData_7Entity(serNumber=" + this.getSerNumber() + ", iicSerNumber=" + this.getIicSerNumber() + ", posData7=" + this.getPosData7();
    }
}
