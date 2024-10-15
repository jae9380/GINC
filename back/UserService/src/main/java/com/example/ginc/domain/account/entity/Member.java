package com.example.ginc.domain.account.entity;


import com.example.ginc.domain.account.entity.type.Gender;
import com.example.ginc.domain.account.entity.type.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static com.example.ginc.domain.account.entity.type.Role.*;

@Entity
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    private String password;
    private String name;
    private int phoneNumber;

    @Email
    private String email;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private LocalDate birth;

    private Role role;

    private LocalDate createdAt;

    private LocalDate modifiedAt;

    public Member(
            String username, String password,
            String name, int phoneNumber,
            String email, Gender gender,
            LocalDate birth, Role role, LocalDate localDate) {
        this.username=username;
        this.password=password;
        this.name=name;
        this.phoneNumber=phoneNumber;
        this.email=email;
        this.gender=gender;
        this.birth=birth;
        this.role=role;
        this.createdAt=localDate;
    }

    public static Member createMember(
            String username, String password,
            String name, int phoneNumber,
            String email, Gender gender,
            LocalDate birth, Role role) {
        return new Member(
                username, password, name,
                phoneNumber, email, gender,
                birth, role, LocalDate.now()
        );
    }

    public void updateInfo(
            String password, String name, LocalDate birth, LocalDate modifiedAt
    ) {
     if (!password.isEmpty()&&password!=null) this.password=password;
     if (!name.isEmpty()&&name!=null) this.name=name;
     if (birth!=null) this.birth=birth;
    }
}
