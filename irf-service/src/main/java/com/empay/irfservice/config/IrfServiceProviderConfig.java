package com.empay.irfservice.config;

import com.empay.common.irf.IrfCalculator;
import com.empay.irfservice.calculator.IrfCalculatorRegistry;
import com.empay.irfservice.calculator.JaywanIrfCalculationService;
import com.empay.irfservice.calculator.McIrfCalculationService;
import com.empay.irfservice.calculator.OnusIrfCalculationService;
import com.empay.irfservice.calculator.OmanNetIrfCalculationService;
import com.empay.irfservice.calculator.UaeSwitchIrfCalculationService;
import com.empay.irfservice.calculator.VisaIrfCalculationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Wires network names to calculator beans. Add a new network key + the
 * matching {@code IrfCalculator} bean to {@link #irfCalculatorRegistry(..)} to
 * support an additional scheme.
 */
@Configuration
public class IrfServiceProviderConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public IrfCalculatorRegistry irfCalculatorRegistry(
            final VisaIrfCalculationService visa,
            final McIrfCalculationService mc,
            final UaeSwitchIrfCalculationService uaeSwitch,
            final JaywanIrfCalculationService jaywan,
            final OmanNetIrfCalculationService omanNet,
            final OnusIrfCalculationService onus) {

        Map<String, IrfCalculator> byNetwork = new LinkedHashMap<>();
        byNetwork.put("VISA", visa);
        byNetwork.put("VSMS", visa);
        byNetwork.put("MCI", mc);
        byNetwork.put("MDS", mc);
        byNetwork.put("UAESWITCH", uaeSwitch);
        byNetwork.put("JAYWAN", jaywan);
        byNetwork.put("OMANNET", omanNet);
        byNetwork.put("ONUS", onus);
        return new IrfCalculatorRegistry(byNetwork);
    }
}
