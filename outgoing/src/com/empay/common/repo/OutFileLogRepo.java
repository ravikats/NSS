/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.common.entity.OutGoingFileProcessingEntity
 *  com.empay.common.repo.OutFileLogRepo
 *  org.springframework.data.jpa.repository.JpaRepository
 */
package com.empay.common.repo;

import com.empay.common.entity.OutGoingFileProcessingEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OutFileLogRepo
extends JpaRepository<OutGoingFileProcessingEntity, Integer> {
    public List<OutGoingFileProcessingEntity> findByFormatCodeAndGeneratedStatusIn(Integer var1, Integer[] var2);

    public OutGoingFileProcessingEntity findByInstitutionCodeAndSerialNumber(Integer var1, Integer var2);

    public OutGoingFileProcessingEntity findTopBygeneratedStatusOrderByLastUpdatedDateDesc(Integer var1);

    public void deleteByInstitutionCodeAndFileIdAndInterfaceCode(Integer var1, String var2, int var3);

    public OutGoingFileProcessingEntity findTopByInstitutionCodeAndInterfaceCodeOrderByLastUpdatedDateDesc(Integer var1, int var2);

    public void deleteBySerialNumber(Integer var1);

    public OutGoingFileProcessingEntity findTopBygeneratedStatusAndInterfaceCodeOrderByLastUpdatedDateDesc(int var1, int var2);
}

