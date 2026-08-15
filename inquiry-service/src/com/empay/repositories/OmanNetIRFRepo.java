/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.entities.OmanNetIRFEntity
 *  com.empay.repositories.OmanNetIRFRepo
 *  org.springframework.data.jpa.repository.JpaRepository
 */
package com.empay.repositories;

import com.empay.entities.OmanNetIRFEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OmanNetIRFRepo
extends JpaRepository<OmanNetIRFEntity, Integer> {
}

