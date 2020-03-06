package com.main.uatouristassistant.controller;

import com.main.uatouristassistant.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServlet;

@Controller
@RequestMapping("/city")
public class CityController extends HttpServlet {

    @Autowired
    private CityService cityService;

    @GetMapping("/listCity")
    @ResponseBody
    public String AllAddresses() {
        String list = cityService.getAllAddresses();
        return "city/listCity";
    }
}
