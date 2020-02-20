package com.main.uatouristassistant.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idAddress;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id")
    private City cityName;

    private String streetName;
    private String numberHouse;

    public Address() {}

    public Address(City city, String streetName, String numberHouse) {
        this.cityName = city;
        this.streetName = streetName;
        this.numberHouse = numberHouse;
    }
}
