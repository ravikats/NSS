// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;

@Entity
@Table(name = "REJECTED_TRANSACTIONS")
public class RejectedTransactionEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RTR_SER_NUMBER")
    private Integer serialNumber;
    @Column(name = "RTR_LAST_UPDATED")
    private LocalDateTime lastUpdated;
    @Column(name = "RTR_UPDATED_USER")
    private Integer user;
    @Column(name = "RTR_INS_CODE")
    private Integer insCode;
    @Column(name = "RTR_INT_CODE")
    private Integer intCode;
    @Column(name = "RTR_PRJ_SER_NUMBER")
    private Integer jobNumber;
    @Column(name = "RTR_FILE_NAME")
    private String fileName;
    @Column(name = "RTR_RET_REF_NUMBER")
    private String rrn;
    @Column(name = "RTR_AMOUNT")
    private Double amount;
    @Column(name = "RTR_TXN_DATE_TIME")
    private LocalDateTime txnDateTime;
    @Column(name = "RTR_MID")
    private String mid;
    @Column(name = "RTR_TID")
    private String tid;
    @Column(name = "RTR_REJECT_REASON")
    private String rejReason;
    @Column(name = "RTR_BUSS_DATE")
    private LocalDate bussDate;
    
    public Integer getSerialNumber() {
        return this.serialNumber;
    }
    
    public LocalDateTime getLastUpdated() {
        return this.lastUpdated;
    }
    
    public Integer getUser() {
        return this.user;
    }
    
    public Integer getInsCode() {
        return this.insCode;
    }
    
    public Integer getIntCode() {
        return this.intCode;
    }
    
    public Integer getJobNumber() {
        return this.jobNumber;
    }
    
    public String getFileName() {
        return this.fileName;
    }
    
    public String getRrn() {
        return this.rrn;
    }
    
    public Double getAmount() {
        return this.amount;
    }
    
    public LocalDateTime getTxnDateTime() {
        return this.txnDateTime;
    }
    
    public String getMid() {
        return this.mid;
    }
    
    public String getTid() {
        return this.tid;
    }
    
    public String getRejReason() {
        return this.rejReason;
    }
    
    public LocalDate getBussDate() {
        return this.bussDate;
    }
    
    public void setSerialNumber(final Integer serialNumber) {
        this.serialNumber = serialNumber;
    }
    
    public void setLastUpdated(final LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
    
    public void setUser(final Integer user) {
        this.user = user;
    }
    
    public void setInsCode(final Integer insCode) {
        this.insCode = insCode;
    }
    
    public void setIntCode(final Integer intCode) {
        this.intCode = intCode;
    }
    
    public void setJobNumber(final Integer jobNumber) {
        this.jobNumber = jobNumber;
    }
    
    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }
    
    public void setRrn(final String rrn) {
        this.rrn = rrn;
    }
    
    public void setAmount(final Double amount) {
        this.amount = amount;
    }
    
    public void setTxnDateTime(final LocalDateTime txnDateTime) {
        this.txnDateTime = txnDateTime;
    }
    
    public void setMid(final String mid) {
        this.mid = mid;
    }
    
    public void setTid(final String tid) {
        this.tid = tid;
    }
    
    public void setRejReason(final String rejReason) {
        this.rejReason = rejReason;
    }
    
    public void setBussDate(final LocalDate bussDate) {
        this.bussDate = bussDate;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof RejectedTransactionEntity)) {
            return false;
        }
        final RejectedTransactionEntity other = (RejectedTransactionEntity)o;
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
        final Object this$user = this.getUser();
        final Object other$user = other.getUser();
        Label_0102: {
            if (this$user == null) {
                if (other$user == null) {
                    break Label_0102;
                }
            }
            else if (this$user.equals(other$user)) {
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
        final Object this$intCode = this.getIntCode();
        final Object other$intCode = other.getIntCode();
        Label_0176: {
            if (this$intCode == null) {
                if (other$intCode == null) {
                    break Label_0176;
                }
            }
            else if (this$intCode.equals(other$intCode)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$jobNumber = this.getJobNumber();
        final Object other$jobNumber = other.getJobNumber();
        Label_0213: {
            if (this$jobNumber == null) {
                if (other$jobNumber == null) {
                    break Label_0213;
                }
            }
            else if (this$jobNumber.equals(other$jobNumber)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$amount = this.getAmount();
        final Object other$amount = other.getAmount();
        Label_0250: {
            if (this$amount == null) {
                if (other$amount == null) {
                    break Label_0250;
                }
            }
            else if (this$amount.equals(other$amount)) {
                break Label_0250;
            }
            return false;
        }
        final Object this$lastUpdated = this.getLastUpdated();
        final Object other$lastUpdated = other.getLastUpdated();
        Label_0287: {
            if (this$lastUpdated == null) {
                if (other$lastUpdated == null) {
                    break Label_0287;
                }
            }
            else if (this$lastUpdated.equals(other$lastUpdated)) {
                break Label_0287;
            }
            return false;
        }
        final Object this$fileName = this.getFileName();
        final Object other$fileName = other.getFileName();
        Label_0324: {
            if (this$fileName == null) {
                if (other$fileName == null) {
                    break Label_0324;
                }
            }
            else if (this$fileName.equals(other$fileName)) {
                break Label_0324;
            }
            return false;
        }
        final Object this$rrn = this.getRrn();
        final Object other$rrn = other.getRrn();
        Label_0361: {
            if (this$rrn == null) {
                if (other$rrn == null) {
                    break Label_0361;
                }
            }
            else if (this$rrn.equals(other$rrn)) {
                break Label_0361;
            }
            return false;
        }
        final Object this$txnDateTime = this.getTxnDateTime();
        final Object other$txnDateTime = other.getTxnDateTime();
        Label_0398: {
            if (this$txnDateTime == null) {
                if (other$txnDateTime == null) {
                    break Label_0398;
                }
            }
            else if (this$txnDateTime.equals(other$txnDateTime)) {
                break Label_0398;
            }
            return false;
        }
        final Object this$mid = this.getMid();
        final Object other$mid = other.getMid();
        Label_0435: {
            if (this$mid == null) {
                if (other$mid == null) {
                    break Label_0435;
                }
            }
            else if (this$mid.equals(other$mid)) {
                break Label_0435;
            }
            return false;
        }
        final Object this$tid = this.getTid();
        final Object other$tid = other.getTid();
        Label_0472: {
            if (this$tid == null) {
                if (other$tid == null) {
                    break Label_0472;
                }
            }
            else if (this$tid.equals(other$tid)) {
                break Label_0472;
            }
            return false;
        }
        final Object this$rejReason = this.getRejReason();
        final Object other$rejReason = other.getRejReason();
        Label_0509: {
            if (this$rejReason == null) {
                if (other$rejReason == null) {
                    break Label_0509;
                }
            }
            else if (this$rejReason.equals(other$rejReason)) {
                break Label_0509;
            }
            return false;
        }
        final Object this$bussDate = this.getBussDate();
        final Object other$bussDate = other.getBussDate();
        if (this$bussDate == null) {
            if (other$bussDate == null) {
                return true;
            }
        }
        else if (this$bussDate.equals(other$bussDate)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof RejectedTransactionEntity;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $serialNumber = this.getSerialNumber();
        result = result * 59 + (($serialNumber == null) ? 43 : $serialNumber.hashCode());
        final Object $user = this.getUser();
        result = result * 59 + (($user == null) ? 43 : $user.hashCode());
        final Object $insCode = this.getInsCode();
        result = result * 59 + (($insCode == null) ? 43 : $insCode.hashCode());
        final Object $intCode = this.getIntCode();
        result = result * 59 + (($intCode == null) ? 43 : $intCode.hashCode());
        final Object $jobNumber = this.getJobNumber();
        result = result * 59 + (($jobNumber == null) ? 43 : $jobNumber.hashCode());
        final Object $amount = this.getAmount();
        result = result * 59 + (($amount == null) ? 43 : $amount.hashCode());
        final Object $lastUpdated = this.getLastUpdated();
        result = result * 59 + (($lastUpdated == null) ? 43 : $lastUpdated.hashCode());
        final Object $fileName = this.getFileName();
        result = result * 59 + (($fileName == null) ? 43 : $fileName.hashCode());
        final Object $rrn = this.getRrn();
        result = result * 59 + (($rrn == null) ? 43 : $rrn.hashCode());
        final Object $txnDateTime = this.getTxnDateTime();
        result = result * 59 + (($txnDateTime == null) ? 43 : $txnDateTime.hashCode());
        final Object $mid = this.getMid();
        result = result * 59 + (($mid == null) ? 43 : $mid.hashCode());
        final Object $tid = this.getTid();
        result = result * 59 + (($tid == null) ? 43 : $tid.hashCode());
        final Object $rejReason = this.getRejReason();
        result = result * 59 + (($rejReason == null) ? 43 : $rejReason.hashCode());
        final Object $bussDate = this.getBussDate();
        result = result * 59 + (($bussDate == null) ? 43 : $bussDate.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "RejectedTransactionEntity(serialNumber=" + this.getSerialNumber() + ", lastUpdated=" + String.valueOf(this.getLastUpdated()) + ", user=" + this.getUser() + ", insCode=" + this.getInsCode() + ", intCode=" + this.getIntCode() + ", jobNumber=" + this.getJobNumber() + ", fileName=" + this.getFileName() + ", rrn=" + this.getRrn() + ", amount=" + this.getAmount() + ", txnDateTime=" + String.valueOf(this.getTxnDateTime()) + ", mid=" + this.getMid() + ", tid=" + this.getTid() + ", rejReason=" + this.getRejReason() + ", bussDate=" + String.valueOf(this.getBussDate());
    }
}
