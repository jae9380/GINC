package com.example.ginc.domain.garage.service;

import com.example.ginc.domain.garage.controller.port.GarageService;
import com.example.ginc.domain.garage.domain.CarDomainEntity;
import com.example.ginc.domain.garage.domain.GarageDomainEntity;
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
    private final CarRepository carRepository;
    private final GarageRepository garageRepository;

    @Override
    @Transactional
    public void vehicleRegistration(RegisterVehicle request) {
        CarDomainEntity carDomainEntity = carRepository.save(CarDomainEntity.register(request));

        GarageDomainEntity garageDomainEntity = garageRepository.save(GarageDomainEntity.builder()
                .car_id(carDomainEntity.getId())
                .build()
        );

        System.out.println(garageDomainEntity);

    }
}
