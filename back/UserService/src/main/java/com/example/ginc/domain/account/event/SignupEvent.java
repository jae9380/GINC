package com.example.ginc.domain.account.event;

import com.example.ginc.domain.garage.domain.RefuelDomainEntity;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class SignupEvent extends ApplicationEvent {
    private final String user_email;
    public SignupEvent(Object source, String user_email) {
        super(source);
        this.user_email = user_email;

    }
}
