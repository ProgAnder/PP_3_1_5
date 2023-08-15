package ru.kata.spring.boot_security.demo.services;


import ru.kata.spring.boot_security.demo.entities.User;

import java.util.List;

public interface UserService {

    List<User> findAllUsers();

    User findUserById(Integer id);

    void addUser(User user);

    void updateUser(User user);

    void deleteUserById(Integer id);


}
