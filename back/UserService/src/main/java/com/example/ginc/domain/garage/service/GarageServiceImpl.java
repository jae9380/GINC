package com.example.ginc.domain.garage.service;

import com.example.ginc.domain.garage.controller.port.GarageService;
import com.example.ginc.domain.garage.controller.port.RefuelService;
import com.example.ginc.domain.garage.domain.*;
import com.example.ginc.domain.garage.event.RefuelingEvent;
import com.example.ginc.domain.garage.exception.GarageException;
import com.example.ginc.domain.garage.policy.GaragePolicy;
import com.example.ginc.domain.garage.service.port.CarRepository;
import com.example.ginc.domain.garage.service.port.CarService;
import com.example.ginc.domain.garage.service.port.GarageRepository;
import com.example.service.port.ClockHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static com.example.ginc.domain.garage.domain.ReplacementPartType.ENGINE_OIL;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class GarageServiceImpl implements GarageService {
    private final GarageRepository garageRepository;
    private final CarService carService;
    private final ClockHolder clockHolder;
    private final GaragePolicy garagePolicy;

    @Override
    @Transactional
    public void garageRegistration(RegisterVehicle request, Long user_id) {
        Long car_id = carService.vehicleRegistration(request);

        garageRepository.save(GarageDomainEntity.builder()
                .user_id(user_id)
                .car_id(car_id)
                .build());
    }

    @Override
    @Transactional
    public void registrationOfInfo(RegisterConsumables request, Long user_id) {
        GarageDomainEntity entity = getByUser_Id(user_id);

        garagePolicy.verifyRecordCreationPermissions(entity, user_id);

        entity = entity.registerConsumables(request, clockHolder);
        garageRepository.save(entity);
    }

    @Override
    public Long getCar_IdByUser_Id(Long user_id) {
        GarageDomainEntity entity = getByUser_Id(user_id);

        garagePolicy.verifyRecordCreationPermissions(entity, user_id);

        if (entity.getCar_id() == null) {
            throw new  GarageException.CarNotFoundException();
        }

        return entity.getCar_id();
    }


    @Override
    public GarageDomainEntity getByUser_Id(Long user_id) {
        return findByUser_Id(user_id)
                .orElseThrow(GarageException.GarageNotFoundException::new);
    }

    @Override
    @Transactional
    public void deleteByUser_Id(Long user_id) {
        GarageDomainEntity entity = getByUser_Id(user_id);

        garageRepository.deleteById(entity.getId());
        carService.deleteById(entity.getCar_id());
    }

    @Override
    public void refuelingEvent(RefuelDomainEntity refueling, Long user_id) {
        GarageDomainEntity entity = getByUser_Id(user_id);

        // TODO: user_id을 기준으로 Garage 불러오고, Garage의 user_id를 비교?
        garagePolicy.verifyRecordCreationPermissions(entity, user_id);

        entity = entity.refueling(refueling);

        garageRepository.save(entity);
        checkReplacementCycle(entity);
    }

    private void checkReplacementCycle(GarageDomainEntity entity) {
        Set<ReplacementPartType> replacementResults = garagePolicy.checkAllReplacementCycles(entity);

        replacementResults.forEach(partType -> {
            log.info(partType + " needs replacement.");
            // TODO: 알림기능 구현 시, 부품교체 알림 발생 이벤트 처리
        });
    }
    private Optional<GarageDomainEntity> findByUser_Id(Long user_id) {
        return garageRepository.findByUserId(user_id);
    }
}
