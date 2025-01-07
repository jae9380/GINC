package com.example.garage.controller.response;

import com.example.garage.domain.RefuelDomainEntity;
import com.example.garage.infrastructure.entity.type.FuelType;

public record RefuelResponse(
        Long id,
        int segmentTotalDistance, // 특정 구간의 총 주행 거리
        int totalRefuelingCost,   // 총 주유 비용
        FuelType fuelType,        // 연료 종류
        int costPerLiter,         // 리터당 연료 가격
        int refuelingVolume,      // 주유량
        long refuelingAt          // 주유 날짜
) {
    public static RefuelResponse from(RefuelDomainEntity entity) {
        return new RefuelResponse(
                entity.getId(),
                entity.getSegmentTotalDistance(),
                entity.getTotalRefuelingCost(),
                entity.getFuelType(),
                entity.getCostPerLiter(),
                entity.getRefuelingVolume(),
                entity.getRefuelingAt()
        );
    }
}
