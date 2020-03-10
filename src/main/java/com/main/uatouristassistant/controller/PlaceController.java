package com.main.uatouristassistant.controller;

import com.main.uatouristassistant.entity.Place;
import com.main.uatouristassistant.entity.PlaceType;
import com.main.uatouristassistant.service.PlaceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequestMapping("/place")
public class PlaceController extends HttpServlet {

    @Autowired
    private PlaceService placeService;

    @PostMapping("/save-place")
    public String addPlace(@RequestParam String placeName,
                           @RequestParam String placeDescription,
                           @RequestParam PlaceType placeType,
                           @RequestParam("image") MultipartFile image,
                           String login,
                           @RequestParam String cityName,
                           @RequestParam String streetName,
                           @RequestParam String numberHouse) {

        placeService.savePlace(placeName, placeDescription, placeType, image, login, cityName, streetName, numberHouse);

        return "redirect:/place/show-places";
    }

    @RequestMapping("/add-place")
    public String addPlacePage(HttpServletRequest request) {
        return "/place/add-place";
    }

    @GetMapping("/show-places")
    public String showAllPlacesPage(HttpServletRequest request) {
        request.setAttribute("places", placeService.findAll());
        return "/place/show-places";
    }

    @GetMapping("/one-place")
    @ResponseBody
    public Place getPlace(@RequestParam Long idPlace) {
        return placeService.getPlaceById(idPlace);
    }

//    @PostMapping("/update-place")
//    @ResponseBody
//    public String

    @RequestMapping("/delete-place")
    public String deletePlace(@RequestParam Long idPlace, HttpServletRequest request) {
        placeService.deletePlace(idPlace);
        return "redirect:/place/show-places";
    }
}
