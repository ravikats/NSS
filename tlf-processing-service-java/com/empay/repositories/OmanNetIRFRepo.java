// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.repositories;

import com.empay.entities.OmanNetIRFEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OmanNetIRFRepo extends JpaRepository<OmanNetIRFEntity, Integer>
{
    boolean existsByMccAndRouteAndSubRouteAndCardType(final String mccCode, final String route, final String subRoute, final Character cardType);
    
    boolean existsBySerialNumber(final Integer serialNumber);
    
    OmanNetIRFEntity findByRouteAndSubRouteAndCardTypeAndMcc(final String route, final String subRoute, final Character cardType, final String mcc);
    
    OmanNetIRFEntity findBySegment(final String segment);
}
