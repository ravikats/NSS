/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.entities.ViewMCNetReconDetailsEntity
 *  com.empay.repositories.ViewMCNetReconDetailsRepo
 *  org.springframework.data.domain.Page
 *  org.springframework.data.domain.Pageable
 *  org.springframework.data.jpa.domain.Specification
 *  org.springframework.data.jpa.repository.JpaRepository
 */
package com.empay.repositories;

import com.empay.entities.ViewMCNetReconDetailsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViewMCNetReconDetailsRepo
extends JpaRepository<ViewMCNetReconDetailsEntity, Integer> {
    public long count(Specification<ViewMCNetReconDetailsEntity> var1);

    public Page<ViewMCNetReconDetailsEntity> findAll(Specification<ViewMCNetReconDetailsEntity> var1, Pageable var2);
}

