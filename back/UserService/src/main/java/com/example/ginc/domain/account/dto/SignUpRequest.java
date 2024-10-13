package com.example.ginc.domain.account.dto;

import com.example.ginc.domain.account.entity.type.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

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
