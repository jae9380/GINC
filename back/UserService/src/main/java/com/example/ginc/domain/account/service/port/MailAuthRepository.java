package com.example.ginc.domain.account.service.port;

import com.example.ginc.domain.account.infrastructure.entity.AuthCode;

import java.util.Optional;

public interface MailAuthRepository {
    AuthCode save(AuthCode authCode);
    Optional<AuthCode> findByUserId(Long user_id);

}
