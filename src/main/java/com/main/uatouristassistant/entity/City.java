package com.main.uatouristassistant.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

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

    @Override
    public String toString() {
        String address = "";
        if ((addresses != null) && (addresses.size() > 0)) {
            for (int i = 0; i < addresses.size(); i++) {
                if (i > 0) {
                    address += ", ";
                }
                address += addresses.get(i).toString();
            }
        }
        return "City {id=" + idCity +
                ", cityName=" + cityName +
                ", addresses= [" + address + "]}";
    }
}
