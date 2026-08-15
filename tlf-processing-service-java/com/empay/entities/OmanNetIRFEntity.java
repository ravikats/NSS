// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.entities;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;

@Entity
@Table(name = "OMAN_NET_IRF")
public class OmanNetIRFEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ONI_SER_NUMBER")
    private Integer serialNumber;
    @Column(name = "ONI_LAST_UPDATED")
    private LocalDateTime lastUpdated;
    @Column(name = "ONI_UPDATED_USER")
    private Integer updatedUser;
    @Column(name = "ONI_ROUTE")
    private String route;
    @Column(name = "ONI_SUB_ROUTE")
    private String subRoute;
    @Column(name = "ONI_CARD_TYPE")
    private Character cardType;
    @Column(name = "ONI_SEGMENT")
    private String segment;
    @Column(name = "ONI_SEGMENT_DESC")
    private String segmentDesc;
    @Column(name = "ONI_MCC")
    private String mcc;
    @Column(name = "ONI_MCC_DESC")
    private String mccDesc;
    @Column(name = "ONI_IRF_PERCENTAGE")
    private Double irfPercentage;
    @Column(name = "ONI_IRF_FIXED")
    private Double irfFixed;
    @Column(name = "ONI_IRF_MAX")
    private Double irfMax;
    
    public static OmanNetIRFEntityBuilder builder() {
        return new OmanNetIRFEntityBuilder();
    }
    
    public OmanNetIRFEntityBuilder toBuilder() {
        return new OmanNetIRFEntityBuilder().serialNumber(this.serialNumber).lastUpdated(this.lastUpdated).updatedUser(this.updatedUser).route(this.route).subRoute(this.subRoute).cardType(this.cardType).segment(this.segment).segmentDesc(this.segmentDesc).mcc(this.mcc).mccDesc(this.mccDesc).irfPercentage(this.irfPercentage).irfFixed(this.irfFixed).irfMax(this.irfMax);
    }
    
    public Integer getSerialNumber() {
        return this.serialNumber;
    }
    
    public LocalDateTime getLastUpdated() {
        return this.lastUpdated;
    }
    
    public Integer getUpdatedUser() {
        return this.updatedUser;
    }
    
    public String getRoute() {
        return this.route;
    }
    
    public String getSubRoute() {
        return this.subRoute;
    }
    
    public Character getCardType() {
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
    
    public String getMccDesc() {
        return this.mccDesc;
    }
    
    public Double getIrfPercentage() {
        return this.irfPercentage;
    }
    
    public Double getIrfFixed() {
        return this.irfFixed;
    }
    
    public Double getIrfMax() {
        return this.irfMax;
    }
    
    public void setSerialNumber(final Integer serialNumber) {
        this.serialNumber = serialNumber;
    }
    
    public void setLastUpdated(final LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
    
    public void setUpdatedUser(final Integer updatedUser) {
        this.updatedUser = updatedUser;
    }
    
    public void setRoute(final String route) {
        this.route = route;
    }
    
    public void setSubRoute(final String subRoute) {
        this.subRoute = subRoute;
    }
    
    public void setCardType(final Character cardType) {
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
    
    public void setMccDesc(final String mccDesc) {
        this.mccDesc = mccDesc;
    }
    
    public void setIrfPercentage(final Double irfPercentage) {
        this.irfPercentage = irfPercentage;
    }
    
    public void setIrfFixed(final Double irfFixed) {
        this.irfFixed = irfFixed;
    }
    
    public void setIrfMax(final Double irfMax) {
        this.irfMax = irfMax;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof OmanNetIRFEntity)) {
            return false;
        }
        final OmanNetIRFEntity other = (OmanNetIRFEntity)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$serialNumber = this.getSerialNumber();
        final Object other$serialNumber = other.getSerialNumber();
        Label_0065: {
            if (this$serialNumber == null) {
                if (other$serialNumber == null) {
                    break Label_0065;
                }
            }
            else if (this$serialNumber.equals(other$serialNumber)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$updatedUser = this.getUpdatedUser();
        final Object other$updatedUser = other.getUpdatedUser();
        Label_0102: {
            if (this$updatedUser == null) {
                if (other$updatedUser == null) {
                    break Label_0102;
                }
            }
            else if (this$updatedUser.equals(other$updatedUser)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$cardType = this.getCardType();
        final Object other$cardType = other.getCardType();
        Label_0139: {
            if (this$cardType == null) {
                if (other$cardType == null) {
                    break Label_0139;
                }
            }
            else if (this$cardType.equals(other$cardType)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$irfPercentage = this.getIrfPercentage();
        final Object other$irfPercentage = other.getIrfPercentage();
        Label_0176: {
            if (this$irfPercentage == null) {
                if (other$irfPercentage == null) {
                    break Label_0176;
                }
            }
            else if (this$irfPercentage.equals(other$irfPercentage)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$irfFixed = this.getIrfFixed();
        final Object other$irfFixed = other.getIrfFixed();
        Label_0213: {
            if (this$irfFixed == null) {
                if (other$irfFixed == null) {
                    break Label_0213;
                }
            }
            else if (this$irfFixed.equals(other$irfFixed)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$irfMax = this.getIrfMax();
        final Object other$irfMax = other.getIrfMax();
        Label_0250: {
            if (this$irfMax == null) {
                if (other$irfMax == null) {
                    break Label_0250;
                }
            }
            else if (this$irfMax.equals(other$irfMax)) {
                break Label_0250;
            }
            return false;
        }
        final Object this$lastUpdated = this.getLastUpdated();
        final Object other$lastUpdated = other.getLastUpdated();
        Label_0287: {
            if (this$lastUpdated == null) {
                if (other$lastUpdated == null) {
                    break Label_0287;
                }
            }
            else if (this$lastUpdated.equals(other$lastUpdated)) {
                break Label_0287;
            }
            return false;
        }
        final Object this$route = this.getRoute();
        final Object other$route = other.getRoute();
        Label_0324: {
            if (this$route == null) {
                if (other$route == null) {
                    break Label_0324;
                }
            }
            else if (this$route.equals(other$route)) {
                break Label_0324;
            }
            return false;
        }
        final Object this$subRoute = this.getSubRoute();
        final Object other$subRoute = other.getSubRoute();
        Label_0361: {
            if (this$subRoute == null) {
                if (other$subRoute == null) {
                    break Label_0361;
                }
            }
            else if (this$subRoute.equals(other$subRoute)) {
                break Label_0361;
            }
            return false;
        }
        final Object this$segment = this.getSegment();
        final Object other$segment = other.getSegment();
        Label_0398: {
            if (this$segment == null) {
                if (other$segment == null) {
                    break Label_0398;
                }
            }
            else if (this$segment.equals(other$segment)) {
                break Label_0398;
            }
            return false;
        }
        final Object this$segmentDesc = this.getSegmentDesc();
        final Object other$segmentDesc = other.getSegmentDesc();
        Label_0435: {
            if (this$segmentDesc == null) {
                if (other$segmentDesc == null) {
                    break Label_0435;
                }
            }
            else if (this$segmentDesc.equals(other$segmentDesc)) {
                break Label_0435;
            }
            return false;
        }
        final Object this$mcc = this.getMcc();
        final Object other$mcc = other.getMcc();
        Label_0472: {
            if (this$mcc == null) {
                if (other$mcc == null) {
                    break Label_0472;
                }
            }
            else if (this$mcc.equals(other$mcc)) {
                break Label_0472;
            }
            return false;
        }
        final Object this$mccDesc = this.getMccDesc();
        final Object other$mccDesc = other.getMccDesc();
        if (this$mccDesc == null) {
            if (other$mccDesc == null) {
                return true;
            }
        }
        else if (this$mccDesc.equals(other$mccDesc)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof OmanNetIRFEntity;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $serialNumber = this.getSerialNumber();
        result = result * 59 + (($serialNumber == null) ? 43 : $serialNumber.hashCode());
        final Object $updatedUser = this.getUpdatedUser();
        result = result * 59 + (($updatedUser == null) ? 43 : $updatedUser.hashCode());
        final Object $cardType = this.getCardType();
        result = result * 59 + (($cardType == null) ? 43 : $cardType.hashCode());
        final Object $irfPercentage = this.getIrfPercentage();
        result = result * 59 + (($irfPercentage == null) ? 43 : $irfPercentage.hashCode());
        final Object $irfFixed = this.getIrfFixed();
        result = result * 59 + (($irfFixed == null) ? 43 : $irfFixed.hashCode());
        final Object $irfMax = this.getIrfMax();
        result = result * 59 + (($irfMax == null) ? 43 : $irfMax.hashCode());
        final Object $lastUpdated = this.getLastUpdated();
        result = result * 59 + (($lastUpdated == null) ? 43 : $lastUpdated.hashCode());
        final Object $route = this.getRoute();
        result = result * 59 + (($route == null) ? 43 : $route.hashCode());
        final Object $subRoute = this.getSubRoute();
        result = result * 59 + (($subRoute == null) ? 43 : $subRoute.hashCode());
        final Object $segment = this.getSegment();
        result = result * 59 + (($segment == null) ? 43 : $segment.hashCode());
        final Object $segmentDesc = this.getSegmentDesc();
        result = result * 59 + (($segmentDesc == null) ? 43 : $segmentDesc.hashCode());
        final Object $mcc = this.getMcc();
        result = result * 59 + (($mcc == null) ? 43 : $mcc.hashCode());
        final Object $mccDesc = this.getMccDesc();
        result = result * 59 + (($mccDesc == null) ? 43 : $mccDesc.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "OmanNetIRFEntity(serialNumber=" + this.getSerialNumber() + ", lastUpdated=" + String.valueOf(this.getLastUpdated()) + ", updatedUser=" + this.getUpdatedUser() + ", route=" + this.getRoute() + ", subRoute=" + this.getSubRoute() + ", cardType=" + this.getCardType() + ", segment=" + this.getSegment() + ", segmentDesc=" + this.getSegmentDesc() + ", mcc=" + this.getMcc() + ", mccDesc=" + this.getMccDesc() + ", irfPercentage=" + this.getIrfPercentage() + ", irfFixed=" + this.getIrfFixed() + ", irfMax=" + this.getIrfMax();
    }
    
    public OmanNetIRFEntity() {
    }
    
    public OmanNetIRFEntity(final Integer serialNumber, final LocalDateTime lastUpdated, final Integer updatedUser, final String route, final String subRoute, final Character cardType, final String segment, final String segmentDesc, final String mcc, final String mccDesc, final Double irfPercentage, final Double irfFixed, final Double irfMax) {
        this.serialNumber = serialNumber;
        this.lastUpdated = lastUpdated;
        this.updatedUser = updatedUser;
        this.route = route;
        this.subRoute = subRoute;
        this.cardType = cardType;
        this.segment = segment;
        this.segmentDesc = segmentDesc;
        this.mcc = mcc;
        this.mccDesc = mccDesc;
        this.irfPercentage = irfPercentage;
        this.irfFixed = irfFixed;
        this.irfMax = irfMax;
    }
    
    public static class OmanNetIRFEntityBuilder
    {
        private Integer serialNumber;
        private LocalDateTime lastUpdated;
        private Integer updatedUser;
        private String route;
        private String subRoute;
        private Character cardType;
        private String segment;
        private String segmentDesc;
        private String mcc;
        private String mccDesc;
        private Double irfPercentage;
        private Double irfFixed;
        private Double irfMax;
        
        OmanNetIRFEntityBuilder() {
        }
        
        public OmanNetIRFEntityBuilder serialNumber(final Integer serialNumber) {
            this.serialNumber = serialNumber;
            return this;
        }
        
        public OmanNetIRFEntityBuilder lastUpdated(final LocalDateTime lastUpdated) {
            this.lastUpdated = lastUpdated;
            return this;
        }
        
        public OmanNetIRFEntityBuilder updatedUser(final Integer updatedUser) {
            this.updatedUser = updatedUser;
            return this;
        }
        
        public OmanNetIRFEntityBuilder route(final String route) {
            this.route = route;
            return this;
        }
        
        public OmanNetIRFEntityBuilder subRoute(final String subRoute) {
            this.subRoute = subRoute;
            return this;
        }
        
        public OmanNetIRFEntityBuilder cardType(final Character cardType) {
            this.cardType = cardType;
            return this;
        }
        
        public OmanNetIRFEntityBuilder segment(final String segment) {
            this.segment = segment;
            return this;
        }
        
        public OmanNetIRFEntityBuilder segmentDesc(final String segmentDesc) {
            this.segmentDesc = segmentDesc;
            return this;
        }
        
        public OmanNetIRFEntityBuilder mcc(final String mcc) {
            this.mcc = mcc;
            return this;
        }
        
        public OmanNetIRFEntityBuilder mccDesc(final String mccDesc) {
            this.mccDesc = mccDesc;
            return this;
        }
        
        public OmanNetIRFEntityBuilder irfPercentage(final Double irfPercentage) {
            this.irfPercentage = irfPercentage;
            return this;
        }
        
        public OmanNetIRFEntityBuilder irfFixed(final Double irfFixed) {
            this.irfFixed = irfFixed;
            return this;
        }
        
        public OmanNetIRFEntityBuilder irfMax(final Double irfMax) {
            this.irfMax = irfMax;
            return this;
        }
        
        public OmanNetIRFEntity build() {
            return new OmanNetIRFEntity(this.serialNumber, this.lastUpdated, this.updatedUser, this.route, this.subRoute, this.cardType, this.segment, this.segmentDesc, this.mcc, this.mccDesc, this.irfPercentage, this.irfFixed, this.irfMax);
        }
        
        @Override
        public String toString() {
            return "OmanNetIRFEntity.OmanNetIRFEntityBuilder(serialNumber=" + this.serialNumber + ", lastUpdated=" + String.valueOf(this.lastUpdated) + ", updatedUser=" + this.updatedUser + ", route=" + this.route + ", subRoute=" + this.subRoute + ", cardType=" + this.cardType + ", segment=" + this.segment + ", segmentDesc=" + this.segmentDesc + ", mcc=" + this.mcc + ", mccDesc=" + this.mccDesc + ", irfPercentage=" + this.irfPercentage + ", irfFixed=" + this.irfFixed + ", irfMax=" + this.irfMax;
        }
    }
}
