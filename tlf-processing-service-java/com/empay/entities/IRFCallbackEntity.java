// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.entities;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;

@Entity
@Table(name = "IRF_CALLBACK")
public class IRFCallbackEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ICB_SER_NUMBER")
    private Integer serialNumber;
    @Column(name = "ICB_LAST_UPDATED", updatable = false)
    private LocalDateTime lastUpdated;
    @Column(name = "ICB_UPDATED_USER")
    private Integer updatedUser;
    @Column(name = "ICB_INS_CODE")
    private Integer institutionCode;
    @Column(name = "ICB_STATUS")
    private Character status;
    @Column(name = "ICB_REF_SER_NUMBER")
    private Integer refSerNumber;
    @Column(name = "ICB_REQUEST")
    private String request;
    @Column(name = "ICB_RESPONSE")
    private String response;
    @Column(name = "ICB_SCHEME_INCHG_FLAG")
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
    
    public void setSerialNumber(final Integer serialNumber) {
        this.serialNumber = serialNumber;
    }
    
    public void setLastUpdated(final LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
    
    public void setUpdatedUser(final Integer updatedUser) {
        this.updatedUser = updatedUser;
    }
    
    public void setInstitutionCode(final Integer institutionCode) {
        this.institutionCode = institutionCode;
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
    
    public void setSchemeInchgFlag(final String schemeInchgFlag) {
        this.schemeInchgFlag = schemeInchgFlag;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof IRFCallbackEntity)) {
            return false;
        }
        final IRFCallbackEntity other = (IRFCallbackEntity)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$serialNumber = this.getSerialNumber();
        final Object other$serialNumber = other.getSerialNumber();
        Label_0065: {
            if (this$serialNumber == null) {
                if (other$serialNumber == null) {
                    break Label_0065;
                }
            }
            else if (this$serialNumber.equals(other$serialNumber)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$updatedUser = this.getUpdatedUser();
        final Object other$updatedUser = other.getUpdatedUser();
        Label_0102: {
            if (this$updatedUser == null) {
                if (other$updatedUser == null) {
                    break Label_0102;
                }
            }
            else if (this$updatedUser.equals(other$updatedUser)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$institutionCode = this.getInstitutionCode();
        final Object other$institutionCode = other.getInstitutionCode();
        Label_0139: {
            if (this$institutionCode == null) {
                if (other$institutionCode == null) {
                    break Label_0139;
                }
            }
            else if (this$institutionCode.equals(other$institutionCode)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$status = this.getStatus();
        final Object other$status = other.getStatus();
        Label_0176: {
            if (this$status == null) {
                if (other$status == null) {
                    break Label_0176;
                }
            }
            else if (this$status.equals(other$status)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$refSerNumber = this.getRefSerNumber();
        final Object other$refSerNumber = other.getRefSerNumber();
        Label_0213: {
            if (this$refSerNumber == null) {
                if (other$refSerNumber == null) {
                    break Label_0213;
                }
            }
            else if (this$refSerNumber.equals(other$refSerNumber)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$lastUpdated = this.getLastUpdated();
        final Object other$lastUpdated = other.getLastUpdated();
        Label_0250: {
            if (this$lastUpdated == null) {
                if (other$lastUpdated == null) {
                    break Label_0250;
                }
            }
            else if (this$lastUpdated.equals(other$lastUpdated)) {
                break Label_0250;
            }
            return false;
        }
        final Object this$request = this.getRequest();
        final Object other$request = other.getRequest();
        Label_0287: {
            if (this$request == null) {
                if (other$request == null) {
                    break Label_0287;
                }
            }
            else if (this$request.equals(other$request)) {
                break Label_0287;
            }
            return false;
        }
        final Object this$response = this.getResponse();
        final Object other$response = other.getResponse();
        Label_0324: {
            if (this$response == null) {
                if (other$response == null) {
                    break Label_0324;
                }
            }
            else if (this$response.equals(other$response)) {
                break Label_0324;
            }
            return false;
        }
        final Object this$schemeInchgFlag = this.getSchemeInchgFlag();
        final Object other$schemeInchgFlag = other.getSchemeInchgFlag();
        if (this$schemeInchgFlag == null) {
            if (other$schemeInchgFlag == null) {
                return true;
            }
        }
        else if (this$schemeInchgFlag.equals(other$schemeInchgFlag)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof IRFCallbackEntity;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $serialNumber = this.getSerialNumber();
        result = result * 59 + (($serialNumber == null) ? 43 : $serialNumber.hashCode());
        final Object $updatedUser = this.getUpdatedUser();
        result = result * 59 + (($updatedUser == null) ? 43 : $updatedUser.hashCode());
        final Object $institutionCode = this.getInstitutionCode();
        result = result * 59 + (($institutionCode == null) ? 43 : $institutionCode.hashCode());
        final Object $status = this.getStatus();
        result = result * 59 + (($status == null) ? 43 : $status.hashCode());
        final Object $refSerNumber = this.getRefSerNumber();
        result = result * 59 + (($refSerNumber == null) ? 43 : $refSerNumber.hashCode());
        final Object $lastUpdated = this.getLastUpdated();
        result = result * 59 + (($lastUpdated == null) ? 43 : $lastUpdated.hashCode());
        final Object $request = this.getRequest();
        result = result * 59 + (($request == null) ? 43 : $request.hashCode());
        final Object $response = this.getResponse();
        result = result * 59 + (($response == null) ? 43 : $response.hashCode());
        final Object $schemeInchgFlag = this.getSchemeInchgFlag();
        result = result * 59 + (($schemeInchgFlag == null) ? 43 : $schemeInchgFlag.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "IRFCallbackEntity(serialNumber=" + this.getSerialNumber() + ", lastUpdated=" + String.valueOf(this.getLastUpdated()) + ", updatedUser=" + this.getUpdatedUser() + ", institutionCode=" + this.getInstitutionCode() + ", status=" + this.getStatus() + ", refSerNumber=" + this.getRefSerNumber() + ", request=" + this.getRequest() + ", response=" + this.getResponse() + ", schemeInchgFlag=" + this.getSchemeInchgFlag();
    }
}
