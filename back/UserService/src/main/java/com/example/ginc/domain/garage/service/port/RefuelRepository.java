package com.example.ginc.domain.garage.service.port;

import com.example.ginc.domain.garage.domain.RefuelDomainEntity;

import java.util.List;

public interface RefuelRepository {
    RefuelDomainEntity save(RefuelDomainEntity entity);
    List<RefuelDomainEntity> getByCarId(Long user_id);
    RefuelDomainEntity getById(Long refueling_id);
}
