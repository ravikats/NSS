/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.common.entity.BusinessDateEntity
 *  com.empay.common.repo.BusinessDateRepo
 *  org.springframework.data.jpa.repository.JpaRepository
 */
package com.empay.common.repo;

import com.empay.common.entity.BusinessDateEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessDateRepo
extends JpaRepository<BusinessDateEntity, Integer> {
    public List<BusinessDateEntity> findByInstitutionCode(int var1);
}

