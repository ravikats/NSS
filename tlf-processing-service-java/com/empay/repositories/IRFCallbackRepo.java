// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.repositories;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.empay.entities.IRFCallbackEntity;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface IRFCallbackRepo extends JpaRepository<IRFCallbackEntity, Integer>
{
    List<IRFCallbackEntity> findAllByStatus(final Character status);
    
    IRFCallbackEntity findByRefSerNumberAndSchemeInchgFlag(final Integer serialNumber, final String flag);
}
