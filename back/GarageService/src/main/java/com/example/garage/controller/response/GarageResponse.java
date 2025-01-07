package com.example.garage.controller.response;

import com.example.garage.domain.GarageDomainEntity;

public record GarageResponse (
        Long id,
        Long user_id,
        Long car_id,
        Long totalDrivingDistance,
        Long serviceDrivingDistance,
        Long serviceTotalFuelCost,
        Long serviceTotalFuelConsumption,
        Long lastEngineOilChangeDate,
        Long lastEngineOilChange,
        Long lastTransmissionOilChangeDate,
        Long lastTransmissionOilChange,
        Long lastBrakeOilChangeDate,
        Long lastBrakeOilChange,
        Long lastBrakePadChangeDate,
        Long lastBrakePadChange,
        Long lastSparkPlugAndCableReplacementDate,
        Long lastSparkPlugAndCableReplacement,
        Long fuelFilterChangeDate,
        Long fuelFilterChange,
        Long lastBatteryChangeDate,
        Long lastBatteryChange
){
    public static GarageResponse from(GarageDomainEntity entity) {
        return new GarageResponse(
                entity.getId(),
                entity.getUser_id(),
                entity.getCar_id(),
                entity.getTotalDrivingDistance(),
                entity.getServiceDrivingDistance(),
                entity.getServiceTotalFuelCost(),
                entity.getServiceTotalFuelConsumption(),
                entity.getLastEngineOilChangeDate(),
                entity.getLastEngineOilChange(),
                entity.getLastTransmissionOilChangeDate(),
                entity.getLastTransmissionOilChange(),
                entity.getLastSparkPlugAndCableReplacementDate(),
                entity.getLastSparkPlugAndCableReplacement(),
                entity.getLastBrakeOilChangeDate(),
                entity.getLastBrakeOilChange(),
                entity.getLastBrakePadChangeDate(),
                entity.getLastBrakePadChange(),
                entity.getFuelFilterChangeDate(),
                entity.getLastEngineOilChange(),
                entity.getLastBatteryChangeDate(),
                entity.getLastBatteryChange()
        );
    }
}
