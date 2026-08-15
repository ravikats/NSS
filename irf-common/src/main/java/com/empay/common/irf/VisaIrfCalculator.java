package com.empay.common.irf;

import com.empay.common.vo.IRFResultVo;

/**
 * Visa-specific IRF calculation contract shared by the MPGS and TLF services.
 * Implementations build the {@code IRFRequestVo} from {@link IrfTxnData}, resolve
 * the issuer BIN range and select the applicable fee program (industry / product /
 * acquirer-downgrade / base / alternative / uncategorized) per region.
 */
@FunctionalInterface
public interface VisaIrfCalculator extends IrfCalculator {
    @Override
    IRFResultVo calculate(Integer insCode, IrfTxnData txnData, String cardNumber);
}
