package com.main.uatouristassistant.controller;

import com.main.uatouristassistant.entity.Address;
import com.main.uatouristassistant.entity.City;
import com.main.uatouristassistant.repository.AddressRepository;
import com.main.uatouristassistant.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/address")
public class AddressController {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private CityRepository cityRepository;

    @PostMapping(path = "addAddress")
    @ResponseBody
    public String addAddress(@RequestParam String cityName,
                             @RequestParam String streetName,
                             @RequestParam String numberHouse) {

        City city;
        if (cityRepository.findByCityName(cityName) == null) {
            city = new City();
            city.setCityName(cityName);
            cityRepository.save(city);
        } else {
            city = cityRepository.findByCityName(cityName);
        }

        Address address = new Address();
        address.setCity(city);
        address.setStreet(streetName);
        address.setNumberHouse(numberHouse);
        addressRepository.save(address);

        return address.toString();
    }

    @GetMapping(path = "/listAddresses")
    @ResponseBody
    public String getAllAddresses() {
        List<Address> list = addressRepository.findAll();
        return list.toString();
    }
}
