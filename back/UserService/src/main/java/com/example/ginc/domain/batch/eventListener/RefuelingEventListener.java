package com.example.ginc.domain.batch.eventListener;

import com.example.ginc.domain.batch.event.RefuelingEvent;
import com.example.ginc.domain.batch.eventListener.port.BatchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
@Slf4j
public class RefuelingEventListener {
    private final BatchService batchService;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void refuelingEventListener(RefuelingEvent event) {
        log.info("Operation : Refueling Event");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        batchService.refueling(event.getEntity());
    }
}
