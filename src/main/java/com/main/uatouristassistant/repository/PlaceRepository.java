package com.main.uatouristassistant.repository;

import com.main.uatouristassistant.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place, Long> {

}
