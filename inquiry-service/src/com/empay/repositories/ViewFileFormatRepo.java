/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.entities.ViewFileFormatsEntity
 *  com.empay.repositories.ViewFileFormatRepo
 *  org.springframework.data.jpa.repository.JpaRepository
 *  org.springframework.stereotype.Repository
 */
package com.empay.repositories;

import com.empay.entities.ViewFileFormatsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViewFileFormatRepo
extends JpaRepository<ViewFileFormatsEntity, String> {
    public ViewFileFormatsEntity findByFileName(String var1);
}

