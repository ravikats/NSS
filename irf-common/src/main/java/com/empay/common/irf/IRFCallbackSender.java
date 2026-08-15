package com.empay.common.irf;

/**
 * Pushes an IRF result to the scheme callback. Two strategies exist in the
 * current codebase:
 * <ul>
 *   <li><b>Tlf</b>: synchronous POST inside the Kafka listener thread
 *       ({@code TxnProcessingService#irfCallback} -> {@code IRFCallbackService#updateApiResponse})
 *       — the dominant contributor to the observed ~2.1s/txn latency.</li>
 *   <li><b>Mpgs</b>: row is persisted as PENDING, then bulk-sent later by
 *       {@code IRFCallbackService#updateIRFCallback(jobNumber)}.</li>
 * </ul>
 * The common contract keeps both strategies possible.
 */
public interface IRFCallbackSender {

    /**
     * Persist the callback row (status = PENDING) and return the stored
     * entity's serial number so the caller can correlate.
     */
    Integer enqueue(IrfResultData data);

    /**
     * Send any pending callbacks for the given job/institution.
     */
    void flush(Integer insCode, Integer jobNumber);

    /**
     * Retry a single callback by stored reference number.
     */
    boolean retry(Integer refSerNumber);
}
