// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.repositories;

import java.util.List;
import com.empay.entities.IpmServiceCodesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IpmServiceCodesRepo extends JpaRepository<IpmServiceCodesEntity, Integer>
{
    List<IpmServiceCodesEntity> findByIicSerNumberAndServiceCode(final Integer iicSerNumber, final Integer serviceCode);
}
