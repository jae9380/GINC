package com.example.ginc.domain.garage.service.port;

import com.example.ginc.domain.garage.domain.GarageDomainEntity;

import java.util.Optional;

public interface GarageRepository {
    GarageDomainEntity save(GarageDomainEntity entity);
    Optional<GarageDomainEntity> findByUserId(Long user_id);
}
