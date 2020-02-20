package com.main.uatouristassistant.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Images {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idImage;
    private String imageName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "palce_id")
    private Place place;

    public Images(String resultImageName) {
        this.imageName = resultImageName;
    }
}
