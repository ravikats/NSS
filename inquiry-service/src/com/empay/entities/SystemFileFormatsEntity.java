/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.entities.SystemFileFormatsEntity
 *  jakarta.persistence.Column
 *  jakarta.persistence.Entity
 *  jakarta.persistence.Id
 *  jakarta.persistence.Table
 */
package com.empay.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name="SYSTEM_FILE_FORMATS")
public class SystemFileFormatsEntity {
    @Id
    @Column(name="SFF_CODE")
    private Integer code;
    @Column(name="SFF_LAST_UPDATED")
    private LocalDateTime lastUpdated;
    @Column(name="SFF_UPDATED_USER")
    private int lastUpdatedUser;
    @Column(name="SFF_DESCRIPTION")
    private String description;

    public Integer getCode() {
        return this.code;
    }

    public LocalDateTime getLastUpdated() {
        return this.lastUpdated;
    }

    public int getLastUpdatedUser() {
        return this.lastUpdatedUser;
    }

    public String getDescription() {
        return this.description;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public void setLastUpdatedUser(int lastUpdatedUser) {
        this.lastUpdatedUser = lastUpdatedUser;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof SystemFileFormatsEntity)) {
            return false;
        }
        SystemFileFormatsEntity other = (SystemFileFormatsEntity)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        if (this.getLastUpdatedUser() != other.getLastUpdatedUser()) {
            return false;
        }
        Integer this$code = this.getCode();
        Integer other$code = other.getCode();
        if (this$code == null ? other$code != null : !((Object)this$code).equals(other$code)) {
            return false;
        }
        LocalDateTime this$lastUpdated = this.getLastUpdated();
        LocalDateTime other$lastUpdated = other.getLastUpdated();
        if (this$lastUpdated == null ? other$lastUpdated != null : !((Object)this$lastUpdated).equals(other$lastUpdated)) {
            return false;
        }
        String this$description = this.getDescription();
        String other$description = other.getDescription();
        return !(this$description == null ? other$description != null : !this$description.equals(other$description));
    }

    protected boolean canEqual(Object other) {
        return other instanceof SystemFileFormatsEntity;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + this.getLastUpdatedUser();
        Integer $code = this.getCode();
        result = result * 59 + ($code == null ? 43 : ((Object)$code).hashCode());
        LocalDateTime $lastUpdated = this.getLastUpdated();
        result = result * 59 + ($lastUpdated == null ? 43 : ((Object)$lastUpdated).hashCode());
        String $description = this.getDescription();
        result = result * 59 + ($description == null ? 43 : $description.hashCode());
        return result;
    }

    public String toString() {
        return "SystemFileFormatsEntity(code=" + this.getCode() + ", lastUpdated=" + String.valueOf(this.getLastUpdated()) + ", lastUpdatedUser=" + this.getLastUpdatedUser() + ", description=" + this.getDescription() + ")";
    }
}

