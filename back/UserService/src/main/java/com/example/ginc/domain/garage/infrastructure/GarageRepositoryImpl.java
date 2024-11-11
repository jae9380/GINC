package com.example.ginc.domain.garage.infrastructure;

import com.example.ginc.domain.garage.domain.GarageDomainEntity;
import com.example.ginc.domain.garage.infrastructure.entity.GarageJpaEntity;
import com.example.ginc.domain.garage.service.port.GarageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class GarageRepositoryImpl implements GarageRepository {
    private final GarageJpaRepository garageJpaRepository;

    @Override
    public GarageDomainEntity save(GarageDomainEntity entity) {
        return garageJpaRepository.save(GarageJpaEntity.from(entity)).to();
    }
}
