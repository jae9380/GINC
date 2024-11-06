package com.example.ginc.util.commone.infrastructure;

import com.example.ginc.util.commone.service.port.ClockHolder;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.LocalDate;

@Component
public class SystemClockHolder implements ClockHolder {
    @Override
    public LocalDate now() {
        return LocalDate.now(Clock.systemUTC());
    }
}
