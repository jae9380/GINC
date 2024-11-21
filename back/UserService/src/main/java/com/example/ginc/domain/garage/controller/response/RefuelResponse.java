package com.example.ginc.domain.garage.controller.response;

import com.example.ginc.domain.garage.domain.RefuelDomainEntity;
import com.example.ginc.domain.garage.infrastructure.entity.type.FuelType;
import lombok.Builder;
import lombok.Getter;

public record RefuelResponse(
        int segmentTotalDistance, // 특정 구간의 총 주행 거리
        int totalRefuelingCost,   // 총 주유 비용
        FuelType fuelType,        // 연료 종류
        int costPerLiter,         // 리터당 연료 가격
        int refuelingVolume,      // 주유량
        long refuelingAt          // 주유 날짜
) {
    public static RefuelResponse from(RefuelDomainEntity entity) {
        return new RefuelResponse(
                entity.getSegmentTotalDistance(),
                entity.getTotalRefuelingCost(),
                entity.getFuelType(),
                entity.getCostPerLiter(),
                entity.getRefuelingVolume(),
                entity.getRefuelingAt()
        );
    }
}
