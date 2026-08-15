// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.common.vo;

import java.time.LocalDateTime;

public class IRFRequestVo
{
    private Integer insCode;
    private String pan;
    private LocalDateTime txnDateTime;
    private Double txnAmount;
    private String terminalType;
    private String mcc;
    private String feePgmIndicator;
    private Character reimbAttribute;
    private String posEntryMode;
    private String terminalCapability;
    private String authCode;
    private String responseCode;
    private String motoEcomIndicator;
    private String fwdinstCode;
    private String mvv;
    private Character qualifierIndicator;
    private Character issuerRegion;
    private String countryCode;
    private String cardProduct;
    private Character crDrIndicator;
    private Character cardDomIntlFlag;
    private Character progRegion;
    private String irdCode;
    private Character txnLimitIndicator;
    private Character crDrIndicatorActual;
    
    public Integer getInsCode() {
        return this.insCode;
    }
    
    public String getPan() {
        return this.pan;
    }
    
    public LocalDateTime getTxnDateTime() {
        return this.txnDateTime;
    }
    
    public Double getTxnAmount() {
        return this.txnAmount;
    }
    
    public String getTerminalType() {
        return this.terminalType;
    }
    
    public String getMcc() {
        return this.mcc;
    }
    
    public String getFeePgmIndicator() {
        return this.feePgmIndicator;
    }
    
    public Character getReimbAttribute() {
        return this.reimbAttribute;
    }
    
    public String getPosEntryMode() {
        return this.posEntryMode;
    }
    
    public String getTerminalCapability() {
        return this.terminalCapability;
    }
    
    public String getAuthCode() {
        return this.authCode;
    }
    
    public String getResponseCode() {
        return this.responseCode;
    }
    
    public String getMotoEcomIndicator() {
        return this.motoEcomIndicator;
    }
    
    public String getFwdinstCode() {
        return this.fwdinstCode;
    }
    
    public String getMvv() {
        return this.mvv;
    }
    
    public Character getQualifierIndicator() {
        return this.qualifierIndicator;
    }
    
    public Character getIssuerRegion() {
        return this.issuerRegion;
    }
    
    public String getCountryCode() {
        return this.countryCode;
    }
    
    public String getCardProduct() {
        return this.cardProduct;
    }
    
    public Character getCrDrIndicator() {
        return this.crDrIndicator;
    }
    
    public Character getCardDomIntlFlag() {
        return this.cardDomIntlFlag;
    }
    
    public Character getProgRegion() {
        return this.progRegion;
    }
    
    public String getIrdCode() {
        return this.irdCode;
    }
    
    public Character getTxnLimitIndicator() {
        return this.txnLimitIndicator;
    }
    
    public Character getCrDrIndicatorActual() {
        return this.crDrIndicatorActual;
    }
    
    public void setInsCode(final Integer insCode) {
        this.insCode = insCode;
    }
    
    public void setPan(final String pan) {
        this.pan = pan;
    }
    
    public void setTxnDateTime(final LocalDateTime txnDateTime) {
        this.txnDateTime = txnDateTime;
    }
    
    public void setTxnAmount(final Double txnAmount) {
        this.txnAmount = txnAmount;
    }
    
    public void setTerminalType(final String terminalType) {
        this.terminalType = terminalType;
    }
    
    public void setMcc(final String mcc) {
        this.mcc = mcc;
    }
    
    public void setFeePgmIndicator(final String feePgmIndicator) {
        this.feePgmIndicator = feePgmIndicator;
    }
    
    public void setReimbAttribute(final Character reimbAttribute) {
        this.reimbAttribute = reimbAttribute;
    }
    
    public void setPosEntryMode(final String posEntryMode) {
        this.posEntryMode = posEntryMode;
    }
    
    public void setTerminalCapability(final String terminalCapability) {
        this.terminalCapability = terminalCapability;
    }
    
    public void setAuthCode(final String authCode) {
        this.authCode = authCode;
    }
    
    public void setResponseCode(final String responseCode) {
        this.responseCode = responseCode;
    }
    
