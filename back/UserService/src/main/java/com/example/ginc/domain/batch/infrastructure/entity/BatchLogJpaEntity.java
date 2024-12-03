package com.example.ginc.domain.batch.infrastructure.entity;

import com.example.ginc.domain.batch.domain.BatchLogDomainEntity;
import com.example.ginc.domain.batch.infrastructure.entity.type.BatchType;
import com.example.ginc.domain.batch.infrastructure.entity.type.OperationType;
import com.example.ginc.domain.garage.domain.CarDomainEntity;
import com.example.ginc.domain.garage.infrastructure.entity.CarJpaEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "batch_log")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class BatchLogJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long timestamp;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BatchType batchType;

    @Column(nullable = false)
    private Boolean success;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OperationType operationType;

    public static BatchLogJpaEntity from(BatchLogDomainEntity entity) {
        BatchLogJpaEntity batchLogJpaEntity = new BatchLogJpaEntity();
        batchLogJpaEntity.id=entity.getId();
        batchLogJpaEntity.userId=entity.getUserId();
        batchLogJpaEntity.timestamp=entity.getTimestamp();
        batchLogJpaEntity.batchType=entity.getBatchType();
        batchLogJpaEntity.success=entity.getSuccess();
        batchLogJpaEntity.operationType=entity.getOperationType();
        return batchLogJpaEntity;
    }

    public BatchLogDomainEntity to() {
        return BatchLogDomainEntity.builder()
                .id(id)
                .userId(userId)
                .timestamp(timestamp)
                .batchType(batchType)
                .success(success)
                .operationType(operationType)
                .build();
    }
}
