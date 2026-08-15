/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.common.entity.OutgoingSummaryEntity
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
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="OUTGOING_SUMMARY")
public class OutgoingSummaryEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="OTS_SER_NUMBER")
    private int serialNumber;
    @Column(name="OTS_LAST_UPDATED")
    private LocalDateTime lastupdated;
    @Column(name="OTS_UPDATED_USER")
    private int updatedUser;
    @Column(name="OTS_INS_CODE")
    private int institution;
    @Column(name="OTS_INT_CODE")
    private int interfaceCode;
    @Column(name="OTS_OUT_FILE_DATE")
    private LocalDate outFileDate;
    @Column(name="OTS_FILE_ID")
    private String fileId;
    @Column(name="OTS_OFL_SER_NUMBER")
    private int refSerialNumber;
    @Column(name="OTS_MTI")
    private String messageTypeId;
    @Column(name="OTS_FUNCTION_CODE")
    private String functionCode;
    @Column(name="OTS_PROC_CODE")
    private String procCode;
    @Column(name="OTS_COUNT")
    private Integer count;
    @Column(name="OTS_AMOUNT")
    private Double txnAmount;
    @Column(name="OTS_SCHG_AMOUNT")
    private Double surchargeAmount;
    @Column(name="OTS_NET_AMOUNT")
    private Double netAmount;
    @Column(name="OTS_GEN_STATUS")
    private Integer generalStatus;

    public int getSerialNumber() {
        return this.serialNumber;
    }

    public LocalDateTime getLastupdated() {
        return this.lastupdated;
    }

    public int getUpdatedUser() {
        return this.updatedUser;
    }

    public int getInstitution() {
        return this.institution;
    }

    public int getInterfaceCode() {
        return this.interfaceCode;
    }

    public LocalDate getOutFileDate() {
        return this.outFileDate;
    }

    public String getFileId() {
        return this.fileId;
    }

    public int getRefSerialNumber() {
        return this.refSerialNumber;
    }

    public String getMessageTypeId() {
        return this.messageTypeId;
    }

    public String getFunctionCode() {
        return this.functionCode;
    }

    public String getProcCode() {
        return this.procCode;
    }

    public Integer getCount() {
        return this.count;
    }

    public Double getTxnAmount() {
        return this.txnAmount;
    }

    public Double getSurchargeAmount() {
        return this.surchargeAmount;
    }

    public Double getNetAmount() {
        return this.netAmount;
    }

    public Integer getGeneralStatus() {
        return this.generalStatus;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setLastupdated(LocalDateTime lastupdated) {
        this.lastupdated = lastupdated;
    }

    public void setUpdatedUser(int updatedUser) {
        this.updatedUser = updatedUser;
    }

    public void setInstitution(int institution) {
        this.institution = institution;
    }

    public void setInterfaceCode(int interfaceCode) {
        this.interfaceCode = interfaceCode;
    }

    public void setOutFileDate(LocalDate outFileDate) {
        this.outFileDate = outFileDate;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public void setRefSerialNumber(int refSerialNumber) {
        this.refSerialNumber = refSerialNumber;
    }

    public void setMessageTypeId(String messageTypeId) {
        this.messageTypeId = messageTypeId;
    }

    public void setFunctionCode(String functionCode) {
        this.functionCode = functionCode;
    }

    public void setProcCode(String procCode) {
        this.procCode = procCode;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void setTxnAmount(Double txnAmount) {
        this.txnAmount = txnAmount;
    }

    public void setSurchargeAmount(Double surchargeAmount) {
        this.surchargeAmount = surchargeAmount;
    }

    public void setNetAmount(Double netAmount) {
        this.netAmount = netAmount;
    }

    public void setGeneralStatus(Integer generalStatus) {
        this.generalStatus = generalStatus;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof OutgoingSummaryEntity)) {
            return false;
        }
        OutgoingSummaryEntity other = (OutgoingSummaryEntity)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        if (this.getSerialNumber() != other.getSerialNumber()) {
            return false;
        }
        if (this.getUpdatedUser() != other.getUpdatedUser()) {
            return false;
        }
        if (this.getInstitution() != other.getInstitution()) {
            return false;
        }
        if (this.getInterfaceCode() != other.getInterfaceCode()) {
            return false;
        }
        if (this.getRefSerialNumber() != other.getRefSerialNumber()) {
            return false;
        }
        Integer this$count = this.getCount();
        Integer other$count = other.getCount();
        if (this$count == null ? other$count != null : !((Object)this$count).equals(other$count)) {
            return false;
        }
        Double this$txnAmount = this.getTxnAmount();
        Double other$txnAmount = other.getTxnAmount();
        if (this$txnAmount == null ? other$txnAmount != null : !((Object)this$txnAmount).equals(other$txnAmount)) {
            return false;
        }
        Double this$surchargeAmount = this.getSurchargeAmount();
        Double other$surchargeAmount = other.getSurchargeAmount();
        if (this$surchargeAmount == null ? other$surchargeAmount != null : !((Object)this$surchargeAmount).equals(other$surchargeAmount)) {
            return false;
        }
        Double this$netAmount = this.getNetAmount();
        Double other$netAmount = other.getNetAmount();
        if (this$netAmount == null ? other$netAmount != null : !((Object)this$netAmount).equals(other$netAmount)) {
            return false;
        }
        Integer this$generalStatus = this.getGeneralStatus();
        Integer other$generalStatus = other.getGeneralStatus();
        if (this$generalStatus == null ? other$generalStatus != null : !((Object)this$generalStatus).equals(other$generalStatus)) {
            return false;
        }
        LocalDateTime this$lastupdated = this.getLastupdated();
        LocalDateTime other$lastupdated = other.getLastupdated();
        if (this$lastupdated == null ? other$lastupdated != null : !((Object)this$lastupdated).equals(other$lastupdated)) {
            return false;
        }
        LocalDate this$outFileDate = this.getOutFileDate();
        LocalDate other$outFileDate = other.getOutFileDate();
        if (this$outFileDate == null ? other$outFileDate != null : !((Object)this$outFileDate).equals(other$outFileDate)) {
            return false;
        }
        String this$fileId = this.getFileId();
        String other$fileId = other.getFileId();
        if (this$fileId == null ? other$fileId != null : !this$fileId.equals(other$fileId)) {
            return false;
        }
        String this$messageTypeId = this.getMessageTypeId();
        String other$messageTypeId = other.getMessageTypeId();
        if (this$messageTypeId == null ? other$messageTypeId != null : !this$messageTypeId.equals(other$messageTypeId)) {
            return false;
        }
        String this$functionCode = this.getFunctionCode();
        String other$functionCode = other.getFunctionCode();
        if (this$functionCode == null ? other$functionCode != null : !this$functionCode.equals(other$functionCode)) {
            return false;
        }
        String this$procCode = this.getProcCode();
        String other$procCode = other.getProcCode();
        return !(this$procCode == null ? other$procCode != null : !this$procCode.equals(other$procCode));
    }

    protected boolean canEqual(Object other) {
        return other instanceof OutgoingSummaryEntity;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + this.getSerialNumber();
        result = result * 59 + this.getUpdatedUser();
        result = result * 59 + this.getInstitution();
        result = result * 59 + this.getInterfaceCode();
        result = result * 59 + this.getRefSerialNumber();
        Integer $count = this.getCount();
        result = result * 59 + ($count == null ? 43 : ((Object)$count).hashCode());
        Double $txnAmount = this.getTxnAmount();
        result = result * 59 + ($txnAmount == null ? 43 : ((Object)$txnAmount).hashCode());
        Double $surchargeAmount = this.getSurchargeAmount();
        result = result * 59 + ($surchargeAmount == null ? 43 : ((Object)$surchargeAmount).hashCode());
        Double $netAmount = this.getNetAmount();
        result = result * 59 + ($netAmount == null ? 43 : ((Object)$netAmount).hashCode());
        Integer $generalStatus = this.getGeneralStatus();
        result = result * 59 + ($generalStatus == null ? 43 : ((Object)$generalStatus).hashCode());
        LocalDateTime $lastupdated = this.getLastupdated();
        result = result * 59 + ($lastupdated == null ? 43 : ((Object)$lastupdated).hashCode());
        LocalDate $outFileDate = this.getOutFileDate();
        result = result * 59 + ($outFileDate == null ? 43 : ((Object)$outFileDate).hashCode());
        String $fileId = this.getFileId();
        result = result * 59 + ($fileId == null ? 43 : $fileId.hashCode());
        String $messageTypeId = this.getMessageTypeId();
        result = result * 59 + ($messageTypeId == null ? 43 : $messageTypeId.hashCode());
        String $functionCode = this.getFunctionCode();
        result = result * 59 + ($functionCode == null ? 43 : $functionCode.hashCode());
        String $procCode = this.getProcCode();
        result = result * 59 + ($procCode == null ? 43 : $procCode.hashCode());
        return result;
    }

    public String toString() {
        return "OutgoingSummaryEntity(serialNumber=" + this.getSerialNumber() + ", lastupdated=" + String.valueOf(this.getLastupdated()) + ", updatedUser=" + this.getUpdatedUser() + ", institution=" + this.getInstitution() + ", interfaceCode=" + this.getInterfaceCode() + ", outFileDate=" + String.valueOf(this.getOutFileDate()) + ", fileId=" + this.getFileId() + ", refSerialNumber=" + this.getRefSerialNumber() + ", messageTypeId=" + this.getMessageTypeId() + ", functionCode=" + this.getFunctionCode() + ", procCode=" + this.getProcCode() + ", count=" + this.getCount() + ", txnAmount=" + this.getTxnAmount() + ", surchargeAmount=" + this.getSurchargeAmount() + ", netAmount=" + this.getNetAmount() + ", generalStatus=" + this.getGeneralStatus() + ")";
    }
}

