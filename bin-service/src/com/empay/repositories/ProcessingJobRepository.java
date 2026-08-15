/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.entities.ProcessingJobsEntity
 *  com.empay.repositories.ProcessingJobRepository
 *  org.springframework.data.jpa.repository.JpaRepository
 *  org.springframework.stereotype.Repository
 */
package com.empay.repositories;

import com.empay.entities.ProcessingJobsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessingJobRepository
extends JpaRepository<ProcessingJobsEntity, Integer> {
    public void deleteBySerialNumber(int var1);

    public ProcessingJobsEntity findBySerialNumber(Integer var1);
}

