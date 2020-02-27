package com.main.uatouristassistant.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    private String login;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    @Enumerated(EnumType.STRING)
    private UserRoles userRole;

    public User() {
    }
}
