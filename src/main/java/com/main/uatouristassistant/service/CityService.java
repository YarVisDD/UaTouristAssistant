package com.main.uatouristassistant.service;

import com.main.uatouristassistant.entity.City;
import com.main.uatouristassistant.repository.CityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@Slf4j
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



    public boolean addCity(@RequestParam (required = false) String cityName) {
        String addCityInfo;
        boolean checkCityName = false;
        City city = new City();
        boolean cityCityNameDb = cityRepository.existsByCityName(cityName);

        if (cityCityNameDb) {
            addCityInfo = cityName + " - already EXISTS. Please try with another CityName";
            log.warn("WARNING!!! User tried to add an existing one CityName. CityName: {}", cityName);
        } else {
            city.setCityName(cityName);

            cityRepository.save(city);
            checkCityName = true;
            addCityInfo = cityName + " has been CREATED!";
            log.info("INFO!!! City has been created. City: {}", city);
        }
        System.out.println(addCityInfo);
        return checkCityName;
    }
}