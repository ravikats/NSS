// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.repositories;

import org.springframework.stereotype.Repository;
import com.empay.entities.JaywanAcqTxnWorkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface JaywanAcqTxnWorkRepo extends JpaRepository<JaywanAcqTxnWorkEntity, Integer>
{
    JaywanAcqTxnWorkEntity findByRrn(final String rrn);
}
