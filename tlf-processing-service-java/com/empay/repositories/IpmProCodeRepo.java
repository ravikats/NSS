// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.repositories;

import java.util.List;
import com.empay.entities.ipmProCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IpmProCodeRepo extends JpaRepository<ipmProCodeEntity, Integer>
{
    List<ipmProCodeEntity> findByIicSerNumberAndProcCode(final Integer iicSerNumber, final String procCode);
}
