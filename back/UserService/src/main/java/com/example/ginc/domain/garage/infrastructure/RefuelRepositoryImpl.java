package com.example.ginc.domain.garage.infrastructure;

import com.example.ginc.domain.garage.domain.RefuelDomainEntity;
import com.example.ginc.domain.garage.exception.GarageException;
import com.example.ginc.domain.garage.infrastructure.entity.RefuelJpaEntity;
import com.example.ginc.domain.garage.service.port.RefuelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RefuelRepositoryImpl implements RefuelRepository {
    private final RefuelJpaRepository refuelJpaRepository;
    @Override
    public RefuelDomainEntity save(RefuelDomainEntity entity) {
        return refuelJpaRepository.save(RefuelJpaEntity.from(entity)).to();
    }

    @Override
    public List<RefuelDomainEntity> getByCarId(Long car_id) {
        return findByCarId(car_id)
                .orElseThrow(GarageException.RefuelingRecordNotFoundException::new);
    }

    private Optional<List<RefuelDomainEntity>> findByCarId(Long car_id) {
        return refuelJpaRepository.findByCarId(car_id)
                .map(list -> list.stream()
                        .map(RefuelJpaEntity::to)
                        .toList());
    }
}
