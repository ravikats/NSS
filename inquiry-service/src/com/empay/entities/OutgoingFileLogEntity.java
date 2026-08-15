/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.entities.FileFormatsEntity
 *  com.empay.entities.OutgoingFileLogEntity
 *  jakarta.persistence.Column
 *  jakarta.persistence.Entity
 *  jakarta.persistence.GeneratedValue
 *  jakarta.persistence.GenerationType
 *  jakarta.persistence.Id
 *  jakarta.persistence.JoinColumn
 *  jakarta.persistence.ManyToOne
 *  jakarta.persistence.Table
 */
package com.empay.entities;

import com.empay.entities.FileFormatsEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="OUT_FILE_LOG")
public class OutgoingFileLogEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="OFL_SER_NUMBER")
    private Integer serialNumber;
    @Column(name="OFL_LAST_UPDATED")
    private LocalDateTime lastUpdated;
    @Column(name="OFL_UPDATED_USER")
    private Integer updatedUser;
    @Column(name="OFL_INS_CODE")
    private Integer insCode;
    @Column(name="OFL_INT_CODE")
    private Integer intCode;
    @Column(name="OFL_GENERATE_STATUS")
    private Integer generateStatus;
    @Column(name="OFL_FILE_NAME")
    private String fileName;
    @Column(name="OFL_BUSS_DATE ")
    private LocalDate businessDate;
    @Column(name=" OFL_GNERATE_DATE")
    private LocalDateTime generatedDate;
    @ManyToOne
    @JoinColumn(name="OFL_FOR_CODE", referencedColumnName="FOR_CODE")
    private FileFormatsEntity fileFormats;
    @Column(name="OFL_FILE_ID")
    private String fileId;

    public Integer getSerialNumber() {
        return this.serialNumber;
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

    public Integer getIntCode() {
        return this.intCode;
    }

    public Integer getGenerateStatus() {
        return this.generateStatus;
    }

    public String getFileName() {
        return this.fileName;
    }

    public LocalDate getBusinessDate() {
        return this.businessDate;
    }

    public LocalDateTime getGeneratedDate() {
        return this.generatedDate;
    }

    public FileFormatsEntity getFileFormats() {
        return this.fileFormats;
    }

    public String getFileId() {
        return this.fileId;
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

    public void setInsCode(Integer insCode) {
        this.insCode = insCode;
    }

    public void setIntCode(Integer intCode) {
        this.intCode = intCode;
    }

    public void setGenerateStatus(Integer generateStatus) {
        this.generateStatus = generateStatus;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setBusinessDate(LocalDate businessDate) {
        this.businessDate = businessDate;
    }

    public void setGeneratedDate(LocalDateTime generatedDate) {
        this.generatedDate = generatedDate;
    }

    public void setFileFormats(FileFormatsEntity fileFormats) {
        this.fileFormats = fileFormats;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof OutgoingFileLogEntity)) {
            return false;
        }
        OutgoingFileLogEntity other = (OutgoingFileLogEntity)o;
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
        Integer this$insCode = this.getInsCode();
        Integer other$insCode = other.getInsCode();
        if (this$insCode == null ? other$insCode != null : !((Object)this$insCode).equals(other$insCode)) {
            return false;
        }
        Integer this$intCode = this.getIntCode();
        Integer other$intCode = other.getIntCode();
        if (this$intCode == null ? other$intCode != null : !((Object)this$intCode).equals(other$intCode)) {
            return false;
        }
        Integer this$generateStatus = this.getGenerateStatus();
        Integer other$generateStatus = other.getGenerateStatus();
        if (this$generateStatus == null ? other$generateStatus != null : !((Object)this$generateStatus).equals(other$generateStatus)) {
            return false;
        }
        LocalDateTime this$lastUpdated = this.getLastUpdated();
        LocalDateTime other$lastUpdated = other.getLastUpdated();
        if (this$lastUpdated == null ? other$lastUpdated != null : !((Object)this$lastUpdated).equals(other$lastUpdated)) {
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
        LocalDateTime this$generatedDate = this.getGeneratedDate();
        LocalDateTime other$generatedDate = other.getGeneratedDate();
        if (this$generatedDate == null ? other$generatedDate != null : !((Object)this$generatedDate).equals(other$generatedDate)) {
            return false;
        }
        FileFormatsEntity this$fileFormats = this.getFileFormats();
        FileFormatsEntity other$fileFormats = other.getFileFormats();
        if (this$fileFormats == null ? other$fileFormats != null : !this$fileFormats.equals(other$fileFormats)) {
            return false;
        }
        String this$fileId = this.getFileId();
        String other$fileId = other.getFileId();
        return !(this$fileId == null ? other$fileId != null : !this$fileId.equals(other$fileId));
    }

    protected boolean canEqual(Object other) {
        return other instanceof OutgoingFileLogEntity;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Integer $serialNumber = this.getSerialNumber();
        result = result * 59 + ($serialNumber == null ? 43 : ((Object)$serialNumber).hashCode());
        Integer $updatedUser = this.getUpdatedUser();
        result = result * 59 + ($updatedUser == null ? 43 : ((Object)$updatedUser).hashCode());
        Integer $insCode = this.getInsCode();
        result = result * 59 + ($insCode == null ? 43 : ((Object)$insCode).hashCode());
        Integer $intCode = this.getIntCode();
        result = result * 59 + ($intCode == null ? 43 : ((Object)$intCode).hashCode());
        Integer $generateStatus = this.getGenerateStatus();
        result = result * 59 + ($generateStatus == null ? 43 : ((Object)$generateStatus).hashCode());
        LocalDateTime $lastUpdated = this.getLastUpdated();
        result = result * 59 + ($lastUpdated == null ? 43 : ((Object)$lastUpdated).hashCode());
        String $fileName = this.getFileName();
        result = result * 59 + ($fileName == null ? 43 : $fileName.hashCode());
        LocalDate $businessDate = this.getBusinessDate();
        result = result * 59 + ($businessDate == null ? 43 : ((Object)$businessDate).hashCode());
        LocalDateTime $generatedDate = this.getGeneratedDate();
        result = result * 59 + ($generatedDate == null ? 43 : ((Object)$generatedDate).hashCode());
        FileFormatsEntity $fileFormats = this.getFileFormats();
        result = result * 59 + ($fileFormats == null ? 43 : $fileFormats.hashCode());
        String $fileId = this.getFileId();
        result = result * 59 + ($fileId == null ? 43 : $fileId.hashCode());
        return result;
    }

    public String toString() {
        return "OutgoingFileLogEntity(serialNumber=" + this.getSerialNumber() + ", lastUpdated=" + String.valueOf(this.getLastUpdated()) + ", updatedUser=" + this.getUpdatedUser() + ", insCode=" + this.getInsCode() + ", intCode=" + this.getIntCode() + ", generateStatus=" + this.getGenerateStatus() + ", fileName=" + this.getFileName() + ", businessDate=" + String.valueOf(this.getBusinessDate()) + ", generatedDate=" + String.valueOf(this.getGeneratedDate()) + ", fileFormats=" + String.valueOf(this.getFileFormats()) + ", fileId=" + this.getFileId() + ")";
    }
}

