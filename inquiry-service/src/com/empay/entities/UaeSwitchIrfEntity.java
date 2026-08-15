/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.entities.UaeSwitchIrfEntity
 *  jakarta.persistence.Column
 *  jakarta.persistence.Entity
 *  jakarta.persistence.GeneratedValue
 *  jakarta.persistence.GenerationType
 *  jakarta.persistence.Id
 *  jakarta.persistence.Table
 */
package com.empay.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="UAE_SWITCH_IRF")
public class UaeSwitchIrfEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="URF_SER_NUMBER")
    private Long serNumber;
    @Column(name="URF_SEGMENT")
    private String segment;
    @Column(name="URF_SEGMENT_DESC")
    private String segmentDesc;
    @Column(name="URF_MCC")
    private String mcc;
    @Column(name="URF_MCC_DESC")
    private String mccDesc;
    @Column(name="URF_IRF_RATE")
    private Double irfRate;
    @Column(name="URF_IRF_MAX")
    private Double irfMax;
    @Column(name="URF_POS_IRF")
    private Double posIrf;
    @Column(name="URF_ECOM_IRF")
    private Double ecomIrf;
    @Column(name="URF_POS_IRF_MAX")
    private Double posIrfMax;
    @Column(name="URF_ECOM_IRF_MAX")
    private Double ecomIrfMax;
    @Column(name="URF_IRF_FIXED")
    private Double irfFixed;

    public Long getSerNumber() {
        return this.serNumber;
    }

    public String getSegment() {
        return this.segment;
    }

    public String getSegmentDesc() {
        return this.segmentDesc;
    }

    public String getMcc() {
        return this.mcc;
    }

    public String getMccDesc() {
        return this.mccDesc;
    }

    public Double getIrfRate() {
        return this.irfRate;
    }

    public Double getIrfMax() {
        return this.irfMax;
    }

    public Double getPosIrf() {
        return this.posIrf;
    }

    public Double getEcomIrf() {
        return this.ecomIrf;
    }

    public Double getPosIrfMax() {
        return this.posIrfMax;
    }

    public Double getEcomIrfMax() {
        return this.ecomIrfMax;
    }

    public Double getIrfFixed() {
        return this.irfFixed;
    }

    public void setSerNumber(Long serNumber) {
        this.serNumber = serNumber;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    public void setSegmentDesc(String segmentDesc) {
        this.segmentDesc = segmentDesc;
    }

    public void setMcc(String mcc) {
        this.mcc = mcc;
    }

    public void setMccDesc(String mccDesc) {
        this.mccDesc = mccDesc;
    }

    public void setIrfRate(Double irfRate) {
        this.irfRate = irfRate;
    }

    public void setIrfMax(Double irfMax) {
        this.irfMax = irfMax;
    }

    public void setPosIrf(Double posIrf) {
        this.posIrf = posIrf;
    }

    public void setEcomIrf(Double ecomIrf) {
        this.ecomIrf = ecomIrf;
    }

    public void setPosIrfMax(Double posIrfMax) {
        this.posIrfMax = posIrfMax;
    }

    public void setEcomIrfMax(Double ecomIrfMax) {
        this.ecomIrfMax = ecomIrfMax;
    }

    public void setIrfFixed(Double irfFixed) {
        this.irfFixed = irfFixed;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof UaeSwitchIrfEntity)) {
            return false;
        }
        UaeSwitchIrfEntity other = (UaeSwitchIrfEntity)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        Long this$serNumber = this.getSerNumber();
        Long other$serNumber = other.getSerNumber();
        if (this$serNumber == null ? other$serNumber != null : !((Object)this$serNumber).equals(other$serNumber)) {
            return false;
        }
        Double this$irfRate = this.getIrfRate();
        Double other$irfRate = other.getIrfRate();
        if (this$irfRate == null ? other$irfRate != null : !((Object)this$irfRate).equals(other$irfRate)) {
            return false;
        }
        Double this$irfMax = this.getIrfMax();
        Double other$irfMax = other.getIrfMax();
        if (this$irfMax == null ? other$irfMax != null : !((Object)this$irfMax).equals(other$irfMax)) {
            return false;
        }
        Double this$posIrf = this.getPosIrf();
        Double other$posIrf = other.getPosIrf();
        if (this$posIrf == null ? other$posIrf != null : !((Object)this$posIrf).equals(other$posIrf)) {
            return false;
        }
        Double this$ecomIrf = this.getEcomIrf();
        Double other$ecomIrf = other.getEcomIrf();
        if (this$ecomIrf == null ? other$ecomIrf != null : !((Object)this$ecomIrf).equals(other$ecomIrf)) {
            return false;
        }
        Double this$posIrfMax = this.getPosIrfMax();
        Double other$posIrfMax = other.getPosIrfMax();
        if (this$posIrfMax == null ? other$posIrfMax != null : !((Object)this$posIrfMax).equals(other$posIrfMax)) {
            return false;
        }
        Double this$ecomIrfMax = this.getEcomIrfMax();
        Double other$ecomIrfMax = other.getEcomIrfMax();
        if (this$ecomIrfMax == null ? other$ecomIrfMax != null : !((Object)this$ecomIrfMax).equals(other$ecomIrfMax)) {
            return false;
        }
        Double this$irfFixed = this.getIrfFixed();
        Double other$irfFixed = other.getIrfFixed();
        if (this$irfFixed == null ? other$irfFixed != null : !((Object)this$irfFixed).equals(other$irfFixed)) {
            return false;
        }
        String this$segment = this.getSegment();
        String other$segment = other.getSegment();
        if (this$segment == null ? other$segment != null : !this$segment.equals(other$segment)) {
            return false;
        }
        String this$segmentDesc = this.getSegmentDesc();
        String other$segmentDesc = other.getSegmentDesc();
        if (this$segmentDesc == null ? other$segmentDesc != null : !this$segmentDesc.equals(other$segmentDesc)) {
            return false;
        }
        String this$mcc = this.getMcc();
        String other$mcc = other.getMcc();
        if (this$mcc == null ? other$mcc != null : !this$mcc.equals(other$mcc)) {
            return false;
        }
        String this$mccDesc = this.getMccDesc();
        String other$mccDesc = other.getMccDesc();
        return !(this$mccDesc == null ? other$mccDesc != null : !this$mccDesc.equals(other$mccDesc));
    }

    protected boolean canEqual(Object other) {
        return other instanceof UaeSwitchIrfEntity;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Long $serNumber = this.getSerNumber();
        result = result * 59 + ($serNumber == null ? 43 : ((Object)$serNumber).hashCode());
        Double $irfRate = this.getIrfRate();
        result = result * 59 + ($irfRate == null ? 43 : ((Object)$irfRate).hashCode());
        Double $irfMax = this.getIrfMax();
        result = result * 59 + ($irfMax == null ? 43 : ((Object)$irfMax).hashCode());
        Double $posIrf = this.getPosIrf();
        result = result * 59 + ($posIrf == null ? 43 : ((Object)$posIrf).hashCode());
        Double $ecomIrf = this.getEcomIrf();
        result = result * 59 + ($ecomIrf == null ? 43 : ((Object)$ecomIrf).hashCode());
        Double $posIrfMax = this.getPosIrfMax();
        result = result * 59 + ($posIrfMax == null ? 43 : ((Object)$posIrfMax).hashCode());
        Double $ecomIrfMax = this.getEcomIrfMax();
        result = result * 59 + ($ecomIrfMax == null ? 43 : ((Object)$ecomIrfMax).hashCode());
        Double $irfFixed = this.getIrfFixed();
        result = result * 59 + ($irfFixed == null ? 43 : ((Object)$irfFixed).hashCode());
        String $segment = this.getSegment();
        result = result * 59 + ($segment == null ? 43 : $segment.hashCode());
        String $segmentDesc = this.getSegmentDesc();
        result = result * 59 + ($segmentDesc == null ? 43 : $segmentDesc.hashCode());
        String $mcc = this.getMcc();
        result = result * 59 + ($mcc == null ? 43 : $mcc.hashCode());
        String $mccDesc = this.getMccDesc();
        result = result * 59 + ($mccDesc == null ? 43 : $mccDesc.hashCode());
        return result;
    }

    public String toString() {
        return "UaeSwitchIrfEntity(serNumber=" + this.getSerNumber() + ", segment=" + this.getSegment() + ", segmentDesc=" + this.getSegmentDesc() + ", mcc=" + this.getMcc() + ", mccDesc=" + this.getMccDesc() + ", irfRate=" + this.getIrfRate() + ", irfMax=" + this.getIrfMax() + ", posIrf=" + this.getPosIrf() + ", ecomIrf=" + this.getEcomIrf() + ", posIrfMax=" + this.getPosIrfMax() + ", ecomIrfMax=" + this.getEcomIrfMax() + ", irfFixed=" + this.getIrfFixed() + ")";
    }
}

