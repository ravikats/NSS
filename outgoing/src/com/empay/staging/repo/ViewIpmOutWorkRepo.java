/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.staging.entities.ViewIpmOutWorkEntity
 *  com.empay.staging.repo.ViewIpmOutWorkRepo
 *  org.springframework.data.jpa.repository.JpaRepository
 */
package com.empay.staging.repo;

import com.empay.staging.entities.ViewIpmOutWorkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViewIpmOutWorkRepo
extends JpaRepository<ViewIpmOutWorkEntity, Integer> {
}

