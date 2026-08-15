/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.entities.FileFormatsEntity
 *  com.empay.entities.SystemFileFormatsEntity
 *  jakarta.persistence.Column
 *  jakarta.persistence.Entity
 *  jakarta.persistence.GeneratedValue
 *  jakarta.persistence.GenerationType
 *  jakarta.persistence.Id
 *  jakarta.persistence.JoinColumn
 *  jakarta.persistence.ManyToOne
 *  jakarta.persistence.Table
 */
package com.empay.entities;

import com.empay.entities.SystemFileFormatsEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name="FILE_FORMATS")
public class FileFormatsEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="FOR_CODE")
    private int code;
    @Column(name="FOR_LAST_UPDATED")
    private LocalDateTime lastUpdated;
    @Column(name="FOR_UPDATED_USER")
    private Integer updatedUser;
    @Column(name="FOR_INS_CODE")
    private Integer institutionCode;
    @Column(name="FOR_TYPE")
    private Character type;
    @Column(name="FOR_INT_TYPE")
    private Character interfaceType;
    @Column(name="FOR_DESCRIPTION")
    private String description;
    @ManyToOne
    @JoinColumn(name="FOR_SYSTEM_CODE", referencedColumnName="SFF_CODE")
    private SystemFileFormatsEntity systemFileFormats;
    @Column(name="FOR_FILE_TYPE")
    private Character fileType;
    @Column(name="FOR_FIELD_SEPERATOR")
    private Character fieldSeperator;

    public int getCode() {
        return this.code;
    }

    public LocalDateTime getLastUpdated() {
        return this.lastUpdated;
    }

    public Integer getUpdatedUser() {
        return this.updatedUser;
    }

    public Integer getInstitutionCode() {
        return this.institutionCode;
    }

    public Character getType() {
        return this.type;
    }

    public Character getInterfaceType() {
        return this.interfaceType;
    }

    public String getDescription() {
        return this.description;
    }

    public SystemFileFormatsEntity getSystemFileFormats() {
        return this.systemFileFormats;
    }

    public Character getFileType() {
        return this.fileType;
    }

    public Character getFieldSeperator() {
        return this.fieldSeperator;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public void setUpdatedUser(Integer updatedUser) {
        this.updatedUser = updatedUser;
    }

    public void setInstitutionCode(Integer institutionCode) {
        this.institutionCode = institutionCode;
    }

    public void setType(Character type) {
        this.type = type;
    }

    public void setInterfaceType(Character interfaceType) {
        this.interfaceType = interfaceType;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSystemFileFormats(SystemFileFormatsEntity systemFileFormats) {
        this.systemFileFormats = systemFileFormats;
    }

    public void setFileType(Character fileType) {
        this.fileType = fileType;
    }

    public void setFieldSeperator(Character fieldSeperator) {
        this.fieldSeperator = fieldSeperator;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof FileFormatsEntity)) {
            return false;
        }
        FileFormatsEntity other = (FileFormatsEntity)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        if (this.getCode() != other.getCode()) {
            return false;
        }
        Integer this$updatedUser = this.getUpdatedUser();
        Integer other$updatedUser = other.getUpdatedUser();
        if (this$updatedUser == null ? other$updatedUser != null : !((Object)this$updatedUser).equals(other$updatedUser)) {
            return false;
        }
        Integer this$institutionCode = this.getInstitutionCode();
        Integer other$institutionCode = other.getInstitutionCode();
        if (this$institutionCode == null ? other$institutionCode != null : !((Object)this$institutionCode).equals(other$institutionCode)) {
            return false;
        }
        Character this$type = this.getType();
        Character other$type = other.getType();
        if (this$type == null ? other$type != null : !((Object)this$type).equals(other$type)) {
            return false;
        }
        Character this$interfaceType = this.getInterfaceType();
        Character other$interfaceType = other.getInterfaceType();
        if (this$interfaceType == null ? other$interfaceType != null : !((Object)this$interfaceType).equals(other$interfaceType)) {
            return false;
        }
        Character this$fileType = this.getFileType();
        Character other$fileType = other.getFileType();
        if (this$fileType == null ? other$fileType != null : !((Object)this$fileType).equals(other$fileType)) {
            return false;
        }
        Character this$fieldSeperator = this.getFieldSeperator();
        Character other$fieldSeperator = other.getFieldSeperator();
        if (this$fieldSeperator == null ? other$fieldSeperator != null : !((Object)this$fieldSeperator).equals(other$fieldSeperator)) {
            return false;
        }
        LocalDateTime this$lastUpdated = this.getLastUpdated();
        LocalDateTime other$lastUpdated = other.getLastUpdated();
        if (this$lastUpdated == null ? other$lastUpdated != null : !((Object)this$lastUpdated).equals(other$lastUpdated)) {
            return false;
        }
        String this$description = this.getDescription();
        String other$description = other.getDescription();
        if (this$description == null ? other$description != null : !this$description.equals(other$description)) {
            return false;
        }
        SystemFileFormatsEntity this$systemFileFormats = this.getSystemFileFormats();
        SystemFileFormatsEntity other$systemFileFormats = other.getSystemFileFormats();
        return !(this$systemFileFormats == null ? other$systemFileFormats != null : !this$systemFileFormats.equals(other$systemFileFormats));
    }

    protected boolean canEqual(Object other) {
        return other instanceof FileFormatsEntity;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + this.getCode();
        Integer $updatedUser = this.getUpdatedUser();
        result = result * 59 + ($updatedUser == null ? 43 : ((Object)$updatedUser).hashCode());
        Integer $institutionCode = this.getInstitutionCode();
        result = result * 59 + ($institutionCode == null ? 43 : ((Object)$institutionCode).hashCode());
        Character $type = this.getType();
        result = result * 59 + ($type == null ? 43 : ((Object)$type).hashCode());
        Character $interfaceType = this.getInterfaceType();
        result = result * 59 + ($interfaceType == null ? 43 : ((Object)$interfaceType).hashCode());
        Character $fileType = this.getFileType();
        result = result * 59 + ($fileType == null ? 43 : ((Object)$fileType).hashCode());
        Character $fieldSeperator = this.getFieldSeperator();
        result = result * 59 + ($fieldSeperator == null ? 43 : ((Object)$fieldSeperator).hashCode());
        LocalDateTime $lastUpdated = this.getLastUpdated();
        result = result * 59 + ($lastUpdated == null ? 43 : ((Object)$lastUpdated).hashCode());
        String $description = this.getDescription();
        result = result * 59 + ($description == null ? 43 : $description.hashCode());
        SystemFileFormatsEntity $systemFileFormats = this.getSystemFileFormats();
        result = result * 59 + ($systemFileFormats == null ? 43 : $systemFileFormats.hashCode());
        return result;
    }

    public String toString() {
        return "FileFormatsEntity(code=" + this.getCode() + ", lastUpdated=" + String.valueOf(this.getLastUpdated()) + ", updatedUser=" + this.getUpdatedUser() + ", institutionCode=" + this.getInstitutionCode() + ", type=" + this.getType() + ", interfaceType=" + this.getInterfaceType() + ", description=" + this.getDescription() + ", systemFileFormats=" + String.valueOf(this.getSystemFileFormats()) + ", fileType=" + this.getFileType() + ", fieldSeperator=" + this.getFieldSeperator() + ")";
    }
}

