// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.repositories;

import java.util.Optional;
import java.util.List;
import java.time.LocalDateTime;
import com.empay.entities.UAERefundTranasactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UAERefundTransactionRepo extends JpaRepository<UAERefundTranasactionEntity, Integer>
{
    List<UAERefundTranasactionEntity> findByGenStatusAndLocalDateTimeBetween(final Integer genStatus, final LocalDateTime fromDate, final LocalDateTime toDate);
    
    Optional<UAERefundTranasactionEntity> findBySerialNumber(final Integer serNumber);
    
    Optional<UAERefundTranasactionEntity> findByRrnAndAuthCodeAndTerminalIdAndMerchantId(final String retRefNumber, final String authCode, final String terminalId, final String merchantId);
}
