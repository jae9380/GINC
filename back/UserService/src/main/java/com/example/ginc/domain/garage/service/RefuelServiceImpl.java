package com.example.ginc.domain.garage.service;

import com.example.ginc.domain.garage.event.RefuelingEvent;
import com.example.ginc.domain.garage.controller.port.GarageService;
import com.example.ginc.domain.garage.controller.port.RefuelService;
import com.example.ginc.domain.garage.domain.RefuelDomainEntity;
import com.example.ginc.domain.garage.domain.Refueling;
import com.example.ginc.domain.garage.service.port.RefuelRepository;
import com.example.service.port.ClockHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RefuelServiceImpl implements RefuelService {
    private final ApplicationEventPublisher publisher;
    private final RefuelRepository refuelRepository;
    private final GarageService garageService;
    private final ClockHolder clockHolder;
    @Override
    @Transactional
    public void refueling(Refueling refueling, Long user_id) {
        RefuelDomainEntity entity =
                refuelRepository.save(RefuelDomainEntity.refueling(
                        refueling,
                        garageService.getCar_IdByUser_Id(user_id),
                        clockHolder));

        publisher.publishEvent(new RefuelingEvent(this,"UPDATE", user_id, entity));
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
    public void modifyRefueling(Long user_id, Long refueling_id, Refueling request) {
        RefuelDomainEntity entity = refuelRepository.getById(refueling_id);
        int modifiedDistance = (request.segmentTotalDistance() - entity.getSegmentTotalDistance()),
                modifiedCost = request.totalRefuelingCost() - entity.getTotalRefuelingCost(),
                modifiedVolume = (request.refuelingVolume() - entity.getRefuelingVolume());
        entity = entity.update(request,clockHolder);
        refuelRepository.save(entity);

        publisher.publishEvent(new RefuelingEvent(
                this,"MODIFY", user_id,
                RefuelDomainEntity.builder()
                                .segmentTotalDistance(modifiedDistance)
                                .totalRefuelingCost(modifiedCost)
                                .refuelingVolume(modifiedVolume)
                                .build()));
    }

    @Override
    @Transactional
    public void deleteRefueling(Long user_id, Long refueling_id) {
        RefuelDomainEntity entity = getById(refueling_id);

        publisher.publishEvent(new RefuelingEvent(this, "DELETE", user_id,
                RefuelDomainEntity.builder()
                        .segmentTotalDistance(-entity.getSegmentTotalDistance())
                        .totalRefuelingCost(-entity.getTotalRefuelingCost())
                        .refuelingVolume(-entity.getRefuelingVolume())
                        .build()));
        refuelRepository.deleteById(refueling_id);

    }

    @Override
    @Transactional
    public void deleteByCar_Id(Long car_id) {
        refuelRepository.deleteByCarId(car_id);
    }
    // TODO: user_id를 이용하여 데이터 조작 가능 여부 검증

}
