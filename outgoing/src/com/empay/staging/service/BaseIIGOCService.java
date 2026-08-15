/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.cryptapi.DecryptResponseVo
 *  com.empay.staging.entities.VisaGOCWorkEntity
 *  com.empay.staging.service.BaseIIGOCService
 */
package com.empay.staging.service;

import com.empay.cryptapi.DecryptResponseVo;
import com.empay.staging.entities.VisaGOCWorkEntity;
import java.util.List;

public interface BaseIIGOCService {
    public List<StringBuilder> getGOCTxnData(List<VisaGOCWorkEntity> var1, DecryptResponseVo var2, String var3, int var4);
}

