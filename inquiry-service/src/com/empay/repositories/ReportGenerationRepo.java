/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.entities.ReportGenerationLogEntity
 *  com.empay.repositories.ReportGenerationRepo
 *  org.springframework.data.jpa.repository.JpaRepository
 *  org.springframework.data.jpa.repository.JpaSpecificationExecutor
 *  org.springframework.stereotype.Repository
 */
package com.empay.repositories;

import com.empay.entities.ReportGenerationLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportGenerationRepo
extends JpaRepository<ReportGenerationLogEntity, Integer>,
JpaSpecificationExecutor<ReportGenerationLogEntity> {
}

