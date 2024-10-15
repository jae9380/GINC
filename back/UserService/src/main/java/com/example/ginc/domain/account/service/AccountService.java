package com.example.ginc.domain.account.service;

import com.example.ginc.domain.account.dto.SignInRequest;
import com.example.ginc.domain.account.dto.SignUpRequest;
import com.example.ginc.domain.account.dto.UpdateRequest;
import org.springframework.transaction.annotation.Transactional;

public interface AccountService {
    void signup(SignUpRequest request);

    void updateUserInfo(Long id, UpdateRequest request);

    void login(SignInRequest signInRequest);
}
