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

public class RtsResponseVo
{
    @NotNull(message = "RRN Must not Be NULL")
    @NotBlank(message = "The RRN cannot be Blank")
    @Length(min = 12, max = 12, message = "Length must be 12")
    private String rrn;
    @NotNull(message = "The mti cannot be null")
    @NotBlank(message = "The mti cannot be blank")
    @Length(max = 4, message = "Max Length 4")
    @Pattern(regexp = "^(0110|0130|0210|0410|0430)$", message = "Invalid MTI value. Allowed values are: 0110, 0130, 0210, 0410, 0430.")
    private String mti;
    @JsonProperty("processing_code")
    @SerializedName("processing_code")
    @NotNull(message = "The processCode cannot Be null")
    @NotBlank(message = "The processCode cannot be blank")
    @Length(max = 6, message = "Max length 6")
    @Pattern(regexp = "^(000000|200000|010000|090000|710000|610000|620000|210000)$", message = "Invalid process code value.Allowed values are:000000,200000,010000,090000,710000,610000,620000,210000.")
    private String processCode;
    @JsonProperty("DE48")
    private RTSde48Vo de48_json;
    @JsonProperty("DE22")
    private String de22_json;
    @JsonProperty("DE24")
    private String de24_json;
    @JsonProperty("DE25")
    private String de25_json;
    @JsonProperty("DE31")
    private String de31_json;
    @JsonProperty("DE95")
    private String de95_json;
    private String status;
    
    public String getRrn() {
        return this.rrn;
    }
    
    public String getMti() {
        return this.mti;
    }
    
    public String getProcessCode() {
        return this.processCode;
    }
    
    public RTSde48Vo getDe48_json() {
        return this.de48_json;
    }
    
    public String getDe22_json() {
        return this.de22_json;
    }
    
    public String getDe24_json() {
        return this.de24_json;
    }
    
    public String getDe25_json() {
        return this.de25_json;
    }
    
    public String getDe31_json() {
        return this.de31_json;
    }
    
    public String getDe95_json() {
        return this.de95_json;
    }
    
    public String getStatus() {
        return this.status;
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
    
    @JsonProperty("DE48")
    public void setDe48_json(final RTSde48Vo de48_json) {
        this.de48_json = de48_json;
    }
    
    @JsonProperty("DE22")
    public void setDe22_json(final String de22_json) {
        this.de22_json = de22_json;
    }
    
    @JsonProperty("DE24")
    public void setDe24_json(final String de24_json) {
        this.de24_json = de24_json;
    }
    
    @JsonProperty("DE25")
    public void setDe25_json(final String de25_json) {
        this.de25_json = de25_json;
    }
    
    @JsonProperty("DE31")
    public void setDe31_json(final String de31_json) {
        this.de31_json = de31_json;
    }
    
    @JsonProperty("DE95")
    public void setDe95_json(final String de95_json) {
        this.de95_json = de95_json;
    }
    
    public void setStatus(final String status) {
        this.status = status;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof RtsResponseVo)) {
            return false;
        }
        final RtsResponseVo other = (RtsResponseVo)o;
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
        Label_0139: {
            if (this$processCode == null) {
                if (other$processCode == null) {
                    break Label_0139;
                }
            }
            else if (this$processCode.equals(other$processCode)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$de48_json = this.getDe48_json();
        final Object other$de48_json = other.getDe48_json();
        Label_0176: {
            if (this$de48_json == null) {
                if (other$de48_json == null) {
                    break Label_0176;
                }
            }
            else if (this$de48_json.equals(other$de48_json)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$de22_json = this.getDe22_json();
        final Object other$de22_json = other.getDe22_json();
        Label_0213: {
            if (this$de22_json == null) {
                if (other$de22_json == null) {
                    break Label_0213;
                }
            }
            else if (this$de22_json.equals(other$de22_json)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$de24_json = this.getDe24_json();
        final Object other$de24_json = other.getDe24_json();
        Label_0250: {
            if (this$de24_json == null) {
                if (other$de24_json == null) {
                    break Label_0250;
                }
            }
            else if (this$de24_json.equals(other$de24_json)) {
                break Label_0250;
            }
            return false;
        }
        final Object this$de25_json = this.getDe25_json();
        final Object other$de25_json = other.getDe25_json();
        Label_0287: {
            if (this$de25_json == null) {
                if (other$de25_json == null) {
                    break Label_0287;
                }
            }
            else if (this$de25_json.equals(other$de25_json)) {
                break Label_0287;
            }
            return false;
        }
        final Object this$de31_json = this.getDe31_json();
        final Object other$de31_json = other.getDe31_json();
        Label_0324: {
            if (this$de31_json == null) {
                if (other$de31_json == null) {
                    break Label_0324;
                }
            }
            else if (this$de31_json.equals(other$de31_json)) {
                break Label_0324;
            }
            return false;
        }
        final Object this$de95_json = this.getDe95_json();
        final Object other$de95_json = other.getDe95_json();
        Label_0361: {
            if (this$de95_json == null) {
                if (other$de95_json == null) {
                    break Label_0361;
                }
            }
            else if (this$de95_json.equals(other$de95_json)) {
                break Label_0361;
            }
            return false;
        }
        final Object this$status = this.getStatus();
        final Object other$status = other.getStatus();
        if (this$status == null) {
            if (other$status == null) {
                return true;
            }
        }
        else if (this$status.equals(other$status)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof RtsResponseVo;
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
        final Object $de48_json = this.getDe48_json();
        result = result * 59 + (($de48_json == null) ? 43 : $de48_json.hashCode());
        final Object $de22_json = this.getDe22_json();
        result = result * 59 + (($de22_json == null) ? 43 : $de22_json.hashCode());
        final Object $de24_json = this.getDe24_json();
        result = result * 59 + (($de24_json == null) ? 43 : $de24_json.hashCode());
        final Object $de25_json = this.getDe25_json();
        result = result * 59 + (($de25_json == null) ? 43 : $de25_json.hashCode());
        final Object $de31_json = this.getDe31_json();
        result = result * 59 + (($de31_json == null) ? 43 : $de31_json.hashCode());
        final Object $de95_json = this.getDe95_json();
        result = result * 59 + (($de95_json == null) ? 43 : $de95_json.hashCode());
        final Object $status = this.getStatus();
        result = result * 59 + (($status == null) ? 43 : $status.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "RtsResponseVo(rrn=" + this.getRrn() + ", mti=" + this.getMti() + ", processCode=" + this.getProcessCode() + ", de48_json=" + String.valueOf(this.getDe48_json()) + ", de22_json=" + this.getDe22_json() + ", de24_json=" + this.getDe24_json() + ", de25_json=" + this.getDe25_json() + ", de31_json=" + this.getDe31_json() + ", de95_json=" + this.getDe95_json() + ", status=" + this.getStatus();
    }
}
