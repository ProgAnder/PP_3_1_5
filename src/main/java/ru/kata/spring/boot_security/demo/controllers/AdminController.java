package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.services.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.services.UserServiceImpl;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class AdminController {

    private final UserServiceImpl userService;
    private final RoleServiceImpl roleService;

    public AdminController(UserServiceImpl userService, RoleServiceImpl roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String getUserList(Model model) {
        model.addAttribute("users", userService.findAllUsers());
        return "index";
    }

    @GetMapping("/admin/new")
    public ModelAndView newUser() {
        User user = new User();
        ModelAndView mav = new ModelAndView("new");
        mav.addObject("newuser", user);
        List<Role> roles = (List<Role>) roleService.findAllRoles();
        mav.addObject("roleList", roles);
        return mav;
    }

    @PostMapping("/admin")
    public String saveNewUser(@ModelAttribute("newuser") User user) {
        Set<Role> roles = new HashSet<>();
        var roleSet = Set.copyOf(user.getRoles());
        for (Role role : roleSet) {
            Role role1 = roleService.findRoleById(Integer.parseInt(role.getName()));
            roles.add(role1);
        }
        user.setRoles(roles);
        userService.addUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/{id}/edit")
    public String editUser(Model model, @PathVariable("id") Integer id) {
        User user = userService.findUserById(id);
        model.addAttribute("updateduser", user);
        model.addAttribute("roleList", roleService.findAllRoles());
        return "edit";
    }

    @PatchMapping("/admin/{id}")
    public String updateUser(@ModelAttribute("updateduser") User user, @PathVariable("id") int id) {
        Set<Role> roles = new HashSet<>();
        var roleSet = Set.copyOf(user.getRoles());
        for (Role role : roleSet) {
            Role role1 = roleService.findRoleById(Integer.parseInt(role.getName()));
            roles.add(role1);
        }
        user.setRoles(roles);
        user.setId(id);
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/admin/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }
}

