package com.main.uatouristassistant.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCity;
    private String cityName;
    private String ciyInfo;

    public City() {}
    public City(String cityName) {
        this.cityName = cityName;
    }
}
