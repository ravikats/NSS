/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.entities.VisaIssAcqRangeEntity
 *  com.empay.repositories.VisaIssAcqRangeRepository
 *  org.springframework.data.jpa.repository.JpaRepository
 *  org.springframework.stereotype.Repository
 */
package com.empay.repositories;

import com.empay.entities.VisaIssAcqRangeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisaIssAcqRangeRepository
extends JpaRepository<VisaIssAcqRangeEntity, Integer> {
    public void deleteAllByJobSerialNumber(Integer var1);
}

