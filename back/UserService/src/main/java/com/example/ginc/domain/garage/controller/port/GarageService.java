package com.example.ginc.domain.garage.controller.port;

import com.example.ginc.domain.garage.domain.*;

public interface GarageService {
    void garageRegistration(RegisterVehicle request, Long user_id);
    void registrationOfInfo(RegisterConsumables request, Long user_id);
    Long getCar_IdByUser_Id(Long user_id);
    GarageDomainEntity getByUser_Id(Long id);
    void deleteByUser_Id(Long user_id);
    void refuelingEvent(RefuelDomainEntity refueling, Long user_id);
//    void modifyRefuelingRecord(RefuelDomainEntity refueling, Long user_id);
//    void deleteRefuelingRecord(RefuelDomainEntity entity, Long userId);
}
