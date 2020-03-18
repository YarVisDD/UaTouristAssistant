package com.main.uatouristassistant.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idPlace;

    private String placeName;

    @Column(length=1000)
    private String placeDescription;

    @Enumerated(EnumType.STRING)
    private PlaceType placeType;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

}
