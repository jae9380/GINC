package com.example.ginc.domain.garage.service.port;

import com.example.ginc.domain.garage.domain.CarDomainEntity;

public interface CarRepository {
    CarDomainEntity save(CarDomainEntity entity);
    void deleteById(Long car_id);
}
