package com.example.ginc.domain.garage.controller.port;

import com.example.ginc.domain.garage.domain.RefuelDomainEntity;
import com.example.ginc.domain.garage.domain.Refueling;

import java.util.List;

public interface RefuelService {
    void refueling(Refueling refueling, Long user_id);
    List<RefuelDomainEntity> getList(Long user_id);
}
