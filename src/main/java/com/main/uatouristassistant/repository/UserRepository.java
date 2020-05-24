package com.main.uatouristassistant.repository;

import com.main.uatouristassistant.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin(String login);
    boolean existsByLogin(String login);
    boolean existsByLoginOrEmail(String login, String Email);
    boolean existsByLoginAndPassword(String login, String password);
}
