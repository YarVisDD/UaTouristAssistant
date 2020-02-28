package com.main.uatouristassistant.controller;

import com.main.uatouristassistant.entity.Address;
import com.main.uatouristassistant.entity.City;
import com.main.uatouristassistant.entity.Place;
import com.main.uatouristassistant.entity.PlaceType;
import com.main.uatouristassistant.repository.AddressRepository;
import com.main.uatouristassistant.repository.CityRepository;
import com.main.uatouristassistant.repository.PlaceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServlet;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@Controller
@RequestMapping(path = "/place")
public class PlaceController extends HttpServlet {
    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CityRepository cityRepository;

    @Value("${upload.path}")
    private String imagePath;

    String projectDir = new File("").getAbsolutePath();

    @PostMapping(path = "/addPlace")
    public String addPlace(@RequestParam String placeName,
                           @RequestParam String placeDescription,
                           @RequestParam PlaceType placeType,
                           @RequestParam("image") MultipartFile image,
                           @RequestParam String userName,
                           @RequestParam String cityName,
                           @RequestParam String streetName,
                           @RequestParam String numberHouse) throws IOException {

        City city;

        if (cityRepository.findByCityName(cityName) == null) {
            city = new City();
            city.setCityName(cityName);
            cityRepository.save(city);
            log.info("INFO!!! City has been created. City: {}", cityName);
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
            File uploadDir = new File(projectDir + imagePath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidImage = UUID.randomUUID().toString();
            String resultImageName = uuidImage + "." + image.getOriginalFilename();

            image.transferTo(new File(projectDir + imagePath + "/" + resultImageName));

            place.setImagePath(resultImageName);
        }

        place.setUserName(userName);
        place.setAddress(address);
        placeRepository.save(place);

        return "redirect:/show-places";
    }

    @GetMapping(path = "/allPlace")
    @ResponseBody
    public Iterable<Place> getAllPlace() {
        return placeRepository.findAll();
    }

    @GetMapping(path = "/onePlace")
    @ResponseBody
    public Place getPlace(@RequestParam Long idPlace) {
        return placeRepository.findPlaceByIdPlace(idPlace);
    }

    @DeleteMapping(path = "/deletePlace")
    @ResponseBody
    public String deletePlace(@RequestParam Long idPlace) {
        try {
            Place place = placeRepository.findPlaceByIdPlace(idPlace);
            placeRepository.deleteById(idPlace);
            log.info("INFO!!! Place has ben delete: {}", place);
            return "The place with idPlace " + idPlace + " has been delete";
        } catch (Exception ex) {
            log.error("ERROR!!! Tried to delete user which does not exist: {}", idPlace);
            return "The place with idPlace " + idPlace + " does not exist!";
        }
    }
}
