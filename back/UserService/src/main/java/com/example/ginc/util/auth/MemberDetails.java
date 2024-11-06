package com.example.ginc.util.auth;

import com.example.ginc.domain.account.domain.UserDomainEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@RequiredArgsConstructor
public class MemberDetails implements UserDetails {
    private final UserDomainEntity userDomainEntity;

    public Long getId() {
        return userDomainEntity.getId();
    }

    @Override
    public String getUsername() {
        return userDomainEntity.getUsername();
    }

    public String getName() {
        return userDomainEntity.getName();
    }

    @Override
    public String getPassword() {
        return userDomainEntity.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(() -> userDomainEntity.getRole().name());
    }
}
