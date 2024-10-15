package com.example.ginc.domain.account.service;

import com.example.ginc.domain.account.dto.SignUpRequest;
import com.example.ginc.domain.account.dto.UpdateRequest;
import com.example.ginc.domain.account.entity.Member;
import com.example.ginc.util.exception.AccountException;
import com.example.ginc.domain.account.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static com.example.ginc.domain.account.entity.type.Role.*;

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
        /* TODO
         유저 아이디 기반으로 기존 가입된 유저 유무 확인 로직 추가 ✅

         case1: 기존 유저가 있다면, 커스텀 에러 발생 ✅
         case2: 기존 유저가 없다면, 계정 성공적인 생성 ✅
            -1: 만약 아이디에 "admin"(대소문자 상관 x) 포함 시 관리자 Role
         */
        accountRepository.findByUsername(request.username())
                .ifPresent(member -> {
                    throw new AccountException.DuplicateUsernameException();
                });

        accountRepository.save(
                Member.createMember(
                        request.username(),
                        request.password(),
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

    private Member findById(long id) {
        return accountRepository.findById(id).orElseThrow(AccountException.MemberNotFoundException::new);
    }
}
