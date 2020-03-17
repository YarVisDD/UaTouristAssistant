package com.main.uatouristassistant.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class PlaceImage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idImage;

    private String image;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "place_id")
    private Place place;
}
