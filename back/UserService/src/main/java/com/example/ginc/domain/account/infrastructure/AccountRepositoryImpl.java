package com.example.ginc.domain.account.infrastructure;

import com.example.ginc.domain.account.domain.UserDomainEntity;
import com.example.ginc.domain.account.infrastructure.entity.UserJpaEntity;
import com.example.ginc.domain.account.service.port.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AccountRepositoryImpl implements AccountRepository {
    private final AccountJpaRepository accountJpaRepository;

    @Override
    public Optional<UserDomainEntity> findByUsername(String username) {
        return accountJpaRepository.findByUsername(username)
                .map(UserJpaEntity::to);
    }

    @Override
    public Optional<UserDomainEntity> findById(long id) {
        return Optional.empty();
    }

    @Override
    public UserDomainEntity save(UserDomainEntity user) {
        return accountJpaRepository.save(UserJpaEntity.from(user)).to();
    }
}
