package com.example.ginc.domain.garage.service.port;

import com.example.ginc.domain.garage.domain.GarageDomainEntity;

public interface GarageRepository {
    GarageDomainEntity save(GarageDomainEntity entity);
}
