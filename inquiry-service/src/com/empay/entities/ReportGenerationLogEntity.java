/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.entities.ReportGenerationLogEntity
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
import java.util.Date;

@Entity
@Table(name="REPORT_GENERATION_LOG")
public class ReportGenerationLogEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="RGL_SER_NUMBER")
    private int serialNumber;
    @Column(name="RGL_LAST_UPDATED")
    private Date lastupdated;
    @Column(name="RGL_UPDATED_USER")
    private Integer updatedUser;
    @Column(name="RGL_INS_CODE")
    private Integer institutionCode;
    @Column(name="RGL_PRJ_SER_NUMBER")
    private Integer jobNumber;
    @Column(name="RGL_REPORT_DATE")
    private Date reportDate;
    @Column(name="RGL_REPORT_NAME")
    private String reportName;
    @Column(name="RGL_REPORT_CODE")
    private String reportCode;
    @Column(name="RGL_FILE_NAME")
    private String fileName;
    @Column(name="RGL_REPORT_STATUS")
    private String status;

    public int getSerialNumber() {
        return this.serialNumber;
    }

    public Date getLastupdated() {
        return this.lastupdated;
    }

    public Integer getUpdatedUser() {
        return this.updatedUser;
    }

    public Integer getInstitutionCode() {
        return this.institutionCode;
    }

    public Integer getJobNumber() {
        return this.jobNumber;
    }

    public Date getReportDate() {
        return this.reportDate;
    }

    public String getReportName() {
        return this.reportName;
    }

    public String getReportCode() {
        return this.reportCode;
    }

    public String getFileName() {
        return this.fileName;
    }

    public String getStatus() {
        return this.status;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setLastupdated(Date lastupdated) {
        this.lastupdated = lastupdated;
    }

    public void setUpdatedUser(Integer updatedUser) {
        this.updatedUser = updatedUser;
    }

    public void setInstitutionCode(Integer institutionCode) {
        this.institutionCode = institutionCode;
    }

    public void setJobNumber(Integer jobNumber) {
        this.jobNumber = jobNumber;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public void setReportCode(String reportCode) {
        this.reportCode = reportCode;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ReportGenerationLogEntity)) {
            return false;
        }
        ReportGenerationLogEntity other = (ReportGenerationLogEntity)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        if (this.getSerialNumber() != other.getSerialNumber()) {
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
        Integer this$jobNumber = this.getJobNumber();
        Integer other$jobNumber = other.getJobNumber();
        if (this$jobNumber == null ? other$jobNumber != null : !((Object)this$jobNumber).equals(other$jobNumber)) {
            return false;
        }
        Date this$lastupdated = this.getLastupdated();
        Date other$lastupdated = other.getLastupdated();
        if (this$lastupdated == null ? other$lastupdated != null : !((Object)this$lastupdated).equals(other$lastupdated)) {
            return false;
        }
        Date this$reportDate = this.getReportDate();
        Date other$reportDate = other.getReportDate();
        if (this$reportDate == null ? other$reportDate != null : !((Object)this$reportDate).equals(other$reportDate)) {
            return false;
        }
        String this$reportName = this.getReportName();
        String other$reportName = other.getReportName();
        if (this$reportName == null ? other$reportName != null : !this$reportName.equals(other$reportName)) {
            return false;
        }
        String this$reportCode = this.getReportCode();
        String other$reportCode = other.getReportCode();
        if (this$reportCode == null ? other$reportCode != null : !this$reportCode.equals(other$reportCode)) {
            return false;
        }
        String this$fileName = this.getFileName();
        String other$fileName = other.getFileName();
        if (this$fileName == null ? other$fileName != null : !this$fileName.equals(other$fileName)) {
            return false;
        }
        String this$status = this.getStatus();
        String other$status = other.getStatus();
        return !(this$status == null ? other$status != null : !this$status.equals(other$status));
    }

    protected boolean canEqual(Object other) {
        return other instanceof ReportGenerationLogEntity;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + this.getSerialNumber();
        Integer $updatedUser = this.getUpdatedUser();
        result = result * 59 + ($updatedUser == null ? 43 : ((Object)$updatedUser).hashCode());
        Integer $institutionCode = this.getInstitutionCode();
        result = result * 59 + ($institutionCode == null ? 43 : ((Object)$institutionCode).hashCode());
        Integer $jobNumber = this.getJobNumber();
        result = result * 59 + ($jobNumber == null ? 43 : ((Object)$jobNumber).hashCode());
        Date $lastupdated = this.getLastupdated();
        result = result * 59 + ($lastupdated == null ? 43 : ((Object)$lastupdated).hashCode());
        Date $reportDate = this.getReportDate();
        result = result * 59 + ($reportDate == null ? 43 : ((Object)$reportDate).hashCode());
        String $reportName = this.getReportName();
        result = result * 59 + ($reportName == null ? 43 : $reportName.hashCode());
        String $reportCode = this.getReportCode();
        result = result * 59 + ($reportCode == null ? 43 : $reportCode.hashCode());
        String $fileName = this.getFileName();
        result = result * 59 + ($fileName == null ? 43 : $fileName.hashCode());
        String $status = this.getStatus();
        result = result * 59 + ($status == null ? 43 : $status.hashCode());
        return result;
    }

    public String toString() {
        return "ReportGenerationLogEntity(serialNumber=" + this.getSerialNumber() + ", lastupdated=" + String.valueOf(this.getLastupdated()) + ", updatedUser=" + this.getUpdatedUser() + ", institutionCode=" + this.getInstitutionCode() + ", jobNumber=" + this.getJobNumber() + ", reportDate=" + String.valueOf(this.getReportDate()) + ", reportName=" + this.getReportName() + ", reportCode=" + this.getReportCode() + ", fileName=" + this.getFileName() + ", status=" + this.getStatus() + ")";
    }
}

