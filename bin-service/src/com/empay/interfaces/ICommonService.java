/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.interfaces.ICommonService
 */
package com.empay.interfaces;

import java.time.LocalDate;

public interface ICommonService {
    public LocalDate convertToGregorianDate(String var1);

    public LocalDate getBusinessDate();

    public Integer insertFileUploadLog(int var1, String var2, int var3, int var4, int var5, int var6);

    public boolean updateFileUploadLog(int var1, int var2, String var3);

    public void updateProcess(int var1, int var2, int var3, int var4, int var5);
}

