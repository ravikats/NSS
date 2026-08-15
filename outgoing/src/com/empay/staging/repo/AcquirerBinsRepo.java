/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.staging.entities.AcquirerBinsEntity
 *  com.empay.staging.repo.AcquirerBinsRepo
 *  org.springframework.data.jpa.repository.JpaRepository
 */
package com.empay.staging.repo;

import com.empay.staging.entities.AcquirerBinsEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AcquirerBinsRepo
extends JpaRepository<AcquirerBinsEntity, Integer> {
    public List<AcquirerBinsEntity> findByInstitutionCodeAndBinType(Integer var1, String var2);
}

