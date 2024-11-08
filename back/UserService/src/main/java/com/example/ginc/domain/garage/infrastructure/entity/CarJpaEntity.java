package com.example.ginc.domain.garage.infrastructure.entity;

import com.example.ginc.domain.garage.infrastructure.entity.type.FuelType;
import com.example.ginc.domain.garage.infrastructure.entity.type.Manufacturer;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
	private Long id;
	@Column(name = "manufacturer")
	private Manufacturer manufacturer;
	@Column(name = "modelName")
	private String modelName;
	@Column(name = "subModelName")
	private String subModelName;
	@Column(name = "manufactureDate")
	private LocalDate manufactureDate;
	@Column(name = "fuelType")
	private FuelType fuelType;
	@Column(name = "carImage")
	private String carImage;
	@Column(name = "carColor")
	private String carColor;
}
