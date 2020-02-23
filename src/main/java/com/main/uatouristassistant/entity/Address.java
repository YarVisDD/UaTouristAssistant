package com.main.uatouristassistant.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idAddress;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "city_id")
    private City city;
    private String street;
    private String numberHouse;

    public Address() {}

    @Override
    public String toString() {
        return "Address {id=" + idAddress +
                ", city_id=" + city.getIdCity() +
                ", street=" + street  +
                ", numberHouse=" + numberHouse + "}";
    }
}
