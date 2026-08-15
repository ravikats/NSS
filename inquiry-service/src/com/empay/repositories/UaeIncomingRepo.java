/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.entities.UaeSwitchNetworkDataEntity
 *  com.empay.repositories.UaeIncomingRepo
 *  org.springframework.data.jpa.repository.JpaRepository
 *  org.springframework.data.jpa.repository.JpaSpecificationExecutor
 *  org.springframework.stereotype.Repository
 */
package com.empay.repositories;

import com.empay.entities.UaeSwitchNetworkDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UaeIncomingRepo
extends JpaRepository<UaeSwitchNetworkDataEntity, Integer>,
JpaSpecificationExecutor<UaeSwitchNetworkDataEntity> {
}

