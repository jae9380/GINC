package com.example.ginc.domain.batch.eventListener.port;

import com.example.ginc.domain.garage.domain.RefuelDomainEntity;

public interface BatchService {
    void refueling(RefuelDomainEntity entity);
}
