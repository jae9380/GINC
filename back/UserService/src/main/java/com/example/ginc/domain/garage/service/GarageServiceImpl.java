package com.example.ginc.domain.garage.service;

import com.example.ginc.domain.garage.controller.port.GarageService;
import com.example.ginc.domain.garage.domain.*;
import com.example.ginc.domain.garage.exception.GarageException;
import com.example.ginc.domain.garage.service.port.CarRepository;
import com.example.ginc.domain.garage.service.port.CarService;
import com.example.ginc.domain.garage.service.port.GarageRepository;
import com.example.ginc.util.commone.service.port.ClockHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GarageServiceImpl implements GarageService {
    private final GarageRepository garageRepository;
    private final CarService carService;
    private final ClockHolder clockHolder;

    @Override
    @Transactional
    public void garageRegistration(RegisterVehicle request, Long user_id) {
        Long car_id = carService.vehicleRegistration(request);

        garageRepository.save(GarageDomainEntity.builder()
                .user_id(user_id)
                .car_id(car_id)
                .build());
    }

    @Override
    public Long refueling(Refueling refueling, Long user_id) {
        GarageDomainEntity entity = getByUser_Id(user_id);
        entity = entity.refueling(refueling);

        garageRepository.save(entity);
        return entity.getCar_id();
    }

    @Override
    @Transactional
    public void registrationOfInfo(RegisterConsumables request, Long user_id) {
        GarageDomainEntity entity = getByUser_Id(user_id);

        entity = entity.registerConsumables(request, clockHolder);
        garageRepository.save(entity);
    }


    private GarageDomainEntity getByUser_Id(Long user_id) {
        return findByUser_Id(user_id)
                .orElseThrow(GarageException.GarageNotFoundException::new);
    }

    private Optional<GarageDomainEntity> findByUser_Id(Long user_id) {
        return garageRepository.findByUserId(user_id);
    }
}
