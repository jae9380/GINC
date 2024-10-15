package com.example.ginc.util.auth.service;

import com.example.ginc.domain.account.entity.Member;
import com.example.ginc.domain.account.repository.AccountRepository;
import com.example.ginc.util.exception.AccountException;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomService {

    private final AccountRepository accountRepository;
    public Member getByUsername(String username) {
        Member member = accountRepository.findByUsername(username)
                .orElseThrow(AccountException.MemberNotFoundException::new);
        return member;
    }
}
