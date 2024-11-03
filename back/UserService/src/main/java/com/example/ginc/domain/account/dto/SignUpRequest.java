package com.example.ginc.domain.account.dto;

import com.example.ginc.domain.account.infrastructure.entity.type.Gender;
import lombok.Getter;

import java.time.LocalDate;

public record SignUpRequest(
        Long id,
        String username,
        String password,
        String name,
        int phoneNumber,
        String email,
        Gender gender,
        LocalDate birth

) {
}
