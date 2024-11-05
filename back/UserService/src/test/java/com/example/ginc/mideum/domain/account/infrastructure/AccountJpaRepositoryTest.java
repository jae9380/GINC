package com.example.ginc.mideum.domain.account.infrastructure;

import com.example.ginc.domain.account.infrastructure.AccountJpaRepository;
import com.example.ginc.domain.account.infrastructure.entity.UserJpaEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest(showSql = true)
@Sql("/initData/account-repository-test-data.sql")
class AccountJpaRepositoryTest {

    @Autowired
    private AccountJpaRepository accountJpaRepository;

    @Test
    void findByUsername으로_유저_불러오기() {
//        given
//        when
        Optional<UserJpaEntity> result = accountJpaRepository.findByUsername("tester1");
//        then
        assertThat(result.isPresent()).isTrue();
    }

    @Test
    void findByUsername으로_없는_유저_불러오기() {
//        given
//        when
        Optional<UserJpaEntity> result = accountJpaRepository.findByUsername("tester2");
//        then
        assertThat(result.isEmpty()).isTrue();
    }
}