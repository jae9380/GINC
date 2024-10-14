package com.example.ginc.domain.account.service;

import com.example.ginc.domain.account.dto.SignUpRequest;
import com.example.ginc.domain.account.dto.UpdateRequest;
import com.example.ginc.domain.account.entity.Member;
import com.example.ginc.domain.account.exception.AccountException;
import com.example.ginc.domain.account.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

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

    @Override
    @Transactional
    public void updateUserInfo(Long id, UpdateRequest request) {
        Member member = findById(id);
        member.updateInfo(
                request.password(), request.name(),
                request.birth(), LocalDate.now()
        );

    }

    private Member findById(long id) {
        return accountRepository.findById(id).orElseThrow(AccountException.MemberNotFoundException::new);
    }
}
