package com.main.uatouristassistant.repository;

import com.main.uatouristassistant.entity.PlaceImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceImageRepository extends JpaRepository<PlaceImage, Long> {
    List<PlaceImage> findByPlaceIdPlace(Long idPlace);
}
