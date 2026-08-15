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
@Table(name = "REPORT_GENERATION_LOG")
public class ReportGenerationLogEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RGL_SER_NUMBER")
    private Integer serNumber;
    @Column(name = "RGL_LAST_UPDATED")
    private LocalDateTime lastUpdated;
    @Column(name = "RGL_UPDATED_USER")
    private Integer updatedUser;
    @Column(name = "RGL_INS_CODE")
    private Integer institutionCode;
    @Column(name = "RGL_PRJ_SER_NUMBER")
    private Integer prjSerNumber;
    @Column(name = "RGL_REPORT_DATE")
    private LocalDate reportDate;
    @Column(name = "RGL_REPORT_CODE")
    private String reportCode;
    @Column(name = "RGL_REPORT_NAME")
    private String reportName;
    @Column(name = "RGL_FILE_NAME")
    private String fileName;
    @Column(name = "RGL_REPORT_STATUS")
    private Integer reportStatus;
    
    ReportGenerationLogEntity(final Integer serNumber, final LocalDateTime lastUpdated, final Integer updatedUser, final Integer institutionCode, final Integer prjSerNumber, final LocalDate reportDate, final String reportCode, final String reportName, final String fileName, final Integer reportStatus) {
        this.serNumber = serNumber;
        this.lastUpdated = lastUpdated;
        this.updatedUser = updatedUser;
        this.institutionCode = institutionCode;
        this.prjSerNumber = prjSerNumber;
        this.reportDate = reportDate;
        this.reportCode = reportCode;
        this.reportName = reportName;
        this.fileName = fileName;
        this.reportStatus = reportStatus;
    }
    
    public static ReportGenerationLogEntityBuilder builder() {
        return new ReportGenerationLogEntityBuilder();
    }
    
    public Integer getSerNumber() {
        return this.serNumber;
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
    
    public Integer getPrjSerNumber() {
        return this.prjSerNumber;
    }
    
    public LocalDate getReportDate() {
        return this.reportDate;
    }
    
    public String getReportCode() {
        return this.reportCode;
    }
    
    public String getReportName() {
        return this.reportName;
    }
    
    public String getFileName() {
        return this.fileName;
    }
    
    public Integer getReportStatus() {
        return this.reportStatus;
    }
    
    public void setSerNumber(final Integer serNumber) {
        this.serNumber = serNumber;
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
    
    public void setPrjSerNumber(final Integer prjSerNumber) {
        this.prjSerNumber = prjSerNumber;
    }
    
    public void setReportDate(final LocalDate reportDate) {
        this.reportDate = reportDate;
    }
    
    public void setReportCode(final String reportCode) {
        this.reportCode = reportCode;
    }
    
    public void setReportName(final String reportName) {
        this.reportName = reportName;
    }
    
    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }
    
    public void setReportStatus(final Integer reportStatus) {
        this.reportStatus = reportStatus;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ReportGenerationLogEntity)) {
            return false;
        }
        final ReportGenerationLogEntity other = (ReportGenerationLogEntity)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$serNumber = this.getSerNumber();
        final Object other$serNumber = other.getSerNumber();
        Label_0065: {
            if (this$serNumber == null) {
                if (other$serNumber == null) {
                    break Label_0065;
                }
            }
            else if (this$serNumber.equals(other$serNumber)) {
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
        final Object this$prjSerNumber = this.getPrjSerNumber();
        final Object other$prjSerNumber = other.getPrjSerNumber();
        Label_0176: {
            if (this$prjSerNumber == null) {
                if (other$prjSerNumber == null) {
                    break Label_0176;
                }
            }
            else if (this$prjSerNumber.equals(other$prjSerNumber)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$reportStatus = this.getReportStatus();
        final Object other$reportStatus = other.getReportStatus();
        Label_0213: {
            if (this$reportStatus == null) {
                if (other$reportStatus == null) {
                    break Label_0213;
                }
            }
            else if (this$reportStatus.equals(other$reportStatus)) {
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
        final Object this$reportDate = this.getReportDate();
        final Object other$reportDate = other.getReportDate();
        Label_0287: {
            if (this$reportDate == null) {
                if (other$reportDate == null) {
                    break Label_0287;
                }
            }
            else if (this$reportDate.equals(other$reportDate)) {
                break Label_0287;
            }
            return false;
        }
        final Object this$reportCode = this.getReportCode();
        final Object other$reportCode = other.getReportCode();
        Label_0324: {
            if (this$reportCode == null) {
                if (other$reportCode == null) {
                    break Label_0324;
                }
            }
            else if (this$reportCode.equals(other$reportCode)) {
                break Label_0324;
            }
            return false;
        }
        final Object this$reportName = this.getReportName();
        final Object other$reportName = other.getReportName();
        Label_0361: {
            if (this$reportName == null) {
                if (other$reportName == null) {
                    break Label_0361;
                }
            }
            else if (this$reportName.equals(other$reportName)) {
                break Label_0361;
            }
            return false;
        }
        final Object this$fileName = this.getFileName();
        final Object other$fileName = other.getFileName();
        if (this$fileName == null) {
            if (other$fileName == null) {
                return true;
            }
        }
        else if (this$fileName.equals(other$fileName)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof ReportGenerationLogEntity;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $serNumber = this.getSerNumber();
        result = result * 59 + (($serNumber == null) ? 43 : $serNumber.hashCode());
        final Object $updatedUser = this.getUpdatedUser();
        result = result * 59 + (($updatedUser == null) ? 43 : $updatedUser.hashCode());
        final Object $institutionCode = this.getInstitutionCode();
        result = result * 59 + (($institutionCode == null) ? 43 : $institutionCode.hashCode());
        final Object $prjSerNumber = this.getPrjSerNumber();
        result = result * 59 + (($prjSerNumber == null) ? 43 : $prjSerNumber.hashCode());
        final Object $reportStatus = this.getReportStatus();
        result = result * 59 + (($reportStatus == null) ? 43 : $reportStatus.hashCode());
        final Object $lastUpdated = this.getLastUpdated();
        result = result * 59 + (($lastUpdated == null) ? 43 : $lastUpdated.hashCode());
        final Object $reportDate = this.getReportDate();
        result = result * 59 + (($reportDate == null) ? 43 : $reportDate.hashCode());
        final Object $reportCode = this.getReportCode();
        result = result * 59 + (($reportCode == null) ? 43 : $reportCode.hashCode());
        final Object $reportName = this.getReportName();
        result = result * 59 + (($reportName == null) ? 43 : $reportName.hashCode());
        final Object $fileName = this.getFileName();
        result = result * 59 + (($fileName == null) ? 43 : $fileName.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "ReportGenerationLogEntity(serNumber=" + this.getSerNumber() + ", lastUpdated=" + String.valueOf(this.getLastUpdated()) + ", updatedUser=" + this.getUpdatedUser() + ", institutionCode=" + this.getInstitutionCode() + ", prjSerNumber=" + this.getPrjSerNumber() + ", reportDate=" + String.valueOf(this.getReportDate()) + ", reportCode=" + this.getReportCode() + ", reportName=" + this.getReportName() + ", fileName=" + this.getFileName() + ", reportStatus=" + this.getReportStatus();
    }
    
    public static class ReportGenerationLogEntityBuilder
    {
        private Integer serNumber;
        private LocalDateTime lastUpdated;
        private Integer updatedUser;
        private Integer institutionCode;
        private Integer prjSerNumber;
        private LocalDate reportDate;
        private String reportCode;
        private String reportName;
        private String fileName;
        private Integer reportStatus;
        
        ReportGenerationLogEntityBuilder() {
        }
        
        public ReportGenerationLogEntityBuilder serNumber(final Integer serNumber) {
            this.serNumber = serNumber;
            return this;
        }
        
        public ReportGenerationLogEntityBuilder lastUpdated(final LocalDateTime lastUpdated) {
            this.lastUpdated = lastUpdated;
            return this;
        }
        
        public ReportGenerationLogEntityBuilder updatedUser(final Integer updatedUser) {
            this.updatedUser = updatedUser;
            return this;
        }
        
        public ReportGenerationLogEntityBuilder institutionCode(final Integer institutionCode) {
            this.institutionCode = institutionCode;
            return this;
        }
        
        public ReportGenerationLogEntityBuilder prjSerNumber(final Integer prjSerNumber) {
            this.prjSerNumber = prjSerNumber;
            return this;
        }
        
        public ReportGenerationLogEntityBuilder reportDate(final LocalDate reportDate) {
            this.reportDate = reportDate;
            return this;
        }
        
        public ReportGenerationLogEntityBuilder reportCode(final String reportCode) {
            this.reportCode = reportCode;
            return this;
        }
        
        public ReportGenerationLogEntityBuilder reportName(final String reportName) {
            this.reportName = reportName;
            return this;
        }
        
        public ReportGenerationLogEntityBuilder fileName(final String fileName) {
            this.fileName = fileName;
            return this;
        }
        
        public ReportGenerationLogEntityBuilder reportStatus(final Integer reportStatus) {
            this.reportStatus = reportStatus;
            return this;
        }
        
        public ReportGenerationLogEntity build() {
            return new ReportGenerationLogEntity(this.serNumber, this.lastUpdated, this.updatedUser, this.institutionCode, this.prjSerNumber, this.reportDate, this.reportCode, this.reportName, this.fileName, this.reportStatus);
        }
        
        @Override
        public String toString() {
            return "ReportGenerationLogEntity.ReportGenerationLogEntityBuilder(serNumber=" + this.serNumber + ", lastUpdated=" + String.valueOf(this.lastUpdated) + ", updatedUser=" + this.updatedUser + ", institutionCode=" + this.institutionCode + ", prjSerNumber=" + this.prjSerNumber + ", reportDate=" + String.valueOf(this.reportDate) + ", reportCode=" + this.reportCode + ", reportName=" + this.reportName + ", fileName=" + this.fileName + ", reportStatus=" + this.reportStatus;
        }
    }
}
