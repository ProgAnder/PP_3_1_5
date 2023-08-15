package ru.kata.spring.boot_security.demo.repositories;

import ru.kata.spring.boot_security.demo.entities.Role;

import java.util.List;

public interface RoleRepository {

    List<Role> findAllRoles();

    void addRole(Role role);

    Role findRoleById(int id);

    Role findRoleByName(String name);
}
