// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.tlfprocessing.vo;

import jakarta.validation.Valid;

public class RequestVo
{
    @Valid
    private SwitchExtractVo payload;
    
    public SwitchExtractVo getPayload() {
        return this.payload;
    }
    
    public void setPayload(final SwitchExtractVo payload) {
        this.payload = payload;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof RequestVo)) {
            return false;
        }
        final RequestVo other = (RequestVo)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$payload = this.getPayload();
        final Object other$payload = other.getPayload();
        if (this$payload == null) {
            if (other$payload == null) {
                return true;
            }
        }
        else if (this$payload.equals(other$payload)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof RequestVo;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $payload = this.getPayload();
        result = result * 59 + (($payload == null) ? 43 : $payload.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "RequestVo(payload=" + String.valueOf(this.getPayload());
    }
}
