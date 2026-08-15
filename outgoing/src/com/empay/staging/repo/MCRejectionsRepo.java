/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.staging.entities.MCRejectionsEntity
 *  com.empay.staging.repo.MCRejectionsRepo
 *  org.springframework.data.jpa.repository.JpaRepository
 */
package com.empay.staging.repo;

import com.empay.staging.entities.MCRejectionsEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MCRejectionsRepo
extends JpaRepository<MCRejectionsEntity, Integer> {
    public List<MCRejectionsEntity> findByRetRefNumberAndMsgTypeIdAndProcCode(String var1, String var2, String var3);
}

