/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.entities.UaeSwitchIrfEntity
 *  com.empay.repositories.UaeSwitchIrfRepo
 *  org.springframework.data.jpa.repository.JpaRepository
 *  org.springframework.data.jpa.repository.JpaSpecificationExecutor
 *  org.springframework.stereotype.Repository
 */
package com.empay.repositories;

import com.empay.entities.UaeSwitchIrfEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UaeSwitchIrfRepo
extends JpaRepository<UaeSwitchIrfEntity, Integer>,
JpaSpecificationExecutor<UaeSwitchIrfEntity> {
}

