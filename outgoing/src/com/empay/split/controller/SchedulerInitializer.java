/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.common.entity.OutgoingSchedulerEntity
 *  com.empay.common.repo.OutgoingSchedulerRepo
 *  com.empay.split.controller.SchedulerInitializer
 *  com.empay.staging.service.DynamicSchedulerService
 *  com.empay.staging.service.OutGoingProcessingService
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.boot.ApplicationArguments
 *  org.springframework.boot.ApplicationRunner
 *  org.springframework.stereotype.Component
 */
package com.empay.split.controller;

import com.empay.common.entity.OutgoingSchedulerEntity;
import com.empay.common.repo.OutgoingSchedulerRepo;
import com.empay.staging.service.DynamicSchedulerService;
import com.empay.staging.service.OutGoingProcessingService;
import java.time.LocalTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class SchedulerInitializer
implements ApplicationRunner {
    @Autowired
    private DynamicSchedulerService taskManager;
    @Autowired
    OutgoingSchedulerRepo ssRepo;
    @Autowired
    OutGoingProcessingService outgoingService;

    public void run(ApplicationArguments args) throws Exception {
        List schedulerDetails = this.ssRepo.findByGenStatus(Character.valueOf('A'));
        for (OutgoingSchedulerEntity entity : schedulerDetails) {
            String taskId = entity.getTaskId();
            LocalTime runTime = LocalTime.parse(entity.getEndTime());
            this.taskManager.scheduleDailyTask(taskId, runTime, () -> this.outgoingService.automateSchedulerTriggering(entity.getEndTime(), entity.getNetwork().toUpperCase()));
        }
    }
}

