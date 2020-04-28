package com.main.uatouristassistant.service;

import com.main.uatouristassistant.entity.City;
import com.main.uatouristassistant.repository.CityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Slf4j
@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public List<City> getAllCity() {
        return cityRepository.findAll();
    }

    public City saveCity(City city) {
        return cityRepository.save(city);
    }

    public City findCity(String cityName) {
        return cityRepository.findByCityName(cityName);
    }

    public boolean deleteCity(@PathVariable String cityName) {
        if (cityRepository.existsByCityName(cityName)) {
            City city = cityRepository.findByCityName(cityName);
            cityRepository.delete(city);
            log.info("INFO!!! City has ben deleted: {}", city);
            System.out.println("The city with cityName" + cityName + " has been deleted");
            return true;
        } else {
            log.error("ERROR!!! Tried to delete city which does not exist: {}", cityName);
            System.out.println("The city with cityName " + cityName + " does not exist!");
            return false;
        }
    }

    public boolean checkCity(String cityName) {
        boolean checkCity = cityRepository.existsByCityName(cityName);
        if (checkCity) {
            return true;
        } else {
            return false;
        }
    }
}
