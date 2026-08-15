/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.entities.OutgoingSchedulerEntity
 *  com.empay.repositories.OutgoingSchedulerRepo
 *  org.springframework.data.jpa.repository.JpaRepository
 */
package com.empay.repositories;

import com.empay.entities.OutgoingSchedulerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OutgoingSchedulerRepo
extends JpaRepository<OutgoingSchedulerEntity, Integer> {
}

