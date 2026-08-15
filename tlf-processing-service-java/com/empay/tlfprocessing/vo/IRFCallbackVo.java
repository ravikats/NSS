// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.tlfprocessing.vo;

public class IRFCallbackVo
{
    private Character status;
    private Integer refSerNumber;
    private String request;
    private String response;
    
    public Character getStatus() {
        return this.status;
    }
    
    public Integer getRefSerNumber() {
        return this.refSerNumber;
    }
    
    public String getRequest() {
        return this.request;
    }
    
    public String getResponse() {
        return this.response;
    }
    
    public void setStatus(final Character status) {
        this.status = status;
    }
    
    public void setRefSerNumber(final Integer refSerNumber) {
        this.refSerNumber = refSerNumber;
    }
    
    public void setRequest(final String request) {
        this.request = request;
    }
    
    public void setResponse(final String response) {
        this.response = response;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof IRFCallbackVo)) {
            return false;
        }
        final IRFCallbackVo other = (IRFCallbackVo)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$status = this.getStatus();
        final Object other$status = other.getStatus();
        Label_0065: {
            if (this$status == null) {
                if (other$status == null) {
                    break Label_0065;
                }
            }
            else if (this$status.equals(other$status)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$refSerNumber = this.getRefSerNumber();
        final Object other$refSerNumber = other.getRefSerNumber();
        Label_0102: {
            if (this$refSerNumber == null) {
                if (other$refSerNumber == null) {
                    break Label_0102;
                }
            }
            else if (this$refSerNumber.equals(other$refSerNumber)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$request = this.getRequest();
        final Object other$request = other.getRequest();
        Label_0139: {
            if (this$request == null) {
                if (other$request == null) {
                    break Label_0139;
                }
            }
            else if (this$request.equals(other$request)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$response = this.getResponse();
        final Object other$response = other.getResponse();
        if (this$response == null) {
            if (other$response == null) {
                return true;
            }
        }
        else if (this$response.equals(other$response)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof IRFCallbackVo;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $status = this.getStatus();
        result = result * 59 + (($status == null) ? 43 : $status.hashCode());
        final Object $refSerNumber = this.getRefSerNumber();
        result = result * 59 + (($refSerNumber == null) ? 43 : $refSerNumber.hashCode());
        final Object $request = this.getRequest();
        result = result * 59 + (($request == null) ? 43 : $request.hashCode());
        final Object $response = this.getResponse();
        result = result * 59 + (($response == null) ? 43 : $response.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "IRFCallbackVo(status=" + this.getStatus() + ", refSerNumber=" + this.getRefSerNumber() + ", request=" + this.getRequest() + ", response=" + this.getResponse();
    }
}
