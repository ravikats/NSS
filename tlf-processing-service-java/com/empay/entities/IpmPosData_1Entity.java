// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;

@Entity
@Table(name = "IPM_POS_DATA_1")
public class IpmPosData_1Entity
{
    @Id
    @Column(name = "ID1_SER_NUMBER")
    private Integer serNumber;
    @Column(name = "ID1_IIC_SER_NUMBER")
    private Integer iicSerNumber;
    @Column(name = "ID1_POS_DATA_1")
    private Character posData1;
    
    public Integer getSerNumber() {
        return this.serNumber;
    }
    
    public Integer getIicSerNumber() {
        return this.iicSerNumber;
    }
    
    public Character getPosData1() {
        return this.posData1;
    }
    
    public void setSerNumber(final Integer serNumber) {
        this.serNumber = serNumber;
    }
    
    public void setIicSerNumber(final Integer iicSerNumber) {
        this.iicSerNumber = iicSerNumber;
    }
    
    public void setPosData1(final Character posData1) {
        this.posData1 = posData1;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof IpmPosData_1Entity)) {
            return false;
        }
        final IpmPosData_1Entity other = (IpmPosData_1Entity)o;
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
        final Object this$posData1 = this.getPosData1();
        final Object other$posData1 = other.getPosData1();
        if (this$posData1 == null) {
            if (other$posData1 == null) {
                return true;
            }
        }
        else if (this$posData1.equals(other$posData1)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof IpmPosData_1Entity;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $serNumber = this.getSerNumber();
        result = result * 59 + (($serNumber == null) ? 43 : $serNumber.hashCode());
        final Object $iicSerNumber = this.getIicSerNumber();
        result = result * 59 + (($iicSerNumber == null) ? 43 : $iicSerNumber.hashCode());
        final Object $posData1 = this.getPosData1();
        result = result * 59 + (($posData1 == null) ? 43 : $posData1.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "IpmPosData_1Entity(serNumber=" + this.getSerNumber() + ", iicSerNumber=" + this.getIicSerNumber() + ", posData1=" + this.getPosData1();
    }
}