    public void setMotoEcomIndicator(final String motoEcomIndicator) {
        this.motoEcomIndicator = motoEcomIndicator;
    }
    
    public void setFwdinstCode(final String fwdinstCode) {
        this.fwdinstCode = fwdinstCode;
    }
    
    public void setMvv(final String mvv) {
        this.mvv = mvv;
    }
    
    public void setQualifierIndicator(final Character qualifierIndicator) {
        this.qualifierIndicator = qualifierIndicator;
    }
    
    public void setIssuerRegion(final Character issuerRegion) {
        this.issuerRegion = issuerRegion;
    }
    
    public void setCountryCode(final String countryCode) {
        this.countryCode = countryCode;
    }
    
    public void setCardProduct(final String cardProduct) {
        this.cardProduct = cardProduct;
    }
    
    public void setCrDrIndicator(final Character crDrIndicator) {
        this.crDrIndicator = crDrIndicator;
    }
    
    public void setCardDomIntlFlag(final Character cardDomIntlFlag) {
        this.cardDomIntlFlag = cardDomIntlFlag;
    }
    
    public void setProgRegion(final Character progRegion) {
        this.progRegion = progRegion;
    }
    
    public void setIrdCode(final String irdCode) {
        this.irdCode = irdCode;
    }
    
    public void setTxnLimitIndicator(final Character txnLimitIndicator) {
        this.txnLimitIndicator = txnLimitIndicator;
    }
    
