// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;

@Entity
@Table(name = "IPM_PROC_CODES")
public class ipmProCodeEntity
{
    @Id
    @Column(name = "IPC_SER_NUMBER")
    private Integer serNumber;
    @Column(name = "IPC_IIC_SER_NUMBER")
    private Integer iicSerNumber;
    @Column(name = "IPC_PROC_CODE")
    private String procCode;
    
    public Integer getSerNumber() {
        return this.serNumber;
    }
    
    public Integer getIicSerNumber() {
        return this.iicSerNumber;
    }
    
    public String getProcCode() {
        return this.procCode;
    }
    
    public void setSerNumber(final Integer serNumber) {
        this.serNumber = serNumber;
    }
    
    public void setIicSerNumber(final Integer iicSerNumber) {
        this.iicSerNumber = iicSerNumber;
    }
    
    public void setProcCode(final String procCode) {
        this.procCode = procCode;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ipmProCodeEntity)) {
            return false;
        }
        final ipmProCodeEntity other = (ipmProCodeEntity)o;
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
        final Object this$procCode = this.getProcCode();
        final Object other$procCode = other.getProcCode();
        if (this$procCode == null) {
            if (other$procCode == null) {
                return true;
            }
        }
        else if (this$procCode.equals(other$procCode)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof ipmProCodeEntity;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $serNumber = this.getSerNumber();
        result = result * 59 + (($serNumber == null) ? 43 : $serNumber.hashCode());
        final Object $iicSerNumber = this.getIicSerNumber();
        result = result * 59 + (($iicSerNumber == null) ? 43 : $iicSerNumber.hashCode());
        final Object $procCode = this.getProcCode();
        result = result * 59 + (($procCode == null) ? 43 : $procCode.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "ipmProCodeEntity(serNumber=" + this.getSerNumber() + ", iicSerNumber=" + this.getIicSerNumber() + ", procCode=" + this.getProcCode();
    }
}
