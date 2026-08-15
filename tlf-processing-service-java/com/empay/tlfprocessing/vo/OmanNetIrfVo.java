// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.tlfprocessing.vo;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;

public class OmanNetIrfVo
{
    @NotBlank(message = "Route cannot be empty")
    @Size(max = 20)
    private String route;
    @NotBlank(message = "Sub-Route cannot be empty")
    @Size(max = 20)
    private String subRoute;
    @NotBlank(message = "CardType cannot be empty")
    @Size(max = 1)
    private String cardType;
    private String segment;
    private String segmentDesc;
    @NotBlank(message = "MCC cannot be empty")
    private String mcc;
    private String mccDescription;
    private Double irfRatePercent;
    private Double irfMax;
    private Double irfFixed;
    private Integer referenceNumber;
    
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
    
    public void setRoute(final String route) {
        this.route = route;
    }
    
    public void setSubRoute(final String subRoute) {
        this.subRoute = subRoute;
    }
    
    public void setCardType(final String cardType) {
        this.cardType = cardType;
    }
    
    public void setSegment(final String segment) {
        this.segment = segment;
    }
    
    public void setSegmentDesc(final String segmentDesc) {
        this.segmentDesc = segmentDesc;
    }
    
    public void setMcc(final String mcc) {
        this.mcc = mcc;
    }
    
    public void setMccDescription(final String mccDescription) {
        this.mccDescription = mccDescription;
    }
    
    public void setIrfRatePercent(final Double irfRatePercent) {
        this.irfRatePercent = irfRatePercent;
    }
    
    public void setIrfMax(final Double irfMax) {
        this.irfMax = irfMax;
    }
    
    public void setIrfFixed(final Double irfFixed) {
        this.irfFixed = irfFixed;
    }
    
    public void setReferenceNumber(final Integer referenceNumber) {
        this.referenceNumber = referenceNumber;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof OmanNetIrfVo)) {
            return false;
        }
        final OmanNetIrfVo other = (OmanNetIrfVo)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$irfRatePercent = this.getIrfRatePercent();
        final Object other$irfRatePercent = other.getIrfRatePercent();
        Label_0065: {
            if (this$irfRatePercent == null) {
                if (other$irfRatePercent == null) {
                    break Label_0065;
                }
            }
            else if (this$irfRatePercent.equals(other$irfRatePercent)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$irfMax = this.getIrfMax();
        final Object other$irfMax = other.getIrfMax();
        Label_0102: {
            if (this$irfMax == null) {
                if (other$irfMax == null) {
                    break Label_0102;
                }
            }
            else if (this$irfMax.equals(other$irfMax)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$irfFixed = this.getIrfFixed();
        final Object other$irfFixed = other.getIrfFixed();
        Label_0139: {
            if (this$irfFixed == null) {
                if (other$irfFixed == null) {
                    break Label_0139;
                }
            }
            else if (this$irfFixed.equals(other$irfFixed)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$referenceNumber = this.getReferenceNumber();
        final Object other$referenceNumber = other.getReferenceNumber();
        Label_0176: {
            if (this$referenceNumber == null) {
                if (other$referenceNumber == null) {
                    break Label_0176;
                }
            }
            else if (this$referenceNumber.equals(other$referenceNumber)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$route = this.getRoute();
        final Object other$route = other.getRoute();
        Label_0213: {
            if (this$route == null) {
                if (other$route == null) {
                    break Label_0213;
                }
            }
            else if (this$route.equals(other$route)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$subRoute = this.getSubRoute();
        final Object other$subRoute = other.getSubRoute();
        Label_0250: {
            if (this$subRoute == null) {
                if (other$subRoute == null) {
                    break Label_0250;
                }
            }
            else if (this$subRoute.equals(other$subRoute)) {
                break Label_0250;
            }
            return false;
        }
        final Object this$cardType = this.getCardType();
        final Object other$cardType = other.getCardType();
        Label_0287: {
            if (this$cardType == null) {
                if (other$cardType == null) {
                    break Label_0287;
                }
            }
            else if (this$cardType.equals(other$cardType)) {
                break Label_0287;
            }
            return false;
        }
        final Object this$segment = this.getSegment();
        final Object other$segment = other.getSegment();
        Label_0324: {
            if (this$segment == null) {
                if (other$segment == null) {
                    break Label_0324;
                }
            }
            else if (this$segment.equals(other$segment)) {
                break Label_0324;
            }
            return false;
        }
        final Object this$segmentDesc = this.getSegmentDesc();
        final Object other$segmentDesc = other.getSegmentDesc();
        Label_0361: {
            if (this$segmentDesc == null) {
                if (other$segmentDesc == null) {
                    break Label_0361;
                }
            }
            else if (this$segmentDesc.equals(other$segmentDesc)) {
                break Label_0361;
            }
            return false;
        }
        final Object this$mcc = this.getMcc();
        final Object other$mcc = other.getMcc();
        Label_0398: {
            if (this$mcc == null) {
                if (other$mcc == null) {
                    break Label_0398;
                }
            }
            else if (this$mcc.equals(other$mcc)) {
                break Label_0398;
            }
            return false;
        }
        final Object this$mccDescription = this.getMccDescription();
        final Object other$mccDescription = other.getMccDescription();
        if (this$mccDescription == null) {
            if (other$mccDescription == null) {
                return true;
            }
        }
        else if (this$mccDescription.equals(other$mccDescription)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof OmanNetIrfVo;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $irfRatePercent = this.getIrfRatePercent();
        result = result * 59 + (($irfRatePercent == null) ? 43 : $irfRatePercent.hashCode());
        final Object $irfMax = this.getIrfMax();
        result = result * 59 + (($irfMax == null) ? 43 : $irfMax.hashCode());
        final Object $irfFixed = this.getIrfFixed();
        result = result * 59 + (($irfFixed == null) ? 43 : $irfFixed.hashCode());
        final Object $referenceNumber = this.getReferenceNumber();
        result = result * 59 + (($referenceNumber == null) ? 43 : $referenceNumber.hashCode());
        final Object $route = this.getRoute();
        result = result * 59 + (($route == null) ? 43 : $route.hashCode());
        final Object $subRoute = this.getSubRoute();
        result = result * 59 + (($subRoute == null) ? 43 : $subRoute.hashCode());
        final Object $cardType = this.getCardType();
        result = result * 59 + (($cardType == null) ? 43 : $cardType.hashCode());
        final Object $segment = this.getSegment();
        result = result * 59 + (($segment == null) ? 43 : $segment.hashCode());
        final Object $segmentDesc = this.getSegmentDesc();
        result = result * 59 + (($segmentDesc == null) ? 43 : $segmentDesc.hashCode());
        final Object $mcc = this.getMcc();
        result = result * 59 + (($mcc == null) ? 43 : $mcc.hashCode());
        final Object $mccDescription = this.getMccDescription();
        result = result * 59 + (($mccDescription == null) ? 43 : $mccDescription.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "OmanNetIrfVo(route=" + this.getRoute() + ", subRoute=" + this.getSubRoute() + ", cardType=" + this.getCardType() + ", segment=" + this.getSegment() + ", segmentDesc=" + this.getSegmentDesc() + ", mcc=" + this.getMcc() + ", mccDescription=" + this.getMccDescription() + ", irfRatePercent=" + this.getIrfRatePercent() + ", irfMax=" + this.getIrfMax() + ", irfFixed=" + this.getIrfFixed() + ", referenceNumber=" + this.getReferenceNumber();
    }
}
