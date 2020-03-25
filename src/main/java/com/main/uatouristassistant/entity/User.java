package com.main.uatouristassistant.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

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
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
    private List<Place> places;

    public User() {
    }
}
