package com.example.garage.infrastructure;

import com.example.garage.infrastructure.entity.RefuelJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RefuelJpaRepository extends JpaRepository<RefuelJpaEntity, Long> {
    Optional<List<RefuelJpaEntity>> findByCarId(Long car_id);
    void deleteByCarId(Long car_id);
}
