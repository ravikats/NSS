/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.jaywan.entities.JaywanAcqTxnDataEntity
 *  com.empay.jaywan.repo.JWNAcqTxnDataRepo
 *  org.springframework.data.jpa.repository.JpaRepository
 *  org.springframework.stereotype.Repository
 */
package com.empay.jaywan.repo;

import com.empay.jaywan.entities.JaywanAcqTxnDataEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JWNAcqTxnDataRepo
extends JpaRepository<JaywanAcqTxnDataEntity, Integer> {
    public List<JaywanAcqTxnDataEntity> findByInstitutionCodeAndFileID(Integer var1, String var2);
}

