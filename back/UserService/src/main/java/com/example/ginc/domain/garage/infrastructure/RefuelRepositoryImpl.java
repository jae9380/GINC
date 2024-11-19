package com.example.ginc.domain.garage.infrastructure;

import com.example.ginc.domain.garage.domain.GarageDomainEntity;
import com.example.ginc.domain.garage.infrastructure.entity.GarageJpaEntity;
import com.example.ginc.domain.garage.service.port.GarageRepository;
import com.example.ginc.domain.garage.service.port.RefuelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RefuelRepositoryImpl implements RefuelRepository {
}
