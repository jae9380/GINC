package com.example.ginc.domain.garage.infrastructure.entity;

import com.example.ginc.domain.garage.domain.CarDomainEntity;
import com.example.ginc.domain.garage.infrastructure.entity.type.FuelType;
import com.example.ginc.domain.garage.infrastructure.entity.type.Manufacturer;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "car")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CarJpaEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Enumerated(EnumType.STRING)
	@Column(name = "manufacturer")
	private Manufacturer manufacturer;
	@Column(name = "modelName")
	private String modelName;
	@Column(name = "subModelName")
	private String subModelName;
	@Column(name = "manufactureDate")
	private String manufactureDate;
	@Enumerated(EnumType.STRING)
	@Column(name = "fuelType")
	private FuelType fuelType;

	public static CarJpaEntity from(CarDomainEntity entity) {
		CarJpaEntity carJpaEntity = new CarJpaEntity();
		carJpaEntity.id=entity.getId();
		carJpaEntity.manufacturer=entity.getManufacturer();
		carJpaEntity.modelName=entity.getModelName();
		carJpaEntity.subModelName=entity.getSubModelName();
		carJpaEntity.manufactureDate=entity.getManufactureDate();
		carJpaEntity.fuelType=entity.getFuelType();
		return carJpaEntity;
	}

	public CarDomainEntity to() {
		return CarDomainEntity.builder()
				.id(id)
				.manufacturer(manufacturer)
				.modelName(modelName)
				.subModelName(subModelName)
				.manufactureDate(manufactureDate)
				.fuelType(fuelType)
				.build();
	}
}
