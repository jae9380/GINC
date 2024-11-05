package com.example.ginc.domain.account.dto;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record UpdateRequest(
        String password,
        String name,
        String birth
) {
}
