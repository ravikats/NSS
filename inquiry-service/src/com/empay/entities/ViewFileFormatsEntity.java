/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.entities.ViewFileFormatsEntity
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
import java.time.LocalDate;

@Entity
@Table(name="VW_FILE_FORMATS")
public class ViewFileFormatsEntity {
    @Id
    @Column(name="FOR_DESCRIPTION")
    private String description;
    @Column(name="UPL_FILE_NAME")
    private String fileName;
    @Column(name="UPL_BUSS_DATE")
    private LocalDate businessDate;
    @Column(name="UPL_TOT_ACCP_TXN_COUNT")
    private Integer totalAcceptedTxnCount;
    @Column(name="UPL_TOT_TXN_COUNT")
    private Integer totalTxnCount;
    @Column(name="upl_upload_status")
    private Integer status;
    @Column(name="UPL_REMARKS")
    private String remarks;

    public String getDescription() {
        return this.description;
    }

    public String getFileName() {
        return this.fileName;
    }

    public LocalDate getBusinessDate() {
        return this.businessDate;
    }

    public Integer getTotalAcceptedTxnCount() {
        return this.totalAcceptedTxnCount;
    }

    public Integer getTotalTxnCount() {
        return this.totalTxnCount;
    }

    public Integer getStatus() {
        return this.status;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setBusinessDate(LocalDate businessDate) {
        this.businessDate = businessDate;
    }

    public void setTotalAcceptedTxnCount(Integer totalAcceptedTxnCount) {
        this.totalAcceptedTxnCount = totalAcceptedTxnCount;
    }

    public void setTotalTxnCount(Integer totalTxnCount) {
        this.totalTxnCount = totalTxnCount;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ViewFileFormatsEntity)) {
            return false;
        }
        ViewFileFormatsEntity other = (ViewFileFormatsEntity)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        Integer this$totalAcceptedTxnCount = this.getTotalAcceptedTxnCount();
        Integer other$totalAcceptedTxnCount = other.getTotalAcceptedTxnCount();
        if (this$totalAcceptedTxnCount == null ? other$totalAcceptedTxnCount != null : !((Object)this$totalAcceptedTxnCount).equals(other$totalAcceptedTxnCount)) {
            return false;
        }
        Integer this$totalTxnCount = this.getTotalTxnCount();
        Integer other$totalTxnCount = other.getTotalTxnCount();
        if (this$totalTxnCount == null ? other$totalTxnCount != null : !((Object)this$totalTxnCount).equals(other$totalTxnCount)) {
            return false;
        }
        Integer this$status = this.getStatus();
        Integer other$status = other.getStatus();
        if (this$status == null ? other$status != null : !((Object)this$status).equals(other$status)) {
            return false;
        }
        String this$description = this.getDescription();
        String other$description = other.getDescription();
        if (this$description == null ? other$description != null : !this$description.equals(other$description)) {
            return false;
        }
        String this$fileName = this.getFileName();
        String other$fileName = other.getFileName();
        if (this$fileName == null ? other$fileName != null : !this$fileName.equals(other$fileName)) {
            return false;
        }
        LocalDate this$businessDate = this.getBusinessDate();
        LocalDate other$businessDate = other.getBusinessDate();
        if (this$businessDate == null ? other$businessDate != null : !((Object)this$businessDate).equals(other$businessDate)) {
            return false;
        }
        String this$remarks = this.getRemarks();
        String other$remarks = other.getRemarks();
        return !(this$remarks == null ? other$remarks != null : !this$remarks.equals(other$remarks));
    }

    protected boolean canEqual(Object other) {
        return other instanceof ViewFileFormatsEntity;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Integer $totalAcceptedTxnCount = this.getTotalAcceptedTxnCount();
        result = result * 59 + ($totalAcceptedTxnCount == null ? 43 : ((Object)$totalAcceptedTxnCount).hashCode());
        Integer $totalTxnCount = this.getTotalTxnCount();
        result = result * 59 + ($totalTxnCount == null ? 43 : ((Object)$totalTxnCount).hashCode());
        Integer $status = this.getStatus();
        result = result * 59 + ($status == null ? 43 : ((Object)$status).hashCode());
        String $description = this.getDescription();
        result = result * 59 + ($description == null ? 43 : $description.hashCode());
        String $fileName = this.getFileName();
        result = result * 59 + ($fileName == null ? 43 : $fileName.hashCode());
        LocalDate $businessDate = this.getBusinessDate();
        result = result * 59 + ($businessDate == null ? 43 : ((Object)$businessDate).hashCode());
        String $remarks = this.getRemarks();
        result = result * 59 + ($remarks == null ? 43 : $remarks.hashCode());
        return result;
    }

    public String toString() {
        return "ViewFileFormatsEntity(description=" + this.getDescription() + ", fileName=" + this.getFileName() + ", businessDate=" + String.valueOf(this.getBusinessDate()) + ", totalAcceptedTxnCount=" + this.getTotalAcceptedTxnCount() + ", totalTxnCount=" + this.getTotalTxnCount() + ", status=" + this.getStatus() + ", remarks=" + this.getRemarks() + ")";
    }
}

