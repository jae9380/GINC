package com.example.ginc.util.auth;

import com.example.ginc.domain.account.entity.Member;
import com.example.ginc.domain.account.service.AccountService;
import com.example.ginc.util.auth.service.CustomService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final CustomService customService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = customService.getByUsername(username);

        return new MemberDetails(member);
    }
}