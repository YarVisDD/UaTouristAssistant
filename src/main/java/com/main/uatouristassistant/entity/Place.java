package com.main.uatouristassistant.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
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
//    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Images> imagesPath;
    private String imagePath;
    //TODO поміняти на юзера
    private String userName;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @Override
    public String toString() {
        return "Place{" +
                "idPlace=" + idPlace + ", placeName=" + placeName +
                ", placeDescription=" + placeDescription +
                ", placeType=" + placeType + ", imagePath=" + imagePath +
                ", userName=" + userName + ", address=" + address + "}";
    }
}
