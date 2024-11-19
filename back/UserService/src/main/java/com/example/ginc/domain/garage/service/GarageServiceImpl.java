package com.example.ginc.domain.garage.service;

import com.example.ginc.domain.garage.controller.port.GarageService;
import com.example.ginc.domain.garage.domain.CarDomainEntity;
import com.example.ginc.domain.garage.domain.GarageDomainEntity;
import com.example.ginc.domain.garage.domain.Refueling;
import com.example.ginc.domain.garage.domain.RegisterVehicle;
import com.example.ginc.domain.garage.service.port.CarRepository;
import com.example.ginc.domain.garage.service.port.GarageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GarageServiceImpl implements GarageService {
    private final GarageRepository garageRepository;
    private final CarRepository carRepository;

    @Override
    @Transactional
    public void vehicleRegistration(RegisterVehicle request) {
        CarDomainEntity entity = carRepository.save(CarDomainEntity.register(request));

        garageRepository.save(GarageDomainEntity.builder()
                .car_id(entity.getId())
                .build());
    }

    @Override
    @Transactional
    public void refueling(Refueling refueling) {

    }
}
