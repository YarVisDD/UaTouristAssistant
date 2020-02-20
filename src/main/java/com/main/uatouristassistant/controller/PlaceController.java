package com.main.uatouristassistant.controller;

import com.main.uatouristassistant.entity.Place;
import com.main.uatouristassistant.entity.PlaceType;
import com.main.uatouristassistant.entity.UserRoles;
import com.main.uatouristassistant.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/place")
public class PlaceController {
    @Autowired
    private PlaceRepository placeRepository;

    @PostMapping(path = "/addPlace")
    @ResponseBody
    public String addPlace(@RequestParam String placeName,
                           @RequestParam String placeDescription,
                           @RequestParam PlaceType placeType,
                           @RequestParam String imageName,
                           @RequestParam String userName,
                           @RequestParam String address) {

        Place place = new Place();
        place.setPlaceName(placeName);
        place.setPlaceDescription(placeDescription);
        place.setPlaceType(placeType);
        place.setImageName(imageName);
        place.setUserName(userName);
        place.setAddress(address);
        placeRepository.save(place);
        return "place create";
    }

    @GetMapping(path = "/listAllPlace")
    @ResponseBody
    public Iterable<Place> getAllPlace() {
        return placeRepository.findAll();
    }
}
