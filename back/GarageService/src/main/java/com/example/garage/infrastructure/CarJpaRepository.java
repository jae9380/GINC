package com.example.garage.infrastructure;

import com.example.garage.infrastructure.entity.CarJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarJpaRepository extends JpaRepository<CarJpaEntity, Long> {
}
