package com.example.ginc.domain.account.controller.port;

import com.example.ginc.domain.account.controller.response.MyProfileResponse;
import com.example.ginc.domain.account.domain.UserDomainEntity;
import com.example.ginc.domain.account.domain.SignIn;
import com.example.ginc.domain.account.domain.SignUp;
import com.example.ginc.domain.account.domain.Update;

public interface AccountService {
    void signup(SignUp request);

    void updateUserInfo(Long id, Update request);

    void login(SignIn signIn);

    UserDomainEntity getByUsername(String username);

    UserDomainEntity getById(Long id);
    MyProfileResponse getMyProfileById(Long id);
}
