/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.staging.entities.McGCOTxnWorkEntity
 *  com.empay.staging.repo.McGCOWorkRepo
 *  org.springframework.data.jpa.repository.JpaRepository
 */
package com.empay.staging.repo;

import com.empay.staging.entities.McGCOTxnWorkEntity;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface McGCOWorkRepo
extends JpaRepository<McGCOTxnWorkEntity, Long> {
    public List<McGCOTxnWorkEntity> findByInsCodeAndGeneralStatus(int var1, int var2);

    public List<McGCOTxnWorkEntity> findByInsCodeAndIntCodeAndGeneralStatus(Integer var1, Integer var2, int var3);

    public List<McGCOTxnWorkEntity> findByInsCodeAndGeneralStatusAndNetworkAndLocalDateTimeLessThanEqual(Integer var1, int var2, String var3, LocalDateTime var4);

    public List<McGCOTxnWorkEntity> findByInsCodeAndGeneralStatusAndNetworkAndLocalDateTimeBetween(Integer var1, int var2, String var3, LocalDateTime var4, LocalDateTime var5);

    public Integer countByInsCodeAndGeneralStatusAndNetworkAndLocalDateTimeLessThanEqual(Integer var1, int var2, String var3, LocalDateTime var4);

    public Integer countByInsCodeAndGeneralStatusAndNetworkAndLocalDateTimeBetween(Integer var1, int var2, String var3, LocalDateTime var4, LocalDateTime var5);
}

