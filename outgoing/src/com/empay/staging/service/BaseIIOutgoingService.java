/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.cryptapi.DecryptResponseVo
 *  com.empay.staging.entities.VisaAcqTxnWorkEntity
 *  com.empay.staging.service.BaseIIOutgoingService
 */
package com.empay.staging.service;

import com.empay.cryptapi.DecryptResponseVo;
import com.empay.staging.entities.VisaAcqTxnWorkEntity;
import java.util.List;

public interface BaseIIOutgoingService {
    public List<StringBuilder> getFeeAndTxnData(List<VisaAcqTxnWorkEntity> var1, List<VisaAcqTxnWorkEntity> var2, DecryptResponseVo var3, String var4, Integer var5);
}

