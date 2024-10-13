package com.example.ginc.domain.account.service;

import com.example.ginc.domain.account.dto.SignUpRequest;
import com.example.ginc.domain.account.entity.Member;
import com.example.ginc.domain.account.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    @Override
    @Transactional
    public void signup(SignUpRequest request) {
        accountRepository.save(
                Member.createMember(
                        request.username(),
                        request.password(),
                        request.name(),
                        request.phoneNumber(),
                        request.email(),
                        request.gender(),
                        request.birth()
                )
        );
    }
}
