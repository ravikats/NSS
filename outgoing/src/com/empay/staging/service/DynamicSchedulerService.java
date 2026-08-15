/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.common.entity.OutgoingSchedulerEntity
 *  com.empay.common.repo.OutgoingSchedulerRepo
 *  com.empay.staging.service.DynamicSchedulerService
 *  com.empay.staging.service.OutGoingProcessingService
 *  com.empay.vo.OutgoingSchedulerVo
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 *  org.springframework.scheduling.Trigger
 *  org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler
 *  org.springframework.stereotype.Service
 */
package com.empay.staging.service;

import com.empay.common.entity.OutgoingSchedulerEntity;
import com.empay.common.repo.OutgoingSchedulerRepo;
import com.empay.staging.service.OutGoingProcessingService;
import com.empay.vo.OutgoingSchedulerVo;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

@Service
public class DynamicSchedulerService {
    private static final Logger log = LogManager.getLogger(DynamicSchedulerService.class);
    private final ThreadPoolTaskScheduler scheduler;
    private final Map<String, ScheduledFuture<?>> scheduledTasks = new ConcurrentHashMap();
    private final Map<String, LocalTime> taskTimes = new ConcurrentHashMap();
    private OutgoingSchedulerRepo schedulerRepo;
    private OutGoingProcessingService outGoingProcessingService;

    public DynamicSchedulerService(OutgoingSchedulerRepo schedulerRepo, OutGoingProcessingService outGoingProcessingService, ThreadPoolTaskScheduler scheduler) {
        this.schedulerRepo = schedulerRepo;
        this.outGoingProcessingService = outGoingProcessingService;
        this.scheduler = scheduler;
    }

    public String configureSchedulerCycle(OutgoingSchedulerVo inputData) {
        OutgoingSchedulerEntity entity = new OutgoingSchedulerEntity();
        String message = " Insert ";
        try {
            if (Boolean.TRUE.equals(inputData.getEditMode())) {
                message = " Update ";
                entity = this.schedulerRepo.findByTaskId(inputData.getName());
                if (entity == null) {
                    return "No data found for the given name :" + inputData.getName();
                }
            }
            if (this.schedulerRepo.existsByEndTime(inputData.getEndTime())) {
                return "Another Scheduler already available for the time :" + inputData.getEndTime();
            }
            entity.setLastUpdated(LocalDateTime.now());
            entity.setUpdatedUser(Integer.valueOf(4));
            if (Boolean.TRUE.equals(inputData.getIsActive())) {
                entity.setGenStatus(Character.valueOf('A'));
            } else {
                entity.setGenStatus(Character.valueOf('D'));
            }
            entity.setTaskId(inputData.getName());
            entity.setNetwork(inputData.getNetwork().toUpperCase());
            entity.setEndTime(inputData.getEndTime());
            this.schedulerRepo.saveAndFlush((Object)entity);
            LocalTime runTime = LocalTime.parse(inputData.getEndTime());
            if (entity.getGenStatus().charValue() == 'A') {
                this.scheduleDailyTask(inputData.getName(), runTime, () -> this.outGoingProcessingService.automateSchedulerTriggering(inputData.getEndTime(), inputData.getNetwork().toUpperCase()));
            }
            return "Cycle Configuration" + message + "Success";
        }
        catch (Exception e) {
            log.error("", (Throwable)e);
            if (e.getCause().toString().contains("UK_TASK_ID")) {
                return "Duplicate Name : Name Alreday Exists";
            }
            return "Cycle Configuration" + message + "Failed";
        }
    }

    public void scheduleDailyTask(String taskId, LocalTime time, Runnable taskLogic) {
        if (this.scheduledTasks.containsKey(taskId)) {
            ((ScheduledFuture)this.scheduledTasks.get(taskId)).cancel(false);
        }
        Trigger dailyTrigger = context -> {
            LocalDateTime dateTime = LocalDateTime.of(LocalDate.now(), time);
            log.info("dateTime :" + String.valueOf(dateTime) + " Local Date :" + String.valueOf(LocalDateTime.now()));
            if (dateTime.isBefore(LocalDateTime.now())) {
                dateTime = dateTime.plusDays(1L);
            }
            return dateTime.atZone(ZoneId.systemDefault()).toInstant();
        };
        ScheduledFuture future = this.scheduler.schedule(() -> {
            log.info("Executing task: " + taskId + " at " + String.valueOf(LocalTime.now()));
            taskLogic.run();
        }, dailyTrigger);
        this.scheduledTasks.put(taskId, future);
        this.taskTimes.put(taskId, time);
    }

    public String removeCycle(String taskId) {
        try {
            if (this.scheduledTasks.containsKey(taskId)) {
                ((ScheduledFuture)this.scheduledTasks.get(taskId)).cancel(true);
                this.scheduledTasks.remove(taskId);
                this.taskTimes.remove(taskId);
            }
            this.schedulerRepo.delete((Object)this.schedulerRepo.findByTaskId(taskId));
            this.schedulerRepo.flush();
            return "Successfully  removed the cycle ";
        }
        catch (Exception e) {
            log.error("", (Throwable)e);
            return "Failed to remove the cycle";
        }
    }

    public List<OutgoingSchedulerVo> getScheduledTasks() {
        return this.mapCycleList(this.schedulerRepo.findAll());
    }

    private List<OutgoingSchedulerVo> mapCycleList(List<OutgoingSchedulerEntity> cycleList) {
        try {
            ArrayList<OutgoingSchedulerVo> result = new ArrayList<OutgoingSchedulerVo>();
            for (OutgoingSchedulerEntity loop : cycleList) {
                OutgoingSchedulerVo valueObject = new OutgoingSchedulerVo();
                valueObject.setName(loop.getTaskId());
                valueObject.setNetwork(loop.getNetwork());
                valueObject.setEndTime(loop.getEndTime());
                valueObject.setIsActive(Boolean.valueOf(loop.getGenStatus().charValue() == 'A'));
                valueObject.setCreatedDateTime(loop.getLastUpdated().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
                valueObject.setTotalCount(null);
                valueObject.setTotalPage(null);
                result.add(valueObject);
            }
            return result;
        }
        catch (Exception e) {
            return Collections.emptyList();
        }
    }
}

