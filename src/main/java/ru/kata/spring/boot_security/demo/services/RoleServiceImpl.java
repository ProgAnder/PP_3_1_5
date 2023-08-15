package ru.kata.spring.boot_security.demo.services;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.repositories.RoleRepositoryImpl;

import java.util.List;


@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepositoryImpl repository;

    public RoleServiceImpl(RoleRepositoryImpl repository) {
        this.repository = repository;
    }

    @Override
    public List<Role> findAllRoles() {
        return repository.findAllRoles();
    }

    @Override
    public void addRole(Role role) {
        repository.addRole(role);
    }

    @Override
    public Role findRoleByName(String name) {
        return repository.findRoleByName(name);
    }

    @Override
    public Role findRoleById(int id) {
        return repository.findRoleById(id);
    }
}
