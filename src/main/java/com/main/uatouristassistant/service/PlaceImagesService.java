package com.main.uatouristassistant.service;

import com.main.uatouristassistant.entity.Place;
import com.main.uatouristassistant.entity.PlaceImage;
import com.main.uatouristassistant.repository.PlaceImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
public class PlaceImagesService {

    @Autowired
    private PlaceImageRepository placeImageRepository;

    public void saveImage(Place place, MultipartFile[] files) {

        for (MultipartFile file : files) {

            if (file != null && !file.getOriginalFilename().isEmpty()) {
                try {
                    PlaceImage img = new PlaceImage();
                    img.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
                    img.setPlace(place);
                    placeImageRepository.save(img);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<PlaceImage> getAllImage() {
        return placeImageRepository.findAll();
    }
}
