/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.interfaces.IValidationService
 *  com.empay.vo.BinResponseVO
 */
package com.empay.interfaces;

import com.empay.vo.BinResponseVO;
import java.io.File;

public interface IValidationService {
    public BinResponseVO validateFile(File var1, String var2, String var3);
}

