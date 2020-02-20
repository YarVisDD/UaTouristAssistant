package com.main.uatouristassistant.controller;

import com.main.uatouristassistant.entity.Address;
import com.main.uatouristassistant.entity.City;
import com.main.uatouristassistant.entity.Place;
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

    @PostMapping(path = "addAddress")
    @ResponseBody
    public Address addAddress(@RequestParam String cityName,
                             @RequestParam String streetName,
                             @RequestParam String numberHouse) {

        Address address = new Address();

        address.setStreetName(streetName);
        address.setNumberHouse(numberHouse);

        return addressRepository.save(address);
    }

    @GetMapping(path = "/listAllAddress")
    @ResponseBody
    public String getAllAddress() {
        Iterable<Address> addresses = addressRepository.findAll();

        for (Address a: addresses) {
            System.out.println(a);
        }

        return "";
    }
}
