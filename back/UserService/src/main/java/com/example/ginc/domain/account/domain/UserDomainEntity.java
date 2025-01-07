package com.example.ginc.domain.account.domain;

import com.example.ginc.domain.account.infrastructure.entity.type.Gender;
import com.example.ginc.domain.account.infrastructure.entity.type.Role;
import com.example.service.port.ClockHolder;
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
    private final Long birth;
    private final Role role;
    private final boolean authenticated;
    private final Long createdAt;
    private final Long modifiedAt;

    @Builder
    public UserDomainEntity(Long id, String username, String password, String name,
                            String phoneNumber, String email, Gender gender,
                            Long birth, Role role, boolean authenticated,
                            Long createdAt, Long modifiedAt) {
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
                .birth(clockHolder.parseDateToMillis(signUp.birth()))
                .role(signUp.username().equals("admin")?ADMIN:GUEST)
                .createdAt(clockHolder.millis())
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
                .birth(birth)
                .role(role)
                .authenticated(authenticated)
                .createdAt(createdAt)
                .modifiedAt(clockHolder.millis())
                .build();
    }

    public UserDomainEntity certification() {
        return UserDomainEntity.builder()
                .id(id)
                .username(username)
                .password(password)
                .name(name)
                .phoneNumber(phoneNumber)
                .email(email)
                .gender(gender)
                .birth(birth)
                .role(USER)
                .authenticated(true)
                .createdAt(createdAt)
                .modifiedAt(modifiedAt)
                .build();
    }
}
