package ru.kata.spring.boot_security.demo.repositories;

import ru.kata.spring.boot_security.demo.entities.Role;

import java.util.List;

public interface RoleRepository {

    List<Role> findAllRoles();

    Role findRoleById(int id);

}
