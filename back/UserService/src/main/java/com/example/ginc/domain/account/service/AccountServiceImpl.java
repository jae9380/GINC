package com.example.ginc.domain.account.service;

import com.example.ginc.domain.account.dto.SignInRequest;
import com.example.ginc.domain.account.dto.SignUpRequest;
import com.example.ginc.domain.account.dto.UpdateRequest;
import com.example.ginc.domain.account.entity.Member;
import com.example.ginc.util.exception.AccountException;
import com.example.ginc.domain.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static com.example.ginc.domain.account.entity.type.Role.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional
    public void signup(SignUpRequest request) {

        accountRepository.findByUsername(request.username())
                .ifPresent(member -> {
                    throw new AccountException.DuplicateUsernameException();
                });

        accountRepository.save(
                Member.createMember(
                        request.username(),
                        bCryptPasswordEncoder.encode(request.password()),
                        request.name(),
                        request.phoneNumber(),
                        request.email(),
                        request.gender(),
                        request.birth(),
                        request.username().toLowerCase().contains("admin")? ADMIN : USER
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

    @Override
    public void login(SignInRequest request) {
        Member member = accountRepository.findByUsername(request.username())
                .orElseThrow(AccountException.MemberNotFoundException::new);

        if (!bCryptPasswordEncoder.matches(request.password(), member.getPassword())) {
            throw new AccountException.InvalidPasswordException();
        }
    }

    private Member findById(long id) {
        return accountRepository.findById(id).orElseThrow(AccountException.MemberNotFoundException::new);
    }
}
