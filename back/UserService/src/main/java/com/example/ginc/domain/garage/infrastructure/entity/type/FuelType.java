package com.example.ginc.domain.garage.infrastructure.entity.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FuelType {
    GASOLINE("가솔린"),
    DIESEL("디젤"),
    GAS("LPG"),
    HYDROGEN("수소"),
    ELECTRIC("전기");

    private final String koreanName;
}
