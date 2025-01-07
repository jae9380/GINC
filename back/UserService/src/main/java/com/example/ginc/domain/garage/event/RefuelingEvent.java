package com.example.ginc.domain.garage.event;

import com.example.ginc.domain.garage.domain.RefuelDomainEntity;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class RefuelingEvent extends ApplicationEvent {
    private final Long user_id;
    private final String action;
    private final RefuelDomainEntity entity;
    public RefuelingEvent(Object source, String action, Long userId, RefuelDomainEntity entity) {
        super(source);
        this.user_id = userId;
        this.action = action;
        this.entity = entity;
    }
}
