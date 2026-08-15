/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.staging.entities.VisaGOCDataEntity
 *  com.empay.staging.repo.VisaGOCDataRepo
 *  org.springframework.data.jpa.repository.JpaRepository
 *  org.springframework.stereotype.Repository
 */
package com.empay.staging.repo;

import com.empay.staging.entities.VisaGOCDataEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisaGOCDataRepo
extends JpaRepository<VisaGOCDataEntity, Integer> {
    public List<VisaGOCDataEntity> findByInstitutionCodeAndFileId(int var1, String var2);
}

