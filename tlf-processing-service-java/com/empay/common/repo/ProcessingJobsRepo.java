// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.common.repo;

import org.springframework.stereotype.Repository;
import com.empay.common.entities.ProcessingJobsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ProcessingJobsRepo extends JpaRepository<ProcessingJobsEntity, Integer>
{
    ProcessingJobsEntity findByProcessSerialNo(final int jobNumber);
}
