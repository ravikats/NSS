/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.common.entity.OutGoingFileProcessingEntity
 *  com.empay.staging.service.IOutGoingSummaryService
 */
package com.empay.staging.service;

import com.empay.common.entity.OutGoingFileProcessingEntity;

public interface IOutGoingSummaryService {
    public void generateOutgoingSummaryPDF(int var1, int var2, int var3, int var4, String var5, String var6, OutGoingFileProcessingEntity var7, String var8);
}

