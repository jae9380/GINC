package com.example.ginc.mideum.domain.account.infrastructure;

import com.example.ginc.domain.account.domain.UserDomainEntity;
import com.example.ginc.domain.account.infrastructure.AccountRepositoryImpl;
import com.example.ginc.domain.account.infrastructure.entity.UserJpaEntity;
import com.example.ginc.domain.account.infrastructure.entity.type.Gender;
import com.example.ginc.domain.account.infrastructure.entity.type.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@SqlGroup({
        @Sql(value = "/initData/account-repository-test-data.sql",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(value = "/initData/delete-all-data.sql",executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
})
class AccountRepositoryImplTest {
    @Autowired
    private AccountRepositoryImpl accountRepositoryImpl;

    @Test
    void findByUsername으로_없는_데이터_UserDomainEntity_형태로_불러오기() {
//        given
//        when
        Optional<UserDomainEntity> result = accountRepositoryImpl.findByUsername("tester2");

//        then
        assertThat(result).isEmpty();
    }

    @Test
    void findByUsername으로_UserDomainEntity_형태로_불러오기() {
//        given
//        when
        Optional<UserDomainEntity> result = accountRepositoryImpl.findByUsername("tester1");

//        then
        assertThat(result).isNotNull();
        assertThat(result.get().getId()).isEqualTo(1L);
        assertThat(result.get().getUsername()).isEqualTo("tester1");
    }

    @Test
    void findById_으로_없는_데이터_UserDomainEntity_형태로_불러오기() {
//        given
//        when
        Optional<UserDomainEntity> result = accountRepositoryImpl.findById(1L);

//        then
        assertThat(result).isNotNull();
        assertThat(result.get().getId()).isEqualTo(1L);
        assertThat(result.get().getUsername()).isEqualTo("tester1");
    }

    @Test
    void findById_으로_UserDomainEntity_형태로_불러오기() {
//        given
//        when
        Optional<UserDomainEntity> result = accountRepositoryImpl.findById(1L);

//        then
        assertThat(result).isNotNull();
        assertThat(result.get().getId()).isEqualTo(1L);
        assertThat(result.get().getUsername()).isEqualTo("tester1");
    }

    @Test
    void save로_데이터_저장하기() {
//        given
//        when
        accountRepositoryImpl.save(
                UserDomainEntity.builder()
                        .id(2L)
                        .username("tester2")
                        .password("test1234")
                        .name("JaeYeol Lee")
                        .phoneNumber("01011111111")
                        .email("testMail@Test.com")
                        .gender(Gender.MALE)
                        .birth(LocalDate.parse("2000-01-01"))
                        .role(Role.USER)
                        .authenticated(false)
                        .createdAt(null)
                        .modifiedAt(LocalDate.now())
                        .build()
        );

//        then
        UserDomainEntity result = accountRepositoryImpl.findByUsername("tester2").get();
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(2L);
        assertThat(result.getUsername()).isEqualTo("tester2");
    }
}
