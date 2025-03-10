package com.example.garage.controller;

import com.example.garage.controller.port.GarageService;
import com.example.garage.controller.response.GarageResponse;
import com.example.garage.domain.RegisterConsumables;
import com.example.garage.domain.RegisterVehicle;
import com.example.util.Empty;
import com.example.util.apiResponse.ApiResponse;
import com.example.util.auth.MemberDetails;
import lombok.RequiredArgsConstructor;
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
    public ApiResponse<Empty> updateGarageProfile (@AuthenticationPrincipal MemberDetails memberDetails,
                                              @RequestBody RegisterConsumables request) {
        garageService.registrationOfInfo(request, memberDetails.getId());
        return ApiResponse.noContent();
    }

//    TODO: 아래의 삭제 관련 API, Garage, Car 삭제 관련 보완 예정
    @DeleteMapping
    public ApiResponse<Empty> deleteGarageProfile (@AuthenticationPrincipal MemberDetails memberDetails) {
        garageService.deleteByUser_Id(memberDetails.getId());
        return ApiResponse.noContent();
    }

}
