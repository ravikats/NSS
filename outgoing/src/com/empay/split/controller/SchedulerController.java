/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.split.controller.SchedulerController
 *  com.empay.staging.service.DynamicSchedulerService
 *  com.empay.vo.OutgoingSchedulerVo
 *  jakarta.validation.Valid
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.http.HttpStatus
 *  org.springframework.http.HttpStatusCode
 *  org.springframework.http.ResponseEntity
 *  org.springframework.web.bind.annotation.DeleteMapping
 *  org.springframework.web.bind.annotation.GetMapping
 *  org.springframework.web.bind.annotation.PathVariable
 *  org.springframework.web.bind.annotation.PostMapping
 *  org.springframework.web.bind.annotation.PutMapping
 *  org.springframework.web.bind.annotation.RequestBody
 *  org.springframework.web.bind.annotation.RequestMapping
 *  org.springframework.web.bind.annotation.RestController
 */
package com.empay.split.controller;

import com.empay.staging.service.DynamicSchedulerService;
import com.empay.vo.OutgoingSchedulerVo;
import jakarta.validation.Valid;
import java.lang.invoke.CallSite;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value={"/OutgoingScheduler/"})
@RestController
public class SchedulerController {
    @Autowired
    private DynamicSchedulerService taskManager;

    @PostMapping(value={"/v1/addCycle"}, produces={"application/json"})
    public ResponseEntity<Map<String, String>> addNewCycle(@RequestBody @Valid OutgoingSchedulerVo inputVo) {
        inputVo.setEditMode(Boolean.valueOf(false));
        String responseMsg = this.taskManager.configureSchedulerCycle(inputVo);
        HashMap<String, String> responseBody = new HashMap<String, String>();
        responseBody.put("message", responseMsg);
        return ResponseEntity.status((HttpStatusCode)HttpStatus.OK).body(responseBody);
    }

    @DeleteMapping(value={"/removeCycle/{name}"})
    public ResponseEntity<Map<String, String>> removeCycleConfig(@PathVariable String taskId) {
        String responseMsg = this.taskManager.removeCycle(taskId);
        HashMap<String, CallSite> responseBody = new HashMap<String, CallSite>();
        responseBody.put("message", (CallSite)((Object)(responseMsg + taskId)));
        return ResponseEntity.status((HttpStatusCode)HttpStatus.OK).body(responseBody);
    }

    @PutMapping(value={"/v1/UpdateCycle"}, produces={"application/json"})
    public ResponseEntity<Map<String, String>> updateCycle(@RequestBody @Valid OutgoingSchedulerVo inputVo) {
        inputVo.setEditMode(Boolean.valueOf(true));
        String responseMsg = this.taskManager.configureSchedulerCycle(inputVo);
        HashMap<String, String> responseBody = new HashMap<String, String>();
        responseBody.put("message", responseMsg);
        return ResponseEntity.status((HttpStatusCode)HttpStatus.OK).body(responseBody);
    }

    @GetMapping(value={"v1/getAllCycle"})
    public List<OutgoingSchedulerVo> getAllTasks() {
        return this.taskManager.getScheduledTasks();
    }
}

