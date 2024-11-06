package com.example.ginc.util.auth.service.port;

import com.example.ginc.util.auth.domain.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository {
    Optional<RefreshToken> findByUserId(Long userId);
    void save(RefreshToken refreshToken);
}
