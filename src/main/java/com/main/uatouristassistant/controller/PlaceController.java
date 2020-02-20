package com.main.uatouristassistant.controller;

import com.main.uatouristassistant.entity.Images;
import com.main.uatouristassistant.entity.Place;
import com.main.uatouristassistant.entity.PlaceType;
import com.main.uatouristassistant.repository.ImageRepository;
import com.main.uatouristassistant.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.UUID;

@Controller
@RequestMapping(path = "/place")
public class PlaceController {
    @Autowired
    private PlaceRepository placeRepository;
    @Autowired
    private ImageRepository imageRepository;

    @Value("${upload.path}")
    private String imagePath;

    @PostMapping(path = "/addPlace")
    @ResponseBody
    public String addPlace(@RequestParam String placeName,
                           @RequestParam String placeDescription,
                           @RequestParam PlaceType placeType,
                           @RequestParam("file") MultipartFile[] images,
                           @RequestParam String userName,
                           @RequestParam String address) throws IOException {

        Place place = new Place();
        place.setPlaceName(placeName);
        place.setPlaceDescription(placeDescription);
        place.setPlaceType(placeType);

        for (MultipartFile image : images) {

            if (image != null && !image.getOriginalFilename().isEmpty()) {
                File uploadDir = new File(imagePath);

                if (uploadDir.exists()) {
                    uploadDir.mkdir();
                }

                String uuidImage = UUID.randomUUID().toString();
                String resultImageName = uuidImage + "." + image.getOriginalFilename();

                image.transferTo(new File(imagePath + "/" + resultImageName));

                imageRepository.save(new Images(resultImageName));
            }
        }

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
