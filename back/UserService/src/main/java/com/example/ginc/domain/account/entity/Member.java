package com.example.ginc.domain.account.entity;


import com.example.ginc.domain.account.entity.type.Gender;
import jakarta.persistence.EnumType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    private String password;
    private String name;
    private String phoneNumber;

    @Email
    private String email;

    @Enumerated(EnumType.STRING)
    private Gender gender;

}
