/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.exceptions.ErrorDetailsVO
 *  com.empay.exceptions.ValidationExceptionVO
 *  com.fasterxml.jackson.annotation.JsonInclude
 *  com.fasterxml.jackson.annotation.JsonInclude$Include
 */
package com.empay.exceptions;

import com.empay.exceptions.ErrorDetailsVO;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;

@JsonInclude(value=JsonInclude.Include.NON_NULL)
public class ValidationExceptionVO {
    public String name;
    public String debugId;
    public String message;
    private List<ErrorDetailsVO> errorDetails;

    public String getName() {
        return this.name;
    }

    public String getDebugId() {
        return this.debugId;
    }

    public String getMessage() {
        return this.message;
    }

    public List<ErrorDetailsVO> getErrorDetails() {
        return this.errorDetails;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDebugId(String debugId) {
        this.debugId = debugId;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setErrorDetails(List<ErrorDetailsVO> errorDetails) {
        this.errorDetails = errorDetails;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ValidationExceptionVO)) {
            return false;
        }
        ValidationExceptionVO other = (ValidationExceptionVO)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        String this$name = this.getName();
        String other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) {
            return false;
        }
        String this$debugId = this.getDebugId();
        String other$debugId = other.getDebugId();
        if (this$debugId == null ? other$debugId != null : !this$debugId.equals(other$debugId)) {
            return false;
        }
        String this$message = this.getMessage();
        String other$message = other.getMessage();
        if (this$message == null ? other$message != null : !this$message.equals(other$message)) {
            return false;
        }
        List this$errorDetails = this.getErrorDetails();
        List other$errorDetails = other.getErrorDetails();
        return !(this$errorDetails == null ? other$errorDetails != null : !((Object)this$errorDetails).equals(other$errorDetails));
    }

    protected boolean canEqual(Object other) {
        return other instanceof ValidationExceptionVO;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        String $name = this.getName();
        result = result * 59 + ($name == null ? 43 : $name.hashCode());
        String $debugId = this.getDebugId();
        result = result * 59 + ($debugId == null ? 43 : $debugId.hashCode());
        String $message = this.getMessage();
        result = result * 59 + ($message == null ? 43 : $message.hashCode());
        List $errorDetails = this.getErrorDetails();
        result = result * 59 + ($errorDetails == null ? 43 : ((Object)$errorDetails).hashCode());
        return result;
    }

    public String toString() {
        return "ValidationExceptionVO(name=" + this.getName() + ", debugId=" + this.getDebugId() + ", message=" + this.getMessage() + ", errorDetails=" + String.valueOf(this.getErrorDetails()) + ")";
    }
}

