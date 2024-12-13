package com.example.ginc.util.commone.infrastructure;

import com.example.ginc.util.commone.service.port.ClockHolder;
import com.example.ginc.util.exception.GlobalException;
import org.springframework.stereotype.Component;

import java.time.*;
import java.time.format.DateTimeParseException;

@Component
public class SystemClockHolder implements ClockHolder {
    @Override
    public long millis() {
        return Clock.systemUTC().millis();
    }
    @Override
    public Long parseDateToMillis(String dateStr) {
        try {
            if (dateStr.matches("\\d{4}-\\d{2}-\\d{2}")) { // 형식: yyyy-MM-dd
                LocalDate date = LocalDate.parse(dateStr);
                return toMillis(date);
            } else if (dateStr.matches("\\d{4}-\\d{2}")) { // 형식: yyyy-MM
                YearMonth yearMonth = YearMonth.parse(dateStr);
                return toMillis(yearMonth);
            } else if (dateStr.matches("\\d{4}")) { // 형식: yyyy
                Year year = Year.of(Integer.parseInt(dateStr));
                return toMillis(year);
            } else {
                throw new IllegalArgumentException("유효하지 않은 날짜 형식입니다.");
            }
        } catch (DateTimeParseException e) {
            throw new GlobalException.BadInputFormatException();
        }
    }

    public long calculateDifferenceFromNow(long pastMillis) {
        long currentMillis = millis();
        return currentMillis - pastMillis;
    }

    /*
    날짜 변환 시 년도를 제외한 정보를 정확한 기입하지 않을 경우 1월 또는 1일을 기본 값으로 변환
     */
    private long toMillis(LocalDate date) {
        return date.atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli();
    }

    private long toMillis(YearMonth yearMonth) {
        return yearMonth.atDay(1).atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli();
    }

    private long toMillis(Year year) {
        return year.atMonth(1).atDay(1).atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli();
    }

}
