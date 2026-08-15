/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.staging.entities.McGCOTxnDataEntity
 *  com.empay.staging.repo.McGCODataRepo
 *  org.springframework.data.jpa.repository.JpaRepository
 */
package com.empay.staging.repo;

import com.empay.staging.entities.McGCOTxnDataEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface McGCODataRepo
extends JpaRepository<McGCOTxnDataEntity, Long> {
    public List<McGCOTxnDataEntity> findByInsCodeAndFileId(Integer var1, String var2);
}

