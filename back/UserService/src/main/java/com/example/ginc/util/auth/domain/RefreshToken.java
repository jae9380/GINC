package com.example.ginc.util.auth.domain;

import com.example.ginc.util.auth.infrastructure.entity.RefreshTokenJpa;
import lombok.Builder;
import lombok.Getter;

@Getter
public class RefreshToken {
    Long id;
    Long userId;
    String refreshToken;

    @Builder
    public RefreshToken(Long id, Long userId, String refreshToken) {
        this.id = id;
        this.userId = userId;
        this.refreshToken = refreshToken;
    }

    public RefreshToken(Long userId, String refreshToken) {
        this.userId = userId;
        this.refreshToken = refreshToken;
    }

    public RefreshToken update(String newRefreshToken) {
        this.refreshToken = newRefreshToken;

        return this;
    }
}
