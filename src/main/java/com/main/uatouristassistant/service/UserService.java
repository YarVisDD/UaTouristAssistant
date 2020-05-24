package com.main.uatouristassistant.service;

import com.main.uatouristassistant.entity.User;
import com.main.uatouristassistant.entity.UserRoles;
import com.main.uatouristassistant.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public boolean addUser(String login, String password, String email, UserRoles userRole,
                           String firstName, String lastName, String dateOfBirth) {
        String addUserInfo;
        boolean checkLogin = false;
        User user = new User();
        boolean userLoginDb = userRepository.existsByLoginOrEmail(login, email);

        if (login.isEmpty() || password.isEmpty() || email.isEmpty()) {
            addUserInfo = "User tried to be registered with EMPTY info. \nLogin: " + login
                    + "\nPassword: " + password + "\nEmail: " + email;
            log.warn("WARNING!!! User tried to be registered with EMPTY info.\nLogin: {}\nPassword: {}\nEmail: {}",
                    login, password, email);
        } else if (userLoginDb) {
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

    public boolean userLogin(String login, String password) {
        boolean loginInfo = false;
        boolean checkLogin = userRepository.existsByLoginAndPassword(login, DigestUtils.sha256Hex(password));
        if (checkLogin) {
            loginInfo = true;
            log.info("INFO!!! User logged in: {}", login);
        } else {
            System.out.println("Password or Login is incorrect");
            log.warn("WARNING!!! Password or Login is incorrect.\nLogin: {}\nPassword: {}", login, password);
        }
        return loginInfo;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUser(String login) {
        return userRepository.findByLogin(login);
    }

    public boolean deleteUser(String login) {
        if (userRepository.existsByLogin(login)) {
            User user = userRepository.findByLogin(login);
            userRepository.delete(user);
            log.info("INFO!!! User has ben deleted: {}", user);
            System.out.println("The user with login " + login + " has been deleted");
            return true;
        } else {
            log.error("ERROR!!! Tried to delete user which does not exist: {}", login);
            System.out.println("The user with login " + login + " does not exist!");
            return false;
        }
    }

    public User saveUser(User user) {
        user.setPassword(DigestUtils.sha256Hex(user.getPassword()));
        return userRepository.save(user);
    }

    public User findUserByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public boolean existsByLogin(String login) {
        return userRepository.existsByLogin(login);
    }

    public boolean updateUser(String login, HttpServletRequest request) {
        String sessionLogin = String.valueOf(request.getSession().getAttribute("userLogin"));
        if (userRepository.existsByLogin(login) || userRepository.existsByLogin(sessionLogin)) {
            UserRoles userRole = userRepository.findByLogin(login).getUserRole();
            UserRoles sessionUserRole = userRepository.findByLogin(sessionLogin).getUserRole();
            if (login.equals(sessionLogin) ||
                    (userRole.equals(sessionUserRole) || sessionUserRole.equals(UserRoles.ADMIN))) {
                request.setAttribute("user", userRepository.findByLogin(login));
                return true;
            }
        }
        return false;
    }
}
