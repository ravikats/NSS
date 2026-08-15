// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.common.repo;

import org.springframework.stereotype.Repository;
import com.empay.common.entities.InterfaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface InterfacesRepo extends JpaRepository<InterfaceEntity, Integer>
{
    InterfaceEntity findByIntCategoryAndInsCode(final String string, final Integer insCode);
    
    InterfaceEntity findByIntCode(final Integer intCode);
}
