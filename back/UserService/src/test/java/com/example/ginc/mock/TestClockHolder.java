package com.example.ginc.mock;

import com.example.ginc.util.commone.service.port.ClockHolder;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@RequiredArgsConstructor
public class TestClockHolder implements ClockHolder {
    private final String date;
    @Override
    public LocalDate now() {
        return LocalDate.parse(date);
    }
}