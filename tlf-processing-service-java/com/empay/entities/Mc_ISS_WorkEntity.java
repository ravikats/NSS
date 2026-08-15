// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.entities;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;

@Entity
@Table(name = "MC_ISS_WORK")
public class Mc_ISS_WorkEntity
{
    @Id
    @Column(name = "MAR_PID")
    private Integer pid;
    @Column(name = "MAR_ISS_RANGE_LOW")
    private String issRangeLow;
    @Column(name = "MAR_ISS_RANGE_HIGH")
    private String issRangeHigh;
    @Column(name = "MAR_GCMS_PROD_ID")
    private String gcmsProdId;
    @Column(name = "MAR_CARD_PROG_ID")
    private String cardProgId;
    @Column(name = "MAR_PRIORITY_CODE")
    private String priorityCode;
    @Column(name = "MAR_PROD_TYPE_ID")
    private Character prodTypeId;
    @Column(name = "MAR_COUNTRY_CODE")
    private String countryCode;
    @Column(name = "MAR_COUNTRY_ALPHA_CODE")
    private String countryAlphaCode;
    @Column(name = "MAR_REGION")
    private Character region;
    @Column(name = "MAR_PRODUCT_CLASS")
    private String productClass;
    @Column(name = "MAR_CARD_NUMBER")
    private String cardNumber;
    @Column(name = "MAR_EFFECTIVE_DATE")
    private LocalDate effectiveDate;
    
    public static Mc_ISS_WorkEntityBuilder builder() {
        return new Mc_ISS_WorkEntityBuilder();
    }
    
    public Integer getPid() {
        return this.pid;
    }
    
    public String getIssRangeLow() {
        return this.issRangeLow;
    }
    
    public String getIssRangeHigh() {
        return this.issRangeHigh;
    }
    
    public String getGcmsProdId() {
        return this.gcmsProdId;
    }
    
    public String getCardProgId() {
        return this.cardProgId;
    }
    
    public String getPriorityCode() {
        return this.priorityCode;
    }
    
    public Character getProdTypeId() {
        return this.prodTypeId;
    }
    
    public String getCountryCode() {
        return this.countryCode;
    }
    
    public String getCountryAlphaCode() {
        return this.countryAlphaCode;
    }
    
    public Character getRegion() {
        return this.region;
    }
    
    public String getProductClass() {
        return this.productClass;
    }
    
    public String getCardNumber() {
        return this.cardNumber;
    }
    
    public LocalDate getEffectiveDate() {
        return this.effectiveDate;
    }
    
    public void setPid(final Integer pid) {
        this.pid = pid;
    }
    
    public void setIssRangeLow(final String issRangeLow) {
        this.issRangeLow = issRangeLow;
    }
    
    public void setIssRangeHigh(final String issRangeHigh) {
        this.issRangeHigh = issRangeHigh;
    }
    
    public void setGcmsProdId(final String gcmsProdId) {
        this.gcmsProdId = gcmsProdId;
    }
    
    public void setCardProgId(final String cardProgId) {
        this.cardProgId = cardProgId;
    }
    
    public void setPriorityCode(final String priorityCode) {
        this.priorityCode = priorityCode;
    }
    
    public void setProdTypeId(final Character prodTypeId) {
        this.prodTypeId = prodTypeId;
    }
    
    public void setCountryCode(final String countryCode) {
        this.countryCode = countryCode;
    }
    
    public void setCountryAlphaCode(final String countryAlphaCode) {
        this.countryAlphaCode = countryAlphaCode;
    }
    
    public void setRegion(final Character region) {
        this.region = region;
    }
    
    public void setProductClass(final String productClass) {
        this.productClass = productClass;
    }
    
    public void setCardNumber(final String cardNumber) {
        this.cardNumber = cardNumber;
    }
    
