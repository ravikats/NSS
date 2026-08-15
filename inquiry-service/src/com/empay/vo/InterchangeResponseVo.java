/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.vo.InterchangeResponseVo
 */
package com.empay.vo;

public class InterchangeResponseVo {
    private String segment;
    private String segmentDesc;
    private String mcc;
    private String mccDescription;
    private Double posIrf;
    private Double ecomIrf;
    private Double posMaxIrf;
    private Double ecomMaxIrf;
    private Double irfRate;
    private Double irfMax;
    private int totalCount;
    private int totalPage;
    private Double irfFixed;

    public String getSegment() {
        return this.segment;
    }

    public String getSegmentDesc() {
        return this.segmentDesc;
    }

    public String getMcc() {
        return this.mcc;
    }

    public String getMccDescription() {
        return this.mccDescription;
    }

    public Double getPosIrf() {
        return this.posIrf;
    }

    public Double getEcomIrf() {
        return this.ecomIrf;
    }

    public Double getPosMaxIrf() {
        return this.posMaxIrf;
    }

    public Double getEcomMaxIrf() {
        return this.ecomMaxIrf;
    }

    public Double getIrfRate() {
        return this.irfRate;
    }

    public Double getIrfMax() {
        return this.irfMax;
    }

    public int getTotalCount() {
        return this.totalCount;
    }

    public int getTotalPage() {
        return this.totalPage;
    }

    public Double getIrfFixed() {
        return this.irfFixed;
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

    public void setMccDescription(String mccDescription) {
        this.mccDescription = mccDescription;
    }

    public void setPosIrf(Double posIrf) {
        this.posIrf = posIrf;
    }

    public void setEcomIrf(Double ecomIrf) {
        this.ecomIrf = ecomIrf;
    }

    public void setPosMaxIrf(Double posMaxIrf) {
        this.posMaxIrf = posMaxIrf;
    }

    public void setEcomMaxIrf(Double ecomMaxIrf) {
        this.ecomMaxIrf = ecomMaxIrf;
    }

    public void setIrfRate(Double irfRate) {
        this.irfRate = irfRate;
    }

    public void setIrfMax(Double irfMax) {
        this.irfMax = irfMax;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public void setIrfFixed(Double irfFixed) {
        this.irfFixed = irfFixed;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof InterchangeResponseVo)) {
            return false;
        }
        InterchangeResponseVo other = (InterchangeResponseVo)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        if (this.getTotalCount() != other.getTotalCount()) {
            return false;
        }
        if (this.getTotalPage() != other.getTotalPage()) {
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
        Double this$posMaxIrf = this.getPosMaxIrf();
        Double other$posMaxIrf = other.getPosMaxIrf();
        if (this$posMaxIrf == null ? other$posMaxIrf != null : !((Object)this$posMaxIrf).equals(other$posMaxIrf)) {
            return false;
        }
        Double this$ecomMaxIrf = this.getEcomMaxIrf();
        Double other$ecomMaxIrf = other.getEcomMaxIrf();
        if (this$ecomMaxIrf == null ? other$ecomMaxIrf != null : !((Object)this$ecomMaxIrf).equals(other$ecomMaxIrf)) {
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
        String this$mccDescription = this.getMccDescription();
        String other$mccDescription = other.getMccDescription();
        return !(this$mccDescription == null ? other$mccDescription != null : !this$mccDescription.equals(other$mccDescription));
    }

    protected boolean canEqual(Object other) {
        return other instanceof InterchangeResponseVo;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + this.getTotalCount();
        result = result * 59 + this.getTotalPage();
        Double $posIrf = this.getPosIrf();
        result = result * 59 + ($posIrf == null ? 43 : ((Object)$posIrf).hashCode());
        Double $ecomIrf = this.getEcomIrf();
        result = result * 59 + ($ecomIrf == null ? 43 : ((Object)$ecomIrf).hashCode());
        Double $posMaxIrf = this.getPosMaxIrf();
        result = result * 59 + ($posMaxIrf == null ? 43 : ((Object)$posMaxIrf).hashCode());
        Double $ecomMaxIrf = this.getEcomMaxIrf();
        result = result * 59 + ($ecomMaxIrf == null ? 43 : ((Object)$ecomMaxIrf).hashCode());
        Double $irfRate = this.getIrfRate();
        result = result * 59 + ($irfRate == null ? 43 : ((Object)$irfRate).hashCode());
        Double $irfMax = this.getIrfMax();
        result = result * 59 + ($irfMax == null ? 43 : ((Object)$irfMax).hashCode());
        Double $irfFixed = this.getIrfFixed();
        result = result * 59 + ($irfFixed == null ? 43 : ((Object)$irfFixed).hashCode());
        String $segment = this.getSegment();
        result = result * 59 + ($segment == null ? 43 : $segment.hashCode());
        String $segmentDesc = this.getSegmentDesc();
        result = result * 59 + ($segmentDesc == null ? 43 : $segmentDesc.hashCode());
        String $mcc = this.getMcc();
        result = result * 59 + ($mcc == null ? 43 : $mcc.hashCode());
        String $mccDescription = this.getMccDescription();
        result = result * 59 + ($mccDescription == null ? 43 : $mccDescription.hashCode());
        return result;
    }

    public String toString() {
        return "InterchangeResponseVo(segment=" + this.getSegment() + ", segmentDesc=" + this.getSegmentDesc() + ", mcc=" + this.getMcc() + ", mccDescription=" + this.getMccDescription() + ", posIrf=" + this.getPosIrf() + ", ecomIrf=" + this.getEcomIrf() + ", posMaxIrf=" + this.getPosMaxIrf() + ", ecomMaxIrf=" + this.getEcomMaxIrf() + ", irfRate=" + this.getIrfRate() + ", irfMax=" + this.getIrfMax() + ", totalCount=" + this.getTotalCount() + ", totalPage=" + this.getTotalPage() + ", irfFixed=" + this.getIrfFixed() + ")";
    }
}

