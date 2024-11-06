package com.example.ginc.domain.account.service;

import com.example.ginc.domain.account.controller.port.AccountService;
import com.example.ginc.domain.account.domain.UserDomainEntity;
import com.example.ginc.domain.account.domain.SignIn;
import com.example.ginc.domain.account.domain.SignUp;
import com.example.ginc.domain.account.domain.Update;
import com.example.ginc.domain.account.service.port.AccountRepository;
import com.example.ginc.domain.account.service.port.BCryptPasswordEncoderService;
import com.example.ginc.util.commone.service.port.ClockHolder;
import com.example.ginc.domain.account.exception.AccountException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final BCryptPasswordEncoderService bCryptPasswordEncoderService;
    private final ClockHolder clockHolder;

    @Override
    @Transactional
    public void signup(SignUp request) {
        validationByUsername(request.username());

        String encryptedPassword = bCryptPasswordEncoderService.encrypt(request.password());
        UserDomainEntity userDomain = UserDomainEntity.create(request, encryptedPassword, clockHolder);
        accountRepository.save(userDomain);
        log.info("User {} successfully signed up.", request.username());
    }

    @Override
    @Transactional
    public void updateUserInfo(Long id, Update request) {
        UserDomainEntity userDomainEntity = getById(id);
        userDomainEntity = userDomainEntity.update(request, clockHolder);
        accountRepository.save( userDomainEntity);
    }

    @Override
    public void login(SignIn request) {
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
