package com.example.ginc.domain.garage.infrastructure;

import com.example.ginc.domain.garage.infrastructure.entity.CarJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarJpaRepository extends JpaRepository<CarJpaEntity, Long> {
}
