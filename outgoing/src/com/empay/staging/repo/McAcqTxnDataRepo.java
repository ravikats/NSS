/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.staging.entities.McAcqTxnDataEntity
 *  com.empay.staging.repo.McAcqTxnDataRepo
 *  org.springframework.data.jpa.repository.JpaRepository
 */
package com.empay.staging.repo;

import com.empay.staging.entities.McAcqTxnDataEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface McAcqTxnDataRepo
extends JpaRepository<McAcqTxnDataEntity, Integer> {
    public List<McAcqTxnDataEntity> findByInstitutionCodeAndFileID(Integer var1, String var2);

    public List<McAcqTxnDataEntity> findByRrnAndMessageTypeIdAndProcCodeOrderByLastUpdatedDesc(String var1, String var2, String var3);
}

