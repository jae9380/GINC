package com.example.ginc.util.auth.service;

import com.example.ginc.domain.account.infrastructure.entity.UserJpaEntity;
import com.example.ginc.domain.account.infrastructure.AccountJpaRepository;
import com.example.ginc.util.exception.AccountException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomService {

    private final AccountJpaRepository accountJpaRepository;
    public UserJpaEntity getByUsername(String username) {
        UserJpaEntity userJpaEntity = accountJpaRepository.findByUsername(username)
                .orElseThrow(AccountException.MemberNotFoundException::new);
        return userJpaEntity;
    }
}
