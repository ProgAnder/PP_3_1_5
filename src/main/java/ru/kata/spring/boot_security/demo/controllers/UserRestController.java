package ru.kata.spring.boot_security.demo.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.dto.UserDTO;
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
    public UserDTO showUserDTOForUser(Principal principal, ModelMapper modelMapper) {

        User user = (User) userService.loadUserByUsername(principal.getName());

        return modelMapper.map(user, UserDTO.class);

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
