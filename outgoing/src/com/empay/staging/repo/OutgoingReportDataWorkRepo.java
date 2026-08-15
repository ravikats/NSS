/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.staging.entities.OutgoingReportDataWorkEntity
 *  com.empay.staging.repo.OutgoingReportDataWorkRepo
 *  org.springframework.data.jpa.repository.JpaRepository
 */
package com.empay.staging.repo;

import com.empay.staging.entities.OutgoingReportDataWorkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OutgoingReportDataWorkRepo
extends JpaRepository<OutgoingReportDataWorkEntity, Integer> {
}

