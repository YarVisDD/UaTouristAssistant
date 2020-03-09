package com.main.uatouristassistant.controller;

import com.main.uatouristassistant.repository.PlaceRepository;
import com.main.uatouristassistant.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@Controller
public class WebController {

    @Autowired
    PlaceRepository placeRepository;

    @Autowired
    UserRepository userRepository;

    @Value("${upload.path}")
    private String imagePath;

    String projectDir = new File("").getAbsolutePath();

    @RequestMapping("/")
    public void handleRequest(HttpServletRequest request) {
        throw new RuntimeException("UA Tourist exception");
    }

    @GetMapping("/")
    public String mainPage() {
        return "index";
    }
}
