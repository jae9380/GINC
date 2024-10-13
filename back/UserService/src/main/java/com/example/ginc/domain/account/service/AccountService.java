package com.example.ginc.domain.account.service;

import com.example.ginc.domain.account.dto.SignUpRequest;
import org.springframework.transaction.annotation.Transactional;

public interface AccountService {
    void signup(SignUpRequest request);
}
