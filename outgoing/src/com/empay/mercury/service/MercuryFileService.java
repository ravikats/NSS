/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.cryptapi.DecryptResponseVo
 *  com.empay.mercury.entities.MercuryAcqTxnWorkEntity
 *  com.empay.mercury.service.MercuryFileService
 */
package com.empay.mercury.service;

import com.empay.cryptapi.DecryptResponseVo;
import com.empay.mercury.entities.MercuryAcqTxnWorkEntity;
import java.util.List;

public interface MercuryFileService {
    public String writeMercuryFile(List<MercuryAcqTxnWorkEntity> var1, String var2, String var3, String var4, DecryptResponseVo var5);
}

