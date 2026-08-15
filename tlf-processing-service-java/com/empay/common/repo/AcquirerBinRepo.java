// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.common.repo;

import com.empay.common.entities.AcquirerBinEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AcquirerBinRepo extends JpaRepository<AcquirerBinEntity, String>
{
    AcquirerBinEntity findByInsCodeAndBinType(final Integer insCode, final Character binType);
}
