// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;

@Entity
@Table(name = "IPM_IRD_CODES")
public class IpmIrdCodeEntity
{
    @Id
    @Column(name = "IIC_SER_NUMBER")
    private Integer serNumber;
    @Column(name = "IIC_PROG_REGION")
    private Character region;
    @Column(name = "IIC_PROC_CODE")
    private Integer procCode;
    @Column(name = "IIC_UPDATED_USER")
    private Integer updatedUser;
    @Column(name = "IIC_IRD")
    private String ird;
    @Column(name = "IIC_PRIORITY")
    private Integer priority;
    @Column(name = "IIC_PROG_NAME")
    private String name;
    @Column(name = "IIC_RATE_PERCENT")
    private Double ratePercent;
    @Column(name = "IIC_RATE_FIXED")
    private Double rateFixed;
    @Column(name = "IIC_TIMELINE")
    private Integer timeLine;
    @Column(name = "IIC_APPR_CODE")
    private Integer apprCode;
    @Column(name = "IIC_MAGSTRIPE_DATA")
    private Integer magStripeData;
    @Column(name = "IIC_TRACE_ID")
    private Integer traceId;
    @Column(name = "IIC_TOLERANCE_PERCENT")
    private Double tolerencePercent;
    @Column(name = "IIC_CARD_ACCEPT_ID")
    private Integer cardAcceptId;
    @Column(name = "IIC_CARD_ACCEPT_NAME")
    private Integer cardAcceptName;
    @Column(name = "IIC_CARD_ACCEPT_ADDRESS")
    private Integer cardAcceptAddress;
    @Column(name = "IIC_CARD_ACCEPT_CITY")
    private Integer cardAcceptCity;
    @Column(name = "IIC_CARD_ACCEPT_ZIP")
    private Integer cardAcceptZip;
    
    public Integer getSerNumber() {
        return this.serNumber;
    }
    
    public Character getRegion() {
        return this.region;
    }
    
    public Integer getProcCode() {
        return this.procCode;
    }
    
    public Integer getUpdatedUser() {
        return this.updatedUser;
    }
    
    public String getIrd() {
        return this.ird;
    }
    
    public Integer getPriority() {
        return this.priority;
    }
    
    public String getName() {
        return this.name;
    }
    
    public Double getRatePercent() {
        return this.ratePercent;
    }
    
    public Double getRateFixed() {
        return this.rateFixed;
    }
    
    public Integer getTimeLine() {
        return this.timeLine;
    }
    
    public Integer getApprCode() {
        return this.apprCode;
    }
    
    public Integer getMagStripeData() {
        return this.magStripeData;
    }
    
    public Integer getTraceId() {
        return this.traceId;
    }
    
    public Double getTolerencePercent() {
        return this.tolerencePercent;
    }
    
    public Integer getCardAcceptId() {
        return this.cardAcceptId;
    }
    
    public Integer getCardAcceptName() {
        return this.cardAcceptName;
    }
    
    public Integer getCardAcceptAddress() {
        return this.cardAcceptAddress;
    }
    
    public Integer getCardAcceptCity() {
        return this.cardAcceptCity;
    }
    
    public Integer getCardAcceptZip() {
        return this.cardAcceptZip;
    }
    
    public void setSerNumber(final Integer serNumber) {
        this.serNumber = serNumber;
    }
    
    public void setRegion(final Character region) {
        this.region = region;
    }
    
    public void setProcCode(final Integer procCode) {
        this.procCode = procCode;
    }
    
    public void setUpdatedUser(final Integer updatedUser) {
        this.updatedUser = updatedUser;
    }
    
    public void setIrd(final String ird) {
        this.ird = ird;
    }
    
