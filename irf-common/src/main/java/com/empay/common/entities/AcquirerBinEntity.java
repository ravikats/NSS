// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.common.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;

@Entity
@Table(name = "ACQUIRER_BINS")
public class AcquirerBinEntity
{
    @Id
    @Column(name = "ACQ_BIN")
    private String acqBin;
    @Column(name = "ACQ_INS_CODE")
    private Integer insCode;
    @Column(name = "ACQ_MC_ICA_NO")
    private String mcIcaNum;
    @Column(name = "ACQ_BIN_TYPE")
    private Character binType;
    
    public String getAcqBin() {
        return this.acqBin;
    }
    
    public Integer getInsCode() {
        return this.insCode;
    }
    
    public String getMcIcaNum() {
        return this.mcIcaNum;
    }
    
    public Character getBinType() {
        return this.binType;
    }
    
    public void setAcqBin(final String acqBin) {
        this.acqBin = acqBin;
    }
    
    public void setInsCode(final Integer insCode) {
        this.insCode = insCode;
    }
    
    public void setMcIcaNum(final String mcIcaNum) {
        this.mcIcaNum = mcIcaNum;
    }
    
    public void setBinType(final Character binType) {
        this.binType = binType;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof AcquirerBinEntity)) {
            return false;
        }
        final AcquirerBinEntity other = (AcquirerBinEntity)o;
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
        final Object this$binType = this.getBinType();
        final Object other$binType = other.getBinType();
        Label_0102: {
            if (this$binType == null) {
                if (other$binType == null) {
                    break Label_0102;
                }
            }
            else if (this$binType.equals(other$binType)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$acqBin = this.getAcqBin();
        final Object other$acqBin = other.getAcqBin();
        Label_0139: {
            if (this$acqBin == null) {
                if (other$acqBin == null) {
                    break Label_0139;
                }
            }
            else if (this$acqBin.equals(other$acqBin)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$mcIcaNum = this.getMcIcaNum();
        final Object other$mcIcaNum = other.getMcIcaNum();
        if (this$mcIcaNum == null) {
            if (other$mcIcaNum == null) {
                return true;
            }
        }
        else if (this$mcIcaNum.equals(other$mcIcaNum)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof AcquirerBinEntity;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $insCode = this.getInsCode();
        result = result * 59 + (($insCode == null) ? 43 : $insCode.hashCode());
        final Object $binType = this.getBinType();
        result = result * 59 + (($binType == null) ? 43 : $binType.hashCode());
        final Object $acqBin = this.getAcqBin();
        result = result * 59 + (($acqBin == null) ? 43 : $acqBin.hashCode());
        final Object $mcIcaNum = this.getMcIcaNum();
        result = result * 59 + (($mcIcaNum == null) ? 43 : $mcIcaNum.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "AcquirerBinEntity(acqBin=" + this.getAcqBin() + ", insCode=" + this.getInsCode() + ", mcIcaNum=" + this.getMcIcaNum() + ", binType=" + this.getBinType();
    }
}

