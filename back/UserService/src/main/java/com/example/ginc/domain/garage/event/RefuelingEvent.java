package com.example.ginc.domain.garage.event;

import com.example.ginc.domain.garage.domain.RefuelDomainEntity;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class RefuelingEvent extends ApplicationEvent {
    private final Long user_id;
    private final RefuelDomainEntity entity;
    public RefuelingEvent(Object source, Long userId, RefuelDomainEntity entity) {
        super(source);
        user_id = userId;
        this.entity = entity;
    }
}
