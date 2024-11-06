package com.example.ginc.domain.account.domain;

import com.example.ginc.domain.account.infrastructure.entity.type.Gender;
import lombok.Builder;

@Builder
public record SignUp(
        String username,
        String password,
        String name,
        String phoneNumber,
        String email,
        Gender gender,
        String birth

) {
}
