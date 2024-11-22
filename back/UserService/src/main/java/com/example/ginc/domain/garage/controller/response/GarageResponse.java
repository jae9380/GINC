package com.example.ginc.domain.garage.controller.response;

import com.example.ginc.domain.garage.domain.GarageDomainEntity;

public record GarageResponse (
        Long id,
        Long user_id,
        Long car_id,
        Long totalDrivingDistance,
        Long serviceDrivingDistance,
        Long serviceTotalFuelCost,
        Long serviceTotalFuelConsumption,
        Long lastEngineOilChange,
        Long lastTransmissionOilChange,
        Long lastSparkPlugAndCableReplacement,
        Long lastBrakeFluidChange,
        Long lastChangeBatteryDate
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
                entity.getLastEngineOilChange(),
                entity.getLastTransmissionOilChange(),
                entity.getLastSparkPlugAndCableReplacement(),
                entity.getLastBrakeFluidChange(),
                entity.getLastChangeBatteryDate()
        );
    }
}
