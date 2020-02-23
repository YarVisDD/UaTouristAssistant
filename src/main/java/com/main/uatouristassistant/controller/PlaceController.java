package com.main.uatouristassistant.controller;

import com.main.uatouristassistant.entity.*;
import com.main.uatouristassistant.repository.AddressRepository;
import com.main.uatouristassistant.repository.CityRepository;
import com.main.uatouristassistant.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(path = "/place")
public class PlaceController {
    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CityRepository cityRepository;

    @Value("${upload.path}")
    private String imagePath;

    @PostMapping(path = "/addPlace")
    @ResponseBody
    public String addPlace(@RequestParam String placeName,
                           @RequestParam String placeDescription,
                           @RequestParam PlaceType placeType,
                           @RequestParam("file") MultipartFile image,
                           @RequestParam String userName,
                           @RequestParam String cityName,
                           @RequestParam String streetName,
                           @RequestParam String numberHouse) throws IOException {

        City city;
        if (cityRepository.findByCityName(cityName) == null) {
            city = new City();
            city.setCityName(cityName);
            cityRepository.save(city);
        } else {
            city = cityRepository.findByCityName(cityName);
        }

        Address address;
        if (addressRepository.findByStreetAndNumberHouse(streetName, numberHouse) == null) {
            address = new Address();
            address.setStreet(streetName);
            address.setNumberHouse(numberHouse);
            address.setStreet(streetName);
            address.setNumberHouse(numberHouse);
            address.setCity(city);
            addressRepository.save(address);
        } else {
            address = addressRepository.findByStreetAndNumberHouse(streetName, numberHouse);
        }

        Place place = new Place();
        place.setPlaceName(placeName);
        place.setPlaceDescription(placeDescription);
        place.setPlaceType(placeType);

        if (image != null && !image.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(imagePath);

            if (uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidImage = UUID.randomUUID().toString();
            String resultImageName = uuidImage + "." + image.getOriginalFilename();

            image.transferTo(new File(imagePath + "/" + resultImageName));

            place.setImagePath(resultImageName);
        }

        place.setUserName(userName);
        place.setAddress(address);
        placeRepository.save(place);
        return place.toString();
    }

    @GetMapping(path = "/listAllPlace")
    @ResponseBody
    public String getAllPlace() {
        List<Place> placeList = placeRepository.findAll();
        return placeList.toString();
    }
}
