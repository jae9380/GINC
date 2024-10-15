package com.example.ginc.domain.account.repository;

import com.example.ginc.domain.account.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUsername(String username);
}
