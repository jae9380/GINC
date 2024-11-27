package com.example.ginc.domain.garage.infrastructure.entity.type;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Manufacturer {
    HYUNDAI("현대"),
    KIA("기아"),
    CHEVROLET("쉐보레(GM대우)"),
    SSANGYONG("쌍용"),
    GENESIS("제네시스"),
    BMW("BMW"),
    AUDI("아우디"),
    VOLKSWAGEN("폭스바겐"),
    MERCEDES_BENZ("벤츠"),
    RENAULT_SAMSUNG("르노 삼성"),
    FORD("포드"),
    MINI("미니"),
    TOYOTA("토요타"),
    LEXUS("렉서스");

    private final String koreanName;
}