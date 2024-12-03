package com.example.ginc.domain.batch.infrastructure;

import com.example.ginc.domain.batch.infrastructure.entity.BatchLogJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BatchLogJpaRepository extends JpaRepository<BatchLogJpaEntity,Long> {
}
