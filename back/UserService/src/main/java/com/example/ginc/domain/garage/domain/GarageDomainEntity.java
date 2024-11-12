package com.example.ginc.domain.garage.domain;

import com.example.ginc.domain.garage.infrastructure.entity.type.FuelType;
import com.example.ginc.domain.garage.infrastructure.entity.type.Manufacturer;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class GarageDomainEntity {

    private final Long id;
	private final Manufacturer manufacturer;
	private final String modelName;
	private final String subModelName;
	private final String manufactureDate;
	private final FuelType fuelType;
	private final Long totalDrivingDistance;  //  총 주행 거리
	private final Long totalFuelCost;  //  총 주유 금액
	private final Long totalFuelConsumption;  //  총 연료 소모량
	private final LocalDate lastRefuelDate;  //  최근 주유 날짜
	private final Long lastRefuelCost;  //  최근 주유 금액
	private final Long lastDrivingDistance;  //  최근 주행 거리
	private final Long lastFuelConsumption;  //  최근 연료 소모량
	private final Long lastEngineOilChange;  //  최근 엔진 오일 교체
	private final Long lastTransmissionOilChange;  //  최근 미션 오일
	private final Long lastSparkPlugAndCableReplacement;  //  최근 점화 플러그 및 케이블
	private final Long lastBrakeFluidChange;  //  최근 브레이크 액
	private final Long lastChangeBatteryDate;  //  배터리 교체 날짜

	@Builder
    public GarageDomainEntity(Long id, Manufacturer manufacturer, String modelName,
							  String subModelName, String manufactureDate, FuelType fuelType,
							  Long totalDrivingDistance, Long totalFuelCost, Long totalFuelConsumption,
							  LocalDate lastRefuelDate, Long lastRefuelCost, Long lastDrivingDistance,
							  Long lastFuelConsumption, Long lastEngineOilChange, Long lastTransmissionOilChange,
							  Long lastSparkPlugAndCableReplacement, Long lastBrakeFluidChange, Long lastChangeBatteryDate) {
        this.id = id;
		this.manufacturer = manufacturer;
		this.modelName = modelName;
		this.subModelName = subModelName;
		this.manufactureDate = manufactureDate;
		this.fuelType = fuelType;
        this.totalDrivingDistance = totalDrivingDistance;
        this.totalFuelCost = totalFuelCost;
        this.totalFuelConsumption = totalFuelConsumption;
        this.lastRefuelDate = lastRefuelDate;
        this.lastRefuelCost = lastRefuelCost;
        this.lastDrivingDistance = lastDrivingDistance;
        this.lastFuelConsumption = lastFuelConsumption;
        this.lastEngineOilChange = lastEngineOilChange;
        this.lastTransmissionOilChange = lastTransmissionOilChange;
        this.lastSparkPlugAndCableReplacement = lastSparkPlugAndCableReplacement;
        this.lastBrakeFluidChange = lastBrakeFluidChange;
        this.lastChangeBatteryDate = lastChangeBatteryDate;
    }

	public static GarageDomainEntity register(RegisterVehicle request) {
		return GarageDomainEntity.builder()
				.manufacturer(request.manufacturer())
				.modelName(request.modelName())
				.subModelName(request.subModelName())
				.manufactureDate(request.manufactureDate())
				.fuelType(request.fuelType())
				.build();
	}
}
