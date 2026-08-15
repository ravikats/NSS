/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.entities.IRFCallbackEntity
 *  jakarta.persistence.Column
 *  jakarta.persistence.Entity
 *  jakarta.persistence.GeneratedValue
 *  jakarta.persistence.GenerationType
 *  jakarta.persistence.Id
 *  jakarta.persistence.Table
 */
package com.empay.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name="IRF_CALLBACK")
public class IRFCallbackEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ICB_SER_NUMBER")
    private Integer serialNumber;
    @Column(name="ICB_LAST_UPDATED")
    private LocalDateTime lastUpdated;
    @Column(name="ICB_UPDATED_USER")
    private Integer updatedUser;
    @Column(name="ICB_INS_CODE")
    private Integer institutionCode;
    @Column(name="ICB_STATUS")
    private Character status;
    @Column(name="ICB_REF_SER_NUMBER")
    private Integer refSerNumber;
    @Column(name="ICB_REQUEST")
    private String request;
    @Column(name="ICB_RESPONSE")
    private String response;
    @Column(name="ICB_SCHEME_INCHG_FLAG")
    private String schemeInchgFlag;

    public Integer getSerialNumber() {
        return this.serialNumber;
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

    public String getSchemeInchgFlag() {
        return this.schemeInchgFlag;
    }

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
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

    public void setStatus(Character status) {
        this.status = status;
    }

    public void setRefSerNumber(Integer refSerNumber) {
        this.refSerNumber = refSerNumber;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public void setSchemeInchgFlag(String schemeInchgFlag) {
        this.schemeInchgFlag = schemeInchgFlag;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof IRFCallbackEntity)) {
            return false;
        }
        IRFCallbackEntity other = (IRFCallbackEntity)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        Integer this$serialNumber = this.getSerialNumber();
        Integer other$serialNumber = other.getSerialNumber();
        if (this$serialNumber == null ? other$serialNumber != null : !((Object)this$serialNumber).equals(other$serialNumber)) {
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
        Character this$status = this.getStatus();
        Character other$status = other.getStatus();
        if (this$status == null ? other$status != null : !((Object)this$status).equals(other$status)) {
            return false;
        }
        Integer this$refSerNumber = this.getRefSerNumber();
        Integer other$refSerNumber = other.getRefSerNumber();
        if (this$refSerNumber == null ? other$refSerNumber != null : !((Object)this$refSerNumber).equals(other$refSerNumber)) {
            return false;
        }
        LocalDateTime this$lastUpdated = this.getLastUpdated();
        LocalDateTime other$lastUpdated = other.getLastUpdated();
        if (this$lastUpdated == null ? other$lastUpdated != null : !((Object)this$lastUpdated).equals(other$lastUpdated)) {
            return false;
        }
        String this$request = this.getRequest();
        String other$request = other.getRequest();
        if (this$request == null ? other$request != null : !this$request.equals(other$request)) {
            return false;
        }
        String this$response = this.getResponse();
        String other$response = other.getResponse();
        if (this$response == null ? other$response != null : !this$response.equals(other$response)) {
            return false;
        }
        String this$schemeInchgFlag = this.getSchemeInchgFlag();
        String other$schemeInchgFlag = other.getSchemeInchgFlag();
        return !(this$schemeInchgFlag == null ? other$schemeInchgFlag != null : !this$schemeInchgFlag.equals(other$schemeInchgFlag));
    }

    protected boolean canEqual(Object other) {
        return other instanceof IRFCallbackEntity;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Integer $serialNumber = this.getSerialNumber();
        result = result * 59 + ($serialNumber == null ? 43 : ((Object)$serialNumber).hashCode());
        Integer $updatedUser = this.getUpdatedUser();
        result = result * 59 + ($updatedUser == null ? 43 : ((Object)$updatedUser).hashCode());
        Integer $institutionCode = this.getInstitutionCode();
        result = result * 59 + ($institutionCode == null ? 43 : ((Object)$institutionCode).hashCode());
        Character $status = this.getStatus();
        result = result * 59 + ($status == null ? 43 : ((Object)$status).hashCode());
        Integer $refSerNumber = this.getRefSerNumber();
        result = result * 59 + ($refSerNumber == null ? 43 : ((Object)$refSerNumber).hashCode());
        LocalDateTime $lastUpdated = this.getLastUpdated();
        result = result * 59 + ($lastUpdated == null ? 43 : ((Object)$lastUpdated).hashCode());
        String $request = this.getRequest();
        result = result * 59 + ($request == null ? 43 : $request.hashCode());
        String $response = this.getResponse();
        result = result * 59 + ($response == null ? 43 : $response.hashCode());
        String $schemeInchgFlag = this.getSchemeInchgFlag();
        result = result * 59 + ($schemeInchgFlag == null ? 43 : $schemeInchgFlag.hashCode());
        return result;
    }

    public String toString() {
        return "IRFCallbackEntity(serialNumber=" + this.getSerialNumber() + ", lastUpdated=" + String.valueOf(this.getLastUpdated()) + ", updatedUser=" + this.getUpdatedUser() + ", institutionCode=" + this.getInstitutionCode() + ", status=" + this.getStatus() + ", refSerNumber=" + this.getRefSerNumber() + ", request=" + this.getRequest() + ", response=" + this.getResponse() + ", schemeInchgFlag=" + this.getSchemeInchgFlag() + ")";
    }
}

