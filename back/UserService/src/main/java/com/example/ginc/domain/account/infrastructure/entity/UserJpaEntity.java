package com.example.ginc.domain.account.infrastructure.entity;


import com.example.ginc.domain.account.domain.UserDomainEntity;
import com.example.ginc.domain.account.infrastructure.entity.type.Gender;
import com.example.ginc.domain.account.infrastructure.entity.type.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Clock;
import java.time.LocalDate;

@Entity
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class UserJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "phoneNumber")
    private int phoneNumber;

    @Email
    @Column(name = "email")
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Column(name = "birth")
    private LocalDate birth;

    @Column(name = "role")
    private Role role;

    @Column(name = "authenticated")
    private boolean authenticated;

    @Column(name = "createdAt")
    private LocalDate createdAt;

    @Column(name = "modifiedAt")
    private LocalDate modifiedAt;

    public static UserJpaEntity from(UserDomainEntity entity) {
        UserJpaEntity userJpaEntity = new UserJpaEntity();
        userJpaEntity.id=entity.getId();
        userJpaEntity.username=entity.getUsername();
        userJpaEntity.password=entity.getPassword();
        userJpaEntity.name=entity.getName();
        userJpaEntity.phoneNumber=entity.getPhoneNumber();
        userJpaEntity.email=entity.getEmail();
        userJpaEntity.gender=entity.getGender();
        userJpaEntity.birth=entity.getBirth();
        userJpaEntity.role=entity.getRole();
        userJpaEntity.authenticated=entity.isAuthenticated();
        userJpaEntity.createdAt=entity.getCreatedAt();
        userJpaEntity.modifiedAt=entity.getModifiedAt();
        return userJpaEntity;
    }

    public UserDomainEntity to() {
        return UserDomainEntity.builder()
                .id(id)
                .username(username)
                .password(password)
                .name(name)
                .phoneNumber(phoneNumber)
                .email(email)
                .gender(gender)
                .birth(birth)
                .role(role)
                .authenticated(authenticated)
                .createdAt(createdAt)
                .modifiedAt(modifiedAt)
                .build();
    }
}
