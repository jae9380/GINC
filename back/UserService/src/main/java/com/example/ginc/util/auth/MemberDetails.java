package com.example.ginc.util.auth;

import com.example.ginc.domain.account.infrastructure.entity.UserJpaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@RequiredArgsConstructor
public class MemberDetails implements UserDetails {
    private final UserJpaEntity userJpaEntity;

    public Long getId() {
        return userJpaEntity.getId();
    }

    @Override
    public String getUsername() {
        return userJpaEntity.getUsername();
    }

    public String getName() {
        return userJpaEntity.getName();
    }

    @Override
    public String getPassword() {
        return userJpaEntity.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(() -> userJpaEntity.getRole().name());
    }
}
