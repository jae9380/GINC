package com.example.ginc.domain.garage.domain;

import com.example.ginc.domain.garage.infrastructure.entity.type.FuelType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class RefuelDomainEntity {
    private Long id;
    private Long car_id;
    private int segmentTotalDistance;
    private int totalRefuelingCost;
    private FuelType fuelType;
    private int costPerLiter;
    private int refuelingVolume;
    private long refuelingAt;


    @Builder
    public RefuelDomainEntity(Long id, Long car_id, int segmentTotalDistance,
                              int totalRefuelingCost, FuelType fuelType, int costPerLiter,
                              int refuelingVolume, long refuelingAt) {
        this.id = id;
        this.car_id = car_id;
        this.segmentTotalDistance = segmentTotalDistance;
        this.totalRefuelingCost = totalRefuelingCost;
        this.fuelType = fuelType;
        this.costPerLiter = costPerLiter;
        this.refuelingVolume = refuelingVolume;
        this.refuelingAt = refuelingAt;
    }
}