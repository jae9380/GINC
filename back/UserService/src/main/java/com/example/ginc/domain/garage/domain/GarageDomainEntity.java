package com.example.ginc.domain.garage.domain;

import com.example.ginc.domain.garage.infrastructure.entity.type.FuelType;
import com.example.ginc.domain.garage.infrastructure.entity.type.Manufacturer;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class GarageDomainEntity {

    private final Long id;
	private final Long user_id;
	private final Long car_id;
	private final Long totalDrivingDistance;  //  총 주행 거리
	private final Long totalFuelCost;  //  총 주유 금액
	private final Long totalFuelConsumption;  //  총 연료 소모량
	private final Long lastEngineOilChange;  //  최근 엔진 오일 교체
	private final Long lastTransmissionOilChange;  //  최근 미션 오일
	private final Long lastSparkPlugAndCableReplacement;  //  최근 점화 플러그 및 케이블
	private final Long lastBrakeFluidChange;  //  최근 브레이크 액
	private final Long lastChangeBatteryDate;  //  배터리 교체 날짜

	@Builder
    public GarageDomainEntity(Long id, Long user_id, Long car_id,
							  Long totalDrivingDistance, Long totalFuelCost, Long totalFuelConsumption,
							  Long lastEngineOilChange, Long lastTransmissionOilChange, Long lastSparkPlugAndCableReplacement,
							  Long lastBrakeFluidChange, Long lastChangeBatteryDate) {
        this.id = id;
		this.user_id = user_id;
		this.car_id = car_id;
        this.totalDrivingDistance = totalDrivingDistance;
        this.totalFuelCost = totalFuelCost;
        this.totalFuelConsumption = totalFuelConsumption;
        this.lastEngineOilChange = lastEngineOilChange;
        this.lastTransmissionOilChange = lastTransmissionOilChange;
        this.lastSparkPlugAndCableReplacement = lastSparkPlugAndCableReplacement;
        this.lastBrakeFluidChange = lastBrakeFluidChange;
        this.lastChangeBatteryDate = lastChangeBatteryDate;
    }


}
