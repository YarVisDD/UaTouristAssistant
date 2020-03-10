package com.main.uatouristassistant.controller;

import com.main.uatouristassistant.entity.City;
import com.main.uatouristassistant.entity.Place;
import com.main.uatouristassistant.entity.User;
import com.main.uatouristassistant.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(path = "/city")
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping(path = "/listCity")
    @ResponseBody
    public Iterable<City> AllCity() {
        return cityService.getAllCity();
    }

    @PostMapping("/save-city")
    public String saveCity(@ModelAttribute City city,BindingResult bindingResult,HttpServletRequest request) {
        cityService.saveCity(city);
        return "redirect:/city/show-cities";
    }

    @RequestMapping("/add-city")
    public String addCityPage(@ModelAttribute City city, HttpServletRequest requestt) {
        return "/city/add-city";
    }

    @GetMapping("/show-cities")
    public String showAllCityPage(HttpServletRequest request) {
        request.setAttribute("city", cityService.getAllCity());
        return "/city/show-cities";
    }

    @RequestMapping("/update-city/{cityName}")
    public String updateCity(@PathVariable String cityName, HttpServletRequest request) {
        request.setAttribute("city", cityService.findCity(cityName));
        return "/city/update-city";
    }
}