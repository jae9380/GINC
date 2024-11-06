package com.example.ginc.domain.account.service.port;

import com.example.ginc.domain.account.domain.UserDomainEntity;

import java.util.Optional;

public interface AccountRepository {
    Optional<UserDomainEntity> findByUsername(String username);

    Optional<UserDomainEntity> findById(long id);

    UserDomainEntity save(UserDomainEntity user);
}
