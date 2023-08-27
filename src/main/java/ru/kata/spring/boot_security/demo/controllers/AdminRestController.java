package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.util.*;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api")
public class AdminRestController {

    private final UserService userService;
    private final RoleService roleService;

    public AdminRestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public ResponseEntity<List<User>> getUserList() {
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }


    @GetMapping("/role")
    public ResponseEntity<List<Role>> getRoleList() {
        return new ResponseEntity<>(roleService.findAllRoles(), HttpStatus.OK);
    }


    @PostMapping("/admin")
    public ResponseEntity<HttpStatus> saveNewUser(@RequestBody User user) {
        user.setRoles(user.getRoles().stream()
                .map(Role::getId)
                .map(roleService::findRoleById)
                .collect(Collectors.toSet()));
        userService.addUser(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }


    @GetMapping("/admin/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        return new ResponseEntity<>(userService.findUserById(id), HttpStatus.OK);
    }


    @PatchMapping("/admin/edit")
    public ResponseEntity<HttpStatus> updateUser(@RequestBody User user) {
        user.setRoles(user.getRoles().stream()
                .map(Role::getId)
                .map(roleService::findRoleById)
                .collect(Collectors.toSet()));
        userService.updateUser(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }


    @DeleteMapping("/admin/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") Integer id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
