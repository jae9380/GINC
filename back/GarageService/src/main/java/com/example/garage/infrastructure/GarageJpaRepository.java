package com.example.garage.infrastructure;

import com.example.garage.infrastructure.entity.GarageJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GarageJpaRepository extends JpaRepository<GarageJpaEntity, Long> {
    Optional<GarageJpaEntity> findByUserId(Long user_id);
    void deleteByUserId(Long user_Id);
}
