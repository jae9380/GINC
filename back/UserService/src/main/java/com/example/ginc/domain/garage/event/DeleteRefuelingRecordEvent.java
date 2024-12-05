package com.example.ginc.domain.garage.event;

import com.example.ginc.domain.garage.domain.RefuelDomainEntity;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class DeleteRefuelingRecordEvent extends ApplicationEvent {
    private final Long user_id;
    private final RefuelDomainEntity entity;
    public DeleteRefuelingRecordEvent(Object source, Long userId, RefuelDomainEntity entity) {
        super(source);
        user_id = userId;
        this.entity = entity;
    }
}
