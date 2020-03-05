package com.main.uatouristassistant.repository;

import com.main.uatouristassistant.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Long> {
    Place findPlaceByIdPlace(Long idPlace);
}
