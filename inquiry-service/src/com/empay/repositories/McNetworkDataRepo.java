/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.entities.McNetworkDataEntity
 *  com.empay.repositories.McNetworkDataRepo
 *  org.springframework.data.jpa.repository.JpaRepository
 *  org.springframework.data.jpa.repository.JpaSpecificationExecutor
 */
package com.empay.repositories;

import com.empay.entities.McNetworkDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface McNetworkDataRepo
extends JpaRepository<McNetworkDataEntity, Integer>,
JpaSpecificationExecutor<McNetworkDataEntity> {
}

