package com.empay.common.irf;

/**
 * Adapter each service implements to bridge its own transaction entity to the
 * shared {@link IrfTxnData} DTO that the common calculators consume.
 *
 * For example:
 * <pre>
 *   class TlfTxnMapper implements IrfTxnDataMapper&lt;PosTransactionEntity&gt; { ... }
 *   class MpgsTxnMapper implements IrfTxnDataMapper&lt;MPGSTxnWorkEntity&gt; { ... }
 * </pre>
 */
@FunctionalInterface
public interface IrfTxnDataMapper<T> {
    IrfTxnData toIrfData(T txnEntity);
}
