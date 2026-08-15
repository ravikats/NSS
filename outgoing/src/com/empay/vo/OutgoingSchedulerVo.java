/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.vo.OutgoingSchedulerVo
 *  com.fasterxml.jackson.annotation.JsonInclude
 *  com.fasterxml.jackson.annotation.JsonInclude$Include
 *  jakarta.validation.constraints.NotEmpty
 *  jakarta.validation.constraints.NotNull
 *  jakarta.validation.constraints.Pattern
 */
package com.empay.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@JsonInclude(value=JsonInclude.Include.NON_NULL)
public class OutgoingSchedulerVo {
    @NotNull(message="Name must not be null")
    @NotEmpty(message="Name must not be empty!")
    public @NotNull(message="Name must not be null") @NotEmpty(message="Name must not be empty!") String name;
    @NotNull(message="Name must not be null")
    @NotEmpty(message="Name must not be empty!")
    @Pattern(regexp="^(?i)(MASTERCARD|VISA|RUPAY|AMEX|JAYWAN)$", message="Invalid network")
    public @NotNull(message="Name must not be null") @NotEmpty(message="Name must not be empty!") @Pattern(regexp="^(?i)(MASTERCARD|VISA|RUPAY|AMEX|JAYWAN)$", message="Invalid network") String network;
    @NotNull(message="Active Status must not be null")
    public @NotNull(message="Active Status must not be null") Boolean isActive;
    @NotNull(message="EndTime must not be null")
    @NotEmpty(message="EndTime must not be empty!")
    @Pattern(regexp="^(?:[01]\\d|2[0-3]):[0-5]\\d:[0-5]\\d$")
    public @NotNull(message="EndTime must not be null") @NotEmpty(message="EndTime must not be empty!") @Pattern(regexp="^(?:[01]\\d|2[0-3]):[0-5]\\d:[0-5]\\d$") String endTime;
    public Boolean editMode;
    public String createdDateTime;
    public Integer totalCount;
    public Integer totalPage;

    public String getName() {
        return this.name;
    }

    public String getNetwork() {
        return this.network;
    }

    public Boolean getIsActive() {
        return this.isActive;
    }

    public String getEndTime() {
        return this.endTime;
    }

    public Boolean getEditMode() {
        return this.editMode;
    }

    public String getCreatedDateTime() {
        return this.createdDateTime;
    }

    public Integer getTotalCount() {
        return this.totalCount;
    }

    public Integer getTotalPage() {
        return this.totalPage;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setEditMode(Boolean editMode) {
        this.editMode = editMode;
    }

    public void setCreatedDateTime(String createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof OutgoingSchedulerVo)) {
            return false;
        }
        OutgoingSchedulerVo other = (OutgoingSchedulerVo)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        Boolean this$isActive = this.getIsActive();
        Boolean other$isActive = other.getIsActive();
        if (this$isActive == null ? other$isActive != null : !((Object)this$isActive).equals(other$isActive)) {
            return false;
        }
        Boolean this$editMode = this.getEditMode();
        Boolean other$editMode = other.getEditMode();
        if (this$editMode == null ? other$editMode != null : !((Object)this$editMode).equals(other$editMode)) {
            return false;
        }
        Integer this$totalCount = this.getTotalCount();
        Integer other$totalCount = other.getTotalCount();
        if (this$totalCount == null ? other$totalCount != null : !((Object)this$totalCount).equals(other$totalCount)) {
            return false;
        }
        Integer this$totalPage = this.getTotalPage();
        Integer other$totalPage = other.getTotalPage();
        if (this$totalPage == null ? other$totalPage != null : !((Object)this$totalPage).equals(other$totalPage)) {
            return false;
        }
        String this$name = this.getName();
        String other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) {
            return false;
        }
        String this$network = this.getNetwork();
        String other$network = other.getNetwork();
        if (this$network == null ? other$network != null : !this$network.equals(other$network)) {
            return false;
        }
        String this$endTime = this.getEndTime();
        String other$endTime = other.getEndTime();
        if (this$endTime == null ? other$endTime != null : !this$endTime.equals(other$endTime)) {
            return false;
        }
        String this$createdDateTime = this.getCreatedDateTime();
        String other$createdDateTime = other.getCreatedDateTime();
        return !(this$createdDateTime == null ? other$createdDateTime != null : !this$createdDateTime.equals(other$createdDateTime));
    }

    protected boolean canEqual(Object other) {
        return other instanceof OutgoingSchedulerVo;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Boolean $isActive = this.getIsActive();
        result = result * 59 + ($isActive == null ? 43 : ((Object)$isActive).hashCode());
        Boolean $editMode = this.getEditMode();
        result = result * 59 + ($editMode == null ? 43 : ((Object)$editMode).hashCode());
        Integer $totalCount = this.getTotalCount();
        result = result * 59 + ($totalCount == null ? 43 : ((Object)$totalCount).hashCode());
        Integer $totalPage = this.getTotalPage();
        result = result * 59 + ($totalPage == null ? 43 : ((Object)$totalPage).hashCode());
        String $name = this.getName();
        result = result * 59 + ($name == null ? 43 : $name.hashCode());
        String $network = this.getNetwork();
        result = result * 59 + ($network == null ? 43 : $network.hashCode());
        String $endTime = this.getEndTime();
        result = result * 59 + ($endTime == null ? 43 : $endTime.hashCode());
        String $createdDateTime = this.getCreatedDateTime();
        result = result * 59 + ($createdDateTime == null ? 43 : $createdDateTime.hashCode());
        return result;
    }

    public String toString() {
        return "OutgoingSchedulerVo(name=" + this.getName() + ", network=" + this.getNetwork() + ", isActive=" + this.getIsActive() + ", endTime=" + this.getEndTime() + ", editMode=" + this.getEditMode() + ", createdDateTime=" + this.getCreatedDateTime() + ", totalCount=" + this.getTotalCount() + ", totalPage=" + this.getTotalPage() + ")";
    }
}

