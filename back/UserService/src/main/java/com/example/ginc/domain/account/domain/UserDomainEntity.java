package com.example.ginc.domain.account.domain;

import com.example.ginc.domain.account.dto.SignInRequest;
import com.example.ginc.domain.account.dto.SignUpRequest;
import com.example.ginc.domain.account.dto.UpdateRequest;
import com.example.ginc.domain.account.infrastructure.entity.type.Gender;
import com.example.ginc.domain.account.infrastructure.entity.type.Role;
import com.example.ginc.util.commone.service.port.ClockHolder;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

import static com.example.ginc.domain.account.infrastructure.entity.type.Role.*;

@Getter
public class UserDomainEntity {
    private final Long id;
    private final String username;
    private final String password;
    private final String name;
    private final String phoneNumber;
    private final String email;
    private final Gender gender;
    private final LocalDate birth;
    private final Role role;
    private final boolean authenticated;
    private final LocalDate createdAt;
    private final LocalDate modifiedAt;

    @Builder
    public UserDomainEntity(Long id, String username, String password, String name, String phoneNumber, String email, Gender gender, LocalDate birth, Role role, boolean authenticated, LocalDate createdAt, LocalDate modifiedAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.gender = gender;
        this.birth = birth;
        this.role = role;
        this.authenticated = authenticated;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static UserDomainEntity create(SignUpRequest signUpRequest, String password, ClockHolder clockHolder) {
        return UserDomainEntity.builder()
                .username(signUpRequest.username())
                .password(password)
                .name(signUpRequest.name())
                .phoneNumber(signUpRequest.phoneNumber())
                .email(signUpRequest.email())
                .gender(signUpRequest.gender())
                .birth(LocalDate.parse(signUpRequest.birth()))
                .role(signUpRequest.username().equals("admin")?ADMIN:GUEST)
                .createdAt(clockHolder.now())
                .build();
    }

    public UserDomainEntity update(UpdateRequest updateRequest, ClockHolder clockHolder) {
        return UserDomainEntity.builder()
                .id(id)
                .username(username)
                .password(updateRequest.password())
                .name(updateRequest.name())
                .phoneNumber(phoneNumber)
                .email(email)
                .gender(gender)
                .birth(LocalDate.parse(updateRequest.birth()))
                .role(role)
                .authenticated(authenticated)
                .createdAt(createdAt)
                .modifiedAt(clockHolder.now())
                .build();
    }
}
