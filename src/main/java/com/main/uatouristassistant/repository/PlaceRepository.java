package com.main.uatouristassistant.repository;

import com.main.uatouristassistant.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Long> {
    @Query(value =
            "SELECT " +
                    "place.place_name, " +
                    "place.place_description, " +
                    "place.place_type, " +
                    "city.city_name, " +
                    "address.street, " +
                    "address.number_house " +
            "FROM " +
                    "place " +
            "INNER JOIN address on place.address_id = address.id_address " +
            "INNER JOIN city on address.city_id = city.id_city", nativeQuery = true)
    List<Place> allPlaceInfo();

    Place findPlaceByIdPlace(Long idPlace);
    Place deleteByIdPlace(Long idPlace);
}
