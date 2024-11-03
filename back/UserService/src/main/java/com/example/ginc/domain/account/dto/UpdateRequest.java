package com.example.ginc.domain.account.dto;

import java.time.LocalDate;

public record UpdateRequest(
        String password,
        String name,
        LocalDate birth
) {
}
