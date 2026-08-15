// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.common.entities;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;

@Entity
@Table(name = "OMANNET_BIN_DATA")
public class OmanNetBinDataEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OBN_SER_NUMBER")
    private Integer serialNumber;
    @Column(name = "OBN_LAST_UPDATED")
    private LocalDateTime lastUpdated;
    @Column(name = "OBN_UPDATED_USER")
    private Integer user;
    @Column(name = "OBN_PRJ_SER_NUMBER")
    private Integer jobNumber;
    @Column(name = "OBN_GEN_STATUS")
    private Integer genStatus;
    @Column(name = "OBN_ROUTE")
    private String route;
    @Column(name = "OBN_SUBROUTE")
    private String subRoute;
    @Column(name = "OBN_BIN_NUMBER")
    private String binNumber;
    @Column(name = "OBN_CARD_TYPE")
    private Character cardType;
    @Column(name = "OBN_REMARK")
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
    
    public void setSerialNumber(final Integer serialNumber) {
        this.serialNumber = serialNumber;
    }
    
    public void setLastUpdated(final LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
    
    public void setUser(final Integer user) {
        this.user = user;
    }
    
    public void setJobNumber(final Integer jobNumber) {
        this.jobNumber = jobNumber;
    }
    
    public void setGenStatus(final Integer genStatus) {
        this.genStatus = genStatus;
    }
    
    public void setRoute(final String route) {
        this.route = route;
    }
    
    public void setSubRoute(final String subRoute) {
        this.subRoute = subRoute;
    }
    
    public void setBinNumber(final String binNumber) {
        this.binNumber = binNumber;
    }
    
    public void setCardType(final Character cardType) {
        this.cardType = cardType;
    }
    
    public void setRemarks(final String remarks) {
        this.remarks = remarks;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof OmanNetBinDataEntity)) {
            return false;
        }
        final OmanNetBinDataEntity other = (OmanNetBinDataEntity)o;
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
        final Object this$user = this.getUser();
        final Object other$user = other.getUser();
        Label_0102: {
            if (this$user == null) {
                if (other$user == null) {
                    break Label_0102;
                }
            }
            else if (this$user.equals(other$user)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$jobNumber = this.getJobNumber();
        final Object other$jobNumber = other.getJobNumber();
        Label_0139: {
            if (this$jobNumber == null) {
                if (other$jobNumber == null) {
                    break Label_0139;
                }
            }
            else if (this$jobNumber.equals(other$jobNumber)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$genStatus = this.getGenStatus();
        final Object other$genStatus = other.getGenStatus();
        Label_0176: {
            if (this$genStatus == null) {
                if (other$genStatus == null) {
                    break Label_0176;
                }
            }
            else if (this$genStatus.equals(other$genStatus)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$cardType = this.getCardType();
        final Object other$cardType = other.getCardType();
        Label_0213: {
            if (this$cardType == null) {
                if (other$cardType == null) {
                    break Label_0213;
                }
            }
            else if (this$cardType.equals(other$cardType)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$lastUpdated = this.getLastUpdated();
        final Object other$lastUpdated = other.getLastUpdated();
        Label_0250: {
            if (this$lastUpdated == null) {
                if (other$lastUpdated == null) {
                    break Label_0250;
                }
            }
            else if (this$lastUpdated.equals(other$lastUpdated)) {
                break Label_0250;
            }
            return false;
        }
        final Object this$route = this.getRoute();
        final Object other$route = other.getRoute();
        Label_0287: {
            if (this$route == null) {
                if (other$route == null) {
                    break Label_0287;
                }
            }
            else if (this$route.equals(other$route)) {
                break Label_0287;
            }
            return false;
        }
        final Object this$subRoute = this.getSubRoute();
        final Object other$subRoute = other.getSubRoute();
        Label_0324: {
            if (this$subRoute == null) {
                if (other$subRoute == null) {
                    break Label_0324;
                }
            }
            else if (this$subRoute.equals(other$subRoute)) {
                break Label_0324;
            }
            return false;
        }
        final Object this$binNumber = this.getBinNumber();
        final Object other$binNumber = other.getBinNumber();
        Label_0361: {
            if (this$binNumber == null) {
                if (other$binNumber == null) {
                    break Label_0361;
                }
            }
            else if (this$binNumber.equals(other$binNumber)) {
                break Label_0361;
            }
            return false;
        }
        final Object this$remarks = this.getRemarks();
        final Object other$remarks = other.getRemarks();
        if (this$remarks == null) {
            if (other$remarks == null) {
                return true;
            }
        }
        else if (this$remarks.equals(other$remarks)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof OmanNetBinDataEntity;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $serialNumber = this.getSerialNumber();
        result = result * 59 + (($serialNumber == null) ? 43 : $serialNumber.hashCode());
        final Object $user = this.getUser();
        result = result * 59 + (($user == null) ? 43 : $user.hashCode());
        final Object $jobNumber = this.getJobNumber();
        result = result * 59 + (($jobNumber == null) ? 43 : $jobNumber.hashCode());
        final Object $genStatus = this.getGenStatus();
        result = result * 59 + (($genStatus == null) ? 43 : $genStatus.hashCode());
        final Object $cardType = this.getCardType();
        result = result * 59 + (($cardType == null) ? 43 : $cardType.hashCode());
        final Object $lastUpdated = this.getLastUpdated();
        result = result * 59 + (($lastUpdated == null) ? 43 : $lastUpdated.hashCode());
        final Object $route = this.getRoute();
        result = result * 59 + (($route == null) ? 43 : $route.hashCode());
        final Object $subRoute = this.getSubRoute();
        result = result * 59 + (($subRoute == null) ? 43 : $subRoute.hashCode());
        final Object $binNumber = this.getBinNumber();
        result = result * 59 + (($binNumber == null) ? 43 : $binNumber.hashCode());
        final Object $remarks = this.getRemarks();
        result = result * 59 + (($remarks == null) ? 43 : $remarks.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "OmanNetBinDataEntity(serialNumber=" + this.getSerialNumber() + ", lastUpdated=" + String.valueOf(this.getLastUpdated()) + ", user=" + this.getUser() + ", jobNumber=" + this.getJobNumber() + ", genStatus=" + this.getGenStatus() + ", route=" + this.getRoute() + ", subRoute=" + this.getSubRoute() + ", binNumber=" + this.getBinNumber() + ", cardType=" + this.getCardType() + ", remarks=" + this.getRemarks();
    }
}
