package com.example.ginc.domain.garage.eventListener;

import com.example.ginc.domain.garage.controller.port.GarageService;
import com.example.ginc.domain.garage.event.DeleteRefuelingRecordEvent;
import com.example.ginc.domain.garage.event.ModifyRefuelingRecordEvent;
import com.example.ginc.domain.garage.event.RefuelingEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class RefuelingEventListener {
    private final GarageService garageService;

    @EventListener
    public void refuelingEventListener(RefuelingEvent event) {
        log.info("UPDATE - Refueling Event Operation : Update Garage information based on refueling records");

        garageService.refueling(event.getEntity(), event.getUser_id());
    }

    @EventListener
    public void modifyRefuelingRecordEvent(ModifyRefuelingRecordEvent event) {
        log.info("MODIFY - Modified Refueling Record Event Operation : Update Garage information based on modified refueling records");

        garageService.modifyRefuelingRecord(event.getEntity(), event.getUser_id());
    }

    @EventListener
    public void deleteRefuelingRecordEvent(DeleteRefuelingRecordEvent event) {
        log.info("DELETE - Deleted Refueling Record Event Operation : Update Garage information based on deleted refueling records");

        garageService.deleteRefuelingRecord(event.getEntity(), event.getUser_id());
    }
}
