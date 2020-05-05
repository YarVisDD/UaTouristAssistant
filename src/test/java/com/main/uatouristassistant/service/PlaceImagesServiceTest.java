package com.main.uatouristassistant.service;

import com.main.uatouristassistant.entity.PlaceImage;
import com.main.uatouristassistant.repository.PlaceImageRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class PlaceImagesServiceTest {

    @Autowired
    private PlaceImagesService imagesService;

    @MockBean
    private PlaceImageRepository mockImageRepository;

    private List<PlaceImage> imageList;

    private MultipartFile file;

    @Test
    void saveImage() {
    }

    @Test
    void getAllImage() {
        Mockito.when(mockImageRepository.findAll()).thenReturn(imageList);
        imagesService.getAllImage();

        Mockito.verify(mockImageRepository, Mockito.times(1)).findAll();
    }

    @Test
    void getImageByIdPlace() {
        Mockito.when(mockImageRepository.findByPlaceIdPlace(Mockito.anyLong())).thenReturn(imageList);
        imagesService.getImageByIdPlace(Mockito.anyLong());

        Mockito.verify(mockImageRepository, Mockito.times(1))
                .findByPlaceIdPlace(Mockito.anyLong());
    }
}