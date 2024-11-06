package com.example.ginc.util.auth.infrastructure;

import com.example.ginc.util.auth.domain.RefreshToken;
import com.example.ginc.util.auth.infrastructure.entity.RefreshTokenJpa;
import com.example.ginc.util.auth.service.port.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RefreshTokenRepositoryImpl implements RefreshTokenRepository {

    private final RefreshTokenJpaRepository refreshTokenJpaRepository;

    @Override
    public Optional<RefreshToken> findByUserId(Long id) {
        return refreshTokenJpaRepository.findByUserId(id).map(RefreshTokenJpa::to);
    }

    @Override
    public void save(RefreshToken refreshToken) {
        refreshTokenJpaRepository.save(RefreshTokenJpa.from(refreshToken));
    }
}
