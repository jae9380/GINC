package com.example.ginc.domain.account.controller.response;

import com.example.ginc.domain.account.domain.UserDomainEntity;
import com.example.ginc.domain.account.infrastructure.entity.UserJpaEntity;
import com.example.ginc.domain.account.infrastructure.entity.type.Gender;

public record SignInResponse(
        String username,
        Gender gender,
        String name,
        String email
) {
    public static SignInResponse from(UserDomainEntity userDomainEntity) {
        return new SignInResponse(
                userDomainEntity.getUsername(),
                userDomainEntity.getGender(),
                userDomainEntity.getName(),
                userDomainEntity.getEmail()
        );
    }
}
