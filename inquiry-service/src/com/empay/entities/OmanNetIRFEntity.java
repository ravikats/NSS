/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.entities.OmanNetIRFEntity
 *  com.empay.entities.OmanNetIRFEntity$OmanNetIRFEntityBuilder
 *  jakarta.persistence.Column
 *  jakarta.persistence.Entity
 *  jakarta.persistence.GeneratedValue
 *  jakarta.persistence.GenerationType
 *  jakarta.persistence.Id
 *  jakarta.persistence.Table
 */
package com.empay.entities;

import com.empay.entities.OmanNetIRFEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name="OMAN_NET_IRF")
public class OmanNetIRFEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ONI_SER_NUMBER")
    private Integer serialNumber;
    @Column(name="ONI_LAST_UPDATED")
    private LocalDateTime lastUpdated;
    @Column(name="ONI_UPDATED_USER")
    private Integer updatedUser;
    @Column(name="ONI_ROUTE")
    private String route;
    @Column(name="ONI_SUB_ROUTE")
    private String subRoute;
    @Column(name="ONI_CARD_TYPE")
    private Character cardType;
    @Column(name="ONI_SEGMENT")
    private String segment;
    @Column(name="ONI_SEGMENT_DESC")
    private String segmentDesc;
    @Column(name="ONI_MCC")
    private String mcc;
    @Column(name="ONI_MCC_DESC")
    private String mccDesc;
    @Column(name="ONI_IRF_PERCENTAGE")
    private Double irfPercentage;
    @Column(name="ONI_IRF_FIXED")
    private Double irfFixed;
    @Column(name="ONI_IRF_MAX")
    private Double irfMax;

    public static OmanNetIRFEntityBuilder builder() {
        return new OmanNetIRFEntityBuilder();
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

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public void setUpdatedUser(Integer updatedUser) {
        this.updatedUser = updatedUser;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public void setSubRoute(String subRoute) {
        this.subRoute = subRoute;
    }

    public void setCardType(Character cardType) {
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

    public void setMccDesc(String mccDesc) {
        this.mccDesc = mccDesc;
    }

    public void setIrfPercentage(Double irfPercentage) {
        this.irfPercentage = irfPercentage;
    }

    public void setIrfFixed(Double irfFixed) {
        this.irfFixed = irfFixed;
    }

    public void setIrfMax(Double irfMax) {
        this.irfMax = irfMax;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof OmanNetIRFEntity)) {
            return false;
        }
        OmanNetIRFEntity other = (OmanNetIRFEntity)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        Integer this$serialNumber = this.getSerialNumber();
        Integer other$serialNumber = other.getSerialNumber();
        if (this$serialNumber == null ? other$serialNumber != null : !((Object)this$serialNumber).equals(other$serialNumber)) {
            return false;
        }
        Integer this$updatedUser = this.getUpdatedUser();
        Integer other$updatedUser = other.getUpdatedUser();
        if (this$updatedUser == null ? other$updatedUser != null : !((Object)this$updatedUser).equals(other$updatedUser)) {
            return false;
        }
        Character this$cardType = this.getCardType();
        Character other$cardType = other.getCardType();
        if (this$cardType == null ? other$cardType != null : !((Object)this$cardType).equals(other$cardType)) {
            return false;
        }
        Double this$irfPercentage = this.getIrfPercentage();
        Double other$irfPercentage = other.getIrfPercentage();
        if (this$irfPercentage == null ? other$irfPercentage != null : !((Object)this$irfPercentage).equals(other$irfPercentage)) {
            return false;
        }
        Double this$irfFixed = this.getIrfFixed();
        Double other$irfFixed = other.getIrfFixed();
        if (this$irfFixed == null ? other$irfFixed != null : !((Object)this$irfFixed).equals(other$irfFixed)) {
            return false;
        }
        Double this$irfMax = this.getIrfMax();
        Double other$irfMax = other.getIrfMax();
        if (this$irfMax == null ? other$irfMax != null : !((Object)this$irfMax).equals(other$irfMax)) {
            return false;
        }
        LocalDateTime this$lastUpdated = this.getLastUpdated();
        LocalDateTime other$lastUpdated = other.getLastUpdated();
        if (this$lastUpdated == null ? other$lastUpdated != null : !((Object)this$lastUpdated).equals(other$lastUpdated)) {
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
        return other instanceof OmanNetIRFEntity;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Integer $serialNumber = this.getSerialNumber();
        result = result * 59 + ($serialNumber == null ? 43 : ((Object)$serialNumber).hashCode());
        Integer $updatedUser = this.getUpdatedUser();
        result = result * 59 + ($updatedUser == null ? 43 : ((Object)$updatedUser).hashCode());
        Character $cardType = this.getCardType();
        result = result * 59 + ($cardType == null ? 43 : ((Object)$cardType).hashCode());
        Double $irfPercentage = this.getIrfPercentage();
        result = result * 59 + ($irfPercentage == null ? 43 : ((Object)$irfPercentage).hashCode());
        Double $irfFixed = this.getIrfFixed();
        result = result * 59 + ($irfFixed == null ? 43 : ((Object)$irfFixed).hashCode());
        Double $irfMax = this.getIrfMax();
        result = result * 59 + ($irfMax == null ? 43 : ((Object)$irfMax).hashCode());
        LocalDateTime $lastUpdated = this.getLastUpdated();
        result = result * 59 + ($lastUpdated == null ? 43 : ((Object)$lastUpdated).hashCode());
        String $route = this.getRoute();
        result = result * 59 + ($route == null ? 43 : $route.hashCode());
        String $subRoute = this.getSubRoute();
        result = result * 59 + ($subRoute == null ? 43 : $subRoute.hashCode());
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
        return "OmanNetIRFEntity(serialNumber=" + this.getSerialNumber() + ", lastUpdated=" + String.valueOf(this.getLastUpdated()) + ", updatedUser=" + this.getUpdatedUser() + ", route=" + this.getRoute() + ", subRoute=" + this.getSubRoute() + ", cardType=" + this.getCardType() + ", segment=" + this.getSegment() + ", segmentDesc=" + this.getSegmentDesc() + ", mcc=" + this.getMcc() + ", mccDesc=" + this.getMccDesc() + ", irfPercentage=" + this.getIrfPercentage() + ", irfFixed=" + this.getIrfFixed() + ", irfMax=" + this.getIrfMax() + ")";
    }

    public OmanNetIRFEntity() {
    }

    public OmanNetIRFEntity(Integer serialNumber, LocalDateTime lastUpdated, Integer updatedUser, String route, String subRoute, Character cardType, String segment, String segmentDesc, String mcc, String mccDesc, Double irfPercentage, Double irfFixed, Double irfMax) {
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
}

