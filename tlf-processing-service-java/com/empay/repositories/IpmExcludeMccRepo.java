// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.repositories;

import java.util.List;
import com.empay.entities.IpmExcludeMccEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IpmExcludeMccRepo extends JpaRepository<IpmExcludeMccEntity, Integer>
{
    List<IpmExcludeMccEntity> findByIicSerNumberAndExcpMcc(final Integer iicSerNumber, final String excpMcc);
}
