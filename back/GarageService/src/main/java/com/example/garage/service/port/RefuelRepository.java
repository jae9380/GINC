package com.example.garage.service.port;

import com.example.garage.domain.RefuelDomainEntity;

import java.util.List;

public interface RefuelRepository {
    RefuelDomainEntity save(RefuelDomainEntity entity);
    List<RefuelDomainEntity> getByCarId(Long user_id);
    RefuelDomainEntity getById(Long refueling_id);
    void deleteById(Long refueling_id);
    void deleteByCarId(Long car_id);
}