    public void setPriority(final Integer priority) {
        this.priority = priority;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public void setRatePercent(final Double ratePercent) {
        this.ratePercent = ratePercent;
    }
    
    public void setRateFixed(final Double rateFixed) {
        this.rateFixed = rateFixed;
    }
    
    public void setTimeLine(final Integer timeLine) {
        this.timeLine = timeLine;
    }
    
    public void setApprCode(final Integer apprCode) {
        this.apprCode = apprCode;
    }
    
    public void setMagStripeData(final Integer magStripeData) {
        this.magStripeData = magStripeData;
    }
    
    public void setTraceId(final Integer traceId) {
        this.traceId = traceId;
    }
    
    public void setTolerencePercent(final Double tolerencePercent) {
        this.tolerencePercent = tolerencePercent;
    }
    
    public void setCardAcceptId(final Integer cardAcceptId) {
        this.cardAcceptId = cardAcceptId;
    }
    
    public void setCardAcceptName(final Integer cardAcceptName) {
        this.cardAcceptName = cardAcceptName;
    }
    
    public void setCardAcceptAddress(final Integer cardAcceptAddress) {
        this.cardAcceptAddress = cardAcceptAddress;
    }
    
    public void setCardAcceptCity(final Integer cardAcceptCity) {
        this.cardAcceptCity = cardAcceptCity;
    }
    
    public void setCardAcceptZip(final Integer cardAcceptZip) {
        this.cardAcceptZip = cardAcceptZip;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof IpmIrdCodeEntity)) {
            return false;
        }
        final IpmIrdCodeEntity other = (IpmIrdCodeEntity)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$serNumber = this.getSerNumber();
        final Object other$serNumber = other.getSerNumber();
        Label_0065: {
            if (this$serNumber == null) {
                if (other$serNumber == null) {
                    break Label_0065;
                }
            }
            else if (this$serNumber.equals(other$serNumber)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$region = this.getRegion();
        final Object other$region = other.getRegion();
        Label_0102: {
            if (this$region == null) {
                if (other$region == null) {
                    break Label_0102;
                }
            }
            else if (this$region.equals(other$region)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$procCode = this.getProcCode();
        final Object other$procCode = other.getProcCode();
        Label_0139: {
            if (this$procCode == null) {
                if (other$procCode == null) {
                    break Label_0139;
                }
            }
            else if (this$procCode.equals(other$procCode)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$updatedUser = this.getUpdatedUser();
        final Object other$updatedUser = other.getUpdatedUser();
        Label_0176: {
            if (this$updatedUser == null) {
                if (other$updatedUser == null) {
                    break Label_0176;
                }
            }
            else if (this$updatedUser.equals(other$updatedUser)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$priority = this.getPriority();
        final Object other$priority = other.getPriority();
        Label_0213: {
            if (this$priority == null) {
                if (other$priority == null) {
                    break Label_0213;
                }
            }
            else if (this$priority.equals(other$priority)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$ratePercent = this.getRatePercent();
        final Object other$ratePercent = other.getRatePercent();
        Label_0250: {
            if (this$ratePercent == null) {
                if (other$ratePercent == null) {
                    break Label_0250;
                }
            }
            else if (this$ratePercent.equals(other$ratePercent)) {
                break Label_0250;
            }
            return false;
        }
        final Object this$rateFixed = this.getRateFixed();
        final Object other$rateFixed = other.getRateFixed();
        Label_0287: {
            if (this$rateFixed == null) {
                if (other$rateFixed == null) {
                    break Label_0287;
                }
            }
            else if (this$rateFixed.equals(other$rateFixed)) {
                break Label_0287;
            }
            return false;
        }
        final Object this$timeLine = this.getTimeLine();
        final Object other$timeLine = other.getTimeLine();
        Label_0324: {
            if (this$timeLine == null) {
                if (other$timeLine == null) {
                    break Label_0324;
                }
            }
            else if (this$timeLine.equals(other$timeLine)) {
                break Label_0324;
            }
            return false;
        }
        final Object this$apprCode = this.getApprCode();
        final Object other$apprCode = other.getApprCode();
        Label_0361: {
            if (this$apprCode == null) {
                if (other$apprCode == null) {
                    break Label_0361;
                }
            }
            else if (this$apprCode.equals(other$apprCode)) {
                break Label_0361;
            }
            return false;
        }
        final Object this$magStripeData = this.getMagStripeData();
        final Object other$magStripeData = other.getMagStripeData();
        Label_0398: {
            if (this$magStripeData == null) {
                if (other$magStripeData == null) {
                    break Label_0398;
                }
            }
            else if (this$magStripeData.equals(other$magStripeData)) {
                break Label_0398;
            }
            return false;
        }
        final Object this$traceId = this.getTraceId();
        final Object other$traceId = other.getTraceId();
        Label_0435: {
            if (this$traceId == null) {
                if (other$traceId == null) {
                    break Label_0435;
                }
            }
            else if (this$traceId.equals(other$traceId)) {
                break Label_0435;
            }
            return false;
        }
        final Object this$tolerencePercent = this.getTolerencePercent();
        final Object other$tolerencePercent = other.getTolerencePercent();
        Label_0472: {
            if (this$tolerencePercent == null) {
                if (other$tolerencePercent == null) {
                    break Label_0472;
                }
            }
            else if (this$tolerencePercent.equals(other$tolerencePercent)) {
                break Label_0472;
            }
            return false;
        }
        final Object this$cardAcceptId = this.getCardAcceptId();
        final Object other$cardAcceptId = other.getCardAcceptId();
        Label_0509: {
            if (this$cardAcceptId == null) {
                if (other$cardAcceptId == null) {
                    break Label_0509;
                }
            }
            else if (this$cardAcceptId.equals(other$cardAcceptId)) {
                break Label_0509;
            }
            return false;
        }
        final Object this$cardAcceptName = this.getCardAcceptName();
        final Object other$cardAcceptName = other.getCardAcceptName();
        Label_0546: {
            if (this$cardAcceptName == null) {
                if (other$cardAcceptName == null) {
                    break Label_0546;
                }
            }
            else if (this$cardAcceptName.equals(other$cardAcceptName)) {
                break Label_0546;
            }
            return false;
        }
        final Object this$cardAcceptAddress = this.getCardAcceptAddress();
        final Object other$cardAcceptAddress = other.getCardAcceptAddress();
        Label_0583: {
            if (this$cardAcceptAddress == null) {
                if (other$cardAcceptAddress == null) {
                    break Label_0583;
                }
            }
            else if (this$cardAcceptAddress.equals(other$cardAcceptAddress)) {
                break Label_0583;
            }
            return false;
        }
        final Object this$cardAcceptCity = this.getCardAcceptCity();
        final Object other$cardAcceptCity = other.getCardAcceptCity();
        Label_0620: {
            if (this$cardAcceptCity == null) {
                if (other$cardAcceptCity == null) {
                    break Label_0620;
                }
            }
            else if (this$cardAcceptCity.equals(other$cardAcceptCity)) {
                break Label_0620;
            }
            return false;
        }
        final Object this$cardAcceptZip = this.getCardAcceptZip();
        final Object other$cardAcceptZip = other.getCardAcceptZip();
        Label_0657: {
            if (this$cardAcceptZip == null) {
                if (other$cardAcceptZip == null) {
                    break Label_0657;
                }
            }
            else if (this$cardAcceptZip.equals(other$cardAcceptZip)) {
                break Label_0657;
            }
            return false;
        }
        final Object this$ird = this.getIrd();
        final Object other$ird = other.getIrd();
        Label_0694: {
            if (this$ird == null) {
                if (other$ird == null) {
                    break Label_0694;
                }
            }
            else if (this$ird.equals(other$ird)) {
                break Label_0694;
            }
            return false;
        }
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null) {
            if (other$name == null) {
                return true;
            }
        }
        else if (this$name.equals(other$name)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof IpmIrdCodeEntity;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $serNumber = this.getSerNumber();
        result = result * 59 + (($serNumber == null) ? 43 : $serNumber.hashCode());
        final Object $region = this.getRegion();
        result = result * 59 + (($region == null) ? 43 : $region.hashCode());
        final Object $procCode = this.getProcCode();
        result = result * 59 + (($procCode == null) ? 43 : $procCode.hashCode());
        final Object $updatedUser = this.getUpdatedUser();
        result = result * 59 + (($updatedUser == null) ? 43 : $updatedUser.hashCode());
        final Object $priority = this.getPriority();
        result = result * 59 + (($priority == null) ? 43 : $priority.hashCode());
        final Object $ratePercent = this.getRatePercent();
        result = result * 59 + (($ratePercent == null) ? 43 : $ratePercent.hashCode());
        final Object $rateFixed = this.getRateFixed();
        result = result * 59 + (($rateFixed == null) ? 43 : $rateFixed.hashCode());
        final Object $timeLine = this.getTimeLine();
        result = result * 59 + (($timeLine == null) ? 43 : $timeLine.hashCode());
        final Object $apprCode = this.getApprCode();
        result = result * 59 + (($apprCode == null) ? 43 : $apprCode.hashCode());
        final Object $magStripeData = this.getMagStripeData();
        result = result * 59 + (($magStripeData == null) ? 43 : $magStripeData.hashCode());
        final Object $traceId = this.getTraceId();
        result = result * 59 + (($traceId == null) ? 43 : $traceId.hashCode());
        final Object $tolerencePercent = this.getTolerencePercent();
        result = result * 59 + (($tolerencePercent == null) ? 43 : $tolerencePercent.hashCode());
        final Object $cardAcceptId = this.getCardAcceptId();
        result = result * 59 + (($cardAcceptId == null) ? 43 : $cardAcceptId.hashCode());
        final Object $cardAcceptName = this.getCardAcceptName();
        result = result * 59 + (($cardAcceptName == null) ? 43 : $cardAcceptName.hashCode());
        final Object $cardAcceptAddress = this.getCardAcceptAddress();
        result = result * 59 + (($cardAcceptAddress == null) ? 43 : $cardAcceptAddress.hashCode());
        final Object $cardAcceptCity = this.getCardAcceptCity();
        result = result * 59 + (($cardAcceptCity == null) ? 43 : $cardAcceptCity.hashCode());
        final Object $cardAcceptZip = this.getCardAcceptZip();
        result = result * 59 + (($cardAcceptZip == null) ? 43 : $cardAcceptZip.hashCode());
        final Object $ird = this.getIrd();
        result = result * 59 + (($ird == null) ? 43 : $ird.hashCode());
        final Object $name = this.getName();
        result = result * 59 + (($name == null) ? 43 : $name.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "IpmIrdCodeEntity(serNumber=" + this.getSerNumber() + ", region=" + this.getRegion() + ", procCode=" + this.getProcCode() + ", updatedUser=" + this.getUpdatedUser() + ", ird=" + this.getIrd() + ", priority=" + this.getPriority() + ", name=" + this.getName() + ", ratePercent=" + this.getRatePercent() + ", rateFixed=" + this.getRateFixed() + ", timeLine=" + this.getTimeLine() + ", apprCode=" + this.getApprCode() + ", magStripeData=" + this.getMagStripeData() + ", traceId=" + this.getTraceId() + ", tolerencePercent=" + this.getTolerencePercent() + ", cardAcceptId=" + this.getCardAcceptId() + ", cardAcceptName=" + this.getCardAcceptName() + ", cardAcceptAddress=" + this.getCardAcceptAddress() + ", cardAcceptCity=" + this.getCardAcceptCity() + ", cardAcceptZip=" + this.getCardAcceptZip();
    }
}
