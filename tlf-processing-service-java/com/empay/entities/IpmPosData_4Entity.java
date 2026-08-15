// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;

@Entity
@Table(name = "IPM_POS_DATA_4")
public class IpmPosData_4Entity
{
    @Id
    @Column(name = "ID4_SER_NUMBER")
    private Integer serNumber;
    @Column(name = "ID4_IIC_SER_NUMBER")
    private Integer iicSerNumber;
    @Column(name = "ID4_POS_DATA_4")
    private Character posData4;
    
    public Integer getSerNumber() {
        return this.serNumber;
    }
    
    public Integer getIicSerNumber() {
        return this.iicSerNumber;
    }
    
    public Character getPosData4() {
        return this.posData4;
    }
    
    public void setSerNumber(final Integer serNumber) {
        this.serNumber = serNumber;
    }
    
    public void setIicSerNumber(final Integer iicSerNumber) {
        this.iicSerNumber = iicSerNumber;
    }
    
    public void setPosData4(final Character posData4) {
        this.posData4 = posData4;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof IpmPosData_4Entity)) {
            return false;
        }
        final IpmPosData_4Entity other = (IpmPosData_4Entity)o;
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
        final Object this$posData4 = this.getPosData4();
        final Object other$posData4 = other.getPosData4();
        if (this$posData4 == null) {
            if (other$posData4 == null) {
                return true;
            }
        }
        else if (this$posData4.equals(other$posData4)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof IpmPosData_4Entity;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $serNumber = this.getSerNumber();
        result = result * 59 + (($serNumber == null) ? 43 : $serNumber.hashCode());
        final Object $iicSerNumber = this.getIicSerNumber();
        result = result * 59 + (($iicSerNumber == null) ? 43 : $iicSerNumber.hashCode());
        final Object $posData4 = this.getPosData4();
        result = result * 59 + (($posData4 == null) ? 43 : $posData4.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "IpmPosData_4Entity(serNumber=" + this.getSerNumber() + ", iicSerNumber=" + this.getIicSerNumber() + ", posData4=" + this.getPosData4();
    }
}
