package com.main.uatouristassistant.repository;

import com.main.uatouristassistant.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
    City findByCityName(String cityName);
    boolean existsByCityName(String cityName);
}
