/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.entities.OutgoingFileLogEntity
 *  com.empay.repositories.OutgoingFileLogRepository
 *  org.springframework.data.jpa.repository.JpaRepository
 *  org.springframework.data.jpa.repository.JpaSpecificationExecutor
 *  org.springframework.stereotype.Repository
 */
package com.empay.repositories;

import com.empay.entities.OutgoingFileLogEntity;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface OutgoingFileLogRepository
extends JpaRepository<OutgoingFileLogEntity, Integer>,
JpaSpecificationExecutor<OutgoingFileLogEntity> {
    public OutgoingFileLogEntity findByBusinessDate(LocalDate var1);

    public List<OutgoingFileLogEntity> findByFileFormats_CodeAndBusinessDate(Integer var1, LocalDate var2);

    public List<OutgoingFileLogEntity> findByFileFormats_SystemFileFormats_CodeAndBusinessDate(Integer var1, LocalDate var2);
}

