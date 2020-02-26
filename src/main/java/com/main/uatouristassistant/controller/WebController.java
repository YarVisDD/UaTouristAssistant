package com.main.uatouristassistant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {
    @GetMapping("/")
    public String MainPage() {
        return "index";
    }

    @RequestMapping("/")
    public void handleRequest() {
        throw new RuntimeException("UA Tourist exception");
    }
/*
    The code below was added for testing
*/
//    @Autowired
//    private UserRepository userRepository;
//
//    @GetMapping("/registration")
//    public String addUser(@RequestParam String login,
//                          @RequestParam String password,
//                          @RequestParam String email,
//                          @RequestParam UserRoles userRole) {
//        User user = new User(login, password, email, userRole);
//        userRepository.save(user);
//        return "index";
//    }
//
//    @GetMapping("/login")
//    public String login(@RequestParam String Login, @RequestParam String Password) {
//        UserController userController = new UserController();
//        return "Logged In!";
//    }
}
