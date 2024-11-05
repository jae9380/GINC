package com.example.ginc.domain.account.dto;

import com.example.ginc.domain.account.infrastructure.entity.type.Gender;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
public record SignUpRequest(
        Long id,
        String username,
        String password,
        String name,
        String phoneNumber,
        String email,
        Gender gender,
        LocalDate birth

) {
}
