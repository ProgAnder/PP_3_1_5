package ru.kata.spring.boot_security.demo.repositories;

import org.springframework.security.core.userdetails.UserDetails;
import ru.kata.spring.boot_security.demo.entities.User;

import java.util.List;

public interface UserRepository {

    UserDetails loadUserByUsername(String username);

    void addUser(User user);

    List<User> findAllUsers();

    User findUserById(int id);

    void deleteUserById(int id);

    void updateUser(User user);
}
