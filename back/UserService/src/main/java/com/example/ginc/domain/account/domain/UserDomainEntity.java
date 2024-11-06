package com.example.ginc.domain.account.domain;

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

    public static UserDomainEntity create(SignUp signUp, String password, ClockHolder clockHolder) {
        return UserDomainEntity.builder()
                .username(signUp.username())
                .password(password)
                .name(signUp.name())
                .phoneNumber(signUp.phoneNumber())
                .email(signUp.email())
                .gender(signUp.gender())
                .birth(LocalDate.parse(signUp.birth()))
                .role(signUp.username().equals("admin")?ADMIN:GUEST)
                .createdAt(clockHolder.now())
                .build();
    }

    public UserDomainEntity update(Update update, ClockHolder clockHolder) {
        return UserDomainEntity.builder()
                .id(id)
                .username(username)
                .password(update.password())
                .name(update.name())
                .phoneNumber(phoneNumber)
                .email(email)
                .gender(gender)
                .birth(LocalDate.parse(update.birth()))
                .role(role)
                .authenticated(authenticated)
                .createdAt(createdAt)
                .modifiedAt(clockHolder.now())
                .build();
    }
}
