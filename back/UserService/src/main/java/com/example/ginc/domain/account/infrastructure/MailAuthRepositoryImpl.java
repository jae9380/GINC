package com.example.ginc.domain.account.infrastructure;

import com.example.ginc.domain.account.infrastructure.entity.AuthCode;
import com.example.ginc.domain.account.service.port.MailAuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MailAuthRepositoryImpl implements MailAuthRepository {
    private final MailAuthJpaRepository mailAuthJpaRepository;

    @Override
    public AuthCode save(AuthCode authCode) {
        return mailAuthJpaRepository.save(authCode);
    }

    @Override
    public Optional<AuthCode> findByUserId(Long user_id) {
        return mailAuthJpaRepository.findByUserId(user_id);
    }
}
