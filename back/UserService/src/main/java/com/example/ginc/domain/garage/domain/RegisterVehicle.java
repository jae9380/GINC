package com.example.ginc.domain.garage.domain;

import com.example.ginc.domain.garage.infrastructure.entity.type.FuelType;
import com.example.ginc.domain.garage.infrastructure.entity.type.Manufacturer;
import lombok.Getter;

import java.time.LocalDate;

public record RegisterVehicle(
        Manufacturer manufacturer,
        String modelName,
        String subModelName,
        String manufactureDate,
        FuelType fuelType
) {
}
