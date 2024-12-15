package com.example.ginc.domain.account.infrastructure;

import com.example.ginc.domain.account.infrastructure.entity.AuthCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MailAuthJpaRepository extends JpaRepository<AuthCode, Long> {
    Optional findByUserId(Long userId);
}
