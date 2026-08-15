/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.omanNet.OmanNetBinUtil
 */
package com.empay.omanNet;

public class OmanNetBinUtil {
    private Integer user;
    private Integer jobNumber;
    private Integer genStatus;
    private String subRoute;
    private String route;
    private String binNumber;
    private String cardType;
    private String remarks;

    public Integer getUser() {
        return this.user;
    }

    public Integer getJobNumber() {
        return this.jobNumber;
    }

    public Integer getGenStatus() {
        return this.genStatus;
    }

    public String getSubRoute() {
        return this.subRoute;
    }

    public String getRoute() {
        return this.route;
    }

    public String getBinNumber() {
        return this.binNumber;
    }

    public String getCardType() {
        return this.cardType;
    }

    public String getRemarks() {
        return this.remarks;
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

    public void setSubRoute(String subRoute) {
        this.subRoute = subRoute;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public void setBinNumber(String binNumber) {
        this.binNumber = binNumber;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof OmanNetBinUtil)) {
            return false;
        }
        OmanNetBinUtil other = (OmanNetBinUtil)o;
        if (!other.canEqual((Object)this)) {
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
        String this$subRoute = this.getSubRoute();
        String other$subRoute = other.getSubRoute();
        if (this$subRoute == null ? other$subRoute != null : !this$subRoute.equals(other$subRoute)) {
            return false;
        }
        String this$route = this.getRoute();
        String other$route = other.getRoute();
        if (this$route == null ? other$route != null : !this$route.equals(other$route)) {
            return false;
        }
        String this$binNumber = this.getBinNumber();
        String other$binNumber = other.getBinNumber();
        if (this$binNumber == null ? other$binNumber != null : !this$binNumber.equals(other$binNumber)) {
            return false;
        }
        String this$cardType = this.getCardType();
        String other$cardType = other.getCardType();
        if (this$cardType == null ? other$cardType != null : !this$cardType.equals(other$cardType)) {
            return false;
        }
        String this$remarks = this.getRemarks();
        String other$remarks = other.getRemarks();
        return !(this$remarks == null ? other$remarks != null : !this$remarks.equals(other$remarks));
    }

    protected boolean canEqual(Object other) {
        return other instanceof OmanNetBinUtil;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Integer $user = this.getUser();
        result = result * 59 + ($user == null ? 43 : ((Object)$user).hashCode());
        Integer $jobNumber = this.getJobNumber();
        result = result * 59 + ($jobNumber == null ? 43 : ((Object)$jobNumber).hashCode());
        Integer $genStatus = this.getGenStatus();
        result = result * 59 + ($genStatus == null ? 43 : ((Object)$genStatus).hashCode());
        String $subRoute = this.getSubRoute();
        result = result * 59 + ($subRoute == null ? 43 : $subRoute.hashCode());
        String $route = this.getRoute();
        result = result * 59 + ($route == null ? 43 : $route.hashCode());
        String $binNumber = this.getBinNumber();
        result = result * 59 + ($binNumber == null ? 43 : $binNumber.hashCode());
        String $cardType = this.getCardType();
        result = result * 59 + ($cardType == null ? 43 : $cardType.hashCode());
        String $remarks = this.getRemarks();
        result = result * 59 + ($remarks == null ? 43 : $remarks.hashCode());
        return result;
    }

    public String toString() {
        return "OmanNetBinUtil(user=" + this.getUser() + ", jobNumber=" + this.getJobNumber() + ", genStatus=" + this.getGenStatus() + ", subRoute=" + this.getSubRoute() + ", route=" + this.getRoute() + ", binNumber=" + this.getBinNumber() + ", cardType=" + this.getCardType() + ", remarks=" + this.getRemarks() + ")";
    }
}

