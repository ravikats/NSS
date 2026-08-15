// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.common.repo;

import java.util.List;
import com.empay.common.entities.OmanNetBinDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OmanNetBinRepo extends JpaRepository<OmanNetBinDataEntity, Integer>
{
    OmanNetBinDataEntity findByBinNumber(final String binNumber);
    
    List<OmanNetBinDataEntity> findByBinNumberInAndSubRoute(final List<String> binNumbers, final String upperCase);
    
    OmanNetBinDataEntity findByBinNumberAndSubRoute(final String substring, final String network);
}
