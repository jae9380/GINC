package com.example.ginc.domain.batch.domain;

import com.example.ginc.domain.batch.infrastructure.entity.type.BatchType;
import com.example.ginc.domain.batch.infrastructure.entity.type.OperationType;
import com.example.ginc.domain.garage.infrastructure.entity.type.FuelType;
import com.example.ginc.domain.garage.infrastructure.entity.type.Manufacturer;
import lombok.Builder;
import lombok.Getter;

@Getter
public class BatchLogDomainEntity {
    private final Long id;
    private final Long userId;
    private final Long timestamp;
    private final BatchType batchType;
    private final Boolean success;
    private final OperationType operationType;

    @Builder
    public BatchLogDomainEntity(Long id, Long userId, Long timestamp,
                                BatchType batchType, Boolean success, OperationType operationType) {
        this.id=id;
        this.userId=userId;
        this.timestamp=timestamp;
        this.batchType=batchType;
        this.success=success;
        this.operationType=operationType;
    }
}
