/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.cryptapi.DecryptResponseVo
 */
package com.empay.cryptapi;

import java.util.Map;

public class DecryptResponseVo {
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

    public void setLogRefId(String logRefId) {
        this.logRefId = logRefId;
    }

    public void setRespMsg(String respMsg) {
        this.respMsg = respMsg;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public void setCardNumbers(Map<String, String> cardNumbers) {
        this.cardNumbers = cardNumbers;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof DecryptResponseVo)) {
            return false;
        }
        DecryptResponseVo other = (DecryptResponseVo)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        String this$logRefId = this.getLogRefId();
        String other$logRefId = other.getLogRefId();
        if (this$logRefId == null ? other$logRefId != null : !this$logRefId.equals(other$logRefId)) {
            return false;
        }
        String this$respMsg = this.getRespMsg();
        String other$respMsg = other.getRespMsg();
        if (this$respMsg == null ? other$respMsg != null : !this$respMsg.equals(other$respMsg)) {
            return false;
        }
        String this$respCode = this.getRespCode();
        String other$respCode = other.getRespCode();
        if (this$respCode == null ? other$respCode != null : !this$respCode.equals(other$respCode)) {
            return false;
        }
        Map this$cardNumbers = this.getCardNumbers();
        Map other$cardNumbers = other.getCardNumbers();
        return !(this$cardNumbers == null ? other$cardNumbers != null : !((Object)this$cardNumbers).equals(other$cardNumbers));
    }

    protected boolean canEqual(Object other) {
        return other instanceof DecryptResponseVo;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        String $logRefId = this.getLogRefId();
        result = result * 59 + ($logRefId == null ? 43 : $logRefId.hashCode());
        String $respMsg = this.getRespMsg();
        result = result * 59 + ($respMsg == null ? 43 : $respMsg.hashCode());
        String $respCode = this.getRespCode();
        result = result * 59 + ($respCode == null ? 43 : $respCode.hashCode());
        Map $cardNumbers = this.getCardNumbers();
        result = result * 59 + ($cardNumbers == null ? 43 : ((Object)$cardNumbers).hashCode());
        return result;
    }

    public String toString() {
        return "DecryptResponseVo(logRefId=" + this.getLogRefId() + ", respMsg=" + this.getRespMsg() + ", respCode=" + this.getRespCode() + ", cardNumbers=" + String.valueOf(this.getCardNumbers()) + ")";
    }
}

