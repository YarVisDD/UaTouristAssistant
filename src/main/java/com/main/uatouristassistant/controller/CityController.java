package com.main.uatouristassistant.controller;

import com.main.uatouristassistant.entity.City;
import com.main.uatouristassistant.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/city")
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping(path = "/listCity")
    @ResponseBody
    public Iterable<City> allCity() {
        return cityService.getAllCity();
    }
}
