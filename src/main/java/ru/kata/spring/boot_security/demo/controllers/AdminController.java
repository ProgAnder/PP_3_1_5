package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;
import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String getUserList(Model model, Principal principal) {
        model.addAttribute("users", userService.findAllUsers());
        User user = (User) userService.loadUserByUsername(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("newuser", new User());
        List<Role> roles = (List<Role>) roleService.findAllRoles();
        model.addAttribute("roleList", roles);
        return "index";
    }

    @PostMapping("/admin")
    public String saveNewUser(@ModelAttribute("newuser") User user) {
        Set<Role> roles = new HashSet<>();
        var roleSet = user.getRolesIds();
        for (Integer roleId : roleSet) {
            Role role1 = roleService.findRoleById(roleId);
            roles.add(role1);
        }
        user.setRoles(roles);
        userService.addUser(user);
        return "redirect:/admin";
    }

    @PatchMapping("/admin/edit")
    public String updateUser(@ModelAttribute("updateduser") User user) {
        Set<Role> roles = new HashSet<>();
        var roleSet = user.getRolesIds();
        for (Integer roleId : roleSet) {
            Role role1 = roleService.findRoleById(roleId);
            roles.add(role1);
        }
        user.setRoles(roles);
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/admin/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }
}