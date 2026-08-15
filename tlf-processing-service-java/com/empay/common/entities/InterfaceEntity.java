// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.common.entities;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;

@Entity
@Table(name = "INTERFACES")
public class InterfaceEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "INT_CODE")
    private Integer intCode;
    @Column(name = "INT_LAST_UPDATED")
    private LocalDateTime lastUpdated;
    @Column(name = "INT_UPDATED_USER")
    private Integer updatedUser;
    @Column(name = "INT_INS_CODE")
    private Integer insCode;
    @Column(name = "INT_NAME")
    private String intName;
    @Column(name = "INT_SHORT_NAME")
    private String intShortName;
    @Column(name = "INT_CATEGORY")
    private String intCategory;
    
    public Integer getIntCode() {
        return this.intCode;
    }
    
    public LocalDateTime getLastUpdated() {
        return this.lastUpdated;
    }
    
    public Integer getUpdatedUser() {
        return this.updatedUser;
    }
    
    public Integer getInsCode() {
        return this.insCode;
    }
    
    public String getIntName() {
        return this.intName;
    }
    
    public String getIntShortName() {
        return this.intShortName;
    }
    
    public String getIntCategory() {
        return this.intCategory;
    }
    
    public void setIntCode(final Integer intCode) {
        this.intCode = intCode;
    }
    
    public void setLastUpdated(final LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
    
    public void setUpdatedUser(final Integer updatedUser) {
        this.updatedUser = updatedUser;
    }
    
    public void setInsCode(final Integer insCode) {
        this.insCode = insCode;
    }
    
    public void setIntName(final String intName) {
        this.intName = intName;
    }
    
    public void setIntShortName(final String intShortName) {
        this.intShortName = intShortName;
    }
    
    public void setIntCategory(final String intCategory) {
        this.intCategory = intCategory;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof InterfaceEntity)) {
            return false;
        }
        final InterfaceEntity other = (InterfaceEntity)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$intCode = this.getIntCode();
        final Object other$intCode = other.getIntCode();
        Label_0065: {
            if (this$intCode == null) {
                if (other$intCode == null) {
                    break Label_0065;
                }
            }
            else if (this$intCode.equals(other$intCode)) {
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
        final Object this$insCode = this.getInsCode();
        final Object other$insCode = other.getInsCode();
        Label_0139: {
            if (this$insCode == null) {
                if (other$insCode == null) {
                    break Label_0139;
                }
            }
            else if (this$insCode.equals(other$insCode)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$lastUpdated = this.getLastUpdated();
        final Object other$lastUpdated = other.getLastUpdated();
        Label_0176: {
            if (this$lastUpdated == null) {
                if (other$lastUpdated == null) {
                    break Label_0176;
                }
            }
            else if (this$lastUpdated.equals(other$lastUpdated)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$intName = this.getIntName();
        final Object other$intName = other.getIntName();
        Label_0213: {
            if (this$intName == null) {
                if (other$intName == null) {
                    break Label_0213;
                }
            }
            else if (this$intName.equals(other$intName)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$intShortName = this.getIntShortName();
        final Object other$intShortName = other.getIntShortName();
        Label_0250: {
            if (this$intShortName == null) {
                if (other$intShortName == null) {
                    break Label_0250;
                }
            }
            else if (this$intShortName.equals(other$intShortName)) {
                break Label_0250;
            }
            return false;
        }
        final Object this$intCategory = this.getIntCategory();
        final Object other$intCategory = other.getIntCategory();
        if (this$intCategory == null) {
            if (other$intCategory == null) {
                return true;
            }
        }
        else if (this$intCategory.equals(other$intCategory)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof InterfaceEntity;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $intCode = this.getIntCode();
        result = result * 59 + (($intCode == null) ? 43 : $intCode.hashCode());
        final Object $updatedUser = this.getUpdatedUser();
        result = result * 59 + (($updatedUser == null) ? 43 : $updatedUser.hashCode());
        final Object $insCode = this.getInsCode();
        result = result * 59 + (($insCode == null) ? 43 : $insCode.hashCode());
        final Object $lastUpdated = this.getLastUpdated();
        result = result * 59 + (($lastUpdated == null) ? 43 : $lastUpdated.hashCode());
        final Object $intName = this.getIntName();
        result = result * 59 + (($intName == null) ? 43 : $intName.hashCode());
        final Object $intShortName = this.getIntShortName();
        result = result * 59 + (($intShortName == null) ? 43 : $intShortName.hashCode());
        final Object $intCategory = this.getIntCategory();
        result = result * 59 + (($intCategory == null) ? 43 : $intCategory.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "InterfaceEntity(intCode=" + this.getIntCode() + ", lastUpdated=" + String.valueOf(this.getLastUpdated()) + ", updatedUser=" + this.getUpdatedUser() + ", insCode=" + this.getInsCode() + ", intName=" + this.getIntName() + ", intShortName=" + this.getIntShortName() + ", intCategory=" + this.getIntCategory();
    }
}
