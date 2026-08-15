// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.tlfprocessing.vo;

public class IRFResultVo
{
    private Integer irdSerNumber;
    private Integer morSerNumber;
    private String irdCode;
    private Double irfPercentage;
    private Double irfFixed;
    private Double irfAmount;
    private Character domIntlFlag;
    private Character cardType;
    private String gcmsProductID;
    private String irfDesc;
    private String irfCountry;
    private Double irfMinAmount;
    private Double irfMaxAmount;
    private Double irfAmountUSD;
    
    public Integer getIrdSerNumber() {
        return this.irdSerNumber;
    }
    
    public Integer getMorSerNumber() {
        return this.morSerNumber;
    }
    
    public String getIrdCode() {
        return this.irdCode;
    }
    
    public Double getIrfPercentage() {
        return this.irfPercentage;
    }
    
    public Double getIrfFixed() {
        return this.irfFixed;
    }
    
    public Double getIrfAmount() {
        return this.irfAmount;
    }
    
    public Character getDomIntlFlag() {
        return this.domIntlFlag;
    }
    
    public Character getCardType() {
        return this.cardType;
    }
    
    public String getGcmsProductID() {
        return this.gcmsProductID;
    }
    
    public String getIrfDesc() {
        return this.irfDesc;
    }
    
    public String getIrfCountry() {
        return this.irfCountry;
    }
    
    public Double getIrfMinAmount() {
        return this.irfMinAmount;
    }
    
    public Double getIrfMaxAmount() {
        return this.irfMaxAmount;
    }
    
    public Double getIrfAmountUSD() {
        return this.irfAmountUSD;
    }
    
    public void setIrdSerNumber(final Integer irdSerNumber) {
        this.irdSerNumber = irdSerNumber;
    }
    
    public void setMorSerNumber(final Integer morSerNumber) {
        this.morSerNumber = morSerNumber;
    }
    
    public void setIrdCode(final String irdCode) {
        this.irdCode = irdCode;
    }
    
    public void setIrfPercentage(final Double irfPercentage) {
        this.irfPercentage = irfPercentage;
    }
    
    public void setIrfFixed(final Double irfFixed) {
        this.irfFixed = irfFixed;
    }
    
    public void setIrfAmount(final Double irfAmount) {
        this.irfAmount = irfAmount;
    }
    
    public void setDomIntlFlag(final Character domIntlFlag) {
        this.domIntlFlag = domIntlFlag;
    }
    
    public void setCardType(final Character cardType) {
        this.cardType = cardType;
    }
    
    public void setGcmsProductID(final String gcmsProductID) {
        this.gcmsProductID = gcmsProductID;
    }
    
    public void setIrfDesc(final String irfDesc) {
        this.irfDesc = irfDesc;
    }
    
    public void setIrfCountry(final String irfCountry) {
        this.irfCountry = irfCountry;
    }
    
    public void setIrfMinAmount(final Double irfMinAmount) {
        this.irfMinAmount = irfMinAmount;
    }
    
    public void setIrfMaxAmount(final Double irfMaxAmount) {
        this.irfMaxAmount = irfMaxAmount;
    }
    
