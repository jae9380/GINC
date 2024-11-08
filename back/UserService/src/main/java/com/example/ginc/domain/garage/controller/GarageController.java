package com.example.ginc.domain.garage.controller;

import com.example.ginc.domain.garage.controller.port.GarageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/garage")
@RequiredArgsConstructor
public class GarageController {
    private final GarageService garageService;
}
