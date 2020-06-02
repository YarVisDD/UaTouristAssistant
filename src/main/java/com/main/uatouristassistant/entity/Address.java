package com.main.uatouristassistant.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idAddress;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "city_id")
    private City city;

    @NotNull
    @Size(min=2, max=50)
    private String street;

    @NotNull
    @Min(1)
    private String numberHouse;
    @OneToOne(mappedBy = "address", fetch = FetchType.EAGER)
    private Place place;

    public Address() {}

}
