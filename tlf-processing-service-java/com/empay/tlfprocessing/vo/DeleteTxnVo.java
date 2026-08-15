// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.tlfprocessing.vo;

import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class DeleteTxnVo
{
    @NotNull(message = "RRN Must not Be NULL")
    @NotBlank(message = "The RRN cannot be Blank")
    @Length(max = 12, message = "Length Must be 12")
    private String retRefNumber;
    @NotNull(message = "The processing_code cannot Be null")
    @NotBlank(message = "The processing_code cannot be blank")
    @Length(max = 6, message = "Max length 6")
    private String processCode;
    @NotNull(message = "The switch_mti cannot be null")
    @NotBlank(message = "The switch_mti cannot be blank")
    @Length(max = 4, message = "Max Length 4")
    private String mti;
    @NotNull(message = "terminal_id cannot be null")
    @NotBlank(message = "The terminal_id cannot be blank")
    @Length(min = 8, max = 8, message = "Length Should be 8")
    private String cardAcceptorTid;
    @NotNull(message = "The amount cannot be null")
    @NotBlank(message = "The amount cannot be blank")
    @Pattern(regexp = "^\\d+$", message = "INVALID AMOUNT ; AMOUNT Should be Greater than Zero")
    private String amountTransaction;
    
    public String getRetRefNumber() {
        return this.retRefNumber;
    }
    
    public String getProcessCode() {
        return this.processCode;
    }
    
    public String getMti() {
        return this.mti;
    }
    
    public String getCardAcceptorTid() {
        return this.cardAcceptorTid;
    }
    
    public String getAmountTransaction() {
        return this.amountTransaction;
    }
    
    public void setRetRefNumber(final String retRefNumber) {
        this.retRefNumber = retRefNumber;
    }
    
    public void setProcessCode(final String processCode) {
        this.processCode = processCode;
    }
    
    public void setMti(final String mti) {
        this.mti = mti;
    }
    
    public void setCardAcceptorTid(final String cardAcceptorTid) {
        this.cardAcceptorTid = cardAcceptorTid;
    }
    
    public void setAmountTransaction(final String amountTransaction) {
        this.amountTransaction = amountTransaction;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof DeleteTxnVo)) {
            return false;
        }
        final DeleteTxnVo other = (DeleteTxnVo)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$retRefNumber = this.getRetRefNumber();
        final Object other$retRefNumber = other.getRetRefNumber();
        Label_0065: {
            if (this$retRefNumber == null) {
                if (other$retRefNumber == null) {
                    break Label_0065;
                }
            }
            else if (this$retRefNumber.equals(other$retRefNumber)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$processCode = this.getProcessCode();
        final Object other$processCode = other.getProcessCode();
        Label_0102: {
            if (this$processCode == null) {
                if (other$processCode == null) {
                    break Label_0102;
                }
            }
            else if (this$processCode.equals(other$processCode)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$mti = this.getMti();
        final Object other$mti = other.getMti();
        Label_0139: {
            if (this$mti == null) {
                if (other$mti == null) {
                    break Label_0139;
                }
            }
            else if (this$mti.equals(other$mti)) {
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
        final Object this$amountTransaction = this.getAmountTransaction();
        final Object other$amountTransaction = other.getAmountTransaction();
        if (this$amountTransaction == null) {
            if (other$amountTransaction == null) {
                return true;
            }
        }
        else if (this$amountTransaction.equals(other$amountTransaction)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof DeleteTxnVo;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $retRefNumber = this.getRetRefNumber();
        result = result * 59 + (($retRefNumber == null) ? 43 : $retRefNumber.hashCode());
        final Object $processCode = this.getProcessCode();
        result = result * 59 + (($processCode == null) ? 43 : $processCode.hashCode());
        final Object $mti = this.getMti();
        result = result * 59 + (($mti == null) ? 43 : $mti.hashCode());
        final Object $cardAcceptorTid = this.getCardAcceptorTid();
        result = result * 59 + (($cardAcceptorTid == null) ? 43 : $cardAcceptorTid.hashCode());
        final Object $amountTransaction = this.getAmountTransaction();
        result = result * 59 + (($amountTransaction == null) ? 43 : $amountTransaction.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "DeleteTxnVo(retRefNumber=" + this.getRetRefNumber() + ", processCode=" + this.getProcessCode() + ", mti=" + this.getMti() + ", cardAcceptorTid=" + this.getCardAcceptorTid() + ", amountTransaction=" + this.getAmountTransaction();
    }
}
