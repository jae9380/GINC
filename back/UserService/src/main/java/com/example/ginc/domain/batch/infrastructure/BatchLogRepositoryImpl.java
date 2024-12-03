package com.example.ginc.domain.batch.infrastructure;

import com.example.ginc.domain.batch.service.port.BatchLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BatchLogRepositoryImpl implements BatchLogRepository {
    private final BatchLogJpaRepository batchLogJpaRepository;
}