    public void setCrDrIndicatorActual(final Character crDrIndicatorActual) {
        this.crDrIndicatorActual = crDrIndicatorActual;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof IRFRequestVo)) {
            return false;
        }
        final IRFRequestVo other = (IRFRequestVo)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$insCode = this.getInsCode();
        final Object other$insCode = other.getInsCode();
        Label_0065: {
            if (this$insCode == null) {
                if (other$insCode == null) {
                    break Label_0065;
                }
            }
            else if (this$insCode.equals(other$insCode)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$txnAmount = this.getTxnAmount();
        final Object other$txnAmount = other.getTxnAmount();
        Label_0102: {
            if (this$txnAmount == null) {
                if (other$txnAmount == null) {
                    break Label_0102;
                }
            }
            else if (this$txnAmount.equals(other$txnAmount)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$reimbAttribute = this.getReimbAttribute();
        final Object other$reimbAttribute = other.getReimbAttribute();
        Label_0139: {
            if (this$reimbAttribute == null) {
                if (other$reimbAttribute == null) {
                    break Label_0139;
                }
            }
            else if (this$reimbAttribute.equals(other$reimbAttribute)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$qualifierIndicator = this.getQualifierIndicator();
        final Object other$qualifierIndicator = other.getQualifierIndicator();
        Label_0176: {
            if (this$qualifierIndicator == null) {
                if (other$qualifierIndicator == null) {
                    break Label_0176;
                }
            }
            else if (this$qualifierIndicator.equals(other$qualifierIndicator)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$issuerRegion = this.getIssuerRegion();
        final Object other$issuerRegion = other.getIssuerRegion();
        Label_0213: {
            if (this$issuerRegion == null) {
                if (other$issuerRegion == null) {
                    break Label_0213;
                }
            }
            else if (this$issuerRegion.equals(other$issuerRegion)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$crDrIndicator = this.getCrDrIndicator();
        final Object other$crDrIndicator = other.getCrDrIndicator();
        Label_0250: {
            if (this$crDrIndicator == null) {
                if (other$crDrIndicator == null) {
                    break Label_0250;
                }
            }
            else if (this$crDrIndicator.equals(other$crDrIndicator)) {
                break Label_0250;
            }
            return false;
        }
        final Object this$cardDomIntlFlag = this.getCardDomIntlFlag();
        final Object other$cardDomIntlFlag = other.getCardDomIntlFlag();
        Label_0287: {
            if (this$cardDomIntlFlag == null) {
                if (other$cardDomIntlFlag == null) {
                    break Label_0287;
                }
            }
            else if (this$cardDomIntlFlag.equals(other$cardDomIntlFlag)) {
                break Label_0287;
            }
            return false;
        }
        final Object this$progRegion = this.getProgRegion();
        final Object other$progRegion = other.getProgRegion();
        Label_0324: {
            if (this$progRegion == null) {
                if (other$progRegion == null) {
                    break Label_0324;
                }
            }
            else if (this$progRegion.equals(other$progRegion)) {
                break Label_0324;
            }
            return false;
        }
        final Object this$txnLimitIndicator = this.getTxnLimitIndicator();
        final Object other$txnLimitIndicator = other.getTxnLimitIndicator();
        Label_0361: {
            if (this$txnLimitIndicator == null) {
                if (other$txnLimitIndicator == null) {
                    break Label_0361;
                }
            }
            else if (this$txnLimitIndicator.equals(other$txnLimitIndicator)) {
                break Label_0361;
            }
            return false;
        }
        final Object this$crDrIndicatorActual = this.getCrDrIndicatorActual();
        final Object other$crDrIndicatorActual = other.getCrDrIndicatorActual();
        Label_0398: {
            if (this$crDrIndicatorActual == null) {
                if (other$crDrIndicatorActual == null) {
                    break Label_0398;
                }
            }
            else if (this$crDrIndicatorActual.equals(other$crDrIndicatorActual)) {
                break Label_0398;
            }
            return false;
        }
        final Object this$pan = this.getPan();
        final Object other$pan = other.getPan();
        Label_0435: {
            if (this$pan == null) {
                if (other$pan == null) {
                    break Label_0435;
                }
            }
            else if (this$pan.equals(other$pan)) {
                break Label_0435;
            }
            return false;
        }
        final Object this$txnDateTime = this.getTxnDateTime();
        final Object other$txnDateTime = other.getTxnDateTime();
        Label_0472: {
            if (this$txnDateTime == null) {
                if (other$txnDateTime == null) {
                    break Label_0472;
                }
            }
            else if (this$txnDateTime.equals(other$txnDateTime)) {
                break Label_0472;
            }
            return false;
        }
        final Object this$terminalType = this.getTerminalType();
        final Object other$terminalType = other.getTerminalType();
        Label_0509: {
            if (this$terminalType == null) {
                if (other$terminalType == null) {
                    break Label_0509;
                }
            }
            else if (this$terminalType.equals(other$terminalType)) {
                break Label_0509;
            }
            return false;
        }
        final Object this$mcc = this.getMcc();
        final Object other$mcc = other.getMcc();
        Label_0546: {
            if (this$mcc == null) {
                if (other$mcc == null) {
                    break Label_0546;
                }
            }
            else if (this$mcc.equals(other$mcc)) {
                break Label_0546;
            }
            return false;
        }
        final Object this$feePgmIndicator = this.getFeePgmIndicator();
        final Object other$feePgmIndicator = other.getFeePgmIndicator();
        Label_0583: {
            if (this$feePgmIndicator == null) {
                if (other$feePgmIndicator == null) {
                    break Label_0583;
                }
            }
            else if (this$feePgmIndicator.equals(other$feePgmIndicator)) {
                break Label_0583;
            }
            return false;
        }
        final Object this$posEntryMode = this.getPosEntryMode();
        final Object other$posEntryMode = other.getPosEntryMode();
        Label_0620: {
            if (this$posEntryMode == null) {
                if (other$posEntryMode == null) {
                    break Label_0620;
                }
            }
            else if (this$posEntryMode.equals(other$posEntryMode)) {
                break Label_0620;
            }
            return false;
        }
        final Object this$terminalCapability = this.getTerminalCapability();
        final Object other$terminalCapability = other.getTerminalCapability();
        Label_0657: {
            if (this$terminalCapability == null) {
                if (other$terminalCapability == null) {
                    break Label_0657;
                }
            }
            else if (this$terminalCapability.equals(other$terminalCapability)) {
                break Label_0657;
            }
            return false;
        }
        final Object this$authCode = this.getAuthCode();
        final Object other$authCode = other.getAuthCode();
        Label_0694: {
            if (this$authCode == null) {
                if (other$authCode == null) {
                    break Label_0694;
                }
            }
            else if (this$authCode.equals(other$authCode)) {
                break Label_0694;
            }
            return false;
        }
        final Object this$responseCode = this.getResponseCode();
        final Object other$responseCode = other.getResponseCode();
        Label_0731: {
            if (this$responseCode == null) {
                if (other$responseCode == null) {
                    break Label_0731;
                }
            }
            else if (this$responseCode.equals(other$responseCode)) {
                break Label_0731;
            }
            return false;
        }
        final Object this$motoEcomIndicator = this.getMotoEcomIndicator();
        final Object other$motoEcomIndicator = other.getMotoEcomIndicator();
        Label_0768: {
            if (this$motoEcomIndicator == null) {
                if (other$motoEcomIndicator == null) {
                    break Label_0768;
                }
            }
            else if (this$motoEcomIndicator.equals(other$motoEcomIndicator)) {
                break Label_0768;
            }
            return false;
        }
        final Object this$fwdinstCode = this.getFwdinstCode();
        final Object other$fwdinstCode = other.getFwdinstCode();
        Label_0805: {
            if (this$fwdinstCode == null) {
                if (other$fwdinstCode == null) {
                    break Label_0805;
                }
            }
            else if (this$fwdinstCode.equals(other$fwdinstCode)) {
                break Label_0805;
            }
            return false;
        }
        final Object this$mvv = this.getMvv();
        final Object other$mvv = other.getMvv();
        Label_0842: {
            if (this$mvv == null) {
                if (other$mvv == null) {
                    break Label_0842;
                }
            }
            else if (this$mvv.equals(other$mvv)) {
                break Label_0842;
            }
            return false;
        }
        final Object this$countryCode = this.getCountryCode();
        final Object other$countryCode = other.getCountryCode();
        Label_0879: {
            if (this$countryCode == null) {
                if (other$countryCode == null) {
                    break Label_0879;
                }
            }
            else if (this$countryCode.equals(other$countryCode)) {
                break Label_0879;
            }
            return false;
        }
        final Object this$cardProduct = this.getCardProduct();
        final Object other$cardProduct = other.getCardProduct();
        Label_0916: {
            if (this$cardProduct == null) {
                if (other$cardProduct == null) {
                    break Label_0916;
                }
            }
            else if (this$cardProduct.equals(other$cardProduct)) {
                break Label_0916;
            }
            return false;
        }
        final Object this$irdCode = this.getIrdCode();
        final Object other$irdCode = other.getIrdCode();
        if (this$irdCode == null) {
            if (other$irdCode == null) {
                return true;
            }
        }
        else if (this$irdCode.equals(other$irdCode)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof IRFRequestVo;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $insCode = this.getInsCode();
        result = result * 59 + (($insCode == null) ? 43 : $insCode.hashCode());
        final Object $txnAmount = this.getTxnAmount();
        result = result * 59 + (($txnAmount == null) ? 43 : $txnAmount.hashCode());
        final Object $reimbAttribute = this.getReimbAttribute();
        result = result * 59 + (($reimbAttribute == null) ? 43 : $reimbAttribute.hashCode());
        final Object $qualifierIndicator = this.getQualifierIndicator();
        result = result * 59 + (($qualifierIndicator == null) ? 43 : $qualifierIndicator.hashCode());
        final Object $issuerRegion = this.getIssuerRegion();
        result = result * 59 + (($issuerRegion == null) ? 43 : $issuerRegion.hashCode());
        final Object $crDrIndicator = this.getCrDrIndicator();
        result = result * 59 + (($crDrIndicator == null) ? 43 : $crDrIndicator.hashCode());
        final Object $cardDomIntlFlag = this.getCardDomIntlFlag();
        result = result * 59 + (($cardDomIntlFlag == null) ? 43 : $cardDomIntlFlag.hashCode());
        final Object $progRegion = this.getProgRegion();
        result = result * 59 + (($progRegion == null) ? 43 : $progRegion.hashCode());
        final Object $txnLimitIndicator = this.getTxnLimitIndicator();
        result = result * 59 + (($txnLimitIndicator == null) ? 43 : $txnLimitIndicator.hashCode());
        final Object $crDrIndicatorActual = this.getCrDrIndicatorActual();
        result = result * 59 + (($crDrIndicatorActual == null) ? 43 : $crDrIndicatorActual.hashCode());
        final Object $pan = this.getPan();
        result = result * 59 + (($pan == null) ? 43 : $pan.hashCode());
        final Object $txnDateTime = this.getTxnDateTime();
        result = result * 59 + (($txnDateTime == null) ? 43 : $txnDateTime.hashCode());
        final Object $terminalType = this.getTerminalType();
        result = result * 59 + (($terminalType == null) ? 43 : $terminalType.hashCode());
        final Object $mcc = this.getMcc();
        result = result * 59 + (($mcc == null) ? 43 : $mcc.hashCode());
        final Object $feePgmIndicator = this.getFeePgmIndicator();
        result = result * 59 + (($feePgmIndicator == null) ? 43 : $feePgmIndicator.hashCode());
        final Object $posEntryMode = this.getPosEntryMode();
        result = result * 59 + (($posEntryMode == null) ? 43 : $posEntryMode.hashCode());
        final Object $terminalCapability = this.getTerminalCapability();
        result = result * 59 + (($terminalCapability == null) ? 43 : $terminalCapability.hashCode());
        final Object $authCode = this.getAuthCode();
        result = result * 59 + (($authCode == null) ? 43 : $authCode.hashCode());
        final Object $responseCode = this.getResponseCode();
        result = result * 59 + (($responseCode == null) ? 43 : $responseCode.hashCode());
        final Object $motoEcomIndicator = this.getMotoEcomIndicator();
        result = result * 59 + (($motoEcomIndicator == null) ? 43 : $motoEcomIndicator.hashCode());
        final Object $fwdinstCode = this.getFwdinstCode();
        result = result * 59 + (($fwdinstCode == null) ? 43 : $fwdinstCode.hashCode());
        final Object $mvv = this.getMvv();
        result = result * 59 + (($mvv == null) ? 43 : $mvv.hashCode());
        final Object $countryCode = this.getCountryCode();
        result = result * 59 + (($countryCode == null) ? 43 : $countryCode.hashCode());
        final Object $cardProduct = this.getCardProduct();
        result = result * 59 + (($cardProduct == null) ? 43 : $cardProduct.hashCode());
        final Object $irdCode = this.getIrdCode();
        result = result * 59 + (($irdCode == null) ? 43 : $irdCode.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "IRFRequestVo(insCode=" + this.getInsCode() + ", pan=" + this.getPan() + ", txnDateTime=" + String.valueOf(this.getTxnDateTime()) + ", txnAmount=" + this.getTxnAmount() + ", terminalType=" + this.getTerminalType() + ", mcc=" + this.getMcc() + ", feePgmIndicator=" + this.getFeePgmIndicator() + ", reimbAttribute=" + this.getReimbAttribute() + ", posEntryMode=" + this.getPosEntryMode() + ", terminalCapability=" + this.getTerminalCapability() + ", authCode=" + this.getAuthCode() + ", responseCode=" + this.getResponseCode() + ", motoEcomIndicator=" + this.getMotoEcomIndicator() + ", fwdinstCode=" + this.getFwdinstCode() + ", mvv=" + this.getMvv() + ", qualifierIndicator=" + this.getQualifierIndicator() + ", issuerRegion=" + this.getIssuerRegion() + ", countryCode=" + this.getCountryCode() + ", cardProduct=" + this.getCardProduct() + ", crDrIndicator=" + this.getCrDrIndicator() + ", cardDomIntlFlag=" + this.getCardDomIntlFlag() + ", progRegion=" + this.getProgRegion() + ", irdCode=" + this.getIrdCode() + ", txnLimitIndicator=" + this.getTxnLimitIndicator() + ", crDrIndicatorActual=" + this.getCrDrIndicatorActual();
    }
}

