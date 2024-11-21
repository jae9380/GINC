package com.example.ginc.domain.garage.controller;

import com.example.ginc.domain.garage.controller.port.RefuelService;
import com.example.ginc.domain.garage.controller.response.RefuelResponse;
import com.example.ginc.domain.garage.domain.RefuelDomainEntity;
import com.example.ginc.domain.garage.domain.Refueling;
import com.example.ginc.domain.garage.domain.RegisterVehicle;
import com.example.ginc.util.Empty;
import com.example.ginc.util.apiResponse.ApiResponse;
import com.example.ginc.util.auth.MemberDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/refueling")
@RequiredArgsConstructor
public class RefuelController {
    private final RefuelService refuelService;
    @PostMapping
    public ApiResponse<Empty> refueling (@AuthenticationPrincipal MemberDetails memberDetails,
                                               @RequestBody Refueling request) {
        refuelService.refueling(request,memberDetails.getId());
        return ApiResponse.noContent();
    }

    @GetMapping
    public ApiResponse<List<RefuelResponse>> refuelingList (@AuthenticationPrincipal MemberDetails memberDetails) {
        return ApiResponse.ok(refuelService.getList(memberDetails.getId())
                .stream()
                .map(RefuelResponse::from)
                .toList());
    }
//    TODO: 주유기록 갖고오기, 일정 기간이 초과한 정보는 삭제
}
