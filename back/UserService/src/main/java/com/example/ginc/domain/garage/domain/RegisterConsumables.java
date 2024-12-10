package com.example.ginc.domain.garage.domain;

public record RegisterConsumables(
        Long totalDrivingDistance,
        String lastEngineOilChangeDate,
        Long lastEngineOilChange,
        String lastTransmissionOilChangeDate,
        Long lastTransmissionOilChange,
        String lastBrakeOilChangeDate,
        Long lastBrakeOilChange,
        String lastBrakePadChangeDate,
        Long lastBrakePadChange,
        String lastSparkPlugAndCableReplacementDate,
        Long lastSparkPlugAndCableReplacement,
        String fuelFilterChangeDate,
        Long fuelFilterChange,
        String lastChangeBatteryDate // "yyyy-MM-dd" 형식

) {
}
