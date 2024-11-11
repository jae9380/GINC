package com.example.ginc.domain.garage.infrastructure.entity;

import com.example.ginc.domain.garage.domain.CarDomainEntity;
import com.example.ginc.domain.garage.domain.GarageDomainEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "garage")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class GarageJpaEntity {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@Column(name = "car_id", nullable = false)
	private Long car_id;
	@Column(name = "totalDrivingDistance")
	private Long totalDrivingDistance;  //  총 주행 거리
	@Column(name = "totalFuelCost")
	private Long totalFuelCost;  //  총 주유 금액
	@Column(name = "totalFuelConsumption")
	private Long totalFuelConsumption;  //  총 연료 소모량
	@Column(name = "lastRefuelDate")
	private LocalDate lastRefuelDate;  //  최근 주유 날짜
	@Column(name = "lastRefuelCost")
	private Long lastRefuelCost;  //  최근 주유 금액
	@Column(name = "lastDrivingDistance")
	private Long lastDrivingDistance;  //  최근 주행 거리
	@Column(name = "lastFuelConsumption")
	private Long lastFuelConsumption;  //  최근 연료 소모량
	@Column(name = "lastEngineOilChange")
	private Long lastEngineOilChange;  //  최근 엔진 오일 교체
	@Column(name = "lastTransmissionOilChange")
	private Long lastTransmissionOilChange;  //  최근 미션 오일
	@Column(name = "lastSparkPlugAndCableReplacement")
	private Long lastSparkPlugAndCableReplacement;  //  최근 점화 플러그 및 케이블
	@Column(name = "lastBrakeFluidChange")
	private Long lastBrakeFluidChange;  //  최근 브레이크 액
	@Column(name = "lastChangeBatteryDate")
	private Long lastChangeBatteryDate;  //  배터리 교체 날짜

	public static GarageJpaEntity from(GarageDomainEntity entity) {
		GarageJpaEntity garageJpaEntity = new GarageJpaEntity();
		garageJpaEntity.id=entity.getId();
		garageJpaEntity.car_id=entity.getId();
		garageJpaEntity.totalDrivingDistance=entity.getTotalDrivingDistance();
		garageJpaEntity.totalFuelCost=entity.getTotalFuelCost();
		garageJpaEntity.totalFuelConsumption=entity.getTotalFuelConsumption();
		garageJpaEntity.lastRefuelDate=entity.getLastRefuelDate();
		garageJpaEntity.lastRefuelCost=entity.getLastRefuelCost();
		garageJpaEntity.lastDrivingDistance=entity.getLastDrivingDistance();
		garageJpaEntity.lastFuelConsumption=entity.getLastFuelConsumption();
		garageJpaEntity.lastEngineOilChange=entity.getLastEngineOilChange();
		garageJpaEntity.lastTransmissionOilChange=entity.getLastTransmissionOilChange();
		garageJpaEntity.lastSparkPlugAndCableReplacement=entity.getLastSparkPlugAndCableReplacement();
		garageJpaEntity.lastBrakeFluidChange=entity.getLastBrakeFluidChange();
		garageJpaEntity.lastChangeBatteryDate=entity.getLastChangeBatteryDate();
		return garageJpaEntity;
	}

	public GarageDomainEntity to() {
		return GarageDomainEntity.builder()
				.id(id)
				.car_id(car_id)
				.totalDrivingDistance(totalDrivingDistance)
				.totalFuelCost(totalFuelCost)
				.totalFuelConsumption(totalFuelConsumption)
				.lastRefuelDate(lastRefuelDate)
				.lastRefuelCost(lastRefuelCost)
				.lastDrivingDistance(lastDrivingDistance)
				.lastFuelConsumption(lastFuelConsumption)
				.lastEngineOilChange(lastEngineOilChange)
				.lastTransmissionOilChange(lastTransmissionOilChange)
				.lastSparkPlugAndCableReplacement(lastSparkPlugAndCableReplacement)
				.lastBrakeFluidChange(lastBrakeFluidChange)
				.lastChangeBatteryDate(lastChangeBatteryDate)
				.build();
	}
}
