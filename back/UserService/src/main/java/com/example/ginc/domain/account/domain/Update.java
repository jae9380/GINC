package com.example.ginc.domain.account.domain;

import lombok.Builder;

@Builder
public record Update(
        String password,
        String name,
        String birth
) {
}
