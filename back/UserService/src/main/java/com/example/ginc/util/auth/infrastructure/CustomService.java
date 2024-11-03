package com.example.ginc.util.auth.infrastructure;

import com.example.ginc.domain.account.domain.UserDomainEntity;
import com.example.ginc.domain.account.infrastructure.entity.UserJpaEntity;
import com.example.ginc.domain.account.infrastructure.AccountJpaRepository;
import com.example.ginc.domain.account.service.port.AccountRepository;
import com.example.ginc.util.exception.AccountException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomService {

    private final AccountRepository accountRepository;
    public UserDomainEntity getByUsername(String username) {
        return  accountRepository.findByUsername(username)
                .orElseThrow(AccountException.MemberNotFoundException::new);
    }
}
