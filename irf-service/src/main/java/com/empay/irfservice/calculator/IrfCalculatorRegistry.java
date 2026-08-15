package com.empay.irfservice.calculator;

import com.empay.common.irf.IrfCalculator;
import com.empay.common.irf.IrfTxnData;
import com.empay.common.vo.IRFResultVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Resolves a network name to its {@link IrfCalculator} bean. Backing map is
 * keyed by network string (e.g. "VISA","MCI","UAESWITCH") so dispatch does not
 * depend on bean names.
 */
@Component
public class IrfCalculatorRegistry {

    private static final Logger log = LoggerFactory.getLogger(IrfCalculatorRegistry.class);
    private final Map<String, IrfCalculator> byNetwork;

    public IrfCalculatorRegistry(Map<String, IrfCalculator> byNetwork) {
        this.byNetwork = byNetwork;
    }

    public IRFResultVo calculate(Integer insCode, IrfTxnData txnData, String network, String cardNumber) {
        String key = (network == null) ? "" : network.toUpperCase();
        IrfCalculator calculator = byNetwork.get(key);
        if (calculator == null) {
            log.warn("No IRF calculator registered for network '{}'", network);
            return null;
        }
        return calculator.calculate(insCode, txnData, cardNumber);
    }
}
