package com.main.uatouristassistant.controller;

import com.main.uatouristassistant.entity.Address;
import com.main.uatouristassistant.entity.City;
import com.main.uatouristassistant.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/city")
public class CityController {
    @Autowired
    private CityRepository cityRepository;

    @GetMapping(path = "/listCity")
    @ResponseBody
    public String getAllAddresses() {
        List<City> list = cityRepository.findAll();
        return list.toString();
    }
}
