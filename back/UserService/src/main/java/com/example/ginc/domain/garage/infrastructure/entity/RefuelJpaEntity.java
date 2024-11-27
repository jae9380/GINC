package com.example.ginc.domain.garage.infrastructure.entity;

import com.example.ginc.domain.garage.domain.RefuelDomainEntity;
import com.example.ginc.domain.garage.infrastructure.entity.type.FuelType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "refuel")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class RefuelJpaEntity {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@Column(name = "carId")
	private Long carId;
	@Column(name = "segmentTotalDistance")
	private int segmentTotalDistance;
	@Column(name = "totalRefuelingCost")
	private int totalRefuelingCost;
	@Enumerated(EnumType.STRING)
	@Column(name = "fuelType")
	private FuelType fuelType;
	@Column(name = "costPerLiter")
	private int costPerLiter;
	@Column(name = "refuelingVolume")
	private int refuelingVolume;
	@Column(name = "refuelingAt")
	private long refuelingAt;

	public static RefuelJpaEntity from(RefuelDomainEntity entity) {
		RefuelJpaEntity refuelJpaEntity = new RefuelJpaEntity();
		refuelJpaEntity.id=entity.getId();
		refuelJpaEntity.carId = entity.getCar_id();
		refuelJpaEntity.segmentTotalDistance=entity.getSegmentTotalDistance();
		refuelJpaEntity.totalRefuelingCost=entity.getTotalRefuelingCost();
		refuelJpaEntity.fuelType=entity.getFuelType();
		refuelJpaEntity.costPerLiter=entity.getCostPerLiter();
		refuelJpaEntity.refuelingVolume=entity.getRefuelingVolume();
		refuelJpaEntity.refuelingAt=entity.getRefuelingAt();
		return refuelJpaEntity;
	}

	public RefuelDomainEntity to() {
		return RefuelDomainEntity.builder()
				.id(id)
				.car_id(carId)
				.segmentTotalDistance(segmentTotalDistance)
				.totalRefuelingCost(totalRefuelingCost)
				.fuelType(fuelType)
				.costPerLiter(costPerLiter)
				.refuelingVolume(refuelingVolume)
				.refuelingAt(refuelingAt)
				.build();
	}
}
