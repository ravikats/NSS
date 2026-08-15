/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.mercury.entities.MercuryAcqTxnWorkEntity
 *  com.empay.mercury.repo.MercuryAcqTxnWorkRepo
 *  org.springframework.data.jpa.repository.JpaRepository
 *  org.springframework.stereotype.Repository
 */
package com.empay.mercury.repo;

import com.empay.mercury.entities.MercuryAcqTxnWorkEntity;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MercuryAcqTxnWorkRepo
extends JpaRepository<MercuryAcqTxnWorkEntity, Integer> {
    public Integer countByInstitutionCodeAndGeneralStatusAndLocalDateTimeLessThanEqual(Integer var1, int var2, LocalDateTime var3);

    public Integer countByInstitutionCodeAndGeneralStatusAndLocalDateTimeBetween(Integer var1, int var2, LocalDateTime var3, LocalDateTime var4);

    public List<MercuryAcqTxnWorkEntity> findByInstitutionCodeAndIntCodeAndGeneralStatusAndLocalDateTimeBetween(Integer var1, Integer var2, int var3, LocalDateTime var4, LocalDateTime var5);

    public List<MercuryAcqTxnWorkEntity> findByInstitutionCodeAndIntCodeAndGeneralStatusAndLocalDateTimeLessThanEqual(Integer var1, Integer var2, Integer var3, LocalDateTime var4);

    public List<MercuryAcqTxnWorkEntity> findByInstitutionCodeAndGeneralStatus(Integer var1, int var2);

    public List<MercuryAcqTxnWorkEntity> findByInstitutionCodeAndIntCodeAndGeneralStatus(Integer var1, Integer var2, int var3);
}

