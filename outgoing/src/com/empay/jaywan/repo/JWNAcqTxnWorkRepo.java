/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.jaywan.entities.JaywanAcqTxnWorkEntity
 *  com.empay.jaywan.repo.JWNAcqTxnWorkRepo
 *  org.springframework.data.jpa.repository.JpaRepository
 *  org.springframework.stereotype.Repository
 */
package com.empay.jaywan.repo;

import com.empay.jaywan.entities.JaywanAcqTxnWorkEntity;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JWNAcqTxnWorkRepo
extends JpaRepository<JaywanAcqTxnWorkEntity, Integer> {
    public List<JaywanAcqTxnWorkEntity> findByInstitutionCode(Integer var1);

    public Integer countByInstitutionCodeAndGenStatus(int var1, int var2);

    public Integer countByInstitutionCodeAndGenStatusAndLocalDateTimeBetween(int var1, int var2, LocalDateTime var3, LocalDateTime var4);

    public Integer countByInstitutionCodeAndGenStatusAndLocalDateTimeLessThanEqual(int var1, int var2, LocalDateTime var3);

    public List<JaywanAcqTxnWorkEntity> findByInstitutionCodeAndIntCodeAndGenStatus(Integer var1, Integer var2, Integer var3);

    public List<JaywanAcqTxnWorkEntity> findByInstitutionCodeAndIntCodeAndGenStatusAndLocalDateTimeBetween(Integer var1, Integer var2, Integer var3, LocalDateTime var4, LocalDateTime var5);

    public List<JaywanAcqTxnWorkEntity> findByInstitutionCodeAndIntCodeAndGenStatusAndLocalDateTimeLessThanEqual(Integer var1, Integer var2, Integer var3, LocalDateTime var4);

    public List<JaywanAcqTxnWorkEntity> findByInstitutionCodeAndGenStatus(Integer var1, Integer var2);

    public List<JaywanAcqTxnWorkEntity> findByInstitutionCodeAndFileID(Integer var1, String var2);

    public List<JaywanAcqTxnWorkEntity> findByRrn(String var1);
}

