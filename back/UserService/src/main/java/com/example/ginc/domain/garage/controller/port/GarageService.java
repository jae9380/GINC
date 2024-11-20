package com.example.ginc.domain.garage.controller.port;

import com.example.ginc.domain.garage.domain.Refueling;
import com.example.ginc.domain.garage.domain.RegisterVehicle;

public interface GarageService {
    void vehicleRegistration(RegisterVehicle request, Long user_id);

    void refueling(Refueling refueling);
}
