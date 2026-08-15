// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.tlfprocessing.vo;

import org.hibernate.validator.constraints.Length;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RTSde48Vo
{
    @JsonProperty("p0023")
    private String PDS23;
    @JsonProperty("p0025")
    private String PDS25;
    @JsonProperty("p0052")
    private String PDS52;
    @JsonProperty("p0148")
    @NotNull(message = "PDS148 cannot be NULL")
    @NotBlank(message = "The PDS148 cannot be Blank")
    @Length(max = 3, message = "Max Length is 3")
    private String PDS148;
    @JsonProperty("p0149")
    private String PDS149;
    @JsonProperty("SF4")
    @NotNull(message = "PDS158 cannot Be NULL")
    @NotBlank(message = "The PDS158 cannot be Blank")
    private String PDS158;
    @JsonProperty("p0165")
    @NotNull(message = "PDS165 Must not Be NULL")
    private String PDS165;
    @JsonProperty("p0176")
    private String PDS176;
    @JsonProperty("p0211")
    private String PDS211;
    @JsonProperty("p0213")
    private String PDS0213;
    
    public String getPDS23() {
        return this.PDS23;
    }
    
    public String getPDS25() {
        return this.PDS25;
    }
    
    public String getPDS52() {
        return this.PDS52;
    }
    
    public String getPDS148() {
        return this.PDS148;
    }
    
    public String getPDS149() {
        return this.PDS149;
    }
    
    public String getPDS158() {
        return this.PDS158;
    }
    
    public String getPDS165() {
        return this.PDS165;
    }
    
    public String getPDS176() {
        return this.PDS176;
    }
    
    public String getPDS211() {
        return this.PDS211;
    }
    
    public String getPDS0213() {
        return this.PDS0213;
    }
    
    @JsonProperty("p0023")
    public void setPDS23(final String PDS23) {
        this.PDS23 = PDS23;
    }
    
    @JsonProperty("p0025")
    public void setPDS25(final String PDS25) {
        this.PDS25 = PDS25;
    }
    
    @JsonProperty("p0052")
    public void setPDS52(final String PDS52) {
        this.PDS52 = PDS52;
    }
    
    @JsonProperty("p0148")
    public void setPDS148(final String PDS148) {
        this.PDS148 = PDS148;
    }
    
    @JsonProperty("p0149")
    public void setPDS149(final String PDS149) {
        this.PDS149 = PDS149;
    }
    
    @JsonProperty("SF4")
    public void setPDS158(final String PDS158) {
        this.PDS158 = PDS158;
    }
    
    @JsonProperty("p0165")
    public void setPDS165(final String PDS165) {
        this.PDS165 = PDS165;
    }
    
    @JsonProperty("p0176")
    public void setPDS176(final String PDS176) {
        this.PDS176 = PDS176;
    }
    
    @JsonProperty("p0211")
    public void setPDS211(final String PDS211) {
        this.PDS211 = PDS211;
    }
    
    @JsonProperty("p0213")
    public void setPDS0213(final String PDS0213) {
        this.PDS0213 = PDS0213;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof RTSde48Vo)) {
            return false;
        }
        final RTSde48Vo other = (RTSde48Vo)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$PDS23 = this.getPDS23();
        final Object other$PDS23 = other.getPDS23();
        Label_0065: {
            if (this$PDS23 == null) {
                if (other$PDS23 == null) {
                    break Label_0065;
                }
            }
            else if (this$PDS23.equals(other$PDS23)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$PDS24 = this.getPDS25();
        final Object other$PDS24 = other.getPDS25();
        Label_0102: {
            if (this$PDS24 == null) {
                if (other$PDS24 == null) {
                    break Label_0102;
                }
            }
            else if (this$PDS24.equals(other$PDS24)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$PDS25 = this.getPDS52();
        final Object other$PDS25 = other.getPDS52();
        Label_0139: {
            if (this$PDS25 == null) {
                if (other$PDS25 == null) {
                    break Label_0139;
                }
            }
            else if (this$PDS25.equals(other$PDS25)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$PDS26 = this.getPDS148();
        final Object other$PDS26 = other.getPDS148();
        Label_0176: {
            if (this$PDS26 == null) {
                if (other$PDS26 == null) {
                    break Label_0176;
                }
            }
            else if (this$PDS26.equals(other$PDS26)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$PDS27 = this.getPDS149();
        final Object other$PDS27 = other.getPDS149();
        Label_0213: {
            if (this$PDS27 == null) {
                if (other$PDS27 == null) {
                    break Label_0213;
                }
            }
            else if (this$PDS27.equals(other$PDS27)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$PDS28 = this.getPDS158();
        final Object other$PDS28 = other.getPDS158();
        Label_0250: {
            if (this$PDS28 == null) {
                if (other$PDS28 == null) {
                    break Label_0250;
                }
            }
            else if (this$PDS28.equals(other$PDS28)) {
                break Label_0250;
            }
            return false;
        }
        final Object this$PDS29 = this.getPDS165();
        final Object other$PDS29 = other.getPDS165();
        Label_0287: {
            if (this$PDS29 == null) {
                if (other$PDS29 == null) {
                    break Label_0287;
                }
            }
            else if (this$PDS29.equals(other$PDS29)) {
                break Label_0287;
            }
            return false;
        }
        final Object this$PDS30 = this.getPDS176();
        final Object other$PDS30 = other.getPDS176();
        Label_0324: {
            if (this$PDS30 == null) {
                if (other$PDS30 == null) {
                    break Label_0324;
                }
            }
            else if (this$PDS30.equals(other$PDS30)) {
                break Label_0324;
            }
            return false;
        }
        final Object this$PDS31 = this.getPDS211();
        final Object other$PDS31 = other.getPDS211();
        Label_0361: {
            if (this$PDS31 == null) {
                if (other$PDS31 == null) {
                    break Label_0361;
                }
            }
            else if (this$PDS31.equals(other$PDS31)) {
                break Label_0361;
            }
            return false;
        }
        final Object this$PDS32 = this.getPDS0213();
        final Object other$PDS32 = other.getPDS0213();
        if (this$PDS32 == null) {
            if (other$PDS32 == null) {
                return true;
            }
        }
        else if (this$PDS32.equals(other$PDS32)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof RTSde48Vo;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $PDS23 = this.getPDS23();
        result = result * 59 + (($PDS23 == null) ? 43 : $PDS23.hashCode());
        final Object $PDS24 = this.getPDS25();
        result = result * 59 + (($PDS24 == null) ? 43 : $PDS24.hashCode());
        final Object $PDS25 = this.getPDS52();
        result = result * 59 + (($PDS25 == null) ? 43 : $PDS25.hashCode());
        final Object $PDS26 = this.getPDS148();
        result = result * 59 + (($PDS26 == null) ? 43 : $PDS26.hashCode());
        final Object $PDS27 = this.getPDS149();
        result = result * 59 + (($PDS27 == null) ? 43 : $PDS27.hashCode());
        final Object $PDS28 = this.getPDS158();
        result = result * 59 + (($PDS28 == null) ? 43 : $PDS28.hashCode());
        final Object $PDS29 = this.getPDS165();
        result = result * 59 + (($PDS29 == null) ? 43 : $PDS29.hashCode());
        final Object $PDS30 = this.getPDS176();
        result = result * 59 + (($PDS30 == null) ? 43 : $PDS30.hashCode());
        final Object $PDS31 = this.getPDS211();
        result = result * 59 + (($PDS31 == null) ? 43 : $PDS31.hashCode());
        final Object $PDS32 = this.getPDS0213();
        result = result * 59 + (($PDS32 == null) ? 43 : $PDS32.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "RTSde48Vo(PDS23=" + this.getPDS23() + ", PDS25=" + this.getPDS25() + ", PDS52=" + this.getPDS52() + ", PDS148=" + this.getPDS148() + ", PDS149=" + this.getPDS149() + ", PDS158=" + this.getPDS158() + ", PDS165=" + this.getPDS165() + ", PDS176=" + this.getPDS176() + ", PDS211=" + this.getPDS211() + ", PDS0213=" + this.getPDS0213();
    }
}
