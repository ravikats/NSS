// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.common.repo;

import org.springframework.stereotype.Repository;
import com.empay.common.entities.ProcessingLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ProcessLogRepo extends JpaRepository<ProcessingLogEntity, Integer>
{
}
