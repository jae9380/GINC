package com.example.ginc.domain.garage.infrastructure;

import com.example.ginc.domain.garage.domain.GarageDomainEntity;
import com.example.ginc.domain.garage.infrastructure.entity.GarageJpaEntity;
import com.example.ginc.domain.garage.service.port.GarageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class GarageRepositoryImpl implements GarageRepository {
    private final GarageJpaRepository garageJpaRepository;

    @Override
    public GarageDomainEntity save(GarageDomainEntity entity) {
        return garageJpaRepository.save(GarageJpaEntity.from(entity)).to();
    }

    @Override
    public Optional<GarageDomainEntity> findByUserId(Long user_id) {
        return garageJpaRepository.findByUserId(user_id).map(GarageJpaEntity::to);
    }

    @Override
    public void deleteByUserId(Long user_id) {
        garageJpaRepository.deleteByUserId(user_id);
    }

    @Override
    public void deleteById(Long id) {
        garageJpaRepository.deleteById(id);
    }
}
