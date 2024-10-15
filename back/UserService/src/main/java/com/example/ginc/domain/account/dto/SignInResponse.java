package com.example.ginc.domain.account.dto;

import com.example.ginc.domain.account.entity.Member;
import com.example.ginc.domain.account.entity.type.Gender;

import java.time.LocalDate;

public record SignInResponse(
        String username,
        Gender gender,
        String name,
        String email
) {
    public static SignInResponse from(Member member) {
        return new SignInResponse(
                member.getUsername(),
                member.getGender(),
                member.getName(),
                member.getEmail()
        );
    }
}
