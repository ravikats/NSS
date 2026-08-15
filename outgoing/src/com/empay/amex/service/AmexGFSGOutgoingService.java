/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.amex.entities.AmexAcqTxnWorkEntity
 *  com.empay.amex.service.AmexGFSGOutgoingService
 *  com.empay.cryptapi.DecryptResponseVo
 */
package com.empay.amex.service;

import com.empay.amex.entities.AmexAcqTxnWorkEntity;
import com.empay.cryptapi.DecryptResponseVo;
import java.util.List;

public interface AmexGFSGOutgoingService {
    public List<StringBuilder> amexOutData(List<AmexAcqTxnWorkEntity> var1, int var2, String var3, DecryptResponseVo var4);
}

