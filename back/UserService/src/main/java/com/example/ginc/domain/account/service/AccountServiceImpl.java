package com.example.ginc.domain.account.service;

import com.example.ginc.domain.account.controller.port.AccountService;
import com.example.ginc.domain.account.domain.UserDomainEntity;
import com.example.ginc.domain.account.dto.SignInRequest;
import com.example.ginc.domain.account.dto.SignUpRequest;
import com.example.ginc.domain.account.dto.UpdateRequest;
import com.example.ginc.domain.account.infrastructure.entity.UserJpaEntity;
import com.example.ginc.domain.account.service.port.AccountRepository;
import com.example.ginc.domain.account.service.port.BCryptPasswordEncoderService;
import com.example.ginc.util.exception.AccountException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final BCryptPasswordEncoderService bCryptPasswordEncoderService;

    @Override
    @Transactional
    public void signup(SignUpRequest request) {
        accountRepository.findByUsername(request.username());

        String encryptedPassword = bCryptPasswordEncoderService.encrypt(request.password());
        UserDomainEntity userDomain = UserDomainEntity.create(request, encryptedPassword);
        accountRepository.save(userDomain);
    }

    @Override
    @Transactional
    public void updateUserInfo(Long id, UpdateRequest request) {
        UserDomainEntity userDomainEntity = findById(id);
        userDomainEntity.update(request);

        accountRepository.save( userDomainEntity);
    }

    @Override
    public void login(SignInRequest request) {
        UserDomainEntity userDomainEntity = getByUsername(request.username());

        if (!bCryptPasswordEncoderService.matches(request.password(), userDomainEntity.getPassword())) {
            throw new AccountException.InvalidPasswordException();
        }
    }

    @Override
    public UserDomainEntity getByUsername(String username) {
        UserDomainEntity userDomainEntity = accountRepository.findByUsername(username)
                .orElseThrow(AccountException.MemberNotFoundException::new);
        return userDomainEntity;
    }

    private UserDomainEntity findById(long id) {
        return accountRepository.findById(id).orElseThrow(AccountException.MemberNotFoundException::new);
    }
}
