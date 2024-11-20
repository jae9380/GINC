package com.example.ginc.domain.garage.service;

import com.example.ginc.domain.garage.controller.port.GarageService;
import com.example.ginc.domain.garage.controller.port.RefuelService;
import com.example.ginc.domain.garage.domain.GarageDomainEntity;
import com.example.ginc.domain.garage.domain.RefuelDomainEntity;
import com.example.ginc.domain.garage.domain.Refueling;
import com.example.ginc.domain.garage.infrastructure.RefuelRepositoryImpl;
import com.example.ginc.util.commone.service.port.ClockHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RefuelServiceImpl implements RefuelService {
    private final RefuelRepositoryImpl refuelRepository;
    private final GarageService garageService;
    private final ClockHolder clockHolder;
    @Override
    @Transactional
    public void refueling(Refueling refueling, Long user_id) {
        Long car_id = garageService.refueling(refueling, user_id);
        RefuelDomainEntity entity =
                refuelRepository.save(RefuelDomainEntity.refueling(refueling, car_id,clockHolder));

    }

}
