// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.tlfprocessing.vo;

public class de61Vo
{
    private String cardInputAbility;
    private String cardCaptureCapability;
    private Character operationalEnv1;
    private Character operationalEnv3;
    private Character chPresent;
    private Character cardPresent;
    
    public String getCardInputAbility() {
        return this.cardInputAbility;
    }
    
    public String getCardCaptureCapability() {
        return this.cardCaptureCapability;
    }
    
    public Character getOperationalEnv1() {
        return this.operationalEnv1;
    }
    
    public Character getOperationalEnv3() {
        return this.operationalEnv3;
    }
    
    public Character getChPresent() {
        return this.chPresent;
    }
    
    public Character getCardPresent() {
        return this.cardPresent;
    }
    
    public void setCardInputAbility(final String cardInputAbility) {
        this.cardInputAbility = cardInputAbility;
    }
    
    public void setCardCaptureCapability(final String cardCaptureCapability) {
        this.cardCaptureCapability = cardCaptureCapability;
    }
    
    public void setOperationalEnv1(final Character operationalEnv1) {
        this.operationalEnv1 = operationalEnv1;
    }
    
    public void setOperationalEnv3(final Character operationalEnv3) {
        this.operationalEnv3 = operationalEnv3;
    }
    
    public void setChPresent(final Character chPresent) {
        this.chPresent = chPresent;
    }
    
    public void setCardPresent(final Character cardPresent) {
        this.cardPresent = cardPresent;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof de61Vo)) {
            return false;
        }
        final de61Vo other = (de61Vo)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$operationalEnv1 = this.getOperationalEnv1();
        final Object other$operationalEnv1 = other.getOperationalEnv1();
        Label_0065: {
            if (this$operationalEnv1 == null) {
                if (other$operationalEnv1 == null) {
                    break Label_0065;
                }
            }
            else if (this$operationalEnv1.equals(other$operationalEnv1)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$operationalEnv2 = this.getOperationalEnv3();
        final Object other$operationalEnv2 = other.getOperationalEnv3();
        Label_0102: {
            if (this$operationalEnv2 == null) {
                if (other$operationalEnv2 == null) {
                    break Label_0102;
                }
            }
            else if (this$operationalEnv2.equals(other$operationalEnv2)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$chPresent = this.getChPresent();
        final Object other$chPresent = other.getChPresent();
        Label_0139: {
            if (this$chPresent == null) {
                if (other$chPresent == null) {
                    break Label_0139;
                }
            }
            else if (this$chPresent.equals(other$chPresent)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$cardPresent = this.getCardPresent();
        final Object other$cardPresent = other.getCardPresent();
        Label_0176: {
            if (this$cardPresent == null) {
                if (other$cardPresent == null) {
                    break Label_0176;
                }
            }
            else if (this$cardPresent.equals(other$cardPresent)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$cardInputAbility = this.getCardInputAbility();
        final Object other$cardInputAbility = other.getCardInputAbility();
        Label_0213: {
            if (this$cardInputAbility == null) {
                if (other$cardInputAbility == null) {
                    break Label_0213;
                }
            }
            else if (this$cardInputAbility.equals(other$cardInputAbility)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$cardCaptureCapability = this.getCardCaptureCapability();
        final Object other$cardCaptureCapability = other.getCardCaptureCapability();
        if (this$cardCaptureCapability == null) {
            if (other$cardCaptureCapability == null) {
                return true;
            }
        }
        else if (this$cardCaptureCapability.equals(other$cardCaptureCapability)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof de61Vo;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $operationalEnv1 = this.getOperationalEnv1();
        result = result * 59 + (($operationalEnv1 == null) ? 43 : $operationalEnv1.hashCode());
        final Object $operationalEnv2 = this.getOperationalEnv3();
        result = result * 59 + (($operationalEnv2 == null) ? 43 : $operationalEnv2.hashCode());
        final Object $chPresent = this.getChPresent();
        result = result * 59 + (($chPresent == null) ? 43 : $chPresent.hashCode());
        final Object $cardPresent = this.getCardPresent();
        result = result * 59 + (($cardPresent == null) ? 43 : $cardPresent.hashCode());
        final Object $cardInputAbility = this.getCardInputAbility();
        result = result * 59 + (($cardInputAbility == null) ? 43 : $cardInputAbility.hashCode());
        final Object $cardCaptureCapability = this.getCardCaptureCapability();
        result = result * 59 + (($cardCaptureCapability == null) ? 43 : $cardCaptureCapability.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "de61Vo(cardInputAbility=" + this.getCardInputAbility() + ", cardCaptureCapability=" + this.getCardCaptureCapability() + ", operationalEnv1=" + this.getOperationalEnv1() + ", operationalEnv3=" + this.getOperationalEnv3() + ", chPresent=" + this.getChPresent() + ", cardPresent=" + this.getCardPresent();
    }
}
