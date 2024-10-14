package com.example.ginc.domain.account.dto;

import com.example.ginc.domain.account.entity.type.Gender;

import java.time.LocalDate;

public record UpdateRequest(
        String password,
        String name,
        LocalDate birth
) {
}
