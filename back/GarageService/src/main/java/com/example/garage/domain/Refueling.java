package com.example.garage.domain;

import com.example.garage.infrastructure.entity.type.FuelType;

public record Refueling(
        Integer segmentTotalDistance, // 특정 구간의 총 주행 거리
        Integer totalRefuelingCost, // 총 주유 비용
        FuelType fuelType, // 연료 종류
        Integer costPerLiter, // 리터당 연료 가격
        Integer refuelingVolume, // 주유량
        String refuelingAt
) {
}
