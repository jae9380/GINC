package com.example.ginc.domain.account.service;

import com.example.ginc.domain.account.controller.port.AccountService;
import com.example.ginc.domain.account.domain.UserDomainEntity;
import com.example.ginc.domain.account.dto.SignInRequest;
import com.example.ginc.domain.account.dto.SignUpRequest;
import com.example.ginc.domain.account.dto.UpdateRequest;
import com.example.ginc.domain.account.service.port.AccountRepository;
import com.example.ginc.domain.account.service.port.BCryptPasswordEncoderService;
import com.example.ginc.util.commone.service.port.ClockHolder;
import com.example.ginc.util.exception.AccountException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final BCryptPasswordEncoderService bCryptPasswordEncoderService;
    private final ClockHolder clockHolder;

    @Override
    @Transactional
    public void signup(SignUpRequest request) {
        validationByUsername(request.username());

        String encryptedPassword = bCryptPasswordEncoderService.encrypt(request.password());
        UserDomainEntity userDomain = UserDomainEntity.create(request, encryptedPassword, clockHolder);
        accountRepository.save(userDomain);
    }

    @Override
    @Transactional
    public void updateUserInfo(Long id, UpdateRequest request) {
        UserDomainEntity userDomainEntity = getById(id);
        userDomainEntity = userDomainEntity.update(request, clockHolder);
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
        return findByUsername(username)
                .orElseThrow(AccountException.MemberNotFoundException::new);
    }

    @Override
    public UserDomainEntity getById(Long id) {
        return findById(id)
                .orElseThrow(AccountException.MemberNotFoundException::new);
    }

    private void validationByUsername(String username) {
        findByUsername(username).ifPresent(member -> {
            throw new AccountException.DuplicateUsernameException();
        });
    }
    private Optional<UserDomainEntity> findById(long id) {
        return accountRepository.findById(id);
    }

    private Optional<UserDomainEntity> findByUsername(String username) {
        return accountRepository.findByUsername(username);
    }
}
