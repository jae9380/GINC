package com.example.ginc.domain.garage.policy;

import com.example.ginc.domain.garage.domain.GarageDomainEntity;
import com.example.ginc.util.exception.GincException;
import com.example.ginc.util.exception.GlobalException;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class GaragePolicy {
    public void verifyRecordCreationPermissions (GarageDomainEntity garage, Long user_id) {
        if (!Objects.equals(garage.getUser_id(), user_id))
            throw new GlobalException.NotAuthorizedException();

    }
}
