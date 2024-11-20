package com.example.ginc.domain.garage.controller.port;

import com.example.ginc.domain.garage.domain.*;

public interface GarageService {
    void garageRegistration(RegisterVehicle request, Long user_id);
    Long refueling(Refueling refueling, Long user_id);
    void registrationOfInfo(RegisterConsumables request, Long user_id);
}
