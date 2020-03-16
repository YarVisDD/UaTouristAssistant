package com.main.uatouristassistant.controller;

import com.main.uatouristassistant.service.PlaceImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/place-img")
public class PlaceImageController extends HttpServlet {

    @Autowired
    private PlaceImagesService imagesService;

    @RequestMapping("/add-img")
    public String addPlacePage(HttpServletRequest request) {

        return "/place-img/add-img";
    }

    @PostMapping("/save-img")
    public String saveImage(@RequestParam("image") MultipartFile image) {
        imagesService.saveImage(image);

        return "redirect:/place-img/show-img";
    }

    @GetMapping("/show-img")
    public String showAllPlacesImage(HttpServletRequest request) {
        request.setAttribute("images", imagesService.getAllImage());

        return "/place-img/show-img";
    }
}
