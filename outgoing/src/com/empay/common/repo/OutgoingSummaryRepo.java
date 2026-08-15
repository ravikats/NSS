/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.common.entity.OutgoingSummaryEntity
 *  com.empay.common.repo.OutgoingSummaryRepo
 *  org.springframework.data.jpa.repository.JpaRepository
 *  org.springframework.stereotype.Repository
 */
package com.empay.common.repo;

import com.empay.common.entity.OutgoingSummaryEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutgoingSummaryRepo
extends JpaRepository<OutgoingSummaryEntity, Integer> {
    public List<OutgoingSummaryEntity> findByInstitutionAndInterfaceCodeAndRefSerialNumber(int var1, int var2, int var3);
}

