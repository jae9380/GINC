package com.example.ginc.domain.batch.event;

import com.example.ginc.domain.garage.domain.RefuelDomainEntity;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class RefuelingEvent extends ApplicationEvent {
    private final RefuelDomainEntity entity;
    public RefuelingEvent(Object source, RefuelDomainEntity entity) {
        super(source);
        this.entity = entity;
    }
}
