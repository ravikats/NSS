/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.entities.OmanNetBinEntity
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
import java.time.LocalDateTime;

@Entity
@Table(name="OMANNET_BIN_DATA")
public class OmanNetBinEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="OBN_SER_NUMBER")
    private Integer serialNumber;
    @Column(name="OBN_LAST_UPDATED")
    private LocalDateTime lastUpdated;
    @Column(name="OBN_UPDATED_USER")
    private Integer user;
    @Column(name="OBN_PRJ_SER_NUMBER")
    private Integer jobNumber;
    @Column(name="OBN_GEN_STATUS")
    private Integer genStatus;
    @Column(name="OBN_ROUTE")
    private String route;
    @Column(name="OBN_SUBROUTE")
    private String subRoute;
    @Column(name="OBN_BIN_NUMBER")
    private String binNumber;
    @Column(name="OBN_CARD_TYPE")
    private Character cardType;
    @Column(name="OBN_REMARK")
    private String remarks;

    public Integer getSerialNumber() {
        return this.serialNumber;
    }

    public LocalDateTime getLastUpdated() {
        return this.lastUpdated;
    }

    public Integer getUser() {
        return this.user;
    }

    public Integer getJobNumber() {
        return this.jobNumber;
    }

    public Integer getGenStatus() {
        return this.genStatus;
    }

    public String getRoute() {
        return this.route;
    }

    public String getSubRoute() {
        return this.subRoute;
    }

    public String getBinNumber() {
        return this.binNumber;
    }

    public Character getCardType() {
        return this.cardType;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public void setJobNumber(Integer jobNumber) {
        this.jobNumber = jobNumber;
    }

    public void setGenStatus(Integer genStatus) {
        this.genStatus = genStatus;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public void setSubRoute(String subRoute) {
        this.subRoute = subRoute;
    }

    public void setBinNumber(String binNumber) {
        this.binNumber = binNumber;
    }

    public void setCardType(Character cardType) {
        this.cardType = cardType;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof OmanNetBinEntity)) {
            return false;
        }
        OmanNetBinEntity other = (OmanNetBinEntity)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        Integer this$serialNumber = this.getSerialNumber();
        Integer other$serialNumber = other.getSerialNumber();
        if (this$serialNumber == null ? other$serialNumber != null : !((Object)this$serialNumber).equals(other$serialNumber)) {
            return false;
        }
        Integer this$user = this.getUser();
        Integer other$user = other.getUser();
        if (this$user == null ? other$user != null : !((Object)this$user).equals(other$user)) {
            return false;
        }
        Integer this$jobNumber = this.getJobNumber();
        Integer other$jobNumber = other.getJobNumber();
        if (this$jobNumber == null ? other$jobNumber != null : !((Object)this$jobNumber).equals(other$jobNumber)) {
            return false;
        }
        Integer this$genStatus = this.getGenStatus();
        Integer other$genStatus = other.getGenStatus();
        if (this$genStatus == null ? other$genStatus != null : !((Object)this$genStatus).equals(other$genStatus)) {
            return false;
        }
        Character this$cardType = this.getCardType();
        Character other$cardType = other.getCardType();
        if (this$cardType == null ? other$cardType != null : !((Object)this$cardType).equals(other$cardType)) {
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
        String this$binNumber = this.getBinNumber();
        String other$binNumber = other.getBinNumber();
        if (this$binNumber == null ? other$binNumber != null : !this$binNumber.equals(other$binNumber)) {
            return false;
        }
        String this$remarks = this.getRemarks();
        String other$remarks = other.getRemarks();
        return !(this$remarks == null ? other$remarks != null : !this$remarks.equals(other$remarks));
    }

    protected boolean canEqual(Object other) {
        return other instanceof OmanNetBinEntity;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Integer $serialNumber = this.getSerialNumber();
        result = result * 59 + ($serialNumber == null ? 43 : ((Object)$serialNumber).hashCode());
        Integer $user = this.getUser();
        result = result * 59 + ($user == null ? 43 : ((Object)$user).hashCode());
        Integer $jobNumber = this.getJobNumber();
        result = result * 59 + ($jobNumber == null ? 43 : ((Object)$jobNumber).hashCode());
        Integer $genStatus = this.getGenStatus();
        result = result * 59 + ($genStatus == null ? 43 : ((Object)$genStatus).hashCode());
        Character $cardType = this.getCardType();
        result = result * 59 + ($cardType == null ? 43 : ((Object)$cardType).hashCode());
        LocalDateTime $lastUpdated = this.getLastUpdated();
        result = result * 59 + ($lastUpdated == null ? 43 : ((Object)$lastUpdated).hashCode());
        String $route = this.getRoute();
        result = result * 59 + ($route == null ? 43 : $route.hashCode());
        String $subRoute = this.getSubRoute();
        result = result * 59 + ($subRoute == null ? 43 : $subRoute.hashCode());
        String $binNumber = this.getBinNumber();
        result = result * 59 + ($binNumber == null ? 43 : $binNumber.hashCode());
        String $remarks = this.getRemarks();
        result = result * 59 + ($remarks == null ? 43 : $remarks.hashCode());
        return result;
    }

    public String toString() {
        return "OmanNetBinEntity(serialNumber=" + this.getSerialNumber() + ", lastUpdated=" + String.valueOf(this.getLastUpdated()) + ", user=" + this.getUser() + ", jobNumber=" + this.getJobNumber() + ", genStatus=" + this.getGenStatus() + ", route=" + this.getRoute() + ", subRoute=" + this.getSubRoute() + ", binNumber=" + this.getBinNumber() + ", cardType=" + this.getCardType() + ", remarks=" + this.getRemarks() + ")";
    }
}

