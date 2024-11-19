package com.example.ginc.domain.garage.domain;

import com.example.ginc.domain.garage.infrastructure.entity.type.FuelType;
import lombok.Builder;

@Builder
public record Refueling(
        int segmentTotalDistance,
        int totalRefuelingCost,
        FuelType fuelType,
        int costPerLiter,
        int refuelingVolume
) {
}
