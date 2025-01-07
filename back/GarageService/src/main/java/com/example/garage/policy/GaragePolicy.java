package com.example.garage.policy;

import com.example.garage.domain.GarageDomainEntity;
import com.example.garage.domain.ReplacementPartType;
import com.example.util.commone.service.port.ClockHolder;
import com.example.util.exception.GlobalException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static com.example.ginc.domain.garage.domain.ReplacementPartType.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class GaragePolicy {
    private final ClockHolder clockHolder;

    private static final long MONTH_TO_MILLIS = 30L * 24L * 60L * 60L * 1000L;
    private static final long YEAR_TO_MILLIS =  365L * 24L * 60L * 60L * 1000L;
    private static final long ENGINE_OIL_MILLIS_CYCLE = 6 * MONTH_TO_MILLIS;
    private static final long ENGINE_OIL_CYCLE = 8000L;
    private static final long TRANSMISSION_OIL_MILLIS_CYCLE = 4 * YEAR_TO_MILLIS;
    private static final long TRANSMISSION_OIL_CYCLE = 80000L;
    private static final long FUEL_FILTER_MILLIS_CYCLE = 2 * YEAR_TO_MILLIS;
    private static final long FUEL_FILTER_CYCLE = 40000L;
    private static final long BRAKE_OIL_MILLIS_CYCLE = 2 * YEAR_TO_MILLIS;
    private static final long BRAKE_OIL_CYCLE = 40000L;
    private static final long BRAKE_PAD_MILLIS_CYCLE = 5 * YEAR_TO_MILLIS;
    private static final long BRAKE_PAD_CYCLE = 60000L;
    private static final long SPARK_PLUG_AND_CABLE_MILLIS_CYCLE = 4 * YEAR_TO_MILLIS;
    private static final long SPARK_PLUG_AND_CABLE_CYCLE = 80000L;
    private static final long BATTERY_MILLIS_CYCLE = 4 * YEAR_TO_MILLIS;
    private static final long BATTERY_CYCLE = 50000L;



    public void verifyRecordCreationPermissions (GarageDomainEntity garage, Long user_id) {
        if (!Objects.equals(garage.getUser_id(), user_id))
            throw new GlobalException.NotAuthorizedException();

    }

    public Set<ReplacementPartType> checkAllReplacementCycles(GarageDomainEntity entity) {
        long currentTotalDrivingDistance = entity.getTotalDrivingDistance();
        Set<ReplacementPartType> result = new HashSet<>();

        if (check_replacementCycleByKm(ENGINE_OIL, currentTotalDrivingDistance - entity.getLastEngineOilChange()) ||
                check_replacementCycleByDate(ENGINE_OIL, entity.getLastEngineOilChangeDate()))
            result.add(ENGINE_OIL);

        if (check_replacementCycleByKm(TRANSMISSION_OIL, currentTotalDrivingDistance - entity.getLastTransmissionOilChange()) ||
                check_replacementCycleByDate(TRANSMISSION_OIL, entity.getLastTransmissionOilChangeDate()))
            result.add(TRANSMISSION_OIL);

        if (check_replacementCycleByKm(BRAKE_OIL, currentTotalDrivingDistance - entity.getLastBrakeOilChange()) ||
                check_replacementCycleByDate(BRAKE_OIL, entity.getLastBrakeOilChangeDate()))
            result.add(BRAKE_OIL);

        if (check_replacementCycleByKm(BRAKE_PAD, currentTotalDrivingDistance - entity.getLastBrakePadChange()) ||
                check_replacementCycleByDate(BRAKE_PAD, entity.getLastBrakePadChangeDate()))
            result.add(BRAKE_PAD);

        if (check_replacementCycleByKm(FUEL_FILTER, currentTotalDrivingDistance - entity.getFuelFilterChange()) ||
                check_replacementCycleByDate(FUEL_FILTER, entity.getFuelFilterChangeDate()))
            result.add(FUEL_FILTER);

        if (check_replacementCycleByKm(SPARK_PLUG_AND_CABLE, currentTotalDrivingDistance - entity.getLastSparkPlugAndCableReplacement()) ||
                check_replacementCycleByDate(SPARK_PLUG_AND_CABLE, entity.getLastSparkPlugAndCableReplacementDate()))
            result.add(SPARK_PLUG_AND_CABLE);

        if (check_replacementCycleByKm(BATTERY, currentTotalDrivingDistance - entity.getLastBatteryChange()) ||
                check_replacementCycleByDate(BATTERY, entity.getLastBatteryChangeDate()))
            result.add(BATTERY);

        return result;
    }

    private boolean check_replacementCycleByKm(ReplacementPartType type, Long period) {
        long cycle;

        switch (type) {
            case ENGINE_OIL -> cycle = ENGINE_OIL_CYCLE;
            case TRANSMISSION_OIL -> cycle = TRANSMISSION_OIL_CYCLE;
            case FUEL_FILTER -> cycle = FUEL_FILTER_CYCLE;
            case BRAKE_OIL -> cycle = BRAKE_OIL_CYCLE;
            case BRAKE_PAD -> cycle = BRAKE_PAD_CYCLE;
            case SPARK_PLUG_AND_CABLE -> cycle = SPARK_PLUG_AND_CABLE_CYCLE;
            case BATTERY -> cycle = BATTERY_CYCLE;
            default -> throw new IllegalArgumentException("Invalid Replacement Part Type");
        }

        log.info("RESULT / Check_replacementCycleByKm Method");
        log.info("---"+type+" - "+(period>=cycle)+"---");
        return period >= cycle;
    }

    private boolean check_replacementCycleByDate(ReplacementPartType type, Long lastChangeMillis) {
        long millis;
        long lapsedDate = clockHolder.calculateDifferenceFromNow(lastChangeMillis);
        switch (type) {
            case ENGINE_OIL -> millis = ENGINE_OIL_MILLIS_CYCLE;
            case TRANSMISSION_OIL -> millis = TRANSMISSION_OIL_MILLIS_CYCLE;
            case FUEL_FILTER -> millis = FUEL_FILTER_MILLIS_CYCLE;
            case BRAKE_OIL -> millis = BRAKE_OIL_MILLIS_CYCLE;
            case BRAKE_PAD -> millis = BRAKE_PAD_MILLIS_CYCLE;
            case SPARK_PLUG_AND_CABLE -> millis = SPARK_PLUG_AND_CABLE_MILLIS_CYCLE;
            case BATTERY -> millis = BATTERY_MILLIS_CYCLE;
            default -> throw new IllegalArgumentException("Invalid Replacement Part Type");
        }
        log.info("RESULT / Check_replacementCycleByDate Method");
        log.info("---"+type+" - "+(lapsedDate>=millis)+"---");
        return lapsedDate >= millis;
    }
}

