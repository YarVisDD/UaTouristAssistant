package com.main.uatouristassistant.repository;

import com.main.uatouristassistant.entity.Place;
import com.main.uatouristassistant.entity.PlaceType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Long> {
    Place findPlaceByIdPlace(Long idPlace);
    List<Place> findByPlaceTypeAndAddress_City_CityName(PlaceType placeType, String city);
    List<Place> findByAddress_City_CityName(String city);
}
