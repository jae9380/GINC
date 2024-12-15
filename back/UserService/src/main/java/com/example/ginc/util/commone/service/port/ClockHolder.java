package com.example.ginc.util.commone.service.port;

import java.time.Clock;
import java.time.LocalDate;

public interface ClockHolder {
    long millis();
    Long parseDateToMillis(String dateStr);
    long calculateDifferenceFromNow(long pastMillis);
    LocalDate millisToLocalDate(Long millis);
}
