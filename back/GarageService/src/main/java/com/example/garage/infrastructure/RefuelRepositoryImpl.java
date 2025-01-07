package com.example.garage.infrastructure;

import com.example.garage.domain.RefuelDomainEntity;
import com.example.garage.exception.GarageException;
import com.example.garage.infrastructure.entity.RefuelJpaEntity;
import com.example.garage.service.port.RefuelRepository;
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

    @Override
    public RefuelDomainEntity getById(Long refueling_id) {
        return findById(refueling_id)
                .orElseThrow(GarageException.RefuelingRecordNotFoundException::new);
    }

    @Override
    public void deleteById(Long refueling_id) {
        refuelJpaRepository.deleteById(refueling_id);
    }

    @Override
    public void deleteByCarId(Long car_id) {
        refuelJpaRepository.deleteByCarId(car_id);
    }

    private Optional<RefuelDomainEntity> findById(Long refueling_id) {
        return refuelJpaRepository.findById(refueling_id).map(RefuelJpaEntity::to);
    }

    private Optional<List<RefuelDomainEntity>> findByCarId(Long car_id) {
        return refuelJpaRepository.findByCarId(car_id)
                .map(list -> list.stream()
                        .map(RefuelJpaEntity::to)
                        .toList());
    }
}
