package com.main.uatouristassistant.service;

import com.main.uatouristassistant.entity.User;
import com.main.uatouristassistant.entity.UserRoles;
import com.main.uatouristassistant.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public boolean addUser(@RequestParam String login,
                           @RequestParam String password,
                           @RequestParam String email,
                           @RequestParam UserRoles userRole,
                           @RequestParam(required = false) String firstName,
                           @RequestParam(required = false) String lastName,
                           @RequestParam(required = false) String dateOfBirth) {
        String addUserInfo;
        boolean checkLogin = false;
        User user = new User();
        boolean userLoginDb = userRepository.existsByLoginOrEmail(login, email);

        if (userLoginDb) {
            addUserInfo = login + " - already REGISTERED. Please try with another LOGIN";
            log.warn("WARNING!!! User tried to be registered with existing login. Login: {}", login);
        } else {
            user.setLogin(login);
            user.setPassword(DigestUtils.sha256Hex(password));
            user.setEmail(email);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setDateOfBirth(dateOfBirth);
            user.setUserRole(userRole);
            userRepository.save(user);
            checkLogin = true;
            addUserInfo = login + " has been REGISTERED!";
            log.info("INFO!!! User has been created. User: {}", user);
        }
        System.out.println(addUserInfo);
        return checkLogin;
    }

    public boolean userLogin(@RequestParam String login, @RequestParam String password) {
        boolean loginInfo = false;
        boolean checkLogin = userRepository.existsByLoginAndPassword(login, DigestUtils.sha256Hex(password));
        if (checkLogin) {
            loginInfo = true;
            log.info("INFO!!! User logged in: {}", login);
        } else {
            System.out.println("Password or Login is incorrect");
            log.warn("WARNING!!! Password or Login is incorrect. Login: {}", login);
        }
        return loginInfo;
    }

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUser(@PathVariable String login) {
        return userRepository.findByLogin(login);
    }

    public boolean deleteUser(@PathVariable String login) {
        try {
            User user = userRepository.findByLogin(login);
            userRepository.delete(user);
            log.info("INFO!!! User has ben deleted: {}", user);
            System.out.println("The user with login " + login + " has been deleted");
            return true;
        } catch (Exception ex) {
            log.error("ERROR!!! Tried to delete user which does not exist: {}", login);
            System.out.println("The user with login " + login + " does not exist!");
            return false;
        }
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User findUser(@RequestParam Long userId) {
        return userRepository.findByUserId(userId);
    }
}
