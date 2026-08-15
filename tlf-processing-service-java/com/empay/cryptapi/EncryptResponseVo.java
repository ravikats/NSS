// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.cryptapi;

import java.util.Map;

public class EncryptResponseVo
{
    private String respCode;
    private String respMsg;
    private String logRefId;
    private Map<String, String> uuids;
    
    public String getRespCode() {
        return this.respCode;
    }
    
    public String getRespMsg() {
        return this.respMsg;
    }
    
    public String getLogRefId() {
        return this.logRefId;
    }
    
    public Map<String, String> getUuids() {
        return this.uuids;
    }
    
    public void setRespCode(final String respCode) {
        this.respCode = respCode;
    }
    
    public void setRespMsg(final String respMsg) {
        this.respMsg = respMsg;
    }
    
    public void setLogRefId(final String logRefId) {
        this.logRefId = logRefId;
    }
    
    public void setUuids(final Map<String, String> uuids) {
        this.uuids = uuids;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof EncryptResponseVo)) {
            return false;
        }
        final EncryptResponseVo other = (EncryptResponseVo)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$respCode = this.getRespCode();
        final Object other$respCode = other.getRespCode();
        Label_0065: {
            if (this$respCode == null) {
                if (other$respCode == null) {
                    break Label_0065;
                }
            }
            else if (this$respCode.equals(other$respCode)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$respMsg = this.getRespMsg();
        final Object other$respMsg = other.getRespMsg();
        Label_0102: {
            if (this$respMsg == null) {
                if (other$respMsg == null) {
                    break Label_0102;
                }
            }
            else if (this$respMsg.equals(other$respMsg)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$logRefId = this.getLogRefId();
        final Object other$logRefId = other.getLogRefId();
        Label_0139: {
            if (this$logRefId == null) {
                if (other$logRefId == null) {
                    break Label_0139;
                }
            }
            else if (this$logRefId.equals(other$logRefId)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$uuids = this.getUuids();
        final Object other$uuids = other.getUuids();
        if (this$uuids == null) {
            if (other$uuids == null) {
                return true;
            }
        }
        else if (this$uuids.equals(other$uuids)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof EncryptResponseVo;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $respCode = this.getRespCode();
        result = result * 59 + (($respCode == null) ? 43 : $respCode.hashCode());
        final Object $respMsg = this.getRespMsg();
        result = result * 59 + (($respMsg == null) ? 43 : $respMsg.hashCode());
        final Object $logRefId = this.getLogRefId();
        result = result * 59 + (($logRefId == null) ? 43 : $logRefId.hashCode());
        final Object $uuids = this.getUuids();
        result = result * 59 + (($uuids == null) ? 43 : $uuids.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "EncryptResponseVo(respCode=" + this.getRespCode() + ", respMsg=" + this.getRespMsg() + ", logRefId=" + this.getLogRefId() + ", uuids=" + String.valueOf(this.getUuids());
    }
}
