/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.entities.ViewVisaFundsTransferDetailsEntity
 *  com.empay.repositories.VisaFundsTransferDetailsRepo
 *  org.springframework.data.domain.Page
 *  org.springframework.data.domain.Pageable
 *  org.springframework.data.jpa.domain.Specification
 *  org.springframework.data.jpa.repository.JpaRepository
 */
package com.empay.repositories;

import com.empay.entities.ViewVisaFundsTransferDetailsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisaFundsTransferDetailsRepo
extends JpaRepository<ViewVisaFundsTransferDetailsEntity, Integer> {
    public long count(Specification<ViewVisaFundsTransferDetailsEntity> var1);

    public Page<ViewVisaFundsTransferDetailsEntity> findAll(Specification<ViewVisaFundsTransferDetailsEntity> var1, Pageable var2);
}

