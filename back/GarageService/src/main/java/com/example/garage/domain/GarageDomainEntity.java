package com.example.garage.domain;

import com.example.service.port.ClockHolder;
import lombok.Builder;
import lombok.Getter;

@Getter
public class GarageDomainEntity {
    private final Long id;
	private final Long user_id;
	private final Long car_id;
	private final Long totalDrivingDistance;  //  총 주행 거리
	private final Long serviceDrivingDistance; // 서비스 총 주행 거리
	private final Long serviceTotalFuelCost;  // 서비스 총 주유 금액
	private final Long serviceTotalFuelConsumption;  // 서비스 총 연료 소모량
	private final Long lastEngineOilChangeDate; //  최근 엔진 오일 교체 교체 날짜
	private final Long lastEngineOilChange;  //  최근 엔진 오일 교체
	private final Long lastTransmissionOilChangeDate; //  최근 미션 오일 교체 날짜
	private final Long lastTransmissionOilChange;  //  최근 미션 오일
	private final Long lastBrakeOilChangeDate; //  최근 브레이크 액 교체 날짜
	private final Long lastBrakeOilChange;  //  최근 브레이크 액
	private final Long lastBrakePadChangeDate; //  최근 브레이크 패드 교체 날짜
	private final Long lastBrakePadChange;  //  최근 브레이크 패드
	private final Long lastSparkPlugAndCableReplacementDate; //  최근 점화 플러그 및 케이블 교체 날짜
	private final Long lastSparkPlugAndCableReplacement;  //  최근 점화 플러그 및 케이블
	private final Long fuelFilterChangeDate; //  최근 연료 필터 교체 날짜
	private final Long fuelFilterChange;  //  최근 연료 필터
	private final Long lastBatteryChange;  //  배터리 교체 날짜
	private final Long lastBatteryChangeDate;  //  배터리 교체 날짜

	@Builder
    public GarageDomainEntity(Long id, Long user_id, Long car_id, Long totalDrivingDistance,
							  Long serviceDrivingDistance, Long serviceTotalFuelCost, Long serviceTotalFuelConsumption,
							  Long lastEngineOilChange, Long lastTransmissionOilChange, Long lastSparkPlugAndCableReplacement,
							  Long lastBrakeOilChange, Long lastBatteryChange, Long lastBatteryChangeDate, Long lastEngineOilChangeDate,
							  Long lastTransmissionOilChangeDate, Long lastBrakeOilChangeDate, Long lastBrakePadChangeDate,
							  Long lastBrakePadChange, Long lastSparkPlugAndCableReplacementDate, Long fuelFilterChangeDate,
							  Long fuelFilterChange) {
        this.id = id;
		this.user_id = user_id;
		this.car_id = car_id;
        this.totalDrivingDistance = totalDrivingDistance;
		this.serviceDrivingDistance = serviceDrivingDistance;
        this.serviceTotalFuelCost = serviceTotalFuelCost;
        this.serviceTotalFuelConsumption = serviceTotalFuelConsumption;
        this.lastEngineOilChange = lastEngineOilChange;
        this.lastTransmissionOilChange = lastTransmissionOilChange;
        this.lastSparkPlugAndCableReplacement = lastSparkPlugAndCableReplacement;
        this.lastBrakeOilChange = lastBrakeOilChange;
        this.lastBatteryChange = lastBatteryChange;
        this.lastBatteryChangeDate = lastBatteryChangeDate;
		this.lastEngineOilChangeDate = lastEngineOilChangeDate;
		this.lastTransmissionOilChangeDate = lastTransmissionOilChangeDate;
		this.lastBrakeOilChangeDate = lastBrakeOilChangeDate;
		this.lastBrakePadChangeDate = lastBrakePadChangeDate;
		this.lastBrakePadChange = lastBrakePadChange;
		this.lastSparkPlugAndCableReplacementDate = lastSparkPlugAndCableReplacementDate;
		this.fuelFilterChangeDate = fuelFilterChangeDate;
		this.fuelFilterChange = fuelFilterChange;
    }

