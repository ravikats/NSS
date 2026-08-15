// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.tlfprocessing.vo;

public class de60_jsonVo
{
    private String terminalCapability;
    private String ecomIndicator;
    private Character chIdMethod;
    private Character acceptanceTrlIndicator;
    
    public String getTerminalCapability() {
        return this.terminalCapability;
    }
    
    public String getEcomIndicator() {
        return this.ecomIndicator;
    }
    
    public Character getChIdMethod() {
        return this.chIdMethod;
    }
    
    public Character getAcceptanceTrlIndicator() {
        return this.acceptanceTrlIndicator;
    }
    
    public void setTerminalCapability(final String terminalCapability) {
        this.terminalCapability = terminalCapability;
    }
    
    public void setEcomIndicator(final String ecomIndicator) {
        this.ecomIndicator = ecomIndicator;
    }
    
    public void setChIdMethod(final Character chIdMethod) {
        this.chIdMethod = chIdMethod;
    }
    
    public void setAcceptanceTrlIndicator(final Character acceptanceTrlIndicator) {
        this.acceptanceTrlIndicator = acceptanceTrlIndicator;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof de60_jsonVo)) {
            return false;
        }
        final de60_jsonVo other = (de60_jsonVo)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$chIdMethod = this.getChIdMethod();
        final Object other$chIdMethod = other.getChIdMethod();
        Label_0065: {
            if (this$chIdMethod == null) {
                if (other$chIdMethod == null) {
                    break Label_0065;
                }
            }
            else if (this$chIdMethod.equals(other$chIdMethod)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$acceptanceTrlIndicator = this.getAcceptanceTrlIndicator();
        final Object other$acceptanceTrlIndicator = other.getAcceptanceTrlIndicator();
        Label_0102: {
            if (this$acceptanceTrlIndicator == null) {
                if (other$acceptanceTrlIndicator == null) {
                    break Label_0102;
                }
            }
            else if (this$acceptanceTrlIndicator.equals(other$acceptanceTrlIndicator)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$terminalCapability = this.getTerminalCapability();
        final Object other$terminalCapability = other.getTerminalCapability();
        Label_0139: {
            if (this$terminalCapability == null) {
                if (other$terminalCapability == null) {
                    break Label_0139;
                }
            }
            else if (this$terminalCapability.equals(other$terminalCapability)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$ecomIndicator = this.getEcomIndicator();
        final Object other$ecomIndicator = other.getEcomIndicator();
        if (this$ecomIndicator == null) {
            if (other$ecomIndicator == null) {
                return true;
            }
        }
        else if (this$ecomIndicator.equals(other$ecomIndicator)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof de60_jsonVo;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $chIdMethod = this.getChIdMethod();
        result = result * 59 + (($chIdMethod == null) ? 43 : $chIdMethod.hashCode());
        final Object $acceptanceTrlIndicator = this.getAcceptanceTrlIndicator();
        result = result * 59 + (($acceptanceTrlIndicator == null) ? 43 : $acceptanceTrlIndicator.hashCode());
        final Object $terminalCapability = this.getTerminalCapability();
        result = result * 59 + (($terminalCapability == null) ? 43 : $terminalCapability.hashCode());
        final Object $ecomIndicator = this.getEcomIndicator();
        result = result * 59 + (($ecomIndicator == null) ? 43 : $ecomIndicator.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "de60_jsonVo(terminalCapability=" + this.getTerminalCapability() + ", ecomIndicator=" + this.getEcomIndicator() + ", chIdMethod=" + this.getChIdMethod() + ", acceptanceTrlIndicator=" + this.getAcceptanceTrlIndicator();
    }
}
