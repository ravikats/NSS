/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.common.entity.FileFormatsEntity
 *  jakarta.persistence.Column
 *  jakarta.persistence.Entity
 *  jakarta.persistence.Id
 *  jakarta.persistence.Table
 */
package com.empay.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;

@Entity
@Table(name="FILE_FORMATS")
public class FileFormatsEntity {
    @Id
    @Column(name="FOR_CODE")
    private Integer code;
    @Column(name="FOR_LAST_UPDATED")
    private Date lastUpdatedDate;
    @Column(name="FOR_UPDATED_USER")
    private int lastUpdatedUser;
    @Column(name="FOR_INS_CODE")
    private int institutionCode;
    @Column(name="FOR_TYPE")
    private Character type;
    @Column(name="FOR_INT_TYPE")
    private Character interfaceType;
    @Column(name="FOR_DESCRIPTION")
    private String description;
    @Column(name="FOR_SYSTEM_CODE")
    private Integer systemCode;
    @Column(name="FOR_FILE_TYPE")
    private Character fileType;
    @Column(name="FOR_FIELD_SEPERATOR")
    private Character fieldSeperator;

    public Integer getCode() {
        return this.code;
    }

    public Date getLastUpdatedDate() {
        return this.lastUpdatedDate;
    }

    public int getLastUpdatedUser() {
        return this.lastUpdatedUser;
    }

    public int getInstitutionCode() {
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

    public Integer getSystemCode() {
        return this.systemCode;
    }

    public Character getFileType() {
        return this.fileType;
    }

    public Character getFieldSeperator() {
        return this.fieldSeperator;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public void setLastUpdatedUser(int lastUpdatedUser) {
        this.lastUpdatedUser = lastUpdatedUser;
    }

    public void setInstitutionCode(int institutionCode) {
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

    public void setSystemCode(Integer systemCode) {
        this.systemCode = systemCode;
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
        if (this.getLastUpdatedUser() != other.getLastUpdatedUser()) {
            return false;
        }
        if (this.getInstitutionCode() != other.getInstitutionCode()) {
            return false;
        }
        Integer this$code = this.getCode();
        Integer other$code = other.getCode();
        if (this$code == null ? other$code != null : !((Object)this$code).equals(other$code)) {
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
        Integer this$systemCode = this.getSystemCode();
        Integer other$systemCode = other.getSystemCode();
        if (this$systemCode == null ? other$systemCode != null : !((Object)this$systemCode).equals(other$systemCode)) {
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
        Date this$lastUpdatedDate = this.getLastUpdatedDate();
        Date other$lastUpdatedDate = other.getLastUpdatedDate();
        if (this$lastUpdatedDate == null ? other$lastUpdatedDate != null : !((Object)this$lastUpdatedDate).equals(other$lastUpdatedDate)) {
            return false;
        }
        String this$description = this.getDescription();
        String other$description = other.getDescription();
        return !(this$description == null ? other$description != null : !this$description.equals(other$description));
    }

    protected boolean canEqual(Object other) {
        return other instanceof FileFormatsEntity;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + this.getLastUpdatedUser();
        result = result * 59 + this.getInstitutionCode();
        Integer $code = this.getCode();
        result = result * 59 + ($code == null ? 43 : ((Object)$code).hashCode());
        Character $type = this.getType();
        result = result * 59 + ($type == null ? 43 : ((Object)$type).hashCode());
        Character $interfaceType = this.getInterfaceType();
        result = result * 59 + ($interfaceType == null ? 43 : ((Object)$interfaceType).hashCode());
        Integer $systemCode = this.getSystemCode();
        result = result * 59 + ($systemCode == null ? 43 : ((Object)$systemCode).hashCode());
        Character $fileType = this.getFileType();
        result = result * 59 + ($fileType == null ? 43 : ((Object)$fileType).hashCode());
        Character $fieldSeperator = this.getFieldSeperator();
        result = result * 59 + ($fieldSeperator == null ? 43 : ((Object)$fieldSeperator).hashCode());
        Date $lastUpdatedDate = this.getLastUpdatedDate();
        result = result * 59 + ($lastUpdatedDate == null ? 43 : ((Object)$lastUpdatedDate).hashCode());
        String $description = this.getDescription();
        result = result * 59 + ($description == null ? 43 : $description.hashCode());
        return result;
    }

    public String toString() {
        return "FileFormatsEntity(code=" + this.getCode() + ", lastUpdatedDate=" + String.valueOf(this.getLastUpdatedDate()) + ", lastUpdatedUser=" + this.getLastUpdatedUser() + ", institutionCode=" + this.getInstitutionCode() + ", type=" + this.getType() + ", interfaceType=" + this.getInterfaceType() + ", description=" + this.getDescription() + ", systemCode=" + this.getSystemCode() + ", fileType=" + this.getFileType() + ", fieldSeperator=" + this.getFieldSeperator() + ")";
    }
}

