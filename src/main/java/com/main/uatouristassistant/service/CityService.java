package com.main.uatouristassistant.service;

import com.main.uatouristassistant.entity.City;
import com.main.uatouristassistant.entity.User;
import com.main.uatouristassistant.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public List<City> getAllCity() {
        return cityRepository.findAll();
    }

    public void saveCity(String cityName) {

        City city;
        if (cityRepository.findByCityName(cityName) == null) {
            city = new City();
            city.setCityName(cityName);
            cityRepository.save(city);
        } else {
            city = cityRepository.findByCityName(cityName);
        }
    }

    public City saveCity(City city) {
        return cityRepository.save(city);
    }

    public City findCity(String cityName) {
        return cityRepository.findByCityName(cityName);
    }
}