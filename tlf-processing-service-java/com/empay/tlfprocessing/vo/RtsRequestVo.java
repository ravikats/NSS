// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.tlfprocessing.vo;

import com.google.gson.annotations.SerializedName;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RtsRequestVo
{
    @NotNull(message = "RRN Must not Be NULL")
    @NotBlank(message = "The RRN cannot be Blank")
    @Length(min = 12, max = 12, message = "Length must be 12")
    private String rrn;
    @NotNull(message = "The mti cannot be null")
    @NotBlank(message = "The mti cannot be blank")
    @Length(max = 4, message = "Max Length 4")
    @Pattern(regexp = "^(0110|0130|0210|0410|0430|1240)$", message = "Invalid MTI value. Allowed values are: 0110, 0130, 0210, 0410, 0430.")
    private String mti;
    @JsonProperty("processing_code")
    @SerializedName("processing_code")
    @NotNull(message = "The processCode cannot Be null")
    @NotBlank(message = "The processCode cannot be blank")
    @Length(max = 6, message = "Max length 6")
    @Pattern(regexp = "^(000000|200000|010000|090000|710000|610000|620000|210000)$", message = "Invalid process code value.Allowed values are:000000,200000,010000,090000,710000,610000,620000,210000.")
    private String processCode;
    
    public String getRrn() {
        return this.rrn;
    }
    
    public String getMti() {
        return this.mti;
    }
    
    public String getProcessCode() {
        return this.processCode;
    }
    
    public void setRrn(final String rrn) {
        this.rrn = rrn;
    }
    
    public void setMti(final String mti) {
        this.mti = mti;
    }
    
    @JsonProperty("processing_code")
    public void setProcessCode(final String processCode) {
        this.processCode = processCode;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof RtsRequestVo)) {
            return false;
        }
        final RtsRequestVo other = (RtsRequestVo)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$rrn = this.getRrn();
        final Object other$rrn = other.getRrn();
        Label_0065: {
            if (this$rrn == null) {
                if (other$rrn == null) {
                    break Label_0065;
                }
            }
            else if (this$rrn.equals(other$rrn)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$mti = this.getMti();
        final Object other$mti = other.getMti();
        Label_0102: {
            if (this$mti == null) {
                if (other$mti == null) {
                    break Label_0102;
                }
            }
            else if (this$mti.equals(other$mti)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$processCode = this.getProcessCode();
        final Object other$processCode = other.getProcessCode();
        if (this$processCode == null) {
            if (other$processCode == null) {
                return true;
            }
        }
        else if (this$processCode.equals(other$processCode)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof RtsRequestVo;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $rrn = this.getRrn();
        result = result * 59 + (($rrn == null) ? 43 : $rrn.hashCode());
        final Object $mti = this.getMti();
        result = result * 59 + (($mti == null) ? 43 : $mti.hashCode());
        final Object $processCode = this.getProcessCode();
        result = result * 59 + (($processCode == null) ? 43 : $processCode.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "RtsRequestVo(rrn=" + this.getRrn() + ", mti=" + this.getMti() + ", processCode=" + this.getProcessCode();
    }
}
