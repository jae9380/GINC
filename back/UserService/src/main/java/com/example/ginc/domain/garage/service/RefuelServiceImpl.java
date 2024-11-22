package com.example.ginc.domain.garage.service;

import com.example.ginc.domain.garage.controller.port.GarageService;
import com.example.ginc.domain.garage.controller.port.RefuelService;
import com.example.ginc.domain.garage.domain.GarageDomainEntity;
import com.example.ginc.domain.garage.domain.RefuelDomainEntity;
import com.example.ginc.domain.garage.domain.Refueling;
import com.example.ginc.domain.garage.infrastructure.RefuelRepositoryImpl;
import com.example.ginc.domain.garage.service.port.RefuelRepository;
import com.example.ginc.util.commone.service.port.ClockHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RefuelServiceImpl implements RefuelService {
    private final RefuelRepository refuelRepository;
    private final GarageService garageService;
    private final ClockHolder clockHolder;
    @Override
    @Transactional
    public void refueling(Refueling refueling, Long user_id) {
        Long car_id = garageService.refueling(refueling, user_id);
        refuelRepository.save(RefuelDomainEntity.refueling(refueling, car_id,clockHolder));
        // TODO: 유류타입 예외 체크
    }

    @Override
    public List<RefuelDomainEntity> getList(Long user_id) {
        Long car_id = garageService.getCar_IdByUser_Id(user_id);
        return refuelRepository.getByCarId(car_id);
    }

    @Override
    public RefuelDomainEntity getById(Long refueling_id) {
        return refuelRepository.getById(refueling_id);
    }

    @Override
    @Transactional
    public void modifyRefueling(Long refueling_id, Refueling request) {
        RefuelDomainEntity entity = refuelRepository.getById(refueling_id);
        refuelRepository.save(entity.update(request,clockHolder));
    }

    @Override
    @Transactional
    public void deleteRefueling(Long refueling_id) {
        refuelRepository.deleteById(refueling_id);
    }

    @Override
    @Transactional
    public void deleteByCar_Id(Long car_id) {
        refuelRepository.deleteByCarId(car_id);
    }
    // TODO: user_id를 이용하여 데이터 조작 가능 여부 검증

}
