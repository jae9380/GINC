package com.example.ginc.domain.garage.policy;

import com.example.ginc.domain.garage.domain.GarageDomainEntity;
import com.example.ginc.domain.garage.domain.ReplacementPartType;
import com.example.ginc.util.exception.GlobalException;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.example.ginc.domain.garage.domain.ReplacementPartType.*;

@Component
public class GaragePolicy {
    private static final long ENGINE_OIL_CYCLE = 8000L;
    private static final long TRANSMISSION_OIL_CYCLE = 40000L;
    private static final long FUEL_FILTER_CYCLE = 40000L;
    private static final long BRAKE_OIL_CYCLE = 40000L;
    private static final long BRAKE_PAD_CYCLE = 50000L;
    private static final long SPARK_PLUG_AND_CABLE_CYCLE = 40000L;
    private static final long BATTERY_CYCLE = 60000L;

    public void verifyRecordCreationPermissions (GarageDomainEntity garage, Long user_id) {
        if (!Objects.equals(garage.getUser_id(), user_id))
            throw new GlobalException.NotAuthorizedException();

    }

    public boolean check_replacementCycle(ReplacementPartType type, Long period) {
        long cycle;

        switch (type) {
            case ENGINE_OIL -> cycle = ENGINE_OIL_CYCLE;
            case TRANSMISSION_OIL -> cycle = TRANSMISSION_OIL_CYCLE;
            case FUEL_FILTER -> cycle = FUEL_FILTER_CYCLE;
            case BRAKE_OIL -> cycle = BRAKE_OIL_CYCLE;
            case BRAKE_PAD -> cycle = BRAKE_PAD_CYCLE;
            case SPARK_PLUG_AND_CABLE -> cycle = SPARK_PLUG_AND_CABLE_CYCLE;
            case CHANGE_BATTERY_DATE -> cycle = BATTERY_CYCLE;
            default -> throw new IllegalArgumentException("Invalid Replacement Part Type");
        }

        return period >= cycle;
    }

    public Map<ReplacementPartType, Boolean> checkAllReplacementCycles(GarageDomainEntity entity) {
        long currentTotalDrivingDistance = entity.getTotalDrivingDistance();
        Map<ReplacementPartType, Boolean> result = new HashMap<>();

        result.put(ENGINE_OIL,
                check_replacementCycle(ENGINE_OIL,
                        currentTotalDrivingDistance - entity.getLastEngineOilChange()));

        result.put(TRANSMISSION_OIL,
                check_replacementCycle(TRANSMISSION_OIL,
                        currentTotalDrivingDistance - entity.getLastTransmissionOilChange()));

        result.put(BRAKE_OIL,
                check_replacementCycle(BRAKE_OIL,
                        currentTotalDrivingDistance - entity.getLastBrakeOilChange()));

        result.put(BRAKE_PAD,
                check_replacementCycle(BRAKE_PAD,
                        currentTotalDrivingDistance - entity.getLastBrakePadChange()));

        result.put(FUEL_FILTER,
                check_replacementCycle(FUEL_FILTER,
                        currentTotalDrivingDistance - entity.getFuelFilterChange()));

        result.put(SPARK_PLUG_AND_CABLE,
                check_replacementCycle(SPARK_PLUG_AND_CABLE,
                        currentTotalDrivingDistance - entity.getFuelFilterChange()));

        result.put(CHANGE_BATTERY_DATE,
                check_replacementCycle(CHANGE_BATTERY_DATE,
                        currentTotalDrivingDistance - entity.getLastChangeBatteryDate()));

        return result;
    }
}