    public void setIrfAmountUSD(final Double irfAmountUSD) {
        this.irfAmountUSD = irfAmountUSD;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof IRFResultVo)) {
            return false;
        }
        final IRFResultVo other = (IRFResultVo)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$irdSerNumber = this.getIrdSerNumber();
        final Object other$irdSerNumber = other.getIrdSerNumber();
        Label_0065: {
            if (this$irdSerNumber == null) {
                if (other$irdSerNumber == null) {
                    break Label_0065;
                }
            }
            else if (this$irdSerNumber.equals(other$irdSerNumber)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$morSerNumber = this.getMorSerNumber();
        final Object other$morSerNumber = other.getMorSerNumber();
        Label_0102: {
            if (this$morSerNumber == null) {
                if (other$morSerNumber == null) {
                    break Label_0102;
                }
            }
            else if (this$morSerNumber.equals(other$morSerNumber)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$irfPercentage = this.getIrfPercentage();
        final Object other$irfPercentage = other.getIrfPercentage();
        Label_0139: {
            if (this$irfPercentage == null) {
                if (other$irfPercentage == null) {
                    break Label_0139;
                }
            }
            else if (this$irfPercentage.equals(other$irfPercentage)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$irfFixed = this.getIrfFixed();
        final Object other$irfFixed = other.getIrfFixed();
        Label_0176: {
            if (this$irfFixed == null) {
                if (other$irfFixed == null) {
                    break Label_0176;
                }
            }
            else if (this$irfFixed.equals(other$irfFixed)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$irfAmount = this.getIrfAmount();
        final Object other$irfAmount = other.getIrfAmount();
        Label_0213: {
            if (this$irfAmount == null) {
                if (other$irfAmount == null) {
                    break Label_0213;
                }
            }
            else if (this$irfAmount.equals(other$irfAmount)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$domIntlFlag = this.getDomIntlFlag();
        final Object other$domIntlFlag = other.getDomIntlFlag();
        Label_0250: {
            if (this$domIntlFlag == null) {
                if (other$domIntlFlag == null) {
                    break Label_0250;
                }
            }
            else if (this$domIntlFlag.equals(other$domIntlFlag)) {
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
        final Object this$irfMinAmount = this.getIrfMinAmount();
        final Object other$irfMinAmount = other.getIrfMinAmount();
        Label_0324: {
            if (this$irfMinAmount == null) {
                if (other$irfMinAmount == null) {
                    break Label_0324;
                }
            }
            else if (this$irfMinAmount.equals(other$irfMinAmount)) {
                break Label_0324;
            }
            return false;
        }
        final Object this$irfMaxAmount = this.getIrfMaxAmount();
        final Object other$irfMaxAmount = other.getIrfMaxAmount();
        Label_0361: {
            if (this$irfMaxAmount == null) {
                if (other$irfMaxAmount == null) {
                    break Label_0361;
                }
            }
            else if (this$irfMaxAmount.equals(other$irfMaxAmount)) {
                break Label_0361;
            }
            return false;
        }
        final Object this$irfAmountUSD = this.getIrfAmountUSD();
        final Object other$irfAmountUSD = other.getIrfAmountUSD();
        Label_0398: {
            if (this$irfAmountUSD == null) {
                if (other$irfAmountUSD == null) {
                    break Label_0398;
                }
            }
            else if (this$irfAmountUSD.equals(other$irfAmountUSD)) {
                break Label_0398;
            }
            return false;
        }
        final Object this$irdCode = this.getIrdCode();
        final Object other$irdCode = other.getIrdCode();
        Label_0435: {
            if (this$irdCode == null) {
                if (other$irdCode == null) {
                    break Label_0435;
                }
            }
            else if (this$irdCode.equals(other$irdCode)) {
                break Label_0435;
            }
            return false;
        }
        final Object this$gcmsProductID = this.getGcmsProductID();
        final Object other$gcmsProductID = other.getGcmsProductID();
        Label_0472: {
            if (this$gcmsProductID == null) {
                if (other$gcmsProductID == null) {
                    break Label_0472;
                }
            }
            else if (this$gcmsProductID.equals(other$gcmsProductID)) {
                break Label_0472;
            }
            return false;
        }
        final Object this$irfDesc = this.getIrfDesc();
        final Object other$irfDesc = other.getIrfDesc();
        Label_0509: {
            if (this$irfDesc == null) {
                if (other$irfDesc == null) {
                    break Label_0509;
                }
            }
            else if (this$irfDesc.equals(other$irfDesc)) {
                break Label_0509;
            }
            return false;
        }
        final Object this$irfCountry = this.getIrfCountry();
        final Object other$irfCountry = other.getIrfCountry();
        if (this$irfCountry == null) {
            if (other$irfCountry == null) {
                return true;
            }
        }
        else if (this$irfCountry.equals(other$irfCountry)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof IRFResultVo;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $irdSerNumber = this.getIrdSerNumber();
        result = result * 59 + (($irdSerNumber == null) ? 43 : $irdSerNumber.hashCode());
        final Object $morSerNumber = this.getMorSerNumber();
        result = result * 59 + (($morSerNumber == null) ? 43 : $morSerNumber.hashCode());
        final Object $irfPercentage = this.getIrfPercentage();
        result = result * 59 + (($irfPercentage == null) ? 43 : $irfPercentage.hashCode());
        final Object $irfFixed = this.getIrfFixed();
        result = result * 59 + (($irfFixed == null) ? 43 : $irfFixed.hashCode());
        final Object $irfAmount = this.getIrfAmount();
        result = result * 59 + (($irfAmount == null) ? 43 : $irfAmount.hashCode());
        final Object $domIntlFlag = this.getDomIntlFlag();
        result = result * 59 + (($domIntlFlag == null) ? 43 : $domIntlFlag.hashCode());
        final Object $cardType = this.getCardType();
        result = result * 59 + (($cardType == null) ? 43 : $cardType.hashCode());
        final Object $irfMinAmount = this.getIrfMinAmount();
        result = result * 59 + (($irfMinAmount == null) ? 43 : $irfMinAmount.hashCode());
        final Object $irfMaxAmount = this.getIrfMaxAmount();
        result = result * 59 + (($irfMaxAmount == null) ? 43 : $irfMaxAmount.hashCode());
        final Object $irfAmountUSD = this.getIrfAmountUSD();
        result = result * 59 + (($irfAmountUSD == null) ? 43 : $irfAmountUSD.hashCode());
        final Object $irdCode = this.getIrdCode();
        result = result * 59 + (($irdCode == null) ? 43 : $irdCode.hashCode());
        final Object $gcmsProductID = this.getGcmsProductID();
        result = result * 59 + (($gcmsProductID == null) ? 43 : $gcmsProductID.hashCode());
        final Object $irfDesc = this.getIrfDesc();
        result = result * 59 + (($irfDesc == null) ? 43 : $irfDesc.hashCode());
        final Object $irfCountry = this.getIrfCountry();
        result = result * 59 + (($irfCountry == null) ? 43 : $irfCountry.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "IRFResultVo(irdSerNumber=" + this.getIrdSerNumber() + ", morSerNumber=" + this.getMorSerNumber() + ", irdCode=" + this.getIrdCode() + ", irfPercentage=" + this.getIrfPercentage() + ", irfFixed=" + this.getIrfFixed() + ", irfAmount=" + this.getIrfAmount() + ", domIntlFlag=" + this.getDomIntlFlag() + ", cardType=" + this.getCardType() + ", gcmsProductID=" + this.getGcmsProductID() + ", irfDesc=" + this.getIrfDesc() + ", irfCountry=" + this.getIrfCountry() + ", irfMinAmount=" + this.getIrfMinAmount() + ", irfMaxAmount=" + this.getIrfMaxAmount() + ", irfAmountUSD=" + this.getIrfAmountUSD();
    }
}