	public GarageDomainEntity refueling(RefuelDomainEntity entity) {
		return GarageDomainEntity.builder()
				.id(id)
				.user_id(user_id)
				.car_id(car_id)
				.totalDrivingDistance(totalDrivingDistance == null ? entity.getSegmentTotalDistance() : totalDrivingDistance + entity.getSegmentTotalDistance())
				.serviceDrivingDistance(serviceDrivingDistance == null ? entity.getSegmentTotalDistance() : serviceDrivingDistance + entity.getSegmentTotalDistance())
				.serviceTotalFuelCost(serviceTotalFuelCost == null ? entity.getTotalRefuelingCost() : serviceTotalFuelCost + entity.getTotalRefuelingCost())
				.serviceTotalFuelConsumption(serviceTotalFuelConsumption == null ? entity.getRefuelingVolume() : serviceTotalFuelConsumption + entity.getRefuelingVolume())
				.lastEngineOilChange(lastEngineOilChange)
				.lastTransmissionOilChange(lastTransmissionOilChange)
				.lastSparkPlugAndCableReplacement(lastSparkPlugAndCableReplacement)
				.lastBrakeOilChangeDate(lastBrakeOilChangeDate)
				.lastBrakeOilChange(lastBrakeOilChange)
				.lastBatteryChange(lastBatteryChange)
				.lastBatteryChangeDate(lastBatteryChangeDate)
				.lastEngineOilChangeDate(lastEngineOilChangeDate)
				.lastTransmissionOilChangeDate(lastTransmissionOilChangeDate)
				.lastBrakePadChangeDate(lastBrakePadChangeDate)
				.lastBrakePadChange(lastBrakePadChange)
				.lastSparkPlugAndCableReplacementDate(lastSparkPlugAndCableReplacementDate)
				.fuelFilterChangeDate(fuelFilterChangeDate)
				.fuelFilterChange(fuelFilterChange)
				.build();
	}

	public GarageDomainEntity registerConsumables(RegisterConsumables request, ClockHolder clockHolder) {
		return GarageDomainEntity.builder()
				.id(id)
				.user_id(user_id)
				.car_id(car_id)
				.totalDrivingDistance(request.totalDrivingDistance())
				.serviceDrivingDistance(serviceDrivingDistance)
				.serviceTotalFuelCost(serviceTotalFuelCost)
				.serviceTotalFuelConsumption(serviceTotalFuelConsumption)
				.lastEngineOilChange(request.lastEngineOilChange())
				.lastTransmissionOilChange(request.lastTransmissionOilChange())
				.lastSparkPlugAndCableReplacement(request.lastSparkPlugAndCableReplacement())
				.lastBrakeOilChangeDate(clockHolder.parseDateToMillis(request.lastBrakeOilChangeDate()))
				.lastBrakeOilChange(request.lastBrakeOilChange())
				.lastBatteryChangeDate(clockHolder.parseDateToMillis(request.lastBatteryChangeDate()))
				.lastBatteryChange(request.lastBatteryChange())
				.lastEngineOilChangeDate(clockHolder.parseDateToMillis(request.lastEngineOilChangeDate()))
				.lastTransmissionOilChangeDate(clockHolder.parseDateToMillis(request.lastTransmissionOilChangeDate()))
				.lastBrakePadChangeDate(clockHolder.parseDateToMillis(request.lastBrakePadChangeDate()))
				.lastBrakePadChange(request.lastBrakePadChange())
				.lastSparkPlugAndCableReplacementDate(clockHolder.parseDateToMillis(request.lastSparkPlugAndCableReplacementDate()))
				.fuelFilterChangeDate(clockHolder.parseDateToMillis(request.fuelFilterChangeDate()))
				.fuelFilterChange(request.fuelFilterChange())
				.build();
	}
}
