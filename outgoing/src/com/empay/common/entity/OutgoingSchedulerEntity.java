/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.common.entity.OutgoingSchedulerEntity
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
import java.time.LocalDateTime;

@Entity
@Table(name="OUTGOING_SCHEDULER")
public class OutgoingSchedulerEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="OGS_SER_NUMBER")
    private int serNumber;
    @Column(name="OGS_LAST_UPDATED")
    private LocalDateTime lastUpdated;
    @Column(name="OGS_UPDATED_USER")
    private Integer updatedUser;
    @Column(name="OGS_GEN_STATUS")
    private Character genStatus;
    @Column(name="OGS_TASK_ID")
    private String taskId;
    @Column(name="OGS_NETWORK")
    private String network;
    @Column(name="OGS_TIME_ZONE")
    private String timeZone;
    @Column(name="OGS_END_TIME")
    private String endTime;

    public int getSerNumber() {
        return this.serNumber;
    }

    public LocalDateTime getLastUpdated() {
        return this.lastUpdated;
    }

    public Integer getUpdatedUser() {
        return this.updatedUser;
    }

    public Character getGenStatus() {
        return this.genStatus;
    }

    public String getTaskId() {
        return this.taskId;
    }

    public String getNetwork() {
        return this.network;
    }

    public String getTimeZone() {
        return this.timeZone;
    }

    public String getEndTime() {
        return this.endTime;
    }

    public void setSerNumber(int serNumber) {
        this.serNumber = serNumber;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public void setUpdatedUser(Integer updatedUser) {
        this.updatedUser = updatedUser;
    }

    public void setGenStatus(Character genStatus) {
        this.genStatus = genStatus;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof OutgoingSchedulerEntity)) {
            return false;
        }
        OutgoingSchedulerEntity other = (OutgoingSchedulerEntity)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        if (this.getSerNumber() != other.getSerNumber()) {
            return false;
        }
        Integer this$updatedUser = this.getUpdatedUser();
        Integer other$updatedUser = other.getUpdatedUser();
        if (this$updatedUser == null ? other$updatedUser != null : !((Object)this$updatedUser).equals(other$updatedUser)) {
            return false;
        }
        Character this$genStatus = this.getGenStatus();
        Character other$genStatus = other.getGenStatus();
        if (this$genStatus == null ? other$genStatus != null : !((Object)this$genStatus).equals(other$genStatus)) {
            return false;
        }
        LocalDateTime this$lastUpdated = this.getLastUpdated();
        LocalDateTime other$lastUpdated = other.getLastUpdated();
        if (this$lastUpdated == null ? other$lastUpdated != null : !((Object)this$lastUpdated).equals(other$lastUpdated)) {
            return false;
        }
        String this$taskId = this.getTaskId();
        String other$taskId = other.getTaskId();
        if (this$taskId == null ? other$taskId != null : !this$taskId.equals(other$taskId)) {
            return false;
        }
        String this$network = this.getNetwork();
        String other$network = other.getNetwork();
        if (this$network == null ? other$network != null : !this$network.equals(other$network)) {
            return false;
        }
        String this$timeZone = this.getTimeZone();
        String other$timeZone = other.getTimeZone();
        if (this$timeZone == null ? other$timeZone != null : !this$timeZone.equals(other$timeZone)) {
            return false;
        }
        String this$endTime = this.getEndTime();
        String other$endTime = other.getEndTime();
        return !(this$endTime == null ? other$endTime != null : !this$endTime.equals(other$endTime));
    }

    protected boolean canEqual(Object other) {
        return other instanceof OutgoingSchedulerEntity;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + this.getSerNumber();
        Integer $updatedUser = this.getUpdatedUser();
        result = result * 59 + ($updatedUser == null ? 43 : ((Object)$updatedUser).hashCode());
        Character $genStatus = this.getGenStatus();
        result = result * 59 + ($genStatus == null ? 43 : ((Object)$genStatus).hashCode());
        LocalDateTime $lastUpdated = this.getLastUpdated();
        result = result * 59 + ($lastUpdated == null ? 43 : ((Object)$lastUpdated).hashCode());
        String $taskId = this.getTaskId();
        result = result * 59 + ($taskId == null ? 43 : $taskId.hashCode());
        String $network = this.getNetwork();
        result = result * 59 + ($network == null ? 43 : $network.hashCode());
        String $timeZone = this.getTimeZone();
        result = result * 59 + ($timeZone == null ? 43 : $timeZone.hashCode());
        String $endTime = this.getEndTime();
        result = result * 59 + ($endTime == null ? 43 : $endTime.hashCode());
        return result;
    }

    public String toString() {
        return "OutgoingSchedulerEntity(serNumber=" + this.getSerNumber() + ", lastUpdated=" + String.valueOf(this.getLastUpdated()) + ", updatedUser=" + this.getUpdatedUser() + ", genStatus=" + this.getGenStatus() + ", taskId=" + this.getTaskId() + ", network=" + this.getNetwork() + ", timeZone=" + this.getTimeZone() + ", endTime=" + this.getEndTime() + ")";
    }
}

