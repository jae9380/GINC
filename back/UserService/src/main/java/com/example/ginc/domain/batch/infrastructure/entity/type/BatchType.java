package com.example.ginc.domain.batch.infrastructure.entity.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BatchType {
    GARAGE("차고"),
    REFUEL("주유");

    private final String koreanName;
}