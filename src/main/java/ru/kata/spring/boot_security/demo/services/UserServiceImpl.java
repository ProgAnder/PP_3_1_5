package ru.kata.spring.boot_security.demo.services;


import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository repository, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;

    }

    @Override
    public List<User> findAllUsers() {
        return repository.findAllUsers();
    }

    @Override
    public User findUserById(Integer id) {
        return repository.findUserById(id);
    }

    @Override
    @Transactional
    public void addUser(User user) {
//        Set<Role> roles = new HashSet<>();
//        for (Role role : userDTO.getRoles()) {
//            roles.add(roleService.findRoleById(role.getId()));
//        }
//        user.setRoles(roles);
/*        user.setRoles(userDTO.getRoles().stream()
                .map(Role::getId)
                .map(roleService::findRoleById)
                .collect(Collectors.toSet()));*/


        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.addUser(user);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.updateUser(user);
    }

    @Override
    @Transactional
    public void deleteUserById(Integer id) {
        repository.deleteUserById(id);
    }

    @Transactional
    public UserDetails loadUserByUsername(String username) {
        return repository.loadUserByUsername(username);
    }


}
