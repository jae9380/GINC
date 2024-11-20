package com.example.ginc.domain.garage.infrastructure;

import com.example.ginc.domain.garage.domain.GarageDomainEntity;
import com.example.ginc.domain.garage.domain.RefuelDomainEntity;
import com.example.ginc.domain.garage.infrastructure.entity.GarageJpaEntity;
import com.example.ginc.domain.garage.infrastructure.entity.RefuelJpaEntity;
import com.example.ginc.domain.garage.service.port.GarageRepository;
import com.example.ginc.domain.garage.service.port.RefuelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RefuelRepositoryImpl implements RefuelRepository {
    private final RefuelJpaRepository refuelJpaRepository;
    @Override
    public RefuelDomainEntity save(RefuelDomainEntity entity) {
        return refuelJpaRepository.save(RefuelJpaEntity.from(entity)).to();
    }
}
