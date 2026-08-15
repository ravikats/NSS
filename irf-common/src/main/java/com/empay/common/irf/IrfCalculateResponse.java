package com.empay.common.irf;

import com.empay.common.vo.IRFResultVo;

/**
 * REST response for an IRF calculation request.
 */
public class IrfCalculateResponse {

    private boolean calculated;
    private IRFResultVo result;

    public IrfCalculateResponse() {
    }

    public IrfCalculateResponse(boolean calculated, IRFResultVo result) {
        this.calculated = calculated;
        this.result = result;
    }

    public boolean isCalculated() {
        return calculated;
    }

    public IrfCalculateResponse setCalculated(boolean calculated) {
        this.calculated = calculated;
        return this;
    }

    public IRFResultVo getResult() {
        return result;
    }

    public IrfCalculateResponse setResult(IRFResultVo result) {
        this.result = result;
        return this;
    }
}
