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
	@Column(name = "serviceDrivingDistance")
	private Long serviceDrivingDistance; // 서비스 이용 시 주행 거리
	@Column(name = "serviceTtotalFuelCost")
	private Long serviceTotalFuelCost;  // 서비스 총 주유 금액
	@Column(name = "serviceTotalFuelConsumption")
	private Long serviceTotalFuelConsumption;  // 서비스 총 연료 소모량
	@Column(name = "lastEngineOilChangeDate")
	private Long lastEngineOilChangeDate;  // 최근 엔진 오일 교체 날짜
	@Column(name = "lastEngineOilChange")
	private Long lastEngineOilChange;  //  최근 엔진 오일 교체
	@Column(name = "lastTransmissionOilChangeDate")
	private Long lastTransmissionOilChangeDate;  // 최근 미션 오일 교체 날짜
	@Column(name = "lastTransmissionOilChange")
	private Long lastTransmissionOilChange;  //  최근 미션 오일
	@Column(name = "lastBrakeOilChangeDate")
	private Long lastBrakeFluidChangeDate;  //  최근 브레이크 액 교체 날짜
	@Column(name = "lastBrakeOilChange")
	private Long lastBrakeFluidChange;  //  최근 브레이크 액
	@Column(name = "lastBrakePadChangeDate")
	private Long lastBrakePadChangeDate;  // 최근 브레이크 패드 교체 날짜
	@Column(name = "lastBrakePadChange")
	private Long lastBrakePadChange;  // 최근 브레이크 패드
	@Column(name = "lastSparkPlugAndCableReplacementDate")
	private Long lastSparkPlugAndCableReplacementDate;  // 최근 점화 플러그 및 케이블 교체 날짜
	@Column(name = "lastSparkPlugAndCableReplacement")
	private Long lastSparkPlugAndCableReplacement;  //  최근 점화 플러그 및 케이블
	@Column(name = "fuelFilterChangeDate")
	private Long fuelFilterChangeDate;  // 최근 연료 필터 교체 날짜
	@Column(name = "fuelFilterChange")
	private Long fuelFilterChange;  // 최근 연료 필터
	@Column(name = "lastBatteryChangeDate")
	private Long lastBatteryChangeDate;  //  배터리 교체 날짜
	@Column(name = "lastBatteryChange")
	private Long lastBatteryChange;  //  배터리 교체 날짜

	public static GarageJpaEntity from(GarageDomainEntity entity) {
		GarageJpaEntity garageJpaEntity = new GarageJpaEntity();
		garageJpaEntity.id=entity.getId();
		garageJpaEntity.userId=entity.getUser_id();
		garageJpaEntity.carId=entity.getCar_id();
		garageJpaEntity.totalDrivingDistance=entity.getTotalDrivingDistance();
		garageJpaEntity.serviceDrivingDistance=entity.getServiceDrivingDistance();
		garageJpaEntity.serviceTotalFuelCost=entity.getServiceTotalFuelCost();
		garageJpaEntity.serviceTotalFuelConsumption=entity.getServiceTotalFuelConsumption();
		garageJpaEntity.lastEngineOilChangeDate=entity.getLastEngineOilChangeDate();
		garageJpaEntity.lastEngineOilChange=entity.getLastEngineOilChange();
		garageJpaEntity.lastTransmissionOilChangeDate=entity.getLastTransmissionOilChangeDate();
		garageJpaEntity.lastTransmissionOilChange=entity.getLastTransmissionOilChange();
		garageJpaEntity.lastBrakeFluidChangeDate=entity.getLastBrakeOilChangeDate();
		garageJpaEntity.lastBrakeFluidChange=entity.getLastBrakeOilChange();
		garageJpaEntity.lastBrakePadChangeDate=entity.getLastBrakePadChangeDate();
		garageJpaEntity.lastBrakePadChange=entity.getLastBrakePadChange();
		garageJpaEntity.lastSparkPlugAndCableReplacementDate=entity.getLastSparkPlugAndCableReplacementDate();
		garageJpaEntity.lastSparkPlugAndCableReplacement=entity.getLastSparkPlugAndCableReplacement();
		garageJpaEntity.fuelFilterChangeDate=entity.getFuelFilterChangeDate();
		garageJpaEntity.fuelFilterChange=entity.getFuelFilterChange();
		garageJpaEntity.lastBatteryChangeDate =entity.getLastBatteryChangeDate();
		garageJpaEntity.lastBatteryChange =entity.getLastBatteryChange();
		return garageJpaEntity;
	}

	public GarageDomainEntity to() {
		return GarageDomainEntity.builder()
				.id(id)
				.user_id(userId)
				.car_id(carId)
				.totalDrivingDistance(totalDrivingDistance)
				.serviceDrivingDistance(serviceDrivingDistance)
				.serviceTotalFuelCost(serviceTotalFuelCost)
				.serviceTotalFuelConsumption(serviceTotalFuelConsumption)
				.lastEngineOilChangeDate(lastEngineOilChangeDate)
				.lastEngineOilChange(lastEngineOilChange)
				.lastTransmissionOilChangeDate(lastTransmissionOilChangeDate)
				.lastTransmissionOilChange(lastTransmissionOilChange)
				.lastBrakeOilChangeDate(lastBrakeFluidChangeDate)
				.lastBrakeOilChange(lastBrakeFluidChange)
				.lastBrakePadChangeDate(lastBrakePadChangeDate)
				.lastBrakePadChange(lastBrakePadChange)
				.lastSparkPlugAndCableReplacementDate(lastSparkPlugAndCableReplacementDate)
				.lastSparkPlugAndCableReplacement(lastSparkPlugAndCableReplacement)
				.fuelFilterChangeDate(fuelFilterChangeDate)
				.fuelFilterChange(fuelFilterChange)
				.lastBatteryChangeDate(lastBatteryChangeDate)
				.lastBatteryChange(lastBatteryChange)
				.build();
	}
}
