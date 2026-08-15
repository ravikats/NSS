// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.tlfprocessing.vo;

import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RefundVo
{
    @NotNull(message = "merchant_id cannot Be null")
    @NotBlank(message = "The merchant_id cannot be blank")
    @Length(min = 15, max = 15, message = "Length Should be 15")
    private String merchantId;
    @NotNull(message = "terminal_id cannot be null")
    @NotBlank(message = "The terminal_id cannot be blank")
    @Length(min = 8, max = 8, message = "Length Should be 8")
    private String terminalId;
    @NotNull(message = "RRN Must not Be NULL")
    @NotBlank(message = "The RRN cannot be Blank")
    @Length(min = 12, max = 12, message = "Length must be 12")
    private String rrn;
    @NotNull(message = "Auth code Must not Be NULL")
    @NotBlank(message = "Auth code cannot be Blank")
    @Length(max = 6, message = "Max Length 6")
    private String authCode;
    @NotNull(message = "The Refund amount cannot be null")
    @NotBlank(message = "The Refund amount cannot be blank")
    @Pattern(regexp = "^\\s*(?!0+(\\.0+)?$)\\d+(\\.\\d+)?\\s*$", message = "INVALID AMOUNT ; AMOUNT Should be Greater than Zero")
    private String refundAmount;
    
    public String getMerchantId() {
        return this.merchantId;
    }
    
    public String getTerminalId() {
        return this.terminalId;
    }
    
    public String getRrn() {
        return this.rrn;
    }
    
    public String getAuthCode() {
        return this.authCode;
    }
    
    public String getRefundAmount() {
        return this.refundAmount;
    }
    
    public void setMerchantId(final String merchantId) {
        this.merchantId = merchantId;
    }
    
    public void setTerminalId(final String terminalId) {
        this.terminalId = terminalId;
    }
    
    public void setRrn(final String rrn) {
        this.rrn = rrn;
    }
    
    public void setAuthCode(final String authCode) {
        this.authCode = authCode;
    }
    
    public void setRefundAmount(final String refundAmount) {
        this.refundAmount = refundAmount;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof RefundVo)) {
            return false;
        }
        final RefundVo other = (RefundVo)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$merchantId = this.getMerchantId();
        final Object other$merchantId = other.getMerchantId();
        Label_0065: {
            if (this$merchantId == null) {
                if (other$merchantId == null) {
                    break Label_0065;
                }
            }
            else if (this$merchantId.equals(other$merchantId)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$terminalId = this.getTerminalId();
        final Object other$terminalId = other.getTerminalId();
        Label_0102: {
            if (this$terminalId == null) {
                if (other$terminalId == null) {
                    break Label_0102;
                }
            }
            else if (this$terminalId.equals(other$terminalId)) {
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
        final Object this$authCode = this.getAuthCode();
        final Object other$authCode = other.getAuthCode();
        Label_0176: {
            if (this$authCode == null) {
                if (other$authCode == null) {
                    break Label_0176;
                }
            }
            else if (this$authCode.equals(other$authCode)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$refundAmount = this.getRefundAmount();
        final Object other$refundAmount = other.getRefundAmount();
        if (this$refundAmount == null) {
            if (other$refundAmount == null) {
                return true;
            }
        }
        else if (this$refundAmount.equals(other$refundAmount)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof RefundVo;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $merchantId = this.getMerchantId();
        result = result * 59 + (($merchantId == null) ? 43 : $merchantId.hashCode());
        final Object $terminalId = this.getTerminalId();
        result = result * 59 + (($terminalId == null) ? 43 : $terminalId.hashCode());
        final Object $rrn = this.getRrn();
        result = result * 59 + (($rrn == null) ? 43 : $rrn.hashCode());
        final Object $authCode = this.getAuthCode();
        result = result * 59 + (($authCode == null) ? 43 : $authCode.hashCode());
        final Object $refundAmount = this.getRefundAmount();
        result = result * 59 + (($refundAmount == null) ? 43 : $refundAmount.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "RefundVo(merchantId=" + this.getMerchantId() + ", terminalId=" + this.getTerminalId() + ", rrn=" + this.getRrn() + ", authCode=" + this.getAuthCode() + ", refundAmount=" + this.getRefundAmount();
    }
}
