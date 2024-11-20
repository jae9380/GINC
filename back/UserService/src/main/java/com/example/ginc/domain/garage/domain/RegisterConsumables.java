package com.example.ginc.domain.garage.domain;

public record RegisterConsumables(
        Long lastEngineOilChange,
        Long lastTransmissionOilChange,
        Long lastSparkPlugAndCableReplacement,
        Long lastBrakeFluidChange,
        String lastChangeBatteryDate // "yyyy-MM-dd" 형식
) {
}
