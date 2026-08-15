/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.common.entity.OutgoingSchedulerEntity
 *  com.empay.common.repo.OutgoingSchedulerRepo
 *  org.springframework.data.jpa.repository.JpaRepository
 */
package com.empay.common.repo;

import com.empay.common.entity.OutgoingSchedulerEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OutgoingSchedulerRepo
extends JpaRepository<OutgoingSchedulerEntity, Integer> {
    public List<OutgoingSchedulerEntity> findByGenStatus(Character var1);

    public OutgoingSchedulerEntity findByTaskId(String var1);

    public boolean existsByEndTime(String var1);
}

