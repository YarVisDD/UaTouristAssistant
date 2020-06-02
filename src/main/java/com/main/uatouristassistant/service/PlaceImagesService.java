package com.main.uatouristassistant.service;

import com.main.uatouristassistant.entity.Place;
import com.main.uatouristassistant.entity.PlaceImage;
import com.main.uatouristassistant.repository.PlaceImageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
public class PlaceImagesService {

    @Autowired
    private PlaceImageRepository placeImageRepository;

    public void saveImage(Place place, MultipartFile[] files) {

        for (MultipartFile file : files) {
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

    public List<PlaceImage> getAllImage() {
        return placeImageRepository.findAll();
    }

    public List<PlaceImage> getImageByIdPlace(Long idPlace) {
        return placeImageRepository.findByPlaceIdPlace(idPlace);
    }

    public boolean isValidFileName(MultipartFile[] files) {
        String fileNameRegex = "([^\\s]+(\\.(?i)(jpg|png|jpeg))$)";
        Pattern pattern = Pattern.compile(fileNameRegex);

        for (MultipartFile file : files) {
            Matcher matcher = pattern.matcher(file.getOriginalFilename());

            if (!matcher.matches()) {
                return false;
            }
        }
        return true;
    }

    public boolean deleteImages(Long idPlace) {
        List<PlaceImage> images = getImageByIdPlace(idPlace);

        if (images.size() != 0) {
            for (PlaceImage image : images) {
                placeImageRepository.delete(image);
                log.info("INFO!!! Image has ben delete: {}", image.getIdImage());
                System.out.println("INFO!!! Image has ben delete " + image.getIdImage());
            }
            return true;
        } else {
            log.error("ERROR!!! Tried to delete image which does not exist.");
            System.out.println("INFO!!! Image has not ben delete");
            return false;
        }
    }
}
