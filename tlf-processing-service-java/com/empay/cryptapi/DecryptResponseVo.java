// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.cryptapi;

import java.util.Map;

public class DecryptResponseVo
{
    private String logRefId;
    private String respMsg;
    private String respCode;
    private Map<String, String> cardNumbers;
    
    public String getLogRefId() {
        return this.logRefId;
    }
    
    public String getRespMsg() {
        return this.respMsg;
    }
    
    public String getRespCode() {
        return this.respCode;
    }
    
    public Map<String, String> getCardNumbers() {
        return this.cardNumbers;
    }
    
    public void setLogRefId(final String logRefId) {
        this.logRefId = logRefId;
    }
    
    public void setRespMsg(final String respMsg) {
        this.respMsg = respMsg;
    }
    
    public void setRespCode(final String respCode) {
        this.respCode = respCode;
    }
    
    public void setCardNumbers(final Map<String, String> cardNumbers) {
        this.cardNumbers = cardNumbers;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof DecryptResponseVo)) {
            return false;
        }
        final DecryptResponseVo other = (DecryptResponseVo)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$logRefId = this.getLogRefId();
        final Object other$logRefId = other.getLogRefId();
        Label_0065: {
            if (this$logRefId == null) {
                if (other$logRefId == null) {
                    break Label_0065;
                }
            }
            else if (this$logRefId.equals(other$logRefId)) {
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
        final Object this$respCode = this.getRespCode();
        final Object other$respCode = other.getRespCode();
        Label_0139: {
            if (this$respCode == null) {
                if (other$respCode == null) {
                    break Label_0139;
                }
            }
            else if (this$respCode.equals(other$respCode)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$cardNumbers = this.getCardNumbers();
        final Object other$cardNumbers = other.getCardNumbers();
        if (this$cardNumbers == null) {
            if (other$cardNumbers == null) {
                return true;
            }
        }
        else if (this$cardNumbers.equals(other$cardNumbers)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof DecryptResponseVo;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $logRefId = this.getLogRefId();
        result = result * 59 + (($logRefId == null) ? 43 : $logRefId.hashCode());
        final Object $respMsg = this.getRespMsg();
        result = result * 59 + (($respMsg == null) ? 43 : $respMsg.hashCode());
        final Object $respCode = this.getRespCode();
        result = result * 59 + (($respCode == null) ? 43 : $respCode.hashCode());
        final Object $cardNumbers = this.getCardNumbers();
        result = result * 59 + (($cardNumbers == null) ? 43 : $cardNumbers.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "DecryptResponseVo(logRefId=" + this.getLogRefId() + ", respMsg=" + this.getRespMsg() + ", respCode=" + this.getRespCode() + ", cardNumbers=" + String.valueOf(this.getCardNumbers());
    }
}
