// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.repositories;

import org.springframework.stereotype.Repository;
import com.empay.entities.McGCOTxnWorkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface McGCOTxnRepo extends JpaRepository<McGCOTxnWorkEntity, Integer>
{
}
