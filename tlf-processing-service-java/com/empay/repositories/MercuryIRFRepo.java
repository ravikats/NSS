// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.repositories;

import org.springframework.stereotype.Repository;
import com.empay.entities.MercuryIRFRateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface MercuryIRFRepo extends JpaRepository<MercuryIRFRateEntity, Integer>
{
    boolean existsByMcc(final String validMcc);
    
    MercuryIRFRateEntity findByMcc(final String mcc);
    
    void deleteByMcc(final String mcc);
}
