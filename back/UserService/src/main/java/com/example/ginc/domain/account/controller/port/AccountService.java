package com.example.ginc.domain.account.controller.port;

import com.example.ginc.domain.account.domain.UserDomainEntity;
import com.example.ginc.domain.account.dto.SignInRequest;
import com.example.ginc.domain.account.dto.SignUpRequest;
import com.example.ginc.domain.account.dto.UpdateRequest;
import com.example.ginc.domain.account.infrastructure.entity.UserJpaEntity;

public interface AccountService {
    void signup(SignUpRequest request);

    void updateUserInfo(Long id, UpdateRequest request);

    void login(SignInRequest signInRequest);

    UserDomainEntity getByUsername(String username);

    UserDomainEntity getById(Long id);
}
