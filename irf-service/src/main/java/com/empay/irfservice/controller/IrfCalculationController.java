package com.empay.irfservice.controller;

import com.empay.common.irf.IrfCalculateRequest;
import com.empay.common.irf.IrfCalculateResponse;
import com.empay.irfservice.calculator.IrfCalculationService;
import com.empay.common.vo.IRFResultVo;
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
public class IrfCalculationController {

    private final IrfCalculationService calculationService;
    private final Environment env;

    public IrfCalculationController(IrfCalculationService calculationService, Environment env) {
        this.calculationService = calculationService;
        this.env = env;
    }

    @PostMapping("/calculate")
    public ResponseEntity<IrfCalculateResponse> calculate(
            @RequestParam(name = "sec", required = false) String sec,
            @RequestBody IrfCalculateRequest request) {
        if (!authorized(sec)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        try {
            if (request.getTxnData() != null && request.getTxnData().getNetwork() == null) {
                request.getTxnData().setNetwork(request.getNetwork());
            }
            IRFResultVo result = calculationService.calculate(
                    request.getInsCode(), request.getTxnData(), request.getNetwork(), request.getCardNumber());
            return ResponseEntity.ok(new IrfCalculateResponse(result != null, result));
        } catch (UnsupportedOperationException e) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
        }
    }

    private boolean authorized(String sec) {
        String expected = env.getProperty("irf.service.sec");
        return expected != null && expected.equals(sec);
    }
}