    public void setEffectiveDate(final LocalDate effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Mc_ISS_WorkEntity)) {
            return false;
        }
        final Mc_ISS_WorkEntity other = (Mc_ISS_WorkEntity)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$pid = this.getPid();
        final Object other$pid = other.getPid();
        Label_0065: {
            if (this$pid == null) {
                if (other$pid == null) {
                    break Label_0065;
                }
            }
            else if (this$pid.equals(other$pid)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$prodTypeId = this.getProdTypeId();
        final Object other$prodTypeId = other.getProdTypeId();
        Label_0102: {
            if (this$prodTypeId == null) {
                if (other$prodTypeId == null) {
                    break Label_0102;
                }
            }
            else if (this$prodTypeId.equals(other$prodTypeId)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$region = this.getRegion();
        final Object other$region = other.getRegion();
        Label_0139: {
            if (this$region == null) {
                if (other$region == null) {
                    break Label_0139;
                }
            }
            else if (this$region.equals(other$region)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$issRangeLow = this.getIssRangeLow();
        final Object other$issRangeLow = other.getIssRangeLow();
        Label_0176: {
            if (this$issRangeLow == null) {
                if (other$issRangeLow == null) {
                    break Label_0176;
                }
            }
            else if (this$issRangeLow.equals(other$issRangeLow)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$issRangeHigh = this.getIssRangeHigh();
        final Object other$issRangeHigh = other.getIssRangeHigh();
        Label_0213: {
            if (this$issRangeHigh == null) {
                if (other$issRangeHigh == null) {
                    break Label_0213;
                }
            }
            else if (this$issRangeHigh.equals(other$issRangeHigh)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$gcmsProdId = this.getGcmsProdId();
        final Object other$gcmsProdId = other.getGcmsProdId();
        Label_0250: {
            if (this$gcmsProdId == null) {
                if (other$gcmsProdId == null) {
                    break Label_0250;
                }
            }
            else if (this$gcmsProdId.equals(other$gcmsProdId)) {
                break Label_0250;
            }
            return false;
        }
        final Object this$cardProgId = this.getCardProgId();
        final Object other$cardProgId = other.getCardProgId();
        Label_0287: {
            if (this$cardProgId == null) {
                if (other$cardProgId == null) {
                    break Label_0287;
                }
            }
            else if (this$cardProgId.equals(other$cardProgId)) {
                break Label_0287;
            }
            return false;
        }
        final Object this$priorityCode = this.getPriorityCode();
        final Object other$priorityCode = other.getPriorityCode();
        Label_0324: {
            if (this$priorityCode == null) {
                if (other$priorityCode == null) {
                    break Label_0324;
                }
            }
            else if (this$priorityCode.equals(other$priorityCode)) {
                break Label_0324;
            }
            return false;
        }
        final Object this$countryCode = this.getCountryCode();
        final Object other$countryCode = other.getCountryCode();
        Label_0361: {
            if (this$countryCode == null) {
                if (other$countryCode == null) {
                    break Label_0361;
                }
            }
            else if (this$countryCode.equals(other$countryCode)) {
                break Label_0361;
            }
            return false;
        }
        final Object this$countryAlphaCode = this.getCountryAlphaCode();
        final Object other$countryAlphaCode = other.getCountryAlphaCode();
        Label_0398: {
            if (this$countryAlphaCode == null) {
                if (other$countryAlphaCode == null) {
                    break Label_0398;
                }
            }
            else if (this$countryAlphaCode.equals(other$countryAlphaCode)) {
                break Label_0398;
            }
            return false;
        }
        final Object this$productClass = this.getProductClass();
        final Object other$productClass = other.getProductClass();
        Label_0435: {
            if (this$productClass == null) {
                if (other$productClass == null) {
                    break Label_0435;
                }
            }
            else if (this$productClass.equals(other$productClass)) {
                break Label_0435;
            }
            return false;
        }
        final Object this$cardNumber = this.getCardNumber();
        final Object other$cardNumber = other.getCardNumber();
        Label_0472: {
            if (this$cardNumber == null) {
                if (other$cardNumber == null) {
                    break Label_0472;
                }
            }
            else if (this$cardNumber.equals(other$cardNumber)) {
                break Label_0472;
            }
            return false;
        }
        final Object this$effectiveDate = this.getEffectiveDate();
        final Object other$effectiveDate = other.getEffectiveDate();
        if (this$effectiveDate == null) {
            if (other$effectiveDate == null) {
                return true;
            }
        }
        else if (this$effectiveDate.equals(other$effectiveDate)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof Mc_ISS_WorkEntity;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $pid = this.getPid();
        result = result * 59 + (($pid == null) ? 43 : $pid.hashCode());
        final Object $prodTypeId = this.getProdTypeId();
        result = result * 59 + (($prodTypeId == null) ? 43 : $prodTypeId.hashCode());
        final Object $region = this.getRegion();
        result = result * 59 + (($region == null) ? 43 : $region.hashCode());
        final Object $issRangeLow = this.getIssRangeLow();
        result = result * 59 + (($issRangeLow == null) ? 43 : $issRangeLow.hashCode());
        final Object $issRangeHigh = this.getIssRangeHigh();
        result = result * 59 + (($issRangeHigh == null) ? 43 : $issRangeHigh.hashCode());
        final Object $gcmsProdId = this.getGcmsProdId();
        result = result * 59 + (($gcmsProdId == null) ? 43 : $gcmsProdId.hashCode());
        final Object $cardProgId = this.getCardProgId();
        result = result * 59 + (($cardProgId == null) ? 43 : $cardProgId.hashCode());
        final Object $priorityCode = this.getPriorityCode();
        result = result * 59 + (($priorityCode == null) ? 43 : $priorityCode.hashCode());
        final Object $countryCode = this.getCountryCode();
        result = result * 59 + (($countryCode == null) ? 43 : $countryCode.hashCode());
        final Object $countryAlphaCode = this.getCountryAlphaCode();
        result = result * 59 + (($countryAlphaCode == null) ? 43 : $countryAlphaCode.hashCode());
        final Object $productClass = this.getProductClass();
        result = result * 59 + (($productClass == null) ? 43 : $productClass.hashCode());
        final Object $cardNumber = this.getCardNumber();
        result = result * 59 + (($cardNumber == null) ? 43 : $cardNumber.hashCode());
        final Object $effectiveDate = this.getEffectiveDate();
        result = result * 59 + (($effectiveDate == null) ? 43 : $effectiveDate.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "Mc_ISS_WorkEntity(pid=" + this.getPid() + ", issRangeLow=" + this.getIssRangeLow() + ", issRangeHigh=" + this.getIssRangeHigh() + ", gcmsProdId=" + this.getGcmsProdId() + ", cardProgId=" + this.getCardProgId() + ", priorityCode=" + this.getPriorityCode() + ", prodTypeId=" + this.getProdTypeId() + ", countryCode=" + this.getCountryCode() + ", countryAlphaCode=" + this.getCountryAlphaCode() + ", region=" + this.getRegion() + ", productClass=" + this.getProductClass() + ", cardNumber=" + this.getCardNumber() + ", effectiveDate=" + String.valueOf(this.getEffectiveDate());
    }
    
    public Mc_ISS_WorkEntity() {
    }
    
    public Mc_ISS_WorkEntity(final Integer pid, final String issRangeLow, final String issRangeHigh, final String gcmsProdId, final String cardProgId, final String priorityCode, final Character prodTypeId, final String countryCode, final String countryAlphaCode, final Character region, final String productClass, final String cardNumber, final LocalDate effectiveDate) {
        this.pid = pid;
        this.issRangeLow = issRangeLow;
        this.issRangeHigh = issRangeHigh;
        this.gcmsProdId = gcmsProdId;
        this.cardProgId = cardProgId;
        this.priorityCode = priorityCode;
        this.prodTypeId = prodTypeId;
        this.countryCode = countryCode;
        this.countryAlphaCode = countryAlphaCode;
        this.region = region;
        this.productClass = productClass;
        this.cardNumber = cardNumber;
        this.effectiveDate = effectiveDate;
    }
    
    public static class Mc_ISS_WorkEntityBuilder
    {
        private Integer pid;
        private String issRangeLow;
        private String issRangeHigh;
        private String gcmsProdId;
        private String cardProgId;
        private String priorityCode;
        private Character prodTypeId;
        private String countryCode;
        private String countryAlphaCode;
        private Character region;
        private String productClass;
        private String cardNumber;
        private LocalDate effectiveDate;
        
        Mc_ISS_WorkEntityBuilder() {
        }
        
        public Mc_ISS_WorkEntityBuilder pid(final Integer pid) {
            this.pid = pid;
            return this;
        }
        
        public Mc_ISS_WorkEntityBuilder issRangeLow(final String issRangeLow) {
            this.issRangeLow = issRangeLow;
            return this;
        }
        
        public Mc_ISS_WorkEntityBuilder issRangeHigh(final String issRangeHigh) {
            this.issRangeHigh = issRangeHigh;
            return this;
        }
        
        public Mc_ISS_WorkEntityBuilder gcmsProdId(final String gcmsProdId) {
            this.gcmsProdId = gcmsProdId;
            return this;
        }
        
        public Mc_ISS_WorkEntityBuilder cardProgId(final String cardProgId) {
            this.cardProgId = cardProgId;
            return this;
        }
        
        public Mc_ISS_WorkEntityBuilder priorityCode(final String priorityCode) {
            this.priorityCode = priorityCode;
            return this;
        }
        
        public Mc_ISS_WorkEntityBuilder prodTypeId(final Character prodTypeId) {
            this.prodTypeId = prodTypeId;
            return this;
        }
        
        public Mc_ISS_WorkEntityBuilder countryCode(final String countryCode) {
            this.countryCode = countryCode;
            return this;
        }
        
        public Mc_ISS_WorkEntityBuilder countryAlphaCode(final String countryAlphaCode) {
            this.countryAlphaCode = countryAlphaCode;
            return this;
        }
        
        public Mc_ISS_WorkEntityBuilder region(final Character region) {
            this.region = region;
            return this;
        }
        
        public Mc_ISS_WorkEntityBuilder productClass(final String productClass) {
            this.productClass = productClass;
            return this;
        }
        
        public Mc_ISS_WorkEntityBuilder cardNumber(final String cardNumber) {
            this.cardNumber = cardNumber;
            return this;
        }
        
        public Mc_ISS_WorkEntityBuilder effectiveDate(final LocalDate effectiveDate) {
            this.effectiveDate = effectiveDate;
            return this;
        }
        
        public Mc_ISS_WorkEntity build() {
            return new Mc_ISS_WorkEntity(this.pid, this.issRangeLow, this.issRangeHigh, this.gcmsProdId, this.cardProgId, this.priorityCode, this.prodTypeId, this.countryCode, this.countryAlphaCode, this.region, this.productClass, this.cardNumber, this.effectiveDate);
        }
        
        @Override
        public String toString() {
            return "Mc_ISS_WorkEntity.Mc_ISS_WorkEntityBuilder(pid=" + this.pid + ", issRangeLow=" + this.issRangeLow + ", issRangeHigh=" + this.issRangeHigh + ", gcmsProdId=" + this.gcmsProdId + ", cardProgId=" + this.cardProgId + ", priorityCode=" + this.priorityCode + ", prodTypeId=" + this.prodTypeId + ", countryCode=" + this.countryCode + ", countryAlphaCode=" + this.countryAlphaCode + ", region=" + this.region + ", productClass=" + this.productClass + ", cardNumber=" + this.cardNumber + ", effectiveDate=" + String.valueOf(this.effectiveDate);
        }
    }
}
