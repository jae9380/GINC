package com.example.garage.domain;

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
        String lastBatteryChangeDate, // "yyyy-MM-dd" 형식
        Long lastBatteryChange

) {
}
