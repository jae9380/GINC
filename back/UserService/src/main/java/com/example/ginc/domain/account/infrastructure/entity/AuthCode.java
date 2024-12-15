package com.example.ginc.domain.account.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "authCode")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class AuthCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userId", nullable = false, unique = true)
    private Long userId;

    @Column(name = "authCode")
    private String authCode;

    @Builder
    public AuthCode (Long id, Long user_id, String authCode) {
        this.id=id;
        this.userId=user_id;
        this.authCode=authCode;
    }

    public static AuthCode create(Long user_id, String authCode) {
        return AuthCode.builder()
                .user_id(user_id)
                .authCode(authCode)
                .build();
    }
    public AuthCode change(String newAuthCode) {
        return AuthCode.builder()
                .id(id)
                .user_id(userId)
                .authCode(newAuthCode)
                .build();
    }
}
