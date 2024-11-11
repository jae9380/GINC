package com.example.ginc.domain.garage.domain;

import com.example.ginc.domain.garage.infrastructure.entity.type.FuelType;
import com.example.ginc.domain.garage.infrastructure.entity.type.Manufacturer;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class CarDomainEntity {
	private final Long id;
	private final Manufacturer manufacturer;
	private final String modelName;
	private final String subModelName;
	private final String manufactureDate;
	private final FuelType fuelType;

	@Builder
    public CarDomainEntity(Long id, Manufacturer manufacturer, String modelName,
						   String subModelName, String manufactureDate, FuelType fuelType) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.modelName = modelName;
        this.subModelName = subModelName;
        this.manufactureDate = manufactureDate;
        this.fuelType = fuelType;
    }

    public static CarDomainEntity register(RegisterVehicle request) {
        return CarDomainEntity.builder()
                .manufacturer(request.manufacturer())
                .modelName(request.modelName())
                .subModelName(request.subModelName())
                .manufactureDate(request.manufactureDate())
                .fuelType(request.fuelType())
                .build();
    }
}
