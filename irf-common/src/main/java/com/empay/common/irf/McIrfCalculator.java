package com.empay.common.irf;

import com.empay.common.vo.IRFResultVo;

/**
 * Mastercard (UAE/Oman) IRF calculation contract shared by the MPGS and TLF services.
 * Implementations resolve the issuer/acquirer BIN range, persist {@code McIrfParams}
 * so the {@code IpmDetailsView} can be read back, apply domestic/overseas override
 * rates and convert amounts per AED/OMR.
 */
@FunctionalInterface
public interface McIrfCalculator extends IrfCalculator {
    @Override
    IRFResultVo calculate(Integer insCode, IrfTxnData txnData, String cardNumber);
}
