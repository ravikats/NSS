package com.empay.irfservice.controller;

import com.empay.irfservice.callback.LocalIRFCallbackSender;
import com.empay.common.irf.IrfResultData;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/irf/v1")
public class IrfCallbackController {

    private final LocalIRFCallbackSender callbackSender;
    private final Environment env;

    public IrfCallbackController(LocalIRFCallbackSender callbackSender, Environment env) {
        this.callbackSender = callbackSender;
        this.env = env;
    }

    @PostMapping("/callback")
    public ResponseEntity<Integer> enqueue(
            @RequestParam(name = "sec", required = false) String sec,
            @RequestBody IrfResultData data) {
        if (!authorized(sec)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Integer serial = callbackSender.enqueue(data);
        return ResponseEntity.ok(serial);
    }

    @PostMapping("/callback/flush")
    public ResponseEntity<Void> flush(
            @RequestParam(name = "sec", required = false) String sec,
            @RequestParam(name = "insCode", required = false) Integer insCode,
            @RequestParam(name = "jobNumber", required = false) Integer jobNumber) {
        if (!authorized(sec)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        callbackSender.flush(insCode, jobNumber);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/callback/retry")
    public ResponseEntity<Boolean> retry(
            @RequestParam(name = "sec", required = false) String sec,
            @RequestParam(name = "refSerNumber") Integer refSerNumber) {
        if (!authorized(sec)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(callbackSender.retry(refSerNumber));
    }

    private boolean authorized(String sec) {
        String expected = env.getProperty("irf.service.sec");
        return expected != null && expected.equals(sec);
    }
}
