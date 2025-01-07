package com.example.garage.eventListener;

import com.example.garage.controller.port.GarageService;
import com.example.garage.event.RefuelingEvent;
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
        log.info(event.getAction()+" - Refueling Event Operation");

        garageService.refuelingEvent(event.getEntity(), event.getUser_id());
    }
}
