package com.main.uatouristassistant.servlets;

import com.main.uatouristassistant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Autowired
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (userService.userLogin(login, password)) {
            HttpSession session = req.getSession();
            session.setMaxInactiveInterval(10*60);
            session.setAttribute("userLogin", login);
            req.getServletContext().getRequestDispatcher("/homepage").forward(req, resp);
        } else {
//            req.setAttribute("error", "Invalid Username or Password");
            resp.sendRedirect(req.getContextPath() + "/login");
        }
    }
}
