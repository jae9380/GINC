package com.example.ginc.mock;

import com.example.ginc.util.commone.service.port.ClockHolder;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@RequiredArgsConstructor
public class TestClockHolder implements ClockHolder {
    private final String date;

    @Override
    public long millis() {
        return 0;
    }

    @Override
    public Long parseDateToMillis(String dateStr) {
        return null;
    }

    @Override
    public long calculateDifferenceFromNow(long pastMillis) {
        return 0;
    }
}