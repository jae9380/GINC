package com.example.garage.service;

import com.example.garage.domain.CarDomainEntity;
import com.example.garage.domain.RegisterVehicle;
import com.example.garage.service.port.CarRepository;
import com.example.garage.service.port.CarService;
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

    @Override
    public void deleteById(Long car_id) {
        carRepository.deleteById(car_id);
    }
}
