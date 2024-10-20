package com.example.ginc.util.auth.service;

public interface RefreshTokenService {
    void save(Long userId, String newRefreshToken);
}
