package com.example.ginc.domain.garage.infrastructure;

import com.example.ginc.domain.garage.infrastructure.entity.CarJpaEntity;
import com.example.ginc.domain.garage.infrastructure.entity.RefuelJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefuelJpaRepository extends JpaRepository<RefuelJpaEntity, Long> {
}
