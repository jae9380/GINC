package com.example.ginc.domain.garage.domain;

import com.example.ginc.domain.garage.infrastructure.entity.type.FuelType;
import lombok.Builder;

public record Refueling(
        int segmentTotalDistance, // 특정 구간의 총 주행 거리
        int totalRefuelingCost, // 총 주유 비용
        FuelType fuelType, // 연료 종류
        int costPerLiter, // 리터당 연료 가격
        int refuelingVolume // 주유량
) {
}
