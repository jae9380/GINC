package com.example.garage.service.port;

import com.example.garage.domain.CarDomainEntity;

public interface CarRepository {
    CarDomainEntity save(CarDomainEntity entity);
    void deleteById(Long car_id);
}
