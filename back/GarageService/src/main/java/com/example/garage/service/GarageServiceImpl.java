package com.example.garage.service;

import com.example.garage.controller.port.GarageService;
import com.example.garage.domain.*;
import com.example.garage.exception.GarageException;
import com.example.garage.policy.GaragePolicy;
import com.example.garage.service.port.CarService;
import com.example.garage.service.port.GarageRepository;
import com.example.ginc.util.commone.service.port.ClockHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

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
