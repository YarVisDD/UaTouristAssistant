package com.main.uatouristassistant.controller;

import com.main.uatouristassistant.entity.City;
import com.main.uatouristassistant.repository.CityRepository;
import com.main.uatouristassistant.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/city")
public class CityController {
    @Autowired
    private CityService cityService;

    @GetMapping(path = "/listCity")
    @ResponseBody
    public String AllAddress() {
        List<City> list = cityService.getAllAddress();
        return "city/listCity";
    }

}
