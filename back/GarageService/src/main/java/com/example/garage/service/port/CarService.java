package com.example.garage.service.port;

import com.example.garage.domain.RegisterVehicle;

public interface CarService {
    Long vehicleRegistration(RegisterVehicle request);
    void deleteById(Long car_id);
}
