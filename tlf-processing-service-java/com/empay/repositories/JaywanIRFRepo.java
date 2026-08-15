// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.repositories;

import java.util.Optional;
import org.springframework.stereotype.Repository;
import com.empay.entities.JaywanIRFEntity;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface JaywanIRFRepo extends JpaRepository<JaywanIRFEntity, Integer>
{
    JaywanIRFEntity findByMcc(final String mcc);
    
    Optional<JaywanIRFEntity> findBySegment(final String string);
    
    boolean existsByMcc(final String validMcc);
    
    Optional<JaywanIRFEntity> findBySegmentAndMcc(final String segment, final String mccCode);
    
    void deleteByMcc(final String mcc);
}
