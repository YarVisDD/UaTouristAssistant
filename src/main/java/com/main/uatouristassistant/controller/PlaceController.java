package com.main.uatouristassistant.controller;

import com.main.uatouristassistant.entity.Place;
import com.main.uatouristassistant.entity.PlaceImage;
import com.main.uatouristassistant.entity.PlaceType;
import com.main.uatouristassistant.service.PlaceImagesService;
import com.main.uatouristassistant.service.PlaceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/place")
public class PlaceController extends HttpServlet {

    @Autowired
    private PlaceService placeService;

    @Autowired
    PlaceImagesService imagesService;

    @PostMapping("/save-place")
    public String addPlace(@RequestParam String placeName,
                           @RequestParam String placeDescription,
                           @RequestParam PlaceType placeType,
                           @RequestParam("image") MultipartFile[] images,
                           String login,
                           @RequestParam String cityName,
                           @RequestParam String streetName,
                           @RequestParam String numberHouse) {

        placeService.savePlace(placeName, placeDescription, placeType, images, login, cityName, streetName, numberHouse);

        return "redirect:/place/show-places";
    }

    @RequestMapping("/add-place")
    public String addPlacePage(HttpServletRequest request) {
        return "/place/add-place";
    }

    @PostMapping("/show-places-by-type")
    public String showPlaceByType(PlaceType placeType, String city, HttpServletRequest request) {
        if (placeType.equals(PlaceType.ALL)) {
            request.setAttribute("places", placeService.findByAddress_City_CityName(city));
        } else {
            request.setAttribute("places", placeService.findByPlaceTypeAndAddress_City_CityName(placeType, city));
        }

        request.setAttribute("images", imagesService.getAllImage());

        return "/place/show-places-by-type";
    }

    @GetMapping("/show-places")
    public String showAllPlacesPage(HttpServletRequest request) {
        request.setAttribute("places", placeService.findAll());
        return "/place/show-places";
    }

    @GetMapping("/show-place/{idPlace}")
    public String getPlace(@PathVariable Long idPlace, HttpServletRequest request) {
        Place place = placeService.getPlaceById(idPlace);
        List<PlaceImage> images = imagesService.gteImageByIdPlace(idPlace);
        request.setAttribute("images", images);
        request.setAttribute("place", place);
        return "/place/show-place";
    }

    @RequestMapping("/delete-place")
    public String deletePlace(@RequestParam Long idPlace, HttpServletRequest request) {
        placeService.deletePlace(idPlace);
        return "redirect:/place/show-places";
    }
}
