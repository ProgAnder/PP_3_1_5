package ru.kata.spring.boot_security.demo.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.security.Principal;

@RestController
@RequestMapping("/api")
public class UserRestController {

    private final UserService userService;


    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public User showUserDTOForUser(Principal principal, ModelMapper modelMapper) {

        User user = (User) userService.loadUserByUsername(principal.getName());

        return modelMapper.map(user, User.class);

//        UserDTO userDTO = new UserDTO();
//        userDTO.setId(user.getId());
//        userDTO.setAge(user.getAge());
//        userDTO.setFirstName(user.getFirstName());
//        userDTO.setLastName(user.getLastName());
//        userDTO.setUsername(user.getUsername());
//        userDTO.setPassword(user.getPassword());
//        userDTO.setRoles(user.getRoles());
//        return userDTO;
    }
}
