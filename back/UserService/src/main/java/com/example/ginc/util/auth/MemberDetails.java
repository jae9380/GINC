package com.example.ginc.util.auth;

import com.example.ginc.domain.account.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@RequiredArgsConstructor
public class MemberDetails implements UserDetails {
    private final Member member;

    public Long getId() {
        return member.getId();
    }

    @Override
    public String getUsername() {
        return member.getUsername();
    }

    public String getName() {
        return member.getName();
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(() -> member.getRole().name());
    }
}
