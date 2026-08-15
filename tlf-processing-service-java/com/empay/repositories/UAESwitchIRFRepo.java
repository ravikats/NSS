// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.repositories;

import java.util.Optional;
import org.springframework.stereotype.Repository;
import com.empay.entities.UAESwitchIRFEntity;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UAESwitchIRFRepo extends JpaRepository<UAESwitchIRFEntity, Integer>
{
    boolean existsByMcc(final String mccCode);
    
    void deleteByMcc(final String mccCode);
    
    Optional<UAESwitchIRFEntity> findBySegment(final String segment);
    
    Optional<UAESwitchIRFEntity> findBySegmentAndMcc(final String segment, final String mcc);
    
    UAESwitchIRFEntity findByMcc(final String mcc);
}
