package com.main.uatouristassistant.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCity;
    private String cityName;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "city", cascade = CascadeType.ALL)
    private List<Address> addresses;

    public City() {}
}
