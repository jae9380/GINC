package com.example.ginc.domain.garage.controller;

import com.example.ginc.domain.garage.controller.port.GarageService;
import com.example.ginc.domain.garage.domain.Refueling;
import com.example.ginc.domain.garage.domain.RegisterVehicle;
import com.example.ginc.util.Empty;
import com.example.ginc.util.apiResponse.ApiResponse;
import com.example.ginc.util.auth.MemberDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/garage")
@RequiredArgsConstructor
public class GarageController {
    private final GarageService garageService;

    @PostMapping
    public ApiResponse<Empty> registerVehicle (@AuthenticationPrincipal MemberDetails memberDetails,
                                               @RequestBody RegisterVehicle request) {
        garageService.vehicleRegistration(request);
    return ApiResponse.noContent();
    }
}
