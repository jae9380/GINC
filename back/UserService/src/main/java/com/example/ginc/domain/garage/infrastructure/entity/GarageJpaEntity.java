package com.example.ginc.domain.garage.infrastructure.entity;

import com.example.ginc.domain.garage.domain.GarageDomainEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "garage")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class GarageJpaEntity {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@Column(name = "userId")
	private Long userId;
	@Column(name = "carId")
	private Long carId;
	@Column(name = "totalDrivingDistance")
	private Long totalDrivingDistance;  //  총 주행 거리
	@Column(name = "totalFuelCost")
	private Long totalFuelCost;  //  총 주유 금액
	@Column(name = "totalFuelConsumption")
	private Long totalFuelConsumption;  //  총 연료 소모량
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
		garageJpaEntity.userId =entity.getUser_id();
		garageJpaEntity.carId = entity.getCar_id();
		garageJpaEntity.totalDrivingDistance=entity.getTotalDrivingDistance();
		garageJpaEntity.totalFuelCost=entity.getTotalFuelCost();
		garageJpaEntity.totalFuelConsumption=entity.getTotalFuelConsumption();
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
				.user_id(userId)
				.car_id(carId)
				.totalDrivingDistance(totalDrivingDistance)
				.totalFuelCost(totalFuelCost)
				.totalFuelConsumption(totalFuelConsumption)
				.lastEngineOilChange(lastEngineOilChange)
				.lastTransmissionOilChange(lastTransmissionOilChange)
				.lastSparkPlugAndCableReplacement(lastSparkPlugAndCableReplacement)
				.lastBrakeFluidChange(lastBrakeFluidChange)
				.lastChangeBatteryDate(lastChangeBatteryDate)
				.build();
	}
}
