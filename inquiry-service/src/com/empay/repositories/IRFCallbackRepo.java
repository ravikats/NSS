/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.entities.IRFCallbackEntity
 *  com.empay.repositories.IRFCallbackRepo
 *  org.springframework.data.jpa.repository.JpaRepository
 *  org.springframework.stereotype.Repository
 */
package com.empay.repositories;

import com.empay.entities.IRFCallbackEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRFCallbackRepo
extends JpaRepository<IRFCallbackEntity, Integer> {
    public IRFCallbackEntity findByRefSerNumber(Integer var1);
}

