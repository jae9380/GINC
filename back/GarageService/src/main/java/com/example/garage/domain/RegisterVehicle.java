package com.example.garage.domain;

import com.example.garage.infrastructure.entity.type.FuelType;
import com.example.garage.infrastructure.entity.type.Manufacturer;

public record RegisterVehicle(
        Manufacturer manufacturer,
        String modelName,
        String subModelName,
        String manufactureDate,
        FuelType fuelType
) {
}
