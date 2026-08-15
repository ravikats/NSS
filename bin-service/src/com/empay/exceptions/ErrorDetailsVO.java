/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.exceptions.ErrorDetailsVO
 *  com.fasterxml.jackson.annotation.JsonInclude
 *  com.fasterxml.jackson.annotation.JsonInclude$Include
 */
package com.empay.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(value=JsonInclude.Include.NON_NULL)
public class ErrorDetailsVO {
    public String field;
    public String value;
    public String location;
    public String issue;

    public String getField() {
        return this.field;
    }

    public String getValue() {
        return this.value;
    }

    public String getLocation() {
        return this.location;
    }

    public String getIssue() {
        return this.issue;
    }

    public void setField(String field) {
        this.field = field;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ErrorDetailsVO)) {
            return false;
        }
        ErrorDetailsVO other = (ErrorDetailsVO)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        String this$field = this.getField();
        String other$field = other.getField();
        if (this$field == null ? other$field != null : !this$field.equals(other$field)) {
            return false;
        }
        String this$value = this.getValue();
        String other$value = other.getValue();
        if (this$value == null ? other$value != null : !this$value.equals(other$value)) {
            return false;
        }
        String this$location = this.getLocation();
        String other$location = other.getLocation();
        if (this$location == null ? other$location != null : !this$location.equals(other$location)) {
            return false;
        }
        String this$issue = this.getIssue();
        String other$issue = other.getIssue();
        return !(this$issue == null ? other$issue != null : !this$issue.equals(other$issue));
    }

    protected boolean canEqual(Object other) {
        return other instanceof ErrorDetailsVO;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        String $field = this.getField();
        result = result * 59 + ($field == null ? 43 : $field.hashCode());
        String $value = this.getValue();
        result = result * 59 + ($value == null ? 43 : $value.hashCode());
        String $location = this.getLocation();
        result = result * 59 + ($location == null ? 43 : $location.hashCode());
        String $issue = this.getIssue();
        result = result * 59 + ($issue == null ? 43 : $issue.hashCode());
        return result;
    }

    public String toString() {
        return "ErrorDetailsVO(field=" + this.getField() + ", value=" + this.getValue() + ", location=" + this.getLocation() + ", issue=" + this.getIssue() + ")";
    }
}

