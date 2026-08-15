/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.entities.BusinessDateEntity
 *  com.empay.repositories.BusinessDateRepository
 *  org.springframework.data.jpa.repository.JpaRepository
 *  org.springframework.stereotype.Repository
 */
package com.empay.repositories;

import com.empay.entities.BusinessDateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessDateRepository
extends JpaRepository<BusinessDateEntity, Integer> {
    public BusinessDateEntity findTopByOrderByInsCodeAsc();
}

