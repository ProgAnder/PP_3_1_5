package ru.kata.spring.boot_security.demo.repositories;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class RoleRepositoryImpl implements RoleRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Role> findAllRoles() {
        return em.createQuery("select r from Role r", Role.class).getResultList();
    }

    @Override
    public void addRole(Role role) {
        em.persist(role);
    }

    @Override
    public Role findRoleById(int id) {
        TypedQuery<Role> query = em.createQuery("select r from Role r where r.id = :p", Role.class);
        return query.setParameter("p", id).getSingleResult();
    }


    @Override
    public Role findRoleByName(String name) {
        TypedQuery<Role> query = em.createQuery("select r from Role r where r.name = :p", Role.class);
        return query.setParameter("p", name).getSingleResult();
    }


}


