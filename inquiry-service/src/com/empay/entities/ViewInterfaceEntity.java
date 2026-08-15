/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.entities.ViewInterfaceEntity
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
import java.time.LocalDateTime;

@Entity
@Table(name="VW_INTERFACE")
public class ViewInterfaceEntity {
    @Id
    @Column(name="UPL_FILE_NAME")
    private String fileName;
    @Column(name="INT_NAME")
    private String interfaceName;
    @Column(name="FOR_DESCRIPTION")
    private String formatDesc;
    @Column(name="PRJ_START_TIME")
    private LocalDateTime startTime;
    @Column(name="PRJ_END_TIME")
    private LocalDateTime endTime;
    @Column(name="UPL_TOT_TXN_COUNT")
    private Integer totalCount;
    @Column(name="UPL_TOT_ACCP_TXN_COUNT")
    private Integer acceptCount;
    @Column(name="UPL_BUSS_DATE")
    private LocalDate bussDate;
    @Column(name="UPL_PROC_DATE")
    private LocalDate processingDate;
    @Column(name="UPL_UPLOAD_STATUS")
    private Integer uploadStatus;

    public String getFileName() {
        return this.fileName;
    }

    public String getInterfaceName() {
        return this.interfaceName;
    }

    public String getFormatDesc() {
        return this.formatDesc;
    }

    public LocalDateTime getStartTime() {
        return this.startTime;
    }

    public LocalDateTime getEndTime() {
        return this.endTime;
    }

    public Integer getTotalCount() {
        return this.totalCount;
    }

    public Integer getAcceptCount() {
        return this.acceptCount;
    }

    public LocalDate getBussDate() {
        return this.bussDate;
    }

    public LocalDate getProcessingDate() {
        return this.processingDate;
    }

    public Integer getUploadStatus() {
        return this.uploadStatus;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public void setFormatDesc(String formatDesc) {
        this.formatDesc = formatDesc;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public void setAcceptCount(Integer acceptCount) {
        this.acceptCount = acceptCount;
    }

    public void setBussDate(LocalDate bussDate) {
        this.bussDate = bussDate;
    }

    public void setProcessingDate(LocalDate processingDate) {
        this.processingDate = processingDate;
    }

    public void setUploadStatus(Integer uploadStatus) {
        this.uploadStatus = uploadStatus;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ViewInterfaceEntity)) {
            return false;
        }
        ViewInterfaceEntity other = (ViewInterfaceEntity)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        Integer this$totalCount = this.getTotalCount();
        Integer other$totalCount = other.getTotalCount();
        if (this$totalCount == null ? other$totalCount != null : !((Object)this$totalCount).equals(other$totalCount)) {
            return false;
        }
        Integer this$acceptCount = this.getAcceptCount();
        Integer other$acceptCount = other.getAcceptCount();
        if (this$acceptCount == null ? other$acceptCount != null : !((Object)this$acceptCount).equals(other$acceptCount)) {
            return false;
        }
        Integer this$uploadStatus = this.getUploadStatus();
        Integer other$uploadStatus = other.getUploadStatus();
        if (this$uploadStatus == null ? other$uploadStatus != null : !((Object)this$uploadStatus).equals(other$uploadStatus)) {
            return false;
        }
        String this$fileName = this.getFileName();
        String other$fileName = other.getFileName();
        if (this$fileName == null ? other$fileName != null : !this$fileName.equals(other$fileName)) {
            return false;
        }
        String this$interfaceName = this.getInterfaceName();
        String other$interfaceName = other.getInterfaceName();
        if (this$interfaceName == null ? other$interfaceName != null : !this$interfaceName.equals(other$interfaceName)) {
            return false;
        }
        String this$formatDesc = this.getFormatDesc();
        String other$formatDesc = other.getFormatDesc();
        if (this$formatDesc == null ? other$formatDesc != null : !this$formatDesc.equals(other$formatDesc)) {
            return false;
        }
        LocalDateTime this$startTime = this.getStartTime();
        LocalDateTime other$startTime = other.getStartTime();
        if (this$startTime == null ? other$startTime != null : !((Object)this$startTime).equals(other$startTime)) {
            return false;
        }
        LocalDateTime this$endTime = this.getEndTime();
        LocalDateTime other$endTime = other.getEndTime();
        if (this$endTime == null ? other$endTime != null : !((Object)this$endTime).equals(other$endTime)) {
            return false;
        }
        LocalDate this$bussDate = this.getBussDate();
        LocalDate other$bussDate = other.getBussDate();
        if (this$bussDate == null ? other$bussDate != null : !((Object)this$bussDate).equals(other$bussDate)) {
            return false;
        }
        LocalDate this$processingDate = this.getProcessingDate();
        LocalDate other$processingDate = other.getProcessingDate();
        return !(this$processingDate == null ? other$processingDate != null : !((Object)this$processingDate).equals(other$processingDate));
    }

    protected boolean canEqual(Object other) {
        return other instanceof ViewInterfaceEntity;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Integer $totalCount = this.getTotalCount();
        result = result * 59 + ($totalCount == null ? 43 : ((Object)$totalCount).hashCode());
        Integer $acceptCount = this.getAcceptCount();
        result = result * 59 + ($acceptCount == null ? 43 : ((Object)$acceptCount).hashCode());
        Integer $uploadStatus = this.getUploadStatus();
        result = result * 59 + ($uploadStatus == null ? 43 : ((Object)$uploadStatus).hashCode());
        String $fileName = this.getFileName();
        result = result * 59 + ($fileName == null ? 43 : $fileName.hashCode());
        String $interfaceName = this.getInterfaceName();
        result = result * 59 + ($interfaceName == null ? 43 : $interfaceName.hashCode());
        String $formatDesc = this.getFormatDesc();
        result = result * 59 + ($formatDesc == null ? 43 : $formatDesc.hashCode());
        LocalDateTime $startTime = this.getStartTime();
        result = result * 59 + ($startTime == null ? 43 : ((Object)$startTime).hashCode());
        LocalDateTime $endTime = this.getEndTime();
        result = result * 59 + ($endTime == null ? 43 : ((Object)$endTime).hashCode());
        LocalDate $bussDate = this.getBussDate();
        result = result * 59 + ($bussDate == null ? 43 : ((Object)$bussDate).hashCode());
        LocalDate $processingDate = this.getProcessingDate();
        result = result * 59 + ($processingDate == null ? 43 : ((Object)$processingDate).hashCode());
        return result;
    }

    public String toString() {
        return "ViewInterfaceEntity(fileName=" + this.getFileName() + ", interfaceName=" + this.getInterfaceName() + ", formatDesc=" + this.getFormatDesc() + ", startTime=" + String.valueOf(this.getStartTime()) + ", endTime=" + String.valueOf(this.getEndTime()) + ", totalCount=" + this.getTotalCount() + ", acceptCount=" + this.getAcceptCount() + ", bussDate=" + String.valueOf(this.getBussDate()) + ", processingDate=" + String.valueOf(this.getProcessingDate()) + ", uploadStatus=" + this.getUploadStatus() + ")";
    }
}

