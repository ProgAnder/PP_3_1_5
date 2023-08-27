package ru.kata.spring.boot_security.demo.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;
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
    public List<User> getUserList() {
        return userService.findAllUsers();
    }


    @GetMapping("/role")
    public List<Role> getRoleList() {
        return roleService.findAllRoles();
    }


    @PostMapping("/admin")
    @Transactional
    public String saveNewUser(@RequestBody User user) {
              Set<Role> roles = new HashSet<>();
        user.setRoles(user.getRoles().stream()
                .map(Role::getId)
                .map(roleService::findRoleById)
                .collect(Collectors.toSet()));
        userService.addUser(user);
        return "redirect:/admin";
    }


    @GetMapping("/admin/{id}")
    public User getUserById(@PathVariable Integer id) {

        System.out.println("123456+++++++++++++++++++++++++++++++++++++++++++++");
        return userService.findUserById(id);
    }



    @PatchMapping("/admin/edit")
    public String updateUser(@RequestBody User user) {

        System.out.println(user);


        Set<Role> roles = new HashSet<>();
        user.setRoles(user.getRoles().stream()
                .map(Role::getId)
                .map(roleService::findRoleById)
                .collect(Collectors.toSet()));

        userService.updateUser(user);

//        Set<Role> roles = new HashSet<>();
//        var roleSet = user.getRolesIds();
//        for (Integer roleId : roleSet) {
//            Role role1 = roleService.findRoleById(roleId);
//            roles.add(role1);
//        }
//        user.setRoles(roles);
//        userService.updateUser(user);
        return "redirect:/admin";
    }







    @DeleteMapping("/admin/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }




}
