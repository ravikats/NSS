// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.common.entities;

import jakarta.persistence.Column;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;

@Entity
@Table(name = "MC_PRODUCT_MAPPING")
public class McProductMappingEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MPM_SER_NUMBER")
    private Integer serialNumber;
    @Column(name = "MPM_GCMS_ID")
    private String gcmsProductId;
    @Column(name = "MOR_IRD")
    private String ird;
    @Column(name = "MPM_CARD_TYPE")
    private Character cardType;
    
    public Integer getSerialNumber() {
        return this.serialNumber;
    }
    
    public String getGcmsProductId() {
        return this.gcmsProductId;
    }
    
    public String getIrd() {
        return this.ird;
    }
    
    public Character getCardType() {
        return this.cardType;
    }
    
    public void setSerialNumber(final Integer serialNumber) {
        this.serialNumber = serialNumber;
    }
    
    public void setGcmsProductId(final String gcmsProductId) {
        this.gcmsProductId = gcmsProductId;
    }
    
    public void setIrd(final String ird) {
        this.ird = ird;
    }
    
    public void setCardType(final Character cardType) {
        this.cardType = cardType;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof McProductMappingEntity)) {
            return false;
        }
        final McProductMappingEntity other = (McProductMappingEntity)o;
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
        final Object this$cardType = this.getCardType();
        final Object other$cardType = other.getCardType();
        Label_0102: {
            if (this$cardType == null) {
                if (other$cardType == null) {
                    break Label_0102;
                }
            }
            else if (this$cardType.equals(other$cardType)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$gcmsProductId = this.getGcmsProductId();
        final Object other$gcmsProductId = other.getGcmsProductId();
        Label_0139: {
            if (this$gcmsProductId == null) {
                if (other$gcmsProductId == null) {
                    break Label_0139;
                }
            }
            else if (this$gcmsProductId.equals(other$gcmsProductId)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$ird = this.getIrd();
        final Object other$ird = other.getIrd();
        if (this$ird == null) {
            if (other$ird == null) {
                return true;
            }
        }
        else if (this$ird.equals(other$ird)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof McProductMappingEntity;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $serialNumber = this.getSerialNumber();
        result = result * 59 + (($serialNumber == null) ? 43 : $serialNumber.hashCode());
        final Object $cardType = this.getCardType();
        result = result * 59 + (($cardType == null) ? 43 : $cardType.hashCode());
        final Object $gcmsProductId = this.getGcmsProductId();
        result = result * 59 + (($gcmsProductId == null) ? 43 : $gcmsProductId.hashCode());
        final Object $ird = this.getIrd();
        result = result * 59 + (($ird == null) ? 43 : $ird.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "McProductMappingEntity(serialNumber=" + this.getSerialNumber() + ", gcmsProductId=" + this.getGcmsProductId() + ", ird=" + this.getIrd() + ", cardType=" + this.getCardType();
    }
}
