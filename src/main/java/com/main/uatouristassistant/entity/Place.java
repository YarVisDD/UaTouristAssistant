package com.main.uatouristassistant.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idPlace;
    private String placeName;
    private String placeDescription;
    @Enumerated(EnumType.STRING)
    private PlaceType placeType;
    //TODO: images
//    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private String imagePath;
    //TODO поміняти на юзера
    private String userName;
    private String address;
//    @OneToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "address_id")
//    private Address address;
}
