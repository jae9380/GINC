package com.example.ginc.util.auth.infrastructure.entity;

import com.example.ginc.util.auth.domain.RefreshToken;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class RefreshTokenJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "user_id", nullable = false, unique = true)
    private Long userId;

    @Column(name = "refresh_token", nullable = false)
    private String refreshToken;

    public static RefreshTokenJpa from(RefreshToken refreshToken) {
     RefreshTokenJpa refreshTokenJpa = new RefreshTokenJpa();
     refreshTokenJpa.id= refreshToken.getId();
     refreshTokenJpa.userId= refreshToken.getUserId();
     refreshTokenJpa.refreshToken= refreshToken.getRefreshToken();
     return refreshTokenJpa;
    }

    public  RefreshToken to() {
        return RefreshToken.builder()
                .id(id)
                .userId(userId)
                .refreshToken(refreshToken)
                .build();
    }
}
