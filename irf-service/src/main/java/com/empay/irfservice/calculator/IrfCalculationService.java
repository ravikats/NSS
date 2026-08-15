package com.empay.irfservice.calculator;

import com.empay.common.irf.IrfCalculator;
import com.empay.common.irf.IrfTxnData;
import com.empay.common.vo.IRFResultVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.empay.common.irf.IrfTxnData;
import com.empay.common.vo.IRFResultVo;
import com.empay.irfservice.calculator.IrfCalculatorRegistry;

/**
 * Routes an incoming IRF request to the correct per-network calculator via the
 * {@link IrfCalculatorRegistry}. Network->calculator is configurable (see
 * {@code irf.service.calculators} in application.yml) so new schemes can be
 * added without code changes.
 */
@Service
public class IrfCalculationService {

    private static final Logger log = LoggerFactory.getLogger(IrfCalculationService.class);

    private final IrfCalculatorRegistry registry;

    public IrfCalculationService(IrfCalculatorRegistry registry) {
        this.registry = registry;
    }

    public IRFResultVo calculate(Integer insCode, IrfTxnData txnData, String network, String cardNumber) {
        return registry.calculate(insCode, txnData, network, cardNumber);
    }
}

