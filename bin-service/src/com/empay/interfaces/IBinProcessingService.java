/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.interfaces.IBinProcessingService
 *  com.empay.vo.BinResponseVO
 */
package com.empay.interfaces;

import com.empay.vo.BinResponseVO;
import java.io.File;

public interface IBinProcessingService {
    public BinResponseVO processBin(String var1, String var2, int var3, int var4, int var5, int var6, String var7, File var8);

    public BinResponseVO binFileDeletion(String var1, String var2);
}

