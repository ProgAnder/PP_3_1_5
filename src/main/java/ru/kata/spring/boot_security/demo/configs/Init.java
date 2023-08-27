package ru.kata.spring.boot_security.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.util.HashSet;
import java.util.Set;

@Component
public class Init implements ApplicationRunner {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public Init(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    public void run(ApplicationArguments args) throws Exception {
        if (roleService.findAllRoles().isEmpty()) {
            Role admin = new Role("ROLE_ADMIN");
            Role user = new Role("ROLE_USER");


//            User.builder().id(null).username("admin@mail.ru").password("100").
//                    firstName("Иванов").lastName("Петр").age((byte)25).roles(new HashSet<>(Set.of(admin))).build();
//
//            User.builder().id(null).username("user@mail.ru").password("100").
//                    firstName("Петров").lastName("Иван").age((byte)50).roles(new HashSet<>(Set.of(user))).build();



            User newAdmin = new User("admin@mail.ru", "100",
                    "Иванов", "Петр", (byte) 50, new HashSet<>(Set.of(admin)));

            User newUser = new User("user@mail.ru", "100",
                    "Петров", "Иван", (byte) 50, new HashSet<>(Set.of(user)));

            userService.addUser(newAdmin);
            userService.addUser(newUser);

        }
    }
}