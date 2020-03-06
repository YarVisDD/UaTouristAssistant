package com.main.uatouristassistant.controller;

import com.main.uatouristassistant.entity.Address;
import com.main.uatouristassistant.entity.City;
import com.main.uatouristassistant.repository.AddressRepository;
import com.main.uatouristassistant.repository.CityRepository;
import com.main.uatouristassistant.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/address")
public class AddressController {
   /* @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private CityRepository cityRepository;*/

    @Autowired
    private AddressService addressService;

    @PostMapping("/addAddress")
    @ResponseBody
    public String addAddress(@RequestParam String cityName,
                             @RequestParam String streetName,
                             @RequestParam String numberHouse) {
        addressService.saveAddress(cityName, streetName, numberHouse);
        return "redirect:address/show-addresses";
    }

    @RequestMapping("/add-address")

    public String addAddressPage(HttpServletRequest request) {
        return "address/add-address";
    }

    @GetMapping("/show-addresses")
    public String showAllPlacesPage(HttpServletRequest request) {
        request.setAttribute("addres", addressService.getAllAddresses());
        return "addres/show-addresses";
    }

    @GetMapping("/listAddresses")
    @ResponseBody
    public String llAddresses() {
        String list = addressService.getAllAddresses();
        return "addres/listAddress";
    }
}
