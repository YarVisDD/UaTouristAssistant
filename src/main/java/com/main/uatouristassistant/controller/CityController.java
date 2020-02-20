package com.main.uatouristassistant.controller;

import com.main.uatouristassistant.entity.City;
import com.main.uatouristassistant.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/city")
public class CityController {
    @Autowired
    private CityRepository cityRepository;

    @PostMapping(path = "/addCity")
    @ResponseBody
    public String addCity(@RequestParam String cityName, String cityInfo) {
        City city = new City();
        if (cityRepository.findByCityName(cityName) == null) {
            city.setCityName(cityName);
        } else {
            return "the city " + cityName+ " already has a database";
        }
        city.setCiyInfo(cityInfo);

        cityRepository.save(city);
        return "add city";
    }

    @GetMapping(path = "/showCity")
    @ResponseBody
    public City getCityByName(@RequestParam String cityName) {
        return cityRepository.findByCityName(cityName);
    }
}
