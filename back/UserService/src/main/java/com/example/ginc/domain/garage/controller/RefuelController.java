package com.example.ginc.domain.garage.controller;

import com.example.ginc.domain.garage.controller.port.RefuelService;
import com.example.ginc.domain.garage.domain.Refueling;
import com.example.ginc.domain.garage.domain.RegisterVehicle;
import com.example.ginc.util.Empty;
import com.example.ginc.util.apiResponse.ApiResponse;
import com.example.ginc.util.auth.MemberDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
//    TODO: 주유기록 갖고오기, 일정 기간이 초과한 정보는 삭제
}
