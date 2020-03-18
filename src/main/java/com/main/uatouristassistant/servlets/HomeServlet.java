package com.main.uatouristassistant.servlets;

import com.main.uatouristassistant.entity.City;
import com.main.uatouristassistant.entity.Place;
import com.main.uatouristassistant.repository.CityRepository;
import com.main.uatouristassistant.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/homepage")
public class HomeServlet extends HttpServlet {
    @Autowired
    CityRepository cityRepository;
    @Autowired
    PlaceRepository placeRepository;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<City> listCities = cityRepository.findAll();
        req.setAttribute("listCities", listCities);

        req.getServletContext().getRequestDispatcher("/homepage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + "/homepage");
    }
}
