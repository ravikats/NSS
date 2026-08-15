/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.entities.MercuryIssAccRangeEntity
 *  com.empay.repositories.MercuryIssAccRangeRepo
 *  org.springframework.data.jpa.repository.JpaRepository
 *  org.springframework.stereotype.Repository
 */
package com.empay.repositories;

import com.empay.entities.MercuryIssAccRangeEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MercuryIssAccRangeRepo
extends JpaRepository<MercuryIssAccRangeEntity, Integer> {
    public List<MercuryIssAccRangeEntity> findByBinRangeLowAndBinRangeHigh(Long var1, Long var2);
}

