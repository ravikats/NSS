// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.repositories;

import com.empay.entities.BusinessDateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessDateRepo extends JpaRepository<BusinessDateEntity, Integer>
{
    BusinessDateEntity findByInsCode(final Integer insCode);
}
