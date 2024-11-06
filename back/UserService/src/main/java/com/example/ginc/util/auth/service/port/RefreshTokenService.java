package com.example.ginc.util.auth.service.port;

public interface RefreshTokenService {
    void save(Long userId, String newRefreshToken);
}
