package com.example.garage.infrastructure;

import com.example.garage.domain.CarDomainEntity;
import com.example.garage.infrastructure.entity.CarJpaEntity;
import com.example.garage.service.port.CarRepository;
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

    @Override
    public void deleteById(Long car_id) {
        carJpaRepository.deleteById(car_id);
    }
}
