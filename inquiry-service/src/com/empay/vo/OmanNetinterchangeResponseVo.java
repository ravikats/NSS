/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.vo.OmanNetinterchangeResponseVo
 */
package com.empay.vo;

public class OmanNetinterchangeResponseVo {
    private String route;
    private String subRoute;
    private String cardType;
    private String segment;
    private String segmentDesc;
    private String mcc;
    private String mccDescription;
    private Double irfRatePercent;
    private Double irfMax;
    private Double irfFixed;
    private Integer referenceNumber;
    public Integer totalCount;
    public Integer totalPage;

    public String getRoute() {
        return this.route;
    }

    public String getSubRoute() {
        return this.subRoute;
    }

    public String getCardType() {
        return this.cardType;
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

    public String getMccDescription() {
        return this.mccDescription;
    }

    public Double getIrfRatePercent() {
        return this.irfRatePercent;
    }

    public Double getIrfMax() {
        return this.irfMax;
    }

    public Double getIrfFixed() {
        return this.irfFixed;
    }

    public Integer getReferenceNumber() {
        return this.referenceNumber;
    }

    public Integer getTotalCount() {
        return this.totalCount;
    }

    public Integer getTotalPage() {
        return this.totalPage;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public void setSubRoute(String subRoute) {
        this.subRoute = subRoute;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
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

    public void setIrfRatePercent(Double irfRatePercent) {
        this.irfRatePercent = irfRatePercent;
    }

    public void setIrfMax(Double irfMax) {
        this.irfMax = irfMax;
    }

    public void setIrfFixed(Double irfFixed) {
        this.irfFixed = irfFixed;
    }

    public void setReferenceNumber(Integer referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof OmanNetinterchangeResponseVo)) {
            return false;
        }
        OmanNetinterchangeResponseVo other = (OmanNetinterchangeResponseVo)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        Double this$irfRatePercent = this.getIrfRatePercent();
        Double other$irfRatePercent = other.getIrfRatePercent();
        if (this$irfRatePercent == null ? other$irfRatePercent != null : !((Object)this$irfRatePercent).equals(other$irfRatePercent)) {
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
        Integer this$referenceNumber = this.getReferenceNumber();
        Integer other$referenceNumber = other.getReferenceNumber();
        if (this$referenceNumber == null ? other$referenceNumber != null : !((Object)this$referenceNumber).equals(other$referenceNumber)) {
            return false;
        }
        Integer this$totalCount = this.getTotalCount();
        Integer other$totalCount = other.getTotalCount();
        if (this$totalCount == null ? other$totalCount != null : !((Object)this$totalCount).equals(other$totalCount)) {
            return false;
        }
        Integer this$totalPage = this.getTotalPage();
        Integer other$totalPage = other.getTotalPage();
        if (this$totalPage == null ? other$totalPage != null : !((Object)this$totalPage).equals(other$totalPage)) {
            return false;
        }
        String this$route = this.getRoute();
        String other$route = other.getRoute();
        if (this$route == null ? other$route != null : !this$route.equals(other$route)) {
            return false;
        }
        String this$subRoute = this.getSubRoute();
        String other$subRoute = other.getSubRoute();
        if (this$subRoute == null ? other$subRoute != null : !this$subRoute.equals(other$subRoute)) {
            return false;
        }
        String this$cardType = this.getCardType();
        String other$cardType = other.getCardType();
        if (this$cardType == null ? other$cardType != null : !this$cardType.equals(other$cardType)) {
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
        return other instanceof OmanNetinterchangeResponseVo;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Double $irfRatePercent = this.getIrfRatePercent();
        result = result * 59 + ($irfRatePercent == null ? 43 : ((Object)$irfRatePercent).hashCode());
        Double $irfMax = this.getIrfMax();
        result = result * 59 + ($irfMax == null ? 43 : ((Object)$irfMax).hashCode());
        Double $irfFixed = this.getIrfFixed();
        result = result * 59 + ($irfFixed == null ? 43 : ((Object)$irfFixed).hashCode());
        Integer $referenceNumber = this.getReferenceNumber();
        result = result * 59 + ($referenceNumber == null ? 43 : ((Object)$referenceNumber).hashCode());
        Integer $totalCount = this.getTotalCount();
        result = result * 59 + ($totalCount == null ? 43 : ((Object)$totalCount).hashCode());
        Integer $totalPage = this.getTotalPage();
        result = result * 59 + ($totalPage == null ? 43 : ((Object)$totalPage).hashCode());
        String $route = this.getRoute();
        result = result * 59 + ($route == null ? 43 : $route.hashCode());
        String $subRoute = this.getSubRoute();
        result = result * 59 + ($subRoute == null ? 43 : $subRoute.hashCode());
        String $cardType = this.getCardType();
        result = result * 59 + ($cardType == null ? 43 : $cardType.hashCode());
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
        return "OmanNetinterchangeResponseVo(route=" + this.getRoute() + ", subRoute=" + this.getSubRoute() + ", cardType=" + this.getCardType() + ", segment=" + this.getSegment() + ", segmentDesc=" + this.getSegmentDesc() + ", mcc=" + this.getMcc() + ", mccDescription=" + this.getMccDescription() + ", irfRatePercent=" + this.getIrfRatePercent() + ", irfMax=" + this.getIrfMax() + ", irfFixed=" + this.getIrfFixed() + ", referenceNumber=" + this.getReferenceNumber() + ", totalCount=" + this.getTotalCount() + ", totalPage=" + this.getTotalPage() + ")";
    }
}

