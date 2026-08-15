// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.tlfprocessing.vo;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseVo
{
    @JsonProperty("ref_id")
    private String uniqueId;
    @JsonProperty("amount")
    private String amountTransaction;
    @JsonProperty("rrn")
    private String rrn;
    @JsonProperty("terminal_id")
    private String cardAcceptorTid;
    @JsonProperty("merchant_id")
    private String cardAcceptorId;
    private String responseMessage;
    private String responseCode;
    @JsonProperty("mti")
    private String mti;
    private List<String> validationErrors;
    
    public String getUniqueId() {
        return this.uniqueId;
    }
    
    public String getAmountTransaction() {
        return this.amountTransaction;
    }
    
    public String getRrn() {
        return this.rrn;
    }
    
    public String getCardAcceptorTid() {
        return this.cardAcceptorTid;
    }
    
    public String getCardAcceptorId() {
        return this.cardAcceptorId;
    }
    
    public String getResponseMessage() {
        return this.responseMessage;
    }
    
    public String getResponseCode() {
        return this.responseCode;
    }
    
    public String getMti() {
        return this.mti;
    }
    
    public List<String> getValidationErrors() {
        return this.validationErrors;
    }
    
    @JsonProperty("ref_id")
    public void setUniqueId(final String uniqueId) {
        this.uniqueId = uniqueId;
    }
    
    @JsonProperty("amount")
    public void setAmountTransaction(final String amountTransaction) {
        this.amountTransaction = amountTransaction;
    }
    
    @JsonProperty("rrn")
    public void setRrn(final String rrn) {
        this.rrn = rrn;
    }
    
    @JsonProperty("terminal_id")
    public void setCardAcceptorTid(final String cardAcceptorTid) {
        this.cardAcceptorTid = cardAcceptorTid;
    }
    
    @JsonProperty("merchant_id")
    public void setCardAcceptorId(final String cardAcceptorId) {
        this.cardAcceptorId = cardAcceptorId;
    }
    
    public void setResponseMessage(final String responseMessage) {
        this.responseMessage = responseMessage;
    }
    
    public void setResponseCode(final String responseCode) {
        this.responseCode = responseCode;
    }
    
    @JsonProperty("mti")
    public void setMti(final String mti) {
        this.mti = mti;
    }
    
    public void setValidationErrors(final List<String> validationErrors) {
        this.validationErrors = validationErrors;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ResponseVo)) {
            return false;
        }
        final ResponseVo other = (ResponseVo)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$uniqueId = this.getUniqueId();
        final Object other$uniqueId = other.getUniqueId();
        Label_0065: {
            if (this$uniqueId == null) {
                if (other$uniqueId == null) {
                    break Label_0065;
                }
            }
            else if (this$uniqueId.equals(other$uniqueId)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$amountTransaction = this.getAmountTransaction();
        final Object other$amountTransaction = other.getAmountTransaction();
        Label_0102: {
            if (this$amountTransaction == null) {
                if (other$amountTransaction == null) {
                    break Label_0102;
                }
            }
            else if (this$amountTransaction.equals(other$amountTransaction)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$rrn = this.getRrn();
        final Object other$rrn = other.getRrn();
        Label_0139: {
            if (this$rrn == null) {
                if (other$rrn == null) {
                    break Label_0139;
                }
            }
            else if (this$rrn.equals(other$rrn)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$cardAcceptorTid = this.getCardAcceptorTid();
        final Object other$cardAcceptorTid = other.getCardAcceptorTid();
        Label_0176: {
            if (this$cardAcceptorTid == null) {
                if (other$cardAcceptorTid == null) {
                    break Label_0176;
                }
            }
            else if (this$cardAcceptorTid.equals(other$cardAcceptorTid)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$cardAcceptorId = this.getCardAcceptorId();
        final Object other$cardAcceptorId = other.getCardAcceptorId();
        Label_0213: {
            if (this$cardAcceptorId == null) {
                if (other$cardAcceptorId == null) {
                    break Label_0213;
                }
            }
            else if (this$cardAcceptorId.equals(other$cardAcceptorId)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$responseMessage = this.getResponseMessage();
        final Object other$responseMessage = other.getResponseMessage();
        Label_0250: {
            if (this$responseMessage == null) {
                if (other$responseMessage == null) {
                    break Label_0250;
                }
            }
            else if (this$responseMessage.equals(other$responseMessage)) {
                break Label_0250;
            }
            return false;
        }
        final Object this$responseCode = this.getResponseCode();
        final Object other$responseCode = other.getResponseCode();
        Label_0287: {
            if (this$responseCode == null) {
                if (other$responseCode == null) {
                    break Label_0287;
                }
            }
            else if (this$responseCode.equals(other$responseCode)) {
                break Label_0287;
            }
            return false;
        }
        final Object this$mti = this.getMti();
        final Object other$mti = other.getMti();
        Label_0324: {
            if (this$mti == null) {
                if (other$mti == null) {
                    break Label_0324;
                }
            }
            else if (this$mti.equals(other$mti)) {
                break Label_0324;
            }
            return false;
        }
        final Object this$validationErrors = this.getValidationErrors();
        final Object other$validationErrors = other.getValidationErrors();
        if (this$validationErrors == null) {
            if (other$validationErrors == null) {
                return true;
            }
        }
        else if (this$validationErrors.equals(other$validationErrors)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof ResponseVo;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $uniqueId = this.getUniqueId();
        result = result * 59 + (($uniqueId == null) ? 43 : $uniqueId.hashCode());
        final Object $amountTransaction = this.getAmountTransaction();
        result = result * 59 + (($amountTransaction == null) ? 43 : $amountTransaction.hashCode());
        final Object $rrn = this.getRrn();
        result = result * 59 + (($rrn == null) ? 43 : $rrn.hashCode());
        final Object $cardAcceptorTid = this.getCardAcceptorTid();
        result = result * 59 + (($cardAcceptorTid == null) ? 43 : $cardAcceptorTid.hashCode());
        final Object $cardAcceptorId = this.getCardAcceptorId();
        result = result * 59 + (($cardAcceptorId == null) ? 43 : $cardAcceptorId.hashCode());
        final Object $responseMessage = this.getResponseMessage();
        result = result * 59 + (($responseMessage == null) ? 43 : $responseMessage.hashCode());
        final Object $responseCode = this.getResponseCode();
        result = result * 59 + (($responseCode == null) ? 43 : $responseCode.hashCode());
        final Object $mti = this.getMti();
        result = result * 59 + (($mti == null) ? 43 : $mti.hashCode());
        final Object $validationErrors = this.getValidationErrors();
        result = result * 59 + (($validationErrors == null) ? 43 : $validationErrors.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "ResponseVo(uniqueId=" + this.getUniqueId() + ", amountTransaction=" + this.getAmountTransaction() + ", rrn=" + this.getRrn() + ", cardAcceptorTid=" + this.getCardAcceptorTid() + ", cardAcceptorId=" + this.getCardAcceptorId() + ", responseMessage=" + this.getResponseMessage() + ", responseCode=" + this.getResponseCode() + ", mti=" + this.getMti() + ", validationErrors=" + String.valueOf(this.getValidationErrors());
    }
}
