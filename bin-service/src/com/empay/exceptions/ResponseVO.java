/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.exceptions.ResponseVO
 *  com.fasterxml.jackson.annotation.JsonProperty
 */
package com.empay.exceptions;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseVO {
    private String name;
    @JsonProperty(value="debug_id")
    private String debugId;
    private String message;

    public String getName() {
        return this.name;
    }

    public String getDebugId() {
        return this.debugId;
    }

    public String getMessage() {
        return this.message;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty(value="debug_id")
    public void setDebugId(String debugId) {
        this.debugId = debugId;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ResponseVO)) {
            return false;
        }
        ResponseVO other = (ResponseVO)o;
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
        return !(this$message == null ? other$message != null : !this$message.equals(other$message));
    }

    protected boolean canEqual(Object other) {
        return other instanceof ResponseVO;
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
        return result;
    }

    public String toString() {
        return "ResponseVO(name=" + this.getName() + ", debugId=" + this.getDebugId() + ", message=" + this.getMessage() + ")";
    }
}

