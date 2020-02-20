package com.main.uatouristassistant.controller;

import com.main.uatouristassistant.entity.Address;
import com.main.uatouristassistant.repository.AddressRepository;
import com.main.uatouristassistant.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/address")
public class AddressController {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private CityRepository cityRepository;

    @PostMapping(path = "addAddress")
    @ResponseBody
    public Address addAddress(@RequestParam String cityName,
                             @RequestParam String streetName,
                             @RequestParam String numberHouse) {

        Address address = new Address();
        address.setCityName(cityRepository.findByCityName(cityName));
        address.setStreetName(streetName);
        address.setNumberHouse(numberHouse);

        return addressRepository.save(address);
    }

    @GetMapping(path = "/listAddresses")
    public @ResponseBody
    Iterable<Address> getAllAddresses() {
        return addressRepository.findAll();
    }
}
