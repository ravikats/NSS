// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.repositories;

import org.springframework.stereotype.Repository;
import com.empay.entities.RejectedTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface RejectedTransactionRepo extends JpaRepository<RejectedTransactionEntity, Integer>
{
}
