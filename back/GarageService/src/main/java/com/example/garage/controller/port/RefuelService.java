package com.example.garage.controller.port;

import com.example.garage.domain.RefuelDomainEntity;
import com.example.garage.domain.Refueling;

import java.util.List;

public interface RefuelService {
    void refueling(Refueling refueling, Long user_id);
    List<RefuelDomainEntity> getList(Long user_id);
    RefuelDomainEntity getById(Long refueling_id);
    void modifyRefueling(Long user_id, Long refueling_id, Refueling refueling);
    void deleteRefueling(Long user_id, Long refueling_id);
    void deleteByCar_Id(Long car_id);
}
