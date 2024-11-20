package com.example.ginc.domain.garage.domain;

public record RegisterInfo(
        Long lastEngineOilChange,
        Long lastTransmissionOilChange,
        Long lastSparkPlugAndCableReplacement,
        Long lastBrakeFluidChange,
        Long lastChangeBatteryDate
) {
}
