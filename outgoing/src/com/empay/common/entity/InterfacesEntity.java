/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.common.entity.InterfacesEntity
 *  jakarta.persistence.Column
 *  jakarta.persistence.Entity
 *  jakarta.persistence.GeneratedValue
 *  jakarta.persistence.GenerationType
 *  jakarta.persistence.Id
 *  jakarta.persistence.Table
 */
package com.empay.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="INTERFACES")
public class InterfacesEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="INT_CODE")
    private int interfaceCode;
    @Column(name="INT_TYPE")
    private String interfaceType;
    @Column(name="INT_NAME")
    private String interfaceName;
    @Column(name="INT_CATEGORY")
    private String interfaceCategory;
    @Column(name="INT_INS_CODE")
    private int institutionCode;

    public int getInterfaceCode() {
        return this.interfaceCode;
    }

    public String getInterfaceType() {
        return this.interfaceType;
    }

    public String getInterfaceName() {
        return this.interfaceName;
    }

    public String getInterfaceCategory() {
        return this.interfaceCategory;
    }

    public int getInstitutionCode() {
        return this.institutionCode;
    }

    public void setInterfaceCode(int interfaceCode) {
        this.interfaceCode = interfaceCode;
    }

    public void setInterfaceType(String interfaceType) {
        this.interfaceType = interfaceType;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public void setInterfaceCategory(String interfaceCategory) {
        this.interfaceCategory = interfaceCategory;
    }

    public void setInstitutionCode(int institutionCode) {
        this.institutionCode = institutionCode;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof InterfacesEntity)) {
            return false;
        }
        InterfacesEntity other = (InterfacesEntity)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        if (this.getInterfaceCode() != other.getInterfaceCode()) {
            return false;
        }
        if (this.getInstitutionCode() != other.getInstitutionCode()) {
            return false;
        }
        String this$interfaceType = this.getInterfaceType();
        String other$interfaceType = other.getInterfaceType();
        if (this$interfaceType == null ? other$interfaceType != null : !this$interfaceType.equals(other$interfaceType)) {
            return false;
        }
        String this$interfaceName = this.getInterfaceName();
        String other$interfaceName = other.getInterfaceName();
        if (this$interfaceName == null ? other$interfaceName != null : !this$interfaceName.equals(other$interfaceName)) {
            return false;
        }
        String this$interfaceCategory = this.getInterfaceCategory();
        String other$interfaceCategory = other.getInterfaceCategory();
        return !(this$interfaceCategory == null ? other$interfaceCategory != null : !this$interfaceCategory.equals(other$interfaceCategory));
    }

    protected boolean canEqual(Object other) {
        return other instanceof InterfacesEntity;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + this.getInterfaceCode();
        result = result * 59 + this.getInstitutionCode();
        String $interfaceType = this.getInterfaceType();
        result = result * 59 + ($interfaceType == null ? 43 : $interfaceType.hashCode());
        String $interfaceName = this.getInterfaceName();
        result = result * 59 + ($interfaceName == null ? 43 : $interfaceName.hashCode());
        String $interfaceCategory = this.getInterfaceCategory();
        result = result * 59 + ($interfaceCategory == null ? 43 : $interfaceCategory.hashCode());
        return result;
    }

    public String toString() {
        return "InterfacesEntity(interfaceCode=" + this.getInterfaceCode() + ", interfaceType=" + this.getInterfaceType() + ", interfaceName=" + this.getInterfaceName() + ", interfaceCategory=" + this.getInterfaceCategory() + ", institutionCode=" + this.getInstitutionCode() + ")";
    }
}

