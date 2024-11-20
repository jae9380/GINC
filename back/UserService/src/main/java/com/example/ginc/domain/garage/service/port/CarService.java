package com.example.ginc.domain.garage.service.port;

import com.example.ginc.domain.garage.domain.RegisterVehicle;

public interface CarService {
    Long vehicleRegistration(RegisterVehicle request);
}
