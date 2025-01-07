package com.example.garage.controller;

import com.example.garage.controller.port.RefuelService;
import com.example.garage.controller.response.RefuelResponse;
import com.example.garage.domain.Refueling;
import com.example.util.Empty;
import com.example.util.apiResponse.ApiResponse;
import com.example.util.auth.MemberDetails;
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
    public ApiResponse<Empty> registerRefueling (@AuthenticationPrincipal MemberDetails memberDetails,
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

    @GetMapping("/{refueling_id}")
    public ApiResponse<RefuelResponse> refueling (@AuthenticationPrincipal MemberDetails memberDetails,
                                                  @PathVariable(name = "refueling_id") Long refueling_id) {
        return ApiResponse.ok(RefuelResponse.from(refuelService.getById(refueling_id)));
    }

    @PutMapping("/{refueling_id}")
    public ApiResponse<Empty> modifyRefueling (@AuthenticationPrincipal MemberDetails memberDetails,
                                               @PathVariable(name = "refueling_id") Long refueling_id,
                                               @RequestBody Refueling request) {
        refuelService.modifyRefueling(memberDetails.getId(), refueling_id, request);
        return ApiResponse.noContent();
    }

    @DeleteMapping("/{refueling_id}")
    public ApiResponse<Empty> deleteRefueling (@AuthenticationPrincipal MemberDetails memberDetails,
                                               @PathVariable(name = "refueling_id") Long refueling_id) {
        refuelService.deleteRefueling(memberDetails.getId(), refueling_id);
        return ApiResponse.noContent();
    }

//    TODO: 아래의 삭제 관련 API, Refueling 삭제 관련 보완 예정
    @DeleteMapping("/car_id/{car_id}")
    public ApiResponse<Empty> deleteByCar_Id (@AuthenticationPrincipal MemberDetails memberDetails,
                                              @PathVariable(name = "car_id") Long car_id) {
        refuelService.deleteByCar_Id(car_id);
        return ApiResponse.noContent();
    }

//    TODO:  일정 기간이 초과한 정보는 삭제
}
