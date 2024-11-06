package com.example.ginc.util.auth.infrastructure;

import com.example.ginc.util.auth.infrastructure.entity.RefreshTokenJpa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenJpaRepository extends JpaRepository<RefreshTokenJpa, Long> {
    Optional<RefreshTokenJpa> findByUserId(Long userId);
}
