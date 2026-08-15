/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.amex.entities.AmexAcqTxnWorkEntity
 *  com.empay.amex.repo.AmexTxnWorkRepo
 *  org.springframework.data.domain.Sort
 *  org.springframework.data.jpa.repository.JpaRepository
 *  org.springframework.stereotype.Repository
 */
package com.empay.amex.repo;

import com.empay.amex.entities.AmexAcqTxnWorkEntity;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AmexTxnWorkRepo
extends JpaRepository<AmexAcqTxnWorkEntity, Integer> {
    public List<AmexAcqTxnWorkEntity> findByInstitutionCodeAndIntCodeAndGenStatusAndLocalDateTimeBetween(Integer var1, Integer var2, int var3, LocalDateTime var4, LocalDateTime var5);

    public List<AmexAcqTxnWorkEntity> findByInstitutionCodeAndIntCodeAndGenStatusAndLocalDateTimeLessThanEqual(Integer var1, Integer var2, int var3, LocalDateTime var4);

    public Integer countByInstitutionCodeAndGenStatusAndLocalDateTimeBetween(Integer var1, int var2, LocalDateTime var3, LocalDateTime var4);

    public Integer countByInstitutionCodeAndGenStatusAndLocalDateTimeLessThanEqual(Integer var1, int var2, LocalDateTime var3);

    public List<AmexAcqTxnWorkEntity> findAll(Sort var1);

    public List<AmexAcqTxnWorkEntity> findByInstitutionCodeAndIntCodeAndGenStatusAndLocalDateTimeBetweenOrderByMerchantIdAscTerminalIdAscTrlBthNumberAsc(Integer var1, Integer var2, int var3, LocalDateTime var4, LocalDateTime var5);

    public List<AmexAcqTxnWorkEntity> findByInstitutionCodeAndIntCodeAndGenStatusAndLocalDateTimeLessThanEqualOrderByMerchantIdAscTerminalIdAscTrlBthNumberAsc(Integer var1, Integer var2, int var3, LocalDateTime var4);

    public List<AmexAcqTxnWorkEntity> findByInstitutionCodeAndGenStatus(Integer var1, int var2);

    public List<AmexAcqTxnWorkEntity> findByInstitutionCodeAndFileId(Integer var1, String var2);

    public List<AmexAcqTxnWorkEntity> findByInstitutionCodeAndIntCodeAndGenStatusAndLocalDateTimeLessThanEqualOrderByMerchantIdAscTerminalIdAscTrlBthNumberAscMappedMidAsc(Integer var1, Integer var2, int var3, LocalDateTime var4);

    public List<AmexAcqTxnWorkEntity> findByInstitutionCodeAndIntCodeAndGenStatusAndLocalDateTimeBetweenOrderByMerchantIdAscTerminalIdAscTrlBthNumberAscMappedMidAsc(Integer var1, Integer var2, int var3, LocalDateTime var4, LocalDateTime var5);

    public List<AmexAcqTxnWorkEntity> findByInstitutionCodeAndIntCodeAndGenStatus(Integer var1, Integer var2, int var3);
}

