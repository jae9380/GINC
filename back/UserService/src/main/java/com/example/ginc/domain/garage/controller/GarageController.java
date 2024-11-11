package com.example.ginc.domain.garage.controller;

import com.example.ginc.domain.garage.controller.port.GarageService;
import com.example.ginc.domain.garage.domain.RegisterVehicle;
import com.example.ginc.util.Empty;
import com.example.ginc.util.apiResponse.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/garage")
@RequiredArgsConstructor
public class GarageController {
    private final GarageService garageService;

    @PostMapping
    public ApiResponse<Empty> registerVehicle (@RequestBody RegisterVehicle request) {
        garageService.vehicleRegistration(request);
    return ApiResponse.noContent();
    }
}
