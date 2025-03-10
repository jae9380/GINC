package com.example.garage.service.port;

import com.example.garage.domain.GarageDomainEntity;

import java.util.Optional;

public interface GarageRepository {
    GarageDomainEntity save(GarageDomainEntity entity);
    Optional<GarageDomainEntity> findByUserId(Long user_id);
    void deleteByUserId(Long id);

    void deleteById(Long id);
}
