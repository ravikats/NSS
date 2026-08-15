package com.empay.common.irf;

import com.empay.common.vo.IRFResultVo;

/**
 * Common contract shared by every IRF calculator (Visa, Mastercard, UAE-Switch,
 * Jaywan, OmanNet, Onus). Services provide their own implementation (or adapter)
 * and publish it; the orchestration simply routes by network.
 */
@FunctionalInterface
public interface IrfCalculator {

    /**
     * @param insCode     institution code
     * @param txnData     transaction data already decrypted/mapped to the shared DTO
     * @param cardNumber  decrypted PAN (first 9 digits are normally enough)
     * @return populated result, or {@code null} when no IRF applies
     */
    IRFResultVo calculate(Integer insCode, IrfTxnData txnData, String cardNumber);

    static boolean supports(String network, String... networks) {
        if (network == null) {
            return false;
        }
        String n = network.toUpperCase();
        for (String candidate : networks) {
            if (n.equals(candidate.toUpperCase())) {
                return true;
            }
        }
        return false;
    }
}
