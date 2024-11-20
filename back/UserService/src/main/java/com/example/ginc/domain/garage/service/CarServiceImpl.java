package com.example.ginc.domain.garage.service;

import com.example.ginc.domain.garage.domain.CarDomainEntity;
import com.example.ginc.domain.garage.service.port.CarRepository;
import com.example.ginc.domain.garage.service.port.CarService;
import com.example.ginc.domain.garage.domain.RegisterVehicle;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    @Override
    public Long vehicleRegistration(RegisterVehicle request) {
        CarDomainEntity entity = carRepository.save(CarDomainEntity.register(request));
        return entity.getId();
    }
}
