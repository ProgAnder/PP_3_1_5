package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.kata.spring.boot_security.demo.services.UserServiceImpl;
import java.security.Principal;

@Controller
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String showUserForUser(Model model, Principal principal) {
        model.addAttribute("user", userService.loadUserByUsername(principal.getName()));
        model.addAttribute("loginusername", principal.getName());
        return "user";
    }

    @GetMapping("/user/{id}")
    public String showUser(@PathVariable(value = "id") int id, Model model, Principal principal) {
        model.addAttribute("user", userService.findUserById(id));
        model.addAttribute("loginusername", principal.getName());
        return "user";
    }
}
