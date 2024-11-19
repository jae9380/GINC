package com.example.ginc.domain.garage.infrastructure;

import com.example.ginc.domain.garage.domain.CarDomainEntity;
import com.example.ginc.domain.garage.domain.GarageDomainEntity;
import com.example.ginc.domain.garage.infrastructure.entity.CarJpaEntity;
import com.example.ginc.domain.garage.infrastructure.entity.GarageJpaEntity;
import com.example.ginc.domain.garage.service.port.CarRepository;
import com.example.ginc.domain.garage.service.port.GarageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CarRepositoryImpl implements CarRepository {
    private final CarJpaRepository carJpaRepository;

    @Override
    public CarDomainEntity save(CarDomainEntity entity) {
        return carJpaRepository.save(CarJpaEntity.from(entity)).to();
    }
}
