package com.example.ginc.domain.garage.service.port;

import com.example.ginc.domain.garage.domain.RefuelDomainEntity;

public interface RefuelRepository {
    RefuelDomainEntity save(RefuelDomainEntity entity);
}
