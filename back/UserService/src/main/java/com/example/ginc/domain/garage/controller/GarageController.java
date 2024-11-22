package com.example.ginc.domain.garage.controller;

import com.example.ginc.domain.garage.controller.port.GarageService;
import com.example.ginc.domain.garage.controller.response.GarageResponse;
import com.example.ginc.domain.garage.domain.Refueling;
import com.example.ginc.domain.garage.domain.RegisterConsumables;
import com.example.ginc.domain.garage.domain.RegisterVehicle;
import com.example.ginc.util.Empty;
import com.example.ginc.util.apiResponse.ApiResponse;
import com.example.ginc.util.auth.MemberDetails;
import lombok.Getter;
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
    public ApiResponse<Empty> registerGarage (@AuthenticationPrincipal MemberDetails memberDetails,
                                               @RequestBody RegisterVehicle request) {
        garageService.garageRegistration(request, memberDetails.getId());
        return ApiResponse.noContent();
    }

    @GetMapping
    public ApiResponse<GarageResponse> getGarageProfile (@AuthenticationPrincipal MemberDetails memberDetails) {
        return ApiResponse.ok(GarageResponse.from(garageService.getByUser_Id(memberDetails.getId())));
    }

    @PutMapping
    public ApiResponse<Empty> updateOfInfo (@AuthenticationPrincipal MemberDetails memberDetails,
                                              @RequestBody RegisterConsumables request) {
        garageService.registrationOfInfo(request, memberDetails.getId());
        return ApiResponse.noContent();
    }

}
