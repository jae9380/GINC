package com.example.ginc.domain.account.controller.response;

import com.example.ginc.domain.account.domain.UserDomainEntity;
import com.example.ginc.domain.account.infrastructure.entity.type.Gender;
import com.example.ginc.domain.account.infrastructure.entity.type.Role;
import com.example.ginc.util.commone.service.port.ClockHolder;

import java.time.LocalDate;

public record MyProfileResponse(
        String username,
        String password,
        String name,
        String phoneNumber,
        String email,
        Gender gender,
        LocalDate birth,
        boolean authenticated,
        LocalDate createdAt,
        LocalDate modifiedAt
) {
    public static MyProfileResponse from(UserDomainEntity userDomainEntity, ClockHolder clockHolder) {
        return new MyProfileResponse(
                userDomainEntity.getUsername(),
                userDomainEntity.getPassword(),
                userDomainEntity.getName(),
                userDomainEntity.getPhoneNumber(),
                userDomainEntity.getEmail(),
                userDomainEntity.getGender(),
                clockHolder.millisToLocalDate(userDomainEntity.getBirth()),
                userDomainEntity.isAuthenticated(),
                clockHolder.millisToLocalDate(userDomainEntity.getCreatedAt()),
                clockHolder.millisToLocalDate(userDomainEntity.getModifiedAt())
        );
    }
}
